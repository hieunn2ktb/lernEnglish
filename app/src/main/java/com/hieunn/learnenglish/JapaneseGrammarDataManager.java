package com.hieunn.learnenglish;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JapaneseGrammarDataManager {

    private static List<JapaneseGrammarLesson> cachedLessons = null;

    public static synchronized List<JapaneseGrammarLesson> getAllLessons(Context context) {
        if (cachedLessons != null && !cachedLessons.isEmpty()) {
            return cachedLessons;
        }

        cachedLessons = new ArrayList<>();
        if (context == null) return cachedLessons;

        try {
            InputStream is = context.getAssets().open("japanese_grammar_lessons.json");
            byte[] buf = new byte[is.available()];
            is.read(buf);
            is.close();

            String jsonStr = new String(buf, StandardCharsets.UTF_8);
            JSONArray array = new JSONArray(jsonStr);

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                int lessonNumber = obj.optInt("lessonNumber", i + 1);
                String title = obj.optString("title", "Bài " + lessonNumber);
                String summary = obj.optString("summary", "");
                String content = obj.optString("content", "");

                cachedLessons.add(new JapaneseGrammarLesson(lessonNumber, title, summary, content));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cachedLessons;
    }

    public static JapaneseGrammarLesson getLessonByNumber(Context context, int lessonNumber) {
        List<JapaneseGrammarLesson> lessons = getAllLessons(context);
        for (JapaneseGrammarLesson l : lessons) {
            if (l.getLessonNumber() == lessonNumber) {
                return l;
            }
        }
        return null;
    }
}
