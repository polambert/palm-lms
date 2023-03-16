package lms;

import java.util.ArrayList;

/**
 * This class sets all parameters for a Chapter
 */
public class Chapter {   
    private ArrayList<Section> sections;
    private Assessment test;

    /**
     * Creates a Chapter with sections
     * @param sections ArrayList of Section types
     */
    public Chapter(ArrayList<Section> sections){
        this.sections = new ArrayList<>();
    }
    /**
     * Creates a chapter with sections and a test for the chapter
     * @param sections ArrayList of Section types
     * @param test type Assessment
     */
    public Chapter(ArrayList<Section> sections, Assessment test){
        this.sections = new ArrayList<Section>();
        this.test = test;
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
