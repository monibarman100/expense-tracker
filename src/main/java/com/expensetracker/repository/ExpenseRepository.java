package com.expensetracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expensetracker.entity.Expense;

import java.util.List;


public interface ExpenseRepository extends JpaRepository<Expense, Long>{

	Page<Expense> findByUserIdAndCategory(Long getid, String category, Pageable pageable);
	
	@Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user.id = :userId")
	Double getTotalExpenseByUserId(@Param("userId") Long userId);

	@Query("SELECT e.category, SUM(e.amount) FROM Expense e WHERE e.user.id = :userId GROUP BY e.category")
	List<Object[]> getCategoryWiseReport(Long userId);

	@Query("SELECT MONTH(e.date), SUM(e.amount) FROM Expense e WHERE e.user.id = :userId GROUP BY e.date")
	List<Object[]> getMonthlyReport(Long userId);

	
	
	List<Expense> findByUserId(Long user_id);


}

	