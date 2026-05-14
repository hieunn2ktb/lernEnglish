package com.hieunn.learnenglish;

import android.os.Bundle;
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
import com.hieunn.learnenglish.db.GrammarQuizEntity;

import java.util.List;

public class ExerciseActivity extends AppCompatActivity {

    private String[][] questions;
    private int currentQuestion = 0;
    private String selectedAnswer = "";
    private CardView selectedCard = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Luôn load từ DB
        int lessonId = getIntent().getIntExtra("lessonId", -1);

        if (lessonId > 0) {
            AppDatabase db = AppDatabase.getInstance(this);
            List<GrammarQuizEntity> dbQuiz = db.grammarQuizDao().getQuizByLessonId(lessonId);
            questions = new String[dbQuiz.size()][6];
            for (int i = 0; i < dbQuiz.size(); i++) {
                GrammarQuizEntity q = dbQuiz.get(i);
                questions[i] = new String[] { q.question, q.optionA, q.optionB, q.optionC, q.optionD, q.correctAnswer };
            }
        } else {
            questions = new String[0][6];
        }

        if (questions.length == 0) {
            Toast.makeText(this, "Chưa có câu hỏi nào!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        CardView cardA = findViewById(R.id.cardOptionA);
        CardView cardB = findViewById(R.id.cardOptionB);
        CardView cardC = findViewById(R.id.cardOptionC);
        CardView cardD = findViewById(R.id.cardOptionD);

        TextView tvA = findViewById(R.id.tvOptionA);
        TextView tvB = findViewById(R.id.tvOptionB);
        TextView tvC = findViewById(R.id.tvOptionC);
        TextView tvD = findViewById(R.id.tvOptionD);

        CardView[] cards = { cardA, cardB, cardC, cardD };
        TextView[] options = { tvA, tvB, tvC, tvD };

        for (int i = 0; i < cards.length; i++) {
            int idx = i;
            cards[i].setOnClickListener(v -> {
                for (CardView c : cards)
                    c.setCardBackgroundColor(0xFFFFFFFF);
                cards[idx].setCardBackgroundColor(0xFFDBEAFE);
                selectedAnswer = options[idx].getText().toString();
                selectedCard = cards[idx];
            });
        }

        Button btnCheck = findViewById(R.id.btnCheckAnswer);
        btnCheck.setOnClickListener(v -> {
            if (selectedAnswer.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn một đáp án!", Toast.LENGTH_SHORT).show();
                return;
            }
            String correct = questions[currentQuestion][5];
            if (selectedAnswer.equals(correct)) {
                if (selectedCard != null)
                    selectedCard.setCardBackgroundColor(0xFFD1FAE5);
                Toast.makeText(this, "Chính xác!", Toast.LENGTH_SHORT).show();
            } else {
                if (selectedCard != null)
                    selectedCard.setCardBackgroundColor(0xFFFEE2E2);
                Toast.makeText(this, "Sai rồi! Đáp án đúng là: " + correct, Toast.LENGTH_SHORT).show();
            }
            v.postDelayed(() -> {
                currentQuestion++;
                if (currentQuestion < questions.length) {
                    loadQuestion(options, cards);
                } else {
                    Toast.makeText(this, "Bạn đã hoàn thành tất cả câu hỏi!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }, 1500);
        });

        loadQuestion(options, cards);
    }

    private void loadQuestion(TextView[] options, CardView[] cards) {
        String[] q = questions[currentQuestion];
        TextView tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestion.setText(q[0]);
        TextView tvNumber = findViewById(R.id.tvQuestionNumber);
        tvNumber.setText(String.valueOf(currentQuestion + 1));
        for (int i = 0; i < 4; i++) {
            options[i].setText(q[i + 1]);
            if (q[i + 1] == null || q[i + 1].trim().isEmpty()) {
                cards[i].setVisibility(View.GONE);
            } else {
                cards[i].setVisibility(View.VISIBLE);
            }
        }
        for (CardView c : cards)
            c.setCardBackgroundColor(0xFFFFFFFF);
        selectedAnswer = "";
        selectedCard = null;
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress((currentQuestion + 1) * 100 / questions.length);
        TextView tvProgress = findViewById(R.id.tvQuestionProgress);
        tvProgress.setText((currentQuestion + 1) + "/" + questions.length);
    }
}
