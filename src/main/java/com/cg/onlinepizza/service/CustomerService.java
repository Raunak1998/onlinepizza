package com.cg.onlinepizza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dto.CustomerDTO;
import com.cg.onlinepizza.exceptions.CustomerAlreadyExistsException;
import com.cg.onlinepizza.exceptions.CustomerNotFoundException;
import com.cg.onlinepizza.exceptions.CustomersNotPresentException;

@Service
public interface CustomerService {

	public List<CustomerDTO> getAllCustomers() throws CustomersNotPresentException;

	public CustomerDTO findCustomer(Integer customerId) throws CustomerNotFoundException;

	public List<CustomerDTO> deleteCustomer(Integer customerId)throws CustomerNotFoundException;

	public List<CustomerDTO> saveCustomer(CustomerDTO customerDTO)throws CustomerAlreadyExistsException;

	public List<CustomerDTO> updateCustomer(CustomerDTO customerDTO)throws CustomerNotFoundException;

	public CustomerDTO signIn(CustomerDTO customerDTO) ;

}



