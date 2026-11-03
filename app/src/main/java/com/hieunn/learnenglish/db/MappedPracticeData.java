package com.hieunn.learnenglish.db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Ngân hàng câu hỏi bổ sung do người dùng cung cấp, được chuyển sang
 * các bài có chủ đề tương ứng trong giáo trình hiện tại.
 */
final class MappedPracticeData {
    private MappedPracticeData() {
    }

    static void seed(AppDatabase db) {
        Map<Integer, Integer> lessonIds = buildLessonIds(db);
        Map<Integer, Set<String>> existing = buildQuestionIndex(db, lessonIds);
        add(db, lessonIds, existing, 1, "______ am Minh.", "I", "You", "He", "She", "I");
        add(db, lessonIds, existing, 1, "______ is my friend.", "I", "You", "He", "We", "He");
        add(db, lessonIds, existing, 1, "______ are students.", "I", "He", "She", "They", "They");
        add(db, lessonIds, existing, 1, "I ______ from Vietnam.", "am", "is", "are", "be", "am");
        add(db, lessonIds, existing, 1, "She ______ a teacher.", "am", "is", "are", "be", "is");
        add(db, lessonIds, existing, 1, "They ______ happy.", "am", "is", "are", "be", "are");
        add(db, lessonIds, existing, 1, "______ name is Lan.", "My", "Your", "His", "Her", "My");
        add(db, lessonIds, existing, 1, "What is ______ name?", "my", "your", "his", "her", "your");
        add(db, lessonIds, existing, 1, "Nice to meet ______.", "I", "you", "he", "she", "you");
        add(db, lessonIds, existing, 1, "Good ______! (chào buổi sáng)", "morning", "afternoon", "evening", "night", "morning");
        add(db, lessonIds, existing, 2, "______ is my phone. (cầm tay)", "This", "That", "These", "Those", "This");
        add(db, lessonIds, existing, 2, "______ is a nice house. (xa)", "This", "That", "These", "Those", "That");
        add(db, lessonIds, existing, 2, "______ are my friends. (gần)", "This", "That", "These", "Those", "These");
        add(db, lessonIds, existing, 2, "______ are birds over there. (xa)", "This", "That", "These", "Those", "Those");
        add(db, lessonIds, existing, 2, "Look at ______ mountains!", "this", "that", "these", "those", "those");
        add(db, lessonIds, existing, 2, "______ flowers smell good.", "This", "That", "These", "Those", "These");
        add(db, lessonIds, existing, 2, "Is ______ your bag?", "this", "these", "those", "they", "this");
        add(db, lessonIds, existing, 2, "What are ______ over there?", "this", "that", "these", "those", "those");
        add(db, lessonIds, existing, 2, "______ way, please.", "This", "That", "These", "Those", "This");
        add(db, lessonIds, existing, 2, "______ is not a problem.", "This", "That", "These", "Those", "That");
        add(db, lessonIds, existing, 3, "I ______ up at 6 every morning.", "get", "gets", "got", "getting", "get");
        add(db, lessonIds, existing, 3, "She ______ to work by bus.", "go", "goes", "went", "going", "goes");
        add(db, lessonIds, existing, 3, "They ______ dinner at 7 PM.", "eat", "eats", "ate", "eating", "eat");
        add(db, lessonIds, existing, 3, "He ______ TV every evening.", "watch", "watches", "watched", "watching", "watches");
        add(db, lessonIds, existing, 3, "We ______ English every day.", "study", "studies", "studied", "studying", "study");
        add(db, lessonIds, existing, 3, "I ______ coffee for breakfast.", "drink", "drinks", "drank", "drinking", "drink");
        add(db, lessonIds, existing, 3, "______ you like music?", "Do", "Does", "Are", "Is", "Do");
        add(db, lessonIds, existing, 3, "______ she work here?", "Do", "Does", "Are", "Is", "Does");
        add(db, lessonIds, existing, 3, "He ______ like vegetables.", "don't", "doesn't", "isn't", "not", "doesn't");
        add(db, lessonIds, existing, 3, "I ______ to bed early.", "go usually", "usually go", "goes usually", "usually goes", "usually go");
        add(db, lessonIds, existing, 4, "I ______ brush my teeth twice a day.", "always", "usually", "sometimes", "never", "always");
        add(db, lessonIds, existing, 4, "It ______ rains in summer here.", "always", "often", "sometimes", "never", "often");
        add(db, lessonIds, existing, 4, "I ______ walk to work, not every day.", "always", "usually", "sometimes", "never", "sometimes");
        add(db, lessonIds, existing, 4, "She is ______ late; she's usually on time.", "always", "usually", "sometimes", "never", "never");
        add(db, lessonIds, existing, 4, "We ______ have lunch together.", "always", "often", "rarely", "never", "often");
        add(db, lessonIds, existing, 4, "______ I stay up late.", "Sometimes", "Always", "Usually", "Never", "Sometimes");
        add(db, lessonIds, existing, 4, "He ______ reads before sleep.", "always", "sometimes", "often", "usually", "usually");
        add(db, lessonIds, existing, 4, "I ______ eat fast food; it's not healthy.", "always", "often", "sometimes", "rarely", "rarely");
        add(db, lessonIds, existing, 4, "How ______ do you exercise?", "often", "always", "usually", "sometimes", "often");
        add(db, lessonIds, existing, 4, "I ______ forget names.", "sometimes", "always", "usually", "often", "sometimes");
        add(db, lessonIds, existing, 5, "I ______ coffee every morning.", "like", "likes", "to like", "liking", "like");
        add(db, lessonIds, existing, 5, "She ______ to learn English well.", "want", "wants", "needing", "need", "wants");
        add(db, lessonIds, existing, 5, "We ______ go now, it's late.", "want", "wants", "need to", "likes", "need to");
        add(db, lessonIds, existing, 5, "Do you ______ watching movies?", "like", "want", "need", "likes", "like");
        add(db, lessonIds, existing, 5, "I ______ a new phone.", "want", "wants", "liking", "need to", "want");
        add(db, lessonIds, existing, 5, "They ______ help with homework.", "need", "needs", "wanting", "like", "need");
        add(db, lessonIds, existing, 5, "I like ______ to music.", "listen", "listening", "to listen", "listens", "listening");
        add(db, lessonIds, existing, 5, "She wants ______ home early.", "go", "goes", "to go", "going", "to go");
        add(db, lessonIds, existing, 5, "Do you want ______?", "come", "coming", "to come", "comes", "to come");
        add(db, lessonIds, existing, 5, "I need ______ more water.", "drink", "to drink", "drinking", "drinks", "to drink");
        add(db, lessonIds, existing, 6, "I ______ my homework now.", "do", "am doing", "does", "am do", "am doing");
        add(db, lessonIds, existing, 6, "Look! It ______.", "rain", "is raining", "rains", "raining", "is raining");
        add(db, lessonIds, existing, 6, "She ______ a book at the moment.", "read", "reads", "is reading", "am reading", "is reading");
        add(db, lessonIds, existing, 6, "They ______ TV right now.", "watch", "watching", "are watching", "is watching", "are watching");
        add(db, lessonIds, existing, 6, "______ you ______ dinner?", "Am / cook", "Is / cooking", "Are / cooking", "Do / cook", "Are / cooking");
        add(db, lessonIds, existing, 6, "He ______ to music.", "is listen", "is listening", "listening", "are listening", "is listening");
        add(db, lessonIds, existing, 6, "We ______ to the station.", "are going", "is going", "go", "goes", "are going");
        add(db, lessonIds, existing, 6, "What ______ you ______?", "are / doing", "is / doing", "do / do", "are / do", "are / doing");
        add(db, lessonIds, existing, 6, "I ______ my keys.", "am finding", "am looking for", "find", "looking", "am looking for");
        add(db, lessonIds, existing, 6, "Quick! The bus ______.", "comes", "is coming", "come", "is come", "is coming");
        add(db, lessonIds, existing, 7, "I ______ to Ha Noi yesterday.", "go", "went", "goes", "going", "went");
        add(db, lessonIds, existing, 7, "She ______ a nice film last week.", "see", "saw", "sees", "seeing", "saw");
        add(db, lessonIds, existing, 7, "They ______ dinner at home last night.", "have", "had", "has", "having", "had");
        add(db, lessonIds, existing, 7, "We ______ very tired yesterday.", "was", "were", "are", "is", "were");
        add(db, lessonIds, existing, 7, "He ______ his bag this morning.", "lose", "lost", "loses", "losing", "lost");
        add(db, lessonIds, existing, 7, "Did you ______ the match?", "watch", "watched", "watching", "watches", "watch");
        add(db, lessonIds, existing, 7, "I ______ not at home yesterday.", "am", "was", "were", "is", "was");
        add(db, lessonIds, existing, 7, "She ______ me last Sunday.", "call", "called", "calls", "calling", "called");
        add(db, lessonIds, existing, 7, "What ______ you do last night?", "do", "did", "are", "were", "did");
        add(db, lessonIds, existing, 7, "It ______ a great party.", "is", "was", "were", "are", "was");
        add(db, lessonIds, existing, 8, "One ______, two ______.", "box / boxes", "boxes / box", "box / box", "boxes / boxes", "box / boxes");
        add(db, lessonIds, existing, 8, "I have ______ books on the shelf.", "many", "much", "a lot", "very", "many");
        add(db, lessonIds, existing, 8, "She doesn't have ______ free time.", "many", "much", "a few", "few", "much");
        add(db, lessonIds, existing, 8, "There are ______ apples left.", "a little", "a few", "much", "little", "a few");
        add(db, lessonIds, existing, 8, "There is ______ water in the glass.", "a few", "some", "many", "several", "some");
        add(db, lessonIds, existing, 8, "I need ______ information.", "some", "many", "a", "few", "some");
        add(db, lessonIds, existing, 8, "How ______ people are coming?", "many", "much", "some", "any", "many");
        add(db, lessonIds, existing, 8, "How ______ does it cost?", "many", "much", "cost", "price", "much");
        add(db, lessonIds, existing, 8, "Do you have ______ questions?", "some", "any", "a", "an", "any");
        add(db, lessonIds, existing, 8, "I have ______ friends here.", "much", "a little", "several", "lot", "several");
        add(db, lessonIds, existing, 9, "I ______ call you later.", "am", "will", "going", "shall", "will");
        add(db, lessonIds, existing, 9, "She ______ visit her parents tomorrow.", "will", "is will", "wills", "going", "will");
        add(db, lessonIds, existing, 9, "We ______ meet at 6 PM.", "will", "are will", "will are", "is will", "will");
        add(db, lessonIds, existing, 9, "______ you be free next week?", "Will", "Are", "Do", "Is", "Will");
        add(db, lessonIds, existing, 9, "I think it ______ rain later.", "is", "will", "can", "shall", "will");
        add(db, lessonIds, existing, 9, "I'm tired. I ______ go to bed now.", "will", "am going to", "going to", "will to", "am going to");
        add(db, lessonIds, existing, 9, "Look at clouds! It ______ rain.", "will", "is going to", "going to", "can", "is going to");
        add(db, lessonIds, existing, 9, "They ______ buy a new house.", "will", "are will", "will are", "is will", "will");
        add(db, lessonIds, existing, 9, "______ we go together?", "Will", "Shall", "Are", "Do", "Shall");
        add(db, lessonIds, existing, 9, "I ______ not forget you.", "am", "will", "do", "shall", "will");
        add(db, lessonIds, existing, 10, "The meeting starts ______ 8 AM.", "in", "on", "at", "to", "at");
        add(db, lessonIds, existing, 10, "We don't work ______ Sunday.", "in", "on", "at", "by", "on");
        add(db, lessonIds, existing, 10, "She was born ______ 1995.", "in", "on", "at", "by", "in");
        add(db, lessonIds, existing, 10, "Let's meet ______ the station.", "in", "on", "at", "to", "at");
        add(db, lessonIds, existing, 10, "My book is ______ the table.", "in", "on", "at", "over", "on");
        add(db, lessonIds, existing, 10, "They live ______ the city center.", "in", "on", "at", "by", "in");
        add(db, lessonIds, existing, 10, "I'll see you ______ Monday.", "in", "on", "at", "by", "on");
        add(db, lessonIds, existing, 10, "We arrived ______ 5 o'clock.", "in", "on", "at", "to", "at");
        add(db, lessonIds, existing, 10, "She is ______ home now.", "in", "on", "at", "by", "at");
        add(db, lessonIds, existing, 10, "It rains a lot ______ winter.", "in", "on", "at", "by", "in");
        add(db, lessonIds, existing, 11, "This is ______ book. (của tôi)", "my", "mine", "I", "me", "my");
        add(db, lessonIds, existing, 11, "Is that ______ bag? (của bạn)", "your", "yours", "you", "yourself", "your");
        add(db, lessonIds, existing, 11, "The house is ______. (của tôi)", "my", "mine", "I", "me", "mine");
        add(db, lessonIds, existing, 11, "She loves ______ job. (công việc của cô ấy)", "her", "hers", "she", "herself", "her");
        add(db, lessonIds, existing, 11, "We like ______ teacher. (thầy giáo của chúng tôi)", "our", "ours", "us", "ourselves", "our");
        add(db, lessonIds, existing, 11, "These keys are ______. (của họ)", "their", "theirs", "them", "themselves", "theirs");
        add(db, lessonIds, existing, 11, "What is ______ name? (tên của anh ấy)", "his", "him", "he", "himself", "his");
        add(db, lessonIds, existing, 11, "That is not ______ car. (xe của chúng tôi)", "our", "ours", "us", "we", "our");
        add(db, lessonIds, existing, 11, "I forgot ______ phone. (điện thoại của tôi)", "my", "mine", "me", "I", "my");
        add(db, lessonIds, existing, 11, "Is this pen ______? (của bạn)", "your", "yours", "you", "yourself", "yours");
        add(db, lessonIds, existing, 12, "This book is ______ than that one.", "interesting", "more interesting", "most interesting", "interestinger", "more interesting");
        add(db, lessonIds, existing, 12, "She is ______ in her class.", "taller", "more tall", "the tallest", "tall", "the tallest");
        add(db, lessonIds, existing, 12, "Today is ______ than yesterday.", "hot", "hotter", "more hot", "hottest", "hotter");
        add(db, lessonIds, existing, 12, "This is ______ story I know.", "good", "better", "best", "the best", "the best");
        add(db, lessonIds, existing, 12, "My job is ______ than yours.", "easy", "easier", "more easy", "easiest", "easier");
        add(db, lessonIds, existing, 12, "Health is ______ than money.", "important", "more important", "most important", "importanter", "more important");
        add(db, lessonIds, existing, 12, "That was ______ moment of the day.", "happy", "happier", "the happiest", "happyest", "the happiest");
        add(db, lessonIds, existing, 12, "I feel ______ now than before.", "good", "better", "best", "gooder", "better");
        add(db, lessonIds, existing, 12, "Which is ______, the bus or the train?", "fast", "faster", "fastest", "more fast", "faster");
        add(db, lessonIds, existing, 12, "She runs ______ than me.", "quick", "quicker", "more quick", "quickest", "quicker");
        add(db, lessonIds, existing, 13, "I ______ speak English a little.", "can", "should", "must", "need", "can");
        add(db, lessonIds, existing, 13, "You ______ see a doctor if you feel sick.", "can", "should", "must", "could", "should");
        add(db, lessonIds, existing, 13, "Everyone ______ follow traffic rules.", "can", "should", "must", "might", "must");
        add(db, lessonIds, existing, 13, "______ I use your phone, please?", "Can", "Should", "Must", "Need", "Can");
        add(db, lessonIds, existing, 13, "You ______ smoke here. It's forbidden.", "can't", "shouldn't", "mustn't", "needn't", "mustn't");
        add(db, lessonIds, existing, 13, "______ you help me with this?", "Can", "Should", "Must", "May", "Can");
        add(db, lessonIds, existing, 13, "We ______ go now or we'll be late.", "can", "should", "must", "might", "must");
        add(db, lessonIds, existing, 13, "You ______ try again, don't give up.", "can", "should", "must", "could", "should");
        add(db, lessonIds, existing, 13, "I ______ hear you clearly, speak louder.", "can't", "shouldn't", "mustn't", "needn't", "can't");
        add(db, lessonIds, existing, 13, "______ we meet at 5?", "Can", "Should", "Must", "Need", "Can");
        add(db, lessonIds, existing, 14, "______ you like coffee? – Yes, I do.", "Do", "Are", "Does", "Have", "Do");
        add(db, lessonIds, existing, 14, "Is she a student? – ______.", "Yes, she is.", "Yes, she does.", "Yes, she can.", "Yes, she has.", "Yes, she is.");
        add(db, lessonIds, existing, 14, "Do they work here? – No, ______.", "they don't", "they aren't", "they not", "they doesn't", "they don't");
        add(db, lessonIds, existing, 14, "______ he go to school yesterday? – Yes, he did.", "Did", "Does", "Is", "Was", "Did");
        add(db, lessonIds, existing, 14, "Are you tired? – No, ______.", "I am not", "I don't", "I not", "I can't", "I am not");
        add(db, lessonIds, existing, 14, "______ you speak slowly, please?", "Can", "Should", "Must", "Need", "Can");
        add(db, lessonIds, existing, 14, "How ______ is it from here?", "far", "long", "many", "often", "far");
        add(db, lessonIds, existing, 14, "______ is your birthday?", "When", "Where", "How", "What", "When");
        add(db, lessonIds, existing, 14, "______ bag is this?", "Whose", "Who", "Which", "What", "Whose");
        add(db, lessonIds, existing, 14, "______ do you like best — tea or coffee?", "Which", "What", "Who", "Whose", "Which");
        add(db, lessonIds, existing, 15, "I ______ my homework yet.", "don't finish", "didn't finish", "haven't finished", "am not finish", "haven't finished");
        add(db, lessonIds, existing, 15, "She ______ to Japan twice.", "has been", "was", "is", "been", "has been");
        add(db, lessonIds, existing, 15, "I ______ already that film.", "saw", "have seen", "see", "seen", "have seen");
        add(db, lessonIds, existing, 15, "Have you finished ______? – Not yet.", "already", "just", "yet", "now", "yet");
        add(db, lessonIds, existing, 15, "They ______ here for five years.", "live", "lived", "have lived", "lives", "have lived");
        add(db, lessonIds, existing, 15, "I ______ lost my keys. Can you help me find them?", "have", "am", "was", "did", "have");
        add(db, lessonIds, existing, 15, "Have you ever ______ a famous person?", "meet", "met", "meeted", "meeting", "met");
        add(db, lessonIds, existing, 15, "She hasn't called ______ this week.", "still", "yet", "already", "now", "yet");
        add(db, lessonIds, existing, 15, "I have ______ had breakfast.", "already", "yet", "never", "ever", "already");
        add(db, lessonIds, existing, 15, "______ you ever been to Ha Noi?", "Have", "Do", "Are", "Did", "Have");
        add(db, lessonIds, existing, 16, "I like tea ______ I don't like coffee.", "and", "but", "because", "so", "but");
        add(db, lessonIds, existing, 16, "It was raining, ______ I stayed at home.", "and", "but", "because", "so", "so");
        add(db, lessonIds, existing, 16, "I went to bed early ______ I was tired.", "and", "but", "because", "so", "because");
        add(db, lessonIds, existing, 16, "She can read ______ write English well.", "and", "but", "because", "so", "and");
        add(db, lessonIds, existing, 16, "This book is good ______ that one is better.", "and", "but", "because", "so", "but");
        add(db, lessonIds, existing, 16, "I need money, ______ I got a part-time job.", "and", "but", "because", "so", "so");
        add(db, lessonIds, existing, 16, "We walked slowly ______ the street was slippery.", "and", "but", "because", "so", "because");
        add(db, lessonIds, existing, 16, "He studied hard ______ passed the exam.", "and", "but", "because", "so", "and");
        add(db, lessonIds, existing, 16, "I have no car, ______ I take the bus.", "and", "but", "because", "so", "so");
        add(db, lessonIds, existing, 16, "______ it's late, we should go home now.", "Because", "So", "But", "And", "Because");
        add(db, lessonIds, existing, 17, "______ you speak more slowly, please?", "Can", "Should", "Must", "Need", "Can");
        add(db, lessonIds, existing, 17, "______ a nice day!", "It's", "Is", "That", "This", "It's");
        add(db, lessonIds, existing, 17, "Thank you very much. – ______.", "You're welcome", "Yes, please", "No, thank you", "Never mind", "You're welcome");
        add(db, lessonIds, existing, 17, "I'm sorry for being late. – ______.", "That's okay", "You're welcome", "It's true", "My pleasure", "That's okay");
        add(db, lessonIds, existing, 17, "Could I have some water? – ______.", "Here you are", "Give you", "There is", "You have", "Here you are");
        add(db, lessonIds, existing, 17, "______ you like to go out tonight?", "Would", "Can", "Should", "Must", "Would");
        add(db, lessonIds, existing, 17, "______ I take your order now?", "Can", "May", "Must", "Should", "May");
        add(db, lessonIds, existing, 17, "How do you do? – ______.", "How do you do", "Fine, thanks", "Very well", "Thank you", "How do you do");
        add(db, lessonIds, existing, 17, "See you tomorrow! – ______.", "See you", "Goodbye you", "Meet you", "Thanks", "See you");
        add(db, lessonIds, existing, 17, "______ there anything else you need?", "Is", "Are", "Do", "Have", "Is");
    }

