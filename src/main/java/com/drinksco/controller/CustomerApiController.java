package com.drinksco.controller;

import com.drinksco.model.ApiErrorResponse;
import com.drinksco.model.CustomerMenuResponse;
import com.drinksco.model.CustomerOrderRequest;
import com.drinksco.model.CustomerReceiptResponse;
import com.drinksco.service.CustomerOrderService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerApiController {

	private final CustomerOrderService customerOrderService;

	@GetMapping("/branches")
	public List<?> branches() {
		return customerOrderService.getBranches();
	}

	@GetMapping("/branches/{branchId}/menu")
	public CustomerMenuResponse menu(@PathVariable Long branchId) {
		return customerOrderService.getMenu(branchId);
	}

	@PostMapping("/orders")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerReceiptResponse placeOrder(@Valid @RequestBody CustomerOrderRequest request) {
		return customerOrderService.placeOrder(request);
	}

	@GetMapping("/orders/{referenceNumber}")
	public CustomerReceiptResponse receipt(@PathVariable String referenceNumber) {
		return customerOrderService.getReceipt(referenceNumber);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handleBadRequest(IllegalArgumentException ex) {
		return new ApiErrorResponse(ex.getMessage());
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse handleNotFound(NoSuchElementException ex) {
		return new ApiErrorResponse(ex.getMessage());
	}
}
