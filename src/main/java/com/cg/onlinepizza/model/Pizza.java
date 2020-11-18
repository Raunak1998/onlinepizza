package com.cg.onlinepizza.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pizza_details")
public class Pizza implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pizza_id")
	private int pizzaId;

	@Column(name="type")
	private String pizzaType;
	
	@Column(name="pizza_size") // Small,Medium,Large
	private String pizzaSize;

	@Column(name="pizza_name")
	private String pizzaName;

	@Column(name="description")
	private String pizzaDescription;

	@Column(name="cost")
	private double pizzaCost;

	public Pizza() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pizza(String pizzaType, String pizzaSize, String pizzaName, String pizzaDescription,
			double pizzaCost) {
		super();
		this.pizzaType = pizzaType;
		this.pizzaSize = pizzaSize;
		this.pizzaName = pizzaName;
		this.pizzaDescription = pizzaDescription;
		this.pizzaCost = pizzaCost;
	}

	public int getPizzaId() {
		return pizzaId; 
	}
	public void setPizzaId(int pizzaId) {
		this.pizzaId = pizzaId;
	}
	public String getPizzaType() {
		return pizzaType;
	}
	public void setPizzaType(String pizzaType) {
		this.pizzaType = pizzaType;
	}
	public String getPizzaSize() {
		return pizzaSize;
	}
	public void setPizzaSize(String pizzaSize) {
		this.pizzaSize = pizzaSize;
	}
	public String getPizzaName() {
		return pizzaName;
	}
	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}
	public String getPizzaDescription() {
		return pizzaDescription;
	}
	public void setPizzaDescription(String pizzaDescription) {
		this.pizzaDescription = pizzaDescription;
	}
	public double getPizzaCost() {
		return pizzaCost;
	}
	public void setPizzaCost(double pizzaCost) {
		this.pizzaCost = pizzaCost;
	}
	


}
