# -*- coding: utf-8 -*-
from choukai_helper import make_task

LESSONS_21_30 = []

# ==================== LESSON 21 ====================
l21_tasks = [
    make_task(
        "I_CD2", 29, 1, "1. 何と言いましたか", "1. Đã nói gì?",
        "1. 何と言いましたか\n1) A: 田中さんは何と言いましたか。\nB: 「ありがとう」と言って、大阪へ行きました。\n2) A: 木村さんは何と言いましたか。\nB: 「また来てください」と言って、岡山へ行きました。\n3) A: 秋田さんは何と言いましたか。\nB: 「こんばんは」と言いました。",
        "1. Đã nói gì?\n1) A: Anh Tanaka đã nói gì thế?\nB: Anh ấy nói \"Cảm ơn nhé\" rồi đi Osaka.\n2) A: Chị Kimura đã nói gì?\nB: Chị ấy nói \"Lần sau lại ghé chơi nhé\" rồi đi Okayama.\n3) A: Anh Akita đã nói gì?\nB: Anh ấy chào \"Chào buổi tối\".",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Tanaka nói gì và đi đâu?", "options": ["ありがとう・大阪", "さようなら・東京"], "correctAnswer": "ありがとう・大阪", "explanation": "Nói cảm ơn và đi Osaka."},
            {"itemIndex": "2", "prompt": "Câu 2: Kimura nói gì và đi đâu?", "options": ["また来てください・岡山", "また明日・京都"], "correctAnswer": "また来てください・岡山", "explanation": "Nói lần sau lại ghé và đi Okayama."},
            {"itemIndex": "3", "prompt": "Câu 3: Akita nói gì?", "options": ["こんばんは・秋田", "こんにちは"], "correctAnswer": "こんばんは・秋田", "explanation": "Nói Konbanwa (chào buổi tối)."}
        ]
    ),
    make_task(
        "I_CD2", 30, 2, "2. どう思いますか", "2. Bạn nghĩ thế nào? (Ý kiến cá nhân)",
        "2. どう思いますか\n1) A: 日本の交通についてどう思いますか。\nB: 便利だと思います。\n2) A: 物価についてどう思いますか。\nB: 高いと思います。\n3) A: 新しいデザインはどう思いますか。\nB: 素敵だと思います。",
        "2. Bạn nghĩ thế nào? (Ý kiến cá nhân)\n1) A: Bạn nghĩ sao về hệ thống giao thông Nhật Bản?\nB: Tôi nghĩ là rất thuận tiện.\n2) A: Bạn nghĩ sao về vật giá ở đây?\nB: Tôi thấy khá đắt đỏ.\n3) A: Bạn thấy mẫu thiết kế mới này thế nào?\nB: Tôi thấy rất tuyệt vời.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Giao thông Nhật", "options": ["○ c (Thuận tiện)", "×"], "correctAnswer": "○ c (Thuận tiện)", "explanation": "Rất tiện lợi (○ c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Vật giá", "options": ["× d (Đắt đỏ)", "○"], "correctAnswer": "× d (Đắt đỏ)", "explanation": "Đắt đỏ (× d)."},
            {"itemIndex": "3", "prompt": "Câu 3: Mẫu thiết kế", "options": ["○ a (Tuyệt đẹp)", "×"], "correctAnswer": "○ a (Tuyệt đẹp)", "explanation": "Tuyệt vời (○ a)."}
        ]
    ),
    make_task(
        "I_CD2", 31, 3, "3. 〜でしょう？", "3. Có đúng không nhỉ? (Xác nhận)",
        "3. 〜でしょう？\n1) A: 明日はいい天気でしょう？\nB: ええ、晴れると思いますよ。\n2) A: あの映画、おもしろいでしょう？\nB: うーん、あまりおもしろくなかったよ。",
        "3. Có đúng không nhỉ? (Xác nhận)\n1) A: Ngày mai chắc trời đẹp nhỉ?\nB: Ừ, tôi nghĩ là trời sẽ nắng đẹp đấy.\n2) A: Bộ phim đó hay lắm đúng không?\nB: Ừm, tôi thấy cũng không hay lắm đâu.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Thời tiết ngày mai", "options": ["○ (Đồng tình trời đẹp)", "×"], "correctAnswer": "○ (Đồng tình trời đẹp)", "explanation": "Đồng tình (○)."},
            {"itemIndex": "2", "prompt": "Câu 2: Bộ phim", "options": ["× (Không hay như mong đợi)", "○"], "correctAnswer": "× (Không hay như mong đợi)", "explanation": "Không hay (×)."}
        ]
    ),
    make_task(
        "I_CD2", 32, 4, "4. 総合問題", "4. Nghe hiểu tổng hợp",
        "4. 総合問題\n1) A: 留学生活はどうですか。\nB: 楽しいですが、たまに寂しいです。\n2) A: 明日のマラソン大会、走れますか。\nB: 足が痛いですから、無理だと思います。\n3) A: 日本語の勉強は役に立ちますか。\nB: はい、仕事にとても役に立ちます。",
        "4. Nghe hiểu tổng hợp\n1) A: Cuộc sống du học thế nào rồi?\nB: Vui vẻ nhưng thỉnh thoảng cũng thấy cô đơn.\n2) A: Giải chạy marathon ngày mai bạn chạy nổi không?\nB: Chân tôi đau nên tôi nghĩ là không thể cố được.\n3) A: Việc học tiếng Nhật có giúp ích gì không?\nB: Có chứ, rất có ích cho công việc của tôi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Du học", "options": ["楽しい・できない (Vui nhưng có lúc không)", "Chán"], "correctAnswer": "楽しい・できない (Vui nhưng có lúc không)", "explanation": "Vui vẻ nhưng có lúc khó."},
            {"itemIndex": "2", "prompt": "Câu 2: Chạy marathon", "options": ["無理だ (Không thể chạy)", "Có thể"], "correctAnswer": "無理だ (Không thể chạy)", "explanation": "Không thể vì đau chân."},
            {"itemIndex": "3", "prompt": "Câu 3: Tiếng Nhật", "options": ["役に立つ (Rất có ích)", "Không có ích"], "correctAnswer": "役に立つ (Rất có ích)", "explanation": "Rất có ích cho công việc."}
        ]
    )
]
LESSONS_21_30.append({
    "lessonNumber": 21, "level": "N5",
    "title": "Bài 21: Ý kiến cá nhân, Phỏng đoán & Trích dẫn",
    "subtitle": "第21課: と思います・と言いました・でしょう",
    "tasks": l21_tasks
})

