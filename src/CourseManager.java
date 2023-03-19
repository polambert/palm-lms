
import java.util.ArrayList;
import java.util.UUID;

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
		this.dbManager = new CourseDBManager();
	}

	/**
	 * Public getInstance for singleton CourseManager
	 * @return The CourseManager
	 */
	public static CourseManager getInstance() {
		if (CourseManager.courseManager == null) {
			CourseManager.courseManager = new CourseManager();
		}
		
		return CourseManager.courseManager;
	}

	public Course getCourseById(UUID id) {
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getId().equals(id)) {
				return courses.get(i);
			}
		}

		return null;
	}

	public boolean loadAllCourses() {
		courses = dbManager.readCoursesFromDB();

		return false;
	}

	// getter and setter...
	public ArrayList<Course> getCourses() {
		return this.courses;
	}
}
