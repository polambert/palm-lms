
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
		this.authorId = author.getId();
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

	public void addReply(Comment reply) {
		this.replies.add(reply);
	}

	public UUID getAuthorId() { return this.authorId; }
	public void setAuthorId(UUID authorId) { this.authorId = authorId; }

	public UUID getId() {
		return this.id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public User getAuthor() {
		return author;
	}
	public LocalDate getDate() {
		return date;
	}
	public UUID getUUID() {
		return id;
	}
	public ArrayList<Comment> getReplies() {
		return replies;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setUUID(UUID id) {
		this.id = id;
	}
	public void setReplies(ArrayList<Comment> replies) {
		this.replies = replies;
	}
	public String toString() {
		String s = "[Comment] id: " + id + "\n";
		s += "\tcomment: " + comment + "\n";
		s += "\tauthorId: " + authorId + "\n";
		s += "\thas author: " + (author == null ? "no" : "yes") + "\n";
		s += "\thas right author: " + (author.getId().equals(authorId) ? "yes" : "no") + "\n";
		s += "\tdate: " + date + "\n";
		s += "\treplies: \n";
		for (int i = 0; i < replies.size(); i++) {
			s += "\t" + replies.get(i).toString().replace("\n", "\n\t");
		}
		return s;
	}
}