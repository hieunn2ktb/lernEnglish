package com.hieunn.learnenglish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JapaneseGrammarLesson implements Serializable {
    private int lessonNumber;
    private String level;
    private String title;
    private String summary;
    private String content;
    private List<JapaneseGrammarPoint> points;

    public JapaneseGrammarLesson(int lessonNumber, String title, String summary, String content) {
        this.lessonNumber = lessonNumber;
        this.level = lessonNumber <= 25 ? "N5" : "N4";
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.points = new ArrayList<>();
    }

    public JapaneseGrammarLesson(int lessonNumber, String level, String title, String summary,
                                 String content, List<JapaneseGrammarPoint> points) {
        this.lessonNumber = lessonNumber;
        this.level = level != null ? level : (lessonNumber <= 25 ? "N5" : "N4");
        this.title = title;
        this.summary = summary;
        this.content = content != null ? content : "";
        this.points = points != null ? points : new ArrayList<>();
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public String getLevel() {
        return level != null ? level : (lessonNumber <= 25 ? "N5" : "N4");
    }

    public String getTitle() {
        return title != null ? title : "";
    }

    public String getSummary() {
        return summary != null ? summary : "";
    }

    public String getContent() {
        return content != null ? content : "";
    }

    public List<JapaneseGrammarPoint> getPoints() {
        return points != null ? points : new ArrayList<>();
    }

    public int getPointsCount() {
        return points != null ? points.size() : 0;
    }

    public JapaneseGrammarPoint getPointByNumber(int pointNumber) {
        if (points == null) return null;
        for (JapaneseGrammarPoint p : points) {
            if (p.getPointNumber() == pointNumber) {
                return p;
            }
        }
        return null;
    }
}
