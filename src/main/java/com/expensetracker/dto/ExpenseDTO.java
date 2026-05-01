package com.expensetracker.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ExpenseDTO {
	
	private Long expense_id;
	
	@NotBlank(message="Title is required")
	private String title;
	
	@NotNull(message="Amount is required")
	private Double amount;
	
	@NotBlank(message="Category is required")
	private String category;
	
	@NotNull(message="Date is required")
	private LocalDate date;
	
	public Long getExpense_id() {
		return expense_id;
	}
	public void setExpense_id(Long expense_id) {
		this.expense_id = expense_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	

}
