package com.hieunn.learnenglish.db;

import android.content.Context;
import android.content.SharedPreferences;

public class DatabaseSeeder {

        private static final String PREF_NAME = "db_seeder";
        private static final String KEY_SEEDED = "data_seeded_v3"; // Đổi version để chạy lại

        public static void seedIfNeeded(Context context) {
                SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                AppDatabase db = AppDatabase.getInstance(context);

                // Luôn kiểm tra và ép cập nhật URL YouTube cho Ngày 1 kể cả khi đã seed
                try {
                        for (LessonEntity lesson : db.lessonDao().getAllLessons()) {
                                if (lesson.title.contains("Ngày 1")
                                                && (lesson.videoUrl == null || lesson.videoUrl.trim().isEmpty())) {
                                        lesson.videoUrl = "x4F1npn2g8Q";
                                        db.lessonDao().updateLesson(lesson);
                                        android.util.Log.d("LearnEnglish", "Updated videoUrl for Day 1");
                                }
                        }
                } catch (Exception e) {
                        android.util.Log.e("LearnEnglish", "Error updating Day 1 Video", e);
                }

                if (prefs.getBoolean(KEY_SEEDED, false))
                        return;

                // Xóa cũ nếu đã seed v1 để làm sạch lại (giữ lại lessons do user tạo nếu cẩn
                // thận,
                // nhưng ở đây ta cứ seed thêm hoặc clear)
                // Để an toàn, chỉ gọi khi cần, hoặc seed luôn
                int count = db.lessonDao().getAllLessons().size();
                if (count == 0 || count == 1) { // Lần đầu hoặc mới có Ngày 1
                        // Clear all để đồng bộ ID
                        db.vocabDao().deleteByLessonId(1);
                        db.grammarQuizDao().deleteByLessonId(1);
                        db.vocabQuizDao().deleteByLessonId(1);
                        db.lessonDao().deleteLessonById(1);

                        seedDay1(db);
                        seedDay2(db);
                        seedDay3(db);
                        seedDay4(db);
                        seedDay5(db);
                        seedDay6(db);
                }

                prefs.edit().putBoolean(KEY_SEEDED, true).apply();
        }

        private static void insertVocab(AppDatabase db, int id, String en, String vn, String type) {
                db.vocabDao().insertVocab(new VocabEntity(id, en, vn, "", type));
        }

        private static void insertQuiz(AppDatabase db, int id, String q, String a, String b, String c, String d,
                        String correct) {
                db.grammarQuizDao().insertQuiz(new GrammarQuizEntity(id, q, a, b, c, d, correct));
        }

        private static void seedDay1(AppDatabase db) {
                LessonEntity lesson = new LessonEntity("Ngày 1: Động từ To Be", "Thể khẳng định và phủ định",
                                "x4F1npn2g8Q");
                int id = (int) db.lessonDao().insertLesson(lesson);

                String type = "Pronoun";
                insertVocab(db, id, "I", "Tôi", type);
                insertVocab(db, id, "You", "Bạn", type);
                insertVocab(db, id, "We", "Chúng tôi", type);
                insertVocab(db, id, "They", "Họ", type);
                insertVocab(db, id, "She", "Cô ấy", type);
                insertVocab(db, id, "He", "Anh ấy", type);
                insertVocab(db, id, "It", "Nó", type);

                type = "Noun";
                insertVocab(db, id, "Man", "Đàn ông", type);
                insertVocab(db, id, "Teacher", "Giáo viên", type);
                insertVocab(db, id, "Student", "Học sinh", type);
                insertVocab(db, id, "Dog", "Con chó", type);
                insertVocab(db, id, "Car", "Ô tô", type);
                insertVocab(db, id, "Book", "Cuốn sách", type);

                type = "Adjective";
                insertVocab(db, id, "Tall", "Cao", type);
                insertVocab(db, id, "Short", "Thấp", type);
                insertVocab(db, id, "Big", "To", type);
                insertVocab(db, id, "Small", "Nhỏ", type);
                insertVocab(db, id, "Happy", "Vui vẻ", type);
                insertVocab(db, id, "Sad", "Buồn", type);

                insertQuiz(db, id, "I ______ a student.", "am", "is", "are", "be", "am");
                insertQuiz(db, id, "She ______ very happy.", "am", "is", "are", "be", "is");
                insertQuiz(db, id, "They ______ from Vietnam.", "am", "is", "are", "be", "are");
                insertQuiz(db, id, "He ______ not a teacher.", "am", "is", "are", "be", "is");
                insertQuiz(db, id, "We ______ good friends.", "am", "is", "are", "be", "are");
                insertQuiz(db, id, "Dịch: măng của tôi (my mother)", "my mother", "her mother", "his mother",
                                "your mother", "my mother");
        }

