package com.ubs.opsit.interviews;

public class Utils {

	public static boolean isStringNullOrEmpty(String str) {
		
		if(null == str || str.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
}
