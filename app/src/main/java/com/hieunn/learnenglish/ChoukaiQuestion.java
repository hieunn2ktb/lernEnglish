package com.hieunn.learnenglish;

import java.io.Serializable;
import java.util.List;

public class ChoukaiQuestion implements Serializable {
    private final int questionNumber;
    private final String itemIndex; // e.g. "例", "1", "2", "3"
    private final String prompt; // e.g. "Ví dụ: Vật được hỏi là gì?"
    private final String questionText;
    private final String questionVi;
    private final List<String> options;
    private final int correctIndex;
    private final String correctAnswer; // "a", "b", "c"
    private final String explanation;
    private final String dialogueScript; // sub-dialogue for this question

    public ChoukaiQuestion(int questionNumber, String itemIndex, String prompt,
                           String questionText, String questionVi, List<String> options,
                           int correctIndex, String correctAnswer, String explanation,
                           String dialogueScript) {
        this.questionNumber = questionNumber;
        this.itemIndex = itemIndex != null ? itemIndex : String.valueOf(questionNumber);
        this.prompt = prompt != null ? prompt : "";
        this.questionText = questionText != null ? questionText : "";
        this.questionVi = questionVi != null ? questionVi : "";
        this.options = options;
        this.correctIndex = correctIndex;
        this.correctAnswer = correctAnswer != null ? correctAnswer : "";
        this.explanation = explanation != null ? explanation : "";
        this.dialogueScript = dialogueScript != null ? dialogueScript : "";
    }

    public ChoukaiQuestion(int questionNumber, String questionText, String questionVi,
                           List<String> options, int correctIndex, String explanation) {
        this(questionNumber, String.valueOf(questionNumber), questionText, questionText, questionVi,
                options, correctIndex, "", explanation, "");
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getItemIndex() {
        return itemIndex;
    }

    public boolean isExample() {
        if (itemIndex == null) return false;
        String s = itemIndex.trim().toLowerCase();
        return s.equals("例") || s.contains("ví dụ") || s.contains("vi du") || s.contains("rei");
    }

    public String getPrompt() {
        if (prompt != null && !prompt.isEmpty()) return prompt;
        if (questionText != null && !questionText.isEmpty()) return questionText;
        return "";
    }

    public String getQuestionText() {
        return getPrompt();
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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getDialogueScript() {
        return dialogueScript;
    }
}
