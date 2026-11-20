package com.hieunn.learnenglish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JapaneseGrammarPoint implements Serializable {
    private String id;
    private int pointNumber;
    private int totalPoints;
    private String title;
    private String meaning;
    private String level;
    private String levelLabel;
    private String structure;
    private String explanation;
    private List<GrammarExample> examples;
    private String notes;
    private List<GrammarQuiz> quizzes;

    public JapaneseGrammarPoint(String id, int pointNumber, int totalPoints, String title,
                                String meaning, String level, String levelLabel,
                                String structure, String explanation,
                                List<GrammarExample> examples, String notes,
                                List<GrammarQuiz> quizzes) {
        this.id = id;
        this.pointNumber = pointNumber;
        this.totalPoints = totalPoints;
        this.title = title;
        this.meaning = meaning;
        this.level = level;
        this.levelLabel = levelLabel;
        this.structure = structure;
        this.explanation = explanation;
        this.examples = examples != null ? examples : new ArrayList<>();
        this.notes = notes != null ? notes : "";
        this.quizzes = quizzes != null ? quizzes : new ArrayList<>();
    }

    public String getId() {
        return id != null ? id : "";
    }

    public int getPointNumber() {
        return pointNumber;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public String getTitle() {
        return title != null ? title : "";
    }

    public String getMeaning() {
        return meaning != null ? meaning : "";
    }

    public String getLevel() {
        return level != null ? level : "N5";
    }

    public String getLevelLabel() {
        return levelLabel != null ? levelLabel : "Cơ bản";
    }

    public String getStructure() {
        return structure != null ? structure : "";
    }

    public String getExplanation() {
        return explanation != null ? explanation : "";
    }

    public List<GrammarExample> getExamples() {
        return examples != null ? examples : new ArrayList<>();
    }

    public String getNotes() {
        return notes != null ? notes : "";
    }

    public List<GrammarQuiz> getQuizzes() {
        return quizzes != null ? quizzes : new ArrayList<>();
    }
}
