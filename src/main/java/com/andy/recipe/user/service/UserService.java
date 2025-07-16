package com.andy.recipe.user.service;

import org.springframework.stereotype.Service;

import com.andy.recipe.user.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepositrory) {
		this.userRepository = userRepositrory;
	}

	public boolean adduser(String loginId, String password, String email) {

		int count = userRepository.insertUser(loginId, password, email);

		if (count == 1) {
			
			return true;
			
		} else {
			
			return false;
			
		}
	}
}
