package com.hieunn.learnenglish;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class JapaneseGrammarPointStudyActivity extends AppCompatActivity {

    private int lessonNumber = 1;
    private int pointNumber = 1;
    private JapaneseGrammarLesson currentLesson;
    private JapaneseGrammarPoint currentPoint;
    private TextToSpeech tts;

    private TextView tvPointHeaderIndex;
    private TextView tvPointHeaderLesson;
    private ImageButton btnPrevPoint;
    private ImageButton btnNextPoint;
    private TextView tvStudyPointTitle;
    private TextView tvStudyPointMeaning;
    private TextView tvStudyStructure;
    private TextView tvStudyExplanation;
    private LinearLayout llExamplesContainer;
    private CardView cardStudyNotes;
    private TextView tvStudyNotes;
    private Button btnPracticeNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_japanese_grammar_point_study);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lessonNumber = getIntent().getIntExtra("lessonNumber", 1);
        pointNumber = getIntent().getIntExtra("pointNumber", 1);

        initViews();
        initTTS();
        loadPoint(lessonNumber, pointNumber);
    }

    private void initViews() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        tvPointHeaderIndex = findViewById(R.id.tvPointHeaderIndex);
        tvPointHeaderLesson = findViewById(R.id.tvPointHeaderLesson);
        btnPrevPoint = findViewById(R.id.btnPrevPoint);
        btnNextPoint = findViewById(R.id.btnNextPoint);
        tvStudyPointTitle = findViewById(R.id.tvStudyPointTitle);
        tvStudyPointMeaning = findViewById(R.id.tvStudyPointMeaning);
        tvStudyStructure = findViewById(R.id.tvStudyStructure);
        tvStudyExplanation = findViewById(R.id.tvStudyExplanation);
        llExamplesContainer = findViewById(R.id.llExamplesContainer);
        cardStudyNotes = findViewById(R.id.cardStudyNotes);
        tvStudyNotes = findViewById(R.id.tvStudyNotes);
        btnPracticeNow = findViewById(R.id.btnPracticeNow);

        btnBack.setOnClickListener(v -> finish());

        btnPrevPoint.setOnClickListener(v -> {
            if (pointNumber > 1) {
                loadPoint(lessonNumber, pointNumber - 1);
            }
        });

        btnNextPoint.setOnClickListener(v -> {
            if (currentLesson != null && pointNumber < currentLesson.getPointsCount()) {
                loadPoint(lessonNumber, pointNumber + 1);
            }
        });

        btnPracticeNow.setOnClickListener(v -> {
            Intent intent = new Intent(this, JapaneseGrammarPracticeActivity.class);
            intent.putExtra("lessonNumber", lessonNumber);
            intent.putExtra("pointNumber", pointNumber);
            intent.putExtra("isComprehensive", false);
            startActivity(intent);
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

    private void loadPoint(int lNum, int pNum) {
        lessonNumber = lNum;
        pointNumber = pNum;

        currentLesson = JapaneseGrammarDataManager.getLessonByNumber(this, lessonNumber);
        if (currentLesson == null) {
            Toast.makeText(this, "Không tìm thấy bài học", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        currentPoint = currentLesson.getPointByNumber(pointNumber);
        if (currentPoint == null) {
            Toast.makeText(this, "Không tìm thấy điểm ngữ pháp", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        int total = currentLesson.getPointsCount();
        String idxStr = String.format(Locale.getDefault(), "%02d/%02d", pointNumber, total);
        tvPointHeaderIndex.setText("NGỮ PHÁP " + idxStr + " • " + currentPoint.getLevelLabel());
        tvPointHeaderLesson.setText("Bài " + lessonNumber + " – " + currentLesson.getTitle());

        tvStudyPointTitle.setText(currentPoint.getTitle());
        tvStudyPointMeaning.setText(currentPoint.getMeaning());

        tvStudyStructure.setText(currentPoint.getStructure());
        tvStudyExplanation.setText(currentPoint.getExplanation());

        // Populate examples
        llExamplesContainer.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);
        for (GrammarExample ex : currentPoint.getExamples()) {
            View exView = inflater.inflate(R.layout.item_grammar_example_card, llExamplesContainer, false);
            TextView tvLabel = exView.findViewById(R.id.tvExampleLabel);
            TextView tvJp = exView.findViewById(R.id.tvJapaneseSentence);
            TextView tvRom = exView.findViewById(R.id.tvRomajiSentence);
            TextView tvVn = exView.findViewById(R.id.tvVietnameseSentence);
            ImageButton btnSpeak = exView.findViewById(R.id.btnSpeakExample);

            tvLabel.setText(ex.getLabel());
            tvJp.setText(ex.getJapanese());
            tvRom.setText(ex.getRomaji());
            tvVn.setText(ex.getVietnamese());

            btnSpeak.setOnClickListener(v -> {
                if (tts != null) {
                    tts.speak(ex.getJapanese(), TextToSpeech.QUEUE_FLUSH, null, null);
                }
            });

            llExamplesContainer.addView(exView);
        }

        if (currentPoint.getNotes() != null && !currentPoint.getNotes().trim().isEmpty()) {
            cardStudyNotes.setVisibility(View.VISIBLE);
            tvStudyNotes.setText(currentPoint.getNotes());
        } else {
            cardStudyNotes.setVisibility(View.GONE);
        }

        btnPrevPoint.setEnabled(pointNumber > 1);
        btnPrevPoint.setAlpha(pointNumber > 1 ? 1.0f : 0.3f);

        btnNextPoint.setEnabled(pointNumber < total);
        btnNextPoint.setAlpha(pointNumber < total ? 1.0f : 0.3f);
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
