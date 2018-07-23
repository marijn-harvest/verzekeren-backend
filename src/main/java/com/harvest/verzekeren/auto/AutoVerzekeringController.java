package com.harvest.verzekeren.auto;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harvest.verzekeren.user.MyUserPrincipal;

@RestController
public class AutoVerzekeringController
{
	private AutoVerzekeringRepository autoVerzekeringRepository;

	private ModelMapper modelMapper;

	@Autowired
	public AutoVerzekeringController(AutoVerzekeringRepository autoVerzekeringRepository, ModelMapper modelMapper)
	{
		this.autoVerzekeringRepository = autoVerzekeringRepository;
		this.modelMapper = modelMapper;
	}

	@PostMapping("/auto-verzekering")
	public void saveAutoVerzekering(@RequestBody JsonAutoVerzekering jsonAutoVerzekering, Authentication authentication)
	{
		MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();

		AutoVerzekering autoVerzekering = modelMapper.map(jsonAutoVerzekering, AutoVerzekering.class);
		autoVerzekering.setUserId(principal.getId());

		autoVerzekeringRepository.save(autoVerzekering);
	}

	@GetMapping("/auto-verzekering")
	public JsonAutoVerzekering getMyAutoVerzekering(Authentication authentication)
	{
		MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
		Optional<AutoVerzekering> autoVerzekeringOptional = autoVerzekeringRepository.findById(principal.getId());

		return autoVerzekeringOptional.map(autoVerzekering -> modelMapper.map(autoVerzekering, JsonAutoVerzekering.class)).orElse(null);
	}
}