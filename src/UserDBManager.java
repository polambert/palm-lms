
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * UserDBManager
 * Provides methods for accessing User database
 * @author Parker Lambert
 */
public class UserDBManager {
	private final String USER_FOLDER 							= "./json/users/";
	private final String USER_FILE_EXTENSION 					= "json";
	private final String USER_FILE_IGNORE 						= "sample_user";

	private final String USER_OBJ_ID 							= "id";
	private final String USER_OBJ_FIRSTNAME 					= "firstName";
	private final String USER_OBJ_LASTNAME 						= "lastName";
	private final String USER_OBJ_EMAIL 						= "email";
	private final String USER_OBJ_DATEOFBIRTH 					= "dateOfBirth";
	private final String USER_OBJ_PASSWORD 						= "password";
	private final String USER_OBJ_CANCREATECOURSES 				= "canCreateCourses";

	private final String USER_OBJ_COURSEPROGRESSES 				= "courseProgresses";

	private final String COURSEPROGRESS_OBJ_COURSEID 			= "courseId";
	private final String COURSEPROGRESS_OBJ_CHAPTERSCOMPLETED 	= "chaptersCompleted";
	private final String COURSEPROGRESS_OBJ_SECTIONSCOMPLETED 	= "sectionsCompleted";
	private final String COURSEPROGRESS_OBJ_GRADES 				= "grades";
	private final String COURSEPROGRESS_OBJ_CERTIFICATE 		= "certificate";

	private final String CERTIFICATE_OBJ_DATECOMPLETED 			= "dateCompleted";
	private final String CERTIFICATE_OBJ_CERTIFICATEID 			= "certificateId";

	/**
	 * Constructor for UserDBManager
	 */
	public UserDBManager() {

	}


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

	/**
	 * Reads and returns all users from DB
	 * @return All users from DB
	 */
	public ArrayList<User> readUsersFromDB() {
		File userFolder = new File(USER_FOLDER);
		File[] userFiles = userFolder.listFiles();

		ArrayList<User> users = new ArrayList<>();

		for (File file : userFiles) {
			String[] fileSplit = file.getName().split("\\.");

			if (file.isFile() && 
			fileSplit[fileSplit.length - 1].equals(USER_FILE_EXTENSION) &&
			!fileSplit[0].equals(USER_FILE_IGNORE)) {
				// it is a json file
				// read it
				User user = readUserFile(file);

				users.add(user);
			}
		}

		return users;
	}

	private User readUserFile(File file) {
		User user = null;

		try {
			FileReader reader = new FileReader(USER_FOLDER + file.getName());
			JSONParser parser = new JSONParser();
			JSONObject userObj = (JSONObject) parser.parse(reader);

			// close the reader as soon as we no longer need it
			reader.close();

			// JSON object is loaded, now extract data
			UUID id = UUID.fromString((String) userObj.get(USER_OBJ_ID));
			String firstName = (String) userObj.get(USER_OBJ_FIRSTNAME);
			String lastName = (String) userObj.get(USER_OBJ_LASTNAME);
			String email = (String) userObj.get(USER_OBJ_EMAIL);
			LocalDate dob = dateStringToDate((String) userObj.get(USER_OBJ_DATEOFBIRTH));
			// don't load password
			boolean canCreateCourses = (boolean) userObj.get(USER_OBJ_CANCREATECOURSES);
			JSONArray courseProgressArr = (JSONArray) userObj.get(USER_OBJ_COURSEPROGRESSES);

			ArrayList<CourseProgress> courseProgresses = new ArrayList<>();

			for (int i = 0; i < courseProgressArr.size(); i++) {
				JSONObject courseProgressObj = (JSONObject) courseProgressArr.get(i);
				UUID courseId =  UUID.fromString((String) courseProgressObj.get(COURSEPROGRESS_OBJ_COURSEID));
				int chaptersCompleted = ((Long) courseProgressObj.get(COURSEPROGRESS_OBJ_CHAPTERSCOMPLETED)).intValue();
				int sectionsCompleted = ((Long) courseProgressObj.get(COURSEPROGRESS_OBJ_SECTIONSCOMPLETED)).intValue();
				
				JSONArray gradesArr = (JSONArray) courseProgressObj.get(COURSEPROGRESS_OBJ_GRADES);
				ArrayList<ArrayList<Double>> grades = new ArrayList<>();

				for (int j = 0; j < gradesArr.size(); j++) {
					JSONArray sectionGradesArr = (JSONArray) gradesArr.get(j);
					ArrayList<Double> sectionGrades = new ArrayList<>();

					for (int k = 0; k < sectionGradesArr.size(); k++) {
						Double grade = (Double) sectionGradesArr.get(k);
						sectionGrades.add(grade);
					}

					grades.add(sectionGrades);
				}

				JSONObject certificateObj = (JSONObject) courseProgressObj.get(COURSEPROGRESS_OBJ_CERTIFICATE);

				String dateString = (String) certificateObj.get(CERTIFICATE_OBJ_DATECOMPLETED);
				LocalDate certificateDate = null;
				if (!dateString.equals("0000-00-00")) {
					certificateDate = dateStringToDate(dateString);
				}

				String idString = (String) certificateObj.get(CERTIFICATE_OBJ_CERTIFICATEID);
				UUID certificateId = null;
				if (!idString.equals("")) {
					UUID.fromString(idString);
				}

				Course course = CourseManager.getInstance().getCourseById(courseId);
				System.out.println(course);
				CourseProgress courseProgress = new CourseProgress(course, chaptersCompleted, sectionsCompleted, grades);

				courseProgress.setDateCompleted(certificateDate);
				courseProgress.setCertificateId(certificateId);

				courseProgresses.add(courseProgress);
			}

			user = new User(id, firstName, lastName, email, dob, courseProgresses);
			user.setCanCreateCourses(canCreateCourses);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
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

	/**
	 * Writes a user to DB from a JSONObject
	 * This is only really meant for private use but there is no risk in making it public
	 * @param userObj JSONObject of User with hashed password
	 */
	public boolean writeUserToDB(JSONObject userObj) {
		UUID id = UUID.fromString((String) userObj.get(USER_OBJ_ID));

		try {
			FileWriter writer = new FileWriter(USER_FOLDER + id + ".json");

			writer.write(userObj.toJSONString());
			writer.flush();
			writer.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	/**
	 * Creates a new user
	 * @param user user to write
	 * @param hashedPassword the user's password, hashed in SHA-512
	 * @return true if success
	 */
	public boolean createNewUser(User user, String hashedPassword) {
		// create the object
		JSONObject userObj = new JSONObject();

		userObj.put(USER_OBJ_ID, user.getId().toString());
		userObj.put(USER_OBJ_FIRSTNAME, user.getFirstName());
		userObj.put(USER_OBJ_LASTNAME, user.getLastName());
		userObj.put(USER_OBJ_EMAIL, user.getEmail());
		userObj.put(USER_OBJ_DATEOFBIRTH, user.getDateOfBirth());
		userObj.put(USER_OBJ_PASSWORD, hashedPassword);

		// no grades or courses yet -- this is a brand new user

		// write to file
		return writeUserToDB(userObj);
	}
}
