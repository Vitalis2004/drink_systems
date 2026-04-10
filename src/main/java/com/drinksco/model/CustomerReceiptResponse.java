package com.drinksco.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CustomerReceiptResponse(
		String referenceNumber,
		String status,
		String customerName,
		String customerPhone,
		CustomerBranchResponse branch,
		LocalDateTime orderedAt,
		BigDecimal totalAmount,
		BigDecimal amountPaid,
		BigDecimal balance,
		List<CustomerReceiptItemResponse> items) {
}
