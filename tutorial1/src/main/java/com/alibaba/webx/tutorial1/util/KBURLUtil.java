package com.alibaba.webx.tutorial1.util;

import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class KBURLUtil {

	private static Pattern kbregx = Pattern.compile("^.*\\.koubei\\.\\w+$");
	
	public static boolean isKoubeiUri(HttpServletRequest request){
		try {
			if(kbregx.matcher(request.getServerName()).matches()){
				return true;
			}
		} catch (Exception e) {
			
		}
		
		return false;
	}
	
	public static String getGUID(){
		return UUID.randomUUID().toString();
	}
	
	public static void main(String[] args) {
		System.out.println(kbregx.matcher("www.koubei.net").matches());
	}
}
