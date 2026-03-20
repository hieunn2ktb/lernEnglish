package com.hieunn.learnenglish.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lessons")
public class LessonEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String description;
    public String videoUrl;
    public long createdAt;

    public LessonEntity() {
    }

    public LessonEntity(String title, String description, String videoUrl) {
        this.title = title;
        this.description = description;
        this.videoUrl = videoUrl;
        this.createdAt = System.currentTimeMillis();
    }
}
