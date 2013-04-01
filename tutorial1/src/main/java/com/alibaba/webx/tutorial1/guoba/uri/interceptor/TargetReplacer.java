package com.alibaba.webx.tutorial1.guoba.uri.interceptor;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.uribroker.interceptor.URIBrokerInterceptor;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineURIBroker;
import com.alibaba.citrus.util.StringEscapeUtil;

/**
 * 
 * @description: book detail 动转静
 * @company: 淘宝网
 * @author: feiyue (feiyue@taobao.com)
 * @date: 2011-12-22
 */
public class TargetReplacer implements URIBrokerInterceptor {

	
	@Autowired
	private HttpServletRequest request;
	
	
	private Map<String, String> serverURI;
	
	private static Pattern kbregx = Pattern.compile("^.*\\.koubei\\.\\w+$");
	private static Pattern tbregx = Pattern.compile("^.*\\.taobao\\.\\w+$");
	
	public void perform(URIBroker arg0) {
		// TODO Auto-generated method stub
		
		TurbineURIBroker tBroker = (TurbineURIBroker)arg0;
		
		
		String bookId =  (String)tBroker.getQuery().get("bookId");
		
		tBroker.setServerURI(getServerName(request));
		
		if(checkQueryParams(tBroker)){
			return ;
		}
		
        StringBuffer str = new StringBuffer("book");

        str.append("--").append("Id-").append(bookId).append("/");
        //重新设置TARGET
        tBroker.setConvertTargetCase(false); //不对路径进行大小字进行转换
        tBroker.removeQueryData("bookId");
        tBroker.setTarget(str.toString());
        tBroker.setComponentPath("");
        
       
   
 
       
        

	}
	
	
	private boolean checkQueryParams(URIBroker broker){
		Map<String, Object/* String or String[] */> paramsMap = broker.getQuery();
		if(paramsMap == null){
			return false;
		}
		for(Map.Entry<String, Object> entry : paramsMap.entrySet()){
			if(entry.getValue() instanceof String){
				if(containsSpecialChar((String)entry.getValue())){
					return true;
				}
			}
			if(entry.getValue() instanceof String[]){
				String[] parmasArray = (String[])entry.getValue();
				if(parmasArray != null && parmasArray.length > 0){
					for(String param : parmasArray){
						if(containsSpecialChar(param)){
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	
	private static boolean containsSpecialChar(String str){
		return (str.indexOf(" ") > -1 || str.indexOf("/") > -1 || str.indexOf("\\") > -1) ? true : false;
	}
	
	private String getServerName(HttpServletRequest request){
		
		if(kbregx.matcher(request.getServerName()).matches()){
			return serverURI.get("koubei");
		}
		
		return serverURI.get("taobao");
		
	}

	public void setServerURI(Map<String, String> serverURI) {
		this.serverURI = serverURI;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println("test");
//		Pattern kbDomain = Pattern.compile("^.*\\.koubei\\.\\w+$");
//		Matcher matcher = kbDomain.matcher("www.koubei.taobao.com");
//		System.out.println(matcher.matches());
		//System.out.println(StringEscapeUtil.escapeURL(" ","GBK"));
		System.out.println("aaabb\\".matches(".*[ \\/].*"));
		System.out.println("aaabb\\".matches(".*[ \\/].*"));
		
	}
	
	

}
