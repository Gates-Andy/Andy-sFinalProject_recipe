package com.andy.recipe.step;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/step")
public class StepController {

	@GetMapping("/create/view")
	public String createView(@RequestParam("postId") int postId, HttpSession session, Model model) {

		if (session.getAttribute("userId") == null) {
			return "redirect:/user/login/view";
		}

		model.addAttribute("postId", postId);

		return "step/input";
	}
}