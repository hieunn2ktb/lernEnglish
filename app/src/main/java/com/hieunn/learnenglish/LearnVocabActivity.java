package com.hieunn.learnenglish;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.VocabEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class LearnVocabActivity extends AppCompatActivity {

    // Word states
    private static final int STATE_NEW = 0;        // Chưa học
    private static final int STATE_LEARNING = 1;    // Đang học (đã sai hoặc chưa đủ 2 lần đúng)
    private static final int STATE_MASTERED = 2;    // Nắm vững (đúng liên tiếp 2 lần)

    private List<VocabItem> allVocab = new ArrayList<>();        // Toàn bộ từ vựng gốc
    private List<VocabItem> queue = new ArrayList<>();           // Hàng đợi câu hỏi
    private Map<VocabItem, Integer> wordState = new HashMap<>(); // Trạng thái từ
    private Map<VocabItem, Integer> correctStreak = new HashMap<>(); // Số lần đúng liên tiếp
    private Map<VocabItem, Boolean> answeredByMC = new HashMap<>();   // Đã đúng bằng trắc nghiệm?
    private Map<VocabItem, Boolean> answeredByWrite = new HashMap<>(); // Đã đúng bằng viết?
    private Set<VocabItem> starredWords = new HashSet<>();       // Từ đánh dấu sao

    private VocabItem currentWord;
    private int correctPosition = 0;
    private boolean answered = false;
    private boolean isWriteMode = false;
    private boolean starOnlyMode = false;  // Chỉ học từ gắn sao
    private TextToSpeech tts;

    // UI
    private CardView[] cards;
    private TextView[] titles;
    private TextView tvWord, tvCount, tvInstruction, tvQuestionLabel;
    private TextView tvNewCount, tvLearningCount, tvMasteredCount;
    private ProgressBar progressBar;
    private Button btnContinue;
    private ImageButton btnAudio, btnStarWord;
    private View layoutMultipleChoice, layoutWriteMode;
    private EditText etWriteAnswer;
    private TextView tvWriteFeedback;
    private Button btnSubmitWrite;

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

        // Bind views
        tvWord = findViewById(R.id.tvQuestionWord);
        tvCount = findViewById(R.id.tvQuestionCount);
        tvInstruction = findViewById(R.id.tvInstruction);
        tvQuestionLabel = findViewById(R.id.tvQuestionLabel);
        progressBar = findViewById(R.id.progressBar);
        btnContinue = findViewById(R.id.btnContinue);

        tvNewCount = findViewById(R.id.tvNewCount);
        tvLearningCount = findViewById(R.id.tvLearningCount);
        tvMasteredCount = findViewById(R.id.tvMasteredCount);

        cards = new CardView[]{
                findViewById(R.id.cardOptionA),
                findViewById(R.id.cardOptionB),
                findViewById(R.id.cardOptionC),
                findViewById(R.id.cardOptionD)
        };
        titles = new TextView[]{
                findViewById(R.id.tvOptionA),
                findViewById(R.id.tvOptionB),
                findViewById(R.id.tvOptionC),
                findViewById(R.id.tvOptionD)
        };

        for (int i = 0; i < 4; i++) {
            int idx = i;
            cards[i].setOnClickListener(v -> onOptionClicked(idx));
        }

        layoutMultipleChoice = findViewById(R.id.layoutMultipleChoice);
        layoutWriteMode = findViewById(R.id.layoutWriteMode);
        etWriteAnswer = findViewById(R.id.etWriteAnswer);
        tvWriteFeedback = findViewById(R.id.tvWriteFeedback);
        btnSubmitWrite = findViewById(R.id.btnSubmitWrite);
        btnAudio = findViewById(R.id.btnAudio);
        btnStarWord = findViewById(R.id.btnStarWord);

        // Listeners
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
            }
        });

        if (btnAudio != null) {
            btnAudio.setOnClickListener(v -> {
                if (currentWord != null && tts != null) {
                    tts.speak(currentWord.getEnglishWord(), TextToSpeech.QUEUE_FLUSH, null, null);
                }
            });
        }

        btnStarWord.setOnClickListener(v -> toggleStar());
        btnSubmitWrite.setOnClickListener(v -> onWriteSubmit());
        btnContinue.setOnClickListener(v -> nextQuestion());

        // Settings button
        findViewById(R.id.btnSettings).setOnClickListener(v -> showSettingsDialog());

        loadData();
    }

    // ======================== SETTINGS DIALOG ========================

    private void showSettingsDialog() {
        String[] items;
        if (starredWords.isEmpty()) {
            items = new String[]{"Học lại từ đầu"};
        } else {
            items = new String[]{
                    "Học lại từ đầu",
                    starOnlyMode ? "Học tất cả từ" : "Chỉ học từ gắn ⭐"
            };
        }

        new AlertDialog.Builder(this)
                .setTitle("Cài đặt")
                .setItems(items, (dialog, which) -> {
                    if (which == 0) {
                        restartLearn();
                    } else if (which == 1) {
                        starOnlyMode = !starOnlyMode;
                        restartLearn();
                    }
                })
                .setNegativeButton("Đóng", null)
                .show();
    }

    private void restartLearn() {
        queue.clear();
        wordState.clear();
        correctStreak.clear();
        answeredByMC.clear();
        answeredByWrite.clear();

        List<VocabItem> source = starOnlyMode ? new ArrayList<>(starredWords) : allVocab;

        for (VocabItem item : source) {
            wordState.put(item, STATE_NEW);
            correctStreak.put(item, 0);
            answeredByMC.put(item, false);
            answeredByWrite.put(item, false);
        }
        queue.addAll(source);
        Collections.shuffle(queue);

        progressBar.setMax(source.size());
        updateStatusCounts();

        if (queue.isEmpty()) {
            Toast.makeText(this, starOnlyMode ? "Không có từ nào được gắn sao" : "Không có từ vựng", Toast.LENGTH_SHORT).show();
            if (starOnlyMode) {
                starOnlyMode = false;
                restartLearn();
            } else {
                finish();
            }
            return;
        }

        Toast.makeText(this, starOnlyMode ? "Chế độ: Chỉ từ gắn ⭐" : "Đã học lại từ đầu!", Toast.LENGTH_SHORT).show();
        nextQuestion();
    }

    // ======================== STAR ========================

    private void toggleStar() {
        if (currentWord == null) return;
        if (starredWords.contains(currentWord)) {
            starredWords.remove(currentWord);
            btnStarWord.setColorFilter(0xFF9CA3AF);
        } else {
            starredWords.add(currentWord);
            btnStarWord.setColorFilter(0xFFFBBF24); // Gold
        }
    }

    // ======================== DATA ========================

    private void loadData() {
        int lessonId = getIntent().getIntExtra("lessonId", -1);
        if (lessonId > 0) {
            AppDatabase db = AppDatabase.getInstance(this);
            List<VocabEntity> dbItems = db.vocabDao().getVocabByLessonId(lessonId);
            for (VocabEntity e : dbItems) {
                allVocab.add(new VocabItem(e.english, e.vietnamese, e.phonetic, e.wordType));
            }
        }

        if (allVocab.isEmpty()) {
            Toast.makeText(this, "Không có từ vựng để học", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize all words as NEW
        for (VocabItem item : allVocab) {
            wordState.put(item, STATE_NEW);
            correctStreak.put(item, 0);
            answeredByMC.put(item, false);
            answeredByWrite.put(item, false);
        }
        queue.addAll(allVocab);
        Collections.shuffle(queue);
        progressBar.setMax(allVocab.size());

        nextQuestion();
    }

    // ======================== STATUS COUNTS ========================

    private void updateStatusCounts() {
        int newCount = 0, learningCount = 0, masteredCount = 0;
        List<VocabItem> source = starOnlyMode ? new ArrayList<>(starredWords) : allVocab;
        for (VocabItem item : source) {
            Integer state = wordState.get(item);
            if (state == null || state == STATE_NEW) newCount++;
            else if (state == STATE_LEARNING) learningCount++;
            else if (state == STATE_MASTERED) masteredCount++;
        }
        tvNewCount.setText(String.valueOf(newCount));
        tvLearningCount.setText(String.valueOf(learningCount));
        tvMasteredCount.setText(String.valueOf(masteredCount));

        progressBar.setProgress(masteredCount);
        tvCount.setText("Nắm vững: " + masteredCount + " / " + source.size());
    }

    // ======================== PICK NEXT WORD ========================

    private VocabItem pickNextWord() {
        // Priority 1: NEW words (chưa học)
        for (VocabItem item : queue) {
            Integer state = wordState.get(item);
            if (state != null && state == STATE_NEW) {
                return item;
            }
        }
        // Priority 2: LEARNING words (đang học, đã sai)
        for (VocabItem item : queue) {
            Integer state = wordState.get(item);
            if (state != null && state == STATE_LEARNING) {
                return item;
            }
        }
        // Fallback: first in queue
        return queue.isEmpty() ? null : queue.get(0);
    }

    // ======================== NEXT QUESTION ========================

    private void nextQuestion() {
        // Remove mastered words from queue
        List<VocabItem> toRemove = new ArrayList<>();
        for (VocabItem item : queue) {
            Integer state = wordState.get(item);
            if (state != null && state == STATE_MASTERED) {
                toRemove.add(item);
            }
        }
        queue.removeAll(toRemove);

        updateStatusCounts();

        if (queue.isEmpty()) {
            showCompletionDialog();
            return;
        }

        answered = false;
        btnContinue.setVisibility(View.GONE);

        currentWord = pickNextWord();

        // Update star icon
        if (starredWords.contains(currentWord)) {
            btnStarWord.setColorFilter(0xFFFBBF24);
        } else {
            btnStarWord.setColorFilter(0xFF9CA3AF);
        }

        // Decide mode: 
        // - First encounter (NEW): always Multiple Choice
        // - LEARNING with MC done but not Write: force Write
        // - LEARNING with Write done but not MC: force MC
        // - Otherwise: random 40% Write / 60% MC
        Integer state = wordState.get(currentWord);
        Boolean mcDone = answeredByMC.get(currentWord);
        Boolean writeDone = answeredByWrite.get(currentWord);

        if (state != null && state == STATE_NEW) {
            isWriteMode = false; // First time: always MC
        } else if (mcDone != null && mcDone && (writeDone == null || !writeDone)) {
            isWriteMode = true; // Force write to get second correct
        } else if (writeDone != null && writeDone && (mcDone == null || !mcDone)) {
            isWriteMode = false; // Force MC to get second correct
        } else {
            isWriteMode = Math.random() < 0.4;
        }

        if (isWriteMode) {
            showWriteMode();
        } else {
            showMultipleChoiceMode();
        }
    }

    // ======================== SHOW MODES ========================

    private void showWriteMode() {
        layoutMultipleChoice.setVisibility(View.GONE);
        layoutWriteMode.setVisibility(View.VISIBLE);

        tvQuestionLabel.setText("Định nghĩa");
        tvInstruction.setText("Gõ thuật ngữ tiếng Anh tương ứng");
        tvWord.setText(currentWord.getVietnameseMeaning());

        if (btnAudio != null) btnAudio.setVisibility(View.GONE);
        etWriteAnswer.setText("");
        tvWriteFeedback.setVisibility(View.GONE);
    }

    private void showMultipleChoiceMode() {
        layoutMultipleChoice.setVisibility(View.VISIBLE);
        layoutWriteMode.setVisibility(View.GONE);

        tvQuestionLabel.setText("Thuật ngữ");
        tvInstruction.setText("Chọn định nghĩa đúng");
        tvWord.setText(currentWord.getEnglishWord());

        if (btnAudio != null) btnAudio.setVisibility(View.VISIBLE);
        if (tts != null) {
            tts.speak(currentWord.getEnglishWord(), TextToSpeech.QUEUE_FLUSH, null, null);
        }

        for (int i = 0; i < 4; i++) {
            cards[i].setCardBackgroundColor(0xFFFFFFFF);
            titles[i].setTextColor(0xFF1F2937);
        }

        List<VocabItem> source = starOnlyMode ? new ArrayList<>(starredWords) : allVocab;
        List<VocabItem> wrongChoices = new ArrayList<>(source);
        wrongChoices.remove(currentWord);
        Collections.shuffle(wrongChoices);

        correctPosition = (int) (Math.random() * 4);
        int wrongIdx = 0;

        for (int i = 0; i < 4; i++) {
            if (i == correctPosition) {
                titles[i].setText(currentWord.getVietnameseMeaning());
            } else {
                if (wrongIdx < wrongChoices.size()) {
                    titles[i].setText(wrongChoices.get(wrongIdx).getVietnameseMeaning());
                    wrongIdx++;
                } else {
                    titles[i].setText("Lựa chọn khác " + i);
                }
            }
        }
    }

    // ======================== ANSWER HANDLERS ========================

    private void onOptionClicked(int idx) {
        if (answered) return;
        answered = true;

        // Mark word as at least LEARNING
        if (wordState.get(currentWord) != null && wordState.get(currentWord) == STATE_NEW) {
            wordState.put(currentWord, STATE_LEARNING);
        }

        if (idx == correctPosition) {
            cards[idx].setCardBackgroundColor(0xFFD1FAE5);
            titles[idx].setTextColor(0xFF065F46);

            onCorrectAnswer(false);
            tvWord.postDelayed(this::nextQuestion, 800);
        } else {
            cards[idx].setCardBackgroundColor(0xFFFEE2E2);
            titles[idx].setTextColor(0xFF991B1B);
            cards[correctPosition].setCardBackgroundColor(0xFFD1FAE5);
            titles[correctPosition].setTextColor(0xFF065F46);

            onWrongAnswer();
            btnContinue.setVisibility(View.VISIBLE);
        }
    }

    private void onWriteSubmit() {
        if (answered) return;
        answered = true;

        // Mark word as at least LEARNING
        if (wordState.get(currentWord) != null && wordState.get(currentWord) == STATE_NEW) {
            wordState.put(currentWord, STATE_LEARNING);
        }

        String answer = etWriteAnswer.getText().toString().trim();
        tvWriteFeedback.setVisibility(View.VISIBLE);

        if (answer.equalsIgnoreCase(currentWord.getEnglishWord())) {
            tvWriteFeedback.setText("Chính xác!");
            tvWriteFeedback.setTextColor(0xFF065F46);
            if (tts != null) tts.speak(currentWord.getEnglishWord(), TextToSpeech.QUEUE_FLUSH, null, null);

            onCorrectAnswer(true);
            tvWord.postDelayed(this::nextQuestion, 800);
        } else {
            tvWriteFeedback.setText("Chưa đúng. Đáp án đúng là:\n" + currentWord.getEnglishWord());
            tvWriteFeedback.setTextColor(0xFF991B1B);
            if (tts != null) tts.speak(currentWord.getEnglishWord(), TextToSpeech.QUEUE_FLUSH, null, null);

            onWrongAnswer();
            btnContinue.setVisibility(View.VISIBLE);
        }
    }

    // ======================== CORE ALGORITHM ========================

    private void onCorrectAnswer(boolean wasWrite) {
        int streak = correctStreak.containsKey(currentWord) ? correctStreak.get(currentWord) : 0;
        streak++;
        correctStreak.put(currentWord, streak);

        if (wasWrite) {
            answeredByWrite.put(currentWord, true);
        } else {
            answeredByMC.put(currentWord, true);
        }

        Boolean mcDone = answeredByMC.get(currentWord);
        Boolean writeDone = answeredByWrite.get(currentWord);

        // Nắm vững khi đúng 2 lần (cả MC lẫn Write)
        if (streak >= 2 && (mcDone != null && mcDone) && (writeDone != null && writeDone)) {
            wordState.put(currentWord, STATE_MASTERED);
        } else {
            wordState.put(currentWord, STATE_LEARNING);
            // Move to later in queue so other words get a chance
            queue.remove(currentWord);
            // Insert at a random position in the second half of the queue
            int insertPos = Math.max(1, queue.size() / 2) + (int) (Math.random() * Math.max(1, queue.size() / 2));
            insertPos = Math.min(insertPos, queue.size());
            queue.add(insertPos, currentWord);
        }
    }

    private void onWrongAnswer() {
        // Reset streak
        correctStreak.put(currentWord, 0);
        wordState.put(currentWord, STATE_LEARNING);

        // Move to back of queue but will reappear soon (2-4 positions later)
        queue.remove(currentWord);
        int insertPos = Math.min(2 + (int) (Math.random() * 3), queue.size());
        queue.add(insertPos, currentWord);
    }

    // ======================== COMPLETION ========================

    private void showCompletionDialog() {
        updateStatusCounts();
        new AlertDialog.Builder(this)
                .setTitle("🎉 Chúc mừng!")
                .setMessage("Bạn đã nắm vững tất cả từ vựng trong bộ thẻ này!")
                .setPositiveButton("Học lại", (d, w) -> restartLearn())
                .setNegativeButton("Hoàn tất", (d, w) -> finish())
                .setCancelable(false)
                .show();
    }

    // ======================== LIFECYCLE ========================

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
