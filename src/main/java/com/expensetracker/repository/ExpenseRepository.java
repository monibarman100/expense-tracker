package com.expensetracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.expensetracker.entity.Expense;

import java.util.List;


public interface ExpenseRepository extends JpaRepository<Expense, Long>{

	Page<Expense> findByUserIdAndCategory(Long getid, String category, Pageable pageable);

	
	
	//List<Expense> findByUserId(Long user_id);


}

	