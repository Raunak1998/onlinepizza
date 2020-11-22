package com.cg.onlinepizza.dto;

import java.io.Serializable;

public class PizzaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pizzaId;

	private String pizzaType;
	
	// Small,Medium,Large
	private String pizzaSize;

	private String pizzaName;

	private String pizzaDescription;

	private double pizzaCost;

	public PizzaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PizzaDTO(String pizzaType, String pizzaSize, String pizzaName, String pizzaDescription,
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pizzaId;
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
		PizzaDTO other = (PizzaDTO) obj;
		if (pizzaId != other.pizzaId)
			return false;
		return true;
	}

	
	
	
}
