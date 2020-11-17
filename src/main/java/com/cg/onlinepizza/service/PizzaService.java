package com.cg.onlinepizza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinepizza.model.Pizza;

@Service
public interface PizzaService {
	public List<Pizza> getAllPizza();

	public Pizza findPizza(Integer pizzaId);

	public List<Pizza> deletePizza(Integer pizzaId);

	public List<Pizza> savePizza(Pizza pizza);

	public List<Pizza> updatePizza(Pizza pizza);

}
