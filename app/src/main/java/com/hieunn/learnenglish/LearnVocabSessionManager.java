package com.hieunn.learnenglish;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Quản lý lưu và khôi phục tiến độ học từ vựng (LearnVocabActivity)
 * Giúp người học tiếp tục đúng vị trí và trạng thái khi thoát ra vào lại.
 */
public class LearnVocabSessionManager {

    private static final String TAG = "LearnVocabSession";
    private static final String PREF_NAME = "learn_vocab_sessions";
    private static final String KEY_SESSION_PREFIX = "session_lesson_";

    public static class SessionData {
        public int sessionCorrect = 0;
        public int sessionWrong = 0;
        public boolean starOnlyMode = false;
        public int masteredCount = 0;
        public int learningCount = 0;
        public int newCount = 0;
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Kiểm tra xem bài học có phiên học dở dang nào không
     */
    public static boolean hasSavedSession(Context context, int lessonId) {
        if (lessonId <= 0) return false;
        String jsonStr = getPrefs(context).getString(KEY_SESSION_PREFIX + lessonId, null);
        if (jsonStr == null || jsonStr.trim().isEmpty()) return false;
        try {
            JSONObject root = new JSONObject(jsonStr);
            boolean isCompleted = root.optBoolean("isCompleted", false);
            if (isCompleted) return false;
            int totalAnswered = root.optInt("sessionCorrect", 0) + root.optInt("sessionWrong", 0);
            int masteredCount = root.optInt("masteredCount", 0);
            int learningCount = root.optInt("learningCount", 0);
            return (totalAnswered > 0 || masteredCount > 0 || learningCount > 0);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Lưu trạng thái chi tiết của phiên học
     */
    public static void saveSession(Context context,
                                   int lessonId,
                                   List<VocabItem> allVocab,
                                   Map<VocabItem, Integer> wordState,
                                   Map<VocabItem, Integer> correctStreak,
                                   Map<VocabItem, Boolean> answeredByMC,
                                   Map<VocabItem, Boolean> answeredByWrite,
                                   Set<VocabItem> starredWords,
                                   List<VocabItem> queue,
                                   int sessionCorrect,
                                   int sessionWrong,
                                   boolean starOnlyMode,
                                   boolean isCompleted) {
        if (lessonId <= 0 || allVocab == null || allVocab.isEmpty()) return;

        try {
            JSONObject root = new JSONObject();
            root.put("lessonId", lessonId);
            root.put("sessionCorrect", sessionCorrect);
            root.put("sessionWrong", sessionWrong);
            root.put("starOnlyMode", starOnlyMode);
            root.put("isCompleted", isCompleted);
            root.put("timestamp", System.currentTimeMillis());

            int mastered = 0;
            int learning = 0;
            int newWords = 0;

            JSONArray wordsArray = new JSONArray();
            for (VocabItem item : allVocab) {
                if (item == null) continue;
                JSONObject w = new JSONObject();
                w.put("en", item.getEnglishWord());
                w.put("vn", item.getVietnameseMeaning());

                Integer st = wordState.get(item);
                int stateVal = (st != null) ? st : 0;
                w.put("state", stateVal);
                if (stateVal == 2) mastered++;
                else if (stateVal == 1) learning++;
                else newWords++;

                Integer streak = correctStreak.get(item);
                w.put("streak", (streak != null) ? streak : 0);

                Boolean mc = answeredByMC.get(item);
                w.put("mc", (mc != null && mc));

                Boolean wr = answeredByWrite.get(item);
                w.put("wr", (wr != null && wr));

                w.put("star", (starredWords != null && starredWords.contains(item)));

                wordsArray.put(w);
            }
            root.put("words", wordsArray);
            root.put("masteredCount", mastered);
            root.put("learningCount", learning);
            root.put("newCount", newWords);

            // Lưu thứ tự các từ còn lại trong hàng đợi
            JSONArray queueArray = new JSONArray();
            if (queue != null) {
                for (VocabItem qItem : queue) {
                    if (qItem != null) {
                        queueArray.put(qItem.getEnglishWord());
                    }
                }
            }
            root.put("queue", queueArray);

            getPrefs(context).edit().putString(KEY_SESSION_PREFIX + lessonId, root.toString()).apply();
            Log.d(TAG, "Saved session for lesson " + lessonId + ": " + mastered + " mastered, " + learning + " learning");
        } catch (Exception e) {
            Log.e(TAG, "Error saving session for lesson " + lessonId, e);
        }
    }

    /**
     * Khôi phục trạng thái phiên học từ bộ nhớ
     */
    public static SessionData restoreSession(Context context,
                                             int lessonId,
                                             List<VocabItem> allVocab,
                                             Map<VocabItem, Integer> wordState,
                                             Map<VocabItem, Integer> correctStreak,
                                             Map<VocabItem, Boolean> answeredByMC,
                                             Map<VocabItem, Boolean> answeredByWrite,
                                             Set<VocabItem> starredWords,
                                             List<VocabItem> queue) {
        if (lessonId <= 0 || allVocab == null || allVocab.isEmpty()) return null;

        String jsonStr = getPrefs(context).getString(KEY_SESSION_PREFIX + lessonId, null);
        if (jsonStr == null || jsonStr.trim().isEmpty()) return null;

        try {
            JSONObject root = new JSONObject(jsonStr);
            SessionData data = new SessionData();
            data.sessionCorrect = root.optInt("sessionCorrect", 0);
            data.sessionWrong = root.optInt("sessionWrong", 0);
            data.starOnlyMode = root.optBoolean("starOnlyMode", false);
            data.masteredCount = root.optInt("masteredCount", 0);
            data.learningCount = root.optInt("learningCount", 0);
            data.newCount = root.optInt("newCount", 0);

            // Bản đồ tra cứu từ allVocab theo từ tiếng Anh (hoặc tiếng Nhật)
            Map<String, VocabItem> lookup = new java.util.HashMap<>();
            for (VocabItem item : allVocab) {
                if (item != null && item.getEnglishWord() != null) {
                    lookup.put(item.getEnglishWord(), item);
                }
            }

            JSONArray wordsArray = root.optJSONArray("words");
            if (wordsArray != null) {
                for (int i = 0; i < wordsArray.length(); i++) {
                    JSONObject w = wordsArray.getJSONObject(i);
                    String en = w.optString("en");
                    VocabItem item = lookup.get(en);
                    if (item != null) {
                        int state = w.optInt("state", 0);
                        int streak = w.optInt("streak", 0);
                        boolean mc = w.optBoolean("mc", false);
                        boolean wr = w.optBoolean("wr", false);
                        boolean star = w.optBoolean("star", false);

                        wordState.put(item, state);
                        correctStreak.put(item, streak);
                        answeredByMC.put(item, mc);
                        answeredByWrite.put(item, wr);

                        if (star && starredWords != null) {
                            starredWords.add(item);
                        }
                    }
                }
            }

            // Đảm bảo tất cả các từ trong allVocab đều có trạng thái hợp lệ
            for (VocabItem item : allVocab) {
                if (!wordState.containsKey(item)) {
                    wordState.put(item, 0);
                    correctStreak.put(item, 0);
                    answeredByMC.put(item, false);
                    answeredByWrite.put(item, false);
                }
            }

            // Khôi phục hàng đợi câu hỏi
            queue.clear();
            JSONArray queueArray = root.optJSONArray("queue");
            Set<VocabItem> addedToQueue = new HashSet<>();
            if (queueArray != null && queueArray.length() > 0) {
                for (int i = 0; i < queueArray.length(); i++) {
                    String en = queueArray.optString(i);
                    VocabItem item = lookup.get(en);
                    if (item != null && !addedToQueue.contains(item)) {
                        Integer state = wordState.get(item);
                        if (state == null || state != 2) { // Chỉ thêm từ chưa mastered
                            queue.add(item);
                            addedToQueue.add(item);
                        }
                    }
                }
            }

            // Thêm những từ chưa mastered mà chưa có trong hàng đợi
            for (VocabItem item : allVocab) {
                Integer state = wordState.get(item);
                if ((state == null || state != 2) && !addedToQueue.contains(item)) {
                    queue.add(item);
                    addedToQueue.add(item);
                }
            }

            Log.d(TAG, "Restored session for lesson " + lessonId + ": queue size=" + queue.size());
            return data;
        } catch (Exception e) {
            Log.e(TAG, "Error restoring session for lesson " + lessonId, e);
            return null;
        }
    }

    /**
     * Xóa sạch phiên học (khi hoàn thành hoặc khi người dùng chọn học lại từ đầu)
     */
    public static void clearSession(Context context, int lessonId) {
        if (lessonId <= 0) return;
        getPrefs(context).edit().remove(KEY_SESSION_PREFIX + lessonId).apply();
        Log.d(TAG, "Cleared session for lesson " + lessonId);
    }
}
