package com.jwtsampleapp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwtsampleapp.api.model.Product;
import com.jwtsampleapp.api.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductRestController {
	
	
	//private static Logger log=Logger.getLogger(ProductRestController.class);

	@Autowired
	private IProductService productService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<String> saveProduct(@RequestBody Product product){
		//log.info("save product method invoked ");
		String message="";
		Integer id = productService.saveProduct(product);
		if(id != null) {
			message="Product saved successfully with id : "+id;
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get/{productId}", method = RequestMethod.GET)
	public ResponseEntity<Product> saveProduct(@PathVariable Integer productId){
		Product product=null;
		Product resultPorduct = productService.findOneProductById(productId);
		if(resultPorduct != null) {
			product=resultPorduct;
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> productList = null;
		productList=productService.getAllProducts();
		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
	}
	
	
	
	public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody Product product){
		Product prod = productService.findOneProductById(productId);
		Product modifiedProduct = null;
		if(prod != null) {
			modifiedProduct = productService.modifyProduct(productId, product);
		}
		return new ResponseEntity<Product>(modifiedProduct, HttpStatus.OK);
	}
}