# ==================== LESSON 22 ====================
l22_tasks = [
    make_task(
        "I_CD2", 33, 1, "1. これは[文]〜です", "1. Đây là thứ mà...",
        "1. これは[文]〜です\n1) A: これはわたしがきのう撮った写真です。\n2) A: これは母が作ったケーキです。\n3) A: これは父からもらった時計です。",
        "1. Đây là thứ mà...\n1) A: Đây là bức ảnh mà hôm qua tôi đã chụp đấy.\n2) A: Đây là chiếc bánh kem do chính tay mẹ tôi làm.\n3) A: Đây là chiếc đồng hồ mà tôi được bố tặng.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Bức ảnh", "options": ["a (Ảnh tự chụp)", "b"], "correctAnswer": "a (Ảnh tự chụp)", "explanation": "Ảnh tôi chụp (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Bánh kem", "options": ["a (Mẹ làm)", "b"], "correctAnswer": "a (Mẹ làm)", "explanation": "Mẹ làm (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Chiếc đồng hồ", "options": ["b (Bố tặng)", "a"], "correctAnswer": "b (Bố tặng)", "explanation": "Bố tặng (b)."}
        ]
    ),
    make_task(
        "I_CD2", 34, 2, "2. [文]人はだれですか", "2. Người đang làm hành động đó là ai?",
        "2. [文]人はだれですか\n1) A: あそこで新聞を読んでいる人はだれですか。\nB: 田中さんです。\n2) A: メガネをかけている人はだれですか。\nB: ミラーさんです。\n3) A: 赤いシャツを着ている人はだれですか。\nB: カリナさんです。\n4) A: 電話をかけている人はだれですか。\nB: 佐藤さんです。",
        "2. Người đang làm hành động đó là ai?\n1) A: Người đang đọc báo đằng kia là ai thế?\nB: Là anh Tanaka đấy.\n2) A: Người đang đeo kính là ai vậy?\nB: Là anh Miller.\n3) A: Người mặc áo sơ mi màu đỏ là ai thế?\nB: Là chị Karina.\n4) A: Người đang gọi điện thoại là ai vậy?\nB: Là chị Satou.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Người đọc báo", "options": ["e (Tanaka)", "a"], "correctAnswer": "e (Tanaka)", "explanation": "Tanaka đọc báo (e)."},
            {"itemIndex": "2", "prompt": "Câu 2: Người đeo kính", "options": ["g (Miller)", "b"], "correctAnswer": "g (Miller)", "explanation": "Miller đeo kính (g)."},
            {"itemIndex": "3", "prompt": "Câu 3: Người mặc áo đỏ", "options": ["a (Karina)", "c"], "correctAnswer": "a (Karina)", "explanation": "Karina mặc áo đỏ (a)."},
            {"itemIndex": "4", "prompt": "Câu 4: Người gọi điện thoại", "options": ["b (Satou)", "d"], "correctAnswer": "b (Satou)", "explanation": "Satou gọi điện (b)."}
        ]
    ),
    make_task(
        "I_CD2", 35, 3, "3. 〜約束/用事/時間があります", "3. Có cuộc hẹn / bận việc / có thời gian làm gì",
        "3. 〜約束/用事/時間があります\n1) A: 今晩一緒にご飯を食べませんか。\nB: すみません、友達と会う約束がありますから。\n2) A: 明日映画を見に行きませんか。\nB: すみません、病院へ行く用事があります。",
        "3. Có cuộc hẹn / bận việc / có thời gian làm gì\n1) A: Tối nay đi ăn cơm cùng tôi nhé?\nB: Xin lỗi bạn, tối nay tôi có hẹn gặp bạn rồi.\n2) A: Ngày mai đi xem phim không?\nB: Tiếc quá, tôi có việc bận phải đi bệnh viện rồi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Lý do từ chối", "options": ["a (Có hẹn với bạn)", "b"], "correctAnswer": "a (Có hẹn với bạn)", "explanation": "Hẹn gặp bạn (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Lý do từ chối", "options": ["b (Bận đi viện)", "a"], "correctAnswer": "b (Bận đi viện)", "explanation": "Có việc đi viện (b)."}
        ]
    )
]
LESSONS_21_30.append({
    "lessonNumber": 22, "level": "N5",
    "title": "Bài 22: Mệnh đề định ngữ bổ nghĩa danh từ",
    "subtitle": "第22課: 連体修飾・名詞修飾・約束・用事",
    "tasks": l22_tasks
})

# ==================== LESSON 23 ====================
l23_tasks = [
    make_task(
        "I_CD2", 37, 1, "1. どんなとき、〜ますか", "1. Khi nào thì làm việc gì?",
        "1. どんなとき、〜ますか\n1) A: どんなとき、タクシーに乗りますか。\nB: 雨が降ったとき、乗ります。\n2) A: どんなとき、辞書を引きますか。\nB: 言葉の意味がわからないとき、引きます。",
        "1. Khi nào thì làm việc gì?\n1) A: Khi nào thì bạn đi taxi?\nB: Khi trời đổ mưa to thì tôi bắt taxi.\n2) A: Khi nào thì bạn tra từ điển?\nB: Khi không hiểu nghĩa của từ thì tôi tra.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đi taxi khi nào?", "options": ["①c ②b ③a (Khi trời mưa)", "Khi rảnh"], "correctAnswer": "①c ②b ③a (Khi trời mưa)", "explanation": "Khi trời mưa (①c ②b ③a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Tra từ điển khi nào?", "options": ["①c ②a ③b (Khi không hiểu từ)", "Mỗi ngày"], "correctAnswer": "①c ②a ③b (Khi không hiểu từ)", "explanation": "Khi không hiểu từ (①c ②a ③b)."}
        ]
    ),
    make_task(
        "I_CD2", 38, 2, "2. 〜とき、〜ました", "2. Khi... thì đã...",
        "2. 〜とき、〜ました\n1) A: 図書館へ行くとき、本を返しました。\n2) A: 図書館へ行ったとき、友達に会いました。\n3) A: 朝出かけるとき、「行ってきます」と言いました。\n4) A: うちへ帰ったとき、「ただいま」と言いました。",
        "2. Khi... thì đã...\n1) A: Khi đi đến thư viện thì tôi tiện thể đem trả sách luôn.\n2) A: Khi đến thư viện rồi thì tôi tình cờ gặp bạn.\n3) A: Khi chuẩn bị rời nhà buổi sáng tôi nói 'Tôi đi đây'.\n4) A: Khi về đến nhà tôi cất tiếng chào 'Tôi đã về rồi đây'.",
        [
            {"itemIndex": "1", "prompt": "Câu 1", "options": ["①b (Trước khi tới thư viện)", "①a"], "correctAnswer": "①b (Trước khi tới thư viện)", "explanation": "Trả sách trước (①b)."},
            {"itemIndex": "2", "prompt": "Câu 2", "options": ["②a (Sau khi tới thư viện)", "②b"], "correctAnswer": "②a (Sau khi tới thư viện)", "explanation": "Gặp bạn sau khi tới (②a)."},
            {"itemIndex": "3", "prompt": "Câu 3", "options": ["③b (Khi đi)", "③a"], "correctAnswer": "③b (Khi đi)", "explanation": "Chào khi đi (③b)."},
            {"itemIndex": "4", "prompt": "Câu 4", "options": ["④b (Khi về)", "④a"], "correctAnswer": "④b (Khi về)", "explanation": "Chào khi về (④b)."}
        ]
    ),
    make_task(
        "I_CD2", 39, 3, "3. 〜と、〜", "3. Hễ... thì... (Điều kiện tất nhiên)",
        "3. 〜と、〜\n1) A: このボタンを押すと、お釣りが出ます。\n2) A: あの角を右へ曲がると、郵便局があります。\n3) A: まっすぐ行くと、橋があります。",
        "3. Hễ... thì... (Điều kiện tất nhiên)\n1) A: Bấm chiếc nút này thì tiền thừa sẽ rơi ra.\n2) A: Rẽ phải ở góc ngã rẽ đằng kia thì sẽ thấy bưu điện.\n3) A: Cứ đi thẳng tiếp thì sẽ nhìn thấy một cây cầu.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Bấm nút", "options": ["a (Tiền thừa ra)", "b"], "correctAnswer": "a (Tiền thừa ra)", "explanation": "Ra tiền thừa (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Rẽ phải", "options": ["a (Thấy bưu điện)", "b"], "correctAnswer": "a (Thấy bưu điện)", "explanation": "Có bưu điện (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đi thẳng", "options": ["b (Thấy cây cầu)", "a"], "correctAnswer": "b (Thấy cây cầu)", "explanation": "Có cây cầu (b)."}
        ]
    ),
    make_task(
        "I_CD2", 40, 4, "4. 〜と、〜があります", "4. Chỉ đường & Địa điểm",
        "4. 〜と、〜があります\n1) A: すみません、市役所はどこですか。\nB: あの信号を渡ると、左側にあります。",
        "4. Chỉ đường & Địa điểm\n1) A: Xin hỏi ủy ban nhân dân thành phố ở đâu ạ?\nB: Băng qua cột đèn tín hiệu đằng kia, tòa nhà nằm ở phía tay trái.",
        [
            {"itemIndex": "1", "prompt": "Cách đến ủy ban", "options": ["c m (Băng qua đèn đỏ rẽ trái)", "a b"], "correctAnswer": "c m (Băng qua đèn đỏ rẽ trái)", "explanation": "Băng qua đèn tín hiệu ở bên trái (c m)."}
        ]
    )
]
LESSONS_21_30.append({
    "lessonNumber": 23, "level": "N5",
    "title": "Bài 23: Khi (とき) & Điều kiện tự nhiên (と)",
    "subtitle": "第23課: とき・と・道案内・条件",
    "tasks": l23_tasks
})

