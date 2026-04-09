package com.drinksco.service;

import com.drinksco.model.Order;
import com.drinksco.model.StockLevel;
import com.drinksco.repository.OrderRepository;
import com.drinksco.repository.StockLevelRepository;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

	private final OrderRepository orderRepository;
	private final StockLevelRepository stockLevelRepository;

	public long getTotalOrders() {
		return orderRepository.count();
	}

	public BigDecimal getTotalRevenue() {
		return orderRepository.findAll().stream()
				.map(Order::getTotalAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public List<Order> getRecentOrders() {
		return orderRepository.findTop10ByOrderByOrderedAtDesc();
	}

	public Map<String, Long> getOrdersByBranch() {
		Map<String, Long> summary = new LinkedHashMap<>();
		for (Order order : orderRepository.findAll()) {
			String branchName = order.getBranch().getName();
			summary.put(branchName, summary.getOrDefault(branchName, 0L) + 1);
		}
		return summary;
	}

	public List<StockLevel> getStockSnapshot() {
		return stockLevelRepository.findAllByOrderByBranchNameAscDrinkNameAsc();
	}
}
