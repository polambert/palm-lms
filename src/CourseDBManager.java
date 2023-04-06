
// Copyright Parker Lambert, PALM 2023
// CSCE 247

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * CourseDBManager
 * Manages reading and writing courses directly to database
 * @author Parker Lambert
 */
public class CourseDBManager extends DataConstants {
	
	/**
	 * Constructs a CourseDBManager
	 */
	public CourseDBManager() {

	}

	/**
	 * Converts a datestring (YYYY-MM-DD) into a LocalDate
	 * @param dateString datestring in format of YYYY-MM-DD
	 * @return LocalDate representing datestring given
	 */
	public LocalDate dateStringToDate(String dateString) {
		// date strings are formatted YYYY-MM-DD
		String[] split = dateString.split("-");

		if (split.length != 3) {
			return null;
		}

		String year = split[0];
		String month = split[1];
		String day = split[2];

		if (year.length() != 4)		return null;
		if (month.length() != 2)	return null;
		if (day.length() != 2)		return null;
		
		// construct the Date
		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month);
		int d = Integer.parseInt(day);

		LocalDate date = LocalDate.of(y, m, d);

		return date;
	}

	/**
	 * Recursive function that can load all Comments from a JSON array
	 * @param commentsArr JSONArray containing comment data
	 * @return ArrayList of Comments
	 */
	private ArrayList<Comment> loadComments(JSONArray commentsArr) {
		ArrayList<Comment> comments = new ArrayList<>();

		for (int i = 0; i < commentsArr.size(); i++) {
			JSONObject commentObj = (JSONObject) commentsArr.get(i);

			String comment = (String) commentObj.get(COMMENT_OBJ_COMMENT);
			UUID authorId = UUID.fromString((String) commentObj.get(COMMENT_OBJ_AUTHOR));
			UUID commentId = UUID.fromString((String) commentObj.get(COMMENT_OBJ_ID));
			LocalDate date = dateStringToDate((String) commentObj.get(COMMENT_OBJ_DATE));

			JSONArray repliesArr = (JSONArray) commentObj.get(COMMENT_OBJ_REPLIES);
			ArrayList<Comment> replies = new ArrayList<>();

			if (repliesArr.size() > 0) {
				// recurse
				replies = loadComments(repliesArr);
			}
			
			comments.add(new Comment(commentId, comment, authorId, date, replies));
		}

		return comments;
	}

	/**
	 * Loads an assessment given a JSONArray assessment
	 * @param jsonAssessment JSONArray containing assessment data
	 * @return Assessment with all information provided
	 */
	public Assessment loadAssessment(JSONArray arr) {
		ArrayList<Question> questions = new ArrayList<>();

		for (int i = 0; i < arr.size(); i++) {
			JSONObject questionObj = (JSONObject) arr.get(i);

			String questionStr = (String) questionObj.get(ASSESSMENT_OBJ_QUESTION);
			JSONArray answersArr = (JSONArray) questionObj.get(ASSESSMENT_OBJ_ANSWERS);
			int correctAnswer = ((Long) questionObj.get(ASSESSMENT_OBJ_CORRECT)).intValue();

			// convert answers to arralist
			ArrayList<String> answers = new ArrayList<>();
			for (int j = 0; j < answersArr.size(); j++) {
				answers.add((String) answersArr.get(j));
			}

			questions.add(new Question(questionStr, answers, correctAnswer));
		}

		return new Assessment(questions);
	}
	
	/**
	 * Loads Course from file
	 * @param file File object to load from
	 * @return Course from file
	 */
	public Course readCourseFile(File file) {
		try {
			FileReader reader = new FileReader(file);
			JSONParser parser = new JSONParser();
			JSONObject courseObj = (JSONObject) parser.parse(reader);

			// close the reader as soon as we no longer need it
			reader.close();

			// JSON object is loaded, now extract data
			UUID id = UUID.fromString((String) courseObj.get(COURSE_OBJ_ID));
			String language = (String) courseObj.get(COURSE_OBJ_LANGUAGE);
			String title = (String) courseObj.get(COURSE_OBJ_TITLE);
			String name = (String) courseObj.get(COURSE_OBJ_NAME);
			UUID authorId = UUID.fromString((String) courseObj.get(COURSE_OBJ_AUTHOR));
			System.out.println(authorId);
			String description = (String) courseObj.get(COURSE_OBJ_DESCRIPTION);

			JSONArray chaptersArr 	= (JSONArray) courseObj.get(COURSE_OBJ_CHAPTERS);
			JSONArray finalExamArr 	= (JSONArray) courseObj.get(COURSE_OBJ_FINAL);
			JSONArray reviewsArr 	= (JSONArray) courseObj.get(COURSE_OBJ_REVIEWS);
			JSONArray commentsArr 	= (JSONArray) courseObj.get(COURSE_OBJ_COMMENTS);

			// Unpack chapters
			ArrayList<Chapter> chapters = new ArrayList<>();
			
			for (int i = 0; i < chaptersArr.size(); i++) {
				JSONObject chapterObj = (JSONObject) chaptersArr.get(i);

				String chapterName = (String) chapterObj.get(CHAPTER_OBJ_NAME);
				JSONArray sectionsArr = (JSONArray) chapterObj.get(CHAPTER_OBJ_SECTIONS);
				JSONArray testArr = (JSONArray) chapterObj.get(CHAPTER_OBJ_TEST);

				Assessment test = loadAssessment(testArr);

				ArrayList<Section> sections = new ArrayList<>();
				
				for (int j = 0; j < sectionsArr.size(); j++) {
					JSONObject sectionObj = (JSONObject) sectionsArr.get(j);

					String sectionName = (String) sectionObj.get(SECTION_OBJ_NAME);
					String sectionText = (String) sectionObj.get(SECTION_OBJ_TEXT);
					JSONArray quizArray = (JSONArray) sectionObj.get(SECTION_OBJ_QUIZ);

					Assessment quiz = loadAssessment(quizArray);

					sections.add(new Section(sectionName, sectionText, quiz));
				}

				chapters.add(new Chapter(chapterName, sections, test));
			}

			// Unpack final exam
			Assessment finalExam = loadAssessment(finalExamArr);

			// Unpack reviews
			ArrayList<Review> reviews = new ArrayList<>();

			for (int j = 0; j < reviewsArr.size(); j++) {
				JSONObject reviewObj = (JSONObject) reviewsArr.get(j);

				int rating = ((Long) reviewObj.get(REVIEW_OBJ_RATING)).intValue();
				String text = (String) reviewObj.get(REVIEW_OBJ_TEXT);
				UUID reviewId = UUID.fromString((String) reviewObj.get(REVIEW_OBJ_ID));
				LocalDate date = dateStringToDate((String) reviewObj.get(REVIEW_OBJ_DATE));
				UUID reviewAuthorId = UUID.fromString((String) reviewObj.get(REVIEW_OBJ_AUTHOR));

				reviews.add(new Review(reviewId, rating, text, reviewAuthorId, date));
			}
			
			// Unpack comments
			ArrayList<Comment> comments = loadComments(commentsArr);

			Course course = new Course(id, name, authorId, chapters, finalExam, reviews, comments);
			course.setTitle(title);
			course.setLanguage(language);
			course.setDescription(description);

			return course;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Reads and returns all courses in the database
	 * @return All courses in the database
	 */
	public ArrayList<Course> readCoursesFromDB() {
		File courseFolder = new File(COURSE_FOLDER);
		File[] courseFiles = courseFolder.listFiles();

		ArrayList<Course> courses = new ArrayList<>();

		for (File file : courseFiles) {
			String[] fileSplit = file.getName().split("\\.");

			if (file.isFile() && 
			fileSplit[fileSplit.length - 1].equals(COURSE_FILE_EXTENSION) &&
			!fileSplit[0].equals(COURSE_FILE_IGNORE)) {
				// it is a json file
				// read it
				courses.add(readCourseFile(file));
			}
		}

		return courses;
	}

	/**
	 * Reads and returns a single specified course
	 * @param id The course in question
	 * @return The Course in question
	 */
	public Course readCourseFromDB(UUID id) {
		File file = new File(USER_FOLDER + id.toString() + ".json");

		return readCourseFile(file);
	}

	/**
	 * Writes all of the given courses to the database
	 * @param courses Courses to write
	 */
	public void writeCoursesToDB(ArrayList<Course> courses) {
		for (int i = 0; i < courses.size(); i++) {
			writeCourseToDB(courses.get(i));
		}
	}

	/**
	 * Writes the specified course to the database
	 * @param course Course to write
	 */
	public boolean writeCourseToDB(Course course) {
		try {
			File file = new File(COURSE_FOLDER + course.getId().toString() + "." + COURSE_FILE_EXTENSION);
			FileWriter fw = new FileWriter(file);
			JSONObject courseObj = new JSONObject();
			courseObj.put(COURSE_OBJ_ID, course.getId().toString());
			courseObj.put(COURSE_OBJ_LANGUAGE, course.getLanguage());
			courseObj.put(COURSE_OBJ_TITLE, course.getTitle());
			courseObj.put(COURSE_OBJ_NAME, course.getName());
			User author = course.getAuthor();
			UUID id = author.getId();
			String idStr = id.toString();
			courseObj.put(COURSE_OBJ_AUTHOR, idStr);
			courseObj.put(COURSE_OBJ_DESCRIPTION, course.getDescription());

			// Chapters array
			JSONArray chaptersArray = new JSONArray();
			for (Chapter chapter : course.getChapters()) {
				JSONObject chapterObj = new JSONObject();
				chapterObj.put(CHAPTER_OBJ_NAME, chapter.getName());

				// Sections array
				JSONArray sectionsArray = new JSONArray();
				for (Section section : chapter.getSections()) {
					JSONObject sectionObj = new JSONObject();
					sectionObj.put(SECTION_OBJ_NAME, section.getName());
					sectionObj.put(SECTION_OBJ_TEXT, section.getText());

					// Quiz Array
					JSONArray quizzesArray = writeAssessment(section.getQuiz());
					sectionObj.put(SECTION_OBJ_QUIZ, quizzesArray);
					sectionsArray.add(sectionObj);
				}
				chapterObj.put(CHAPTER_OBJ_SECTIONS, sectionsArray);

				// Test object
				JSONArray testArray = writeAssessment(chapter.getTest());
				chapterObj.put(CHAPTER_OBJ_TEST, testArray);
				chaptersArray.add(chapterObj);
			}
			courseObj.put(COURSE_OBJ_CHAPTERS, chaptersArray);

			// Final Exam object
			JSONArray finalExam = writeAssessment(course.getFinalExam());

			courseObj.put(COURSE_OBJ_FINAL, finalExam);

			// Reviews array
			JSONArray reviewsArray = new JSONArray();
			for (Review review : course.getReviews()) {
				JSONObject reviewObj = new JSONObject();
				reviewObj.put(REVIEW_OBJ_RATING, review.getRating());
				reviewObj.put(REVIEW_OBJ_TEXT, review.getText());
				reviewObj.put(REVIEW_OBJ_ID, review.getId().toString());
				reviewObj.put(REVIEW_OBJ_DATE, review.getDate().toString());
				reviewObj.put(REVIEW_OBJ_AUTHOR, review.getAuthor().getId().toString());
				reviewsArray.add(reviewObj);
			}
			courseObj.put(COURSE_OBJ_REVIEWS, reviewsArray);

			// Comments array
			JSONArray commentsArray = writeComments(course.getComments());
			courseObj.put(COURSE_OBJ_COMMENTS, commentsArray);

			
			// Writing the JSON object to the file
			fw.write(courseObj.toJSONString());
			fw.flush();
			fw.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Converts an Assessment to a writeable JSON array
	 * @param assessment Assessment to write
	 * @return writeable JSON array
	 */
	private JSONArray writeAssessment(Assessment assessment) {
		if (assessment == null) {
			return new JSONArray();
		}

		ArrayList<Question> questions = assessment.getQuestions();

		if (questions == null) {
			return new JSONArray();
		}

		JSONArray questionsArr = new JSONArray();

		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			JSONObject questionObj = new JSONObject();

			ArrayList<String> answers = question.getAnswers();
			JSONArray answersArr = new JSONArray();

			for (int j = 0; j < answers.size(); j++) {
				answersArr.add(answers.get(j));
			}

			questionObj.put(ASSESSMENT_OBJ_QUESTION, question.getQuestion());
			questionObj.put(ASSESSMENT_OBJ_ANSWERS, answersArr);
			questionObj.put(ASSESSMENT_OBJ_CORRECT, question.getCorrectAnswer());

			questionsArr.add(questionObj);
		}

		return questionsArr;
	}

	/**
	 * Converts a list of Comments to a writeable JSON array
	 * @param comments Comments to write
	 * @return writeable JSON array
	 */
	public JSONArray writeComments(ArrayList<Comment> comments) {
		JSONArray commentsArray = new JSONArray();
		for(Comment comment : comments){
			JSONObject commentObject = new JSONObject();
			commentObject.put(COMMENT_OBJ_COMMENT, comment.getComment());
			commentObject.put(COMMENT_OBJ_AUTHOR, comment.getAuthor().getId().toString());
			commentObject.put(COMMENT_OBJ_ID, comment.getId().toString());
			commentObject.put(COMMENT_OBJ_DATE, comment.getDate().toString());
			JSONArray replyArray = writeComments(comment.getReplies());
			commentObject.put(COMMENT_OBJ_REPLIES, replyArray);
			commentsArray.add(commentObject);
		}
		return commentsArray; 
	}
}


