package com.andy.recipe.step;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.andy.recipe.common.FileManager;
import com.andy.recipe.step.service.StepService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/step")
public class StepRestController {

	private final StepService stepService;

	public StepRestController(StepService stepService) {
		this.stepService = stepService;
	}

	@PostMapping("/create")
	public Map<String, String> createStep(@RequestParam("postId") int postId,
			@RequestParam("stepNumber") int stepNumber, @RequestParam("content") String content,
			@RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {

		Map<String, String> resultMap = new HashMap<>();

		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			resultMap.put("result", "logout");
			return resultMap;
		}

		String imagePath = FileManager.saveFile(userId, imageFile);

		if (stepService.addStep(postId, stepNumber, content, imagePath)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
}