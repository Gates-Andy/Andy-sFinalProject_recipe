package com.andy.recipe.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.andy.recipe.user.domain.User;

@Mapper
public interface UserRepository {
	
	public int insertUser(@Param("loginId") String loginId, @Param("password") String password, @Param("email") String email);

	public int selectCountByloginId(@Param("loginId") String loginId);

	public int selectCountByemail(@Param("email") String email);
	
	public User selectUser(@Param("loginId") String loginId, @Param("password") String password);
	
}
