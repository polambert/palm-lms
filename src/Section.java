
/**
 * This class will provide the information for a Courses section
 * and provide the description
 */
public class Section {
	private String name;
	private String text;
	private Assessment quiz;

	public Section(String name, String text) {
		this.name = name;
		this.text = text;
	}

	public Section(String name, String text, Assessment quiz){
		this.name = name;
		this.text = text;
		this.quiz = quiz; 
	}

	public String getName() { return name; }
	public void setName(String newName) { this.name = newName; }

	public Assessment getQuiz() {
		return quiz;
	}

	public void setQuiz(Assessment quiz) {
		this.quiz = quiz;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
