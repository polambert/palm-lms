import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * CourseDBManagerTest
 * Contains test cases for CourseDBManager
 * @author Parker Lambert
 */
public class CourseDBManagerTest {
	/* readCourseFile */
	@Test
	public void testReadCourseFileValidAuthor() {
		File file = new File("./json/courses/37fd8b96-a0a5-491b-91cf-4dcf08f07c00.json");
		String authorId = "8e76a50c-d843-455b-b778-7543e96e6d10";
		Course course = new CourseDBManager().readCourseFile(file);
		assertEquals(authorId, course.getAuthorId().toString(), "ID was not same");
	}

	@Test
	public void testReadCourseFileValidLanguage() {
		File file = new File("./json/courses/37fd8b96-a0a5-491b-91cf-4dcf08f07c00.json");
		String lang = "Python";
		Course course = new CourseDBManager().readCourseFile(file);
		assertEquals(lang, course.getLanguage(), "ID was not same");
	}

	@Test
	public void testReadCourseFileInvalid() {
		File file = new File("./json/courses/does-not-exist.json");
		Course course = new CourseDBManager().readCourseFile(file);
		assertNull(course);
	}

	@Test
	public void testReadCourseFileNull() {
		assertNull(new CourseDBManager().readCourseFile(null));
	}

	/* loadAssessment */
	@Test
	public void testLoadAssessmentValid() {
		JSONArray assessArr = new JSONArray();
		JSONObject questionObj = new JSONObject();
		JSONArray answersArr = new JSONArray();
		answersArr.add("First answer");
		answersArr.add("Second answer");
		String question = "1234567890";
		questionObj.put("question", question);
		questionObj.put("answers", answersArr);
		questionObj.put("correctAnswer", Long.valueOf(0));
		assessArr.add(questionObj);

		Assessment ass = new CourseDBManager().loadAssessment(assessArr);
		assertEquals(question, ass.getQuestions().get(0).getQuestion());
	}

	@Test
	public void testLoadAssessmentInvalid() {
		// has no answers or correctAnswer attribute
		JSONArray assessArr = new JSONArray();
		JSONObject questionObj = new JSONObject();
		String question = "1234567890";
		questionObj.put("question", question);
		assessArr.add(questionObj);

		Assessment ass = new CourseDBManager().loadAssessment(assessArr);
		assertNull(ass);
	}

	@Test
	public void testLoadAssessmentNull() {
		Assessment ass = new CourseDBManager().loadAssessment(null);
		assertNull(ass);
	}

	/* dateStringToDate */
	@Test
	public void testDateStringValid() {
		String dateString = "2016-04-29";
		LocalDate correct = LocalDate.of(2016, 4, 29);
		LocalDate date = new CourseDBManager().dateStringToDate(dateString);
		assertEquals(correct, date);
	}
	
	@Test
	public void testDateStringInvalid() {
		String dateString = "1--1";
		LocalDate date = new CourseDBManager().dateStringToDate(dateString);
		assertNull(date);
	}
	
	@Test
	public void testDateStringInvalid2() {
		String dateString = "A-A-A";
		LocalDate date = new CourseDBManager().dateStringToDate(dateString);
		assertNull(date);
	}
	
	@Test
	public void testDateStringNull() {
		assertNull(new CourseDBManager().dateStringToDate(null));
	}

	/* writeCourseToDB */
	@Test
	public void testWriteCourseValidAuthor() {
		// create a valid, but empty course
		UUID courseId = UUID.randomUUID();
		User author = new User(UUID.randomUUID(), "first", "last", "email", LocalDate.now(), new ArrayList<>());
		Course course = new Course(courseId, "course", author, new ArrayList<>(), new Assessment(new ArrayList<>()), new ArrayList<>(), new ArrayList<>());

		new CourseDBManager().writeCourseToDB(course);

		// check it's good
		String coursePath = "./json/courses/" + courseId + ".json";
		File file = new File(coursePath);

		Course course2 = new CourseDBManager().readCourseFile(file);
		assertEquals(author.getId(), course2.getAuthorId(), "ID was not same");
	}

	@Test
	public void testWriteCourseValidLanguage() {
		// create a valid, but empty course
		UUID courseId = UUID.randomUUID();
		User author = new User(UUID.randomUUID(), "first", "last", "email", LocalDate.now(), new ArrayList<>());
		Course course = new Course(courseId, "course", author, new ArrayList<>(), new Assessment(new ArrayList<>()), new ArrayList<>(), new ArrayList<>());
		course.setLanguage("testlanguage");

		new CourseDBManager().writeCourseToDB(course);

		// check it's good
		String coursePath = "./json/courses/" + courseId + ".json";
		File file = new File(coursePath);

		Course course2 = new CourseDBManager().readCourseFile(file);
		assertEquals("testlanguage", course2.getLanguage());
	}

	@Test
	public void testWriteCourseNullAuthor() {
		// create a valid, but empty course
		UUID courseId = UUID.randomUUID();
		User author = null;
		Course course = new Course(courseId, "course", author, new ArrayList<>(), new Assessment(new ArrayList<>()), new ArrayList<>(), new ArrayList<>());

		boolean success = new CourseDBManager().writeCourseToDB(course);

		// check it's good
		assertFalse(success);
	}

	@Test
	public void testWriteCourseNull() {
		// create a valid, but empty course
		boolean success = new CourseDBManager().writeCourseToDB(null);

		// check it's good
		assertFalse(success);
	}

	/* writeComments */
	@Test
	public void testWriteCommentsValid() {
		ArrayList<Comment> comments = new ArrayList<>();
		UUID id = UUID.randomUUID();
		User author = new User(UUID.randomUUID(), "first", "last", "email", LocalDate.now(), new ArrayList<>());
		comments.add(new Comment(id, "testcomment", author, LocalDate.now(), new ArrayList<>()));

		JSONArray commentsArr = new CourseDBManager().writeComments(comments);
		assertEquals(author.getId().toString(), (String) ((JSONObject)commentsArr.get(0)).get("author"));
	}

	@Test
	public void testWriteCommentsEmpty() {
		ArrayList<Comment> comments = new ArrayList<>();

		JSONArray commentsArr = new CourseDBManager().writeComments(comments);
		assertEquals(0, commentsArr.size());
	}

	@Test
	public void testWriteCommentsNullAuthor() {
		ArrayList<Comment> comments = new ArrayList<>();
		UUID id = UUID.randomUUID();
		User author = null;
		comments.add(new Comment(id, "testcomment", author, LocalDate.now(), new ArrayList<>()));

		JSONArray commentsArr = new CourseDBManager().writeComments(comments);
		assertNull(commentsArr);
	}

	@Test
	public void testWriteCommentsNull() {
		JSONArray commentsArr = new CourseDBManager().writeComments(null);
		assertNull(commentsArr);
	}

	/*
		readCourseFile
		loadAssessment
		loadComments
		dateStringToDate
		writeCourseToDB
		writeAssessment
		writeComments
	*/
}
