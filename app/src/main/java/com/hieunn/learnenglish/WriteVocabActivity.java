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
    
    private VocabItem currentWord;
    private boolean isShowingCorrection = false;

    private TextView tvVietnameseMeaning;
    private EditText etAnswer;
    private TextView tvQuestionCount;
    private ProgressBar progressBar;
    private Button btnSubmit;
    private LinearLayout llFeedback;
    private TextView tvCorrectAnswer;

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

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        btnSubmit.setOnClickListener(v -> onSubmitClicked());

        loadData();
    }

    private void loadData() {
        int lessonId = getIntent().getIntExtra("lessonId", -1);
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
        etAnswer.setText("");
        etAnswer.setHint("Gõ tiếng Anh");
        etAnswer.setBackgroundResource(R.drawable.bg_card_rounded_quizlet);
        llFeedback.setVisibility(View.GONE);
        btnSubmit.setText("Kiểm tra");

        currentWord = learningList.get(0);
        tvVietnameseMeaning.setText(currentWord.getVietnameseMeaning());

        tvQuestionCount.setText(totalMastered + " / " + originalVocab.size());
        progressBar.setProgress(totalMastered);
        
        etAnswer.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etAnswer, InputMethodManager.SHOW_IMPLICIT);
    }

    private void onSubmitClicked() {
        String answer = etAnswer.getText().toString().trim();
        String expected = currentWord.getEnglishWord().trim();

        if (isShowingCorrection) {
            if (answer.equalsIgnoreCase(expected)) {
                learningList.remove(0);
                learningList.add(currentWord);
                etAnswer.setBackgroundColor(0xFFD1FAE5);
                etAnswer.postDelayed(this::nextQuestion, 300);
            } else {
                Toast.makeText(this, "Hãy chép lại thật cẩn thận: " + expected, Toast.LENGTH_SHORT).show();
            }
            return;
        }

        if (answer.equalsIgnoreCase(expected)) {
            learningList.remove(0);
            totalMastered++;
            etAnswer.setBackgroundColor(0xFFD1FAE5);
            etAnswer.postDelayed(this::nextQuestion, 300);
        } else {
            isShowingCorrection = true;
            llFeedback.setVisibility(View.VISIBLE);
            tvCorrectAnswer.setText(expected);
            btnSubmit.setText("Tiếp tục (Chép lại từ)");
            etAnswer.setText("");
            etAnswer.setHint("Hãy chép lại từ trên");
            etAnswer.setBackgroundColor(0xFFFEE2E2);
        }
    }
}