# ==================== LESSON 24 ====================
l24_tasks = [
    make_task(
        "I_CD2", 41, 1, "1. 〜をくれました", "1. Đã tặng/cho tôi cái gì?",
        "1. 〜をくれました\n1) A: そのセーター、素敵ですね。\nB: 姉がくれたんです。\n2) A: その帽子、可愛いですね。\nB: 友達がくれました。\n3) A: このワインはどうしたんですか。\nB: 社長にもらいました。",
        "1. Đã tặng/cho tôi cái gì?\n1) A: Chiếc áo len đó đẹp quá bạn ơi.\nB: Chị gái tôi tặng cho tôi đấy.\n2) A: Chiếc mũ đó trông xinh xắn ghê.\nB: Bạn tôi tặng cho tôi đó.\n3) A: Chai rượu vang này ở đâu ra thế?\nB: Tôi được giám đốc tặng cho.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Áo len", "options": ["セーター くれた (Chị tặng)", "Mua"], "correctAnswer": "セーター くれた (Chị tặng)", "explanation": "Chị gái tặng (セーター くれた)."},
            {"itemIndex": "2", "prompt": "Câu 2: Chiếc mũ", "options": ["帽子 くれた (Bạn tặng)", "Nhặt được"], "correctAnswer": "帽子 くれた (Bạn tặng)", "explanation": "Bạn tặng (帽子 くれた)."},
            {"itemIndex": "3", "prompt": "Câu 3: Rượu vang", "options": ["ワイン もらった (Được tặng)", "Tự mua"], "correctAnswer": "ワイン もらった (Được tặng)", "explanation": "Được giám đốc tặng (ワイン もらった)."}
        ]
    ),
    make_task(
        "I_CD2", 42, 2, "2. 〜てもらいました/くれました", "2. Được ai giúp / Ai giúp cho mình",
        "2. 〜てもらいました/くれました\n1) A: 引っ越しは大変でしたね。\nB: 同僚に手伝ってもらいました。\n2) A: 道がわかりませんでした。\nB: 親切な人が教えてくれました。\n3) A: 日本語のレポートはどうしましたか。\nB: 先生に直してもらいました。",
        "2. Được ai giúp / Ai giúp cho mình\n1) A: Việc chuyển nhà vất vả ghê nhỉ.\nB: Tôi được đồng nghiệp giúp chuyển đồ hộ.\n2) A: Tôi bị lạc đường không biết lối đi.\nB: May mà có một người tốt bụng chỉ đường giúp cho.\n3) A: Bài báo cáo tiếng Nhật bạn xử lý thế nào?\nB: Tôi được thầy giáo sửa lỗi giúp cho.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Chuyển nhà", "options": ["b (Đồng nghiệp giúp)", "a"], "correctAnswer": "b (Đồng nghiệp giúp)", "explanation": "Được đồng nghiệp giúp (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Chỉ đường", "options": ["a (Người tốt chỉ hộ)", "b"], "correctAnswer": "a (Người tốt chỉ hộ)", "explanation": "Người tốt chỉ hộ (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Sửa báo cáo", "options": ["a (Thầy giáo sửa cho)", "b"], "correctAnswer": "a (Thầy giáo sửa cho)", "explanation": "Thầy giáo sửa hộ (a)."}
        ]
    ),
    make_task(
        "I_CD2", 43, 3, "3. 〜てくれます/あげます", "3. Giúp đỡ lẫn nhau",
        "3. 〜てくれます/あげます\n1) A: 部屋を掃除してあげました。\nB: ご飯を作ってくれました。\n2) A: 駅まで迎えに行ってあげました。",
        "3. Giúp đỡ lẫn nhau\n1) A: Tôi dọn dẹp phòng giúp bạn, còn bạn ấy nấu bữa cơm ngon đãi tôi.\n2) A: Tôi đã ra nhà ga đón bạn ấy về.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Dọn dẹp & nấu ăn", "options": ["掃除して 作って", "Không làm"], "correctAnswer": "掃除して 作って", "explanation": "Dọn phòng và nấu ăn."},
            {"itemIndex": "2", "prompt": "Câu 2: Đi đón ở ga", "options": ["取りに行って (Đón về)", "Không đi"], "correctAnswer": "取りに行って (Đón về)", "explanation": "Đi đón về."}
        ]
    ),
    make_task(
        "I_CD2", 44, 4, "4. 総合問題", "4. Bài tập tổng hợp",
        "4. 総合問題\n1) A: チンさん、山田さんに傘を貸してあげましたね。\n2) A: 男の人がチンさんに荷物を持ってくれました。\n3) A: チンさんは佐藤さんに案内してもらいました。",
        "4. Bài tập tổng hợp\n1) A: Bạn Chin cho anh Yamada mượn chiếc ô.\n2) A: Một người đàn ông đã xách hành lý hộ bạn Chin.\n3) A: Bạn Chin được chị Satou hướng dẫn tham quan.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ai giúp ai?", "options": ["チン 山田 (Chin cho Yamada mượn)", "Ngược lại"], "correctAnswer": "チン 山田 (Chin cho Yamada mượn)", "explanation": "Chin giúp Yamada."},
            {"itemIndex": "2", "prompt": "Câu 2: Ai xách đồ?", "options": ["男の人 チンさん (Người nam xách cho Chin)", "Chin tự xách"], "correctAnswer": "男の人 チンさん (Người nam xách cho Chin)", "explanation": "Người nam xách cho Chin."},
            {"itemIndex": "3", "prompt": "Câu 3: Ai dẫn đường?", "options": ["チン 佐藤 (Chin được Satou hướng dẫn)", "Không ai"], "correctAnswer": "チン 佐藤 (Chin được Satou hướng dẫn)", "explanation": "Chin được Satou hướng dẫn."}
        ]
    )
]
LESSONS_21_30.append({
    "lessonNumber": 24, "level": "N5",
    "title": "Bài 24: Cho - Nhận hành động (~てあげる/もらう/くれる)",
    "subtitle": "第24課: くれます・てもらいました・親切",
    "tasks": l24_tasks
})

