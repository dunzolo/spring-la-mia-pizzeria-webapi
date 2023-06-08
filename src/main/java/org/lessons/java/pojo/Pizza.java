package org.lessons.java.pojo;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class,
	    property = "id"
	)
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Il nome non può essere null")
	private String nome;
	@NotBlank(message = "La descrizione non può essere null")
	private String descrizione;
	@NotBlank(message = "URL foto non può essere null")
	private String foto;
	@Min(value = 0, message = "Il prezzo non può essere minore di 0")
	private int prezzo;
	
	@OneToMany(mappedBy = "pizza", cascade = CascadeType.REMOVE)
	private List<OffertaSpeciale> offerta;
	
	@ManyToMany
	private List<Ingrediente> ingredienti;
	
	public Pizza() {}
	public Pizza(String nome, String descrizione, String foto, int prezzo, Ingrediente...ingredienti ) {
		setNome(nome);
		setDescrizione(descrizione);
		setFoto(foto);
		setPrezzo(prezzo);
		setIngredientis(ingredienti);
	}
	
	//get e set
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
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	public String getFormatPrezzo() {
		return prezzo + "€";
	}
	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	public List<OffertaSpeciale> getOfferta() {
		return offerta;
	}
	public void setOfferta(List<OffertaSpeciale> offerta) {
		this.offerta = offerta;
	}
	public void setIngredientis(Ingrediente[] ingredienti) {
		
		setIngredienti(Arrays.asList(ingredienti));
	}
	public void addIngrediente(Ingrediente ingrediente) {
		
		getIngredienti().add(ingrediente);
	}
	public void removeIngrediente(Ingrediente ingrediente) {
		
		getIngredienti().remove(ingrediente);
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getNome() 
				+ "\nDescrizione: " + getDescrizione()
				+ "\nPrezzo: " + getPrezzo();
	}
}