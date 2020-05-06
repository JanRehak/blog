package com.tieto.javabootcamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tieto.javabootcamp.model.User;
import com.tieto.javabootcamp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
	
	public UserController(@Autowired UserService userService) {
		this.userService = userService;
	}

	@GetMapping()
	public List<User> getUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping(params={"userName"})
	public List<User> getUsersByName(
			@RequestParam(name="userName", required=true) String name) {
		return userService.getUsersByName(name);
	}
	
	@GetMapping("/{userName}")
	public User getUser(@PathVariable("userName") String name) {
		return userService.getUser(name);
	}
	
	@CrossOrigin("*")
	@PostMapping()
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.removeUser(userId);
	}

	@PutMapping("/{userId}")
	public User updateUser(@PathVariable Long userId, @RequestBody User user) {
		return userService.updateUser(userId, user);
	}


}
