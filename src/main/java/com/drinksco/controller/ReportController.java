package com.drinksco.controller;

import com.drinksco.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ReportController {

	private final ReportService reportService;

	@GetMapping("/admin/reports")
	public String reports(Model model) {
		model.addAttribute("pageTitle", "Reports");
		model.addAttribute("totalOrders", reportService.getTotalOrders());
		model.addAttribute("totalRevenue", reportService.getTotalRevenue());
		model.addAttribute("ordersByBranch", reportService.getOrdersByBranch());
		model.addAttribute("stockSnapshot", reportService.getStockSnapshot());
		return "admin/reports";
	}
}
