package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.List;

public class Film {
	// this film object needs to be populated with all possible categories associated to it
	private int 	id;
	private String 	title;
	private int 	releaseYear;
	private String 	rating; // Has to be string due to the nature of alphanumerics NC17 vs R
	private String 	description;
	private String 	language;
	private int		lengthOfMovie;  // this is in minutes
	private List<Actor> actors; // we will need to populate this into a list using getNext(); should be a fun peice of code.
	
	
	
	public Film(int id, String title, int releaseYear, String rating, String description, String language,
			int lengthOfMovie) {
		super();
		this.id = id;
		this.title = title;
		this.releaseYear = releaseYear;
		this.rating = rating;
		this.description = description;
		this.language = language;
		this.lengthOfMovie = lengthOfMovie;
		actors = new ArrayList<Actor>();
		
	}
            
	@Override
	public String toString() {
		String allActors = "";
		
		for (Actor actor: actors) { // iterate through all of our actors
			allActors += actor.getFirstName() + " " + actor.getLastName() + ", ";
 		}
		
		return "Title: " + title + "\nYear: " + releaseYear + "\nRating: " + rating + "\nDescription: " + description + "\nActors: " + allActors.substring(0, allActors.length()-2);
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public Film(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getLengthOfMovie() {
		return lengthOfMovie;
	}
	public void setLengthOfMovie(int lengthOfMovie) {
		this.lengthOfMovie = lengthOfMovie;
	}	

}
