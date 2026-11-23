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
                                String itemIndex = qObj.optString("itemIndex", String.valueOf(q + 1));
                                String prompt = qObj.optString("prompt", qObj.optString("questionText", ""));
                                String qVi = qObj.optString("questionVi", "");

                                String correctAnswer = qObj.optString("correctAnswer", "").trim().toLowerCase();

                                List<String> opts = new ArrayList<>();
                                JSONArray optArr = qObj.optJSONArray("options");
                                if (optArr != null) {
                                    for (int o = 0; o < optArr.length(); o++) {
                                        opts.add(optArr.getString(o));
                                    }
                                }

                                int correctIndex = qObj.optInt("correctIndex", -1);
                                if (correctIndex < 0 && !correctAnswer.isEmpty() && !opts.isEmpty()) {
                                    // 1. Exact match
                                    for (int o = 0; o < opts.size(); o++) {
                                        if (opts.get(o).trim().equalsIgnoreCase(correctAnswer)) {
                                            correctIndex = o;
                                            break;
                                        }
                                    }
                                    // 2. Prefix match e.g. correctAnswer is "b" and opt is "b. ..." or "b) ..."
                                    if (correctIndex < 0 && correctAnswer.length() == 1) {
                                        String pfxDot = correctAnswer + ".";
                                        String pfxParen = correctAnswer + ")";
                                        String pfxParenJa = correctAnswer + "）";
                                        for (int o = 0; o < opts.size(); o++) {
                                            String optTrim = opts.get(o).trim().toLowerCase();
                                            if (optTrim.startsWith(pfxDot) || optTrim.startsWith(pfxParen) || optTrim.startsWith(pfxParenJa)) {
                                                correctIndex = o;
                                                break;
                                            }
                                        }
                                    }
                                    // 3. Option starts with answer or answer starts with option e.g. "e. 奈良"
                                    if (correctIndex < 0) {
                                        for (int o = 0; o < opts.size(); o++) {
                                            String optTrim = opts.get(o).trim().toLowerCase();
                                            if (optTrim.startsWith(correctAnswer) || correctAnswer.startsWith(optTrim)) {
                                                correctIndex = o;
                                                break;
                                            }
                                        }
                                    }
                                    // 4. Fallback: single letter index if in range
                                    if (correctIndex < 0 && !correctAnswer.isEmpty()) {
                                        char c = correctAnswer.charAt(0);
                                        if (c >= 'a' && c <= 'z') {
                                            int idx = c - 'a';
                                            if (idx < opts.size()) correctIndex = idx;
                                        } else if (c >= '0' && c <= '9') {
                                            int idx = c - '0';
                                            if (idx < opts.size()) correctIndex = idx;
                                        }
                                    }
                                }
                                if (correctIndex < 0) correctIndex = 0;

                                String expl = qObj.optString("explanation", "");

                                String subDialogue = extractSubDialogue(script, itemIndex);
                                if (subDialogue == null || subDialogue.isEmpty()) {
                                    subDialogue = script;
                                }

                                qList.add(new ChoukaiQuestion(
                                        qNum, itemIndex, prompt, prompt, qVi, opts, correctIndex, correctAnswer, expl, subDialogue
                                ));
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

    public static String extractSubDialogue(String script, String itemIndex) {
        if (script == null || script.isEmpty() || itemIndex == null) return "";
        String[] lines = script.split("\n");
        String currentKey = null;
        StringBuilder sb = null;
        Map<String, String> map = new HashMap<>();

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;

            int sepIndex = -1;
            String keyCandidate = null;
            if (trimmed.startsWith("例")) {
                keyCandidate = "例";
                sepIndex = 1;
            } else {
                int i = 0;
                while (i < trimmed.length() && Character.isDigit(trimmed.charAt(i))) {
                    i++;
                }
                if (i > 0) {
                    keyCandidate = trimmed.substring(0, i);
                    sepIndex = i;
                }
            }

            if (keyCandidate != null && sepIndex < trimmed.length()) {
                char nextChar = trimmed.charAt(sepIndex);
                if (nextChar == ')' || nextChar == '）' || nextChar == '.' || nextChar == ':' || nextChar == '：' || nextChar == '、') {
                    if (currentKey != null && sb != null) {
                        map.put(currentKey, sb.toString().trim());
                    }
                    currentKey = keyCandidate;
                    sb = new StringBuilder();
                    String remainder = trimmed.substring(sepIndex + 1).trim();
                    if (!remainder.isEmpty()) {
                        sb.append(remainder).append("\n");
                    }
                    continue;
                }
            }

            if (currentKey != null && sb != null) {
                sb.append(trimmed).append("\n");
            }
        }

        if (currentKey != null && sb != null) {
            map.put(currentKey, sb.toString().trim());
        }

        return map.containsKey(itemIndex) ? map.get(itemIndex) : "";
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
