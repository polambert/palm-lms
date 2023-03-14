
import java.util.ArrayList;
import java.util.UUID;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * CourseDBManager
 * Manages reading and writing courses directly to database
 * @author Parker Lambert
 */
public class CourseDBManager {
	private final String COURSE_FOLDER = "./json/courses/";
	private final String COURSE_FILE_EXTENSION = "json";
	private final String COURSE_FILE_IGNORE = "sample_course";

	private final String COURSE_OBJ_ID = "id";
	private final String COURSE_OBJ_LANGUAGE = "language";
	private final String COURSE_OBJ_TITLE = "title";
	private final String COURSE_OBJ_NAME = "name";
	private final String COURSE_OBJ_AUTHOR = "author";

	/**
	 * Constructs a CourseDBManager
	 */
	public CourseDBManager() {

	}

	/**
	 * Reads and returns all courses in the database
	 * @return All courses in the database
	 */
	public ArrayList<Course> readCoursesFromDB() {
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

					// JSON object is loaded, now extract data
					UUID id = UUID.fromString((String) courseObj.get(COURSE_OBJ_ID));
					String language = (String) courseObj.get(COURSE_OBJ_LANGUAGE);
					String title = (String) courseObj.get(COURSE_OBJ_TITLE);
					String name = (String) courseObj.get(COURSE_OBJ_NAME);
					UUID authorId = UUID.fromString((String) courseObj.get(COURSE_OBJ_AUTHOR));

					System.out.println(title);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	/**
	 * Reads and returns a single specified course
	 * @param id The course in question
	 * @return The Course in question
	 */
	public Course readCourseFromDB(UUID id) {
		return null;
	}

	/**
	 * Writes all of the given courses to the database
	 * @param courses Courses to write
	 */
	public void writeCoursesToDB(ArrayList<Course> courses) {

	}

	/**
	 * Writes the specified course to the database
	 * @param course Course to write
	 */
	public void writeCourseToDB(Course course) {

	}
}
