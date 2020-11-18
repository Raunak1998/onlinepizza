package com.cg.onlinepizza.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dto.CustomerDTO;
import com.cg.onlinepizza.dto.OrderDTO;
import com.cg.onlinepizza.model.Customer;
import com.cg.onlinepizza.model.Order;
import com.cg.onlinepizza.repository.CustomerRepository;
import com.cg.onlinepizza.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;

	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
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

		//		Set<Order> order = new HashSet<>();
		//		Set<OrderDTO> orderDTO = new HashSet<>(customerDTO.getOrder());
		//		if(orderDTO == null)
		//		{
		//			customer.setOrder(null);
		//		}
		//		else
		//		{
		//			for(OrderDTO o: orderDTO)
		//			{
		//				order.add(OrderServiceImpl.DTOToEntity(o));
		//			}
		//
		//			customer.setOrder(order);
		//		}
		customer.setOrder(null);
		return customer;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		List<CustomerDTO> customerDTOReturn = new ArrayList<>();
		for(Customer c:customerRepository.findAll())
		{
			customerDTOReturn.add(entityToDTO(c));
		}

		return customerDTOReturn;
	}

	@Override
	public List<CustomerDTO> deleteCustomer(Integer customerId) {
		customerRepository.deleteById(customerId);
		List<CustomerDTO> customerDTOReturn = new ArrayList<>();
		for(Customer c:customerRepository.findAll())
		{
			customerDTOReturn.add(entityToDTO(c));
		}
		return customerDTOReturn;
	}

	@Override
	public List<CustomerDTO> saveCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer = DTOToEntity(customerDTO);

		customerRepository.saveAndFlush(customer);

		List<CustomerDTO> customerDTOReturn = new ArrayList<>();
		for(Customer c:customerRepository.findAll())
		{
			customerDTOReturn.add(entityToDTO(c));
		}
		return customerDTOReturn;
	}

	@Override
	public List<CustomerDTO> updateCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer = DTOToEntity(customerDTO);

		customerRepository.save(customer);

		List<CustomerDTO> customerDTOReturn = new ArrayList<>();
		for(Customer c:customerRepository.findAll())
		{
			customerDTOReturn.add(entityToDTO(c));
		}

		return  customerDTOReturn;
	}

	@Override
	public CustomerDTO findCustomer(Integer customerId) {
		Optional<Customer>customer=customerRepository.findById(customerId);
		return entityToDTO(customer.get());
	}

}


