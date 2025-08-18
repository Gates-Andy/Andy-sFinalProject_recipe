package com.andy.recipe.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andy.recipe.comment.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post/comment")
public class CommentRestController {

	private final CommentService commentService;

	public CommentRestController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/create")
	public Map<String, String> createComment(@RequestParam("postId") long postId, @RequestParam("text") String text,
			HttpSession session) {

		Map<String, String> resultMap = new HashMap<>();

		Object userIdObj = session.getAttribute("userId");

		long userId = (long) userIdObj;

		if (commentService.addComment(userId, postId, text)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		return resultMap;
	}

	@PostMapping("/delete")
	public Map<String, String> deleteComment(@RequestParam("commentId") long commentId) {
		
		Map<String, String> resultMap = new HashMap<>();

		boolean deleted = commentService.deleteComment(commentId);
		
		if (deleted) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		return resultMap;
	}
	
}