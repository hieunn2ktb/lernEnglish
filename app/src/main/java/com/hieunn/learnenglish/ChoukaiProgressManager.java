package com.hieunn.learnenglish;

import android.content.Context;
import android.content.SharedPreferences;

/** Persists Choukai answers and the last opened task for every lesson. */
public final class ChoukaiProgressManager {
    private static final String PREF_NAME = "choukai_progress";
    private static final String KEY_LAST_TASK = "last_task_";
    private static final String KEY_ANSWER = "answer_";

    private ChoukaiProgressManager() {
    }

    private static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    private static String answerKey(int lessonNumber, int taskNumber, int questionNumber) {
        return KEY_ANSWER + lessonNumber + "_" + taskNumber + "_" + questionNumber;
    }

    public static void saveAnswer(Context context, int lessonNumber, int taskNumber,
                                  int questionNumber, int selectedIndex) {
        prefs(context).edit()
                .putInt(answerKey(lessonNumber, taskNumber, questionNumber), selectedIndex)
                .apply();
    }

    public static int getSelectedAnswer(Context context, int lessonNumber, int taskNumber,
                                        int questionNumber) {
        return prefs(context).getInt(
                answerKey(lessonNumber, taskNumber, questionNumber), -1);
    }

    public static void saveLastTask(Context context, int lessonNumber, int taskIndex) {
        prefs(context).edit().putInt(KEY_LAST_TASK + lessonNumber, taskIndex).apply();
    }

    public static int getLastTask(Context context, int lessonNumber, int taskCount) {
        if (taskCount <= 0) return 0;
        int saved = prefs(context).getInt(KEY_LAST_TASK + lessonNumber, 0);
        return Math.max(0, Math.min(saved, taskCount - 1));
    }

    public static int getAnsweredCount(Context context, ChoukaiLesson lesson) {
        if (lesson == null) return 0;
        int count = 0;
        for (ChoukaiTask task : lesson.getTasks()) {
            for (ChoukaiQuestion question : task.getQuestions()) {
                if (getSelectedAnswer(context, lesson.getLessonNumber(), task.getTaskNumber(),
                        question.getQuestionNumber()) >= 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int getTotalQuestionCount(ChoukaiLesson lesson) {
        if (lesson == null) return 0;
        int count = 0;
        for (ChoukaiTask task : lesson.getTasks()) {
            count += task.getQuestions().size();
        }
        return count;
    }

    public static boolean isTaskCompleted(Context context, int lessonNumber, ChoukaiTask task) {
        if (task == null || task.getQuestions().isEmpty()) return false;
        for (ChoukaiQuestion question : task.getQuestions()) {
            if (getSelectedAnswer(context, lessonNumber, task.getTaskNumber(),
                    question.getQuestionNumber()) < 0) {
                return false;
            }
        }
        return true;
    }
}
