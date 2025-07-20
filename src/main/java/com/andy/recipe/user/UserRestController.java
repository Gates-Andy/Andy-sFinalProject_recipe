package com.andy.recipe.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andy.recipe.user.service.UserService;

@RequestMapping("/user")
@RestController
public class UserRestController {
	private final UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/join")
	public Map<String, String> join(@RequestParam String loginId, @RequestParam String password, @RequestParam String email) {

		Map<String, String> resultMap = new HashMap<>();

		if (userService.adduser(loginId, password, email)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}

	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicateId(@RequestParam String loginId) {
		Map<String, Boolean> resultMap = new HashMap<>();

		if (userService.isDuplicateId(loginId)) {
			resultMap.put("isDuplicate", true);
		} else {
			resultMap.put("isDuplicate", false);
		}
		return resultMap;
	}

	@GetMapping("/duplicate-email")
	public Map<String, Boolean> isDuplicateEmail(@RequestParam String email) {
		Map<String, Boolean> resultMap = new HashMap<>();

		if (userService.isDuplicateEmail(email)) {
			resultMap.put("isDuplicate", true);
		} else {
			resultMap.put("isDuplicate", false);
		}
		return resultMap;
	}

}
