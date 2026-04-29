package com.expensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.entity.Expense;
import com.expensetracker.entity.User;
import com.expensetracker.repository.ExpenseRepository;
import com.expensetracker.repository.UserRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String addExpense(ExpenseDTO request) {
		
		String email = (String) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found!"));
		
		Expense expense = new Expense();
		expense.setTitle(request.getTitle());
		expense.setAmount(request.getAmount());
		expense.setCategory(request.getCategory());
		expense.setDate(request.getDate());
		expense.setUser(user);
		
		expenseRepository.save(expense);
		
		return "Expense added successfully";
	}

	@Override
	public List<Expense> getUserExpenses() {
		
		String email = (String) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
				
		return expenseRepository.findByUserId(user.getid());
	}

}
