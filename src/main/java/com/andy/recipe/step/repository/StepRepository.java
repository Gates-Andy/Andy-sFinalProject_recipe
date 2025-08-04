package com.andy.recipe.step.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.andy.recipe.step.domain.Step;

@Mapper
public interface StepRepository {
	int insertStep(Step step);
	
	List<Step> selectByPostId(long postId); // postid에 저장되어있는 모든 정보를 가져와
}
