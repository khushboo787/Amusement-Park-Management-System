package com.JoyLand.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.JoyLand.model.Customer;
import com.JoyLand.service.CustomerService;

import jakarta.validation.Valid;


@RestController
public class CustomerController {

	
	private CustomerService customerService;
	private PasswordEncoder passwordEncoder;
	
	
	
	public CustomerController(CustomerService customerService, PasswordEncoder passwordEncoder) {
		super();
		this.customerService = customerService;
		this.passwordEncoder = passwordEncoder;
	}



	@PostMapping("/customers")
	public ResponseEntity<Customer> saveCustomerHandler(@RequestBody Customer customer){
		
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		
		customer.setRole("ROLE_"+customer.getRole().toUpperCase());
		
		
		Customer registeredCustomer= customerService.insertCustomer(customer);
		
		return new ResponseEntity<>(registeredCustomer,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/customers/{email}")
	public ResponseEntity<Customer> getCustomerByEmailHandler(@PathVariable("email") String email){
		
		
		Customer customer= customerService.getCustomerDetailsByEmail(email);
		
		return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomerHandler(){
		
		
		List<Customer> customers= customerService.getAllCustomerDetails();
		
		return new ResponseEntity<>(customers,HttpStatus.ACCEPTED);
		
	}
	
	
	


	
}
