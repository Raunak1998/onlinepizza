package com.cg.onlinepizza.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dto.PizzaDTO;
import com.cg.onlinepizza.exceptions.PizzaAlreadyExistsException;
import com.cg.onlinepizza.exceptions.PizzaIdNotFoundException;
import com.cg.onlinepizza.model.Pizza;
import com.cg.onlinepizza.repository.PizzaRepository;
import com.cg.onlinepizza.service.PizzaService;

@Service
public class PizzaServiceImpl implements PizzaService{

	@Autowired
	private PizzaRepository pizzaRepository;
	
	public static PizzaDTO entityToDTO(Pizza pizza) 
	{
		
		PizzaDTO pizzaDTO = new PizzaDTO();
		pizzaDTO.setPizzaId(pizza.getPizzaId());
		pizzaDTO.setPizzaName(pizza.getPizzaName());
		pizzaDTO.setPizzaSize(pizza.getPizzaSize());
		pizzaDTO.setPizzaType(pizza.getPizzaType());
		pizzaDTO.setPizzaCost(pizza.getPizzaCost());
		pizzaDTO.setPizzaDescription(pizza.getPizzaDescription());
		
		return pizzaDTO;
	}
	
	public static Pizza DTOToEntity(PizzaDTO pizzaDTO) 
	{
		
		Pizza pizza = new Pizza();
		pizza.setPizzaId(pizzaDTO.getPizzaId());
		pizza.setPizzaName(pizzaDTO.getPizzaName());
		pizza.setPizzaSize(pizzaDTO.getPizzaSize());
		pizza.setPizzaType(pizzaDTO.getPizzaType());
		pizza.setPizzaCost(pizzaDTO.getPizzaCost());
		pizza.setPizzaDescription(pizzaDTO.getPizzaDescription());
		
		return pizza;
	}
	

	@Override
	public List<PizzaDTO> getAllPizza() {
		List<PizzaDTO> pizzaDTOReturn = new ArrayList<>();
		for(Pizza p:pizzaRepository.findAll())
		{
			pizzaDTOReturn.add(entityToDTO(p));
		}
		
		return pizzaDTOReturn;
	}

	@Override
	public List<PizzaDTO> deletePizza(Integer pizzaId) throws PizzaIdNotFoundException{
		
		List<PizzaDTO> pizzaDTOReturn = new ArrayList<>();
		
		Optional<Pizza>checkPizza=pizzaRepository.findById(pizzaId);
		if(!checkPizza.isPresent())
		{
			throw new PizzaIdNotFoundException("Pizza with id " + pizzaId + " not Found");
		}
		
		pizzaRepository.deleteById(pizzaId);
		
		List<Pizza>PizzaEntityList=pizzaRepository.findAll();
		
		if(PizzaEntityList==null || PizzaEntityList.isEmpty())
		{
			throw new PizzaIdNotFoundException("No Pizzas Found");
		}
			
		for(Pizza p:pizzaRepository.findAll())
		{
			pizzaDTOReturn.add(entityToDTO(p));
		}
		return pizzaDTOReturn;
	}

	@Override
	public List<PizzaDTO> savePizza(PizzaDTO pizzaDTO) throws PizzaAlreadyExistsException{
		Pizza pizza = new Pizza();
		pizza = DTOToEntity(pizzaDTO);
		Optional<Pizza>returnPizza=pizzaRepository.findById(pizza.getPizzaId());
		if(returnPizza.isPresent())
		{
			throw new PizzaAlreadyExistsException("Pizza can not be added");
		}
		pizzaRepository.saveAndFlush(pizza);
		
		List<PizzaDTO> pizzaDTOReturn = new ArrayList<>();
		for(Pizza p:pizzaRepository.findAll())
		{
			pizzaDTOReturn.add(entityToDTO(p));
		}
		return pizzaDTOReturn;
	}

	@Override
	public List<PizzaDTO> updatePizza(PizzaDTO pizzaDTO) throws PizzaIdNotFoundException{
		Pizza pizza = new Pizza();
		pizza = DTOToEntity(pizzaDTO);
		Optional<Pizza>checkPizza=pizzaRepository.findById(pizza.getPizzaId());
		if(!checkPizza.isPresent())
		{
			throw new PizzaIdNotFoundException("Pizza with id " + pizza.getPizzaId()+ "not Found");
		}
		
		pizzaRepository.save(pizza);
		
		List<PizzaDTO> pizzaDTOReturn = new ArrayList<>();
		for(Pizza p:pizzaRepository.findAll())
		{
			pizzaDTOReturn.add(entityToDTO(p));
		}
		
		return  pizzaDTOReturn;
	}
	@Override
	public PizzaDTO findPizza(Integer pizzaId) throws PizzaIdNotFoundException{
		Optional<Pizza>checkPizza=pizzaRepository.findById(pizzaId);
		if(!checkPizza.isPresent())
		{
			throw new PizzaIdNotFoundException("Pizza with id " + pizzaId+ "not Found");
		}
		return entityToDTO(checkPizza.get());
	}

}
