
import java.util.ArrayList;
import java.util.Date;

/**
 * UserManager
 * Manages Users and User storage in the database
 * @author Parker Lambert
 */
public class UserManager {
	private ArrayList<User> users;
	private static UserManager userManager;
	private UserDBManager dbManager;

	/**
	 * Private constructor for singleton UserManager
	 */
	private UserManager() {
		this.dbManager = new UserDBManager();
	}

	/**
	 * Public getInstance for singleton UserManager
	 * @return The UserManager
	 */
	public static UserManager getInstance() {
		if (UserManager.userManager == null) {
			UserManager.userManager = new UserManager();
		}

		return UserManager.userManager;
	}

	public boolean loadAllUsers() {
		users = dbManager.readUsersFromDB();

		return false;
	}


	/**
	 * Attempts a login with provided email and password
	 * @param email Email to log in to
	 * @param password Password to log in with
	 * @return True if success, false if fail
	 */
	public boolean attemptLogin(String email, char[] password) {
		String convertedPasswrod = String.valueOf(password);
		for(User user : users) {
			if(email.equals(user.getEmail()) && convertedPassword.equals(user.getPassword()))
				return true;
		}
		return false;
	}

	/**
	 * Attempts a signup with the provided information
	 * @param email Email to sign up with
	 * @param password Password
	 * @param firstName First name
	 * @param lastName Last name
	 * @param dateOfBirth Date of birth
	 * @return True if success, false if fail
	 */
	public boolean attemptSignup(String email, char[] password,
			String firstName, String lastName, Date dateOfBirth) {
		String convertedPassword = String.valueOf(password);
		if(email.equals(user.getEmail()) && convertedPasswrod.equals(user.getPasswrod()) && 
			firstName.equals(user.getFirstName()) && lastName.equals(user.getLastName()) && 
			dateOfBirth.compateTo(user.getDate()))
			return true;
		return false;
	}

	// getters and setters
}
