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
    private TextToSpeech tts;
    private int lessonId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_unit_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lessonId = getIntent().getIntExtra("lessonId", -1);

        viewPagerCards = findViewById(R.id.viewPagerCards);
        tvIndicator = findViewById(R.id.tvIndicator);
        rvVocabList = findViewById(R.id.rvVocabList);
        tvTermCount = findViewById(R.id.tvTermCount);

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
    }

    private void loadData() {
        if (lessonId > 0) {
            AppDatabase db = AppDatabase.getInstance(this);
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
                        tts.speak(items.get(position).getEnglishWord(), TextToSpeech.QUEUE_FLUSH, null, null);
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
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
