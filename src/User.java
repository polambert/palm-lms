
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Stores information and methods for a User in LMS
 * @author Luke Lane, Parker Lambert
 */
public class User 
{
	private String firstName;
	private String lastName;
	private String email;
	private UUID id;
	private LocalDate dateOfBirth;
	private boolean canCreateCourses;
	private ArrayList<CourseProgress> courseProgresses = new ArrayList<>();

	/**
	 * Constructs a User
	 * @param id User's ID
	 * @param firstName first name
	 * @param lastName last name
	 * @param email email
	 * @param dateOfBirth date of birth
	 * @param courseProgresses list of progress in all enrolled courses
	 */
	public User(UUID id, String firstName, String lastName, String email, LocalDate dateOfBirth, ArrayList<CourseProgress> courseProgresses) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.courseProgresses = courseProgresses;
	}

	/**
	 * Enrolls the user in <course>
	 * @param course Course to enroll user in
	 * @return true if success
	 */
    public boolean enrollIn(Course course) {
        CourseProgress cp = new CourseProgress(course);
        courseProgresses.add(cp);
        return true;
    }

	/**
	 * Drops specified course
	 * @param course Course to drop
	 * @return true if success
	 */
    public boolean drop(Course course) {
        for (int i = 0; i < courseProgresses.size(); i++) {
            if (courseProgresses.get(i).getCourse().getId().equals(course.getId())) {
                courseProgresses.remove(i);
                return true;
            }
        }

        return false;
    }

	/**
	 * Returns full name (firstName + " " + lastName)
	 * @return full name
	 */
	public String getFullName() {
		return firstName + " " + lastName;
	}

	/**
	 * Returns a string representation of the User used for debug purposes
	 * @return string representation debug only
	 */
	public String toString() {
		String s = "[User] id: " + id + "\n";
		s += "\tfirstName: " + firstName + "\n";
		s += "\tlastName: " + lastName + "\n";
		s += "\temail: " + email + "\n";
		s += "\tdateOfBirth: " + dateOfBirth + "\n";
		s += "\tcanCreateCourses: " + canCreateCourses + "\n";

		s += "\tcourseProgresses: \n";
		for (int i = 0; i < courseProgresses.size(); i++) {
			s += "\t\t" + courseProgresses.get(i).toString().replace("\n", "\n\t\t");
		}

		return s;
	}

	/**
	 * Returns CourseProgres in specified Course
	 * @param course Course to get progress in
	 * @return User's CourseProgress in Course
	 */
    public CourseProgress getCourseProgressIn(Course course) {
        for (int i = 0; i < courseProgresses.size(); i++) {
            if (course.getId().equals(courseProgresses.get(i).getCourse().getId())) {
                return courseProgresses.get(i);
            }
        }
        return null;
    }


	// getters and setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean canCreateCourses() {
		return canCreateCourses;
	}

	public ArrayList<CourseProgress> getCourseProgresses() {
		return this.courseProgresses;
	}

	public void setCourseProgresses(ArrayList<CourseProgress> courseProgresses) {
		this.courseProgresses = courseProgresses;
	}
	public void setCanCreateCourses(boolean canCreateCourses) {
        this.canCreateCourses = canCreateCourses;
	}
	public ArrayList<Course> getEnrolledCourses() {
		ArrayList<Course> courses = new ArrayList<>();

		for (int i = 0; i < courseProgresses.size(); i++) {
			courses.add(courseProgresses.get(i).getCourse());
		}

		return courses;
	}


}
