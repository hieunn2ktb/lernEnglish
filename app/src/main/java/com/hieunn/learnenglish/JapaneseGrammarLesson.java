package com.hieunn.learnenglish;

import java.io.Serializable;

public class JapaneseGrammarLesson implements Serializable {
    private int lessonNumber;
    private String title;
    private String summary;
    private String content;

    public JapaneseGrammarLesson(int lessonNumber, String title, String summary, String content) {
        this.lessonNumber = lessonNumber;
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public int getLessonNumber() {
        return lessonNumber;
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
}
