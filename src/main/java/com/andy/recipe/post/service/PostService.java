package com.andy.recipe.post.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.andy.recipe.post.domain.Post;
import com.andy.recipe.post.repository.PostRepository;

@Service
public class PostService {

	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public List<Post> getPostList() {

		List<Post> postList = postRepository.selectPostList();

		return postList;
	}

	public List<Post> getPostListByUserId(long userId) {

		List<Post> postList = postRepository.selectByUserId(userId);

		return postList;
	}

	public boolean addPost(long userId, String title, String category, int headcount, String content, String imagePath) {
		
		Post post = new Post();
		
		post.setUserId(userId);
		post.setTitle(title);
		post.setCategory(category);
		post.setHeadcount(headcount);
		post.setContent(content);
		post.setImagePath(imagePath);
		post.setCreatedAt(LocalDateTime.now());
		post.setUpdatedAt(LocalDateTime.now());
		
		int result = postRepository.insertPost(post);
		
		return result == 1;
	}
}