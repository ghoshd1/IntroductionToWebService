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
import com.poc.code.introwebservice.model.Post;
import com.poc.code.introwebservice.model.User;
import com.poc.code.introwebservice.repository.PostJPARepository;
import com.poc.code.introwebservice.repository.UserJPARepository;

@RestController
public class UserJPAController {
	
	@Autowired
	private UserJPARepository userRepo;
	
	@Autowired
	private PostJPARepository postRepo;

	@GetMapping(path = "/jpa/users")
	public List<User> getAllUser() {
		return userRepo.findAll();
	}
	
	@GetMapping(path = "/jpa/user/{id}")
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
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
		  User savedUser = userRepo.save(user);
		  URI uri = ServletUriComponentsBuilder
				 .fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(savedUser.getId())
				 .toUri();
		  return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path = "/jpa/user/{id}")
	public void deleteUser(@PathVariable int id){
		 userRepo.deleteById(id);
	}
	
	@GetMapping(path = "/jpa/user/{id}/posts")
	public List<Post> getPostForUser(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			List<Post> postList = user.get().getPost();
			return postList;
		}
		throw new UserNotFoundException(String.format("id = "+id));
		
	}
	
	@PostMapping(path = "/jpa/user/{id}/posts")
	public ResponseEntity<Object> savePostForUser(@PathVariable int id,@RequestBody Post post) {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			post.setUser(user.get());
			postRepo.save(post);
			  URI uri = ServletUriComponentsBuilder
						 .fromCurrentRequest()
						 .path("/{id}")
						 .buildAndExpand(user.get().getId())
						 .toUri();
				  return ResponseEntity.created(uri).build();
			
		}
		throw new UserNotFoundException(String.format("id = "+id));
		
	}
}
