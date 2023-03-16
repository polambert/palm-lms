
import java.util.ArrayList;
import java.util.UUID;

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

	public Course(UUID id, String name, User author, ArrayList<Chapter> chapters,
	Assessment finalExam, ArrayList<Review> reviews, ArrayList<Comment> comments) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.chapters = chapters;
		this.finalExam = finalExam;
		this.reviews = reviews;
		this.comments = comments;
	}
	
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

	//getters and setters
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
		return null;
	}

	public int getChapterCount() {
		return 0;
	}

	public Assessment getFinalExam() {
		return null;
	}

	public String toString() {
		String s = "[Course] id: " + id + "\n";
		s += "\tname: " + name + "\n";
		s += "\tlanguage: " + language + "\n";
		s += "\ttitle: " + title + "\n";
		s += "\tauthorId: " + authorId + "\n";
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


