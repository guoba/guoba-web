package com.alibaba.webx.tutorial1.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.webx.tutorial1.guoba.module.service.dataobject.SearchResultDO;
import com.alibaba.webx.tutorial1.util.SearchEngineRunnable;

public class ConcurrentSearchService implements InitializingBean {

	protected Logger log = LoggerFactory
			.getLogger(ConcurrentSearchService.class);

	private SearchService searchService;

	private ExecutorService executorService;

	/**
	 * 线程池的核心线程数量
	 */
	private int coreThreadCount;
	/**
	 * 线程池的最大线程数量
	 */
	private int maxThreadCount;

	/**
	 * 线程池中空闲线程的存活时间，单位秒
	 */
	private long aliveTime;

	private int ACTIONS = 2;

	public SearchResultDO doSearch() {

		SearchResultDO searchResultDO = new SearchResultDO();
		CountDownLatch cd = new CountDownLatch(2);

		for (int i = 1; i <= ACTIONS; i++) {
			executeRunnable(i, cd, searchService, searchResultDO);
		}

		try {
			cd.await();
		} catch (InterruptedException ie) {
			// 其实不太可能被中断.
			throw new RuntimeException(ie);
		}

		return searchResultDO;
	}

	private void executeRunnable(int actionType, CountDownLatch cd,
			SearchService searchService, SearchResultDO searchResultDO) {
		SearchEngineRunnable searchEngineRunnable = new SearchEngineRunnable(
				actionType, cd, searchService, searchResultDO);
		try {

			executorService.execute(searchEngineRunnable);

		} catch (RejectedExecutionException exception) { //
			// TODO: handle exception
			searchEngineRunnable.threadErrorHandle();
			log.error("threadErrorHandle");
		}
	}

	public SearchService getSearchService() {

		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if (this.coreThreadCount == 0) {
			this.coreThreadCount = 50;
		}
		if (this.maxThreadCount == 0) {
			this.maxThreadCount = 300;
		}
		if (this.aliveTime == 0L) {
			this.aliveTime = 30L;
		}
		executorService = new ThreadPoolExecutor(this.coreThreadCount,
				this.maxThreadCount, this.aliveTime, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());
	}

}
