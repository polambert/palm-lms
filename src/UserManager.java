
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

	/**
	 * Loads all users from disk
	 * @return
	 */
	public boolean loadAllUsers() {
		users = dbManager.readUsersFromDB();

		return true;
	}

	/**
	 * Writes all users to disk
	 * @return
	 */
	public boolean writeAllUsers() {
		// save logged in user
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId().equals(loggedInUser.getId())) {
				users.set(i, loggedInUser);
			}
		}

		return dbManager.writeUsersToDB(users);
	}

	// main method used for testing loading/writing/signup/login of users
	/*
	public static void main(String[] args) {
		CourseManager.getInstance().loadAllCourses();
		UserManager.getInstance().loadAllUsers();
		CourseManager.getInstance().updateUsers();

		// testing
		System.out.println("USERMANAGER TESTING");

		// test load user
		System.out.println(" -- LOAD USER TEST");
		User u = UserManager.getInstance().getUsers().get(0);
		System.out.println(u);

		// test write user
		System.out.println(" -- WRITE USER TEST");
		System.out.println(UserManager.getInstance().writeAllUsers());

		// sign up
		char[] pass = { 'p', 'a', 's', 's' };
		// System.out.println(" -- SIGN UP TEST");
		// System.out.println(UserManager.getInstance().attemptSignup("parker@test.com", pass, "Parker", "Lambert", LocalDate.now()));

		// try to log in
		System.out.println(" -- LOG IN TEST");
		System.out.println(UserManager.getInstance().attemptLogin("parker@test.com", pass));
	}
	*/

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

	/**
	 * Returns a user's ID given their email
	 * @param email email of user to find
	 * @return ID of user with same email
	 */
	public UUID getIdFromEmail(String email) {
		return dbManager.getIdFromEmail(email);
	}

	/**
	 * Returns a user object given an id
	 * @param id id of user to find
	 * @return user object of user with same id
	 */
	public User getUserFromId(UUID id) {
		for (int i = 0; i < users.size(); i++) {
			if (id.equals(users.get(i).getId())) {
				return users.get(i);
			}
		}
		return null;
	}

	/**
	 * Updates master login lookup find
	 */
	private void updateLoginLookup() {
		loadAllUsers();

		dbManager.updateLoginLookup(users);
	}

	/**
	 * Returns the currently logged in user
	 * @return The currently logged in user
	 */
	public User getLoggedInUser() {
		return this.loggedInUser;
	}

	/**
	 * Logs the user out
	 */
	public void logout() {
		this.loggedInUser = null;
	}

	// getters and setters
	public ArrayList<User> getUsers() {
		return this.users;
	}

	public void addUser(User user, ArrayList<User> userList) {
		userList.add(user);
	}
}
