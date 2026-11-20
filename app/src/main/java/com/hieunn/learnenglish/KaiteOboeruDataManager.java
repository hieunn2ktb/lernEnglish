package com.hieunn.learnenglish;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Quản lý nạp và truy xuất bài tập ngữ pháp từ sách
 * Minna no Nihongo Kaite Oboeru Bunpou Renshuuchou I & II (Bài 1 -> 50).
 */
public class KaiteOboeruDataManager {

    private static Map<Integer, List<GrammarQuiz>> cachedQuizzes = null;
    private static Map<Integer, String> cachedLessonTitles = null;

    private static synchronized void loadIfNeeded(Context context) {
        if (cachedQuizzes != null && !cachedQuizzes.isEmpty()) {
            return;
        }

        cachedQuizzes = new HashMap<>();
        cachedLessonTitles = new HashMap<>();
        if (context == null) return;

        try {
            // First try unified json file
            String[] candidateFiles = new String[] {
                    "kaite_oboeru_exercises.json",
                    "kaite_oboeru_batch1.json",
                    "kaite_oboeru_batch2.json",
                    "kaite_oboeru_batch3.json",
                    "kaite_oboeru_batch4.json",
                    "kaite_oboeru_batch5.json"
            };

            for (String fname : candidateFiles) {
                try (InputStream is = context.getAssets().open(fname)) {
                    byte[] buf = new byte[is.available()];
                    is.read(buf);
                    String jsonStr = new String(buf, StandardCharsets.UTF_8);
                    JSONArray arr = new JSONArray(jsonStr);

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject lObj = arr.getJSONObject(i);
                        int lNum = lObj.optInt("lessonNumber", i + 1);
                        String title = lObj.optString("lessonTitle", lObj.optString("title", "Bài " + lNum));
                        cachedLessonTitles.put(lNum, title);

                        List<GrammarQuiz> quizList = cachedQuizzes.get(lNum);
                        if (quizList == null) {
                            quizList = new ArrayList<>();
                            cachedQuizzes.put(lNum, quizList);
                        }

                        JSONArray qArr = lObj.optJSONArray("quizzes");
                        if (qArr == null) {
                            qArr = lObj.optJSONArray("questions");
                        }
                        if (qArr != null) {
                            for (int q = 0; q < qArr.length(); q++) {
                                JSONObject qObj = qArr.getJSONObject(q);
                                String question = qObj.optString("question", "");
                                String meaning = qObj.optString("meaning", "");
                                int correctIndex = qObj.optInt("correctIndex", 0);
                                String explanation = qObj.optString("explanation", "");

                                List<String> options = new ArrayList<>();
                                JSONArray optArr = qObj.optJSONArray("options");
                                if (optArr != null) {
                                    for (int o = 0; o < optArr.length(); o++) {
                                        options.add(optArr.getString(o));
                                    }
                                }

                                quizList.add(new GrammarQuiz(question, meaning, options, correctIndex, explanation));
                            }
                        }
                    }
                } catch (Exception ignored) {
                    // File may not exist yet or individual batch loaded
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized List<GrammarQuiz> getQuizzesForLesson(Context context, int lessonNumber) {
        loadIfNeeded(context);
        if (cachedQuizzes != null && cachedQuizzes.containsKey(lessonNumber)) {
            return new ArrayList<>(cachedQuizzes.get(lessonNumber));
        }
        return new ArrayList<>();
    }

    public static synchronized boolean hasExercisesForLesson(Context context, int lessonNumber) {
        loadIfNeeded(context);
        return cachedQuizzes != null && cachedQuizzes.containsKey(lessonNumber) && !cachedQuizzes.get(lessonNumber).isEmpty();
    }

    public static synchronized String getLessonTitle(Context context, int lessonNumber) {
        loadIfNeeded(context);
        if (cachedLessonTitles != null && cachedLessonTitles.containsKey(lessonNumber)) {
            return cachedLessonTitles.get(lessonNumber);
        }
        return "Bài " + lessonNumber;
    }

    public static synchronized int getTotalQuizCount(Context context) {
        loadIfNeeded(context);
        int total = 0;
        if (cachedQuizzes != null) {
            for (List<GrammarQuiz> list : cachedQuizzes.values()) {
                total += list.size();
            }
        }
        return total;
    }
}