# ==================== LESSON 25 ====================
l25_tasks = [
    make_task(
        "I_CD2", 45, 1, "1. [もし]〜たら、〜", "1. Nếu... thì... (Giả định tương lai)",
        "1. [もし]〜たら、〜\n1) A: もし1億円あったら、何をしますか。\nB: 世界旅行をしたいです。\n2) A: 明日雨が降ったら、どうしますか。\nB: うちで本を読みます。\n3) A: 時間があったら、映画を見に行きませんか。\nB: ええ、行きましょう。",
        "1. Nếu... thì... (Giả định tương lai)\n1) A: Giả sử nếu có 100 triệu yên, bạn sẽ làm gì?\nB: Tôi muốn đi du lịch vòng quanh thế giới.\n2) A: Nếu ngày mai trời mưa thì bạn tính sao?\nB: Tôi sẽ ở nhà đọc sách.\n3) A: Nếu có thời gian rảnh, đi xem phim với tôi nhé?\nB: Vâng, cùng đi nào.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nếu có 100 triệu yên", "options": ["a (Du lịch thế giới)", "b"], "correctAnswer": "a (Du lịch thế giới)", "explanation": "Đi du lịch thế giới (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nếu mai trời mưa", "options": ["b (Ở nhà đọc sách)", "a"], "correctAnswer": "b (Ở nhà đọc sách)", "explanation": "Ở nhà đọc sách (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Nếu rảnh rỗi", "options": ["a (Đi xem phim)", "b"], "correctAnswer": "a (Đi xem phim)", "explanation": "Đi xem phim (a)."}
        ]
    ),
    make_task(
        "I_CD2", 46, 2, "2. 〜たら、〜てください", "2. Sau khi... thì xin hãy...",
        "2. 〜たら、〜てください\n1) A: 駅に着いたら、電話してください。\n2) A: 宿題が終わったら、遊びに来てください。\n3) A: 会議が始まったら、静かにしてください。\n4) A: 荷物が届いたら、教えてください。\n5) A: レポートを読んだら、返してください。",
        "2. Sau khi... thì xin hãy...\n1) A: Khi nào đến ga thì hãy gọi điện thoại cho tôi nhé.\n2) A: Làm xong bài tập rồi thì sang nhà mình chơi nhé.\n3) A: Khi cuộc họp bắt đầu thì xin hãy giữ trật tự.\n4) A: Khi hàng về đến nơi thì báo cho tôi biết với nhé.\n5) A: Đọc xong bản báo cáo thì trả lại cho tôi nhé.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đến ga", "options": ["①e (Gọi điện thoại)", "①a"], "correctAnswer": "①e (Gọi điện thoại)", "explanation": "Gọi điện thoại (①e)."},
            {"itemIndex": "2", "prompt": "Câu 2: Xong bài tập", "options": ["②a (Sang chơi)", "②b"], "correctAnswer": "②a (Sang chơi)", "explanation": "Sang chơi (②a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Bắt đầu họp", "options": ["③c (Trật tự)", "③d"], "correctAnswer": "③c (Trật tự)", "explanation": "Giữ trật tự (③c)."},
            {"itemIndex": "4", "prompt": "Câu 4: Hàng đến", "options": ["④f (Báo tin)", "④a"], "correctAnswer": "④f (Báo tin)", "explanation": "Báo tin (④f)."},
            {"itemIndex": "5", "prompt": "Câu 5: Đọc xong báo cáo", "options": ["⑤d (Trả lại)", "⑤b"], "correctAnswer": "⑤d (Trả lại)", "explanation": "Trả lại (⑤d)."}
        ]
    ),
    make_task(
        "I_CD2", 47, 3, "3. 〜ても、〜", "3. Dù... thì vẫn...",
        "3. 〜ても、〜\n1) A: 雨が降っても、出かけますか。\nB: はい、降っても出かけます。\n2) A: 高くても、買いますか。\nB: いいえ、高かったら買いません。\n3) A: 忙しくても、日本語を勉強しますか。\nB: はい、毎日勉強します。",
        "3. Dù... thì vẫn...\n1) A: Cho dù trời mưa bạn vẫn ra ngoài chứ?\nB: Vâng, dù mưa tôi vẫn đi.\n2) A: Dù đắt bạn vẫn mua chứ?\nB: Không, nếu đắt thì tôi không mua đâu.\n3) A: Dù bận rộn bạn vẫn học tiếng Nhật đều đặn chứ?\nB: Vâng, ngày nào tôi cũng học.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Dù mưa", "options": ["a (Vẫn đi ra ngoài)", "b"], "correctAnswer": "a (Vẫn đi ra ngoài)", "explanation": "Vẫn đi (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nếu đắt", "options": ["b (Không mua)", "a"], "correctAnswer": "b (Không mua)", "explanation": "Không mua (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Dù bận", "options": ["b (Vẫn học đều)", "a"], "correctAnswer": "b (Vẫn học đều)", "explanation": "Vẫn học đều đặn (b)."}
        ]
    )
]
LESSONS_21_30.append({
    "lessonNumber": 25, "level": "N5",
    "title": "Bài 25: Thể điều kiện giả định (たら/ても)",
    "subtitle": "第25課: もし〜たら・〜ても・仮定条件",
    "tasks": l25_tasks
})

