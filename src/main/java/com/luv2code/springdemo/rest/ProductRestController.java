package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.entity.Product;
import com.luv2code.springdemo.service.CustomerService;
import com.luv2code.springdemo.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		System.out.println(productService.getProducts());
		return productService.getProducts();
	}
	
	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable int productId){
		Product theProduct = productService.getProduct(productId);
		if(theProduct==null) {
			throw new CustomerNotFoundException("There is no product with Id: "+productId);
		}
		return theProduct;
	}
	@GetMapping("/products/customers/{productId}")
	public List<Customer> getCustomers(@PathVariable int productId){
		List<Customer> theCustomer = productService.getCustomers(productId);
		System.out.println(theCustomer);
		if(theCustomer==null) {
			throw new CustomerNotFoundException("There is no customer with Id: "+productId);
		}
		return theCustomer;
	}

	@PostMapping("/products")
	public Product addProduct(@RequestBody Product theProduct) {
		//set id to 0 as it is insert and not update
		theProduct.setId(0);
		productService.saveProduct(theProduct);
		return theProduct;
	}
	
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product theProduct) {
		productService.saveProduct(theProduct);
		return theProduct;
	}
	
	@DeleteMapping("/products/{productId}")
	public String deleteProduct(@PathVariable int productId) {
		Product theProduct = productService.getProduct(productId);
		if(theProduct==null) {
			throw new CustomerNotFoundException("There is no product with Id: "+productId);
		}
		productService.deleteProduct(productId);
		return "Deleted product with Id: "+productId;
	}
}
