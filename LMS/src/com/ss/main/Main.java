package com.ss.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void userCall(int input) throws IOException {
		System.out.println("Would you like to add/delete/update/retrieve :");
		System.out.println("1. Add");
		System.out.println("2. Delete");
		System.out.println("3. Update");
		System.out.println("4. Retrieve");
		System.out.println("0. Exit");

		Scanner sc = new Scanner(System.in);
		int action = sc.nextInt();
		while (action < 0 || action > 4) {
			System.out.println("Please enter a value between 0 to 4");
			action = sc.nextInt();
		}

		if (action == 0) {
			System.out.println("Exiting the system...bye");
			System.exit(0);

		}
		Controller controller = new Controller();
		switch (input) {
		case 1:
			controller.authorFunc(action);
			break;
		case 2:
			controller.publisherFunc(action);
			break;
		case 3:
			controller.bookFunc(action);
			break;
		}

	}

	public static void main(String[] args) throws IOException {

		System.out.println("Welcome to LMS. What would you like to do today:");
		System.out.println("1. Author");
		System.out.println("2. Publisher");
		System.out.println("3. Book");
		System.out.println("0. Exit");
		System.out.println("Please enter:");
		Scanner sc = new Scanner(System.in);

		int input = sc.nextInt();
		while (input < 0 || input > 3) {
			System.out.println("Please enter a value between 0 to 3");
			input = sc.nextInt();
		}
		if (input == 0) {
			System.out.println("Exiting the system...bye");
			System.exit(0);

		} else {
			userCall(input);
		}

	}

}
