package com.hieunn.learnenglish;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JapaneseGrammarPointAdapter extends RecyclerView.Adapter<JapaneseGrammarPointAdapter.ViewHolder> {

    public interface OnPointClickListener {
        void onPointClick(JapaneseGrammarPoint point, int position);
    }

    private final Context context;
    private final int lessonNumber;
    private List<JapaneseGrammarPoint> pointList = new ArrayList<>();
    private final OnPointClickListener listener;

    public JapaneseGrammarPointAdapter(Context context, int lessonNumber, OnPointClickListener listener) {
        this.context = context;
        this.lessonNumber = lessonNumber;
        this.listener = listener;
    }

    public void setPoints(List<JapaneseGrammarPoint> points) {
        this.pointList = points != null ? points : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grammar_point_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JapaneseGrammarPoint point = pointList.get(position);
        holder.bind(point, lessonNumber, context, listener);
    }

    @Override
    public int getItemCount() {
        return pointList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvPointNumberBadge;
        private final TextView tvPointTitle;
        private final TextView tvPointMeaning;
        private final LinearLayout containerStatusBadge;
        private final ImageView ivCheckIcon;
        private final TextView tvStatusText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPointNumberBadge = itemView.findViewById(R.id.tvPointNumberBadge);
            tvPointTitle = itemView.findViewById(R.id.tvPointTitle);
            tvPointMeaning = itemView.findViewById(R.id.tvPointMeaning);
            containerStatusBadge = itemView.findViewById(R.id.containerStatusBadge);
            ivCheckIcon = itemView.findViewById(R.id.ivCheckIcon);
            tvStatusText = itemView.findViewById(R.id.tvStatusText);
        }

        public void bind(JapaneseGrammarPoint point, int lessonNumber, Context context, OnPointClickListener listener) {
            String numStr = String.format(Locale.getDefault(), "%02d", point.getPointNumber());
            tvPointNumberBadge.setText(numStr);
            tvPointTitle.setText(point.getTitle());
            tvPointMeaning.setText(point.getMeaning());

            boolean isCompleted = JapaneseGrammarProgressManager.isPointCompleted(context, lessonNumber, point.getPointNumber());

            if (isCompleted) {
                containerStatusBadge.setBackgroundResource(R.drawable.bg_badge_green);
                ivCheckIcon.setVisibility(View.VISIBLE);
                ivCheckIcon.setImageResource(R.drawable.ic_check);
                ivCheckIcon.setColorFilter(Color.parseColor("#16A34A"));
                tvStatusText.setText("Đã học");
                tvStatusText.setTextColor(Color.parseColor("#16A34A"));
            } else {
                containerStatusBadge.setBackgroundResource(R.drawable.bg_badge_gray);
                ivCheckIcon.setVisibility(View.GONE);
                tvStatusText.setText("Học ngay →");
                tvStatusText.setTextColor(Color.parseColor("#475569"));
            }

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onPointClick(point, getAdapterPosition());
                }
            });
        }
    }
}
