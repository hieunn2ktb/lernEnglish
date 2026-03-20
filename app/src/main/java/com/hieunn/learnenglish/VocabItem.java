package com.hieunn.learnenglish;

public class VocabItem {
    private String englishWord;
    private String vietnameseMeaning;
    private String phonetic;
    private String wordType;

    public VocabItem(String englishWord, String vietnameseMeaning, String phonetic, String wordType) {
        this.englishWord = englishWord;
        this.vietnameseMeaning = vietnameseMeaning;
        this.phonetic = phonetic;
        this.wordType = wordType;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getVietnameseMeaning() {
        return vietnameseMeaning;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public String getWordType() {
        return wordType;
    }
}
