
import java.util.ArrayList;
import java.util.UUID;

import java.time.LocalDate;

public class CourseProgress {
	private Course course;
	private int chaptersCompleted;
	private int sectionsCompleted;
	private ArrayList<ArrayList<Double>> grades = new ArrayList<>();
	private double grade;
	private LocalDate dateCompleted;
	private UUID certificateId;


	public CourseProgress(Course course, int chaptersCompleted, int sectionsCompleted, ArrayList<ArrayList<Double>> grades) {
		this.course = course;
		this.chaptersCompleted = chaptersCompleted;
		this.sectionsCompleted = sectionsCompleted;
		this.grades = grades;
	}

	public CourseProgress(Course course) {
		this.course = course;
	}
	
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

	}
	public void setSectionProgress(int newSectionProgress) {

	}
	public int incChapterProgress(){   
		return chaptersCompleted+1;
	}
	public int incSectionProgress(){
		return sectionsCompleted+1;
	}
	public double getProgressPercent(){
		return chaptersCompleted/course.getChapterCount();
	}
	public String generateCertificate(){
		return "";
	}




}
