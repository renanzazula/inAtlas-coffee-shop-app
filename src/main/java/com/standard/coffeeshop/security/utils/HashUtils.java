package com.standard.coffeeshop.security.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;


public class HashUtils {
	private static final Logger log= LoggerFactory.getLogger(HashUtils.class);
	public static String hashString(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.reset();
			byte[] buffer = input.getBytes("UTF-8");
			md.update(buffer);
			byte[] digest = md.digest();
	
			String hexStr = "";
			for (int i = 0; i < digest.length; i++) {
				hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
			}
			return hexStr;
		}
		catch (Exception e) {
			log.error("error in hashString", e);
			return null;
		}
	}


}