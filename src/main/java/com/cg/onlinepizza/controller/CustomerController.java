package com.cg.onlinepizza.controller;


import java.util.List;

import javax.validation.Valid;

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
import com.cg.onlinepizza.exceptions.CustomerAlreadyExistsException;
import com.cg.onlinepizza.exceptions.CustomerNotFoundException;
import com.cg.onlinepizza.exceptions.CustomersNotPresentException;
import com.cg.onlinepizza.service.CustomerService;


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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/login")
	public ResponseEntity<CustomerDTO> signIn(@Valid @RequestBody CustomerDTO customerDTO) {

		CustomerDTO customers = customerService.signIn(customerDTO);
		if (customers == null) {
			return new ResponseEntity("Sorry! Users not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CustomerDTO>(customers, HttpStatus.OK);
	}
}

