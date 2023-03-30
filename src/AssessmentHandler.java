
import java.util.ArrayList;
import java.util.Scanner;

/**
 * AssessmentHandler
 * Provides the static method for assessment-taking
 * @author Parker Lambert
 */
public class AssessmentHandler
{
	/**
	 * Clears the screen
	 */
	private static void clearScreen() {
		System.out.println("\n\n\n\n\n\n"); // visually shows clear if they scroll up
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	/**
	 * Starts and runs an Assessment for the User to take
	 * @param assessment The Assessment to take
	 * @return Grade, from 0 to 100 inclusive
	 */
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

		double grade = 100;

		if (questions.size() != 0) {
			grade = 100 * numCorrect / (numCorrect + numWrong);
		}

		System.out.println("You scored a " + (int) grade + "%.");

		if (grade >= 80) {
			// pass
			System.out.println("You passed!");
		} else {
			// fail
			System.out.println("You failed, and you will have to retake this assessment again.");
		}

		System.out.println();

		return grade;
	}

}
