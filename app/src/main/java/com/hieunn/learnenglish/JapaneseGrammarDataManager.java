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

        // Try loading structured grammar data first
        try {
            InputStream is = context.getAssets().open("japanese_grammar_structured.json");
            byte[] buf = new byte[is.available()];
            is.read(buf);
            is.close();

            String jsonStr = new String(buf, StandardCharsets.UTF_8);
            JSONArray array = new JSONArray(jsonStr);

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                int lessonNumber = obj.optInt("lessonNumber", i + 1);
                String level = obj.optString("level", lessonNumber <= 25 ? "N5" : "N4");
                String title = obj.optString("title", "Bài " + lessonNumber);
                String summary = obj.optString("summary", "");
                String content = obj.optString("content", "");

                List<JapaneseGrammarPoint> pointList = new ArrayList<>();
                JSONArray pointsArray = obj.optJSONArray("points");
                if (pointsArray != null) {
                    for (int p = 0; p < pointsArray.length(); p++) {
                        JSONObject pObj = pointsArray.getJSONObject(p);
                        String id = pObj.optString("id", "l" + lessonNumber + "_p" + (p + 1));
                        int pointNumber = pObj.optInt("pointNumber", p + 1);
                        int totalPoints = pObj.optInt("totalPoints", pointsArray.length());
                        String pTitle = pObj.optString("title", "");
                        String meaning = pObj.optString("meaning", "");
                        String pLevel = pObj.optString("level", level);
                        String levelLabel = pObj.optString("levelLabel", lessonNumber <= 25 ? "Cơ bản" : "Trung cấp");
                        String structure = pObj.optString("structure", "");
                        String explanation = pObj.optString("explanation", "");
                        String notes = pObj.optString("notes", "");

                        List<GrammarExample> examples = new ArrayList<>();
                        JSONArray exArray = pObj.optJSONArray("examples");
                        if (exArray != null) {
                            for (int e = 0; e < exArray.length(); e++) {
                                JSONObject eObj = exArray.getJSONObject(e);
                                examples.add(new GrammarExample(
                                        eObj.optString("label", "Ví dụ " + (e + 1)),
                                        eObj.optString("japanese", ""),
                                        eObj.optString("romaji", ""),
                                        eObj.optString("vietnamese", "")
                                ));
                            }
                        }

                        List<GrammarQuiz> quizzes = new ArrayList<>();
                        JSONArray qArray = pObj.optJSONArray("quizzes");
                        if (qArray != null) {
                            for (int q = 0; q < qArray.length(); q++) {
                                JSONObject qObj = qArray.getJSONObject(q);
                                List<String> options = new ArrayList<>();
                                JSONArray optArray = qObj.optJSONArray("options");
                                if (optArray != null) {
                                    for (int o = 0; o < optArray.length(); o++) {
                                        options.add(optArray.getString(o));
                                    }
                                }
                                quizzes.add(new GrammarQuiz(
                                        qObj.optString("question", ""),
                                        qObj.optString("meaning", ""),
                                        options,
                                        qObj.optInt("correctIndex", 0),
                                        qObj.optString("explanation", "")
                                ));
                            }
                        }

                        pointList.add(new JapaneseGrammarPoint(
                                id, pointNumber, totalPoints, pTitle, meaning, pLevel,
                                levelLabel, structure, explanation, examples, notes, quizzes
                        ));
                    }
                }

                cachedLessons.add(new JapaneseGrammarLesson(lessonNumber, level, title, summary, content, pointList));
            }
            return cachedLessons;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Fallback to legacy file if structured json fails
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

    public static JapaneseGrammarPoint getGrammarPoint(Context context, int lessonNumber, int pointNumber) {
        JapaneseGrammarLesson lesson = getLessonByNumber(context, lessonNumber);
        if (lesson != null) {
            return lesson.getPointByNumber(pointNumber);
        }
        return null;
    }
}
