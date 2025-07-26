package org.java.spring.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.java.spring.spring_la_mia_pizzeria_crud.model.Discount;
import org.java.spring.spring_la_mia_pizzeria_crud.model.Pizza;
import org.java.spring.spring_la_mia_pizzeria_crud.repositories.DiscountRepository;
import org.java.spring.spring_la_mia_pizzeria_crud.repositories.IngredientRepository;
import org.java.spring.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

  @Autowired
  private PizzaService pizzaService;

  @Autowired
  private DiscountRepository discountRepository;

  @Autowired
  private IngredientRepository ingredientRepository;

  @GetMapping
  public String index(Model model) {

    List<Pizza> pizzas = pizzaService.findAll(); // & SELECT * FROM `pizzas` ==> list of objects with type pizza

    model.addAttribute("pizzas", pizzas);

    return "pizzas/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {

    Pizza pizza = pizzaService.getById(id);

    model.addAttribute("pizza", pizza);
    model.addAttribute("ingredients", ingredientRepository.findAll());

    return "pizzas/show";
  }

  @GetMapping("/search")
  public String searchByTitle(@RequestParam String name, Model model) {

    List<Pizza> pizzas = pizzaService.findByName(name);

    model.addAttribute("pizzas", pizzas);
    model.addAttribute("name", name);

    return "pizzas/index";
  }

  @GetMapping("/create")
  public String create(Model model) {

    model.addAttribute("pizza", new Pizza());
    model.addAttribute("ingredients", ingredientRepository.findAll());

    return "pizzas/create";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

    if (bindingResult.hasFieldErrors("price")) {
      bindingResult.rejectValue("price", "typeMismatch", "Price must be a valid number (e.g. 9.99 )");
    }

    if (bindingResult.hasErrors()) {
      model.addAttribute("ingredients", ingredientRepository.findAll());
      return "pizzas/create";
    }

    pizzaService.create(formPizza);

    return "redirect:/pizzas";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("ingredients", ingredientRepository.findAll());
    model.addAttribute("pizza", pizzaService.getById(id));

    return "pizzas/edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("ingredients", ingredientRepository.findAll());
      return "pizzas/edit";
    }

    pizzaService.update(formPizza);

    return "redirect:/pizzas";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable("id") Integer id) {

    Pizza pizza = pizzaService.getById(id);

    for (Discount discountToDelete : pizza.getDiscounts()) {
      discountRepository.delete(discountToDelete);
    }

    pizzaService.deleteById(id);

    return "redirect:/pizzas";
  }

  @GetMapping("/{id}/discount")
  public String discount(@PathVariable Integer id, Model model) {

    Discount discount = new Discount();
    discount.setPizza(pizzaService.getById(id));

    model.addAttribute("discount", discount);

    return "discounts/create-or-edit";
  }

}