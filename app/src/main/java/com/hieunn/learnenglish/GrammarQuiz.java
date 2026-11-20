package com.hieunn.learnenglish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GrammarQuiz implements Serializable {
    private String question;
    private String meaning;
    private List<String> options;
    private int correctIndex;
    private String explanation;

    public GrammarQuiz(String question, String meaning, List<String> options, int correctIndex, String explanation) {
        this.question = question;
        this.meaning = meaning;
        this.options = options != null ? options : new ArrayList<>();
        this.correctIndex = correctIndex;
        this.explanation = explanation;
    }

    public String getQuestion() {
        return question != null ? question : "";
    }

    public String getMeaning() {
        return meaning != null ? meaning : "";
    }

    public List<String> getOptions() {
        return options != null ? options : new ArrayList<>();
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public String getExplanation() {
        return explanation != null ? explanation : "";
    }
}
