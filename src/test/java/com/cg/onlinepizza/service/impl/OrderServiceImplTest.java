package com.cg.onlinepizza.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.onlinepizza.dto.CouponDTO;
import com.cg.onlinepizza.dto.CustomerDTO;
import com.cg.onlinepizza.dto.OrderDTO;
import com.cg.onlinepizza.dto.PizzaDTO;
import com.cg.onlinepizza.exceptions.CustomerNotFoundException;
import com.cg.onlinepizza.exceptions.OrderIdNotFoundException;
import com.cg.onlinepizza.model.Coupon;
import com.cg.onlinepizza.model.Customer;
import com.cg.onlinepizza.model.Order;
import com.cg.onlinepizza.model.Pizza;
import com.cg.onlinepizza.repository.OrderRepository;

@SpringBootTest
public class OrderServiceImplTest {

	@Autowired
	private OrderServiceImpl orderService;

	@MockBean
	private OrderRepository orderRepository;

	@Test
	public void getAllOrdersPresentTest()
	{
		CustomerDTO customer = new CustomerDTO("ABC", "DEF", 1234567890L, "abc@gmail.com", "Address", "Username", "Password", null);
		PizzaDTO pizzaDTO = new PizzaDTO("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		Set<PizzaDTO> pizzas = new HashSet<>();
		pizzas.add(pizzaDTO);
		CouponDTO coupon = new CouponDTO("GET50", "50% OFF", "On Orders above 500Rs");
		OrderDTO order1 = new OrderDTO(LocalDate.now(), 1500, customer, pizzas , coupon);
		OrderDTO order2 = new OrderDTO(LocalDate.now(), 2000, customer, pizzas , coupon);
		Mockito.when(orderRepository.findAll()).thenReturn(Stream.of(OrderServiceImpl.DTOToEntity(order1),OrderServiceImpl.DTOToEntity(order2)).collect(Collectors.toList()));
		List<OrderDTO> actual = orderService.getAllOrders();
		assertEquals(2,actual.size());
	}

	@Test
	public void getAllOrdersNotPresentTest()
	{
		Mockito.when(orderRepository.findAll()).thenThrow(new OrderIdNotFoundException("No orders present in the database"));

		Exception exception = assertThrows(OrderIdNotFoundException.class,()->orderService.getAllOrders());
		assertTrue(exception.getMessage().contains("No orders present in the database"));
	}

	@Test
	public void findOrderNullTest()
	{
		Integer orderId = null;
		OrderDTO actual = orderService.findOrder(orderId);
		assertNull(actual);
	}

	@Test
	public void findOrderPresentTest()
	{
		Customer customer = new Customer("ABC", "DEF", 1234567890L, "abc@gmail.com", "Address", "Username", "Password", null);
		Pizza pizza = new Pizza("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		Set<Pizza> pizzas = new HashSet<>();
		pizzas.add(pizza);
		Coupon coupon = new Coupon("GET50", "50% OFF", "On Orders above 500Rs");
		Order order1 = new Order(LocalDate.now(), 1500, customer, pizzas , coupon);
		order1.setOrderId(1);

		Mockito.when(orderRepository.findById(order1.getOrderId())).thenReturn(Optional.of(order1));

		OrderDTO actual = orderService.findOrder(order1.getOrderId());
		assertEquals(OrderServiceImpl.entityToDTO(order1),actual);
	}

	@Test
	public void findOrderNotPresentTest()
	{
		Customer customer = new Customer("ABC", "DEF", 1234567890L, "abc@gmail.com", "Address", "Username", "Password", null);
		Pizza pizza = new Pizza("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		Set<Pizza> pizzas = new HashSet<>();
		pizzas.add(pizza);
		Coupon coupon = new Coupon("GET50", "50% OFF", "On Orders above 500Rs");
		Order order1 = new Order(LocalDate.now(), 1500, customer, pizzas , coupon);
		order1.setOrderId(1);

		Mockito.when(orderRepository.findAll()).thenThrow(new OrderIdNotFoundException("OrderId not present in the database"));
		Exception exception = assertThrows(OrderIdNotFoundException.class,()->orderService.findOrder(2));
		assertTrue(exception.getMessage().contains("OrderId not present in the database"));
	}

	@Test
	public void deleteOrderNullTest()
	{
		Integer orderId = null;
		List<OrderDTO> actual = orderService.deleteOrder(orderId);
		assertNull(actual);
	}

	@Test
	public void updateOrderNullTest()
	{
		OrderDTO order1 = null;
		List<OrderDTO> actual = orderService.updateOrder(order1);
		assertNull(actual);
	}

	@Test
	public void updateOrderPresentTest()
	{
		Customer customer = new Customer("ABC", "DEF", 1234567890L, "abc@gmail.com", "Address", "Username", "Password", null);
		Pizza pizza = new Pizza("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		Set<Pizza> pizzas = new HashSet<>();
		pizzas.add(pizza);
		Coupon coupon = new Coupon("GET50", "50% OFF", "On Orders above 500Rs");
		Order order = new Order(LocalDate.now(), 1500, customer, pizzas , coupon);
		order.setOrderId(1);
		Mockito.when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
		//		order.setOrderId(2);
		Mockito.when(orderRepository.save(order)).thenReturn(order);
		Mockito.when(orderRepository.findAll()).thenReturn(Stream.of(order).collect(Collectors.toList()));

		List<OrderDTO> actual = orderService.updateOrder(OrderServiceImpl.entityToDTO(order));
		assertEquals(1,actual.size());
	}

	@Test
	public void updateOrderNotPresentTest()
	{
		Customer customer = new Customer("ABC", "DEF", 1234567890L, "abc@gmail.com", "Address", "Username", "Password", null);
		Pizza pizza = new Pizza("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		Set<Pizza> pizzas = new HashSet<>();
		pizzas.add(pizza);
		Coupon coupon = new Coupon("GET50", "50% OFF", "On Orders above 500Rs");
		Order order = new Order(LocalDate.now(), 1500, customer, pizzas , coupon);
		order.setOrderId(1);

		Mockito.when(orderRepository.save(order)).thenThrow(new OrderIdNotFoundException("OrderId not present in the database"));
		Exception exception = assertThrows(OrderIdNotFoundException.class,()->orderService.findOrder(2));
		assertTrue(exception.getMessage().contains("OrderId not present in the database"));
	}

	@Test
	public void saveOrderNullTest()
	{
		OrderDTO order1 = null;
		List<OrderDTO> actual = orderService.saveOrder(order1);
		assertNull(actual);
	}

	@Test
	public void saveOrderPresentTest()
	{
		Customer customer = new Customer("ABC", "DEF", 1234567890L, "abc@gmail.com", "Address", "Username", "Password", null);
		Pizza pizza = new Pizza("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		Set<Pizza> pizzas = new HashSet<>();
		pizzas.add(pizza);
		Coupon coupon = new Coupon("GET50", "50% OFF", "On Orders above 500Rs");
		Order order = new Order(LocalDate.now(), 1500, customer, pizzas , coupon);
		order.setOrderId(1);

		Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(order));
		Mockito.when(orderRepository.saveAndFlush(order)).thenReturn(order);

		Mockito.when(orderRepository.findAll()).thenReturn(Stream.of(order).collect(Collectors.toList()));
		List<OrderDTO> actual = orderService.updateOrder(OrderServiceImpl.entityToDTO(order));
		assertEquals(1,actual.size());
	}

	@Test
	public void findOrdersByCustomerIdPresentTest()
	{
		Customer customer = new Customer("ABC", "DEF", 1234567890L, "abc@gmail.com", "Address", "Username", "Password", null);
		customer.setCustomerId(1);
		Pizza pizza = new Pizza("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		Set<Pizza> pizzas = new HashSet<>();
		pizzas.add(pizza);
		Coupon coupon = new Coupon("GET50", "50% OFF", "On Orders above 500Rs");
		Order order = new Order(LocalDate.now(), 1500, customer, pizzas , coupon);
		order.setOrderId(1);
		Mockito.when(orderRepository.findByCustomerCustomerId(order.getCustomer().getCustomerId())).thenReturn(Stream.of(order).collect(Collectors.toList()));

		OrderDTO orderReturned= OrderServiceImpl.entityToDTO(order);
		List<OrderDTO> ordersExpected = Stream.of(orderReturned).collect(Collectors.toList()); 

		List<OrderDTO> actual = orderService.findOrdersByCustomerId(1);
		assertEquals(ordersExpected,actual);
	}

	@Test
	public void findOrdersByCustomerIdNotPresentTest()
	{
		Customer customer = new Customer("ABC", "DEF", 1234567890L, "abc@gmail.com", "Address", "Username", "Password", null);
		Pizza pizza = new Pizza("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		Set<Pizza> pizzas = new HashSet<>();
		pizzas.add(pizza);
		Coupon coupon = new Coupon("GET50", "50% OFF", "On Orders above 500Rs");
		Order order1 = new Order(LocalDate.now(), 1500, customer, pizzas , coupon);
		order1.setOrderId(1);

		Mockito.when(orderRepository.findByCustomerCustomerId(order1.getCustomer().getCustomerId())).thenThrow(new CustomerNotFoundException("Customer not present"));
		Exception exception = assertThrows(CustomerNotFoundException.class,()->orderService.findOrdersByCustomerId(2));
		assertTrue(exception.getMessage().contains("Customer not present"));
	}
	
	@Test
	public void findOrdersByCustomerNullTest() {
		
		Integer customerId = null;
		
		List<OrderDTO> actual = orderService.findOrdersByCustomerId(customerId);
		assertNull(actual);
	}
}
