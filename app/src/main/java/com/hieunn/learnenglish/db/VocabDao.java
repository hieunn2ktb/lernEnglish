package com.hieunn.learnenglish.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VocabDao {
    @Query("SELECT * FROM vocab_items WHERE lessonId = :lessonId")
    List<VocabEntity> getVocabByLessonId(int lessonId);

    @Insert
    void insertVocab(VocabEntity vocab);

    @Insert
    void insertAllVocab(List<VocabEntity> vocabList);

    @Query("DELETE FROM vocab_items WHERE lessonId = :lessonId")
    void deleteByLessonId(int lessonId);

    @Query("SELECT COUNT(*) FROM vocab_items WHERE lessonId = :lessonId")
    int getVocabCount(int lessonId);
}
