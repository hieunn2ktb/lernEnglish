package com.hieunn.learnenglish;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;

import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.VocabEntity;

import java.util.ArrayList;
import java.util.List;

public class VocabularyActivity extends AppCompatActivity {

    private int learned = 0;
    private int missed = 0;
    private int totalItems = 0;
    private CardStackLayoutManager layoutManager;

    private TextView tvProgress, tvRemaining, tvLearned, tvMissed;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vocabulary);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        tvProgress = findViewById(R.id.tvProgress);
        tvRemaining = findViewById(R.id.tvRemaining);
        tvLearned = findViewById(R.id.tvLearned);
        tvMissed = findViewById(R.id.tvMissed);
        progressBar = findViewById(R.id.progressBar);

        CardStackView cardStackView = findViewById(R.id.cardStackView);

        layoutManager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
            }

            @Override
            public void onCardSwiped(Direction direction) {
                if (direction == Direction.Right)
                    learned++;
                else
                    missed++;
                updateStats();
            }

            @Override
            public void onCardRewound() {
            }

            @Override
            public void onCardCanceled() {
            }

            @Override
            public void onCardAppeared(android.view.View view, int position) {
            }

            @Override
            public void onCardDisappeared(android.view.View view, int position) {
            }
        });

        cardStackView.setLayoutManager(layoutManager);

        // Luôn load từ DB
        int lessonId = getIntent().getIntExtra("lessonId", -1);
        List<VocabItem> items = new ArrayList<>();

        if (lessonId > 0) {
            AppDatabase db = AppDatabase.getInstance(this);
            List<VocabEntity> dbItems = db.vocabDao().getVocabByLessonId(lessonId);
            for (VocabEntity e : dbItems) {
                items.add(new VocabItem(e.english, e.vietnamese, e.phonetic, e.wordType));
            }
        }

        if (items.isEmpty()) {
            Toast.makeText(this, "Chưa có từ vựng nào!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        totalItems = items.size();
        updateStats();

        VocabCardAdapter adapter = new VocabCardAdapter(items);
        cardStackView.setAdapter(adapter);

        ImageButton btnSkip = findViewById(R.id.btnSkip);
        btnSkip.setOnClickListener(v -> {
            SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Left).build();
            layoutManager.setSwipeAnimationSetting(setting);
            cardStackView.swipe();
        });

        ImageButton btnKnow = findViewById(R.id.btnKnow);
        btnKnow.setOnClickListener(v -> {
            SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Right).build();
            layoutManager.setSwipeAnimationSetting(setting);
            cardStackView.swipe();
        });

        ImageButton btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(v -> cardStackView.rewind());
    }

    private void updateStats() {
        int swiped = learned + missed;
        int remaining = totalItems - swiped;
        if (remaining < 0)
            remaining = 0;
        tvProgress.setText(swiped + " of " + totalItems);
        tvRemaining.setText(String.valueOf(remaining));
        tvLearned.setText(String.valueOf(learned));
        tvMissed.setText(String.valueOf(missed));
        progressBar.setProgress(totalItems > 0 ? (swiped * 100 / totalItems) : 0);
    }
}
