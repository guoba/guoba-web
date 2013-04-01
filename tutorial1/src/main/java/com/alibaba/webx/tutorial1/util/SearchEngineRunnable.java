/**
 * 
 */
package com.alibaba.webx.tutorial1.util;

import java.util.concurrent.CountDownLatch;

import com.alibaba.webx.tutorial1.guoba.module.service.dataobject.SearchResultDO;

/**
 * 描述：
 * @author feiyue
 * @date 2013-3-28
 */
public class SearchEngineRunnable implements Runnable {

	
	private int actionType;
	
	private CountDownLatch cd;
	
	private SearchService searchService;
	
	private SearchResultDO searchResultDO;
	
	public SearchEngineRunnable(int actionType,CountDownLatch cd,SearchService searchService,SearchResultDO searchResultDO){
		this.actionType = actionType;
		this.cd = cd;
		this.searchService = searchService;
		this.searchResultDO = searchResultDO;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		blockHandle();
		
		cd.countDown();
	}
	
	
	private void blockHandle(){
		if(actionType == 1){
			searchResultDO.setC2cItems(searchService.c2cSearch());
		}else{
			searchResultDO.setTmallItems(searchService.tmallSearch());
		}
	}
	
	
	
	public void threadErrorHandle() {
		// TODO Auto-generated method stub
		blockHandle();
		cd.countDown();
	}
	
	
	
}
