package com.harvest.verzekeren.auto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoVerzekeringController
{
	@Autowired
	private AutoVerzekeringRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/auto-verzekering")
	public void createAutoVerzekering(@RequestBody JsonAutoVerzekering jsonAutoVerzekering)
	{
		AutoVerzekering autoVerzekering = modelMapper.map(jsonAutoVerzekering, AutoVerzekering.class);
		repository.save(autoVerzekering);
	}
}