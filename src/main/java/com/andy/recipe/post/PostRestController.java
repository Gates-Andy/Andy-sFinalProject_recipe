package com.andy.recipe.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.andy.recipe.post.service.PostService;

import common.FileManager;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {

	private final PostService postService;

	public PostRestController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping("/create")
	public Map<String, String> createPost(@RequestParam("title") String title,
			@RequestParam("category") String category, @RequestParam("headcount") int headcount,
			@RequestParam("content") String content, @RequestParam("imageFile") MultipartFile imageFile,
			HttpSession session) {

		Map<String, String> resultMap = new HashMap<>();

		long loginId = (long) session.getAttribute("userId"); 

		String imagePath = null;
		
		if (imageFile != null && !imageFile.isEmpty()) {
			imagePath = FileManager.saveFile(loginId, imageFile);
			if (imagePath == null) {
				resultMap.put("result", "fail");
				resultMap.put("message", "파일 업로드에 실패했습니다.");
				return resultMap;
			}
		}

		if (postService.addPost(loginId, title, category, headcount, content, imagePath)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
			resultMap.put("message", "게시글 추가에 실패했습니다.");
		}

		return resultMap;
	}
}
