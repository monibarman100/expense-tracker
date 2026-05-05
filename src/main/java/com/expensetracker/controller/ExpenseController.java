package com.expensetracker.controller;

import java.util.List;
import java.util.Map;

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
	
	@GetMapping
	public List<Expense> getExpenses(){
		
		return expenseService.getUserExpenses();
		
	}
	
	@GetMapping("/filter")
	public Page<Expense> getExpenses(
			String category,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size,
			@RequestParam(defaultValue = "date") String sortBy,
			@RequestParam(defaultValue = "desc") String direction){
		
		return expenseService.getFilteredExpenses(category,page, size, sortBy, direction);
	}
	
	@PutMapping("/{id}")
	public String updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO request) {
		
		return expenseService.updateExpense(id, request);
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteExpense(@PathVariable Long id) {
		
		return expenseService.deleteMapping(id);
		
	}
	
	@GetMapping("/total")
	public Double getTotalExpense() {
		
		return expenseService.getTotalExpense();
	
	}
	
	@GetMapping("/report/category")
	public Map<String,Double> getExpenseByCategory(){
		
		return expenseService.getCategoryReport();
		
	}
	
	@GetMapping("/report/monthly")
	public Map<Integer,Double> getExpensesMonthly(){
		
		return expenseService.getMonthlyReport();
	}

}
