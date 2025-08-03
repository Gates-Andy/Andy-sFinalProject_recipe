package com.andy.recipe.ingredient;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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
			@RequestPart(value = "imageFile", required = false) MultipartFile imageFile,

			@RequestParam("ingredientName2") String ingredientName2,
			@RequestParam("ingredientAmount2") String ingredientAmount2, @RequestParam("content2") String content2,
			@RequestPart(value = "imageFile2", required = false) MultipartFile imageFile2,

			@RequestParam("ingredientName3") String ingredientName3,
			@RequestParam("ingredientAmount3") String ingredientAmount3, @RequestParam("content3") String content3,
			@RequestPart(value = "imageFile3", required = false) MultipartFile imageFile3,

			HttpSession session) {

		Map<String, String> resultMap = new HashMap<>();

		Long userId = (Long) session.getAttribute("userId");

		String imagePath = FileManager.saveFile(userId, imageFile);
		String imagePath2 = FileManager.saveFile(userId, imageFile2);
		String imagePath3 = FileManager.saveFile(userId, imageFile3);

		if (ingredientService.addIngredient(postId, ingredientName, ingredientAmount, content, imagePath) && 
				ingredientService.addIngredient(postId, ingredientName2, ingredientAmount2, content2, imagePath2) && 
				ingredientService.addIngredient(postId, ingredientName3, ingredientAmount3, content3, imagePath3) ) {
			
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}

}
