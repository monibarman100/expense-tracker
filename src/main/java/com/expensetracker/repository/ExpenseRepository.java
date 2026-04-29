package com.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.expensetracker.entity.Expense;

import java.util.List;


public interface ExpenseRepository extends JpaRepository<Expense, Long>{
	
	List<Expense> findByUserId(Long user_id);


}
