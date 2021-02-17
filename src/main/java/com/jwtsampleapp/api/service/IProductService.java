package com.jwtsampleapp.api.service;

import java.util.List;

import com.jwtsampleapp.api.model.Product;

public interface IProductService {

	
	public Integer saveProduct(Product product);
	public Product findOneProductById(Integer productId);
	public List<Product> getAllProducts();
	public String removeProductById(Integer productId);
	public Product modifyProduct(Integer productId, Product product);
}
