package com.ss.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import com.ss.dao.AuthorDao;
import com.ss.dao.BookDao;
import com.ss.model.Author;
import com.ss.model.Book;

public class AuthorService {

	public void readAuthors() throws IOException {
		AuthorDao authorDao = new AuthorDao();
		Map<String, String> authorMap = authorDao.readAllAuthors();
		authorMap.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + " " + entry.getValue());
		});

	}

	public void addAuthors() throws IOException {
		AuthorDao authorDao = new AuthorDao();
		Map<String, String> authors = authorDao.readAllAuthors();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter new author id:");
		String inputId = sc.nextLine();
		System.out.println("Please enter new author name:");
		String inputName = sc.nextLine();
		authors.put(inputId, inputName);
		authorDao.writeAuthorBack(authors);

	}

	public void updateAuthors() throws IOException {

		AuthorDao authorDao = new AuthorDao();
		System.out.println("Please enter author name:");
		Scanner sc = new Scanner(System.in);
		String authorInput = sc.nextLine();

		System.out.println("Please enter new author name:");
		String newAuthorInput = sc.nextLine();

		Map<String, String> authors = authorDao.readAllAuthors();

		authors.entrySet().forEach(entry -> {
			if (authorInput.equals(entry.getValue())) {
				authors.replace(entry.getKey(), authorInput, newAuthorInput);
			}
		});

		authorDao.writeAuthorBack(authors);

		BookDao bookDao = new BookDao();
		Map<String, List<Book>> authorBookMap = bookDao.readAuthorBook();

		Iterator it = authorBookMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry map = (Map.Entry) it.next();
			if (authorInput.equals(map.getKey())) {
				List<Book> newKey = (List<Book>) map.getValue();
				authorBookMap.remove(map.getKey());
				authorBookMap.put(newAuthorInput, newKey);
				break;
			}

		}

		bookDao.writeAuthorBookBack(authorBookMap);

	}

	public void deleteAuthors() throws IOException {

		System.out.println("Please enter author name:");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		AuthorDao authorDao = new AuthorDao();

		Map<String, String> authorMap = authorDao.readAllAuthors();
		authorMap.entrySet().removeIf(entry -> (input.equals(entry.getValue())));

		authorDao.writeAuthorBack(authorMap);

		BookDao bookDao = new BookDao();
		Map<String, List<Book>> authorBookMap = bookDao.readAuthorBook();

		authorBookMap.entrySet().removeIf(entry -> (input.equals(entry.getKey())));

		bookDao.writeAuthorBookBack(authorBookMap);

	}
}
