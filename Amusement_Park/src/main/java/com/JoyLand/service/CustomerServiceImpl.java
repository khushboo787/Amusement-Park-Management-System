package com.JoyLand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JoyLand.model.Customer;
import com.JoyLand.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
 
	@Autowired
	private CustomerRepository customerRepository ;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	
	@Override
	public Customer insertCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCustomer(Integer customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> viewAllCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer viewCustomerById(Integer customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer validateCustomer(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
