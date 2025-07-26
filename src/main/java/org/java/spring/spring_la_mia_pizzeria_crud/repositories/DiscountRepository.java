package org.java.spring.spring_la_mia_pizzeria_crud.repositories;

import org.java.spring.spring_la_mia_pizzeria_crud.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {

}
