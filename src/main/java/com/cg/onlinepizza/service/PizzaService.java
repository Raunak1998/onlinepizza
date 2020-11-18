package com.cg.onlinepizza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dto.PizzaDTO;

@Service
public interface PizzaService {
	public List<PizzaDTO> getAllPizza();

	public PizzaDTO findPizza(Integer pizzaId);

	public List<PizzaDTO> deletePizza(Integer pizzaId);

	public List<PizzaDTO> savePizza(PizzaDTO pizzaDTO);

	public List<PizzaDTO> updatePizza(PizzaDTO pizzaDTO);

}
