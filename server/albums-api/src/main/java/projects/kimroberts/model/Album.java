package projects.kimroberts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Album {

	private int id;
	private String title;
	private String artist;
	private int year;
	
	public Album() {}
	
	public Album(@JsonProperty("id") int id, 
					@JsonProperty("title") String title, 
					@JsonProperty("artist") String artist, 
					@JsonProperty("year") int year) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.year = year;
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

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
}
