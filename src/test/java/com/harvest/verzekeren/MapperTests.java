package com.harvest.verzekeren;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.harvest.verzekeren.auto.AutoVerzekering;
import com.harvest.verzekeren.auto.JsonAutoVerzekering;
import com.harvest.verzekeren.user.JsonUser;
import com.harvest.verzekeren.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { GeneralConfig.class })
public class MapperTests
{
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Test
	public void checkAutoVerzekeringMapping()
	{
		JsonAutoVerzekering jsonAutoVerzekering = new JsonAutoVerzekering();
		jsonAutoVerzekering.setType("Testing type");

		AutoVerzekering autoVerzekering = modelMapper.map(jsonAutoVerzekering, AutoVerzekering.class);

		assertEquals(jsonAutoVerzekering.getType(), autoVerzekering.getType());
	}

	@Test
	public void checkUserMapping()
	{
		JsonUser jsonUser = new JsonUser();
		jsonUser.setUsername("Testing username");
		jsonUser.setPassword("Testing password");
		jsonUser.setVoornaam("Testing voornaam");
		jsonUser.setAchternaam("Testing achternaam");

		User user = modelMapper.map(jsonUser, User.class);

		assertEquals(jsonUser.getVoornaam(), user.getVoornaam());
		assertEquals(jsonUser.getAchternaam(), user.getAchternaam());
		assertEquals(jsonUser.getUsername(), user.getUsername());
		assertTrue(passwordEncoder.matches(jsonUser.getPassword(), user.getPassword()));
	}
}