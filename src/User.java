import java.util.ArrayList;
import java.util.UUID;

public class User 
{
    private String firstName;
    private String lastName;
    private String email;
    private UUID id;
    private date dateOfBirth;
    private boolean canCreateCourses;
    private ArrayList<CourseProgress> courseProgresses = new ArrayList<>();



    public User(String name, String email, Date dateOfBirth, ArrayList<CourseProgress> courseProgresses){

    }
    public void setCanCreateCourses(boolean canCreateCourses) {

    }
    public ArrayList<Course> getEnrolledCourses() {

    }


}
