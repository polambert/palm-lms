
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

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

	public static void startAssessment(CourseProgress courseProgress) {
		Course course = courseProgress.getCourse();

		boolean canTakeTest = courseProgress.canTakeTest();
		boolean canTakeFinal = courseProgress.canTakeFinal();

		if (canTakeFinal) {
			// course final
			Assessment finalExam = course.getFinalExam();
			double score = AssessmentHandler.start(finalExam);
			if (score >= MIN_PASSING_GRADE) {
				// CERTIFICATE STUFF HERE
				courseProgress.generateCertificate();
				System.out.println("Congratulations -- you have completed '" + course.getTitle() + "'!");
				System.out.println("You can view your certificate from the course menu.");
				courseProgress.completedSectionAssessment(course.getChapterCount(), 0, score);
				userManager.writeAllUsers();
			}
		} else if (canTakeTest) {
			// chapter test
			Assessment test = course.getChapters().get(courseProgress.getChaptersCompleted()).getTest();
			double score = AssessmentHandler.start(test);
			if (score >= MIN_PASSING_GRADE) {
				// move to first section of next chapter
				courseProgress.completedSectionAssessment(courseProgress.getChaptersCompleted(), courseProgress.getSectionsCompleted(), score);
				courseProgress.incChapterProgress();
				courseProgress.setSectionProgress(0);
				userManager.writeAllUsers();
			}
		} else {
			// section quiz
			Assessment quiz = course.getChapters().get(courseProgress.getChaptersCompleted()).getSections().get(courseProgress.getSectionsCompleted()).getQuiz();
			double score = AssessmentHandler.start(quiz);
			if (score >= MIN_PASSING_GRADE) {
				// move to next section
				courseProgress.completedSectionAssessment(courseProgress.getChaptersCompleted(), courseProgress.getSectionsCompleted(), score);
				courseProgress.incSectionProgress();
				userManager.writeAllUsers();
			}
		}

		/*
		Course assessmentCourse = courseProgress.getCourse();
		//Start on current chapter of progress
		int chapterStart = courseProgress.getChaptersCompleted();
		// Don't start at next section, we are on this section so we take this quiz
		int sectionStart = courseProgress.getSectionsCompleted();
		//Return true if chapter progress isn't complete
		boolean availableChapterAssessment = !courseProgress.canTakeTest(); // (courseProgress.getChapterProgress() < assessmentCourse.getChapterCount());
		//Return true if section progress isn't complete
		boolean availableSectionAssessment = !courseProgress.canTakeTest(); // (courseProgress.getSectionProgress() < assessmentCourse.getChapters().get(chapterStart).getSectionCount());
		//Return true if all sections in chapter are done, starts the Test
		boolean availableChapterTest = courseProgress.canTakeTest(); // (assessmentCourse.getChapters().get(chapterStart).getSectionCount() == courseProgress.getSectionProgress());
		//Return true if all sections in chapter are done, including the Test, start new Chapter
		boolean nothingAvailable = false; // (assessmentCourse.getChapters().get(chapterStart).getSectionCount() < courseProgress.getSectionProgress());
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
		*/
	}

   

    
    

}




