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

import com.ss.model.Book;
import com.ss.model.Publisher;

public class PublisherDao {

	public static PublisherDao myObj;

	/**
	 * Create private constructor
	 */
	public PublisherDao() {

	}

	/**
	 * Create a static method to get instance.
	 */
	public static PublisherDao getInstance() {
		if (myObj == null) {
			myObj = new PublisherDao();
		}
		return myObj;
	}

	public Map<String, String> readAllPublishers() throws IOException {
		FileInputStream fin = new FileInputStream("/Users/jiening/Desktop/publisher.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(fin));

		Map<String, String> publisherMap = new HashMap<String, String>();

		String line;
		while ((line = buffReader.readLine()) != null) {
			String[] splitArray = line.split(";");
			publisherMap.put(splitArray[0], splitArray[1]);
		}
		buffReader.close();
		return publisherMap;
	}

	public void writePublisherBack(Map<String, String> publisherMap) throws IOException {
		File f = new File("/Users/jiening/Desktop/publisher.txt");
		if (f.exists()) {
			f.delete();
		}

		FileWriter out = new FileWriter(f);
		publisherMap.entrySet().forEach(entry -> {
			try {
				out.write(entry.getKey() + ";" + entry.getValue());
				out.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		out.close();

	}

}
