package com.hieunn.learnenglish;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KanjiReadingAdapter extends RecyclerView.Adapter<KanjiReadingAdapter.ViewHolder> {

    public interface OnSentenceSpeakerClickListener {
        void onSpeakerClick(String sentence);
    }

    private final List<String> sentences;
    private final OnSentenceSpeakerClickListener speakerListener;

    public KanjiReadingAdapter(List<String> sentences, OnSentenceSpeakerClickListener speakerListener) {
        this.sentences = sentences;
        this.speakerListener = speakerListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kanji_reading, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String sentence = sentences.get(position);
        holder.tvSentenceIndex.setText(String.valueOf(position + 1));
        holder.tvSentenceText.setText(sentence);

        holder.btnSpeakSentence.setOnClickListener(v -> {
            if (speakerListener != null) {
                speakerListener.onSpeakerClick(sentence);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sentences != null ? sentences.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSentenceIndex;
        TextView tvSentenceText;
        ImageButton btnSpeakSentence;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSentenceIndex = itemView.findViewById(R.id.tvSentenceIndex);
            tvSentenceText = itemView.findViewById(R.id.tvSentenceText);
            btnSpeakSentence = itemView.findViewById(R.id.btnSpeakSentence);
        }
    }
}
