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
	String dummyTest = "select title, release_year, rating, description, length from film";
	FilmQueryApp app = new FilmQueryApp();
    app.launch();
 //   app.test();
 //   System.out.println(db.findFilmById(143).toString()); 
//    app.launch();
//    String checkResultTest = "select actor_id, actor.first_name, actor.last_name, film.title, film.id, film.release_year, film.rating, film.description, film.length, language.name from film_actor join actor on film_actor.actor_id = actor.id join film on film_actor.film_id = film.id join language on film.language_id = language.id";
//    db.databaseAccess(checkResultTest);
    
  }

//  private void test() {
//    Film film = db.findFilmById(1);
//    System.out.println(film);
//  }
//
  private void launch() throws SQLException {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) throws SQLException {
    System.out.println("Welcome to our last use of Scanner\n");
    System.out.println("Please selet from an option below: ");
    System.out.println("1. Look up a film by it's ID number");
    System.out.println("2. Look up a film by searching a keyword");
    System.out.println("3. Exit the application");
    
    int optionNumber = input.nextInt();
    switch (optionNumber) {
    case 1: System.out.println("Please enter a film number ID to look up: ");
    	int lookupNumber = input.nextInt();
        System.out.println(db.findFilmById(lookupNumber).toString()); 
        break;
    case 2:
    case 3: break;
    default: System.out.println("Error");
    }
  }

}
