package com.hieunn.learnenglish;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KanjiCardAdapter extends RecyclerView.Adapter<KanjiCardAdapter.ViewHolder> {

    public interface OnSpeakerClickListener {
        void onSpeakerClick(KanjiItem item);
    }

    private final List<KanjiItem> items;
    private final OnSpeakerClickListener speakerListener;

    public KanjiCardAdapter(List<KanjiItem> items, OnSpeakerClickListener speakerListener) {
        this.items = items;
        this.speakerListener = speakerListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kanji_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KanjiItem item = items.get(position);
        holder.tvKanjiChar.setText(item.getKanji());
        holder.tvHanViet.setText(item.getHanViet());

        if (item.getKunyomi().isEmpty()) {
            holder.tvKunyomi.setVisibility(View.GONE);
        } else {
            holder.tvKunyomi.setVisibility(View.VISIBLE);
            holder.tvKunyomi.setText("Kun: " + item.getKunyomi());
        }

        if (item.getOnyomi().isEmpty()) {
            holder.tvOnyomi.setVisibility(View.GONE);
        } else {
            holder.tvOnyomi.setVisibility(View.VISIBLE);
            holder.tvOnyomi.setText("On: " + item.getOnyomi());
        }

        if (item.getExamples() != null && !item.getExamples().isEmpty()) {
            holder.tvExamples.setVisibility(View.VISIBLE);
            StringBuilder sb = new StringBuilder("Ví dụ: ");
            for (int i = 0; i < item.getExamples().size(); i++) {
                sb.append(item.getExamples().get(i));
                if (i < item.getExamples().size() - 1) sb.append(", ");
            }
            holder.tvExamples.setText(sb.toString());
        } else {
            holder.tvExamples.setVisibility(View.GONE);
        }

        holder.btnSpeakItem.setOnClickListener(v -> {
            if (speakerListener != null) {
                speakerListener.onSpeakerClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKanjiChar;
        TextView tvHanViet;
        TextView tvKunyomi;
        TextView tvOnyomi;
        TextView tvExamples;
        ImageButton btnSpeakItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKanjiChar = itemView.findViewById(R.id.tvKanjiChar);
            tvHanViet = itemView.findViewById(R.id.tvHanViet);
            tvKunyomi = itemView.findViewById(R.id.tvKunyomi);
            tvOnyomi = itemView.findViewById(R.id.tvOnyomi);
            tvExamples = itemView.findViewById(R.id.tvExamples);
            btnSpeakItem = itemView.findViewById(R.id.btnSpeakItem);
        }
    }
}
