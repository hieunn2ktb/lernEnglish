package com.hieunn.learnenglish.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GrammarQuizDao {
    @Query("SELECT * FROM grammar_quiz WHERE lessonId = :lessonId")
    List<GrammarQuizEntity> getQuizByLessonId(int lessonId);

    @Insert
    void insertQuiz(GrammarQuizEntity quiz);

    @Insert
    void insertAllQuiz(List<GrammarQuizEntity> quizList);

    @Query("DELETE FROM grammar_quiz WHERE lessonId = :lessonId")
    void deleteByLessonId(int lessonId);

    @Query("SELECT COUNT(*) FROM grammar_quiz WHERE lessonId = :lessonId")
    int getQuizCount(int lessonId);
}
