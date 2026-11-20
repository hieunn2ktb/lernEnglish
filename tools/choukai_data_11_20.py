# -*- coding: utf-8 -*-
from choukai_helper import make_task

LESSONS_11_20 = []

# ==================== LESSON 11 ====================
l11_tasks = [
    make_task(
        "I_CD1", 43, 1, "1. いくつですか", "1. Mấy cái / Bao nhiêu?",
        "1. いくつですか\n1) A: すみません、紅茶を1つとビールを1つ、あとサンドイッチを2つください。\nB: はい、かしこまりました。\n2) A: ジュースを2つとサンドイッチを1つ、カレーライスを2つお願いします。\nB: はい、ありがとうございます。",
        "1. Mấy cái / Bao nhiêu?\n1) A: Xin lỗi, cho tôi 1 hồng trà, 1 bia và 2 bánh kẹp sandwich.\nB: Vâng, tôi xin nhận đơn.\n2) A: Cho tôi 2 nước hoa quả, 1 bánh sandwich và 2 suất cơm cà ri.\nB: Vâng, xin cảm ơn quý khách.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đồ uống & món ăn bàn 1", "options": ["紅茶1, ビール1, サンドイッチ2", "紅茶2, ビール1"], "correctAnswer": "紅茶1, ビール1, サンドイッチ2", "explanation": "Hồng trà 1, bia 1, sandwich 2."},
            {"itemIndex": "2", "prompt": "Câu 2: Đồ uống & món ăn bàn 2", "options": ["ジュース2, サンドイッチ1, カレーライス2", "ジュース1, カレー1"], "correctAnswer": "ジュース2, サンドイッチ1, カレーライス2", "explanation": "Nước hoa quả 2, sandwich 1, cà ri 2."}
        ]
    ),
    make_task(
        "I_CD1", 44, 2, "2. 何枚/何台/何人...", "2. Mấy tờ / Mấy chiếc / Mấy người...",
        "2. 何枚/何台/何人...\n1) A: ご家族は何人ですか。\nB: 子供が5人います。全部で11人です。\n2) A: 会社に外国人が何人いますか。\nB: アメリカ人が3人、インド人が1人、ヨーロッパ人が4人で、全部で10人です。\n3) A: 車は何台ありますか。\nB: わたしの車が2台、妻の車が1台、子供の車が1台で、全部で4台です。",
        "2. Mấy tờ / Mấy chiếc / Mấy người...\n1) A: Gia đình bạn có bao nhiêu người?\nB: Có 5 người con, tất cả là 11 người.\n2) A: Công ty bạn có bao nhiêu người nước ngoài?\nB: 3 người Mỹ, 1 người Ấn Độ, 4 người châu Âu, tổng cộng 10 người.\n3) A: Nhà bạn có bao nhiêu chiếc xe ô tô?\nB: Của tôi 2 chiếc, của vợ 1 chiếc, của con 1 chiếc, tổng cộng 4 chiếc.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Số người trong gia đình", "options": ["子ども5人・全部で11人", "子ども3人・全部で5人"], "correctAnswer": "子ども5人・全部で11人", "explanation": "5 con, tổng 11 người."},
            {"itemIndex": "2", "prompt": "Câu 2: Số người nước ngoài trong công ty", "options": ["アメリカ3, インド1, ヨーロッパ4 (Tổng 10)", "Tổng 8 người"], "correctAnswer": "アメリカ3, インド1, ヨーロッパ4 (Tổng 10)", "explanation": "Mỹ 3, Ấn 1, Âu 4 (Tổng 10)."},
            {"itemIndex": "3", "prompt": "Câu 3: Số lượng xe hơi", "options": ["わたしの車2, 妻1, 子ども1 (Tổng 4)", "Tổng 3 chiếc"], "correctAnswer": "わたしの車2, 妻1, 子ども1 (Tổng 4)", "explanation": "Của tôi 2, vợ 1, con 1 (Tổng 4)."}
        ]
    ),
    make_task(
        "I_CD1", 45, 3, "3. どのくらい〜ましたか", "3. Đã làm trong bao lâu?",
        "3. どのくらい〜ましたか\n1) A: どのくらい日本語を勉強しましたか。\nB: 4か月勉強しました。\n2) A: 日本にどのくらいいますか。\nB: 5年います。\n3) A: 国へどのくらい帰りましたか。\nB: 1週間帰りました。",
        "3. Đã làm trong bao lâu?\n1) A: Bạn đã học tiếng Nhật trong bao lâu?\nB: Tôi đã học được 4 tháng rồi.\n2) A: Bạn ở Nhật Bản bao lâu rồi?\nB: Được 5 năm rồi.\n3) A: Bạn về thăm nước trong bao lâu?\nB: Tôi về trong 1 tuần.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Thời gian học tiếng Nhật", "options": ["4か月 (d)", "3か月"], "correctAnswer": "4か月 (d)", "explanation": "4 tháng (4か月 d)."},
            {"itemIndex": "2", "prompt": "Câu 2: Thời gian ở Nhật", "options": ["5年 (b)", "3年"], "correctAnswer": "5年 (b)", "explanation": "5 năm (5年 b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Thời gian về nước", "options": ["1週間 (c)", "2週間"], "correctAnswer": "1週間 (c)", "explanation": "1 tuần (1週間 c)."}
        ]
    ),
    make_task(
        "I_CD1", 46, 4, "4. どのくらいかかりますか", "4. Mất bao lâu / Tốn bao nhiêu?",
        "4. どのくらいかかりますか\n1) A: 東京から大阪まで新幹線でどのくらいかかりますか。\nB: 2時間半かかります。\n2) A: 駅からうちまで歩いてどのくらいですか。\nB: 15分くらいです。",
        "4. Mất bao lâu / Tốn bao nhiêu?\n1) A: Từ Tokyo đến Osaka đi bằng Shinkansen mất bao lâu?\nB: Mất khoảng 2 tiếng rưỡi.\n2) A: Từ nhà ga về đến nhà bạn đi bộ mất bao lâu?\nB: Mất khoảng 15 phút.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Tokyo đến Osaka", "options": ["b a (2 tiếng rưỡi)", "1 tiếng"], "correctAnswer": "b a (2 tiếng rưỡi)", "explanation": "2 giờ 30 phút (b a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Ga về nhà", "options": ["a a (15 phút đi bộ)", "30 phút"], "correctAnswer": "a a (15 phút đi bộ)", "explanation": "15 phút đi bộ (a a)."}
        ]
    )
]
LESSONS_11_20.append({
    "lessonNumber": 11, "level": "N5",
    "title": "Bài 11: Số lượng, Thời lượng & Khoảng cách",
    "subtitle": "第11課: 助数詞・数量・期間・どのくらい",
    "tasks": l11_tasks
})

