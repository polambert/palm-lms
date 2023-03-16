
import java.util.Date;
import java.util.Scanner;

public class UIHandler
{
	private static ProgramState state;
	private static AssessmentHandler assessmentHandler;
	private static CourseManager courseManager;
	private static UserManager userManager;


	//getters and setters
	public ProgramState getState() {
		return state;
	}
	public void setState(ProgramState state) {
		this.state = state;
	}

	public AssessmentHandler getAssessmentHandler() {
		return assessmentHandler;
	}

	public void setAssessmentHandler(AssessmentHandler assessmentHandler) {
		this.assessmentHandler = assessmentHandler;
	}

	public CourseManager getCourseManager() {
		return courseManager;
	}

	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}

	public UserManager getUserManager() {
		return UserManager;
	}

	public void setUserManager(UserManager userManager) {
		UserManager = userManager;
	}

	public void main(String args[]) {
=======
		public static void main (String args[]){
			courseManager = CourseManager.getInstance();
			userManager = UserManager.getInstance();
>>>>>>>a8aa1220e7cd5385b1294bbea891c1f12ccb3325

			courseManager.loadAllCourses();
		}

		public static boolean login(String email,char[] password){
			return true;
		}

		public boolean logout() {
			return true;
		}

		public boolean signup(String name, String email, Date dateOfBirth,char[] password){
			return true;
		}

		public void startAssessment(CourseProgress courseProgress){

		}

		public boolean enrollCourse(Course course){
			return true;
		}

		public boolean dropCourse(Course course){
			return true;
		}

		public boolean makeCourse() {
			return true;
		}

		public boolean leaveReview(Review review, Course course){
			return true;
		}

		public boolean leaveComment(Comment comment, Course course){
			return true;
		}

		public boolean deleteReview(Review review, Course course){
			return true;
		}

		public boolean deleteComment(Comment comment, Course course){
			return true;
		}


	}


}

