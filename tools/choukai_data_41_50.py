# -*- coding: utf-8 -*-
from choukai_helper import make_task

LESSONS_41_50 = []

# ==================== LESSON 41 ====================
l41_tasks = [
    make_task(
        "II_CD2", 28, 1, "1. 〜をいただきます/くださいます/やります", "1. Kính ngữ cho - nhận vật phẩm",
        "1. 〜をいただきます/くださいます/やります\n1) A: 先生に本をいただきました。\n2) A: 祖母にお小遣いをいただきました。\n3) A: 孫におもちゃをやりました。",
        "1. Kính ngữ cho - nhận vật phẩm\n1) A: Tôi được thầy giáo tặng cho cuốn sách quý.\n2) A: Tôi được bà ngoại cho tiền tiêu vặt.\n3) A: Tôi mua tặng cho đứa cháu món đồ chơi yêu thích.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Sách của thầy", "options": ["先生 ③ b (Nhận từ thầy)", "Tự mua"], "correctAnswer": "先生 ③ b (Nhận từ thầy)", "explanation": "Được thầy giáo tặng (先生 ③ b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Tiền tiêu vặt của bà", "options": ["祖母 ⑤ b (Nhận từ bà)", "Bố cho"], "correctAnswer": "祖母 ⑤ b (Nhận từ bà)", "explanation": "Bà ngoại cho (祖母 ⑤ b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đồ chơi cho cháu", "options": ["孫 ② a (Tặng cho cháu nhỏ)", "Cho bạn"], "correctAnswer": "孫 ② a (Tặng cho cháu nhỏ)", "explanation": "Cho cháu nội/ngoại (孫 ② a)."}
        ]
    ),
    make_task(
        "II_CD2", 29, 2, "2. 〜てくださいます", "2. Người trên làm giúp cho mình",
        "2. 〜てくださいます\n1) A: 部長が駅まで車で送ってくださいました。\n2) A: 社長がご馳走してくださいました。",
        "2. Người trên làm giúp cho mình\n1) A: Đích thân trưởng phòng đã lái xe chở tôi ra tận ga.\n2) A: Giám đốc đã chiêu đãi chúng tôi một bữa tiệc thịnh soạn.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Trưởng phòng đưa xe", "options": ["③ (Trưởng phòng đưa)", "①"], "correctAnswer": "③ (Trưởng phòng đưa)", "explanation": "Trưởng phòng chở giúp (③)."},
            {"itemIndex": "2", "prompt": "Câu 2: Giám đốc chiêu đãi", "options": ["① ② (Giám đốc đãi)", "③"], "correctAnswer": "① ② (Giám đốc đãi)", "explanation": "Giám đốc khao bữa ăn (① ②)."}
        ]
    ),
    make_task(
        "II_CD2", 30, 3, "3. 〜てやります", "3. Làm cho cấp dưới, con cháu, thú cưng",
        "3. 〜てやります\n1) A: 犬に餌をやりました。\n2) A: 花に水をやりました。\n3) A: 息子に自転車を買ってやりました。",
        "3. Làm cho cấp dưới, con cháu, thú cưng\n1) A: Tôi cho cún cưng ăn bữa trưa.\n2) A: Tôi tưới nước cho mấy chậu hoa ngoài ban công.\n3) A: Tôi mua chiếc xe đạp mới cho cậu con trai.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Cho chó ăn", "options": ["b (Cho cún ăn)", "a"], "correctAnswer": "b (Cho cún ăn)", "explanation": "Cho chó ăn (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Tưới hoa", "options": ["a (Tưới nước cho hoa)", "b"], "correctAnswer": "a (Tưới nước cho hoa)", "explanation": "Tưới hoa (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Mua xe cho con", "options": ["b (Mua xe cho con)", "a"], "correctAnswer": "b (Mua xe cho con)", "explanation": "Mua xe cho con trai (b)."}
        ]
    ),
    make_task(
        "II_CD2", 31, 4, "4. 〜てくださいませんか", "4. Xin làm ơn giúp tôi... (Nhờ vả rất lịch sự)",
        "4. 〜てくださいませんか\n1) A: コピー機の使い方を教えてくださいませんか。\n2) A: 日本語の手紙を直してくださいませんか。\n3) A: もう一度説明してくださいませんか。",
        "4. Xin làm ơn giúp tôi... (Nhờ vả rất lịch sự)\n1) A: Làm ơn hướng dẫn giúp tôi cách sử dụng chiếc máy photocopy này với được không ạ?\n2) A: Thầy có thể vui lòng sửa giúp em lá thư tiếng Nhật này được không ạ?\n3) A: Bạn làm ơn giải thích lại chỗ này một lần nữa giúp tôi được không ạ?",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Cách dùng máy photo", "options": ["f (Chỉ cách dùng máy)", "a"], "correctAnswer": "f (Chỉ cách dùng máy)", "explanation": "Nhờ chỉ cách dùng máy (f)."},
            {"itemIndex": "2", "prompt": "Câu 2: Sửa thư", "options": ["c (Nhờ sửa thư)", "b"], "correctAnswer": "c (Nhờ sửa thư)", "explanation": "Nhờ sửa thư tiếng Nhật (c)."},
            {"itemIndex": "3", "prompt": "Câu 3: Giải thích lại", "options": ["d (Nhờ giải thích lại)", "a"], "correctAnswer": "d (Nhờ giải thích lại)", "explanation": "Nhờ giảng lại (d)."}
        ]
    )
]
LESSONS_41_50.append({
    "lessonNumber": 41, "level": "N4",
    "title": "Bài 41: Kính ngữ cho - nhận (いただきます/くださいます/やります)",
    "subtitle": "第41課: いただきます・くださいます・やります・てくださいませんか",
    "tasks": l41_tasks
})

# ==================== LESSON 42 ====================
l42_tasks = [
    make_task(
        "II_CD2", 32, 1, "1. 〜(の)ために、〜", "1. Vì mục đích gì / Cho ai...",
        "1. 〜(の)ために、〜\n1) A: 家を買うために、一生懸命貯金しています。\n2) A: 健康のために、毎朝野菜ジュースを飲んでいます。\n3) A: 留学のために、英語を勉強しています。",
        "1. Vì mục đích gì / Cho ai...\n1) A: Để mua được căn nhà ấm cúng, tôi đang nỗ lực hết mình tiết kiệm tiền.\n2) A: Vì sức khỏe, sáng nào tôi cũng uống một ly nước ép rau củ.\n3) A: Để chuẩn bị đi du học, tôi đang dốc sức học ngoại ngữ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Mua nhà", "options": ["d (Tiết kiệm mua nhà)", "a"], "correctAnswer": "d (Tiết kiệm mua nhà)", "explanation": "Tiết kiệm mua nhà (d)."},
            {"itemIndex": "2", "prompt": "Câu 2: Sức khỏe", "options": ["e (Uống nước rau vì sức khỏe)", "b"], "correctAnswer": "e (Uống nước rau vì sức khỏe)", "explanation": "Uống nước rau củ vì sức khỏe (e)."},
            {"itemIndex": "3", "prompt": "Câu 3: Du học", "options": ["b (Học ngoại ngữ du học)", "c"], "correctAnswer": "b (Học ngoại ngữ du học)", "explanation": "Học tiếng để du học (b)."}
        ]
    ),
    make_task(
        "II_CD2", 33, 2, "2. 〜(の)に、〜", "2. Dùng cho mục đích / việc gì",
        "2. 〜(の)に、〜\n1) A: このハサミは花を切るのに使います。\n2) A: この辞書は漢字を調べるのにとても便利です。\n3) A: このカバンは旅行に行くのにちょうどいい大きさです。",
        "2. Dùng cho mục đích / việc gì\n1) A: Chiếc kéo này chuyên dùng để cắt tỉa cành hoa cắm bình.\n2) A: Cuốn từ điển này cực kỳ tiện lợi cho việc tra cứu chữ Hán.\n3) A: Chiếc ba lô này có kích cỡ vừa vặn hoàn hảo cho các chuyến du lịch ngắn ngày.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Kéo tỉa hoa", "options": ["b (Cắt hoa)", "a"], "correctAnswer": "b (Cắt hoa)", "explanation": "Dùng cắt hoa (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Từ điển tra Hán tự", "options": ["a (Tra cứu chữ Hán)", "b"], "correctAnswer": "a (Tra cứu chữ Hán)", "explanation": "Dùng tra chữ Hán (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Ba lô du lịch", "options": ["a (Đi du lịch)", "b"], "correctAnswer": "a (Đi du lịch)", "explanation": "Vừa vặn đi du lịch (a)."}
        ]
    ),
    make_task(
        "II_CD2", 34, 3, "3. 〜(の)に〜(は/も)かかります", "3. Mất bao lâu / tốn bao nhiêu chi phí cho việc gì",
        "3. 〜(の)に〜(は/も)かかります\n1) A: 家を建てるのに、どのくらいかかりますか。\nB: 100日、200日、300日、全部で1,100万円かかります。\n2) A: 修理するのにどのくらいかかりますか。\nB: 30日、100日かかります。",
        "3. Mất bao lâu / tốn bao nhiêu chi phí cho việc gì\n1) A: Xây cất ngôi nhà này tốn bao nhiêu chi phí và thời gian?\nB: Từng giai đoạn 100, 200, 300 ngày, tổng cộng hết 1.100 vạn yên.\n2) A: Sửa chữa thiết bị này mất khoảng bao lâu?\nB: Mất khoảng 30 ngày đến 100 ngày.",
        [
            {"itemIndex": "1", "prompt": "Thời gian & chi phí xây nhà", "options": ["100 200 300 200 1,100", "500 1,000"], "correctAnswer": "100 200 300 200 1,100", "explanation": "Các con số thời gian và chi phí."},
            {"itemIndex": "2", "prompt": "Sửa chữa", "options": ["0 50 20 0 0 30 100", "10 20"], "correctAnswer": "0 50 20 0 0 30 100", "explanation": "Thống kê ngày sửa chữa."}
        ]
    )
]
LESSONS_41_50.append({
    "lessonNumber": 42, "level": "N4",
    "title": "Bài 42: Mục đích vì/cho (〜ために/〜のに) & Chi phí/Thời gian",
    "subtitle": "第42課: ために・のに使う・のに時間がかかる",
    "tasks": l42_tasks
})

