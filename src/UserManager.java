
import java.util.ArrayList;
import java.util.UUID;

import java.time.LocalDate;

/**
 * UserManager
 * Manages Users and User storage in the database
 * @author Parker Lambert
 */
public class UserManager {
	private User loggedInUser;

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

	/*public static void main(String[] args) {
		CourseManager.getInstance().loadAllCourses();
		UserManager.getInstance().loadAllUsers();

		System.out.println(UserManager.getInstance().getUsers().get(0));

		// try to log in
		System.out.println(UserManager.getInstance().attemptLogin("parker@test.com", { 'p', 'a', 's', 's' }));
	}*/


	/**
	 * Attempts a login with provided email and password
	 * @param email Email to log in to
	 * @param password Password to log in with
	 * @return True if success, false if fail
	 */
	public boolean attemptLogin(String email, char[] password) {
		// get their id
		User loginUser = dbManager.attemptLogin(email, password);

		if (loginUser != null) {
			this.loggedInUser = loginUser;

			return true;
		} else {
			return false;
		}
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

		String hashedPassword = dbManager.hashPassword(password);

		boolean success = dbManager.createNewUser(new User(id, firstName, lastName, email, dateOfBirth, null), hashedPassword);

		updateLoginLookup();

		return success;
	}

	public UUID getIdFromEmail(String email) {
		return dbManager.getIdFromEmail(email);
	}

	public User getUserFromId(UUID id) {
		for (int i = 0; i < users.size(); i++) {
			if (id.equals(users.get(i).getId())) {
				return users.get(i);
			}
		}
		return null;
	}

	private void updateLoginLookup() {
		loadAllUsers();

		dbManager.updateLoginLookup(users);
	}

	public User getLoggedInUser() {
		return this.loggedInUser;
	}

	public void logout() {
		this.loggedInUser = null;
	}

	// getters and setters
	public ArrayList<User> getUsers() {
		return this.users;
	}
}
