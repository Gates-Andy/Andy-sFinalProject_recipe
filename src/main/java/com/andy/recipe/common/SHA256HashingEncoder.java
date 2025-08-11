package com.andy.recipe.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256HashingEncoder {
	public static String encode(String message) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

			byte[] bytes = message.getBytes();

			messageDigest.update(bytes);

			byte[] digest = messageDigest.digest();

			StringBuilder result = new StringBuilder();

			for (byte b : digest) {
				result.append(String.format("%02x", b));
			}

			return result.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
