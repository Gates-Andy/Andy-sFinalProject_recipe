package com.andy.recipe.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andy.recipe.user.domain.User;
import com.andy.recipe.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {
	private final UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/join")
	public Map<String, String> join(@RequestParam("loginId") String loginId, @RequestParam("password") String password,
			@RequestParam("email") String email) {

		Map<String, String> resultMap = new HashMap<>();

		if (userService.adduser(loginId, password, email)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}

	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicateId(@RequestParam("loginId") String loginId) {

		Map<String, Boolean> resultMap = new HashMap<>();

		if (userService.isDuplicateId(loginId)) {
			resultMap.put("isDuplicate", true);
		} else {
			resultMap.put("isDuplicate", false);
		}
		return resultMap;
	}

	@GetMapping("/duplicate-email")
	public Map<String, Boolean> isDuplicateEmail(@RequestParam("email") String email) {

		Map<String, Boolean> resultMap = new HashMap<>();

		if (userService.isDuplicateEmail(email)) {
			resultMap.put("isDuplicate", true);
		} else {
			resultMap.put("isDuplicate", false);
		}
		return resultMap;
	}

	@PostMapping("/login")
	public Map<String, String> login(@RequestParam("loginId") String loginId, @RequestParam("password") String password,
			HttpServletRequest request) {

		Map<String, String> resultMap = new HashMap<>();

		User user = userService.getUser(loginId, password);

		if (user != null) {
			resultMap.put("result", "success");

			HttpSession session = request.getSession();

			session.setAttribute("userId", user.getId());

		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}

}
