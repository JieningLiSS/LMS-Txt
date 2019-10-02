package com.ss.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import com.ss.dao.AuthorDao;
import com.ss.dao.BookDao;
import com.ss.dao.PublisherDao;
import com.ss.model.Author;
import com.ss.model.Book;
import com.ss.model.Publisher;

public class BookService {

	public void readBooks() throws IOException {
		// TODO Auto-generated method stub
		BookDao bookDao = new BookDao();
		Map<String, List<Book>> bookMap = bookDao.readBookMap();
		for (Entry<String, List<Book>> e : bookMap.entrySet()) {
			System.out.println(e.getKey());
		}

	}

	public void addBooks() throws IOException {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		System.out.println("You will need to enter author, book and publisher name.");

		System.out.println("Please enter book name:");
		String bookName = sc.nextLine();

		System.out.println("please enter author name:");
		String authorNamne = sc.nextLine();

		AuthorDao authorDao = new AuthorDao();
		Map<String, String> authorMap = authorDao.readAllAuthors();

		authorMap.entrySet().forEach(entry -> {
			if (!authorMap.containsValue(authorNamne)) {
				System.out.println("Author name doesn't exist, please enter again!");
				try {
					addBooks();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		System.out.println("Please enter publisher name:");
		String publisherName = sc.nextLine();

		PublisherDao publisherDao = new PublisherDao();
		Map<String, String> publisherMap = publisherDao.readAllPublishers();

		publisherMap.entrySet().forEach(entry -> {
			if (!publisherMap.containsValue(publisherName)) {
				System.out.println("Publisher name doesn't exist, please enter again!");
				try {
					addBooks();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		BookDao bookDao = new BookDao();
		Map<String, List<Book>> bookMap = bookDao.readBookMap();

		List<Book> books = new ArrayList<>();
		Book book = new Book();
		book.setAuthor(authorNamne);
		book.setPublisher(publisherName);
		books.add(book);
		bookMap.put(bookName, books);
		bookDao.writeBookBack(bookMap);

	}

	public void updateBooks() throws IOException {
		// TODO Auto-generated method stub

		System.out.println("Which book do you want to update?");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		System.out.println("Please enter new book name:");
		String newInput = sc.nextLine();
		BookDao bookDao = new BookDao();
		Map<String, List<Book>> bookMap = bookDao.readBookMap();
		Iterator iterator = bookMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			if (input.equals(key)) {
				List<Book> keyValue = bookMap.get(key);
				bookMap.remove(key);
				bookMap.put(newInput, keyValue);
				break;

			}
		}
		bookDao.writeBookBack(bookMap);
	}

	public static void deleteBooks() throws IOException {
		// TODO Auto-generated method stub
		BookDao bookDao = new BookDao();
		System.out.println("Please enter book name:");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		Map<String, List<Book>> bookMap = bookDao.readBookMap();
		Iterator iterator = bookMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			if (input.equals(key)) {
				bookMap.remove(key);
				break;
			}
		}

		bookDao.writeBookBack(bookMap);

	}

}