# ==================== LESSON 12 ====================
l12_tasks = [
    make_task(
        "I_CD1", 47, 1, "1. 〜はどうでしたか", "1. Đã như thế nào? (Quá khứ)",
        "1. 〜はどうでしたか\n1) A: 旅行はどうでしたか。\nB: とても楽しかったです。\n2) A: お祭りはどうでしたか。\nB: にぎやかでした。\n3) A: 映画はどうでしたか。\nB: あまりおもしろくなかったです。\n4) A: きのうの休みはどうでしたか。\nB: 暇でした。",
        "1. Đã như thế nào? (Quá khứ)\n1) A: Chuyến đi du lịch thế nào?\nB: Rất là vui vẻ.\n2) A: Lễ hội thế nào?\nB: Rất náo nhiệt.\n3) A: Bộ phim thế nào?\nB: Không hay lắm.\n4) A: Ngày nghỉ hôm qua thế nào?\nB: Rảnh rỗi lắm.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Chuyến đi du lịch", "options": ["楽しかったです", "疲労しました"], "correctAnswer": "楽しかったです", "explanation": "Rất vui (楽しかったです)."},
            {"itemIndex": "2", "prompt": "Câu 2: Lễ hội", "options": ["にぎやかでした", "静かでした"], "correctAnswer": "にぎやかでした", "explanation": "Náo nhiệt (にぎやかでした)."},
            {"itemIndex": "3", "prompt": "Câu 3: Bộ phim", "options": ["おもしろくなかったです", "おもしろかったです"], "correctAnswer": "おもしろくなかったです", "explanation": "Không hay (おもしろくなかったです)."},
            {"itemIndex": "4", "prompt": "Câu 4: Ngày nghỉ", "options": ["暇でした", "忙しかったです"], "correctAnswer": "暇でした", "explanation": "Rảnh rỗi (暇でした)."}
        ]
    ),
    make_task(
        "I_CD1", 48, 2, "2. どちらが〜ですか", "2. Cái nào hơn? (So sánh 2 vật)",
        "2. どちらが〜ですか\n1) A: 日本と中国とどちらが大きいですか。\nB: 中国のほうがずっと大きいです。\n2) A: 海と山とどちらが好きですか。\nB: 山のほうが好きです。\n3) A: 電車とバスとどちらが速いですか。\nB: 電車のほうが速いです。",
        "2. Cái nào hơn? (So sánh 2 vật)\n1) A: Nhật Bản và Trung Quốc nước nào rộng lớn hơn?\nB: Trung Quốc rộng hơn nhiều.\n2) A: Biển và núi bạn thích bên nào hơn?\nB: Tôi thích núi hơn.\n3) A: Tàu điện và xe buýt phương tiện nào nhanh hơn?\nB: Tàu điện nhanh hơn.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nước nào rộng hơn?", "options": ["a. 中国 (Trung Quốc)", "b. 日本 (Nhật Bản)"], "correctAnswer": "a. 中国 (Trung Quốc)", "explanation": "Trung Quốc rộng hơn (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Thích biển hay núi hơn?", "options": ["b. 山 (Núi)", "a. 海 (Biển)"], "correctAnswer": "b. 山 (Núi)", "explanation": "Thích núi hơn (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Phương tiện nào nhanh hơn?", "options": ["b. 電車 (Tàu điện)", "a. バス (Xe buýt)"], "correctAnswer": "b. 電車 (Tàu điện)", "explanation": "Tàu điện nhanh hơn (b)."}
        ]
    ),
    make_task(
        "I_CD1", 49, 3, "3. 〜がいちばん〜です", "3. Cái gì là nhất? (So sánh cao nhất)",
        "3. 〜がいちばん〜です\n1) A: スポーツの中で何がいちばん好きですか。\nB: サッカーがいちばん好きです。\n2) A: 季節の中でいつがいちばん好きですか。\nB: 秋がいちばん好きです。",
        "3. Cái gì là nhất? (So sánh cao nhất)\n1) A: Trong các môn thể thao bạn thích môn nào nhất?\nB: Tôi thích bóng đá nhất.\n2) A: Trong 4 mùa bạn thích mùa nào nhất?\nB: Tôi thích mùa thu nhất.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Môn thể thao thích nhất", "options": ["a. サッカー (Bóng đá)", "b. 野球 (Bóng chày)"], "correctAnswer": "a. サッカー (Bóng đá)", "explanation": "Thích bóng đá nhất (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Mùa thích nhất", "options": ["c. 秋 (Mùa thu)", "a. 春 (Mùa xuân)"], "correctAnswer": "c. 秋 (Mùa thu)", "explanation": "Thích mùa thu nhất (c)."}
        ]
    ),
    make_task(
        "I_CD1", 50, 4, "4. 〜がいちばん〜かったです/〜でした", "4. Cái gì là nhất trong quá khứ?",
        "4. 〜がいちばん〜かったです/〜でした\n1) A: アジアの中でどこがいちばんよかったですか。\nB: ベトナムがいちばんよかったです。\n2) A: 料理の中で何がいちばん美味しかったですか。\nB: 韓国の料理がいちばん美味しかったです。\n3) A: ダンスは何がいちばんおもしろかったですか。\nB: インドネシアのダンスがいちばんおもしろかったです。",
        "4. Cái gì là nhất trong quá khứ?\n1) A: Trong chuyến đi châu Á nơi nào tuyệt nhất?\nB: Việt Nam là tuyệt vời nhất.\n2) A: Trong các món ăn món nào ngon nhất?\nB: Món ăn Hàn Quốc ngon nhất.\n3) A: Điệu múa nào thú vị nhất?\nB: Điệu múa của Indonesia là thú vị nhất.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nơi tuyệt nhất", "options": ["ベトナム (Việt Nam)", "タイ (Thái Lan)"], "correctAnswer": "ベトナム (Việt Nam)", "explanation": "Việt Nam tuyệt nhất."},
            {"itemIndex": "2", "prompt": "Câu 2: Món ăn ngon nhất", "options": ["韓国の料理 (Món Hàn Quốc)", "日本の料理"], "correctAnswer": "韓国の料理 (Món Hàn Quốc)", "explanation": "Món Hàn Quốc ngon nhất."},
            {"itemIndex": "3", "prompt": "Câu 3: Điệu múa thú vị nhất", "options": ["インドネシアのダンス (Múa Indonesia)", "インドのダンス"], "correctAnswer": "インドネシアのダンス (Múa Indonesia)", "explanation": "Điệu múa Indonesia."}
        ]
    )
]
LESSONS_11_20.append({
    "lessonNumber": 12, "level": "N5",
    "title": "Bài 12: So sánh & Cảm tưởng quá khứ",
    "subtitle": "第12課: 比較・どちら・一番・過去の感想",
    "tasks": l12_tasks
})

