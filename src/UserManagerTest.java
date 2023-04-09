import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Luke Lane
 */
public class UserManagerTest{
    User Luke = new User(UUID.randomUUID(), "Luke", "Lane", "Luke@test.com", LocalDate.of(2002, 2, 6), null);

    @BeforeEach
    public void setUp(){

        UserManager userManager = UserManager.getInstance();
        userManager.loadAllUsers();
        ArrayList<User> users = userManager.getUsers();
        userManager.addUser(Luke, users);
    }
    @Test
    public void testGetLoggedInUserNull(){
        assertNull(UserManager.getInstance().getLoggedInUser());

    }
    @Test
    public void testAttemptSignUp(){
        UserManager userManager = UserManager.getInstance();
        userManager.loadAllUsers();
        ArrayList<User> users = userManager.getUsers();
        userManager.addUser(Luke, users);
        char[] pass = {'p','a','s','s'};
        userManager.attemptSignup("Luke@test.com", pass, "Luke", "Lane", LocalDate.of(2002, 2, 6));
        assertEquals(Luke, userManager.getLoggedInUser());
    }
    @Test
    public void attemptLogin(){
        UserManager userManager = UserManager.getInstance();
        char[] pass = {'p','a','s','s'};    
        assertTrue(userManager.attemptLogin("Luke@test.com", pass));
    }
    @Test
    public void testGetLoggedInUserId(){
        UserManager userManager = UserManager.getInstance();
        char[] pass = {'p','a','s','s'};
        userManager.attemptLogin("Luke@test.com", pass);
        assertEquals(userManager.getLoggedInUser().getId(),userManager.getIdFromEmail("Luke@test.com"));

    }
    @Test
    public void testGetUsersFirstName(){
        UserManager userManager = UserManager.getInstance();
        userManager.loadAllUsers();
        ArrayList<User> users = userManager.getUsers();
        userManager.addUser(Luke, users);
        String name = users.get(users.size()-1).getFirstName();
        assertEquals(name, "Luke");
    }
    @Test
    public void testGetUsersLastName(){
        UserManager userManager = UserManager.getInstance();
        userManager.loadAllUsers();
        ArrayList<User> users = userManager.getUsers();
        userManager.addUser(Luke, users);
        String name = users.get(users.size()-1).getLastName();
        assertEquals(name, "Lane");
    }
    @Test
    public void testGetUsersFullName(){
        UserManager userManager = UserManager.getInstance();
        userManager.loadAllUsers();
        ArrayList<User> users = userManager.getUsers();
        userManager.addUser(Luke, users);
        String name = users.get(users.size()-1).getFullName();
        assertEquals(name, "Luke Lane");
    }

    @Test
    public void testGetUsers(){
        UserManager userManager = UserManager.getInstance();
        ArrayList<User> users = userManager.getUsers();
        assertEquals(users, userManager.getUsers());
    }
    @Test
    public void testGetUsersFullEmail(){
        UserManager userManager = UserManager.getInstance();
        userManager.loadAllUsers();
        ArrayList<User> users = userManager.getUsers();
        userManager.addUser(Luke, users);
        String email = users.get(users.size()-1).getEmail();
        assertEquals(email, "Luke@test.com");
    }
    @Test
    public void testGetUsersBirthdate(){
        UserManager userManager = UserManager.getInstance();
        userManager.loadAllUsers();
        ArrayList<User> users = userManager.getUsers();
        userManager.addUser(Luke, users);
        LocalDate birth = users.get(users.size()-1).getDateOfBirth();
        assertEquals(birth, LocalDate.of(2002, 2, 6));
    }

    @Test
    public void testGetUserFromId(){
        UserManager userManager = UserManager.getInstance();
        userManager.loadAllUsers();
        ArrayList<User> users = userManager.getUsers();
        userManager.addUser(Luke, users);
        UUID id = users.get(users.size()-1).getId();
        assertEquals(id, userManager.getLoggedInUser().getId());
    }
    @Test
    public void testGetLoggedInUserCourses(){
        UserManager userManager = UserManager.getInstance();
        userManager.loadAllUsers();
        ArrayList<User> users = userManager.getUsers();
        userManager.addUser(Luke, users);
        ArrayList<Course> courses = users.get(users.size()-1).getEnrolledCourses();
        assertEquals(courses, userManager.getLoggedInUser().getEnrolledCourses());
    }

   @Test
   public void testLogout(){
    UserManager userManager = UserManager.getInstance();
    userManager.logout();
    assertEquals(null, userManager.getLoggedInUser());
   }

}