
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

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
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	/* 
		public static void main (String args[]){
			courseManager = CourseManager.getInstance();
			userManager = UserManager.getInstance();

			courseManager.loadAllCourses();
		}
	*/
	//=======
	public static void main(String args[]) {
		courseManager = CourseManager.getInstance();
		userManager = UserManager.getInstance();
		courseManager.loadAllCourses();
		ArrayList<Course> courses = courseManager.getCourses();
		//System.out.println(courses.get(0));
		signInMenu();
		
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

//>>>>>>> 5c5c6f818b5e6cccdc86d643c472e0d14d5592c1
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



    public static void showSignInMenu() {
        System.out.println("*****Welcome to PALM*****");
        System.out.println("***************");
        System.out.println("1. Log In");
        System.out.println("2. New User");
        System.out.println("3. Quit");
        System.out.println("***************");
        System.out.println("What would you like to do?:");
    }

    public static void signInMenu() {
        showSignInMenu();
        Scanner scan = new Scanner(System.in);
        String command= scan.nextLine();
        switch(command){
            case "1": {
                System.out.println("What is your email?");
                String email= scan.nextLine();
                System.out.println("What is your password?");
                String pass= scan.nextLine();
                char[] password = new char[pass.length()];
                for (int i = 0; i < pass.length(); i++) {
                    password[i] = pass.charAt(i);
                }
                //attemptLogin(email, password);
                break;
            }
            case "2":{
                System.out.println("What is your name?");
                String name= scan.nextLine();
                System.out.println("What is your email?");
                String email= scan.nextLine();
                System.out.println("What is your password?");
                String pass= scan.nextLine();
                char[] ch = new char[pass.length()];
                for (int i = 0; i < pass.length(); i++) {
                    ch[i] = pass.charAt(i);
                }
                login(email, ch);
                return;
            }
            case "3":{
                System.out.println("Thank you for using PALM");
                return;
            }
            default :{
                System.err.println("Error! Invalid command entered. Please try again.");
            }
        }
    }

}




