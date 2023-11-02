package com.JoyLand.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JoyLand.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Optional<Customer> findByEmail(String email);
	
}
