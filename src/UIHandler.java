
import java.util.Date;

public class UIHandler {
	private ProgramState state;
	private AssessmentHandler assessmentHandler;
	private CourseManager courseManager;
	private UserManager UserManager;

	public void main(String args[]) {

	}

	public boolean login(String email, char[] password) {
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

