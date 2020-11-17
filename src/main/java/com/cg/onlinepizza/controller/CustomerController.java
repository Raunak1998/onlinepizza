package com.cg.onlinepizza.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinepizza.model.Customer;
import com.cg.onlinepizza.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/update")
	public ResponseEntity<List<Customer>> updateCoupon(
			@RequestBody Customer customer){
		List<Customer> customers = customerService.updateCustomer(customer);
		if(customers.isEmpty())
		{
			return new ResponseEntity("Sorry! Customers not available!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/insert")
	public ResponseEntity<List<Customer>> insertCustomer(
			@RequestBody Customer customer){
		List<Customer> customers = customerService.saveCustomer(customer);
		if(customers.isEmpty())
		{
			return new ResponseEntity("Sorry! Customer could not be inserted!",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<List<Customer>> deleteCustomer(
			@PathVariable("customerId")int customerId){
		List<Customer> customers= customerService.deleteCustomer(customerId);
		if(customers.isEmpty() || customers==null) {
			return new ResponseEntity("Sorry! CustomerId not available!", 
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/find/{customerName}")
	public ResponseEntity<Customer> findCustomer(
			@PathVariable("customerName")int customerId){
		Customer customer= customerService.findCustomer(customerId);
		if(customer==null) {
			return new ResponseEntity("Sorry! CustomerId not found!", 
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/findAll")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customers= customerService.getAllCustomers();
		if(customers.isEmpty()) {
			return new ResponseEntity("Sorry! Customers not found!", 
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
}

