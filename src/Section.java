
/**
 * This class will provide the information for a Courses section
 * and provide the description
 * @author Luke Lane
 */
public class Section {
    private String name;
    private String text;
    private Assessment quiz;

    /**
     * Creates a section with description
     * @param text String description of section
     */
    public Section(String name, String text, Assessment quiz) {
        this.name = name;
        this.text = text;
        this.quiz = quiz;
    }

    // getters and setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
