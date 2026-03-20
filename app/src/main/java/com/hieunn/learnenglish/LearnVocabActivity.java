package com.hieunn.learnenglish;

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

import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.VocabEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class LearnVocabActivity extends AppCompatActivity {
    private List<VocabItem> learningList = new ArrayList<>();
    private List<VocabItem> masteredList = new ArrayList<>();
    private List<VocabItem> originalVocab = new ArrayList<>();
    
    private VocabItem currentWord;
    private int correctPosition = 0;
    private boolean answered = false;
    private TextToSpeech tts;

    private CardView[] cards;
    private TextView[] titles;
    private TextView tvWord;
    private TextView tvCount;
    private ProgressBar progressBar;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_learn_vocab);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvWord = findViewById(R.id.tvQuestionWord);
        tvCount = findViewById(R.id.tvQuestionCount);
        progressBar = findViewById(R.id.progressBar);
        btnContinue = findViewById(R.id.btnContinue);

        cards = new CardView[] {
                findViewById(R.id.cardOptionA),
                findViewById(R.id.cardOptionB),
                findViewById(R.id.cardOptionC),
                findViewById(R.id.cardOptionD)
        };

        titles = new TextView[] {
                findViewById(R.id.tvOptionA),
                findViewById(R.id.tvOptionB),
                findViewById(R.id.tvOptionC),
                findViewById(R.id.tvOptionD)
        };

        for (int i = 0; i < 4; i++) {
            int idx = i;
            cards[i].setOnClickListener(v -> onOptionClicked(idx));
        }

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.btnClose).setOnClickListener(v -> finish());

        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
            }
        });

        ImageButton btnAudio = findViewById(R.id.btnAudio);
        if(btnAudio != null) {
            btnAudio.setOnClickListener(v -> {
                if (currentWord != null && tts != null) {
                    tts.speak(currentWord.getEnglishWord(), TextToSpeech.QUEUE_FLUSH, null, null);
                }
            });
        }

        btnContinue.setOnClickListener(v -> nextQuestion());

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
            Toast.makeText(this, "Chúc mừng! Bạn đã nắm vững tất cả các từ.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        answered = false;
        btnContinue.setVisibility(View.GONE);
        
        currentWord = learningList.get(0);
        tvWord.setText(currentWord.getEnglishWord());
        
        if (tts != null) {
            tts.speak(currentWord.getEnglishWord(), TextToSpeech.QUEUE_FLUSH, null, null);
        }

        tvCount.setText("Đã thuộc: " + masteredList.size() + " / " + originalVocab.size());
        progressBar.setProgress(masteredList.size());

        for (int i = 0; i < 4; i++) {
            cards[i].setCardBackgroundColor(0xFFFFFFFF);
            titles[i].setTextColor(0xFF1F2937);
        }

        // Prepare wrong choices
        List<VocabItem> wrongChoices = new ArrayList<>(originalVocab);
        wrongChoices.remove(currentWord);
        Collections.shuffle(wrongChoices);

        correctPosition = (int) (Math.random() * 4);
        int wrongIdx = 0;

        for (int i = 0; i < 4; i++) {
            if (i == correctPosition) {
                titles[i].setText(currentWord.getVietnameseMeaning());
            } else {
                if(wrongIdx < wrongChoices.size()){
                    titles[i].setText(wrongChoices.get(wrongIdx).getVietnameseMeaning());
                    wrongIdx++;
                } else {
                    titles[i].setText("Lựa chọn khác " + i);
                }
            }
        }
    }

    private void onOptionClicked(int idx) {
        if (answered) return;
        answered = true;

        if (idx == correctPosition) {
            // Correct - move to mastered if we let it
            cards[idx].setCardBackgroundColor(0xFFD1FAE5); // Green
            titles[idx].setTextColor(0xFF065F46);

            learningList.remove(currentWord);
            masteredList.add(currentWord);
            
            // Auto advance
            tvWord.postDelayed(this::nextQuestion, 800);
        } else {
            // Wrong
            cards[idx].setCardBackgroundColor(0xFFFEE2E2); // Red
            titles[idx].setTextColor(0xFF991B1B);
            
            cards[correctPosition].setCardBackgroundColor(0xFFD1FAE5);
            titles[correctPosition].setTextColor(0xFF065F46);

            // Shove current word back to the end of learning queue
            learningList.remove(currentWord);
            learningList.add(currentWord); // re-add at the end

            btnContinue.setVisibility(View.VISIBLE);
        }
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
