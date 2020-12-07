package com.cg.onlinepizza.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dto.CustomerDTO;
import com.cg.onlinepizza.dto.OrderDTO;
import com.cg.onlinepizza.exceptions.CustomerAlreadyExistsException;
import com.cg.onlinepizza.exceptions.CustomerNotFoundException;
import com.cg.onlinepizza.exceptions.CustomerUserNameNotFoundException;
import com.cg.onlinepizza.exceptions.CustomersNotPresentException;
import com.cg.onlinepizza.model.Customer;
import com.cg.onlinepizza.model.Order;
import com.cg.onlinepizza.repository.CustomerRepository;
import com.cg.onlinepizza.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	static Logger log = Logger.getLogger(CustomerServiceImpl.class.getName());
	
	@Autowired
	private CustomerRepository customerRepository;

	public static CustomerDTO entityToDTO(Customer customer) 
	{

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setLastName(customer.getLastName());
		customerDTO.setCustomerMobile(customer.getCustomerMobile());
		customerDTO.setCustomerAddress(customer.getCustomerAddress());
		customerDTO.setUserName(customer.getUserName());
		customerDTO.setPassword(customer.getPassword());
		customerDTO.setCustomerEmail(customer.getCustomerEmail());

		Set<OrderDTO> orderDTO = new HashSet<>();
		if(customer.getOrder() == null)
			customerDTO.setOrder(null);
		else
		{
			for(Order o: customer.getOrder())
			{
				orderDTO.add(OrderServiceImpl.entityToDTO(o));
			}

			customerDTO.setOrder(orderDTO);
		}
		return customerDTO;
	}

	public static Customer DTOToEntity(CustomerDTO customerDTO)
	{
		Customer customer = new Customer();
		customer.setCustomerId(customerDTO.getCustomerId());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		customer.setCustomerMobile(customerDTO.getCustomerMobile());
		customer.setCustomerAddress(customerDTO.getCustomerAddress());
		customer.setUserName(customerDTO.getUserName());
		customer.setPassword(customerDTO.getPassword());
		customer.setCustomerEmail(customerDTO.getCustomerEmail());
		customer.setOrder(null);
		return customer;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() throws CustomersNotPresentException {
		
		log.info("Service Layer - Entry - get Customers");
		
		List<CustomerDTO> customerDTOReturn = new ArrayList<>();
		for(Customer c:customerRepository.findAll())
		{
			customerDTOReturn.add(entityToDTO(c));
		}
        if(customerDTOReturn.isEmpty())
        {
        	throw new CustomersNotPresentException("No Customers Available");
        }
        
        log.info("Service Layer - Exit - get Customers");
        
		return customerDTOReturn;
	}

	@Override
	public List<CustomerDTO> deleteCustomer(Integer customerId) throws CustomerNotFoundException{
		
		if(customerId == null)
		{
			return null;
		}
		log.info("Service Layer - Entry - delete Customers");
		
		Optional<Customer> checking = customerRepository.findById(customerId);
		if(checking.isEmpty())
		{
			 throw new CustomerNotFoundException("Customer Not Found To Delete"); 
		}
		customerRepository.deleteById(customerId);
		List<CustomerDTO> customerDTOReturn = new ArrayList<>();
		for(Customer c:customerRepository.findAll())
		{
			log.warn("WARN: delete customers Started");
			
			customerDTOReturn.add(entityToDTO(c));
		}
		
		log.info("Service Layer - Exit - delete Customers");
		
		return customerDTOReturn;
	}

	@Override
	public List<CustomerDTO> saveCustomer(CustomerDTO customerDTO) throws CustomerAlreadyExistsException {
		
		if(customerDTO == null)
		{
			return null;
		}
		
		log.info("Service Layer - Entry - save Customers");
		
		Optional<Customer> checking = customerRepository.findById(DTOToEntity(customerDTO).getCustomerId());
		if(checking.isPresent())
		{
			throw new CustomerAlreadyExistsException("Customer Already Exists");
		}
		Customer customer = new Customer();
		customer = DTOToEntity(customerDTO);

		customerRepository.saveAndFlush(customer);

		List<CustomerDTO> customerDTOReturn = new ArrayList<>();
		for(Customer c:customerRepository.findAll())
		{
			customerDTOReturn.add(entityToDTO(c));
		}
		
		log.info("Service Layer - Exit - save Customers");
		
		return customerDTOReturn;
	}

	@Override
	public List<CustomerDTO> updateCustomer(CustomerDTO customerDTO) throws CustomerNotFoundException{
		
		if(customerDTO == null)
		{
			return null;
		}
		
		log.info("Service Layer - Entry - update Customers");
		
		Optional<Customer>customers=customerRepository.findById(DTOToEntity(customerDTO).getCustomerId());
		
		  if(customers.isEmpty()) { 
			  throw new CustomerNotFoundException("Customer Not Found"); 
			  }
		
		Customer customer = new Customer();
		customer = DTOToEntity(customerDTO);

		customerRepository.save(customer);

		List<CustomerDTO> customerDTOReturn = new ArrayList<>();
		for(Customer c:customerRepository.findAll())
		{
			customerDTOReturn.add(entityToDTO(c));
		}
		log.info("Service Layer - Exit - Update Customers");
		return  customerDTOReturn;
	}

	@Override
	public CustomerDTO findCustomer(Integer customerId) throws CustomersNotPresentException {
		
		log.info("Service Layer - Entry - find Customer");
		if(customerId == null)
			return null;
		Optional<Customer>customer=customerRepository.findById(customerId);
		
		  if(customer.isEmpty()) { 
			  
			  throw new CustomersNotPresentException("Customer Not Found"); 
			  }
		
		 log.info("Service Layer - Exit - find Customer");	
		return entityToDTO(customer.get());
	}

	@Override
	public CustomerDTO signIn(CustomerDTO customerDTO) throws NoSuchElementException,CustomerUserNameNotFoundException,CustomerNotFoundException {

		if (customerDTO == null) {
			return null;
		}

		Customer customer = DTOToEntity(customerDTO);
		if (customer == null) {
			log.warn("No User Available");
			throw new CustomerNotFoundException("Customer Not Found");
		}

		Optional<Customer> option = customerRepository.findByUserName(customer.getUserName());
		if (!option.get().getUserName().equals(customerDTO.getUserName())) {
			log.warn("No User Available");
			throw new CustomerUserNameNotFoundException("Customer Not Found");
		}
		else
		{
			if (!option.get().getPassword().equals(customerDTO.getPassword())) {
				log.warn("No User Available");
				throw new CustomerNotFoundException("Customer Not Found");
			}
		}

		customer = option.get();
		CustomerDTO dto = entityToDTO(customer);

		return dto;
	}
}


