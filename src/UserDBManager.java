
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.math.BigInteger;

import java.security.MessageDigest;

/**
 * UserDBManager
 * Provides methods for accessing User database
 * @author Parker Lambert
 */
public class UserDBManager extends DataConstants {
	private final String HASH_ALGORITHM = "SHA-512";

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
			//System.out.println(file.getName());
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
				//System.out.println(course);
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
		// get their id
		String id = getIdFromEmail(email).toString();
		File file = new File(USER_FOLDER + id + ".json");

		return readUserFile(file);
	}

	/**
	 * Writes all provided users to the database
	 * @param users Users to write to DB
	 */
	public boolean writeUsersToDB(ArrayList<User> users) {
		for (int i = 0; i < users.size(); i++) {
			writeUserToDB(users.get(i));
		}

		return true;
	}

	/**
	 * Writes one user to DB
	 * @param user User to write
	 */
	public void writeUserToDB(User user) {
		JSONObject userObj = new JSONObject();

		userObj.put(USER_OBJ_ID, user.getId().toString());
		userObj.put(USER_OBJ_FIRSTNAME, user.getFirstName());
		userObj.put(USER_OBJ_LASTNAME, user.getLastName());
		userObj.put(USER_OBJ_EMAIL, user.getEmail());
		userObj.put(USER_OBJ_DATEOFBIRTH, user.getDateOfBirth().toString());
		userObj.put(USER_OBJ_CANCREATECOURSES, user.canCreateCourses());

		// get their password
		try {
			File file = new File(USER_FOLDER + user.getId() + ".json");
			FileReader reader = new FileReader(file);
			JSONParser parser = new JSONParser();
			JSONObject oldUserObj = (JSONObject) parser.parse(reader);
			userObj.put(USER_OBJ_PASSWORD, oldUserObj.get(USER_OBJ_PASSWORD));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Unable to load password for user " + user.getId());
			return;
		}

		JSONArray courseProgressArr = new JSONArray();
		ArrayList<CourseProgress> courseProgresses = user.getCourseProgresses();

		for (int i = 0; i < courseProgresses.size(); i++) {
			CourseProgress courseProgress = courseProgresses.get(i);
			JSONObject courseProgressObj = new JSONObject();

			JSONArray gradesArr = new JSONArray();
			ArrayList<ArrayList<Double>> grades = courseProgress.getGrades();

			JSONObject certificateObj = new JSONObject();

			for (int j = 0; j < grades.size(); j++) {
				JSONArray gradesArrArr = new JSONArray();

				for (int k = 0; k < grades.get(j).size(); k++) {
					gradesArrArr.add(grades.get(j).get(k));
				}

				gradesArr.add(gradesArrArr);
			}

			if (courseProgress.getCertificateId() != null) {
				certificateObj.put(CERTIFICATE_OBJ_CERTIFICATEID, courseProgress.getCertificateId().toString());
			} else {
				certificateObj.put(CERTIFICATE_OBJ_CERTIFICATEID, "");
			}

			if (courseProgress.getDateCompleted() != null) {
				certificateObj.put(CERTIFICATE_OBJ_DATECOMPLETED, courseProgress.getDateCompleted().toString());
			} else {
				certificateObj.put(CERTIFICATE_OBJ_DATECOMPLETED, "");
			}

			courseProgressObj.put(COURSEPROGRESS_OBJ_COURSEID, courseProgress.getCourse().getId());
			courseProgressObj.put(COURSEPROGRESS_OBJ_CHAPTERSCOMPLETED, courseProgress.getChaptersCompleted());
			courseProgressObj.put(COURSEPROGRESS_OBJ_SECTIONSCOMPLETED, courseProgress.getSectionsCompleted());
			courseProgressObj.put(COURSEPROGRESS_OBJ_GRADES, gradesArr);
			courseProgressObj.put(COURSEPROGRESS_OBJ_CERTIFICATE, certificateObj);
		}

		userObj.put(USER_OBJ_COURSEPROGRESSES, courseProgressArr);

		writeUserToDB(userObj);
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
		userObj.put(USER_OBJ_CANCREATECOURSES, false);

		// no grades or courses yet -- this is a brand new user

		// write to file
		return writeUserToDB(userObj);
	}

	public UUID getIdFromEmail(String email) {
		try {
			FileReader reader = new FileReader(new File(USER_LOGIN_LOOKUP));
			JSONParser parser = new JSONParser();
			JSONObject lookupObj = (JSONObject) parser.parse(reader);

			reader.close();

			String id = (String) lookupObj.get(email);

			if (id != null) {
				return UUID.fromString(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void updateLoginLookup(ArrayList<User> users) {
		JSONObject lookupObj = new JSONObject();

		for (int i = 0; i < users.size(); i++) {
			UUID id = users.get(i).getId();
			String email = users.get(i).getEmail();

			lookupObj.put(email, id.toString());
		}

		// write to file
		try {
			FileWriter writer = new FileWriter(new File(USER_LOGIN_LOOKUP));
			writer.write(lookupObj.toJSONString());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User attemptLogin(String email, char[] password) {
		UUID id = getIdFromEmail(email);

		try {
			File file = new File(USER_FOLDER + id.toString() + ".json");
			FileReader reader = new FileReader(file);
			JSONParser parser = new JSONParser();
			JSONObject userObj = (JSONObject) parser.parse(reader);

			reader.close();
			
			// get password from userObj
			String storedPW = (String) userObj.get(USER_OBJ_PASSWORD);

			// hash given password
			String hashedPW = hashPassword(password);

			if (hashedPW.equals(storedPW)) {
				// passwords match
				// load and return the user
				return readUserFromDB(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String hashPassword(char[] password) {
		MessageDigest md = null;

		try {
			// hash the password
			md = MessageDigest.getInstance(HASH_ALGORITHM);
			
			// a String is constructed with the password, but it should be
			//   immediately garbage collected since ownership isn't passed.
			byte[] messageDigest = md.digest(new String(password).getBytes());

			// convert into hex
			BigInteger number = new BigInteger(1, messageDigest);

			return number.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
