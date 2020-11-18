package com.cg.onlinepizza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dto.CustomerDTO;

@Service
public interface CustomerService {

	public List<CustomerDTO> getAllCustomers();

	public CustomerDTO findCustomer(Integer customerId);

	public List<CustomerDTO> deleteCustomer(Integer customerId);

	public List<CustomerDTO> saveCustomer(CustomerDTO customerDTO);

	public List<CustomerDTO> updateCustomer(CustomerDTO customerDTO);

}



