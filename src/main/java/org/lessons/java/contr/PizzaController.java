package org.lessons.java.contr;

import java.util.List;
import java.util.Optional;

import org.lessons.java.pojo.Ingrediente;
import org.lessons.java.pojo.Pizza;
import org.lessons.java.serv.IngredienteService;
import org.lessons.java.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PizzaController {
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping("/")
	public String getPizza(Model model) {
		
		List<Pizza> pizza_list = pizzaService.findAllPizza();
		
		model.addAttribute("pizzaList", pizza_list);
		
		return "index";
	}
	
	@PostMapping("/pizza/nome")
	public String getBookByTitle(Model model, @RequestParam(required = false) String nome) {
		
		List<Pizza> pizza_list = pizzaService.findByNome(nome);
		model.addAttribute("pizzaList", pizza_list);
		model.addAttribute("nome", nome);
		
		return "index";
	}
	
	@GetMapping("/pizza/{id}")
	public String getPizza(Model model,
			@PathVariable("id") int id) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		Pizza pizza = optPizza.get();
		
		Optional<Pizza> optPizzaId = pizzaService.findByIdWithSpecialOffer(id);
		Pizza special_offer_pizza = optPizzaId.get();
		
		List<Ingrediente> ingredienti = ingredienteService.findAllIngredienti();
		model.addAttribute("ingredienti", ingredienti);
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("offerte_list", special_offer_pizza.getOfferta());
		
		return "single-pizza";
	}
	
	@GetMapping("/pizza/create")
	public String createPizza(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteService.findAllIngredienti();
		model.addAttribute("ingredienti", ingredienti);
		
		model.addAttribute("pizza", new Pizza());
		
		return "create-pizza";
	}
	
	@PostMapping("/pizza/create")
	public String storePizza(
			Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			//non è necessario
			//for(ObjectError err : bindingResult.getAllErrors()) {
			//	System.err.println("Error: " + err.getDefaultMessage());
			//}
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			//queste righe le riporto nel file HTML
			//if(bindingResult.hasFieldErrors("nome"))
			//bindingResult.getFieldError("nome").getDefaultMessage();
			
			return "create-pizza";
		}
		
		pizzaService.savePizza(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(
			@PathVariable int id
		) {
		
		Optional<Pizza> pizzaOpt = pizzaService.findPizzaById(id);
		Pizza pizza = pizzaOpt.get();
		pizzaService.deletePizza(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/edit/{id}")
	public String editPizza(
			Model model,
			@PathVariable int id
		) {
		
		Optional<Pizza> pizzaOpt = pizzaService.findPizzaById(id);
		Pizza pizza = pizzaOpt.get();
		
		List<Ingrediente> ingredienti = ingredienteService.findAllIngredienti();
		model.addAttribute("ingredienti", ingredienti);
		
		model.addAttribute("pizza", pizza);
		
		return "edit-pizza";
	}
	
	@PostMapping("/pizza/edit/{id}")
	public String editPizza(
			Model model,
			@PathVariable int id,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult
		) {
		
		if(bindingResult.hasErrors()) {
			//non è necessario
			//for(ObjectError err : bindingResult.getAllErrors()) {
			//	System.err.println("Error: " + err.getDefaultMessage());
			//}
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			//queste righe le riporto nel file HTML
			//if(bindingResult.hasFieldErrors("nome"))
			//bindingResult.getFieldError("nome").getDefaultMessage();
			
			return "edit-pizza";
		}
		
		pizzaService.savePizza(pizza);
		
		return "redirect:/";
	}
}
