import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
/**
 * CourseDBManagerTest
 * Contains test cases for CourseDBManager
 * @author Ayush Patel
 */

public class UserDBManagerTest 
{
   
    private ArrayList<User> userList = UserManager.getInstance().getUsers();

    @BeforeEach
	public void setup() {
		userList.clear();
        userList.add(new User(null, null, null, null, null, null));
        userList.add(new User(null, null, null, null, null, null));
        UserManager.getInstance().writeAllUsers();		
	}

    @AfterEach
	public void tearDown() {
        UserManager.getInstance().getUsers().clear();
		UserManager.getInstance().writeAllUsers();
	}

    @Test
    public void getIdFromEmailValid(){
        String email = "parker@test.com";
        UUID id = new UserDBManager().getIdFromEmail(email);
        assertEquals("a955b664-b017-498a-bf38-78946f4a6c9a", id.toString());
    }

    @Test
    public void getIdFromEmailWrong(){
        String email = "parker@test.com";
        UUID id = new UserDBManager().getIdFromEmail(email);
        assertNotEquals("a955b665-b017-498a-bf38-78946f4a6c9a", id.toString());
    }

    @Test
    public void readUserFromDBPass(){
        String email = "parker@test.com";
        User user = new UserDBManager().readUserFromDB(email);
        String testName = user.getFirstName();
        //assertEquals("parker@test.com", user.getEmail());
        assertEquals("parker", testName);
    }

    @Test
    public void readUserFromDBFail(){

    }

    @Test
    public void createNewUserPass(){

    }

    @Test
    public void createNewUserFail(){

    }

    @Test
    public void attemptLoginPass(){

    }

    @Test
    public void attemptLoginFail(){

    }



}
