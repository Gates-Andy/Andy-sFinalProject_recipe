package com.andy.recipe.post.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.andy.recipe.ingredient.domain.Ingredient;
import com.andy.recipe.ingredient.service.IngredientService;
import com.andy.recipe.post.domain.Post;
import com.andy.recipe.post.dto.PostDto;
import com.andy.recipe.post.repository.PostRepository;
import com.andy.recipe.step.domain.Step;
import com.andy.recipe.step.service.StepService;
import com.andy.recipe.user.domain.User;
import com.andy.recipe.user.service.UserService;

@Service
public class PostService {

	private final PostRepository postRepository;
	private final UserService userService; // loginId를 얻어오기 위해
	private final IngredientService ingredientService; // 재료 수량 사진 설명 얻어와야하잖아 postService에서 주입해서 dto만들어야지
	private final StepService stepService;

	public PostService(PostRepository postRepository, UserService userService, IngredientService ingredientService,
			StepService stepService) {
		this.postRepository = postRepository;
		this.userService = userService;
		this.ingredientService = ingredientService;
		this.stepService = stepService;
	}

	public List<PostDto> getPostList() {

		List<Post> postList = postRepository.selectPostList(); // DB에서 모든 게시글(Post 객체들)을 가져온다

		List<PostDto> postDtoList = new ArrayList<>(); // 결과를 담을 DTO 리스트 생성

		for (Post post : postList) {

			PostDto dto = new PostDto(); // 빈 DTO 객체 생성 (여기에 필요한 값들을 담을 예정)

			User user = userService.getUserById(post.getUserId()); // post에 저장된 userId로 User 정보를 조회
			String loginId = user.getLoginId(); // user 객체에서 loginId를 꺼냄 (예: superyoon1)
			dto.setLoginId(loginId); // 로그인 ID를 DTO에 저장

			dto.setId(post.getId());

			dto.setUserId(post.getUserId()); // 작성자 ID
			dto.setTitle(post.getTitle()); // 제목
			dto.setContent(post.getContent()); // 내용
			dto.setHeadcount(post.getHeadcount()); // 모집 인원 수
			dto.setCategory(post.getCategory()); // 카테고리
			dto.setImagePath(post.getImagePath()); // 이미지 경로를 DTO에 저장

			List<Ingredient> ingredientList = ingredientService.getIngredientsByPostId(post.getId()); // postId를 파라미터로
																										// 재료내용(list)룰
																										// 가져와여기에 저장 한뒤
																										// dto에 저장
			dto.setIngredientList(ingredientList);

			postDtoList.add(dto); // 완성된 DTO를 리스트에 추가
		}

		return postDtoList; // 모든 게시글 DTO 목록을 반환
	}

	public List<PostDto> getPostList(long userId) {
		List<Post> postList = postRepository.selectPostListByUserId(userId);

		List<PostDto> postDtoList = new ArrayList<>();

		for (Post post : postList) {
			PostDto dto = new PostDto();

			User user = userService.getUserById(post.getUserId());
			dto.setLoginId(user.getLoginId());

			dto.setId(post.getId());
			dto.setUserId(post.getUserId());
			dto.setTitle(post.getTitle());
			dto.setContent(post.getContent());
			dto.setHeadcount(post.getHeadcount());
			dto.setCategory(post.getCategory());
			dto.setImagePath(post.getImagePath());

			List<Ingredient> ingredientList = ingredientService.getIngredientsByPostId(post.getId());
			dto.setIngredientList(ingredientList);

			postDtoList.add(dto);
		}

		return postDtoList;
	}

	public PostDto getPostById(long id) {

		Post post = postRepository.selectPostById(id); // post id 를 파라미터로 하나 통째로 가져올거고

		PostDto dto = new PostDto(); // dto 객체를 반들어

		User user = userService.getUserById(post.getUserId());
		dto.setLoginId(user.getLoginId());

		dto.setId(post.getId());
		dto.setUserId(post.getUserId());
		dto.setTitle(post.getTitle());
		dto.setContent(post.getContent());
		dto.setHeadcount(post.getHeadcount());
		dto.setCategory(post.getCategory());
		dto.setImagePath(post.getImagePath());

		List<Ingredient> ingredientList = ingredientService.getIngredientsByPostId(post.getId());
		dto.setIngredientList(ingredientList);

		List<Step> stepList = stepService.getStepsByPostId(post.getId());
		dto.setStepList(stepList);

		return dto;
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