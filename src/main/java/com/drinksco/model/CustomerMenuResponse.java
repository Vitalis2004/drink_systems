package com.drinksco.model;

import java.util.List;

public record CustomerMenuResponse(
		CustomerBranchResponse branch,
		List<CustomerMenuDrinkResponse> drinks) {
}