# ==================== LESSON 13 ====================
l13_tasks = [
    make_task(
        "I_CD1", 51, 1, "1. 〜が欲しいです", "1. Muốn có cái gì?",
        "1. 〜が欲しいです\n1) A: 今何が欲しいですか。\nB: 子どもが欲しいです。にぎやかなうちがいいです。\n2) A: どんなうちが欲しいですか。\nB: 広い庭があるうちが欲しいです。犬と遊びます。\n3) A: 何が欲しいですか。\nB: お金とパソコンが欲しいです。",
        "1. Muốn có cái gì?\n1) A: Bây giờ bạn muốn có cái gì?\nB: Tôi muốn có con, một gia đình rộn rã tiếng cười.\n2) A: Bạn muốn có một ngôi nhà như thế nào?\nB: Tôi muốn nhà có mảnh sân rộng để chơi với cún cưng.\n3) A: Bạn muốn có gì?\nB: Tôi muốn có nhiều tiền và một chiếc máy tính xịn.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Điều mong muốn", "options": ["①子ども ②にぎやかなうち", "車"], "correctAnswer": "①子ども ②にぎやかなうち", "explanation": "Muốn có con và nhà đông vui."},
            {"itemIndex": "2", "prompt": "Câu 2: Ngôi nhà mong muốn", "options": ["①広い庭 ②犬と遊ぶ", "狭い部屋"], "correctAnswer": "①広い庭 ②犬と遊ぶ", "explanation": "Sân rộng và chơi với cún."},
            {"itemIndex": "3", "prompt": "Câu 3: Vật mong muốn", "options": ["①お金 ②パソコン", "カメラ"], "correctAnswer": "①お金 ②パソコン", "explanation": "Tiền và máy tính."}
        ]
    ),
    make_task(
        "I_CD1", 52, 2, "2. 〜たいです", "2. Muốn làm cái gì?",
        "2. 〜たいです\n1) A: 週末何をしたいですか。\nB: 映画を見に行きたいです。\n2) A: どこへ行きたいですか。\nB: 温泉に行きたいです。\n3) A: 何を食べたいですか。\nB: 寿司を食べたいです。\n4) A: だれに会いたいですか。\nB: 家族に会いたいです。",
        "2. Muốn làm cái gì?\n1) A: Cuối tuần bạn muốn làm gì?\nB: Tôi muốn đi xem phim.\n2) A: Bạn muốn đi đâu?\nB: Muốn đi tắm suối nước nóng Onsen.\n3) A: Bạn muốn ăn món gì?\nB: Muốn ăn sushi.\n4) A: Bạn muốn gặp ai nhất?\nB: Tôi muốn gặp lại gia đình.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Muốn làm gì?", "options": ["c. 映画を見る", "a. 寝る"], "correctAnswer": "c. 映画を見る", "explanation": "Xem phim (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Muốn đi đâu?", "options": ["e. 温泉に行く", "b. 海に行く"], "correctAnswer": "e. 温泉に行く", "explanation": "Đi suối nước nóng (e)."},
            {"itemIndex": "3", "prompt": "Câu 3: Muốn ăn gì?", "options": ["h. 寿司を食べる", "f. ラーメン"], "correctAnswer": "h. 寿司を食べる", "explanation": "Ăn sushi (h)."},
            {"itemIndex": "4", "prompt": "Câu 4: Muốn gặp ai?", "options": ["f. 家族に会う", "d. 友達"], "correctAnswer": "f. 家族に会う", "explanation": "Gặp gia đình (f)."}
        ]
    ),
    make_task(
        "I_CD1", 53, 3, "3. 何をしに行きますか", "3. Đi đâu để làm gì?",
        "3. 何をしに行きますか\n1) A: 郵便局へ切手を買いに行きます。\n2) A: デパートへ服を買いに行きます。\n3) A: 図書館へ本を返しに行きます。",
        "3. Đi đâu để làm gì?\n1) A: Tôi đi bưu điện để mua tem thư.\n2) A: Tôi đi trung tâm thương mại để mua sắm quần áo.\n3) A: Tôi đến thư viện để trả lại sách.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đi bưu điện", "options": ["b a (Mua tem)", "c d"], "correctAnswer": "b a (Mua tem)", "explanation": "Đi mua tem (b a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đi trung tâm thương mại", "options": ["a b (Mua quần áo)", "c d"], "correctAnswer": "a b (Mua quần áo)", "explanation": "Mua quần áo (a b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đến thư viện", "options": ["a b (Trả sách)", "c d"], "correctAnswer": "a b (Trả sách)", "explanation": "Trả sách (a b)."}
        ]
    ),
    make_task(
        "I_CD1", 54, 4, "4. 何をしに来ましたか", "4. Đến đây để làm mục đích gì?",
        "4. 何をしに来ましたか\n1) A: 日本へ何をしに来ましたか。\nB: 経済を勉強しに来ました。\n2) A: 神戸へ何をしに来ましたか。\nB: 友達に会いに来ました。\n3) A: この会社へ何をしに来ましたか。\nB: 見学に来ました。",
        "4. Đến đây để làm mục đích gì?\n1) A: Bạn đến Nhật Bản để làm gì thế?\nB: Tôi đến để học ngành kinh tế.\n2) A: Bạn đến Kobe để làm gì?\nB: Tôi đến thăm bạn.\n3) A: Bạn đến công ty này với mục đích gì?\nB: Tôi đến để tham quan học hỏi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đến Nhật làm gì?", "options": ["c. 経済を勉強する", "a. 観光"], "correctAnswer": "c. 経済を勉強する", "explanation": "Học kinh tế (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đến Kobe làm gì?", "options": ["b. 友達に会う", "a. 仕事"], "correctAnswer": "b. 友達に会う", "explanation": "Thăm bạn bè (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đến công ty làm gì?", "options": ["e. 見学する", "d. 面接"], "correctAnswer": "e. 見学する", "explanation": "Tham quan thực tế (e)."}
        ]
    )
]
LESSONS_11_20.append({
    "lessonNumber": 13, "level": "N5",
    "title": "Bài 13: Mong muốn & Mục đích di chuyển",
    "subtitle": "第13課: 欲しい・たい・目的 (~に行きます)",
    "tasks": l13_tasks
})

