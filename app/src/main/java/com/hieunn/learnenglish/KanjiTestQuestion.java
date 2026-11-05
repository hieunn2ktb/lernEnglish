package com.hieunn.learnenglish;

import java.io.Serializable;
import java.util.List;

public class KanjiTestQuestion implements Serializable {
    private int number;
    private String question;
    private List<String> options;
    private int correctAnswerIndex; // 1-based index (1 to 4)

    public KanjiTestQuestion(int number, String question, List<String> options, int correctAnswerIndex) {
        this.number = number;
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public int getNumber() {
        return number;
    }

    public String getQuestion() {
        return question != null ? question : "";
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public int getZeroBasedCorrectIndex() {
        return correctAnswerIndex >= 1 ? correctAnswerIndex - 1 : 0;
    }

    public String getCorrectOptionText() {
        int idx = getZeroBasedCorrectIndex();
        if (options != null && idx >= 0 && idx < options.size()) {
            return options.get(idx);
        }
        return "";
    }

    public boolean isCorrect(int selectedZeroBased) {
        return selectedZeroBased == getZeroBasedCorrectIndex();
    }
}
