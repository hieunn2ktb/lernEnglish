package com.hieunn.learnenglish;

import java.io.Serializable;
import java.util.List;

public class ChoukaiLesson implements Serializable {
    private final int lessonNumber;
    private final String level;
    private final String title;
    private final String subtitle;
    private final List<ChoukaiTask> tasks;

    public ChoukaiLesson(int lessonNumber, String level, String title, String subtitle, List<ChoukaiTask> tasks) {
        this.lessonNumber = lessonNumber;
        this.level = level;
        this.title = title;
        this.subtitle = subtitle;
        this.tasks = tasks;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public String getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public List<ChoukaiTask> getTasks() {
        return tasks;
    }
}
