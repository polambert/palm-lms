
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

public class CourseProgress 
{
    private Course course;
    private User user;
    private int chaptersCompleted;
    private int sectionsCompleted;
    private ArrayList<ArrayList<Double>> chapters = new ArrayList<>();
    private double grade;
    private Date dateCompleted;
    private String certificateId;


    // getters and setters
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public ArrayList<ArrayList<double>> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<ArrayList<double>> chapters) {
        this.chapters = chapters;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public CourseProgress(Course course, int chaptersCompleted, int sectionsCompleted){

    }
    public CourseProgress(Course course){

    }
    public int getChapterProgress(){

    }
    public int getSectionProgress(){

    }
    public void setChapterProgress(int){

    }
    public void setSectionProgress(int){

    }
    public int incChapterProgress(){

    }
    public int incSectionProgress(){

    }
    public double getProgressPercent(){

    }
    public String generateCertificate(){

    }




}
