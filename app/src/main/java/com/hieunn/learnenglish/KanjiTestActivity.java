package com.hieunn.learnenglish;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class KanjiTestActivity extends AppCompatActivity {

    private int lessonId = 1;
    private KanjiLesson lesson;
    private List<KanjiTestQuestion> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int correctCount = 0;
    private boolean isAnswered = false;

    private TextToSpeech tts;

    // Header views
    private ImageButton btnBack;
    private TextView tvQuizTitle;
    private TextView tvQuestionProgress;
    private TextView tvScoreBadge;
    private ProgressBar pbQuizProgress;

    // Question Views
    private ScrollView scrollQuestionContainer;
    private TextView tvQuestionNumber;
    private TextView tvQuestionPrompt;
    private TextView tvQuestionHint;
    private ImageButton btnSpeakQuestion;

    // 4 Options
    private CardView cardOption1;
    private CardView cardOption2;
    private CardView cardOption3;
    private CardView cardOption4;
    private TextView tvOptionBadge1;
    private TextView tvOptionBadge2;
    private TextView tvOptionBadge3;
    private TextView tvOptionBadge4;
    private TextView tvOptionText1;
    private TextView tvOptionText2;
    private TextView tvOptionText3;
    private TextView tvOptionText4;

    // Feedback
    private LinearLayout layoutBottomFeedback;
    private TextView tvFeedbackMessage;
    private Button btnNextQuestion;

    // Result
    private LinearLayout layoutResultContainer;
    private TextView tvResultScore;
    private TextView tvResultSubtext;
    private Button btnRetryQuiz;
    private Button btnFinishQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_test);

        lessonId = getIntent().getIntExtra("lessonId", 1);
        lesson = KanjiDataManager.getLessonById(this, lessonId);

        if (lesson == null) {
            Toast.makeText(this, "Không tìm thấy dữ liệu bài thi", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        initTTS();
        initViews();
        buildQuestions();
        startQuiz();
    }

    private void initTTS() {
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.JAPANESE);
                tts.setSpeechRate(0.85f);
            }
        });
    }

    private void speakJapanese(String text) {
        if (tts != null && text != null && !text.isEmpty()) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        tvQuizTitle = findViewById(R.id.tvQuizTitle);
        tvQuestionProgress = findViewById(R.id.tvQuestionProgress);
        tvScoreBadge = findViewById(R.id.tvScoreBadge);
        pbQuizProgress = findViewById(R.id.pbQuizProgress);

        scrollQuestionContainer = findViewById(R.id.scrollQuestionContainer);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestionPrompt = findViewById(R.id.tvQuestionPrompt);
        tvQuestionHint = findViewById(R.id.tvQuestionHint);
        btnSpeakQuestion = findViewById(R.id.btnSpeakQuestion);

        cardOption1 = findViewById(R.id.cardOption1);
        cardOption2 = findViewById(R.id.cardOption2);
        cardOption3 = findViewById(R.id.cardOption3);
        cardOption4 = findViewById(R.id.cardOption4);

        tvOptionBadge1 = findViewById(R.id.tvOptionBadge1);
        tvOptionBadge2 = findViewById(R.id.tvOptionBadge2);
        tvOptionBadge3 = findViewById(R.id.tvOptionBadge3);
        tvOptionBadge4 = findViewById(R.id.tvOptionBadge4);

        tvOptionText1 = findViewById(R.id.tvOptionText1);
        tvOptionText2 = findViewById(R.id.tvOptionText2);
        tvOptionText3 = findViewById(R.id.tvOptionText3);
        tvOptionText4 = findViewById(R.id.tvOptionText4);

        layoutBottomFeedback = findViewById(R.id.layoutBottomFeedback);
        tvFeedbackMessage = findViewById(R.id.tvFeedbackMessage);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);

        layoutResultContainer = findViewById(R.id.layoutResultContainer);
        tvResultScore = findViewById(R.id.tvResultScore);
        tvResultSubtext = findViewById(R.id.tvResultSubtext);
        btnRetryQuiz = findViewById(R.id.btnRetryQuiz);
        btnFinishQuiz = findViewById(R.id.btnFinishQuiz);

        btnBack.setOnClickListener(v -> finish());
        btnNextQuestion.setOnClickListener(v -> nextQuestion());
        btnRetryQuiz.setOnClickListener(v -> startQuiz());
        btnFinishQuiz.setOnClickListener(v -> finish());

        cardOption1.setOnClickListener(v -> onOptionSelected(0));
        cardOption2.setOnClickListener(v -> onOptionSelected(1));
        cardOption3.setOnClickListener(v -> onOptionSelected(2));
        cardOption4.setOnClickListener(v -> onOptionSelected(3));

        btnSpeakQuestion.setOnClickListener(v -> {
            if (currentQuestionIndex < questions.size()) {
                speakJapanese(questions.get(currentQuestionIndex).getQuestion());
            }
        });
    }

    private void buildQuestions() {
        questions.clear();

        // 1. If lesson has predefined questions (e.g. Lesson 11 review exam)
        if (lesson.getQuestions() != null && !lesson.getQuestions().isEmpty()) {
            questions.addAll(lesson.getQuestions());
            return;
        }

        // 2. Otherwise generate questions dynamically from Kanji items
        List<KanjiItem> kanjis = lesson.getKanjis();
        if (kanjis == null || kanjis.isEmpty()) return;

        List<KanjiLesson> allLessons = KanjiDataManager.getAllLessons(this);
        List<String> poolHanViet = new ArrayList<>();
        List<String> poolKanji = new ArrayList<>();
        for (KanjiLesson l : allLessons) {
            for (KanjiItem item : l.getKanjis()) {
                if (!item.getHanViet().isEmpty()) poolHanViet.add(item.getHanViet());
                if (!item.getKanji().isEmpty()) poolKanji.add(item.getKanji());
            }
        }

        Random rng = new Random();
        int qNum = 1;

        // Type 1: Kanji -> Hán Việt
        for (KanjiItem k : kanjis) {
            if (k.getHanViet().isEmpty()) continue;
            List<String> opts = new ArrayList<>();
            opts.add(k.getHanViet());

            Collections.shuffle(poolHanViet);
            for (String hv : poolHanViet) {
                if (!opts.contains(hv)) {
                    opts.add(hv);
                    if (opts.size() == 4) break;
                }
            }

            Collections.shuffle(opts);
            int correctIdx = opts.indexOf(k.getHanViet()) + 1; // 1-based
            String prompt = "Chữ Kanji 「" + k.getKanji() + "」 có âm Hán Việt là gì?";
            questions.add(new KanjiTestQuestion(qNum++, prompt, opts, correctIdx));
        }

        // Type 2: Hán Việt -> Kanji
        for (KanjiItem k : kanjis) {
            if (k.getKanji().isEmpty() || k.getHanViet().isEmpty()) continue;
            List<String> opts = new ArrayList<>();
            opts.add(k.getKanji());

            Collections.shuffle(poolKanji);
            for (String kj : poolKanji) {
                if (!opts.contains(kj)) {
                    opts.add(kj);
                    if (opts.size() == 4) break;
                }
            }

            Collections.shuffle(opts);
            int correctIdx = opts.indexOf(k.getKanji()) + 1;
            String prompt = "Âm Hán Việt 「" + k.getHanViet() + "」 viết bằng chữ Kanji nào?";
            questions.add(new KanjiTestQuestion(qNum++, prompt, opts, correctIdx));
        }

        // Shuffle generated questions
        Collections.shuffle(questions);
    }

    private void startQuiz() {
        if (questions.isEmpty()) {
            Toast.makeText(this, "Bài học chưa có câu hỏi kiểm tra", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        currentQuestionIndex = 0;
        correctCount = 0;

        tvQuizTitle.setText("Kiểm tra: " + lesson.getTitle());
        scrollQuestionContainer.setVisibility(View.VISIBLE);
        layoutBottomFeedback.setVisibility(View.GONE);
        layoutResultContainer.setVisibility(View.GONE);

        displayQuestion(0);
    }

    private void displayQuestion(int index) {
        if (index < 0 || index >= questions.size()) return;

        KanjiTestQuestion q = questions.get(index);
        isAnswered = false;

        tvQuestionProgress.setText("Câu " + (index + 1) + " / " + questions.size());
        tvScoreBadge.setText(correctCount + " Đúng");
        int progress = (int) (((index + 1) * 100f) / questions.size());
        pbQuizProgress.setProgress(progress);

        tvQuestionNumber.setText("CÂU " + (index + 1));
        tvQuestionPrompt.setText(q.getQuestion());

        if (lesson.isTest()) {
            tvQuestionHint.setText("Chọn cách đọc Hiragana đúng nhất cho phần Kanji:");
        } else {
            tvQuestionHint.setText("Chọn một trong 4 phương án đúng bên dưới:");
        }

        // Reset Options styling
        resetOptionCard(cardOption1, tvOptionBadge1, tvOptionText1, 1);
        resetOptionCard(cardOption2, tvOptionBadge2, tvOptionText2, 2);
        resetOptionCard(cardOption3, tvOptionBadge3, tvOptionText3, 3);
        resetOptionCard(cardOption4, tvOptionBadge4, tvOptionText4, 4);

        List<String> options = q.getOptions();
        tvOptionText1.setText(options.size() > 0 ? options.get(0) : "");
        tvOptionText2.setText(options.size() > 1 ? options.get(1) : "");
        tvOptionText3.setText(options.size() > 2 ? options.get(2) : "");
        tvOptionText4.setText(options.size() > 3 ? options.get(3) : "");

        cardOption1.setVisibility(options.size() > 0 ? View.VISIBLE : View.GONE);
        cardOption2.setVisibility(options.size() > 1 ? View.VISIBLE : View.GONE);
        cardOption3.setVisibility(options.size() > 2 ? View.VISIBLE : View.GONE);
        cardOption4.setVisibility(options.size() > 3 ? View.VISIBLE : View.GONE);

        layoutBottomFeedback.setVisibility(View.GONE);
    }

    private void resetOptionCard(CardView card, TextView badge, TextView text, int num) {
        card.setCardBackgroundColor(0xFFFFFFFF);
        card.setClickable(true);
        badge.setBackgroundResource(R.drawable.bg_badge_gray);
        badge.setTextColor(0xFF475569);
        badge.setText(String.valueOf(num));
        text.setTextColor(0xFF1E293B);
    }

    private void onOptionSelected(int selectedIndexZeroBased) {
        if (isAnswered) return;
        isAnswered = true;

        KanjiTestQuestion q = questions.get(currentQuestionIndex);
        int correctIdx = q.getZeroBasedCorrectIndex();
        boolean isCorrect = (selectedIndexZeroBased == correctIdx);

        if (isCorrect) {
            correctCount++;
            tvScoreBadge.setText(correctCount + " Đúng");
        }

        // Highlight options
        CardView[] cards = {cardOption1, cardOption2, cardOption3, cardOption4};
        TextView[] badges = {tvOptionBadge1, tvOptionBadge2, tvOptionBadge3, tvOptionBadge4};
        TextView[] texts = {tvOptionText1, tvOptionText2, tvOptionText3, tvOptionText4};

        for (int i = 0; i < cards.length; i++) {
            cards[i].setClickable(false);
            if (i == correctIdx) {
                // Correct card style (Green)
                cards[i].setCardBackgroundColor(0xFFECFDF5);
                badges[i].setBackgroundResource(R.drawable.bg_badge_green);
                badges[i].setTextColor(0xFF065F46);
                badges[i].setText("✓");
                texts[i].setTextColor(0xFF047857);
            } else if (i == selectedIndexZeroBased && !isCorrect) {
                // Wrong card selected (Red)
                cards[i].setCardBackgroundColor(0xFFFEF2F2);
                badges[i].setBackgroundResource(R.drawable.bg_badge_red);
                badges[i].setTextColor(0xFFEF4444);
                badges[i].setText("✗");
                texts[i].setTextColor(0xFFB91C1C);
            }
        }

        // Bottom feedback
        layoutBottomFeedback.setVisibility(View.VISIBLE);
        if (isCorrect) {
            tvFeedbackMessage.setText("Chính xác! 🎉");
            tvFeedbackMessage.setTextColor(0xFF059669);
        } else {
            String correctText = q.getCorrectOptionText();
            tvFeedbackMessage.setText("Chưa chính xác! Đáp án đúng: " + correctText);
            tvFeedbackMessage.setTextColor(0xFFEF4444);
        }

        if (currentQuestionIndex == questions.size() - 1) {
            btnNextQuestion.setText("Xem kết quả 🎉");
        } else {
            btnNextQuestion.setText("Câu tiếp theo →");
        }
    }

    private void nextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            displayQuestion(currentQuestionIndex);
        } else {
            showResult();
        }
    }

    private void showResult() {
        scrollQuestionContainer.setVisibility(View.GONE);
        layoutBottomFeedback.setVisibility(View.GONE);
        layoutResultContainer.setVisibility(View.VISIBLE);

        int total = questions.size();
        int percent = (int) ((correctCount * 100f) / total);

        tvResultScore.setText(correctCount + " / " + total + " câu đúng (" + percent + "%)");
        if (percent >= 90) {
            tvResultSubtext.setText("Xuất sắc! 🎉 Bạn đã thành thạo toàn bộ chữ Kanji bài này!");
        } else if (percent >= 70) {
            tvResultSubtext.setText("Rất tốt! Bạn đã nắm vững phần lớn kiến thức.");
        } else {
            tvResultSubtext.setText("Hãy tiếp tục ôn tập lại các chữ Kanji để đạt điểm cao hơn nhé!");
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
