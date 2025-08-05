package com.andy.recipe.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.andy.recipe.post.domain.Post;

@Mapper
public interface PostRepository {

	public List<Post> selectPostList();

	public List<Post> selectByUserId(long userId);

	public List<Post> selectPostListByUserId(long userId);

	public Post selectPostById(@Param("id") long id);

	public int insertPost(Post post);

	int updatePost(Post post);

	int deletePost(@Param("id") long id);
}
