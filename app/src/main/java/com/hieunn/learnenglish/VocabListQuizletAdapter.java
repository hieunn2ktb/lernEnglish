package com.hieunn.learnenglish;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VocabListQuizletAdapter extends RecyclerView.Adapter<VocabListQuizletAdapter.ViewHolder> {
    private List<VocabItem> items;
    private TextToSpeech tts;
    private boolean[] starred;

    public VocabListQuizletAdapter(List<VocabItem> items, TextToSpeech tts) {
        this.items = items;
        this.tts = tts;
        this.starred = new boolean[items.size()];
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vocab_list_quizlet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VocabItem item = items.get(position);
        holder.tvEnglish.setText(item.getEnglishWord());
        holder.tvVietnamese.setText(item.getVietnameseMeaning());

        holder.btnPronounce.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak(item.getEnglishWord(), TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                Toast.makeText(holder.itemView.getContext(), "TextToSpeech chưa sẵn sàng", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle star toggle
        holder.btnStar.setOnClickListener(v -> {
            starred[position] = !starred[position];
            if (starred[position]) {
                holder.btnStar.setColorFilter(0xFFFBBF24); // Yellow tint
            } else {
                holder.btnStar.clearColorFilter(); // Original outline color
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEnglish;
        TextView tvVietnamese;
        ImageButton btnPronounce;
        ImageButton btnStar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEnglish = itemView.findViewById(R.id.tvEnglish);
            tvVietnamese = itemView.findViewById(R.id.tvVietnamese);
            btnPronounce = itemView.findViewById(R.id.btnPronounce);
            btnStar = itemView.findViewById(R.id.btnStar);
        }
    }
}
