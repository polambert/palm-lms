import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
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
	private static final String[] HOME_MENU = {
		"View Enrolled Courses",
		"Enroll in a Course",
		"Create a Course",
		"Enter a Course",
		"Log Out"
	};
	private static final String[] SIGN_IN_MENU = {
		"Log In",
		"New User",
		"Quit"
	};
	private static final String[] COMMENT_MENU = {
		"Leave Comment",
		"Return to Course"
	};
	private static final String[] REVIEW_MENU = {
		"Leave Review",
		"Return to Course"
	};


	/**
	 * Converts a datestring (YYYY-MM-DD) into a LocalDate
	 * @param dateString datestring in format of YYYY-MM-DD
	 * @return LocalDate representing datestring given
	 */
	private static LocalDate dateStringToDate(String dateString) {
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


	//method that prints array of options
	private static void showHomeMenu() {
		System.out.println("*****Home Menu*****");
		for(int i=0;i<HOME_MENU.length;i++)
			System.out.println((i+1)+". "+HOME_MENU[i]);
		System.out.println("***************");
		System.out.println("What would you like to do?:");
	}


	//switch statement method
	private static void homeMenu(String email) {
		while (true) {
			showHomeMenu();
			Scanner scan = new Scanner(System.in);
			int num = Integer.parseInt(scan.nextLine());
			String command = HOME_MENU[num-1];
			clearScreen();

			switch(command)
			{
				case "View Enrolled Courses":
				{
					//print all enrolled classes
					ArrayList<Course> courses = UserManager.getInstance().getLoggedInUser().getEnrolledCourses();
					
					if (courses.size() == 0) {
						System.out.println("You are not currently enrolled in any courses.\n");
						break;
					}

					for(int i=0; i<courses.size(); i++){
						System.out.println((i+1) + ". " + courses.get(i).getTitle());   
					}
					
					System.out.print("Enter a number: ");
					int enrollClass = Integer.parseInt(scan.nextLine());
					Course enroll = courses.get(enrollClass - 1);
					courseMenu(enroll);
				
					break;
				}
				case "Enroll in a Course":
				{
					System.out.println("What course would you like to enroll in?");
					ArrayList<Course> courses = CourseManager.getInstance().getCourses();

					for(int i=0; i < courses.size(); i++) {
						System.out.println("  " + (i+1) + ". " + courses.get(i).getTitle());
						System.out.println("      Language: " + courses.get(i).getLanguage());
						System.out.println("      Description: " + courses.get(i).getDescription());
					}
					
					System.out.print("Enter a number: ");
					int enrollClass = Integer.parseInt(scan.nextLine());
					Course course = courses.get(enrollClass - 1);
					
					boolean success = UserManager.getInstance().getLoggedInUser().enrollIn(course);
					UserManager.getInstance().writeAllUsers();

					if (success) {
						System.out.println("You are now enrolled in '" + course.getName() + "'.");
						System.out.println("Sending you to the course menu.");
					}

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
					Course course = CourseManager.getInstance().createCourse(name, title, language, description);

					System.out.println("How many chapters is the course");
					int chapters = Integer.parseInt(scan.nextLine());
					ArrayList<Chapter> chaptersList = new ArrayList<Chapter>(chapters);
					course.setChapters(chaptersList);

					for(int i = 0; i < chapters; i++){
						int chapterNumber = i + 1;
						System.out.println("What is the name of chapter " + chapterNumber + "?");
						String chapterName = scan.nextLine();

						System.out.println("How many sections is chapter " + chapterNumber + "?");
						int numSections = Integer.parseInt(scan.nextLine());
						ArrayList<Section> sections = new ArrayList<Section>(numSections);

						System.out.println("How many questions is the chapter " + chapterNumber + " test?");
						int numQuestions = Integer.parseInt(scan.nextLine());
						ArrayList<Question> questions = new ArrayList<Question>(numQuestions);

						for(int j = 0; j < numQuestions; j++)
						{
							int questionNumber = j + 1;
							System.out.println("What is question number "+ questionNumber + "?");
							String actualQuestion = scan.nextLine();

							//ask user if we want to change number of answer options to anything other than 4
							ArrayList<String> options = new ArrayList<String>(4);

							for(int k = 0; k < 4; k++)
							{
								int optionNumber = k + 1;
								System.out.println("What is question "+ questionNumber + ", option number " + optionNumber + "?");
								String option = scan.nextLine();
								options.set(k, option);
							}

							System.out.println("Which option 1-4 is the correct anser");
							int optionNumber = Integer.parseInt(scan.nextLine());
							int rightAnswer = optionNumber - 1;


							Question question = new Question(actualQuestion, options, rightAnswer);
							questions.set(j, question);
						}
					

						Assessment test = new Assessment(questions);

						Chapter chapter = new Chapter(chapterName, sections, test);
					
				

					}

				
					CourseManager.getInstance().writeAllCourses();
					System.out.println("Your course "+ name + " is created! The course id is " + course.getId());
					

					

					break;
				}
				case "Enter a Course":
				{
					System.out.println("What class would you like to enter");
					String course= scan.nextLine();
					//convert string to course
					//courseMenu(course);
					break;
				}
				case "Log Out":
				{
					UserManager.getInstance().logout();
					return;
				}
				default :
				{
					System.err.println("Error! Invalid command entered. Please try again.");
				}
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
		while (true) {
			showSignInMenu();
			Scanner scan = new Scanner(System.in);
			int num = Integer.parseInt(scan.nextLine());
			String command = SIGN_IN_MENU[num-1];
			clearScreen();

			switch(command) {
				case "Log In": {
					System.out.println("What is your email?");
					String email= scan.nextLine();
					clearScreen();
					System.out.println("What is your password?");
					String pass= scan.nextLine();
					clearScreen();
					char[] password = new char[pass.length()];
					for (int i = 0; i < pass.length(); i++) {
						password[i] = pass.charAt(i);
					}
					boolean success = UserManager.getInstance().attemptLogin(email, password);
					if (success) {
						clearScreen();
						System.out.println("Login was a success.");
						System.out.println();
						homeMenu(email);
					} else {
						clearScreen();
						System.out.println("Incorrect email or password.");
						System.out.println();
					}
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
					System.out.println("What is your date of birth? (YYYY-MM-DD)");
					String birthday= scan.nextLine();
					clearScreen();
					LocalDate date = dateStringToDate(birthday);

					UserManager.getInstance().attemptSignup(email, ch, name, name, date);
					clearScreen();
					// YYYY-MM-DD
					//LocalDate date = dateStringToDate(date);
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
	}

	private static void showCourseMenu(Course course, Chapter chapter) {
		System.out.println("***************");
		System.out.println("Course: " + course.getTitle());
		System.out.println("Chapter: " + course.getChapterCount());
		System.out.println("Rating: ");
		System.out.println("***************");
		for(int i=0;i<COURSE_MENU.length;i++)
			System.out.println((i+1)+". "+COURSE_MENU[i]);
		System.out.println("***************");
		System.out.println("What would you like to do?:");
	}

	private static void courseMenu(Course course) {
		//add course and section to show course below

		showCourseMenu(course, course.getChapters().get(UserManager.getInstance().getLoggedInUser().getCourseProgressIn(course).getChaptersCompleted()));
		Scanner scan = new Scanner(System.in);
		int num = Integer.parseInt(scan.nextLine());
		String command = COURSE_MENU[num-1];
		clearScreen();

		switch(command)
		{
			case "Study Section": {
				//study section
				//print text for user to read
				break;
			}
			case "Take Quiz": {
				UIHandler.startAssessment(UserManager.getInstance().getLoggedInUser().getCourseProgressIn(course));
				break;
			}
			case "View/Leave Review": {
				ArrayList<Review> reviews;
				reviews = course.getReviews();
				for(Review review : reviews){
					System.out.println(review.toString());
				}
				reviewMenu(course);
				break;
			}
			case "View/Leave Comment": {
				ArrayList<Comment> comments;
				comments = course.getComments();
				for(Comment comment : comments){
					System.out.println(comment.toString());
				}
				commentMenu(course);
				break;
			}
			case "Drop Class": {
				System.out.println("What class would you like to drop");
				ArrayList<Course> courses = UserManager.getInstance().getLoggedInUser().getEnrolledCourses();  
				for(int i=0;i<courses.size();i++){
					System.out.println((i+1) + ". " + courses.get(i).getTitle());
				}
				int dropClass = Integer.parseInt(scan.nextLine());

				Course drop = courses.get(dropClass - 1);
				UserManager.getInstance().getLoggedInUser().drop(drop);
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

	private static void commentMenu(Course course) {
		showCommentMenu();
		Scanner scan = new Scanner(System.in);
		int num = Integer.parseInt(scan.nextLine());
		String command = COMMENT_MENU[num-1];
		clearScreen();
		
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
				clearScreen();
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

	private static void reviewMenu(Course course) {
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
				clearScreen();
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

	private static void clearScreen() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}  

	
}
