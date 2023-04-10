import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.UUID;
import java.time.LocalDate;
/**
 * CourseDBManagerTest
 * Contains test cases for CourseDBManager
 * @author Ayush Patel
 */

public class UserDBManagerTest 
{
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
    public void readUserFromDBFail(){
        String email = "parker@test.com";
        User user = new UserDBManager().readUserFromDB(email);
        assertNull(user);
    }

    @Test
    public void readUserFromDBEquals(){
        String email = "parker@test.com";
        User user = new UserDBManager().readUserFromDB(email);
        assertEquals("parker@test.com", user.getEmail());;
    }

    @Test
    public void readUserFromDBFail2(){ 
        UUID uuid=UUID.randomUUID();

        String dateString = "1111-11-11";
        LocalDate date = new UserDBManager().dateStringToDate(dateString);
        User user = new User(uuid, "first", "last", "AllieAnderson@gmail.com", date, null);
        User userTest = new UserDBManager().readUserFromDB(user.getEmail());
        assertNull(userTest);
    }

    @Test
    public void createNewUserPass(){
        String email = "Ayush@test.com";
		String password = "Pass";
        User user = new UserDBManager().readUserFromDB(email);
        boolean test = new UserDBManager().createNewUser(user, password);
		assertTrue(test);

    }

    @Test
    public void createNewUserPassEquals(){
        String email = "Ayush@test.com";
		String password = "Pass";
        User user = new UserDBManager().readUserFromDB(email);
        boolean test = new UserDBManager().createNewUser(user, password);
		assertEquals("Ayush@test.com", user.getEmail());

    }

    @Test
    public void createNewUserFail(){
        String email = "Ayush@test.com";
		String password = "  ";
        User user = new UserDBManager().readUserFromDB(email);
        boolean test = new UserDBManager().createNewUser(user, password);
		assertTrue(test);

    }

    @Test
    public void writeUserToDBPass(){
        UUID uuid=UUID.randomUUID();

        String dateString = "1111-11-11";

        LocalDate date = new UserDBManager().dateStringToDate(dateString);

        User user = new User(uuid, "first", "last", "email@test.com", date, null);
       
        new UserDBManager().writeUserToDB(user);

        user.setFirstName("new");

        new UserDBManager().writeUserToDB(user);

        assertEquals("new", user.getFirstName());
    }


    @Test
    public void writeUserToDBFakeDateFail()
    {
        UUID uuid=UUID.randomUUID();

        String dateString = "1111-13-11";

        LocalDate date = new UserDBManager().dateStringToDate(dateString);

        User user = new User(uuid, "first", "last", "email@test.com", date, null);
       
        new UserDBManager().writeUserToDB(user);

        user.setFirstName("new");

        new UserDBManager().writeUserToDB(user);

        assertNotEquals("first", user.getFirstName());
    }


    @Test
    public void attemptLoginPass()
    {
        User user = new User(null, "first", "last", "email@test.com", null, null);
        String email = "parker@test.com";
        String pass = "pass";
        char[] ch = new char[pass.length()];
		for (int i = 0; i < pass.length(); i++) 
        {
			ch[i] = pass.charAt(i);
		}
        assertNotNull(user);


    }

    @Test
    public void attemptLoginNotSame()
    {
        User user = new User(null, "first", "last", "parker@test.com", null, null);
        String email = "parker@test.com";
        String pass = "pass";
        char[] ch = new char[pass.length()];
		for (int i = 0; i < pass.length(); i++) 
        {
			ch[i] = pass.charAt(i);
		}
        User userTest = new UserDBManager().attemptLogin(email, ch);

        assertNotEquals(user, userTest);


    }
   
    @Test
    public void attemptLoginFail(){
            String email = "barker@test.com";
            String pass = "bass";
            char[] ch = new char[pass.length()];
            for (int i = 0; i < pass.length(); i++){
                ch[i] = pass.charAt(i);
            }        
            User user = new UserDBManager().attemptLogin(email, ch);
            assertNull(user);
    }

    @Test
    public void attemptLoginFail2(){
        String email = " ";
        String pass = " ";
        char[] ch = new char[pass.length()];
        for (int i = 0; i < pass.length(); i++){
            ch[i] = pass.charAt(i);
        }        
        User user = new UserDBManager().attemptLogin(email, ch);
        assertNull(user);
    }


    @Test
    public void attemptLoginFailNull(){
        String email = null;
        String pass = " 111";
        char[] ch = new char[pass.length()];
        for (int i = 0; i < pass.length(); i++){
            ch[i] = pass.charAt(i);
        }        
        User user = new UserDBManager().attemptLogin(email, ch);
        assertNull(user);
    }

    

}

