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

import com.cg.onlinepizza.dto.CustomerDTO;
import com.cg.onlinepizza.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/update")
	public ResponseEntity<List<CustomerDTO>> updateCoupon(
			@RequestBody CustomerDTO customerDTO){
		List<CustomerDTO> customers = customerService.updateCustomer(customerDTO);
		if(customers.isEmpty())
		{
			return new ResponseEntity("Sorry! Customers not available!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<CustomerDTO>>(customers,HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/insert")
	public ResponseEntity<List<CustomerDTO>> insertCustomer(
			@RequestBody CustomerDTO customer){
		List<CustomerDTO> customers = customerService.saveCustomer(customer);
		if(customers.isEmpty())
		{
			return new ResponseEntity("Sorry! Customer could not be inserted!",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<CustomerDTO>>(customers,HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<List<CustomerDTO>> deleteCustomer(
			@PathVariable("customerId")int customerId){
		List<CustomerDTO> customers= customerService.deleteCustomer(customerId);
		if(customers.isEmpty() || customers==null) {
			return new ResponseEntity("Sorry! CustomerId not available!", 
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<CustomerDTO>>(customers, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/find/{customerId}")
	public ResponseEntity<CustomerDTO> findCustomer(
			@PathVariable("customerId")int customerId){
		CustomerDTO customer= customerService.findCustomer(customerId);
		if(customer==null) {
			return new ResponseEntity("Sorry! CustomerId not found!", 
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/findAll")
	public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
		List<CustomerDTO> customers= customerService.getAllCustomers();
		if(customers.isEmpty()) {
			return new ResponseEntity("Sorry! Customers not found!", 
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<CustomerDTO>>(customers, HttpStatus.OK);
	}
}

