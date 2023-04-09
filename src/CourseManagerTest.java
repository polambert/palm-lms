import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.PasswordAuthentication;
import java.time.LocalDate;
import java.util.UUID;

import javax.security.auth.kerberos.KerberosCredMessage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * CourseManagerTest class for testing CourseManager Methods
 * @author Mamudu Bah
 */
public class CourseManagerTest {
    /* getEnrolledCourses */
    @Test
    public void testGetEnrolledCoursesValid() {
        CourseManager courseManager = CourseManager.getInstance();
        courseManager.loadAllCourses();
        String name = courseManager.getCourses().get(0).getName();
        User user = new User(UUID.randomUUID(), "first", "last", "email", LocalDate.now(), new ArrayList<CourseProgress>());
        user.enrollIn(courseManager.getCourses().get(0));
        ArrayList<Course> userEnrolledCourses = courseManager.getEnrolledCourses(user);
        assertEquals(userEnrolledCourses.get(0).getName(), name);
    }
    @Test
    public void testGetEnrolledCoursesInvalidNoEnrolledCourses() {
        CourseManager courseManager = CourseManager.getInstance();
        courseManager.loadAllCourses();
        User user = new User(UUID.randomUUID(), "first", "last", "email", LocalDate.now(), new ArrayList<CourseProgress>());
        ArrayList<Course> userEnrolledCourses = courseManager.getEnrolledCourses(user);
        assertTrue(userEnrolledCourses.isEmpty());
    }
    @Test
    public void testGetEnrolledCoursesAfterDroppedCourse() {
        CourseManager courseManager = CourseManager.getInstance();
        courseManager.loadAllCourses();
        User user = new User(UUID.randomUUID(), "first", "last", "email", LocalDate.now(), new ArrayList<CourseProgress>());
        user.enrollIn(courseManager.getCourses().get(0));
        user.drop(courseManager.getCourses().get(0));
        ArrayList<Course> userEnrolledCourses = courseManager.getEnrolledCourses(user);
        assertFalse(userEnrolledCourses.contains(courseManager.getCourses().get(0)));
    }
    @Test
    public void testGetEnrolledCoursesNullUser() {
        CourseManager courseManager = CourseManager.getInstance();
        ArrayList<Course> userEnrolledCourses = courseManager.getEnrolledCourses(null);
        assertNull(userEnrolledCourses);
    }

    /* writeAllCourses */
    @Test
    public void testWriteAllCourses() {

    }

    /* getCourseMadeByUser */
    @Test
    public void testGetCoursesMadeByUser() {
        
    }

    /* createCourse */
    @Test
    public void testCreateCourseValid() {
        CourseManager courseManager = CourseManager.getInstance();
        UserManager userManager = UserManager.getInstance();
        char[] password = {'1', '2', '3', '4'};
        userManager.attemptLogin("AllieAnderson@gmail.com", password);
        Course course = courseManager.createCourse("pyt", "learn quick", "python", "learn python today");
        assertEquals("pyt", course.getName());
    }
    @Test
    public void testCreateCourseInvalid() {
        CourseManager courseManager = CourseManager.getInstance();
        UserManager userManager = UserManager.getInstance();
        char[] password = {'1', '2', '3', '4'};
        userManager.attemptLogin("AllieAnderson@gmail.com", password);
        Course course = courseManager.createCourse("  ", "", "", "");
        assertNull(course);
    }
    @Test
    public void testCreateCourse() {
        CourseManager courseManager = CourseManager.getInstance();
    }
    @Test
    public void testCreateCourseNull() {
        CourseManager courseManager = CourseManager.getInstance();
        UserManager userManager = UserManager.getInstance();
        char[] password = { '1', '2', '3', '4'};
        userManager.attemptSignup("Bender@gmail.com", password, "first", "last", LocalDate.now());
        userManager.attemptLogin("Bender@gmail.com", password);
        Course course = courseManager.createCourse(null, null, null, null);
        assertNull(course.getName());
    }
}
