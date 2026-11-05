package com.hieunn.learnenglish;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class KanjiDetailActivity extends AppCompatActivity {

    private int lessonId = 1;
    private KanjiLesson lesson;
    private TextToSpeech tts;

    // Header views
    private ImageButton btnBack;
    private TextView tvLessonTitle;
    private TextView tvLessonSubtitle;
    private Button btnStartQuiz;

    // Tabs
    private TextView tabFlashcard;
    private TextView tabKanjiList;
    private TextView tabReading;
    private TextView tabWriting;

    // Containers
    private View containerFlashcard;
    private RecyclerView rvKanjiList;
    private RecyclerView rvReading;
    private View containerWriting;
    private LinearLayout layoutWritingItems;

    // Flashcard views
    private CardView cardFlashcard;
    private View cardFront;
    private View cardBack;
    private TextView tvCardIndex;
    private TextView tvCardKanji;
    private TextView tvCardHanViet;
    private TextView tvCardBackKanji;
    private TextView tvCardBackHanViet;
    private TextView tvCardKunyomi;
    private TextView tvCardOnyomi;
    private TextView tvCardExamples;
    private ImageButton btnSpeakCard;
    private Button btnPrevCard;
    private Button btnNextCard;

    private int currentCardIndex = 0;
    private boolean isCardShowingBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_detail);

        lessonId = getIntent().getIntExtra("lessonId", 1);
        lesson = KanjiDataManager.getLessonById(this, lessonId);

        if (lesson == null) {
            Toast.makeText(this, "Không tìm thấy dữ liệu bài học", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        initTTS();
        initViews();
        setupHeader();
        setupTabs();
        setupFlashcardView();
        setupKanjiListView();
        setupReadingView();
        setupWritingView();

        // Default tab: Flashcard (or Reading if test)
        if (lesson.isTest() || lesson.getKanjis().isEmpty()) {
            selectTab(1); // Reading or list
        } else {
            selectTab(0);
        }
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
        tvLessonTitle = findViewById(R.id.tvLessonTitle);
        tvLessonSubtitle = findViewById(R.id.tvLessonSubtitle);
        btnStartQuiz = findViewById(R.id.btnStartQuiz);

        tabFlashcard = findViewById(R.id.tabFlashcard);
        tabKanjiList = findViewById(R.id.tabKanjiList);
        tabReading = findViewById(R.id.tabReading);
        tabWriting = findViewById(R.id.tabWriting);

        containerFlashcard = findViewById(R.id.containerFlashcard);
        rvKanjiList = findViewById(R.id.rvKanjiList);
        rvReading = findViewById(R.id.rvReading);
        containerWriting = findViewById(R.id.containerWriting);
        layoutWritingItems = findViewById(R.id.layoutWritingItems);

        cardFlashcard = findViewById(R.id.cardFlashcard);
        cardFront = findViewById(R.id.cardFront);
        cardBack = findViewById(R.id.cardBack);

        tvCardIndex = findViewById(R.id.tvCardIndex);
        tvCardKanji = findViewById(R.id.tvCardKanji);
        tvCardHanViet = findViewById(R.id.tvCardHanViet);
        tvCardBackKanji = findViewById(R.id.tvCardBackKanji);
        tvCardBackHanViet = findViewById(R.id.tvCardBackHanViet);
        tvCardKunyomi = findViewById(R.id.tvCardKunyomi);
        tvCardOnyomi = findViewById(R.id.tvCardOnyomi);
        tvCardExamples = findViewById(R.id.tvCardExamples);
        btnSpeakCard = findViewById(R.id.btnSpeakCard);
        btnPrevCard = findViewById(R.id.btnPrevCard);
        btnNextCard = findViewById(R.id.btnNextCard);
    }

    private void setupHeader() {
        btnBack.setOnClickListener(v -> finish());
        tvLessonTitle.setText(lesson.getTitle());

        if (lesson.isTest()) {
            tvLessonSubtitle.setText("30 câu trắc nghiệm kiểm tra tổng hợp");
            btnStartQuiz.setText("Làm bài thi");
        } else {
            tvLessonSubtitle.setText(lesson.getTotalKanji() > 0 ? (lesson.getTotalKanji() + " chữ Kanji") : lesson.getDescription());
            btnStartQuiz.setText("Thi thử");
        }

        btnStartQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(this, KanjiTestActivity.class);
            intent.putExtra("lessonId", lesson.getId());
            startActivity(intent);
        });
    }

    private void setupTabs() {
        tabFlashcard.setOnClickListener(v -> selectTab(0));
        tabKanjiList.setOnClickListener(v -> selectTab(1));
        tabReading.setOnClickListener(v -> selectTab(2));
        tabWriting.setOnClickListener(v -> selectTab(3));

        // Nếu bài là bài thi (Lesson 11 không có chữ Kanji lẻ)
        if (lesson.isTest() || lesson.getKanjis().isEmpty()) {
            tabFlashcard.setVisibility(View.GONE);
            tabKanjiList.setVisibility(View.GONE);
            tabWriting.setVisibility(View.GONE);
        }
    }

    private void selectTab(int index) {
        // Reset tab styles
        int activeBg = R.drawable.bg_tab_active_red;
        int activeColor = 0xFFFFFFFF;
        int inactiveColor = 0xFF4B5563;

        tabFlashcard.setBackgroundColor(0);
        tabFlashcard.setTextColor(inactiveColor);
        tabKanjiList.setBackgroundColor(0);
        tabKanjiList.setTextColor(inactiveColor);
        tabReading.setBackgroundColor(0);
        tabReading.setTextColor(inactiveColor);
        tabWriting.setBackgroundColor(0);
        tabWriting.setTextColor(inactiveColor);

        containerFlashcard.setVisibility(View.GONE);
        rvKanjiList.setVisibility(View.GONE);
        rvReading.setVisibility(View.GONE);
        containerWriting.setVisibility(View.GONE);

        switch (index) {
            case 0:
                tabFlashcard.setBackgroundResource(activeBg);
                tabFlashcard.setTextColor(activeColor);
                containerFlashcard.setVisibility(View.VISIBLE);
                break;
            case 1:
                tabKanjiList.setBackgroundResource(activeBg);
                tabKanjiList.setTextColor(activeColor);
                rvKanjiList.setVisibility(View.VISIBLE);
                break;
            case 2:
                tabReading.setBackgroundResource(activeBg);
                tabReading.setTextColor(activeColor);
                rvReading.setVisibility(View.VISIBLE);
                break;
            case 3:
                tabWriting.setBackgroundResource(activeBg);
                tabWriting.setTextColor(activeColor);
                containerWriting.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setupFlashcardView() {
        List<KanjiItem> kanjis = lesson.getKanjis();
        if (kanjis.isEmpty()) return;

        currentCardIndex = 0;
        isCardShowingBack = false;
        displayCurrentCard();

        cardFlashcard.setOnClickListener(v -> flipCard());

        btnSpeakCard.setOnClickListener(v -> {
            if (currentCardIndex >= 0 && currentCardIndex < kanjis.size()) {
                speakJapanese(kanjis.get(currentCardIndex).getKanji());
            }
        });

        btnPrevCard.setOnClickListener(v -> {
            if (currentCardIndex > 0) {
                currentCardIndex--;
                isCardShowingBack = false;
                displayCurrentCard();
            }
        });

        btnNextCard.setOnClickListener(v -> {
            if (currentCardIndex < kanjis.size() - 1) {
                currentCardIndex++;
                isCardShowingBack = false;
                displayCurrentCard();
            }
        });
    }

    private void displayCurrentCard() {
        List<KanjiItem> kanjis = lesson.getKanjis();
        if (kanjis.isEmpty() || currentCardIndex < 0 || currentCardIndex >= kanjis.size()) return;

        KanjiItem item = kanjis.get(currentCardIndex);

        cardFront.setVisibility(isCardShowingBack ? View.GONE : View.VISIBLE);
        cardBack.setVisibility(isCardShowingBack ? View.VISIBLE : View.GONE);

        tvCardIndex.setText((currentCardIndex + 1) + " / " + kanjis.size());
        tvCardKanji.setText(item.getKanji());
        tvCardHanViet.setText(item.getHanViet());

        tvCardBackKanji.setText(item.getKanji());
        tvCardBackHanViet.setText(item.getHanViet());
        tvCardKunyomi.setText(item.getKunyomi().isEmpty() ? "(không có)" : item.getKunyomi());
        tvCardOnyomi.setText(item.getOnyomi().isEmpty() ? "(không có)" : item.getOnyomi());

        if (item.getExamples() != null && !item.getExamples().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < item.getExamples().size(); i++) {
                sb.append(item.getExamples().get(i));
                if (i < item.getExamples().size() - 1) sb.append(", ");
            }
            tvCardExamples.setText(sb.toString());
        } else {
            tvCardExamples.setText("(chưa có ví dụ)");
        }

        btnPrevCard.setEnabled(currentCardIndex > 0);
        btnPrevCard.setAlpha(currentCardIndex > 0 ? 1.0f : 0.4f);
        btnNextCard.setEnabled(currentCardIndex < kanjis.size() - 1);
        btnNextCard.setAlpha(currentCardIndex < kanjis.size() - 1 ? 1.0f : 0.4f);
    }

    private void flipCard() {
        final ObjectAnimator flipOut = ObjectAnimator.ofFloat(cardFlashcard, "scaleX", 1f, 0f);
        final ObjectAnimator flipIn = ObjectAnimator.ofFloat(cardFlashcard, "scaleX", 0f, 1f);
        flipOut.setDuration(150);
        flipIn.setDuration(150);
        flipOut.setInterpolator(new AccelerateDecelerateInterpolator());
        flipIn.setInterpolator(new AccelerateDecelerateInterpolator());

        flipOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isCardShowingBack = !isCardShowingBack;
                if (isCardShowingBack) {
                    cardFront.setVisibility(View.GONE);
                    cardBack.setVisibility(View.VISIBLE);
                } else {
                    cardFront.setVisibility(View.VISIBLE);
                    cardBack.setVisibility(View.GONE);
                }
                flipIn.start();
            }
        });
        flipOut.start();
    }

    private void setupKanjiListView() {
        rvKanjiList.setLayoutManager(new LinearLayoutManager(this));
        rvKanjiList.setAdapter(new KanjiCardAdapter(lesson.getKanjis(), item -> speakJapanese(item.getKanji())));
    }

    private void setupReadingView() {
        rvReading.setLayoutManager(new LinearLayoutManager(this));
        rvReading.setAdapter(new KanjiReadingAdapter(lesson.getReadingPractice(), sentence -> speakJapanese(sentence)));
    }

    private void setupWritingView() {
        layoutWritingItems.removeAllViews();
        List<String> writings = lesson.getWritingPractice();
        if (writings == null || writings.isEmpty()) return;

        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < writings.size(); i++) {
            final String prompt = writings.get(i);
            final int index = i + 1;

            View itemView = inflater.inflate(R.layout.item_kanji_writing, layoutWritingItems, false);
            TextView tvIndex = itemView.findViewById(R.id.tvWritingIndex);
            TextView tvPrompt = itemView.findViewById(R.id.tvWritingPrompt);
            ImageButton btnSpeak = itemView.findViewById(R.id.btnSpeakWriting);
            EditText etInput = itemView.findViewById(R.id.etWritingInput);
            Button btnCheck = itemView.findViewById(R.id.btnCheckWriting);
            TextView tvFeedback = itemView.findViewById(R.id.tvWritingFeedback);
            View scrollCandidates = itemView.findViewById(R.id.scrollWritingCandidates);
            LinearLayout layoutCandidates = itemView.findViewById(R.id.layoutWritingCandidates);

            tvIndex.setText(String.valueOf(index));
            tvPrompt.setText(prompt);

            btnSpeak.setOnClickListener(v -> speakJapanese(prompt));

            // Attach RomajiHelper with candidate suggestions
            RomajiHelper.attach(this, etInput, scrollCandidates, layoutCandidates, () -> true);

            btnCheck.setOnClickListener(v -> {
                String input = etInput.getText().toString().trim();
                if (input.isEmpty()) {
                    tvFeedback.setVisibility(View.VISIBLE);
                    tvFeedback.setTextColor(0xFFEF4444);
                    tvFeedback.setText("Vui lòng nhập câu trả lời");
                    return;
                }

                boolean isCorrect = checkWritingMatch(input, prompt);
                tvFeedback.setVisibility(View.VISIBLE);
                if (isCorrect) {
                    tvFeedback.setTextColor(0xFF059669);
                    tvFeedback.setText("✓ Chính xác! Rất tốt!");
                } else {
                    tvFeedback.setTextColor(0xFFEF4444);
                    tvFeedback.setText("Chưa chính xác. Đáp án gợi ý: " + prompt);
                }
            });

            layoutWritingItems.addView(itemView);
        }
    }

    private boolean checkWritingMatch(String userInput, String targetPrompt) {
        if (userInput == null || targetPrompt == null) return false;
        String cleanUser = userInput.trim().toLowerCase();
        String cleanTarget = targetPrompt.trim().toLowerCase();

        if (cleanUser.equals(cleanTarget)) return true;

        // Split tokens in target prompt: e.g. "ひとり 人" -> accepts "ひとり" or "人"
        String[] parts = cleanTarget.split("[\\s()（）]+");
        for (String part : parts) {
            if (!part.isEmpty() && (cleanUser.equals(part) || cleanUser.contains(part) || part.contains(cleanUser))) {
                return true;
            }
        }

        return false;
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
