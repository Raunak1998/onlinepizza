package com.cg.onlinepizza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinepizza.model.Order;

@Service
public interface OrderService {

	public List<Order> getAllOrders();

	public Order findCustomer(Integer orderId);

	public List<Order> deleteOrder(Integer orderId);

	public List<Order> saveOrder(Order order);

	public List<Order> updateOrder(Order order);
}
