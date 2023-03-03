import java.util.UUID;
import java.util.Date;
import java.util.ArrayList;

public class Comment {

    private String comment;
    private User author;
    private UUID id;
    private Date date;
    private ArrayList<Comment> replies;

    //Create
    public Comment(String comment, User author) {
        //this.id = UUID.randomUUID();
        //this.comment = comment;
        //this.author = author;
        //this.date = new Date();
        //this.replies = new ArrayList<Comment>;
    }

    //Set
    public Comment(UUID id, String comment, User author) {
        //this.id = id;
        //this.comment = comment;
        //this.author = author;
        //this.date = new Date();
        //this.replies = new ArrayList<Comment>;
    }

    public String getComment() {
        return comment;
    }
    public User getAuthor() {
        return author;
    }
    public Date getDate() {
        return date;
    }
    public UUID getUUID() {
        return id;
    }
    public ArrayList<Comment> getReplies() {
        return replies;
    }
    public void setComment(String comment) {

    }
    public void setAuthor(User author) {

    }
    public void setDate(Date date) {

    }
    public void setUUID(UUID id) {

    }
    public void setReplies(ArrayList<Comment> replies) {
        
    }
}