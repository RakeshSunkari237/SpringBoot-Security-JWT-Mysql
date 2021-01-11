package com.jwtsampleapp.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwtsampleapp.api.auth.service.InvalidUserAuthEntryPoint;
import com.jwtsampleapp.api.auth.service.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Autowired
	public  InvalidUserAuthEntryPoint authenticationEntryPoint;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SecurityFilter securityFilter;
	
	 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(pwdEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
		.authorizeRequests()
		.antMatchers("/user/save","/user/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	 	.and()
	 	// register filter for 2nd request  onwards
	 	.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
}
