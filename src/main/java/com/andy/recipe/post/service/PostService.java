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
		
		List<Post> postList = postRepository.selectPostList();  // DB에서 모든 게시글(Post 객체들)을 가져온다

		List<PostDto> dtoList = new ArrayList<>(); // 결과를 담을 DTO 리스트 생성

		for (Post post : postList) {
			
			PostDto dto = new PostDto(); // 빈 DTO 객체 생성 (여기에 필요한 값들을 담을 예정)

			User user = userService.getUserById(post.getUserId()); // post에 저장된 userId로 User 정보를 조회 

			String loginId = user.getLoginId(); // user 객체에서 loginId를 꺼냄 (예: superyoon1)
			
			dto.setLoginId(loginId); // 로그인 ID를 DTO에 저장
			
			dto.setUserId(post.getUserId());     // 작성자 ID를 DTO에 저장
			dto.setTitle(post.getTitle());       // 제목을 DTO에 저장
			dto.setContent(post.getContent());   // 내용을 DTO에 저장
			dto.setHeadcount(post.getHeadcount()); // 모집 인원 수를 DTO에 저장
			dto.setCategory(post.getCategory());   // 카테고리를 DTO에 저장
			dto.setImagePath(post.getImagePath()); // 이미지 경로를 DTO에 저장

			dtoList.add(dto); // 완성된 DTO를 리스트에 추가
		}

		return dtoList; // 모든 게시글 DTO 목록을 반환
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