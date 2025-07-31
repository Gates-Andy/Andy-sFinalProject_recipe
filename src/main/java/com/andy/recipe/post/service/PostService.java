package com.andy.recipe.post.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.andy.recipe.post.domain.Post;
import com.andy.recipe.post.dto.PostDto;
import com.andy.recipe.post.repository.PostRepository;
import com.andy.recipe.user.domain.User;
import com.andy.recipe.user.service.UserService;

@Service
public class PostService {

	private final PostRepository postRepository;
	private final UserService userService; // loginId를 얻어오기 위해

	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}

	/*
	 * public List<Post> getPostList() {
	 * 
	 * List<Post> postList = postRepository.selectPostList();
	 * 
	 * return postList; }
	 */

	public List<PostDto> getPostList() {
		
		List<Post> postList = postRepository.selectPostList();

		List<PostDto> dtoList = new ArrayList<>();

		for (Post post : postList) {
			
			User user = userService.getUserById(post.getUserId()); // user객체 id = 1 
			
			PostDto dto = new PostDto();

			String loginId = user.getLoginId(); // use의 객체의 id가 1인 놈의 로그인아이디 반환.
			
			dto.setUserId(post.getUserId()); // 1
			
			dto.setLoginId(loginId); // superyoon1
			
			dto.setTitle(post.getTitle());
			dto.setContent(post.getContent());
			dto.setHeadcount(post.getHeadcount());
			dto.setCategory(post.getCategory());
			dto.setImagePath(post.getImagePath());

			dtoList.add(dto); // id = 2 인놈을 찾으러 다 넣엇으면 저장 dto1이 저장되겠지
		}

		return dtoList;
	}

	public List<Post> getPostListByUserId(long userId) {

		List<Post> postList = postRepository.selectByUserId(userId);

		return postList;
	}

	public boolean addPost(long userId, String title, int headcount, String category, String content,
			String imagePath) {

		Post post = new Post();

		post.setUserId(userId);
		post.setTitle(title);
		post.setHeadcount(headcount);
		post.setCategory(category);
		post.setContent(content);
		post.setImagePath(imagePath);
		post.setCreatedAt(LocalDateTime.now());
		post.setUpdatedAt(LocalDateTime.now());

		int result = postRepository.insertPost(post);

		return result == 1;
	}
	
}