package com.andy.recipe.user.service;

import org.springframework.stereotype.Service;

import com.andy.recipe.common.SHA256HashingEncoder;
import com.andy.recipe.user.domain.User;
import com.andy.recipe.user.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
	    this.userRepository = userRepository;
	}

	public boolean adduser(String loginId, String password, String email) {

		String hashingPassword = SHA256HashingEncoder.encode(password);

		int count = userRepository.insertUser(loginId, hashingPassword, email);

		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isDuplicateId(String loginId) {
		
		int count = userRepository.selectCountByloginId(loginId);

		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isDuplicateEmail(String email) {
		
		int count = userRepository.selectCountByemail(email);

		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	public User getUser(String loginId, String password) {

		String hashingPassword = SHA256HashingEncoder.encode(password);

		return userRepository.selectUser(loginId, hashingPassword);
	}

	public User getUserById(long Id) {
		
		return userRepository.selectUserById(Id);
		
	}
}
