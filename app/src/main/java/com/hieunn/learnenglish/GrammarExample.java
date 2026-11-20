package com.hieunn.learnenglish;

import java.io.Serializable;

public class GrammarExample implements Serializable {
    private String label;
    private String japanese;
    private String romaji;
    private String vietnamese;

    public GrammarExample(String label, String japanese, String romaji, String vietnamese) {
        this.label = label;
        this.japanese = japanese;
        this.romaji = romaji;
        this.vietnamese = vietnamese;
    }

    public String getLabel() {
        return label != null ? label : "";
    }

    public String getJapanese() {
        return japanese != null ? japanese : "";
    }

    public String getRomaji() {
        return romaji != null ? romaji : "";
    }

    public String getVietnamese() {
        return vietnamese != null ? vietnamese : "";
    }
}
