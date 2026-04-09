package com.drinksco.controller;

import com.drinksco.model.Branch;
import com.drinksco.service.ReportService;
import com.drinksco.service.StockService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final StockService stockService;
	private final ReportService reportService;

	@GetMapping("/admin")
	public String dashboard(Model model) {
		model.addAttribute("pageTitle", "Admin Dashboard");
		model.addAttribute("branches", stockService.getAllBranches());
		model.addAttribute("recentOrders", reportService.getRecentOrders());
		model.addAttribute("totalOrders", reportService.getTotalOrders());
		model.addAttribute("totalRevenue", reportService.getTotalRevenue());
		return "admin/dashboard";
	}

	@GetMapping("/admin/stock")
	public String stock(@RequestParam(required = false) Long branchId, Model model) {
		List<Branch> branches = stockService.getAllBranches();
		Long selectedBranchId = branchId;
		if (selectedBranchId == null && !branches.isEmpty()) {
			selectedBranchId = branches.get(0).getId();
		}

		model.addAttribute("pageTitle", "Stock Levels");
		model.addAttribute("branches", branches);
		model.addAttribute("selectedBranchId", selectedBranchId);
		model.addAttribute("stockLevels", selectedBranchId == null
				? List.of()
				: stockService.getStockForBranch(selectedBranchId));
		return "admin/stock";
	}
}
