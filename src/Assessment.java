
import java.util.ArrayList;

/**
 * This class will be used for each Assessment in a chapter or section
 * and can add questions to a certain Assessment
 * @author Luke Lane
 */
public class Assessment {
    private ArrayList<Question> questions;
    /**
     * Creates a new Assessment with an ArrayList of questions
     * @param questions ArrayList of Question types
     */
    public Assessment(ArrayList<Question> questions){
        this.questions = questions;
    }

    /**
     * Adds a question to the assessment
     * @param question The question to add
     */
    public void addQuestion(Question question) {
        questions.add(question);    
    }
    
    // getters and setters
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions){
        this.questions = questions;
    }

}
