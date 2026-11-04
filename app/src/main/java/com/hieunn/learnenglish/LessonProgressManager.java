package com.hieunn.learnenglish;

import android.content.Context;
import android.content.SharedPreferences;

import com.hieunn.learnenglish.db.AppDatabase;

public class LessonProgressManager {

    private static final String PREF_NAME = "lesson_learning_progress";

    private static final String KEY_VOCAB_MASTERED = "vocab_mastered_";
    private static final String KEY_VOCAB_CORRECT = "vocab_correct_";
    private static final String KEY_VOCAB_WRONG = "vocab_wrong_";

    private static final String KEY_GRAMMAR_MASTERED = "grammar_mastered_";
    private static final String KEY_GRAMMAR_CORRECT = "grammar_correct_";
    private static final String KEY_GRAMMAR_WRONG = "grammar_wrong_";

    private static final String KEY_VIDEO_WATCHED = "video_watched_";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void recordGrammarAnswer(Context context, int lessonId, boolean isCorrect, int currentMasteredCount) {
        if (lessonId <= 0) return;
        SharedPreferences prefs = getPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();

        if (isCorrect) {
            int correct = prefs.getInt(KEY_GRAMMAR_CORRECT + lessonId, 0) + 1;
            editor.putInt(KEY_GRAMMAR_CORRECT + lessonId, correct);
        } else {
            int wrong = prefs.getInt(KEY_GRAMMAR_WRONG + lessonId, 0) + 1;
            editor.putInt(KEY_GRAMMAR_WRONG + lessonId, wrong);
        }

        int oldMastered = prefs.getInt(KEY_GRAMMAR_MASTERED + lessonId, 0);
        if (currentMasteredCount > oldMastered) {
            editor.putInt(KEY_GRAMMAR_MASTERED + lessonId, currentMasteredCount);
        }

        editor.apply();
    }

    public static void setGrammarMastered(Context context, int lessonId, int masteredCount) {
        if (lessonId <= 0) return;
        getPrefs(context).edit().putInt(KEY_GRAMMAR_MASTERED + lessonId, masteredCount).apply();
    }

    public static void recordVocabAnswer(Context context, int lessonId, boolean isCorrect, int currentMasteredCount) {
        if (lessonId <= 0) return;
        SharedPreferences prefs = getPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();

        if (isCorrect) {
            int correct = prefs.getInt(KEY_VOCAB_CORRECT + lessonId, 0) + 1;
            editor.putInt(KEY_VOCAB_CORRECT + lessonId, correct);
        } else {
            int wrong = prefs.getInt(KEY_VOCAB_WRONG + lessonId, 0) + 1;
            editor.putInt(KEY_VOCAB_WRONG + lessonId, wrong);
        }

        int oldMastered = prefs.getInt(KEY_VOCAB_MASTERED + lessonId, 0);
        if (currentMasteredCount > oldMastered) {
            editor.putInt(KEY_VOCAB_MASTERED + lessonId, currentMasteredCount);
        }

        editor.apply();
    }

    public static void setVocabMastered(Context context, int lessonId, int masteredCount) {
        if (lessonId <= 0) return;
        getPrefs(context).edit().putInt(KEY_VOCAB_MASTERED + lessonId, masteredCount).apply();
    }

    public static void recordVideoWatched(Context context, int lessonId) {
        if (lessonId <= 0) return;
        getPrefs(context).edit().putBoolean(KEY_VIDEO_WATCHED + lessonId, true).apply();
    }

    public static boolean isVideoWatched(Context context, int lessonId) {
        if (lessonId <= 0) return false;
        return getPrefs(context).getBoolean(KEY_VIDEO_WATCHED + lessonId, false);
    }

    public static int getVocabMastered(Context context, int lessonId) {
        if (lessonId <= 0) return 0;
        int mastered = getPrefs(context).getInt(KEY_VOCAB_MASTERED + lessonId, 0);
        int total = getVocabTotal(context, lessonId);
        return Math.min(mastered, total);
    }

    public static int getVocabTotal(Context context, int lessonId) {
        if (lessonId <= 0) return 0;
        try {
            return AppDatabase.getInstance(context).vocabDao().getVocabCount(lessonId);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getGrammarMastered(Context context, int lessonId) {
        if (lessonId <= 0) return 0;
        int mastered = getPrefs(context).getInt(KEY_GRAMMAR_MASTERED + lessonId, 0);
        int total = getGrammarTotal(context, lessonId);
        return Math.min(mastered, total);
    }

    public static int getGrammarTotal(Context context, int lessonId) {
        if (lessonId <= 0) return 0;
        try {
            return AppDatabase.getInstance(context).grammarQuizDao().getQuizCount(lessonId);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getTotalCorrect(Context context, int lessonId) {
        if (lessonId <= 0) return 0;
        SharedPreferences prefs = getPrefs(context);
        return prefs.getInt(KEY_VOCAB_CORRECT + lessonId, 0) + prefs.getInt(KEY_GRAMMAR_CORRECT + lessonId, 0);
    }

    public static int getTotalWrong(Context context, int lessonId) {
        if (lessonId <= 0) return 0;
        SharedPreferences prefs = getPrefs(context);
        return prefs.getInt(KEY_VOCAB_WRONG + lessonId, 0) + prefs.getInt(KEY_GRAMMAR_WRONG + lessonId, 0);
    }

    public static int getAccuracyPercent(Context context, int lessonId) {
        int correct = getTotalCorrect(context, lessonId);
        int wrong = getTotalWrong(context, lessonId);
        int total = correct + wrong;
        if (total <= 0) return 0;
        return (correct * 100) / total;
    }

    public static int getVocabProgressPercent(Context context, int lessonId) {
        int total = getVocabTotal(context, lessonId);
        if (total <= 0) return 100;
        int mastered = getVocabMastered(context, lessonId);
        return Math.min(100, (mastered * 100) / total);
    }

    public static int getGrammarProgressPercent(Context context, int lessonId) {
        int total = getGrammarTotal(context, lessonId);
        if (total <= 0) return 100;
        int mastered = getGrammarMastered(context, lessonId);
        return Math.min(100, (mastered * 100) / total);
    }

    public static int getDayProgressPercent(Context context, int lessonId) {
        int vocabTotal = getVocabTotal(context, lessonId);
        int grammarTotal = getGrammarTotal(context, lessonId);
        int totalItems = vocabTotal + grammarTotal;

        if (totalItems <= 0) {
            return isVideoWatched(context, lessonId) ? 100 : 0;
        }

        int mastered = getVocabMastered(context, lessonId) + getGrammarMastered(context, lessonId);
        return Math.min(100, (mastered * 100) / totalItems);
    }

    public static void resetProgress(Context context, int lessonId) {
        if (lessonId <= 0) return;
        getPrefs(context).edit()
                .remove(KEY_VOCAB_MASTERED + lessonId)
                .remove(KEY_VOCAB_CORRECT + lessonId)
                .remove(KEY_VOCAB_WRONG + lessonId)
                .remove(KEY_GRAMMAR_MASTERED + lessonId)
                .remove(KEY_GRAMMAR_CORRECT + lessonId)
                .remove(KEY_GRAMMAR_WRONG + lessonId)
                .remove(KEY_VIDEO_WATCHED + lessonId)
                .apply();
    }
}
