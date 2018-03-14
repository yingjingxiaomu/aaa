package com.jk.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * Copyright © 2017 金科教育. All rights reserved. <br>
 * 类: Md5Util <br>
 * 描述: MD5加密 <br>
 * 作者: Teacher song<br>
 * 时间: 2017年6月27日 下午2:27:06
 */
public class Md5Util {

	public static String get32(String check){



		char [] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'A', 'B', 'C', 'D', 'E', 'F','a','b','c','d','e','f'};
		final int temp = 0xf;
		final int arraySize = 32;
		final int strLen = 16;
		final int offset = 4;
		try {
			MessageDigest md = MessageDigest
					.getInstance("MD5");
			md.update(check.getBytes());
			byte [] tmp = md.digest();
			char [] str = new char[arraySize];
			int k = 0;
			for (int i = 0; i < strLen; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> offset & temp];
				str[k++] = hexDigits[byte0 & temp];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}




	/**
	 * 
	 * 方法: getMd532 <br>
	 * 描述: 32位md5加密 <br>
	 * 作者: Teacher song<br>
	 * 时间: 2017年6月27日 下午2:27:38
	 * @param plainText
	 * @return
	 */
	public static String getMd532(String plainText) {  
	    try {  
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        md.update(plainText.getBytes());  
	        byte b[] = md.digest();  
	        int i;
	        StringBuffer buf = new StringBuffer("");  
	        for (int offset = 0; offset < b.length; offset++) {  
	            i = b[offset];  
	            if (i < 0)  
	                i += 256;  
	            if (i < 16)  
	                buf.append("0");  
	            buf.append(Integer.toHexString(i));  
	        }
	        //32位加密  
	        return buf.toString().toLowerCase();
	        // 16位的加密  
	        //return buf.toString().substring(8, 24);  
	    } catch (NoSuchAlgorithmException e) {  
	        e.printStackTrace();  
	        return null;  
	    }  
	}  
	/**
	 * 
	 * 方法: getMd516 <br>
	 * 描述: 16位MD5加密 <br>
	 * 作者: Teacher song<br>
	 * 时间: 2017年6月27日 下午2:27:52
	 * @param plainText
	 * @return
	 */
	public static String getMd516(String plainText) {  
	    try {  
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        md.update(plainText.getBytes());  
	        byte b[] = md.digest();  
	        int i;  
	        StringBuffer buf = new StringBuffer("");  
	        for (int offset = 0; offset < b.length; offset++) {  
	            i = b[offset];  
	            if (i < 0)  
	                i += 256;  
	            if (i < 16)  
	                buf.append("0");  
	            buf.append(Integer.toHexString(i));  
	        }  
	        //32位加密  
	       // return buf.toString();  
	        // 16位的加密  
	        return buf.toString().substring(8, 24);  
	    } catch (NoSuchAlgorithmException e) {  
	        e.printStackTrace();  
	        return null;  
	    }  
	}
}
