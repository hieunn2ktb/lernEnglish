package com.hieunn.learnenglish;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.GrammarQuizEntity;

public class AddGrammarQuizActivity extends AppCompatActivity {

    private int lessonId;
    private int addedCount = 0;
    private StringBuilder addedList = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_grammar_quiz);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lessonId = getIntent().getIntExtra("lessonId", -1);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        EditText etQuestion = findViewById(R.id.etQuestion);
        EditText etA = findViewById(R.id.etOptionA);
        EditText etB = findViewById(R.id.etOptionB);
        EditText etC = findViewById(R.id.etOptionC);
        EditText etD = findViewById(R.id.etOptionD);
        EditText etCorrect = findViewById(R.id.etCorrect);
        TextView tvCount = findViewById(R.id.tvCount);
        TextView tvAddedList = findViewById(R.id.tvAddedList);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            String question = etQuestion.getText().toString().trim();
            String a = etA.getText().toString().trim();
            String b = etB.getText().toString().trim();
            String c = etC.getText().toString().trim();
            String d = etD.getText().toString().trim();
            String correct = etCorrect.getText().toString().trim();

            if (question.isEmpty() || a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || correct.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show();
                return;
            }

            AppDatabase db = AppDatabase.getInstance(this);
            db.grammarQuizDao().insertQuiz(new GrammarQuizEntity(lessonId, question, a, b, c, d, correct));

            addedCount++;
            tvCount.setText(addedCount + " câu");
            addedList.append(addedCount).append(". ").append(question).append("\n");
            tvAddedList.setText(addedList.toString());

            // Xóa form
            etQuestion.setText("");
            etA.setText("");
            etB.setText("");
            etC.setText("");
            etD.setText("");
            etCorrect.setText("");
            etQuestion.requestFocus();

            Toast.makeText(this, "Đã thêm câu hỏi!", Toast.LENGTH_SHORT).show();
        });

        Button btnDone = findViewById(R.id.btnDone);
        btnDone.setOnClickListener(v -> {
            Toast.makeText(this, "Đã lưu bài học thành công!", Toast.LENGTH_LONG).show();
            // Quay về MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}