# ==================== LESSON 26 (Book 2 N4) ====================
l26_tasks = [
    make_task(
        "II_CD1", 2, 1, "1. 会社の人はどうですか。どうしてですか。", "1. Tình hình đồng nghiệp & Vì sao thế?",
        "1. 会社の人はどうですか。どうしてですか。\n例) A: おはようございます。あ、忙しいんですか。\nB: ええ、ちょっと手伝っていただけませんか。会議の資料です。9時半からです。\n1) B: おはようございます。\nA: 調子が悪いんですか。\nB: ええ、きのうの晩12時まで残業しました。\nA: 12時？何を作っていたんですか。\nB: アメリカ出張の資料を作っていました。\n2) B: ふあーっ、おはよう。\nA: 眠いんですか。\nB: ええ、きのうの晩お酒を飲みましたから。朝3時までです。高校のときの友達と。\n3) B: おはようございます。\nA: 今日は元気ですね。いいことがあったんですか。\nB: ええ、きのうの晩、子どもが生まれたんです。女の子です。",
        "1. Tình hình đồng nghiệp & Vì sao thế?\nVí dụ) A: Chào buổi sáng. Ơ, anh đang bận lắm à?\nB: Ừ, giúp anh một tay được không? Tài liệu cuộc họp 9h30 đấy.\n1) B: Chào buổi sáng.\nA: Trông bạn mệt mỏi thế, không khỏe à?\nB: Ừ, tối qua tớ phải tăng ca tới 12h đêm làm tài liệu công tác Mỹ.\n2) B: Oáp... chào buổi sáng.\nA: Buồn ngủ lắm à?\nB: Ừ, tối qua đi nhậu với bạn cấp 3 đến tận 3h sáng.\n3) B: Chào buổi sáng!\nA: Hôm nay tươi tỉnh thế, có chuyện gì vui à?\nB: Ừ, tối qua vợ tớ vừa sinh em bé, là một bé gái!",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đồng nghiệp 1 sao thế?", "options": ["d ③ (Mệt mỏi vì tăng ca đến 12h làm tài liệu)", "a"], "correctAnswer": "d ③ (Mệt mỏi vì tăng ca đến 12h làm tài liệu)", "explanation": "Tăng ca đêm làm tài liệu (d ③)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đồng nghiệp 2 sao thế?", "options": ["e ① (Buồn ngủ vì nhậu đến 3h sáng với bạn)", "b"], "correctAnswer": "e ① (Buồn ngủ vì nhậu đến 3h sáng với bạn)", "explanation": "Buồn ngủ vì uống rượu đến 3h sáng (e ①)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đồng nghiệp 3 sao thế?", "options": ["b ⑤ (Vui vẻ vì vừa sinh con gái)", "c"], "correctAnswer": "b ⑤ (Vui vẻ vì vừa sinh con gái)", "explanation": "Vui vì sinh con gái (b ⑤)."}
        ]
    ),
    make_task(
        "II_CD1", 3, 2, "2. 学生はいろいろなことをします。どうしてですか。", "2. Học sinh làm nhiều việc khác nhau, vì sao?",
        "2. 学生はいろいろなことをします。どうしてですか。\n例) A: さあ、始めましょう。皆さん、きのうの宿題は？\nB: 先生、宿題を忘れました。あした出してもいいですか。\n1) B: 先生、すみません。帰ってもいいですか。\nA: どうしたんですか。調子が悪いんですか。\nB: ええ、お腹がとても痛いんです。\n2) B: 先生、辞書を貸していただけませんか。\nA: どうしたんですか。\nB: うちに忘れたんです。\n3) B: 先生、ちょっと手伝っていただけませんか。\nA: どうしたんですか。\nB: この漢字の読み方がわからないんです。",
        "2. Học sinh làm nhiều việc khác nhau, vì sao?\nVí dụ) Thầy giáo: Nào, bài tập hôm qua đâu em?\nB: Em quên ở nhà rồi ạ, mai em nộp được không thầy?\n1) B: Thầy ơi cho em xin về sớm được không ạ? Bụng em đau quặn thắt lại rồi ạ.\n2) B: Thầy ơi cho em mượn từ điển một lát được không? Em để quên ở nhà rồi ạ.\n3) B: Thầy ơi chỉ giúp em chữ Hán này đọc thế nào với ạ?",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Xin về sớm vì sao?", "options": ["b (Đau bụng)", "a"], "correctAnswer": "b (Đau bụng)", "explanation": "Bị đau bụng (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Mượn từ điển vì sao?", "options": ["b (Quên ở nhà)", "a"], "correctAnswer": "b (Quên ở nhà)", "explanation": "Để quên ở nhà (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Nhờ thầy vì sao?", "options": ["a (Không biết cách đọc chữ Hán)", "b"], "correctAnswer": "a (Không biết cách đọc chữ Hán)", "explanation": "Không biết đọc chữ Hán (a)."}
        ]
    ),
    make_task(
        "II_CD1", 4, 3, "3. 〜んですが、〜ていただけませんか", "3. Nhờ vả lịch sự thể んですが",
        "3. 〜んですが、〜ていただけませんか\n1) A: 生け花を習いたいんですが、いい先生を紹介していただけませんか。\n2) A: 市役所へ行きたいんですが、道を教えていただけませんか。\n3) A: カメラが壊れたんですが、見ていただけませんか。",
        "3. Nhờ vả lịch sự thể んですが\n1) A: Tôi muốn học cắm hoa Ikebana, bạn giới thiệu giúp tôi một giáo viên giỏi được không?\n2) A: Tôi muốn đến tòa thị chính, bạn chỉ đường giúp tôi được không?\n3) A: Chiếc máy ảnh của tôi bị hỏng mất rồi, bạn xem giúp tôi một chút được không?",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Học cắm hoa", "options": ["⑤ a (Giới thiệu giáo viên)", "① b"], "correctAnswer": "⑤ a (Giới thiệu giáo viên)", "explanation": "Giới thiệu giáo viên cắm hoa (⑤ a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đến ủy ban", "options": ["④ a (Chỉ đường hộ)", "② b"], "correctAnswer": "④ a (Chỉ đường hộ)", "explanation": "Chỉ đường đi (④ a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Máy ảnh hỏng", "options": ["② b (Xem giúp máy ảnh)", "③ a"], "correctAnswer": "② b (Xem giúp máy ảnh)", "explanation": "Nhờ xem hộ máy ảnh (② b)."}
        ]
    ),
    make_task(
        "II_CD1", 5, 4, "4. 〜んですが、〜たらいいですか", "4. Xin lời khuyên: Tôi nên làm thế nào?",
        "4. 〜んですが、〜たらいいですか\n1) A: パスポートを落としたんですが、どうしたらいいですか。\nB: すぐ警察へ行ってください。\n2) A: 頭が痛いんですが、どうしたらいいですか。\nB: 早く薬を飲んで寝てください。\n3) A: ゴミを捨てたいんですが、どこに出したらいいですか。\nB: 燃えるゴミは月曜日の朝出してください。",
        "4. Xin lời khuyên: Tôi nên làm thế nào?\n1) A: Tôi đánh rơi mất hộ chiếu rồi, giờ tôi phải làm sao đây?\nB: Bạn hãy đến ngay đồn cảnh sát gần nhất nhé.\n2) A: Đầu tôi đau nhức quá, giờ nên làm thế nào?\nB: Uống thuốc giảm đau rồi đi ngủ sớm đi bạn.\n3) A: Tôi muốn vứt rác, phải bỏ ra chỗ nào và vào lúc nào?\nB: Rác cháy được bạn mang bỏ vào sáng thứ Hai nhé.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Mất hộ chiếu", "options": ["h ④ (Đi đồn cảnh sát)", "f ①"], "correctAnswer": "h ④ (Đi đồn cảnh sát)", "explanation": "Đến đồn cảnh sát (h ④)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đau đầu", "options": ["f ① (Uống thuốc và ngủ)", "g ③"], "correctAnswer": "f ① (Uống thuốc và ngủ)", "explanation": "Uống thuốc đi ngủ (f ①)."},
            {"itemIndex": "3", "prompt": "Câu 3: Vứt rác", "options": ["g ③ (Sáng thứ Hai vứt rác cháy)", "h ④"], "correctAnswer": "g ③ (Sáng thứ Hai vứt rác cháy)", "explanation": "Bỏ rác sáng thứ 2 (g ③)."}
        ]
    )
]
LESSONS_21_30.append({
    "lessonNumber": 26, "level": "N4",
    "title": "Bài 26: Thể giải thích んです, Tình huống & Nhờ vả",
    "subtitle": "第26課: 〜んです・どうして・〜ていただけませんか",
    "tasks": l26_tasks
})

