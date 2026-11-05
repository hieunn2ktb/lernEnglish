package com.hieunn.learnenglish;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.VocabEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WriteVocabActivity extends AppCompatActivity {
    private List<VocabItem> learningList = new ArrayList<>();
    private List<VocabItem> originalVocab = new ArrayList<>();
    private int totalMastered = 0;
    private int lessonId = -1;
    
    private VocabItem currentWord;
    private boolean isShowingCorrection = false;

    private TextView tvVietnameseMeaning;
    private EditText etAnswer;
    private TextView tvQuestionCount;
    private ProgressBar progressBar;
    private Button btnSubmit;
    private LinearLayout llFeedback;
    private TextView tvCorrectAnswer;
    private TextView tvCorrectionHint;
    private TextView btnSkipQuestion;
    private int correctionAttempts = 0;
    private RomajiHelper.ImeController imeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_write_vocab);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvVietnameseMeaning = findViewById(R.id.tvVietnameseMeaning);
        etAnswer = findViewById(R.id.etAnswer);
        tvQuestionCount = findViewById(R.id.tvQuestionCount);
        progressBar = findViewById(R.id.progressBar);
        btnSubmit = findViewById(R.id.btnSubmit);
        llFeedback = findViewById(R.id.llFeedback);
        tvCorrectAnswer = findViewById(R.id.tvCorrectAnswer);
        tvCorrectionHint = findViewById(R.id.tvCorrectionHint);
        btnSkipQuestion = findViewById(R.id.btnSkipQuestion);

        if (btnSkipQuestion != null) {
            btnSkipQuestion.setOnClickListener(v -> skipCurrentQuestion());
        }

        View layoutImeBar = findViewById(R.id.layoutImeBar);
        Button btnImeMode = findViewById(R.id.btnImeMode);
        LinearLayout layoutCandidates = findViewById(R.id.layoutCandidates);

        imeController = new RomajiHelper.ImeController(
                this,
                etAnswer,
                layoutImeBar,
                btnImeMode,
                layoutCandidates,
                () -> currentWord != null && VocabItem.isJapaneseText(currentWord.getEnglishWord())
        );

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        btnSubmit.setOnClickListener(v -> onSubmitClicked());

        loadData();
    }

    private void loadData() {
        lessonId = getIntent().getIntExtra("lessonId", -1);
        if (lessonId > 0) {
            AppDatabase db = AppDatabase.getInstance(this);
            List<VocabEntity> dbItems = db.vocabDao().getVocabByLessonId(lessonId);
            for (VocabEntity e : dbItems) {
                originalVocab.add(new VocabItem(e.english, e.vietnamese, e.phonetic, e.wordType));
            }
            learningList.addAll(originalVocab);
            Collections.shuffle(learningList);
        }

        if (learningList.isEmpty()) {
            Toast.makeText(this, "Không có từ vựng để học", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        progressBar.setMax(originalVocab.size());
        nextQuestion();
    }

    private void nextQuestion() {
        if (learningList.isEmpty()) {
            Toast.makeText(this, "Chúc mừng! Bạn đã hoàn thành phần Viết.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        isShowingCorrection = false;
        correctionAttempts = 0;
        etAnswer.setText("");
        currentWord = learningList.get(0);
        if (VocabItem.isJapaneseText(currentWord.getEnglishWord())) {
            etAnswer.setHint("Gõ Romaji hoặc tiếng Nhật");
        } else {
            etAnswer.setHint("Gõ tiếng Anh");
        }
        if (imeController != null) {
            imeController.refreshVisibility();
        }
        etAnswer.setBackgroundResource(R.drawable.bg_card_rounded_quizlet);
        llFeedback.setVisibility(View.GONE);
        if (btnSkipQuestion != null) {
            btnSkipQuestion.setVisibility(View.GONE);
        }
        if (tvCorrectionHint != null) {
            tvCorrectionHint.setText("Hãy gõ lại để tiếp tục!");
            tvCorrectionHint.setTextColor(0xFF4B5563);
        }
        btnSubmit.setText("Kiểm tra");

        tvVietnameseMeaning.setText(currentWord.getVietnameseMeaning());

        tvQuestionCount.setText(totalMastered + " / " + originalVocab.size());
        progressBar.setProgress(totalMastered);
        
        etAnswer.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etAnswer, InputMethodManager.SHOW_IMPLICIT);
    }

    private void skipCurrentQuestion() {
        if (!learningList.isEmpty()) {
            VocabItem skipped = learningList.remove(0);
            learningList.add(skipped);
        }
        nextQuestion();
    }

    private void onSubmitClicked() {
        String answer = (imeController != null) ? imeController.finalizeAnswer() : etAnswer.getText().toString().trim();
        String expected = currentWord.getEnglishWord().trim();

        if (isShowingCorrection) {
            if (VocabItem.isAnswerMatching(answer, expected, currentWord.getPhonetic())) {
                learningList.remove(0);
                learningList.add(currentWord);
                etAnswer.setBackgroundColor(0xFFD1FAE5);
                etAnswer.postDelayed(this::nextQuestion, 300);
            } else {
                correctionAttempts++;
                if (answer.isEmpty() || correctionAttempts >= 2) {
                    skipCurrentQuestion();
                } else {
                    String hint = "Hãy gõ đúng: " + expected;
                    if (currentWord.getPhonetic() != null && !currentWord.getPhonetic().isEmpty()) {
                        hint += " (Romaji: " + currentWord.getPhonetic() + ")";
                    }
                    if (tvCorrectionHint != null) {
                        tvCorrectionHint.setText(hint + "\n(Hoặc bấm \"Bỏ qua\" bên dưới để tiếp tục)");
                        tvCorrectionHint.setTextColor(0xFFDC2626);
                    }
                    if (btnSkipQuestion != null) {
                        btnSkipQuestion.setVisibility(View.VISIBLE);
                    }
                    Toast.makeText(this, hint, Toast.LENGTH_SHORT).show();
                }
            }
            return;
        }

        if (VocabItem.isAnswerMatching(answer, expected, currentWord.getPhonetic())) {
            learningList.remove(0);
            totalMastered++;
            LessonProgressManager.recordVocabAnswer(this, lessonId, true, totalMastered);
            etAnswer.setBackgroundColor(0xFFD1FAE5);
            etAnswer.postDelayed(this::nextQuestion, 300);
        } else {
            LessonProgressManager.recordVocabAnswer(this, lessonId, false, totalMastered);
            isShowingCorrection = true;
            correctionAttempts = 0;
            llFeedback.setVisibility(View.VISIBLE);

            String displayExpected = expected;
            if (currentWord.getPhonetic() != null && !currentWord.getPhonetic().isEmpty()) {
                displayExpected += "  [" + currentWord.getPhonetic() + "]";
            }
            tvCorrectAnswer.setText(displayExpected);

            if (tvCorrectionHint != null) {
                tvCorrectionHint.setText("Hãy gõ lại từ trên để tiếp tục!");
                tvCorrectionHint.setTextColor(0xFF4B5563);
            }
            if (btnSkipQuestion != null) {
                btnSkipQuestion.setVisibility(View.VISIBLE);
            }

            btnSubmit.setText("Tiếp tục (Chép lại từ)");
            etAnswer.setText("");
            etAnswer.setHint("Hãy chép lại từ trên");
            etAnswer.setBackgroundColor(0xFFFEE2E2);
        }
    }
}
