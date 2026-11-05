package com.hieunn.learnenglish;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.hieunn.learnenglish.db.GrammarQuizEntity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Quản lý lưu và khôi phục tiến độ luyện ngữ pháp (ExerciseActivity)
 * Giúp người học tiếp tục đúng câu hỏi khi thoát ra vào lại.
 */
public class ExerciseSessionManager {

    private static final String TAG = "ExerciseSession";
    private static final String PREF_NAME = "exercise_sessions";
    private static final String KEY_SESSION_PREFIX = "grammar_session_lesson_";

    public static class SessionData {
        public int sessionCorrect = 0;
        public int sessionWrong = 0;
        public int practiceMode = 0;
        public int masteredCount = 0;
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static boolean hasSavedSession(Context context, int lessonId) {
        if (lessonId <= 0) return false;
        String jsonStr = getPrefs(context).getString(KEY_SESSION_PREFIX + lessonId, null);
        if (jsonStr == null || jsonStr.trim().isEmpty()) return false;
        try {
            JSONObject root = new JSONObject(jsonStr);
            boolean isCompleted = root.optBoolean("isCompleted", false);
            if (isCompleted) return false;
            int total = root.optInt("sessionCorrect", 0) + root.optInt("sessionWrong", 0);
            return total > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static void saveSession(Context context,
                                   int lessonId,
                                   List<GrammarQuizEntity> allQuestions,
                                   Map<Integer, Integer> questionState,
                                   Map<Integer, Integer> correctStreak,
                                   Map<Integer, Boolean> answeredByMC,
                                   Map<Integer, Boolean> answeredByWrite,
                                   List<GrammarQuizEntity> queue,
                                   int sessionCorrect,
                                   int sessionWrong,
                                   int practiceMode,
                                   boolean isCompleted) {
        if (lessonId <= 0 || allQuestions == null || allQuestions.isEmpty()) return;

        try {
            JSONObject root = new JSONObject();
            root.put("lessonId", lessonId);
            root.put("sessionCorrect", sessionCorrect);
            root.put("sessionWrong", sessionWrong);
            root.put("practiceMode", practiceMode);
            root.put("isCompleted", isCompleted);

            int mastered = 0;
            JSONArray qArray = new JSONArray();
            for (GrammarQuizEntity q : allQuestions) {
                if (q == null) continue;
                JSONObject item = new JSONObject();
                item.put("id", q.id);

                Integer st = questionState.get(q.id);
                int stateVal = (st != null) ? st : 0;
                item.put("state", stateVal);
                if (stateVal == 2) mastered++;

                Integer streak = correctStreak.get(q.id);
                item.put("streak", (streak != null) ? streak : 0);

                Boolean mc = answeredByMC.get(q.id);
                item.put("mc", (mc != null && mc));

                Boolean wr = answeredByWrite.get(q.id);
                item.put("wr", (wr != null && wr));

                qArray.put(item);
            }
            root.put("questions", qArray);
            root.put("masteredCount", mastered);

            JSONArray queueArray = new JSONArray();
            if (queue != null) {
                for (GrammarQuizEntity q : queue) {
                    if (q != null) queueArray.put(q.id);
                }
            }
            root.put("queue", queueArray);

            getPrefs(context).edit().putString(KEY_SESSION_PREFIX + lessonId, root.toString()).apply();
            Log.d(TAG, "Saved exercise session for lesson " + lessonId + ": mastered=" + mastered);
        } catch (Exception e) {
            Log.e(TAG, "Error saving exercise session for lesson " + lessonId, e);
        }
    }

    public static SessionData restoreSession(Context context,
                                             int lessonId,
                                             List<GrammarQuizEntity> allQuestions,
                                             Map<Integer, Integer> questionState,
                                             Map<Integer, Integer> correctStreak,
                                             Map<Integer, Boolean> answeredByMC,
                                             Map<Integer, Boolean> answeredByWrite,
                                             List<GrammarQuizEntity> queue) {
        if (lessonId <= 0 || allQuestions == null || allQuestions.isEmpty()) return null;

        String jsonStr = getPrefs(context).getString(KEY_SESSION_PREFIX + lessonId, null);
        if (jsonStr == null || jsonStr.trim().isEmpty()) return null;

        try {
            JSONObject root = new JSONObject(jsonStr);
            SessionData data = new SessionData();
            data.sessionCorrect = root.optInt("sessionCorrect", 0);
            data.sessionWrong = root.optInt("sessionWrong", 0);
            data.practiceMode = root.optInt("practiceMode", 0);
            data.masteredCount = root.optInt("masteredCount", 0);

            Map<Integer, GrammarQuizEntity> lookup = new java.util.HashMap<>();
            for (GrammarQuizEntity q : allQuestions) {
                if (q != null) lookup.put(q.id, q);
            }

            JSONArray qArray = root.optJSONArray("questions");
            if (qArray != null) {
                for (int i = 0; i < qArray.length(); i++) {
                    JSONObject item = qArray.getJSONObject(i);
                    int id = item.optInt("id");
                    if (lookup.containsKey(id)) {
                        questionState.put(id, item.optInt("state", 0));
                        correctStreak.put(id, item.optInt("streak", 0));
                        answeredByMC.put(id, item.optBoolean("mc", false));
                        answeredByWrite.put(id, item.optBoolean("wr", false));
                    }
                }
            }

            for (GrammarQuizEntity q : allQuestions) {
                if (!questionState.containsKey(q.id)) {
                    questionState.put(q.id, 0);
                    correctStreak.put(q.id, 0);
                    answeredByMC.put(q.id, false);
                    answeredByWrite.put(q.id, false);
                }
            }

            queue.clear();
            JSONArray queueArray = root.optJSONArray("queue");
            Set<Integer> addedToQueue = new HashSet<>();
            if (queueArray != null && queueArray.length() > 0) {
                for (int i = 0; i < queueArray.length(); i++) {
                    int id = queueArray.optInt(i);
                    GrammarQuizEntity q = lookup.get(id);
                    if (q != null && !addedToQueue.contains(id)) {
                        Integer state = questionState.get(id);
                        if (state == null || state != 2) {
                            queue.add(q);
                            addedToQueue.add(id);
                        }
                    }
                }
            }

            for (GrammarQuizEntity q : allQuestions) {
                Integer state = questionState.get(q.id);
                if ((state == null || state != 2) && !addedToQueue.contains(q.id)) {
                    queue.add(q);
                    addedToQueue.add(q.id);
                }
            }

            Log.d(TAG, "Restored exercise session for lesson " + lessonId + ": queue size=" + queue.size());
            return data;
        } catch (Exception e) {
            Log.e(TAG, "Error restoring exercise session for lesson " + lessonId, e);
            return null;
        }
    }

    public static void clearSession(Context context, int lessonId) {
        if (lessonId <= 0) return;
        getPrefs(context).edit().remove(KEY_SESSION_PREFIX + lessonId).apply();
    }
}
