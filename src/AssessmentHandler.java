
import java.util.ArrayList;
import java.util.Scanner;

public class AssessmentHandler
{
	private int numberCorrect;
	private int numberIncorrect;
	private Question currentQuestion;
	private int currentQuestionNumber;

	public AssessmentHandler(){

	}

	private static void clearScreen() {
		System.out.println("\n\n\n\n\n\n"); // visually shows clear if they scroll up
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	// returns grade achieved
	public static double start(Assessment assessment) {
		// start taking the assessment
		Scanner scanner = new Scanner(System.in);

		ArrayList<Question> questions = assessment.getQuestions();

		int numCorrect = 0;
		int numWrong = 0;

		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			System.out.println("Question #" + (i+1) + ": " + question.getQuestion());

			ArrayList<String> answers = question.getAnswers();
			for (int j = 0; j < answers.size(); j++) {
				System.out.println("  " + (j+1) + ". " + answers.get(j));
			}

			// get answer
			int answer = Integer.parseInt(scanner.nextLine()) - 1;
			int correct = question.getCorrectAnswer();

			clearScreen();

			if (answer == correct) {
				System.out.println("Correct!");
				numCorrect++;
			} else {
				System.out.println("Sorry, that was incorrect.");
				numWrong++;
			}
		}

		double grade = 100 * numCorrect / (numCorrect + numWrong);

		System.out.println("You scored a " + (int) grade + "%.");

		if (grade >= 80) {
			// pass
			System.out.println("You passed! You will now be moved on to the next section.");
		} else {
			// fail
			System.out.println("You failed, and you will have to retake this section quiz again.");
		}

		System.out.println();

		return grade;
	}

	public double calculateScore(){
		return 0;
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
