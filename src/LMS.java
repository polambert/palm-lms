import java.util.Scanner;

public class LMS  {
    private static void showHomeMenu() {
        System.out.println("*****Home Menu*****");
        System.out.println("1. View Enrolled Classes");
        System.out.println("2. Enroll In a Course");
        System.out.println("3. Create a Course");
        System.out.println("4. Enter a Course");
        System.out.println("5. Log Out");
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
                System.out.println("What class would you like to enroll in");
                String course= scan.nextLine();
                //convert string to course
                courseMenu(course);
                break;
            }
            case "5":
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

    private static void showSignInMenu() {
        System.out.println("*****Welcome to PALM*****");
        System.out.println("***************");
        System.out.println("1. Log In");
        System.out.println("2. Quit");
        System.out.println("***************");
        System.out.println("What would you like to do?:");
    }

    private void signInMenu() {
        showSignInMenu();
        Scanner scan = new Scanner(System.in);
        String command= scan.nextLine();
        switch(command)
        {
            case "1":
            {
                System.out.println("What is your email?");
                String email= scan.nextLine();
                System.out.println("What is your password?");
                char[] pass= scan.nextchar[]();
                login(String email, char[] pass);
                break;
            }
            case "2":
            {
                System.out.println("Thank you for using PALM");
                return;
            }
            default :
            {
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
        System.out.println("1. Study Section");
        System.out.println("2. Take Quiz");
        System.out.println("3. View/Leave Review ");
        System.out.println("4. View/Leave Comment");
        System.out.println("5. Drop Class");
        System.out.println("6. Go Home");
        System.out.println("***************");
        System.out.println("What would you like to do?:");
    }

    private void courseMenu(Course course) {
        //add course and section to show course below
        showCourseMenu();
        Scanner scan = new Scanner(System.in);
        String command= scan.nextLine();
        switch(command)
        {
            case "1": {
                //study section
                //print text for user to read
                break;
            }
            case "2": {
                startAssessment(CourseProgress courseProgress);
                break;
            }
            case "3": {
                //switch to Review display
                break;
            }
            case "4": {
                //switch to Comment display
                break;
            }
            case "5": {
                dropCourse(Course course) {
                    break;
                }
            }
            case "6": {
                homeMenu();
                break;
            }
            default : {

                System.err.println("Error! Invalid command entered. Please try again.");
            }
        }
    }


    private static void showCommentMenu() {

    }

    private void commentMenu() {

    }

    private static void showReviewMenu() {

    }

    private void reviewMenu() {

    }


}
