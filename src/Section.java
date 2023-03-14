
/**
 * This class will provide the information for a Courses section
 * and provide the description
 */
public class Section {
    private String text;
    private Assessment quiz;

    public Section(String text){
        this.text = text;
    }

    public Section(String text, Assessment quiz){
        this.text = text;
        this.quiz = quiz; 
    }

    public Assessment getQuiz(){
        return quiz;
    }

    public void setQuiz(Assessment quiz){
        this.quiz = quiz;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

}
