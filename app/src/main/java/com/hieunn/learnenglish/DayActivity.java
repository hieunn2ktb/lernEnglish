package com.hieunn.learnenglish;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.LessonEntity;

public class DayActivity extends AppCompatActivity {

    private int lessonId;

    // Tiến độ & Thống kê
    private TextView tvDayProgressPercent;
    private ProgressBar progressDay;
    private TextView tvStatCorrect, tvStatWrong, tvStatAccuracy;

    // Timeline Steps
    private View layoutTimelineVideo;
    private TextView tvTimelineNode1, tvBadgeVideo;
    private View itemTimelineVideo;

    private View layoutTimelineVocab;
    private TextView tvTimelineNode2, tvVocabProgress, tvBadgeVocab;
    private ProgressBar progressVocabMini;
    private View itemVocab;

    private View layoutTimelineGrammar;
    private TextView tvTimelineNode3, tvGrammarProgress, tvBadgeGrammar;
    private ProgressBar progressGrammarMini;
    private View itemGrammar;

    private View layoutTimelineSummary;
    private TextView tvTimelineNode4, tvBadgeSummary, tvSummaryDesc;
    private View itemSummary;

    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_day_one);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lessonId = getIntent().getIntExtra("lessonId", -1);

        initViews();
    }

    private void initViews() {
        // Nút quay lại
        ImageButton btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        // Tiến độ & Thống kê
        tvDayProgressPercent = findViewById(R.id.tvDayProgressPercent);
        progressDay = findViewById(R.id.progressDay);
        tvStatCorrect = findViewById(R.id.tvStatCorrect);
        tvStatWrong = findViewById(R.id.tvStatWrong);
        tvStatAccuracy = findViewById(R.id.tvStatAccuracy);

        // Timeline Step 1 (Video)
        layoutTimelineVideo = findViewById(R.id.layoutTimelineVideo);
        tvTimelineNode1 = findViewById(R.id.tvTimelineNode1);
        tvBadgeVideo = findViewById(R.id.tvBadgeVideo);
        itemTimelineVideo = findViewById(R.id.itemTimelineVideo);

        // Timeline Step 2 (Từ vựng)
        layoutTimelineVocab = findViewById(R.id.layoutTimelineVocab);
        tvTimelineNode2 = findViewById(R.id.tvTimelineNode2);
        tvVocabProgress = findViewById(R.id.tvVocabProgress);
        progressVocabMini = findViewById(R.id.progressVocabMini);
        tvBadgeVocab = findViewById(R.id.tvBadgeVocab);
        itemVocab = findViewById(R.id.itemVocab);

        // Timeline Step 3 (Ngữ pháp)
        layoutTimelineGrammar = findViewById(R.id.layoutTimelineGrammar);
        tvTimelineNode3 = findViewById(R.id.tvTimelineNode3);
        tvGrammarProgress = findViewById(R.id.tvGrammarProgress);
        progressGrammarMini = findViewById(R.id.progressGrammarMini);
        tvBadgeGrammar = findViewById(R.id.tvBadgeGrammar);
        itemGrammar = findViewById(R.id.itemGrammar);

        // Timeline Step 4 (Tổng kết)
        layoutTimelineSummary = findViewById(R.id.layoutTimelineSummary);
        tvTimelineNode4 = findViewById(R.id.tvTimelineNode4);
        tvBadgeSummary = findViewById(R.id.tvBadgeSummary);
        tvSummaryDesc = findViewById(R.id.tvSummaryDesc);
        itemSummary = findViewById(R.id.itemSummary);

        // Thêm từ vựng
        View itemAddVocab = findViewById(R.id.itemAddVocab);
        if (itemAddVocab != null) {
            itemAddVocab.setOnClickListener(v -> {
                Intent intent = new Intent(this, CreateSetActivity.class);
                intent.putExtra("lessonId", lessonId);
                intent.putExtra("from_day_activity", true);
                startActivity(intent);
            });
        }

        btnContinue = findViewById(R.id.btnContinue);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lessonId == -1) return;

        AppDatabase db = AppDatabase.getInstance(this);
        LessonEntity lesson = db.lessonDao().getLessonById(lessonId);
        if (lesson == null) return;

        // Tiêu đề & Mô tả bài học
        TextView tvTitle = findViewById(R.id.tvLessonTitle);
        if (tvTitle != null) tvTitle.setText(lesson.title);

        TextView tvDesc = findViewById(R.id.tvLessonDesc);
        if (tvDesc != null && lesson.description != null && !lesson.description.isEmpty()) {
            tvDesc.setText(lesson.description);
        }

        // Cấu hình video YouTube
        setupVideoSection(lesson);

        // Cập nhật thẻ tiến độ ngày học & thống kê đúng/sai
        updateProgressAndStats();

        // Cập nhật Lộ trình học tập (Timeline)
        updateTimeline(lesson, db);
    }

    private void setupVideoSection(LessonEntity lesson) {
        View playerContainer = findViewById(R.id.youtube_player_container);
        ImageView thumbnailView = findViewById(R.id.youtube_thumbnail);

        if (lesson.videoUrl == null || lesson.videoUrl.trim().isEmpty()) {
            if (playerContainer != null) playerContainer.setVisibility(View.GONE);
            if (layoutTimelineVideo != null) layoutTimelineVideo.setVisibility(View.GONE);
            return;
        }

        if (playerContainer != null) playerContainer.setVisibility(View.VISIBLE);
        if (layoutTimelineVideo != null) layoutTimelineVideo.setVisibility(View.VISIBLE);

        String videoId = lesson.videoUrl.trim();
        if (videoId.contains("v=")) {
            videoId = videoId.split("v=")[1];
            int ampersandPosition = videoId.indexOf('&');
            if (ampersandPosition != -1) videoId = videoId.substring(0, ampersandPosition);
        } else if (videoId.contains("youtu.be/")) {
            videoId = videoId.split("youtu.be/")[1];
            int questionPosition = videoId.indexOf('?');
            if (questionPosition != -1) videoId = videoId.substring(0, questionPosition);
        }

        String finalVideoId = videoId.replaceAll("[^a-zA-Z0-9_-]", "");
        Log.d("LearnEnglish", "DayActivity Thumbnail for: '" + finalVideoId + "'");

        if (thumbnailView != null) {
            String thumbUrl = "https://img.youtube.com/vi/" + finalVideoId + "/hqdefault.jpg";
            Glide.with(this).load(thumbUrl).into(thumbnailView);
        }

        View.OnClickListener videoClickListener = v -> {
            LessonProgressManager.recordVideoWatched(this, lessonId);
            updateProgressAndStats();
            updateVideoTimelineNode();

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + finalVideoId));
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + finalVideoId));
                startActivity(intent);
            }
        };

        if (playerContainer != null) playerContainer.setOnClickListener(videoClickListener);
        if (itemTimelineVideo != null) itemTimelineVideo.setOnClickListener(videoClickListener);
    }

    private void updateProgressAndStats() {
        int dayPercent = LessonProgressManager.getDayProgressPercent(this, lessonId);
        int totalCorrect = LessonProgressManager.getTotalCorrect(this, lessonId);
        int totalWrong = LessonProgressManager.getTotalWrong(this, lessonId);
        int accuracy = LessonProgressManager.getAccuracyPercent(this, lessonId);

        if (tvDayProgressPercent != null) {
            tvDayProgressPercent.setText(dayPercent + "%");
        }
        if (progressDay != null) {
            progressDay.setProgress(dayPercent);
        }
        if (tvStatCorrect != null) {
            tvStatCorrect.setText(totalCorrect + " câu");
        }
        if (tvStatWrong != null) {
            tvStatWrong.setText(totalWrong + " câu");
        }
        if (tvStatAccuracy != null) {
            tvStatAccuracy.setText(accuracy + "%");
        }
    }

    private void updateVideoTimelineNode() {
        boolean watched = LessonProgressManager.isVideoWatched(this, lessonId);
        if (tvTimelineNode1 != null) {
            if (watched) {
                tvTimelineNode1.setBackgroundResource(R.drawable.bg_circle_green);
                tvTimelineNode1.setText("✓");
            } else {
                tvTimelineNode1.setBackgroundResource(R.drawable.bg_circle_blue);
                tvTimelineNode1.setText("1");
            }
        }
        if (tvBadgeVideo != null) {
            if (watched) {
                tvBadgeVideo.setText("Đã xem ✓");
                tvBadgeVideo.setBackgroundResource(R.drawable.bg_badge_green);
                tvBadgeVideo.setTextColor(0xFF065F46);
            } else {
                tvBadgeVideo.setText("Chưa xem");
                tvBadgeVideo.setBackgroundResource(R.drawable.bg_badge_gray);
                tvBadgeVideo.setTextColor(0xFF6B7280);
            }
        }
    }

    private void updateTimeline(LessonEntity lesson, AppDatabase db) {
        int vocabTotal = db.vocabDao().getVocabCount(lessonId);
        int grammarTotal = db.grammarQuizDao().getQuizCount(lessonId);

        // Step 1: Video
        updateVideoTimelineNode();

        // Step 2: Từ vựng
        int vocabMastered = LessonProgressManager.getVocabMastered(this, lessonId);
        int vocabPercent = LessonProgressManager.getVocabProgressPercent(this, lessonId);
        if (layoutTimelineVocab != null) {
            if (vocabTotal > 0) {
                layoutTimelineVocab.setVisibility(View.VISIBLE);
                if (tvVocabProgress != null) {
                    tvVocabProgress.setText("Đã nắm vững: " + vocabMastered + "/" + vocabTotal + " từ (" + vocabPercent + "%)");
                }
                if (progressVocabMini != null) {
                    progressVocabMini.setProgress(vocabPercent);
                }
                if (tvTimelineNode2 != null) {
                    if (vocabPercent >= 100) {
                        tvTimelineNode2.setBackgroundResource(R.drawable.bg_circle_green);
                        tvTimelineNode2.setText("✓");
                    } else if (vocabPercent > 0) {
                        tvTimelineNode2.setBackgroundResource(R.drawable.bg_circle_blue);
                        tvTimelineNode2.setText("2");
                    } else {
                        tvTimelineNode2.setBackgroundResource(R.drawable.bg_circle_gray);
                        tvTimelineNode2.setText("2");
                    }
                }
                if (tvBadgeVocab != null) {
                    if (vocabPercent >= 100) {
                        tvBadgeVocab.setText("Đã xong ✓");
                        tvBadgeVocab.setBackgroundResource(R.drawable.bg_badge_green);
                        tvBadgeVocab.setTextColor(0xFF065F46);
                    } else if (vocabPercent > 0) {
                        tvBadgeVocab.setText("Đang học (" + vocabPercent + "%)");
                        tvBadgeVocab.setBackgroundResource(R.drawable.bg_badge_blue);
                        tvBadgeVocab.setTextColor(0xFF2563EB);
                    } else {
                        tvBadgeVocab.setText("Chưa học");
                        tvBadgeVocab.setBackgroundResource(R.drawable.bg_badge_gray);
                        tvBadgeVocab.setTextColor(0xFF6B7280);
                    }
                }
                if (itemVocab != null) {
                    itemVocab.setOnClickListener(v -> {
                        Intent intent = new Intent(this, UnitDetailActivity.class);
                        intent.putExtra("lessonId", lessonId);
                        startActivity(intent);
                    });
                }
            } else {
                layoutTimelineVocab.setVisibility(View.GONE);
            }
        }

        // Step 3: Luyện ngữ pháp
        int grammarMastered = LessonProgressManager.getGrammarMastered(this, lessonId);
        int grammarPercent = LessonProgressManager.getGrammarProgressPercent(this, lessonId);
        if (layoutTimelineGrammar != null) {
            if (grammarTotal > 0) {
                layoutTimelineGrammar.setVisibility(View.VISIBLE);
                if (tvGrammarProgress != null) {
                    tvGrammarProgress.setText("Trắc nghiệm & Tự luận: " + grammarMastered + "/" + grammarTotal + " câu (" + grammarPercent + "%)");
                }
                if (progressGrammarMini != null) {
                    progressGrammarMini.setProgress(grammarPercent);
                }
                if (tvTimelineNode3 != null) {
                    if (grammarPercent >= 100) {
                        tvTimelineNode3.setBackgroundResource(R.drawable.bg_circle_green);
                        tvTimelineNode3.setText("✓");
                    } else if (grammarPercent > 0) {
                        tvTimelineNode3.setBackgroundResource(R.drawable.bg_circle_blue);
                        tvTimelineNode3.setText("3");
                    } else {
                        tvTimelineNode3.setBackgroundResource(R.drawable.bg_circle_gray);
                        tvTimelineNode3.setText("3");
                    }
                }
                if (tvBadgeGrammar != null) {
                    if (grammarPercent >= 100) {
                        tvBadgeGrammar.setText("Đã xong ✓");
                        tvBadgeGrammar.setBackgroundResource(R.drawable.bg_badge_green);
                        tvBadgeGrammar.setTextColor(0xFF065F46);
                    } else if (grammarPercent > 0) {
                        tvBadgeGrammar.setText("Đang học (" + grammarPercent + "%)");
                        tvBadgeGrammar.setBackgroundResource(R.drawable.bg_badge_blue);
                        tvBadgeGrammar.setTextColor(0xFF2563EB);
                    } else {
                        tvBadgeGrammar.setText("Chưa học");
                        tvBadgeGrammar.setBackgroundResource(R.drawable.bg_badge_gray);
                        tvBadgeGrammar.setTextColor(0xFF6B7280);
                    }
                }
                if (itemGrammar != null) {
                    itemGrammar.setOnClickListener(v -> {
                        Intent intent = new Intent(this, ExerciseActivity.class);
                        intent.putExtra("lessonId", lessonId);
                        startActivity(intent);
                    });
                }
            } else {
                layoutTimelineGrammar.setVisibility(View.GONE);
            }
        }

        // Step 4: Tổng kết
        int dayPercent = LessonProgressManager.getDayProgressPercent(this, lessonId);
        if (tvTimelineNode4 != null) {
            if (dayPercent >= 100) {
                tvTimelineNode4.setBackgroundResource(R.drawable.bg_circle_green);
                tvTimelineNode4.setText("✓");
            } else {
                tvTimelineNode4.setBackgroundResource(R.drawable.bg_circle_gray);
                tvTimelineNode4.setText("4");
            }
        }
        if (tvBadgeSummary != null) {
            if (dayPercent >= 100) {
                tvBadgeSummary.setText("Hoàn thành xuất sắc ✓");
                tvBadgeSummary.setBackgroundResource(R.drawable.bg_badge_green);
                tvBadgeSummary.setTextColor(0xFF065F46);
            } else {
                tvBadgeSummary.setText("Chưa đạt (Cần 100%)");
                tvBadgeSummary.setBackgroundResource(R.drawable.bg_badge_gray);
                tvBadgeSummary.setTextColor(0xFF6B7280);
            }
        }
        if (tvSummaryDesc != null) {
            if (dayPercent >= 100) {
                tvSummaryDesc.setText("Chúc mừng! Bạn đã hoàn thành xuất sắc toàn bộ mục tiêu ngày học này.");
            } else {
                tvSummaryDesc.setText("Hoàn thành từ vựng và ngữ pháp để mở khóa chứng nhận.");
            }
        }
        if (itemSummary != null) {
            itemSummary.setOnClickListener(v -> {
                Intent intent = new Intent(this, LessonSummaryActivity.class);
                intent.putExtra("lessonId", lessonId);
                startActivity(intent);
            });
        }

        // Cập nhật nút Hành động tiếp tục dưới đáy màn hình
        if (btnContinue != null) {
            if (vocabTotal > 0 && vocabPercent < 100) {
                btnContinue.setText("Tiếp tục: Bước 2 - Học từ vựng");
                btnContinue.setOnClickListener(v -> {
                    Intent intent = new Intent(this, UnitDetailActivity.class);
                    intent.putExtra("lessonId", lessonId);
                    startActivity(intent);
                });
            } else if (grammarTotal > 0 && grammarPercent < 100) {
                btnContinue.setText("Tiếp tục: Bước 3 - Luyện ngữ pháp");
                btnContinue.setOnClickListener(v -> {
                    Intent intent = new Intent(this, ExerciseActivity.class);
                    intent.putExtra("lessonId", lessonId);
                    startActivity(intent);
                });
            } else {
                btnContinue.setText("Hoàn tất: Xem tổng kết ngày học");
                btnContinue.setOnClickListener(v -> {
                    Intent intent = new Intent(this, LessonSummaryActivity.class);
                    intent.putExtra("lessonId", lessonId);
                    startActivity(intent);
                });
            }
        }
    }
}
