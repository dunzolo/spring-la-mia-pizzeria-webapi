package org.lessons.java.contr;

import java.util.List;
import java.util.Optional;

import org.lessons.java.pojo.OffertaSpeciale;
import org.lessons.java.pojo.Pizza;
import org.lessons.java.serv.OffertaSpecialeService;
import org.lessons.java.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class OffertaController {
	
	@Autowired
	private OffertaSpecialeService offertaSpecialeService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/offerte")
	public String getOfferte(Model model) {
		
		List<OffertaSpeciale> offerte_list = offertaSpecialeService.findAll();
		
		model.addAttribute("offerte_list", offerte_list);
		
		return "index-offerte";
	}
	
	@GetMapping("/offerta/{id}")
	public String getSingleOfferta(Model model,
			@PathVariable("id") int id) {
		
		Optional<OffertaSpeciale> optOfferta = offertaSpecialeService.findById(id);
		OffertaSpeciale offerta = optOfferta.get();
		
		model.addAttribute("offerta", offerta);
		
		return "single-offerta";
	}
	
	@GetMapping("/offerta/create")
	public String createOfferta(Model model) {
		
		List<Pizza> pizza_list = pizzaService.findAllPizza();
		
		model.addAttribute("pizza_list", pizza_list);
		model.addAttribute("offerta", new OffertaSpeciale());
		
		return "create-offerta";
	}
	
	@PostMapping("/offerta/create")
	public String storeOffertaSpeciale(
			Model model,
			@Valid @ModelAttribute OffertaSpeciale offertaSpeciale,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			List<Pizza> pizza_list = pizzaService.findAllPizza();
			
			model.addAttribute("pizza_list", pizza_list);
			model.addAttribute("offerta", offertaSpeciale);
			model.addAttribute("errors", bindingResult);
			
			return "create-offerta";
		}
		
		offertaSpecialeService.save(offertaSpeciale);
		
		return "redirect:/offerte";
	}
	
	@GetMapping("/offerta/edit/{id}")
	public String editOfferta(
			Model model,
			@PathVariable int id
		) {
		
		List<Pizza> pizza_list = pizzaService.findAllPizza();
		model.addAttribute("pizza_list", pizza_list);
		
		Optional<OffertaSpeciale> offertaOpt = offertaSpecialeService.findById(id);
		OffertaSpeciale offerta = offertaOpt.get();
		
		model.addAttribute("offerta", offerta);
		
		return "edit-offerta";
	}
	
	@PostMapping("/offerta/edit/{id}")
	public String editPizza(
			Model model,
			@PathVariable int id,
			@Valid @ModelAttribute OffertaSpeciale offerta,
			BindingResult bindingResult
		) {
		
		if(bindingResult.hasErrors()) {
			//non è necessario
			//for(ObjectError err : bindingResult.getAllErrors()) {
			//	System.err.println("Error: " + err.getDefaultMessage());
			//}
			
			model.addAttribute("offerta", offerta);
			model.addAttribute("errors", bindingResult);
			
			//queste righe le riporto nel file HTML
			//if(bindingResult.hasFieldErrors("nome"))
			//bindingResult.getFieldError("nome").getDefaultMessage();
			
			return "edit-offerta";
		}
		
		offertaSpecialeService.save(offerta);
		
		return "redirect:/offerte";
	}
	
	@GetMapping("/offerta/delete/{id}")
	public String deleteOfferta(
			@PathVariable int id
		) {
		
		Optional<OffertaSpeciale> offertaOpt = offertaSpecialeService.findById(id);
		OffertaSpeciale offerta = offertaOpt.get();
		offertaSpecialeService.delete(offerta);
		
		return "redirect:/offerte";
	}
}
