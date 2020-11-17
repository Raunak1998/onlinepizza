package com.cg.onlinepizza.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.model.Order;
import com.cg.onlinepizza.repository.OrderRepository;
import com.cg.onlinepizza.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepo.findAll(); 
	}

	@Override
	public Order findCustomer(Integer orderId) {
		Optional<Order>order=orderRepo.findById(orderId);
		return order.get();
	}

	@Override
	public List<Order> deleteOrder(Integer orderId) {
		orderRepo.deleteById(orderId);
		return orderRepo.findAll();
	}

	@Override
	public List<Order> saveOrder(Order order) {
		orderRepo.saveAndFlush(order);

		return  orderRepo.findAll();
	}

	@Override
	public List<Order> updateOrder(Order order) {
		orderRepo.save(order);

		return  orderRepo.findAll();
	}

}
