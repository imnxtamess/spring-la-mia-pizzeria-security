package org.java.spring.spring_la_mia_pizzeria_crud.repositories;

import java.util.List;

import org.java.spring.spring_la_mia_pizzeria_crud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

  public List<Pizza> findByNameContainingIgnoreCase(String name);

}