# ==================== LESSON 43 (Book 2 CD3) ====================
l43_tasks = [
    make_task(
        "II_CD3", 1, 1, "1. (今にも)〜そうです", "1. Có vẻ sắp xảy ra ngay bây giờ",
        "1. (今にも)〜そうです\n1) A: 今にも雨が降りそうですね。\nB: ええ、傘を持っていきましょう。\n2) A: ボタンが取れそうですよ。\nB: あ、本当ですね。ありがとうございます。\n3) A: 荷物が落ちそうですよ。\nB: わっ、危ない！ありがとうございます。",
        "1. Có vẻ sắp xảy ra ngay bây giờ\n1) A: Trời trông có vẻ như sắp đổ mưa to đến nơi rồi nhỉ.\nB: Vâng, chúng ta mang theo ô đi nhé.\n2) A: Cúc áo của bạn trông sắp đứt tuột ra kìa.\nB: Ôi đúng thật, may quá cảm ơn bạn nhé.\n3) A: Chồng hành lý của bạn có vẻ sắp rơi tuột xuống đất kìa.\nB: Ái chà, nguy hiểm quá! Cảm ơn bạn nhiều.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Trời sắp mưa", "options": ["a (Sắp mưa)", "b"], "correctAnswer": "a (Sắp mưa)", "explanation": "Trời sắp mưa (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Cúc áo sắp tuột", "options": ["c (Cúc áo sắp tuột)", "a"], "correctAnswer": "c (Cúc áo sắp tuột)", "explanation": "Cúc sắp tuột (c)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đồ đạc sắp rơi", "options": ["a (Hành lý sắp rơi)", "b"], "correctAnswer": "a (Hành lý sắp rơi)", "explanation": "Hành lý sắp rơi (a)."}
        ]
    ),
    make_task(
        "II_CD3", 2, 2, "2. (これから)〜そうです", "2. Có vẻ như sắp tới sẽ...",
        "2. (これから)〜そうです\n1) A: これから寒くなりそうですね。\n2) A: 景気が良くなりそうですね。\n3) A: 物価が上がりそうですね。",
        "2. Có vẻ như sắp tới sẽ...\n1) A: Từ đây trở đi thời tiết có vẻ sẽ rét đậm hơn nhiều đấy.\n2) A: Tình hình kinh tế thị trường có vẻ sắp khởi sắc tích cực hơn rồi.\n3) A: Vật giá có vẻ như sắp leo thang tăng giá.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Thời tiết lạnh", "options": ["c (Sắp lạnh đi)", "a"], "correctAnswer": "c (Sắp lạnh đi)", "explanation": "Sắp lạnh đi (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Kinh tế", "options": ["a (Kinh tế tốt lên)", "b"], "correctAnswer": "a (Kinh tế tốt lên)", "explanation": "Kinh tế tốt lên (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Vật giá", "options": ["b (Giá cả tăng)", "c"], "correctAnswer": "b (Giá cả tăng)", "explanation": "Vật giá tăng (b)."}
        ]
    ),
    make_task(
        "II_CD3", 3, 3, "3. [形容詞]そうです", "3. Trông có vẻ ngon / vui / ấm áp...",
        "3. [形容詞]そうです\n1) A: このケーキ、とても美味しそうですね。\n2) A: あの映画、おもしろそうですね。\n3) A: このコート、軽くて温かそうですね。",
        "3. Trông có vẻ ngon / vui / ấm áp...\n1) A: Chiếc bánh kem này trông có vẻ ngon mắt quá đi mất.\n2) A: Bộ phim đó trông có vẻ kịch tính hấp dẫn đấy.\n3) A: Chiếc áo khoác này trông vừa nhẹ vừa ấm áp.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Chiếc bánh kem", "options": ["c (Trông có vẻ ngon)", "a"], "correctAnswer": "c (Trông có vẻ ngon)", "explanation": "Trông ngon lành (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Bộ phim", "options": ["a (Trông có vẻ hay)", "b"], "correctAnswer": "a (Trông có vẻ hay)", "explanation": "Trông có vẻ hay (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Áo khoác", "options": ["b (Trông ấm áp)", "a"], "correctAnswer": "b (Trông ấm áp)", "explanation": "Trông ấm áp (b)."}
        ]
    ),
    make_task(
        "II_CD3", 4, 4, "4. 〜て来ます", "4. Đi làm việc gì đó rồi quay lại ngay",
        "4. 〜て来ます\n1) A: ちょっと切符を買って来ます。\n2) A: ジュースを買って来ます。\n3) A: 手を洗って来ます。",
        "4. Đi làm việc gì đó rồi quay lại ngay\n1) A: Tôi chạy đi mua vé một lát rồi quay lại ngay đây.\n2) A: Tôi chạy đi mua lon nước hoa quả một chút rồi quay lại.\n3) A: Tôi vào nhà vệ sinh rửa tay một lát rồi quay ra ngay.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đi mua vé", "options": ["d/③ c (Mua vé rồi về)", "a"], "correctAnswer": "d/③ c (Mua vé rồi về)", "explanation": "Mua vé rồi quay lại (d/③ c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Mua nước", "options": ["e/④ c (Mua nước rồi về)", "b"], "correctAnswer": "e/④ c (Mua nước rồi về)", "explanation": "Mua nước rồi lại (e/④ c)."},
            {"itemIndex": "3", "prompt": "Câu 3: Rửa tay", "options": ["b/⑥ (Rửa tay rồi về)", "a"], "correctAnswer": "b/⑥ (Rửa tay rồi về)", "explanation": "Rửa tay rồi vào (b/⑥)."}
        ]
    )
]
LESSONS_41_50.append({
    "lessonNumber": 43, "level": "N4",
    "title": "Bài 43: Có vẻ sắp... (〜そうです) & Đi rồi về (〜て来ます)",
    "subtitle": "第43課: そうです・て来ます・直前の様子",
    "tasks": l43_tasks
})

