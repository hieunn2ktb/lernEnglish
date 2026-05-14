package com.hieunn.learnenglish;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LessonSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lesson_summary);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Back
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
        
        int lessonId = getIntent().getIntExtra("lessonId", -1);

        // On lai tu vung -> mo UnitDetailActivity
        Button btnReviewVocab = findViewById(R.id.btnReviewVocab);
        btnReviewVocab.setOnClickListener(v -> {
            Intent intent = new Intent(this, UnitDetailActivity.class);
            intent.putExtra("lessonId", lessonId);
            startActivity(intent);
        });

        // Lam lai bai tap -> mo ExerciseActivity
        Button btnRedoExercise = findViewById(R.id.btnRedoExercise);
        btnRedoExercise.setOnClickListener(v -> {
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra("lessonId", lessonId);
            startActivity(intent);
        });

        // Kiem tra tu vung lai -> mo VocabQuizActivity
        Button btnRedoQuiz = findViewById(R.id.btnRedoQuiz);
        btnRedoQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(this, VocabQuizActivity.class);
            intent.putExtra("lessonId", lessonId);
            startActivity(intent);
        });

        // Ve trang chinh -> quay ve MainActivity
        Button btnGoHome = findViewById(R.id.btnGoHome);
        btnGoHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}
