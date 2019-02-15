package io.azmain.moviecatalogservice.models;

public class Movie {

	private String movieId;
	private String movieName;
	
	/*
	 * When you have java unmarshal something which is not an object to a object you
	 * need an empty contructor. Java marshalling/unmarshalling. The way of
	 * marhshalling & unmarshalling works is java first creates an instance then
	 * parses the strings and populates it one by one.
	 */
	
	public Movie() {};
	
	/* Overloaded constructor */
	public Movie(String movieId, String movieName) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
	}
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	
}
