package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	// DatabaseAccessor db = new DatabaseAccessorObject();
	DatabaseAccessorObject db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {

		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}
	private void printMenu() {
		System.out.println("Welcome to our last use of Scanner\n");
		System.out.println("Please selet from an option below: ");
		System.out.println("1. Look up a film by it's ID number");
		System.out.println("2. Look up a film by searching a keyword");
		System.out.println("3. Exit the application");

	}
	private void startUserInterface(Scanner input) throws SQLException {
		printMenu();
		int optionNumber = input.nextInt();
			switch (optionNumber) {
			case 1:
				System.out.println("Please enter a film number ID to look up: ");
				int lookupNumber = input.nextInt();
				System.out.println(db.findFilmById(lookupNumber).toString());
				break;
			case 2:
				System.out.println("Please enter a keyword to search for in the film titles");
				String keyWord = input.next();
				for (Film f : db.findFilmBySearchString(keyWord)) {
					if (f.equals(null)) System.out.println("Your search: " + keyWord + " was not found");
					System.out.println(f);
					
				}
			case 3:
				break;
			default:
				System.out.println("Error");
			}
		
	}

}