# ==================== LESSON 27 ====================
l27_tasks = [
    make_task(
        "II_CD1", 6, 1, "1. 可能動詞", "1. Động từ thể khả năng",
        "1. 可能動詞\n1) A: 漢字が書けますか。\nB: いいえ、読めますが、書けません。\n2) A: 日本語の新聞が読めますか。\nB: いいえ、読めません。\n3) A: お酒が飲めますか。\nB: ええ、ビールなら飲めます。",
        "1. Động từ thể khả năng\n1) A: Bạn có viết được chữ Hán không?\nB: Đọc thì tôi đọc được, chứ viết thì không viết được.\n2) A: Bạn đọc được báo tiếng Nhật không?\nB: Không, tôi chưa đọc được báo.\n3) A: Bạn có uống được rượu không?\nB: Bia thì tôi uống được một chút.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Viết chữ Hán", "options": ["① b (Không viết được, chỉ đọc được)", "① a"], "correctAnswer": "① b (Không viết được, chỉ đọc được)", "explanation": "Đọc được nhưng không viết được (① b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đọc báo tiếng Nhật", "options": ["⑥ f (Không đọc được)", "② d"], "correctAnswer": "⑥ f (Không đọc được)", "explanation": "Không đọc được báo (⑥ f)."},
            {"itemIndex": "3", "prompt": "Câu 3: Uống bia rượu", "options": ["⑤ c (Uống được bia)", "④ e"], "correctAnswer": "⑤ c (Uống được bia)", "explanation": "Uống được bia (⑤ c)."}
        ]
    ),
    make_task(
        "II_CD1", 7, 2, "2. (場所)で[可能動詞]", "2. Có thể làm gì ở địa điểm nào?",
        "2. (場所)で[可能動詞]\n1) A: 教室で昼ごはんを食べることができますか。\nB: いいえ、教室では食べられません。食堂で食べてください。\n2) A: 寮の部屋で料理が作れますか。\nB: 部屋ではできません。台所で作ってください。\n3) A: ここでタバコが吸えますか。\nB: はい、灰皿があるところで吸えますよ。",
        "2. Có thể làm gì ở địa điểm nào?\n1) A: Ở trong lớp học có ăn trưa được không?\nB: Không, trong lớp không được ăn, bạn xuống nhà ăn ăn nhé.\n2) A: Ở trong phòng ký túc xá có nấu nướng được không?\nB: Trong phòng không nấu được đâu, phải nấu ở gian bếp chung.\n3) A: Ở đây có hút thuốc được không?\nB: Chỗ có gạt tàn thì hút được đấy bạn.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ăn trưa trong lớp", "options": ["×→食堂でする (Phải ăn ở nhà ăn)", "○"], "correctAnswer": "×→食堂でする (Phải ăn ở nhà ăn)", "explanation": "Phải ăn ở nhà ăn (×→食堂でする)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nấu ăn trong phòng", "options": ["×→(小さい)台所でする (Nấu ở bếp)", "○"], "correctAnswer": "×→(小さい)台所でする (Nấu ở bếp)", "explanation": "Nấu ở gian bếp (×→台所でする)."},
            {"itemIndex": "3", "prompt": "Câu 3: Hút thuốc chỗ có gạt tàn", "options": ["○ (Được phép)", "×"], "correctAnswer": "○ (Được phép)", "explanation": "Hút thuốc được ở nơi có gạt tàn (○)."}
        ]
    ),
    make_task(
        "II_CD1", 8, 3, "3. 見えます、聞こえます", "3. Nhìn thấy & Nghe thấy tự nhiên",
        "3. 見えます、聞こえます\n1) A: 部屋の窓から何が見えますか。\nB: 海が見えます。\n2) A: 外から何の音が聞こえますか。\nB: 波の音が聞こえます。\n3) A: 鳥の声が聞こえますね。\nB: ええ、きれいな声ですね。",
        "3. Nhìn thấy & Nghe thấy tự nhiên\n1) A: Từ cửa sổ phòng bạn nhìn thấy cảnh gì?\nB: Nhìn thấy biển rộng bao la.\n2) A: Từ bên ngoài nghe thấy tiếng gì thế?\nB: Nghe thấy tiếng sóng biển rì rào vỗ bờ.\n3) A: Nghe thấy tiếng chim hót líu lo nhỉ.\nB: Vâng, tiếng hót trong trẻo dễ thương ghê.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nhìn thấy từ cửa sổ", "options": ["④ a (Nhìn thấy biển)", "① b"], "correctAnswer": "④ a (Nhìn thấy biển)", "explanation": "Nhìn thấy biển (④ a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Tiếng sóng biển", "options": ["① b (Nghe tiếng sóng)", "② a"], "correctAnswer": "① b (Nghe tiếng sóng)", "explanation": "Tiếng sóng biển (① b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Tiếng chim hót", "options": ["② a (Tiếng chim)", "③ b"], "correctAnswer": "② a (Tiếng chim)", "explanation": "Tiếng chim hót (② a)."}
        ]
    ),
    make_task(
        "II_CD1", 9, 4, "4. 総合問題", "4. Bài nghe tổng hợp",
        "4. 総合問題\n新しく建てるマンションの設備について話しています。\n部屋にエアコンがあります。ペットは飼えません。\n海の景色が見えます。電車の音は聞こえません。",
        "4. Bài nghe tổng hợp\nNói về trang thiết bị căn hộ chung cư mới:\nTrong phòng có trang bị máy lạnh. Không được nuôi thú cưng.\nTừ phòng có thể ngắm cảnh biển. Không bị ảnh hưởng bởi tiếng ồn tàu điện.",
        [
            {"itemIndex": "1", "prompt": "Tiện ích & quy định căn hộ", "options": ["①あります ②ありません ③部屋 ④聞こえます ⑤見えません ⑥できません ⑦できません", "Không có máy lạnh"], "correctAnswer": "①あります ②ありません ③部屋 ④聞こえます ⑤見えません ⑥できません ⑦できません", "explanation": "Có điều hòa, không nuôi thú, thấy cảnh biển, không ồn."}
        ]
    )
]
LESSONS_21_30.append({
    "lessonNumber": 27, "level": "N4",
    "title": "Bài 27: Động từ thể khả năng & Giác quan (見えます/聞こえます)",
    "subtitle": "第27課: 可能形・見える・聞こえる・できる",
    "tasks": l27_tasks
})

# ==================== LESSON 28 ====================
l28_tasks = [
    make_task(
        "II_CD1", 10, 1, "1. 〜ながら〜", "1. Vừa làm việc này vừa làm việc kia",
        "1. 〜ながら〜\n1) A: いつも音楽を聞きながら勉強しています。\n2) A: テレビを見ながらご飯を食べてはいけません。\n3) A: 歌を歌いながら運転します。",
        "1. Vừa làm việc này vừa làm việc kia\n1) A: Tôi lúc nào cũng vừa nghe nhạc vừa học bài.\n2) A: Không được vừa ăn cơm vừa xem tivi đâu đấy.\n3) A: Tôi vừa lái xe vừa ngân nga hát.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Vừa nghe nhạc vừa học", "options": ["a (Nghe nhạc học bài)", "b"], "correctAnswer": "a (Nghe nhạc học bài)", "explanation": "Vừa nghe nhạc vừa học (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Vừa xem tivi vừa ăn", "options": ["a (Xem tivi ăn cơm)", "b"], "correctAnswer": "a (Xem tivi ăn cơm)", "explanation": "Vừa xem tivi vừa ăn (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Vừa lái xe vừa hát", "options": ["b (Lái xe ca hát)", "a"], "correctAnswer": "b (Lái xe ca hát)", "explanation": "Vừa lái xe vừa hát (b)."}
        ]
    ),
    make_task(
        "II_CD1", 11, 2, "2. 〜ながら〜 (Công việc & Đời sống)", "2. Vừa đi học vừa đi làm",
        "2. 〜ながら〜\n1) A: アルバイトをしながら大学に通っています。\n2) A: 働きながら日本語学校で勉強しています。\n3) A: 子育てをしながら仕事を続けています。",
        "2. Vừa đi học vừa đi làm\n1) A: Tôi vừa đi làm thêm vừa theo học đại học.\n2) A: Tôi vừa đi làm vừa học ở trường tiếng Nhật.\n3) A: Chị ấy vừa nuôi con nhỏ vừa tiếp tục công việc.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Làm thêm & học đại học", "options": ["a (Vừa làm vừa học đại học)", "b"], "correctAnswer": "a (Vừa làm vừa học đại học)", "explanation": "Học đại học và làm thêm (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đi làm & học tiếng", "options": ["a (Vừa đi làm vừa học tiếng)", "b"], "correctAnswer": "a (Vừa đi làm vừa học tiếng)", "explanation": "Đi làm và học tiếng Nhật (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Nuôi con & làm việc", "options": ["b (Nuôi con và làm việc)", "a"], "correctAnswer": "b (Nuôi con và làm việc)", "explanation": "Nuôi con và làm việc (b)."}
        ]
    ),
    make_task(
        "II_CD1", 12, 3, "3. 〜ています(習慣)", "3. Thói quen duy trì thường xuyên",
        "3. 〜ています(習慣)\n1) A: 毎朝ジョギングをしています。\n2) A: 毎晩日記を書いています。\n3) A: 週末は学生食堂でカレーを食べています。",
        "3. Thói quen duy trì thường xuyên\n1) A: Mỗi sáng tôi đều chạy bộ thể dục.\n2) A: Mỗi tối tôi đều viết nhật ký.\n3) A: Cuối tuần tôi thường ăn cà ri ở nhà ăn sinh viên.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ăn trưa thói quen", "options": ["a 学生食堂 カレー", "b"], "correctAnswer": "a 学生食堂 カレー", "explanation": "Ăn cà ri ở nhà ăn sinh viên."},
            {"itemIndex": "2", "prompt": "Câu 2: Thói quen ở nhà", "options": ["a うち b", "c"], "correctAnswer": "a うち b", "explanation": "Ở nhà."},
            {"itemIndex": "3", "prompt": "Câu 3: Thói quen", "options": ["c", "d"], "correctAnswer": "c", "explanation": "Đáp án c."}
        ]
    ),
    make_task(
        "II_CD1", 13, 4, "4. 〜し、〜し、〜", "4. Liệt kê lý do (Vừa... lại vừa...)",
        "4. 〜し、〜し、〜\n1) A: この店は値段も安いし、料理も美味しいし、いつもここに来ます。\n2) A: 田中さんは親切だし、頭もいいし、素敵な人です。",
        "4. Liệt kê lý do (Vừa... lại vừa...)\n1) A: Quán này giá cả vừa rẻ, đồ ăn lại vừa ngon, nên tôi lúc nào cũng ghé ăn.\n2) A: Anh Tanaka vừa tốt bụng, đầu óc lại thông minh, là một người rất tuyệt.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Lý do chọn quán", "options": ["a ①a ②b (Rẻ và ngon)", "c"], "correctAnswer": "a ①a ②b (Rẻ và ngon)", "explanation": "Rẻ và ngon (a ①a ②b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nhận xét anh Tanaka", "options": ["a ①b ②b (Tốt bụng và thông minh)", "c"], "correctAnswer": "a ①b ②b (Tốt bụng và thông minh)", "explanation": "Tốt và thông minh (a ①b ②b)."}
        ]
    )
]
LESSONS_21_30.append({
    "lessonNumber": 28, "level": "N4",
    "title": "Bài 28: Vừa... vừa... (ながら), Thói quen & Liệt kê lý do (し)",
    "subtitle": "第28課: ながら・習慣ています・理由のし",
    "tasks": l28_tasks
})

