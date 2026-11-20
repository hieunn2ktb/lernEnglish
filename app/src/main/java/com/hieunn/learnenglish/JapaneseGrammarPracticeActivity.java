package com.hieunn.learnenglish;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JapaneseGrammarPracticeActivity extends AppCompatActivity {

    private int lessonNumber = 1;
    private int pointNumber = 1;
    private boolean isComprehensive = false;
    private boolean isKaiteOboeru = false;

    private JapaneseGrammarLesson currentLesson;
    private JapaneseGrammarPoint currentPoint;
    private List<GrammarQuiz> quizList = new ArrayList<>();
    private int currentQuizIndex = 0;
    private int correctCount = 0;
    private boolean hasAnsweredCurrentQuestion = false;

    private TextToSpeech tts;

    private ImageButton btnCloseQuiz;
    private TextView tvPracticeHeaderTitle;
    private TextView tvPracticePointTag;
    private TextView tvQuestionCounter;
    private ProgressBar pbQuizProgress;

    private NestedScrollView nsvQuizContent;
    private TextView tvQuestionSentence;
    private TextView tvQuestionMeaning;
    private ImageButton btnSpeakQuestion;

    private Button btnOption0;
    private Button btnOption1;
    private Button btnOption2;
    private Button btnOption3;
    private Button[] optionButtons;

    private CardView cardFeedback;
    private LinearLayout llFeedbackBackground;
    private TextView tvFeedbackTitle;
    private TextView tvFeedbackExplanation;

    private LinearLayout llCompletionView;
    private TextView tvCompletionTitle;
    private TextView tvCompletionSubtitle;
    private TextView tvCompletionScore;
    private Button btnNextPointOrFinish;
    private Button btnBackToHub;

    private LinearLayout llBottomActionBar;
    private Button btnContinueQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_japanese_grammar_practice);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lessonNumber = getIntent().getIntExtra("lessonNumber", 1);
        pointNumber = getIntent().getIntExtra("pointNumber", 1);
        isComprehensive = getIntent().getBooleanExtra("isComprehensive", false);
        isKaiteOboeru = getIntent().getBooleanExtra("isKaiteOboeru", false);

        initViews();
        initTTS();
        loadQuizData();
    }

    private void initViews() {
        btnCloseQuiz = findViewById(R.id.btnCloseQuiz);
        tvPracticeHeaderTitle = findViewById(R.id.tvPracticeHeaderTitle);
        tvPracticePointTag = findViewById(R.id.tvPracticePointTag);
        tvQuestionCounter = findViewById(R.id.tvQuestionCounter);
        pbQuizProgress = findViewById(R.id.pbQuizProgress);

        nsvQuizContent = findViewById(R.id.nsvQuizContent);
        tvQuestionSentence = findViewById(R.id.tvQuestionSentence);
        tvQuestionMeaning = findViewById(R.id.tvQuestionMeaning);
        btnSpeakQuestion = findViewById(R.id.btnSpeakQuestion);

        btnOption0 = findViewById(R.id.btnOption0);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        optionButtons = new Button[]{btnOption0, btnOption1, btnOption2, btnOption3};

        cardFeedback = findViewById(R.id.cardFeedback);
        llFeedbackBackground = findViewById(R.id.llFeedbackBackground);
        tvFeedbackTitle = findViewById(R.id.tvFeedbackTitle);
        tvFeedbackExplanation = findViewById(R.id.tvFeedbackExplanation);

        llCompletionView = findViewById(R.id.llCompletionView);
        tvCompletionTitle = findViewById(R.id.tvCompletionTitle);
        tvCompletionSubtitle = findViewById(R.id.tvCompletionSubtitle);
        tvCompletionScore = findViewById(R.id.tvCompletionScore);
        btnNextPointOrFinish = findViewById(R.id.btnNextPointOrFinish);
        btnBackToHub = findViewById(R.id.btnBackToHub);

        llBottomActionBar = findViewById(R.id.llBottomActionBar);
        btnContinueQuiz = findViewById(R.id.btnContinueQuiz);

        btnCloseQuiz.setOnClickListener(v -> finish());
        btnBackToHub.setOnClickListener(v -> finish());

        for (int i = 0; i < optionButtons.length; i++) {
            final int optIdx = i;
            optionButtons[i].setOnClickListener(v -> onOptionSelected(optIdx));
        }

        btnSpeakQuestion.setOnClickListener(v -> {
            if (tts != null && currentQuizIndex < quizList.size()) {
                String q = quizList.get(currentQuizIndex).getQuestion();
                q = q.replace("（　）", "...").replace("()", "...");
                tts.speak(q, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnContinueQuiz.setOnClickListener(v -> onContinueClicked());
    }

    private void initTTS() {
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.JAPANESE);
                tts.setSpeechRate(0.85f);
            }
        });
    }

    private void loadQuizData() {
        currentLesson = JapaneseGrammarDataManager.getLessonByNumber(this, lessonNumber);
        if (currentLesson == null) {
            Toast.makeText(this, "Không có dữ liệu bài học", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        quizList.clear();

        if (isKaiteOboeru) {
            tvPracticeHeaderTitle.setText("Bài tập Kaite Oboeru – Bài " + lessonNumber);
            tvPracticePointTag.setText("文法チェックシート • Minna no Nihongo");
            List<GrammarQuiz> koList = KaiteOboeruDataManager.getQuizzesForLesson(this, lessonNumber);
            if (koList != null && !koList.isEmpty()) {
                quizList.addAll(koList);
            }
        } else if (isComprehensive) {
            tvPracticeHeaderTitle.setText("Kiểm tra tổng hợp Bài " + lessonNumber);
            tvPracticePointTag.setText(currentLesson.getTitle());

            // Collect quizzes from all points in lesson
            for (JapaneseGrammarPoint p : currentLesson.getPoints()) {
                quizList.addAll(p.getQuizzes());
            }
        } else {
            currentPoint = currentLesson.getPointByNumber(pointNumber);
            if (currentPoint != null) {
                tvPracticeHeaderTitle.setText("Kiểm tra kiến thức");
                tvPracticePointTag.setText(currentPoint.getTitle() + " (" + currentPoint.getMeaning() + ")");
                quizList.addAll(currentPoint.getQuizzes());
            }
        }

        if (quizList.isEmpty()) {
            Toast.makeText(this, "Không tìm thấy câu hỏi cho mục này", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        currentQuizIndex = 0;
        correctCount = 0;
        displayQuestion(currentQuizIndex);
    }

    private void displayQuestion(int index) {
        if (index >= quizList.size()) {
            showCompletionView();
            return;
        }

        hasAnsweredCurrentQuestion = false;
        GrammarQuiz quiz = quizList.get(index);

        int total = quizList.size();
        tvQuestionCounter.setText((index + 1) + "/" + total);
        int progress = (int) Math.round(((index + 1) * 100.0) / total);
        pbQuizProgress.setProgress(progress);

        tvQuestionSentence.setText(quiz.getQuestion());
        tvQuestionMeaning.setText(quiz.getMeaning());

        List<String> options = quiz.getOptions();
        for (int i = 0; i < optionButtons.length; i++) {
            Button btn = optionButtons[i];
            if (i < options.size()) {
                btn.setVisibility(View.VISIBLE);
                btn.setText(options.get(i));
                btn.setEnabled(true);
                btn.setBackgroundResource(R.drawable.bg_option_outline);
                btn.setTextColor(Color.parseColor("#1E293B"));
            } else {
                btn.setVisibility(View.GONE);
            }
        }

        cardFeedback.setVisibility(View.GONE);
        btnContinueQuiz.setEnabled(false);
        btnContinueQuiz.setBackgroundResource(R.drawable.bg_btn_primary_disabled);
    }

    private void onOptionSelected(int selectedIndex) {
        if (hasAnsweredCurrentQuestion || currentQuizIndex >= quizList.size()) return;
        hasAnsweredCurrentQuestion = true;

        GrammarQuiz quiz = quizList.get(currentQuizIndex);
        int correctIndex = quiz.getCorrectIndex();
        boolean isCorrect = (selectedIndex == correctIndex);

        if (isCorrect) {
            correctCount++;
        }

        // Highlight options
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setEnabled(false);
            if (i == correctIndex) {
                optionButtons[i].setBackgroundColor(Color.parseColor("#DCFCE7"));
                optionButtons[i].setTextColor(Color.parseColor("#15803D"));
            } else if (i == selectedIndex && !isCorrect) {
                optionButtons[i].setBackgroundColor(Color.parseColor("#FEE2E2"));
                optionButtons[i].setTextColor(Color.parseColor("#B91C1C"));
            }
        }

        // Show feedback card
        cardFeedback.setVisibility(View.VISIBLE);
        if (isCorrect) {
            cardFeedback.setCardBackgroundColor(Color.parseColor("#F0FDF4"));
            tvFeedbackTitle.setText("✓ Chính xác!");
            tvFeedbackTitle.setTextColor(Color.parseColor("#16A34A"));
            tvFeedbackExplanation.setTextColor(Color.parseColor("#166534"));
        } else {
            cardFeedback.setCardBackgroundColor(Color.parseColor("#FEF2F2"));
            tvFeedbackTitle.setText("✕ Chưa chính xác!");
            tvFeedbackTitle.setTextColor(Color.parseColor("#DC2626"));
            tvFeedbackExplanation.setTextColor(Color.parseColor("#991B1B"));
        }
        tvFeedbackExplanation.setText(quiz.getExplanation());

        // Enable continue button
        btnContinueQuiz.setEnabled(true);
        btnContinueQuiz.setBackgroundResource(R.drawable.bg_button_blue);

        // Auto-scroll to feedback
        nsvQuizContent.post(() -> nsvQuizContent.fullScroll(View.FOCUS_DOWN));
    }

    private void onContinueClicked() {
        if (currentQuizIndex + 1 < quizList.size()) {
            currentQuizIndex++;
            displayQuestion(currentQuizIndex);
        } else {
            showCompletionView();
        }
    }

    private void showCompletionView() {
        nsvQuizContent.setVisibility(View.GONE);
        llBottomActionBar.setVisibility(View.GONE);
        llCompletionView.setVisibility(View.VISIBLE);

        int total = quizList.size();
        int percent = total > 0 ? (int) Math.round((correctCount * 100.0) / total) : 0;
        tvCompletionScore.setText("Kết quả: " + correctCount + "/" + total + " Đúng (" + percent + "%)");

        if (isKaiteOboeru) {
            tvCompletionTitle.setText("Hoàn thành Bài tập Kaite Oboeru!");
            tvCompletionSubtitle.setText("Bạn đã làm xong phiếu bài tập ngữ pháp (文法チェックシート) của Minna no Nihongo Bài " + lessonNumber + ".");
            btnNextPointOrFinish.setText("Quay về bài học");
            btnNextPointOrFinish.setOnClickListener(v -> finish());
        } else if (isComprehensive) {
            JapaneseGrammarProgressManager.setLessonQuizPassed(this, lessonNumber, true);
            tvCompletionTitle.setText("Hoàn thành Bài " + lessonNumber + "!");
            tvCompletionSubtitle.setText("Bạn đã hoàn thành bài kiểm tra tổng hợp Minna no Nihongo Bài " + lessonNumber + ".");

            if (lessonNumber < 50) {
                btnNextPointOrFinish.setText("Học Bài " + (lessonNumber + 1) + " →");
                btnNextPointOrFinish.setOnClickListener(v -> {
                    Intent intent = new Intent(this, JapaneseGrammarDetailActivity.class);
                    intent.putExtra("lessonNumber", lessonNumber + 1);
                    startActivity(intent);
                    finish();
                });
            } else {
                btnNextPointOrFinish.setText("Quay về bài học");
                btnNextPointOrFinish.setOnClickListener(v -> finish());
            }
        } else {
            // Mark single point completed
            JapaneseGrammarProgressManager.markPointCompleted(this, lessonNumber, pointNumber);

            tvCompletionTitle.setText("Nắm vững ngữ pháp!");
            tvCompletionSubtitle.setText("Bạn đã hoàn thành mẫu câu '" + (currentPoint != null ? currentPoint.getTitle() : "") + "'. Tiến độ học tập đã được lưu.");

            int totalPoints = currentLesson != null ? currentLesson.getPointsCount() : 7;
            if (pointNumber < totalPoints) {
                btnNextPointOrFinish.setText("Học ngữ pháp tiếp theo (" + (pointNumber + 1) + "/" + totalPoints + ") →");
                btnNextPointOrFinish.setOnClickListener(v -> {
                    Intent intent = new Intent(this, JapaneseGrammarPointStudyActivity.class);
                    intent.putExtra("lessonNumber", lessonNumber);
                    intent.putExtra("pointNumber", pointNumber + 1);
                    startActivity(intent);
                    finish();
                });
            } else {
                btnNextPointOrFinish.setText("Làm bài kiểm tra tổng hợp cả bài →");
                btnNextPointOrFinish.setOnClickListener(v -> {
                    Intent intent = new Intent(this, JapaneseGrammarPracticeActivity.class);
                    intent.putExtra("lessonNumber", lessonNumber);
                    intent.putExtra("isComprehensive", true);
                    startActivity(intent);
                    finish();
                });
            }
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
