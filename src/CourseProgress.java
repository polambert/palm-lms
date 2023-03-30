
import java.util.ArrayList;
import java.util.UUID;

import java.time.LocalDate;

/**
 * Stores information about a user's completion in a Course.
 * @author Kaleb Bah, Parker Lambert
 */
public class CourseProgress {
	private Course course;
	private int chaptersCompleted;
	private int sectionsCompleted;
	private ArrayList<ArrayList<Double>> grades = new ArrayList<>();
	private double grade;
	private LocalDate dateCompleted;
	private UUID certificateId;

	/**
	 * Constructs a CourseProgress with already provided information
	 * @param course Course
	 * @param chaptersCompleted Number of chapters finished
	 * @param sectionsCompleted Number of sections finished
	 * @param grades 2D array of grades
	 */
	public CourseProgress(Course course, int chaptersCompleted, int sectionsCompleted, ArrayList<ArrayList<Double>> grades) {
		this.course = course;
		this.chaptersCompleted = chaptersCompleted;
		this.sectionsCompleted = sectionsCompleted;
		this.grades = grades;
	}

	/**
	 * Constructs a brand-new course progress for when a user enrolls in a Course
	 * @param course Course
	 */
	public CourseProgress(Course course) {
		this.course = course;
		this.chaptersCompleted = 0;
		this.sectionsCompleted = 0;
		this.grades = new ArrayList<>();

		ArrayList<Chapter> chapters = course.getChapters();
		for (int i = 0; i < chapters.size(); i++) {
			Chapter chapter = chapters.get(i);
			
			ArrayList<Double> sectionGrades = new ArrayList<Double>();

			ArrayList<Section> sections = chapter.getSections();
			for (int j = 0; j < sections.size(); j++) {
				sectionGrades.add(0.00); // quiz
			}

			sectionGrades.add(0.00); // test

			grades.add(sectionGrades);
		}

		ArrayList<Double> finalExam = new ArrayList<>();
		finalExam.add(0.00);

		grades.add(finalExam); // final exam
	}

	/**
	 * Updates users' grades after completing a quiz
	 * @param chapterIndex Chapter
	 * @param sectionIndex Section
	 * @param grade Grade for assessment
	 */
	public void completedSectionAssessment(int chapterIndex, int sectionIndex, double grade) {
		// Check if the specified chapter and section exist in the grades arraylist
		if (grades.size() <= chapterIndex) {
			grades.add(new ArrayList<Double>());
		} else if (grades.get(chapterIndex).size() <= sectionIndex) {
			grades.get(chapterIndex).add(0.0);
		}
	
		// Update the grade at the specified chapter and section
		grades.get(chapterIndex).set(sectionIndex, grade);
	}
	
	/**
	 * String representation of CourseProgress, used for debug
	 * @return string representation used for debug
	 */
	public String toString() {
		String s = "[CourseProgress] courseId: " + course.getId() + "\n";
		s += "\tcourseName: " + course.getName() + "\n";
		s += "\tchaptersCompleted: " + chaptersCompleted + "\n";
		s += "\tsectionsCompleted: " + sectionsCompleted + "\n";
		s += "\tdateCompleted: " + dateCompleted + "\n";
		s += "\tcertificateId: " + certificateId + "\n";

		s += "\tgrades:\n";
		for (int i = 0; i < grades.size(); i++) {
			if (i == grades.size() - 1) {
				s += "\t\tFinal: ";
			} else {
				s += "\t\tChapter " + (i+1) + ": ";
			}

			for (int j = 0; j < grades.get(i).size(); j++) {
				double grade = grades.get(i).get(j);

				if (grade < 0.01) {
					// grade is zero, don't show it, user hasn't taken assessment yet
					s += "---";
				} else {
					s += grades.get(i).get(j);
				}
				

				if (j != grades.get(i).size() - 1) s += ", ";
			}

			s += "\n";
		}
		return s;
	}

	/**
	 * Returns true if user can take test
	 * @return true if user can take test
	 */
	public boolean canTakeTest() {
		if (chaptersCompleted >= course.getChapterCount()) {
			// this means they can take the final
			return false;
		}
		if (certificateId != null) {
			return false; // course is finished
		}
		return sectionsCompleted >= course.getChapters().get(chaptersCompleted).getSections().size();
	}

	/**
	 * Returns true if user can take final
	 * @return true if user can take final
	 */
	public boolean canTakeFinal() {
		if (certificateId != null) {
			return false; // course is finished
		}
		return chaptersCompleted >= course.getChapters().size();
	}

	/**
	 * Returns true if user has finished course
	 * @return true if user has finished course
	 */
	public boolean hasFinishedCourse() {
		if (certificateId != null) {
			return true;
		}
		return false;
	}

	/**
	 * Generates a certificate, called upon completion of a Course
	 * @return UUID of certificate
	 */
	public UUID generateCertificate() {
		this.certificateId = UUID.randomUUID();
		this.dateCompleted = LocalDate.now();
		return certificateId;
	}


	// getters and setters
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getChaptersCompleted() {
		return chaptersCompleted;
	}

	public void setChaptersCompleted(int chaptersCompleted) {
		this.chaptersCompleted = chaptersCompleted;
	}

	public int getSectionsCompleted() {
		return sectionsCompleted;
	}

	public void setSectionsCompleted(int sectionsCompleted) {
		this.sectionsCompleted = sectionsCompleted;
	}

	public ArrayList<ArrayList<Double>> getGrades() {
		return grades;
	}

	public void setgrades(ArrayList<ArrayList<Double>> grades) {
		this.grades = grades;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public LocalDate getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(LocalDate dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public UUID getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(UUID certificateId) {
		this.certificateId = certificateId;
	}

	public int getChapterProgress(){
		return chaptersCompleted;
	}
	public int getSectionProgress(){
		return sectionsCompleted;
	}
	public void setChapterProgress(int newChapterProgress) {
		this.chaptersCompleted = newChapterProgress;
	}
	public void setSectionProgress(int newSectionProgress) {
		this.sectionsCompleted = newSectionProgress;
	}
	public int incChapterProgress(){   
		return ++chaptersCompleted;
	}
	public int incSectionProgress(){
		return ++sectionsCompleted;
	}
	public double getProgressPercent(){
		return chaptersCompleted/course.getChapterCount();
	}




}
