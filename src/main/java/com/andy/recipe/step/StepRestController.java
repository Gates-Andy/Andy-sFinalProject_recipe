package com.andy.recipe.step;

import java.util.HashMap;
import java.util.List;
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
	public Map<String, String> createStep(

			@RequestParam("postId") int postId,

			@RequestParam("stepNumber") List<Integer> stepNumbers, 
			@RequestParam("content") List<String> contents,
			@RequestParam("imageFile") List<MultipartFile> imageFiles,

			HttpSession session) {

		Map<String, String> resultMap = new HashMap<>();

		Long userId = (Long) session.getAttribute("userId");
		
		if (userId == null) {
			resultMap.put("result", "logout");
			return resultMap;
		}

		boolean allSuccess = true;

		for (int i = 0; i < stepNumbers.size(); i++) {
			String imagePath = FileManager.saveFile(userId, imageFiles.get(i));
			
			boolean success = stepService.addStep(postId, stepNumbers.get(i), contents.get(i), imagePath);
			
			if (!success)
				allSuccess = false;

		}

		resultMap.put("result", allSuccess ? "success" : "fail");
		
		return resultMap;
	}
}