package com.hieunn.learnenglish;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.LessonEntity;

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
        if (lessonId > 0) {
            AppDatabase db = AppDatabase.getInstance(this);
            LessonEntity lesson = db.lessonDao().getLessonById(lessonId);
            if (lesson != null) {
                TextView tvTitle = findViewById(R.id.tvSummaryLessonTitle);
                if (tvTitle != null) tvTitle.setText(lesson.title);
            }

            int percent = LessonProgressManager.getDayProgressPercent(this, lessonId);
            int accuracy = LessonProgressManager.getAccuracyPercent(this, lessonId);
            int vocabMastered = LessonProgressManager.getVocabMastered(this, lessonId);
            int vocabTotal = LessonProgressManager.getVocabTotal(this, lessonId);
            int grammarMastered = LessonProgressManager.getGrammarMastered(this, lessonId);
            int grammarTotal = LessonProgressManager.getGrammarTotal(this, lessonId);

            TextView tvStatus = findViewById(R.id.tvSummaryStatus);
            if (tvStatus != null) {
                tvStatus.setText("Tiến độ: " + percent + "% • " + (percent >= 100 ? "Hoàn thành xuất sắc" : "Đang học"));
            }

            TextView tvAccuracy = findViewById(R.id.tvSummaryAccuracy);
            if (tvAccuracy != null) tvAccuracy.setText(accuracy + "%");

            TextView tvVocab = findViewById(R.id.tvSummaryVocabCount);
            if (tvVocab != null) tvVocab.setText(vocabMastered + "/" + vocabTotal);

            TextView tvGrammar = findViewById(R.id.tvSummaryGrammarCount);
            if (tvGrammar != null) tvGrammar.setText(grammarMastered + "/" + grammarTotal);
        }

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
