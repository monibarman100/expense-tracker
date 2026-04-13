package com.expensetracker.service;

import com.expensetracker.dto.UserRequestDTO;

public interface UserService {
	String registerUser(UserRequestDTO request);
}
