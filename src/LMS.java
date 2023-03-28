import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

//Methods that contain arrays that are print later
public class LMS {
	private static final String[] COURSE_MENU = {
		"Study Section",
		"Take Quiz",
		"View/Leave Review",
		"View/Leave Comment",
		"View Grades",
		"Drop This Class",
		"Go Home"
	};
	private static final String[] HOME_MENU = {
		"View Enrolled Courses",
		"Enroll in a Course",
		"Create a Course",
        "Edit a Course",
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
    private static final String[] EDIT_MENU = {
		"Name",
		"Description",
		"Language",
        "Return to Course",
        "Title",
        "Edit Chapters",
        "Edit sections",
        "Edit final",
        "Go Home"

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
		System.out.println("Welcome, " + UserManager.getInstance().getLoggedInUser().getFullName());
		for(int i=0;i<HOME_MENU.length;i++)
			System.out.println((i+1)+". "+HOME_MENU[i]);
		System.out.println("***************");
		System.out.print("What would you like to do? ");
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
					
					System.out.println("Which course would you like to select?\n");

					for(int i=0; i<courses.size(); i++){
						System.out.println((i+1) + ". " + courses.get(i).getTitle());   
					}
					
					System.out.print("\nEnter a number: ");
					int enrollClass = Integer.parseInt(scan.nextLine());
					Course enroll = courses.get(enrollClass - 1);
					clearScreen();
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
						System.out.println("      Rating: " + courses.get(i).getRating());
					}
					
					System.out.print("Enter a number: ");
					int enrollClass = Integer.parseInt(scan.nextLine());
					Course course = courses.get(enrollClass - 1);
					
					boolean success = UserManager.getInstance().getLoggedInUser().enrollIn(course);
					UserManager.getInstance().writeAllUsers();

					clearScreen();

					if (success) {
						System.out.println("You are now enrolled in '" + course.getName() + "'.");
						System.out.println("Sending you to the course menu.\n");
						courseMenu(course);
					} else {
						System.out.println("An error has occured.");
						System.out.println("You were not able to be enrolled in '" + course.getName() + "'.\n");
					}

					break;
				}
				case "Create a Course":
				{
					if (!UserManager.getInstance().getLoggedInUser().canCreateCourses()) {
						System.out.println("You do not have permission to create a course.\n");
						return;
					}

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
							
                            //get options method 
							ArrayList<String> options = getOptions();

							System.out.println("Which option 1-4 is the correct answer");
							int optionNumber = Integer.parseInt(scan.nextLine());
							int rightAnswer = optionNumber - 1;


							Question question = new Question(actualQuestion, options, rightAnswer);
							questions.add(question);
						}
                        
					
						Assessment test = new Assessment(questions);

						Chapter chapter = new Chapter(chapterName, sections, test);
					
