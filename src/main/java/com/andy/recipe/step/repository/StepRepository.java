package com.andy.recipe.step.repository;

import org.apache.ibatis.annotations.Mapper;

import com.andy.recipe.step.domain.Step;

@Mapper
public interface StepRepository {
	int insertStep(Step step);
}
