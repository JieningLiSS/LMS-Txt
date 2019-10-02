package com.ss.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ss.dao.AuthorDao;
import com.ss.dao.BookDao;
import com.ss.dao.PublisherDao;
import com.ss.model.Book;
import com.ss.model.Publisher;

public class PublisherService {

	public void readPublishers() throws IOException {
		PublisherDao publisherDao = new PublisherDao();
		Map<String, String> publisherMap = publisherDao.readAllPublishers();
		publisherMap.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + " " + entry.getValue());
		});
	}

	public void addPublishers() throws IOException {

		PublisherDao publisherDao = new PublisherDao();
		Map<String, String> publishers = publisherDao.readAllPublishers();

		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter new publisher id:");
		String inputId = sc.nextLine();
		System.out.println("Please enter new publisher name:");
		String inputName = sc.nextLine();
		publishers.put(inputId, inputName);
		publisherDao.writePublisherBack(publishers);

	}

	public void updatePublishers() throws IOException {
		// TODO Auto-generated method stub

		PublisherDao publisherDao = new PublisherDao();
		System.out.println("Please enter publisher name:");
		Scanner sc = new Scanner(System.in);
		String publisherInput = sc.nextLine();

		System.out.println("Please enter new publisher name:");
		String newPublisherInput = sc.nextLine();

		Map<String, String> publishers = publisherDao.readAllPublishers();

		publishers.entrySet().forEach(entry -> {
			if (publisherInput.equals(entry.getValue())) {
				publishers.replace(entry.getKey(), publisherInput, newPublisherInput);
			}
		});

		publisherDao.writePublisherBack(publishers);

		BookDao bookDao = new BookDao();
		Map<String, List<Book>> publisherBookMap = bookDao.readPublisherBook();

		Iterator it = publisherBookMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry map = (Map.Entry) it.next();
			if (publisherInput.equals(map.getKey())) {
				List<Book> newKey = (List<Book>) map.getValue();
				publisherBookMap.remove(map.getKey());
				publisherBookMap.put(newPublisherInput, newKey);
				break;
			}
		}

		bookDao.writePublisherBookBack(publisherBookMap);
	}

	public void deletePublishers() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Please enter author name:");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		PublisherDao publisherDao = new PublisherDao();

		Map<String, String> publisherMap = publisherDao.readAllPublishers();
		publisherMap.entrySet().removeIf(entry -> (input.equals(entry.getValue())));

		publisherDao.writePublisherBack(publisherMap);

		BookDao bookDao = new BookDao();
		Map<String, List<Book>> publisherBookMap = bookDao.readPublisherBook();

		publisherBookMap.entrySet().removeIf(entry -> (input.equals(entry.getKey())));
		bookDao.writePublisherBookBack(publisherBookMap);
	}

}
