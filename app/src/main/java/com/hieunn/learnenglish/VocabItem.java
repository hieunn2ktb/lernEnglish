package com.hieunn.learnenglish;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        int fullParenIdx = clean.indexOf('（');
        if (fullParenIdx > 0) {
            clean = clean.substring(0, fullParenIdx).trim();
        }
        return clean;
    }

    public static String normalizeText(String s) {
        if (s == null) return "";
        return s.replace('\u3000', ' ')
                .replace('’', '\'')
                .replace('‘', '\'')
                .replace('`', '\'')
                .replace('“', '"')
                .replace('”', '"')
                .replace('–', '-')
                .replace('—', '-')
                .replaceAll("\\s+", " ")
                .trim();
    }

    public static String stripPunctuation(String s) {
        if (s == null) return "";
        return s.replaceAll("[.。、,!?:;~～*＊]+$", "").replaceAll("^[~～*＊]+", "").trim();
    }

    public static String stripSymbols(String s) {
        if (s == null) return "";
        return s.replace("~", "").replace("～", "").replace("*", "").replace("＊", "").trim();
    }

    public static String stripKanji(String s) {
        if (s == null) return "";
        return s.replaceAll("[\\u4E00-\\u9FAF]", "");
    }

    public static String removeAllBracketsAndParens(String s) {
        if (s == null) return "";
        String prev = null;
        String curr = s;
        while (!curr.equals(prev)) {
            prev = curr;
            curr = curr.replaceAll("\\([^()]*\\)|（[^（）]*）|\\[[^\\[\\]]*\\]|［[^［］]*］|【[^【】]*】|《[^《》]*》", "");
        }
        return curr;
    }

    public static List<String> extractAllBracketContents(String s) {
        List<String> contents = new ArrayList<>();
        if (s == null) return contents;
        Pattern pattern = Pattern.compile("[\\(（\\[［【《]([^\\(（\\)）\\[\\]［］【】《》]+)[\\)）\\]］】》]");
        String working = s;
        while (true) {
            Matcher m = pattern.matcher(working);
            boolean found = false;
            while (m.find()) {
                found = true;
                contents.add(m.group(1).trim());
            }
            if (!found) break;
            working = pattern.matcher(working).replaceAll(" ");
        }
        return contents;
    }

    public static Set<String> getCandidates(String expected, String phonetic) {
        Set<String> candidates = new HashSet<>();
        String exp = normalizeText(expected);
        if (exp.isEmpty()) return candidates;

        candidates.add(exp);

        // 1. Completely stripped of all brackets & parentheses
        String noParensAll = removeAllBracketsAndParens(exp).replaceAll("\\s+", " ").trim();
        if (!noParensAll.isEmpty()) {
            candidates.add(noParensAll);
            // Strip na: 「な」, ［な］, [な]
            String noNa = noParensAll.replaceAll("[「［\\[]な[」］\\]]", "").trim();
            if (!noNa.isEmpty()) candidates.add(noNa);
        }

        // 2. Keep content inside brackets but remove bracket delimiters: [おねがいします] -> おねがいします
        String delimitersRemoved = exp.replaceAll("[\\(\\)（）\\[\\]［］【】《》「」『』]", "").replaceAll("\\s+", " ").trim();
        if (!delimitersRemoved.isEmpty()) {
            candidates.add(delimitersRemoved);
        }

        // 3. Without group indicators 【...】 and 《...》
        String noGroups = exp.replaceAll("【[^】]*】|《[^》]*》", "").trim();
        candidates.add(noGroups);
        String noGroupsNoParens = removeAllBracketsAndParens(noGroups).trim();
        if (!noGroupsNoParens.isEmpty()) {
            candidates.add(noGroupsNoParens);
            String noNa = noGroupsNoParens.replaceAll("[「［\\[]な[」］\\]]", "").trim();
            if (!noNa.isEmpty()) candidates.add(noNa);
        }

        // 4. Extract all inside brackets / parentheses
        List<String> contents = extractAllBracketContents(exp);
        for (String c : contents) {
            if (c.matches("^[ⅠⅡⅢIVX0-9]+$")) continue;
            candidates.add(c);
            String cClean = c.replaceAll("【[^】]*】|《[^》]*》", "").trim();
            if (!cClean.isEmpty()) candidates.add(cClean);
            for (String sub : c.split("[/,、/／]")) {
                sub = sub.trim();
                if (!sub.isEmpty() && !sub.matches("^[ⅠⅡⅢIVX0-9]+$")) {
                    candidates.add(sub);
                }
            }
        }

        // 5. Without Kanji
        String noKanji = stripKanji(exp);
        noKanji = removeAllBracketsAndParens(noKanji).replaceAll("\\s+", " ").trim();
        if (!noKanji.isEmpty()) {
            candidates.add(noKanji);
        }

        // 6. Phonetic (Romaji)
        if (phonetic != null && !phonetic.trim().isEmpty()) {
            String ph = normalizeText(phonetic);
            if (!ph.isEmpty()) {
                candidates.add(ph);
                candidates.add(ph.replace("-", ""));
                candidates.add(stripSymbols(ph));
                String phNoBrackets = removeAllBracketsAndParens(ph).trim();
                if (!phNoBrackets.isEmpty()) {
                    candidates.add(phNoBrackets);
                    candidates.add(stripSymbols(phNoBrackets));
                }
                String phDelimsRemoved = ph.replaceAll("[\\(\\)（）\\[\\]［］【】《》「」『』]", "").trim();
                if (!phDelimsRemoved.isEmpty()) {
                    candidates.add(phDelimsRemoved);
                    candidates.add(stripSymbols(phDelimsRemoved));
                }
            }
        }

        // Expand with Hiragana <-> Katakana equivalents
        for (String c : new HashSet<>(candidates)) {
            String kata = RomajiHelper.hiraganaToKatakana(c);
            if (!kata.equals(c)) candidates.add(kata);
            String hira = RomajiHelper.katakanaToHiragana(c);
            if (!hira.equals(c)) candidates.add(hira);
        }

        // Expand with punctuation/symbol stripped variants
        Set<String> allCandidates = new HashSet<>(candidates);
        for (String c : candidates) {
            allCandidates.add(stripPunctuation(c));
            allCandidates.add(stripSymbols(c));
            allCandidates.add(stripPunctuation(stripSymbols(c)));
        }

        return allCandidates;
    }

    public static boolean isAnswerMatching(String input, String expected, String phonetic) {
        if (input == null || expected == null) return false;
        String cleanInput = normalizeText(input).toLowerCase();
        if (cleanInput.isEmpty()) return false;

        String uStrip = stripPunctuation(stripSymbols(cleanInput));
        String uNoSpace = cleanInput.replace(" ", "");
        String uStripNoSpace = uStrip.replace(" ", "");

        String hiraInput = RomajiHelper.toHiragana(cleanInput, true);
        String hiraStrip = stripPunctuation(stripSymbols(hiraInput));
        String hiraNoSpace = hiraInput.replace(" ", "");
        String hiraStripNoSpace = hiraStrip.replace(" ", "");

        Set<String> candidates = getCandidates(expected, phonetic);
        for (String candidate : candidates) {
            if (candidate == null || candidate.isEmpty()) continue;
            String cl = candidate.toLowerCase();
            if (cleanInput.equalsIgnoreCase(cl) || uStrip.equalsIgnoreCase(cl)) return true;
            if (hiraInput.equalsIgnoreCase(cl) || hiraStrip.equalsIgnoreCase(cl)) return true;

            String clStrip = stripPunctuation(stripSymbols(cl));
            if (cleanInput.equalsIgnoreCase(clStrip) || uStrip.equalsIgnoreCase(clStrip)) return true;
            if (hiraInput.equalsIgnoreCase(clStrip) || hiraStrip.equalsIgnoreCase(clStrip)) return true;

            if (uNoSpace.equalsIgnoreCase(cl.replace(" ", "")) || uStripNoSpace.equalsIgnoreCase(clStrip.replace(" ", ""))) {
                return true;
            }
            if (hiraNoSpace.equalsIgnoreCase(cl.replace(" ", "")) || hiraStripNoSpace.equalsIgnoreCase(clStrip.replace(" ", ""))) {
                return true;
            }
        }

        return false;
    }
}
