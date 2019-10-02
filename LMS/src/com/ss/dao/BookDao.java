package com.ss.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import com.ss.model.Author;
import com.ss.model.Book;
import com.ss.model.Publisher;

public class BookDao {

	public static BookDao myObj;

	/**
	 * Create private constructor
	 */
	public BookDao() {

	}

	/**
	 * Create a static method to get instance.
	 */
	public static BookDao getInstance() {
		if (myObj == null) {
			myObj = new BookDao();
		}
		return myObj;
	}

	public Map<String, List<Book>> readBookMap() throws IOException {
		FileInputStream fin = new FileInputStream("/Users/jiening/Desktop/book.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(fin));

		Map<String, List<Book>> bookMap = new HashMap<String, List<Book>>();
		String authorLine;

		while ((authorLine = buffReader.readLine()) != null) {
			String[] splitArray = authorLine.split(";");
			Book b = new Book();
			b.setAuthor(splitArray[0]);
			b.setPublisher(splitArray[2]);

			if (bookMap.containsKey(splitArray[1])) {
				bookMap.get(splitArray[1]).add(b);
			} else {
				List<Book> books = new ArrayList<>();
				books.add(b);
				bookMap.put(splitArray[1], books);
			}
		}
		buffReader.close();
		return bookMap;

	}

	public Map<String, List<Book>> readAuthorBook() throws IOException {
		FileInputStream fin = new FileInputStream("/Users/jiening/Desktop/book.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(fin));

		Map<String, List<Book>> authorBookMap = new HashMap<String, List<Book>>();
		String authorLine;

		while ((authorLine = buffReader.readLine()) != null) {
			String[] splitArray = authorLine.split(";");

			Book b = new Book();
			b.setTitle(splitArray[1]);
			b.setPublisher(splitArray[2]);

			if (authorBookMap.containsKey(splitArray[0])) {
				authorBookMap.get(splitArray[0]).add(b);
			} else {
				List<Book> books = new ArrayList<>();
				books.add(b);
				authorBookMap.put(splitArray[0], books);
			}
		}
		buffReader.close();
		return authorBookMap;

	}

	public Map<String, List<Book>> readPublisherBook() throws IOException {
		FileInputStream fin = new FileInputStream("/Users/jiening/Desktop/book.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(fin));

		Map<String, List<Book>> publisherBookMap = new HashMap<String, List<Book>>();
		String authorLine;

		while ((authorLine = buffReader.readLine()) != null) {
			String[] splitArray = authorLine.split(";");
			Book b = new Book();
			b.setAuthor(splitArray[0]);
			b.setTitle(splitArray[1]);

			if (publisherBookMap.containsKey(splitArray[2])) {
				publisherBookMap.get(splitArray[2]).add(b);
			} else {
				List<Book> books = new ArrayList<>();
				books.add(b);
				publisherBookMap.put(splitArray[2], books);
			}
		}
		buffReader.close();
		return publisherBookMap;

	}

	public void writeBookBack(Map<String, List<Book>> bookMap) throws IOException {
		// TODO Auto-generated method stub

		File f = new File("/Users/jiening/Desktop/book.txt");
		if (f.exists()) {
			f.delete();
		}
		FileWriter out = new FileWriter(f);

		for (Entry<String, List<Book>> e : bookMap.entrySet()) {
			for (Book e1 : e.getValue()) {
				String str = e1.getAuthor() + ";" + e.getKey() + ";" + e1.getPublisher();
				out.write(str);
				out.write("\n");
			}
		}
		out.close();

	}

	public void writeAuthorBookBack(Map<String, List<Book>> authorBookMap) throws IOException {

		File f = new File("/Users/jiening/Desktop/book.txt");
		if (f.exists()) {
			f.delete();
		}
		FileWriter out = new FileWriter(f);

		for (Entry<String, List<Book>> e : authorBookMap.entrySet()) {
			for (Book e1 : e.getValue()) {
				String str = e.getKey() + ";" + e1.getTitle() + ";" + e1.getPublisher();
				out.write(str);
				out.write("\n");

			}
		}

		out.close();

	}

	public void writePublisherBookBack(Map<String, List<Book>> publisherBookMap) throws IOException {
		// TODO Auto-generated method stub

		File f = new File("/Users/jiening/Desktop/book.txt");
		if (f.exists()) {
			f.delete();
		}
		FileWriter out = new FileWriter(f);

		for (Entry<String, List<Book>> e : publisherBookMap.entrySet()) {
			for (Book e1 : e.getValue()) {
				String str = e1.getAuthor() + ";" + e1.getTitle() + ";" + e.getKey();
				out.write(str);
				out.write("\n");

			}
		}

		out.close();

	}

}
