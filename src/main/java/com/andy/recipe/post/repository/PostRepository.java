package com.andy.recipe.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.andy.recipe.post.domain.Post;

@Mapper
public interface PostRepository {
	public List<Post> selectByUserId(long userId);
	
	public List<Post> selectPostList();
	
	public int insertPost(Post post);
}
