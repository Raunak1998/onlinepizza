package com.cg.onlinepizza.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class OrderDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "OrderId cannot be null")
	private int orderId;
	
	@NotNull(message = "Order date cannot be blank. it should be present or future date")
	@FutureOrPresent
	private LocalDate orderDate;
	
	@NotNull(message = "Total Cost should be present")
	@Positive
	private double totalCost;
	
	@NotNull(message = "Customer should be assigned cannot be null")
	private CustomerDTO customer;
	
	@NotNull(message = "Pizzas should be present in the order to place it.")
	private List<PizzaDTO> pizzas;

	private CouponDTO coupon; 


	public OrderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDTO(LocalDate orderDate, double totalCost, CustomerDTO customer, List<PizzaDTO> pizzas,
			CouponDTO coupon) {
		super();
		this.orderDate = orderDate;
		this.totalCost = totalCost;
		this.customer = customer;
		this.pizzas = pizzas;
		this.coupon = coupon;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}


	public List<PizzaDTO> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<PizzaDTO> pizzas) {
		this.pizzas = pizzas;
	}

	public CouponDTO getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponDTO coupon) {
		this.coupon = coupon;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDTO other = (OrderDTO) obj;
		if (orderId != other.orderId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", orderDate=" + orderDate + ", totalCost=" + totalCost + ", customer="
				+ customer + ", pizzas=" + pizzas + ", coupon=" + coupon + "]";
	}

}

