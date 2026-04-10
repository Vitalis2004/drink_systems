package com.drinksco.repository;

import com.drinksco.model.Drink;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {

	Optional<Drink> findByNameIgnoreCase(String name);
}
