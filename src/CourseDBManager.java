
package lms;

import java.util.ArrayList;
import java.util.UUID;

/**
 * CourseDBManager
 * Manages reading and writing courses directly to database
 * @author Parker Lambert
 */
public class CourseDBManager {
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
