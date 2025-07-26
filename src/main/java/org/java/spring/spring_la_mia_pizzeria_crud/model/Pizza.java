package org.java.spring.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(min = 3, max = 55, message = "The name needs to be between 3 and 50 characters")
  @Column(nullable = false)
  @NotBlank(message = "Name is required")
  private String name;

  @Lob
  private String description;

  @NotNull(message = "Image is required")
  @Size(min = 3, max = 255, message = "The url needs to be between 3 and 255 characters")
  @Column(nullable = false)
  private String image_url;

  @NotNull(message = "Price is required")
  @Column(precision = 5, scale = 2)
  private BigDecimal price;

  @ManyToMany()
  @JoinTable(name = "ingredient_pizza", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
  private List<Ingredient> ingredients;

  @OneToMany(mappedBy = "pizza")
  private List<Discount> discounts;

  public List<Ingredient> getIngredients() {
    return this.ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public List<Discount> getDiscounts() {
    return this.discounts;
  }

  public void setDiscounts(List<Discount> discounts) {
    this.discounts = discounts;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImage_url() {
    return image_url;
  }

  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

}
