package org.lessons.java.pojo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class OffertaSpeciale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@FutureOrPresent(message = "La data non può essere nel passato")
    private LocalDate dataInizio;
	@FutureOrPresent(message = "La data non può essere nel passato")
	private LocalDate dataFine;
	@NotBlank(message = "Il nome non può essere null")
	private String titolo;
	@Min(value = 0, message = "Il prezzo non può essere minore di 0")
	private int sconto;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pizza pizza;
	
	public OffertaSpeciale() {}
	public OffertaSpeciale(LocalDate dataInizio, LocalDate dataFine, String titolo, int sconto, Pizza pizza) {
		setDataInizio(dataInizio);
		setDataFine(dataFine);
		setTitolo(titolo);
		setSconto(sconto);
		setPizza(pizza);
	}
 	
	//get e set
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public int getSconto() {
		return sconto;
	}
	public void setSconto(int sconto) {
		this.sconto = sconto;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	public String getPrezzoScontato() {
		double prezzo_scontato =  getPizza().getPrezzo() - ((getPizza().getPrezzo() * getSconto()) / 100f);
		return String.format("%,.2f", prezzo_scontato) + "€";
	}
	
	@Override
	public String toString() {
			
		return "[" + getId() + "] " + getTitolo() + " - " + getSconto() + "% " + getDataInizio() + " " + getDataFine();
	}
}
