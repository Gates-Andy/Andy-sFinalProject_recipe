package com.andy.recipe.ingredient.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.andy.recipe.ingredient.domain.Ingredient;

@Mapper
public interface IngredientRepository {
	public int insertIngredient(Ingredient ingredient);
	
	List<Ingredient> selectByPostId(long postId); // postid에 저장되어있는 모든 정보를 가져와
}
