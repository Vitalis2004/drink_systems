package com.drinksco.model;

import java.math.BigDecimal;

public record CustomerMenuDrinkResponse(
		Long id,
		String name,
		String category,
		BigDecimal price,
		Integer availableQuantity) {
}
