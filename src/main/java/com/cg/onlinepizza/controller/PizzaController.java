package com.cg.onlinepizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinepizza.model.Pizza;
import com.cg.onlinepizza.service.PizzaService;

@RestController
@RequestMapping("/pizza")
public class PizzaController {
	@Autowired
	private PizzaService pizzaService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/update")
	public ResponseEntity<List<Pizza>> updatePizza(
			@RequestBody Pizza pizza){
		List<Pizza> pizzas= pizzaService.updatePizza(pizza);
		if(pizzas.isEmpty())
		{
			return new ResponseEntity("Sorry! Pizza not available!", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Pizza>>(pizzas, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/insert")
	public ResponseEntity<List<Pizza>> insertOrder(
			@RequestBody Pizza pizza){
		List<Pizza> pizzas= pizzaService.savePizza(pizza);
		if(pizzas.isEmpty())
		{
			return new ResponseEntity("Sorry! Pizza could not be inserted!", 
					HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<Pizza>>(pizzas, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/delete/{pizzaId}")
	public ResponseEntity<List<Pizza>> deletePizza(
			@PathVariable("pizzaId")Integer pizzaId){
		List<Pizza> pizzas= pizzaService.deletePizza(pizzaId);
		if(pizzas.isEmpty() || pizzas==null) {
			return new ResponseEntity("Sorry! PizzaId not available!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Pizza>>(pizzas, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/find/{pizzaId}")
	public ResponseEntity<Pizza> findPizza(
			@PathVariable("pizzaId")Integer pizzaId){
		Pizza pizza= pizzaService.findPizza(pizzaId);
		if(pizza==null) {
			return new ResponseEntity("Sorry! Pizza not found!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/findAll")
	public ResponseEntity<List<Pizza>> getAllPizza(){
		List<Pizza> pizzas= pizzaService.getAllPizza();
		if(pizzas.isEmpty()) {
			return new ResponseEntity("Sorry! Pizzas not available!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Pizza>>(pizzas, HttpStatus.OK);
	}

}
