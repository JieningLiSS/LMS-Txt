package com.ss.main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.ss.dao.AuthorDao;
import com.ss.dao.BookDao;
import com.ss.dao.PublisherDao;
import com.ss.service.AuthorService;
import com.ss.service.BookService;
import com.ss.service.PublisherService;

public class Controller {

	public static void gotoUpperMenu(int input) throws IOException {
		System.out.println("Would you like to go back to upper menu? Y/N");
		Scanner sc = new Scanner(System.in);
		String gobackInput = sc.nextLine();
		if ("Y".equalsIgnoreCase(gobackInput)) {
			Main.userCall(input);
		} else {
			System.out.println("Exiting the system...bye");
		}

	}

	public void authorFunc(int action) throws IOException {
		// TODO Auto-generated method stub
		AuthorService authorService = new AuthorService();
		if (action == 1) {
			authorService.addAuthors();
			System.out.println("Add author completed!");

		} else if (action == 2) {
			authorService.deleteAuthors();
			System.out.println("Delete author completed!");

		} else if (action == 3) {
			authorService.updateAuthors();
			System.out.println("Update authors completed!");

		} else {
			authorService.readAuthors();
			System.out.println("Get authors completed!");

		}
		gotoUpperMenu(1);

	}

	public void publisherFunc(int action) throws IOException {
		// TODO Auto-generated method stub
		PublisherService publisherService = new PublisherService();

		if (action == 1) {
			publisherService.addPublishers();
			System.out.println("Add publisher completed!");

		} else if (action == 2) {
			publisherService.deletePublishers();
			System.out.println("Delete publisher completed!");

		} else if (action == 3) {
			publisherService.updatePublishers();
			System.out.println("Update publisher completed!");

		} else {
			publisherService.readPublishers();
			System.out.println("Get publishers completed!");

		}
		gotoUpperMenu(2);

	}

	public void bookFunc(int action) throws IOException {
		// TODO Auto-generated method stub

		BookService bookService = new BookService();

		if (action == 1) {
			bookService.addBooks();
			System.out.println("Add new book completed!");

		} else if (action == 2) {
			bookService.deleteBooks();
			System.out.println("Delete book completed!");

		} else if (action == 3) {
			bookService.updateBooks();
			System.out.println("Update book completed!");

		} else {
			bookService.readBooks();
			System.out.println("Get book completed!");

		}

		gotoUpperMenu(3);

	}

}
