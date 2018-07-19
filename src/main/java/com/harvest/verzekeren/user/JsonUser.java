package com.harvest.verzekeren.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class JsonUser
{
	private String username;

	private String password;

	private String voornaam;

	private String achternaam;

	@JsonIgnore
	public String getPassword()
	{
		return password;
	}

	@JsonProperty
	public void setPassword(String password)
	{
		this.password = password;
	}
}
