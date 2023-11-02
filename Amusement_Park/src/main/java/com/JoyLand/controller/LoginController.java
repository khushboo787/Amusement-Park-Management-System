package com.JoyLand.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JoyLand.model.Customer;
import com.JoyLand.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {
	private CustomerService customerService;

	public LoginController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@GetMapping("/signIn")
	public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth){
		log.info("Inside the getLoggedInCustomerDetailsHandler");
		System.out.println(auth);		
		 Customer customer= customerService.getCustomerDetailsByEmail(auth.getName());
		 
		 return new ResponseEntity<>(customer.getUsername()+ " Logged In Successfully", HttpStatus.ACCEPTED);	
	}
}
