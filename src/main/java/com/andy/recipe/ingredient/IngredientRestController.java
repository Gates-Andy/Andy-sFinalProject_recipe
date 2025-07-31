package com.andy.recipe.ingredient;

import java.util.HashMap;
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
			@RequestParam("ingredientName") String ingredientName,
			@RequestParam("ingredientAmount") String ingredientAmount, @RequestParam("content") String content,
			@RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {

		Map<String, String> resultMap = new HashMap<>();

		Long userId = (Long) session.getAttribute("userId");

		String imagePath = FileManager.saveFile(userId, imageFile);

		if (ingredientService.addIngredient(postId, ingredientName, ingredientAmount, content, imagePath)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
}
