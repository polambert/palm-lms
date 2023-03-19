
import java.util.ArrayList;
import java.util.UUID;

import java.time.LocalDate;

import java.math.BigInteger;

import java.security.MessageDigest;

/**
 * UserManager
 * Manages Users and User storage in the database
 * @author Parker Lambert
 */
public class UserManager {
	private final String HASH_ALGORITHM = "SHA-512";

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

		return true;
	}

	public static void main(String[] args) {
		CourseManager.getInstance().loadAllCourses();
		UserManager.getInstance().loadAllUsers();

		System.out.println(UserManager.getInstance().getUsers().get(0));

		// create a new user
		System.out.println("Creating a new user");
		char[] password = { 'p', 'a', 's', 's' };
		boolean success = UserManager.getInstance().attemptSignup("parker@test.com", password, "Parker", "Lambert", LocalDate.now());
		System.out.println(success);
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
			String firstName, String lastName, LocalDate dateOfBirth) {
		// create an id
		UUID id = UUID.randomUUID();

		String hashedPassword = null;

		try {
			// hash the password
			MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);

			// a String is constructed with the password, but it should be
			//   immediately garbage collected since ownership isn't passed.
			byte[] messageDigest = md.digest(new String(password).getBytes());

			// convert into hex
			BigInteger number = new BigInteger(1, messageDigest);
			hashedPassword = number.toString(16);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dbManager.createNewUser(new User(id, firstName, lastName, email, dateOfBirth, null), hashedPassword);
	}

	// getters and setters
	public ArrayList<User> getUsers() {
		return this.users;
	}
}
