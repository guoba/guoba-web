package com.alibaba.webx.tutorial1.guoba.module.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.webx.tutorial1.guoba.module.Book;

public class BookService {

	static Map<String, Book> books = new HashMap<String, Book>();
	
	public void insertBook(Book book){
		book.setBookId(UUID.randomUUID().toString().replaceAll("-", ""));
		books.put(book.getBookId(), book);
	}
	
	public Collection<Book> getBooks(){
		return books.values();
	}
	
	public void deleteBookById(String bookId){
		 books.remove(bookId);
	}
	
	public Book getBookById(String bookId){
		return books.get(bookId);
	}
	
	public void updateBook(Book book){
		books.put(book.getBookId(), book);
	}
}
