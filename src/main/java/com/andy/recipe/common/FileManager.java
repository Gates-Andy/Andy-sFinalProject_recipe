package com.andy.recipe.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {//파일이 저장될 기본 경로를 상수로 정의함
	// public static final String FILE_UPLOAD_PATH = "D:\\ANDY_GATES\\WEB\\20250220\\springProject\\upload";
	// public static final String FILE_UPLOAD_PATH = "C:\\Users\\mujik\\OneDrive\\바탕 화면\\project\\upload";
	public static final String FILE_UPLOAD_PATH = "/home/ec2-user/upload/images"; // 배포시
	

	public static String saveFile(long userId, MultipartFile file) {

		if (file == null) {
			return null;
		}

		String directoryName = "/" + userId + "_" + System.currentTimeMillis(); // 1(userId)_(hyphen)1752051868117(currentTimeMillis)

		String directoryPath = FILE_UPLOAD_PATH + directoryName;

		File directory = new File(directoryPath); // 객체 생성 directoryPath

		if (!directory.mkdir()) { // 해당 경로에 폴더 생성
			return null;
		}
		;

		String filePath = directoryPath + "/" + file.getOriginalFilename(); // 최종 저장될 파일의 경로 구성

		try {
			byte[] bytes = file.getBytes(); // 파일 데이터를 byte 배열로 추출

			Path path = Paths.get(filePath); // 저장할 경로 객체 생성

			Files.write(path, bytes); // 실제로 파일 저장

		} catch (IOException e) {

			e.printStackTrace();

			return null;
		}

		return "/images" + directoryName + "/" + file.getOriginalFilename();
		// 이게 리턴되서 저장이 될건데... /images/13_1753431686624/3.jpg
	}
}
