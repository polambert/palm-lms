
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class UIHandler extends LMS
{
	private static ProgramState state;
	private static AssessmentHandler assessmentHandler;
	private static CourseManager courseManager;
	private static UserManager userManager;

	private static final double MIN_PASSING_GRADE = 80.0;


	//getters and setters
	public ProgramState getState() {
		return state;
	}
	public void setState(ProgramState state) {
		this.state = state;
	}

	public AssessmentHandler getAssessmentHandler() {
		return assessmentHandler;
	}

	public void setAssessmentHandler(AssessmentHandler assessmentHandler) {
		this.assessmentHandler = assessmentHandler;
	}

	public CourseManager getCourseManager() {
		return courseManager;
	}

	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	/* 
		public static void main (String args[]){
			courseManager = CourseManager.getInstance();
			userManager = UserManager.getInstance();

			courseManager.loadAllCourses();
		}
	*/
	//=======
	public static void main(String args[]) {
		courseManager = CourseManager.getInstance();
		userManager = UserManager.getInstance();
		courseManager.loadAllCourses();
		userManager.loadAllUsers();
		courseManager.updateUsers();
		
		signInMenu();
	}

	public static boolean login(String email, char[] password) {
		return true;
	}

	public boolean logout() {
		return true;
	}

	public boolean signup(String name, String email, Date dateOfBirth, char[] password) {
		return true;
	}

	public static void startAssessment(CourseProgress courseProgress) {
		Course assessmentCourse = courseProgress.getCourse();
		//Start on current chapter of progress
		int chapterStart = courseProgress.getChaptersCompleted();
		// Don't start at next section, we are on this section so we take this quiz
		int sectionStart = courseProgress.getSectionsCompleted();
		//Return true if chapter progress isn't complete
		boolean availableChapterAssessment = (courseProgress.getChapterProgress() < assessmentCourse.getChapterCount());
		//Return true if section progress isn't complete
		boolean availableSectionAssessment = (courseProgress.getSectionProgress() < assessmentCourse.getChapters().get(chapterStart).getSectionCount());
		//Return true if all sections in chapter are done, starts the Test
		boolean availableChapterTest = (assessmentCourse.getChapters().get(chapterStart).getSectionCount() == courseProgress.getSectionProgress());
		//Return true if all sections in chapter are done, including the Test, start new Chapter
		boolean nothingAvailable = (assessmentCourse.getChapters().get(chapterStart).getSectionCount() < courseProgress.getSectionProgress());
		//Taking a section quiz
		if(availableChapterAssessment && availableSectionAssessment) {
			Assessment quiz = assessmentCourse.getChapters().get(chapterStart).getSections().get(sectionStart).getQuiz();
			double score = assessmentHandler.start(quiz);
			if(score >= MIN_PASSING_GRADE) {
				courseProgress.incSectionProgress();
				courseProgress.completedSectionAssessment(chapterStart, sectionStart, score);
				userManager.writeAllUsers();
			}
				
		}
		//Taking a Chapter Test
		else if(availableChapterTest) {
			Assessment test = assessmentCourse.getChapters().get(chapterStart).getTest();
			double score = assessmentHandler.start(test);
			if(score >= MIN_PASSING_GRADE) {
				courseProgress.incSectionProgress();
				courseProgress.completedSectionAssessment(chapterStart, sectionStart, score);
				userManager.writeAllUsers();
			}
		}
		//Kind of assuming here that if the chapter's section progress and the chapter's 
		//	section count are the same then you would increment 1 more time for the progress 
		//	as a way to signify in the next check that all section quiz have been taken as 
		//	well as the section test so you can move on to the next chapter and eventually final exam
		//New Chapter, Taking new quiz in new section, Also resetting section counter for new chapter
		else if(nothingAvailable) {
			courseProgress.incChapterProgress();
			courseProgress.setSectionsCompleted(0);
			userManager.writeAllUsers();
			startAssessment(courseProgress);
		}
	}

	public boolean enrollCourse(Course course) {
		return true;
	}

//>>>>>>> 5c5c6f818b5e6cccdc86d643c472e0d14d5592c1
	public boolean dropCourse(Course course){
		return true;
	}

	public boolean makeCourse() {
		return true;
	}

	public boolean deleteReview(Review review, Course course){
		return true;
	}

	public boolean deleteComment(Comment comment, Course course){
		return true;
	}



   

    
    

}




