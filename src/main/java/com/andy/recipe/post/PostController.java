package com.andy.recipe.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.andy.recipe.post.dto.PostDto;
import com.andy.recipe.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	// 모든 포스트 목록을 dto로 묶어 가져와 model에 담아 thymleaf사용 view로 클라이언트에게 보여줄수 있게 로직 구현해봤음
	@GetMapping("/main/view")
	public String main(Model model) {
		List<PostDto> postDtoList = postService.getPostList(); 
		model.addAttribute("postDtoList", postDtoList);
		return "post/main";
	}
	
	// 각 로그인 이용자의 모든 포스트 본인만 보이도록 본인의 객체(게시글)만 가져와야함
	@GetMapping("/myRecipe/view")
	public String myPage(HttpSession session, Model model) {

		Object userIdObj = session.getAttribute("userId");
		long userId = (long) userIdObj;
		model.addAttribute("userId", userId);

		Object loginIdObj = session.getAttribute("loginId");
		String loginId = (String) loginIdObj;
		model.addAttribute("loginId", loginId);

		List<PostDto> postDtoList = postService.getPostList(userId); 
		model.addAttribute("postDtoList", postDtoList);

		return "post/mypage";
	}

	@GetMapping("/recipes/view")
	public String recipes(@RequestParam("id") Long id, Model model, HttpSession session) {

		long userId = (long) session.getAttribute("userId");

		PostDto postDto = postService.getPostById(id, userId);

		model.addAttribute("postDto", postDto);
		model.addAttribute("userId", userId);

		return "post/recipes";
	}
	
	@GetMapping("/ranking/view")
	public String ranking(Model model) {

		List<PostDto> postDtoList = postService.getPostList(); 
		model.addAttribute("postDtoList", postDtoList);
		return "post/ranking";

	}
	
	@GetMapping("/create/view")
	public String inputPost(HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/user/login/view";
		}
		return "post/create";
	}
	
	@GetMapping("/update/view")
	public String updateView(@RequestParam("id") long id, HttpSession session, Model model) {

		PostDto postDto = postService.getPostById(id);
		model.addAttribute("postDto", postDto);
		return "post/edit";
	}

}
