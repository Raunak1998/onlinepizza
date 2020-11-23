package com.cg.onlinepizza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dto.OrderDTO;
import com.cg.onlinepizza.exceptions.OrderIdNotFoundException;

@Service
public interface OrderService {

	public List<OrderDTO> getAllOrders() throws OrderIdNotFoundException;

	public OrderDTO findOrder(Integer orderId) throws OrderIdNotFoundException;

	public List<OrderDTO> deleteOrder(Integer orderId) throws OrderIdNotFoundException;

	public List<OrderDTO> saveOrder(OrderDTO order);

	public List<OrderDTO> updateOrder(OrderDTO order) throws OrderIdNotFoundException;

	public List<OrderDTO> findOrdersByCustomerId(Integer customerId);
}
