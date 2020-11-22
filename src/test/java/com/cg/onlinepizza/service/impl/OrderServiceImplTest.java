package com.cg.onlinepizza.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlinepizza.dto.CouponDTO;
import com.cg.onlinepizza.dto.CustomerDTO;
import com.cg.onlinepizza.dto.OrderDTO;
import com.cg.onlinepizza.dto.PizzaDTO;
import com.cg.onlinepizza.exceptions.OrderIdNotFoundException;
import com.cg.onlinepizza.model.Order;
import com.cg.onlinepizza.repository.OrderRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

	@Autowired
	private OrderServiceImpl orderService;
	
	@MockBean
	private OrderRepository orderRepository;
	
	@Test
	public void getAllOrders()
	{
		CustomerDTO customer = new CustomerDTO(1, "ABC", "DEF", 1234567890L, "abc@gmail.com", "Address", "Username", "Password", null);
		PizzaDTO pizzaDTO = new PizzaDTO(1, "Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		Set<PizzaDTO> pizzas = new HashSet<>();
		pizzas.add(pizzaDTO);
		CouponDTO coupon = new CouponDTO("GET50", "50% OFF", "On Orders above 500Rs");
		OrderDTO order1 = new OrderDTO(1, LocalDate.now(), 1500, customer, pizzas , coupon);
		OrderDTO order2 = new OrderDTO(1, LocalDate.now(), 2000, customer, pizzas , coupon);
		Mockito.when(orderRepository.findAll()).thenReturn(Stream.of(OrderServiceImpl.DTOToEntity(order1),OrderServiceImpl.DTOToEntity(order2)).collect(Collectors.toList()));
		List<OrderDTO> actual = orderService.getAllOrders();
		assertEquals(2,actual.size());
	}
	
	@Test
	public void getAllOrdersNotPresent()
	{
		Mockito.when(orderRepository.findAll()).thenThrow(new OrderIdNotFoundException("No orders present in the database"));

		Exception exception = assertThrows(OrderIdNotFoundException.class,()->orderRepository.findAll());
		assertTrue(exception.getMessage().contains("No orders present in the database"));
	}
	
	@Test
	public void findOrderNull()
	{
		Integer orderId = null;

		Mockito.when(orderRepository.findById(orderId)).thenThrow(new OrderIdNotFoundException("OrderId could not be null"));

		Exception exception = assertThrows(OrderIdNotFoundException.class,()->orderRepository.findById(orderId));
		assertTrue(exception.getMessage().contains("OrderId could not be null"));
	}
	
	@Test
	public void findOrder()
	{
		Integer orderId = 1;
		Optional<Order> order = Optional.empty();

		Mockito.when(orderRepository.findById(orderId)).thenReturn(order);

		Optional<Order> returnedOrder = orderRepository.findById(orderId);
		assertEquals(order,returnedOrder);
	}
}
