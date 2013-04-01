package com.alibaba.webx.tutorial1.guoba;

import java.util.HashMap;
import java.util.Map;

public class CityService {

	private static Map<String, String> cityMapI = new HashMap<String, String>();
	private static Map<String, String> cityMapII = new HashMap<String, String>();
	static{
		cityMapI.put("2595", "hangzhou");
		cityMapI.put("1079", "wuhan");
		cityMapI.put("2079", "shanghai");
		
		cityMapII.put("hangzhou", "2595");
		cityMapII.put("wuhan", "1079");
		cityMapII.put("shanghai", "2079");
		
	}
	
	public String getPyByCityId(String cityId){
		return cityMapI.get(cityId) == null ? cityMapI.get("2595") : cityMapI.get(cityId);
	}
	
	public String getCityIdByPy(String pinying){
		return cityMapII.get(pinying) == null ? cityMapII.get("hangzhou") : cityMapII.get(pinying);
	}
}
