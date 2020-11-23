package com.cg.onlinepizza.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.onlinepizza.dto.CustomerDTO;
import com.cg.onlinepizza.exceptions.CustomerAlreadyExistsException;
import com.cg.onlinepizza.exceptions.CustomerNotFoundException;
import com.cg.onlinepizza.exceptions.CustomersNotPresentException;
import com.cg.onlinepizza.model.Customer;
import com.cg.onlinepizza.repository.CustomerRepository;
import com.cg.onlinepizza.service.CustomerService;

@SpringBootTest
public class CustomerServiceImplTest {

	@Autowired
	private CustomerService customerService;

	@MockBean
	CustomerRepository customerRepository;

	//Check the saveCustomer method for null values.
	@Test
	public void saveCustomerNullTest() throws CustomerAlreadyExistsException
	{
		CustomerDTO customerDto = null;
		List<CustomerDTO> actual = customerService.saveCustomer(customerDto);
		assertNull(actual);
	}

	//Check the saveCustomer method to add new values.
	@Test
	public void saveCustomerTest() throws CustomersNotPresentException {
		Customer customer = new Customer("Ram","Achanta",(long) 1235667890,"abc@gmail.com","2-33,kakinada","Ram101","1234",null);
		Mockito.when(customerRepository.saveAndFlush(customer)).thenReturn(customer);  
		Mockito.when(customerRepository.findAll()).thenReturn(Stream.of(customer).collect(Collectors.toList()));
		List<CustomerDTO> actual = customerService.getAllCustomers();
		assertEquals(1,actual.size());
	}

	//checks if the customer already exists or not.
	@Test
	public void saveExistingCustomerTest() {
		Customer customer1 = new Customer("Ram","Achanta",(long) 1235667890,"abc@gmail.com","2-33,kakinada","Ram101","1234",null);
		customer1.setCustomerId(1);
		Mockito.when(customerRepository.saveAndFlush(customer1)).thenThrow(new CustomerAlreadyExistsException("Customer Already Exists"));
		//CustomerDTO customerDto = CustomerServiceImpl.entityToDTO(customer1);
		Exception exception = assertThrows(CustomerAlreadyExistsException.class,()->customerService.saveCustomer(CustomerServiceImpl.entityToDTO(customer1)));
		assertTrue(exception.getMessage().contains("Customer Already Exists"));
	}

	//Retrieving all the customers present.
	@Test
	public void getAllCustomersPresentTest() throws CustomersNotPresentException {
		CustomerDTO customer1 = new CustomerDTO("Ram","Achanta",(long) 1235667890,"abc@gmail.com","2-33,kakinada","Ram101","1234",null);
		customer1.setCustomerId(1);
		CustomerDTO customer2 = new CustomerDTO("Ram","Achanta",(long) 1235667890,"abc@gmail.com","2-33,kakinada","Ram101","1234",null);
		customer2.setCustomerId(2);
		Mockito.when(customerRepository.findAll()).thenReturn(Stream.of(CustomerServiceImpl.DTOToEntity(customer1),CustomerServiceImpl.DTOToEntity(customer2)).collect(Collectors.toList()));
		List<CustomerDTO> actual = customerService.getAllCustomers();
		assertEquals(2,actual.size());
	}

	//No customers are present to retrieve.
	@Test
	public void getAllCustomersNotPresentTest()
	{
		Mockito.when(customerRepository.findAll()).thenThrow(new CustomersNotPresentException("No customers present in the database"));

		Exception exception = assertThrows(CustomersNotPresentException.class,()->customerService.getAllCustomers());
		assertTrue(exception.getMessage().contains("No customers present in the database"));
	}