# ==================== LESSON 14 ====================
l14_tasks = [
    make_task(
        "I_CD2", 1, 1, "1. 〜てください", "1. Xin hãy làm... (Yêu cầu lịch sự)",
        "1. 〜てください\n1) A: すみませんが、ちょっと待ってください。\nB: はい、わかりました。\n2) A: 窓を開けてください。\nB: はい、開けます。\n3) A: ここに名前を書いてください。\nB: はい、書きます。",
        "1. Xin hãy làm... (Yêu cầu lịch sự)\n1) A: Xin lỗi, xin bạn hãy đợi một lát.\nB: Vâng, tôi hiểu rồi.\n2) A: Xin vui lòng mở giúp cửa sổ ra với ạ.\nB: Vâng, tôi mở ngay.\n3) A: Xin hãy viết tên bạn vào chỗ này.\nB: Vâng, tôi xin viết.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Hành động yêu cầu", "options": ["c. 待ってください (Đợi một chút)", "a. 来てください"], "correctAnswer": "c. 待ってください (Đợi một chút)", "explanation": "Xin hãy đợi (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Hành động yêu cầu", "options": ["c. 窓を開けてください (Mở cửa sổ)", "b. 閉めてください"], "correctAnswer": "c. 窓を開けてください (Mở cửa sổ)", "explanation": "Mở cửa sổ (c)."},
            {"itemIndex": "3", "prompt": "Câu 3: Hành động yêu cầu", "options": ["a. 名前を書いてください (Viết tên)", "b. 読んでください"], "correctAnswer": "a. 名前を書いてください (Viết tên)", "explanation": "Viết tên (a)."}
        ]
    ),
    make_task(
        "I_CD2", 2, 2, "2. 〜ましょうか", "2. Để tôi giúp bạn nhé?",
        "2. 〜ましょうか\n1) A: 荷物を持ちましょうか。\nB: すみません、お願いします。\n2) A: エアコンをつけましょうか。\nB: いいえ、結構です。\n3) A: 写真を撮りましょうか。\nB: ええ、お願いします。",
        "2. Để tôi giúp bạn nhé?\n1) A: Để tôi xách hành lý giúp bạn nhé?\nB: Ôi cảm ơn bạn, phiền bạn nhé.\n2) A: Để tôi bật điều hòa lên nhé?\nB: Dạ thôi không cần đâu ạ.\n3) A: Để tôi chụp ảnh cho hai bạn nhé?\nB: Vâng, nhờ bạn giúp với ạ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Giúp xách đồ", "options": ["c. Xách đồ (Nhờ giúp)", "a. Từ chối"], "correctAnswer": "c. Xách đồ (Nhờ giúp)", "explanation": "Nhờ xách đồ (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Bật điều hòa", "options": ["b. Bật điều hòa (Từ chối: 結構です)", "a. Đồng ý"], "correctAnswer": "b. Bật điều hòa (Từ chối: 結構です)", "explanation": "Từ chối (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Chụp ảnh", "options": ["a. Chụp ảnh (Đồng ý)", "b. Từ chối"], "correctAnswer": "a. Chụp ảnh (Đồng ý)", "explanation": "Nhờ chụp ảnh (a)."}
        ]
    ),
    make_task(
        "I_CD2", 3, 3, "3. 〜ています", "3. Đang diễn ra (Hành động)",
        "3. 〜ています\n1) A: 今雨が降っていますか。\nB: はい、降っています。\n2) A: ミラーさんは今何をしていますか。\nB: 電話をかけています。",
        "3. Đang diễn ra (Hành động)\n1) A: Bây giờ trời đang mưa phải không?\nB: Vâng, trời đang mưa đấy.\n2) A: Anh Miller đang làm gì thế?\nB: Anh ấy đang gọi điện thoại.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Thời tiết hiện tại", "options": ["c. 雨が降っています (Đang mưa)", "a. 晴れています"], "correctAnswer": "c. 雨が降っています (Đang mưa)", "explanation": "Đang mưa (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Anh Miller đang làm gì?", "options": ["e. 電話をかけています (Gọi điện)", "b. 本を読んでいます"], "correctAnswer": "e. 電話をかけています (Gọi điện)", "explanation": "Đang gọi điện thoại (e)."}
        ]
    ),
    make_task(
        "I_CD2", 4, 4, "4. 総合問題", "4. Bài nghe tổng hợp",
        "4. 総合問題\n1) A: 秋田さん、荷物を運んでください。\nB: はい、わかりました。\n2) A: 727便の飛行機ですね。\nB: はい、そうです。\n3) A: 247号室の青木さんですね。\nB: はい、そうです。",
        "4. Bài nghe tổng hợp\n1) A: Anh Akita, xin hãy chuyển hành lý giúp tôi.\nB: Vâng, tôi làm ngay.\n2) A: Chuyến bay số hiệu 727 phải không?\nB: Vâng đúng rồi.\n3) A: Anh Aoki ở phòng số 247 đúng không ạ?\nB: Vâng chính xác.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nhân vật và việc làm", "options": ["秋田 b", "山田 a"], "correctAnswer": "秋田 b", "explanation": "Anh Akita chuyển đồ (秋田 b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Số hiệu chuyến bay", "options": ["727 e", "737 f"], "correctAnswer": "727 e", "explanation": "Chuyến bay 727 (727 e)."},
            {"itemIndex": "3", "prompt": "Câu 3: Số phòng và họ tên", "options": ["247 青木 f", "248 鈴木"], "correctAnswer": "247 青木 f", "explanation": "Phòng 247 anh Aoki (247 青木 f)."}
        ]
    )
]
LESSONS_11_20.append({
    "lessonNumber": 14, "level": "N5",
    "title": "Bài 14: Thể て, Sai khiến & Đề nghị giúp",
    "subtitle": "第14課: てください・ましょうか・ています",
    "tasks": l14_tasks
})

# ==================== LESSON 15 ====================
l15_tasks = [
    make_task(
        "I_CD2", 5, 1, "1. 〜てもいいですか", "1. Làm việc này có được không?",
        "1. 〜てもいいですか\n1) A: ここで写真を撮ってもいいですか。\nB: いいえ、いけません。\n2) A: タバコを吸ってもいいですか。\nB: はい、どうぞ。\n3) A: この辞書を使ってもいいですか。\nB: すみません、今使っていますから、だめです。\n4) A: ここに入ってもいいですか。\nB: はい、どうぞお入りください。",
        "1. Làm việc này có được không?\n1) A: Chụp ảnh ở đây có được phép không ạ?\nB: Không, không được chụp ở đây đâu ạ.\n2) A: Tôi hút thuốc ở đây có được không?\nB: Vâng, xin mời bạn.\n3) A: Tôi dùng cuốn từ điển này được không?\nB: Xin lỗi bạn, tôi đang tra nên không được rồi.\n4) A: Tôi vào đây được không ạ?\nB: Vâng, xin mời bạn cứ vào.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Chụp ảnh", "options": ["× (Không được)", "○ (Được)"], "correctAnswer": "× (Không được)", "explanation": "Không được (×)."},
            {"itemIndex": "2", "prompt": "Câu 2: Hút thuốc", "options": ["○ (Được phép)", "× (Không)"], "correctAnswer": "○ (Được phép)", "explanation": "Được phép (○)."},
            {"itemIndex": "3", "prompt": "Câu 3: Mượn từ điển", "options": ["× (Không được)", "○ (Được)"], "correctAnswer": "× (Không được)", "explanation": "Không được vì đang dùng (×)."},
            {"itemIndex": "4", "prompt": "Câu 4: Đi vào trong", "options": ["○ (Được vào)", "× (Không)"], "correctAnswer": "○ (Được vào)", "explanation": "Được vào (○)."}
        ]
    ),
    make_task(
        "I_CD2", 6, 2, "2. 〜てはいけません", "2. Cấm không được làm",
        "2. 〜てはいけません\n1) A: ここに車を止めてはいけません。\n2) A: お酒を飲んで運転してはいけません。",
        "2. Cấm không được làm\n1) A: Không được dừng đỗ xe ô tô ở đây.\n2) A: Không được lái xe sau khi đã uống rượu bia.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đỗ xe", "options": ["①× ②× (Biển cấm đỗ)", "○ ○"], "correctAnswer": "①× ②× (Biển cấm đỗ)", "explanation": "Cấm đỗ xe (①× ②×)."},
            {"itemIndex": "2", "prompt": "Câu 2: Lái xe uống rượu", "options": ["①× ②○ (Cấm tuyệt đối)", "○ ○"], "correctAnswer": "①× ②○ (Cấm tuyệt đối)", "explanation": "Cấm uống rượu lái xe (①× ②○)."}
        ]
    ),
    make_task(
        "I_CD2", 7, 3, "3. 持っています/知っています...", "3. Sở hữu & Hiểu biết (Trạng thái)",
        "3. 持っています/知っています...\n1) A: パスポートを持っていますか。\nB: はい、持っています。\n2) A: あの人の名前を知っていますか。\nB: いいえ、知りません。",
        "3. Sở hữu & Hiểu biết (Trạng thái)\n1) A: Bạn có mang hộ chiếu theo không?\nB: Có, tôi có mang theo.\n2) A: Bạn có biết tên người đằng kia không?\nB: Không, tôi không biết.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Mang theo hộ chiếu", "options": ["a b (Có mang theo)", "c d"], "correctAnswer": "a b (Có mang theo)", "explanation": "Có mang hộ chiếu (a b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Biết tên người kia", "options": ["b b (Không biết: 知りません)", "a a"], "correctAnswer": "b b (Không biết: 知りません)", "explanation": "Không biết (b b)."}
        ]
    ),
    make_task(
        "I_CD2", 8, 4, "4. 結婚しています/住んでいます/働いています...", "4. Tình trạng hôn nhân, Nơi ở, Nghề nghiệp",
        "4. 結婚しています/住んでいます/働いています...\n1) A: 結婚していますか。\nB: はい、結婚しています。\n2) A: どこに住んでいますか。\nB: 大阪に住んでいます。\n3) A: どこで働いていますか。\nB: 自動車の会社で働いています。\n4) A: 家族はどこにいますか。\nB: 東京にいます。\n5) A: 日本語学校で何を勉強していますか。\nB: 日本の文化を勉強しています。",
        "4. Tình trạng hôn nhân, Nơi ở, Nghề nghiệp\n1) A: Bạn đã kết hôn chưa?\nB: Vâng, tôi kết hôn rồi.\n2) A: Bạn hiện đang sống ở đâu?\nB: Tôi sống ở Osaka.\n3) A: Bạn làm việc ở đâu vậy?\nB: Tôi làm việc ở một công ty sản xuất ô tô.\n4) A: Gia đình bạn đang ở đâu?\nB: Đang ở Tokyo.\n5) A: Ở trường tiếng Nhật bạn học những gì?\nB: Tôi học về văn hóa Nhật Bản.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Hôn nhân", "options": ["①b (Đã kết hôn)", "①a"], "correctAnswer": "①b (Đã kết hôn)", "explanation": "Đã kết hôn (①b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nơi sống", "options": ["②a (Osaka)", "②b"], "correctAnswer": "②a (Osaka)", "explanation": "Sống ở Osaka (②a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Nơi làm việc", "options": ["③a (Công ty ô tô)", "③b"], "correctAnswer": "③a (Công ty ô tô)", "explanation": "Công ty ô tô (③a)."},
            {"itemIndex": "4", "prompt": "Câu 4: Gia đình", "options": ["④b (Ở Tokyo)", "④a"], "correctAnswer": "④b (Ở Tokyo)", "explanation": "Ở Tokyo (④b)."},
            {"itemIndex": "5", "prompt": "Câu 5: Học văn hóa", "options": ["⑤a (Văn hóa Nhật)", "⑤b"], "correctAnswer": "⑤a (Văn hóa Nhật)", "explanation": "Học văn hóa (⑤a)."}
        ]
    )
]
LESSONS_11_20.append({
    "lessonNumber": 15, "level": "N5",
    "title": "Bài 15: Cho phép, Cấm đoán & Trạng thái sống",
    "subtitle": "第15課: てもいいですか・てはいけません・状態",
    "tasks": l15_tasks
})

