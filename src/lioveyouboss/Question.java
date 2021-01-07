package lioveyouboss;

public abstract class Question {
    private final String text;
    private final String[] answerChoices;
    private final int id;

    public Question(int id, String text, String[] answerChoices) {
        this.text = text;
        this.answerChoices = answerChoices;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getAnswerChoices(int i) {
        return answerChoices[i];
    }

    abstract public boolean match(int expected, int actual);

    public int indexOf(String matchingAnswerChoice) {
        for (int i = 0; i < answerChoices.length; i++) {
            if (answerChoices[i].equals(matchingAnswerChoice)) {
                return i;
            }
        }
        return -1;
    }
}
