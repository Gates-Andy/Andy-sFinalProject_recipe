package com.andy.recipe.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.andy.recipe.comment.domain.Comment;
import com.andy.recipe.comment.dto.CommentDto;
import com.andy.recipe.comment.repository.CommentRepository;
import com.andy.recipe.user.domain.User;
import com.andy.recipe.user.service.UserService;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final UserService userService;
	
	public CommentService(CommentRepository commentRepository,UserService userService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
	}

	public boolean addComment(long userId, int postId, String text) {

		Comment comment = new Comment();
		comment.setUserId((int) userId);
		comment.setPostId((int) postId);
		comment.setText(text);

		int result = commentRepository.insertComment(comment);

		return result == 1;
	}

	public boolean deleteComment(long commentId, long currentUserId) {
		
	    Comment comment = commentRepository.findById(commentId);
	    
	    if (comment == null) {
	        return false; // 댓글이 없음
	    }
	    
	    if (comment.getUserId() != currentUserId) {
	        return false; // 작성자가 아니므로 삭제 불가
	    }
	    
	    int result = commentRepository.deleteCommentById(commentId);
	    
	    return result == 1;
	}

	public List<CommentDto> getCommentListByPostId(long postId) {
		
		List<Comment> comments = commentRepository.findCommentsByPostId(postId);
		
		List<CommentDto> commentDtos = new ArrayList<>();

		for (Comment comment : comments) {
			User user = userService.getUserById(comment.getUserId());

			CommentDto dto = new CommentDto();
			dto.setId(comment.getId());
			dto.setUserId(comment.getUserId());
			dto.setUserName(user.getLoginId()); // 닉네임 또는 사용자명
			dto.setPostId(comment.getPostId());
			dto.setText(comment.getText());
			dto.setCreatedAt(comment.getCreatedAt());
			dto.setUpdatedAt(comment.getUpdatedAt());

			commentDtos.add(dto);
		}

		return commentDtos;
	}

}