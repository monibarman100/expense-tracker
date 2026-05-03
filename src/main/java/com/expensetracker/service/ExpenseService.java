package com.expensetracker.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.entity.Expense;

public interface ExpenseService {

	String addExpense(ExpenseDTO request);

	//List<Expense> getUserExpenses();
	
	String updateExpense(Long id, ExpenseDTO request);

	String deleteMapping(Long id);

	//Page<Expense> getUserExpenses(int page, int size);

	//Page<Expense> getExpensesByCategory(String category, int page, int size);

	Page<Expense> getFilteredExpenses(String category, int page, int size, String sortBy, String direction);

}
