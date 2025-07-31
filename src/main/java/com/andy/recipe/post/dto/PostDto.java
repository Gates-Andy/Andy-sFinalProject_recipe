package com.andy.recipe.post.dto;

public class PostDto {
	private long userId;
	private String loginId; // user 테이블에서 조인해서 얻어오자..
	private String title;
	private String content;
	private int headcount;
	private String category;
	private String imagePath;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) { 
		this.userId = userId;
	}

	public String getLoginId() { // user 테이블에서 조인해서 얻어오자..
		return loginId;
	}

	public void setLoginId(String loginId) { // user 테이블에서 조인해서 얻어오자..
		this.loginId = loginId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHeadcount() {
		return headcount;
	}

	public void setHeadcount(int headcount) {
		this.headcount = headcount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
