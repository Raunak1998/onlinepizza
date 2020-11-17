package com.cg.onlinepizza.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.model.Customer;
import com.cg.onlinepizza.repository.CustomerRepository;
import com.cg.onlinepizza.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public List<Customer> deleteCustomer(Integer customerId) {
		customerRepo.deleteById(customerId);
		return customerRepo.findAll();
	}

	@Override
	public List<Customer> saveCustomer(Customer customer) {
		customerRepo.saveAndFlush(customer);

		return  customerRepo.findAll();
	}

	@Override
	public List<Customer> updateCustomer(Customer customer) {
		customerRepo.save(customer);

		return  customerRepo.findAll();
	}

	@Override
	public Customer findCustomer(Integer customerId) {
		Optional<Customer>customer=customerRepo.findById(customerId);
		return customer.get();
	}


}


