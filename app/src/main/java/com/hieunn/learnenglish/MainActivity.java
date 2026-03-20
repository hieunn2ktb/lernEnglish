package com.hieunn.learnenglish;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.DatabaseSeeder;
import com.hieunn.learnenglish.db.LessonEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Chèn dữ liệu mẫu khi app chạy lần đầu
        DatabaseSeeder.seedIfNeeded(this);

        // Nút tạo bài học mới
        Button btnCreate = findViewById(R.id.btnCreateLesson);
        btnCreate.setOnClickListener(v -> {
            startActivity(new Intent(this, CreateLessonActivity.class));
        });

        loadLessons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLessons();
    }

    private void loadLessons() {
        LinearLayout layoutLessons = findViewById(R.id.layoutLessons);
        layoutLessons.removeAllViews();

        AppDatabase db = AppDatabase.getInstance(this);
        List<LessonEntity> lessons = db.lessonDao().getAllLessons();

        for (int i = 0; i < lessons.size(); i++) {
            LessonEntity lesson = lessons.get(i);

            CardView card = new CardView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            cardParams.bottomMargin = dpToPx(12);
            card.setLayoutParams(cardParams);
            card.setRadius(dpToPx(16));
            card.setCardElevation(dpToPx(3));
            card.setCardBackgroundColor(0xFFFFFFFF);

            LinearLayout innerLayout = new LinearLayout(this);
            innerLayout.setOrientation(LinearLayout.HORIZONTAL);
            innerLayout.setGravity(Gravity.CENTER_VERTICAL);
            innerLayout.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));

            // Badge số thứ tự
            TextView badge = new TextView(this);
            LinearLayout.LayoutParams badgeParams = new LinearLayout.LayoutParams(dpToPx(44), dpToPx(44));
            badge.setLayoutParams(badgeParams);
            badge.setBackgroundResource(R.drawable.bg_badge_blue);
            badge.setGravity(Gravity.CENTER);
            badge.setText(String.valueOf(i + 1));
            badge.setTextColor(0xFF3B82F6);
            badge.setTextSize(18);

            // Nội dung
            LinearLayout textLayout = new LinearLayout(this);
            textLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            textParams.leftMargin = dpToPx(12);
            textLayout.setLayoutParams(textParams);

            TextView tvTitle = new TextView(this);
            tvTitle.setText(lesson.title);
            tvTitle.setTextColor(0xFF1F2937);
            tvTitle.setTextSize(17);
            tvTitle.setTypeface(null, android.graphics.Typeface.BOLD);

            TextView tvDesc = new TextView(this);
            String descText = (lesson.description != null && !lesson.description.isEmpty())
                    ? lesson.description
                    : "Bài học tùy chỉnh";
            tvDesc.setText(descText);
            tvDesc.setTextColor(0xFF6B7280);
            tvDesc.setTextSize(13);

            textLayout.addView(tvTitle);
            textLayout.addView(tvDesc);

            innerLayout.addView(badge);
            innerLayout.addView(textLayout);
            card.addView(innerLayout);

            // Bấm để vào học
            card.setOnClickListener(v -> {
                Intent intent = new Intent(this, DayActivity.class);
                intent.putExtra("lessonId", lesson.id);
                startActivity(intent);
            });

            // Nhấn giữ để xóa
            card.setOnLongClickListener(v -> {
                new AlertDialog.Builder(this)
                        .setTitle("Xóa bài học")
                        .setMessage("Bạn có chắc muốn xóa \"" + lesson.title
                                + "\"?\nTất cả từ vựng và câu hỏi sẽ bị xóa theo.")
                        .setPositiveButton("Xóa", (dialog, which) -> {
                            db.vocabDao().deleteByLessonId(lesson.id);
                            db.grammarQuizDao().deleteByLessonId(lesson.id);
                            db.vocabQuizDao().deleteByLessonId(lesson.id);
                            db.lessonDao().deleteLessonById(lesson.id);
                            loadLessons();
                        })
                        .setNegativeButton("Hủy", null)
                        .show();
                return true;
            });

            layoutLessons.addView(card);
        }
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}