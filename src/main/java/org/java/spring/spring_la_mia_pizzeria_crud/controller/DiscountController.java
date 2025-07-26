package org.java.spring.spring_la_mia_pizzeria_crud.controller;

import org.java.spring.spring_la_mia_pizzeria_crud.model.Discount;
import org.java.spring.spring_la_mia_pizzeria_crud.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/discounts")
public class DiscountController {

  @Autowired
  private DiscountRepository repository;

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("discount") Discount formDiscount, BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      return "discounts/create-or-edit";
    }

    repository.save(formDiscount);

    return "redirect:/pizzas/" + formDiscount.getPizza().getId();
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    model.addAttribute("discount", repository.findById(id).get());
    model.addAttribute("edit", true);

    return "discounts/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("discount") Discount formDiscount, BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      return "discounts/create-or-edit";
    }

    repository.save(formDiscount);

    return "redirect:/pizzas/" + formDiscount.getPizza().getId();
  }

}
