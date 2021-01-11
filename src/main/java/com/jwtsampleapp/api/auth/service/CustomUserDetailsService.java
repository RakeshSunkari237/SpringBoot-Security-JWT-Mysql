package com.jwtsampleapp.api.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwtsampleapp.api.model.User;
import com.jwtsampleapp.api.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByUserName(username);
		UserDetails userDetails=null;
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			userDetails=new CustomUserDetails(user);
		}else {
			throw new UsernameNotFoundException("username not existed : "+username);
		}
		return userDetails;
	}

}
