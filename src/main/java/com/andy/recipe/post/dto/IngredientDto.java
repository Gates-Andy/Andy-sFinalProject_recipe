package com.andy.recipe.post.dto;

import org.springframework.web.multipart.MultipartFile;

public class IngredientDto {
	private int postId; // post에서 가져와야한다.
	private String ingredientName;
	private String ingredientAmount;
	private String content;
	private MultipartFile imageFile;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getIngredientAmount() {
		return ingredientAmount;
	}

	public void setIngredientAmount(String ingredientAmount) {
		this.ingredientAmount = ingredientAmount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
}