# ==================== LESSON 44 ====================
l44_tasks = [
    make_task(
        "II_CD3", 5, 1, "1. [動詞]すぎます", "1. Làm việc gì đó quá mức (~すぎます)",
        "1. [動詞]すぎます\n1) A: 夕べお酒を飲みすぎました。\n2) A: 食べすぎて、お腹が痛いです。\n3) A: 働きすぎて、倒れてしまいました。",
        "1. Làm việc gì đó quá mức (~すぎます)\n1) A: Tối qua tôi lỡ chén uống rượu bia quá chén say mèm.\n2) A: Ăn nhiều quá độ nên giờ bụng tôi đau quặn thắt.\n3) A: Làm việc kiệt sức quá độ nên bị ngất xỉu ngã quỵ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Uống quá chén", "options": ["d ① (Uống rượu quá mức)", "a"], "correctAnswer": "d ① (Uống rượu quá mức)", "explanation": "Uống quá nhiều (d ①)."},
            {"itemIndex": "2", "prompt": "Câu 2: Ăn quá no", "options": ["a ② (Ăn quá nhiều đau bụng)", "b"], "correctAnswer": "a ② (Ăn quá nhiều đau bụng)", "explanation": "Ăn quá độ (a ②)."},
            {"itemIndex": "3", "prompt": "Câu 3: Làm việc quá sức", "options": ["e ① (Làm việc quá sức)", "c"], "correctAnswer": "e ① (Làm việc quá sức)", "explanation": "Làm việc quá tải (e ①)."}
        ]
    ),
    make_task(
        "II_CD3", 6, 2, "2. [形容詞]すぎます", "2. Tính từ quá mức (Cay quá, đắt quá...)",
        "2. [形容詞]すぎます\n1) A: このカレーは辛すぎます。\n2) A: この部屋は狭すぎます。\n3) A: 値段が高すぎます。\n4) A: 問題が難しすぎます。\n5) A: 音楽の音が大きすぎます。",
        "2. Tính từ quá mức (Cay quá, đắt quá...)\n1) A: Đĩa cà ri này cay xé lưỡi, cay quá mức.\n2) A: Căn phòng này chật chội quá mức.\n3) A: Mức giá này đắt đỏ quá mức chịu đựng.\n4) A: Đề bài kiểm tra này hóc búa khó quá mức.\n5) A: Tiếng nhạc bật to quá đinh tai nhức óc.",
        [
            {"itemIndex": "1", "prompt": "Đánh giá các hiện tượng", "options": ["(1) × (2) × (3) × (4) ○ (5) ○", "○ ○ ○ × ×"], "correctAnswer": "(1) × (2) × (3) × (4) ○ (5) ○", "explanation": "Đánh giá mức độ quá tải."}
        ]
    ),
    make_task(
        "II_CD3", 7, 3, "3. 〜やすい/にくいです", "3. Dễ làm / Khó làm việc gì",
        "3. 〜やすい/にくいです\n1) A: この歌は歌いやすいですね。\n2) A: この靴は走りにくいです。\n3) A: この車は乗りやすいです。",
        "3. Dễ làm / Khó làm việc gì\n1) A: Bài hát này giai điệu êm ái, rất dễ hát theo.\n2) A: Đôi giày này gót cao, khó chạy bộ quá.\n3) A: Chiếc xe ô tô này ghế êm, ngồi đi đường dài rất dễ chịu.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Bài hát", "options": ["b 歌い やすい (Dễ hát)", "Khó hát"], "correctAnswer": "b 歌い やすい (Dễ hát)", "explanation": "Dễ hát theo (歌いやすい)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đôi giày", "options": ["d 走り にくい (Khó chạy)", "Dễ chạy"], "correctAnswer": "d 走り にくい (Khó chạy)", "explanation": "Khó chạy bộ (走りにくい)."},
            {"itemIndex": "3", "prompt": "Câu 3: Chiếc xe", "options": ["a 乗り やすい (Dễ đi)", "Khó đi"], "correctAnswer": "a 乗り やすい (Dễ đi)", "explanation": "Dễ đi dễ lái (乗りやすい)."}
        ]
    ),
    make_task(
        "II_CD3", 8, 4, "4. 〜やすい/にくいです (Thiết bị)", "4. Thiết bị đồ dùng dễ/khó dùng",
        "4. 〜やすい/にくいです\n1) A: この携帯電話は使いやすいです。\n2) A: この薬は苦くて飲みにくいです。\n3) A: この字は小さくて読みにくいです。",
        "4. Thiết bị đồ dùng dễ/khó dùng\n1) A: Chiếc điện thoại này giao diện trực quan, rất dễ sử dụng.\n2) A: Thuốc này đắng nghét, khó nuốt quá.\n3) A: Nét chữ này nhỏ li ti, khó đọc quá.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Điện thoại", "options": ["c (Dễ sử dụng)", "a"], "correctAnswer": "c (Dễ sử dụng)", "explanation": "Dễ sử dụng (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Thuốc đắng", "options": ["e (Khó uống)", "b"], "correctAnswer": "e (Khó uống)", "explanation": "Khó uống (e)."},
            {"itemIndex": "3", "prompt": "Câu 3: Chữ nhỏ", "options": ["a (Khó đọc)", "c"], "correctAnswer": "a (Khó đọc)", "explanation": "Khó đọc (a)."}
        ]
    ),
    make_task(
        "II_CD3", 9, 5, "5. 〜く/〜にします", "5. Làm cho... biến đổi (Chỉnh âm thanh, độ dài...)",
        "5. 〜く/〜にします\n1) A: 音を小さくしてください。\nB: はい、小さくします。\n2) A: 髪を短くしてください。\nB: はい、わかりました。",
        "5. Làm cho... biến đổi (Chỉnh âm thanh, độ dài...)\n1) A: Làm ơn chỉnh âm lượng nhỏ bớt lại với ạ.\nB: Vâng, tôi vặn nhỏ ngay.\n2) A: Cắt ngắn tóc cho em với ạ.\nB: Vâng, anh sẽ cắt tỉa ngắn gàng.",
        [
            {"itemIndex": "1", "prompt": "Điều chỉnh", "options": ["b (Chỉnh nhỏ bớt)", "a"], "correctAnswer": "b (Chỉnh nhỏ bớt)", "explanation": "Chỉnh nhỏ lại (b)."}
        ]
    ),
    make_task(
        "II_CD3", 10, 6, "6. [名詞]にします", "6. Quyết định chọn cái gì",
        "6. [名詞]にします\n1) A: ご注文は何にしますか。\nB: わたしはコーヒーにします。\n2) A: 電車で行きますか、バスにしますか。\nB: 電車にします。動物園へ行ってお弁当を食べます。ビデオカメラも持って行きます。",
        "6. Quyết định chọn cái gì\n1) A: Quý khách quyết định chọn dùng món gì ạ?\nB: Cho tôi một tách cà phê.\n2) A: Bạn đi bằng tàu hay chọn đi xe buýt?\nB: Tôi chọn đi tàu điện. Chúng ta sẽ đến sở thú ngắm thú, ăn cơm hộp bento và mang theo máy quay phim ghi hình.",
        [
            {"itemIndex": "1", "prompt": "Lựa chọn phương tiện & hoạt động", "options": ["電車 動物園 お弁当 ビデオカメラ", "Xe buýt"], "correctAnswer": "電車 動物園 お弁当 ビデオカメラ", "explanation": "Chọn tàu, sở thú, cơm bento, máy quay phim."}
        ]
    )
]
LESSONS_41_50.append({
    "lessonNumber": 44, "level": "N4",
    "title": "Bài 44: Quá mức (すぎます), Dễ/Khó (やすい/にくい) & Quyết định chọn (にします)",
    "subtitle": "第44課: すぎる・やすい・にくい・〜にします",
    "tasks": l44_tasks
})

