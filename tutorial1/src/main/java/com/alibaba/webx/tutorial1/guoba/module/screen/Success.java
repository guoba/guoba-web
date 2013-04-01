package com.alibaba.webx.tutorial1.guoba.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;

public class Success {

	 public void execute(@Param("info") String info, Context context) {
	        context.put("info", info);
	    }
}