# ==================== LESSON 16 ====================
l16_tasks = [
    make_task(
        "I_CD2", 9, 1, "1. 〜て、〜て、〜", "1. Nối chuỗi hành động liên tiếp",
        "1. 〜て、〜て、〜\n1) A: 日曜日は何をしましたか。\nB: 京都駅に行って、バスに乗って、1時にレストランで昼ごはんを食べました。5時にうちへ帰りました。",
        "1. Nối chuỗi hành động liên tiếp\n1) A: Chủ Nhật vừa rồi bạn đã làm những gì?\nB: Tôi đến ga Kyoto, bắt xe buýt, lúc 1 giờ ăn trưa ở nhà hàng. Đến 5 giờ chiều thì về nhà.",
        [
            {"itemIndex": "1", "prompt": "Trình tự chuyến đi Chủ Nhật", "options": ["①京都駅 ②b ③1:00 ④バス ⑤a ⑥5:00 ⑦レストラン", "①大阪 ②c"], "correctAnswer": "①京都駅 ②b ③1:00 ④バス ⑤a ⑥5:00 ⑦レストラン", "explanation": "Ga Kyoto -> xe buýt -> 1:00 ăn nhà hàng -> 5:00 về nhà."}
        ]
    ),
    make_task(
        "I_CD2", 10, 2, "2. どうやって行きますか", "2. Đi bằng cách nào? (Chỉ đường)",
        "2. どうやって行きますか\n1) A: 大学までどうやって行きますか。\nB: 地下鉄に乗って、本町で降りて、歩いて行きます。",
        "2. Đi bằng cách nào? (Chỉ đường)\n1) A: Đi đến trường đại học bằng cách nào vậy?\nB: Đi tàu điện ngầm, xuống ở ga Honmachi rồi đi bộ tới.",
        [
            {"itemIndex": "1", "prompt": "Cách đi đến trường", "options": ["b g (Đi tàu điện ngầm, xuống ga rồi đi bộ)", "a c"], "correctAnswer": "b g (Đi tàu điện ngầm, xuống ga rồi đi bộ)", "explanation": "Tàu điện ngầm rồi đi bộ (b g)."}
        ]
    ),
    make_task(
        "I_CD2", 11, 3, "3. どうやって買いますか", "3. Mua/Rút tiền bằng cách nào?",
        "3. どうやって買いますか\n1) A: 現金自動支払機はどうやって使いますか。\nB: キャッシュカードを入れて、暗証番号を押して、金額を押します。",
        "3. Mua/Rút tiền bằng cách nào?\n1) A: Cây ATM rút tiền tự động dùng thế nào vậy?\nB: Cho thẻ ngân hàng vào, nhập mã pin bí mật rồi bấm số tiền cần rút.",
        [
            {"itemIndex": "1", "prompt": "Trình tự thao tác cây ATM", "options": ["b d (Cho thẻ -> Nhập mã pin -> Rút tiền)", "a c"], "correctAnswer": "b d (Cho thẻ -> Nhập mã pin -> Rút tiền)", "explanation": "Cho thẻ, nhập mã PIN, chọn số tiền (b d)."}
        ]
    ),
    make_task(
        "I_CD2", 12, 4, "4. 〜てから、〜", "4. Sau khi làm xong... thì làm...",
        "4. 〜てから、〜\n1) A: 仕事が終わってから、何をしますか。\nB: 同僚と飲みに行きます。\n2) A: 国へ帰ってから、何をしますか。\nB: 父の会社で働きます。",
        "4. Sau khi làm xong... thì làm...\n1) A: Sau khi tan làm bạn sẽ làm gì?\nB: Đi uống một chầu với đồng nghiệp.\n2) A: Sau khi về nước bạn sẽ làm gì?\nB: Tôi sẽ làm việc ở công ty của bố.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Sau khi tan sở", "options": ["b (Đi uống)", "a (Về nhà ngủ)"], "correctAnswer": "b (Đi uống)", "explanation": "Đi uống với đồng nghiệp (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Sau khi về nước", "options": ["b (Làm ở công ty bố)", "a (Mở quán)"], "correctAnswer": "b (Làm ở công ty bố)", "explanation": "Làm ở công ty gia đình (b)."}
        ]
    ),
    make_task(
        "I_CD2", 13, 5, "5. 〜くて/〜で", "5. Nối tính từ (Vì/Và vừa... vừa...)",
        "5. 〜くて/〜で\n1) A: このパソコンはどうですか。\nB: 軽くて便利です。\n2) A: あのレストランはどうですか。\nB: 静かで料理が美味しいです。\n3) A: サントスさんはどんな人ですか。\nB: 背が高くて親切な人です。",
        "5. Nối tính từ (Vì/Và vừa... vừa...)\n1) A: Chiếc laptop này thế nào?\nB: Nhẹ và tiện lợi lắm.\n2) A: Nhà hàng đằng kia thế nào?\nB: Yên tĩnh và đồ ăn rất ngon.\n3) A: Anh Santos là người thế nào?\nB: Dáng người cao ráo và tính tình tốt bụng.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Chiếc máy tính", "options": ["a (Nhẹ và tiện lợi)", "c (Nặng)"], "correctAnswer": "a (Nhẹ và tiện lợi)", "explanation": "Nhẹ và tiện (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nhà hàng", "options": ["b (Yên tĩnh và ngon)", "a (Ồn ào)"], "correctAnswer": "b (Yên tĩnh và ngon)", "explanation": "Yên tĩnh và ngon (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Anh Santos", "options": ["b (Cao ráo tốt bụng)", "a (Thấp)"], "correctAnswer": "b (Cao ráo tốt bụng)", "explanation": "Cao và tốt bụng (b)."}
        ]
    )
]
LESSONS_11_20.append({
    "lessonNumber": 16, "level": "N5",
    "title": "Bài 16: Nối câu, Trình tự hành động & Cách thức",
    "subtitle": "第16課: て・てから・行き方・使い方・くてで",
    "tasks": l16_tasks
})

