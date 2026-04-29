package com.expensetracker.dto;

import java.time.LocalDate;

public class ExpenseDTO {
	
	private Long expense_id;
	private String title;
	private Double amount;
	private String category;
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
