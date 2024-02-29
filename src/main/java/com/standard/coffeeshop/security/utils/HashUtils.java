package com.standard.coffeeshop.security.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;


public class HashUtils {
	private static final Logger log= LoggerFactory.getLogger(HashUtils.class);

	public HashUtils() {
	}

	public static StringBuilder hashString(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.reset();
			byte[] buffer = input.getBytes("UTF-8");
			md.update(buffer);
			byte[] digest = md.digest();
	
			StringBuilder hexStr = new StringBuilder("");
			for (int i = 0; i < digest.length; i++) {
				hexStr.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
			}
			return hexStr;
		}
		catch (Exception e) {
			log.error("error in hashString", e);
			return null;
		}
	}


}