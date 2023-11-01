package com.JoyLand.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.JoyLand.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
