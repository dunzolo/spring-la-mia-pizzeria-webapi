package org.lessons.java.pojo;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Ingrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Size(min = 3, max = 255)
	private String nome;
	
	@Column(columnDefinition = "TEXT")
	@Size(min = 0, max = 65536)
	private String descrizione;
	
	@ManyToMany(mappedBy = "ingredienti")
	private List<Pizza> pizza;
	
	public Ingrediente() {}
	
	public Ingrediente(String nome, String descrizione) {
		setNome(nome);
		setDescrizione(descrizione);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Pizza> getListaPizze() {
		return pizza;
	}

	public void setListaPizze(List<Pizza> lista_pizze) {
		this.pizza = lista_pizze;
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "] " + getNome() + 
				"\n" + getDescrizione();
	}
	
}
