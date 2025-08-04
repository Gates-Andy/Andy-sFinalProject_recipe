package com.andy.recipe.like.service;

import org.springframework.stereotype.Service;

import com.andy.recipe.like.domain.Like;
import com.andy.recipe.like.repository.LikeRepository;

import jakarta.persistence.PersistenceException;

@Service
public class LikeService {

	private final LikeRepository likeRepository;

	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}

	public boolean addLike(long userId, long postId) {

		Like like = new Like();
		like.setUserId((int) userId);
		like.setPostId((int) postId);

		try {
			likeRepository.insertLike(like);
		} catch (PersistenceException e) {
			return false;
		}
		return true;
	}

	public int likeCountByPostId(long postId) {
		return likeRepository.countByPostId(postId);
	}

	public boolean isLikePostIdAndUserId(long postId, long loginId) {
		return likeRepository.existsByPostIdAndLoginId(postId, loginId);
	}
}