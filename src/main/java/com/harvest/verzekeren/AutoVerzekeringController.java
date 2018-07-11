package com.harvest.verzekeren;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoVerzekeringController
{
	private AutoVerzekeringRepository repository;

	public AutoVerzekeringController(AutoVerzekeringRepository repository)
	{
		this.repository = repository;
	}

	@PostMapping("/auto-verzekering")
	public void createAutoVerzekering(@RequestBody JsonAutoVerzekering jsonAutoVerzekering)
	{
		AutoVerzekering autoVerzekering = new AutoVerzekering(jsonAutoVerzekering.getVoornaam(), jsonAutoVerzekering.getAchternaam(),
			jsonAutoVerzekering.getType());
		repository.save(autoVerzekering);
	}
}