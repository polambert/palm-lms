
import java.util.Date;

public static class UIHandler {
	private static ProgramState state;
	private static AssessmentHandler assessmentHandler;
	private static CourseManager courseManager;
	private static UserManager userManager;

	public static void main(String args[]) {
		courseManager = CourseManager.getInstance();
		userManager = UserManager.getInstance();

		courseManager.loadAllCourses();
	}

	public static boolean login(String email, char[] password) {
		return true;
	}

	public boolean logout() {
		return true;
	}

	public boolean signup(String name, String email, Date dateOfBirth, char[] password) {
		return true;
	}

	public void startAssessment(CourseProgress courseProgress) {

	}

	public boolean enrollCourse(Course course) {
		return true;
	}

	public boolean dropCourse(Course course) {
		return true;
	}

	public boolean makeCourse() {
		return true;
	}

	public boolean leaveReview(Review review, Course course) {
		return true;
	}

	public boolean leaveComment(Comment comment, Course course) {
		return true;
	}

	public boolean deleteReview(Review review, Course course) {
		return true;
	}

	public boolean deleteComment(Comment comment, Course course) {
		return true;
	}   
}

