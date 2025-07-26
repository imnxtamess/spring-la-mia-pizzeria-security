package org.java.spring.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "discounts")
public class Discount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "A discount cannot exist without specifying a name")
  @Size(min = 3, message = "A discount name should be at least 3 chars long")
  private String discountName;

  @NotBlank(message = "A start date must be set")
  private LocalDate startDate;

  @NotBlank(message = "An end date must be set")
  private LocalDate endDate;

  // > Pizza da cui dipendo
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "pizza_id", nullable = false)
  private Pizza pizza;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDiscountName() {
    return this.discountName;
  }

  public void setDiscountName(String discountName) {
    this.discountName = discountName;
  }

  public LocalDate getStartDate() {
    return this.startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return this.endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Pizza getPizza() {
    return this.pizza;
  }

  public void setPizza(Pizza pizza) {
    this.pizza = pizza;
  }

}
