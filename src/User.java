
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;

public class User 
{
	private String firstName;
	private String lastName;
	private String email;
	private UUID id;
	private LocalDate dateOfBirth;
	private boolean canCreateCourses;
	private ArrayList<CourseProgress> courseProgresses = new ArrayList<>();
	


	public User(UUID id, String firstName, String lastName, String email, LocalDate dateOfBirth, ArrayList<CourseProgress> courseProgresses) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.courseProgresses = courseProgresses;
	}

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


	//getters and setters
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

	public boolean isCanCreateCourses() {
		return canCreateCourses;
	}

	public ArrayList<CourseProgress> getCourseProgresses() {
		return courseProgresses;
	}

	public void setCourseProgresses(ArrayList<CourseProgress> courseProgresses) {
		this.courseProgresses = courseProgresses;
	}
	public void setCanCreateCourses(boolean canCreateCourses) {

	}
	public ArrayList<Course> getEnrolledCourses() {
		return null;
	}


}
