package com.andy.recipe.post.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.andy.recipe.comment.dto.CommentDto;
import com.andy.recipe.comment.service.CommentService;
import com.andy.recipe.common.FileManager;
import com.andy.recipe.ingredient.domain.Ingredient;
import com.andy.recipe.ingredient.service.IngredientService;
import com.andy.recipe.like.service.LikeService;
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
	private final UserService userService; 			   // loginId를 얻어오기 위해
	private final IngredientService ingredientService; // 재료 수량 사진 설명 얻어와야하잖아 postService에서 주입해서 dto만들어야지
	private final StepService stepService; 			   // 작업 절차 사진 설명 얻어와야하잖아 postService에서 주입해서 dto만들어야지
	private final LikeService likeService;
	private final CommentService commentService;
	
	public PostService(
			PostRepository postRepository, 
			UserService userService, 
			IngredientService ingredientService, 
			StepService stepService, 
			LikeService likeService, 
			CommentService commentService) {
		this.postRepository = postRepository;
		this.userService = userService;
		this.ingredientService = ingredientService;
		this.stepService = stepService;
		this.likeService = likeService;
		this.commentService = commentService;
	}

	public List<PostDto> getPostList() { 

		List<Post> postList = postRepository.selectPostList(); 

		List<PostDto> postDtoList = new ArrayList<>(); 

		for (Post post : postList) {

			PostDto dto = new PostDto(); 
			// 1. post에 저장된 userId(fk)로 User 정보를 조회, 조회된 int에 user객체에서 loginId를 꺼내어 dto에 저장
			User user = userService.getUserById(post.getUserId()); 
			String loginId = user.getLoginId(); 
			dto.setLoginId(loginId); 
			
			// 2. post entity 맴버변수 모두 꺼내어 저장
			dto.setId(post.getId());
			dto.setUserId(post.getUserId()); 
			dto.setTitle(post.getTitle()); 
			dto.setContent(post.getContent()); 
			dto.setHeadcount(post.getHeadcount()); 
			dto.setCategory(post.getCategory());
			dto.setImagePath(post.getImagePath());

			List<Ingredient> ingredientList = ingredientService.getIngredientsByPostId(post.getId());
			dto.setIngredientList(ingredientList);
			
			int likeCount = likeService.likeCountByPostId(post.getId());
			dto.setLikeCount(likeCount);
			
			postDtoList.add(dto); 
		}

		return postDtoList; 
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

		Post post = postRepository.selectPostById(id);

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

		List<Step> stepList = stepService.getStepsByPostId(post.getId());
		dto.setStepList(stepList);
		
		return dto;
	}
	
	public PostDto getPostById(long postId, long currentUserId) {

		Post post = postRepository.selectPostById(postId);

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

		List<Step> stepList = stepService.getStepsByPostId(post.getId());
		dto.setStepList(stepList);

		int likeCount = likeService.likeCountByPostId(post.getId());
		dto.setLikeCount(likeCount);
		
		boolean likedByCurrentUser = likeService.isPostLikedByUser(postId, currentUserId);
		dto.setLikedByCurrentUser(likedByCurrentUser);
		
		List<CommentDto> commentList = commentService.getCommentListByPostId(post.getId());
		dto.setCommentList(commentList);
		
		return dto;
	}

//	public List<Post> getPostListByUserId(long userId) {
//
//		List<Post> postList = postRepository.selectByUserId(userId);
//
//		return postList;
//	}

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

	public boolean updatePost(long id, long loginId, String title, int headcount, String category, String content,
			MultipartFile imageFile) {

		Post post = postRepository.selectPostById(id);

		post.setTitle(title);
		post.setHeadcount(headcount);
		post.setContent(content);
		post.setCategory(category);

		if (imageFile != null) {
			String imagePath = FileManager.saveFile(loginId, imageFile);
			post.setImagePath(imagePath);
		}

		int result = postRepository.updatePost(post);

		return result == 1;
	}

	public boolean deletePost(long id) {

		int result = postRepository.deletePost(id);

		return result == 1;
	}
}