# ==================== LESSON 45 ====================
l45_tasks = [
    make_task(
        "II_CD3", 11, 1, "1. [動詞]場合は、〜", "1. Trong trường hợp động từ xảy ra...",
        "1. [動詞]場合は、〜\n1) A: エレベーターが止まった場合は、乗らないで警察を呼んでください。\n2) A: 台風が来た場合は、窓を閉めておいて自転車を片付けてください。\n3) A: 火事の場合は、非常口から逃げてやけどに気をつけてください。",
        "1. Trong trường hợp động từ xảy ra...\n1) A: Trường hợp thang máy bị kẹt dừng đột ngột, không được bước vào mà gọi ngay cứu hộ.\n2) A: Trường hợp có bão lớn đổ bộ, hãy đóng chặt cửa sổ và cất dọn xe đạp vào nhà.\n3) A: Trường hợp có hỏa hoạn cháy nổ, thoát hiểm bằng cửa thoát hiểm và tránh bị bỏng.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Thang máy hỏng", "options": ["①乗らない ②警察/救助を呼ぶ", "Đi tiếp"], "correctAnswer": "①乗らない ②警察/救助を呼ぶ", "explanation": "Không vào và gọi cứu hộ."},
            {"itemIndex": "2", "prompt": "Câu 2: Bão đổ bộ", "options": ["①台風 ②閉めておく ③自転車", "Mở cửa"], "correctAnswer": "①台風 ②閉めておく ③自転車", "explanation": "Đóng cửa cất xe khi bão."},
            {"itemIndex": "3", "prompt": "Câu 3: Cháy", "options": ["①火事 ②非常口 ③やけど", "Ở yên"], "correctAnswer": "①火事 ②非常口 ③やけど", "explanation": "Cháy thì ra cửa thoát hiểm tránh bỏng."}
        ]
    ),
    make_task(
        "II_CD3", 12, 2, "2. [形容詞]場合は、〜", "2. Trong trường hợp tính từ (Tình trạng sức khỏe...)",
        "2. [形容詞]場合は、〜\n1) A: 咳がひどい場合は、無理をしないで病院へ行ってください。\n2) A: 調子がよくならない場合は、薬を変えてみてください。\n3) A: 様子が変な場合は、すぐに知らせてください。",
        "2. Trong trường hợp tính từ (Tình trạng sức khỏe...)\n1) A: Trường hợp cơn ho trở nên dữ dội nghiêm trọng, đừng cố làm việc mà hãy đi khám ngay.\n2) A: Trường hợp sức khỏe không thấy thuyên giảm, hãy thử đổi sang loại thuốc khác.\n3) A: Trường hợp nhận thấy có biểu hiện kỳ lạ bất thường, hãy báo tin ngay lập tức.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ho nặng", "options": ["せきがひどい e (Ho nặng)", "Sốt nhẹ"], "correctAnswer": "せきがひどい e (Ho nặng)", "explanation": "Ho nặng thì đi khám."},
            {"itemIndex": "2", "prompt": "Câu 2: Không đỡ", "options": ["よくならない b (Không đỡ bệnh)", "Khỏi hẳn"], "correctAnswer": "よくならない b (Không đỡ bệnh)", "explanation": "Không thuyên giảm bệnh."},
            {"itemIndex": "3", "prompt": "Câu 3: Bất thường", "options": ["様子が変な d (Bất thường)", "Bình thường"], "correctAnswer": "様子が変な d (Bất thường)", "explanation": "Biểu hiện bất thường."}
        ]
    ),
    make_task(
        "II_CD3", 13, 3, "3. 〜のに、〜", "3. Mặc dù... thế mà... (Bất mãn, trái ngược)",
        "3. 〜のに、〜\n1) A: 約束したのに、彼は来ませんでした。\n2) A: 一生懸命勉強したのに、不合格でした。\n3) A: 日曜日なのに、仕事に行かなければなりません。",
        "3. Mặc dù... thế mà... (Bất mãn, trái ngược)\n1) A: Đã hẹn rõ ràng thế rồi mà cậu ấy lại không thèm đến.\n2) A: Tôi đã học ngày học đêm chăm chỉ thế mà kết quả vẫn bị trượt.\n3) A: Rõ ràng là ngày Chủ Nhật thế mà vẫn phải nai lưng đi làm.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Hẹn mà không đến", "options": ["d ⑤ (Hẹn mà không đến)", "a"], "correctAnswer": "d ⑤ (Hẹn mà không đến)", "explanation": "Hẹn rồi mà không tới (d ⑤)."},
            {"itemIndex": "2", "prompt": "Câu 2: Học mà trượt", "options": ["e ② (Học chăm mà trượt)", "b"], "correctAnswer": "e ② (Học chăm mà trượt)", "explanation": "Học chăm mà trượt thi (e ②)."},
            {"itemIndex": "3", "prompt": "Câu 3: Chủ Nhật đi làm", "options": ["b ④ (Chủ Nhật phải đi làm)", "c"], "correctAnswer": "b ④ (Chủ Nhật phải đi làm)", "explanation": "Chủ Nhật phải làm (b ④)."}
        ]
    ),
    make_task(
        "II_CD3", 14, 4, "4. 〜のに、〜 (Bất công / Nghịch lý)", "4. Mặc dù cùng hoàn cảnh thế mà...",
        "4. 〜のに、〜\n1) A: 同じ仕事をしているのに、給料が違います。\n2) A: 残業したのに、残業手当が出ませんでした。\n3) A: 早く来たのに、一番前の席に座れませんでした。",
        "4. Mặc dù cùng hoàn cảnh thế mà...\n1) A: Làm cùng một khối lượng công việc như nhau thế mà lương bổng lại chênh lệch.\n2) A: Cày cuốc tăng ca cật lực thế mà chẳng được nhận tiền phụ cấp tăng ca.\n3) A: Đến rõ là sớm thế mà chẳng xí được hàng ghế đầu tiên.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Cùng việc khác lương", "options": ["同じ仕事をしている e", "Khác việc"], "correctAnswer": "削除なし", "correctAnswer": "同じ仕事をしている e", "explanation": "Cùng làm việc mà khác lương."},
            {"itemIndex": "2", "prompt": "Câu 2: Tăng ca không tiền", "options": ["残業した c", "Về sớm"], "correctAnswer": "残業した c", "explanation": "Tăng ca không trợ cấp."},
            {"itemIndex": "3", "prompt": "Câu 3: Đến sớm không ghế đầu", "options": ["早く来た b", "Đến muộn"], "correctAnswer": "早く来た b", "explanation": "Đến sớm mà không được ghế đầu."}
        ]
    )
]
LESSONS_41_50.append({
    "lessonNumber": 45, "level": "N4",
    "title": "Bài 45: Trong trường hợp (〜場合) & Mặc dù thế mà (〜のに)",
    "subtitle": "第45課: 場合・のに・逆接不満",
    "tasks": l45_tasks
})

