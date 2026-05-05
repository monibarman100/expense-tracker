package com.expensetracker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

	@Override
	public String updateExpense(Long id, ExpenseDTO request) {
		
		String email = (String) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		Expense expense = expenseRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Expense not found"));
		
		if(!expense.getUser().getid().equals(user.getid())){
			throw new RuntimeException("Unauthorized");
		}
		
		expense.setTitle(request.getTitle());
		expense.setAmount(request.getAmount());
		expense.setCategory(request.getCategory());
		expense.setDate(request.getDate());
		
		expenseRepository.save(expense);
		
		return "Expense updated successfully";
	}

	@Override
	public String deleteMapping(Long id) {
		
		String email = (String) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		Expense expense = expenseRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Expense not found"));
		
		if(!expense.getUser().getid().equals(user.getid())) {
			throw new RuntimeException("Unauthorized");
		}
		
		expenseRepository.delete(expense);
		
		return "Expense deleted successfully";
	}

	/*@Override
	public Page<Expense> getExpensesByCategory(String category, int page, int size) {
	
		
		String email = (String) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
				
		Pageable pageable = PageRequest.of(page, size);
		
		return expenseRepository.findByUserIdAndCategory(user.getid(), category, pageable);
	}*/

	@Override
	public Page<Expense> getFilteredExpenses(String category, int page, int size, String sortBy, String direction) {
		
		String email = (String) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
				
		Sort sort = Sort.by("category").ascending()
				.and(Sort.by("amount").descending());
				
		Pageable pageable = PageRequest.of(page, size, sort);
		
		return expenseRepository.findByUserIdAndCategory(user.getid(), category, pageable);
	}

	@Override
	public Double getTotalExpense() {
		
		String email = (String) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
				
		return expenseRepository.getTotalExpenseByUserId(user.getid());
	}

	@Override
	public Map<String, Double> getCategoryReport() {
		
		String email = (String) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		List<Object[]> results = expenseRepository.getCategoryWiseReport(user.getid());
		
		/*Map<String,Double> report = new HashMap<>();
		
		for(Object[] row : results)
			report.put((String) row[0], (Double) row[1]);*/
		
		Map<String,Double> report = results.stream()
				.collect(Collectors.toMap(
						row -> (String) row[0],
						row -> (Double) row[1]
								
				));				
		
		return report;
		
	}

	@Override
	public Map<Integer, Double> getMonthlyReport() {
		
		String email = (String) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		List<Object[]> results = expenseRepository.getMonthlyReport(user.getid());
		
	/*	Map<Integer,Double> report = new HashMap<>();
		
		for(Object[] row : results) {
			report.put((Integer) row[0], (Double) row[1]);
		}*/
		
		Map<Integer,Double> report = results.stream()
				.collect(Collectors.toMap(
						row -> (Integer) row[0],
						row -> (Double) row[1]
					));			
								
		return report;
	}

	

	
}
