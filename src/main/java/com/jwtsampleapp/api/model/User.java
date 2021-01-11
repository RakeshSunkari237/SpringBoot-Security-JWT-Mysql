package com.jwtsampleapp.api.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="user_tab")
public class User {
	
	@Id
	@GeneratedValue
	private Integer userId;
	private String userName;
	private String email;
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="role_tab", joinColumns = @JoinColumn(name="user_id"))
	private Set<String> roles;

}
