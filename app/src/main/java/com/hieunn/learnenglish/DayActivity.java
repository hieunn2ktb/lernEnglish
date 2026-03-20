package com.hieunn.learnenglish;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.content.ActivityNotFoundException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.LessonEntity;

public class DayActivity extends AppCompatActivity {

    private int lessonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_day_one); // Dùng chung layout

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lessonId = getIntent().getIntExtra("lessonId", -1);

        // Load lesson info
        AppDatabase db = AppDatabase.getInstance(this);
        LessonEntity lesson = db.lessonDao().getLessonById(lessonId);

        // Update header title
        TextView tvTitle = findViewById(R.id.tvLessonTitle);
        if (tvTitle != null && lesson != null) {
            tvTitle.setText(lesson.title);
        }

        // Update description
        TextView tvDesc = findViewById(R.id.tvLessonDesc);
        if (tvDesc != null && lesson != null && lesson.description != null && !lesson.description.isEmpty()) {
            tvDesc.setText(lesson.description);
        }

        // Back button
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // YouTube Player - ẩn nếu không có video
        View playerContainer = findViewById(R.id.youtube_player_container);
        ImageView thumbnailView = findViewById(R.id.youtube_thumbnail);

        if (lesson == null || lesson.videoUrl == null || lesson.videoUrl.trim().isEmpty()) {
            if (playerContainer != null)
                playerContainer.setVisibility(View.GONE);
        } else {
            String videoId = lesson.videoUrl.trim();
            // Xử lý nếu người dùng nhập full URL
            if (videoId.contains("v=")) {
                videoId = videoId.split("v=")[1];
                int ampersandPosition = videoId.indexOf('&');
                if (ampersandPosition != -1) {
                    videoId = videoId.substring(0, ampersandPosition);
                }
            } else if (videoId.contains("youtu.be/")) {
                videoId = videoId.split("youtu.be/")[1];
                int questionPosition = videoId.indexOf('?');
                if (questionPosition != -1) {
                    videoId = videoId.substring(0, questionPosition);
                }
            }

            // Ép Regex chặn tất cả ký tự lạ, Zero-width space hay UTF-8 lỗi
            String finalVideoId = videoId.replaceAll("[^a-zA-Z0-9_-]", "");
            Log.d("LearnEnglish", "DayActivity Thumbnail for: '" + finalVideoId + "'");

            // Load Thumbnail bằng Glide
            if (thumbnailView != null) {
                String thumbUrl = "https://img.youtube.com/vi/" + finalVideoId + "/hqdefault.jpg";
                Glide.with(this).load(thumbUrl).into(thumbnailView);
            }

            // Bấm vào để mở YouTube
            if (playerContainer != null) {
                playerContainer.setOnClickListener(v -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + finalVideoId));
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://www.youtube.com/watch?v=" + finalVideoId));
                        startActivity(intent);
                    }
                });
            }
        }

        // Playlist items
        int vocabCount = db.vocabDao().getVocabCount(lessonId);
        int grammarCount = db.grammarQuizDao().getQuizCount(lessonId);

        // Ẩn các item không có nội dung
        View itemVocab = findViewById(R.id.itemVocab);
        if (itemVocab != null) {
            if (vocabCount > 0) {
                itemVocab.setOnClickListener(v -> {
                    Intent intent = new Intent(this, UnitDetailActivity.class);
                    intent.putExtra("lessonId", lessonId);
                    startActivity(intent);
                });
            } else {
                itemVocab.setVisibility(View.GONE);
            }
        }

        View itemGrammar = findViewById(R.id.itemGrammar);
        if (itemGrammar != null) {
            if (grammarCount > 0) {
                itemGrammar.setOnClickListener(v -> {
                    Intent intent = new Intent(this, ExerciseActivity.class);
                    intent.putExtra("lessonId", lessonId);
                    startActivity(intent);
                });
            } else {
                itemGrammar.setVisibility(View.GONE);
            }
        }

        // Ẩn các item không dùng cho dynamic lesson
        View itemSummary = findViewById(R.id.itemSummary);
        if (itemSummary != null)
            itemSummary.setVisibility(View.GONE);

        View itemVocabQuiz = findViewById(R.id.itemVocabQuiz);
        if (itemVocabQuiz != null)
            itemVocabQuiz.setVisibility(View.GONE);

        // Continue button
        Button btnContinue = findViewById(R.id.btnContinue);
        if (btnContinue != null) {
            btnContinue.setOnClickListener(v -> {
                if (vocabCount > 0) {
                    Intent intent = new Intent(this, UnitDetailActivity.class);
                    intent.putExtra("lessonId", lessonId);
                    startActivity(intent);
                } else if (grammarCount > 0) {
                    Intent intent = new Intent(this, ExerciseActivity.class);
                    intent.putExtra("lessonId", lessonId);
                    startActivity(intent);
                }
            });
        }
    }
}
