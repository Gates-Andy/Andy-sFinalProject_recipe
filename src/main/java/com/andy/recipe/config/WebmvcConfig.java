package com.andy.recipe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.andy.recipe.common.FileManager;

@Configuration
public class WebmvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")

				// .addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/");
				.addResourceLocations("file://" + FileManager.FILE_UPLOAD_PATH + "/"); // 배포시
				// file:///D:\\ANDY_GATES\\WEB\\20250220\\springProject\\upload/
	}

}
