package com.hieunn.learnenglish.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vocab_items")
public class VocabEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int lessonId;
    public String english;
    public String vietnamese;
    public String phonetic;
    public String wordType;

    public VocabEntity() {
    }

    public VocabEntity(int lessonId, String english, String vietnamese, String phonetic, String wordType) {
        this.lessonId = lessonId;
        this.english = english;
        this.vietnamese = vietnamese;
        this.phonetic = phonetic;
        this.wordType = wordType;
    }
}
