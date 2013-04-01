/**
 * 
 */
package com.alibaba.webx.tutorial1.guoba.module.service.dataobject;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述： 搜索返回对象
 * @author feiyue
 * @date 2013-3-28
 */
public class SearchResultDO {

	//天猫宝贝
	private List<ItemDO> tmallItems = new ArrayList<ItemDO>();
	
	//集市宝贝
	private List<ItemDO> c2cItems = new ArrayList<ItemDO>();

	public List<ItemDO> getTmallItems() {
		return tmallItems;
	}

	public void setTmallItems(List<ItemDO> tmallItems) {
		this.tmallItems = tmallItems;
	}

	public List<ItemDO> getC2cItems() {
		return c2cItems;
	}

	public void setC2cItems(List<ItemDO> c2cItems) {
		this.c2cItems = c2cItems;
	} 
	
	
	
	
}
