package com.jwtsampleapp.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwtsampleapp.api.model.User;
import com.jwtsampleapp.api.repository.UserRepository;
import com.jwtsampleapp.api.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Integer saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user).getUserId();
	}

}
