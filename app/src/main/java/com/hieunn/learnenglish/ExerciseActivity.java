package com.hieunn.learnenglish;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.hieunn.learnenglish.db.GrammarQuizEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciseActivity extends AppCompatActivity {
    private static final int STATE_NEW = 0;
    private static final int STATE_LEARNING = 1;
    private static final int STATE_MASTERED = 2;
    private static final int REQUIRED_CORRECT_STREAK = 2;

    private final List<GrammarQuizEntity> allQuestions = new ArrayList<>();
    private final List<GrammarQuizEntity> queue = new ArrayList<>();
    private final Map<Integer, Integer> questionState = new HashMap<>();
    private final Map<Integer, Integer> correctStreak = new HashMap<>();
    private GrammarQuizEntity currentQuestion;
    private String selectedAnswer = "";
    private CardView selectedCard;
    private boolean answered;
    private CardView[] cards;
    private TextView[] options;
    private TextView tvQuestion, tvQuestionNumber, tvQuestionProgress;
    private TextView tvNewCount, tvLearningCount, tvMasteredCount;
    private ProgressBar progressBar;
    private Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });
        bindViews();
        loadData();
    }

    private void bindViews() {
        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestionProgress = findViewById(R.id.tvQuestionProgress);
        tvNewCount = findViewById(R.id.tvNewCount);
        tvLearningCount = findViewById(R.id.tvLearningCount);
        tvMasteredCount = findViewById(R.id.tvMasteredCount);
        progressBar = findViewById(R.id.progressBar);
        btnCheck = findViewById(R.id.btnCheckAnswer);
        cards = new CardView[]{findViewById(R.id.cardOptionA), findViewById(R.id.cardOptionB), findViewById(R.id.cardOptionC), findViewById(R.id.cardOptionD)};
        options = new TextView[]{findViewById(R.id.tvOptionA), findViewById(R.id.tvOptionB), findViewById(R.id.tvOptionC), findViewById(R.id.tvOptionD)};
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.btnSettings).setOnClickListener(v -> showSettingsDialog());
        for (int i = 0; i < cards.length; i++) {
            int index = i;
            cards[i].setOnClickListener(v -> selectOption(index));
        }
        btnCheck.setOnClickListener(v -> checkAnswer());
    }

    private void loadData() {
        int lessonId = getIntent().getIntExtra("lessonId", -1);
        if (lessonId > 0) {
            AppDatabase db = AppDatabase.getInstance(this);
            allQuestions.addAll(db.grammarQuizDao().getQuizByLessonId(lessonId));
        }
        if (allQuestions.isEmpty()) {
            Toast.makeText(this, "Chưa có câu hỏi nào!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        restartPractice(false);
    }

    private void restartPractice(boolean showMessage) {
        queue.clear();
        questionState.clear();
        correctStreak.clear();
        for (GrammarQuizEntity question : allQuestions) {
            questionState.put(question.id, STATE_NEW);
            correctStreak.put(question.id, 0);
        }
        queue.addAll(allQuestions);
        Collections.shuffle(queue);
        progressBar.setMax(allQuestions.size());
        if (showMessage) Toast.makeText(this, "Đã luyện lại từ đầu!", Toast.LENGTH_SHORT).show();
        nextQuestion();
    }

    private void showSettingsDialog() {
        new AlertDialog.Builder(this).setTitle("Cài đặt")
                .setItems(new String[]{"Luyện lại từ đầu"}, (dialog, which) -> restartPractice(true))
                .setNegativeButton("Đóng", null).show();
    }

    private void selectOption(int index) {
        if (answered || cards[index].getVisibility() != View.VISIBLE) return;
        for (CardView card : cards) card.setCardBackgroundColor(0xFFFFFFFF);
        cards[index].setCardBackgroundColor(0xFFDBEAFE);
        selectedAnswer = options[index].getText().toString();
        selectedCard = cards[index];
    }

    private void checkAnswer() {
        if (answered) {
            nextQuestion();
            return;
        }
        if (selectedAnswer.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn một đáp án!", Toast.LENGTH_SHORT).show();
            return;
        }
        answered = true;
        String correct = currentQuestion.correctAnswer == null ? "" : currentQuestion.correctAnswer.trim();
        boolean isCorrect = selectedAnswer.trim().equalsIgnoreCase(correct);
        int correctIndex = findCorrectOption(correct);
        if (isCorrect) {
            selectedCard.setCardBackgroundColor(0xFFD1FAE5);
            int streak = correctStreak.get(currentQuestion.id) + 1;
            correctStreak.put(currentQuestion.id, streak);
            if (streak >= REQUIRED_CORRECT_STREAK) questionState.put(currentQuestion.id, STATE_MASTERED);
            else {
                questionState.put(currentQuestion.id, STATE_LEARNING);
                requeueCurrent(false);
            }
            Toast.makeText(this, "Chính xác!", Toast.LENGTH_SHORT).show();
            btnCheck.postDelayed(this::nextQuestion, 800);
        } else {
            selectedCard.setCardBackgroundColor(0xFFFEE2E2);
            if (correctIndex >= 0) cards[correctIndex].setCardBackgroundColor(0xFFD1FAE5);
            correctStreak.put(currentQuestion.id, 0);
            questionState.put(currentQuestion.id, STATE_LEARNING);
            requeueCurrent(true);
            Toast.makeText(this, "Chưa đúng. Đáp án đúng là: " + correct, Toast.LENGTH_LONG).show();
            btnCheck.setText("Tiếp tục");
        }
        updateStatus();
    }

    private int findCorrectOption(String correct) {
        for (int i = 0; i < options.length; i++)
            if (options[i].getText().toString().trim().equalsIgnoreCase(correct)) return i;
        return -1;
    }

    private void requeueCurrent(boolean wasWrong) {
        queue.remove(currentQuestion);
        int offset = wasWrong ? 2 + (int) (Math.random() * 3) : Math.max(1, queue.size() / 2);
        queue.add(Math.min(offset, queue.size()), currentQuestion);
    }

    private GrammarQuizEntity pickNextQuestion() {
        for (GrammarQuizEntity q : queue) if (questionState.get(q.id) == STATE_NEW) return q;
        for (GrammarQuizEntity q : queue) if (questionState.get(q.id) == STATE_LEARNING) return q;
        return queue.isEmpty() ? null : queue.get(0);
    }

    private void nextQuestion() {
        queue.removeIf(q -> questionState.get(q.id) == STATE_MASTERED);
        updateStatus();
        if (queue.isEmpty()) {
            showCompletionDialog();
            return;
        }
        currentQuestion = pickNextQuestion();
        answered = false;
        selectedAnswer = "";
        selectedCard = null;
        btnCheck.setText("Kiểm tra");
        tvQuestion.setText(currentQuestion.question);
        tvQuestionNumber.setText(String.valueOf(allQuestions.indexOf(currentQuestion) + 1));
        String[] values = {currentQuestion.optionA, currentQuestion.optionB, currentQuestion.optionC, currentQuestion.optionD};
        for (int i = 0; i < cards.length; i++) {
            String value = values[i];
            options[i].setText(value == null ? "" : value);
            cards[i].setVisibility(value == null || value.trim().isEmpty() ? View.GONE : View.VISIBLE);
            cards[i].setCardBackgroundColor(0xFFFFFFFF);
        }
    }

    private void updateStatus() {
        int newCount = 0, learningCount = 0, masteredCount = 0;
        for (GrammarQuizEntity question : allQuestions) {
            int state = questionState.containsKey(question.id) ? questionState.get(question.id) : STATE_NEW;
            if (state == STATE_NEW) newCount++;
            else if (state == STATE_LEARNING) learningCount++;
            else masteredCount++;
        }
        tvNewCount.setText(String.valueOf(newCount));
        tvLearningCount.setText(String.valueOf(learningCount));
        tvMasteredCount.setText(String.valueOf(masteredCount));
        progressBar.setProgress(masteredCount);
        tvQuestionProgress.setText("Nắm vững: " + masteredCount + "/" + allQuestions.size());
    }

    private void showCompletionDialog() {
        new AlertDialog.Builder(this).setTitle("🎉 Chúc mừng!")
                .setMessage("Bạn đã nắm vững tất cả câu hỏi ngữ pháp trong bài này!")
                .setPositiveButton("Luyện lại", (dialog, which) -> restartPractice(false))
                .setNegativeButton("Hoàn tất", (dialog, which) -> finish())
                .setCancelable(false).show();
    }
}
