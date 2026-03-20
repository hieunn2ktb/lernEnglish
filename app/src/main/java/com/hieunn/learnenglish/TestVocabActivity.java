package com.hieunn.learnenglish;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.LessonEntity;
import com.hieunn.learnenglish.db.VocabEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class TestVocabActivity extends AppCompatActivity {

    private enum QuestionType {
        MULTIPLE_CHOICE,
        TRUE_FALSE,
        MATCHING,
        WRITTEN
    }

    private enum DisplayMode {
        TERM,
        DEFINITION,
        BOTH
    }

    private ScrollView setupContainer;
    private ScrollView testContainer;
    private ScrollView resultContainer;
    private TextView tvLessonName;
    private TextView tvQuestionCount;
    private SeekBar seekQuestionCount;
    private SwitchCompat cbUseAll;
    private SwitchCompat cbMultipleChoice;
    private SwitchCompat cbTrueFalse;
    private SwitchCompat cbMatching;
    private SwitchCompat cbWritten;
    private RadioGroup rgDisplayMode;
    private RadioButton rbDisplayTerm;
    private RadioButton rbDisplayDefinition;
    private RadioButton rbDisplayBoth;
    private Button btnStartTest;
    private ImageButton btnOptions;

    private TextView tvProgress;
    private ProgressBar progressTest;
    private TextView tvQuestionType;
    private TextView tvPromptLabel;
    private TextView tvPromptValue;
    private LinearLayout layoutMultipleChoice;
    private LinearLayout layoutTrueFalse;
    private LinearLayout layoutMatching;
    private LinearLayout layoutMatchRows;
    private LinearLayout layoutWritten;
    private CardView[] choiceCards;
    private TextView[] choiceLabels;
    private Button btnTrue;
    private Button btnFalse;
    private EditText etWrittenAnswer;
    private Button btnNext;

    private TextView tvScore;
    private TextView tvScoreDetail;
    private Button btnRetakeFull;
    private Button btnRetryWrong;
    private Button btnBackToSetup;
    private RecyclerView rvResult;

    private final List<VocabItem> allVocab = new ArrayList<>();
    private final List<TestQuestion> questions = new ArrayList<>();
    private List<VocabItem> lastWrongItems = new ArrayList<>();
    private int currentIndex = -1;
    private boolean isInTest = false;
    private ResultAdapter resultAdapter;
    private TextWatcher writtenWatcher;

    private int lessonId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_vocab);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindViews();
        setupRecycler();
        setupListeners();
        loadData();
    }

    private void bindViews() {
        setupContainer = findViewById(R.id.setupContainer);
        testContainer = findViewById(R.id.testContainer);
        resultContainer = findViewById(R.id.resultContainer);
        tvLessonName = findViewById(R.id.tvLessonName);
        tvQuestionCount = findViewById(R.id.tvQuestionCount);
        seekQuestionCount = findViewById(R.id.seekQuestionCount);
        cbUseAll = findViewById(R.id.cbUseAll);
        cbMultipleChoice = findViewById(R.id.cbMultipleChoice);
        cbTrueFalse = findViewById(R.id.cbTrueFalse);
        cbMatching = findViewById(R.id.cbMatching);
        cbWritten = findViewById(R.id.cbWritten);
        rgDisplayMode = findViewById(R.id.rgDisplayMode);
        rbDisplayTerm = findViewById(R.id.rbDisplayTerm);
        rbDisplayDefinition = findViewById(R.id.rbDisplayDefinition);
        rbDisplayBoth = findViewById(R.id.rbDisplayBoth);
        btnStartTest = findViewById(R.id.btnStartTest);
        btnOptions = findViewById(R.id.btnOptions);

        tvProgress = findViewById(R.id.tvProgress);
        progressTest = findViewById(R.id.progressTest);
        tvQuestionType = findViewById(R.id.tvQuestionType);
        tvPromptLabel = findViewById(R.id.tvPromptLabel);
        tvPromptValue = findViewById(R.id.tvPromptValue);
        layoutMultipleChoice = findViewById(R.id.layoutMultipleChoice);
        layoutTrueFalse = findViewById(R.id.layoutTrueFalse);
        layoutMatching = findViewById(R.id.layoutMatching);
        layoutMatchRows = findViewById(R.id.layoutMatchRows);
        layoutWritten = findViewById(R.id.layoutWritten);
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);
        etWrittenAnswer = findViewById(R.id.etWrittenAnswer);
        btnNext = findViewById(R.id.btnNext);

        choiceCards = new CardView[]{
                findViewById(R.id.cardChoice1),
                findViewById(R.id.cardChoice2),
                findViewById(R.id.cardChoice3),
                findViewById(R.id.cardChoice4)
        };
        choiceLabels = new TextView[]{
                findViewById(R.id.tvChoice1),
                findViewById(R.id.tvChoice2),
                findViewById(R.id.tvChoice3),
                findViewById(R.id.tvChoice4)
        };

        tvScore = findViewById(R.id.tvScore);
        tvScoreDetail = findViewById(R.id.tvScoreDetail);
        btnRetakeFull = findViewById(R.id.btnRetakeFull);
        btnRetryWrong = findViewById(R.id.btnRetryWrong);
        btnBackToSetup = findViewById(R.id.btnBackToSetup);
        rvResult = findViewById(R.id.rvResult);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }

    private void setupRecycler() {
        rvResult.setLayoutManager(new LinearLayoutManager(this));
        resultAdapter = new ResultAdapter();
        rvResult.setAdapter(resultAdapter);
    }

    private void setupListeners() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekQuestionCount.setMin(1);
        }
        seekQuestionCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateQuestionCountLabel();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        cbUseAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            seekQuestionCount.setEnabled(!isChecked);
            updateQuestionCountLabel();
        });

        btnStartTest.setOnClickListener(v -> startTestWithSource(allVocab, false));

        btnOptions.setOnClickListener(v -> {
            if (testContainer.getVisibility() == View.VISIBLE && isInTest) {
                new AlertDialog.Builder(this)
                        .setTitle("Dừng bài kiểm tra?")
                        .setMessage("Bạn sẽ mất tiến trình hiện tại nếu quay lại tùy chỉnh.")
                        .setPositiveButton("Quay lại", (d, w) -> showSetup())
                        .setNegativeButton("Tiếp tục", null)
                        .show();
            } else {
                showSetup();
            }
        });

        btnNext.setOnClickListener(v -> onNextClicked());
        btnRetakeFull.setOnClickListener(v -> startTestWithSource(allVocab, false));
        btnRetryWrong.setOnClickListener(v -> {
            if (lastWrongItems.isEmpty()) {
                Toast.makeText(this, "Không có câu sai nào ở lượt trước", Toast.LENGTH_SHORT).show();
            } else {
                startTestWithSource(lastWrongItems, true);
            }
        });
        btnBackToSetup.setOnClickListener(v -> showSetup());

        btnTrue.setOnClickListener(v -> selectTrueFalse(true));
        btnFalse.setOnClickListener(v -> selectTrueFalse(false));

        for (int i = 0; i < choiceCards.length; i++) {
            int idx = i;
            choiceCards[i].setOnClickListener(v -> onChoiceSelected(idx));
        }
    }

    private void loadData() {
        lessonId = getIntent().getIntExtra("lessonId", -1);
        AppDatabase db = AppDatabase.getInstance(this);
        if (lessonId > 0) {
            LessonEntity lesson = db.lessonDao().getLessonById(lessonId);
            if (lesson != null) {
                tvLessonName.setText(String.format(Locale.getDefault(), "%s", lesson.title));
            }
            List<VocabEntity> entities = db.vocabDao().getVocabByLessonId(lessonId);
            for (VocabEntity entity : entities) {
                allVocab.add(new VocabItem(entity.english, entity.vietnamese, entity.phonetic, entity.wordType));
            }
        }

        if (allVocab.isEmpty()) {
            Toast.makeText(this, "Không có từ vựng để kiểm tra", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        seekQuestionCount.setMax(Math.max(1, allVocab.size()));
        int defaultCount = Math.min(20, allVocab.size());
        seekQuestionCount.setProgress(defaultCount);
        updateQuestionCountLabel();
    }

    private void updateQuestionCountLabel() {
        int total = allVocab.size();
        if (cbUseAll.isChecked()) {
            tvQuestionCount.setText(String.format(Locale.getDefault(), "Toàn bộ (%d câu)", total));
        } else {
            int selected = Math.max(1, Math.min(seekQuestionCount.getProgress(), total));
            tvQuestionCount.setText(String.format(Locale.getDefault(), "%d câu (tối đa %d)", selected, total));
        }
    }

    private void startTestWithSource(List<VocabItem> source, boolean forceUseAll) {
        if (source == null || source.isEmpty()) {
            Toast.makeText(this, "Không có dữ liệu để tạo bài kiểm tra", Toast.LENGTH_SHORT).show();
            return;
        }

        List<QuestionType> enabledTypes = getEnabledQuestionTypes();
        if (enabledTypes.isEmpty()) {
            Toast.makeText(this, "Chọn ít nhất một dạng câu hỏi", Toast.LENGTH_SHORT).show();
            return;
        }

        int targetCount = forceUseAll ? source.size() : getDesiredQuestionCount(source.size());
        if (targetCount <= 0) {
            Toast.makeText(this, "Số câu hỏi phải lớn hơn 0", Toast.LENGTH_SHORT).show();
            return;
        }

        List<VocabItem> pool = new ArrayList<>(source);
        Collections.shuffle(pool);
        if (targetCount > pool.size()) {
            targetCount = pool.size();
        }

        questions.clear();
        DisplayMode selectedDisplay = getSelectedDisplayMode();
        Random random = new Random();

        for (int i = 0; i < targetCount; i++) {
            VocabItem base = pool.get(i);
            TestQuestion question = buildQuestionForItem(base, enabledTypes, selectedDisplay, random, pool);
            if (question != null) {
                questions.add(question);
            }
        }

        if (questions.isEmpty()) {
            Toast.makeText(this, "Không thể tạo bài kiểm tra với tùy chọn hiện tại", Toast.LENGTH_SHORT).show();
            return;
        }

        currentIndex = 0;
        isInTest = true;
        showTestContainer();
        showQuestion();
    }

    private int getDesiredQuestionCount(int available) {
        if (cbUseAll.isChecked()) {
            return available;
        }
        int selected = seekQuestionCount.getProgress();
        if (selected <= 0) {
            selected = Math.min(10, available);
        }
        return Math.min(selected, available);
    }

    private List<QuestionType> getEnabledQuestionTypes() {
        List<QuestionType> list = new ArrayList<>();
        if (cbMultipleChoice.isChecked()) list.add(QuestionType.MULTIPLE_CHOICE);
        if (cbTrueFalse.isChecked()) list.add(QuestionType.TRUE_FALSE);
        if (cbMatching.isChecked()) list.add(QuestionType.MATCHING);
        if (cbWritten.isChecked()) list.add(QuestionType.WRITTEN);
        return list;
    }

    private DisplayMode getSelectedDisplayMode() {
        int checkedId = rgDisplayMode.getCheckedRadioButtonId();
        if (checkedId == R.id.rbDisplayTerm) return DisplayMode.TERM;
        if (checkedId == R.id.rbDisplayDefinition) return DisplayMode.DEFINITION;
        return DisplayMode.BOTH;
    }

    private TestQuestion buildQuestionForItem(VocabItem base, List<QuestionType> enabledTypes,
                                              DisplayMode selectedDisplay, Random random, List<VocabItem> pool) {
        List<QuestionType> attempts = new ArrayList<>(enabledTypes);
        Collections.shuffle(attempts, random);
        for (QuestionType type : attempts) {
            TestQuestion question = createQuestion(base, type, selectedDisplay, random, pool);
            if (question != null) {
                return question;
            }
        }
        return null;
    }

    private DisplayMode resolveDisplayMode(DisplayMode mode, Random random) {
        if (mode == DisplayMode.BOTH) {
            return random.nextBoolean() ? DisplayMode.TERM : DisplayMode.DEFINITION;
        }
        return mode;
    }

    private TestQuestion createQuestion(VocabItem base, QuestionType type, DisplayMode mode,
                                        Random random, List<VocabItem> pool) {
        switch (type) {
            case MULTIPLE_CHOICE:
                return createMultipleChoiceQuestion(base, mode, random, pool);
            case TRUE_FALSE:
                return createTrueFalseQuestion(base, mode, random, pool);
            case MATCHING:
                return createMatchingQuestion(random, pool);
            case WRITTEN:
                return createWrittenQuestion(base, mode, random);
            default:
                return null;
        }
    }

    private TestQuestion createMultipleChoiceQuestion(VocabItem base, DisplayMode mode, Random random, List<VocabItem> pool) {
        DisplayMode effective = resolveDisplayMode(mode, random);
        TestQuestion q = new TestQuestion(QuestionType.MULTIPLE_CHOICE, effective);
        q.promptIsTerm = effective == DisplayMode.TERM;
        q.prompt = q.promptIsTerm ? base.getEnglishWord() : base.getVietnameseMeaning();
        q.promptLabel = q.promptIsTerm ? "Thuật ngữ" : "Định nghĩa";
        q.correctAnswer = q.promptIsTerm ? base.getVietnameseMeaning() : base.getEnglishWord();
        q.options.add(q.correctAnswer);
        for (VocabItem item : pool) {
            if (item == base) continue;
            String candidate = q.promptIsTerm ? item.getVietnameseMeaning() : item.getEnglishWord();
            if (!q.options.contains(candidate)) {
                q.options.add(candidate);
            }
            if (q.options.size() >= 4) break;
        }
        if (q.options.size() < 2) {
            return null;
        }
        Collections.shuffle(q.options, random);
        q.relatedItems.add(base);
        return q;
    }

    private TestQuestion createTrueFalseQuestion(VocabItem base, DisplayMode mode, Random random, List<VocabItem> pool) {
        boolean canCreateFalse = pool.size() > 1;
        boolean statementTrue = !canCreateFalse || random.nextBoolean();
        VocabItem wrongItem = null;
        if (!statementTrue) {
            List<VocabItem> others = new ArrayList<>(pool);
            others.remove(base);
            Collections.shuffle(others, random);
            wrongItem = others.isEmpty() ? null : others.get(0);
            if (wrongItem == null) {
                statementTrue = true;
            }
        }

        DisplayMode effective = resolveDisplayMode(mode, random);
        boolean promptIsTerm = effective == DisplayMode.TERM;
        String left = promptIsTerm ? base.getEnglishWord() : base.getVietnameseMeaning();
        String right;
        if (statementTrue || wrongItem == null) {
            right = promptIsTerm ? base.getVietnameseMeaning() : base.getEnglishWord();
        } else {
            right = promptIsTerm ? wrongItem.getVietnameseMeaning() : wrongItem.getEnglishWord();
        }

        TestQuestion q = new TestQuestion(QuestionType.TRUE_FALSE, effective);
        q.promptIsTerm = promptIsTerm;
        q.promptLabel = "Phán đoán";
        q.prompt = String.format(Locale.getDefault(), "%s = %s", left, right);
        q.correctAnswer = statementTrue ? "Đúng" : "Sai";
        q.options.add("Đúng");
        q.options.add("Sai");
        q.relatedItems.add(base);
        if (wrongItem != null) q.relatedItems.add(wrongItem);
        return q;
    }

    private TestQuestion createWrittenQuestion(VocabItem base, DisplayMode mode, Random random) {
        DisplayMode effective = resolveDisplayMode(mode, random);
        TestQuestion q = new TestQuestion(QuestionType.WRITTEN, effective);
        q.promptIsTerm = effective == DisplayMode.TERM;
        q.prompt = q.promptIsTerm ? base.getEnglishWord() : base.getVietnameseMeaning();
        q.promptLabel = q.promptIsTerm ? "Thuật ngữ" : "Định nghĩa";
        q.correctAnswer = q.promptIsTerm ? base.getVietnameseMeaning() : base.getEnglishWord();
        q.relatedItems.add(base);
        return q;
    }

    private TestQuestion createMatchingQuestion(Random random, List<VocabItem> pool) {
        if (pool.size() < 2) {
            return null;
        }
        List<VocabItem> selection = new ArrayList<>(pool);
        Collections.shuffle(selection, random);
        int pairCount = Math.min(3, selection.size());
        if (pairCount < 2) {
            return null;
        }
        TestQuestion q = new TestQuestion(QuestionType.MATCHING, DisplayMode.TERM);
        for (int i = 0; i < pairCount; i++) {
            VocabItem item = selection.get(i);
            q.matchTerms.add(item.getEnglishWord());
            q.matchCorrectDefinitions.add(item.getVietnameseMeaning());
            q.relatedItems.add(item);
        }
        q.matchDefinitionOptions.addAll(q.matchCorrectDefinitions);
        List<String> extra = new ArrayList<>();
        for (VocabItem item : pool) {
            String def = item.getVietnameseMeaning();
            if (!q.matchDefinitionOptions.contains(def)) {
                extra.add(def);
            }
        }
        Collections.shuffle(extra, random);
        for (String candidate : extra) {
            if (q.matchDefinitionOptions.size() >= q.matchCorrectDefinitions.size() + 2) break;
            q.matchDefinitionOptions.add(candidate);
        }
        Collections.shuffle(q.matchDefinitionOptions, random);
        q.promptLabel = "Ghép thẻ";
        q.prompt = "Chọn định nghĩa phù hợp cho từng thuật ngữ";
        return q;
    }

    private void showQuestion() {
        if (currentIndex < 0 || currentIndex >= questions.size()) {
            return;
        }
        TestQuestion question = questions.get(currentIndex);
        tvProgress.setText(String.format(Locale.getDefault(), "Câu %d / %d", currentIndex + 1, questions.size()));
        progressTest.setMax(questions.size());
        progressTest.setProgress(currentIndex + 1);
        tvQuestionType.setText(question.getTypeLabel());
        tvPromptLabel.setText(question.promptLabel);
        tvPromptValue.setText(question.prompt);

        layoutMultipleChoice.setVisibility(question.type == QuestionType.MULTIPLE_CHOICE ? View.VISIBLE : View.GONE);
        layoutTrueFalse.setVisibility(question.type == QuestionType.TRUE_FALSE ? View.VISIBLE : View.GONE);
        layoutMatching.setVisibility(question.type == QuestionType.MATCHING ? View.VISIBLE : View.GONE);
        layoutWritten.setVisibility(question.type == QuestionType.WRITTEN ? View.VISIBLE : View.GONE);

        if (question.type == QuestionType.MULTIPLE_CHOICE) {
            for (int i = 0; i < choiceCards.length; i++) {
                if (i < question.options.size()) {
                    choiceCards[i].setVisibility(View.VISIBLE);
                    choiceLabels[i].setText(question.options.get(i));
                } else {
                    choiceCards[i].setVisibility(View.GONE);
                }
            }
            updateChoiceSelection(question.userAnswer);
        } else if (question.type == QuestionType.TRUE_FALSE) {
            updateTrueFalseButtons(question.userAnswer);
        } else if (question.type == QuestionType.WRITTEN) {
            if (writtenWatcher != null) {
                etWrittenAnswer.removeTextChangedListener(writtenWatcher);
            }
            etWrittenAnswer.setText(question.userAnswer != null ? question.userAnswer : "");
            etWrittenAnswer.setHint(question.promptIsTerm ? "Nhập nghĩa tiếng Việt" : "Nhập thuật ngữ tiếng Anh");
            writtenWatcher = new WrittenAnswerWatcher(question);
            etWrittenAnswer.addTextChangedListener(writtenWatcher);
        } else if (question.type == QuestionType.MATCHING) {
            renderMatchingRows(question);
        }

        boolean isLast = currentIndex == questions.size() - 1;
        btnNext.setText(isLast ? "Gửi bài" : "Lưu và tiếp tục");
        updateNextButtonState();
    }

    private void updateChoiceSelection(String answer) {
        for (int i = 0; i < choiceCards.length; i++) {
            CardView card = choiceCards[i];
            TextView label = choiceLabels[i];
            if (card.getVisibility() != View.VISIBLE) continue;
            if (answer != null && answer.equals(label.getText().toString())) {
                card.setCardBackgroundColor(Color.parseColor("#DBEAFE"));
            } else {
                card.setCardBackgroundColor(Color.WHITE);
            }
        }
    }

    private void onChoiceSelected(int idx) {
        if (currentIndex < 0 || currentIndex >= questions.size()) return;
        TestQuestion question = questions.get(currentIndex);
        if (question.type != QuestionType.MULTIPLE_CHOICE) return;
        if (idx >= question.options.size()) return;
        question.userAnswer = question.options.get(idx);
        updateChoiceSelection(question.userAnswer);
        updateNextButtonState();
    }

    private void selectTrueFalse(boolean isTrue) {
        if (currentIndex < 0 || currentIndex >= questions.size()) return;
        TestQuestion question = questions.get(currentIndex);
        if (question.type != QuestionType.TRUE_FALSE) return;
        question.userAnswer = isTrue ? "Đúng" : "Sai";
        updateTrueFalseButtons(question.userAnswer);
        updateNextButtonState();
    }

    private void updateTrueFalseButtons(String answer) {
        boolean trueSelected = "Đúng".equalsIgnoreCase(answer);
        boolean falseSelected = "Sai".equalsIgnoreCase(answer);
        styleDecisionButton(btnTrue, trueSelected);
        styleDecisionButton(btnFalse, falseSelected);
    }

    private void styleDecisionButton(Button button, boolean selected) {
        if (selected) {
            button.setBackgroundResource(R.drawable.bg_btn_primary);
            button.setTextColor(Color.WHITE);
        } else {
            button.setBackgroundResource(R.drawable.bg_btn_outline);
            button.setTextColor(Color.parseColor("#111827"));
        }
    }

    private void renderMatchingRows(TestQuestion question) {
        layoutMatchRows.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < question.matchTerms.size(); i++) {
            View row = inflater.inflate(R.layout.item_match_dropdown, layoutMatchRows, false);
            TextView tvTerm = row.findViewById(R.id.tvMatchTerm);
            Spinner spinner = row.findViewById(R.id.spinnerDefinitions);
            tvTerm.setText(question.matchTerms.get(i));

            List<String> items = new ArrayList<>();
            items.add("Chọn định nghĩa phù hợp");
            items.addAll(question.matchDefinitionOptions);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            String saved = question.userMatchSelections.get(i);
            if (saved != null) {
                int index = question.matchDefinitionOptions.indexOf(saved);
                spinner.setSelection(index >= 0 ? index + 1 : 0);
            } else {
                spinner.setSelection(0);
            }

            spinner.setOnItemSelectedListener(new MatchSelectionListener(question, i));
            layoutMatchRows.addView(row);
        }
    }

    private void updateNextButtonState() {
        boolean enabled = false;
        if (currentIndex >= 0 && currentIndex < questions.size()) {
            enabled = questions.get(currentIndex).isAnswered();
        }
        setPrimaryButtonState(btnNext, enabled);
    }

    private void setPrimaryButtonState(Button button, boolean enabled) {
        button.setEnabled(enabled);
        button.setBackgroundResource(enabled ? R.drawable.bg_btn_primary : R.drawable.bg_btn_primary_disabled);
    }

    private void onNextClicked() {
        if (currentIndex < 0 || currentIndex >= questions.size()) return;
        TestQuestion question = questions.get(currentIndex);
        if (!question.isAnswered()) {
            Toast.makeText(this, "Hãy hoàn thành câu hỏi này trước", Toast.LENGTH_SHORT).show();
            return;
        }
        if (currentIndex == questions.size() - 1) {
            showResult();
        } else {
            currentIndex++;
            showQuestion();
        }
    }

    private void showTestContainer() {
        setupContainer.setVisibility(View.GONE);
        resultContainer.setVisibility(View.GONE);
        testContainer.setVisibility(View.VISIBLE);
    }

    private void showSetup() {
        isInTest = false;
        setupContainer.setVisibility(View.VISIBLE);
        testContainer.setVisibility(View.GONE);
        resultContainer.setVisibility(View.GONE);
    }

    private void showResult() {
        isInTest = false;
        int correctCount = 0;
        for (TestQuestion question : questions) {
            if (question.isCorrect()) {
                correctCount++;
            }
        }
        int total = questions.size();
        int percent = Math.round((correctCount * 100f) / total);
        tvScore.setText(percent + "%");
        tvScoreDetail.setText(String.format(Locale.getDefault(), "%d / %d câu dúng", correctCount, total));

        lastWrongItems = extractWrongItems();
        btnRetryWrong.setVisibility(lastWrongItems.isEmpty() ? View.GONE : View.VISIBLE);

        resultAdapter.notifyDataSetChanged();
        setupContainer.setVisibility(View.GONE);
        testContainer.setVisibility(View.GONE);
        resultContainer.setVisibility(View.VISIBLE);
    }

    private List<VocabItem> extractWrongItems() {
        Map<String, VocabItem> map = new LinkedHashMap<>();
        for (TestQuestion question : questions) {
            if (!question.isCorrect()) {
                for (VocabItem item : question.relatedItems) {
                    if (item == null) continue;
                    String key = item.getEnglishWord() + "|" + item.getVietnameseMeaning();
                    if (!map.containsKey(key)) {
                        map.put(key, new VocabItem(item.getEnglishWord(), item.getVietnameseMeaning(), item.getPhonetic(), item.getWordType()));
                    }
                }
            }
        }
        return new ArrayList<>(map.values());
    }

    private class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_result, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            TestQuestion question = questions.get(position);
            holder.tvQuestion.setText(String.format(Locale.getDefault(), "Câu %d - %s", position + 1, question.getTypeLabel()));
            holder.tvUserAnswer.setText("Bạn trả lời: " + question.getUserAnswerText());
            holder.tvCorrectAnswer.setText("Đáp án đúng: " + question.getCorrectAnswerText());
            holder.indicator.setBackgroundColor(question.isCorrect() ? Color.parseColor("#10B981") : Color.parseColor("#EF4444"));
        }

        @Override
        public int getItemCount() {
            return questions.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvQuestion;
            TextView tvUserAnswer;
            TextView tvCorrectAnswer;
            View indicator;

            ViewHolder(View itemView) {
                super(itemView);
                tvQuestion = itemView.findViewById(R.id.tvResultQuestion);
                tvUserAnswer = itemView.findViewById(R.id.tvUserAnswer);
                tvCorrectAnswer = itemView.findViewById(R.id.tvCorrectAnswer);
                indicator = itemView.findViewById(R.id.viewIndicator);
            }
        }
    }

    private static class TestQuestion {
        final QuestionType type;
        final DisplayMode displayMode;
        String promptLabel;
        String prompt;
        String correctAnswer;
        final List<String> options = new ArrayList<>();
        final List<String> matchTerms = new ArrayList<>();
        final List<String> matchCorrectDefinitions = new ArrayList<>();
        final List<String> matchDefinitionOptions = new ArrayList<>();
        final Map<Integer, String> userMatchSelections = new LinkedHashMap<>();
        final List<VocabItem> relatedItems = new ArrayList<>();
        String userAnswer;
        boolean promptIsTerm;

        TestQuestion(QuestionType type, DisplayMode displayMode) {
            this.type = type;
            this.displayMode = displayMode;
        }

        boolean isAnswered() {
            if (type == QuestionType.MATCHING) {
                return !matchTerms.isEmpty() && userMatchSelections.size() == matchTerms.size();
            } else {
                return userAnswer != null && !userAnswer.trim().isEmpty();
            }
        }

        boolean isCorrect() {
            switch (type) {
                case MATCHING:
                    if (userMatchSelections.size() != matchTerms.size()) return false;
                    for (int i = 0; i < matchTerms.size(); i++) {
                        String ans = userMatchSelections.get(i);
                        if (ans == null || !ans.equalsIgnoreCase(matchCorrectDefinitions.get(i))) {
                            return false;
                        }
                    }
                    return true;
                case WRITTEN:
                    return userAnswer != null && userAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
                default:
                    return userAnswer != null && userAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
            }
        }

        String getTypeLabel() {
            switch (type) {
                case MULTIPLE_CHOICE:
                    return "Trắc nghiệm";
                case TRUE_FALSE:
                    return "Đúng / Sai";
                case MATCHING:
                    return "Ghép thẻ";
                case WRITTEN:
                    return "Tự luận";
                default:
                    return "Câu hỏi";
            }
        }

        String getUserAnswerText() {
            if (!isAnswered()) return "Chưa trả lời";
            if (type == QuestionType.MATCHING) {
                return formatPairs(userMatchSelections, matchTerms);
            }
            return userAnswer;
        }

        String getCorrectAnswerText() {
            if (type == QuestionType.MATCHING) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < matchTerms.size(); i++) {
                    if (i > 0) sb.append("; ");
                    sb.append(matchTerms.get(i)).append(" -> ").append(matchCorrectDefinitions.get(i));
                }
                return sb.toString();
            }
            return correctAnswer;
        }

        private String formatPairs(Map<Integer, String> selections, List<String> terms) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < terms.size(); i++) {
                if (i > 0) sb.append("; ");
                sb.append(terms.get(i)).append(" -> ");
                String ans = selections.get(i);
                    sb.append(ans == null ? "?" : ans);
            }
            return sb.toString();
        }
    }

    private class WrittenAnswerWatcher implements TextWatcher {
        private final TestQuestion question;

        WrittenAnswerWatcher(TestQuestion question) {
            this.question = question;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            question.userAnswer = s.toString().trim();
            updateNextButtonState();
        }
    }

    private class MatchSelectionListener implements AdapterView.OnItemSelectedListener {
        private final TestQuestion question;
        private final int rowIndex;

        MatchSelectionListener(TestQuestion question, int rowIndex) {
            this.question = question;
            this.rowIndex = rowIndex;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                question.userMatchSelections.remove(rowIndex);
            } else {
                int realIndex = position - 1;
                if (realIndex >= 0 && realIndex < question.matchDefinitionOptions.size()) {
                    String value = question.matchDefinitionOptions.get(realIndex);
                    question.userMatchSelections.put(rowIndex, value);
                }
            }
            updateNextButtonState();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            question.userMatchSelections.remove(rowIndex);
            updateNextButtonState();
        }
    }
}


