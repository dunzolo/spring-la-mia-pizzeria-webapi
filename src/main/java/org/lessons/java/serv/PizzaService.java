package org.lessons.java.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.lessons.java.pojo.Pizza;
import org.lessons.java.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepo pizzaRepo;
	
	public List<Pizza> findAllPizza() {
		
		return pizzaRepo.findAll();
	}
	public Pizza savePizza(Pizza pizza) {
		
		return pizzaRepo.save(pizza);
	}
	public Optional<Pizza> findPizzaById(int id) {
		
		return pizzaRepo.findById(id);
	}
	public List<Pizza> findByNome(String nome) {
		
		return pizzaRepo.findByNomeContaining(nome);
	}
	public void deletePizza(Pizza pizza) {
		
		pizzaRepo.delete(pizza);
	}
	
	@Transactional
	public Optional<Pizza> findByIdWithSpecialOffer(int id) {
		
		Optional<Pizza> pizzaOpt = pizzaRepo.findById(id);
		Hibernate.initialize(pizzaOpt.get().getOffertaSpeciale());
		
		return pizzaOpt;
	}
}