# ==================== LESSON 46 ====================
l46_tasks = [
    make_task(
        "II_CD3", 15, 1, "1. 〜ところです", "1. Đúng vào lúc... (Sắp sửa / Đang / Vừa mới xong)",
        "1. 〜ところです\n1) A: 今から会議が始まるところです。\n2) A: 今昼ごはんを食べているところです。\n3) A: たった今電車が着いたところです。",
        "1. Đúng vào lúc... (Sắp sửa / Đang / Vừa mới xong)\n1) A: Đúng lúc này cuộc họp chuẩn bị bắt đầu.\n2) A: Tôi đang đúng vào lúc đang ăn cơm trưa.\n3) A: Chuyến tàu điện vừa mới cập bến ngay tức thì.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Chuẩn bị họp", "options": ["c (Sắp sửa bắt đầu)", "a"], "correctAnswer": "c (Sắp sửa bắt đầu)", "explanation": "Sắp sửa bắt đầu họp (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đang ăn", "options": ["a (Đúng lúc đang ăn trưa)", "b"], "correctAnswer": "a (Đúng lúc đang ăn trưa)", "explanation": "Đang giữa bữa ăn (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Tàu vừa tới", "options": ["c (Vừa mới cập bến)", "b"], "correctAnswer": "c (Vừa mới cập bến)", "explanation": "Tàu vừa mới tới (c)."}
        ]
    ),
    make_task(
        "II_CD3", 16, 2, "2. 〜ところです (Hành động)", "2. Đúng lúc sắp sửa làm gì",
        "2. 〜ところです\n1) A: 今から出かけるところです。\n2) A: 資料を読んでいるところです。\n3) A: 電話を切ったところです。",
        "2. Đúng lúc sắp sửa làm gì\n1) A: Tôi đang chuẩn bị xỏ giày bước chân ra khỏi nhà đây.\n2) A: Tôi đang dán mắt vào đọc tài liệu cuộc họp.\n3) A: Tôi vừa mới cúp máy cuộc gọi xong tức thì.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Sắp ra ngoài", "options": ["b (Sắp bước ra ngoài)", "a"], "correctAnswer": "b (Sắp bước ra ngoài)", "explanation": "Sắp sửa ra ngoài (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đang đọc", "options": ["a (Đang chăm chú đọc)", "b"], "correctAnswer": "a (Đang chăm chú đọc)", "explanation": "Đang đọc tài liệu (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Vừa cúp máy", "options": ["c (Vừa mới cúp máy)", "a"], "correctAnswer": "c (Vừa mới cúp máy)", "explanation": "Vừa cúp máy xong (c)."}
        ]
    ),
    make_task(
        "II_CD3", 17, 3, "3. 〜ばかりです", "3. Vừa mới làm xong (Cảm giác thời gian còn mới)",
        "3. 〜ばかりです\n1) A: 先月この会社に入ったばかりです。\n2) A: 買ったばかりの時計を失くしてしまいました。\n3) A: さっき起きたばかりです。",
        "3. Vừa mới làm xong (Cảm giác thời gian còn mới)\n1) A: Tôi mới chân ướt chân ráo vào công ty này từ tháng trước thôi.\n2) A: Chiếc đồng hồ vừa mới tinh mới mua hôm nào đã đánh rơi mất rồi.\n3) A: Tôi vừa mới chợp mắt dậy tức thì.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Vào công ty", "options": ["c (Mới vào tháng trước)", "a"], "correctAnswer": "c (Mới vào tháng trước)", "explanation": "Mới vào tháng trước (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đồng hồ mới mua", "options": ["a (Mới mua đã mất)", "b"], "correctAnswer": "a (Mới mua đã mất)", "explanation": "Vừa mới mua (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Vừa ngủ dậy", "options": ["b (Vừa mới thức dậy)", "a"], "correctAnswer": "b (Vừa mới thức dậy)", "explanation": "Vừa mới dậy (b)."}
        ]
    ),
    make_task(
        "II_CD3", 18, 4, "4. 〜はずです", "4. Chắc chắn là... (Căn cứ phỏng đoán chắc nịch)",
        "4. 〜はずです\n1) A: 田中さんは今日来るはずですよ。昨日そう言っていました。\n2) A: 鍵はカバンの中にあるはずです。入れたのを覚えています。\n3) A: 彼は英語が上手なはずです。アメリカに住んでいましたから。",
        "4. Chắc chắn là... (Căn cứ phỏng đoán chắc nịch)\n1) A: Anh Tanaka chắc chắn hôm nay sẽ tới, hôm qua anh ấy đã khẳng định thế.\n2) A: Chùm chìa khóa chắc chắn nằm trong cặp, tôi nhớ rõ là đã bỏ vào rồi.\n3) A: Anh ấy tiếng Anh chắc chắn là bắn như gió, từng sống ở Mỹ mấy năm mà.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Tanaka sẽ đến", "options": ["b (Chắc chắn sẽ đến)", "a"], "correctAnswer": "b (Chắc chắn sẽ đến)", "explanation": "Chắc chắn sẽ tới (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Chìa khóa trong túi", "options": ["a (Chắc chắn ở trong túi)", "b"], "correctAnswer": "a (Chắc chắn ở trong túi)", "explanation": "Chắc chắn trong túi (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Giỏi tiếng Anh", "options": ["a (Chắc chắn giỏi tiếng)", "b"], "correctAnswer": "a (Chắc chắn giỏi tiếng)", "explanation": "Chắc chắn giỏi tiếng Anh (a)."}
        ]
    )
]
LESSONS_41_50.append({
    "lessonNumber": 46, "level": "N4",
    "title": "Bài 46: Giai đoạn hành động (〜ところ/〜ばかり) & Chắc chắn (〜はず)",
    "subtitle": "第46課: ところ・ばかり・はずです",
    "tasks": l46_tasks
})

# ==================== LESSON 47 ====================
l47_tasks = [
    make_task(
        "II_CD3", 19, 1, "1. 〜そうです(書かれた情報を伝える)", "1. Nghe nói là... (Truyền đạt tin tức báo chí)",
        "1. 〜そうです(書かれた情報を伝える)\n1) A: 新聞によると、明日は全国的に大雨になるそうです。\n2) A: 天気予報によると、台風が近づいているそうです。",
        "1. Nghe nói là... (Truyền đạt tin tức báo chí)\n1) A: Theo báo chí đưa tin, ngày mai trên toàn quốc sẽ có mưa rất to.\n2) A: Theo bản tin dự báo thời tiết, cơn bão mạnh đang áp sát vào đất liền.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Tin tức báo chí", "options": ["d (Báo đưa tin mưa to)", "a"], "correctAnswer": "d (Báo đưa tin mưa to)", "explanation": "Theo tin báo chí (d)."},
            {"itemIndex": "2", "prompt": "Câu 2: Dự báo bão", "options": ["a (Bão đang tới)", "b"], "correctAnswer": "a (Bão đang tới)", "explanation": "Bão áp sát (a)."}
        ]
    ),
    make_task(
        "II_CD3", 20, 2, "2. 〜そうです(聞いた情報を伝える)", "2. Nghe đồn là... (Truyền đạt lại lời người khác)",
        "2. 〜そうです(聞いた情報を伝える)\n1) A: 山田さんの話によると、佐藤さんは来月結婚するそうです。\n2) A: 部長の話では、来期はボーナスがたくさん出るそうです。",
        "2. Nghe đồn là... (Truyền đạt lại lời người khác)\n1) A: Theo như anh Yamada kể, chị Satou tháng sau sẽ lên xe hoa kết hôn đấy.\n2) A: Theo lời sếp trưởng phòng bật mí, quý tới tiền thưởng thưởng tết sẽ rất hậu hĩnh.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Chị Satou cưới", "options": ["a (Nghe nói tháng sau cưới)", "b"], "correctAnswer": "a (Nghe nói tháng sau cưới)", "explanation": "Tháng sau kết hôn (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Thưởng tết", "options": ["b (Nghe nói thưởng nhiều)", "a"], "correctAnswer": "b (Nghe nói thưởng nhiều)", "explanation": "Thưởng nhiều (b)."}
        ]
    ),
    make_task(
        "II_CD3", 21, 3, "3. 〜そうです (Nguồn tin)", "3. Nghe nói từ nguồn tin",
        "3. 〜そうです\n1) A: インターネットのニュースによると、新しいスマートフォンが出るそうです。\n2) A: 噂によると、あの店は近々閉店するそうです。",
        "3. Nghe nói từ nguồn tin\n1) A: Theo tin tức trên mạng internet, chiếc điện thoại smartphone thế hệ mới sắp ra mắt.\n2) A: Theo lời đồn đại thì cửa tiệm đằng kia sắp sửa đóng cửa ngừng kinh doanh.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Tin smartphone", "options": ["インターネット 木 b b (Tin tức internet)", "Báo giấy"], "correctAnswer": "インターネット 木 b b (Tin tức internet)", "explanation": "Theo mạng internet."},
            {"itemIndex": "2", "prompt": "Câu 2: Báo giấy giá cả", "options": ["新聞 a 50万 (Tin tức báo giấy)", "Tạp chí"], "correctAnswer": "新聞 a 50万 (Tin tức báo giấy)", "explanation": "Theo báo giấy 50 vạn."},
            {"itemIndex": "3", "prompt": "Câu 3: Dữ liệu", "options": ["(ある)データ a b", "Không rõ"], "correctAnswer": "(ある)データ a b", "explanation": "Dữ liệu khảo sát."}
        ]
    ),
    make_task(
        "II_CD3", 22, 4, "4. 〜ようです", "4. Dường như là... / Có vẻ như... (Quan sát phán đoán)",
        "4. 〜ようです\n1) A: 外がとても騒がしいですね。事故があったようです。\n2) A: 彼はとても嬉しそうですね。いいことがあったようです。\n3) A: あの店は行列ができていますね。とても美味しいようです。",
        "4. Dường như là... / Có vẻ như... (Quan sát phán đoán)\n1) A: Ngoài đường ồn ào còi hú thế kia, dường như vừa có tai nạn xảy ra.\n2) A: Trông mặt anh ấy hớn hở tươi rói, hình như vừa gặp được chuyện đại hỷ.\n3) A: Quán kia người ta xếp hàng dài dằng dặc, dường như đồ ăn ở đó ngon nức tiếng.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Tai nạn", "options": ["c g (Hình như có tai nạn)", "a"], "correctAnswer": "c g (Hình như có tai nạn)", "explanation": "Dường như có tai nạn (c g)."},
            {"itemIndex": "2", "prompt": "Câu 2: Chuyện vui", "options": ["b h (Hình như có chuyện vui)", "a"], "correctAnswer": "b h (Hình như có chuyện vui)", "explanation": "Dường như gặp chuyện vui (b h)."},
            {"itemIndex": "3", "prompt": "Câu 3: Quán ngon", "options": ["d j ④ k f (Quán ngon đông khách)", "c"], "correctAnswer": "d j ④ k f (Quán ngon đông khách)", "explanation": "Quán ngon xếp hàng đông."}
        ]
    )
]
LESSONS_41_50.append({
    "lessonNumber": 47, "level": "N4",
    "title": "Bài 47: Nghe nói là (〜そうです) & Dường như (〜ようです)",
    "subtitle": "第47課: そうです・ようです・伝聞推量",
    "tasks": l47_tasks
})

