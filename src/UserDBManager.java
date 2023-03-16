
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * UserDBManager
 * Provides methods for accessing User database
 * @author Parker Lambert
 */
public class UserDBManager {
	private final String USER_FOLDER 				= "./json/users/";
	private final String USER_FILE_EXTENSION 		= "json";
	private final String USER_FILE_IGNORE 		= "sample_user";

	private final String USER_OBJ_ID = "id";
	private final String USER_OBJ_FIRSTNAME = "firstName";
	private final String USER_OBJ_LASTNAME = "lastName";
	private final String USER_OBJ_EMAIL = "email";
	private final String USER_OBJ_DATEOFBIRTH = "dateOfBirth";
	private final String USER_OBJ_PASSWORD = "password";
	private final String USER_OBJ_CANCREATECOURSES = "canCreateCourses";

	private final String USER_OBJ_COURSEPROGRESSES = "courseProgresses";

	private final String USERPROGRESSES_OBJ_COURSEID = "courseId";
	private final String USERPROGRESSES_OBJ_CHAPTERSCOMPLETED = "chaptersCompleted";
	private final String USERPROGRESSES_OBJ_SECTIONSCOMPLETED = "sectionsCompleted";
	private final String USERPROGRESSES_OBJ_GRADES = "grades"
	private final String USERPROGRESSES_OBJ_CERTIFICATE = "certificate";

	private final String CERTIFICATE_OBJ_DATECOMPLETED = "dateCompleted";
	private final String CERTIFICATE_OBJ_CERTIFICATEID = "certificateId";

	/**
	 * Constructor for UserDBManager
	 */
	public UserDBManager() {

	}

	/**
	 * Reads and returns all users from DB
	 * @return All users from DB
	 */
	public ArrayList<User> readUsersFromDB() {
		File courseFolder = new File(COURSE_FOLDER);
		File[] courseFiles = courseFolder.listFiles();

		for (File file : courseFiles) {
			String[] fileSplit = file.getName().split("\\.");

			System.out.println(file.getName());

			System.out.println(fileSplit[fileSplit.length - 1]);

			if (file.isFile() && 
			fileSplit[fileSplit.length - 1].equals(COURSE_FILE_EXTENSION) &&
			!fileSplit[0].equals(COURSE_FILE_IGNORE)) {
				// it is a json file
				// read it
				try {
					FileReader reader = new FileReader(COURSE_FOLDER + file.getName());
					JSONParser parser = new JSONParser();
					JSONObject courseObj = (JSONObject) parser.parse(reader);

					// close the reader as soon as we no longer need it
					reader.close();

					// JSON object is loaded, now extract data
					UUID id = fromString((String) courseObj.get(USER.OBJ.GETID))
					firstName
					lastName
					email
					dateOfBirth
					password
					canCreateCourses
					courseProgresses

		return null;
	}

	/**
	 * Reads and returns one user from DB
	 * @param email Email of user to return
	 * @return User
	 */
	public User readUserFromDB(String email) {
		return null;
	}

	/**
	 * Writes all provided users to the database
	 * @param users Users to write to DB
	 */
	public void writeUsersToDB(ArrayList<User> users) {

	}

	/**
	 * Writes one user to DB
	 * @param email Email of user to write to DB
	 */
	public void writeUserToDB(String email) {

	}
}
