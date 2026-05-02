package com.expensetracker.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.entity.Expense;
import com.expensetracker.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping
	public String addExpense(@Valid @RequestBody ExpenseDTO request) {
		
		return expenseService.addExpense(request);
		
	}
	
	/*@GetMapping
	public List<Expense> getExpenses(){
		
		return expenseService.getUserExpenses();
		
	}*/
	
	@GetMapping("/filter")
	public Page<Expense> getExpenses(
			String category,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size){
		
		return expenseService.getExpensesByCategory(category,page, size);
	}
	
	@PutMapping("/{id}")
	public String updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO request) {
		
		return expenseService.updateExpense(id, request);
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteExpense(@PathVariable Long id) {
		
		return expenseService.deleteMapping(id);
		
	}

}
