
import java.util.ArrayList;

public class AssessmentHandler
{
	private int numberCorrect;
	private int numberIncorrect;
	private Question currentQuestion;
	private int currentQuestionNumber;

	public AssessmentHandler(){

	}
	public static void start(Assessment assessment){
		// start taking the assessment
		ArrayList<Question> question = assessment.getQuestions();		
	}
	public double calculateScore(){
		return 0;
	}

	//getters and setters
	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public int getNumberCorrect() {
		return numberCorrect;
	}

	public void setNumberCorrect(int numberCorrect) {
		this.numberCorrect = numberCorrect;
	}

	public int getNumberIncorrect() {
		return numberIncorrect;
	}
	public void setNumberIncorrect(int numberIncorrect) {
		this.numberIncorrect = numberIncorrect;
	}

	public Question getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(Question currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

	public int getCurrentQuestionNumber() {
		return currentQuestionNumber;
	}

	public void setCurrentQuestionNumber(int currentQuestionNumber) {
		this.currentQuestionNumber = currentQuestionNumber;
	}


}
