
import java.util.ArrayList;

/**
 * This class will be used for each Assessment in a chapter or section
 * and can add questions to a certain Assessment
 */

public class Assessment {
    private ArrayList<Question> questions;
    
    public Assessment(ArrayList<Question> questions){
        this.questions = questions;
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
