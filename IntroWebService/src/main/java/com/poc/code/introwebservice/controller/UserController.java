package com.poc.code.introwebservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.poc.code.introwebservice.exception.UserNotFoundException;
import com.poc.code.introwebservice.model.User;
import com.poc.code.introwebservice.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepo;

	@GetMapping(path = "/users")
	public List<User> getAllUser() {
		return userRepo.getAllUser();
	}
	
	@GetMapping(path = "/user/{id}")
	public EntityModel<User>  getUserById(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			
		  // HATEOAS :: Hypermedia as the Engine of Application State
			EntityModel<User> model = EntityModel.of(user.get());
			WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUser());
			model.add(linkTo.withRel("all-users"));
			return model;
		}
		throw new UserNotFoundException(String.format("id = "+id));
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
		  User savedUser = userRepo.save(user);
		  URI uri = ServletUriComponentsBuilder
				 .fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(savedUser.getId())
				 .toUri();
		  return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path = "/user/{id}")
	public void deleteUser(@PathVariable int id){
		Optional<User> deleteduser = userRepo.deleteById(id);
		
		if(deleteduser.isEmpty())
			throw new UserNotFoundException(String.format("id = "+id));
	}
	
	
	
	
}
