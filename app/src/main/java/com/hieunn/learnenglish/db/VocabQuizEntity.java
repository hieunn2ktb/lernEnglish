package com.hieunn.learnenglish.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vocab_quiz")
public class VocabQuizEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int lessonId;
    public String word;
    public String correctTitle;
    public String correctDesc;
    public String wrong1Title;
    public String wrong1Desc;
    public String wrong2Title;
    public String wrong2Desc;
    public String wrong3Title;
    public String wrong3Desc;
    public String explanation;

    public VocabQuizEntity() {
    }

    public VocabQuizEntity(int lessonId, String word, String correctTitle, String correctDesc,
            String wrong1Title, String wrong1Desc, String wrong2Title, String wrong2Desc,
            String wrong3Title, String wrong3Desc, String explanation) {
        this.lessonId = lessonId;
        this.word = word;
        this.correctTitle = correctTitle;
        this.correctDesc = correctDesc;
        this.wrong1Title = wrong1Title;
        this.wrong1Desc = wrong1Desc;
        this.wrong2Title = wrong2Title;
        this.wrong2Desc = wrong2Desc;
        this.wrong3Title = wrong3Title;
        this.wrong3Desc = wrong3Desc;
        this.explanation = explanation;
    }
}
