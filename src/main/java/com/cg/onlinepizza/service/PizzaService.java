package com.cg.onlinepizza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dto.PizzaDTO;
import com.cg.onlinepizza.exceptions.PizzaAlreadyExistsException;
import com.cg.onlinepizza.exceptions.PizzaIdNotFoundException;

@Service
public interface PizzaService {
	public List<PizzaDTO> getAllPizza();

	public PizzaDTO findPizza(Integer pizzaId);

	public List<PizzaDTO> deletePizza(Integer pizzaId) throws PizzaIdNotFoundException;

	public List<PizzaDTO> savePizza(PizzaDTO pizzaDTO) throws PizzaAlreadyExistsException;

	public List<PizzaDTO> updatePizza(PizzaDTO pizzaDTO) throws PizzaIdNotFoundException;

}
