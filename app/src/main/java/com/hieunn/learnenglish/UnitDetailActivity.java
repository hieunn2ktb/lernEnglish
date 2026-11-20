package com.hieunn.learnenglish;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.VocabEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UnitDetailActivity extends AppCompatActivity {

    private ViewPager2 viewPagerCards;
    private TextView tvIndicator;
    private RecyclerView rvVocabList;
    private TextView tvTermCount;
    private TextView tvUnitTitle;
    private TextToSpeech tts;
    private int lessonId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_unit_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lessonId = getIntent().getIntExtra("lessonId", -1);

        viewPagerCards = findViewById(R.id.viewPagerCards);
        tvIndicator = findViewById(R.id.tvIndicator);
        rvVocabList = findViewById(R.id.rvVocabList);
        tvTermCount = findViewById(R.id.tvTermCount);
        tvUnitTitle = findViewById(R.id.tvUnitTitle);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Setup TextToSpeech
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
            }
        });

        loadData();

        findViewById(R.id.btnStudyAll).setOnClickListener(v -> {
            // Start learning session (Tinder cards or other activity)
            Intent intent = new Intent(this, VocabularyActivity.class);
            intent.putExtra("lessonId", lessonId);
            startActivity(intent);
        });

        findViewById(R.id.btnOptionFlashcards).setOnClickListener(v -> {
            Intent intent = new Intent(this, VocabularyActivity.class);
            intent.putExtra("lessonId", lessonId);
            startActivity(intent);
        });

        findViewById(R.id.btnOptionWrite).setOnClickListener(v -> {
            Intent intent = new Intent(this, WriteVocabActivity.class);
            intent.putExtra("lessonId", lessonId);
            startActivity(intent);
        });

        findViewById(R.id.btnOptionMatch).setOnClickListener(v -> {
            Intent intent = new Intent(this, MatchVocabActivity.class);
            intent.putExtra("lessonId", lessonId);
            startActivity(intent);
        });

        findViewById(R.id.btnOptionTest).setOnClickListener(v -> {
            Intent intent = new Intent(this, TestVocabActivity.class);
            intent.putExtra("lessonId", lessonId);
            startActivity(intent);
        });

        findViewById(R.id.btnOptionLearn).setOnClickListener(v -> {
            Intent intent = new Intent(this, LearnVocabActivity.class);
            intent.putExtra("lessonId", lessonId);
            startActivity(intent);
        });

        TextView btnOptionGrammar = findViewById(R.id.btnOptionGrammar);
        TextView btnOptionKaiteOboeru = findViewById(R.id.btnOptionKaiteOboeru);
        TextView btnOptionChoukai = findViewById(R.id.btnOptionChoukai);
        if (lessonId > 0) {
            AppDatabase db = AppDatabase.getInstance(this);
            com.hieunn.learnenglish.db.LessonEntity lesson = db.lessonDao().getLessonById(lessonId);
            if (lesson != null && MainActivity.isJapaneseLesson(lesson)) {
                int lessonNum = extractLessonNumber(lesson.title);
                if (lessonNum > 0 && lessonNum <= 50) {
                    if (btnOptionGrammar != null) {
                        btnOptionGrammar.setVisibility(android.view.View.VISIBLE);
                        btnOptionGrammar.setText("⛩️ Ngữ pháp Bài " + lessonNum);
                        btnOptionGrammar.setOnClickListener(v -> {
                            Intent intent = new Intent(this, JapaneseGrammarDetailActivity.class);
                            intent.putExtra("lessonNumber", lessonNum);
                            startActivity(intent);
                        });
                    }

                    if (btnOptionKaiteOboeru != null) {
                        btnOptionKaiteOboeru.setVisibility(android.view.View.VISIBLE);
                        btnOptionKaiteOboeru.setText("✍️ Bài tập Kaite Oboeru (Bài " + lessonNum + ")");
                        btnOptionKaiteOboeru.setOnClickListener(v -> {
                            Intent intent = new Intent(this, JapaneseGrammarPracticeActivity.class);
                            intent.putExtra("lessonNumber", lessonNum);
                            intent.putExtra("isKaiteOboeru", true);
                            startActivity(intent);
                        });
                    }

                    if (btnOptionChoukai != null) {
                        btnOptionChoukai.setVisibility(android.view.View.VISIBLE);
                        btnOptionChoukai.setText("🎧 Luyện nghe Choukai (Bài " + lessonNum + ")");
                        btnOptionChoukai.setOnClickListener(v -> {
                            Intent intent = new Intent(this, JapaneseChoukaiActivity.class);
                            intent.putExtra("lessonNumber", lessonNum);
                            startActivity(intent);
                        });
                    }
                }
            }
        }
    }

    private int extractLessonNumber(String title) {
        if (title != null) {
            String lower = title.toLowerCase().trim();
            if (lower.startsWith("bài ")) {
                try {
                    String numStr = lower.substring(4).split("[:\\s]")[0].trim();
                    return Integer.parseInt(numStr);
                } catch (Exception e) {}
            }
        }
        return -1;
    }

    private void loadData() {
        if (lessonId > 0) {
            AppDatabase db = AppDatabase.getInstance(this);
            com.hieunn.learnenglish.db.LessonEntity lesson = db.lessonDao().getLessonById(lessonId);
            if (lesson != null && tvUnitTitle != null) {
                tvUnitTitle.setText(lesson.title);
            }

            List<VocabEntity> dbItems = db.vocabDao().getVocabByLessonId(lessonId);
            List<VocabItem> items = new ArrayList<>();
            for (VocabEntity e : dbItems) {
                items.add(new VocabItem(e.english, e.vietnamese, e.phonetic, e.wordType));
            }

            if (items.isEmpty()) {
                Toast.makeText(this, "Chưa có từ vựng nào!", Toast.LENGTH_SHORT).show();
                return;
            }

            tvTermCount.setText(items.size() + " thuật ngữ");
            tvIndicator.setText("1 / " + items.size());

            // Setup ViewPager2
            FlashcardQuizletAdapter pagerAdapter = new FlashcardQuizletAdapter(items, tts);
            viewPagerCards.setAdapter(pagerAdapter);
            viewPagerCards.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    tvIndicator.setText((position + 1) + " / " + items.size());
                    if (tts != null) {
                        String word = items.get(position).getEnglishWord();
                        if (VocabItem.isJapaneseText(word)) {
                            tts.setLanguage(Locale.JAPANESE);
                            word = VocabItem.getCleanSpeechText(word);
                        } else {
                            tts.setLanguage(Locale.US);
                        }
                        tts.speak(word, TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                }
            });

            // Setup RecyclerView
            rvVocabList.setLayoutManager(new LinearLayoutManager(this));
            VocabListQuizletAdapter listAdapter = new VocabListQuizletAdapter(items, tts);
            rvVocabList.setAdapter(listAdapter);
        } else {
            Toast.makeText(this, "Lỗi khi tải dữ liệu bài học", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStudyButtons();
    }

    private void updateStudyButtons() {
        Button btnStudyAll = findViewById(R.id.btnStudyAll);
        TextView btnOptionLearn = findViewById(R.id.btnOptionLearn);
        if (lessonId > 0 && LearnVocabSessionManager.hasSavedSession(this, lessonId)) {
            int mastered = LessonProgressManager.getVocabMastered(this, lessonId);
            int total = LessonProgressManager.getVocabTotal(this, lessonId);
            if (btnStudyAll != null) {
                btnStudyAll.setText("Tiếp tục học (" + mastered + "/" + total + " từ)");
                btnStudyAll.setOnClickListener(v -> {
                    Intent intent = new Intent(this, LearnVocabActivity.class);
                    intent.putExtra("lessonId", lessonId);
                    startActivity(intent);
                });
            }
            if (btnOptionLearn != null) {
                btnOptionLearn.setText("Học (Tiếp tục: " + mastered + "/" + total + " từ)");
            }
        } else {
            if (btnStudyAll != null) {
                btnStudyAll.setText("Học bộ học phần này");
                btnStudyAll.setOnClickListener(v -> {
                    Intent intent = new Intent(this, LearnVocabActivity.class);
                    intent.putExtra("lessonId", lessonId);
                    startActivity(intent);
                });
            }
            if (btnOptionLearn != null) {
                btnOptionLearn.setText("Học");
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
