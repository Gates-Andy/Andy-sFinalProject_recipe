package com.andy.recipe.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andy.recipe.like.service.LikeService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class LikeRestController {

	private final LikeService likeService;

	public LikeRestController(LikeService likeService) {
		this.likeService = likeService;
	}

	@PostMapping("/like")
	public Map<String, String> createLike(@RequestParam("postId") long postId, HttpSession session) {

		Map<String, String> resultMap = new HashMap<>();

		Object userIdObj = session.getAttribute("userId");

		if (userIdObj == null) {
			resultMap.put("result", "fail");
			resultMap.put("message", "User not logged in");
			return resultMap;
		}

		long userId = (long) userIdObj;

		if (likeService.addLike(userId, postId)) {

			resultMap.put("result", "success");

		} else {

			resultMap.put("result", "fail");

		}

		return resultMap;
	}
}