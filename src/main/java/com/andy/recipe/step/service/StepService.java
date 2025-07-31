package com.andy.recipe.step.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.andy.recipe.step.domain.Step;
import com.andy.recipe.step.repository.StepRepository;

@Service
public class StepService {

	private final StepRepository stepRepository;

	public StepService(StepRepository stepRepository) {
		this.stepRepository = stepRepository;
	}

	public boolean addStep(int postId, int stepNumber, String content, String imagePath) {

		Step step = new Step();
		step.setPostId(postId);
		step.setStepNumber(stepNumber);
		step.setContent(content);
		step.setImagePath(imagePath);
		step.setCreatedAt(LocalDateTime.now());
		step.setUpdatedAt(LocalDateTime.now());

		int result = stepRepository.insertStep(step);

		return result == 1;
	}
}
