package com.hieunn.learnenglish;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class KanjiDataManager {

    private static List<KanjiLesson> cachedLessons = null;

    public static synchronized List<KanjiLesson> getAllLessons(Context context) {
        if (cachedLessons != null && !cachedLessons.isEmpty()) {
            return cachedLessons;
        }

        cachedLessons = new ArrayList<>();
        if (context == null) return cachedLessons;

        try {
            InputStream is = context.getAssets().open("kanji_lessons.json");
            byte[] buf = new byte[is.available()];
            is.read(buf);
            is.close();

            String jsonStr = new String(buf, StandardCharsets.UTF_8);
            JSONArray array = new JSONArray(jsonStr);

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                int id = obj.optInt("id", i + 1);
                int lessonNumber = obj.optInt("lessonNumber", id);
                String title = obj.optString("title", "Bài " + lessonNumber);
                String desc = obj.optString("description", "");
                boolean isTest = obj.optBoolean("isTest", false);
                int totalKanji = obj.optInt("totalKanji", 0);

                List<KanjiItem> kanjis = new ArrayList<>();
                JSONArray kanjiArr = obj.optJSONArray("kanjis");
                if (kanjiArr != null) {
                    for (int j = 0; j < kanjiArr.length(); j++) {
                        JSONObject kObj = kanjiArr.getJSONObject(j);
                        String kanji = kObj.optString("kanji", "");
                        String hanViet = kObj.optString("hanViet", "");
                        String kun = kObj.optString("kunyomi", "");
                        String on = kObj.optString("onyomi", "");
                        List<String> examples = new ArrayList<>();
                        JSONArray exArr = kObj.optJSONArray("examples");
                        if (exArr != null) {
                            for (int k = 0; k < exArr.length(); k++) {
                                examples.add(exArr.getString(k));
                            }
                        }
                        kanjis.add(new KanjiItem(kanji, hanViet, kun, on, examples));
                    }
                }

                List<String> readingPractice = new ArrayList<>();
                JSONArray rArr = obj.optJSONArray("readingPractice");
                if (rArr != null) {
                    for (int j = 0; j < rArr.length(); j++) {
                        readingPractice.add(rArr.getString(j));
                    }
                }

                List<String> writingPractice = new ArrayList<>();
                JSONArray wArr = obj.optJSONArray("writingPractice");
                if (wArr != null) {
                    for (int j = 0; j < wArr.length(); j++) {
                        writingPractice.add(wArr.getString(j));
                    }
                }

                List<KanjiTestQuestion> questions = new ArrayList<>();
                JSONArray qArr = obj.optJSONArray("questions");
                if (qArr != null) {
                    for (int j = 0; j < qArr.length(); j++) {
                        JSONObject qObj = qArr.getJSONObject(j);
                        int num = qObj.optInt("number", j + 1);
                        String q = qObj.optString("question", "");
                        int correctIdx = qObj.optInt("correctAnswerIndex", 1);
                        List<String> options = new ArrayList<>();
                        JSONArray oArr = qObj.optJSONArray("options");
                        if (oArr != null) {
                            for (int k = 0; k < oArr.length(); k++) {
                                options.add(oArr.getString(k));
                            }
                        }
                        questions.add(new KanjiTestQuestion(num, q, options, correctIdx));
                    }
                }

                cachedLessons.add(new KanjiLesson(id, lessonNumber, title, desc, isTest,
                        totalKanji, kanjis, readingPractice, writingPractice, questions));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cachedLessons;
    }

    public static KanjiLesson getLessonById(Context context, int id) {
        List<KanjiLesson> lessons = getAllLessons(context);
        for (KanjiLesson l : lessons) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }
}
