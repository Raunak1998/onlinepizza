package com.cg.onlinepizza.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "order_details")
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	
	@FutureOrPresent
	@Column(name = "order_date")
	private LocalDate orderDate;
	
	@Positive
	@Column(name = "total_cost")
	private double totalCost;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@NotNull
	@ManyToMany
	@JoinTable(name = "pizza_order_details", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "pizza_id")})
	private Set<Pizza> pizzas;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "coupon_name")
	private Coupon coupon; 

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int orderId, LocalDate orderDate, double totalCost, Customer customer, Set<Pizza> pizzas, Coupon coupon) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
		this.customer = customer;
		this.pizzas = pizzas;
		this.coupon = coupon;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Set<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
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
		Order other = (Order) obj;
		if (orderId != other.orderId)
			return false;
		return true;
	}
	
}
