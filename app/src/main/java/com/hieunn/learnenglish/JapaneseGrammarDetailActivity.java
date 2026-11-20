package com.hieunn.learnenglish;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class JapaneseGrammarDetailActivity extends AppCompatActivity {

    private int lessonNumber = 1;
    private JapaneseGrammarLesson currentLesson;
    private TextToSpeech tts;

    private TextView tvGrammarHeaderTitle;
    private TextView tvGrammarHeaderSubtitle;
    private TextView tvLevelBadge;
    private TextView tvProgressFraction;
    private ProgressBar pbLessonProgress;
    private Button btnContinueStudy;
    private TextView tvLessonBannerTitle;
    private TextView tvLessonBannerSummary;
    private RecyclerView rvGrammarPoints;
    private JapaneseGrammarPointAdapter adapter;
    private CardView cardKaiteOboeru;
    private CardView cardChoukai;
    private CardView cardComprehensiveQuiz;
    private TextView tvComprehensiveStatus;
    private Button btnPrevLesson;
    private Button btnNextLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_japanese_grammar_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lessonNumber = getIntent().getIntExtra("lessonNumber", 1);

        initViews();
        initTTS();
        loadLesson(lessonNumber);
    }

    private void initViews() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        ImageButton btnSpeakGrammar = findViewById(R.id.btnSpeakGrammar);
        tvGrammarHeaderTitle = findViewById(R.id.tvGrammarHeaderTitle);
        tvGrammarHeaderSubtitle = findViewById(R.id.tvGrammarHeaderSubtitle);
        tvLevelBadge = findViewById(R.id.tvLevelBadge);
        tvProgressFraction = findViewById(R.id.tvProgressFraction);
        pbLessonProgress = findViewById(R.id.pbLessonProgress);
        btnContinueStudy = findViewById(R.id.btnContinueStudy);
        tvLessonBannerTitle = findViewById(R.id.tvLessonBannerTitle);
        tvLessonBannerSummary = findViewById(R.id.tvLessonBannerSummary);
        rvGrammarPoints = findViewById(R.id.rvGrammarPoints);
        cardKaiteOboeru = findViewById(R.id.cardKaiteOboeru);
        cardComprehensiveQuiz = findViewById(R.id.cardComprehensiveQuiz);
        cardChoukai = findViewById(R.id.cardChoukai);
        tvComprehensiveStatus = findViewById(R.id.tvComprehensiveStatus);
        btnPrevLesson = findViewById(R.id.btnPrevLesson);
        btnNextLesson = findViewById(R.id.btnNextLesson);

        btnBack.setOnClickListener(v -> finish());
        btnSpeakGrammar.setOnClickListener(v -> speakLesson());

        rvGrammarPoints.setLayoutManager(new LinearLayoutManager(this));
        adapter = new JapaneseGrammarPointAdapter(this, lessonNumber, (point, position) -> {
            openGrammarPointStudy(point.getPointNumber());
        });
        rvGrammarPoints.setAdapter(adapter);

        btnContinueStudy.setOnClickListener(v -> {
            int targetPoint = JapaneseGrammarProgressManager.getFirstUncompletedPoint(this, lessonNumber);
            openGrammarPointStudy(targetPoint);
        });

        if (cardKaiteOboeru != null) {
            cardKaiteOboeru.setOnClickListener(v -> {
                Intent intent = new Intent(this, JapaneseGrammarPracticeActivity.class);
                intent.putExtra("lessonNumber", lessonNumber);
                intent.putExtra("isKaiteOboeru", true);
                startActivity(intent);
            });
        }

        cardComprehensiveQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(this, JapaneseGrammarPracticeActivity.class);
            intent.putExtra("lessonNumber", lessonNumber);
            intent.putExtra("isComprehensive", true);
            startActivity(intent);
        });

        if (cardChoukai != null) {
            cardChoukai.setOnClickListener(v -> {
                Intent intent = new Intent(this, JapaneseChoukaiActivity.class);
                intent.putExtra("lessonNumber", lessonNumber);
                startActivity(intent);
            });
        }

        btnPrevLesson.setOnClickListener(v -> {
            if (lessonNumber > 1) {
                loadLesson(lessonNumber - 1);
            }
        });

        btnNextLesson.setOnClickListener(v -> {
            if (lessonNumber < 50) {
                loadLesson(lessonNumber + 1);
            }
        });
    }

    private void initTTS() {
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.JAPANESE);
                tts.setSpeechRate(0.85f);
            }
        });
    }

    private void loadLesson(int number) {
        lessonNumber = number;
        currentLesson = JapaneseGrammarDataManager.getLessonByNumber(this, lessonNumber);

        if (currentLesson == null) {
            Toast.makeText(this, "Không tìm thấy dữ liệu bài " + lessonNumber, Toast.LENGTH_SHORT).show();
            return;
        }

        String level = currentLesson.getLevel();
        tvLevelBadge.setText(level);
        tvGrammarHeaderTitle.setText("Bài " + lessonNumber + " – Ngữ pháp tiếng Nhật");
        tvGrammarHeaderSubtitle.setText("Minna no Nihongo (Bài " + lessonNumber + " / 50)");

        tvLessonBannerTitle.setText(currentLesson.getTitle());
        int pointsCount = currentLesson.getPointsCount();
        tvLessonBannerSummary.setText(level + " • " + pointsCount + " mẫu ngữ pháp");

        adapter = new JapaneseGrammarPointAdapter(this, lessonNumber, (point, position) -> {
            openGrammarPointStudy(point.getPointNumber());
        });
        rvGrammarPoints.setAdapter(adapter);
        adapter.setPoints(currentLesson.getPoints());

        updateProgressUI();

        btnPrevLesson.setEnabled(lessonNumber > 1);
        btnPrevLesson.setAlpha(lessonNumber > 1 ? 1.0f : 0.4f);

        btnNextLesson.setEnabled(lessonNumber < 50);
        btnNextLesson.setAlpha(lessonNumber < 50 ? 1.0f : 0.4f);
    }

    private void updateProgressUI() {
        if (currentLesson == null) return;

        int total = currentLesson.getPointsCount();
        int completed = JapaneseGrammarProgressManager.getCompletedPointsCount(this, lessonNumber);
        int percent = total > 0 ? (int) Math.round((completed * 100.0) / total) : 0;

        tvProgressFraction.setText(completed + "/" + total);
        pbLessonProgress.setProgress(percent);

        if (completed >= total && total > 0) {
            btnContinueStudy.setText("Ôn tập lại bài học ✓");
        } else {
            btnContinueStudy.setText("Tiếp tục học →");
        }

        boolean passedQuiz = JapaneseGrammarProgressManager.isLessonQuizPassed(this, lessonNumber);
        if (passedQuiz) {
            tvComprehensiveStatus.setText("✓ Đã hoàn thành kiểm tra tổng hợp");
        } else {
            tvComprehensiveStatus.setText("Luyện tập tất cả các mẫu câu để củng cố");
        }

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void openGrammarPointStudy(int pointNumber) {
        Intent intent = new Intent(this, JapaneseGrammarPointStudyActivity.class);
        intent.putExtra("lessonNumber", lessonNumber);
        intent.putExtra("pointNumber", pointNumber);
        startActivity(intent);
    }

    private void speakLesson() {
        if (tts != null && currentLesson != null) {
            if (!currentLesson.getPoints().isEmpty()) {
                JapaneseGrammarPoint firstP = currentLesson.getPoints().get(0);
                if (!firstP.getExamples().isEmpty()) {
                    tts.speak(firstP.getExamples().get(0).getJapanese(), TextToSpeech.QUEUE_FLUSH, null, null);
                    return;
                }
            }
            tts.speak(currentLesson.getTitle(), TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateProgressUI();
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
