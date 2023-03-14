package lms;

import java.util.ArrayList;

/**
 * This class will hold the information that each question holds
 */
public class Question {
    private String question;
    private ArrayList<String> answers;
    private int correctAnswer;

    public Question(String question, ArrayList<String> answers, int correctAnswers){
        this.question = question;
        this.answers =new ArrayList<String>();
        this.correctAnswer = correctAnswers;
    }

    public ArrayList<String> getAnswers(){
        return answers;
    }

    public void setAnswers(ArrayList<String> answers){
        this.answers = answers;
    }

    public String getQuestion(){
        return question;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public int getCorrectAnswer(){
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer){
        this.correctAnswer = correctAnswer;
    }
    
}
