package com.JoyLand.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JoyLand.model.Activity;
import com.JoyLand.model.Customer;
import com.JoyLand.repository.CustomerRepository;
import com.JoyLand.exception.CustomerException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{
 
	@Autowired
	private CustomerRepository customerRepository ;
	
	private PasswordEncoder passwordEncoder;
	
	
	
	public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
		this.customerRepository = customerRepository;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public Customer insertCustomer(Customer customer) {
		log.info("insert new customer");
		List<Activity> act= customer.getActivities();
		for(Activity a:act) {
			a.setCustomer(customer);
		}
		Optional<Customer> existingCustomer = customerRepository.findByEmail(customer.getEmail());
		
		if(existingCustomer.isPresent()) throw new CustomerException("Customer with this email has already been registered. Please use a different email ID.");		
		
		return customerRepository.save(customer); 
	}

	
	@Override
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException {
		return customerRepository.findByEmail(email).orElseThrow(() -> new CustomerException("Customer Not found with Email: "+email));
			
	}

	
	
	@Override
	public List<Customer> viewAllCustomer()throws CustomerException {
		
		List<Customer> customers = customerRepository.findAll();
	
		if(customers.isEmpty())
			throw new CustomerException("No Customer find");
		
		return customers;
		
	}	
	
	
	
	@Override
	public Customer updateCustomer(Integer customerId, Customer customer) {
		
       Optional<Customer> cus = customerRepository.findById(customerId);
		
		if (cus.isPresent()) {
			cus.get().setAddress(customer.getAddress());
			cus.get().setUsername(customer.getUsername());
			return customerRepository.save(cus.get());
		} else {
			throw new CustomerException(" Customer Does Not Exist with given id "+ customer.getCustomerId());
		}
		
		
	}

	@Override
	public String deleteCustomer(Integer customerID) {
		Optional<Customer> opt = customerRepository.findById(customerID);

		if (opt.isPresent()) {
			customerRepository.delete(opt.get());
			return "Customer Deleted successfully!";
		} else {

			throw new CustomerException("No customer found with the given ID:" + customerID);
		}
	}

	

	@Override
	public Optional<Customer> viewCustomerById(Integer customerID) {
		Optional<Customer> cus = customerRepository.findById(customerID);
		if(!cus.isPresent()) throw new CustomerException("No customer found with the given ID:" + customerID);
		return cus;
	}

	
	
	@Override
	public Optional<Customer> validateCustomer(String username, String password) {
		
		 Optional<Customer> customer = customerRepository.findByUsername(username);
	    
	        if (!customer.isPresent()) {
	            throw new CustomerException("Invalid username");
	        } else  if (!passwordEncoder.matches(password, customer.get().getPassword())) {
	            throw new CustomerException("Invalid Password");
	        }else if (customer.isPresent() && passwordEncoder.matches(password, customer.get().getPassword())){
	        	log.info("valid customer");
	        }
			
		    return customer;
		
	}



}
