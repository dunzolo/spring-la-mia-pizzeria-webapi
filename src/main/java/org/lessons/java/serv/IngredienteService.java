package org.lessons.java.serv;

import java.util.List;
import java.util.Optional;

import org.lessons.java.pojo.Ingrediente;
import org.lessons.java.pojo.OffertaSpeciale;
import org.lessons.java.repo.IngredienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {
	@Autowired
	private IngredienteRepo ingredienteRepo;
	
	public List<Ingrediente> findAllIngredienti() {
		
		return ingredienteRepo.findAll();
	}
	public Optional<Ingrediente> findIngredienteById(int id) {
		
		return ingredienteRepo.findById(id);
	}
	public Ingrediente saveIngrediente(Ingrediente ingrediente) {
		
		return ingredienteRepo.save(ingrediente);
	}
	public void delete(Ingrediente ingrediente) {
		
		ingredienteRepo.delete(ingrediente);
	}
}
