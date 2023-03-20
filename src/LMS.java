import java.util.Scanner;

public class LMS {
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
                String enrollClass = scan.nextLine();
                //enrollCourse(enrollClass);
                break;
            }
            case "3":
            {
                //makeCourse();
                break;
            }
            case "4":
            {
                System.out.println("What class would you like to enroll in");
                String course= scan.nextLine();
                //convert string to course
                //courseMenu(course);
                break;
            }
            case "5":
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

    private static void showSignInMenu() {
        System.out.println("*****Welcome to PALM*****");
        System.out.println("***************");
        System.out.println("1. Log In");
        System.out.println("2. New User");
        System.out.println("3. Quit");
        System.out.println("***************");
        System.out.println("What would you like to do?:");
    }

    private void signInMenu() {
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
                attemptLogin(email, password);
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
                //signup(name, email, Date dateOfBirth, ch;
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
        //showCourseMenu();
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
                //startAssessment(CourseProgress courseProgress);
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
                //dropCourse(Course course) {
                    break;
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
        System.out.println("*****Review Menu*****");
        System.out.println("Course: ");
        System.out.println("*************************");
        //print out all the current Comments
        System.out.println("*************************");
        System.out.println("1. Leave a Comment");
        System.out.println("2. Return to course");
        System.out.println("*************************");
        System.out.println("What would you like to do?:");
    }

    private void commentMenu(Course course) {
        showCommentMenu();
        Scanner scan = new Scanner(System.in);
        String command= scan.nextLine();
        switch(command)
        {
            case "1":
            {
                System.out.println("What is your Comment?");
                String comment= scan.nextLine();
                //add comment to the array list of comments
                break;
            }
            case "2":
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
        System.out.println("1. Leave a Review");
        System.out.println("2. Return to course");
        System.out.println("*************************");
        System.out.println("What would you like to do?:");
    }

    private void reviewMenu(Course course) {
        showReviewMenu();
        Scanner scan = new Scanner(System.in);
        String command= scan.nextLine();
        switch(command)
        {
            case "1":
            {
                System.out.println("What is your Review?");
                String comment= scan.nextLine();
                //add review to the array list of reviews
                break;
            }
            case "2":
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