# ==================== LESSON 48 ====================
l48_tasks = [
    make_task(
        "II_CD3", 23, 1, "1. (人)を/に〜(さ)せます[使役動詞]", "1. Thể sai khiến (Bắt / Cho phép làm việc gì)",
        "1. (人)を/に〜(さ)せます[使役動詞]\n1) A: 部長は山田さんを出張に行かせます。\n2) A: 母は子どもに野菜を食べさせます。\n3) A: 先生は学生に宿題をたくさんさせます。\n4) A: 父は弟に本を読ませます。",
        "1. Thể sai khiến (Bắt / Cho phép làm việc gì)\n1) A: Trưởng phòng cử anh Yamada đi công tác xa.\n2) A: Người mẹ bắt đứa con phải ăn rau xanh đầy đủ.\n3) A: Thầy giáo giao và bắt học sinh phải làm rất nhiều bài tập về nhà.\n4) A: Bố bắt cậu em trai ngồi vào bàn đọc sách.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đi công tác", "options": ["b させます ④ (Bắt làm)", "a"], "correctAnswer": "b させます ④ (Bắt làm)", "explanation": "Cử đi công tác (b させます ④)."},
            {"itemIndex": "2", "prompt": "Câu 2: Cử đi đâu", "options": ["a 行かせます ③ (Bắt đi)", "b"], "correctAnswer": "a 行かせます ③ (Bắt đi)", "explanation": "Bắt đi (a 行かせます ③)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đọc sách", "options": ["b 読ませます ⑤ (Bắt đọc)", "a"], "correctAnswer": "b 読ませます ⑤ (Bắt đọc)", "explanation": "Bắt đọc (b 読ませます ⑤)."},
            {"itemIndex": "4", "prompt": "Câu 4: Ăn rau", "options": ["a 食べさせます ② (Bắt ăn)", "b"], "correctAnswer": "a 食べさせます ② (Bắt ăn)", "explanation": "Bắt ăn (a 食べさせます ②)."}
        ]
    ),
    make_task(
        "II_CD3", 24, 2, "2. (人)を/に〜(さ)せます (Công sở)", "2. Phân công công việc công sở",
        "2. (人)を/に〜(さ)せます\n1) A: 課長は誰にコピーをさせましたか。\nB: 新入社員にさせました。\n2) A: 会議の案内は誰に送らせましたか。\nB: 秘書に送らせました。",
        "2. Phân công công việc công sở\n1) A: Tổ trưởng giao việc photo tài liệu cho ai làm?\nB: Giao cho nhân viên mới vào làm.\n2) A: Thư mời cuộc họp sếp bảo ai gửi đi vậy?\nB: Bảo cô thư ký gửi đi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Photo", "options": ["① a (Giao nhân viên mới)", "b"], "correctAnswer": "① a (Giao nhân viên mới)", "explanation": "Giao photo (① a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Gửi thư mời", "options": ["⑤ b ③ ④ b (Giao thư ký)", "a"], "correctAnswer": "⑤ b ③ ④ b (Giao thư ký)", "explanation": "Giao gửi thư (⑤ b ③ ④ b)."}
        ]
    ),
    make_task(
        "II_CD3", 25, 3, "3. (好きなことを)〜(さ)せます", "3. Cho phép làm điều mình yêu thích",
        "3. (好きなことを)〜(さ)せます\n1) A: 子どもに好きなピアノを習わせています。\n2) A: 娘をアメリカに留学させました。\n3) A: 息子にバイクの免許を取らせませんでした。",
        "3. Cho phép làm điều mình yêu thích\n1) A: Tôi cho phép con tôi được theo học đàn piano theo sở thích của cháu.\n2) A: Tôi cho con gái đi du học bên Mỹ.\n3) A: Tôi dứt khoát không cho phép cậu con trai thi bằng lái xe máy nguy hiểm.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Học đàn", "options": ["○ (Cho phép học)", "×"], "correctAnswer": "○ (Cho phép học)", "explanation": "Cho phép học đàn (○)."},
            {"itemIndex": "2", "prompt": "Câu 2: Du học Mỹ", "options": ["○ (Cho phép đi du học)", "×"], "correctAnswer": "○ (Cho phép đi du học)", "explanation": "Cho phép du học (○)."},
            {"itemIndex": "3", "prompt": "Câu 3: Bằng xe máy", "options": ["× (Cấm không cho phép)", "○"], "correctAnswer": "× (Cấm không cho phép)", "explanation": "Không cho phép thi bằng lái (×)."}
        ]
    ),
    make_task(
        "II_CD3", 26, 4, "4. 〜(さ)せていただけませんか", "4. Xin phép cho tôi được làm việc gì...",
        "4. 〜(さ)せていただけませんか\n1) A: 申し訳ありませんが、今日早く帰らせていただけませんか。\nB: ええ、いいですよ。お大事に。\n2) A: 写真を撮らせていただけませんか。\nB: すみません、ここは撮影禁止です。\n3) A: もう一度考えさせていただけませんか。\nB: ええ、明日までに返事をください。",
        "4. Xin phép cho tôi được làm việc gì...\n1) A: Em xin lỗi sếp, chiều nay cho em xin phép được về sớm có được không ạ?\nB: Ừ được chứ, về nghỉ ngơi giữ gìn sức khỏe nhé.\n2) A: Xin phép cho tôi chụp một bức ảnh lưu niệm ở đây được không ạ?\nB: Rất tiếc, ở đây nghiêm cấm chụp ảnh bạn ạ.\n3) A: Xin cho phép tôi được suy nghĩ thêm một chút được không ạ?\nB: Được, hạn chót trước ngày mai trả lời cho tôi nhé.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Về sớm", "options": ["a ○ 申し込みの書類を出す (Được duyệt)", "×"], "correctAnswer": "a ○ 申し込みの書類を出す (Được duyệt)", "explanation": "Được cho phép (a ○)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nhờ quán bên cạnh", "options": ["b × 隣の店に頼む (Bị từ chối)", "○"], "correctAnswer": "b × 隣の店に頼む (Bị từ chối)", "explanation": "Bị từ chối (b ×)."},
            {"itemIndex": "3", "prompt": "Câu 3: Ghi tên sđt", "options": ["b ○ 名前と電話番号を書く (Được duyệt)", "×"], "correctAnswer": "b ○ 名前と電話番号を書く (Được duyệt)", "explanation": "Được duyệt (b ○)."}
        ]
    )
]
LESSONS_41_50.append({
    "lessonNumber": 48, "level": "N4",
    "title": "Bài 48: Thể sai khiến (使役形) & Xin phép làm (〜させていただけませんか)",
    "subtitle": "第48課: 使役動詞・行かせる・〜させてください",
    "tasks": l48_tasks
})

