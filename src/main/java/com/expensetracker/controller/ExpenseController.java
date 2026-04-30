package com.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.entity.Expense;
import com.expensetracker.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping
	public String addExpense(@RequestBody ExpenseDTO request) {
		
		return expenseService.addExpense(request);
		
	}
	
	@GetMapping
	public List<Expense> getExpenses(){
		
		return expenseService.getUserExpenses();
		
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
