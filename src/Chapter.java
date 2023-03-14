
import java.util.ArrayList;

/**
 * This class will be the parent to Section and Assesment
 */
public class Chapter {   
    private ArrayList<Section> sections;
    private Assessment test;

    public Chapter(ArrayList<Section> sections){
        this.sections = new ArrayList<>();
    }

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