        private static void seedDay2(AppDatabase db) {
                LessonEntity lesson = new LessonEntity("Ngày 2: Thể nghi vấn To Be", "Câu hỏi Yes/No và This/That", "");
                int id = (int) db.lessonDao().insertLesson(lesson);

                String type = "Noun";
                insertVocab(db, id, "uncle", "chú, bác", type);
                insertVocab(db, id, "aunt", "dì, cô", type);
                insertVocab(db, id, "parent", "bố/mẹ", type);
                insertVocab(db, id, "children", "con cái", type);
                insertVocab(db, id, "room", "phòng", type);
                insertVocab(db, id, "kitchen", "bếp", type);
                insertVocab(db, id, "daughter", "con gái", type);
                insertVocab(db, id, "son", "con trai", type);
                insertVocab(db, id, "picture", "bức tranh", type);
                insertVocab(db, id, "box", "cái hộp", type);
                insertVocab(db, id, "doctor", "bác sĩ", type);
                insertVocab(db, id, "lawyer", "luật sư", type);
                insertVocab(db, id, "firefighter", "lính cứu hỏa", type);
                insertVocab(db, id, "friend", "người bạn", type);

                type = "Adjective";
                insertVocab(db, id, "lovely", "đáng yêu", type);
                insertVocab(db, id, "late", "muộn", type);
                insertVocab(db, id, "busy", "bận rộn", type);
                insertVocab(db, id, "kind", "tốt bụng", type);
                insertVocab(db, id, "new", "mới", type);
                insertVocab(db, id, "old", "cũ", type);

                insertQuiz(db, id, "These firefighters _______ kind.", "are", "is", "am", "be", "are");
                insertQuiz(db, id, "Is this your room? - No, _______.", "he is", "there is", "it isn't", "is not",
                                "it isn't");
                insertQuiz(db, id, "Here _______ my lovely daughters.", "is", "am", "are", "be", "are");
                insertQuiz(db, id, "_______ she a busy lawyer?", "Am", "Is", "Are", "Be", "Is");
                insertQuiz(db, id, "Those are my old _______.", "a friend", "friend", "friends", "the friend",
                                "friends");
                insertQuiz(db, id, "_______ there children in the kitchen?", "Is", "Are", "Am", "Be", "Are");
                insertQuiz(db, id, "Is this man your uncle? - Yes, _______.", "he is", "she is", "I am", "they are",
                                "he is");
                insertQuiz(db, id, "There are new _______ in the box.", "books", "book", "a book", "the book", "books");
        }

        private static void seedDay3(AppDatabase db) {
                LessonEntity lesson = new LessonEntity("Ngày 3: Câu hỏi Who, What", "Câu hỏi với Who, What + To Be",
                                "");
                int id = (int) db.lessonDao().insertLesson(lesson);

                String type = "Noun";
                insertVocab(db, id, "grandfather", "ông", type);
                insertVocab(db, id, "grandmother", "bà", type);
                insertVocab(db, id, "cousin", "anh/chị em họ", type);
                insertVocab(db, id, "classmate", "bạn cùng lớp", type);
                insertVocab(db, id, "banana", "quả chuối", type);
                insertVocab(db, id, "cake", "bánh", type);
                insertVocab(db, id, "bag", "cái túi/cặp", type);
                insertVocab(db, id, "desk", "cái bàn", type);
                insertVocab(db, id, "chair", "cái ghế", type);
                insertVocab(db, id, "shirt", "áo sơ mi", type);
                insertVocab(db, id, "hat", "cái mũ", type);
                insertVocab(db, id, "jeans", "quần bò", type);
                insertVocab(db, id, "pillow", "cái gối", type);
                insertVocab(db, id, "sock", "cái tất", type);

                insertQuiz(db, id, "_______ is this? – It’s a desk.", "What", "Who", "Where", "When", "What");
                insertQuiz(db, id, "_______ are these? – They are shirts.", "What", "Who", "Where", "When", "What");
                insertQuiz(db, id, "_______ are these? – They are my children.", "What", "Who", "Where", "When", "Who");
                insertQuiz(db, id, "What are _______? – They are her dogs.", "those", "it", "you", "that", "those");
                insertQuiz(db, id, "Who are they? – They _______ our classmates.", "is", "am", "are", "be", "are");
                insertQuiz(db, id, "What is this? – _______ a chair.", "They're", "It's", "I'm", "He's", "It's");
                insertQuiz(db, id, "Who _______ that? – It’s his grandmother.", "is", "are", "am", "be", "is");
        }

