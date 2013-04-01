package com.alibaba.webx.tutorial1.guoba.uri.interceptor;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.uribroker.interceptor.URIBrokerInterceptor;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineURIBroker;
import com.alibaba.webx.tutorial1.guoba.CityService;

/**
 * 
 * @description: search detail list
 * @company: 淘宝网
 * @author: feiyue (feiyue@taobao.com)
 * @date: 2011-12-22
 */
public class SearchTargetReplacer implements URIBrokerInterceptor {
	
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private HttpServletRequest request;
	
	private Map<String, String> serverURI;
	
	private static Pattern kbregx = Pattern.compile("^.*\\.koubei\\.\\w+$");
	private static Pattern tbregx = Pattern.compile("^.*\\.taobao\\.\\w+$");

	public void perform(URIBroker arg0) {
		// TODO Auto-generated method stub
		
		TurbineURIBroker tBroker = (TurbineURIBroker)arg0;
		tBroker.setServerURI(getServerName(request));
		tBroker.setComponentPath("");
		
		String city =  (String)tBroker.getQuery().get("city");
		String category = (String)tBroker.getQuery().get("category");
		String subcates = (String)tBroker.getQuery().get("subcates");
	
      
        StringBuffer str = new StringBuffer(cityService.getPyByCityId(city));
        str.append("/searchbooks--category-").append(category).append("--subcates").append("-").append(subcates).append("/");
        //重新设置TARGET
        tBroker.setConvertTargetCase(false); //不对路径进行大小字进行转换
        tBroker.removeQueryData("city");
        tBroker.removeQueryData("category");
        tBroker.removeQueryData("subcates");
        tBroker.setTarget(str.toString());
        
     
//        System.out.println(tBroker.get);
		
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
	
}
