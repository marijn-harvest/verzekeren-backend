package com.harvest.verzekeren.auto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harvest.verzekeren.user.MyUserPrincipal;

@RestController
public class AutoVerzekeringController
{
	@Autowired
	private AutoVerzekeringRepository autoVerzekeringRepository;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/auto-verzekering")
	public void createAutoVerzekering(@RequestBody JsonAutoVerzekering jsonAutoVerzekering, Authentication authentication)
	{
		MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();

		AutoVerzekering autoVerzekering = modelMapper.map(jsonAutoVerzekering, AutoVerzekering.class);
		autoVerzekering.setUserId(principal.getId());

		autoVerzekeringRepository.save(autoVerzekering);
	}
}