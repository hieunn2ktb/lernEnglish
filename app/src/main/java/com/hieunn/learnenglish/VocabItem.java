package com.hieunn.learnenglish;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean isJapanese() {
        return isJapaneseText(englishWord);
    }

    public static boolean isJapaneseText(String text) {
        if (text == null) return false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // Hiragana: 0x3040-0x309F, Katakana: 0x30A0-0x30FF, CJK Kanji: 0x4E00-0x9FAF
            if ((c >= 0x3040 && c <= 0x309F) || (c >= 0x30A0 && c <= 0x30FF) || (c >= 0x4E00 && c <= 0x9FAF)) {
                return true;
            }
        }
        return false;
    }

    public static String getCleanSpeechText(String word) {
        if (word == null) return "";
        String clean = word.replaceAll("【[^】]*】", "").trim();
        int parenIdx = clean.indexOf('(');
        if (parenIdx > 0) {
            clean = clean.substring(0, parenIdx).trim();
        }
        return clean;
    }

    public static boolean isAnswerMatching(String input, String expected, String phonetic) {
        if (input == null || expected == null) return false;
        String cleanInput = input.trim().toLowerCase();
        String cleanExpected = expected.trim().toLowerCase();
        if (cleanInput.isEmpty()) return false;

        // Direct match
        if (cleanInput.equalsIgnoreCase(cleanExpected)) return true;

        // Check Romaji / Phonetic match
        if (phonetic != null && !phonetic.isEmpty()) {
            String cleanPhonetic = phonetic.trim().toLowerCase();
            if (cleanInput.equalsIgnoreCase(cleanPhonetic)) return true;
            if (cleanInput.replace("-", "").equalsIgnoreCase(cleanPhonetic.replace("-", ""))) return true;
        }

        // Check Japanese components
        if (isJapaneseText(expected)) {
            String noBrackets = expected.replaceAll("【[^】]*】", "").trim();
            if (cleanInput.equalsIgnoreCase(noBrackets)) return true;

            // Check kana or kanji inside/outside parentheses, e.g. "わたし (私)"
            int pOpen = noBrackets.indexOf('(');
            int pClose = noBrackets.indexOf(')');
            if (pOpen > 0 && pClose > pOpen) {
                String kana = noBrackets.substring(0, pOpen).trim();
                String kanji = noBrackets.substring(pOpen + 1, pClose).trim();
                if (cleanInput.equalsIgnoreCase(kana)) return true;
                if (cleanInput.equalsIgnoreCase(kanji)) return true;
            }

            // Also check without 「な」
            String noNa = noBrackets.replace("「な」", "").replace("［な］", "").trim();
            if (cleanInput.equalsIgnoreCase(noNa)) return true;
        }

        return false;
    }
}
