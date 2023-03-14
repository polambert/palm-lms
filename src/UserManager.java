
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

	/**
	 * Attempts a login with provided email and password
	 * @param email Email to log in to
	 * @param password Password to log in with
	 * @return True if success, false if fail
	 */
	public boolean attemptLogin(String email, char[] password) {
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
		return false;
	}

	// getters and setters
}
