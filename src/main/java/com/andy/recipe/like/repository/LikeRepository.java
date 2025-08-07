package com.andy.recipe.like.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.andy.recipe.like.domain.Like;

@Mapper
public interface LikeRepository {

	int insertLike(Like like);

	int countByPostId(@Param("postId") Long postId);
	
	int existsByUserIdAndPostId(@Param("userId") int userId, @Param("postId") int postId);
}
