package com.hieunn.learnenglish;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class JapaneseGrammarProgressManager {

    private static final String PREF_NAME = "japanese_grammar_progress";
    private static final String KEY_POINT_PREFIX = "grammar_completed_"; // format: grammar_completed_{lesson}_{point}
    private static final String KEY_LESSON_QUIZ_PREFIX = "grammar_lesson_quiz_passed_"; // format: grammar_lesson_quiz_passed_{lesson}

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isPointCompleted(Context context, int lessonNumber, int pointNumber) {
        if (context == null) return false;
        String key = KEY_POINT_PREFIX + lessonNumber + "_" + pointNumber;
        return getPrefs(context).getBoolean(key, false);
    }

    public static void markPointCompleted(Context context, int lessonNumber, int pointNumber) {
        if (context == null) return;
        String key = KEY_POINT_PREFIX + lessonNumber + "_" + pointNumber;
        getPrefs(context).edit().putBoolean(key, true).apply();
    }

    public static int getCompletedPointsCount(Context context, int lessonNumber) {
        if (context == null) return 0;
        JapaneseGrammarLesson lesson = JapaneseGrammarDataManager.getLessonByNumber(context, lessonNumber);
        if (lesson == null) return 0;

        int count = 0;
        for (JapaneseGrammarPoint p : lesson.getPoints()) {
            if (isPointCompleted(context, lessonNumber, p.getPointNumber())) {
                count++;
            }
        }
        return count;
    }

    public static int getLessonProgressPercent(Context context, int lessonNumber) {
        if (context == null) return 0;
        JapaneseGrammarLesson lesson = JapaneseGrammarDataManager.getLessonByNumber(context, lessonNumber);
        if (lesson == null || lesson.getPoints().isEmpty()) return 0;

        int total = lesson.getPoints().size();
        int completed = getCompletedPointsCount(context, lessonNumber);
        return (int) Math.round((completed * 100.0) / total);
    }

    public static int getFirstUncompletedPoint(Context context, int lessonNumber) {
        if (context == null) return 1;
        JapaneseGrammarLesson lesson = JapaneseGrammarDataManager.getLessonByNumber(context, lessonNumber);
        if (lesson == null || lesson.getPoints().isEmpty()) return 1;

        for (JapaneseGrammarPoint p : lesson.getPoints()) {
            if (!isPointCompleted(context, lessonNumber, p.getPointNumber())) {
                return p.getPointNumber();
            }
        }
        // If all are completed, return 1 to review
        return 1;
    }

    public static boolean isLessonFullyCompleted(Context context, int lessonNumber) {
        if (context == null) return false;
        JapaneseGrammarLesson lesson = JapaneseGrammarDataManager.getLessonByNumber(context, lessonNumber);
        if (lesson == null || lesson.getPoints().isEmpty()) return false;
        return getCompletedPointsCount(context, lessonNumber) >= lesson.getPoints().size();
    }

    public static int getTotalOverallCompletedPoints(Context context) {
        if (context == null) return 0;
        List<JapaneseGrammarLesson> lessons = JapaneseGrammarDataManager.getAllLessons(context);
        int total = 0;
        for (JapaneseGrammarLesson l : lessons) {
            total += getCompletedPointsCount(context, l.getLessonNumber());
        }
        return total;
    }

    public static void setLessonQuizPassed(Context context, int lessonNumber, boolean passed) {
        if (context == null) return;
        getPrefs(context).edit().putBoolean(KEY_LESSON_QUIZ_PREFIX + lessonNumber, passed).apply();
    }

    public static boolean isLessonQuizPassed(Context context, int lessonNumber) {
        if (context == null) return false;
        return getPrefs(context).getBoolean(KEY_LESSON_QUIZ_PREFIX + lessonNumber, false);
    }
}
