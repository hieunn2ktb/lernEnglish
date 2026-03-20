package com.hieunn.learnenglish;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.LessonEntity;

public class CreateLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_lesson);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        EditText etTitle = findViewById(R.id.etTitle);
        EditText etDesc = findViewById(R.id.etDescription);
        EditText etVideo = findViewById(R.id.etVideoUrl);

        Button btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String desc = etDesc.getText().toString().trim();
            String video = etVideo.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên bài học!", Toast.LENGTH_SHORT).show();
                return;
            }

            AppDatabase db = AppDatabase.getInstance(this);
            LessonEntity lesson = new LessonEntity(title, desc, video);
            long lessonId = db.lessonDao().insertLesson(lesson);

            Toast.makeText(this, "Đã tạo bài học!", Toast.LENGTH_SHORT).show();

            // Chuyển sang thêm từ vựng
            Intent intent = new Intent(this, AddVocabActivity.class);
            intent.putExtra("lessonId", (int) lessonId);
            startActivity(intent);
            finish();
        });
    }
}
