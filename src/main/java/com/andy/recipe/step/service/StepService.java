package com.andy.recipe.step.service;

import java.time.LocalDateTime;
import java.util.List;

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
	
	public List<Step> getStepsByPostId(long postId) {
		
        return stepRepository.selectByPostId(postId); // postid에 저장되어있는 모든 정보를 가져와 그대로 가져가
        
    }
}
