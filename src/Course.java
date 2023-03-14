package lms;

import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private UUID id;
    private String language;
    private String title;
    private String name;
    private User author;
    private String description;
    private ArrayList<Chapter> chapters = new ArrayList<>();
    private Assessment finalExam;
    private ArrayList<Review> reviews = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();

    //getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    public void setFinalExam(Assessment finalExam) {
        this.finalExam = finalExam;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    //create
    public Course(int id, String title) {
        //this.id = UUID.randomUUID();
        //this.title = title;
    }


    public Review addReview(int rating, String text, User author) {
        return review;
    }

    public int getChapterCount() {
        return int ;
    }

    public Assessment getFinalExam() {
        return assessment;
    }





}


