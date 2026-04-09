package com.drinksco.config;

import com.drinksco.model.Branch;
import com.drinksco.model.Drink;
import com.drinksco.repository.BranchRepository;
import com.drinksco.repository.DrinkRepository;
import com.drinksco.service.StockService;
import java.math.BigDecimal;
import java.util.List;
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
			if (branchRepository.count() > 0 || drinkRepository.count() > 0) {
				return;
			}

			Branch cbd = branchRepository.save(Branch.builder().name("CBD Branch").location("City Center").build());
			Branch westlands = branchRepository.save(Branch.builder().name("Westlands Branch").location("Westlands").build());
			Branch kilimani = branchRepository.save(Branch.builder().name("Kilimani Branch").location("Kilimani").build());

			List<Drink> drinks = drinkRepository.saveAll(List.of(
					Drink.builder().name("Cola").category("Soda").price(new BigDecimal("2.50")).build(),
					Drink.builder().name("Orange Juice").category("Juice").price(new BigDecimal("3.20")).build(),
					Drink.builder().name("Mineral Water").category("Water").price(new BigDecimal("1.20")).build(),
					Drink.builder().name("Iced Tea").category("Tea").price(new BigDecimal("2.80")).build()));

			for (Drink drink : drinks) {
				stockService.seedStock(cbd, drink, 45);
				stockService.seedStock(westlands, drink, 35);
				stockService.seedStock(kilimani, drink, 25);
			}
		};
	}
}
