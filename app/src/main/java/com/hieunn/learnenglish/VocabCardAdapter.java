package com.hieunn.learnenglish;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VocabCardAdapter extends RecyclerView.Adapter<VocabCardAdapter.ViewHolder> {

    private List<VocabItem> items;

    public VocabCardAdapter(List<VocabItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vocab_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VocabItem item = items.get(position);
        holder.tvEnglishWord.setText(item.getEnglishWord());
        holder.tvVietnameseMeaning.setText(item.getVietnameseMeaning());
        holder.tvPhonetic.setText(item.getPhonetic());
        holder.tvWordType.setText(item.getWordType());

        // Reset
        holder.tvVietnameseMeaning.setVisibility(View.INVISIBLE);
        holder.tvTapHint.setVisibility(View.VISIBLE);

        holder.itemView.setOnClickListener(v -> {
            boolean isVisible = holder.tvVietnameseMeaning.getVisibility() == View.VISIBLE;
            holder.tvVietnameseMeaning.setVisibility(isVisible ? View.INVISIBLE : View.VISIBLE);
            holder.tvTapHint.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEnglishWord, tvVietnameseMeaning, tvPhonetic, tvWordType, tvTapHint;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEnglishWord = itemView.findViewById(R.id.tvEnglishWord);
            tvVietnameseMeaning = itemView.findViewById(R.id.tvVietnameseMeaning);
            tvPhonetic = itemView.findViewById(R.id.tvPhonetic);
            tvWordType = itemView.findViewById(R.id.tvWordType);
            tvTapHint = itemView.findViewById(R.id.tvTapHint);
        }
    }
}
