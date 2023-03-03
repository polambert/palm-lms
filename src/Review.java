import java.util.UUID;
import java.util.Date;

public class Review {

    private int rating;
    private String text;
    private UUID id;
    private Date date;
    private User author;

    //Create
    public Review(int rating, Course course, User author, String text) {
        //this.id = UUID.randomUUID();
        //this.rating = rating;
        //this.course = course;
        //this.author = author;
        //this.text = text;
        //this.date = new Date();
    }

    //Set
    public Review(UUID id, int rating, Course course, User author, String text) {
        //this.id = id;
        //this.rating = rating;
        //this.course = course;
        //this.author = author;
        //this.text = text;
        //this.date = new Date();
    }

    public int getRating() {
        return rating;
    }
    public Course getCourse() {
        return course;
    }
    public User getAuthor() {
        return author;
    }
    public String getText() {
        return text;
    }
    public Date getDate() {
        return date;
    }
    public void setRating(int rating) {

    }
    public void setCoures(Course course) {

    }
    public void setAuthor(User author) {

    }
    public void setText(String text) {

    }
    public void setDate(Date date) {
        
    }

}