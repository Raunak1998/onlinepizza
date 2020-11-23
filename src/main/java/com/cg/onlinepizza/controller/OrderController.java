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

import com.cg.onlinepizza.dto.OrderDTO;
import com.cg.onlinepizza.exceptions.DatabaseException;
import com.cg.onlinepizza.exceptions.OrderIdNotFoundException;
import com.cg.onlinepizza.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PutMapping("/update")
	public ResponseEntity<List<OrderDTO>> updateOrder(
			@Valid @RequestBody OrderDTO order){
		List<OrderDTO> orders = orderService.updateOrder(order);
		return new ResponseEntity<List<OrderDTO>>(orders,HttpStatus.OK);
	}

	@PostMapping("/insert")
	public ResponseEntity<List<OrderDTO>> insertOrder(
			@Valid @RequestBody OrderDTO order) throws DatabaseException{
		List<OrderDTO> orders = orderService.saveOrder(order);
		return new ResponseEntity<List<OrderDTO>>(orders,HttpStatus.OK);
	}

	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<List<OrderDTO>> deleteOrder(
			@PathVariable("orderId")int orderId){
		List<OrderDTO> orders= orderService.deleteOrder(orderId);
		return new ResponseEntity<List<OrderDTO>>(orders, HttpStatus.OK);
	}

	@GetMapping("/find/{orderId}")
	public ResponseEntity<OrderDTO> findOrder(
			@PathVariable("orderId")int orderId) throws OrderIdNotFoundException{
		OrderDTO order= orderService.findOrder(orderId);
		return new ResponseEntity<OrderDTO>(order, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/findAll")
	public ResponseEntity<List<OrderDTO>> getAllOrders(){
		List<OrderDTO> orders= orderService.getAllOrders();
		if(!orders.isEmpty()) {
			return new ResponseEntity<List<OrderDTO>>(orders, HttpStatus.OK);
		}
		return new ResponseEntity("Sorry! Orders not found!", 
				HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("find/customer/{customerId}")
	public ResponseEntity<List<OrderDTO>> getAllOrdersByCustomer(@PathVariable("customerId") Integer customerId){
		List<OrderDTO> orders = orderService.findOrdersByCustomerId(customerId);
		return new ResponseEntity<List<OrderDTO>>(orders, HttpStatus.OK);
	}
	

}
