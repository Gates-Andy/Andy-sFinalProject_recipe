package com.andy.recipe.ingredient.repository;

import org.apache.ibatis.annotations.Mapper;

import com.andy.recipe.ingredient.domain.Ingredient;

@Mapper
public interface IngredientRepository {
	public int insertIngredient(Ingredient ingredient);
}
