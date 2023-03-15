
import java.util.ArrayList;

/**
 * This class will be the parent to Section and Assesment
 */
public class Chapter {
    private String name;
    private ArrayList<Section> sections;
    private Assessment test;

    public Chapter(String name, ArrayList<Section> sections) {
        this.name = name;
        this.sections = sections;
    }

    public Chapter(String name, ArrayList<Section> sections, Assessment test) {
        this.name = name;
        this.sections = sections;
        this.test = test;
    }

    public int getSectionCount() {
        return sections.size();
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSection(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public Assessment getTest() {
        return test;
    }

    public void setTest(Assessment test) {
        this.test = test;
    }

}