# ==================== LESSON 17 ====================
l17_tasks = [
    make_task(
        "I_CD2", 14, 1, "1. 〜ないでください", "1. Xin đừng làm...",
        "1. 〜ないでください\n1) A: ここにゴミを捨てないでください。\n2) A: ここでタバコを吸わないでください。\n3) A: 大声で話さないでください。",
        "1. Xin đừng làm...\n1) A: Xin đừng vứt rác ở đây.\n2) A: Xin đừng hút thuốc lá ở đây.\n3) A: Xin đừng nói to tiếng làm phiền người khác.",
        [
            {"itemIndex": "1", "prompt": "Đoạn 1", "options": ["× × ○", "○ ○ ×"], "correctAnswer": "× × ○", "explanation": "Biển cấm (× × ○)."},
            {"itemIndex": "2", "prompt": "Đoạn 2", "options": ["× ○ ×", "○ × ○"], "correctAnswer": "× ○ ×", "explanation": "Biển cấm (× ○ ×)."},
            {"itemIndex": "3", "prompt": "Đoạn 3", "options": ["○ × ×", "× × ○"], "correctAnswer": "○ × ×", "explanation": "Biển báo (○ × ×)."}
        ]
    ),
    make_task(
        "I_CD2", 15, 2, "2. 〜ないでください", "2. Nhắc nhở xin đừng làm...",
        "2. 〜ないでください\n1) A: 傘を忘れないでくださいね。\nB: はい、気をつけます。\n2) A: パスポートを無くさないでください。\nB: はい、大切にします。\n3) A: 無理をしないでください。\nB: ありがとうございます。",
        "2. Nhắc nhở xin đừng làm...\n1) A: Nhớ đừng để quên ô đấy nhé.\nB: Vâng, tôi sẽ chú ý.\n2) A: Đừng làm mất hộ chiếu nhé.\nB: Vâng, tôi sẽ giữ cẩn thận.\n3) A: Đừng làm việc quá sức nhé.\nB: Cảm ơn bạn nhiều.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nhắc nhở", "options": ["b (Đừng quên ô)", "a"], "correctAnswer": "b (Đừng quên ô)", "explanation": "Đừng quên ô (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nhắc nhở", "options": ["c (Đừng làm mất hộ chiếu)", "a"], "correctAnswer": "c (Đừng làm mất hộ chiếu)", "explanation": "Đừng làm mất hộ chiếu (c)."},
            {"itemIndex": "3", "prompt": "Câu 3: Nhắc nhở", "options": ["a (Đừng làm việc quá sức)", "b"], "correctAnswer": "a (Đừng làm việc quá sức)", "explanation": "Đừng quá sức (a)."}
        ]
    ),
    make_task(
        "I_CD2", 16, 3, "3. 〜なければなりません", "3. Bắt buộc phải làm...",
        "3. 〜なければなりません\n1) A: 今日病院へ行かなければなりません。\n2) A: レポートを出さなければなりません。",
        "3. Bắt buộc phải làm...\n1) A: Hôm nay tôi phải đi đến bệnh viện khám.\n2) A: Tôi phải nộp bản báo cáo trước hạn.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Phải làm gì?", "options": ["b (Phải đi khám)", "a"], "correctAnswer": "b (Phải đi khám)", "explanation": "Phải đi khám bệnh (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Phải làm gì?", "options": ["d (Phải nộp báo cáo)", "c"], "correctAnswer": "d (Phải nộp báo cáo)", "explanation": "Phải nộp báo cáo (d)."}
        ]
    ),
    make_task(
        "I_CD2", 17, 4, "4. 〜なくてもいいです", "4. Không cần phải làm...",
        "4. 〜なくてもいいです\n1) A: 明日来なくてもいいですか。\nB: はい、来なくてもいいです。\n2) A: お金を払わなくてもいいですか。\nB: はい、無料ですから払わなくてもいいです。\n3) A: 急がなくてもいいですか。\nB: はい、時間はたっぷりあります。",
        "4. Không cần phải làm...\n1) A: Ngày mai tôi không cần đến có được không?\nB: Vâng, ngày mai bạn không cần đến.\n2) A: Không cần trả tiền có được không?\nB: Vâng, vì miễn phí nên không cần trả tiền.\n3) A: Không cần vội vã đúng không?\nB: Đúng rồi, thời gian còn rất nhiều.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đến công ty", "options": ["b (Không cần đến)", "a"], "correctAnswer": "b (Không cần đến)", "explanation": "Không cần đến (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Trả tiền", "options": ["b (Không cần trả)", "a"], "correctAnswer": "b (Không cần trả)", "explanation": "Không cần trả tiền (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Vội vã", "options": ["a b (Không cần vội)", "c d"], "correctAnswer": "a b (Không cần vội)", "explanation": "Không cần vội (a b)."}
        ]
    )
]
LESSONS_11_20.append({
    "lessonNumber": 17, "level": "N5",
    "title": "Bài 17: Cấm đoán, Bắt buộc & Không cần thiết",
    "subtitle": "第17課: ないでください・なければ・なくてもいい",
    "tasks": l17_tasks
})

