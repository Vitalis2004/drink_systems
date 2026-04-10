package com.drinksco.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartViewItem {

	private Long drinkId;

	private String drinkName;

	private String category;

	private BigDecimal unitPrice;

	private Integer quantity;

	private BigDecimal lineTotal;
}
