package com.springboot.controller;

import com.springboot.entity.User;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private UserService userService;

	@GetMapping("/health-check")
	public String healthCheckup() {
		return "OK";
	}

	@PostMapping("/create-user")
	public void setUser(@RequestBody User user) {
		System.out.println("user =>> " + user);
		user.setDate(new Date());
		user.setRoles(Arrays.asList("USER"));
		userService.save(user, "new");
	}
}