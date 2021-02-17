package com.jwtsampleapp.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jwtsampleapp.api.model.Product;
import com.jwtsampleapp.api.repository.ProductRepository;
import com.jwtsampleapp.api.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Integer saveProduct(Product product) {
		return productRepository.save(product).getProductId();
	}

	@Override
	@Cacheable(value = "products", key = "#productId")
	public Product findOneProductById(Integer productId) {
		Product product =null;
		Optional<Product> optional = productRepository.findById(productId);
		if(optional.isPresent()) {
			 product = optional.get();
		}
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public String removeProductById(Integer productId) {
		String message="";
		boolean isProductExist = productRepository.existsById(productId);
		if(isProductExist) {
			productRepository.deleteById(productId);
			message="Product successfully deleted with id : "+productId;
		}
		return message;
	}

	@Override
	public Product modifyProduct(Integer productId, Product product) {
		Product updatedProduct=null;
		boolean isProductExist = productRepository.existsById(productId);
		if(isProductExist) {
			updatedProduct=productRepository.save(product);
		}
		return updatedProduct;
	}

}
