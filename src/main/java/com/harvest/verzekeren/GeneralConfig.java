package com.harvest.verzekeren;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.harvest.verzekeren.user.JsonUser;
import com.harvest.verzekeren.user.User;

@Configuration
public class GeneralConfig
{
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Bean
	public ModelMapper modelMapper()
	{
		ModelMapper modelMapper = new ModelMapper();

		Converter<String, String> toEncoded = ctx -> passwordEncoder.encode(ctx.getSource());

		modelMapper.createTypeMap(JsonUser.class, User.class)
			.addMappings(mapper -> mapper.using(toEncoded).map(JsonUser::getPassword, User::setPassword));

		return modelMapper;
	}
}
