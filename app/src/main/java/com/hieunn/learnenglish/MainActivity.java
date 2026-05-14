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
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
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

        Button btnLearnAll = findViewById(R.id.btnLearnAll);
        if (btnLearnAll != null) {
            btnLearnAll.setOnClickListener(v -> {
                showSelectLessonsDialog();
            });
        }

        loadLessons();
    }

    private void showSelectLessonsDialog() {
        AppDatabase db = AppDatabase.getInstance(this);
        List<LessonEntity> lessons = db.lessonDao().getAllLessons();
        if (lessons.isEmpty()) {
            android.widget.Toast.makeText(this, "Chưa có bài học nào", android.widget.Toast.LENGTH_SHORT).show();
            return;
        }

        lessons.sort((l1, l2) -> {
            int d1 = extractDayNumber(l1.title);
            int d2 = extractDayNumber(l2.title);
            if (d1 > 0 && d2 > 0) return Integer.compare(d1, d2);
            if (d1 > 0) return -1;
            if (d2 > 0) return 1;
            if (l1.title != null && l2.title != null) return l1.title.compareTo(l2.title);
            return 0;
        });

        String[] lessonTitles = new String[lessons.size()];
        boolean[] checkedItems = new boolean[lessons.size()];

        for (int i = 0; i < lessons.size(); i++) {
            lessonTitles[i] = lessons.get(i).title;
            checkedItems[i] = true; // Mặc định chọn tất cả
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Chọn các bài muốn học")
                .setMultiChoiceItems(lessonTitles, checkedItems, (dialogInterface, which, isChecked) -> {
                    checkedItems[which] = isChecked;
                })
                .setPositiveButton("Bắt đầu", (dialogInterface, which) -> {
                    java.util.ArrayList<Integer> selectedIds = new java.util.ArrayList<>();
                    for (int i = 0; i < checkedItems.length; i++) {
                        if (checkedItems[i]) {
                            selectedIds.add(lessons.get(i).id);
                        }
                    }
                    if (!selectedIds.isEmpty()) {
                        Intent intent = new Intent(this, LearnVocabActivity.class);
                        intent.putIntegerArrayListExtra("selectedLessonIds", selectedIds);
                        startActivity(intent);
                    } else {
                        android.widget.Toast.makeText(this, "Vui lòng chọn ít nhất 1 bài", android.widget.Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Hủy", null)
                .setNeutralButton("Chọn/Bỏ tất cả", null);
                
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(dialogInterface -> {
            Button neutralBtn = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);
            neutralBtn.setOnClickListener(v -> {
                boolean isAllChecked = true;
                for (boolean b : checkedItems) {
                    if (!b) {
                        isAllChecked = false;
                        break;
                    }
                }
                
                boolean newState = !isAllChecked;
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = newState;
                    dialog.getListView().setItemChecked(i, newState);
                }
            });
        });
        
        dialog.show();
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

        // Sort by Day Number to fix ordering issues after DB resets 
        lessons.sort((l1, l2) -> {
            int d1 = extractDayNumber(l1.title);
            int d2 = extractDayNumber(l2.title);
            if (d1 > 0 && d2 > 0) return Integer.compare(d1, d2);
            if (d1 > 0) return -1;
            if (d2 > 0) return 1;
            if (l1.title != null && l2.title != null) return l1.title.compareTo(l2.title);
            return 0;
        });

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

    private int extractDayNumber(String title) {
        if (title != null && title.toLowerCase().startsWith("ngày ")) {
            try {
                String numStr = title.substring(5).split(":")[0].trim();
                return Integer.parseInt(numStr);
            } catch (Exception e) {
                // Return -1 if parse fails
            }
        }
        return -1;
    }
}