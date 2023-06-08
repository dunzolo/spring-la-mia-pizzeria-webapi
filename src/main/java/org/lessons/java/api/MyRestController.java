package org.lessons.java.api;

import java.util.List;
import java.util.Optional;

import org.lessons.java.api.dto.PizzaDto;
import org.lessons.java.pojo.Pizza;
import org.lessons.java.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class MyRestController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> sayHello() {
		
		return new ResponseEntity<>("Hello, World!", HttpStatus.OK);
	}
	
	@GetMapping("/pizze")
	public ResponseEntity<List<Pizza>> getPizzaList() {
		
		List<Pizza> pizza_list = pizzaService.findAllPizza();
		
		return new ResponseEntity<>(pizza_list, HttpStatus.OK);
	}
	
	@GetMapping("/pizza")
	public ResponseEntity<List<Pizza>> getPizzaByName(
			@RequestParam(required = false) String nome
		) {
		
		List<Pizza> pizza_list = pizzaService.findByNome(nome);
		
		return new ResponseEntity<>(pizza_list, HttpStatus.OK);
	}
	
	
	@GetMapping("/pizza/{id}")
	public ResponseEntity<PizzaDto> getSinglePizza(
			@PathVariable int id
		) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		
		//se inserisco un id di una pizza che non esiste
		if(optPizza.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
		Pizza pizza = optPizza.get();
		
		return new ResponseEntity<>(new PizzaDto(pizza), HttpStatus.OK);
	}
	
	@PostMapping("/pizza")
	public ResponseEntity<PizzaDto> storePizza(
			@RequestBody Pizza pizza) {
		
//		if (bindingResult.hasErrors()) {
//			
//			return new ResponseEntity<>(
//					new BookResponseDto(bindingResult), 
//					HttpStatus.BAD_REQUEST
//				);
//		}
		
		pizza = pizzaService.savePizza(pizza);
		
		return new ResponseEntity<>(
				new PizzaDto(pizza), 
				HttpStatus.OK);	
	}
	
	@PutMapping("/pizza")
	public ResponseEntity<PizzaDto> updatePizza(
			@RequestBody Pizza pizza
		) {
		
		pizza = pizzaService.savePizza(pizza);
		
		return new ResponseEntity<>(
				new PizzaDto(pizza), 
				HttpStatus.OK);	
	}
	
	@DeleteMapping("/pizza/{id}")
	public ResponseEntity<PizzaDto> deletePizza(
			@PathVariable int id
		) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		
		if (optPizza.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);	
		}
		
		Pizza pizza = optPizza.get();
		
		pizza.getIngredienti().clear();
		pizzaService.savePizza(pizza);
		pizzaService.deletePizza(pizza);
		
		return new ResponseEntity<>(
				new PizzaDto(pizza), 
				HttpStatus.OK);		
	}
}
