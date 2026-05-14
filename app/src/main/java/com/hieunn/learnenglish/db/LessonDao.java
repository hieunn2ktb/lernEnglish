package com.hieunn.learnenglish.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LessonDao {
    @Query("SELECT * FROM lessons ORDER BY id ASC")
    List<LessonEntity> getAllLessons();

    @Query("SELECT * FROM lessons WHERE id = :id")
    LessonEntity getLessonById(int id);

    @Insert
    long insertLesson(LessonEntity lesson);

    @Delete
    void deleteLesson(LessonEntity lesson);

    @Update
    void updateLesson(LessonEntity lesson);

    @Query("DELETE FROM lessons WHERE id = :id")
    void deleteLessonById(int id);
}
