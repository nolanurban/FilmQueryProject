package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

private static String URL = "jdbc:mysql://localhost:3306/sdvid";

static { // driver access
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("Oops, we had a forName issue");
		}
	}

  public Film findFilmById(int filmId) throws SQLException {
	String SQL = "select actor_id, actor.first_name, actor.last_name, film.title, film.id, film.release_year, film.rating, film.description, film.length, language.name from film_actor join actor on film_actor.actor_id = actor.id join film on film_actor.film_id = film.id join language on film.language_id = language.id";
	ResultSet result = databaseAccess(SQL);
	List<Actor> actorList = new ArrayList<>();  // placed for long term goal
    Film film = null; 
    
    while (result.next()) {
    	
    	if (result.getInt("id") == filmId) {
    		
    					film = new Film(filmId, 
						result.getString("title"), 
						result.getInt("release_year"),
						result.getString("rating"),
						result.getString("description"),
						result.getString("name"), //language
						result.getInt("length"),
						actorList);
    		}   
    }
    return film;
    
  }
  
public ResultSet databaseAccess(String input) throws SQLException {
		String user = "student";
		String pass = "student";
	
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(input);
		ResultSet result = stmt.executeQuery();
		
//			while (result.next()) {
//				if (result.getInt("id") == 1) {
//			   					System.out.println(result.getString("title")); 
//			   					System.out.println(result.getInt("release_year"));
//			   					System.out.println(result.getString("rating"));
//			   					System.out.println(result.getString("description"));
//			   					System.out.println(result.getString("name")); //language
//			   					System.out.println(result.getInt("length"));
//				}
//			}
////		int customerCount = 0;
////		while (result.next() && customerCount != 20) {
////			customerCount++;
////			System.out.println(result.getString("first_name") + " " + result.getString("last_name"));
////		}
//		stmt.close();
//		result.close();
//		conn.close();
//		System.out.println(result.getMetaData());
		return result;
		
	}

}
