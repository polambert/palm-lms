package lms;

/**
 * This class will provide the information for a Courses section
 * and provide the description
 */
public class Section {
    private String text;
    private Assesment quiz;

    public Section(String text){
        this.text = text;
    }

    public Section(String text, Assesment quiz){
        this.text = text;
        this.quiz = quiz; 
    }

    public Assesment getQuiz(){
        return quiz;
    }

    public void setQuiz(Assesment quiz){
        this.quiz = quiz;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

}
