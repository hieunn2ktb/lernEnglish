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
import com.hieunn.learnenglish.db.VocabEntity;

public class AddVocabActivity extends AppCompatActivity {

    private int lessonId;
    private int addedCount = 0;
    private StringBuilder addedList = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_vocab);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lessonId = getIntent().getIntExtra("lessonId", -1);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        EditText etEnglish = findViewById(R.id.etEnglish);
        EditText etVietnamese = findViewById(R.id.etVietnamese);
        EditText etPhonetic = findViewById(R.id.etPhonetic);
        EditText etWordType = findViewById(R.id.etWordType);
        TextView tvCount = findViewById(R.id.tvCount);
        TextView tvAddedList = findViewById(R.id.tvAddedList);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            String english = etEnglish.getText().toString().trim();
            String vietnamese = etVietnamese.getText().toString().trim();
            String phonetic = etPhonetic.getText().toString().trim();
            String wordType = etWordType.getText().toString().trim();

            if (english.isEmpty() || vietnamese.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập từ tiếng Anh và nghĩa!", Toast.LENGTH_SHORT).show();
                return;
            }

            AppDatabase db = AppDatabase.getInstance(this);
            db.vocabDao().insertVocab(new VocabEntity(lessonId, english, vietnamese, phonetic, wordType));

            addedCount++;
            tvCount.setText(addedCount + " từ");
            addedList.append(addedCount).append(". ").append(english).append(" - ").append(vietnamese).append("\n");
            tvAddedList.setText(addedList.toString());

            // Xóa form
            etEnglish.setText("");
            etVietnamese.setText("");
            etPhonetic.setText("");
            etWordType.setText("");
            etEnglish.requestFocus();

            Toast.makeText(this, "Đã thêm: " + english, Toast.LENGTH_SHORT).show();
        });

        Button btnDone = findViewById(R.id.btnDone);
        btnDone.setOnClickListener(v -> {
            // Chuyển sang thêm câu hỏi ngữ pháp
            Intent intent = new Intent(this, AddGrammarQuizActivity.class);
            intent.putExtra("lessonId", lessonId);
            startActivity(intent);
            finish();
        });
    }
}
