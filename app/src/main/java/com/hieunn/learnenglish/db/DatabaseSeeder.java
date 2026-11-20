package com.hieunn.learnenglish.db;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class DatabaseSeeder {

    private static final String PREF_NAME = "db_seeder";
    private static final String KEY_SEEDED = "data_seeded_v9";
    private static final String KEY_ENRICHED = "lesson_enriched_v1";
    private static final String KEY_LESSON2_DEMONSTRATIVES = "lesson2_demonstratives_v1";
    private static final String KEY_MAPPED_PRACTICE = "mapped_practice_v1";
    private static final String KEY_JAPANESE_SEEDED = "japanese_seeded_v2";
    private static final String KEY_KAITE_OBOERU_SEEDED = "kaite_oboeru_seeded_v3";
    private static final String DAY_1_VIDEO_ID = "x4F1npn2g8Q";

    public static void seedIfNeeded(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        AppDatabase db = AppDatabase.getInstance(context);

        ensureLessonMetadata(db);
        ensureExtendedLessonContent(db);

        if (!prefs.getBoolean(KEY_SEEDED, false)) {
            List<LessonEntity> lessons = db.lessonDao().getAllLessons();

            // Xoá Ngày 1 -> 6 cũ để cập nhật dữ liệu mới
            for (LessonEntity l : lessons) {
                if (l.title != null && (l.title.contains("Ngày 1:") || l.title.contains("Ngày 2:") || 
                        l.title.contains("Ngày 3:") || l.title.contains("Ngày 4:") || 
                        l.title.contains("Ngày 5:") || l.title.contains("Ngày 6:"))) {
                    db.vocabDao().deleteByLessonId(l.id);
                    db.grammarQuizDao().deleteByLessonId(l.id);
                    db.vocabQuizDao().deleteByLessonId(l.id);
                    db.lessonDao().deleteLessonById(l.id);
                }
            }
            seedDefaultLessons(db);

            ensureLessonMetadata(db);
            ensureExtendedLessonContent(db);
            prefs.edit().putBoolean(KEY_SEEDED, true).apply();
        }

        if (!prefs.getBoolean(KEY_ENRICHED, false)) {
            seedEnrichedContent(db);
            prefs.edit().putBoolean(KEY_ENRICHED, true).apply();
        }

        if (!prefs.getBoolean(KEY_LESSON2_DEMONSTRATIVES, false)) {
            seedLesson2DemonstrativePractice(db);
            prefs.edit().putBoolean(KEY_LESSON2_DEMONSTRATIVES, true).apply();
        }

        if (!prefs.getBoolean(KEY_MAPPED_PRACTICE, false)) {
            MappedPracticeData.seed(db);
            prefs.edit().putBoolean(KEY_MAPPED_PRACTICE, true).apply();
        }

        seedJapaneseIfNeeded(context, db, prefs);
        seedKaiteOboeruIfNeeded(context, db, prefs);
    }

    public static void seedJapaneseIfNeeded(Context context, AppDatabase db, SharedPreferences prefs) {
        if (prefs.getBoolean(KEY_JAPANESE_SEEDED, false)) {
            return;
        }

        // Clean up previously seeded Japanese lessons to reload latest standardized items
        List<LessonEntity> allLessons = db.lessonDao().getAllLessons();
        for (LessonEntity l : allLessons) {
            if ("ja".equalsIgnoreCase(l.videoUrl) || (l.title != null && l.title.startsWith("Bài "))) {
                db.vocabDao().deleteByLessonId(l.id);
                db.lessonDao().deleteLesson(l);
            }
        }

        try {
            android.content.res.AssetManager am = context.getAssets();
            java.io.InputStream is = am.open("japanese_lessons.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonStr = new String(buffer, java.nio.charset.StandardCharsets.UTF_8);
            org.json.JSONArray lessonsArray = new org.json.JSONArray(jsonStr);

            db.runInTransaction(() -> {
                for (int i = 0; i < lessonsArray.length(); i++) {
                    try {
                        org.json.JSONObject lObj = lessonsArray.getJSONObject(i);
                        String title = lObj.getString("title");
                        String desc = lObj.getString("description");
                        String lang = lObj.optString("language", "ja");

                        LessonEntity lesson = new LessonEntity(title, desc, lang);
                        long lessonId = db.lessonDao().insertLesson(lesson);

                        org.json.JSONArray vocabs = lObj.getJSONArray("vocabularies");
                        List<VocabEntity> vocabEntities = new java.util.ArrayList<>();
                        for (int j = 0; j < vocabs.length(); j++) {
                            org.json.JSONObject vObj = vocabs.getJSONObject(j);
                            String word = vObj.getString("word");
                            String vietnamese = vObj.getString("vietnamese");
                            String romaji = vObj.optString("romaji", "");
                            String wordType = vObj.optString("wordType", "Từ vựng");

                            vocabEntities.add(new VocabEntity((int) lessonId, word, vietnamese, romaji, wordType));
                        }
                        if (!vocabEntities.isEmpty()) {
                            db.vocabDao().insertAllVocab(vocabEntities);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            prefs.edit().putBoolean(KEY_JAPANESE_SEEDED, true).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void seedKaiteOboeruIfNeeded(Context context, AppDatabase db, SharedPreferences prefs) {
        if (prefs.getBoolean(KEY_KAITE_OBOERU_SEEDED, false)) {
            return;
        }

        try {
            List<LessonEntity> allLessons = db.lessonDao().getAllLessons();
            db.runInTransaction(() -> {
                for (LessonEntity l : allLessons) {
                    if (l.title != null && (l.title.startsWith("Bài ") || "ja".equalsIgnoreCase(l.videoUrl))) {
                        int lNum = extractLessonNumber(l.title);
                        if (lNum > 0 && lNum <= 50) {
                            List<com.hieunn.learnenglish.GrammarQuiz> quizzes =
                                    com.hieunn.learnenglish.KaiteOboeruDataManager.getQuizzesForLesson(context, lNum);
                            if (quizzes != null && !quizzes.isEmpty()) {
                                db.grammarQuizDao().deleteByLessonId(l.id);
                                List<GrammarQuizEntity> entities = new java.util.ArrayList<>();
                                for (com.hieunn.learnenglish.GrammarQuiz q : quizzes) {
                                    List<String> opts = q.getOptions();
                                    String a = opts.size() > 0 ? opts.get(0) : "";
                                    String b = opts.size() > 1 ? opts.get(1) : "";
                                    String c = opts.size() > 2 ? opts.get(2) : "";
                                    String d = opts.size() > 3 ? opts.get(3) : "";
                                    int cIdx = q.getCorrectIndex();
                                    String correct = (cIdx >= 0 && cIdx < opts.size()) ? opts.get(cIdx) : a;
                                    entities.add(new GrammarQuizEntity(l.id, q.getQuestion(), a, b, c, d, correct));
                                }
                                db.grammarQuizDao().insertAllQuiz(entities);
                            }
                        }
                    }
                }
            });

            prefs.edit().putBoolean(KEY_KAITE_OBOERU_SEEDED, true).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int extractLessonNumber(String title) {
        if (title != null) {
            String lower = title.toLowerCase().trim();
            if (lower.startsWith("bài ")) {
                try {
                    String numStr = lower.substring(4).split("[:\\s]")[0].trim();
                    return Integer.parseInt(numStr);
                } catch (Exception ignored) {
                }
            }
        }
        return -1;
    }

    private static void seedDefaultLessons(AppDatabase db) {
        seedDay1(db);
        seedDay2(db);
        seedDay3(db);
        seedDay4(db);
        seedDay5(db);
        seedDay6(db);
    }

    private static void ensureLessonMetadata(AppDatabase db) {
        upsertLessonMetadata(db, "Ngày 1", "Ngày 1: Động từ To Be",
                "Thể khẳng định và phủ định", DAY_1_VIDEO_ID);
        upsertLessonMetadata(db, "Ngày 7", "Ngày 7: Thể nghi vấn của động từ thường",
                "Ở hiện tại đơn", "");
        upsertLessonMetadata(db, "Ngày 8", "Ngày 8: Thì hiện tại đơn",
                "Tổng hợp cấu trúc và cách dùng", "");
        upsertLessonMetadata(db, "Ngày 9", "Ngày 9: Từ loại",
                "Nhận biết và sử dụng các từ loại cơ bản", "");
        upsertLessonMetadata(db, "Ngày 10", "Ngày 10: Thì hiện tại tiếp diễn",
                "Cấu trúc, cách dùng và dấu hiệu nhận biết", "");
        upsertLessonMetadata(db, "Ngày 11", "Ngày 11: Phân biệt thì hiện tại đơn và hiện tại tiếp diễn",
                "So sánh hai thì để tránh nhầm lẫn", "");
        upsertLessonMetadata(db, "Ngày 12", "Ngày 12: Thì quá khứ đơn",
                "Thể khẳng định", "");
        upsertLessonMetadata(db, "Ngày 13", "Ngày 13: Thì quá khứ đơn",
                "Thể phủ định và nghi vấn", "");
        upsertLessonMetadata(db, "Ngày 14", "Ngày 14: Thì quá khứ tiếp diễn",
                "Cấu trúc và cách dùng", "");
        upsertLessonMetadata(db, "Ngày 15", "Ngày 15: Thì hiện tại hoàn thành",
                "Cấu trúc, cách dùng và dấu hiệu nhận biết", "");
        upsertLessonMetadata(db, "Ngày 16", "Ngày 16: Thì tương lai đơn",
                "Cấu trúc, cách dùng và dấu hiệu nhận biết", "");
        LessonEntity day17 = upsertLessonMetadata(db, "Ngày 17", "Ngày 17: Thì tương lai hoàn thành",
                "Diễn tả hành động hoàn tất trước một mốc trong tương lai", "");
        if (day17.title != null && day17.title.contains("Câu điều kiện")) {
            day17.title = "Ngày 17: Thì tương lai hoàn thành";
            day17.description = "Diễn tả hành động hoàn tất trước một mốc trong tương lai";
            db.lessonDao().updateLesson(day17);
        }
    }

    private static void ensureExtendedLessonContent(AppDatabase db) {
        ensureLessonContent(db, "Ngày 7", "Ngày 7: Thể nghi vấn của động từ thường", "Ở hiện tại đơn", 7);
        ensureLessonContent(db, "Ngày 8", "Ngày 8: Thì hiện tại đơn", "Tổng hợp cấu trúc và cách dùng", 8);
        ensureLessonContent(db, "Ngày 9", "Ngày 9: Từ loại", "Nhận biết và sử dụng các từ loại cơ bản", 9);
        ensureLessonContent(db, "Ngày 10", "Ngày 10: Thì hiện tại tiếp diễn",
                "Cấu trúc, cách dùng và dấu hiệu nhận biết", 10);
        ensureLessonContent(db, "Ngày 11", "Ngày 11: Phân biệt thì hiện tại đơn và hiện tại tiếp diễn",
                "So sánh hai thì để tránh nhầm lẫn", 11);
        ensureLessonContent(db, "Ngày 12", "Ngày 12: Thì quá khứ đơn", "Thể khẳng định", 12);
        ensureLessonContent(db, "Ngày 13", "Ngày 13: Thì quá khứ đơn", "Thể phủ định và nghi vấn", 13);
        ensureLessonContent(db, "Ngày 14", "Ngày 14: Thì quá khứ tiếp diễn", "Cấu trúc và cách dùng", 14);
        ensureLessonContent(db, "Ngày 15", "Ngày 15: Thì hiện tại hoàn thành",
                "Cấu trúc, cách dùng và dấu hiệu nhận biết", 15);
        ensureLessonContent(db, "Ngày 16", "Ngày 16: Thì tương lai đơn",
                "Cấu trúc, cách dùng và dấu hiệu nhận biết", 16);
        ensureLessonContent(db, "Ngày 17", "Ngày 17: Thì tương lai hoàn thành",
                "Diễn tả hành động hoàn tất trước một mốc trong tương lai", 17);
    }

    private static void ensureLessonContent(AppDatabase db, String dayLabel, String title, String description,
            int dayNumber) {
        LessonEntity lesson = upsertLessonMetadata(db, dayLabel, title, description, "");
        int vocabCount = db.vocabDao().getVocabCount(lesson.id);
        int quizCount = db.grammarQuizDao().getQuizCount(lesson.id);
        if (vocabCount >= getExpectedVocabCount(dayNumber) && quizCount >= getExpectedQuizCount(dayNumber)) {
            return;
        }

        db.vocabDao().deleteByLessonId(lesson.id);
        db.grammarQuizDao().deleteByLessonId(lesson.id);

        switch (dayNumber) {
            case 7:
                seedDay7Content(db, lesson.id);
                break;
            case 8:
                seedDay8Content(db, lesson.id);
                break;
            case 9:
                seedDay9Content(db, lesson.id);
                break;
            case 10:
                seedDay10Content(db, lesson.id);
                break;
            case 11:
                seedDay11Content(db, lesson.id);
                break;
            case 12:
                seedDay12Content(db, lesson.id);
                break;
            case 13:
                seedDay13Content(db, lesson.id);
                break;
            case 14:
                seedDay14Content(db, lesson.id);
                break;
            case 15:
                seedDay15Content(db, lesson.id);
                break;
            case 16:
                seedDay16Content(db, lesson.id);
                break;
            case 17:
                seedDay17Content(db, lesson.id);
                break;
            default:
                break;
        }
    }

    private static int getExpectedVocabCount(int dayNumber) {
        switch (dayNumber) {
            case 7:
                return 22;
            case 8:
                return 46;
            case 9:
                return 26;
            case 10:
                return 14;
            case 11:
                return 14;
            case 12:
                return 25;
            case 13:
                return 11;
            case 14:
                return 10;
            case 15:
                return 14;
            case 16:
                return 20;
            case 17:
                return 8;
            default:
                return 0;
        }
    }

    private static int getExpectedQuizCount(int dayNumber) {
        switch (dayNumber) {
            case 1:
                return 25;
            case 2:
                return 16;
            case 6:
                return 23;
            case 7:
                return 15;
            case 8:
                return 20;
            case 10:
            case 11:
                return 20;
            case 12:
                return 20;
            case 13:
                return 33;
            case 9:
                return 24;
            case 14:
                return 20;
            case 15:
                return 25;
            case 16:
                return 39;
            case 17:
                return 34;
            default:
                return 0;
        }
    }

    private static LessonEntity upsertLessonMetadata(AppDatabase db, String dayLabel, String title, String description,
            String videoUrl) {
        LessonEntity existingLesson = findLessonByDayLabel(db, dayLabel);
        if (existingLesson == null) {
            long lessonId = db.lessonDao().insertLesson(new LessonEntity(title, description, videoUrl));
            return db.lessonDao().getLessonById((int) lessonId);
        }

        boolean shouldUpdate = false;
        if ((existingLesson.title == null || existingLesson.title.trim().isEmpty())
                && title != null && !title.trim().isEmpty()) {
            existingLesson.title = title;
            shouldUpdate = true;
        }
        if ((existingLesson.description == null || existingLesson.description.trim().isEmpty())
                && description != null && !description.trim().isEmpty()) {
            existingLesson.description = description;
            shouldUpdate = true;
        }
        if ((existingLesson.videoUrl == null || existingLesson.videoUrl.trim().isEmpty())
                && videoUrl != null && !videoUrl.trim().isEmpty()) {
            existingLesson.videoUrl = videoUrl;
            shouldUpdate = true;
        }

        if (shouldUpdate) {
            db.lessonDao().updateLesson(existingLesson);
        }
        return existingLesson;
    }

    private static LessonEntity findLessonByDayLabel(AppDatabase db, String dayLabel) {
        for (LessonEntity lesson : db.lessonDao().getAllLessons()) {
            if (lesson.title != null && lesson.title.contains(dayLabel)) {
                return lesson;
            }
        }
        return null;
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
                DAY_1_VIDEO_ID);
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
        insertQuiz(db, id, "Dịch: mẹ của tôi (my mother)", "my mother", "her mother", "his mother",
                "your mother", "my mother");

        // Nối các ô để chỉ ra mạo từ 'a/an' phù hợp
        insertQuiz(db, id, "Mạo từ phù hợp với 'baby'", "a", "an", "", "", "a");
        insertQuiz(db, id, "Mạo từ phù hợp với 'orange'", "a", "an", "", "", "an");
        insertQuiz(db, id, "Mạo từ phù hợp với 'woman'", "a", "an", "", "", "a");
        insertQuiz(db, id, "Mạo từ phù hợp với 'car'", "a", "an", "", "", "a");
        insertQuiz(db, id, "Mạo từ phù hợp với 'apple'", "a", "an", "", "", "an");

        // Điền dạng phù hợp của động từ 'to be' ('am/ is/ are')
        insertQuiz(db, id, "We _______ happy.", "am", "is", "are", "", "are");
        insertQuiz(db, id, "It _______ my book.", "am", "is", "are", "", "is");
        insertQuiz(db, id, "They _______ her dogs.", "am", "is", "are", "", "are");
        // Câu I ___ a student đã có ở trên
        insertQuiz(db, id, "He _______ her brother.", "am", "is", "are", "", "is");

        // Viết lại câu sử dụng dạng viết tắt của động từ 'to be'
        insertQuiz(db, id, "Dạng viết tắt của: 'It is a big book.'", "It's a big book.", "Its a big book.", "", "", "It's a big book.");
        insertQuiz(db, id, "Dạng viết tắt của: 'We are not teachers.'", "We aren't teachers.", "We isn't teachers.", "We're not teacher.", "", "We aren't teachers.");
        insertQuiz(db, id, "Dạng viết tắt của: 'They are small apples.'", "They're small apples.", "There small apples.", "", "", "They're small apples.");
        insertQuiz(db, id, "Dạng viết tắt của: 'He is short.'", "He's short.", "Hes short.", "", "", "He's short.");
        insertQuiz(db, id, "Dạng viết tắt của: 'She is in the car.'", "She's in the car.", "Shes in the car.", "", "", "She's in the car.");

        // Chọn đáp án phù hợp
        insertQuiz(db, id, "She _______ short; she is tall.", "are", "am", "isn't", "", "isn't");
        insertQuiz(db, id, "I _______ a teacher. I am a student.", "is not", "am not", "aren't", "", "am not");
        insertQuiz(db, id, "My brother is happy. He _______ sad.", "isn't", "are", "am not", "", "isn't");
        insertQuiz(db, id, "They are not her books; they _______ my books.", "is", "are", "am", "", "are");
        insertQuiz(db, id, "It _______ a big car. It's a small car.", "aren't", "am not", "is not", "", "is not");
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

        // Danh từ số ít / số nhiều dựa vào hình ảnh
        insertQuiz(db, id, "Danh từ số nhiều của 'man' (nhiều người đàn ông)", "men", "mans", "mens", "", "men");
        insertQuiz(db, id, "Danh từ số hiệu của 'apple' (nhiều quả táo)", "apples", "apple", "applies", "", "apples");
        insertQuiz(db, id, "Danh từ số nhiều của 'box' (nhiều chiếc hộp)", "boxes", "boxs", "box", "", "boxes");
        insertQuiz(db, id, "Danh từ số nhiều của 'picture' (nhiều bức tranh)", "pictures", "picture", "picturees", "", "pictures");

        // Điền 'This/ That/ These/ Those' và dạng phù hợp của 'to be'.
        insertQuiz(db, id, "Điền từ: _______ my father. (chỉ gần, 1 người)", "This is", "That is", "These are", "Those are", "This is");
        insertQuiz(db, id, "Điền từ: _______ my books. (chỉ xa, nhiều vật)", "This is", "That is", "These are", "Those are", "Those are");
        insertQuiz(db, id, "Điền từ: _______ my friend. (chỉ xa, 1 người)", "This is", "That is", "These are", "Those are", "That is");
        insertQuiz(db, id, "Điền từ: _______ my students. (chỉ gần, nhiều người)", "This is", "That is", "These are", "Those are", "These are");

        // Viết câu trả lời phù hợp (Yes/No)
        insertQuiz(db, id, "Are they oranges? (Hình ảnh: có quả cam)", "Yes, they are.", "No, they aren't.", "", "", "Yes, they are.");
        insertQuiz(db, id, "Are they babies? (Hình ảnh: sinh viên/học sinh lớn)", "Yes, they are.", "No, they aren't.", "", "", "No, they aren't.");
        insertQuiz(db, id, "Is this a cat? (Hình ảnh: con chó)", "Yes, it is.", "No, it isn't.", "", "", "No, it isn't.");
        insertQuiz(db, id, "Is he a doctor? (Hình ảnh: bác sĩ)", "Yes, he is.", "No, he isn't.", "", "", "Yes, he is.");

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
        LessonEntity lesson = new LessonEntity("Ngày 3: Câu hỏi Who, What", "Câu hỏi với Who, What + To Be", "");
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
        LessonEntity lesson = new LessonEntity("Ngày 6: Phủ định động từ thường", "Cách dùng don't và doesn't", "");
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

        // Nối các chủ ngữ với trợ động từ phủ định
        insertQuiz(db, id, "Trợ động từ phủ định phù hợp với 'Our teacher'", "don't", "doesn't", "", "", "doesn't");
        insertQuiz(db, id, "Trợ động từ phủ định phù hợp với 'We'", "don't", "doesn't", "", "", "don't");
        insertQuiz(db, id, "Trợ động từ phủ định phù hợp với 'Her father'", "don't", "doesn't", "", "", "doesn't");
        insertQuiz(db, id, "Trợ động từ phủ định phù hợp với 'My cat'", "don't", "doesn't", "", "", "doesn't");
        insertQuiz(db, id, "Trợ động từ phủ định phù hợp với 'You'", "don't", "doesn't", "", "", "don't");

        // Chọn đáp án đúng (các câu còn thiếu ở bài chọn đáp án)
        insertQuiz(db, id, "Their parents _______ in the afternoon.", "doesn't jog", "jogs", "don't jog", "", "don't jog");
        insertQuiz(db, id, "Freddy _______ English.", "doesn't teach", "don't teaches", "teach", "", "doesn't teach");
        insertQuiz(db, id, "My brother _______ at the hospital.", "doesn't works", "don't work", "doesn't work", "", "doesn't work");
        insertQuiz(db, id, "His son _______ books in the shopping centre.", "doesn't buy", "don't buy", "buy", "", "doesn't buy");
        insertQuiz(db, id, "They _______ their food.", "shares", "don't share", "doesn't shares", "", "don't share");

        // Chuyển sang thể phủ định
        insertQuiz(db, id, "Phủ định của: 'My parents phone me in the evening.'", "My parents don't phone me in the evening.", "My parents doesn't phone me in the evening.", "", "", "My parents don't phone me in the evening.");
        insertQuiz(db, id, "Phủ định của: 'We travel to the university by bus.'", "We doesn't travel to the university by bus.", "We don't travel to the university by bus.", "", "", "We don't travel to the university by bus.");
        insertQuiz(db, id, "Phủ định của: 'I visit my grandparents every day.'", "I don't visit my grandparents every day.", "I doesn't visit my grandparents every day.", "", "", "I don't visit my grandparents every day.");
        insertQuiz(db, id, "Phủ định của: 'Jimmy has a small cat.'", "Jimmy don't have a small cat.", "Jimmy doesn't have a small cat.", "", "", "Jimmy doesn't have a small cat.");
        insertQuiz(db, id, "Phủ định của: 'He dances in his room in his free time.'", "He don't dance in his room in his free time.", "He doesn't dance in his room in his free time.", "", "", "He doesn't dance in his room in his free time.");
    }

    private static void seedDay7Content(AppDatabase db, int id) {
        insertVocab(db, id, "rain", "mưa", "Word");
        insertVocab(db, id, "snow", "rơi tuyết", "Word");
        insertVocab(db, id, "wear", "mặc, đội", "Word");
        insertVocab(db, id, "finish", "hoàn thành", "Word");
        insertVocab(db, id, "sleep", "ngủ", "Word");
        insertVocab(db, id, "understand", "hiểu", "Word");
        insertVocab(db, id, "rent", "thuê", "Word");
        insertVocab(db, id, "clean", "lau dọn", "Word");
        insertVocab(db, id, "feed", "cho ăn", "Word");
        insertVocab(db, id, "want", "muốn", "Word");
        insertVocab(db, id, "bank", "ngân hàng", "Word");
        insertVocab(db, id, "fruit", "quả", "Word");
        insertVocab(db, id, "vegetable", "rau củ", "Word");
        insertVocab(db, id, "tea", "trà", "Word");
        insertVocab(db, id, "cinema", "rạp chiếu phim", "Word");
        insertVocab(db, id, "question", "câu hỏi", "Word");
        insertVocab(db, id, "pie", "bánh", "Word");
        insertVocab(db, id, "toy", "đồ chơi", "Word");
        insertVocab(db, id, "violin", "vi-ô-lông", "Word");
        insertVocab(db, id, "window", "cửa sổ", "Word");
        insertVocab(db, id, "summer", "mùa hè", "Word");
        insertVocab(db, id, "winter", "mùa đông", "Word");

        insertQuiz(db, id, "Does Anna _______ the violin?", "plays", "play", "", "", "play");
        insertQuiz(db, id, "_______ they visit the cinema on weekends?", "Do", "Does", "", "", "Do");
        insertQuiz(db, id, "Do you _______ to the gym in the afternoon?", "go", "goes", "", "", "go");
        insertQuiz(db, id, "Do they _______ at university?", "studies", "study", "", "", "study");
        insertQuiz(db, id, "_______ he feed the cat in the evening?", "Does", "Do", "", "", "Does");
        insertQuiz(db, id, "Does it rain in the winter? - Yes, it _______.", "do", "does", "", "", "does");
        insertQuiz(db, id, "Do your parents rent a flat? - No, they _______.", "don't", "doesn't", "", "", "don't");
        insertQuiz(db, id, "Does your daughter want the pie? - Yes, she _______.", "does", "doesn't", "", "", "does");
        insertQuiz(db, id, "Do your grandparents _______ at 10.00? - No, they don't.", "sleeps", "sleep", "", "", "sleep");
        insertQuiz(db, id, "_______ he finish his homework at night? - Yes, he _______.", "Does - does", "Do - do", "", "", "Does - does");

        // Chuyển các câu ở thể khẳng định sau sang thể nghi vấn
        insertQuiz(db, id, "Chuyển sang nghi vấn: 'His mother drinks tea in the morning.'",
                "Does his mother drink tea in the morning?",
                "Do his mother drinks tea in the morning?",
                "Is his mother drinks tea in the morning?",
                "Does his mother drinks tea in the morning?",
                "Does his mother drink tea in the morning?");
        insertQuiz(db, id, "Chuyển sang nghi vấn: 'Harry eats fruits every evening.'",
                "Does Harry eat fruits every evening?",
                "Do Harry eats fruits every evening?",
                "Does Harry eats fruits every evening?",
                "Is Harry eat fruits every evening?",
                "Does Harry eat fruits every evening?");
        insertQuiz(db, id, "Chuyển sang nghi vấn: 'Joey teaches him English.'",
                "Does Joey teach him English?",
                "Do Joey teaches him English?",
                "Does Joey teaches him English?",
                "Is Joey teach him English?",
                "Does Joey teach him English?");
        insertQuiz(db, id, "Chuyển sang nghi vấn: 'Her sisters work at a bank.'",
                "Do her sisters work at a bank?",
                "Does her sisters work at a bank?",
                "Do her sisters works at a bank?",
                "Are her sisters work at a bank?",
                "Do her sisters work at a bank?");
        insertQuiz(db, id, "Chuyển sang nghi vấn: 'Their father buys them new toys.'",
                "Does their father buy them new toys?",
                "Do their father buys them new toys?",
                "Does their father buys them new toys?",
                "Is their father buy them new toys?",
                "Does their father buy them new toys?");
    }

    private static void seedDay8Content(AppDatabase db, int id) {
        insertVocab(db, id, "rise", "mọc", "Word");
        insertVocab(db, id, "set", "lặn", "Word");
        insertVocab(db, id, "leave", "rời", "Word");
        insertVocab(db, id, "start", "bắt đầu", "Word");
        insertVocab(db, id, "boil", "sôi", "Word");
        insertVocab(db, id, "see", "ghé thăm", "Word");
        insertVocab(db, id, "hate", "ghét", "Word");
        insertVocab(db, id, "have", "ăn sáng/trưa/tối", "Word");
        insertVocab(db, id, "tidy", "dọn dẹp", "Word");
        insertVocab(db, id, "meet", "gặp gỡ", "Word");
        insertVocab(db, id, "cycle", "đạp xe", "Word");
        insertVocab(db, id, "run", "chạy", "Word");
        insertVocab(db, id, "turn", "biến thành", "Word");
        insertVocab(db, id, "cry", "khóc", "Word");
        insertVocab(db, id, "Sun", "mặt trời", "Word");
        insertVocab(db, id, "world", "thế giới", "Word");
        insertVocab(db, id, "East", "phía Đông", "Word");
        insertVocab(db, id, "West", "phía Tây", "Word");
        insertVocab(db, id, "spring", "mùa xuân", "Word");
        insertVocab(db, id, "autumn/fall", "mùa thu", "Word");
        insertVocab(db, id, "people", "mọi người", "Word");
        insertVocab(db, id, "park", "công viên", "Word");
        insertVocab(db, id, "student", "học sinh, sinh viên", "Word");
        insertVocab(db, id, "water", "nước", "Word");
        insertVocab(db, id, "brother-in-law", "anh/em rể", "Word");
        insertVocab(db, id, "sister-in-law", "chị/em dâu", "Word");
        insertVocab(db, id, "breakfast", "bữa sáng", "Word");
        insertVocab(db, id, "dinner", "bữa tối", "Word");
        insertVocab(db, id, "bedroom", "phòng ngủ", "Word");
        insertVocab(db, id, "cartoon", "hoạt hình", "Word");
        insertVocab(db, id, "novel", "tiểu thuyết", "Word");
        insertVocab(db, id, "tree", "cây cối", "Word");
        insertVocab(db, id, "hot", "nóng", "Word");
        insertVocab(db, id, "active", "năng động", "Word");
        insertVocab(db, id, "cute", "đáng yêu", "Word");
        insertVocab(db, id, "clean", "sạch sẽ", "Word");
        insertVocab(db, id, "tidy", "gọn gàng", "Word");
        insertVocab(db, id, "neat", "ngăn nắp", "Word");
        insertVocab(db, id, "yellow", "màu vàng", "Word");
        insertVocab(db, id, "careful", "cẩn thận", "Word");
        insertVocab(db, id, "always", "luôn luôn", "Word");
        insertVocab(db, id, "usually", "thường thường", "Word");
        insertVocab(db, id, "often", "thường", "Word");
        insertVocab(db, id, "sometimes", "thỉnh thoảng", "Word");
        insertVocab(db, id, "hardly", "hiếm khi", "Word");
        insertVocab(db, id, "never", "không bao giờ", "Word");

        insertQuiz(db, id, "_______ your bedroom always neat?", "Is", "Are", "Am", "", "Is");
        insertQuiz(db, id, "We _______ vegetables and fruits.", "hates", "doesn't hate", "don't hate", "", "don't hate");
        insertQuiz(db, id, "The sun _______ in the West.", "set", "don't set", "sets", "", "sets");
        insertQuiz(db, id, "He _______ the trees.", "water hardly", "hardly waters", "waters hardly", "", "hardly waters");
        insertQuiz(db, id, "Her baby _______ every night.", "crys", "cries", "cry", "", "cries");
        insertQuiz(db, id, "_______ people late?", "Is", "Does", "Are", "", "Are");
        insertQuiz(db, id, "Their children _______ very lovely.", "is", "are", "am", "", "are");
        insertQuiz(db, id, "It _______ in the summer.", "never snows", "snows never", "never snow", "", "never snows");
        insertQuiz(db, id, "_______ your father work at the bank?", "Does", "Are", "Do", "", "Does");
        insertQuiz(db, id, "My sister _______ a novel every night.", "reads always", "always reads", "always read", "", "always reads");

        // Cho dạng đúng của động từ trong ngoặc vào chỗ trống
        insertQuiz(db, id, "Janna _______ (run) in the park every morning.",
                "runs", "run", "running", "is run", "runs");
        insertQuiz(db, id, "They _______ (be/not) usually late.",
                "aren't", "isn't", "don't be", "doesn't be", "aren't");
        insertQuiz(db, id, "_______ he _______ (eat) dinner at 7 p.m. every day?",
                "Does - eat", "Do - eats", "Does - eats", "Is - eating", "Does - eat");
        insertQuiz(db, id, "They _______ (not/cycle) to school.",
                "don't cycle", "doesn't cycle", "aren't cycle", "not cycle", "don't cycle");
        insertQuiz(db, id, "She (be) _______ always careful.",
                "is", "are", "am", "be", "is");
        insertQuiz(db, id, "(Be) _______ your father always busy?",
                "Is", "Are", "Does", "Do", "Is");
        insertQuiz(db, id, "My brother never (tidy) _______ his room.",
                "tidies", "tidy", "tidys", "is tidying", "tidies");
        insertQuiz(db, id, "Do you often (have) _______ breakfast at 7 a.m.?",
                "have", "has", "having", "had", "have");
        insertQuiz(db, id, "His daughter (do) _______ her homework after dinner.",
                "does", "do", "is doing", "doing", "does");
        insertQuiz(db, id, "Our children always (meet) _______ their friends on weekends.",
                "meet", "meets", "meeting", "is meeting", "meet");
    }

    private static void seedDay9Content(AppDatabase db, int id) {
        insertVocab(db, id, "flower", "hoa", "Word");
        insertVocab(db, id, "girl", "cô gái", "Word");
        insertVocab(db, id, "teacher", "giáo viên", "Word");
        insertVocab(db, id, "actor", "diễn viên", "Word");
        insertVocab(db, id, "moment", "khoảnh khắc", "Word");
        insertVocab(db, id, "boy", "chàng trai", "Word");
        insertVocab(db, id, "happiness", "niềm vui", "Word");
        insertVocab(db, id, "city", "thành phố", "Word");
        insertVocab(db, id, "artist", "nghệ sĩ", "Word");
        insertVocab(db, id, "weather", "thời tiết", "Word");
        insertVocab(db, id, "nice", "tốt, đẹp", "Word");
        insertVocab(db, id, "good", "tốt, khỏe", "Word");
        insertVocab(db, id, "great", "tuyệt vời", "Word");
        insertVocab(db, id, "easy", "dễ dàng", "Word");
        insertVocab(db, id, "beautiful", "đẹp", "Word");
        insertVocab(db, id, "suitable", "phù hợp", "Word");
        insertVocab(db, id, "active", "năng động", "Word");
        insertVocab(db, id, "careless", "bất cẩn", "Word");
        insertVocab(db, id, "quickly", "nhanh chóng", "Word");
        insertVocab(db, id, "carefully", "đầy cẩn thận", "Word");
        insertVocab(db, id, "carelessly", "đầy bất cẩn", "Word");
        insertVocab(db, id, "fast", "nhanh", "Word");
        insertVocab(db, id, "well", "tốt, giỏi", "Word");
        insertVocab(db, id, "hard", "chăm chỉ", "Word");
        insertVocab(db, id, "very", "rất", "Word");
        insertVocab(db, id, "quite", "khá", "Word");

        insertQuiz(db, id, "\"flower\" thuộc từ loại nào?", "Tính từ", "Danh từ", "Trạng từ", "", "Danh từ");
        insertQuiz(db, id, "\"beautiful\" thuộc từ loại nào?", "Tính từ", "Danh từ", "Trạng từ", "", "Tính từ");
        insertQuiz(db, id, "\"quickly\" thuộc từ loại nào?", "Tính từ", "Danh từ", "Trạng từ", "", "Trạng từ");
        insertQuiz(db, id, "\"weather\" thuộc từ loại nào?", "Tính từ", "Danh từ", "Trạng từ", "", "Danh từ");
        insertQuiz(db, id, "\"active\" thuộc từ loại nào?", "Tính từ", "Danh từ", "Trạng từ", "", "Tính từ");
        insertQuiz(db, id, "\"beautiful\" trong 'beautiful pictures' thuộc từ loại nào?", "Tính từ", "Danh từ", "Trạng từ", "", "Tính từ");
        insertQuiz(db, id, "\"easy\" trong 'quite easy' thuộc từ loại nào?", "Tính từ", "Danh từ", "Trạng từ", "", "Tính từ");
        insertQuiz(db, id, "\"carefully\" trong 'does her homework carefully' thuộc từ loại nào?", "Tính từ", "Danh từ", "Trạng từ", "", "Trạng từ");

        // Chọn đáp án chỉ ra cách sắp xếp từ loại phù hợp
        insertQuiz(db, id, "Sắp xếp đúng: 'beautiful' + 'flowers'",
                "flowers beautiful", "beautiful flowers", "", "", "beautiful flowers");
        insertQuiz(db, id, "Sắp xếp đúng: 'very' + 'good'",
                "very good", "good very", "", "", "very good");
        insertQuiz(db, id, "Sắp xếp đúng: 'nice' + 'weather'",
                "nice weather", "weather nice", "", "", "nice weather");
        insertQuiz(db, id, "Sắp xếp đúng: 'fast' + 'go'",
                "fast go", "go fast", "", "", "go fast");
        insertQuiz(db, id, "Sắp xếp đúng: 'easy' + 'quite'",
                "easy quite", "quite easy", "", "", "quite easy");

        // Chọn đáp án chỉ ra từ được gạch chân thuộc từ loại nào
        insertQuiz(db, id, "'big' trong 'My sister lives in a big city.' thuộc từ loại nào?",
                "Tính từ", "Danh từ", "Trạng từ", "", "Tính từ");
        insertQuiz(db, id, "'nice' trong 'The weather is nice in the spring.' thuộc từ loại nào?",
                "Tính từ", "Danh từ", "Trạng từ", "", "Tính từ");
        insertQuiz(db, id, "'dishes' trong 'Her mother washes the dishes every day.' thuộc từ loại nào?",
                "Tính từ", "Danh từ", "Trạng từ", "", "Danh từ");
        insertQuiz(db, id, "'great' trong 'Quang and Hung are great friends.' thuộc từ loại nào?",
                "Tính từ", "Danh từ", "Trạng từ", "", "Tính từ");

        // Chọn đáp án chỉ ra vị trí phù hợp trong câu
        insertQuiz(db, id, "Kien is an (A) student (B) in my class. Đặt 'active' vào vị trí nào?",
                "A", "B", "", "", "A");
        insertQuiz(db, id, "The water is (A) hot (B). Đặt 'very' vào vị trí nào?",
                "A", "B", "", "", "A");
        insertQuiz(db, id, "He doesn't understand this (A) question (B). Đặt 'easy' vào vị trí nào?",
                "A", "B", "", "", "A");
        insertQuiz(db, id, "(A) Jimmy swims (B). Đặt 'quickly' vào vị trí nào?",
                "A", "B", "", "", "B");
        insertQuiz(db, id, "The film is (A) good (B). Đặt 'quite' vào vị trí nào?",
                "A", "B", "", "", "A");
        insertQuiz(db, id, "(A) They drive (B). Đặt 'carelessly' vào vị trí nào?",
                "A", "B", "", "", "B");
        insertQuiz(db, id, "My grandparents have (A) two (B) cats. Đặt 'small' vào vị trí nào?",
                "A", "B", "", "", "B");
    }

    private static void seedDay10Content(AppDatabase db, int id) {
        insertVocab(db, id, "rest", "nghỉ ngơi", "Word");
        insertVocab(db, id, "close", "đóng lại", "Word");
        insertVocab(db, id, "type", "gõ", "Word");
        insertVocab(db, id, "give", "đưa cho", "Word");
        insertVocab(db, id, "talk", "nói chuyện", "Word");
        insertVocab(db, id, "fly", "bay", "Word");
        insertVocab(db, id, "stand", "đứng", "Word");
        insertVocab(db, id, "wait", "đợi", "Word");
        insertVocab(db, id, "gate", "cổng", "Word");
        insertVocab(db, id, "living room", "phòng khách", "Word");
        insertVocab(db, id, "dentist", "nha sĩ", "Word");
        insertVocab(db, id, "letter", "lá thư", "Word");
        insertVocab(db, id, "keyboard", "bàn phím", "Word");
        insertVocab(db, id, "yard", "sân", "Word");

        insertQuiz(db, id, "Look! The sun _______.", "rises", "is rising", "are rising", "", "is rising");
        insertQuiz(db, id, "Luke _______ Maths in his bedroom now.", "study", "don't study", "is studying", "", "is studying");
        insertQuiz(db, id, "His sister _______ the flowers in the garden right now.", "is watering", "water", "don't water", "", "is watering");
        insertQuiz(db, id, "_______ your children _______ cartoons now?", "Do - watch", "Does - watch", "Are - watching", "", "Are - watching");
        insertQuiz(db, id, "Listen! Kate _______ in her room.", "doesn't sing", "sings", "is singing", "", "is singing");
        insertQuiz(db, id, "He _______ in the garden at the moment.", "are standing", "is standing", "stand", "", "is standing");
        insertQuiz(db, id, "Mike _______ his grandparents at the moment.", "visits", "is visiting", "are visiting", "", "is visiting");
        insertQuiz(db, id, "The students _______ to their teacher now.", "is listening", "listens", "are listening", "", "are listening");
        insertQuiz(db, id, "At present, Ly _______ for the bus.", "is waiting", "waits", "are waiting", "", "is waiting");
        insertQuiz(db, id, "She _______ the kitchen now.", "isn't cleaning", "don't clean", "aren't cleaning", "", "isn't cleaning");

        // Chuyển các động từ sau sang dạng V-ing
        insertQuiz(db, id, "Dạng V-ing của 'set' là gì?",
                "setting", "seting", "seting", "settin", "setting");
        insertQuiz(db, id, "Dạng V-ing của 'close' là gì?",
                "closing", "closeing", "closting", "clossing", "closing");
        insertQuiz(db, id, "Dạng V-ing của 'run' là gì?",
                "running", "runing", "runing", "runeing", "running");
        insertQuiz(db, id, "Dạng V-ing của 'work' là gì?",
                "working", "workin", "workking", "workes", "working");
        insertQuiz(db, id, "Dạng V-ing của 'live' là gì?",
                "living", "liveing", "livving", "lifing", "living");

        // Chia dạng thì hiện tại tiếp diễn của các động từ trong ngoặc
        insertQuiz(db, id, "I _______ (rest) in the living room at the moment.",
                "am resting", "is resting", "rests", "rest", "am resting");
        insertQuiz(db, id, "It _______ (not/rain) now.",
                "isn't raining", "aren't raining", "don't rain", "not raining", "isn't raining");
        insertQuiz(db, id, "My mother _______ (phone) my dentist now.",
                "is phoning", "phones", "are phoning", "am phoning", "is phoning");
        insertQuiz(db, id, "She _______ (write) a letter right now.",
                "is writing", "is writeing", "writes", "are writing", "is writing");
        insertQuiz(db, id, "We _______ (talk) in the yard at present.",
                "are talking", "is talking", "talks", "am talking", "are talking");
    }

    private static void seedDay11Content(AppDatabase db, int id) {
        insertVocab(db, id, "attend", "tham dự", "Word");
        insertVocab(db, id, "make", "làm", "Word");
        insertVocab(db, id, "mop", "lau chùi", "Word");
        insertVocab(db, id, "shop", "mua sắm", "Word");
        insertVocab(db, id, "sit", "ngồi", "Word");
        insertVocab(db, id, "build", "xây dựng", "Word");
        insertVocab(db, id, "love", "yêu thích", "Word");
        insertVocab(db, id, "know", "biết", "Word");
        insertVocab(db, id, "think", "nghĩ rằng", "Word");
        insertVocab(db, id, "believe", "tin rằng", "Word");
        insertVocab(db, id, "radio", "đài phát thanh", "Word");
        insertVocab(db, id, "meeting", "cuộc họp", "Word");
        insertVocab(db, id, "answer", "câu trả lời", "Word");
        insertVocab(db, id, "skirt", "váy", "Word");

        insertQuiz(db, id, "They _______ the answer.", "knows", "are knowing", "don't know", "", "don't know");
        insertQuiz(db, id, "He _______ the floor every morning.", "is mopping", "mops", "mop", "", "mops");
        insertQuiz(db, id, "They _______ for their friends at the moment.", "don't wait", "wait", "are waiting", "", "are waiting");
        insertQuiz(db, id, "Look! Our parents _______ in the living room.", "are dancing", "dance", "dances", "", "are dancing");
        insertQuiz(db, id, "My father _______ TV in the living room at present.", "is watching", "watches", "are watching", "", "is watching");
        insertQuiz(db, id, "Listen! She _______ the piano.", "plays", "is playing", "play", "", "is playing");
        insertQuiz(db, id, "We often _______ in the living room after dinner.", "are sitting", "sits", "sit", "", "sit");
        insertQuiz(db, id, "I never _______ a skirt to work.", "wear", "wears", "am wearing", "", "wear");
        insertQuiz(db, id, "The boys _______ in the garden now.", "sit", "are sitting", "is sitting", "", "are sitting");
        insertQuiz(db, id, "I _______ some fruits.", "want", "is wanting", "doesn't want", "", "want");

        // Cho dạng V(s/es) và V-ing của các động từ sau
        insertQuiz(db, id, "Dạng V(s/es) và V-ing của 'fly' là gì?",
                "flies - flying", "flys - flying", "flies - fluing", "flyes - flying", "flies - flying");
        insertQuiz(db, id, "Dạng V(s/es) và V-ing của 'wait' là gì?",
                "waits - waiting", "waites - waiting", "waits - waitting", "wait - waitying", "waits - waiting");
        insertQuiz(db, id, "Dạng V(s/es) và V-ing của 'wash' là gì?",
                "washes - washing", "washs - washing", "washes - washeing", "washs - washhing", "washes - washing");
        insertQuiz(db, id, "Dạng V(s/es) và V-ing của 'learn' là gì?",
                "learns - learning", "learnes - learning", "learns - learnning", "learnes - learneing", "learns - learning");
        insertQuiz(db, id, "Dạng V(s/es) và V-ing của 'mop' là gì?",
                "mops - mopping", "mopes - moping", "mops - moping", "mopps - mopping", "mops - mopping");

        // Cho dạng hiện tại đơn hoặc hiện tại tiếp diễn
        insertQuiz(db, id, "I _______ (attend) two meetings every week.",
                "attend", "am attending", "attends", "is attending", "attend");
        insertQuiz(db, id, "They _______ (listen) to the radio at present.",
                "are listening", "listen", "listens", "is listening", "are listening");
        insertQuiz(db, id, "She _______ (make) a cake in the kitchen now.",
                "is making", "makes", "make", "are making", "is making");
        insertQuiz(db, id, "My son _______ (clean) his bedroom every Saturday.",
                "cleans", "is cleaning", "clean", "are cleaning", "cleans");
        insertQuiz(db, id, "They _______ (not shop) at the moment.",
                "aren't shopping", "don't shop", "doesn't shop", "isn't shopping", "aren't shopping");
    }

    private static void seedDay12Content(AppDatabase db, int id) {
        insertVocab(db, id, "begin", "bắt đầu", "Word");
        insertVocab(db, id, "break", "làm vỡ", "Word");
        insertVocab(db, id, "bring", "mang theo", "Word");
        insertVocab(db, id, "come", "đến", "Word");
        insertVocab(db, id, "cost", "trị giá", "Word");
        insertVocab(db, id, "cut", "cắt", "Word");
        insertVocab(db, id, "draw", "vẽ", "Word");
        insertVocab(db, id, "find", "tìm thấy", "Word");
        insertVocab(db, id, "get", "có được", "Word");
        insertVocab(db, id, "hear", "nghe", "Word");
        insertVocab(db, id, "hold", "tổ chức, cầm, nắm", "Word");
        insertVocab(db, id, "keep", "giữ", "Word");
        insertVocab(db, id, "pay", "trả tiền", "Word");
        insertVocab(db, id, "say", "nói", "Word");
        insertVocab(db, id, "sell", "bán", "Word");
        insertVocab(db, id, "send", "gửi", "Word");
        insertVocab(db, id, "spend", "dành thời gian, tiền bạc", "Word");
        insertVocab(db, id, "take", "cầm, mang", "Word");
        insertVocab(db, id, "tell", "kể, bảo", "Word");
        insertVocab(db, id, "win", "chiến thắng", "Word");
        insertVocab(db, id, "hour", "giờ", "Word");
        insertVocab(db, id, "day", "ngày", "Word");
        insertVocab(db, id, "story", "câu chuyện", "Word");
        insertVocab(db, id, "vase", "cái bình", "Word");
        insertVocab(db, id, "movie", "bộ phim", "Word");

        insertQuiz(db, id, "I _______ to the story last night.", "listened", "listen", "am listening", "", "listened");
        insertQuiz(db, id, "My grandmother _______ a teacher in 2000.", "is", "were", "was", "", "was");
        insertQuiz(db, id, "I _______ a movie last week.", "am watching", "watched", "watch", "", "watched");
        insertQuiz(db, id, "In 2010, we _______ in a small house in London.", "live", "lived", "are living", "", "lived");
        insertQuiz(db, id, "Bob _______ in the living room two hours ago.", "are", "is", "was", "", "was");
        insertQuiz(db, id, "My son _______ the vase yesterday.", "is breaking", "breaks", "broke", "", "broke");
        insertQuiz(db, id, "My parents _______ a new car last year.", "bought", "buy", "are buying", "", "bought");
        insertQuiz(db, id, "The children _______ in the yard yesterday.", "is playing", "play", "played", "", "played");
        insertQuiz(db, id, "Her daughter _______ a beautiful picture last month.", "is drawing", "drew", "draw", "", "drew");
        insertQuiz(db, id, "It _______ last weekend.", "rained", "rains", "rain", "", "rained");

        // Cho dạng quá khứ của các động từ sau
        insertQuiz(db, id, "Dạng quá khứ của 'study' là gì?",
                "studied", "studyed", "studies", "studed", "studied");
        insertQuiz(db, id, "Dạng quá khứ của 'live' là gì?",
                "lived", "liveed", "liven", "lives", "lived");
        insertQuiz(db, id, "Dạng quá khứ của 'cut' là gì?",
                "cut", "cutted", "cuts", "cuted", "cut");
        insertQuiz(db, id, "Dạng quá khứ của 'begin' là gì?",
                "began", "beginned", "begun", "begined", "began");
        insertQuiz(db, id, "Dạng quá khứ của 'mop' là gì?",
                "mopped", "moped", "mops", "moped", "mopped");

        // Chia dạng quá khứ đơn của các động từ trong ngoặc
        insertQuiz(db, id, "They _______ (bring) a book last week.",
                "brought", "bringed", "bring", "brings", "brought");
        insertQuiz(db, id, "She _______ (find) a dog yesterday.",
                "found", "finded", "finds", "find", "found");
        insertQuiz(db, id, "She _______ (visit) her parents last Sunday.",
                "visited", "visitted", "visit", "visits", "visited");
        insertQuiz(db, id, "It _______ (be) a nice day yesterday.",
                "was", "were", "is", "been", "was");
        insertQuiz(db, id, "We _______ (be) late last night.",
                "were", "was", "are", "been", "were");
    }

    private static void seedDay13Content(AppDatabase db, int id) {
        insertVocab(db, id, "bill", "hóa đơn", "Word");
        insertVocab(db, id, "suit", "bộ com lê", "Word");
        insertVocab(db, id, "factory", "nhà máy", "Word");
        insertVocab(db, id, "family", "gia đình", "Word");
        insertVocab(db, id, "shoe", "giày", "Word");
        insertVocab(db, id, "contest", "cuộc thi", "Word");
        insertVocab(db, id, "cold", "lạnh", "Word");
        insertVocab(db, id, "fresh", "tươi, mới", "Word");
        insertVocab(db, id, "close", "gần, gần gũi, thân thiết", "Word");
        insertVocab(db, id, "late", "muộn", "Word");
        insertVocab(db, id, "abroad", "nước ngoài", "Word");

        insertQuiz(db, id, "My parents _______ the old house in 2000.", "didn't sell", "don't sell", "doesn't sell", "", "didn't sell");
        insertQuiz(db, id, "_______ your father work at a factory in 2014?", "Does", "Do", "Did", "", "Did");
        insertQuiz(db, id, "Daniel _______ up late yesterday.", "doesn't get", "don't get", "didn't get", "", "didn't get");
        insertQuiz(db, id, "The vegetables _______ fresh yesterday.", "isn't", "weren't", "wasn't", "", "weren't");
        insertQuiz(db, id, "Did your child _______ a hat to school yesterday?", "wearing", "wore", "wear", "", "wear");
        insertQuiz(db, id, "He _______ me a letter last month.", "don't send", "didn't send", "doesn't send", "", "didn't send");
        insertQuiz(db, id, "_______ it hot yesterday?", "Is", "Was", "Were", "", "Was");
        insertQuiz(db, id, "My sister _______ new shoes last month.", "didn't buy", "don't buy", "doesn't buy", "", "didn't buy");
        insertQuiz(db, id, "Lucy _______ busy last night.", "wasn't", "weren't", "aren't", "", "wasn't");
        insertQuiz(db, id, "_______ the party last week funny?", "Were", "Was", "Are", "", "Was");

        // Quiz 1 - Quá khứ đơn thể phủ định
        insertQuiz(db, id, "Tom _______ at school yesterday.",
                "weren't", "wasn't", "", "", "wasn't");
        insertQuiz(db, id, "It didn't _______ yesterday.",
                "rain", "rained", "", "", "rain");
        insertQuiz(db, id, "They _______ at home last night.",
                "weren't", "wasn't", "", "", "weren't");
        insertQuiz(db, id, "Her children _______ go to school last Friday.",
                "didn't", "don't", "", "", "didn't");

        // Quiz 2 - Quá khứ đơn thể nghi vấn
        insertQuiz(db, id, "_______ Tim busy last Friday?",
                "Was", "Were", "", "", "Was");
        insertQuiz(db, id, "Did they _______ late last night?",
                "sleep", "slept", "", "", "sleep");
        insertQuiz(db, id, "_______ it cold last year? - No, it wasn't.",
                "Were", "Was", "", "", "Was");
        insertQuiz(db, id, "_______ he wear his new suit yesterday? - Yes, he did.",
                "Does", "Did", "", "", "Did");

        // Practice
        insertQuiz(db, id, "James _______ at the party last night.",
                "weren't", "wasn't", "", "", "wasn't");
        insertQuiz(db, id, "_______ your parents in Paris in 2000?",
                "Was", "Were", "", "", "Were");
        insertQuiz(db, id, "My father _______ work at this factory in 2013.",
                "didn't", "doesn't", "", "", "didn't");
        insertQuiz(db, id, "Did your family _______ abroad last summer?",
                "travel", "travelled", "", "", "travel");
        insertQuiz(db, id, "My mother didn't _______ fresh fruits yesterday.",
                "buy", "bought", "", "", "buy");
        insertQuiz(db, id, "_______ it snow last year? - No, it didn't.",
                "Does", "Did", "", "", "Did");
        insertQuiz(db, id, "My son _______ mop the floor yesterday.",
                "didn't", "doesn't", "", "", "didn't");
        insertQuiz(db, id, "_______ your cousin see his doctor last week?",
                "Did", "Does", "", "", "Did");
        insertQuiz(db, id, "_______ your brother-in-law come last night? - No, he didn't.",
                "Did", "Does", "", "", "Did");
        insertQuiz(db, id, "Their daughter didn't _______ them yesterday.",
                "call", "called", "", "", "call");
        insertQuiz(db, id, "I didn't _______ Henry last Sunday.",
                "met", "meet", "", "", "meet");
        insertQuiz(db, id, "Did your son _______ his shoes yesterday? - Yes, he did.",
                "leave", "left", "", "", "leave");
        insertQuiz(db, id, "Did he _______ the bill last night? - Yes, he did.",
                "pay", "paid", "", "", "pay");
        insertQuiz(db, id, "My friend didn't _______ the contest.",
                "won", "win", "", "", "win");
        insertQuiz(db, id, "Did he _______ an ice cream last night? - No, he didn't.",
                "eat", "ate", "", "", "eat");
    }

    private static void seedDay14Content(AppDatabase db, int id) {
        insertVocab(db, id, "chat", "tán gẫu", "Word");
        insertVocab(db, id, "fix", "sửa chữa", "Word");
        insertVocab(db, id, "stop", "dừng lại", "Word");
        insertVocab(db, id, "arrive", "đến", "Word");
        insertVocab(db, id, "change", "thay đổi", "Word");
        insertVocab(db, id, "bicycle", "xe đạp", "Word");
        insertVocab(db, id, "accident", "vụ tai nạn", "Word");
        insertVocab(db, id, "police", "cảnh sát", "Word");
        insertVocab(db, id, "clothes", "quần áo", "Word");
        insertVocab(db, id, "game", "trò chơi", "Word");

        insertQuiz(db, id, "At 5.00 yesterday, we _______ the movie.", "watched", "were watching", "", "", "were watching");
        insertQuiz(db, id, "Yesterday I met her when I _______.", "am walking", "was walking", "", "", "was walking");
        insertQuiz(db, id, "I _______ at home yesterday.", "didn't stay", "wasn't staying", "", "", "wasn't staying");
        insertQuiz(db, id, "We _______ an accident last month.", "had", "were having", "", "", "had");
        insertQuiz(db, id, "They _______ to music when I came last night.", "are listening", "were listening", "", "", "were listening");
        insertQuiz(db, id, "Jina _______ with her family at 10.00 last night.", "is talking", "was talking", "talked", "", "was talking");
        insertQuiz(db, id, "She _______ for the bus at 4.30 yesterday.", "wait", "are waiting", "was waiting", "", "was waiting");
        insertQuiz(db, id, "I _______ a cartoon at 4 p.m. yesterday.", "was watching", "am watching", "watched", "", "was watching");
        insertQuiz(db, id, "My mother _______ the clothes at 8.00 last night.", "aren't washing", "wasn't washing", "don't wash", "", "wasn't washing");
        insertQuiz(db, id, "His friend _______ coffee at 6.30 yesterday.", "am drinking", "drink", "was drinking", "", "was drinking");
        insertQuiz(db, id, "When I came yesterday, she _______ in the kitchen.", "is cooking", "cooks", "was cooking", "", "was cooking");
        insertQuiz(db, id, "The boys _______ volleyball at 6.00 yesterday afternoon.", "aren't playing", "don't play", "weren't playing", "", "weren't playing");
        insertQuiz(db, id, "They _______ breakfast when we arrived yesterday.", "were having", "is having", "are having", "", "were having");
        insertQuiz(db, id, "I _______ to school when I met Tim.", "is going", "go", "was going", "", "was going");
        insertQuiz(db, id, "_____ your children _____ homework at 3.30 yesterday?", "Does - do", "Were - doing", "Did - did", "", "Were - doing");

        // Chia dạng thì quá khứ tiếp diễn cho các động từ trong ngoặc
        insertQuiz(db, id, "I _______ (chat) with my friends at 9.30 last night.",
                "was chatting", "am chatting", "chatted", "were chatting", "was chatting");
        insertQuiz(db, id, "His children _______ (not/play) games when he came home.",
                "weren't playing", "didn't play", "wasn't playing", "aren't playing", "weren't playing");
        insertQuiz(db, id, "My father _______ (fix) my bicycle at 4.30 yesterday.",
                "was fixing", "fixed", "were fixing", "is fixing", "was fixing");
        insertQuiz(db, id, "_______ he _______ (work) at the factory at 5.00 yesterday?",
                "Was - working", "Did - work", "Were - working", "Is - working", "Was - working");
        insertQuiz(db, id, "Their parents _______ (drive) to the supermarket at 3.30 yesterday.",
                "were driving", "was driving", "drove", "are driving", "were driving");
    }

    private static void seedDay15Content(AppDatabase db, int id) {
        insertVocab(db, id, "receive", "nhận được", "Word");
        insertVocab(db, id, "search", "tìm kiếm", "Word");
        insertVocab(db, id, "marry", "kết hôn", "Word");
        insertVocab(db, id, "lose", "mất", "Word");
        insertVocab(db, id, "paint", "sơn", "Word");
        insertVocab(db, id, "smoke", "hút thuốc", "Word");
        insertVocab(db, id, "match", "trận đấu", "Word");
        insertVocab(db, id, "song", "bài hát", "Word");
        insertVocab(db, id, "essay", "bài luận", "Word");
        insertVocab(db, id, "minute", "phút", "Word");
        insertVocab(db, id, "key", "chìa khóa", "Word");
        insertVocab(db, id, "message", "tin nhắn", "Word");
        insertVocab(db, id, "time", "lần", "Word");
        insertVocab(db, id, "watch", "đồng hồ", "Word");

        insertQuiz(db, id, "I _______ lived here for 5 years.", "have", "has", "", "", "have");
        insertQuiz(db, id, "She _______ worked at the factory for 2 months.", "have", "has", "", "", "has");
        insertQuiz(db, id, "We _______ just received a message from her.", "have", "has", "", "", "have");
        insertQuiz(db, id, "He _______ just lost his key.", "have", "has", "", "", "has");
        insertQuiz(db, id, "_______ you ever been to Paris?", "Have", "Has", "", "", "Have");
        insertQuiz(db, id, "She _______ this game for 3 hours.", "has played", "have played", "is playing", "", "has played");
        insertQuiz(db, id, "Have you ever _______ chess?", "play", "playing", "played", "", "played");
        insertQuiz(db, id, "We _______ already watched that movie.", "has", "have", "didn't", "", "have");
        insertQuiz(db, id, "She _______ already _______ her homework.", "has - finished", "have - finished", "doesn't - finish", "", "has - finished");
        insertQuiz(db, id, "We have just _______ breakfast.", "had", "have", "has", "", "had");
        insertQuiz(db, id, "He _______ this watch for 5 years.", "was wearing", "has worn", "wears", "", "has worn");
        insertQuiz(db, id, "_______ your daughter ever drawn a picture?", "Have", "Has", "Did", "", "Has");
        insertQuiz(db, id, "My parents have recently _______ a new house.", "buys", "buy", "bought", "", "bought");
        insertQuiz(db, id, "She _______ just _______ her keys.", "is - finding", "have - found", "has - found", "", "has - found");
        insertQuiz(db, id, "They _______ her since 2010.", "don't see", "didn't seee", "haven't seen", "", "haven't seen");

        // Cho dạng quá khứ phân từ của các động từ sau
        insertQuiz(db, id, "Dạng quá khứ phân từ (V-ed/cột 3) của 'stop' là gì?",
                "stopped", "stoped", "stoping", "stops", "stopped");
        insertQuiz(db, id, "Dạng quá khứ phân từ (V-ed/cột 3) của 'marry' là gì?",
                "married", "marryed", "marring", "marrys", "married");
        insertQuiz(db, id, "Dạng quá khứ phân từ (V-ed/cột 3) của 'be' là gì?",
                "been", "was", "beed", "being", "been");
        insertQuiz(db, id, "Dạng quá khứ phân từ (V-ed/cột 3) của 'eat' là gì?",
                "eaten", "ate", "eated", "eating", "eaten");
        insertQuiz(db, id, "Dạng quá khứ phân từ (V-ed/cột 3) của 'tell' là gì?",
                "told", "telled", "telling", "tolds", "told");

        // Chia dạng thì hiện tại hoàn thành của các động từ trong ngoặc
        insertQuiz(db, id, "They _______ (fix) the bicycle since 8.00 a.m.",
                "have fixed", "has fixed", "fixed", "are fixing", "have fixed");
        insertQuiz(db, id, "He _______ (live) here for 6 months.",
                "has lived", "have lived", "lived", "is living", "has lived");
        insertQuiz(db, id, "My father _______ recently _______ (paint) my room.",
                "has - painted", "have - painted", "is - painting", "was - painting", "has - painted");
        insertQuiz(db, id, "My sister _______ (run) in the park for 20 minutes.",
                "has run", "have run", "is running", "ran", "has run");
        insertQuiz(db, id, "We _______ (study) English for 3 weeks.",
                "have studied", "has studied", "studied", "are studying", "have studied");
    }

    private static void seedDay16Content(AppDatabase db, int id) {
        insertVocab(db, id, "return", "quay trở lại", "Word");
        insertVocab(db, id, "check", "kiểm tra", "Word");
        insertVocab(db, id, "lend", "cho vay, cho mượn", "Word");
        insertVocab(db, id, "look", "trông có vẻ", "Word");
        insertVocab(db, id, "cancel", "huỷ bỏ", "Word");
        insertVocab(db, id, "carry", "mang, vác", "Word");
        insertVocab(db, id, "turn on", "bật lên", "Word");
        insertVocab(db, id, "suitcase", "va li", "Word");
        insertVocab(db, id, "drink", "đồ uống", "Word");
        insertVocab(db, id, "juice", "nước ép", "Word");
        insertVocab(db, id, "heater", "máy sưởi", "Word");
        insertVocab(db, id, "partner", "bạn đời, bạn đồng hành", "Word");
        insertVocab(db, id, "tired", "mệt mỏi", "Word");
        insertVocab(db, id, "hungry", "đói", "Word");
        insertVocab(db, id, "better", "tốt hơn, khoẻ hơn", "Word");
        insertVocab(db, id, "perfect", "hoàn hảo", "Word");
        insertVocab(db, id, "today", "hôm nay", "Word");
        insertVocab(db, id, "tomorrow", "ngày mai", "Word");
        insertVocab(db, id, "tonight", "tối nay", "Word");
        insertVocab(db, id, "soon", "sớm", "Word");

        insertQuiz(db, id, "I believe they will ______ the match.", "win", "won", "", "", "win");
        insertQuiz(db, id, "Will she come to your party? – Yes, she ______.", "won’t", "will", "", "", "will");
        insertQuiz(db, id, "We ______ you the letter soon.", "will send", "will sending", "", "", "will send");
        insertQuiz(db, id, "I think James ________ tomorrow.", "will arrive", "arrived", "", "", "will arrive");
        insertQuiz(db, id, "I’m sure that you will ______ the party.", "enjoy", "enjoyed", "", "", "enjoy");
        insertQuiz(db, id, "They won’t ______ us money.", "lending", "lend", "", "", "lend");
        insertQuiz(db, id, "I think it ______ a lovely day tomorrow.", "was", "will be", "", "", "will be");
        insertQuiz(db, id, "I will _______ these plants for you.", "water", "watered", "", "", "water");
        insertQuiz(db, id, "I don’t think he _______ tonight.", "will leave", "will leaving", "", "", "will leave");
        insertQuiz(db, id, "You look tired. I ______ you carry this suitcase.", "will help", "will helped", "", "", "will help");
        insertQuiz(db, id, "I suppose it _______ next week.", "will rain", "rained", "", "", "will rain");
        insertQuiz(db, id, "I think they _______ the match.", "cancel", "will cancel", "", "", "will cancel");
        insertQuiz(db, id, "I believe she _______ next month.", "returned", "will return", "", "", "will return");
        insertQuiz(db, id, "A: Do you want a drink? B: I ______ an orange juice.", "will have", "will having", "", "", "will have");
        insertQuiz(db, id, "Are you hungry? Wait. I ______ lunch for you.", "cooking", "will cook", "", "", "will cook");
        insertQuiz(db, id, "I’m sure you _______ a great time in Paris.", "have", "will have", "", "", "will have");
        insertQuiz(db, id, "I believe you _______ better soon.", "will feel", "have felt", "", "", "will feel");
        insertQuiz(db, id, "A: I’m cold. B: I _______ on the heater.", "will turn", "will turned", "", "", "will turn");
        insertQuiz(db, id, "I think he _____ a perfect partner in the future.", "will find", "has found", "", "", "will find");

        insertQuiz(db, id, "They _____________ (return) home tonight.", "will return", "returns", "", "", "will return");
        insertQuiz(db, id, "We _____________ (be) better soon.", "will be", "are", "", "", "will be");
        insertQuiz(db, id, "______ he _______ (tell) a story tomorrow?", "Will - tell", "Does - tell", "", "", "Will - tell");
        insertQuiz(db, id, "I _____________ (lend) him the book next week.", "will lend", "lends", "", "", "will lend");
        insertQuiz(db, id, "Quang _____________ (not sell) his car next year.", "won't sell", "doesn't sell", "", "", "won't sell");
        insertQuiz(db, id, "Will the boy travel by car? - No, __________.", "he won't", "he doesn't", "", "", "he won't");
        insertQuiz(db, id, "Will you get up at 6.00 tomorrow? - _______________.", "Yes, I will.", "No, I don't.", "", "", "Yes, I will.");
        insertQuiz(db, id, "Will they watch a cartoon tonight? - ______________.", "Yes, they will.", "No, they don't.", "", "", "Yes, they will.");
        insertQuiz(db, id, "Will he wear shoes to the party? - No, _______________.", "he won't", "he doesn't", "", "", "he won't");
        insertQuiz(db, id, "Will your kids go to school tomorrow? - _______________.", "Yes, they will.", "No, they won't.", "", "", "Yes, they will.");

        insertQuiz(db, id, "This bag is so big. I _______ it for you.", "will carry", "carried", "have carried", "", "will carry");
        insertQuiz(db, id, "They _______ the meeting soon.", "cancelled", "have cancelled", "will cancel", "", "will cancel");
        insertQuiz(db, id, "I don’t think it _______ tomorrow.", "rains", "will rain", "rained", "", "will rain");
        insertQuiz(db, id, "It's cold. I _______ on the heater.", "is turning", "will turn", "turned", "", "will turn");
        insertQuiz(db, id, "We _______ a new house next year.", "didn’t buy", "haven’t bought", "won’t buy", "", "won’t buy");
        insertQuiz(db, id, "I think she _______ back tomorrow.", "will go", "goes", "went", "", "will go");
        insertQuiz(db, id, "_______ you write your essay tonight?", "Will", "Are", "Have", "", "Will");
        insertQuiz(db, id, "I think it _______ hot tomorrow.", "was", "is", "will be", "", "will be");
        insertQuiz(db, id, "Your parents _______ your dog soon.", "feed", "will feed", "don’t feed", "", "will feed");
        insertQuiz(db, id, "Freddy _______ a picture next week.", "have brought", "bring", "will bring", "", "will bring");
    }

    private static void seedDay17Content(AppDatabase db, int id) {
        insertVocab(db, id, "complete", "hoàn thành", "Word");
        insertVocab(db, id, "graduate", "tốt nghiệp", "Word");
        insertVocab(db, id, "pass", "vượt qua, thi đỗ", "Word");
        insertVocab(db, id, "retire", "nghỉ hưu", "Word");
        insertVocab(db, id, "film", "bộ phim", "Word");
        insertVocab(db, id, "guest", "khách", "Word");
        insertVocab(db, id, "report", "báo cáo", "Word");
        insertVocab(db, id, "project", "dự án", "Word");

        insertQuiz(db, id, "She will have _______ the novel by 5.00.", "finish", "finished", "", "", "finished");
        insertQuiz(db, id, "He won’t ______ written the essay by tomorrow.", "have", "has", "", "", "have");
        insertQuiz(db, id, "Next week I _______ here for 2 years.", "will work", "will have worked", "", "", "will have worked");
        insertQuiz(db, id, "We ________ for 3 years by next month.", "will have married", "will marry", "", "", "will have married");
        insertQuiz(db, id, "Our guests are coming at 7.30. I will ______ by then.", "has finished", "have finished", "", "", "have finished");
        insertQuiz(db, id, "By next week Linda will have ______.", "graduated", "graduate", "", "", "graduated");
        insertQuiz(db, id, "My baby _______ for 2 hours by 9.00.", "will have slept", "will sleep", "", "", "will have slept");
        insertQuiz(db, id, "I hope that I will have _______ my exam by summer.", "pass", "passed", "", "", "passed");
        insertQuiz(db, id, "Sophia _________ work by the end of this week.", "won’t have finished", "won’t has finished", "", "", "won’t have finished");
        insertQuiz(db, id, "Will you ________ the report by 6.00 tomorrow?", "had finished", "have finished", "", "", "have finished");
        insertQuiz(db, id, "My brother ________ in hospital for 2 weeks by next week.", "will have been", "has been", "", "", "will have been");
        insertQuiz(db, id, "We ________ the project by the end of this week.", "are completing", "will have completed", "", "", "will have completed");
        insertQuiz(db, id, "His sister-in-law _______ at this bank for 3 years by next month.", "has worked", "will have worked", "", "", "will have worked");
        insertQuiz(db, id, "They _______ home by 7.30 tonight.", "won’t have gone", "won’t had gone", "", "", "won’t have gone");
        insertQuiz(db, id, "Next week we ________ in Paris for 2 years.", "have lived", "will have lived", "", "", "will have lived");
        insertQuiz(db, id, "She ________ by 6.00 today.", "won’t have finished", "won’t has finished", "", "", "won’t have finished");
        insertQuiz(db, id, "By next week, my grandfather ______ for 2 months.", "will have retired", "has retired", "", "", "will have retired");
        insertQuiz(db, id, "Susan _______ by the end of next month.", "will have left", "has left", "", "", "will have left");
        insertQuiz(db, id, "He _______ this novel by the end of this week.", "is reading", "will have read", "", "", "will have read");

        insertQuiz(db, id, "By tomorrow, I _____________ (complete) the project.", "will have completed", "complete", "", "", "will have completed");
        insertQuiz(db, id, "Next month, my father _____________ (work) for the factory for a year.", "will have worked", "works", "", "", "will have worked");
        insertQuiz(db, id, "I _____________ (not /send) the letter by 5 p.m. today.", "won't have sent", "don't send", "", "", "won't have sent");
        insertQuiz(db, id, "He _____________ (return) the book by the end of the day.", "will have returned", "returns", "", "", "will have returned");
        insertQuiz(db, id, "_____ you __________ (arrive) in Hanoi by tomorrow afternoon?", "Will - have arrived", "Do - arrive", "", "", "Will - have arrived");
        insertQuiz(db, id, "They _______ the film by the end of this month.", "will finish", "will have finished", "", "", "will have finished");
        insertQuiz(db, id, "Next month, we _______ in New York for 2 years.", "will be", "will have been", "", "", "will have been");
        insertQuiz(db, id, "It’s very hot. I _______ the fan.", "will turn", "will have turned", "", "", "will turn");
        insertQuiz(db, id, "By next year, she _______ from university.", "will graduate", "will have graduated", "", "", "will have graduated");
        insertQuiz(db, id, "I think it ______ tomorrow morning.", "will rain", "will have rained", "", "", "will rain");
        
        insertQuiz(db, id, "My teacher _______ the report by tomorrow.", "will not receive", "don’t receive", "won’t have received", "", "won’t have received");
        insertQuiz(db, id, "My son _______ the homework by 7 pm today.", "will do", "haven’t done", "will have done", "", "will have done");
        insertQuiz(db, id, "_____ they _______ to a new flat by the end of this month?", "Will – have moved", "Do – move", "Are – moving", "", "Will – have moved");
        insertQuiz(db, id, "Next year, I _______ him for 10 years.", "knew", "will have known", "don’t know", "", "will have known");
        insertQuiz(db, id, "By next month, I _______ the house.", "didn’t paint", "won’t have painted", "doesn’t paint", "", "won’t have painted");
        insertQuiz(db, id, "Next month, I _______ English for 2 years.", "will have learnt", "am learning", "was learning", "", "will have learnt");
        insertQuiz(db, id, "Next week, they _______ at university for a month.", "has studied", "don’t study", "will have studied", "", "will have studied");
        insertQuiz(db, id, "By 6 pm today, his mother _______ dinner.", "has cooked", "will have cooked", "wasn’t cooking", "", "will have cooked");
        insertQuiz(db, id, "We _______ your suitcase by the end of the day.", "are finding", "hasn’t found", "will have found", "", "will have found");
        insertQuiz(db, id, "By tomorrow, I _______ the essay.", "won’t have written", "didn’t write", "will not write", "", "won’t have written");
    }

    /** Bổ sung nội dung tự soạn, đi từ nhận biết đến vận dụng cho từng chủ điểm. */
    private static void seedEnrichedContent(AppDatabase db) {
        addEnrichment(db, "Ngày 1", new String[][]{
                {"ready", "sẵn sàng", "Adjective"}, {"careful", "cẩn thận", "Adjective"},
                {"neighbor", "hàng xóm", "Noun"}, {"important", "quan trọng", "Adjective"}
        }, new String[][]{
                {"[Cơ bản] My name ______ Lan.", "am", "is", "are", "be", "is"},
                {"[Cơ bản] The children ______ not tired.", "am", "is", "are", "be", "are"},
                {"[Trung bình] Chọn câu đúng.", "She is a careful student.", "She are a careful student.", "She am careful student.", "She be a careful student.", "She is a careful student."},
                {"[Trung bình] Tom and I ______ ready, but Anna ______ not.", "are - is", "is - are", "am - is", "are - are", "are - is"},
                {"[Nâng cao] Neither the teacher nor the student ______ late.", "am", "is", "are", "be", "is"}
        });
        addEnrichment(db, "Ngày 2", new String[][]{
                {"near", "gần", "Adjective"}, {"far", "xa", "Adjective"},
                {"object", "đồ vật", "Noun"}, {"relative", "họ hàng", "Noun"}
        }, new String[][]{
                {"[Cơ bản] ______ this your bag?", "Am", "Is", "Are", "Be", "Is"},
                {"[Cơ bản] ______ those your keys?", "Am", "Is", "Are", "Be", "Are"},
                {"[Trung bình] Is your aunt a doctor? – Yes, ______.", "she is", "it is", "they are", "she does", "she is"},
                {"[Trung bình] ______ are my shoes over there.", "This", "That", "These", "Those", "Those"},
                {"[Nâng cao] Chọn câu hỏi đúng cho câu trả lời “No, these aren't mine.”", "Are these yours?", "Is this yours?", "Do these yours?", "Are this yours?", "Are these yours?"}
        });
        addEnrichment(db, "Ngày 3", new String[][]{
                {"owner", "chủ sở hữu", "Noun"}, {"stranger", "người lạ", "Noun"},
                {"wallet", "ví tiền", "Noun"}, {"umbrella", "ô, dù", "Noun"}
        }, new String[][]{
                {"[Cơ bản] ______ is that man? – He is my uncle.", "What", "Who", "Where", "When", "Who"},
                {"[Cơ bản] ______ is in the box? – A new phone.", "What", "Who", "Where", "When", "What"},
                {"[Trung bình] ______ are those people? – Our new neighbors.", "What", "Who", "Where", "When", "Who"},
                {"[Trung bình] What ______ your favorite subjects?", "am", "is", "are", "be", "are"},
                {"[Nâng cao] “______ is responsible for this project?” – “Ms. Hoa is.”", "What", "Who", "Where", "When", "Who"}
        });
        addEnrichment(db, "Ngày 4", new String[][]{
                {"library", "thư viện", "Noun"}, {"meeting", "cuộc họp", "Noun"},
                {"weekend", "cuối tuần", "Noun"}, {"shelf", "kệ", "Noun"}
        }, new String[][]{
                {"[Cơ bản] ______ is the library? – Next to the bank.", "When", "Where", "Who", "What", "Where"},
                {"[Cơ bản] The meeting is ______ 9 a.m.", "in", "on", "at", "under", "at"},
                {"[Trung bình] We visit our grandparents ______ Sundays.", "in", "on", "at", "from", "on"},
                {"[Trung bình] The books are ______ the top shelf.", "in", "on", "at", "between", "on"},
                {"[Nâng cao] ______ does the course begin? – On the first Monday in June.", "Where", "When", "Who", "How many", "When"}
        });
        addEnrichment(db, "Ngày 5", new String[][]{
                {"practice", "luyện tập", "Verb"}, {"prepare", "chuẩn bị", "Verb"},
                {"usually", "thường xuyên", "Adverb"}, {"routine", "thói quen hằng ngày", "Noun"}
        }, new String[][]{
                {"[Cơ bản] I ______ English every evening.", "practice", "practices", "practicing", "practiced", "practice"},
                {"[Cơ bản] My sister ______ breakfast at 7 a.m.", "have", "has", "having", "had", "has"},
                {"[Trung bình] The bus ______ at this stop every ten minutes.", "stop", "stops", "stopping", "stopped", "stops"},
                {"[Trung bình] Nam usually ______ his homework before dinner.", "finish", "finishes", "finished", "finishing", "finishes"},
                {"[Nâng cao] Water ______ at 100°C under normal conditions.", "boil", "boils", "is boiling", "boiled", "boils"}
        });
        addEnrichment(db, "Ngày 6", new String[][]{
                {"believe", "tin tưởng", "Verb"}, {"need", "cần", "Verb"},
                {"understand", "hiểu", "Verb"}, {"often", "thường", "Adverb"}
        }, new String[][]{
                {"[Cơ bản] I ______ drink coffee at night.", "don't", "doesn't", "am not", "isn't", "don't"},
                {"[Cơ bản] She ______ work on Sundays.", "don't", "doesn't", "isn't", "not", "doesn't"},
                {"[Trung bình] My parents ______ understand this rule.", "doesn't", "don't", "aren't", "not", "don't"},
                {"[Trung bình] He doesn't ______ a car.", "has", "have", "having", "had", "have"},
                {"[Nâng cao] Neither Mai nor her brother ______ like spicy food.", "don't", "doesn't", "isn't", "aren't", "doesn't"}
        });
        addEnrichment(db, "Ngày 7", new String[][]{
                {"question", "câu hỏi", "Noun"}, {"answer", "trả lời", "Verb"},
                {"prefer", "thích hơn", "Verb"}, {"frequency", "tần suất", "Noun"}
        }, new String[][]{
                {"[Cơ bản] ______ you like music?", "Do", "Does", "Are", "Is", "Do"},
                {"[Cơ bản] ______ Lan walk to school?", "Do", "Does", "Is", "Has", "Does"},
                {"[Trung bình] Where ______ your parents work?", "do", "does", "are", "is", "do"},
                {"[Trung bình] How often ______ he play tennis?", "do", "does", "is", "has", "does"},
                {"[Nâng cao] Which book ______ your teacher recommend?", "do", "does", "is", "has", "does"}
        });
        addEnrichment(db, "Ngày 8", new String[][]{
                {"habit", "thói quen", "Noun"}, {"schedule", "lịch trình", "Noun"},
                {"rarely", "hiếm khi", "Adverb"}, {"always", "luôn luôn", "Adverb"}
        }, new String[][]{
                {"[Cơ bản] We ______ dinner together every day.", "eat", "eats", "are eating", "ate", "eat"},
                {"[Cơ bản] He ______ late for class.", "never is", "is never", "never", "does never", "is never"},
                {"[Trung bình] The train ______ at 6:15 tomorrow morning.", "leave", "leaves", "is leave", "leaving", "leaves"},
                {"[Trung bình] How often ______ she ______ the gym?", "do - visit", "does - visit", "is - visiting", "does - visits", "does - visit"},
                {"[Nâng cao] If you heat ice, it ______.", "melt", "melts", "is melting", "melted", "melts"}
        });
        addEnrichment(db, "Ngày 9", new String[][]{
                {"noun", "danh từ", "Noun"}, {"verb", "động từ", "Noun"},
                {"adjective", "tính từ", "Noun"}, {"adverb", "trạng từ", "Noun"}
        }, new String[][]{
                {"[Cơ bản] Từ nào là danh từ?", "beauty", "beautiful", "beautifully", "beautify", "beauty"},
                {"[Cơ bản] She is a ______ singer.", "beauty", "beautiful", "beautifully", "beautify", "beautiful"},
                {"[Trung bình] He completed the task ______.", "success", "successful", "successfully", "succeed", "successfully"},
                {"[Trung bình] The suffix “-tion” thường tạo thành loại từ nào?", "Danh từ", "Động từ", "Tính từ", "Trạng từ", "Danh từ"},
                {"[Nâng cao] Her quick ______ prevented a serious accident.", "decide", "decision", "decisive", "decisively", "decision"}
        });
        addEnrichment(db, "Ngày 10", new String[][]{
                {"currently", "hiện tại", "Adverb"}, {"temporary", "tạm thời", "Adjective"},
                {"improve", "cải thiện", "Verb"}, {"concentrate", "tập trung", "Verb"}
        }, new String[][]{
                {"[Cơ bản] Look! The baby ______.", "cries", "is crying", "cry", "cried", "is crying"},
                {"[Cơ bản] We ______ for the bus now.", "wait", "are waiting", "waited", "have waited", "are waiting"},
                {"[Trung bình] She ______ with her aunt this month.", "stays", "is staying", "stayed", "has stayed", "is staying"},
                {"[Trung bình] Why ______ you ______?", "do - laugh", "are - laughing", "is - laughing", "did - laugh", "are - laughing"},
                {"[Nâng cao] More people ______ online these days.", "shop", "are shopping", "shopped", "shops", "are shopping"}
        });
        addEnrichment(db, "Ngày 11", new String[][]{
                {"normally", "thông thường", "Adverb"}, {"at the moment", "ngay lúc này", "Phrase"},
                {"permanent", "lâu dài", "Adjective"}, {"trend", "xu hướng", "Noun"}
        }, new String[][]{
                {"[Cơ bản] She usually ______ tea, but today she ______ coffee.", "drinks - is drinking", "is drinking - drinks", "drink - drinks", "drank - drinks", "drinks - is drinking"},
                {"[Cơ bản] Be quiet! I ______ to the news.", "listen", "listens", "am listening", "listened", "am listening"},
                {"[Trung bình] My father ______ in Hanoi, but he ______ in Da Nang this week.", "works - is working", "is working - works", "work - working", "worked - works", "works - is working"},
                {"[Trung bình] I ______ what you mean.", "am understanding", "understand", "understood now", "am understand", "understand"},
                {"[Nâng cao] The climate ______ warmer, so scientists ______ the changes closely.", "gets - study", "is getting - are studying", "get - are study", "is get - study", "is getting - are studying"}
        });
        addEnrichment(db, "Ngày 12", new String[][]{
                {"yesterday", "hôm qua", "Adverb"}, {"ago", "trước đây", "Adverb"},
                {"discover", "khám phá", "Verb"}, {"achieve", "đạt được", "Verb"}
        }, new String[][]{
                {"[Cơ bản] We ______ the museum yesterday.", "visit", "visited", "visiting", "visits", "visited"},
                {"[Cơ bản] She ______ a letter last night.", "write", "wrote", "written", "writes", "wrote"},
                {"[Trung bình] The class ______ at nine and ______ at eleven.", "began - ended", "begin - end", "begun - ended", "begins - ends", "began - ended"},
                {"[Trung bình] They ______ a rare bird during the trip.", "see", "saw", "seen", "seeing", "saw"},
                {"[Nâng cao] After years of research, the scientist finally ______ a solution.", "find", "found", "founded", "has find", "found"}
        });
        addEnrichment(db, "Ngày 13", new String[][]{
                {"miss", "bỏ lỡ", "Verb"}, {"attend", "tham dự", "Verb"},
                {"reason", "lý do", "Noun"}, {"unexpected", "bất ngờ", "Adjective"}
        }, new String[][]{
                {"[Cơ bản] I ______ go out last night.", "don't", "didn't", "wasn't", "doesn't", "didn't"},
                {"[Cơ bản] ______ you see Minh yesterday?", "Do", "Did", "Were", "Have", "Did"},
                {"[Trung bình] Why ______ she ______ the meeting?", "did - miss", "did - missed", "does - miss", "was - miss", "did - miss"},
                {"[Trung bình] They didn't ______ the answer.", "knew", "know", "known", "knowing", "know"},
                {"[Nâng cao] Which route ______ the driver ______ after the road closed?", "did - take", "did - took", "was - take", "does - took", "did - take"}
        });
        addEnrichment(db, "Ngày 14", new String[][]{
                {"interrupt", "làm gián đoạn", "Verb"}, {"while", "trong khi", "Conjunction"},
                {"suddenly", "đột nhiên", "Adverb"}, {"background", "bối cảnh", "Noun"}
        }, new String[][]{
                {"[Cơ bản] At 8 p.m., I ______ TV.", "watched", "was watching", "am watching", "watch", "was watching"},
                {"[Cơ bản] They ______ when the phone rang.", "slept", "were sleeping", "are sleeping", "sleep", "were sleeping"},
                {"[Trung bình] While Lan ______, her brother ______ dinner.", "studied - cooked", "was studying - was cooking", "studies - cooks", "is studying - cooks", "was studying - was cooking"},
                {"[Trung bình] What ______ you ______ at this time yesterday?", "did - do", "were - doing", "are - doing", "was - doing", "were - doing"},
                {"[Nâng cao] The lights went out while the surgeon ______ the operation.", "performed", "was performing", "performs", "has performed", "was performing"}
        });
        addEnrichment(db, "Ngày 15", new String[][]{
                {"experience", "trải nghiệm", "Noun"}, {"recently", "gần đây", "Adverb"},
                {"already", "đã rồi", "Adverb"}, {"yet", "chưa, rồi", "Adverb"}
        }, new String[][]{
                {"[Cơ bản] I ______ my homework already.", "finished", "have finished", "finish", "am finishing", "have finished"},
                {"[Cơ bản] She ______ never ______ abroad.", "has - traveled", "have - traveled", "did - travel", "is - traveling", "has - traveled"},
                {"[Trung bình] ______ you ever ______ sushi?", "Did - eat", "Have - eaten", "Has - ate", "Are - eating", "Have - eaten"},
                {"[Trung bình] We haven't received the results ______.", "already", "yet", "ever", "since", "yet"},
                {"[Nâng cao] This is the most useful course I ______.", "ever took", "have ever taken", "am taking", "had take", "have ever taken"}
        });
        addEnrichment(db, "Ngày 16", new String[][]{
                {"promise", "hứa", "Verb"}, {"predict", "dự đoán", "Verb"},
                {"probably", "có lẽ", "Adverb"}, {"decision", "quyết định", "Noun"}
        }, new String[][]{
                {"[Cơ bản] I ______ call you tonight.", "will", "am", "did", "have", "will"},
                {"[Cơ bản] She won't ______ late.", "arrives", "arrive", "arrived", "arriving", "arrive"},
                {"[Trung bình] A: The doorbell is ringing. B: I ______ it.", "answer", "will answer", "answered", "am answer", "will answer"},
                {"[Trung bình] I promise I ______ anyone your secret.", "won't tell", "don't tell", "didn't tell", "haven't told", "won't tell"},
                {"[Nâng cao] Based on current trends, demand ______ increase next year.", "probably will", "will probably", "probably", "is probably increase", "will probably"}
        });
        addEnrichment(db, "Ngày 17", new String[][]{
                {"deadline", "hạn chót", "Noun"}, {"milestone", "cột mốc", "Noun"},
                {"accomplish", "hoàn thành, đạt được", "Verb"}, {"by then", "trước lúc đó", "Phrase"}
        }, new String[][]{
                {"[Cơ bản] By Friday, I ______ the report.", "will finish", "will have finished", "finished", "have finished", "will have finished"},
                {"[Cơ bản] She will have ______ before noon.", "leave", "left", "leaving", "leaves", "left"},
                {"[Trung bình] ______ they ______ the bridge by next year?", "Will - complete", "Will - have completed", "Do - complete", "Have - completed", "Will - have completed"},
                {"[Trung bình] By 2030, he ______ at the company for twenty years.", "will work", "will have worked", "has worked", "works", "will have worked"},
                {"[Nâng cao] By the time the auditors arrive, the team ______ all the financial records.", "will review", "will have reviewed", "reviews", "has reviewed", "will have reviewed"}
        });
    }

    private static void addEnrichment(AppDatabase db, String dayLabel, String[][] vocabulary, String[][] quizzes) {
        LessonEntity lesson = findLessonByDayLabel(db, dayLabel);
        if (lesson == null) return;
        for (String[] word : vocabulary) {
            insertVocab(db, lesson.id, word[0], word[1], word[2]);
        }
        for (String[] quiz : quizzes) {
            insertQuiz(db, lesson.id, quiz[0], quiz[1], quiz[2], quiz[3], quiz[4], quiz[5]);
        }
    }

    private static void seedLesson2DemonstrativePractice(AppDatabase db) {
        LessonEntity lesson = findLessonByDayLabel(db, "Ngày 2");
        if (lesson == null) return;

        String[][] quizzes = {
                // Cơ bản
                {"[Cơ bản] ______ is my phone. (cầm trong tay)", "This", "That", "These", "Those", "This"},
                {"[Cơ bản] ______ is a nice house. (chỉ ngôi nhà ở xa)", "This", "That", "These", "Those", "That"},
                {"[Cơ bản] ______ are my friends. (đứng cạnh bạn)", "This", "That", "These", "Those", "These"},
                {"[Cơ bản] ______ are birds over there. (chỉ những con chim ở xa)", "This", "That", "These", "Those", "Those"},
                {"[Cơ bản] ______ cup is mine. (chiếc cốc ở gần)", "This", "That", "These", "Those", "This"},

                // Trung bình
                {"[Trung bình] Look at ______ mountains over there!", "this", "that", "these", "those", "those"},
                {"[Trung bình] ______ is a photo of my family. (bức ảnh ở gần)", "This", "That", "These", "Those", "This"},
                {"[Trung bình] ______ flowers smell good. (những bông hoa ở gần)", "This", "That", "These", "Those", "These"},
                {"[Trung bình] Is ______ your bag? (chiếc túi ở gần)", "this", "these", "those", "they", "this"},
                {"[Trung bình] What are ______ over there?", "this", "that", "these", "those", "those"},

                // Nâng cao
                {"[Nâng cao] ______ days, I feel very well.", "This", "That", "These", "Those", "These"},
                {"[Nâng cao] ______ was a great movie.", "This", "That", "These", "Those", "That"},
                {"[Nâng cao] ______ are the moments I enjoy most.", "This", "That", "These", "Those", "These"},
                {"[Nâng cao] ______ sounds perfect!", "This", "That", "These", "Those", "That"},
                {"[Nâng cao] ______ are small things, but they are important.", "This", "That", "These", "Those", "These"},

                // Giao tiếp hằng ngày
                {"[Giao tiếp] ______ way, please.", "This", "That", "These", "Those", "This"},
                {"[Giao tiếp] ______ is the bus stop over there.", "This", "That", "These", "Those", "That"},
                {"[Giao tiếp] ______ are my shoes here.", "This", "That", "These", "Those", "These"},
                {"[Giao tiếp] How much are ______ apples? (chỉ những quả táo ở xa)", "this", "that", "these", "those", "those"},
                {"[Giao tiếp] I’ll take ______ one here, please.", "this", "that", "these", "those", "this"},

                // Lỗi thường gặp và củng cố
                {"[Củng cố] ______ is not a problem.", "This", "That", "These", "Those", "That"},
                {"[Củng cố] ______ buildings over there look new.", "This", "That", "These", "Those", "Those"},
                {"[Củng cố] Is ______ seat here taken?", "this", "these", "those", "they", "this"},
                {"[Củng cố] ______ is my friend, Minh. (giới thiệu người đứng cạnh)", "This", "That", "These", "Those", "This"},
                {"[Củng cố] ______ are my colleagues here.", "This", "That", "These", "Those", "These"}
        };

        for (String[] quiz : quizzes) {
            insertQuiz(db, lesson.id, quiz[0], quiz[1], quiz[2], quiz[3], quiz[4], quiz[5]);
        }
    }
}