# ==================== LESSON 29 ====================
l29_tasks = [
    make_task(
        "II_CD1", 14, 1, "1. 〜が〜ています", "1. Trạng thái tự động từ (Cửa mở/đèn bật...)",
        "1. 〜が〜ています\n1) A: ドアが開いていますよ。\n2) A: 電気が消えていますね。\n3) A: 車が止まっています。",
        "1. Trạng thái tự động từ (Cửa mở/đèn bật...)\n1) A: Cửa đang mở toang kìa bạn.\n2) A: Đèn đang tắt ngúm rồi nhỉ.\n3) A: Có chiếc ô tô đang dừng đỗ đằng kia.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Cửa phòng", "options": ["b (Cửa đang mở)", "a (Cửa đóng)"], "correctAnswer": "b (Cửa đang mở)", "explanation": "Cửa đang mở (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đèn điện", "options": ["b (Đèn đang tắt)", "a (Đèn bật)"], "correctAnswer": "b (Đèn đang tắt)", "explanation": "Đèn tắt (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Xe ô tô", "options": ["a (Xe đang đỗ)", "b (Xe đang chạy)"], "correctAnswer": "a (Xe đang đỗ)", "explanation": "Xe đang dừng đỗ (a)."}
        ]
    ),
    make_task(
        "II_CD1", 15, 2, "2. 〜ていました", "2. Đang ở trạng thái trong quá khứ",
        "2. 〜ていました\n1) A: 電車がとても混んでいました。\n2) A: 袋が破れていました。",
        "2. Đang ở trạng thái trong quá khứ\n1) A: Tàu điện lúc ấy rất là đông đúc chen chúc.\n2) A: Chiếc túi đựng lúc đó đã bị rách mất rồi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Tàu điện lúc đó", "options": ["b (Tàu đông nghẹt)", "a (Tàu vắng)"], "correctAnswer": "b (Tàu đông nghẹt)", "explanation": "Tàu rất đông (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Cái túi", "options": ["a (Túi bị rách)", "b (Túi còn nguyên)"], "correctAnswer": "a (Túi bị rách)", "explanation": "Túi rách (a)."}
        ]
    ),
    make_task(
        "II_CD1", 16, 3, "3. 〜は〜ています", "3. Đồ vật đang trong trạng thái...",
        "3. 〜は〜ています\n1) A: この椅子は壊れていますから、座らないでください。\n2) A: そのコップは汚れていますよ。\n3) A: ボタンが外れていますよ。",
        "3. Đồ vật đang trong trạng thái...\n1) A: Chiếc ghế này bị gãy hỏng rồi, đừng ngồi lên nhé.\n2) A: Chiếc cốc đó bẩn đấy, để tớ rửa đã.\n3) A: Cúc áo của bạn bị tuột ra kìa.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Chiếc ghế", "options": ["a (Bị gãy hỏng)", "b"], "correctAnswer": "a (Bị gãy hỏng)", "explanation": "Ghế hỏng (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Chiếc cốc", "options": ["a (Bị vấy bẩn)", "b"], "correctAnswer": "a (Bị vấy bẩn)", "explanation": "Cốc bẩn (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Cúc áo", "options": ["b (Tuột cúc)", "a"], "correctAnswer": "b (Tuột cúc)", "explanation": "Tuột cúc áo (b)."}
        ]
    ),
    make_task(
        "II_CD1", 17, 4, "4. 〜てしまいました", "4. Hoàn thành xong xuôi việc gì",
        "4. 〜てしまいました\n1) A: 歌を全部覚えてしまいました。\n2) A: レポートを全部書いてしまいました。\n3) A: 本を全部読んでしまいました。",
        "4. Hoàn thành xong xuôi việc gì\n1) A: Tôi đã học thuộc lòng toàn bộ bài hát rồi.\n2) A: Tôi đã viết xong xuôi toàn bộ bản báo cáo rồi.\n3) A: Tôi đã đọc hết veo cuốn sách dày cộp rồi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Bài hát", "options": ["1 歌 覚えた (Đã thuộc xong)", "Chưa thuộc"], "correctAnswer": "1 歌 覚えた (Đã thuộc xong)", "explanation": "Thuộc xong bài hát."},
            {"itemIndex": "2", "prompt": "Câu 2: Bản báo cáo", "options": ["2 レポート/宿題 書いた/やった (Đã viết xong)", "Chưa viết"], "correctAnswer": "2 レポート/宿題 書いた/やった (Đã viết xong)", "explanation": "Viết xong báo cáo."},
            {"itemIndex": "3", "prompt": "Câu 3: Cuốn sách", "options": ["1 本 読んだ (Đã đọc xong)", "Chưa đọc"], "correctAnswer": "1 本 読んだ (Đã đọc xong)", "explanation": "Đọc xong cuốn sách."}
        ]
    ),
    make_task(
        "II_CD1", 18, 5, "5. 〜てしまいます", "5. Sẽ làm xong ngay bây giờ",
        "5. 〜てしまいます\n1) A: この仕事、今日中にやってしまいます。\n2) A: このワイン、全部飲んでしまいます。",
        "5. Sẽ làm xong ngay bây giờ\n1) A: Công việc này tôi sẽ làm xong luôn trong ngày hôm nay.\n2) A: Chai rượu vang này tôi sẽ uống cạn luôn tối nay.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Xong công việc trong ngày", "options": ["b (Làm cho xong)", "a"], "correctAnswer": "b (Làm cho xong)", "explanation": "Làm cho xong việc (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Uống cạn", "options": ["c (Uống hết)", "a"], "correctAnswer": "c (Uống hết)", "explanation": "Uống hết sạch (c)."}
        ]
    ),
    make_task(
        "II_CD1", 19, 6, "6. 〜てしまいました(残念なこと)", "6. Nuối tiếc / Lỡ làm việc gì đó mất rồi",
        "6. 〜てしまいました(残念なこと)\n1) A: 電車を乗り間違えてしまいました。\n2) A: 財布をどこかに落としてしまいました。",
        "6. Nuối tiếc / Lỡ làm việc gì đó mất rồi\n1) A: Tôi lỡ bước nhầm chuyến tàu mất rồi.\n2) A: Tôi lỡ làm đánh rơi mất chiếc ví tiền ở đâu đó rồi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Lên nhầm tàu", "options": ["e まちがえた ① (Đi nhầm tàu)", "Đúng tàu"], "correctAnswer": "e まちがえた ① (Đi nhầm tàu)", "explanation": "Lên nhầm chuyến tàu."},
            {"itemIndex": "2", "prompt": "Câu 2: Đánh rơi ví", "options": ["c 落とした ② (Đánh rơi ví)", "Tìm thấy"], "correctAnswer": "c 落とした ② (Đánh rơi ví)", "explanation": "Đánh rơi ví tiền."}
        ]
    )
]
LESSONS_21_30.append({
    "lessonNumber": 29, "level": "N4",
    "title": "Bài 29: Tự động từ trạng thái & Nuối tiếc (てしまいました)",
    "subtitle": "第29課: が〜ています・てしまいました・残念",
    "tasks": l29_tasks
})

