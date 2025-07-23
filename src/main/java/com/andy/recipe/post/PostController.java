package com.andy.recipe.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.andy.recipe.post.domain.Post;
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

		List<Post> postList = postService.getPostList();

		model.addAttribute("postList", postList);

		return "post/main";
	}

	@GetMapping("/myRecipe/view")
	public String myPage(HttpSession session, Model model) {

		Object userIdObj = session.getAttribute("userId");
		Object loginIdObj = session.getAttribute("loginId");

		long userId = (long) userIdObj;
		String loginId = (String) loginIdObj;

		List<Post> postList = postService.getPostListByUserId(userId);

		model.addAttribute("postList", postList);

		model.addAttribute("userId", userId);

		model.addAttribute("loginId", loginId);

		return "post/mypage";
	}
	
	@GetMapping("/create/view")
	public String inputPost(HttpSession session) {

		if (session.getAttribute("userId") == null) {
			return "redirect:/user/login/view";
		}

		return "post/create";
	}
}
