package com.alibaba.webx.tutorial1.guoba.module.action;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.webx.tutorial1.guoba.module.Book;
import com.alibaba.webx.tutorial1.guoba.module.service.BookService;

public class BookAction {

	@Autowired
	private BookService bookService;
	
	@Autowired
	protected URIBrokerService urlBrokerService;
	
	public void doInsert(@FormGroup("book") Book book,
			Navigator nav,Context context,ParameterParser paramParser){
		
		

		if(book.getPrice() == null){
			context.put("errorMsg", "价格错误");
			return;
		}
		
		bookService.insertBook(book);
//		if(book.getPrice() == null){
//			context.put("errorMsg", "价格错误");
//			return;
//		}
		
		System.out.println("---------- 1 -----------");
		
		
		nav.redirectTo("guobaLink").withTarget("success").uri()
		.addQueryData("info", "增加成功").setReference("aaa");
		
		System.out.println("---------- 2 -----------");
		goErrorPage(nav);
	}
	
	
	protected void goErrorPage(Navigator nav) {
//		ContentURIBroker homeServer = (ContentURIBroker)urlBrokerService.getURIBroker("homeServer").fork();
//		nav.redirectToLocation(homeServer.getURI("/book.htm").render());
		//nav.redirectTo("guobaLink").withTarget("book");
	}
	
	public void doDelete(@Param("bookId") String bookId,Navigator nav){
		bookService.deleteBookById(bookId);
		nav.redirectTo("guobaLink").withTarget("success")
		.withParameter("info", "删除成功");
	}
	
	public void doUpdate(@FormGroup("book") Book book,ParameterParser paramParser,Context context,
			Navigator nav){
		String bookId = paramParser.getString("bookId");
		if(StringUtils.isEmpty(bookId)){
			context.put("errorMsg", "书籍id为空");
			return;
		}
		if(book.getPrice() == null){
			context.put("errorMsg", "价格错误");
			return;
		}
		
		book.setBookId(bookId);
		bookService.updateBook(book);
		nav.redirectTo("guobaLink").withTarget("success")
		.withParameter("info", "修改成功");
	}
}
