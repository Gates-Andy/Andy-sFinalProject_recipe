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

			@RequestParam("stepNumber") int stepNumber, 
			@RequestParam("content") String content,
			@RequestParam("imageFile") MultipartFile imageFile,

			@RequestParam("stepNumber2") int stepNumber2, 
			@RequestParam("content2") String content2,
			@RequestParam("imageFile2") MultipartFile imageFile2,

			@RequestParam("stepNumber3") int stepNumber3, 
			@RequestParam("content3") String content3,
			@RequestParam("imageFile3") MultipartFile imageFile3,

			HttpSession session) {

		Map<String, String> resultMap = new HashMap<>();

		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			resultMap.put("result", "logout");
			return resultMap;
		}

		String imagePath = FileManager.saveFile(userId, imageFile);
		String imagePath2 = FileManager.saveFile(userId, imageFile2);
		String imagePath3 = FileManager.saveFile(userId, imageFile3);

		if (stepService.addStep(postId, stepNumber, content, imagePath)
				&& stepService.addStep(postId, stepNumber2, content2, imagePath2)
				&& stepService.addStep(postId, stepNumber3, content3, imagePath3)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
}