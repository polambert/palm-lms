
import java.util.ArrayList;

import java.time.LocalDate;

public class CourseProgress {
	private Course course;
	private int chaptersCompleted;
	private int sectionsCompleted;
	private ArrayList<ArrayList<Double>> grades = new ArrayList<>();
	private double grade;
	private LocalDate dateCompleted;
	private String certificateId;


	public CourseProgress(Course course, int chaptersCompleted, int sectionsCompleted, ArrayList<ArrayList<Double>> grades) {
		this.course = course;
		this.chaptersCompleted = chaptersCompleted;
		this.sectionsCompleted = sectionsCompleted;
		this.grades = grades;
	}

	public CourseProgress(Course course) {
		this.course = course;
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

	public String getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(String certificateId) {
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
