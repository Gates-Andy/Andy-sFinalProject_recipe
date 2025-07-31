package com.andy.recipe.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.andy.recipe.common.FileManager;
import com.andy.recipe.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {

	private final PostService postService;

	public PostRestController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping("/create")
	public Map<String, String> createPost(
			@RequestParam("title") String title,
			@RequestParam("headcount") int headcount,
			@RequestParam("category") String category, 
			@RequestParam("content") String content, 
			@RequestParam("imageFile") MultipartFile imageFile,
			HttpSession session) {

		Map<String, String> resultMap = new HashMap<>();

		Long userId = (Long) session.getAttribute("userId");

		String imagePath = FileManager.saveFile(userId, imageFile);

		if (postService.addPost(userId, title, headcount, category, content, imagePath)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	@PostMapping("/ingredient")
	public Map<String, String> putIngredient(
			@RequestParam("postId") int postId, 
			@RequestParam("ingredientName") String ingredientName, 
			@RequestParam("ingredientAmount") String ingredientAmount, 
			@RequestParam("content") String content, 
			@RequestParam("imageFile") MultipartFile imageFile,
			HttpSession session) {
		
	    Map<String, String> resultMap = new HashMap<>();
	    
	    Long userId = (Long) session.getAttribute("userId");
	   

	
	@PostMapping("/step")
	public Map<String, String> putStep(
			@RequestParam("stepNumber") int stepNumber,
			@RequestParam("content") String content,
			@RequestParam("imageFile") MultipartFile imageFile,
			HttpSession session) {
		
		Map<String, String> resultMap = new HashMap<>();
		
		return resultMap;
	}
	
	
}