        private static void seedDay4(AppDatabase db) {
                LessonEntity lesson = new LessonEntity("Ngày 4: Câu hỏi Where, When",
                                "Giới từ chỉ thời gian và nơi chốn", "");
                int id = (int) db.lessonDao().insertLesson(lesson);

                String type = "Noun";
                insertVocab(db, id, "park", "công viên", type);
                insertVocab(db, id, "garden", "vườn", type);
                insertVocab(db, id, "wardrobe", "tủ quần áo", type);
                insertVocab(db, id, "shopping centre", "trung tâm mua sắm", type);
                insertVocab(db, id, "table", "cái bàn", type);
                insertVocab(db, id, "wall", "tường", type);
                insertVocab(db, id, "floor", "sàn nhà", type);
                insertVocab(db, id, "sofa", "ghế sô pha", type);
                insertVocab(db, id, "school", "trường học", type);
                insertVocab(db, id, "work", "nơi làm việc", type);
                insertVocab(db, id, "home", "nhà", type);
                insertVocab(db, id, "supermarket", "siêu thị", type);
                insertVocab(db, id, "party", "bữa tiệc", type);
                insertVocab(db, id, "airport", "sân bay", type);
                insertVocab(db, id, "train station", "nhà ga tàu", type);
                insertVocab(db, id, "clock", "đồng hồ", type);
                insertVocab(db, id, "class", "lớp học", type);
                insertVocab(db, id, "exam", "kỳ thi", type);
                insertVocab(db, id, "birthday", "ngày sinh nhật", type);

                type = "Time";
                insertVocab(db, id, "morning", "buổi sáng", type);
                insertVocab(db, id, "afternoon", "buổi chiều", type);
                insertVocab(db, id, "evening", "buổi tối", type);
                insertVocab(db, id, "lunchtime", "giờ ăn trưa", type);
                insertVocab(db, id, "night", "ban đêm", type);

                insertQuiz(db, id, "_______ is your exam? – It’s on Monday.", "When", "Where", "Who", "What", "When");
                insertQuiz(db, id, "_______ is my clock? – It’s on the wall.", "When", "Where", "Who", "What", "Where");
                insertQuiz(db, id, "_______ are the kids? – They are in the park.", "When", "Where", "Who", "What",
                                "Where");
                insertQuiz(db, id, "We are _______ the supermarket.", "in", "on", "at", "under", "at");
                insertQuiz(db, id, "The jeans are _______ the wardrobe.", "in", "on", "at", "under", "in");
                insertQuiz(db, id, "His birthday is _______ Monday.", "in", "on", "at", "under", "on");
                insertQuiz(db, id, "The Math class is _______ the morning.", "in", "on", "at", "under", "in");
                insertQuiz(db, id, "The oranges are _______ the floor.", "in", "on", "at", "under", "on");
        }

