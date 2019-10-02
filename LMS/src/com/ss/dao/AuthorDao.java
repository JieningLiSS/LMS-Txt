package com.ss.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

public class AuthorDao {

	public static AuthorDao myObj;

	/**
	 * Create private constructor
	 */
	public AuthorDao() {

	}

	/**
	 * Create a static method to get instance.
	 */
	public static AuthorDao getInstance() {
		if (myObj == null) {
			myObj = new AuthorDao();
		}
		return myObj;
	}

	public Map<String, String> readAllAuthors() throws IOException {
		FileInputStream fin = new FileInputStream("/Users/jiening/Desktop/author.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(fin));

		Map<String, String> authorMap = new HashMap<String, String>();

		String line;
		while ((line = buffReader.readLine()) != null) {
			String[] splitArray = line.split(";");
			authorMap.put(splitArray[0], splitArray[1]);
		}

		buffReader.close();
		return authorMap;
	}

	public void writeAuthorBack(Map<String, String> authorMap) throws IOException {
		File f = new File("/Users/jiening/Desktop/author.txt");
		if (f.exists()) {
			f.delete();
		}
		FileWriter out = new FileWriter(f);

		authorMap.entrySet().forEach(entry -> {
			try {
				out.write(entry.getKey() + ";" + entry.getValue());
				out.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		out.close();
	}

}
