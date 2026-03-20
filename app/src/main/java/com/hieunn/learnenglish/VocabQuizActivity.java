package com.hieunn.learnenglish;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VocabQuizActivity extends AppCompatActivity {

        // Mỗi câu hỏi: [từ tiếng Anh, đáp_án_đúng(title), đáp_án_đúng(desc),
        // sai1(title), sai1(desc), sai2(title), sai2(desc), sai3(title), sai3(desc),
        // giải_thích]
        private String[][] quizData = {
                        // === ĐẠI TỪ NHÂN XƯNG ===
                        { "I", "Tôi", "Đại từ nhân xưng ngôi thứ nhất số ít",
                                        "Bạn", "Đại từ nhân xưng ngôi thứ hai",
                                        "Anh ấy", "Đại từ nhân xưng ngôi thứ ba nam",
                                        "Họ", "Đại từ nhân xưng ngôi thứ ba số nhiều",
                                        "\"I\" là đại từ nhân xưng ngôi thứ nhất số ít, nghĩa là \"Tôi\"." },
                        { "You", "Bạn / Các bạn", "Đại từ ngôi thứ hai",
                                        "Tôi", "Đại từ ngôi thứ nhất",
                                        "Cô ấy", "Đại từ ngôi thứ ba nữ",
                                        "Chúng tôi", "Đại từ ngôi thứ nhất số nhiều",
                                        "\"You\" là đại từ ngôi thứ hai, có thể dùng cho số ít hoặc số nhiều." },
                        { "She", "Cô ấy", "Đại từ ngôi thứ ba số ít (nữ)",
                                        "Anh ấy", "Đại từ ngôi thứ ba (nam)",
                                        "Nó", "Đại từ chỉ sự vật",
                                        "Tôi", "Đại từ ngôi thứ nhất",
                                        "\"She\" là đại từ ngôi thứ ba số ít, chỉ người nữ." },
                        { "He", "Anh ấy", "Đại từ ngôi thứ ba số ít (nam)",
                                        "Cô ấy", "Đại từ ngôi thứ ba (nữ)",
                                        "Họ", "Đại từ ngôi thứ ba số nhiều",
                                        "Bạn", "Đại từ ngôi thứ hai",
                                        "\"He\" là đại từ ngôi thứ ba số ít, chỉ người nam." },
                        { "They", "Họ / Bọn họ", "Đại từ ngôi thứ ba số nhiều",
                                        "Chúng tôi", "Đại từ ngôi thứ nhất số nhiều",
                                        "Anh ấy", "Đại từ ngôi thứ ba số ít",
                                        "Bạn", "Đại từ ngôi thứ hai",
                                        "\"They\" là đại từ ngôi thứ ba số nhiều, chỉ nhiều người/vật." },

                        // === TÍNH TỪ SỞ HỮU ===
                        { "My", "Của tôi", "Tính từ sở hữu ngôi thứ nhất",
                                        "Của bạn", "Tính từ sở hữu ngôi thứ hai",
                                        "Của anh ấy", "Tính từ sở hữu ngôi thứ ba nam",
                                        "Của họ", "Tính từ sở hữu ngôi thứ ba số nhiều",
                                        "\"My\" là tính từ sở hữu đi với I, nghĩa là \"của tôi\". VD: my cat = con mèo của tôi." },
                        { "Their", "Của họ", "Tính từ sở hữu ngôi thứ ba số nhiều",
                                        "Của chúng tôi", "Tính từ sở hữu ngôi thứ nhất nhiều",
                                        "Của cô ấy", "Tính từ sở hữu ngôi thứ ba nữ",
                                        "Của bạn", "Tính từ sở hữu ngôi thứ hai",
                                        "\"Their\" là tính từ sở hữu đi với They, nghĩa là \"của họ\". VD: their mother = mẹ của họ." },
                        { "His", "Của anh ấy", "Tính từ sở hữu ngôi thứ ba nam",
                                        "Của cô ấy", "Tính từ sở hữu ngôi thứ ba nữ",
                                        "Của tôi", "Tính từ sở hữu ngôi thứ nhất",
                                        "Của nó", "Tính từ sở hữu cho sự vật",
                                        "\"His\" là tính từ sở hữu đi với He, nghĩa là \"của anh ấy\". VD: his book = sách của anh ấy." },
                        { "Her", "Của cô ấy", "Tính từ sở hữu ngôi thứ ba nữ",
                                        "Của anh ấy", "Tính từ sở hữu ngôi thứ ba nam",
                                        "Của họ", "Tính từ sở hữu ngôi thứ ba nhiều",
                                        "Của chúng tôi", "Tính từ sở hữu ngôi thứ nhất nhiều",
                                        "\"Her\" là tính từ sở hữu đi với She, nghĩa là \"của cô ấy\". VD: her sister = chị gái của cô ấy." },

                        // === DANH TỪ ===
                        { "Teacher", "Giáo viên", "Người dạy học",
                                        "Học sinh", "Người đi học",
                                        "Bố", "Người trong gia đình",
                                        "Đàn ông", "Giới tính nam",
                                        "\"Teacher\" là danh từ chỉ người, nghĩa là giáo viên - người dạy học." },
                        { "Student", "Học sinh / Sinh viên", "Người đi học",
                                        "Giáo viên", "Người dạy học",
                                        "Mẹ", "Người trong gia đình",
                                        "Chị gái", "Người trong gia đình",
                                        "\"Student\" là danh từ chỉ người, nghĩa là học sinh hoặc sinh viên." },
                        { "Father", "Bố / Cha", "Người cha trong gia đình",
                                        "Mẹ", "Người mẹ trong gia đình",
                                        "Chị gái", "Người chị em trong gia đình",
                                        "Giáo viên", "Người dạy học",
                                        "\"Father\" là danh từ chỉ người, nghĩa là bố hoặc cha." },

                        // === TÍNH TỪ ===
                        { "Happy", "Vui vẻ / Hạnh phúc", "Tính từ chỉ cảm xúc tích cực",
                                        "Buồn", "Tính từ chỉ cảm xúc tiêu cực",
                                        "Cao", "Tính từ chỉ chiều cao",
                                        "To", "Tính từ chỉ kích thước",
                                        "\"Happy\" là tính từ chỉ cảm xúc tích cực, nghĩa là vui vẻ hoặc hạnh phúc." },
                        { "Tall", "Cao", "Tính từ chỉ chiều cao lớn",
                                        "Thấp", "Tính từ chỉ chiều cao nhỏ",
                                        "To", "Tính từ chỉ kích thước",
                                        "Buồn", "Tính từ chỉ cảm xúc",
                                        "\"Tall\" là tính từ chỉ chiều cao, nghĩa là cao. Trái nghĩa với Short (thấp)." },
                        { "Big", "To / Lớn", "Tính từ chỉ kích thước lớn",
                                        "Nhỏ", "Tính từ chỉ kích thước nhỏ",
                                        "Cao", "Tính từ chỉ chiều cao",
                                        "Vui", "Tính từ chỉ cảm xúc",
                                        "\"Big\" là tính từ chỉ kích thước, nghĩa là to hoặc lớn. Trái nghĩa với Small (nhỏ)." },
        };

        private int currentQuestion = 0;
        private int correctCount = 0;
        private int wrongCount = 0;
        private boolean answered = false;
        private int correctPosition = 0;

        private CardView[] cards;
        private TextView[] titles;
        private TextView[] descs;
        private ImageView[] icons;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                EdgeToEdge.enable(this);
                setContentView(R.layout.activity_vocab_quiz);

                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                        return insets;
                });

                ImageButton btnBack = findViewById(R.id.btnBack);
                btnBack.setOnClickListener(v -> finish());

                ImageButton btnClose = findViewById(R.id.btnClose);
                btnClose.setOnClickListener(v -> finish());

                cards = new CardView[] {
                                findViewById(R.id.cardOptionA),
                                findViewById(R.id.cardOptionB),
                                findViewById(R.id.cardOptionC),
                                findViewById(R.id.cardOptionD)
                };

                titles = new TextView[] {
                                findViewById(R.id.tvOptionA_title),
                                findViewById(R.id.tvOptionB_title),
                                findViewById(R.id.tvOptionC_title),
                                findViewById(R.id.tvOptionD_title)
                };

                descs = new TextView[] {
                                findViewById(R.id.tvOptionA_desc),
                                findViewById(R.id.tvOptionB_desc),
                                findViewById(R.id.tvOptionC_desc),
                                findViewById(R.id.tvOptionD_desc)
                };

                icons = new ImageView[] {
                                findViewById(R.id.iconA),
                                findViewById(R.id.iconB),
                                findViewById(R.id.iconC),
                                findViewById(R.id.iconD)
                };

                for (int i = 0; i < 4; i++) {
                        int idx = i;
                        cards[i].setOnClickListener(v -> onOptionClicked(idx));
                }

                Button btnContinue = findViewById(R.id.btnContinue);
                btnContinue.setOnClickListener(v -> {
                        currentQuestion++;
                        if (currentQuestion < quizData.length) {
                                loadQuestion();
                        } else {
                                finish();
                        }
                });

                loadQuestion();
        }

        private void loadQuestion() {
                answered = false;
                String[] q = quizData[currentQuestion];

                TextView tvWord = findViewById(R.id.tvQuestionWord);
                tvWord.setText(q[0]);

                TextView tvCount = findViewById(R.id.tvQuestionCount);
                tvCount.setText("Câu " + (currentQuestion + 1) + " / " + quizData.length);

                ProgressBar progressBar = findViewById(R.id.progressBar);
                progressBar.setProgress((currentQuestion + 1) * 100 / quizData.length);

                findViewById(R.id.cardFeedback).setVisibility(View.GONE);
                findViewById(R.id.btnContinue).setVisibility(View.GONE);

                correctPosition = (int) (Math.random() * 4);

                int wrongIdx = 0;
                for (int i = 0; i < 4; i++) {
                        cards[i].setCardBackgroundColor(0xFFFFFFFF);
                        icons[i].setVisibility(View.GONE);

                        if (i == correctPosition) {
                                titles[i].setText(q[1]);
                                descs[i].setText(q[2]);
                        } else {
                                int pairStart = 3 + wrongIdx * 2;
                                titles[i].setText(q[pairStart]);
                                descs[i].setText(q[pairStart + 1]);
                                wrongIdx++;
                        }
                }

                updateStats();
        }

        private void onOptionClicked(int idx) {
                if (answered)
                        return;
                answered = true;

                boolean isCorrect = (idx == correctPosition);

                CardView feedbackCard = findViewById(R.id.cardFeedback);
                TextView feedbackTitle = findViewById(R.id.tvFeedbackTitle);
                TextView feedbackDesc = findViewById(R.id.tvFeedbackDesc);

                if (isCorrect) {
                        correctCount++;
                        cards[idx].setCardBackgroundColor(0xFFD1FAE5);
                        icons[idx].setVisibility(View.VISIBLE);
                        icons[idx].setImageResource(R.drawable.ic_check);
                        icons[idx].setColorFilter(0xFF10B981);

                        feedbackCard.setCardBackgroundColor(0xFFF0FDF4);
                        feedbackTitle.setText("Tuyệt vời!");
                        feedbackTitle.setTextColor(0xFF059669);
                } else {
                        wrongCount++;
                        cards[idx].setCardBackgroundColor(0xFFFEE2E2);
                        icons[idx].setVisibility(View.VISIBLE);
                        icons[idx].setImageResource(R.drawable.ic_close);
                        icons[idx].setColorFilter(0xFFEF4444);

                        cards[correctPosition].setCardBackgroundColor(0xFFD1FAE5);
                        icons[correctPosition].setVisibility(View.VISIBLE);
                        icons[correctPosition].setImageResource(R.drawable.ic_check);
                        icons[correctPosition].setColorFilter(0xFF10B981);

                        feedbackCard.setCardBackgroundColor(0xFFFEF2F2);
                        feedbackTitle.setText("Chưa đúng!");
                        feedbackTitle.setTextColor(0xFFDC2626);
                }

                String[] q = quizData[currentQuestion];
                feedbackDesc.setText(q[9]);
                feedbackCard.setVisibility(View.VISIBLE);
                findViewById(R.id.btnContinue).setVisibility(View.VISIBLE);

                updateStats();
        }

        private void updateStats() {
                int remaining = quizData.length - currentQuestion - (answered ? 1 : 0);
                if (remaining < 0)
                        remaining = 0;

                ((TextView) findViewById(R.id.tvCorrectCount)).setText(String.valueOf(correctCount));
                ((TextView) findViewById(R.id.tvWrongCount)).setText(String.valueOf(wrongCount));
                ((TextView) findViewById(R.id.tvRemainingCount)).setText(String.valueOf(remaining));
        }
}
