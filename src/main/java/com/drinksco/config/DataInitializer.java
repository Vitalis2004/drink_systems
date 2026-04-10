package com.drinksco.config;

import com.drinksco.model.Branch;
import com.drinksco.model.Drink;
import com.drinksco.repository.BranchRepository;
import com.drinksco.repository.DrinkRepository;
import com.drinksco.service.StockService;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

	private final BranchRepository branchRepository;
	private final DrinkRepository drinkRepository;
	private final StockService stockService;

	@Bean
	CommandLineRunner loadSampleData() {
		return args -> {
			Map<Branch, Integer> branches = new LinkedHashMap<>();
			branches.put(ensureBranch("Headquarters", "Main Office"), 60);
			branches.put(ensureBranch("Mombasa", "Mombasa"), 45);
			branches.put(ensureBranch("Nakuru", "Nakuru"), 35);
			branches.put(ensureBranch("Kisumu", "Kisumu"), 25);

			List<Drink> drinks = List.of(
					ensureDrink("Cola", "Soda", "2.50"),
					ensureDrink("Orange Juice", "Juice", "3.20"),
					ensureDrink("Mineral Water", "Water", "1.20"),
					ensureDrink("Iced Tea", "Tea", "2.80"));

			for (Map.Entry<Branch, Integer> branchEntry : branches.entrySet()) {
				for (Drink drink : drinks) {
					stockService.seedStock(branchEntry.getKey(), drink, branchEntry.getValue());
				}
			}
		};
	}

	private Branch ensureBranch(String name, String location) {
		return branchRepository.findByNameIgnoreCase(name)
				.orElseGet(() -> branchRepository.save(Branch.builder()
						.name(name)
						.location(location)
						.build()));
	}

	private Drink ensureDrink(String name, String category, String price) {
		return drinkRepository.findByNameIgnoreCase(name)
				.orElseGet(() -> drinkRepository.save(Drink.builder()
						.name(name)
						.category(category)
						.price(new BigDecimal(price))
						.build()));
	}
}
