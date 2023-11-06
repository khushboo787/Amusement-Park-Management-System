package com.JoyLand.service;

import java.util.List;
import java.util.Optional;

import com.JoyLand.exception.CustomerException;
import com.JoyLand.model.Customer;

public interface CustomerService {
	public Customer insertCustomer(Customer customer);
	public Customer updateCustomer(Integer customerId,Customer customer);
	public String deleteCustomer(Integer customerID);
	List<Customer> viewAllCustomer() throws CustomerException;
	public Optional<Customer> viewCustomerById(Integer customerID);
	Customer getCustomerDetailsByEmail(String email) throws CustomerException;
	Optional<Customer> validateCustomer(String username, String password);
}