# ==================== LESSON 30 ====================
l30_tasks = [
    make_task(
        "II_CD1", 20, 1, "1. 〜てあります", "1. Đang có sẵn trạng thái mục đích",
        "1. 〜てあります\n1) A: カレンダーに予定が書いてあります。\n2) A: 壁にポスターが貼ってあります。",
        "1. Đang có sẵn trạng thái mục đích\n1) A: Trên tờ lịch đã được ghi sẵn các lịch trình dự định.\n2) A: Trên tường đang dán sẵn một tấm áp phích quảng cáo.",
        [
            {"itemIndex": "1", "prompt": "Trạng thái đồ vật", "options": ["b (Có dán sẵn/viết sẵn)", "a"], "correctAnswer": "b (Có dán sẵn/viết sẵn)", "explanation": "Viết sẵn lịch trình (b)."}
        ]
    ),
    make_task(
        "II_CD1", 21, 2, "2. 〜てあります (Vị trí đồ vật)", "2. Đồ vật đang được đặt sẵn ở đâu?",
        "2. 〜てあります\n1) A: はさみはどこですか。\nB: 引き出しに入れてありますよ。\n2) A: お皿はどこですか。\nB: 棚に並べてあります。\n3) A: ビールはどこですか。\nB: 冷蔵庫に冷やしてありますよ。",
        "2. Đồ vật đang được đặt sẵn ở đâu?\n1) A: Cây kéo ở đâu rồi bạn nhỉ?\nB: Kéo được cất sẵn trong ngăn kéo đó bạn.\n2) A: Mấy cái đĩa ăn ở đâu thế?\nB: Được xếp ngay ngắn trên giá bát đĩa rồi.\n3) A: Mấy lon bia ở đâu thế?\nB: Được ướp lạnh sẵn trong tủ lạnh rồi đấy.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Cây kéo", "options": ["d ⑥ (Trong ngăn kéo)", "a"], "correctAnswer": "d ⑥ (Trong ngăn kéo)", "explanation": "Trong ngăn kéo (d ⑥)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đĩa ăn", "options": ["e ② (Trên giá kệ)", "b"], "correctAnswer": "e ② (Trên giá kệ)", "explanation": "Trên giá (e ②)."},
            {"itemIndex": "3", "prompt": "Câu 3: Bia lon", "options": ["c ③ (Ướp trong tủ lạnh)", "a"], "correctAnswer": "c ③ (Ướp trong tủ lạnh)", "explanation": "Ướp trong tủ lạnh (c ③)."}
        ]
    ),
    make_task(
        "II_CD1", 22, 3, "3. 〜はもう〜てあります", "3. Đã chuẩn bị sẵn sàng việc gì chưa?",
        "3. 〜はもう〜てあります\n1) A: ホテルはもう予約してありますか。\nB: はい、予約してあります。\n2) A: 会議室の準備はもうしてありますか。\nB: はい、もうしてあります。\n3) A: チケットはもう買ってありますか。\nB: はい、買ってあります。",
        "3. Đã chuẩn bị sẵn sàng việc gì chưa?\n1) A: Khách sạn đã được đặt phòng trước chưa bạn?\nB: Rồi, tôi đã đặt phòng xong xuôi rồi.\n2) A: Phòng họp đã chuẩn bị chu đáo chưa?\nB: Vâng, đã chuẩn bị xong cả rồi ạ.\n3) A: Vé vào cửa đã mua sẵn chưa?\nB: Vâng, đã mua sẵn rồi ạ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đặt phòng", "options": ["✓ ✓ ○ (Đã đặt sẵn)", "×"], "correctAnswer": "✓ ✓ ○ (Đã đặt sẵn)", "explanation": "Đã đặt khách sạn xong (✓ ✓ ○)."},
            {"itemIndex": "2", "prompt": "Câu 2: Chuẩn bị phòng họp", "options": ["○ ○ (Đã chuẩn bị)", "×"], "correctAnswer": "○ ○ (Đã chuẩn bị)", "explanation": "Đã chuẩn bị chu đáo (○ ○)."},
            {"itemIndex": "3", "prompt": "Câu 3: Mua vé", "options": ["✓ ○ (Đã mua sẵn)", "×"], "correctAnswer": "✓ ○ (Đã mua sẵn)", "explanation": "Đã mua vé sẵn (✓ ○)."}
        ]
    ),
    make_task(
        "II_CD1", 23, 4, "4. 〜ておきます", "4. Chuẩn bị trước / Giữ nguyên trạng thái",
        "4. 〜ておきます\n1) A: 旅行の前に切符を買っておきます。\n2) A: パーティーの前に部屋を片付けておきます。\n3) A: 窓を開けておいてください。\n4) A: そのままにしておいてください。\n5) A: 冷蔵庫に入れておきます。",
        "4. Chuẩn bị trước / Giữ nguyên trạng thái\n1) A: Trước chuyến đi tôi mua vé tàu trước.\n2) A: Trước buổi tiệc tôi dọn dẹp phòng ốc tươm tất trước.\n3) A: Xin cứ để nguyên cửa sổ mở như vậy nhé.\n4) A: Cứ để nguyên hiện trạng như thế giúp tôi.\n5) A: Cất sẵn vào trong tủ lạnh bảo quản.",
        [
            {"itemIndex": "1", "prompt": "Câu 1", "options": ["a. × b. ○", "a. ○ b. ×"], "correctAnswer": "a. × b. ○", "explanation": "Đáp án a. × b. ○."},
            {"itemIndex": "2", "prompt": "Câu 2", "options": ["a. × b. ○", "a. ○ b. ×"], "correctAnswer": "a. × b. ○", "explanation": "Đáp án a. × b. ○."},
            {"itemIndex": "3", "prompt": "Câu 3", "options": ["a. ○ b. ×", "a. × b. ○"], "correctAnswer": "a. ○ b. ×", "explanation": "Đáp án a. ○ b. ×."},
            {"itemIndex": "4", "prompt": "Câu 4", "options": ["a. ○ b. ×", "a. × b. ○"], "correctAnswer": "a. ○ b. ×", "explanation": "Đáp án a. ○ b. ×."},
            {"itemIndex": "5", "prompt": "Câu 5", "options": ["a. × b. ○", "a. ○ b. ×"], "correctAnswer": "a. × b. ○", "explanation": "Đáp án a. × b. ○."}
        ]
    )
]
LESSONS_21_30.append({
    "lessonNumber": 30, "level": "N4",
    "title": "Bài 30: Tha động từ thể てあります & Chuẩn bị trước (ておきます)",
    "subtitle": "第30課: てあります・もう〜てあります・ておきます",
    "tasks": l30_tasks
})

print("Lessons 21 to 30 defined in LESSONS_21_30.")
