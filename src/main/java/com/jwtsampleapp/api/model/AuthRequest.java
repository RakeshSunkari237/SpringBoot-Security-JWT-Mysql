package com.jwtsampleapp.api.model;

import lombok.Data;

@Data
public class AuthRequest {

	private String userName;
	private String password;
}
