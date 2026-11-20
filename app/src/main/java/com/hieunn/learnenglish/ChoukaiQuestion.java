package com.hieunn.learnenglish;

import java.io.Serializable;
import java.util.List;

public class ChoukaiQuestion implements Serializable {
    private final int questionNumber;
    private final String questionText;
    private final String questionVi;
    private final List<String> options;
    private final int correctIndex;
    private final String explanation;

    public ChoukaiQuestion(int questionNumber, String questionText, String questionVi,
                           List<String> options, int correctIndex, String explanation) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.questionVi = questionVi;
        this.options = options;
        this.correctIndex = correctIndex;
        this.explanation = explanation;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getQuestionVi() {
        return questionVi;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public String getExplanation() {
        return explanation;
    }
}
