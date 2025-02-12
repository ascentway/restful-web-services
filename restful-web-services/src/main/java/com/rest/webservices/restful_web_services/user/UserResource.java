package com.rest.webservices.restful_web_services.user;

//import static org.springframework.hateos.sever.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import jakarta.validation.Valid;

@RestController

public class UserResource {

	private UserDaoService userservice;
	
	public UserResource(UserDaoService userservice) {

		this.userservice = userservice;
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUser()
	{
		return userservice.findAll();
	}
	
	//GET /users
	
	//http://localhost:8080/users
	
	//Entity  Model
	//WebMVCLink Builder
	
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUsers(@PathVariable int id){
		User user = userservice.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id:"+id);
		
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass().retrieveAllUser()));
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	

	
//	@PostMapping("/users")
//	public void createUser(@RequestBody User user) {
//		userservice.save(user);
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser (@Valid @RequestBody User user)
	{
		User savedUser = userservice.save(user);
		// /users/4 => /users /{id},	user.getid
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		userservice.deleteById(id);
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = userservice.findOne(id);
		if(user==null)
		{
			throw new UserNotFoundException("id:"+id);
		}
			EntityModel<User> entityModel= EntityModel.of(user);
			return entityModel;
		
	}
}


