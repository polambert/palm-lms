
import java.util.UUID;

import java.time.LocalDate;

/**
 * Stores information about a Review
 * @author Kaleb Bah
 */
public class Review {
	private int rating;
	private String text;
	private UUID id;
	private LocalDate date;
	private User author;
	private UUID authorId; // used only while loading data

	/**
	 * Constructs a Review using a User for the author
	 * @param id Review ID
	 * @param rating Rating
	 * @param text Text
	 * @param author Review author
	 * @param date Date posted
	 */
	public Review(UUID id, int rating, String text, User author, LocalDate date) {
		this.id = id;
		this.rating = rating;
		this.text = text;
		this.author = author;
		this.date = date;
	}

	/**
	 * Constructs a Review using a UUID for the author
	 * @param id Review ID
	 * @param rating Rating
	 * @param text Text
	 * @param authorId Review author's ID
	 * @param date Date posted
	 */
	public Review(UUID id, int rating, String text, UUID authorId, LocalDate date) {
		this.id = id;
		this.rating = rating;
		this.text = text;
		this.authorId = authorId;
		this.date = date;
	}

	// getters and setters
	public UUID getAuthorId() { return this.authorId; }
	public void setAuthorId(UUID authorId) { this.authorId = authorId; }

	public UUID getId() {
		return this.id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public Course getCourse() {
		return null;
	}
	public User getAuthor() {
		return author;
	}
	public String getText() {
		return text;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String toString() {
		String s = "[Review] rating: " + rating + "\n";
		s += "\ttext: " + text + "\n";
		s += "\tid: " + id + "\n";
		s += "\tdate: " + date + "\n";
		s += "\tauthorId: " + authorId + "\n";
		s += "\thas author: " + (author == null ? "no" : "yes") + "\n";
		s += "\thas right author: " + (author.getId().equals(authorId) ? "yes" : "no") + "\n";
		
		return s;
	}

}