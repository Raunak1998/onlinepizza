package com.cg.onlinepizza.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dto.CouponDTO;
import com.cg.onlinepizza.dto.CustomerDTO;
import com.cg.onlinepizza.dto.OrderDTO;
import com.cg.onlinepizza.dto.PizzaDTO;
import com.cg.onlinepizza.exceptions.CustomerNotFoundException;
import com.cg.onlinepizza.exceptions.DatabaseException;
import com.cg.onlinepizza.exceptions.OrderIdNotFoundException;
import com.cg.onlinepizza.model.Coupon;
import com.cg.onlinepizza.model.Customer;
import com.cg.onlinepizza.model.Order;
import com.cg.onlinepizza.model.Pizza;
import com.cg.onlinepizza.repository.OrderRepository;
import com.cg.onlinepizza.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	static Logger log = Logger.getLogger(OrderServiceImpl.class.getName());
	
	@Autowired
	private OrderRepository orderRepository;

	public static OrderDTO entityToDTO(Order order) 
	{
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setOrderDate(order.getOrderDate());
		orderDTO.setTotalCost(order.getTotalCost());

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(order.getCustomer().getCustomerId());
		customerDTO.setFirstName(order.getCustomer().getFirstName());
		customerDTO.setLastName(order.getCustomer().getLastName());
		customerDTO.setCustomerMobile(order.getCustomer().getCustomerMobile());
		customerDTO.setCustomerAddress(order.getCustomer().getCustomerAddress());
		customerDTO.setUserName(order.getCustomer().getUserName());
		customerDTO.setPassword(order.getCustomer().getPassword());
		customerDTO.setCustomerEmail(order.getCustomer().getCustomerEmail());
		customerDTO.setOrder(null);

		orderDTO.setCustomer(customerDTO);

		Set<PizzaDTO> pizzasDTO = new HashSet<>();
		Set<Pizza> pizzas = new HashSet<>(order.getPizzas());
		for(Pizza p:pizzas)
		{
			PizzaDTO pizzaDTO = new PizzaDTO();
			pizzaDTO.setPizzaId(p.getPizzaId());
			pizzaDTO.setPizzaName(p.getPizzaName());
			pizzaDTO.setPizzaSize(p.getPizzaSize());
			pizzaDTO.setPizzaType(p.getPizzaType());
			pizzaDTO.setPizzaCost(p.getPizzaCost());
			pizzaDTO.setPizzaDescription(p.getPizzaDescription());
			pizzasDTO.add(pizzaDTO);
		}

		orderDTO.setPizzas(pizzasDTO);

		CouponDTO couponDTO = new CouponDTO();
		couponDTO.setCouponName(order.getCoupon().getCouponName());
		couponDTO.setCouponType(order.getCoupon().getCouponType());
		couponDTO.setCouponDescription(order.getCoupon().getCouponDescription());

		orderDTO.setCoupon(couponDTO);
		return orderDTO;
	}

	public static Order DTOToEntity(OrderDTO orderDTO)
	{
		Order order = new Order();
		order.setOrderId(orderDTO.getOrderId());
		order.setOrderDate(orderDTO.getOrderDate());
		order.setTotalCost(orderDTO.getTotalCost());

		Customer customer = new Customer();
		customer.setCustomerId(orderDTO.getCustomer().getCustomerId());
		customer.setFirstName(orderDTO.getCustomer().getFirstName());
		customer.setLastName(orderDTO.getCustomer().getLastName());
		customer.setCustomerMobile(orderDTO.getCustomer().getCustomerMobile());
		customer.setCustomerAddress(orderDTO.getCustomer().getCustomerAddress());
		customer.setUserName(orderDTO.getCustomer().getUserName());
		customer.setPassword(orderDTO.getCustomer().getPassword());
		customer.setCustomerEmail(orderDTO.getCustomer().getCustomerEmail());	
		customer.setOrder(null);

		order.setCustomer(customer);

		Set<Pizza> pizzas = new HashSet<>();
		Set<PizzaDTO> pizzasDTO = new HashSet<>(orderDTO.getPizzas());
		for(PizzaDTO p:pizzasDTO)
		{
			Pizza pizza = new Pizza();
			pizza.setPizzaId(p.getPizzaId());
			pizza.setPizzaName(p.getPizzaName());
			pizza.setPizzaSize(p.getPizzaSize());
			pizza.setPizzaType(p.getPizzaType());
			pizza.setPizzaCost(p.getPizzaCost());
			pizza.setPizzaDescription(p.getPizzaDescription());
			pizzas.add(pizza);
		}

		order.setPizzas(pizzas);

		Coupon coupon = new Coupon();
		coupon.setCouponName(orderDTO.getCoupon().getCouponName());
		coupon.setCouponType(orderDTO.getCoupon().getCouponType());
		coupon.setCouponDescription(orderDTO.getCoupon().getCouponDescription());

		order.setCoupon(coupon);
		return order;
	}


	//Method that returns List of all orders present in the database.
	@SuppressWarnings("unused")
	@Override
	public List<OrderDTO> getAllOrders() throws OrderIdNotFoundException{
		
		log.info("Service Layer - Entry - Get All Orders");
		
		List<OrderDTO> orderDTOReturn = new ArrayList<>();
		for(Order o:orderRepository.findAll())
		{
			orderDTOReturn.add(entityToDTO(o));
		}
		if(orderDTOReturn==null)
			throw new OrderIdNotFoundException("Orders not present in the database");

		log.info("Service Layer - Exit - Get All Orders");
		return  orderDTOReturn;
	}

	//Method return the details of the particular OrderId.
	@Override
	public OrderDTO findOrder(Integer orderId) throws OrderIdNotFoundException {
		
		log.info("Service Layer - Entry - Get Orders By Id");
		
		if(orderId == null)
			return null;
		
		Optional<Order>order = orderRepository.findById(orderId);
		if(!order.isPresent())
		{
			throw new OrderIdNotFoundException("OrderId not present in the database!");
		}
		
		log.info("Service Layer - Exit - Get Orders By Id");
		
		return entityToDTO(order.get());
	}

	//Method removes the the order permanently from the database
	@Override
	public List<OrderDTO> deleteOrder(Integer orderId) throws OrderIdNotFoundException{
		
		log.info("Service Layer - Entry - Delete Order");
		
		if(orderId == null)
			return null;
		
		Optional<Order>order = orderRepository.findById(orderId);
		if(!order.isPresent())
		{
			throw new OrderIdNotFoundException("OrderId not present in the database!");
		}
		
		log.warn("WARN: delete orders Started");
		
		orderRepository.deleteById(orderId);
		List<OrderDTO> orderDTOReturn = new ArrayList<>();
		for(Order o:orderRepository.findAll())
		{
			orderDTOReturn.add(entityToDTO(o));
		}
		
		log.info("Service Layer - Exit - Delete Orders");
		
		return  orderDTOReturn;
	}

	//Methods adds the details of the order in the database.
	@Override
	public List<OrderDTO> saveOrder(OrderDTO orderDTO) throws DatabaseException{
		
		log.info("Service Layer - Entry - Save Orders");
		if(orderDTO==null)
			return null;
		Order order = new Order();
		order = DTOToEntity(orderDTO);
		Order orderCheck = new Order();
		orderCheck = orderRepository.saveAndFlush(order);
		if(orderCheck == null)
		{
			throw new DatabaseException("Order cannot be added!");
		}
		List<OrderDTO> orderDTOReturn = new ArrayList<>();
		for(Order o:orderRepository.findAll())
		{
			orderDTOReturn.add(entityToDTO(o));
		}

		log.info("Service Layer - Exit - Save Orders");
		return  orderDTOReturn;
	}

	//Method checks for the existing orders in the database and updates the details.
	@Override
	public List<OrderDTO> updateOrder(OrderDTO orderDTO) throws OrderIdNotFoundException{
		
		log.info("Service Layer - Entry - Update Orders");
		if(orderDTO == null)
			return null;
		Optional<Order>order = orderRepository.findById(orderDTO.getOrderId());
		if(!order.isPresent())
		{
			throw new OrderIdNotFoundException("OrderId not present in the database!");
		}
		Order order1 = new Order();
		order1 = DTOToEntity(orderDTO);
		orderRepository.save(order1);
		List<OrderDTO> orderDTOReturn = new ArrayList<>();
		for(Order o:orderRepository.findAll())
		{
			orderDTOReturn.add(entityToDTO(o));
		}
		
		log.info("Service Layer - Exit - Update Orders");
		
		return  orderDTOReturn;
	}
	
	//Method returns all the orders based on the given customerId.
	@Override
	public List<OrderDTO> findOrdersByCustomerId(Integer customerId) throws CustomerNotFoundException{
		log.info("Service Layer - Entry - Find Orders By CustomerId");
		if (customerId == null)
			return null;
		
		List<Order> orders = new ArrayList<>();
		orderRepository.findByCustomerCustomerId(customerId).forEach(orders::add);
		
		if(orders.size()<1)
			throw new CustomerNotFoundException("Customer not present");
		
		List<OrderDTO> ordersReturn = new ArrayList<>();
		for (Order o : orders ) {
			ordersReturn.add(entityToDTO(o));
		}
		log.info("Service Layer - Exit - Find Orders By Customer");
		return ordersReturn;
	}
}
