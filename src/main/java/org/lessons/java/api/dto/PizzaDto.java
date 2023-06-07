package org.lessons.java.api.dto;

import org.lessons.java.pojo.Pizza;
import org.springframework.validation.BindingResult;

public class PizzaDto {
	Pizza pizza;
	BindingResult bindingResult;
	
	public PizzaDto(Pizza pizza) {
		setPizza(pizza);
	}
	
	public PizzaDto(BindingResult bindingResult) {
		setBindingResult(bindingResult);
	}
	
	public PizzaDto(Pizza pizza, BindingResult bindingResult) {
		setPizza(pizza);
		setBindingResult(bindingResult);
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
	
}
