package com.drinksco.model;

public record CustomerBranchResponse(
		Long id,
		String name,
		String displayName,
		String label,
		String location,
		boolean headquarters) {
}
