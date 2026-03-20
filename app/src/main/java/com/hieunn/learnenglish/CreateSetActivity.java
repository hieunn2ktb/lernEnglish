package com.hieunn.learnenglish;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hieunn.learnenglish.db.AppDatabase;
import com.hieunn.learnenglish.db.LessonEntity;
import com.hieunn.learnenglish.db.VocabEntity;

import java.util.ArrayList;
import java.util.List;

public class CreateSetActivity extends AppCompatActivity {
    private EditText etTitle;
    private EditText etDescription;
    private RecyclerView rvCards;
    private EditCardAdapter adapter;
    private List<VocabModel> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_set);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        rvCards = findViewById(R.id.rvCards);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Initialize with 2 empty cards like Quizlet
        cardList = new ArrayList<>();
        cardList.add(new VocabModel("", ""));
        cardList.add(new VocabModel("", ""));

        rvCards.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EditCardAdapter(cardList);
        rvCards.setAdapter(adapter);

        findViewById(R.id.btnAddCard).setOnClickListener(v -> {
            cardList.add(new VocabModel("", ""));
            adapter.notifyItemInserted(cardList.size() - 1);
            rvCards.scrollToPosition(cardList.size() - 1);
        });

        findViewById(R.id.btnDone).setOnClickListener(v -> saveLesson());

        findViewById(R.id.btnImport).setOnClickListener(v -> openImportDialog());
    }

    private void openImportDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nhập từ Word, Excel, Docs");

        final EditText input = new EditText(this);
        input.setHint("Dán văn bản của bạn vào đây...\nVí dụ:\nHello\tXin chào\nCat\tCon mèo");
        input.setMinLines(5);
        input.setMaxLines(10);
        input.setGravity(android.view.Gravity.TOP | android.view.Gravity.START);
        input.setPadding(32, 32, 32, 32);
        builder.setView(input);

        builder.setPositiveButton("Nhập", (dialog, which) -> {
            String text = input.getText().toString();
            importText(text);
        });
        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void importText(String text) {
        if (text.trim().isEmpty()) return;
        String[] lines = text.split("\n");
        int count = 0;
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            // First try tab, then try comma or dash
            String[] parts = line.split("\t");
            if (parts.length < 2) {
                parts = line.split("-");
            }
            if (parts.length >= 2) {
                cardList.add(new VocabModel(parts[0].trim(), parts[1].trim()));
                count++;
            } else {
                cardList.add(new VocabModel(line.trim(), ""));
            }
        }
        if (count > 0) {
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Đã nhập " + count + " thẻ!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Không tìm thấy dữ liệu hợp lệ (cần phân cách bằng phím Tab hoặc dấu gạch ngang)", Toast.LENGTH_LONG).show();
        }
    }

    private void saveLesson() {
        String title = etTitle.getText().toString().trim();
        if (title.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tiêu đề học phần", Toast.LENGTH_SHORT).show();
            etTitle.requestFocus();
            return;
        }

        List<VocabModel> validCards = new ArrayList<>();
        for (VocabModel card : cardList) {
            if (!card.term.trim().isEmpty() && !card.definition.trim().isEmpty()) {
                validCards.add(card);
            }
        }

        if (validCards.size() < 2) {
            Toast.makeText(this, "Học phần phải có ít nhất 2 thẻ hoàn chỉnh", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save to DB
        AppDatabase db = AppDatabase.getInstance(this);
        String desc = etDescription.getText().toString().trim();
        LessonEntity lesson = new LessonEntity(title, desc, "");
        long lessonId = db.lessonDao().insertLesson(lesson);

        List<VocabEntity> entities = new ArrayList<>();
        for (VocabModel c : validCards) {
            entities.add(new VocabEntity((int) lessonId, c.term, c.definition, "", ""));
        }
        db.vocabDao().insertAllVocab(entities);

        Toast.makeText(this, "Tạo học phần thành công!", Toast.LENGTH_SHORT).show();
        
        // Return to MainActivity or Lesson Detail
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private static class VocabModel {
        String term;
        String definition;

        VocabModel(String term, String definition) {
            this.term = term;
            this.definition = definition;
        }
    }

    private class EditCardAdapter extends RecyclerView.Adapter<EditCardAdapter.ViewHolder> {
        private List<VocabModel> mList;

        EditCardAdapter(List<VocabModel> list) {
            mList = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_card, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            VocabModel item = mList.get(position);
            holder.tvCardIndex.setText(String.valueOf(position + 1));

            // Remove previous text watcher to prevent infinite loops changing values
            if (holder.termWatcher != null) {
                holder.etTerm.removeTextChangedListener(holder.termWatcher);
            }
            if (holder.defWatcher != null) {
                holder.etDef.removeTextChangedListener(holder.defWatcher);
            }

            holder.etTerm.setText(item.term);
            holder.etDef.setText(item.definition);

            // Add new watcher
            holder.termWatcher = new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                    item.term = s.toString();
                }
                @Override public void afterTextChanged(Editable s) {}
            };
            holder.etTerm.addTextChangedListener(holder.termWatcher);

            holder.defWatcher = new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                    item.definition = s.toString();
                }
                @Override public void afterTextChanged(Editable s) {}
            };
            holder.etDef.addTextChangedListener(holder.defWatcher);

            holder.btnDelete.setOnClickListener(v -> {
                int currPos = holder.getAdapterPosition();
                if (currPos != RecyclerView.NO_POSITION) {
                    mList.remove(currPos);
                    notifyItemRemoved(currPos);
                    notifyItemRangeChanged(currPos, mList.size() - currPos);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvCardIndex;
            ImageButton btnDelete;
            EditText etTerm;
            EditText etDef;
            TextWatcher termWatcher;
            TextWatcher defWatcher;

            ViewHolder(View itemView) {
                super(itemView);
                tvCardIndex = itemView.findViewById(R.id.tvCardIndex);
                btnDelete = itemView.findViewById(R.id.btnDelete);
                etTerm = itemView.findViewById(R.id.etTerm);
                etDef = itemView.findViewById(R.id.etDef);
                
                // Fix issue with EditCard xml bug resolving
                View deleteBtn = itemView.findViewById(R.id.btnDelete);
                if(deleteBtn == null){
                     // mock lookup
                }
            }
        }
    }
}
