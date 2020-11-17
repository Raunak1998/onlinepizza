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

import com.cg.onlinepizza.model.Order;
import com.cg.onlinepizza.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/update")
	public ResponseEntity<List<Order>> updateCoupon(
			@RequestBody Order order){
		List<Order> orders = orderService.updateOrder(order);
		if(orders.isEmpty())
		{
			return new ResponseEntity("Sorry! Orders not available!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/insert")
	public ResponseEntity<List<Order>> insertCustomer(
			@RequestBody Order order){
		List<Order> orders = orderService.saveOrder(order);
		if(orders.isEmpty())
		{
			return new ResponseEntity("Sorry! Order could not be inserted!",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<List<Order>> deleteCustomer(
			@PathVariable("orderId")int orderId){
		List<Order> orders= orderService.deleteOrder(orderId);
		if(orders.isEmpty() || orders==null) {
			return new ResponseEntity("Sorry! OrderId not available!", 
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/find/{orderId}")
	public ResponseEntity<Order> findCustomer(
			@PathVariable("orderId")int orderId){
		Order order= orderService.findCustomer(orderId);
		if(order==null) {
			return new ResponseEntity("Sorry! Order not found!", 
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/findAll")
	public ResponseEntity<List<Order>> getAllCustomers(){
		List<Order> orders= orderService.getAllOrders();
		if(orders.isEmpty()) {
			return new ResponseEntity("Sorry! Orders not found!", 
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
}
