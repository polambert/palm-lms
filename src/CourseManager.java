
import java.util.ArrayList;
import java.util.UUID;

/**
 * CourseManager
 * Stores and manages courses during runtime
 * @author Parker Lambert
 */
public class CourseManager {
	private static ArrayList<Course> courses;
	private static CourseManager courseManager;
	private static CourseDBManager dbManager;

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

		return true;
	}

	public boolean writeAllCourses() {
		dbManager.writeCoursesToDB(courses);

		return true;
	}

	public ArrayList<Course> getCoursesMadeByUser(User user) {
		ArrayList<Course> courseList = new ArrayList<>();
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getAuthorId().equals(user.getId())) {
				courseList.add(courses.get(i));
			}
		}
		return courseList;
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
			for (int j = 0; j < reviews.size(); j++) {
				Review review = reviews.get(j);
				if (review.getAuthor() == null && review.getAuthorId() != null) {
					review.setAuthor(UserManager.getInstance().getUserFromId(review.getAuthorId()));
					//System.out.println(UserManager.getInstance().getUserFromId(review.getAuthorId()));
				}
			}

			// update comments
			ArrayList<Comment> comments = course.getComments();
			updateComments(comments);
		}
	}

	public Course createCourse(String name, String title, String language, String description) {
		User author = UserManager.getInstance().getLoggedInUser();
		if (author.canCreateCourses()) {
			// create the course
			Course course = new Course(UUID.randomUUID(), name, author, new ArrayList<Chapter>(), null, new ArrayList<Review>(), new ArrayList<Comment>());

			course.setAuthor(author);
			course.setAuthorId(author.getId());
			course.setLanguage(language);
			course.setTitle(title);
			course.setDescription(description);

			courses.add(course);
			writeAllCourses();

			return course;
		} else {
			return null;
		}
	}

	/*
	public static void main(String[] args) {
		CourseManager.getInstance().loadAllCourses();
		UserManager.getInstance().loadAllUsers();
		CourseManager.getInstance().updateUsers();

		// testing
		System.out.println("COURSEMANAGER TESTING");

		// test load course
		System.out.println(" -- LOAD COURSE TEST");
		Course c = CourseManager.getInstance().getCourses().get(0);
		System.out.println(c);

		// test write course
		System.out.println(" -- WRITE COURSE TEST");
		System.out.println(CourseManager.getInstance().writeAllCourses());

		// test create course
		//
	}
	*/

	public ArrayList<Course> getEnrolledCourses(User user) {
		ArrayList<CourseProgress> courseProgresses = user.getCourseProgresses();
		ArrayList<Course> enrolledCourses = new ArrayList<>();

		for (int i = 0; i < courseProgresses.size(); i++) {
			UUID courseId = courseProgresses.get(i).getCourse().getId();
			enrolledCourses.add(getCourseById(courseId));
		}

		return enrolledCourses;
	}

	// getter and setter...
	public ArrayList<Course> getCourses() {
		return this.courses;
	}
}
