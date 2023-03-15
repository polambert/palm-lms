
// Copyright Parker Lambert, PALM 2023
// CSCE 247

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * CourseDBManager
 * Manages reading and writing courses directly to database
 * @author Parker Lambert
 */
public class CourseDBManager {
	private final String COURSE_FOLDER 				= "./json/courses/";
	private final String COURSE_FILE_EXTENSION 		= "json";
	private final String COURSE_FILE_IGNORE 		= "sample_course";

	private final String COURSE_OBJ_ID 				= "id";
	private final String COURSE_OBJ_LANGUAGE 		= "language";
	private final String COURSE_OBJ_TITLE 			= "title";
	private final String COURSE_OBJ_NAME 			= "name";
	private final String COURSE_OBJ_AUTHOR 			= "author";
	private final String COURSE_OBJ_DESCRIPTION 	= "description";
	private final String COURSE_OBJ_CHAPTERS 		= "chapters";
	private final String COURSE_OBJ_FINAL 			= "finalExam";
	private final String COURSE_OBJ_REVIEWS 		= "reviews";
	private final String COURSE_OBJ_COMMENTS 		= "comments";

	private final String CHAPTER_OBJ_NAME 			= "name";
	private final String CHAPTER_OBJ_SECTIONS 		= "sections";
	private final String CHAPTER_OBJ_TEST			= "test";

	private final String SECTION_OBJ_NAME 			= "name";
	private final String SECTION_OBJ_TEXT			= "text";
	private final String SECTION_OBJ_QUIZ			= "quiz";

	private final String ASSESSMENT_OBJ_QUESTION	= "question";
	private final String ASSESSMENT_OBJ_ANSWERS		= "answers";
	private final String ASSESSMENT_OBJ_CORRECT		= "correctAnswer";

	/**
	 * Constructs a CourseDBManager
	 */
	public CourseDBManager() {

	}

	private Date dateStringToDate(String dateString) {
		// date strings are formatted YYYY-MM-DD
		String[] split = dateString.split("-");

		if (split.length != 3) {
			throw new Exception("[CourseDBManager.dateStringToDate] dateString did not split into three, instead " + split.length);
		}

		String year = split[0];
		String month = split[1];
		String day = split[2];

		if (year.length() != 4)
			throw new Exception("[CourseDBManager.dateStringToDate] Year was " + year.length() + " digits, should be 4.");

		if (month.length() != 2)
			throw new Exception("[CourseDBManager.dateStringToDate] Month was " + month.length() + " digits, should be 2.");

		if (day.length() != 2)
			throw new Exception("[CourseDBManager.dateStringToDate] Day was " + day.length() + " digits, should be 2.");
		
		// construct the Date

	}

	/**
	 * Loads an assessment given a JSONArray assessment
	 * @param jsonAssessment JSONArray containing assessment data
	 * @return Assessment with all information provided
	 */
	private Assessment loadAssessment(JSONArray arr) {
		ArrayList<Question> questions = new ArrayList<>();

		for (int i = 0; i < arr.size(); i++) {
			JSONObject questionObj = (JSONObject) arr.get(i);

			String questionStr = (String) questionObj.get(ASSESSMENT_OBJ_QUESTION);
			JSONArray answersArr = (JSONArray) questionObj.get(ASSESSMENT_OBJ_ANSWERS);
			int correctAnswer = (int) questionObj.get(ASSESSMENT_OBJ_CORRECT);

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
	 * Reads and returns all courses in the database
	 * @return All courses in the database
	 */
	public ArrayList<Course> readCoursesFromDB() {
		File courseFolder = new File(COURSE_FOLDER);
		File[] courseFiles = courseFolder.listFiles();

		for (File file : courseFiles) {
			String[] fileSplit = file.getName().split("\\.");

			System.out.println(file.getName());

			System.out.println(fileSplit[fileSplit.length - 1]);

			if (file.isFile() && 
			fileSplit[fileSplit.length - 1].equals(COURSE_FILE_EXTENSION) &&
			!fileSplit[0].equals(COURSE_FILE_IGNORE)) {
				// it is a json file
				// read it
				try {
					FileReader reader = new FileReader(COURSE_FOLDER + file.getName());
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
					for (int j = 0; j < reviewsArr.size(); j++) {
						JSONObject reviewObj = (JSONObject) reviewsArr.get(j);

						rating
						text
						id
						date
						author
					}
					
					// Unpack comments


					System.out.println(title);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	/**
	 * Reads and returns a single specified course
	 * @param id The course in question
	 * @return The Course in question
	 */
	public Course readCourseFromDB(UUID id) {
		return null;
	}

	/**
	 * Writes all of the given courses to the database
	 * @param courses Courses to write
	 */
	public void writeCoursesToDB(ArrayList<Course> courses) {

	}

	/**
	 * Writes the specified course to the database
	 * @param course Course to write
	 */
	public void writeCourseToDB(Course course) {

	}
}
