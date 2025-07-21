package com.andy.recipe.post.service;

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
	
	public List<Post> getPostList(long userId){
		
		List<Post> postList = postRepository.selectByUserId(userId);
				
		return postList;
	}
}
