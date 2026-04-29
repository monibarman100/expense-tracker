package com.expensetracker.service;

import java.util.List;

import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.entity.Expense;

public interface ExpenseService {

	String addExpense(ExpenseDTO request);

	List<Expense> getUserExpenses();

}
