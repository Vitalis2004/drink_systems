package com.drinksco.model;

import java.math.BigDecimal;

public record CustomerReceiptItemResponse(
		Long drinkId,
		String name,
		Integer quantity,
		BigDecimal unitPrice,
		BigDecimal lineTotal) {
}
