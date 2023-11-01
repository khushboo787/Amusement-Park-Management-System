package com.JoyLand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.JoyLand.model.Customer;
import com.JoyLand.service.CustomerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;


@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> registerCustomer (@Valid @RequestBody Customer customer){
		return new ResponseEntity<Customer>(customerService.insertCustomer(customer), HttpStatus.CREATED);
	}
	
	
	
}
