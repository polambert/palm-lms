
import java.util.ArrayList;

/**
 * This class will hold the information that each question holds
 * @author PALM
 */
public class Question {
    private String question;
    private ArrayList<String> answers;
    private int correctAnswer;

    /**
     * Creates a new question
     * @param question String; The actual question
     * @param answers ArrayList of Strings for possible answers
     * @param correctAnswer int for Correct answer in ArrayList answers
     */
    public Question(String question, ArrayList<String> answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    // getters and setters
    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public String getQuestion() {
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
