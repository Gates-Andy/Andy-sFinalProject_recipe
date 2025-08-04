package com.andy.recipe.comment.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.andy.recipe.comment.domain.Comment;

@Mapper
public interface CommentRepository {
	
	int insertComment(Comment comment);

	List<Comment> findCommentsByPostId(@Param("postId") int postId);

	int deleteCommentById(@Param("id") int id);
	
}
