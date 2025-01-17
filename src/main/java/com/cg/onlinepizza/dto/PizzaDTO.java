package com.cg.onlinepizza.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PizzaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "PizzaId cannot be null")
	private int pizzaId;

	@NotBlank(message = "PizzaType cannot be null")
	private String pizzaType;
	
	// Small,Medium,Large
	@NotBlank(message = "Pizza size cannot be blank. Should be among Small,Medium and Large")
	private String pizzaSize;

	@NotNull(message = "Pizza name cannot be null")
	private String pizzaName;

	private String pizzaDescription;

	@NotNull(message = "Pizza Cost cannot be null")
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
