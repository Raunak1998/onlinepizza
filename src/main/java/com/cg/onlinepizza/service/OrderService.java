package com.cg.onlinepizza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dto.OrderDTO;

@Service
public interface OrderService {

	public List<OrderDTO> getAllOrders();

	public OrderDTO findCustomer(Integer orderId);

	public List<OrderDTO> deleteOrder(Integer orderId);

	public List<OrderDTO> saveOrder(OrderDTO order);

	public List<OrderDTO> updateOrder(OrderDTO order);
}
