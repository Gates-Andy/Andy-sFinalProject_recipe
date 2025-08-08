package com.andy.recipe.like.service;

import org.springframework.stereotype.Service;

import com.andy.recipe.like.domain.Like;
import com.andy.recipe.like.repository.LikeRepository;

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

		int result = likeRepository.insertLike(like);

		return result == 1;
	}

	public boolean removeLike(long userId, long postId) {

		Like like = new Like();
		like.setUserId((int) userId);
		like.setPostId((int) postId);

		int result = likeRepository.deleteLike(userId, postId);

		return result == 1;
	}

	public int likeCountByPostId(long postId) {

		return likeRepository.countByPostId(postId);

	}

	public boolean isPostLikedByUser(long postId, long userId) {

		return likeRepository.existsByPostIdAndUserId(postId, userId);

	}

}