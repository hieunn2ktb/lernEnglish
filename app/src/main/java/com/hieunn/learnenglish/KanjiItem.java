package com.hieunn.learnenglish;

import java.io.Serializable;
import java.util.List;

public class KanjiItem implements Serializable {
    private String kanji;
    private String hanViet;
    private String kunyomi;
    private String onyomi;
    private List<String> examples;

    public KanjiItem(String kanji, String hanViet, String kunyomi, String onyomi, List<String> examples) {
        this.kanji = kanji;
        this.hanViet = hanViet;
        this.kunyomi = kunyomi;
        this.onyomi = onyomi;
        this.examples = examples;
    }

    public String getKanji() {
        return kanji != null ? kanji : "";
    }

    public String getHanViet() {
        return hanViet != null ? hanViet : "";
    }

    public String getKunyomi() {
        return kunyomi != null ? kunyomi : "";
    }

    public String getOnyomi() {
        return onyomi != null ? onyomi : "";
    }

    public List<String> getExamples() {
        return examples;
    }
}
