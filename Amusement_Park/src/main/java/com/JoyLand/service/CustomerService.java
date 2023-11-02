package com.JoyLand.service;

import java.util.List;

import com.JoyLand.exception.CustomerException;
import com.JoyLand.model.Customer;

public interface CustomerService {
	public Customer insertCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public String deleteCustomer(Integer customerID);
	public List<Customer> viewAllCustomer();
	public Customer viewCustomerById(Integer customerID);
	public Customer validateCustomer(String username, String password);
	List<Customer> getAllCustomerDetails() throws CustomerException;
	Customer getCustomerDetailsByEmail(String email) throws CustomerException;
}
