
import java.util.ArrayList;
import java.util.UUID;

/**
 * CourseManager
 * Stores and manages courses during runtime
 * @author Parker Lambert
 */
public class CourseManager {
	private ArrayList<Course> courses;
	private static CourseManager courseManager;
	private CourseDBManager dbManager;

	/**
	 * Private constructor for singleton CourseManager
	 */
	private CourseManager() {
		this.dbManager = new CourseDBManager();
	}

	/**
	 * Public getInstance for singleton CourseManager
	 * @return The CourseManager
	 */
	public static CourseManager getInstance() {
		if (CourseManager.courseManager == null) {
			CourseManager.courseManager = new CourseManager();
		}
		
		return CourseManager.courseManager;
	}

	public Course getCourseById(UUID id) {
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getId().equals(id)) {
				return courses.get(i);
			}
		}

		return null;
	}

	public boolean loadAllCourses() {
		courses = dbManager.readCoursesFromDB();

		return false;
	}

	public void updateComments(ArrayList<Comment> comments) {
		for (int i = 0; i < comments.size(); i++) {
			Comment comment = comments.get(i);
			if (comment.getAuthor() == null && comment.getAuthorId() != null) {
				comment.setAuthor(UserManager.getInstance().getUserFromId(comment.getAuthorId()));
			}

			// do their replies
			ArrayList<Comment> replies = comment.getReplies();
			updateComments(replies);
		}
	}

	public void updateUsers() {
		for (int i = 0; i < courses.size(); i++) {
			Course course = courses.get(i);

			// update course
			if (course.getAuthor() == null && course.getAuthorId() != null) {
				course.setAuthor(UserManager.getInstance().getUserFromId(course.getAuthorId()));
			}

			// update reviews
			ArrayList<Review> reviews = course.getReviews();
			for (int j = 0; j < reviews.size(); i++) {
				Review review = reviews.get(i);
				if (review.getAuthor() == null && review.getAuthorId() != null) {
					review.setAuthor(UserManager.getInstance().getUserFromId(review.getAuthorId()));
				}
			}

			// update comments
			ArrayList<Comment> comments = course.getComments();
			updateComments(comments);
		}
	}

	// getter and setter...
	public ArrayList<Course> getCourses() {
		return this.courses;
	}
}
