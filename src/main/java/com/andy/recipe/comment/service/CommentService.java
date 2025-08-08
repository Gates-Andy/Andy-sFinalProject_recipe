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

	public boolean addComment(long userId, int postId, String text) {

		Comment comment = new Comment();
		comment.setUserId((int) userId);
		comment.setPostId((int) postId);
		comment.setText(text);

		int result = commentRepository.insertComment(comment);
		
		return result == 1;
	}
	
	public boolean deleteComment(int id) {
		
		int result = commentRepository.deleteCommentById(id);
		
		return result == 1;
	}

	public List<Comment> getCommentListByPostId(long postId) {
		
		return commentRepository.findCommentsByPostId(postId);
		
	}

}