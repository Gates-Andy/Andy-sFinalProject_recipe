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

		List<PostDto> postDtoList = postService.getPostList();

		model.addAttribute("postDtoList", postDtoList);

		return "post/main";
	}

	@GetMapping("/recipes/view")
	public String recipes(@RequestParam("id") Long id, Model model) {
		
		PostDto postDto = postService.getPostById(id);
	    
	    model.addAttribute("postDto", postDto);
	    
	    return "post/recipes";
	    
	}

	@GetMapping("/myRecipe/view")
	public String myPage(HttpSession session, Model model) {

		Object userIdObj = session.getAttribute("userId");
		Object loginIdObj = session.getAttribute("loginId");

		long userId = (long) userIdObj;
		String loginId = (String) loginIdObj;

		 List<PostDto> postDtoList = postService.getPostList(userId); // 다른 로그인 아이디로 보이니까

		model.addAttribute("postDtoList", postDtoList);

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
