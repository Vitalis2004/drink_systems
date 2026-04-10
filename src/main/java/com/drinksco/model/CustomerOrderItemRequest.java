package com.drinksco.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CustomerOrderItemRequest(
		@NotNull Long drinkId,
		@NotNull @Min(0) Integer quantity) {
}
