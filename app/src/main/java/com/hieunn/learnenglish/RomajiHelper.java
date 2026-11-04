package com.hieunn.learnenglish;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class RomajiHelper {

    private static final Map<String, String> ROMAJI_TABLE = new HashMap<>();

    static {
        // Yoon (3-char combinations)
        ROMAJI_TABLE.put("kya", "きゃ");
        ROMAJI_TABLE.put("kyu", "きゅ");
        ROMAJI_TABLE.put("kyo", "きょ");
        ROMAJI_TABLE.put("sha", "しゃ");
        ROMAJI_TABLE.put("shu", "しゅ");
        ROMAJI_TABLE.put("sho", "しょ");
        ROMAJI_TABLE.put("she", "しぇ");
        ROMAJI_TABLE.put("sya", "しゃ");
        ROMAJI_TABLE.put("syu", "しゅ");
        ROMAJI_TABLE.put("syo", "しょ");
        ROMAJI_TABLE.put("cha", "ちゃ");
        ROMAJI_TABLE.put("chu", "ちゅ");
        ROMAJI_TABLE.put("cho", "ちょ");
        ROMAJI_TABLE.put("che", "ちぇ");
        ROMAJI_TABLE.put("tya", "ちゃ");
        ROMAJI_TABLE.put("tyu", "ちゅ");
        ROMAJI_TABLE.put("tyo", "ちょ");
        ROMAJI_TABLE.put("nya", "にゃ");
        ROMAJI_TABLE.put("nyu", "にゅ");
        ROMAJI_TABLE.put("nyo", "にょ");
        ROMAJI_TABLE.put("hya", "ひゃ");
        ROMAJI_TABLE.put("hyu", "ひゅ");
        ROMAJI_TABLE.put("hyo", "ひょ");
        ROMAJI_TABLE.put("mya", "みゃ");
        ROMAJI_TABLE.put("myu", "みゅ");
        ROMAJI_TABLE.put("myo", "みょ");
        ROMAJI_TABLE.put("rya", "りゃ");
        ROMAJI_TABLE.put("ryu", "りゅ");
        ROMAJI_TABLE.put("ryo", "りょ");
        ROMAJI_TABLE.put("gya", "ぎゃ");
        ROMAJI_TABLE.put("gyu", "ぎゅ");
        ROMAJI_TABLE.put("gyo", "ぎょ");
        ROMAJI_TABLE.put("ja", "じゃ");
        ROMAJI_TABLE.put("ju", "じゅ");
        ROMAJI_TABLE.put("jo", "じょ");
        ROMAJI_TABLE.put("je", "じぇ");
        ROMAJI_TABLE.put("jya", "じゃ");
        ROMAJI_TABLE.put("jyu", "じゅ");
        ROMAJI_TABLE.put("jyo", "じょ");
        ROMAJI_TABLE.put("zya", "じゃ");
        ROMAJI_TABLE.put("zyu", "じゅ");
        ROMAJI_TABLE.put("zyo", "じょ");
        ROMAJI_TABLE.put("bya", "びゃ");
        ROMAJI_TABLE.put("byu", "びゅ");
        ROMAJI_TABLE.put("byo", "びょ");
        ROMAJI_TABLE.put("pya", "ぴゃ");
        ROMAJI_TABLE.put("pyu", "ぴゅ");
        ROMAJI_TABLE.put("pyo", "ぴょ");

        // Small kana / special
        ROMAJI_TABLE.put("xtsu", "っ");
        ROMAJI_TABLE.put("ltsu", "っ");
        ROMAJI_TABLE.put("tsu", "つ");
        ROMAJI_TABLE.put("chi", "ち");
        ROMAJI_TABLE.put("shi", "し");
        ROMAJI_TABLE.put("fu", "ふ");

        // Basic 2-char
        ROMAJI_TABLE.put("ka", "か"); ROMAJI_TABLE.put("ki", "き"); ROMAJI_TABLE.put("ku", "く"); ROMAJI_TABLE.put("ke", "け"); ROMAJI_TABLE.put("ko", "こ");
        ROMAJI_TABLE.put("sa", "さ"); ROMAJI_TABLE.put("si", "し"); ROMAJI_TABLE.put("su", "す"); ROMAJI_TABLE.put("se", "せ"); ROMAJI_TABLE.put("so", "そ");
        ROMAJI_TABLE.put("ta", "た"); ROMAJI_TABLE.put("ti", "ち"); ROMAJI_TABLE.put("tu", "つ"); ROMAJI_TABLE.put("te", "て"); ROMAJI_TABLE.put("to", "と");
        ROMAJI_TABLE.put("na", "な"); ROMAJI_TABLE.put("ni", "に"); ROMAJI_TABLE.put("nu", "ぬ"); ROMAJI_TABLE.put("ne", "ね"); ROMAJI_TABLE.put("no", "の");
        ROMAJI_TABLE.put("ha", "は"); ROMAJI_TABLE.put("hi", "ひ"); ROMAJI_TABLE.put("hu", "ふ"); ROMAJI_TABLE.put("he", "へ"); ROMAJI_TABLE.put("ho", "ほ");
        ROMAJI_TABLE.put("ma", "ま"); ROMAJI_TABLE.put("mi", "み"); ROMAJI_TABLE.put("mu", "む"); ROMAJI_TABLE.put("me", "め"); ROMAJI_TABLE.put("mo", "も");
        ROMAJI_TABLE.put("ya", "や"); ROMAJI_TABLE.put("yu", "ゆ"); ROMAJI_TABLE.put("yo", "よ");
        ROMAJI_TABLE.put("ra", "ら"); ROMAJI_TABLE.put("ri", "り"); ROMAJI_TABLE.put("ru", "る"); ROMAJI_TABLE.put("re", "れ"); ROMAJI_TABLE.put("ro", "ろ");
        ROMAJI_TABLE.put("wa", "わ"); ROMAJI_TABLE.put("wo", "を");

        // Dakuten
        ROMAJI_TABLE.put("ga", "が"); ROMAJI_TABLE.put("gi", "ぎ"); ROMAJI_TABLE.put("gu", "ぐ"); ROMAJI_TABLE.put("ge", "げ"); ROMAJI_TABLE.put("go", "ご");
        ROMAJI_TABLE.put("za", "ざ"); ROMAJI_TABLE.put("zi", "じ"); ROMAJI_TABLE.put("zu", "ず"); ROMAJI_TABLE.put("ze", "ぜ"); ROMAJI_TABLE.put("zo", "ぞ");
        ROMAJI_TABLE.put("da", "だ"); ROMAJI_TABLE.put("di", "ぢ"); ROMAJI_TABLE.put("du", "づ"); ROMAJI_TABLE.put("de", "で"); ROMAJI_TABLE.put("do", "ど");
        ROMAJI_TABLE.put("ba", "ば"); ROMAJI_TABLE.put("bi", "び"); ROMAJI_TABLE.put("bu", "ぶ"); ROMAJI_TABLE.put("be", "べ"); ROMAJI_TABLE.put("bo", "ぼ");

        // Handakuten
        ROMAJI_TABLE.put("pa", "ぱ"); ROMAJI_TABLE.put("pi", "ぴ"); ROMAJI_TABLE.put("pu", "ぷ"); ROMAJI_TABLE.put("pe", "ぺ"); ROMAJI_TABLE.put("po", "ぽ");

        // Foreign sounds
        ROMAJI_TABLE.put("fa", "ふぁ"); ROMAJI_TABLE.put("fi", "ふぃ"); ROMAJI_TABLE.put("fe", "ふぇ"); ROMAJI_TABLE.put("fo", "ふぉ");
        ROMAJI_TABLE.put("va", "ゔぁ"); ROMAJI_TABLE.put("vi", "ゔぃ"); ROMAJI_TABLE.put("vu", "ゔ"); ROMAJI_TABLE.put("ve", "ゔぇ"); ROMAJI_TABLE.put("vo", "ゔぉ");

        // Single vowels
        ROMAJI_TABLE.put("a", "あ"); ROMAJI_TABLE.put("i", "い"); ROMAJI_TABLE.put("u", "う"); ROMAJI_TABLE.put("e", "え"); ROMAJI_TABLE.put("o", "お");

        // Double n and long vowel
        ROMAJI_TABLE.put("nn", "ん");
        ROMAJI_TABLE.put("n'", "ん");
        ROMAJI_TABLE.put("-", "ー");

        // Small vowels
        ROMAJI_TABLE.put("xa", "ぁ"); ROMAJI_TABLE.put("xi", "ぃ"); ROMAJI_TABLE.put("xu", "ぅ"); ROMAJI_TABLE.put("xe", "ぇ"); ROMAJI_TABLE.put("xo", "ぉ");
        ROMAJI_TABLE.put("la", "ぁ"); ROMAJI_TABLE.put("li", "ぃ"); ROMAJI_TABLE.put("lu", "ぅ"); ROMAJI_TABLE.put("le", "ぇ"); ROMAJI_TABLE.put("lo", "ぉ");
        ROMAJI_TABLE.put("xya", "ゃ"); ROMAJI_TABLE.put("xyu", "ゅ"); ROMAJI_TABLE.put("xyo", "ょ");
        ROMAJI_TABLE.put("lya", "ゃ"); ROMAJI_TABLE.put("lyu", "ゅ"); ROMAJI_TABLE.put("lyo", "ょ");
    }

    public static String toHiragana(String romaji, boolean isFinal) {
        if (romaji == null || romaji.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        int n = romaji.length();
        int i = 0;

        while (i < n) {
            char c = romaji.charAt(i);

            // If non-ASCII (already Kana, Kanji, fullwidth, etc.) or space, keep as-is
            if (c > 127) {
                sb.append(c);
                i++;
                continue;
            }

            char cLower = Character.toLowerCase(c);
            if (!((cLower >= 'a' && cLower <= 'z') || c == '-' || c == '\'')) {
                sb.append(c);
                i++;
                continue;
            }

            // Check tch -> っch
            if (i + 2 < n && romaji.substring(i, i + 3).equalsIgnoreCase("tch")) {
                sb.append("っ");
                i++;
                continue;
            }

            // Check double consonants: kk, ss, tt, pp, dd, gg, etc. (excluding n)
            if (i + 1 < n) {
                char nextC = Character.toLowerCase(romaji.charAt(i + 1));
                if (cLower == nextC && "bcdfghjklmpqrstvwxyz".indexOf(cLower) >= 0 && cLower != 'n') {
                    sb.append("っ");
                    i++;
                    continue;
                }
            }

            // Check 'n'
            if (cLower == 'n') {
                if (i + 1 < n) {
                    char nextC = Character.toLowerCase(romaji.charAt(i + 1));
                    if ("aiueoy".indexOf(nextC) < 0) {
                        sb.append("ん");
                        if (nextC == 'n' || nextC == '\'') {
                            i += 2;
                        } else {
                            i++;
                        }
                        continue;
                    }
                } else if (isFinal) {
                    sb.append("ん");
                    i++;
                    continue;
                }
            }

            // Match longest prefix from table (up to 4 chars)
            boolean matched = false;
            for (int len = 4; len >= 1; len--) {
                if (i + len <= n) {
                    String sub = romaji.substring(i, i + len).toLowerCase();
                    String mapped = ROMAJI_TABLE.get(sub);
                    if (mapped != null) {
                        sb.append(mapped);
                        i += len;
                        matched = true;
                        break;
                    }
                }
            }

            if (!matched) {
                sb.append(c);
                i++;
            }
        }

        return sb.toString();
    }

    public static String hiraganaToKatakana(String hiragana) {
        if (hiragana == null) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hiragana.length(); i++) {
            char c = hiragana.charAt(i);
            if (c >= 0x3041 && c <= 0x3096) {
                sb.append((char) (c + 0x60));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String katakanaToHiragana(String katakana) {
        if (katakana == null) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < katakana.length(); i++) {
            char c = katakana.charAt(i);
            if (c >= 0x30A1 && c <= 0x30F6) {
                sb.append((char) (c - 0x60));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static boolean isKatakanaWord(String text) {
        if (text == null) return false;
        boolean hasKata = false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0x3040 && c <= 0x309F) return false;
            if (c >= 0x30A0 && c <= 0x30FF) hasKata = true;
        }
        return hasKata;
    }

    public static String convertRomaji(String romaji, boolean isFinal, boolean isKatakanaMode) {
        String hira = toHiragana(romaji, isFinal);
        if (isKatakanaMode) {
            return hiraganaToKatakana(hira);
        }
        return hira;
    }

    public interface ActiveCheck {
        boolean isJapaneseActive();
        boolean isKatakanaExpected();
    }

    public static void attachRomajiConverter(EditText editText, ActiveCheck activeCheck) {
        if (editText == null || activeCheck == null) return;

        editText.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (isUpdating) return;
                if (!activeCheck.isJapaneseActive()) return;

                String original = s.toString();
                boolean katakanaMode = activeCheck.isKatakanaExpected();
                String converted = convertRomaji(original, false, katakanaMode);

                if (!converted.equals(original)) {
                    isUpdating = true;
                    int selStart = editText.getSelectionStart();
                    int lengthDiff = converted.length() - original.length();

                    editText.setText(converted);
                    int newCursor = Math.max(0, Math.min(converted.length(), selStart + lengthDiff));
                    editText.setSelection(newCursor);
                    isUpdating = false;
                }
            }
        });
    }

    public static String getFinalizedAnswer(EditText editText, boolean isKatakanaMode) {
        if (editText == null) return "";
        String text = editText.getText().toString();
        String finalized = convertRomaji(text, true, isKatakanaMode).trim();
        if (!finalized.equals(text)) {
            editText.setText(finalized);
            editText.setSelection(finalized.length());
        }
        return finalized;
    }
}
