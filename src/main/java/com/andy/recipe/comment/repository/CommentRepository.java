package com.andy.recipe.comment.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.andy.recipe.comment.domain.Comment;

@Mapper
public interface CommentRepository {
	
	int insertComment(Comment comment);

	int deleteCommentById(@Param("id") long id);
	
	Comment findById(@Param("id") long id);
	
	List<Comment> findCommentsByPostId(@Param("postId") long postId);

}
