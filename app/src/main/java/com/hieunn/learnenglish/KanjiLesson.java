package com.hieunn.learnenglish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KanjiLesson implements Serializable {
    private int id;
    private int lessonNumber;
    private String title;
    private String description;
    private boolean isTest;
    private int totalKanji;
    private List<KanjiItem> kanjis;
    private List<String> readingPractice;
    private List<String> writingPractice;
    private List<KanjiTestQuestion> questions;

    public KanjiLesson(int id, int lessonNumber, String title, String description, boolean isTest,
                       int totalKanji, List<KanjiItem> kanjis, List<String> readingPractice,
                       List<String> writingPractice, List<KanjiTestQuestion> questions) {
        this.id = id;
        this.lessonNumber = lessonNumber;
        this.title = title;
        this.description = description;
        this.isTest = isTest;
        this.totalKanji = totalKanji;
        this.kanjis = kanjis != null ? kanjis : new ArrayList<>();
        this.readingPractice = readingPractice != null ? readingPractice : new ArrayList<>();
        this.writingPractice = writingPractice != null ? writingPractice : new ArrayList<>();
        this.questions = questions != null ? questions : new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public String getTitle() {
        return title != null ? title : "";
    }

    public String getDescription() {
        return description != null ? description : "";
    }

    public boolean isTest() {
        return isTest;
    }

    public int getTotalKanji() {
        return totalKanji;
    }

    public List<KanjiItem> getKanjis() {
        return kanjis;
    }

    public List<String> getReadingPractice() {
        return readingPractice;
    }

    public List<String> getWritingPractice() {
        return writingPractice;
    }

    public List<KanjiTestQuestion> getQuestions() {
        return questions;
    }
}
