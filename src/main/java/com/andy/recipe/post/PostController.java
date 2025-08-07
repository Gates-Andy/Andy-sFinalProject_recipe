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

	@GetMapping("/main/view")
	public String main(Model model) {

		List<PostDto> postDtoList = postService.getPostList(); // 모든 포스트 목록을 다가져와 view로

		model.addAttribute("postDtoList", postDtoList);

		return "post/main";

	}

	@GetMapping("/recipes/view")
	public String recipes(@RequestParam("id") Long id, Model model, HttpSession session) {

		Object userIdObj = session.getAttribute("userId");
		if (userIdObj == null) {
			return "redirect:/user/login/view";
		}
		long userId = ((Number) userIdObj).longValue();

		model.addAttribute("userId", userId);

		PostDto postDto = postService.getPostById(id, userId);

		model.addAttribute("postDto", postDto);

		return "post/recipes";
	}

	@GetMapping("/myRecipe/view")
	public String myPage(HttpSession session, Model model) {

		Object userIdObj = session.getAttribute("userId");
		long userId = (long) userIdObj;
		model.addAttribute("userId", userId);

		Object loginIdObj = session.getAttribute("loginId");
		String loginId = (String) loginIdObj;
		model.addAttribute("loginId", loginId);

		List<PostDto> postDtoList = postService.getPostList(userId); // 각 로그인 이용자의 모든 포스트 본인만 보이도록
		model.addAttribute("postDtoList", postDtoList);

		return "post/mypage";
	}

	@GetMapping("/update/view")
	public String updateView(@RequestParam("id") long id, HttpSession session, Model model) {

		Object userIdObj = session.getAttribute("userId");
		long userId = (long) userIdObj;
		model.addAttribute("userId", userId);

		Object loginIdObj = session.getAttribute("loginId");
		String loginId = (String) loginIdObj;
		model.addAttribute("loginId", loginId);

		PostDto PostDto = postService.getPostById(id, userId);
		model.addAttribute("PostDto", PostDto);

		return "post/edit";
	}

	@GetMapping("/create/view")
	public String inputPost(HttpSession session) {

		if (session.getAttribute("userId") == null) {
			return "redirect:/user/login/view";
		}

		return "post/create";
	}

}
