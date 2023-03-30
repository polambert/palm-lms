

import java.util.ArrayList;

/**
 * This class stores all informationa and methods for chapters
 * @author Luke Lane
 */
public class Chapter {
    private String name;
    private ArrayList<Section> sections;
    private Assessment test;

    /**
     * Creates a Chapter with sections
     * @param sections ArrayList of Section types
     */
    public Chapter(String name, ArrayList<Section> sections, Assessment test) {
        this.name = name;
        this.sections = sections;
        this.test = test;
    }

    // getters and setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getSectionCount() {
        return sections.size();
    }

    public ArrayList<Section> getSections(){
        return sections;
    }

    public void setSection(ArrayList<Section> sections){
        this.sections = sections;
    }

    public Assessment getTest(){
        return test;
    }

    public void setTest(Assessment test){
        this.test = test;
    }

}
