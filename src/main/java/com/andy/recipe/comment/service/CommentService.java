package com.andy.recipe.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.andy.recipe.comment.domain.Comment;
import com.andy.recipe.comment.repository.CommentRepository;

@Service
public class CommentService {
	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public boolean addComment(int userId, int postId, String text) {

		Comment comment = new Comment();
		comment.setUserId(userId);
		comment.setPostId(postId);
		comment.setText(text);

		int result = commentRepository.insertComment(comment);
		
		return result > 0;
	}

	public List<Comment> getCommentListByPostId(int postId) {
		return commentRepository.findCommentsByPostId(postId);
	}

	public boolean deleteComment(int id) {
		return commentRepository.deleteCommentById(id) > 0;
	}

}