package com.hieunn.learnenglish;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Quản lý nạp và truy xuất bài luyện nghe Minna no Nihongo Choukai Tasuku 25 (Bài 1 -> 50).
 */
public class ChoukaiDataManager {
    private static final String TAG = "ChoukaiDataManager";
    private static Map<Integer, ChoukaiLesson> cachedLessons = null;
    private static List<ChoukaiLesson> lessonList = null;

    private static synchronized void loadIfNeeded(Context context) {
        if (cachedLessons != null && !cachedLessons.isEmpty()) {
            return;
        }

        cachedLessons = new HashMap<>();
        lessonList = new ArrayList<>();
        if (context == null) return;

        try (InputStream is = context.getAssets().open("japanese_choukai_lessons.json")) {
            byte[] buf = new byte[is.available()];
            int bytesRead = is.read(buf);
            String jsonStr = new String(buf, 0, bytesRead, StandardCharsets.UTF_8);
            JSONArray arr = new JSONArray(jsonStr);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject lObj = arr.getJSONObject(i);
                int lessonNumber = lObj.optInt("lessonNumber", i + 1);
                String level = lObj.optString("level", lessonNumber <= 25 ? "N5" : "N4");
                String title = lObj.optString("title", "Bài " + lessonNumber);
                String subtitle = lObj.optString("subtitle", "");

                List<ChoukaiTask> taskList = new ArrayList<>();
                JSONArray tasksArr = lObj.optJSONArray("tasks");
                if (tasksArr != null) {
                    for (int t = 0; t < tasksArr.length(); t++) {
                        JSONObject tObj = tasksArr.getJSONObject(t);
                        int taskNumber = tObj.optInt("taskNumber", t + 1);
                        String tTitle = tObj.optString("title", "");
                        String tTitleVi = tObj.optString("titleVi", "");
                        String cdName = tObj.optString("cdName", "");
                        String cdFolder = tObj.optString("cdFolder", "");
                        String audioFile = tObj.optString("audioFile", "");
                        String trackLabel = tObj.optString("trackLabel", "");
                        String script = tObj.optString("script", "");
                        String scriptVi = tObj.optString("scriptVi", "");

                        List<ChoukaiQuestion> qList = new ArrayList<>();
                        JSONArray qArr = tObj.optJSONArray("questions");
                        if (qArr != null) {
                            for (int q = 0; q < qArr.length(); q++) {
                                JSONObject qObj = qArr.getJSONObject(q);
                                int qNum = qObj.optInt("questionNumber", q + 1);
                                String qText = qObj.optString("questionText", "");
                                String qVi = qObj.optString("questionVi", "");
                                int correctIndex = qObj.optInt("correctIndex", 0);
                                String expl = qObj.optString("explanation", "");

                                List<String> opts = new ArrayList<>();
                                JSONArray optArr = qObj.optJSONArray("options");
                                if (optArr != null) {
                                    for (int o = 0; o < optArr.length(); o++) {
                                        opts.add(optArr.getString(o));
                                    }
                                }
                                qList.add(new ChoukaiQuestion(qNum, qText, qVi, opts, correctIndex, expl));
                            }
                        }

                        taskList.add(new ChoukaiTask(
                                taskNumber, tTitle, tTitleVi, cdName, cdFolder,
                                audioFile, trackLabel, script, scriptVi, qList
                        ));
                    }
                }

                ChoukaiLesson lesson = new ChoukaiLesson(lessonNumber, level, title, subtitle, taskList);
                cachedLessons.put(lessonNumber, lesson);
                lessonList.add(lesson);
            }
            Log.d(TAG, "Loaded " + cachedLessons.size() + " Choukai lessons.");
        } catch (Exception e) {
            Log.e(TAG, "Failed to load Choukai lessons", e);
        }
    }

    public static synchronized ChoukaiLesson getLesson(Context context, int lessonNumber) {
        loadIfNeeded(context);
        if (cachedLessons != null && cachedLessons.containsKey(lessonNumber)) {
            return cachedLessons.get(lessonNumber);
        }
        return null;
    }

    public static synchronized List<ChoukaiLesson> getAllLessons(Context context) {
        loadIfNeeded(context);
        if (lessonList != null) {
            return new ArrayList<>(lessonList);
        }
        return new ArrayList<>();
    }
}
