package com.drinksco.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

public record CustomerOrderRequest(
		@NotNull Long branchId,
		@NotBlank String customerName,
		String customerPhone,
		@PositiveOrZero BigDecimal amountPaid,
		@NotEmpty List<@Valid CustomerOrderItemRequest> items) {
}
