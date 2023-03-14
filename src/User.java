import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class User 
{
    private String firstName;
    private String lastName;
    private String email;
    private UUID id;
    private Date dateOfBirth;
    private boolean canCreateCourses;
    private ArrayList<CourseProgress> courseProgresses = new ArrayList<>();


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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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


    public User(String name, String email, Date dateOfBirth, ArrayList<CourseProgress> courseProgresses){

    }
    public void setCanCreateCourses(boolean canCreateCourses) {

    }
    public ArrayList<Course> getEnrolledCourses() {

    }


}
