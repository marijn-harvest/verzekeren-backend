package com.harvest.verzekeren.user;

import java.security.Principal;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
	private UserRepository userRepository;

	private ModelMapper modelMapper;

	@Autowired
	public UserController(UserRepository userRepository, ModelMapper modelMapper)
	{
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/principal")
	public Principal getPrincipal(Principal principal)
	{
		return principal;
	}

	@GetMapping("/user")
	public JsonUser getUser(Authentication authentication)
	{
		MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
		Optional<User> userOptional = userRepository.findById(principal.getId());

		return userOptional.map(user -> modelMapper.map(user, JsonUser.class)).orElse(null);
	}

	@PostMapping("/user")
	public void createUser(@RequestBody JsonUser jsonUser)
	{
		User user = modelMapper.map(jsonUser, User.class);
		userRepository.save(user);
	}

	@PutMapping("/user")
	public void editUser(@RequestBody JsonUser jsonUser, Authentication authentication)
	{
		User user = modelMapper.map(jsonUser, User.class);

		MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
		Optional<User> userToSaveOptional = userRepository.findById(principal.getId());

		if (userToSaveOptional.isPresent())
		{
			User userToSave = userToSaveOptional.get();

			if (user.getPassword() != null)
			{
				BeanUtils.copyProperties(user, userToSave, "id", "autoVerzekering");
			}
			else
			{
				BeanUtils.copyProperties(user, userToSave, "password", "id", "autoVerzekering");
			}
			userRepository.save(userToSave);
		}
	}
}