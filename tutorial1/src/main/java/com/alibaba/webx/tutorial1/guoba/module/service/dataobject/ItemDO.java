/**
 * 
 */
package com.alibaba.webx.tutorial1.guoba.module.service.dataobject;

import java.math.BigDecimal;

/**
 * 描述：
 * @author feiyue
 * @date 2013-3-28
 */
public class ItemDO {

	private String title;
	
	private String price;
	
	private int type; //1:集市,2:天猫

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
