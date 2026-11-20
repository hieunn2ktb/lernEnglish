package com.hieunn.learnenglish;

import java.io.Serializable;
import java.util.List;

public class ChoukaiTask implements Serializable {
    private final int taskNumber;
    private final String title;
    private final String titleVi;
    private final String cdName;
    private final String cdFolder;
    private final String audioFile;
    private final String trackLabel;
    private final String script;
    private final String scriptVi;
    private final List<ChoukaiQuestion> questions;

    public ChoukaiTask(int taskNumber, String title, String titleVi, String cdName, String cdFolder,
                       String audioFile, String trackLabel, String script, String scriptVi,
                       List<ChoukaiQuestion> questions) {
        this.taskNumber = taskNumber;
        this.title = title;
        this.titleVi = titleVi;
        this.cdName = cdName;
        this.cdFolder = cdFolder;
        this.audioFile = audioFile;
        this.trackLabel = trackLabel;
        this.script = script;
        this.scriptVi = scriptVi;
        this.questions = questions;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleVi() {
        return titleVi;
    }

    public String getCdName() {
        return cdName;
    }

    public String getCdFolder() {
        return cdFolder;
    }

    public String getAudioFile() {
        return audioFile;
    }

    public String getTrackLabel() {
        return trackLabel;
    }

    public String getScript() {
        return script;
    }

    public String getScriptVi() {
        return scriptVi;
    }

    public List<ChoukaiQuestion> getQuestions() {
        return questions;
    }
}
