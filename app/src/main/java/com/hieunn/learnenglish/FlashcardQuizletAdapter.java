package com.hieunn.learnenglish;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FlashcardQuizletAdapter extends RecyclerView.Adapter<FlashcardQuizletAdapter.ViewHolder> {
    private List<VocabItem> items;
    private android.speech.tts.TextToSpeech tts;

    public FlashcardQuizletAdapter(List<VocabItem> items, android.speech.tts.TextToSpeech tts) {
        this.items = items;
        this.tts = tts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flashcard_quizlet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VocabItem item = items.get(position);
        holder.tvWord.setText(item.getEnglishWord());

        holder.itemView.setOnClickListener(v -> {
            if (holder.tvWord.getText().toString().equals(item.getEnglishWord())) {
                holder.tvWord.setText(item.getVietnameseMeaning());
                holder.tvWord.setTextColor(0xFF374151); // Dark Gray
            } else {
                holder.tvWord.setText(item.getEnglishWord());
                holder.tvWord.setTextColor(0xFF1F2937); // Near Black
                if (tts != null) {
                    tts.speak(item.getEnglishWord(), android.speech.tts.TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWord;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWord = itemView.findViewById(R.id.tvWord);
        }
    }
}
