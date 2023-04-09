import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.UUID;

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

    /* GetCourseByID */
    @Test
    public void testGetCourseByIDValid() {
        //Valid interaction
        CourseManager courseManager = CourseManager.getInstance();
        courseManager.loadAllCourses();
        UUID id = UUID.fromString("5d2e37c5-4f9c-4bfc-a0ea-4b879f7b6231");        Course course = courseManager.getCourseById(id);
        assertNotNull(course);
    }
    @Test
    public void testGetCourseByIDInvalid() {
        //Not a courseId in database
        CourseManager courseManager = CourseManager.getInstance();
        courseManager.loadAllCourses();
        UUID id = UUID.fromString("5d2e37f5-4f9c-4bfc-a0ea-4b879f7b6231");        Course course = courseManager.getCourseById(id);
        assertNull(course);
    }
    @Test
    public void testGetCourseByIDEmpty() {
        //Id is empty
        CourseManager courseManager = CourseManager.getInstance();
        courseManager.loadAllCourses();
        UUID id = UUID.fromString("");   
        Course course = courseManager.getCourseById(id);
        assertNull(course);
    }
    @Test
    public void testGetCourseByIDNull() {
        //ID is null
        CourseManager courseManager = CourseManager.getInstance();
        courseManager.loadAllCourses();
        UUID id = UUID.fromString(null);
        Course course = courseManager.getCourseById(id);
        assertNull(course);
    }

    /* getCourseMadeByUser */
    @Test
    public void testGetCoursesMadeByUserValid() {
        //Valid no courses made by user
        CourseManager courseManager = CourseManager.getInstance();
        courseManager.loadAllCourses();
        User user = new User(UUID.randomUUID(), "first", "last", "email", LocalDate.now(), new ArrayList<CourseProgress>());
        ArrayList<Course> userCourse = courseManager.getCoursesMadeByUser(user);
        assertTrue(userCourse.isEmpty());
    }
    @Test
    public void testGetCoursesMadeByUserInvalid() {
        //Invalid no UUID
        CourseManager courseManager = CourseManager.getInstance();
        courseManager.loadAllCourses();
        User user = new User(null, "first", "last", "email", LocalDate.now(), new ArrayList<CourseProgress>());
        ArrayList<Course> userCourse = courseManager.getCoursesMadeByUser(user);
        assertTrue(userCourse.isEmpty());
        
    }
    @Test
    public void testGetCoursesMadeByUserNull() {
        //Valid no user entry
        CourseManager courseManager = CourseManager.getInstance();
        courseManager.loadAllCourses();
        ArrayList<Course> userCourse = courseManager.getCoursesMadeByUser(null);
        assertTrue(userCourse.isEmpty());
    }

    /* createCourse */
    @Test
    public void testCreateCourseValid() {
        CourseManager courseManager = CourseManager.getInstance();
        UserManager userManager = UserManager.getInstance();
        char[] password = {'P', 'a', 's', 's'};
        userManager.attemptLogin("ScoutSolace@gmail.com", password); // Valid Course Creator
        Course course = courseManager.createCourse("pyt", "learn quick", "python", "learn python today");
        assertEquals(course.getName(), "pyt");
    }
    @Test
    public void testCreateCourseInvalid() {
        CourseManager courseManager = CourseManager.getInstance();
        UserManager userManager = UserManager.getInstance();
        char[] password = {'P', 'a', 's', 's'};
        userManager.attemptLogin("ScoutSolace@gmail.com", password); // Valid Course Creator
        Course course = courseManager.createCourse("", "", "", "");
        assertNull(course);
    }
    @Test
    public void testCreateCourseNotAllowed() {
        CourseManager courseManager = CourseManager.getInstance();
        UserManager userManager = UserManager.getInstance();
        char[] password = {'1', '2', '3', '4'};
        userManager.attemptLogin("AllieAnderson@gmail.com", password); // Invalid Course Creator
        Course course = courseManager.createCourse("", "", "", "");
        assertNull(course);
    }
    @Test
    public void testCreateCourseNull() {
        CourseManager courseManager = CourseManager.getInstance();
        UserManager userManager = UserManager.getInstance();
        char[] password = {'P', 'a', 's', 's'};
        userManager.attemptLogin("ScoutSolace@gmail.com", password); // Valid Course Creator
        Course course = courseManager.createCourse(null, null, null, null);
        assertNull(course);
    }
}
