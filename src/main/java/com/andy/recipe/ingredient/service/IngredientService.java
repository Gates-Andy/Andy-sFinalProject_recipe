package com.andy.recipe.ingredient.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.andy.recipe.ingredient.domain.Ingredient;
import com.andy.recipe.ingredient.repository.IngredientRepository;

@Service
public class IngredientService {
	private final IngredientRepository ingredientRepository;

	public IngredientService(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public boolean addIngredient(int postId, String ingredientName, String ingredientAmount, String content,
			String imagePath) {

		Ingredient ingredient = new Ingredient();

		ingredient.setPostId(postId);
		ingredient.setIngredientName(ingredientName);
		ingredient.setIngredientAmount(ingredientAmount);
		ingredient.setContent(content);
		ingredient.setImagePath(imagePath);
		ingredient.setCreatedAt(LocalDateTime.now());
		ingredient.setUpdatedAt(LocalDateTime.now());

		int result = ingredientRepository.insertIngredient(ingredient);

		return result == 1;
	}

}
