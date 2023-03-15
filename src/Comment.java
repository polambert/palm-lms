
import java.util.UUID;
import java.util.ArrayList;

import java.time.LocalDate;

public class Comment {
	private String comment;
	private User author;
	private UUID authorId;
	private UUID id;
	private LocalDate date;
	private ArrayList<Comment> replies;

	public Comment(UUID id, String comment, User author, LocalDate date, ArrayList<Comment> replies) {
		this.id = id;
		this.comment = comment;
		this.author = author;
		this.date = date;
		this.replies = replies;
	}
	
	public Comment(UUID id, String comment, UUID authorId, LocalDate date, ArrayList<Comment> replies) {
		this.id = id;
		this.comment = comment;
		this.authorId = authorId;
		this.date = date;
		this.replies = replies;
	}

	public String getComment() {
		return comment;
	}
	public User getAuthor() {
		return author;
	}
	public Date getDate() {
		return date;
	}
	public UUID getUUID() {
		return id;
	}
	public ArrayList<Comment> getReplies() {
		return replies;
	}
	public void setComment(String comment) {

	}
	public void setAuthor(User author) {

	}
	public void setDate(Date date) {

	}
	public void setUUID(UUID id) {

	}
	public void setReplies(ArrayList<Comment> replies) {
		
	}
}