	//checks if the customer is present.
	@Test 
	public void findCustomerPresentTest() throws CustomerNotFoundException {
		Customer customer = new Customer("ABC", "DEF", 1234567890L, "abc@gmail.com", "Address", "Username", "Password", null);
		customer.setCustomerId(1);
		Mockito.when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.of(customer)); 
		CustomerDTO actual = customerService.findCustomer(customer.getCustomerId());
		assertEquals(CustomerServiceImpl.entityToDTO(customer),actual);
	}

	//checks if the customer is not present and returns an appropriate error.
	@Test 
	public void findCustomerNotPresentTest() {

		Mockito.when(customerRepository.findById(2)).thenThrow(new CustomersNotPresentException("Customer not present in the database"));
		Exception exception = assertThrows(CustomersNotPresentException.class,()->customerService.findCustomer(2));
		assertTrue(exception.getMessage().contains("Customer not present in the database"));
	}

	//check the find customer method for null values.
	@Test
	public void findCustomerNullTest() throws CustomerNotFoundException
	{
		Integer customerId = null;
		CustomerDTO actual = customerService.findCustomer(customerId);
		assertNull(actual);
	}

        //check the update customer method for null values.
	@Test
	public void updateCustomerNullTest() throws CustomerNotFoundException
	{
		CustomerDTO customerDto = null;
		List<CustomerDTO> actual = customerService.updateCustomer(customerDto);
		assertNull(actual);
	}

	//Checks if the value is present in database so as to perform update operation using updateCustomer method.
	@Test
	public void updateCustomerPresentTest() throws CustomerNotFoundException {
		Customer customer = new Customer("Mythili","Seela",(long)1234567890,"abc@gmail.com","2-27,Rajahmundry","mythu27","2727",null);
		customer.setCustomerId(1);
		Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		Mockito.when(customerRepository.findAll()).thenReturn(Stream.of(customer).collect(Collectors.toList()));

		List<CustomerDTO> actual = customerService.updateCustomer(CustomerServiceImpl.entityToDTO(customer));
		assertEquals(1,actual.size());
	}

	//Checks if the customer passed to update is not found in database and returns an appropriate error.
	@Test
	public void updateCustomerNotPresentTest() throws CustomerNotFoundException 
	{
		Customer customer = new Customer("Mythili","Seela",(long)1234567890,"abc@gmail.com","2-27,Rajahmundry","mythu27","2727",null);
		customer.setCustomerId(1);
		Mockito.when(customerRepository.findById(2)).thenThrow(new CustomerNotFoundException("Customer not present in the database"));
		Exception exception = assertThrows(CustomerNotFoundException.class,()->customerService.findCustomer(2));
		assertTrue(exception.getMessage().contains("Customer not present in the database"));
	}

	//check the delete customer method for null values.
	@Test
	public void deleteCustomerNullTest() throws CustomerNotFoundException
	{
		Integer customerId = null;
		List<CustomerDTO> actual = customerService.deleteCustomer(customerId);
		assertNull(actual);
	}
	
	//checks if the sign in is done successfully or not.
	@Test
	public void signInSuccessTest() {
		Customer customer = new Customer("Mythili","Seela",(long)1234567890,"abc@gmail.com","2-27,Rajahmundry","mythu27","2727",null);
		customer.setCustomerId(1);
		Integer id = customer.getCustomerId();
		String password = customer.getPassword();
		Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
		CustomerDTO actual = customerService.findCustomer(id);
		Integer id1 = actual.getCustomerId();
		String password1 = actual.getPassword();
		assertEquals(id,id1);
		assertEquals(password,password1);	
	}
	
	//check the sign in method for null values.
	@Test
	public void signInNullTest() throws CustomerNotFoundException {
		
			CustomerDTO user = null;	
			CustomerDTO actual = customerService.signIn(user);			
			assertNull(actual);
		}

//	@Test
//	public void deleteCustomerPresentTest() throws CustomerNotFoundException 
//	{
//		Integer customerId = 1;
//		Customer customer1 = new Customer("Mythili","Seela",(long)1234567890,"abc@gmail.com","2-27,Rajahmundry","mythu27","2727",null);
//		customer1.setCustomerId(1);
//		Customer customer2 = new Customer("ABC","Surname",(long)1234567890,"abc@gmail.com","Address","username","2727",null);
//		customer2.setCustomerId(1);
//
//		Mockito.when(customerRepository.save(customer1)).thenReturn(customer1);
//		Mockito.when(customerRepository.findAll()).thenReturn(Stream.of(customer1).collect(Collectors.toList()));
//
//		List<CustomerDTO> actual = customerService.deleteCustomer(customerId);
//		assertEquals(1,actual.size());
//	}

	/*
	@Test
	public void deleteCustomerNotPresentTest() throws CustomerNotFoundException 
	{
		Customer customer = new Customer("Mythili","Seela",(long)1234567890,"abc@gmail.com","2-27,Rajahmundry","mythu27","2727",null);
		customer.setCustomerId(1);
		Mockito.when(customerRepository.delete(customer)).thenThrow(new CustomerNotFoundException("Customer not present in the database"));
		Exception exception = assertThrows(CustomerNotFoundException.class,()->customerService.findCustomer(2));
		assertTrue(exception.getMessage().contains("Customer not present in the database"));
	}
	 */

}


