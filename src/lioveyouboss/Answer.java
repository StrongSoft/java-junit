package lioveyouboss;

public class Answer {
    private final int i;
    private final Question question;

    public Answer(Question question, int i) {
        this.question = question;
        this.i = i;
    }

    public Answer(Question characteristic, String matchingValue) {
        question = characteristic;
        i = characteristic.indexOf(matchingValue);
    }

    public String getQuestionText() {
        return question.getText();
    }

    @Override
    public String toString() {
        return String.format("%s %s", question.getText(), question.getAnswerChoices(i));
    }

    public boolean match(int expected) {
        return question.match(expected, i);
    }

    public boolean match(Answer otherAnswer) {
        return question.match(i, otherAnswer.i);
    }

    public Question getCharacteristic() {
        return question;
    }
}
