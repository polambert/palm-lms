import java.util.ArrayList;
import java.util.Scanner;

public class LMS {
    private static final String[] COURSE_MENU = {
        "Study Section",
        "Take Quiz",
        "View/Leave Review",
        "View/Leave Comment",
        "Drop Class","Go Home"
    };
    private static final String[] HOME_MENU = {"View Enrolled Classes",
        "Enroll in a Course",
        "Create a Course",
        "Enter a Course",
        "Log Out"
    };
    private static final String[] SIGN_IN_MENU = {"Log In",
        "New User",
        "Quit"
    };
    private static final String[] COMMENT_MENU = {"Leave Comment",
        "Return to Course"
    };
    private static final String[] REVIEW_MENU = {"Leave Review",
        "Return to Course"
    };

    private static void showHomeMenu() {
        System.out.println("*****Home Menu*****");
        for(int i=0;i<HOME_MENU.length;i++)
            System.out.println((i+1)+". "+HOME_MENU[i]);
        System.out.println("***************");
        System.out.println("What would you like to do?:");
    }

    private void homeMenu() {
        showHomeMenu();
        Scanner scan = new Scanner(System.in);
        int num = Integer.parseInt(scan.nextLine());
        String command = HOME_MENU[num-1];
        switch(command)
        {
            case "View Enrolled Courses":
            {
                //print all enrolled classes
                break;
            }
            case "Enroll in a Course":
            {
                System.out.println("What class would you like to enroll in");
                String enrollClass = scan.nextLine();
                //enrollCourse(enrollClass);
                break;
            }
            case "Create a Course":
            {
                //makeCourse();
                break;
            }
            case "Enter a Course":
            {
                System.out.println("What class would you like to enroll in");
                String course= scan.nextLine();
                //convert string to course
                //courseMenu(course);
                break;
            }
            case "Log Out":
            {
                //logout();
                break;
            }
            default :
            {
                System.err.println("Error! Invalid command entered. Please try again.");
            }
        }
    }

    public void showSignInMenu() {
        System.out.println("*****Welcome to PALM*****");
        System.out.println("***************");
        for(int i=0;i<SIGN_IN_MENU.length;i++)
            System.out.println((i+1)+". "+SIGN_IN_MENU[i]);
        System.out.println("***************");
        System.out.println("What would you like to do?:");
    }

    public void signInMenu() {
        showSignInMenu();
        Scanner scan = new Scanner(System.in);
        int num = Integer.parseInt(scan.nextLine());
        String command = SIGN_IN_MENU[num-1];
        switch(command){
            case "Log In": {
                System.out.println("What is your email?");
                String email= scan.nextLine();
                System.out.println("What is your password?");
                String pass= scan.nextLine();
                char[] password = new char[pass.length()];
                for (int i = 0; i < pass.length(); i++) {
                    password[i] = pass.charAt(i);
                }
                UserManager.getInstance().attemptLogin(email, password);
                break;
            }
            case "New User":{
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
                //signup(name, email, Date dateOfBirth, ch;
                return;
            }
            case "Quit":{
                System.out.println("Thank you for using PALM");
                return;
            }
            default :{
                System.err.println("Error! Invalid command entered. Please try again.");
            }
        }
    }

    private static void showCourseMenu(Course course, Chapter chapter) {
        System.out.println("***************");
        System.out.println("Course:" + course);
        System.out.println("Chapter:" + chapter);
        System.out.println("Rating:");
        System.out.println("***************");
        for(int i=0;i<=COURSE_MENU.length;i++)
            System.out.println((i+1)+". "+COURSE_MENU[i]);
        System.out.println("***************");
        System.out.println("What would you like to do?:");
    }

	private void courseMenu(Course course) {
		//add course and section to show course below

		showCourseMenu(course, course.getChapters().get(UserManager.getInstance().getLoggedInUser().getCourseProgressIn(course).getChaptersCompleted()));

        //Left unfinished - LukeLane
        Scanner scan = new Scanner(System.in);
        int num = Integer.parseInt(scan.nextLine());
        String command = COURSE_MENU[num-1];

		switch(command)
		{
			case "Study Section": {
				//study section
				//print text for user to read
				break;
			}
			case "Take Quiz": {
				//startAssessment(CourseProgress courseProgress);
				break;
			}
			case "View/Leave Review": {
				//switch to Review display
				break;
			}
			case "View/Leave Comment": {
				//switch to Comment display
				break;
			}
			case "Drop Class": {
				//dropCourse(Course course) {
					break;
				}
			
			case "Go Home": {
				homeMenu();
				break;
			}
			default : {

				System.err.println("Error! Invalid command entered. Please try again.");
			}
		}
	}


    private static void showCommentMenu() {
        System.out.println("*****Review Menu*****");
        System.out.println("Course: ");
        System.out.println("*************************");
        //print out all the current Comments
        System.out.println("*************************");
        for(int i=0;i<COMMENT_MENU.length;i++)
            System.out.println((i+1)+". "+COMMENT_MENU[i]);
        System.out.println("*************************");
        System.out.println("What would you like to do?:");
    }

    private void commentMenu(Course course) {
        showCommentMenu();
        Scanner scan = new Scanner(System.in);
        int num = Integer.parseInt(scan.nextLine());
        String command = COMMENT_MENU[num-1];
        switch(command)
        {
            case "Leave Comment":
            {
                System.out.println("What is your Comment?");
                String comment= scan.nextLine();
                //add comment to the array list of comments
                break;
            }
            case "Return to Course":
            {
                courseMenu(course);
                return;
            }
            default :
            {
                
                System.err.println("Error! Invalid command entered. Please try again.");
            }
        }
    }

    private static void showReviewMenu() {
        System.out.println("*****Review Menu*****");
        System.out.println("Course: ");
        System.out.println("Rating: ");
        System.out.println("*************************");
        //print out all the current Review
        System.out.println("*************************");
        for(int i=0;i<REVIEW_MENU.length;i++)
            System.out.println((i+1)+". "+REVIEW_MENU[i]);
        System.out.println("*************************");
        System.out.println("What would you like to do?:");
    }

    private void reviewMenu(Course course) {
        showReviewMenu();
        Scanner scan = new Scanner(System.in);
        int num = Integer.parseInt(scan.nextLine());
        String command = REVIEW_MENU[num-1];
        switch(command)
        {
            case "Leave Review":
            {
                System.out.println("What is your Review?");
                String comment= scan.nextLine();
                //add review to the array list of reviews
                break;
            }
            case "Return to Course":
            {
                courseMenu(course);
                return;
            }
            default :
            {
                
                System.err.println("Error! Invalid command entered. Please try again.");
            }
        }
    }




	
}
