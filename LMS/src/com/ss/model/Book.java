package com.ss.model;

import java.util.ArrayList;
import java.util.List;

public class Book {

	private String title;
	private String publisher;
	private String author;
	private List<Book> books;

	public Book() {
		this.books = new ArrayList<Book>();
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String isbn) {
		this.publisher = isbn;
	}
}
