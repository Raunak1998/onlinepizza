package com.cg.onlinepizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinepizza.dto.PizzaDTO;
import com.cg.onlinepizza.service.PizzaService;

@CrossOrigin
@RestController
@RequestMapping("/pizza")
public class PizzaController {
	@Autowired
	private PizzaService pizzaService;
	
	@PutMapping("/update")
	public ResponseEntity<List<PizzaDTO>> updatePizza(
			@RequestBody PizzaDTO pizza){
		List<PizzaDTO> pizzas= pizzaService.updatePizza(pizza);
		return new ResponseEntity<List<PizzaDTO>>(pizzas, HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<List<PizzaDTO>> insertPizza(
			@RequestBody PizzaDTO pizza){
		List<PizzaDTO> pizzas= pizzaService.savePizza(pizza);
		return new ResponseEntity<List<PizzaDTO>>(pizzas, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{pizzaId}")
	public ResponseEntity<List<PizzaDTO>> deletePizza(
			@PathVariable("pizzaId")Integer pizzaId){
		List<PizzaDTO> pizzas= pizzaService.deletePizza(pizzaId);
		return new ResponseEntity<List<PizzaDTO>>(pizzas, HttpStatus.OK);
	}
	
	@GetMapping("/find/{pizzaId}")
	public ResponseEntity<PizzaDTO> findPizza(
			@PathVariable("pizzaId")Integer pizzaId){
		PizzaDTO pizza= pizzaService.findPizza(pizzaId);
		return new ResponseEntity<PizzaDTO>(pizza, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/findAll")
	public ResponseEntity<List<PizzaDTO>> getAllPizza(){
		List<PizzaDTO> pizzas= pizzaService.getAllPizza();
		if(pizzas.isEmpty()) {
			return new ResponseEntity("Sorry! Pizzas not available!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<PizzaDTO>>(pizzas, HttpStatus.OK);
	}

}
