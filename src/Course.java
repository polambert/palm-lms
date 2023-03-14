package lms;

import java.util.ArrayList;
import java.util.UUID;

public class Course 
{
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

    //create
    public Course(int id, String title){
        //this.id = UUID.randomUUID();
        //this.title = title;
    }


    public Review addReview(int rating, String text, User author){
        return review;
    }

    public int getChapterCount(){
        return int;
    }
    
    public Assessment getFinalExam(){
        return assessment;
    }


