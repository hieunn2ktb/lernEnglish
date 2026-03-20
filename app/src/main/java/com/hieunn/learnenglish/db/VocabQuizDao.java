package com.hieunn.learnenglish.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VocabQuizDao {
    @Query("SELECT * FROM vocab_quiz WHERE lessonId = :lessonId")
    List<VocabQuizEntity> getQuizByLessonId(int lessonId);

    @Insert
    void insertQuiz(VocabQuizEntity quiz);

    @Insert
    void insertAllQuiz(List<VocabQuizEntity> quizList);

    @Query("DELETE FROM vocab_quiz WHERE lessonId = :lessonId")
    void deleteByLessonId(int lessonId);

    @Query("SELECT COUNT(*) FROM vocab_quiz WHERE lessonId = :lessonId")
    int getQuizCount(int lessonId);
}
