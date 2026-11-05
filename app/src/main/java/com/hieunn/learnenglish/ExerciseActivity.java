package com.hieunn.learnenglish;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.GrammarQuizEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ExerciseActivity extends AppCompatActivity {

    // Trạng thái câu hỏi (tương tự LearnVocabActivity)
    private static final int STATE_NEW = 0;        // Chưa học
    private static final int STATE_LEARNING = 1;   // Đang học
    private static final int STATE_MASTERED = 2;   // Nắm vững (cần đúng cả trắc nghiệm và tự viết)
    private static final int REQUIRED_CORRECT_STREAK = 2;

    // Chế độ luyện tập
    public static final int MODE_MIXED = 0;        // Kết hợp (Trắc nghiệm + Tự viết tự luận - mặc định)
    public static final int MODE_WRITE_ONLY = 1;   // Chỉ tự viết tự luận
    public static final int MODE_MC_ONLY = 2;      // Chỉ trắc nghiệm

    private int practiceMode = MODE_MIXED;
    private boolean isWriteMode = false;
    private boolean answered = false;

    private final List<GrammarQuizEntity> allQuestions = new ArrayList<>();
    private final List<GrammarQuizEntity> queue = new ArrayList<>();
    private final Map<Integer, Integer> questionState = new HashMap<>();
    private final Map<Integer, Integer> correctStreak = new HashMap<>();
    private final Map<Integer, Boolean> answeredByMC = new HashMap<>();
    private final Map<Integer, Boolean> answeredByWrite = new HashMap<>();

    private GrammarQuizEntity currentQuestion;
    private TextToSpeech tts;
    private int lessonId = -1;
    private int sessionCorrect = 0;
    private int sessionWrong = 0;

    // Views
    private TextView tvQuestion, tvQuestionNumber, tvQuestionProgress, tvInstruction, tvSessionStats;
    private TextView tvNewCount, tvLearningCount, tvMasteredCount;
    private ProgressBar progressBar;
    private Button btnCheckAnswer;
    private ImageButton btnAudio;
    private LinearLayout layoutMultipleChoice, layoutWriteMode;
    private CardView[] cards;
    private TextView[] options;
    private EditText etWriteAnswer;
    private TextView tvWriteFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        initViews();
        initTts();
        loadData();
    }

    private void initViews() {
        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestionProgress = findViewById(R.id.tvQuestionProgress);
        tvInstruction = findViewById(R.id.tvInstruction);
        tvSessionStats = findViewById(R.id.tvSessionStats);

        tvNewCount = findViewById(R.id.tvNewCount);
        tvLearningCount = findViewById(R.id.tvLearningCount);
        tvMasteredCount = findViewById(R.id.tvMasteredCount);
        progressBar = findViewById(R.id.progressBar);

        btnCheckAnswer = findViewById(R.id.btnCheckAnswer);
        btnAudio = findViewById(R.id.btnAudio);
        layoutMultipleChoice = findViewById(R.id.layoutMultipleChoice);
        layoutWriteMode = findViewById(R.id.layoutWriteMode);

        cards = new CardView[]{
                findViewById(R.id.cardOptionA),
                findViewById(R.id.cardOptionB),
                findViewById(R.id.cardOptionC),
                findViewById(R.id.cardOptionD)
        };

        options = new TextView[]{
                findViewById(R.id.tvOptionA),
                findViewById(R.id.tvOptionB),
                findViewById(R.id.tvOptionC),
                findViewById(R.id.tvOptionD)
        };

        etWriteAnswer = findViewById(R.id.etWriteAnswer);
        tvWriteFeedback = findViewById(R.id.tvWriteFeedback);

        // Nút quay lại và Cài đặt
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.btnSettings).setOnClickListener(v -> showSettingsDialog());

        // Phát âm
        if (btnAudio != null) {
            btnAudio.setOnClickListener(v -> speakCurrentQuestion());
        }

        // Chọn đáp án trắc nghiệm (phản hồi ngay giống LearnVocabActivity)
        for (int i = 0; i < cards.length; i++) {
            final int index = i;
            cards[i].setOnClickListener(v -> onOptionClicked(index));
        }

        // Nút Kiểm tra / Tiếp tục
        btnCheckAnswer.setOnClickListener(v -> onCheckAnswerClicked());

        // Bấm Done trên bàn phím để kiểm tra trong phần Tự viết
        etWriteAnswer.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO) {
                if (!answered && isWriteMode) {
                    checkWriteAnswer();
                    return true;
                }
            }
            return false;
        });
    }

    private void initTts() {
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
            }
        });
    }

    private void loadData() {
        allQuestions.clear();
        lessonId = getIntent().getIntExtra("lessonId", -1);
        ArrayList<Integer> selectedLessonIds = getIntent().getIntegerArrayListExtra("selectedLessonIds");

        AppDatabase db = AppDatabase.getInstance(this);
        if (selectedLessonIds != null && !selectedLessonIds.isEmpty()) {
            for (Integer id : selectedLessonIds) {
                allQuestions.addAll(db.grammarQuizDao().getQuizByLessonId(id));
            }
        } else if (lessonId > 0) {
            allQuestions.addAll(db.grammarQuizDao().getQuizByLessonId(lessonId));
        }

        if (allQuestions.isEmpty()) {
            Toast.makeText(this, "Chưa có câu hỏi ngữ pháp nào!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Kiểm tra xem có phiên luyện ngữ pháp trước đó chưa hoàn thành không
        if (lessonId > 0 && ExerciseSessionManager.hasSavedSession(this, lessonId)) {
            ExerciseSessionManager.SessionData data = ExerciseSessionManager.restoreSession(
                    this,
                    lessonId,
                    allQuestions,
                    questionState,
                    correctStreak,
                    answeredByMC,
                    answeredByWrite,
                    queue
            );
            if (data != null) {
                sessionCorrect = data.sessionCorrect;
                sessionWrong = data.sessionWrong;
                practiceMode = data.practiceMode;
                progressBar.setMax(allQuestions.size());
                updateStatus();

                if (queue.isEmpty()) {
                    showCompletionDialog();
                } else {
                    Toast.makeText(this, "Tiếp tục luyện: Đã xong " + data.masteredCount + "/" + allQuestions.size() + " câu", Toast.LENGTH_SHORT).show();
                    nextQuestion();
                }
                return;
            }
        }

        restartPractice(false);
    }

    private void restartPractice(boolean showMessage) {
        if (lessonId > 0) {
            ExerciseSessionManager.clearSession(this, lessonId);
        }
        queue.clear();
        questionState.clear();
        correctStreak.clear();
        answeredByMC.clear();
        answeredByWrite.clear();
        sessionCorrect = 0;
        sessionWrong = 0;

        for (GrammarQuizEntity question : allQuestions) {
            questionState.put(question.id, STATE_NEW);
            correctStreak.put(question.id, 0);
            answeredByMC.put(question.id, false);
            answeredByWrite.put(question.id, false);
        }

        queue.addAll(allQuestions);
        Collections.shuffle(queue);
        progressBar.setMax(allQuestions.size());
        updateStatus();

        if (showMessage) {
            Toast.makeText(this, "Đã luyện lại từ đầu!", Toast.LENGTH_SHORT).show();
        }

        nextQuestion();
    }

    private void showSettingsDialog() {
        String modeDesc;
        if (practiceMode == MODE_MIXED) {
            modeDesc = "Kết hợp (Trắc nghiệm + Tự viết)";
        } else if (practiceMode == MODE_WRITE_ONLY) {
            modeDesc = "Chỉ tự viết tự luận";
        } else {
            modeDesc = "Chỉ trắc nghiệm";
        }

        String[] items = new String[]{
                "Luyện lại từ đầu",
                "Chế độ học (Hiện tại: " + modeDesc + ")"
        };

        new AlertDialog.Builder(this)
                .setTitle("Cài đặt luyện ngữ pháp")
                .setItems(items, (dialog, which) -> {
                    if (which == 0) {
                        restartPractice(true);
                    } else if (which == 1) {
                        showSelectModeDialog();
                    }
                })
                .setNegativeButton("Đóng", null)
                .show();
    }

    private void showSelectModeDialog() {
        String[] modes = new String[]{
                "Kết hợp (Trắc nghiệm & Tự viết tự luận) - Khuyên dùng",
                "Chỉ tự viết tự luận",
                "Chỉ trắc nghiệm"
        };

        new AlertDialog.Builder(this)
                .setTitle("Chọn chế độ luyện tập")
                .setSingleChoiceItems(modes, practiceMode, (dialog, which) -> {
                    practiceMode = which;
                    dialog.dismiss();
                    restartPractice(true);
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private GrammarQuizEntity pickNextQuestion() {
        // Ưu tiên 1: Câu hỏi mới (STATE_NEW)
        for (GrammarQuizEntity q : queue) {
            if (questionState.get(q.id) == STATE_NEW) return q;
        }
        // Ưu tiên 2: Câu hỏi đang học (STATE_LEARNING)
        for (GrammarQuizEntity q : queue) {
            if (questionState.get(q.id) == STATE_LEARNING) return q;
        }
        return queue.isEmpty() ? null : queue.get(0);
    }

    private boolean hasValidMultipleChoiceOptions(GrammarQuizEntity q) {
        int count = 0;
        if (q.optionA != null && !q.optionA.trim().isEmpty()) count++;
        if (q.optionB != null && !q.optionB.trim().isEmpty()) count++;
        if (q.optionC != null && !q.optionC.trim().isEmpty()) count++;
        if (q.optionD != null && !q.optionD.trim().isEmpty()) count++;
        return count >= 2;
    }

    private void nextQuestion() {
        // Loại bỏ các câu đã nắm vững
        queue.removeIf(q -> questionState.get(q.id) == STATE_MASTERED);
        updateStatus();

        if (queue.isEmpty()) {
            showCompletionDialog();
            return;
        }

        currentQuestion = pickNextQuestion();
        answered = false;

        tvQuestion.setText(currentQuestion.question);
        tvQuestionNumber.setText(String.valueOf(allQuestions.indexOf(currentQuestion) + 1));
        tvWriteFeedback.setVisibility(View.GONE);

        // Quyết định chế độ câu hỏi (giống LearnVocabActivity)
        boolean canDoMC = hasValidMultipleChoiceOptions(currentQuestion);
        if (!canDoMC || practiceMode == MODE_WRITE_ONLY) {
            isWriteMode = true;
        } else if (practiceMode == MODE_MC_ONLY) {
            isWriteMode = false;
        } else {
            // MODE_MIXED:
            Integer state = questionState.get(currentQuestion.id);
            Boolean mcDone = answeredByMC.get(currentQuestion.id);
            Boolean writeDone = answeredByWrite.get(currentQuestion.id);

            if (state != null && state == STATE_NEW) {
                isWriteMode = false; // Lần đầu: luôn Trắc nghiệm
            } else if (mcDone != null && mcDone && (writeDone == null || !writeDone)) {
                isWriteMode = true; // Buộc phải tự viết để nắm vững
            } else if (writeDone != null && writeDone && (mcDone == null || !mcDone)) {
                isWriteMode = false; // Buộc phải trắc nghiệm để nắm vững
            } else {
                isWriteMode = Math.random() < 0.5;
            }
        }

        if (isWriteMode) {
            showWriteMode();
        } else {
            showMultipleChoiceMode();
        }
    }

    private void showWriteMode() {
        layoutMultipleChoice.setVisibility(View.GONE);
        layoutWriteMode.setVisibility(View.VISIBLE);

        tvInstruction.setText("Tự luận: Gõ câu trả lời chính xác");
        etWriteAnswer.setText("");
        etWriteAnswer.setBackgroundResource(R.drawable.bg_edittext);
        tvWriteFeedback.setVisibility(View.GONE);

        btnCheckAnswer.setVisibility(View.VISIBLE);
        btnCheckAnswer.setText("Kiểm tra");

        etWriteAnswer.requestFocus();
        etWriteAnswer.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(etWriteAnswer, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 150);
    }

    private void showMultipleChoiceMode() {
        layoutMultipleChoice.setVisibility(View.VISIBLE);
        layoutWriteMode.setVisibility(View.GONE);

        tvInstruction.setText("Chọn đáp án đúng");
        btnCheckAnswer.setVisibility(View.GONE);

        hideKeyboard();

        String[] values = {currentQuestion.optionA, currentQuestion.optionB, currentQuestion.optionC, currentQuestion.optionD};
        for (int i = 0; i < cards.length; i++) {
            String value = values[i];
            options[i].setText(value == null ? "" : value);
            options[i].setTextColor(0xFF374151);
            cards[i].setVisibility(value == null || value.trim().isEmpty() ? View.GONE : View.VISIBLE);
            cards[i].setCardBackgroundColor(0xFFFFFFFF);
        }
    }

    private void onOptionClicked(int index) {
        if (answered || cards[index].getVisibility() != View.VISIBLE) return;
        answered = true;

        String selected = options[index].getText().toString().trim();
        String correct = currentQuestion.correctAnswer == null ? "" : currentQuestion.correctAnswer.trim();
        boolean isCorrect = isAnswerCorrect(selected, correct, currentQuestion.question);
        int correctIndex = findCorrectOption(correct);

        if (isCorrect) {
            sessionCorrect++;
            cards[index].setCardBackgroundColor(0xFFD1FAE5);
            options[index].setTextColor(0xFF065F46);

            speakText(correct);
            onCorrectAnswer(false);
            recordAnswer(true);
            btnCheckAnswer.postDelayed(this::nextQuestion, 800);
        } else {
            sessionWrong++;
            cards[index].setCardBackgroundColor(0xFFFEE2E2);
            options[index].setTextColor(0xFF991B1B);
            if (correctIndex >= 0) {
                cards[correctIndex].setCardBackgroundColor(0xFFD1FAE5);
                options[correctIndex].setTextColor(0xFF065F46);
            }

            speakText(correct);
            onWrongAnswer();
            recordAnswer(false);
            btnCheckAnswer.setVisibility(View.VISIBLE);
            btnCheckAnswer.setText("Tiếp tục");
        }

        updateStatus();
    }

    private void checkWriteAnswer() {
        hideKeyboard();
        String userInput = etWriteAnswer.getText().toString().trim();
        if (userInput.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập câu trả lời!", Toast.LENGTH_SHORT).show();
            return;
        }

        answered = true;
        String correct = currentQuestion.correctAnswer == null ? "" : currentQuestion.correctAnswer.trim();
        boolean isCorrect = isAnswerCorrect(userInput, correct, currentQuestion.question);

        tvWriteFeedback.setVisibility(View.VISIBLE);

        if (isCorrect) {
            sessionCorrect++;
            etWriteAnswer.setBackgroundColor(0xFFD1FAE5);
            tvWriteFeedback.setText("Chính xác! 🎉");
            tvWriteFeedback.setTextColor(0xFF065F46);

            speakText(correct);
            onCorrectAnswer(true);
            recordAnswer(true);
            btnCheckAnswer.postDelayed(this::nextQuestion, 800);
        } else {
            sessionWrong++;
            etWriteAnswer.setBackgroundColor(0xFFFEE2E2);
            tvWriteFeedback.setText("Chưa đúng. Đáp án đúng là:\n" + correct);
            tvWriteFeedback.setTextColor(0xFF991B1B);

            speakText(correct);
            onWrongAnswer();
            recordAnswer(false);
            btnCheckAnswer.setText("Tiếp tục");
        }

        updateStatus();
    }

    private void onCheckAnswerClicked() {
        if (answered) {
            nextQuestion();
        } else if (isWriteMode) {
            checkWriteAnswer();
        }
    }

    private void onCorrectAnswer(boolean wasWrite) {
        int streak = correctStreak.containsKey(currentQuestion.id) ? correctStreak.get(currentQuestion.id) : 0;
        streak++;
        correctStreak.put(currentQuestion.id, streak);

        if (wasWrite) {
            answeredByWrite.put(currentQuestion.id, true);
        } else {
            answeredByMC.put(currentQuestion.id, true);
        }

        // Kiểm tra điều kiện Nắm vững (Mastered)
        boolean mastered;
        if (practiceMode == MODE_MIXED) {
            Boolean mcDone = answeredByMC.get(currentQuestion.id);
            Boolean writeDone = answeredByWrite.get(currentQuestion.id);
            // Cần đúng liên tiếp ít nhất 2 lần và đã vượt qua cả trắc nghiệm lẫn tự viết
            mastered = streak >= REQUIRED_CORRECT_STREAK && (mcDone != null && mcDone) && (writeDone != null && writeDone);
        } else {
            mastered = streak >= REQUIRED_CORRECT_STREAK;
        }

        if (mastered) {
            questionState.put(currentQuestion.id, STATE_MASTERED);
        } else {
            questionState.put(currentQuestion.id, STATE_LEARNING);
            requeueCurrent(false);
        }
    }

    private void onWrongAnswer() {
        correctStreak.put(currentQuestion.id, 0);
        questionState.put(currentQuestion.id, STATE_LEARNING);
        requeueCurrent(true);
    }

    private void requeueCurrent(boolean wasWrong) {
        queue.remove(currentQuestion);
        int offset = wasWrong ? 2 + (int) (Math.random() * 3) : Math.max(1, queue.size() / 2);
        queue.add(Math.min(offset, queue.size()), currentQuestion);
    }

    private int findCorrectOption(String correct) {
        for (int i = 0; i < options.length; i++) {
            if (options[i].getText().toString().trim().equalsIgnoreCase(correct)) return i;
        }
        for (int i = 0; i < options.length; i++) {
            if (isAnswerCorrect(options[i].getText().toString().trim(), correct, currentQuestion.question)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isAnswerCorrect(String userInput, String expected, String question) {
        if (userInput == null || expected == null) return false;

        String cleanInput = normalizeAnswer(userInput);
        String cleanExpected = normalizeAnswer(expected);

        if (cleanInput.isEmpty()) return false;

        // 1. Khớp chính xác không phân biệt hoa thường
        if (cleanInput.equalsIgnoreCase(cleanExpected)) return true;

        // Khớp thông qua logic VocabItem (tự động bỏ ngoặc đơn, kanji, biến thể)
        if (VocabItem.isAnswerMatching(userInput, expected, null)) return true;

        // 2. So sánh không tính dấu câu cuối câu (. ! ?)
        String stripInput = stripTrailingPunctuation(cleanInput);
        String stripExpected = stripTrailingPunctuation(cleanExpected);
        if (stripInput.equalsIgnoreCase(stripExpected)) return true;

        // 3. Khớp biến thể dấu gạch ngang (e.g. "Will - have arrived" vs "Will have arrived")
        String noDashInput = stripInput.replace("-", " ").replaceAll("\\s+", " ").trim();
        String noDashExpected = stripExpected.replace("-", " ").replaceAll("\\s+", " ").trim();
        if (noDashInput.equalsIgnoreCase(noDashExpected)) return true;

        // 4. Nếu câu hỏi có chỗ điền từ "_____" và người dùng gõ cả câu hoàn chỉnh
        if (question != null && question.contains("___")) {
            String fullSentence = normalizeAnswer(question.replaceAll("_+", expected));
            String stripFull = stripTrailingPunctuation(fullSentence);
            if (stripInput.equalsIgnoreCase(stripFull)) return true;
        }

        return false;
    }

    private String normalizeAnswer(String s) {
        if (s == null) return "";
        return s.trim()
                .replace('’', '\'')
                .replace('‘', '\'')
                .replace('`', '\'')
                .replace('“', '"')
                .replace('”', '"')
                .replace('–', '-')
                .replace('—', '-')
                .replaceAll("\\s+", " ");
    }

    private String stripTrailingPunctuation(String s) {
        if (s == null) return "";
        return s.replaceAll("[.!?]+$", "").trim();
    }

    private void recordAnswer(boolean isCorrect) {
        int targetLessonId = (currentQuestion != null && currentQuestion.lessonId > 0) ? currentQuestion.lessonId : lessonId;
        LessonProgressManager.recordGrammarAnswer(this, targetLessonId, isCorrect, getMasteredCount());
        saveCurrentSession(false);
    }

    private void saveCurrentSession(boolean isCompleted) {
        int targetLessonId = (currentQuestion != null && currentQuestion.lessonId > 0) ? currentQuestion.lessonId : lessonId;
        if (targetLessonId > 0 && !allQuestions.isEmpty()) {
            ExerciseSessionManager.saveSession(
                    this,
                    targetLessonId,
                    allQuestions,
                    questionState,
                    correctStreak,
                    answeredByMC,
                    answeredByWrite,
                    queue,
                    sessionCorrect,
                    sessionWrong,
                    practiceMode,
                    isCompleted
            );
        }
    }

    private int getMasteredCount() {
        int count = 0;
        for (GrammarQuizEntity question : allQuestions) {
            Integer state = questionState.get(question.id);
            if (state != null && state == STATE_MASTERED) count++;
        }
        return count;
    }

    private void updateStatus() {
        int newCount = 0, learningCount = 0, masteredCount = 0;
        for (GrammarQuizEntity question : allQuestions) {
            int state = questionState.containsKey(question.id) ? questionState.get(question.id) : STATE_NEW;
            if (state == STATE_NEW) newCount++;
            else if (state == STATE_LEARNING) learningCount++;
            else masteredCount++;
        }

        tvNewCount.setText(String.valueOf(newCount));
        tvLearningCount.setText(String.valueOf(learningCount));
        tvMasteredCount.setText(String.valueOf(masteredCount));

        if (tvSessionStats != null) {
            tvSessionStats.setText("Đúng: " + sessionCorrect + "  •  Sai: " + sessionWrong);
        }

        progressBar.setProgress(masteredCount);
        tvQuestionProgress.setText("Nắm vững: " + masteredCount + "/" + allQuestions.size());
    }

    private void showCompletionDialog() {
        saveCurrentSession(true);
        updateStatus();
        int total = sessionCorrect + sessionWrong;
        int accuracy = total > 0 ? (sessionCorrect * 100) / total : 100;

        String msg = "Bạn đã nắm vững tất cả câu hỏi ngữ pháp trong bài này!\n\n"
                + "✅ Số lần làm đúng: " + sessionCorrect + "\n"
                + "❌ Số lần làm sai: " + sessionWrong + "\n"
                + "🎯 Độ chính xác: " + accuracy + "%";

        new AlertDialog.Builder(this)
                .setTitle("🎉 Chúc mừng!")
                .setMessage(msg)
                .setPositiveButton("Luyện lại", (dialog, which) -> restartPractice(false))
                .setNegativeButton("Hoàn tất", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }

    private void speakCurrentQuestion() {
        if (currentQuestion == null || tts == null) return;
        String textToSpeak = getSpeakableText(currentQuestion);
        if (!textToSpeak.isEmpty()) {
            tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void speakText(String text) {
        if (tts != null && text != null && !text.trim().isEmpty()) {
            tts.speak(text.trim(), TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private String getSpeakableText(GrammarQuizEntity q) {
        if (q == null) return "";
        if (q.question.contains("___") && q.correctAnswer != null) {
            return q.question.replaceAll("_+", q.correctAnswer);
        }
        if (isEnglish(q.question)) {
            return q.question;
        }
        return q.correctAnswer != null ? q.correctAnswer : "";
    }

    private boolean isEnglish(String text) {
        String vietnameseChars = "àáảãạăắằẳẵặâấầẩẫậèéẻẽẹêếềểễệìíỉĩịòóỏõọôốồổỗộơớờởỡợùúủũụưứừửữựỳýỷỹỵđ";
        for (char c : text.toLowerCase().toCharArray()) {
            if (vietnameseChars.indexOf(c) >= 0) return false;
        }
        return true;
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveCurrentSession(false);
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
