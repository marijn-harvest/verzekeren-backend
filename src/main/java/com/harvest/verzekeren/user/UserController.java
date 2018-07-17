package com.harvest.verzekeren.user;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/user")
	public Principal getUser(Principal user)
	{
		return user;
	}

	@PostMapping("/user")
	public void createUser(@RequestBody JsonUser jsonUser)
	{
		User user = modelMapper.map(jsonUser, User.class);
		repository.save(user);
	}
}