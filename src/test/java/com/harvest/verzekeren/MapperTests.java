package com.harvest.verzekeren;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import com.harvest.verzekeren.auto.AutoVerzekering;
import com.harvest.verzekeren.auto.JsonAutoVerzekering;

public class MapperTests
{
	private static final ModelMapper modelMapper = new ModelMapper();

	@Test
	public void checkAutoVerzekeringMapping()
	{
		JsonAutoVerzekering jsonAutoVerzekering = new JsonAutoVerzekering();
		jsonAutoVerzekering.setVoornaam("Testing voornaam");
		jsonAutoVerzekering.setAchternaam("Testing achternaam");
		jsonAutoVerzekering.setType("Testing type");

		AutoVerzekering autoVerzekering = modelMapper.map(jsonAutoVerzekering, AutoVerzekering.class);

		assertEquals(jsonAutoVerzekering.getVoornaam(), autoVerzekering.getVoornaam());
		assertEquals(jsonAutoVerzekering.getAchternaam(), autoVerzekering.getAchternaam());
		assertEquals(jsonAutoVerzekering.getType(), autoVerzekering.getType());
	}
}