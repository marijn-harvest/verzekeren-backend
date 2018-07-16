package com.harvest.verzekeren;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.harvest.verzekeren.user.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private MyUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.cors().and()
			.csrf().disable()
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.httpBasic();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer()
		{
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				registry.addMapping("/**");
			}
		};
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