# ==================== LESSON 18 ====================
l18_tasks = [
    make_task(
        "I_CD2", 18, 1, "1. 〜ことができます", "1. Có thể làm được gì? (Năng lực)",
        "1. 〜ることができます\n1) A: ピアノを弾くことができますか。\nB: はい、少し弾くことができます。\n2) A: 漢字を何文字読むことができますか。\nB: 100字くらい読むことができます。\n3) A: 泳ぐことができますか。\nB: いいえ、全然泳ぐことができません。",
        "1. Có thể làm được gì? (Năng lực)\n1) A: Bạn có thể chơi đàn piano không?\nB: Có, tôi chơi được một chút.\n2) A: Bạn có thể đọc được bao nhiêu chữ Hán (Kanji)?\nB: Tôi đọc được khoảng 100 chữ.\n3) A: Bạn biết bơi không?\nB: Không, tôi hoàn toàn không biết bơi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Chơi piano", "options": ["c (Chơi được chút ít)", "a (Rất giỏi)"], "correctAnswer": "c (Chơi được chút ít)", "explanation": "Chơi được chút ít (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đọc chữ Kanji", "options": ["e (Khoảng 100 chữ)", "b (1000 chữ)"], "correctAnswer": "e (Khoảng 100 chữ)", "explanation": "Khoảng 100 chữ (e)."},
            {"itemIndex": "3", "prompt": "Câu 3: Bơi lội", "options": ["a (Không biết bơi)", "c (Bơi giỏi)"], "correctAnswer": "a (Không biết bơi)", "explanation": "Không biết bơi (a)."}
        ]
    ),
    make_task(
        "I_CD2", 19, 2, "2. 〜で〜ことができます", "2. Ở đâu có thể làm được gì?",
        "2. 〜で〜ことができます\n1) A: このホテルでインターネットを使うことができますか。\nB: はい、部屋で使うことができます。\n2) A: ここで日本円を両替することができますか。\nB: いいえ、ここではできません。銀行でできます。\n3) A: カードで払うことができますか。\nB: はい、VISAカードなら使えます。",
        "2. Ở đâu có thể làm được gì?\n1) A: Ở khách sạn này có dùng được internet không ạ?\nB: Có, dùng được ngay tại phòng bạn ạ.\n2) A: Ở đây có đổi được tiền yên Nhật không?\nB: Dạ ở đây không đổi được, bạn đổi ở ngân hàng nhé.\n3) A: Tôi thanh toán bằng thẻ được không?\nB: Vâng, thẻ VISA dùng được ạ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Internet tại phòng", "options": ["○ (Được)", "× (Không)"], "correctAnswer": "○ (Được)", "explanation": "Dùng được tại phòng (○)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đổi tiền tại đây", "options": ["× (Không được)", "○ (Được)"], "correctAnswer": "× (Không được)", "explanation": "Không được tại đây (×)."},
            {"itemIndex": "3", "prompt": "Câu 3: Quẹt thẻ thanh toán", "options": ["○ (Được)", "× (Không)"], "correctAnswer": "○ (Được)", "explanation": "Thanh toán thẻ được (○)."}
        ]
    ),
    make_task(
        "I_CD2", 20, 3, "3. 趣味は何ですか", "3. Sở thích của bạn là gì?",
        "3. 趣味は何ですか\n1) A: サントスさん、趣味は何ですか。\nB: ワインを集めることです。\n2) A: ミラーさん、趣味は何ですか。\nB: 音楽を聞くことと、料理を作ることです。",
        "3. Sở thích của bạn là gì?\n1) A: Anh Santos, sở thích của anh là gì?\nB: Là sưu tầm các loại rượu vang.\n2) A: Anh Miller, sở thích của anh là gì?\nB: Là nghe nhạc và nấu các món ăn ngon.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Sở thích anh Santos", "options": ["ワインを集める e", "スポーツ"], "correctAnswer": "ワインを集める e", "explanation": "Sưu tầm rượu vang (ワインを集める e)."},
            {"itemIndex": "2", "prompt": "Câu 2: Sở thích anh Miller", "options": ["音楽を聞く b / 料理 d", "読書"], "correctAnswer": "音楽を聞く b / 料理 d", "explanation": "Nghe nhạc và nấu ăn (音楽を聞く b / 料理 d)."}
        ]
    ),
    make_task(
        "I_CD2", 21, 4, "4. 〜まえに、〜", "4. Trước khi làm việc gì...",
        "4. 〜まえに、〜\n1) A: ごはんを食べるまえに、手を洗います。\n2) A: 寝るまえに、本を読みます。\n3) A: 日本へ来るまえに、日本語を勉強しました。",
        "4. Trước khi làm việc gì...\n1) A: Trước khi ăn cơm thì rửa sạch tay.\n2) A: Trước khi đi ngủ thì đọc sách một lúc.\n3) A: Trước khi sang Nhật thì đã học tiếng Nhật.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Trước khi ăn cơm", "options": ["b (Rửa tay)", "a"], "correctAnswer": "b (Rửa tay)", "explanation": "Rửa tay (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Trước khi đi ngủ", "options": ["b (Đọc sách)", "a"], "correctAnswer": "b (Đọc sách)", "explanation": "Đọc sách (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Trước khi sang Nhật", "options": ["a (Học tiếng Nhật)", "b"], "correctAnswer": "a (Học tiếng Nhật)", "explanation": "Học tiếng Nhật (a)."}
        ]
    )
]
LESSONS_11_20.append({
    "lessonNumber": 18, "level": "N5",
    "title": "Bài 18: Thể từ điển, Khả năng & Sở thích",
    "subtitle": "第18課: ことができます・趣味・まえに",
    "tasks": l18_tasks
})

# ==================== LESSON 19 ====================
l19_tasks = [
    make_task(
        "I_CD2", 22, 1, "1. 〜たことがあります", "1. Đã từng trải nghiệm việc gì?",
        "1. 〜たことがあります\n1) A: すき焼きを食べたことがありますか。\nB: はい、あります。とても美味しかったです。\n2) A: 富士山に登ったことがありますか。\nB: いいえ、一度もありません。\n3) A: 馬に乗ったことがありますか。\nB: はい、北海道で乗ったことがあります。",
        "1. Đã từng trải nghiệm việc gì?\n1) A: Bạn đã từng ăn món lẩu Sukiyaki chưa?\nB: Có rồi, ngon tuyệt vời luôn.\n2) A: Bạn đã từng leo núi Phú Sĩ chưa?\nB: Chưa, chưa một lần nào cả.\n3) A: Bạn đã từng cưỡi ngựa bao giờ chưa?\nB: Có, tôi từng cưỡi ngựa ở Hokkaido rồi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ăn lẩu Sukiyaki", "options": ["b (Đã từng ăn)", "a"], "correctAnswer": "b (Đã từng ăn)", "explanation": "Đã từng ăn (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Leo núi Phú Sĩ", "options": ["c (Chưa từng leo)", "a"], "correctAnswer": "c (Chưa từng leo)", "explanation": "Chưa từng leo (c)."},
            {"itemIndex": "3", "prompt": "Câu 3: Cưỡi ngựa", "options": ["b (Đã từng cưỡi ở Hokkaido)", "a"], "correctAnswer": "b (Đã từng cưỡi ở Hokkaido)", "explanation": "Đã từng cưỡi ngựa (b)."}
        ]
    ),
    make_task(
        "I_CD2", 23, 2, "2. 〜たり、〜たりします", "2. Liệt kê hành động đại diện",
        "2. 〜たり、〜たりします\n1) A: 休みの日は何をしましたか。\nB: 両親のうちへ行ったり、釣りをしに行ったりしました。\n2) A: 夏休みは何をしましたか。\nB: 山に登ったり、海で泳いだり、本を読んだりしました。",
        "2. Liệt kê hành động đại diện\n1) A: Ngày nghỉ bạn làm những gì?\nB: Tôi về nhà bố mẹ chơi, rồi đi câu cá.\n2) A: Kì nghỉ hè bạn đã làm những gì?\nB: Tôi leo núi, bơi ở biển và đọc sách ở Bali.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ngày nghỉ làm gì?", "options": ["①両親のうち ②釣りに行った", "買い物"], "correctAnswer": "①両親のうち ②釣りに行った", "explanation": "Về nhà bố mẹ và đi câu cá."},
            {"itemIndex": "2", "prompt": "Câu 2: Kì nghỉ hè", "options": ["③山に登った ④3 ⑤インドネシアのバリ ⑥海で泳いだ ⑦本を読んだ", "Ngủ ở nhà"], "correctAnswer": "③山に登った ④3 ⑤インドネシアのバリ ⑥海で泳いだ ⑦本を読んだ", "explanation": "Leo núi, đi Bali bơi biển, đọc sách."}
        ]
    ),
    make_task(
        "I_CD2", 24, 3, "3. 〜く/〜になりました", "3. Trở nên, biến đổi thành...",
        "3. 〜く/〜になりました\n1) A: 暖かくなりましたね。\nB: ええ、春になりましたね。\n2) A: 髪を切りましたね。短くなりましたね。\nB: ええ、さっぱりしました。\n3) A: 日本語が上手になりましたね。\nB: いいえ、まだまだです。",
        "3. Trở nên, biến đổi thành...\n1) A: Trời đã trở nên ấm áp hơn rồi nhỉ.\nB: Vâng, đã vào mùa xuân rồi mà.\n2) A: Bạn mới cắt tóc à, ngắn hẳn đi nhỉ.\nB: Vâng, nhẹ nhõm mát mẻ hẳn.\n3) A: Tiếng Nhật của bạn ngày càng tiến bộ giỏi hơn rồi đấy.\nB: Đâu có, tôi vẫn còn phải học nhiều.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Thời tiết ấm lên", "options": ["b (Trở nên ấm áp)", "a"], "correctAnswer": "b (Trở nên ấm áp)", "explanation": "Ấm áp hơn (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Tóc cắt ngắn", "options": ["a (Cắt ngắn đi)", "b"], "correctAnswer": "a (Cắt ngắn đi)", "explanation": "Cắt ngắn (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Tiếng Nhật", "options": ["a (Giỏi hơn)", "b"], "correctAnswer": "a (Giỏi hơn)", "explanation": "Tiếng Nhật giỏi hơn (a)."}
        ]
    )
]
LESSONS_11_20.append({
    "lessonNumber": 19, "level": "N5",
    "title": "Bài 19: Thể た, Đã từng & Liệt kê hành động",
    "subtitle": "第19課: たことがあります・たりたり・〜になります",
    "tasks": l19_tasks
})

