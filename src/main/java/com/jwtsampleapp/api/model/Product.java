package com.jwtsampleapp.api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer productId;
	private String productCode;
	private String productName;
	private Long productCost;

}
