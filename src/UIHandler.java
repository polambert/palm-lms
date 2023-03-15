
import java.util.Date;
import java.util.Scanner;

public class UIHandler {
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
	public static void main(String args[]) {
		courseManager = CourseManager.getInstance();
		userManager = UserManager.getInstance();
>>>>>>> a8aa1220e7cd5385b1294bbea891c1f12ccb3325

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


	private static void showHomeMenu() {
		System.out.println("*****Home Menu*****");
		System.out.println("1. View Enrolled Classes");
		System.out.println("2. Enroll In a Course");
		System.out.println("3. Create a Course");
		System.out.println("4. Log Out");
		System.out.println("***************");
		System.out.println("What would you like to do?:");
	}

	private static void showSignInMenu() {
		System.out.println("*****Welcome to PALM*****");
		System.out.println("***************");
		System.out.println("1. View Enrolled Classes");
		System.out.println("2. Enroll In a Course");
		System.out.println("***************");
		System.out.println("What would you like to do?:");
	}

	private static void showCourseMenu() {
		System.out.println("***************");
		System.out.println("Course:");
		System.out.println("Chapter:");
		System.out.println("Rating:");
		System.out.println("***************");
		System.out.println("1. Study Section");
		System.out.println("2. Take Quiz");
		System.out.println("3. Take Test");
		System.out.println("4. Take Exam");
		System.out.println("5. View/Leave Review ");
		System.out.println("6. View/Leave Comment");
		System.out.println("7. Drop Class");
		System.out.println("8. Go Home");
		System.out.println("***************");
		System.out.println("What would you like to do?:");
	}

	private void homeMenu() {
		showHomeMenu();
		Scanner scan = new Scanner(System.in);
		String command= scan.nextLine();
		switch(command)
		{
			case "1":
			{
				//print all enrolled classes
				break;
			}
			case "2":
			{
				System.out.println("What class would you like to enroll in");
				String enrollClass= scan.nextLine();
				enrollCourse(Course enrollClass);
				break;
			}
			case "3":
			{
				makeCourse();
				break;
			}

			case "4":
			{
				logout();
				break;
			}

			default :
			{
				System.err.println("Error! Invalid command entered. Please try again.");
			}
		}
	}



}

