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

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		System.out.println(customerService.getCustomers());
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId){
		Customer theCustomer = customerService.getCustomer(customerId);
		if(theCustomer==null) {
			throw new CustomerNotFoundException("There is no customer with Id: "+customerId);
		}
		return theCustomer;
	}
	@GetMapping("/customers/products/{customerId}")
	public List<Product> getProducts(@PathVariable int customerId){
		List<Product> theProduct = customerService.getProducts(customerId);
		System.out.println(theProduct);
		if(theProduct==null) {
			throw new CustomerNotFoundException("There is no product with Id: "+customerId);
		}
		return theProduct;
	}

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		//set id to 0 as it is insert and not update
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		Customer theCustomer = customerService.getCustomer(customerId);
		if(theCustomer==null) {
			throw new CustomerNotFoundException("There is no customer with Id: "+customerId);
		}
		customerService.deleteCustomer(customerId);
		return "Deleted customer with Id: "+customerId;
	}
}
