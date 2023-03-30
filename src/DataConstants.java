
// Copyright 2023 Parker Lambert, PALM
// CSCE 247


/**
 * Stores important data constants for CourseDBManager and UserDBManager
 * @author Parker Lambert
 */
public class DataConstants {
	protected final String COURSE_FOLDER 				= "./json/courses/";
	protected final String COURSE_FILE_EXTENSION 		= "json";
	protected final String COURSE_FILE_IGNORE 		    = "sample_course";
    
	protected final String USER_FOLDER 				    = "./json/users/";
	protected final String USER_FILE_EXTENSION 		    = "json";
	protected final String USER_FILE_IGNORE 			= "sample_user";
	protected final String USER_LOGIN_LOOKUP			= "./json/login_lookup.json";

	protected final String COURSE_OBJ_ID 				= "id";
	protected final String COURSE_OBJ_LANGUAGE 		    = "language";
	protected final String COURSE_OBJ_TITLE 			= "title";
	protected final String COURSE_OBJ_NAME 			    = "name";
	protected final String COURSE_OBJ_AUTHOR 			= "author";
	protected final String COURSE_OBJ_DESCRIPTION 	    = "description";
	protected final String COURSE_OBJ_CHAPTERS 		    = "chapters";
	protected final String COURSE_OBJ_FINAL 			= "finalExam";
	protected final String COURSE_OBJ_REVIEWS 		    = "reviews";
	protected final String COURSE_OBJ_COMMENTS 		    = "comments";

	protected final String CHAPTER_OBJ_NAME 			= "name";
	protected final String CHAPTER_OBJ_SECTIONS 		= "sections";
	protected final String CHAPTER_OBJ_TEST			    = "test";

	protected final String SECTION_OBJ_NAME 			= "name";
	protected final String SECTION_OBJ_TEXT			    = "text";
	protected final String SECTION_OBJ_QUIZ			    = "quiz";

	protected final String ASSESSMENT_OBJ_QUESTION	    = "question";
	protected final String ASSESSMENT_OBJ_ANSWERS		= "answers";
	protected final String ASSESSMENT_OBJ_CORRECT		= "correctAnswer";

	protected final String REVIEW_OBJ_RATING			= "rating";
	protected final String REVIEW_OBJ_TEXT			    = "text";
	protected final String REVIEW_OBJ_ID				= "id";
	protected final String REVIEW_OBJ_DATE			    = "date";
	protected final String REVIEW_OBJ_AUTHOR			= "author";

	protected final String COMMENT_OBJ_COMMENT		    = "comment";
	protected final String COMMENT_OBJ_AUTHOR			= "author";
	protected final String COMMENT_OBJ_ID				= "id";
	protected final String COMMENT_OBJ_DATE			    = "date";
	protected final String COMMENT_OBJ_REPLIES		    = "replies";  

	protected final String USER_OBJ_ID 				    = "id";
	protected final String USER_OBJ_FIRSTNAME 		    = "firstName";
	protected final String USER_OBJ_LASTNAME 			= "lastName";
	protected final String USER_OBJ_EMAIL 			    = "email";
	protected final String USER_OBJ_DATEOFBIRTH 		= "dateOfBirth";
	protected final String USER_OBJ_PASSWORD 			= "password";
	protected final String USER_OBJ_CANCREATECOURSES 	= "canCreateCourses";
	protected final String USER_OBJ_COURSEPROGRESSES 	= "courseProgresses";

	protected final String COURSEPROGRESS_OBJ_COURSEID 			    = "courseId";
	protected final String COURSEPROGRESS_OBJ_CHAPTERSCOMPLETED 	= "chaptersCompleted";
	protected final String COURSEPROGRESS_OBJ_SECTIONSCOMPLETED 	= "sectionsCompleted";
	protected final String COURSEPROGRESS_OBJ_GRADES 				= "grades";
	protected final String COURSEPROGRESS_OBJ_CERTIFICATE 		    = "certificate";

	protected final String CERTIFICATE_OBJ_DATECOMPLETED 	= "dateCompleted";
	protected final String CERTIFICATE_OBJ_CERTIFICATEID 	= "certificateId";
}
