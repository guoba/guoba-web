/**
 * 
 */
package com.alibaba.webx.tutorial1.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.webx.tutorial1.guoba.module.service.dataobject.ItemDO;
import com.alibaba.webx.tutorial1.guoba.module.service.dataobject.SearchResultDO;

/**
 * 
 * 描述：
 * @author feiyue
 * @date 2013-3-28
 */
public class SearchService {

	
	public SearchResultDO doSearch(){
		SearchResultDO searchResultDO = new SearchResultDO();
		searchResultDO.setTmallItems(tmallSearch());
		searchResultDO.setC2cItems(c2cSearch());
		
		return searchResultDO;
	}
	
	
	public List<ItemDO> c2cSearch(){
		
		List<ItemDO> result = new ArrayList<ItemDO>();
		
		ItemDO item1 = new ItemDO();
		item1.setTitle("集市宝贝1");
		item1.setPrice("12.0");
		item1.setType(1);
		
		ItemDO item2 = new ItemDO();
		item2.setTitle("集市宝贝2");
		item2.setPrice("13.0");
		item2.setType(1);
		
		ItemDO item3 = new ItemDO();
		item3.setTitle("集市宝贝3");
		item3.setPrice("14.0");
		item3.setType(1);
		
		result.add(item1);
		result.add(item2);
		result.add(item3);
		
		try {
			Thread.currentThread().sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	public List<ItemDO> tmallSearch(){
		
		List<ItemDO> result = new ArrayList<ItemDO>();
		
		ItemDO item1 = new ItemDO();
		item1.setTitle("天猫宝贝1");
		item1.setPrice("12.0");
		item1.setType(2);
		
		ItemDO item2 = new ItemDO();
		item2.setTitle("天猫宝贝2");
		item2.setPrice("13.0");
		item2.setType(2);
		
		ItemDO item3 = new ItemDO();
		item3.setTitle("天猫宝贝3");
		item3.setPrice("14.0");
		item3.setType(2);
		
	
		
		result.add(item1);
		result.add(item2);
		result.add(item3);
		
		try {
			Thread.currentThread().sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}
