package com.hieunn.learnenglish;

import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JapaneseGrammarDetailActivity extends AppCompatActivity {

    private int lessonNumber = 1;
    private JapaneseGrammarLesson currentLesson;
    private TextToSpeech tts;

    private TextView tvGrammarHeaderTitle;
    private TextView tvGrammarHeaderSubtitle;
    private TextView tvLessonBannerTitle;
    private TextView tvLessonBannerSummary;
    private TextView tvGrammarContent;
    private Button btnPrevLesson;
    private Button btnNextLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_japanese_grammar_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lessonNumber = getIntent().getIntExtra("lessonNumber", 1);

        initViews();
        initTTS();
        loadLesson(lessonNumber);
    }

    private void initViews() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        ImageButton btnSpeakGrammar = findViewById(R.id.btnSpeakGrammar);
        tvGrammarHeaderTitle = findViewById(R.id.tvGrammarHeaderTitle);
        tvGrammarHeaderSubtitle = findViewById(R.id.tvGrammarHeaderSubtitle);
        tvLessonBannerTitle = findViewById(R.id.tvLessonBannerTitle);
        tvLessonBannerSummary = findViewById(R.id.tvLessonBannerSummary);
        tvGrammarContent = findViewById(R.id.tvGrammarContent);
        btnPrevLesson = findViewById(R.id.btnPrevLesson);
        btnNextLesson = findViewById(R.id.btnNextLesson);

        btnBack.setOnClickListener(v -> finish());

        btnSpeakGrammar.setOnClickListener(v -> speakLesson());

        btnPrevLesson.setOnClickListener(v -> {
            if (lessonNumber > 1) {
                loadLesson(lessonNumber - 1);
            }
        });

        btnNextLesson.setOnClickListener(v -> {
            if (lessonNumber < 50) {
                loadLesson(lessonNumber + 1);
            }
        });
    }

    private void initTTS() {
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.JAPANESE);
                tts.setSpeechRate(0.85f);
            }
        });
    }

    private void loadLesson(int number) {
        lessonNumber = number;
        currentLesson = JapaneseGrammarDataManager.getLessonByNumber(this, lessonNumber);

        if (currentLesson == null) {
            Toast.makeText(this, "Không tìm thấy dữ liệu bài " + lessonNumber, Toast.LENGTH_SHORT).show();
            return;
        }

        tvGrammarHeaderTitle.setText("Ngữ pháp Bài " + lessonNumber);
        tvGrammarHeaderSubtitle.setText("Minna no Nihongo (Bài " + lessonNumber + " / 50)");

        tvLessonBannerTitle.setText(currentLesson.getTitle());
        tvLessonBannerSummary.setText(currentLesson.getSummary());

        tvGrammarContent.setText(formatGrammarContent(currentLesson.getContent()));

        btnPrevLesson.setEnabled(lessonNumber > 1);
        btnPrevLesson.setAlpha(lessonNumber > 1 ? 1.0f : 0.4f);

        btnNextLesson.setEnabled(lessonNumber < 50);
        btnNextLesson.setAlpha(lessonNumber < 50 ? 1.0f : 0.4f);
    }

    private CharSequence formatGrammarContent(String rawContent) {
        if (rawContent == null || rawContent.isEmpty()) return "";

        SpannableStringBuilder ssb = new SpannableStringBuilder(rawContent);

        // Highlight main headings: "1.", "2.", "3.", etc. at start of line
        Pattern headingPattern = Pattern.compile("(?m)^\\s*([0-9０-９]+[\\.\\．\\)]\\s*[^\\n]+)");
        Matcher mHeading = headingPattern.matcher(rawContent);
        while (mHeading.find()) {
            ssb.setSpan(new StyleSpan(Typeface.BOLD), mHeading.start(), mHeading.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.setSpan(new ForegroundColorSpan(0xFF1E293B), mHeading.start(), mHeading.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.setSpan(new RelativeSizeSpan(1.08f), mHeading.start(), mHeading.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // Highlight "＊ Ý nghĩa:" / "Ý nghĩa"
        highlightPattern(ssb, rawContent, "＊\\s*Ý nghĩa[^\\n]*", 0xFF059669);

        // Highlight "＊ Cách dùng:" / "Cách dùng"
        highlightPattern(ssb, rawContent, "＊\\s*Cách dùng[^\\n]*", 0xFF2563EB);

        // Highlight " Chú ý:" / "Chú ý"
        highlightPattern(ssb, rawContent, "\\s*Chú ý[^\\n]*", 0xFFD97706);

        // Highlight " Ví dụ:" / "Ví dụ"
        highlightPattern(ssb, rawContent, "(?:\\s*Ví dụ|Ví dụ)[^\\n]*", 0xFFEF4444);

        return ssb;
    }

    private void highlightPattern(SpannableStringBuilder ssb, String text, String regex, int color) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            ssb.setSpan(new StyleSpan(Typeface.BOLD), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.setSpan(new ForegroundColorSpan(color), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private void speakLesson() {
        if (tts != null && currentLesson != null) {
            // Speak Japanese sentences in the lesson
            String content = currentLesson.getContent();
            // Find first Japanese sentence in examples
            Pattern p = Pattern.compile("(?m)^\\s*(?:[1-9]\\)|[1-9]\\s*\\.?|\\-)?\\s*([\\u3040-\\u309F\\u30A0-\\u30FF\\u4E00-\\u9FAF][^\\n]{4,})");
            Matcher m = p.matcher(content);
            if (m.find()) {
                tts.speak(m.group(1).trim(), TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                tts.speak(currentLesson.getTitle(), TextToSpeech.QUEUE_FLUSH, null, null);
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
