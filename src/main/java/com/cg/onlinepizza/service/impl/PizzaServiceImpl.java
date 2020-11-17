package com.cg.onlinepizza.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.model.Pizza;
import com.cg.onlinepizza.repository.PizzaRepository;
import com.cg.onlinepizza.service.PizzaService;

@Service
public class PizzaServiceImpl implements PizzaService{

	@Autowired
	private PizzaRepository pizzaRepo;

	@Override
	public List<Pizza> getAllPizza() {
		return pizzaRepo.findAll();
	}

	@Override
	public List<Pizza> deletePizza(Integer pizzaId) {
		pizzaRepo.deleteById(pizzaId);
		return pizzaRepo.findAll();
	}

	@Override
	public List<Pizza> savePizza(Pizza pizza) {
		pizzaRepo.saveAndFlush(pizza);

		return  pizzaRepo.findAll();
	}

	@Override
	public List<Pizza> updatePizza(Pizza pizza) {
		pizzaRepo.save(pizza);

		return  pizzaRepo.findAll();
	}
	@Override
	public Pizza findPizza(Integer pizzaId) {
		Optional<Pizza>pizza=pizzaRepo.findById(pizzaId);
		return pizza.get();
	}

}
