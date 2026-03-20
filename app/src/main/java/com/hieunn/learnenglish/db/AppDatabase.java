package com.hieunn.learnenglish.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { LessonEntity.class, VocabEntity.class, GrammarQuizEntity.class,
        VocabQuizEntity.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract LessonDao lessonDao();

    public abstract VocabDao vocabDao();

    public abstract GrammarQuizDao grammarQuizDao();

    public abstract VocabQuizDao vocabQuizDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "learn_english_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
