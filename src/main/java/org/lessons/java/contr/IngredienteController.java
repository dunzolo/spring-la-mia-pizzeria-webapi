package org.lessons.java.contr;

import java.util.List;
import java.util.Optional;

import org.lessons.java.pojo.Ingrediente;
import org.lessons.java.serv.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping("/ingredienti")
	public String getIngredienti(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteService.findAllIngredienti();
		
		model.addAttribute("ingredienti", ingredienti);
		
		return "index-ingredienti";
	}
	
	@GetMapping("/ingredienti/create")
	public String getSingleIngrediente(Model model) {
		
		model.addAttribute("ingrediente", new Ingrediente());
		
		return "create-ingrediente";
	}
	
	@PostMapping("/ingredienti/create")
	public String getSingleIngrediente(
			@ModelAttribute Ingrediente ingrediente
		) {
		
		ingredienteService.saveIngrediente(ingrediente);
		
		return "redirect:/ingredienti";
	}
	
	@GetMapping("/ingredienti/edit/{id}")
	public String editOfferta(
			Model model,
			@PathVariable int id
		) {
		
		Optional<Ingrediente> ingredienteOpt = ingredienteService.findIngredienteById(id);
		Ingrediente ingrediente = ingredienteOpt.get();
		
		model.addAttribute("ingrediente", ingrediente);
		
		return "edit-ingrediente";
	}
	
	@PostMapping("/ingredienti/edit/{id}")
	public String editPizza(
			Model model,
			@PathVariable int id,
			@ModelAttribute Ingrediente ingrediente
		) {
		
		ingredienteService.saveIngrediente(ingrediente);
		
		return "redirect:/ingredienti";
	}
	
	@GetMapping("/ingredienti/delete/{id}")
	public String deleteOfferta(
			@PathVariable int id
		) {
		
		Optional<Ingrediente> ingredienteOpt = ingredienteService.findIngredienteById(id);
		Ingrediente ingrediente = ingredienteOpt.get();
		ingredienteService.delete(ingrediente);
		
		return "redirect:/ingredienti";
	}
}
