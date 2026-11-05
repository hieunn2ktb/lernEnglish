package com.hieunn.learnenglish;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean isJapaneseTab = false;
    private boolean isKanjiSubTab = false;
    private TextView tvHeaderTitle;
    private TextView tabEnglish;
    private TextView tabJapanese;
    private View layoutJapaneseSubTabs;
    private TextView subTabVocab;
    private TextView subTabKanji;
    private Button btnCreateLesson;
    private Button btnLearnAll;

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

        // Chèn dữ liệu mẫu tiếng Anh và 50 bài tiếng Nhật từ PDF khi app chạy lần đầu
        DatabaseSeeder.seedIfNeeded(this);

        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tabEnglish = findViewById(R.id.tabEnglish);
        tabJapanese = findViewById(R.id.tabJapanese);
        layoutJapaneseSubTabs = findViewById(R.id.layoutJapaneseSubTabs);
        subTabVocab = findViewById(R.id.subTabVocab);
        subTabKanji = findViewById(R.id.subTabKanji);
        btnCreateLesson = findViewById(R.id.btnCreateLesson);
        btnLearnAll = findViewById(R.id.btnLearnAll);

        tabEnglish.setOnClickListener(v -> switchTab(false));
        tabJapanese.setOnClickListener(v -> switchTab(true));

        if (subTabVocab != null) subTabVocab.setOnClickListener(v -> switchJapaneseSubTab(false));
        if (subTabKanji != null) subTabKanji.setOnClickListener(v -> switchJapaneseSubTab(true));

        // Nút tạo bài học mới
        btnCreateLesson.setOnClickListener(v -> {
            startActivity(new Intent(this, CreateLessonActivity.class));
        });

        if (btnLearnAll != null) {
            btnLearnAll.setOnClickListener(v -> {
                showSelectLessonsDialog();
            });
        }

        updateTabUI();
        loadLessons();
    }

    private void switchTab(boolean toJapanese) {
        if (isJapaneseTab == toJapanese) return;
        isJapaneseTab = toJapanese;
        updateTabUI();
        loadLessons();
    }

    private void switchJapaneseSubTab(boolean toKanji) {
        if (isKanjiSubTab == toKanji) return;
        isKanjiSubTab = toKanji;
        updateSubTabUI();
        loadLessons();
    }

    private void updateSubTabUI() {
        if (subTabVocab == null || subTabKanji == null) return;
        if (!isKanjiSubTab) {
            subTabVocab.setBackgroundResource(R.drawable.bg_tab_active_red);
            subTabVocab.setTextColor(0xFFFFFFFF);
            subTabKanji.setBackgroundColor(0);
            subTabKanji.setTextColor(0xFF4B5563);

            if (tvHeaderTitle != null) tvHeaderTitle.setText("Tiếng Nhật Minna");
            if (btnLearnAll != null) {
                btnLearnAll.setVisibility(View.VISIBLE);
                btnLearnAll.setText("Ôn tập từ");
            }
        } else {
            subTabKanji.setBackgroundResource(R.drawable.bg_tab_active_red);
            subTabKanji.setTextColor(0xFFFFFFFF);
            subTabVocab.setBackgroundColor(0);
            subTabVocab.setTextColor(0xFF4B5563);

            if (tvHeaderTitle != null) tvHeaderTitle.setText("Chữ Hán Kanji (20 bài)");
            if (btnLearnAll != null) {
                btnLearnAll.setVisibility(View.GONE);
            }
        }
    }

    private void updateTabUI() {
        if (!isJapaneseTab) {
            tabEnglish.setBackgroundResource(R.drawable.bg_tab_active_blue);
            tabEnglish.setTextColor(0xFFFFFFFF);
            tabJapanese.setBackgroundColor(0x00000000);
            tabJapanese.setTextColor(0xFF4B5563);

            if (layoutJapaneseSubTabs != null) layoutJapaneseSubTabs.setVisibility(View.GONE);
            if (tvHeaderTitle != null) tvHeaderTitle.setText("Lộ Trình Tiếng Anh");
            if (btnCreateLesson != null) btnCreateLesson.setVisibility(View.VISIBLE);
            if (btnLearnAll != null) {
                btnLearnAll.setVisibility(View.VISIBLE);
                btnLearnAll.setText("Học tất cả");
            }
        } else {
            tabJapanese.setBackgroundResource(R.drawable.bg_tab_active_red);
            tabJapanese.setTextColor(0xFFFFFFFF);
            tabEnglish.setBackgroundColor(0x00000000);
            tabEnglish.setTextColor(0xFF4B5563);

            if (layoutJapaneseSubTabs != null) layoutJapaneseSubTabs.setVisibility(View.VISIBLE);
            if (btnCreateLesson != null) btnCreateLesson.setVisibility(View.GONE);

            updateSubTabUI();
        }
    }

    private void showSelectLessonsDialog() {
        AppDatabase db = AppDatabase.getInstance(this);
        List<LessonEntity> allLessons = db.lessonDao().getAllLessons();
        List<LessonEntity> lessons = new ArrayList<>();
        for (LessonEntity l : allLessons) {
            if (isJapaneseTab == isJapaneseLesson(l)) {
                lessons.add(l);
            }
        }

        if (lessons.isEmpty()) {
            android.widget.Toast.makeText(this, "Chưa có bài học nào", android.widget.Toast.LENGTH_SHORT).show();
            return;
        }

        if (isJapaneseTab) {
            lessons.sort((l1, l2) -> {
                int n1 = extractLessonNumber(l1.title);
                int n2 = extractLessonNumber(l2.title);
                if (n1 > 0 && n2 > 0) return Integer.compare(n1, n2);
                if (n1 > 0) return -1;
                if (n2 > 0) return 1;
                return Integer.compare(l1.id, l2.id);
            });
        } else {
            lessons.sort((l1, l2) -> {
                int d1 = extractDayNumber(l1.title);
                int d2 = extractDayNumber(l2.title);
                if (d1 > 0 && d2 > 0) return Integer.compare(d1, d2);
                if (d1 > 0) return -1;
                if (d2 > 0) return 1;
                if (l1.title != null && l2.title != null) return l1.title.compareTo(l2.title);
                return 0;
            });
        }

        String[] lessonTitles = new String[lessons.size()];
        boolean[] checkedItems = new boolean[lessons.size()];

        for (int i = 0; i < lessons.size(); i++) {
            lessonTitles[i] = lessons.get(i).title;
            checkedItems[i] = true; // Mặc định chọn tất cả
        }

        String dialogTitle = isJapaneseTab ? "Chọn các bài tiếng Nhật muốn học" : "Chọn các bài muốn học";

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(dialogTitle)
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

        if (isJapaneseTab && isKanjiSubTab) {
            loadKanjiLessons(layoutLessons);
            return;
        }

        AppDatabase db = AppDatabase.getInstance(this);
        List<LessonEntity> allLessons = db.lessonDao().getAllLessons();
        List<LessonEntity> lessons = new ArrayList<>();
        for (LessonEntity l : allLessons) {
            if (isJapaneseTab == isJapaneseLesson(l)) {
                lessons.add(l);
            }
        }

        if (isJapaneseTab) {
            lessons.sort((l1, l2) -> {
                int n1 = extractLessonNumber(l1.title);
                int n2 = extractLessonNumber(l2.title);
                if (n1 > 0 && n2 > 0) return Integer.compare(n1, n2);
                if (n1 > 0) return -1;
                if (n2 > 0) return 1;
                return Integer.compare(l1.id, l2.id);
            });
        } else {
            lessons.sort((l1, l2) -> {
                int d1 = extractDayNumber(l1.title);
                int d2 = extractDayNumber(l2.title);
                if (d1 > 0 && d2 > 0) return Integer.compare(d1, d2);
                if (d1 > 0) return -1;
                if (d2 > 0) return 1;
                if (l1.title != null && l2.title != null) return l1.title.compareTo(l2.title);
                return 0;
            });
        }

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
            if (isJapaneseTab) {
                badge.setBackgroundResource(R.drawable.bg_badge_red);
                int lNum = extractLessonNumber(lesson.title);
                badge.setText(String.valueOf(lNum > 0 ? lNum : (i + 1)));
                badge.setTextColor(0xFFDC2626);
            } else {
                badge.setBackgroundResource(R.drawable.bg_badge_blue);
                badge.setText(String.valueOf(i + 1));
                badge.setTextColor(0xFF3B82F6);
            }
            badge.setGravity(Gravity.CENTER);
            badge.setTextSize(17);
            badge.setTypeface(null, android.graphics.Typeface.BOLD);

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
            tvTitle.setTextSize(16);
            tvTitle.setTypeface(null, android.graphics.Typeface.BOLD);

            TextView tvDesc = new TextView(this);
            String descText = (lesson.description != null && !lesson.description.isEmpty())
                    ? lesson.description
                    : "Bài học từ vựng";
            tvDesc.setText(descText);
            tvDesc.setTextColor(0xFF6B7280);
            tvDesc.setTextSize(13);

            textLayout.addView(tvTitle);
            textLayout.addView(tvDesc);

            // Tiến độ và thống kê
            int percent = LessonProgressManager.getDayProgressPercent(this, lesson.id);
            int correct = LessonProgressManager.getTotalCorrect(this, lesson.id);
            int wrong = LessonProgressManager.getTotalWrong(this, lesson.id);

            TextView tvProgress = new TextView(this);
            String progressStr = "Tiến độ: " + percent + "%";
            if (correct + wrong > 0) {
                progressStr += " • Đúng: " + correct + " | Sai: " + wrong;
            }
            tvProgress.setText(progressStr);
            tvProgress.setTextSize(12);
            tvProgress.setTextColor(percent >= 100 ? 0xFF059669 : (percent > 0 ? (isJapaneseTab ? 0xFFDC2626 : 0xFF2563EB) : 0xFF9CA3AF));
            LinearLayout.LayoutParams progressParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            progressParams.topMargin = dpToPx(4);
            tvProgress.setLayoutParams(progressParams);
            textLayout.addView(tvProgress);

            ProgressBar pb = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            LinearLayout.LayoutParams pbParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(4));
            pbParams.topMargin = dpToPx(6);
            pb.setLayoutParams(pbParams);
            pb.setMax(100);
            pb.setProgress(percent);
            pb.setProgressDrawable(androidx.core.content.ContextCompat.getDrawable(
                    this, percent >= 100 ? R.drawable.progress_bar_green : (isJapaneseTab ? R.drawable.progress_bar_pink : R.drawable.progress_bar_blue)));
            textLayout.addView(pb);

            // Badge phần trăm bên phải
            TextView tvRightBadge = new TextView(this);
            LinearLayout.LayoutParams rightBadgeParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            rightBadgeParams.leftMargin = dpToPx(8);
            tvRightBadge.setLayoutParams(rightBadgeParams);
            tvRightBadge.setPadding(dpToPx(8), dpToPx(4), dpToPx(8), dpToPx(4));
            tvRightBadge.setTextSize(12);
            tvRightBadge.setTypeface(null, android.graphics.Typeface.BOLD);
            if (percent >= 100) {
                tvRightBadge.setText("100% ✓");
                tvRightBadge.setBackgroundResource(R.drawable.bg_badge_green);
                tvRightBadge.setTextColor(0xFF065F46);
            } else if (percent > 0) {
                tvRightBadge.setText(percent + "%");
                if (isJapaneseTab) {
                    tvRightBadge.setBackgroundResource(R.drawable.bg_badge_red);
                    tvRightBadge.setTextColor(0xFFDC2626);
                } else {
                    tvRightBadge.setBackgroundResource(R.drawable.bg_badge_blue);
                    tvRightBadge.setTextColor(0xFF2563EB);
                }
            } else {
                tvRightBadge.setText("0%");
                tvRightBadge.setBackgroundResource(R.drawable.bg_badge_gray);
                tvRightBadge.setTextColor(0xFF6B7280);
            }

            innerLayout.addView(badge);
            innerLayout.addView(textLayout);
            innerLayout.addView(tvRightBadge);
            card.addView(innerLayout);

            // Bấm để vào học
            card.setOnClickListener(v -> {
                if (isJapaneseTab) {
                    // Tiếng Nhật: Mở trung tâm học từ vựng bài học
                    Intent intent = new Intent(this, UnitDetailActivity.class);
                    intent.putExtra("lessonId", lesson.id);
                    startActivity(intent);
                } else {
                    // Tiếng Anh: Mở lộ trình 4 bước DayActivity
                    Intent intent = new Intent(this, DayActivity.class);
                    intent.putExtra("lessonId", lesson.id);
                    startActivity(intent);
                }
            });

            // Nhấn giữ để xóa / đặt lại
            card.setOnLongClickListener(v -> {
                new AlertDialog.Builder(this)
                        .setTitle(isJapaneseTab ? "Tùy chọn bài học" : "Xóa bài học")
                        .setMessage(isJapaneseTab
                                ? "Bạn muốn đặt lại tiến độ học cho \"" + lesson.title + "\"?"
                                : "Bạn có chắc muốn xóa \"" + lesson.title + "\"?\nTất cả từ vựng và câu hỏi sẽ bị xóa theo.")
                        .setPositiveButton(isJapaneseTab ? "Đặt lại tiến độ" : "Xóa", (dialog, which) -> {
                            if (isJapaneseTab) {
                                LessonProgressManager.resetProgress(this, lesson.id);
                                loadLessons();
                            } else {
                                db.vocabDao().deleteByLessonId(lesson.id);
                                db.grammarQuizDao().deleteByLessonId(lesson.id);
                                db.vocabQuizDao().deleteByLessonId(lesson.id);
                                db.lessonDao().deleteLessonById(lesson.id);
                                loadLessons();
                            }
                        })
                        .setNegativeButton("Hủy", null)
                        .show();
                return true;
            });

            layoutLessons.addView(card);
        }
    }

    public static boolean isJapaneseLesson(LessonEntity lesson) {
        if (lesson == null) return false;
        if ("ja".equalsIgnoreCase(lesson.videoUrl)) return true;
        if (lesson.title != null) {
            String lower = lesson.title.toLowerCase();
            if (lower.startsWith("bài ") || lower.contains("nihongo") || lower.contains("tiếng nhật")) {
                return true;
            }
        }
        return false;
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

    private int extractLessonNumber(String title) {
        if (title != null) {
            String lower = title.toLowerCase().trim();
            if (lower.startsWith("bài ")) {
                try {
                    String numStr = lower.substring(4).split("[:\\s]")[0].trim();
                    return Integer.parseInt(numStr);
                } catch (Exception e) {
                    // Return -1 if parse fails
                }
            }
        }
        return -1;
    }

    private void loadKanjiLessons(LinearLayout layoutLessons) {
        layoutLessons.removeAllViews();
        List<KanjiLesson> kanjiLessons = KanjiDataManager.getAllLessons(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        for (KanjiLesson lesson : kanjiLessons) {
            View itemView = inflater.inflate(R.layout.item_kanji_lesson, layoutLessons, false);

            TextView tvBadge = itemView.findViewById(R.id.tvLessonBadge);
            TextView tvTitle = itemView.findViewById(R.id.tvLessonTitle);
            TextView tvDesc = itemView.findViewById(R.id.tvLessonDesc);
            TextView tvCountBadge = itemView.findViewById(R.id.tvCountBadge);
            LinearLayout layoutChips = itemView.findViewById(R.id.layoutKanjiChips);
            View scrollChips = itemView.findViewById(R.id.scrollKanjiChips);

            tvTitle.setText(lesson.getTitle());

            if (lesson.isTest()) {
                tvBadge.setText("TEST");
                tvBadge.setTextSize(10);
                tvDesc.setText("Kiểm tra 30 câu ôn tập tổng hợp (Bài 1 - 10)");
                tvCountBadge.setText("30 câu hỏi");
                if (scrollChips != null) scrollChips.setVisibility(View.GONE);
            } else {
                tvBadge.setText(String.valueOf(lesson.getLessonNumber()));
                tvBadge.setTextSize(17);
                tvDesc.setText(lesson.getDescription());
                tvCountBadge.setText(lesson.getTotalKanji() + " chữ");

                // Preview chips for Kanji
                if (layoutChips != null) {
                    layoutChips.removeAllViews();
                    for (KanjiItem k : lesson.getKanjis()) {
                        TextView chip = new TextView(this);
                        chip.setText(k.getKanji() + "\n" + k.getHanViet());
                        chip.setTextSize(11);
                        chip.setTypeface(null, android.graphics.Typeface.BOLD);
                        chip.setGravity(Gravity.CENTER);
                        chip.setTextColor(0xFF1E293B);
                        chip.setBackgroundResource(R.drawable.bg_ime_candidate);
                        chip.setPadding(dpToPx(10), dpToPx(5), dpToPx(10), dpToPx(5));
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        lp.rightMargin = dpToPx(8);
                        chip.setLayoutParams(lp);
                        layoutChips.addView(chip);
                    }
                }
            }

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(this, KanjiDetailActivity.class);
                intent.putExtra("lessonId", lesson.getId());
                startActivity(intent);
            });

            layoutLessons.addView(itemView);
        }
    }
}
