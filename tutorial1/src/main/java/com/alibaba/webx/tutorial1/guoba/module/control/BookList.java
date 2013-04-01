package com.alibaba.webx.tutorial1.guoba.module.control;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.webx.tutorial1.guoba.module.service.BookService;

public class BookList {

	@Autowired
	private BookService bookService;
	
	public void execute(Context context) {
        context.put("booklist", bookService.getBooks());
    }
}
