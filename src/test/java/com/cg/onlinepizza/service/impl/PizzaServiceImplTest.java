package com.cg.onlinepizza.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import com.cg.onlinepizza.dto.PizzaDTO;
import com.cg.onlinepizza.exceptions.PizzaIdNotFoundException;
import com.cg.onlinepizza.model.Pizza;
import com.cg.onlinepizza.repository.PizzaRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
public class PizzaServiceImplTest {

	@Autowired
	private PizzaServiceImpl pizzaService;

	@MockBean
	private PizzaRepository pizzaRepository;

	@Test
	public void getAllPizzasPresent()
	{
	
		PizzaDTO pizza1 = new PizzaDTO("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		PizzaDTO pizza2 = new PizzaDTO("Non Veg", "Large", "Non Veg Exotica", "Premium Non Veg Pizza", 600);
		
		
		Mockito.when(pizzaRepository.findAll()).thenReturn(Stream.of(PizzaServiceImpl.DTOToEntity(pizza1),PizzaServiceImpl.DTOToEntity(pizza2)).collect(Collectors.toList()));
		List<PizzaDTO> actual = pizzaService.getAllPizza();
		assertEquals(2,actual.size());
	}

	@Test
	public void getAllPizzasNotPresent()
	{
		Mockito.when(pizzaRepository.findAll()).thenThrow(new PizzaIdNotFoundException("No pizzas present in the database"));

		Exception exception = assertThrows(PizzaIdNotFoundException.class,()->pizzaService.getAllPizza());
		assertTrue(exception.getMessage().contains("No pizzas present in the database"));
	}

	@Test
	public void findPizzaNull()
	{
		Integer pizzaId = null;
		PizzaDTO actual = pizzaService.findPizza(pizzaId);
		assertNull(actual);
	}

	@Test
	public void findPizzaPresent()
	{
		
		Pizza pizza1 = new Pizza("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		
		pizza1.setPizzaId(1);

		Mockito.when(pizzaRepository.findById(pizza1.getPizzaId())).thenReturn(Optional.of(pizza1));

		PizzaDTO actual = pizzaService.findPizza(pizza1.getPizzaId());
		assertEquals(PizzaServiceImpl.entityToDTO(pizza1),actual);
	}

	@Test
	public void findPizzaNotPresent()
	{
		Pizza pizza1 = new Pizza("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		
		pizza1.setPizzaId(1);

		Mockito.when(pizzaRepository.findById(2)).thenThrow(new PizzaIdNotFoundException("PizzaId not present in the database"));
		Exception exception = assertThrows(PizzaIdNotFoundException.class,()->pizzaService.findPizza(2));
		assertTrue(exception.getMessage().contains("PizzaId not present in the database"));
	}
	
	
	@Test
	public void deletePizzaPresent() throws PizzaIdNotFoundException {
		
		Pizza pizza = new Pizza("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		pizza.setPizzaId(1);
		Mockito.when(pizzaRepository.findById(1)).thenReturn(Optional.of(pizza));

		Mockito.when(pizzaRepository.save(pizza)).thenReturn(pizza);
		Mockito.when(pizzaRepository.findAll()).thenReturn(Stream.of(pizza).collect(Collectors.toList()));

		List<PizzaDTO> actual = pizzaService.deletePizza(pizza.getPizzaId());
		assertEquals(1,actual.size());
	}
	
	
	

	@Test
	public void deletePizzaNull()
	{
		Integer pizzaId = null;
		List<PizzaDTO> actual = pizzaService.deletePizza(pizzaId);
		assertNull(actual);
	}

	@Test
	public void updatePizzaNull()
	{
		PizzaDTO pizza1 = null;
		List<PizzaDTO> actual = pizzaService.updatePizza(pizza1);
		assertNull(actual);
	}

	
	

	@Test
	public void updatePizzaPresent()
	{
		
		Pizza pizza = new Pizza("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		
		pizza.setPizzaId(1);

		Mockito.when(pizzaRepository.findById(1)).thenReturn(Optional.of(pizza));

		Mockito.when(pizzaRepository.save(pizza)).thenReturn(pizza);

		Mockito.when(pizzaRepository.findAll()).thenReturn(Stream.of(pizza).collect(Collectors.toList()));

		List<PizzaDTO> actual = pizzaService.updatePizza(PizzaServiceImpl.entityToDTO(pizza));
		assertEquals(1,actual.size());
	}

	@Test
	public void updatePizzaNotPresent()
	{
		
		Pizza pizza = new Pizza("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		pizza.setPizzaId(1);
		Mockito.when(pizzaRepository.findById(2)).thenThrow(new PizzaIdNotFoundException("PizzaId not present in the database"));
		Exception exception = assertThrows(PizzaIdNotFoundException.class,()->pizzaService.findPizza(2));
		assertTrue(exception.getMessage().contains("PizzaId not present in the database"));
	}

	@Test
	public void savePizzaNull()
	{
		PizzaDTO pizza1 = null;
		List<PizzaDTO> actual = pizzaService.savePizza(pizza1);
		assertNull(actual);
	}

	@Test
	public void savePizzaPresent()
	{
		
		Pizza pizza = new Pizza("Veg", "Medium", "Veg Exotica", "Premium Veg Pizza", 500);
		pizza.setPizzaId(1);

		Mockito.when(pizzaRepository.findById(1)).thenReturn(Optional.of(pizza));
		Mockito.when(pizzaRepository.saveAndFlush(pizza)).thenReturn(pizza);

		Mockito.when(pizzaRepository.findAll()).thenReturn(Stream.of(pizza).collect(Collectors.toList()));
		List<PizzaDTO> actual = pizzaService.updatePizza(PizzaServiceImpl.entityToDTO(pizza));
		assertEquals(1,actual.size());
	}
	
}