    private static Map<Integer, Integer> buildLessonIds(AppDatabase db) {
        Map<Integer, Integer> ids = new HashMap<>();
        List<LessonEntity> lessons = db.lessonDao().getAllLessons();
        for (int day = 1; day <= 17; day++) {
            String label = "Ngày " + day + ":";
            for (LessonEntity lesson : lessons) {
                if (lesson.title != null && lesson.title.contains(label)) {
                    ids.put(day, lesson.id);
                    break;
                }
            }
        }
        return ids;
    }

    private static Map<Integer, Set<String>> buildQuestionIndex(
            AppDatabase db, Map<Integer, Integer> lessonIds) {
        Map<Integer, Set<String>> index = new HashMap<>();
        for (Integer lessonId : lessonIds.values()) {
            Set<String> questions = new HashSet<>();
            for (GrammarQuizEntity quiz : db.grammarQuizDao().getQuizByLessonId(lessonId)) {
                questions.add(normalize(quiz.question));
            }
            index.put(lessonId, questions);
        }
        return index;
    }

    private static void add(AppDatabase db, Map<Integer, Integer> lessonIds,
            Map<Integer, Set<String>> existing, int sourceLesson, String question,
            String a, String b, String c, String d, String correct) {
        // Bài 2 đã được bổ sung bằng bộ 25 câu chi tiết ở seed riêng.
        if (sourceLesson == 2) return;

        int targetDay = mapTargetDay(sourceLesson, question);
        Integer lessonId = lessonIds.get(targetDay);
        if (lessonId == null) return;

        String key = normalize(question);
        Set<String> lessonQuestions = existing.get(lessonId);
        if (lessonQuestions != null && lessonQuestions.add(key)) {
            db.grammarQuizDao().insertQuiz(
                    new GrammarQuizEntity(lessonId, question, a, b, c, d, correct));
        }
    }

