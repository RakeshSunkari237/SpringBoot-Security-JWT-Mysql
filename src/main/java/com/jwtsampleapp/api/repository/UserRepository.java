package com.jwtsampleapp.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtsampleapp.api.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByUserName(String username);
}