        private static void seedDay5(AppDatabase db) {
                LessonEntity lesson = new LessonEntity("Ngày 5: Động từ thường", "Thể khẳng định ở hiện tại đơn", "");
                int id = (int) db.lessonDao().insertLesson(lesson);

                String type = "Verb";
                insertVocab(db, id, "play", "chơi", type);
                insertVocab(db, id, "watch", "xem", type);
                insertVocab(db, id, "read", "đọc", type);
                insertVocab(db, id, "write", "viết", type);
                insertVocab(db, id, "listen", "nghe", type);
                insertVocab(db, id, "speak", "nói", type);
                insertVocab(db, id, "live", "sống", type);
                insertVocab(db, id, "like", "thích", type);
                insertVocab(db, id, "enjoy", "thích, tận hưởng", type);
                insertVocab(db, id, "sing", "hát", type);
                insertVocab(db, id, "dance", "nhảy", type);
                insertVocab(db, id, "walk", "đi bộ", type);
                insertVocab(db, id, "learn", "học", type);
                insertVocab(db, id, "visit", "ghé thăm", type);
                insertVocab(db, id, "wash", "rửa", type);
                insertVocab(db, id, "study", "học", type);
                insertVocab(db, id, "have", "có", type);
                insertVocab(db, id, "do", "làm", type);
                insertVocab(db, id, "eat", "ăn", type);
                insertVocab(db, id, "go", "đi", type);
                insertVocab(db, id, "help", "giúp đỡ", type);
                insertVocab(db, id, "drink", "uống", type);

                type = "Noun";
                insertVocab(db, id, "chess", "cờ vua", type);
                insertVocab(db, id, "candy", "kẹo", type);
                insertVocab(db, id, "football", "bóng đá", type);
                insertVocab(db, id, "dishes", "bát đĩa", type);
                insertVocab(db, id, "homework", "bài tập về nhà", type);
                insertVocab(db, id, "housework", "công việc nhà", type);
                insertVocab(db, id, "coffee", "cà phê", type);

                insertQuiz(db, id, "They _____ to school by bus.", "goes", "go", "going", "gone", "go");
                insertQuiz(db, id, "Trang ______ playing the guitar.", "likes", "like", "liking", "liked", "likes");
                insertQuiz(db, id, "Her cousins _____ in Da Nang.", "live", "lives", "living", "lived", "live");
                insertQuiz(db, id, "He ______ two brothers.", "has", "have", "having", "had", "has");
                insertQuiz(db, id, "She _______ letters to her friends.", "write", "writes", "writing", "written",
                                "writes");
                insertQuiz(db, id, "My parents _______ TV at night.", "watch", "watches", "watching", "watched",
                                "watch");
                insertQuiz(db, id, "She _______ the dishes after dinner.", "wash", "washes", "washing", "washed",
                                "washes");
                insertQuiz(db, id, "Their students _______ chess every day.", "play", "plays", "playing", "played",
                                "play");
        }

        private static void seedDay6(AppDatabase db) {
                LessonEntity lesson = new LessonEntity("Ngày 6: Phủ định động từ thường", "Cách dùng don't và doesn't",
                                "");
                int id = (int) db.lessonDao().insertLesson(lesson);

                String type = "Verb";
                insertVocab(db, id, "work", "làm việc", type);
                insertVocab(db, id, "swim", "bơi lội", type);
                insertVocab(db, id, "drive", "lái xe", type);
                insertVocab(db, id, "phone", "gọi điện", type);
                insertVocab(db, id, "get up", "thức dậy", type);
                insertVocab(db, id, "teach", "dạy học", type);
                insertVocab(db, id, "jog", "chạy bộ", type);
                insertVocab(db, id, "buy", "mua", type);
                insertVocab(db, id, "water", "tưới nước", type);

                type = "Noun";
                insertVocab(db, id, "meat", "thịt", type);
                insertVocab(db, id, "plant", "cây trồng", type);
                insertVocab(db, id, "weekend", "cuối tuần", type);
                insertVocab(db, id, "flat", "căn hộ", type);
                insertVocab(db, id, "café", "quán cà phê", type);
                insertVocab(db, id, "free time", "thời gian rảnh", type);
                insertVocab(db, id, "ice cream", "kem", type);
                insertVocab(db, id, "gym", "phòng tập", type);
                insertVocab(db, id, "hospital", "bệnh viện", type);

                insertQuiz(db, id, "Luke _______ live with his parents.", "doesn't", "don't", "is not", "do not",
                                "doesn't");
                insertQuiz(db, id, "I ______ like swimming.", "does not", "do not", "am not", "don't likes", "do not");
                insertQuiz(db, id, "They ______ to school by train.", "doesn't go", "don't go", "isn't go", "aren't go",
                                "don't go");
                insertQuiz(db, id, "My teacher _______ to music in her free time.", "doesn't listen", "don't listen",
                                "listen", "listens", "doesn't listen");
                insertQuiz(db, id, "We _______ at weekends.", "swims", "don't swim", "doesn't swim", "swimming",
                                "don't swim");
                insertQuiz(db, id, "He _______ the plants.", "doesn't waters", "don't water", "doesn't water",
                                "watering", "doesn't water");
                insertQuiz(db, id, "His sister _______ a car.", "doesn't drives", "doesn't drive", "drive", "driving",
                                "doesn't drive");
                insertQuiz(db, id, "My students _______ up at 6.00.", "doesn't get", "don't get", "gets", "getting",
                                "don't get");
        }
}
