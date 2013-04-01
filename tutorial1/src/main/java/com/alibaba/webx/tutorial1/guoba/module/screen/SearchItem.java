/**
 * 
 */
package com.alibaba.webx.tutorial1.guoba.module.screen;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.webx.tutorial1.guoba.module.service.dataobject.ItemDO;
import com.alibaba.webx.tutorial1.guoba.module.service.dataobject.SearchResultDO;
import com.alibaba.webx.tutorial1.util.ConcurrentSearchService;
import com.alibaba.webx.tutorial1.util.SearchService;

/**
 * 描述：同步求天猫和集市宝贝
 * 
 * @author feiyue
 * @date 2013-3-28
 */
public class SearchItem {

	
	protected Logger log = LoggerFactory.getLogger(SearchItem.class);
	
	@Autowired
	private SearchService searchService;

	@Autowired
	private ConcurrentSearchService concurrentSearchService;
	
	private double visitCount = 0;
	
	private long beginVisitTime = 0L;
	
	public void execute(Context context, ParameterParser paramParser) {
		//0是同步,其他是并发
		int concurrent = paramParser.getInt("concurrent",0);
		
		if(concurrent > 0){
			concurrentSearch(context);
		}else{
			blockSearch(context);
		}
		
		qps();
		
	}

	
	private void qps(){
		if(beginVisitTime  == 0){
			beginVisitTime = System.currentTimeMillis();
		}
		log.debug("qps: "
				+ ((++visitCount)/((System.currentTimeMillis() - beginVisitTime)/1000L)));
		
//		if( (++visitCount) % 100 == 0){
//			log.debug("qps: "
//					+ ((visitCount)/((System.currentTimeMillis() - beginVisitTime)/1000L)));
//		}
		
	}
	
	private void concurrentSearch(Context context) {

		long begin = System.currentTimeMillis();

		List<ItemDO> items = new ArrayList<ItemDO>();

		SearchResultDO searchResultDO = concurrentSearchService.doSearch();

		items.addAll(searchResultDO.getTmallItems());

		items.addAll(searchResultDO.getC2cItems());

		context.put("items", items);

		//log.debug("concurrent times: "
		//		+ (System.currentTimeMillis() - begin));
	}

	private void blockSearch(Context context) {

		long begin = System.currentTimeMillis();

		List<ItemDO> items = new ArrayList<ItemDO>();

		SearchResultDO searchResultDO = searchService.doSearch();

		items.addAll(searchResultDO.getTmallItems());

		items.addAll(searchResultDO.getC2cItems());

		context.put("items", items);

		log.debug("block times: "
				+ (System.currentTimeMillis() - begin));
	}
	
	public static void main(String[] args) throws InterruptedException {
		double visitCount = 1;
		
		long begin = System.currentTimeMillis();
		
		Thread.currentThread().sleep(1000);
		
		for(int i=1; i<1000; i++){
			System.out.println((++visitCount)/(System.currentTimeMillis() - begin)/1000L);
		}
	}
}
