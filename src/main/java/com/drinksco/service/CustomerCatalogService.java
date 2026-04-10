package com.drinksco.service;

import com.drinksco.model.Branch;
import com.drinksco.model.StockLevel;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CustomerCatalogService {

	private static final List<BranchSeed> CUSTOMER_BRANCHES = List.of(
			new BranchSeed("Headquarters", "Nairobi CBD", 60),
			new BranchSeed("Mombasa", "Mombasa Island", 45),
			new BranchSeed("Nakuru", "Nakuru Town", 35),
			new BranchSeed("Kisumu", "Kisumu City", 25));

	private static final List<DrinkSeed> CUSTOMER_DRINKS = List.of(
			new DrinkSeed("Coca Cola", "Soda", new BigDecimal("50")),
			new DrinkSeed("Sprite", "Soda", new BigDecimal("50")),
			new DrinkSeed("Fanta", "Soda", new BigDecimal("65")),
			new DrinkSeed("Minute Maid", "Juice", new BigDecimal("80")));

	private final Map<String, Integer> branchOrder = indexByName(CUSTOMER_BRANCHES.stream()
			.map(BranchSeed::name)
			.toList());

	private final Map<String, Integer> drinkOrder = indexByName(CUSTOMER_DRINKS.stream()
			.map(DrinkSeed::name)
			.toList());

	public List<BranchSeed> getSeedBranches() {
		return CUSTOMER_BRANCHES;
	}

	public List<DrinkSeed> getSeedDrinks() {
		return CUSTOMER_DRINKS;
	}

	public List<Branch> getCustomerBranches(List<Branch> branches) {
		List<Branch> customerBranches = branches.stream()
				.filter(branch -> isCustomerBranch(branch.getName()))
				.sorted(Comparator.comparingInt(branch -> branchOrderIndex(branch.getName())))
				.toList();
		return customerBranches.isEmpty() ? branches : customerBranches;
	}

	public Branch findCustomerBranch(List<Branch> branches, Long branchId) {
		return getCustomerBranches(branches).stream()
				.filter(branch -> Objects.equals(branch.getId(), branchId))
				.findFirst()
				.orElse(null);
	}

	public List<StockLevel> getCustomerMenuStock(List<StockLevel> stockLevels) {
		return stockLevels.stream()
				.filter(stock -> stock.getDrink() != null && isCustomerDrink(stock.getDrink().getName()))
				.sorted(Comparator.comparingInt(stock -> drinkOrderIndex(stock.getDrink().getName())))
				.toList();
	}

	public boolean isCustomerBranch(String branchName) {
		return branchOrder.containsKey(normalizeKey(branchName));
	}

	public boolean isCustomerDrink(String drinkName) {
		return drinkOrder.containsKey(normalizeKey(drinkName));
	}

	public String getBranchDisplayName(Branch branch) {
		if (branch == null) {
			return "";
		}
		if ("Headquarters".equalsIgnoreCase(branch.getName())) {
			return "Nairobi HQ";
		}
		return branch.getName();
	}

	public String getBranchLabel(Branch branch) {
		if (branch == null) {
			return "";
		}
		if ("Headquarters".equalsIgnoreCase(branch.getName())) {
			return "NAIROBI (HQ)";
		}
		return branch.getName().toUpperCase(Locale.ROOT);
	}

	public String getBranchLocation(Branch branch) {
		return branch == null ? "" : branch.getLocation();
	}

	public boolean isHeadquarters(Branch branch) {
		return branch != null && "Headquarters".equalsIgnoreCase(branch.getName());
	}

	private Map<String, Integer> indexByName(List<String> names) {
		return names.stream()
				.map(this::normalizeKey)
				.collect(Collectors.toMap(Function.identity(), key -> names.indexOf(findOriginalName(names, key))));
	}

	private String findOriginalName(List<String> names, String normalizedKey) {
		return names.stream()
				.filter(name -> normalizeKey(name).equals(normalizedKey))
				.findFirst()
				.orElseThrow();
	}

	private int branchOrderIndex(String branchName) {
		return branchOrder.getOrDefault(normalizeKey(branchName), Integer.MAX_VALUE);
	}

	private int drinkOrderIndex(String drinkName) {
		return drinkOrder.getOrDefault(normalizeKey(drinkName), Integer.MAX_VALUE);
	}

	private String normalizeKey(String value) {
		return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
	}

	public record BranchSeed(String name, String location, int stockQuantity) {
	}

	public record DrinkSeed(String name, String category, BigDecimal price) {
	}
}