    private static int mapTargetDay(int sourceLesson, String question) {
        switch (sourceLesson) {
            case 1:  return 1;   // To be, giới thiệu
            case 3:  return 8;   // Hiện tại đơn tổng hợp
            case 4:  return 8;   // Trạng từ tần suất trong hiện tại đơn
            case 5:  return 5;   // Động từ thường ở hiện tại
            case 6:  return 10;  // Hiện tại tiếp diễn
            case 7:
                return question.startsWith("Did ")
                        || question.startsWith("What ")
                        || question.contains(" not ") ? 13 : 12;
            case 8:  return 9;   // Danh từ đếm được và lượng từ
            case 9:  return 16;  // Tương lai đơn
            case 10: return 4;   // Giới từ thời gian, nơi chốn
            case 11: return 2;   // Tính từ/đại từ sở hữu và chỉ định
            case 12: return 9;   // Tính từ và trạng từ so sánh
            case 13: return 7;   // Trợ động từ khuyết thiếu và câu hỏi
            case 14:
                if (question.contains("birthday")) return 4;
                if (question.startsWith("Did") || question.contains("yesterday")) return 13;
                if (question.startsWith("Is ") || question.startsWith("Are ")) return 2;
                return 7;
            case 15: return 15;  // Hiện tại hoàn thành
            case 16: return 9;   // Liên từ thuộc nhóm từ loại
            case 17: return 7;   // Mẫu câu hỏi/yêu cầu trong giao tiếp
            default: return sourceLesson;
        }
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT)
                .replaceAll("\\s+", " ");
    }
}