                        chaptersList.add(i, chapter);
				

					}

				
					CourseManager.getInstance().writeAllCourses();
					System.out.println("Your course "+ name + " is created! The course id is " + course.getId());
					

					

					break;
				}
                case "Edit a Course":
                {
                    courseEditMenu();
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

    public static void showCourseEditMenu() {
		System.out.println("*****Edit Course Menue*****");
		System.out.println("***************");
		for(int i=0;i<EDIT_MENU.length;i++)
			System.out.println((i+1)+". "+EDIT_MENU[i]);
		System.out.println("***************");
		System.out.print("What would you like to do? ");
	}


    public static void courseEditMenu() {
		clearScreen();
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the UUID of the course you want to edit?");
        String courseUuid = scan.nextLine();
        UUID uuid = UUID.fromString(courseUuid);
        Course course = CourseManager.getInstance().getCourseById(uuid);
                    
        clearScreen();
		while (true) {
			showCourseEditMenu();
			int num = Integer.parseInt(scan.nextLine());
			String command = EDIT_MENU[num-1];
			clearScreen();
            CourseManager.getInstance().writeAllCourses();

			switch(command) {
				case "Name": {
                    System.out.println("Current name is: " + course.getName());
                    System.out.println("What would you like to name the course?");
                    String newName = scan.nextLine();
                    course.setName(newName);
					break;
				}
				case "Description":{
                    System.out.println("Current description is: " + course.getDescription());
					System.out.println("What would you like to set as the description the course?");
                    String newDescription = scan.nextLine();
                    course.setDescription(newDescription);
                    break;
				}
				case "Language":{
                    System.out.println("Current language is: " + course.getLanguage());
                    System.out.println("What would you like to set as the language the course?");
                    String newLanguage= scan.nextLine();
                    course.setLanguage(newLanguage);
					break;
				}
                case "Title":{
                    System.out.println("Current title is: " + course.getTitle());
                    System.out.println("What would you like to set as the title the course?");
                    String newTitle= scan.nextLine();
                    course.setTitle(newTitle);
					break;
				}
                case "Go Home":{
                    System.out.println("Leaving course edit mode?");
                    //homeMenu();
                }
                case "Edit Chapters":{
                    
					break;
				}
                case "Edit sections":{
                    
					break;
				}
                case "Edit final":{
                    
					break;
				}
				default: {
					System.err.println("Error! Invalid command entered. Please try again.");
					break;
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
		System.out.print("What would you like to do? ");
	}

	public static void signInMenu() {
		clearScreen();

		while (true) {
			showSignInMenu();
			Scanner scan = new Scanner(System.in);
			int num = Integer.parseInt(scan.nextLine());
			String command = SIGN_IN_MENU[num-1];
			clearScreen();

			switch(command) {
				case "Log In": {
					System.out.println("You are logging in.");
					System.out.println("What is your email?");
					String email= scan.nextLine();
					clearScreen();
					System.out.println("You are logging in.");
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
					System.out.println("You are creating a new account.");
					System.out.println("What is your first name?");
					String firstName = scan.nextLine();
					clearScreen();
					System.out.println("You are creating a new account.");
					System.out.println("What is your last name?");
					String lastName = scan.nextLine();
					clearScreen();
					System.out.println("You are creating a new account.");
					System.out.println("What is your email?");
					String email= scan.nextLine();
					clearScreen();
					System.out.println("You are creating a new account.");
					System.out.println("What is your password?");
					String pass= scan.nextLine();
					clearScreen();
					char[] ch = new char[pass.length()];
					for (int i = 0; i < pass.length(); i++) {
						ch[i] = pass.charAt(i);
					}
					System.out.println("What is your date of birth? (YYYY-MM-DD)");
					String birthday= scan.nextLine();
					clearScreen();
					LocalDate date = dateStringToDate(birthday);

					boolean success = UserManager.getInstance().attemptSignup(email, ch, firstName, lastName, date);

					if (success) {
						System.out.println("Successfully created your account with email '" + email + "'.");
						System.out.println("Please log in.\n");
					}
					break;
				}
				case "Quit":{
					System.out.println("Thank you for using PALM.");

					System.exit(0);
					
					break;
				}
				default: {
					System.err.println("Error! Invalid command entered. Please try again.");
					break;
				}
			}
		}
	}

	private static void showCourseMenu(Course course) {
		System.out.println("******* Course Menu ******");
		System.out.println("Course: " + course.getTitle());
		System.out.println("Rating: " + course.getRating());
		
		CourseProgress progress = UserManager.getInstance().getLoggedInUser().getCourseProgressIn(course);
		int c = progress.getChaptersCompleted() + 1;
		int s = progress.getSectionsCompleted() + 1;

		// see if they're finished with course content
		if (progress.getCertificateId() != null) {
			// course has been completed
			System.out.println("You have completed this course.");
		} else if (c - 1 == course.getChapterCount() && progress.canTakeFinal()) {
			System.out.println("Ready to take final.");
		} else {
			int sLeft = course.getChapters().get(c-1).getSections().size() - s;

			if (sLeft > 0) {
				System.out.println("On Chapter " + c + ", Section " + s + " (" + sLeft + " more sections left in chapter).");
			} else if (sLeft == 0) {
				System.out.println("On Chapter " + c + ", Section " + s + " (no more sections in chapter).");
			} else if (progress.canTakeTest()) {
				System.out.println("Ready to take Chapter " + c + " test.");
			} else if (progress.canTakeFinal()) {
				System.out.println(progress.getCertificateId());
				System.out.println("Ready to take final.");
			}
		}

		System.out.println("**************************");
		for(int i=0;i<COURSE_MENU.length;i++) {
			if (COURSE_MENU[i].equals("Take Quiz")) {
				// see if they are able to take a test or a final
				if (progress.getCertificateId() != null) {
					// finished with course
					System.out.println((i+1) + ". View Certificate");
				} else if (progress.canTakeTest()) {
					System.out.println((i+1) + ". Take Test");
				} else if (progress.canTakeFinal()) {
					System.out.println((i+1) + ". Take Final");
				} else {
					System.out.println((i+1) + ". " + COURSE_MENU[i]);
				}
			} else {
				System.out.println((i+1) + ". " + COURSE_MENU[i]);
			}
		}
		System.out.println("**************************");
		System.out.print("What would you like to do? ");
	}

	private static void displaySection(Course course, int c, int s) {
		Chapter chapter = course.getChapters().get(c);
		Section section = chapter.getSections().get(s);
		System.out.println("Studying section of '" + course.getTitle() + "'");

		String text = "Chapter #" + (c+1) + ", Section #" + (s+1) + "\n\n";

		if (s == 0) {
			// first section, display chapter name
			text += "CHAPTER " + (c+1) + ": " + chapter.getName() + "\n";
		}

		text += "SECTION " + (c+1) + ": " + section.getName() + "\n\n";

		text += section.getText() + "\n\n";

		System.out.println(text);

		System.out.print("Type '1' if you would like to save this text to a file, anything else to return: ");
		Scanner scanner = new Scanner(System.in);

		if (scanner.nextLine().equals("1")) {
			// save to file
			System.out.print("Enter filename: ");
			String location = scanner.nextLine();
			File file = new File(location);
			try {
				FileWriter writer = new FileWriter(file);
				writer.write(text);
				writer.flush();
				writer.close();

				System.out.println("Section text successfully saved to " + location + "\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void courseMenu(Course course) {

		while (true) {
			showCourseMenu(course);
			Scanner scan = new Scanner(System.in);
			int num = Integer.parseInt(scan.nextLine());
			String command = COURSE_MENU[num-1];
			clearScreen();

			switch(command)
			{
				case "Study Section": {
					//study section
					//print text for user to read
					CourseProgress progress = UserManager.getInstance().getLoggedInUser().getCourseProgressIn(course);

					System.out.println("1. View Section I'm Currently On");
					System.out.println("2. View Specific Section");
					System.out.print("What would you like to do? ");
					String answer = scan.nextLine();
					clearScreen();
					
					if (answer.equals("2")) {
						// view specific section
						System.out.println("Viewing a Specific Section");
						int cc = course.getChapterCount();

						System.out.print("Chapter (" + cc + " available): ");
						int c = Integer.parseInt(scan.nextLine()) - 1;

						if (c >= 0 && c < course.getChapterCount()) {
							Chapter chapter = course.getChapters().get(c);

							int sc = chapter.getSectionCount();

							System.out.print("Section (" + sc + " in chapter): ");
							int s = Integer.parseInt(scan.nextLine()) - 1;

							if (s >= 0 && s < chapter.getSectionCount()) {
								clearScreen();

								displaySection(course, c, s);
							} else {
								System.out.println("Sorry, that is not a valid section number.");
							}
						} else {
							System.out.println("Sorry, that is not a valid chapter number.");
						}
					} else if (answer.equals("1")) {
						// view current section
						if (progress.hasFinishedCourse()) {
							System.out.println("You've already finished this course.\n");
							break;
						}
	
						if (progress.canTakeTest()) {
							System.out.println("I see that you're studying for a test.");
						}
						
						ArrayList<Chapter> chapters = course.getChapters();
						int c = progress.getChapterProgress();
						Chapter chapter = chapters.get(progress.getChapterProgress());
						
						ArrayList<Section> sections = chapter.getSections();
						int s = progress.getSectionProgress();
						
						displaySection(course, c, s);
					} else {
						System.out.println("Sorry, that's not an option. Returning you to course menu.\n");
					}
					break;
				}
				case "Take Quiz": {
					CourseProgress progress = UserManager.getInstance().getLoggedInUser().getCourseProgressIn(course);
					if (progress.getCertificateId() != null) {
						// finished course, show their certificate
						User user = UserManager.getInstance().getLoggedInUser();
						String name = user.getFirstName() + " " + user.getLastName();
						String text = "This certificate honors " + name + " for completion of the course '" + course.getTitle() + "'\n";
						text += "    Date Completed: " + progress.getDateCompleted() + "\n";
						text += "    Certificate ID: " + progress.getCertificateId() + "\n";

						System.out.println(text);
						System.out.println();
						System.out.print("Type '1' if you would like to save this certificate to a text file, anything else to return: ");

						Scanner scanner = new Scanner(System.in);
						String answer = scanner.nextLine();
						
						clearScreen();

						if (answer.equals("1")) {
							// save to text file.
							System.out.print("Where would you like it to be saved? ");
							String location = scanner.nextLine();
							File file = new File(location);
							try {
								FileWriter writer = new FileWriter(file);
								writer.write(text);
								writer.flush();
								writer.close();

								System.out.println("Certificate successfully saved to " + location);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} else {
						UIHandler.startAssessment(UserManager.getInstance().getLoggedInUser().getCourseProgressIn(course));
					}
					break;
				}
				case "View/Leave Review": {
					reviewMenu(course);
					break;
				}
				case "View/Leave Comment": {
					commentMenu(course);
					break;
				}
				case "View Grades": {
					System.out.println("Your current grades for '" + course.getTitle() + "':");
					System.out.println("Hyphens (--) mean that an assessment has not yet been completed.");
					System.out.println("Parentheses () indicate a chapter test.");
					System.out.println();

					ArrayList<ArrayList<Double>> grades = UserManager.getInstance().getLoggedInUser().getCourseProgressIn(course).getGrades();
					for (int i = 0; i < grades.size(); i++) {
						if (i == grades.size() - 1) {
							System.out.println("  Final Exam: " + grades.get(i).get(0));
						} else {
							System.out.print("  Chapter #" + (i+1) + ": ");

							for (int j = 0; j < grades.get(i).size(); j++) {
								if (j < grades.get(i).size() - 1) {
									System.out.print(grades.get(i).get(j) + ", ");
								} else {
									System.out.print("(" + grades.get(i).get(j) + ")");
								}
							}

							System.out.println();
						}
					}

					System.out.println();

					break;
				}
				case "Drop This Class": {
					System.out.print("Are you sure you want to drop '" + course.getTitle() + "'? (y/N) ");
					char c = scan.next().charAt(0);

					if (c == 'y' || c == 'Y') {
						// drop the class
						UserManager.getInstance().getLoggedInUser().drop(course);
						UserManager.getInstance().writeAllUsers();

						System.out.println("You are no longer enrolled in '" + course.getTitle() + "'.\n");
					}

					break;
				}
				
				case "Go Home": {
					return;
				}
				default : {

					System.err.println("Error! Invalid command entered. Please try again.");
				}
			}
		}
	}


	private static void showCommentMenu(Course course) {
		System.out.println("*****Comment Menu*****");
		System.out.println("Course: " + course.getTitle());
		System.out.println("******* Comments ********");
		System.out.println();

		ArrayList<Comment> comments = course.getComments();

		for (int i = 0; i < comments.size(); i++) {
			Comment comment = comments.get(i);
			System.out.println(comment.getComment());

			System.out.println("  By: " + comment.getAuthor().getFullName());
			System.out.println("  On: " + comment.getDate());
		}

		System.out.println();
		System.out.println("*************************");
		for(int i=0;i<COMMENT_MENU.length;i++)
			System.out.println((i+1)+". "+COMMENT_MENU[i]);
		System.out.println("*************************");
		System.out.println("What would you like to do?:");
	}

	private static void commentMenu(Course course) {
		showCommentMenu(course);
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

	private static void showReviewMenu(Course course) {
		System.out.println("*****Review Menu*****");
		System.out.println("Course: " + course.getTitle());
		System.out.println("Rating: " + course.getRating());
		System.out.println("********* Reviews **********");
		System.out.println();
		ArrayList<Review> reviews = course.getReviews();
		for (int i = 0; i < reviews.size(); i++) {
			Review review = reviews.get(i);
			for (int j = 0; j < 5; j++) {
				if (j < review.getRating()) System.out.print("*");
				else System.out.print("_");
			}
			System.out.print(" ");
			System.out.println(review.getText());
			User author = review.getAuthor();
			String name = author.getFirstName() + " " + author.getLastName();
			System.out.print("  " + review.getRating() + " star");
			if (review.getRating() != 1) System.out.print("s");
			System.out.println();
			System.out.println("  By: " + name);
			System.out.println("  On: " + review.getDate());
		}
		System.out.println();
		System.out.println("*************************");
		for(int i=0;i<REVIEW_MENU.length;i++)
			System.out.println((i+1)+". "+REVIEW_MENU[i]);
		
		System.out.println("*************************");
		System.out.println("What would you like to do?:");
	}

	private static void reviewMenu(Course course) {
		showReviewMenu(course);
		Scanner scan = new Scanner(System.in);
		int num = Integer.parseInt(scan.nextLine());
		String command = REVIEW_MENU[num-1];

		clearScreen();

		switch(command)
		{
			case "Leave Review":
			{
				System.out.print("What is your Review? ");
				String comment= scan.nextLine();
				System.out.print("What is your rating? [1-5] ");
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
		System.out.println("\n\n\n\n\n\n"); // visually shows clear if they scroll up
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private  static ArrayList<String> getOptions(){
        Scanner scaner = new Scanner(System.in);
        ArrayList<String> options = new ArrayList<String>(4);
        for(int k = 0; k < 4; k++)
        {
            int questionNumber = k + 1;
           
                
                int optionNumber = k + 1;
                System.out.println("What is question "+ questionNumber + ", option number " + optionNumber + "?");
                String option = scaner.nextLine();
                options.add(option);
                
                
            
        }
        return options;
    }
}
