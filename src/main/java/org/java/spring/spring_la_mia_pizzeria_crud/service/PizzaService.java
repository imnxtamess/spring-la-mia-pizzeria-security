package org.java.spring.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.java.spring.spring_la_mia_pizzeria_crud.model.Discount;
import org.java.spring.spring_la_mia_pizzeria_crud.model.Pizza;
import org.java.spring.spring_la_mia_pizzeria_crud.repositories.DiscountRepository;
import org.java.spring.spring_la_mia_pizzeria_crud.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

  @Autowired
  private PizzaRepository pizzaRepository;

  @Autowired
  private DiscountRepository discountRepository;

  public List<Pizza> findAll() {
    return pizzaRepository.findAll();
  }

  public Pizza getById(Integer id) {

    return pizzaRepository.findById(id).get();
  }

  public Optional<Pizza> findById(Integer id) {

    return pizzaRepository.findById(id);
  }

  public List<Pizza> findByName(String name) {

    return pizzaRepository.findByNameContainingIgnoreCase(name);

  }

  public Pizza create(Pizza pizza) {

    return pizzaRepository.save(pizza);
  }

  public Pizza update(Pizza pizza) {

    return pizzaRepository.save(pizza);
  }

  public void deleteById(Integer id) {

    Pizza pizza = getById(id);

    for (Discount discountToDelete : pizza.getDiscounts()) {
      discountRepository.delete(discountToDelete);
    }

    pizzaRepository.delete(pizza);
  }
}
