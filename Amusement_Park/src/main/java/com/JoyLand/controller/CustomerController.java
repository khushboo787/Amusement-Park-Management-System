package com.JoyLand.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.JoyLand.exception.CustomerException;
import com.JoyLand.model.Activity;
import com.JoyLand.model.Customer;
import com.JoyLand.model.Ticket;
import com.JoyLand.service.CustomerService;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class CustomerController {

	
	private CustomerService customerService;
	private PasswordEncoder passwordEncoder;
	
	
	
	public CustomerController(CustomerService customerService, PasswordEncoder passwordEncoder) {
		super();
		this.customerService = customerService;
		this.passwordEncoder = passwordEncoder;
	}



	@PostMapping("/registerCustomer")
	public ResponseEntity<Customer> registeredCustomer(@Valid @RequestBody Customer customer){
		List<Activity> actList= customer.getActivities();
		if( actList != null) {
			for(Activity ac: actList) {
				ac.setCustomer(customer);
			}
		}
		
		List<Ticket> tcList= customer.getTickets();
		if( tcList != null) {
			for(Ticket tc: tcList) {
				tc.setCustomer(customer);
			}
		}
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		
		customer.setRole("ROLE_"+customer.getRole().toUpperCase());
		
		
		Customer registeredCustomer= customerService.insertCustomer(customer);
		
		return new ResponseEntity<>(registeredCustomer,HttpStatus.ACCEPTED);
		
	}

	
	@PutMapping("/updateCustomerById/{customerId}")
	public ResponseEntity<Customer> updateCustomerDetails(@Valid @PathVariable Integer customerId, 
			@Valid @RequestBody Customer customer) throws CustomerException{
		
		return new ResponseEntity<>(customerService.updateCustomer(customerId, customer), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteCustomerById/{id}")
	public ResponseEntity<String> deleteCustomer(@Valid @PathVariable("id") Integer id) throws CustomerException {
		return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
	}
	
	
	@GetMapping("/viewCustomers")
	public ResponseEntity<List<Customer>> viewAllCustomer(){		
		List<Customer> customers= customerService.viewAllCustomer();
		
		return new ResponseEntity<>(customers,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/viewCustomerById/{id}")
	public ResponseEntity<Optional<Customer>> viewCustomerById(@Valid  @PathVariable("id") Integer id) throws CustomerException {
	return new ResponseEntity<>(customerService.viewCustomerById(id), HttpStatus.OK);
	}
	
	
	
	@GetMapping("/validateCustomerByUsernameAndPassword/{username}/{password}")
	public ResponseEntity<Optional<Customer>> validateCustomer(@Valid @PathVariable("username") String username ,@Valid @PathVariable("password") String password) throws CustomerException {
		log.info("validate customer");
		return new ResponseEntity<>(customerService.validateCustomer(username, password), HttpStatus.OK);
	}

	
}
