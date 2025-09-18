package com.andy.recipe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.andy.recipe.common.FileManager;

@Configuration
public class WebmvcConfig implements WebMvcConfigurer {
	//1. Spring MVC에서 서버 로컬에 저장된 이미지 파일을 웹에서 접근 가능하게 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")

				.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/");
				// file:///D:\\ANDY_GATES\\WEB\\20250220\\springProject\\upload/
	}

}
