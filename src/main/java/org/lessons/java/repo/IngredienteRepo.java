package org.lessons.java.repo;

import org.lessons.java.pojo.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepo extends JpaRepository<Ingrediente, Integer> {

}
