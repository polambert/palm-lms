
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Holds the main method, which uses the top-level facade LMS.
 * @author Ayush Patel
 */
public class UIHandler extends LMS {
	private static AssessmentHandler assessmentHandler;
	private static CourseManager courseManager;
	private static UserManager userManager;

	private static final double MIN_PASSING_GRADE = 80.0;

	/**
	 * Static main method entry point for LMS
	 * @param args Command line arguments
	 */
	public static void main(String args[]) {
		courseManager = CourseManager.getInstance();
		userManager = UserManager.getInstance();
		courseManager.loadAllCourses();
		userManager.loadAllUsers();
		courseManager.updateUsers();
		
		signInMenu();
	}

	/**
	 * Handles appropriate logic for assessments, calls AssessmentHandler.start
	 * @param courseProgress The User's CourseProgress for the Course they are
	 *     taking an assessment in
	 */
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
	}

	// getters and setters
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
}
