
package lms;

import java.util.ArrayList;

/**
 * CourseManager
 * Stores and manages courses during runtime
 * @author Parker Lambert
 */
public class CourseManager {
	private ArrayList<Course> courses;
	private static CourseManager courseManager;
	private CourseDBManager dbManager;

	/**
	 * Private constructor for singleton CourseManager
	 */
	private CourseManager() {

	}

	/**
	 * Public getInstance for singleton CourseManager
	 * @return The CourseManager
	 */
	public static CourseManager getInstance() {
		if (CourseManager.courseManager == null) {
			CourseManager.courseManager = new CourseManager();
		} else {
			return CourseManager.courseManager;
		}
	}

	// getter and setter...
}
