package com.andy.recipe.like.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.andy.recipe.like.domain.Like;

@Mapper
public interface LikeRepository {

	int insertLike(Like like);

	int deleteLike(@Param("userId") long userId, @Param("postId") long postId);
	
	int countByPostId(@Param("postId") Long postId);

	boolean existsByPostIdAndUserId(@Param("postId")long postId, @Param("userId")long userId);
}
