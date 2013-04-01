package com.alibaba.webx.tutorial1.guoba.module.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.webx.tutorial1.guoba.module.service.BookService;

public class BookDetail {

	@Autowired
	 private BookService bookService;
	
	 public void execute(@Param("bookId") String bookId, Context context) {
		
	     if(bookId != null){
	    	 com.alibaba.webx.tutorial1.guoba.module.Book book =  bookService.getBookById(bookId);
	    	 context.put("book", book);
	     }
		
		 
	 }
}
