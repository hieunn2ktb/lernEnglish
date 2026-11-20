package com.hieunn.learnenglish;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

public class JapaneseChoukaiActivity extends AppCompatActivity implements ChoukaiAudioHelper.AudioListener {

    private int lessonNumber = 1;
    private ChoukaiLesson currentLesson;
    private ChoukaiTask currentTask;
    private int currentTaskIndex = 0;

    private ChoukaiAudioHelper audioHelper;
    private boolean isUserSeeking = false;

    // Header views
    private TextView tvLessonTitle;
    private TextView tvLessonSubtitle;

    // Task tabs
    private LinearLayout layoutTaskTabs;

    // Audio player views
    private TextView tvTrackLabel;
    private TextView tvAudioSourceBadge;
    private TextView tvAudioStatusDetail;
    private SeekBar seekBarAudio;
    private TextView tvCurrentTime;
    private TextView tvTotalTime;
    private Button btnSpeed;
    private ImageButton btnPlayPause;
    private ImageButton btnReplay5;
    private ImageButton btnForward5;
    private ImageButton btnRestartAudio;

    // Task title & questions
    private TextView tvTaskTitle;
    private TextView tvTaskTitleVi;
    private LinearLayout layoutQuestions;

    // Script collapsible
    private View btnToggleScript;
    private LinearLayout layoutScriptBody;
    private TextView tvScriptToggleArrow;
    private TextView tvScriptJa;
    private TextView tvScriptVi;
    private boolean isScriptExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_japanese_choukai);

        lessonNumber = getIntent().getIntExtra("lessonNumber", 1);

        initViews();
        audioHelper = new ChoukaiAudioHelper(this, this);

        loadLessonData();
    }

    private void initViews() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        ImageButton btnAudioSettings = findViewById(R.id.btnAudioSettings);
        btnAudioSettings.setOnClickListener(v -> showAudioSettingsDialog());

        tvLessonTitle = findViewById(R.id.tvLessonTitle);
        tvLessonSubtitle = findViewById(R.id.tvLessonSubtitle);
        layoutTaskTabs = findViewById(R.id.layoutTaskTabs);

        tvTrackLabel = findViewById(R.id.tvTrackLabel);
        tvAudioSourceBadge = findViewById(R.id.tvAudioSourceBadge);
        tvAudioStatusDetail = findViewById(R.id.tvAudioStatusDetail);
        seekBarAudio = findViewById(R.id.seekBarAudio);
        tvCurrentTime = findViewById(R.id.tvCurrentTime);
        tvTotalTime = findViewById(R.id.tvTotalTime);
        btnSpeed = findViewById(R.id.btnSpeed);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        btnReplay5 = findViewById(R.id.btnReplay5);
        btnForward5 = findViewById(R.id.btnForward5);
        btnRestartAudio = findViewById(R.id.btnRestartAudio);

        tvTaskTitle = findViewById(R.id.tvTaskTitle);
        tvTaskTitleVi = findViewById(R.id.tvTaskTitleVi);
        layoutQuestions = findViewById(R.id.layoutQuestions);

        btnToggleScript = findViewById(R.id.btnToggleScript);
        layoutScriptBody = findViewById(R.id.layoutScriptBody);
        tvScriptToggleArrow = findViewById(R.id.tvScriptToggleArrow);
        tvScriptJa = findViewById(R.id.tvScriptJa);
        tvScriptVi = findViewById(R.id.tvScriptVi);

        // Player actions
        btnPlayPause.setOnClickListener(v -> {
            if (audioHelper != null) {
                audioHelper.togglePlayPause();
            }
        });

        btnReplay5.setOnClickListener(v -> {
            if (audioHelper != null) {
                audioHelper.seekRelative(-5000);
            }
        });

        btnForward5.setOnClickListener(v -> {
            if (audioHelper != null) {
                audioHelper.seekRelative(5000);
            }
        });

        btnRestartAudio.setOnClickListener(v -> {
            if (audioHelper != null && currentTask != null) {
                audioHelper.stop();
                audioHelper.seekTo(0);
                audioHelper.play();
            }
        });

        btnSpeed.setOnClickListener(v -> {
            float cur = audioHelper.getPlaybackSpeed();
            float next;
            if (Math.abs(cur - 0.8f) < 0.05f) {
                next = 1.0f;
            } else if (Math.abs(cur - 1.0f) < 0.05f) {
                next = 1.2f;
            } else {
                next = 0.8f;
            }
            audioHelper.setPlaybackSpeed(next);
            btnSpeed.setText(String.format(Locale.US, "%.1fx", next));
        });

        seekBarAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    tvCurrentTime.setText(formatTime(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isUserSeeking = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isUserSeeking = false;
                if (audioHelper != null) {
                    audioHelper.seekTo(seekBar.getProgress());
                }
            }
        });

        // Script collapsible
        btnToggleScript.setOnClickListener(v -> {
            isScriptExpanded = !isScriptExpanded;
            layoutScriptBody.setVisibility(isScriptExpanded ? View.VISIBLE : View.GONE);
            tvScriptToggleArrow.setText(isScriptExpanded ? "▲ Đóng" : "▼ Mở");
        });
    }

    private void loadLessonData() {
        currentLesson = ChoukaiDataManager.getLesson(this, lessonNumber);
        if (currentLesson == null) {
            Toast.makeText(this, "Không tìm thấy dữ liệu bài " + lessonNumber, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        tvLessonTitle.setText("Bài " + currentLesson.getLessonNumber() + ": 聴解タスク (" + currentLesson.getLevel() + ")");
        tvLessonSubtitle.setText(currentLesson.getTitle() + (currentLesson.getSubtitle().isEmpty() ? "" : " - " + currentLesson.getSubtitle()));

        buildTaskTabs();
        selectTask(0);
    }

    private void buildTaskTabs() {
        layoutTaskTabs.removeAllViews();
        List<ChoukaiTask> tasks = currentLesson.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            final int index = i;
            ChoukaiTask t = tasks.get(i);
            Button tabBtn = new Button(this);
            tabBtn.setText("Task " + t.getTaskNumber());
            tabBtn.setTextSize(13f);
            tabBtn.setAllCaps(false);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    dpToPx(38)
            );
            lp.setMargins(0, 0, dpToPx(8), 0);
            tabBtn.setLayoutParams(lp);

            tabBtn.setOnClickListener(v -> selectTask(index));
            layoutTaskTabs.addView(tabBtn);
        }
    }

    private void selectTask(int index) {
        if (currentLesson == null || currentLesson.getTasks().isEmpty()) return;
        if (index < 0 || index >= currentLesson.getTasks().size()) return;

        currentTaskIndex = index;
        currentTask = currentLesson.getTasks().get(index);

        // Update tabs styling
        for (int i = 0; i < layoutTaskTabs.getChildCount(); i++) {
            View child = layoutTaskTabs.getChildAt(i);
            if (child instanceof Button) {
                Button b = (Button) child;
                if (i == index) {
                    b.setBackgroundResource(R.drawable.bg_btn_primary);
                    b.setTextColor(Color.WHITE);
                } else {
                    b.setBackgroundResource(R.drawable.bg_card_rounded_quizlet);
                    b.setTextColor(Color.parseColor("#475569"));
                }
            }
        }

        // Update task title
        tvTaskTitle.setText(currentTask.getTitle());
        tvTaskTitleVi.setText(currentTask.getTitleVi());

        // Update audio info
        tvTrackLabel.setText(currentTask.getTrackLabel());
        tvAudioStatusDetail.setText("Đang chuẩn bị file: " + currentTask.getAudioFile() + "...");

        // Update script
        tvScriptJa.setText(currentTask.getScript());
        tvScriptVi.setText(currentTask.getScriptVi());

        // Reset script collapsible to collapsed state when switching tasks
        isScriptExpanded = false;
        layoutScriptBody.setVisibility(View.GONE);
        tvScriptToggleArrow.setText("▼ Mở");

        // Load Audio
        if (audioHelper != null) {
            audioHelper.loadTask(currentTask);
        }

        // Render questions
        renderQuestions(currentTask.getQuestions());
    }

    private void renderQuestions(List<ChoukaiQuestion> questions) {
        layoutQuestions.removeAllViews();
        if (questions == null || questions.isEmpty()) {
            TextView tvEmpty = new TextView(this);
            tvEmpty.setText("Nghe lời thoại và xem nội dung script ở bên dưới.");
            tvEmpty.setTextColor(Color.parseColor("#64748B"));
            tvEmpty.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));
            layoutQuestions.addView(tvEmpty);
            return;
        }

        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < questions.size(); i++) {
            ChoukaiQuestion q = questions.get(i);
            View qView = inflater.inflate(R.layout.item_choukai_question, layoutQuestions, false);

            TextView tvBadge = qView.findViewById(R.id.tvQuestionBadge);
            TextView tvStatus = qView.findViewById(R.id.tvQuestionStatus);
            TextView tvQText = qView.findViewById(R.id.tvQuestionText);
            TextView tvQVi = qView.findViewById(R.id.tvQuestionVi);
            LinearLayout layoutOptions = qView.findViewById(R.id.layoutOptions);
            LinearLayout layoutExplanation = qView.findViewById(R.id.layoutExplanation);
            TextView tvExplanationText = qView.findViewById(R.id.tvExplanationText);

            tvBadge.setText("Câu " + q.getQuestionNumber());
            tvQText.setText(q.getQuestionText());
            if (q.getQuestionVi() != null && !q.getQuestionVi().isEmpty()) {
                tvQVi.setText(q.getQuestionVi());
                tvQVi.setVisibility(View.VISIBLE);
            } else {
                tvQVi.setVisibility(View.GONE);
            }

            final List<String> options = q.getOptions();
            final int correctIdx = q.getCorrectIndex();
            final String explanation = q.getExplanation();

            for (int optIdx = 0; optIdx < options.size(); optIdx++) {
                final int selectedIdx = optIdx;
                Button optBtn = new Button(this);
                optBtn.setText(options.get(optIdx));
                optBtn.setTextSize(14f);
                optBtn.setAllCaps(false);
                optBtn.setBackgroundResource(R.drawable.bg_option_outline);
                optBtn.setTextColor(Color.parseColor("#1E293B"));

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                lp.setMargins(0, dpToPx(4), 0, dpToPx(4));
                optBtn.setLayoutParams(lp);

                optBtn.setOnClickListener(v -> {
                    // Disable all buttons in this question once answered
                    for (int k = 0; k < layoutOptions.getChildCount(); k++) {
                        View child = layoutOptions.getChildAt(k);
                        child.setEnabled(false);
                        if (k == correctIdx) {
                            child.setBackgroundResource(R.drawable.bg_badge_green);
                            if (child instanceof Button) {
                                ((Button) child).setTextColor(Color.parseColor("#15803D"));
                            }
                        }
                    }

                    boolean isCorrect = (selectedIdx == correctIdx);
                    if (isCorrect) {
                        optBtn.setBackgroundResource(R.drawable.bg_badge_green);
                        optBtn.setTextColor(Color.parseColor("#15803D"));
                        tvStatus.setVisibility(View.VISIBLE);
                        tvStatus.setText("✓ Chính xác");
                        tvStatus.setTextColor(Color.parseColor("#15803D"));
                    } else {
                        optBtn.setBackgroundResource(R.drawable.bg_badge_red);
                        optBtn.setTextColor(Color.parseColor("#B91C1C"));
                        tvStatus.setVisibility(View.VISIBLE);
                        tvStatus.setText("✗ Chưa đúng");
                        tvStatus.setTextColor(Color.parseColor("#DC2626"));
                    }

                    // Show explanation
                    layoutExplanation.setVisibility(View.VISIBLE);
                    String correctText = (correctIdx >= 0 && correctIdx < options.size()) ? options.get(correctIdx) : "";
                    String explMsg = "Đáp án đúng: " + correctText;
                    if (explanation != null && !explanation.isEmpty()) {
                        explMsg += "\n" + explanation;
                    }
                    tvExplanationText.setText(explMsg);
                });

                layoutOptions.addView(optBtn);
            }

            layoutQuestions.addView(qView);
        }
    }

    private void showAudioSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cấu hình file âm thanh Choukai");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(dpToPx(20), dpToPx(12), dpToPx(20), dpToPx(12));

        TextView tvGuide = new TextView(this);
        tvGuide.setText("• Bài 1 đã có sẵn âm thanh gốc tích hợp trong ứng dụng (Assets).\n" +
                "• Để nghe trọn vẹn 50 bài (Bài 2 -> 50) bằng file MP3 gốc từ CD:\n" +
                "  1. Hãy chép thư mục audio vào bộ nhớ máy:\n" +
                "     /sdcard/Music/choukai/ hoặc /sdcard/Download/choukai/\n" +
                "  2. Hoặc nhập đường dẫn tùy chỉnh bên dưới:\n");
        tvGuide.setTextColor(Color.parseColor("#334155"));
        tvGuide.setTextSize(13f);
        layout.addView(tvGuide);

        SharedPreferences prefs = getSharedPreferences("choukai_prefs", Context.MODE_PRIVATE);
        String savedDir = prefs.getString(ChoukaiAudioHelper.PREF_AUDIO_DIR, "");

        final EditText etPath = new EditText(this);
        etPath.setHint("/sdcard/Music/choukai");
        etPath.setText(savedDir);
        layout.addView(etPath);

        builder.setView(layout);
        builder.setPositiveButton("Lưu", (dialog, which) -> {
            String newPath = etPath.getText().toString().trim();
            prefs.edit().putString(ChoukaiAudioHelper.PREF_AUDIO_DIR, newPath).apply();
            Toast.makeText(this, "Đã lưu đường dẫn âm thanh", Toast.LENGTH_SHORT).show();
            if (audioHelper != null && currentTask != null) {
                audioHelper.loadTask(currentTask);
            }
        });
        builder.setNeutralButton("Dùng mặc định", (dialog, which) -> {
            prefs.edit().remove(ChoukaiAudioHelper.PREF_AUDIO_DIR).apply();
            if (audioHelper != null && currentTask != null) {
                audioHelper.loadTask(currentTask);
            }
        });
        builder.setNegativeButton("Đóng", null);
        builder.show();
    }

    // AudioListener implementation
    @Override
    public void onAudioPrepared(int durationMs, String sourceDescription, boolean isTts) {
        tvAudioStatusDetail.setText(sourceDescription);
        seekBarAudio.setMax(durationMs);
        tvTotalTime.setText(formatTime(durationMs));
        seekBarAudio.setEnabled(!isTts);

        if (isTts) {
            tvAudioSourceBadge.setText("Giọng TTS");
            tvAudioSourceBadge.setBackgroundResource(R.drawable.bg_badge_gray);
            tvAudioSourceBadge.setTextColor(Color.parseColor("#4B5563"));
        } else if (sourceDescription.contains("Assets")) {
            tvAudioSourceBadge.setText("Assets (Gốc)");
            tvAudioSourceBadge.setBackgroundResource(R.drawable.bg_badge_green);
            tvAudioSourceBadge.setTextColor(Color.parseColor("#15803D"));
        } else {
            tvAudioSourceBadge.setText("Bộ nhớ máy");
            tvAudioSourceBadge.setBackgroundResource(R.drawable.bg_badge_blue);
            tvAudioSourceBadge.setTextColor(Color.parseColor("#1D4ED8"));
        }
    }

    @Override
    public void onAudioProgress(int currentMs, int durationMs) {
        if (!isUserSeeking) {
            seekBarAudio.setProgress(currentMs);
            tvCurrentTime.setText(formatTime(currentMs));
        }
    }

    @Override
    public void onAudioStateChanged(boolean isPlaying) {
        if (isPlaying) {
            btnPlayPause.setImageResource(R.drawable.ic_pause);
        } else {
            btnPlayPause.setImageResource(R.drawable.ic_play_arrow);
        }
    }

    @Override
    public void onAudioCompleted() {
        seekBarAudio.setProgress(0);
        tvCurrentTime.setText("00:00");
        btnPlayPause.setImageResource(R.drawable.ic_play_arrow);
    }

    @Override
    public void onAudioError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        btnPlayPause.setImageResource(R.drawable.ic_play_arrow);
    }

    private String formatTime(int ms) {
        int seconds = (ms / 1000) % 60;
        int minutes = (ms / (1000 * 60)) % 60;
        return String.format(Locale.US, "%02d:%02d", minutes, seconds);
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (audioHelper != null) {
            audioHelper.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (audioHelper != null) {
            audioHelper.release();
            audioHelper = null;
        }
    }
}
