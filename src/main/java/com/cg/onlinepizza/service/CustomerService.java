package com.cg.onlinepizza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinepizza.model.Customer;

@Service
public interface CustomerService {

	public List<Customer> getAllCustomers();

	public Customer findCustomer(Integer customerId);

	public List<Customer> deleteCustomer(Integer customerId);

	public List<Customer> saveCustomer(Customer customer);

	public List<Customer> updateCustomer(Customer customer);

}



