public class AssessmentHandler
{
    private Assessment assessment;
    private int numberCorrect;
    private int numberIncorrect;
    private Question currentQuestion;
    private int currentQuestionNumber;

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


    public AssessmentHandler(){

    }
    public void startAssessment(Assessment assessment){

    }
    public double calculateScore(){

    }


}
