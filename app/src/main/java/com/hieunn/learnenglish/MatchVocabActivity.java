package com.hieunn.learnenglish;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.VocabEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchVocabActivity extends AppCompatActivity {
    private TextView tvTimer;
    private RecyclerView rvCards;
    private LinearLayout llResult;
    private TextView tvFinalTime;
    private Button btnPlayAgain;

    private List<MatchCard> cardList;
    private MatchAdapter adapter;

    private long startTime;
    private final Handler timerHandler = new Handler(Looper.getMainLooper());
    private boolean isPlaying = false;
    private int matchesFound = 0;
    private int totalPairs = 0;

    private int firstSelectedIndex = -1;
    private boolean isProcessing = false;

    private final Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if (isPlaying) {
                long elapsed = System.currentTimeMillis() - startTime;
                float seconds = elapsed / 1000f;
                tvTimer.setText(String.format("%.1fs", seconds));
                timerHandler.postDelayed(this, 100);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_match_vocab);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvTimer = findViewById(R.id.tvTimer);
        rvCards = findViewById(R.id.rvCards);
        llResult = findViewById(R.id.llResult);
        tvFinalTime = findViewById(R.id.tvFinalTime);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        btnPlayAgain.setOnClickListener(v -> startGame());

        rvCards.setLayoutManager(new GridLayoutManager(this, 3));
        
        startGame();
    }

    private void startGame() {
        llResult.setVisibility(View.GONE);
        tvTimer.setText("0.0s");
        matchesFound = 0;
        firstSelectedIndex = -1;
        isProcessing = false;

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
            Toast.makeText(this, "Không có từ vựng", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Collections.shuffle(items);
        int takeCount = Math.min(items.size(), 6);
        totalPairs = takeCount;
        
        cardList = new ArrayList<>();
        int pairId = 0;
        for (int i = 0; i < takeCount; i++) {
            VocabItem item = items.get(i);
            cardList.add(new MatchCard(pairId, item.getEnglishWord(), true));
            cardList.add(new MatchCard(pairId, item.getVietnameseMeaning(), false));
            pairId++;
        }
        
        Collections.shuffle(cardList);
        
        adapter = new MatchAdapter();
        rvCards.setAdapter(adapter);

        // Start timer
        startTime = System.currentTimeMillis();
        isPlaying = true;
        timerHandler.postDelayed(timerRunnable, 100);
    }

    private void handleCardClick(int position) {
        if (isProcessing) return;
        MatchCard clickedCard = cardList.get(position);
        if (clickedCard.isMatched) return;

        if (firstSelectedIndex == -1) {
            // Select first card
            firstSelectedIndex = position;
            clickedCard.isSelected = true;
            adapter.notifyItemChanged(position);
        } else {
            // Clicked same card again
            if (firstSelectedIndex == position) {
                clickedCard.isSelected = false;
                firstSelectedIndex = -1;
                adapter.notifyItemChanged(position);
                return;
            }

            // Clicked second card
            isProcessing = true;
            MatchCard firstCard = cardList.get(firstSelectedIndex);
            clickedCard.isSelected = true;
            adapter.notifyItemChanged(position);

            if (firstCard.pairId == clickedCard.pairId) {
                // Match
                MatchesFound();
                int currentFirstIndex = firstSelectedIndex;
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    firstCard.isMatched = true;
                    clickedCard.isMatched = true;
                    adapter.notifyItemChanged(currentFirstIndex);
                    adapter.notifyItemChanged(position);
                    isProcessing = false;
                }, 300);
                firstSelectedIndex = -1;
            } else {
                // Not match
                firstCard.isWrong = true;
                clickedCard.isWrong = true;
                adapter.notifyItemChanged(firstSelectedIndex);
                adapter.notifyItemChanged(position);

                int currentFirstIndex = firstSelectedIndex;
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    firstCard.isSelected = false;
                    clickedCard.isSelected = false;
                    firstCard.isWrong = false;
                    clickedCard.isWrong = false;
                    adapter.notifyItemChanged(currentFirstIndex);
                    adapter.notifyItemChanged(position);
                    isProcessing = false;
                }, 500);
                firstSelectedIndex = -1;
            }
        }
    }

    private void MatchesFound() {
        matchesFound++;
        if (matchesFound >= totalPairs) {
            isPlaying = false;
            long elapsed = System.currentTimeMillis() - startTime;
            float seconds = elapsed / 1000f;
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                llResult.setVisibility(View.VISIBLE);
                tvFinalTime.setText(String.format("Thành tích: %.1fs", seconds));
            }, 500);
        }
    }

    @Override
    protected void onDestroy() {
        isPlaying = false;
        timerHandler.removeCallbacks(timerRunnable);
        super.onDestroy();
    }

    private static class MatchCard {
        int pairId;
        String text;
        boolean isEnglish;
        boolean isSelected = false;
        boolean isMatched = false;
        boolean isWrong = false;

        public MatchCard(int pairId, String text, boolean isEnglish) {
            this.pairId = pairId;
            this.text = text;
            this.isEnglish = isEnglish;
        }
    }

    private class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match_card, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            MatchCard card = cardList.get(position);
            holder.tvText.setText(card.text);

            if (card.isMatched) {
                holder.cardView.setVisibility(View.INVISIBLE);
            } else {
                holder.cardView.setVisibility(View.VISIBLE);
                if (card.isWrong) {
                    holder.cardView.setCardBackgroundColor(0xFFFEE2E2); // Red
                } else if (card.isSelected) {
                    holder.cardView.setCardBackgroundColor(0xFFDBEAFE); // Blue
                } else {
                    holder.cardView.setCardBackgroundColor(0xFFFFFFFF); // White
                }
            }

            holder.cardView.setOnClickListener(v -> handleCardClick(holder.getAdapterPosition()));
        }

        @Override
        public int getItemCount() {
            return cardList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            CardView cardView;
            TextView tvText;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                cardView = itemView.findViewById(R.id.cardView);
                tvText = itemView.findViewById(R.id.tvText);
            }
        }
    }
}
