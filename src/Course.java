
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Stores all information and methods related to a Course
 * @author Luke Lane, Kaleb Bah
 */
public class Course {
	private UUID id;
	private String language;
	private String title;
	private String name;
	private User author;
	private UUID authorId;
	private String description;
	private ArrayList<Chapter> chapters = new ArrayList<>();
	private Assessment finalExam;
	private ArrayList<Review> reviews = new ArrayList<>();
	private ArrayList<Comment> comments = new ArrayList<>();

	/**
	 * Constructs a Course with a User author
	 * @param id ID of course
	 * @param name name of course
	 * @param author author of course
	 * @param chapters list of chapters
	 * @param finalExam final exam assessment
	 * @param reviews list of reviews
	 * @param comments list of comments
	 */
	public Course(UUID id, String name, User author, ArrayList<Chapter> chapters,
	Assessment finalExam, ArrayList<Review> reviews, ArrayList<Comment> comments) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.authorId = author.getId();
		this.chapters = chapters;
		this.finalExam = finalExam;
		this.reviews = reviews;
		this.comments = comments;
	}
	
	/**
	 * Constructs a Course with a UUID author
	 * @param id ID of course
	 * @param name name of course
	 * @param authorId ID of author of course
	 * @param chapters list of chapters
	 * @param finalExam final exam assessment
	 * @param reviews list of reviews
	 * @param comments list of comments
	 */
	public Course(UUID id, String name, UUID authorId, ArrayList<Chapter> chapters,
	Assessment finalExam, ArrayList<Review> reviews, ArrayList<Comment> comments) {
		this.id = id;
		this.name = name;
		this.authorId = authorId;
		this.chapters = chapters;
		this.finalExam = finalExam;
		this.reviews = reviews;
		this.comments = comments;
	}

	/**
	 * Returns the average rating of the course
	 * @return Average rating of the course
	 */
	public double getRating() {
		double sum = 0.0;
		for (int i = 0; i < reviews.size(); i++) {
			sum += reviews.get(i).getRating();
		}
		if (reviews.size() == 0) {
			return 0;
		}
		return sum / reviews.size();
	}

	// getters and setters
	public UUID getAuthorId() { return this.authorId; }
	public void setAuthorId(UUID authorId) { this.authorId = authorId; }
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(ArrayList<Chapter> chapters) {
		this.chapters = chapters;
	}

	public void setFinalExam(Assessment finalExam) {
		this.finalExam = finalExam;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public Review addReview(int rating, String text, User author) {
		Review review = new Review(UUID.randomUUID(), rating, text, author, LocalDate.now());
		reviews.add(review);
		return review;
	}

	public int getChapterCount() {
		return chapters.size();
	}

	public Assessment getFinalExam() {
		return finalExam;
	}

	public String toString() {
		String s = "[Course] id: " + id + "\n";
		s += "\tname: " + name + "\n";
		s += "\tlanguage: " + language + "\n";
		s += "\ttitle: " + title + "\n";
		s += "\tauthorId: " + authorId + "\n";
		s += "\thas author: " + (author == null ? "no" : "yes") + "\n";
		s += "\thas right author: " + (author.getId().equals(authorId) ? "yes" : "no") + "\n";
		s += "\tdescription: " + description + "\n";

		s += "\treviews: \n";
		for (int i = 0; i < reviews.size(); i++) {
			s += "\t" + reviews.get(i).toString().replace("\n", "\n\t");
		}

		s += "\tcomments: \n";
		for (int i = 0; i < comments.size(); i++) {
			s += "\t" + comments.get(i).toString().replace("\n", "\n\t");
		}

		return s;
	}



}


