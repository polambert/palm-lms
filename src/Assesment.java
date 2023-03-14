package lms;

import java.util.ArrayList;

/**
 * This class will be used for each Assesment in a chapter or section
 * and can add questions to a certain Assesment
 */

public class Assesment {
    private ArrayList<Question> questions;
    
    public Assesment(ArrayList<Question> questions){
        this.questions = new ArrayList<Question>();
    }

    public void addQuestion(Question question){
        questions.add(question);    
    }
    
    public ArrayList<Question> getQuestions(){
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions){
        this.questions = questions;
    }
}