# ==================== LESSON 20 ====================
l20_tasks = [
    make_task(
        "I_CD2", 25, 1, "1. 普通体会話(友達と)", "1. Thể thông thường (Hội thoại bạn bè)",
        "1. 普通体会話(友達と)\n1) A: 明日暇？\nB: うん、暇だよ。どこか行く？\n2) A: 宿題終わった？\nB: ううん、まだ。難しいよ。\n3) A: ビール飲む？\nB: うん、飲む！",
        "1. Thể thông thường (Hội thoại bạn bè)\n1) A: Mai rảnh không?\nB: Ừ rảnh, có đi đâu chơi không?\n2) A: Bài tập xong chưa?\nB: Chưa, còn nhiều và khó lắm.\n3) A: Làm cốc bia không?\nB: Ừ, uống chứ!",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ngày mai có rảnh không?", "options": ["a. うん、暇 (Rảnh)", "b. 忙しい"], "correctAnswer": "a. うん、暇 (Rảnh)", "explanation": "Rảnh rỗi (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Xong bài tập chưa?", "options": ["b. まだ (Chưa)", "a. 終わった"], "correctAnswer": "b. まだ (Chưa)", "explanation": "Chưa xong (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Uống bia không?", "options": ["b. 飲む (Có uống)", "a. 飲まない"], "correctAnswer": "b. 飲む (Có uống)", "explanation": "Có uống (b)."}
        ]
    ),
    make_task(
        "I_CD2", 26, 2, "2. 普通体会話(家族と)", "2. Thể thông thường (Nói chuyện với người nhà)",
        "2. 普通体会話(家族と)\n1) A: お父さん、昨日何食べたの？\nB: 居酒屋でお酒を飲んで、散歩したよ。\n2) A: 晩ご飯は何だった？\nB: カレーだったよ。ちょっと辛かった。\n3) A: 今度の日曜日、動物園行かない？\nB: うん、行きたい！",
        "2. Thể thông thường (Nói chuyện với người nhà)\n1) A: Bố ơi, hôm qua bố đi ăn gì thế?\nB: Bố đi nhậu với bạn rồi đi dạo mát.\n2) A: Bữa tối là món gì thế?\nB: Là món cà ri, hơi cay một chút.\n3) A: Chủ Nhật này đi sở thú chơi không con?\nB: Dạ có, con muốn đi!",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Bố đã làm gì hôm qua?", "options": ["のんだ・さんぽした (Uống rượu & đi dạo)", "Học bài"], "correctAnswer": "のんだ・さんぽした (Uống rượu & đi dạo)", "explanation": "Uống rượu và đi dạo (のんだ・さんぽした)."},
            {"itemIndex": "2", "prompt": "Câu 2: Bữa tối là món gì?", "options": ["カレーだった・からかった (Cà ri cay)", "Phở"], "correctAnswer": "カレーだった・からかった (Cà ri cay)", "explanation": "Cơm cà ri hơi cay (カレーだった・からかった)."},
            {"itemIndex": "3", "prompt": "Câu 3: Chủ Nhật", "options": ["いきたい (Muốn đi)", "Không muốn đi"], "correctAnswer": "いきたい (Muốn đi)", "explanation": "Muốn đi sở thú (いきたい)."}
        ]
    ),
    make_task(
        "I_CD2", 27, 3, "3. 普通体会話(小学生と)", "3. Thể thông thường (Trẻ em tiểu học)",
        "3. 普通体会話(小学生と)\n1) A: 学校楽しい？\nB: うん、楽しいよ。\n2) A: 勉強好き？\nB: ううん、あまり好きじゃない。\n3) A: 好きな先生は誰？\nB: 田中先生！",
        "3. Thể thông thường (Trẻ em tiểu học)\n1) A: Đi học ở trường có vui không cháu?\nB: Dạ vui lắm ạ.\n2) A: Cháu có thích học bài không?\nB: Dạ không thích lắm đâu ạ.\n3) A: Thầy cô nào cháu quý nhất?\nB: Thầy Tanaka ạ!",
        [
            {"itemIndex": "1", "prompt": "Đoạn 1", "options": ["b (Vui vẻ)", "a"], "correctAnswer": "b (Vui vẻ)", "explanation": "Đáp án b."},
            {"itemIndex": "2", "prompt": "Câu 2", "options": ["b (Không thích học lắm)", "a"], "correctAnswer": "b (Không thích học lắm)", "explanation": "Đáp án b."},
            {"itemIndex": "3", "prompt": "Câu 3", "options": ["b (Thầy Tanaka)", "a"], "correctAnswer": "b (Thầy Tanaka)", "explanation": "Đáp án b."}
        ]
    ),
    make_task(
        "I_CD2", 28, 4, "4. 普通体と丁寧体の会話(上司と)", "4. Hội thoại thể lịch sự và thân mật (Với cấp trên)",
        "4. 普通体と丁寧体の会話(上司と)\n1) 部長: 明日の会議の資料、できた？\n社員: はい、もうできております。\n2) 部長: ちょっと手伝ってくれる？\n社員: はい、かしこまりました。",
        "4. Hội thoại thể lịch sự và thân mật (Với cấp trên)\n1) Trưởng phòng: Tài liệu cuộc họp ngày mai xong chưa em?\nNhân viên: Dạ báo cáo anh, em đã chuẩn bị xong xuôi rồi ạ.\n2) Trưởng phòng: Em phụ anh một tay chỗ này được không?\nNhân viên: Dạ vâng, em xin nhận lời ngay ạ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Báo cáo tài liệu", "options": ["× (Khác biệt sắc thái)", "○"], "correctAnswer": "× (Khác biệt sắc thái)", "explanation": "Trưởng phòng dùng thể ngắn, nhân viên dùng kính ngữ (×)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nhờ vả cấp dưới", "options": ["× (Sắc thái trên dưới)", "○"], "correctAnswer": "× (Sắc thái trên dưới)", "explanation": "Cấp trên dùng thể thường, cấp dưới dùng thể lịch sự (×)."}
        ]
    )
]
LESSONS_11_20.append({
    "lessonNumber": 20, "level": "N5",
    "title": "Bài 20: Thể thông thường (Hội thoại đời thường)",
    "subtitle": "第20課: 普通体・友達家族との会話",
    "tasks": l20_tasks
})

print("Lessons 11 to 20 defined in LESSONS_11_20.")
