import re

file_path = r"C:\Users\Admin\AndroidStudioProjects\LearnEnglish\app\src\main\java\com\hieunn\learnenglish\db\DatabaseSeeder.java"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Update getExpectedVocabCount
vocab_pattern = r"(case 15:\s*return 14;)"
vocab_replacement = r"\1\n            case 16:\n                return 20;\n            case 17:\n                return 8;"
content = re.sub(vocab_pattern, vocab_replacement, content)

# Update getExpectedQuizCount
quiz_pattern = r"(case 15:\s*return 25;)"
quiz_replacement = r"\1\n            case 16:\n                return 39;\n            case 17:\n                return 34;"
content = re.sub(quiz_pattern, quiz_replacement, content)

# Update ensureLessonContent switch statement
switch_pattern = r"(case 15:\s*seedDay15Content\(db, lesson\.id\);\s*break;)"
switch_replacement = r"\1\n            case 16:\n                seedDay16Content(db, lesson.id);\n                break;\n            case 17:\n                seedDay17Content(db, lesson.id);\n                break;"
content = re.sub(switch_pattern, switch_replacement, content)

# Add new methods at the end
new_methods = """
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
}
"""

content = content.replace("}\n}", "}\n" + new_methods)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
