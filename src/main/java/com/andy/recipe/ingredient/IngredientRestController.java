package com.andy.recipe.ingredient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.andy.recipe.common.FileManager;
import com.andy.recipe.ingredient.service.IngredientService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/ingredient")
public class IngredientRestController {

	private final IngredientService ingredientService;

	public IngredientRestController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	@PostMapping("/create")
	public Map<String, String> createIngredient(@RequestParam("postId") int postId,

			@RequestParam("ingredientNumber") List<String> ingredientNumber,
			
			@RequestParam("ingredientName") List<String> ingredientName,
			@RequestParam("ingredientAmount") List<String> ingredientAmount,
			@RequestParam("content") List<String> content, 
			@RequestParam("imageFile") List<MultipartFile> imageFiles,

			HttpSession session) {

		Map<String, String> resultMap = new HashMap<>();

		Long userId = (Long) session.getAttribute("userId");

		if (userId == null) {
			resultMap.put("result", "logout");
			return resultMap;
		}

		boolean allSuccess = true;

		for (int i = 0; i < ingredientNumber.size(); i++) {
			String imagePath = FileManager.saveFile(userId, imageFiles.get(i));

			boolean success = ingredientService.addIngredient(
					postId
					, ingredientNumber.get(i)
					, ingredientName.get(i)
					, ingredientAmount.get(i)
					, content.get(i)
					, imagePath);

			if (!success)
				allSuccess = false;

		}

		resultMap.put("result", allSuccess ? "success" : "fail");

		return resultMap;
	}

}
