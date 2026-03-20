package com.hieunn.learnenglish.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grammar_quiz")
public class GrammarQuizEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int lessonId;
    public String question;
    public String optionA;
    public String optionB;
    public String optionC;
    public String optionD;
    public String correctAnswer;

    public GrammarQuizEntity() {
    }

    public GrammarQuizEntity(int lessonId, String question, String optionA, String optionB,
            String optionC, String optionD, String correctAnswer) {
        this.lessonId = lessonId;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }
}
