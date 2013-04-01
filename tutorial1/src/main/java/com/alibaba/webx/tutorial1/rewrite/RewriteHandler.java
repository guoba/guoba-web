package com.alibaba.webx.tutorial1.rewrite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.alibaba.citrus.service.requestcontext.rewrite.RewriteSubstitutionContext;
import com.alibaba.citrus.service.requestcontext.rewrite.RewriteSubstitutionHandler;

public class RewriteHandler implements RewriteSubstitutionHandler{

	
	public void postSubstitution(RewriteSubstitutionContext context) {
		// TODO Auto-generated method stub
		
		context.getParameters().clear();
		addContextParamsString("city","/(\\w+)/search",context);
		addContextParamsString("category","c-(\\w)",context);
		addContextParamsString("area","a-(\\w)",context);
		
		
		context.setPath("/guoba/search_book_list.htm");
		
		
	}
	
	
	private void addContextParamsString(String paramName,String regx,RewriteSubstitutionContext context){
		if(StringUtils.isEmpty(regx) || StringUtils.isEmpty(context.getPath()) || StringUtils.isEmpty(paramName)){
			return;
		}
		
		Pattern pattern =  Pattern.compile(regx);
		Matcher matcher = pattern.matcher(context.getPath());
		if(matcher.find()){
			context.getParameters().add(paramName, matcher.group(1));
		}
		
	}
	
	public static void main(String[] args) {
		
		
		String str = "--category-6--subcate-8--area-9";
		
		Pattern pattern =  Pattern.compile("category-(\\w)");
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()){
			System.out.println(matcher.group(1));
		}
	}	
}
