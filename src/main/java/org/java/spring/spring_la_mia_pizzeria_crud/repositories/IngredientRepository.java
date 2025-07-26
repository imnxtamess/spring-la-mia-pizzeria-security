package org.java.spring.spring_la_mia_pizzeria_crud.repositories;

import org.java.spring.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
