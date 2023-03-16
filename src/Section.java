package lms;

/**
 * This class will provide the information for a Courses section
 * and provide the description
 */
public class Section {
    private String text;
    private Assessment quiz;
    /**
     * Creates a section with description
     * @param text String description of section
     */
    public Section(String text){
        this.text = text;
    }
    /**
     * Creates a section with assigned quiz
     * @param text String description of section
     * @param quiz Assessment for section
     */
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