# ==================== LESSON 49 ====================
l49_tasks = [
    make_task(
        "II_CD3", 27, 1, "1. (ら)れます[尊敬動詞]", "1. Kính ngữ thể bị động (Tôn kính hành động của đối phương)",
        "1. (ら)れます[尊敬動詞]\n1) A: 社長は何時に来られますか。\nB: 9時に着かれます。\n2) A: 先生は何とおっしゃいましたか。\nB: 「明日の試験を頑張ってください」と言われました。\n3) A: 部長はもう帰られましたか。\nB: はい、先ほど帰られました。",
        "1. Kính ngữ thể bị động (Tôn kính hành động của đối phương)\n1) A: Giám đốc mấy giờ sẽ giá lâm tới ạ?\nB: 9 giờ ngài sẽ đến nơi.\n2) A: Thầy giáo đã căn dặn những gì thế?\nB: Thầy dặn cả lớp cố gắng làm bài thi thật tốt.\n3) A: Trưởng phòng đã ra về rồi ạ?\nB: Vâng, ngài vừa mới rời công ty lúc nãy.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Giờ sếp đến", "options": ["9時 着く (9 giờ đến)", "8h"], "correctAnswer": "9時 着く (9 giờ đến)", "explanation": "9h tới nơi."},
            {"itemIndex": "2", "prompt": "Câu 2: Thầy nói", "options": ["話す する (Căn dặn thi tốt)", "Im lặng"], "correctAnswer": "話す する (Căn dặn thi tốt)", "explanation": "Thầy nói căn dặn."},
            {"itemIndex": "3", "prompt": "Câu 3: Cuộc họp", "options": ["会議 行く / 食事して 帰る", "Ở lại"], "correctAnswer": "会議 行く / 食事して 帰る", "explanation": "Đi họp rồi ăn xong về."}
        ]
    ),
    make_task(
        "II_CD3", 28, 2, "2. お〜になります", "2. Kính ngữ dạng お〜になります",
        "2. お〜になります\n1) A: 社長はゴルフをおやめになりました。\n2) A: 先生にお会いになりましたか。\n3) A: この本をお借りになりましたか。",
        "2. Kính ngữ dạng お〜になります\n1) A: Giám đốc đã nghỉ chơi môn golf rồi.\n2) A: Bạn đã được diện kiến gặp thầy giáo chưa?\n3) A: Ngài đã mượn cuốn sách quý này rồi phải không ạ?",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Bỏ chơi golf", "options": ["やめた (Đã bỏ chơi golf)", "Vẫn chơi"], "correctAnswer": "やめた (Đã bỏ chơi golf)", "explanation": "Đã nghỉ chơi golf."},
            {"itemIndex": "2", "prompt": "Câu 2: Gặp thầy", "options": ["会った (Đã gặp mặt)", "Chưa gặp"], "correctAnswer": "会った (Đã gặp mặt)", "explanation": "Đã gặp mặt thầy."},
            {"itemIndex": "3", "prompt": "Câu 3: Mượn sách", "options": ["借りる (Mượn sách)", "Mua"], "correctAnswer": "借りる (Mượn sách)", "explanation": "Đã mượn sách."}
        ]
    ),
    make_task(
        "II_CD3", 29, 3, "3. 特別な尊敬語", "3. Kính ngữ đặc biệt (いらっしゃる/召し上がる/おっしゃる...)",
        "3. 特別な尊敬語\n1) A: 社長はどちらにいらっしゃいますか。\nB: 会議室にいらっしゃいます。\n2) A: 何を召し上がりますか。\nB: お寿司を召し上がります。\n3) A: お名前は何とおっしゃいますか。\nB: スミスとおっしゃいます。\n4) A: ご存知ですか。\nB: はい、よく存じております。",
        "3. Kính ngữ đặc biệt (いらっしゃる/召し上がる/おっしゃる...)\n1) A: Giám đốc đang ở đâu thế ạ?\nB: Ngài đang ở trong phòng họp lớn.\n2) A: Quý khách dùng món gì ạ?\nB: Ngài dùng món sushi truyền thống.\n3) A: Quý danh của ngài là gì ạ?\nB: Tên ngài là Smith.\n4) A: Ngài có biết chuyện đó không?\nB: Vâng, ngài biết rất rõ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nơi ở của sếp", "options": ["b (Ở phòng họp)", "a"], "correctAnswer": "b (Ở phòng họp)", "explanation": "Ở phòng họp (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Dùng món", "options": ["b (Dùng sushi)", "a"], "correctAnswer": "b (Dùng sushi)", "explanation": "Ăn sushi (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Quý danh", "options": ["a (Tên Smith)", "b"], "correctAnswer": "a (Tên Smith)", "explanation": "Tên là Smith (a)."},
            {"itemIndex": "4", "prompt": "Câu 4: Hiểu biết", "options": ["b (Biết rõ)", "a"], "correctAnswer": "b (Biết rõ)", "explanation": "Biết rõ thông tin (b)."}
        ]
    ),
    make_task(
        "II_CD3", 30, 4, "4. お〜ください", "4. Xin kính mời quý khách... (お〜ください)",
        "4. お〜ください\n1) A: こちらにお掛けください。\n2) A: どうぞお召し上がりください。\n3) A: 少々お待ちください。",
        "4. Xin kính mời quý khách... (お〜ください)\n1) A: Xin kính mời ngài an tọa ngồi vào chiếc ghế này ạ.\n2) A: Xin kính mời ngài dùng bữa tự nhiên ạ.\n3) A: Xin quý khách vui lòng chờ trong giây lát ạ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Mời ngồi", "options": ["b (Kính mời ngồi)", "a"], "correctAnswer": "b (Kính mời ngồi)", "explanation": "Mời ngồi ghế (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Mời dùng bữa", "options": ["a (Kính mời dùng bữa)", "b"], "correctAnswer": "a (Kính mời dùng bữa)", "explanation": "Mời dùng bữa (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Mời đợi", "options": ["b (Xin vui lòng chờ)", "a"], "correctAnswer": "b (Xin vui lòng chờ)", "explanation": "Xin đợi chút (b)."}
        ]
    ),
    make_task(
        "II_CD3", 31, 5, "5. 総合問題", "5. Kính ngữ tổng hợp",
        "5. 総合問題\nある有名な学者へのインタビューです。\n16歳でアメリカへ留学されました。経済学を専攻されました。\n京都大学を卒業され、アジアの地震や台風を研究されています。子どもたちに柔道を教えていらっしゃいます。",
        "5. Kính ngữ tổng hợp\nPhỏng vấn một học giả danh tiếng:\nNăm 16 tuổi ngài đã sang Mỹ du học. Ngài nghiên cứu chuyên sâu về kinh tế học.\nSau đó ngài tốt nghiệp đại học Kyoto, nghiên cứu thiên tai bão động đất ở châu Á. Ngài còn dạy võ Judo cho các em nhỏ.",
        [
            {"itemIndex": "1", "prompt": "Tiểu sử học giả", "options": ["①16歳 ②留学した/行った ③経済 ④入って ⑤3 ⑥転勤した ⑦ピアノを弾く", "Không rõ"], "correctAnswer": "①16歳 ②留学した/行った ③経済 ④入って ⑤3 ⑥転勤した ⑦ピアノを弾く", "explanation": "16 tuổi du học, học kinh tế."},
            {"itemIndex": "2", "prompt": "Hoạt động xã hội", "options": ["①京都 ②卒業した ③アジア ④地震や台風 ⑤勤めている ⑥スポーツ/柔道 ⑦子どもたち", "Không rõ"], "correctAnswer": "①京都 ②卒業した ③アジア ④地震や台風 ⑤勤めている ⑥スポーツ/柔道 ⑦子どもたち", "explanation": "Tốt nghiệp Kyoto, dạy Judo trẻ em."}
        ]
    )
]
LESSONS_41_50.append({
    "lessonNumber": 49, "level": "N4",
    "title": "Bài 49: Tôn kính ngữ (尊敬語 - Thể tôn trọng đối phương)",
    "subtitle": "第49課: 尊敬動詞・お〜になります・いらっしゃる・お〜ください",
    "tasks": l49_tasks
})

