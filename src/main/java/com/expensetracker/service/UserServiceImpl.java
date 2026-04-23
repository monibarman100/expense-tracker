package com.expensetracker.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.expensetracker.dto.LoginRequestDTO;
import com.expensetracker.dto.UserRequestDTO;
import com.expensetracker.entity.User;
import com.expensetracker.repository.UserRepository;
import com.expensetracker.utility.JwtUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public String registerUser(UserRequestDTO request) {
		
		if(userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("User already exists");
		}
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		userRepository.save(user);
		
		return "User registered successfully";
		
	}

	@Override
	public String loginUser(LoginRequestDTO request) {
		
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		//Compare Password
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			
			throw new RuntimeException("Invalid Password");
		}
			
		//return "Login Successfully";
		
		return jwtUtil.generateToken(user.getEmail());
	}

}
