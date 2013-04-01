package com.alibaba.webx.tutorial1.guoba.module.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.webx.tutorial1.guoba.CityService;

public class SearchBookList {

	 @Autowired
	 private CityService cityService;
	
	 public void execute(@Param("city")String city,@Param("category")String category,
			 				@Param("subcates")String subcates, Context context) {
		 context.put("city", cityService.getCityIdByPy(city));
		 context.put("category", category);
		 context.put("subcates", subcates);
		 
	 }
}