# ==================== LESSON 50 ====================
l50_tasks = [
    make_task(
        "II_CD3", 32, 1, "1. お/ご〜します", "1. Khiêm nhường ngữ (Hạ mình phục vụ đối phương)",
        "1. お/ご〜します\n1) A: 重い荷物をお持ちします。\n2) A: 駅までお送りします。\n3) A: 明日の予定をご案内します。\n4) A: 社長にご連絡します。",
        "1. Khiêm nhường ngữ (Hạ mình phục vụ đối phương)\n1) A: Để em xách chiếc vali nặng nề này giúp ngài ạ.\n2) A: Để em lái xe tiễn ngài ra tận nhà ga ạ.\n3) A: Em xin phép được hướng dẫn lịch trình ngày mai cho ngài ạ.\n4) A: Em xin phép liên lạc báo cáo ngay cho giám đốc ạ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Xách vali", "options": ["e (Xách đồ hộ)", "a"], "correctAnswer": "e (Xách đồ hộ)", "explanation": "Xách đồ giúp (e)."},
            {"itemIndex": "2", "prompt": "Câu 2: Tiễn ra ga", "options": ["d (Tiễn ra ga)", "b"], "correctAnswer": "d (Tiễn ra ga)", "explanation": "Tiễn ra ga (d)."},
            {"itemIndex": "3", "prompt": "Câu 3: Báo lịch trình", "options": ["b (Báo lịch trình)", "c"], "correctAnswer": "b (Báo lịch trình)", "explanation": "Hướng dẫn lịch trình (b)."},
            {"itemIndex": "4", "prompt": "Câu 4: Liên lạc giám đốc", "options": ["c (Liên lạc sếp)", "a"], "correctAnswer": "c (Liên lạc sếp)", "explanation": "Liên lạc báo cáo (c)."}
        ]
    ),
    make_task(
        "II_CD3", 33, 2, "2. 特別な謙譲語", "2. Khiêm nhường ngữ đặc biệt (参る/申す/いたす/存じる...)",
        "2. 特別な謙譲語\n1) A: ハノイから参りましたグエンと申します。\n2) A: 明日10時に伺います。\n3) A: 資料を拝見しました。\n4) A: コーヒーをいただきます。",
        "2. Khiêm nhường ngữ đặc biệt (参る/申す/いたす/存じる...)\n1) A: Tôi tên là Nguyễn, hạ mình kính cẩn đến từ thủ đô Hà Nội ạ.\n2) A: Ngày mai vào đúng 10h sáng em xin phép được diện kiến đến thăm văn phòng ngài ạ.\n3) A: Em đã kính cẩn xem qua toàn bộ tập tài liệu quý giá ngài gửi rồi ạ.\n4) A: Em xin phép được nhận tách cà phê ạ.",
        [
            {"itemIndex": "1", "prompt": "Kính cẩn xưng danh", "options": ["(1)かめ (2)箱 (3)a (4)b (5)b (6)戦争", "Không có"], "correctAnswer": "(1)かめ (2)箱 (3)a (4)b (5)b (6)戦争", "explanation": "Kính cẩn dùng khiêm nhường ngữ."}
        ]
    ),
    make_task(
        "II_CD3", 34, 3, "3. 総合問題 (Doanh nghiệp)", "3. Hội thoại giao tiếp thương mại",
        "3. 総合問題\n日本の商社でのビジネス電話のやり取りです。\n「いつも大変お世話になっております。ABC商事の田中と申します。\n部長の佐藤様はいらっしゃいますでしょうか。\nただいま席を外しております。戻りましたら折り返しお電話いたしましょうか。」",
        "3. Hội thoại giao tiếp thương mại\nCuộc điện thoại giao dịch đối tác chuẩn mực của doanh nghiệp Nhật:\n\"Kính chào quý công ty, chúng tôi luôn biết ơn sự chiếu cố của quý ngài. Tôi là Tanaka từ tập đoàn thương mại ABC.\nXin hỏi ngài trưởng phòng Satou có ở đó không ạ?\nDạ báo cáo ngài, sếp Satou hiện đang bước ra ngoài có chút việc. Khi ngài ấy quay lại, chúng tôi xin phép bảo sếp gọi điện lại cho ngài ngay được không ạ?\"",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Xưng tên đối tác", "options": ["b (Chào hỏi chuẩn mực)", "a"], "correctAnswer": "b (Chào hỏi chuẩn mực)", "explanation": "Chào hỏi thương mại chuẩn mực (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Tình trạng sếp", "options": ["a (Sếp đang ra ngoài)", "b"], "correctAnswer": "a (Sếp đang ra ngoài)", "explanation": "Sếp đang bước ra ngoài (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đề nghị gọi lại", "options": ["a (Sẽ gọi lại)", "c"], "correctAnswer": "a (Sẽ gọi lại)", "explanation": "Sẽ gọi lại sau (a)."}
        ]
    ),
    make_task(
        "II_CD3", 35, 4, "4. いろいろなアナウンスを聞きました。どうしなければなりませんか。", "4. Nghe các thông báo phát thanh công cộng",
        "4. いろいろなアナウンスを聞きました。どうしなければなりませんか。\n例) お客様のお呼び出しを申し上げます。ワン・シュエ様、1階ロビーまでお越しください。\n1) JL935便のお客様、お待たせいたしました。ただいまからご案内いたします。係員にチケットをお見せになって、お入りください。1番から20番までの方、お越しください。\n2) 皆様にお願いいたします。こちらの建物は禁煙になっております。おタバコはご遠慮ください。また、コンサート中は携帯電話の電源をお切りください。\n3) きょうは映画「みんなの家」をご覧いただき、ありがとうございました。どうぞお忘れ物がないようにご注意ください。また、大変混んでおりますので、お出口は横の1番から3番までをご利用ください。",
        "4. Nghe các thông báo phát thanh công cộng\nVí dụ) Xin phát thanh thông báo tìm khách: Kính mời quý khách Wang Xue di chuyển xuống sảnh tầng 1 có người gặp ạ.\n1) Quý hành khách trên chuyến bay JL935, xin mời chuẩn bị lên máy bay. Quý khách vui lòng xuất trình vé cho nhân viên mặt đất. Mời các hành khách số ghế từ 1 đến 20 vào trước.\n2) Xin quý khán giả lưu ý: Toàn bộ tòa nhà này nghiêm cấm hút thuốc. Trong suốt thời gian diễn ra buổi hòa nhạc, xin quý vị tắt nguồn điện thoại di động.\n3) Trân trọng cảm ơn quý vị đã đến thưởng thức bộ phim 'Minna no Ie'. Xin quý vị kiểm tra kỹ tư trang tránh bỏ quên. Lối ra cửa 1 đến cửa 3 đang mở để quý khách di chuyển thuận tiện.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Khách chuyến bay JL935", "options": ["a (Xuất trình vé, ghế 1-20 lên trước)", "b"], "correctAnswer": "a (Xuất trình vé, ghế 1-20 lên trước)", "explanation": "Xuất trình vé cho nhân viên, số 1-20 vào trước (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Khán giả hòa nhạc", "options": ["a (Cấm hút thuốc, tắt điện thoại)", "b"], "correctAnswer": "a (Cấm hút thuốc, tắt điện thoại)", "explanation": "Cấm thuốc lá, tắt nguồn điện thoại di động (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Khán giả xem phim xong", "options": ["b (Kiểm tra đồ đạc, ra cửa 1-3)", "a"], "correctAnswer": "b (Kiểm tra đồ đạc, ra cửa 1-3)", "explanation": "Kiểm tra tư trang, ra cửa 1-3 (b)."}
        ]
    )
]
LESSONS_41_50.append({
    "lessonNumber": 50, "level": "N4",
    "title": "Bài 50: Khiêm nhường ngữ (謙譲語) & Thông báo nơi công cộng",
    "subtitle": "第50課: お/ご〜します・参る・申す・アナウンス",
    "tasks": l50_tasks
})

print("Lessons 41 to 50 defined in LESSONS_41_50.")
