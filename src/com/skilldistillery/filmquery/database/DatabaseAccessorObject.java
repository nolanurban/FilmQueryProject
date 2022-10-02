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
  public List<Film> findFilmBySearchString(String searchString) throws SQLException {
		String SQL = "select film.id, film.title, film.release_year, film.rating, film.description, language.name, film.length from film join language on film.language_id = language.id where film.title like '%" + searchString + "%' or film.description like '%" + searchString + "%'";
		ResultSet result = databaseAccess(SQL);
		List<Actor> actorList = new ArrayList<>();  // placed for long term goal
	    List<Film> searchList = new ArrayList<>();
 		
	  	
	    while (result.next()) {
	    		Film film = new Film(result.getInt("id"), 
							result.getString("title"), 
							result.getInt("release_year"),
							result.getString("rating"),
							result.getString("description"),
							result.getString("name"), //language
							result.getInt("length"),
							actorList);
	    		searchList.add(film);
	    		}   
	  
	    return searchList;  
}

  
public ResultSet databaseAccess(String input) throws SQLException {
		String user = "student";
		String pass = "student";
	
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(input);
		ResultSet result = stmt.executeQuery();
		
		return result;
		
	}

}
