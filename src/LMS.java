import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.UUID;

//Methods that contain arrays that are print later
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


	/**
	 * Converts a datestring (YYYY-MM-DD) into a LocalDate
	 * @param dateString datestring in format of YYYY-MM-DD
	 * @return LocalDate representing datestring given
	 */
	private LocalDate dateStringToDate(String dateString) {
		// date strings are formatted YYYY-MM-DD
		String[] split = dateString.split("-");

		if (split.length != 3) {
			return null;
		}

		String year = split[0];
		String month = split[1];
		String day = split[2];

		if (year.length() != 4)		return null;
		if (month.length() != 2)	return null;
		if (day.length() != 2)		return null;
		
		// construct the Date
		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month);
		int d = Integer.parseInt(day);

		LocalDate date = LocalDate.of(y, m, d);

		return date;
	}


    //method that prints array
    private static void showHomeMenu() {
        System.out.println("*****Home Menu*****");
        for(int i=0;i<HOME_MENU.length;i++)
            System.out.println((i+1)+". "+HOME_MENU[i]);
        System.out.println("***************");
        System.out.println("What would you like to do?:");
    }


    //switch statement method
    private static void homeMenu(String email) {
        showHomeMenu();
        Scanner scan = new Scanner(System.in);
        int num = Integer.parseInt(scan.nextLine());
        String command = HOME_MENU[num-1];
        switch(command)
        {
            case "View Enrolled Courses":
            {
                //print all enrolled classes
                UUID userID = UserManager.getInstance().getIdFromEmail(email);
                CourseManager.getInstance().getCourseById(userID);
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
                System.out.println("What is the name of the class");
                String name = scan.nextLine();
                System.out.println("What is the title of the class");
                String title = scan.nextLine();
                System.out.println("What is the language of the class");
                String language = scan.nextLine();
                System.out.println("What is the description of the class");
                String description = scan.nextLine();
                CourseManager.getInstance().createCourse(name, title, language, description);
                CourseManager.getInstance().writeAllCourses();
                System.out.println("Your course "+ name + " is created!");

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

    public static void showSignInMenu() {
        System.out.println("*****Welcome to PALM*****");
        System.out.println("***************");
        for(int i=0;i<SIGN_IN_MENU.length;i++)
            System.out.println((i+1)+". "+SIGN_IN_MENU[i]);
        System.out.println("***************");
        System.out.println("What would you like to do?:");
    }

    public static void signInMenu() {
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
                homeMenu(email);
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
                System.out.println("What is your date of birth?");
                String birthday= scan.nextLine();
                //signup(name, email, Date dateOfBirth, ch);
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
                //get user email
				//homeMenu(email);
				break;
			}
			default : {

				System.err.println("Error! Invalid command entered. Please try again.");
			}
		}
	}


    private static void showCommentMenu() {
        System.out.println("*****Comment Menu*****");
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
                String userComment= scan.nextLine();
                ArrayList<Comment> replies = new ArrayList<>();
                Comment comment = new Comment(UUID.randomUUID(), userComment, UserManager.getInstance().getLoggedInUser(), LocalDate.now(), replies);
                //add comment to the array list of comments
				course.getComments().add(comment);
				CourseManager.getInstance().writeAllCourses();
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
                System.out.print("What is your Review? ");
                String comment= scan.nextLine();
                System.out.print("What is your rating? (1-5) ");
                int rating = Integer.parseInt(scan.nextLine());

				if (rating >= 1 && rating <= 5) {
					course.addReview(rating, comment, UserManager.getInstance().getLoggedInUser());
					CourseManager.getInstance().writeAllCourses();
				} else {
					System.out.println("Unable to add review, please make sure rating is between 1-5.");
				}

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
