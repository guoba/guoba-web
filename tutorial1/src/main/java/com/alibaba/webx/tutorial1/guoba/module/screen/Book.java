package com.alibaba.webx.tutorial1.guoba.module.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.webx.tutorial1.guoba.module.service.BookService;

public class Book {

	 @Autowired
	 private BookService bookService;
	
	 public void execute(@Param("bookId") String bookId, Context context,ParameterParser paramParser) {
		 com.alibaba.webx.tutorial1.guoba.module.Book actionBook =  (com.alibaba.webx.tutorial1.guoba.module.Book)context.get("book");
		 
		 
	     if(bookId != null){
	    	 com.alibaba.webx.tutorial1.guoba.module.Book book =  bookService.getBookById(bookId);
	    	 System.out.println(book.getBookId());
	    	 context.put("book", book);
	    	 context.put("bookId", book.getBookId());
	     }
	     
	     System.out.println("paramParser testId:" + paramParser.getString("testId"));
		 System.out.println("context.get('errorMsg')"+context.get("errorMsg"));
		
		 
	 }
}
