package com.cg.onlinepizza.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinepizza.dto.CustomerDTO;
import com.cg.onlinepizza.exceptions.CustomerAlreadyExistsException;
import com.cg.onlinepizza.exceptions.CustomerNotFoundException;
import com.cg.onlinepizza.exceptions.CustomerUserNameNotFoundException;
import com.cg.onlinepizza.exceptions.CustomersNotPresentException;
import com.cg.onlinepizza.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PutMapping("/update")
	public ResponseEntity<List<CustomerDTO>> updateCoupon(
			@Valid @RequestBody CustomerDTO customerDTO)throws CustomerNotFoundException{
		List<CustomerDTO> customers = customerService.updateCustomer(customerDTO);
		return new ResponseEntity<List<CustomerDTO>>(customers,HttpStatus.OK);
	}

	@PostMapping("/insert")
	public ResponseEntity<List<CustomerDTO>> insertCustomer(
			@Valid @RequestBody CustomerDTO customer)throws CustomerAlreadyExistsException{
		List<CustomerDTO> customers = customerService.saveCustomer(customer);
		return new ResponseEntity<List<CustomerDTO>>(customers,HttpStatus.OK);
	}

	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<List<CustomerDTO>> deleteCustomer(
			@PathVariable("customerId")int customerId)throws CustomerNotFoundException{
		List<CustomerDTO> customers= customerService.deleteCustomer(customerId);
		return new ResponseEntity<List<CustomerDTO>>(customers, HttpStatus.OK);
	}

	@GetMapping("/find/{customerId}")
	public ResponseEntity<CustomerDTO> findCustomer (
			@PathVariable("customerId")int customerId)throws CustomerNotFoundException{
		CustomerDTO customer= customerService.findCustomer(customerId);
		return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws CustomersNotPresentException{
		List<CustomerDTO> customers= customerService.getAllCustomers();
		return new ResponseEntity<List<CustomerDTO>>(customers, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<CustomerDTO> signIn( @RequestBody CustomerDTO customerDTO) throws CustomerUserNameNotFoundException
		{
			CustomerDTO customer = customerService.signIn(customerDTO);
			return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
		}
	}


