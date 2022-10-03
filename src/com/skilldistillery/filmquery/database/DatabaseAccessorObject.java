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

private static String 	URL = "jdbc:mysql://localhost:3306/sdvid";
Connection 				conn;
PreparedStatement 		stmt;
ResultSet 				result;

static { // driver access
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("Oops, we had a forName issue");
		}
	}

  public Film findFilmById(int filmId) throws SQLException {
		String SQL = "select actor_id, actor.first_name, actor.last_name, film.title, film.id, film.release_year, film.rating, film.description, film.length, language.name from film_actor join actor on film_actor.actor_id = actor.id join film on film_actor.film_id = film.id join language on film.language_id = language.id where film.id = " + filmId;		
		ResultSet result = databaseAccess(SQL);
		List<Actor> actorList = new ArrayList<>();
		// placed for long term goal
		result.next();
		
		Film film = new Film(filmId, 
			result.getString("title"), 
			result.getInt("release_year"),
			result.getString("rating"),
			result.getString("description"),
			result.getString("name"), //language
			result.getInt("length"));
    
		
		    while (result.next()) {  
		    	Actor actor = new Actor(result.getInt("actor_id"), result.getString("first_name"), result.getString("last_name"));
		    	actorList.add(actor);				
		    }   
		    
		    film.setActors(actorList); // store our actors in our film object
		    closeConnections();
		    return film;
    
  }
  
  public List<Film> findFilmBySearchString(String searchString) throws SQLException {
		String SQL = "select actor_id, actor.first_name, actor.last_name, film.title, film.id, film.release_year, film.rating, film.description, film.length, language.name from film_actor join actor on film_actor.actor_id = actor.id join film on film_actor.film_id = film.id join language on film.language_id = language.id where film.title like '%" + searchString + "%' or film.description like '%" + searchString + "%'";		
		ResultSet result = databaseAccess(SQL);
	    List<Film> searchList = new ArrayList<>();
 		if (result.next())	  	
	    do {
	    		Film film = new Film(result.getInt("id"), 
							result.getString("title"), 
							result.getInt("release_year"),
							result.getString("rating"),
							result.getString("description"),
							result.getString("name"), //language
							result.getInt("length"));
	    			
	    		List<Actor> actorList = new ArrayList<>();  // create a list of actor objects
	    		
	    		int filmId = result.getInt("id"); // id check in place
	    		do {	// had to do a do while because of getNext() headaches
	    			Actor currentActor = new Actor(	result.getInt("actor_id"),
	    											result.getString("first_name"), 
	    											result.getString("last_name")); // create an actor obj (each film object can have multiple)
	    			actorList.add(currentActor); // add actor to the list
	    			if (!result.next()) {  
	    				film.setActors(actorList); // we have to set it here because it will skip one entry without
	    				searchList.add(film);
	    				closeConnections();
	    				return searchList;
	    				}
	    			} while (filmId == result.getInt("id")); // are we on the same film still
	    		
//	    		result.previous(); // if we aren't on the same film, we need to step back one in the iteration.
	    		film.setActors(actorList); // take our actor list and apply it to our film object
	    		searchList.add(film); // now we add our film obj into our film list
	    } while (true);  
 		else {
 			closeConnections();
 			return searchList;	
 		}
 		
 		
}
private void closeConnections() throws SQLException {
	result.close();
	stmt.close();
	conn.close();
}
  
public ResultSet databaseAccess(String input) throws SQLException {
		String user = "student";
		String pass = "student";
		conn = DriverManager.getConnection(URL, user, pass);
		stmt = conn.prepareStatement(input);
		result = stmt.executeQuery();

		return result;		
	}

}
