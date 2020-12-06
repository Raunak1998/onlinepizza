package com.cg.onlinepizza.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.onlinepizza.exceptions.CustomerNotFoundException;
import com.cg.onlinepizza.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	public Optional<Customer> findByUserName(@Param("name") String userName) throws CustomerNotFoundException;
	}

