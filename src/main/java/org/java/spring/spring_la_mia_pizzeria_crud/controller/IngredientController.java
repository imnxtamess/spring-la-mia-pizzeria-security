package org.java.spring.spring_la_mia_pizzeria_crud.controller;

import org.java.spring.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.java.spring.spring_la_mia_pizzeria_crud.model.Pizza;
import org.java.spring.spring_la_mia_pizzeria_crud.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

  @Autowired
  private IngredientRepository ingredientRepository;

  @GetMapping
  public String index(Model model) {

    model.addAttribute("ingredients", ingredientRepository.findAll());

    return "ingredients/index";
  }

  @GetMapping("/create")
  public String create(Model model) {

    model.addAttribute("ingredient", new Ingredient());

    return "ingredients/create-or-edit";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      return "ingredients/create-or-edit";
    }

    ingredientRepository.save(ingredient);

    return "redirect:/ingredients";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    model.addAttribute("ingredient", ingredientRepository.findById(id).get());

    return "ingredients/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      return "ingredients/create-or-edit";
    }

    ingredientRepository.save(ingredient);

    return "redirect:/ingredients";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {

    Ingredient ingredientToDelete = ingredientRepository.findById(id).get();

    for (Pizza linkedpizza : ingredientToDelete.getPizzas()) {
      linkedpizza.getIngredients().remove(ingredientToDelete);
    }

    ingredientRepository.delete(ingredientToDelete);

    return "redirect:/ingredients";
  }

}
