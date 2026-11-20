# -*- coding: utf-8 -*-
from choukai_helper import make_task

LESSONS_01_10 = []

# ==================== LESSON 1 ====================
l1_tasks = [
    make_task(
        "I_CD1", 2, 1, "1. 名前は a ですか、b ですか。", "1. Tên là a hay b?",
        "1. 名前は a ですか、b ですか。\n例) A: 初めまして、益田です。\nB: 松田さんですね。\nA: いいえ、益田です。\n1) A: 初めまして、佐野です。どうぞよろしく。\nB: さろさんですか。\nA: いいえ、佐野です。\n2) A: こんにちは、鈴木です。\nB: ああ、鈴木さんですね。\nA: ええ。\n3) A: 失礼ですが、お名前は？\nB: 後藤です。\nA: ごどうさんですか。\nB: いいえ、後藤です。",
        "1. Tên nhân vật là a hay b?\nVí dụ) A: Xin chào, tôi là Masuda.\nB: Anh Matsuda phải không?\nA: Không, tôi là Masuda.\n1) A: Xin chào, tôi là Sano. Rất mong được giúp đỡ.\nB: Anh Saro à?\nA: Không, là Sano ạ.\n2) A: Xin chào, tôi là Suzuki.\nB: À, anh Suzuki phải không.\nA: Vâng đúng rồi.\n3) A: Xin lỗi, anh tên là gì ạ?\nB: Tôi là Gotou.\nA: Anh Godou à?\nB: Không, là Gotou ạ.",
        [
            {"itemIndex": "例", "prompt": "Ví dụ: Tên của nhân vật", "options": ["a. まつだ (Matsuda)", "b. ますだ (Masuda)"], "correctAnswer": "b", "explanation": "Nhân vật nói: いいえ、益田 (ますだ) です -> Đáp án b."},
            {"itemIndex": "1", "prompt": "Câu 1: Tên của nhân vật", "options": ["a. さの (Sano)", "b. さろ (Saro)"], "correctAnswer": "a", "explanation": "Nhân vật đính chính: いいえ、佐野 (さの) です -> Đáp án a."},
            {"itemIndex": "2", "prompt": "Câu 2: Tên của nhân vật", "options": ["a. すずき (Suzuki)", "b. つづき (Tsuzuki)"], "correctAnswer": "a", "explanation": "Nhân vật xác nhận: 鈴木 (すずき) です -> Đáp án a."},
            {"itemIndex": "3", "prompt": "Câu 3: Tên của nhân vật", "options": ["a. こどう (Kodou)", "b. ごとう (Gotou)"], "correctAnswer": "b", "explanation": "Nhân vật sửa lại: いいえ、後藤 (ごとう) です -> Đáp án b."}
        ]
    ),
    make_task(
        "I_CD1", 3, 2, "2. 国は a ですか、b ですか。", "2. Đất nước/quốc tịch là a hay b?",
        "2. 国は a ですか、b ですか。\n例) A: 初めまして、ワンです。中国から来ました。どうぞよろしく。\nB: 初めまして、田中です。どうぞよろしく。\n1) A: 皆さん、こちらはセンさんです。\nB: 初めまして、センです。インドから来ました。どうぞよろしくお願いします。\n2) A: 佐藤さん、こちらはジャンさんです。\nC: ジャンです。フランスから来ました。どうぞよろしく。\nB: 佐藤です。よろしくお願いします。\n3) A: 初めまして、コールです。\nB: コーンさんですか。\nA: いいえ、コールです。ドイツから来ました。\nB: わたしは山田です。よろしくお願いします。",
        "2. Quốc gia xuất xứ là a hay b?\nVí dụ) A: Xin chào, tôi là Wang. Tôi đến từ Trung Quốc.\nB: Xin chào, tôi là Tanaka.\n1) A: Mọi người ơi, đây là anh Sen.\nB: Xin chào, tôi là Sen, đến từ Ấn Độ.\n2) A: Chị Satou, đây là anh Jean.\nC: Tôi là Jean, đến từ Pháp.\nB: Tôi là Satou, rất vui được gặp anh.\n3) A: Xin chào, tôi là Cole.\nB: Anh Cone à?\nA: Không, là Cole. Tôi đến từ Đức.\nB: Tôi là Yamada. Rất mong được giúp đỡ.",
        [
            {"itemIndex": "例", "prompt": "Ví dụ: ワン (Wang) đến từ nước nào?", "options": ["a. 中国 (Trung Quốc)", "b. 韓国 (Hàn Quốc)"], "correctAnswer": "a", "explanation": "Wang giới thiệu: 中国から来ました -> Đáp án a."},
            {"itemIndex": "1", "prompt": "Câu 1: セン (Sen) đến từ nước nào?", "options": ["a. インド (Ấn Độ)", "b. インドネシア (Indonesia)"], "correctAnswer": "a", "explanation": "Sen giới thiệu: インドから来ました -> Đáp án a."},
            {"itemIndex": "2", "prompt": "Câu 2: ジャン (Jean) đến từ nước nào?", "options": ["a. ブラジル (Brazil)", "b. フランス (Pháp)"], "correctAnswer": "b", "explanation": "Jean giới thiệu: フランスから来ました -> Đáp án b."},
            {"itemIndex": "3", "prompt": "Câu 3: コール (Cole) đến từ nước nào?", "options": ["a. インド (Ấn Độ)", "b. ドイツ (Đức)"], "correctAnswer": "b", "explanation": "Cole nói: ドイツから来ました -> Đáp án b."}
        ]
    ),
    make_task(
        "I_CD1", 4, 3, "3. 女の人の仕事は a ですか、b ですか。", "3. Nghề nghiệp của người phụ nữ là a hay b?",
        "3. 女の人の仕事は a ですか、b ですか。\n例) A: 初めまして、ワットです。イギリスから来ました。\nB: 初めまして、小林です。\nA: わたしはさくら大学の教師です。\nB: あ、わたしも教師です。富士大学です。どうぞよろしく。\n1) A: 初めまして、ワンです。\nB: 初めまして、後藤です。\nA: わたしは神戸病院の医者です。どうぞよろしく。\nB: あ、わたしも医者です。どうぞよろしく。\n2) A: 初めまして、カルロスです。ブラジルから来ました。\nB: 初めまして、木村です。\nA: わたしはブラジルエアーの社員です。\nB: 木村さんは？\nB: わたしは銀行員です。\n3) A: 初めまして、ミラーです。IMCの社員です。どうぞよろしく。\nB: 初めまして、益田です。\nA: 益田さんもIMCの社員ですか。\nB: いいえ、パワー電気の社員です。どうぞよろしく。",
        "3. Công việc của người phụ nữ là a hay b?\nVí dụ) A: Xin chào, tôi là Watt đến từ Anh, giảng viên đại học Sakura.\nB: Tôi là Kobayashi, tôi cũng là giảng viên ở đại học Fuji.\n1) A: Tôi là Wang, bác sĩ bệnh viện Kobe.\nB: Tôi là Gotou, tôi cũng là bác sĩ.\n2) A: Tôi là Carlos, nhân viên hãng hàng không Brazil Air. Chị Kimura làm nghề gì?\nB: Tôi là nhân viên ngân hàng.\n3) A: Tôi là Miller, nhân viên công ty IMC. Cô Masuda cũng làm ở IMC à?\nB: Không, tôi là nhân viên công ty Điện lực Power.",
        [
            {"itemIndex": "例", "prompt": "Ví dụ: Người phụ nữ làm ở đâu?", "options": ["a. 富士大学 (Đại học Fuji)", "b. さくら大学 (Đại học Sakura)"], "correctAnswer": "a", "explanation": "Người phụ nữ (B) nói: わたしも教師です。富士大学です -> Đáp án a."},
            {"itemIndex": "1", "prompt": "Câu 1: Nghề nghiệp của người phụ nữ (後藤)", "options": ["a. 医者 (Bác sĩ)", "b. 教師 (Giáo viên)"], "correctAnswer": "a", "explanation": "Người phụ nữ nói: わたしも医者です (Tôi cũng là bác sĩ) -> Đáp án a."},
            {"itemIndex": "2", "prompt": "Câu 2: Nghề nghiệp của người phụ nữ (木村)", "options": ["a. 会社の社員 (Nhân viên công ty)", "b. 銀行員 (Nhân viên ngân hàng)"], "correctAnswer": "b", "explanation": "Người phụ nữ nói: わたしは銀行員です (Tôi là nhân viên ngân hàng) -> Đáp án b."},
            {"itemIndex": "3", "prompt": "Câu 3: Nơi làm việc của người phụ nữ (益田)", "options": ["a. IMC", "b. パワー電気 (Điện lực Power)"], "correctAnswer": "b", "explanation": "Người phụ nữ nói: いいえ、パワー電気の社員です -> Đáp án b."}
        ]
    ),
    make_task(
        "I_CD1", 5, 4, "4. 何歳ですか。", "4. Bao nhiêu tuổi?",
        "4. 何歳ですか。\n例) A: ミラーさん、失礼ですが、おいくつですか。\nB: 28歳です。\n1) A: 益田さん、お母さんはおいくつですか。\nB: 母ですか。71歳です。\n2) A: 可愛いですね。何歳ですか。\nB: 8歳です。小学2年生です。\n3) A: ケリーさん、失礼ですが、おいくつですか。\nB: 26歳です。\nA: そうですか。わたしも26歳です。",
        "4. Bao nhiêu tuổi?\nVí dụ) A: Anh Miller, xin lỗi nhưng anh bao nhiêu tuổi rồi ạ?\nB: Tôi 28 tuổi.\n1) A: Anh Masuda, mẹ của anh bao nhiêu tuổi rồi?\nB: Mẹ tôi à? 71 tuổi rồi.\n2) A: Bé dễ thương quá, bé mấy tuổi rồi?\nB: Cháu 8 tuổi, học sinh lớp 2.\n3) A: Kelly này, xin hỏi cô bao nhiêu tuổi?\nB: Tôi 26 tuổi.\nA: Thế à, tôi cũng 26 tuổi.",
        [
            {"itemIndex": "例", "prompt": "Ví dụ: Tuổi của ミラー (Miller)", "options": ["28歳", "25歳", "30歳"], "correctAnswer": "28歳", "explanation": "Miller trả lời: 28歳です -> 28 tuổi."},
            {"itemIndex": "1", "prompt": "Câu 1: Tuổi của mẹ anh 益田 (Masuda)", "options": ["71歳", "61歳", "81歳"], "correctAnswer": "71歳", "explanation": "Masuda trả lời: 母ですか。71歳 (ななじゅういっさい) です -> 71 tuổi."},
            {"itemIndex": "2", "prompt": "Câu 2: Tuổi của em bé", "options": ["8歳", "7歳", "9歳"], "correctAnswer": "8歳", "explanation": "Người mẹ trả lời: 8歳 (はっさい) です -> 8 tuổi."},
            {"itemIndex": "3", "prompt": "Câu 3: Tuổi của cô ケリー (Kelly)", "options": ["26歳", "24歳", "28歳"], "correctAnswer": "26歳", "explanation": "Kelly trả lời: 26歳 (にじゅうろくさい) です -> 26 tuổi."}
        ]
    )
]
LESSONS_01_10.append({
    "lessonNumber": 1, "level": "N5",
    "title": "Bài 1: Giới thiệu bản thân & Nghề nghiệp",
    "subtitle": "第1課: 自己紹介・国・仕事・年齢",
    "tasks": l1_tasks
})

# ==================== LESSON 2 ====================
l2_tasks = [
    make_task(
        "I_CD1", 6, 1, "1. これ/それ/あれは〜です", "1. Đây/Đó/Kia là cái gì?",
        "1. これ/それ/あれは〜です\n例) A: これは何ですか。\nB: それはテレホンカードです。\n1) A: それは何ですか。\nB: これですか。韓国語のテープです。\n2) A: あれは何ですか。\nB: ドアのベルです。\n3) A: それは何ですか。\nB: チョコレートです。どうぞ。",
        "1. Đây/Đó/Kia là cái gì?\nVí dụ) A: Cái này là gì vậy?\nB: Đó là thẻ điện thoại công cộng.\n1) A: Cái đó là gì vậy?\nB: Cái này à? Là băng học tiếng Hàn Quốc.\n2) A: Kia là cái gì thế?\nB: Là chuông cửa đó.\n3) A: Đó là gì thế?\nB: Là sô-cô-la đấy. Xin mời bạn.",
        [
            {"itemIndex": "例", "prompt": "Ví dụ: Vật được hỏi là gì?", "options": ["a. カード", "b. テレホンカード", "c. きっぷ"], "correctAnswer": "b", "explanation": "Người B trả lời: テレホンカードです -> Thẻ điện thoại."},
            {"itemIndex": "1", "prompt": "Câu 1: Vật trong đoạn 1 là gì?", "options": ["a. 日本語のテープ", "b. 英語のテープ", "c. 韓国語のテープ"], "correctAnswer": "c", "explanation": "Người B nói: 韓国語 (かんこくご) のテープです -> Băng tiếng Hàn."},
            {"itemIndex": "2", "prompt": "Câu 2: Vật trong đoạn 2 là gì?", "options": ["a. ドアのベル", "b. テレビ", "c. ラジオ"], "correctAnswer": "a", "explanation": "Người B nói: ドアのベルです -> Chuông cửa."},
            {"itemIndex": "3", "prompt": "Câu 3: Vật trong đoạn 3 là gì?", "options": ["a. ケーキ", "b. チョコレート", "c. 花"], "correctAnswer": "b", "explanation": "Người B trả lời: チョコレートです -> Sô-cô-la."}
        ]
    ),
    make_task(
        "I_CD1", 7, 2, "2. aですか、bですか", "2. Là a hay là b?",
        "2. aですか、bですか\n例) A: 失礼ですが、お名前は？\nB: なかしまです。\nA: なかしまさんですか、なかじまさんですか。\nB: なかしまです。\n1) A: 会社のお名前は？\nB: ひふみでんきです。\nA: ひふみでんきですか、いちにっさんでんきですか。\nB: ひふみでんきです。\n2) A: お電話番号は？\nB: 060-0603-8901です。\nA: 1ですか、7ですか。\nB: 1です。\n3) A: これはボールペンですか、シャープペンシルですか。\nB: シャープペンシルです。",
        "2. Là a hay là b?\nVí dụ) A: Xin hỏi tên anh là gì ạ?\nB: Tôi là Nakashima.\nA: Nakashima hay Nakajima ạ?\nB: Nakashima ạ.\n1) A: Tên công ty là gì ạ?\nB: Hifumi Denki ạ.\nA: Hifumi Denki hay Ichinissan Denki ạ?\nB: Hifumi Denki ạ.\n2) A: Số điện thoại là bao nhiêu?\nB: 060-0603-8901.\nA: Là số 1 hay số 7 ở đuôi ạ?\nB: Số 1 ạ.\n3) A: Đây là bút bi hay bút chì kim?\nB: Bút chì kim ạ.",
        [
            {"itemIndex": "例", "prompt": "Ví dụ: Tên họ chính xác", "options": ["a. なかしま", "b. なかじま"], "correctAnswer": "a", "explanation": "Khẳng định: なかしまです -> Chọn a."},
            {"itemIndex": "1", "prompt": "Câu 1: Tên công ty", "options": ["a. ひふみでんき", "b. いちにっさんでんき"], "correctAnswer": "a", "explanation": "Khẳng định: ひふみでんきです -> Chọn a."},
            {"itemIndex": "2", "prompt": "Câu 2: Số cuối cùng", "options": ["a. 1", "b. 7"], "correctAnswer": "a", "explanation": "Người nói trả lời: 1です -> Chọn a."},
            {"itemIndex": "3", "prompt": "Câu 3: Đồ vật là gì", "options": ["a. ボールペン", "b. シャープペンシル"], "correctAnswer": "b", "explanation": "Khẳng định: シャープペンシルです -> Chọn b."}
        ]
    ),
    make_task(
        "I_CD1", 8, 3, "3. 何の〜ですか", "3. Là loại gì / về cái gì?",
        "3. 何の〜ですか\n例) A: これは自動車の本です。\n1) A: それは何の雑誌ですか。\nB: カメラの雑誌です。\n2) A: これは何のCDですか。\nB: 日本語のCDです。\n3) A: それは何の鍵ですか。\nB: 車の鍵です。",
        "3. Là loại gì / về cái gì?\nVí dụ) A: Đây là cuốn sách về ô tô.\n1) A: Đó là tạp chí gì thế?\nB: Tạp chí về máy ảnh.\n2) A: Đây là đĩa CD gì vậy?\nB: Đĩa CD tiếng Nhật.\n3) A: Đó là chìa khóa gì vậy?\nB: Chìa khóa xe hơi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Tạp chí về cái gì?", "options": ["a. カメラ (Máy ảnh)", "b. 自動車 (Ô tô)"], "correctAnswer": "a", "explanation": "B trả lời: カメラの雑誌です -> Đáp án a."},
            {"itemIndex": "2", "prompt": "Câu 2: Đĩa CD về cái gì?", "options": ["a. 音楽 (Âm nhạc)", "b. 日本語 (Tiếng Nhật)"], "correctAnswer": "b", "explanation": "B trả lời: 日本語のCDです -> Đáp án b."},
            {"itemIndex": "3", "prompt": "Câu 3: Chìa khóa của cái gì?", "options": ["a. うち (Nhà)", "b. 車 (Ô tô)"], "correctAnswer": "b", "explanation": "B trả lời: 車の鍵です -> Đáp án b."}
        ]
    ),
    make_task(
        "I_CD1", 9, 4, "4. だれのですか", "4. Của ai?",
        "4. だれのですか\n例) A: この傘はミラーさんのですか。\nB: はい、そうです。\n1) A: この手帳はだれのですか。\nB: 佐藤さんのです。\n2) A: その辞書はワンさんのですか。\nB: はい、ワンさんのです。\n3) A: この時計はだれのですか。\nB: 木村さんのです。\n4) A: この鞄は山田さんのですか。\nB: いいえ、サントスさんのです。",
        "4. Của ai?\nVí dụ) A: Cái ô này của anh Miller phải không?\nB: Vâng, đúng rồi.\n1) A: Cuốn sổ tay này của ai thế?\nB: Của chị Satou.\n2) A: Cuốn từ điển đó của anh Wang phải không?\nB: Vâng, của anh Wang.\n3) A: Chiếc đồng hồ này của ai vậy?\nB: Của chị Kimura.\n4) A: Chiếc cặp này của anh Yamada à?\nB: Không, của anh Santos đấy.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Sổ tay của ai?", "options": ["a. ミラーさん", "b. 山田さん", "c. 佐藤さん"], "correctAnswer": "c", "explanation": "Trả lời: 佐藤さんのです -> Đáp án c."},
            {"itemIndex": "2", "prompt": "Câu 2: Từ điển của ai?", "options": ["a. ワンさん", "b. 木村さん", "c. サントスさん"], "correctAnswer": "a", "explanation": "Trả lời: ワンさんのです -> Đáp án a."},
            {"itemIndex": "3", "prompt": "Câu 3: Đồng hồ của ai?", "options": ["a. 佐藤さん", "b. ミラーさん", "c. ワンさん", "d. 木村さん"], "correctAnswer": "d", "explanation": "Trả lời: 木村さんのです -> Đáp án d."},
            {"itemIndex": "4", "prompt": "Câu 4: Cặp xách của ai?", "options": ["a. 山田さん", "b. ミラーさん", "c. 佐藤さん", "d. 木村さん", "e. サントスさん"], "correctAnswer": "e", "explanation": "Trả lời: サントスさんのです -> Đáp án e."}
        ]
    )
]
LESSONS_01_10.append({
    "lessonNumber": 2, "level": "N5",
    "title": "Bài 2: Đồ vật xung quanh & Sở hữu",
    "subtitle": "第2課: これ・それ・あれ・持ち物",
    "tasks": l2_tasks
})

print("Lessons 1 and 2 defined.")

# ==================== LESSON 3 ====================
l3_tasks = [
    make_task(
        "I_CD1", 10, 1, "1. ここ/そこ/あそこは〜です", "1. Đây/Đó/Kia là nơi nào?",
        "1. ここ/そこ/あそこは〜です\n例) A: すみません、受付はどこですか。\nB: あちらです。\n1) A: すみません、会議室はどこですか。\nB: 3階です。あちらのエレベーターの隣です。\n2) A: 食堂はどこですか。\nB: 地下1階です。\n3) A: お手洗いはどこですか。\nB: 2階の階段の横です。",
        "1. Đây/Đó/Kia là nơi nào?\nVí dụ) A: Xin lỗi, quầy lễ tân ở đâu ạ?\nB: Ở đằng kia ạ.\n1) A: Xin hỏi phòng họp ở đâu ạ?\nB: Ở tầng 3, cạnh thang máy đằng kia ạ.\n2) A: Nhà ăn ở đâu ạ?\nB: Ở tầng hầm B1 ạ.\n3) A: Nhà vệ sinh ở đâu ạ?\nB: Ở tầng 2, bên cạnh cầu thang bộ ạ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Phòng họp ở đâu?", "options": ["a. 3階 (Tầng 3)", "b. 2階 (Tầng 2)"], "correctAnswer": "a", "explanation": "B trả lời: 3階です -> Đáp án a."},
            {"itemIndex": "2", "prompt": "Câu 2: Nhà ăn ở đâu?", "options": ["a. 1階 (Tầng 1)", "b. 地下1階 (Tầng hầm 1)"], "correctAnswer": "b", "explanation": "B trả lời: 地下1階です -> Đáp án b."},
            {"itemIndex": "3", "prompt": "Câu 3: Nhà vệ sinh ở đâu?", "options": ["a. 1階 (Tầng 1)", "b. 2階 (Tầng 2)"], "correctAnswer": "b", "explanation": "B trả lời: 2階の階段の横です -> Đáp án b."}
        ]
    ),
    make_task(
        "I_CD1", 11, 2, "2. どこですか", "2. Ở đâu?",
        "2. どこですか\n1) A: すみません、ワイン売り場はどこですか。\nB: 地下1階です。\n2) A: カメラ売り場はどこですか。\nB: 5階です。\n3) A: 婦人服売り場はどこですか。\nB: 3階です。",
        "2. Ở đâu?\n1) A: Xin lỗi, quầy bán rượu vang ở đâu ạ?\nB: Ở tầng hầm 1 ạ.\n2) A: Quầy bán máy ảnh ở đâu vậy ạ?\nB: Ở tầng 5 ạ.\n3) A: Quầy bán trang phục phụ nữ ở đâu ạ?\nB: Ở tầng 3 ạ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Quầy rượu vang ở tầng mấy?", "options": ["f. 地下1階", "g. 1階", "h. 2階"], "correctAnswer": "f", "explanation": "Đáp án f: 地下1階."},
            {"itemIndex": "2", "prompt": "Câu 2: Quầy máy ảnh ở tầng mấy?", "options": ["a. 3階", "b. 4階", "h. 5階"], "correctAnswer": "h", "explanation": "Đáp án h: 5階."},
            {"itemIndex": "3", "prompt": "Câu 3: Quầy quần áo nữ ở tầng mấy?", "options": ["a. 1階", "b. 2階", "c. 3階"], "correctAnswer": "c", "explanation": "Đáp án c: 3階."}
        ]
    ),
    make_task(
        "I_CD1", 12, 3, "3. お国/会社はどちらですか・何の会社ですか", "3. Đất nước/Công ty của bạn ở đâu?",
        "3. お国/会社はどちらですか・何の会社ですか\n1) A: サントスさん、お国はどちらですか。\nB: ブラジルです。\nA: 会社はどちらですか。\nB: ブラジルエアーです。\n2) A: マリアさん、会社はどちらですか。\nB: さくら大学です。コンピューターを教えています。",
        "3. Đất nước/Công ty của bạn ở đâu?\n1) A: Anh Santos, anh đến từ nước nào?\nB: Brazil ạ.\nA: Công ty anh là công ty nào?\nB: Hãng hàng không Brazil Air ạ.\n2) A: Chị Maria, nơi làm việc là đâu ạ?\nB: Đại học Sakura ạ, tôi dạy về máy tính.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nước và công ty của anh Santos", "options": ["a. アメリカ・IMC", "b. ブラジル・ブラジルエアー"], "correctAnswer": "b", "explanation": "Brazil và Brazil Air -> Đáp án b."},
            {"itemIndex": "2", "prompt": "Câu 2: Nơi làm việc của chị Maria", "options": ["a. さくら大学・コンピューター", "b. 神戸病院・医者"], "correctAnswer": "a", "explanation": "Đại học Sakura dạy tin học -> Đáp án a."}
        ]
    ),
    make_task(
        "I_CD1", 13, 4, "4. いくらですか・どこのですか", "4. Bao nhiêu tiền và của nước nào?",
        "4. いくらですか・どこのですか\n1) A: このワインはどこのですか。\nB: フランスのです。\nA: いくらですか。\nB: 2,800円です。\n2) A: このパソコンは日本のですね。\nB: はい、そうです。\nA: いくらですか。\nB: 98,000円です。\n3) A: このカメラはいくらですか。\nB: 56,000円です。ドイツのです。\nA: あのカメラはいくらですか。\nB: 18,500円です。日本のです。",
        "4. Bao nhiêu tiền và của nước nào?\n1) A: Chai rượu vang này của nước nào?\nB: Của Pháp ạ.\nA: Giá bao nhiêu vậy?\nB: 2.800 yên ạ.\n2) A: Chiếc máy tính này của Nhật nhỉ.\nB: Vâng đúng rồi.\nA: Giá bao nhiêu vậy?\nB: 98.000 yên ạ.\n3) A: Chiếc máy ảnh này giá bao nhiêu?\nB: 56.000 yên, của Đức sản xuất.\nA: Còn chiếc máy ảnh kia thì sao?\nB: 18.500 yên, của Nhật Bản.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Giá rượu vang và xuất xứ", "options": ["2,800円 (Pháp)", "3,800円 (Ý)", "1,800円 (Pháp)"], "correctAnswer": "2,800円 (Pháp)", "explanation": "Giá 2,800 yên của Pháp."},
            {"itemIndex": "2", "prompt": "Câu 2: Giá máy tính", "options": ["88,000円", "98,000円", "108,000円"], "correctAnswer": "98,000円", "explanation": "Giá 98,000 yên."},
            {"itemIndex": "3", "prompt": "Câu 3: Chiếc máy ảnh này (gần)", "options": ["56,000円 (Đức)", "18,500円 (Nhật)"], "correctAnswer": "56,000円 (Đức)", "explanation": "56,000 yên của Đức."},
            {"itemIndex": "4", "prompt": "Câu 4: Chiếc máy ảnh kia (xa)", "options": ["18,500円 (Nhật)", "28,500円 (Nhật)"], "correctAnswer": "18,500円 (Nhật)", "explanation": "18,500 yên của Nhật."}
        ]
    )
]
LESSONS_01_10.append({
    "lessonNumber": 3, "level": "N5",
    "title": "Bài 3: Địa điểm, Nơi chốn & Giá cả",
    "subtitle": "第3課: ここ・そこ・あそこ・場所・値段",
    "tasks": l3_tasks
})

# ==================== LESSON 4 ====================
l4_tasks = [
    make_task(
        "I_CD1", 14, 1, "1. 何時ですか", "1. Mấy giờ rồi?",
        "1. 何時ですか\n1) A: 今何時ですか。\nB: 1時半です。\n2) A: 今何時ですか。\nB: 7時15分です。\n3) A: 今何時ですか。\nB: 12時50分です。",
        "1. Mấy giờ rồi?\n1) A: Bây giờ là mấy giờ rồi ạ?\nB: 1 giờ 30 phút (1 giờ rưỡi).\n2) A: Bây giờ mấy giờ?\nB: 7 giờ 15 phút.\n3) A: Bây giờ mấy giờ?\nB: 12 giờ 50 phút.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Thời gian được nói tới", "options": ["1:30", "1:15", "2:30"], "correctAnswer": "1:30", "explanation": "1時半 (1 giờ 30 phút)."},
            {"itemIndex": "2", "prompt": "Câu 2: Thời gian được nói tới", "options": ["7:15", "7:45", "8:15"], "correctAnswer": "7:15", "explanation": "7時15分."},
            {"itemIndex": "3", "prompt": "Câu 3: Thời gian được nói tới", "options": ["12:50", "12:15", "1:50"], "correctAnswer": "12:50", "explanation": "12時50分."}
        ]
    ),
    make_task(
        "I_CD1", 15, 2, "2. 何時から何時までですか・休みは何曜日ですか", "2. Từ mấy giờ đến mấy giờ & Nghỉ thứ mấy?",
        "2. 何時から何時までですか・休みは何曜日ですか\n1) A: 銀行は何時から何時までですか。\nB: 朝9時から夕方6時までです。休みは火曜日です。\n2) A: 病院は何時から何時までですか。\nB: 午前9時から12時までと、午後4時半から7時半までです。休みは日曜日です。",
        "2. Từ mấy giờ đến mấy giờ & Nghỉ thứ mấy?\n1) A: Ngân hàng làm việc từ mấy giờ đến mấy giờ ạ?\nB: Từ 9 giờ sáng đến 6 giờ chiều. Ngày nghỉ là thứ Ba.\n2) A: Bệnh viện khám từ mấy giờ đến mấy giờ?\nB: Buổi sáng từ 9:00 đến 12:00, buổi chiều từ 16:30 đến 19:30. Nghỉ vào Chủ Nhật.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Giờ làm việc ngân hàng & ngày nghỉ", "options": ["9:00〜6:00 (Nghỉ thứ Ba)", "9:00〜5:00 (Nghỉ thứ Hai)"], "correctAnswer": "9:00〜6:00 (Nghỉ thứ Ba)", "explanation": "9:00〜6:00 và nghỉ 火曜日 (thứ Ba)."},
            {"itemIndex": "2", "prompt": "Câu 2: Giờ làm việc bệnh viện & ngày nghỉ", "options": ["9:00〜12:00 / 4:30〜7:30 (Nghỉ Chủ Nhật)", "8:00〜12:00 (Nghỉ thứ Bảy)"], "correctAnswer": "9:00〜12:00 / 4:30〜7:30 (Nghỉ Chủ Nhật)", "explanation": "9:00〜12:00 / 4:30〜7:30 và nghỉ 日曜日."}
        ]
    ),
    make_task(
        "I_CD1", 16, 3, "3. 〜ました/〜ませんでした", "3. Đã làm / Không làm (Quá khứ)",
        "3. 〜ました/〜ませんでした\n1) A: きのう勉強しましたか。\nB: いいえ、しませんでした。\n2) A: おととい働きましたか。\nB: はい、働きました。\n3) A: 今朝起きましたか。\nB: はい、6時に起きました。",
        "3. Đã làm / Không làm (Quá khứ)\n1) A: Hôm qua bạn có học bài không?\nB: Không, tôi không học.\n2) A: Hôm kia bạn có đi làm không?\nB: Có, tôi đã đi làm.\n3) A: Sáng nay bạn dậy sớm không?\nB: Vâng, tôi dậy lúc 6 giờ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nhân vật có học bài không?", "options": ["○ (Có)", "× (Không)"], "correctAnswer": "× (Không)", "explanation": "いいえ、しませんでした -> Không làm (×)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nhân vật có đi làm hôm kia không?", "options": ["○ (Có)", "× (Không)"], "correctAnswer": "○ (Có)", "explanation": "はい、働きました -> Có làm (○)."},
            {"itemIndex": "3", "prompt": "Câu 3: Nhân vật có thức dậy lúc 6h không?", "options": ["○ (Có)", "× (Không)"], "correctAnswer": "○ (Có)", "explanation": "はい、6時に起きました -> Có (○)."}
        ]
    ),
    make_task(
        "I_CD1", 17, 4, "4. 何曜日に〜ましたか", "4. Đã làm vào thứ mấy?",
        "4. 何曜日に〜ましたか\n月曜日から日曜日までのスケジュールを聞きます。\nA: 月曜日に働きましたか。\nB: はい、働きました。\nA: 火曜日は？\nB: はい、火曜日も働きました。\nA: 水曜日は？\nB: 水曜日は休みました。\nA: 木曜日は？\nB: 働きました。\nA: 金曜日は？\nB: 休みました。\nA: 土曜日と日曜日は？\nB: もちろん休みました。",
        "4. Đã làm vào thứ mấy?\nNghe lịch làm việc từ thứ Hai đến Chủ Nhật:\nA: Thứ Hai bạn có làm việc không?\nB: Có, tôi làm việc.\nA: Thứ Ba thì sao?\nB: Vâng, thứ Ba cũng làm.\nA: Thứ Tư?\nB: Thứ Tư tôi nghỉ.\nA: Thứ Năm?\nB: Có làm việc.\nA: Thứ Sáu?\nB: Tôi nghỉ.\nA: Thứ Bảy và Chủ Nhật?\nB: Đương nhiên là nghỉ rồi.",
        [
            {"itemIndex": "1", "prompt": "Lịch làm việc từ Thứ 2 đến Chủ Nhật", "options": ["○ ○ × ○ × × ×", "○ ○ ○ × × × ×", "○ × ○ × ○ × ×"], "correctAnswer": "○ ○ × ○ × × ×", "explanation": "Làm T2(○), T3(○), T4 nghỉ(×), T5 làm(○), T6 nghỉ(×), T7 nghỉ(×), CN nghỉ(×)."}
        ]
    ),
    make_task(
        "I_CD1", 18, 5, "5. 電話番号は何番ですか", "5. Số điện thoại là số mấy?",
        "5. 電話番号は何番ですか\n1) A: 山田さんの携帯電話番号は何番ですか。\nB: 090-985-3178です。\n2) A: 神戸病院の電話番号は何番ですか。\nB: 078-4211-3168です。\n3) A: あそこは何番ですか。\nB: 336-8080です。",
        "5. Số điện thoại là số mấy?\n1) A: Số di động của anh Yamada là bao nhiêu?\nB: Là 090-985-3178.\n2) A: Số bệnh viện Kobe là bao nhiêu?\nB: Là 078-4211-3168.\n3) A: Chỗ đằng kia số mấy thế?\nB: Là 336-8080.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Số điện thoại Yamada", "options": ["090-985-3178", "090-985-3187"], "correctAnswer": "090-985-3178", "explanation": "Số: 090-985-3178."},
            {"itemIndex": "2", "prompt": "Câu 2: Số bệnh viện Kobe", "options": ["078-4211-3168", "078-4211-3186"], "correctAnswer": "078-4211-3168", "explanation": "Số: 078-4211-3168."},
            {"itemIndex": "3", "prompt": "Câu 3: Số điện thoại", "options": ["336-8080", "336-8008"], "correctAnswer": "336-8080", "explanation": "Số: 336-8080."}
        ]
    )
]
LESSONS_01_10.append({
    "lessonNumber": 4, "level": "N5",
    "title": "Bài 4: Thời gian, Giờ giấc & Lịch trình",
    "subtitle": "第4課: 時間・曜日・毎日のスケジュール",
    "tasks": l4_tasks
})

print("Lessons 1 to 4 defined.")

# ==================== LESSON 5 ====================
l5_tasks = [
    make_task(
        "I_CD1", 19, 1, "1. どこへ行きましたか", "1. Đã đi đâu?",
        "1. どこへ行きましたか\n1) A: 日曜日にどこへ行きましたか。\nB: 奈良へ行きました。\n2) A: どこへ行きましたか。\nB: どこも行きませんでした。うちで休みました。\n3) A: きのうどこへ行きましたか。\nB: 友達のうちへ行きました。",
        "1. Đã đi đâu?\n1) A: Chủ Nhật bạn đã đi đâu thế?\nB: Tôi đã đi Nara.\n2) A: Bạn có đi đâu không?\nB: Tôi chẳng đi đâu cả, ở nhà nghỉ ngơi.\n3) A: Hôm qua bạn đã đi đâu?\nB: Tôi đến nhà bạn chơi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đi đâu?", "options": ["e. 奈良 (Nara)", "a. 京都 (Kyoto)"], "correctAnswer": "e. 奈良 (Nara)", "explanation": "Đi Nara -> Đáp án e."},
            {"itemIndex": "2", "prompt": "Câu 2: Đi đâu?", "options": ["b. うち (Ở nhà)", "c. デパート (Trung tâm thương mại)"], "correctAnswer": "b. うち (Ở nhà)", "explanation": "Ở nhà không đi đâu -> Đáp án b."},
            {"itemIndex": "3", "prompt": "Câu 3: Đi đâu?", "options": ["d. 友達のうち (Nhà bạn)", "e. 学校 (Trường học)"], "correctAnswer": "d. 友達のうち (Nhà bạn)", "explanation": "Đến nhà bạn -> Đáp án d."}
        ]
    ),
    make_task(
        "I_CD1", 20, 2, "2. 何で行きますか", "2. Đi bằng phương tiện gì?",
        "2. 何で行きますか\n1) A: 会社まで何で行きますか。\nB: 電車で行きます。\n2) A: 駅まで何で行きますか。\nB: 自転車で行きます。\n3) A: 東京まで何で行きますか。\nB: 新幹線で行きます。\n4) A: スーパーまで何で行きますか。\nB: 歩いて行きます。",
        "2. Đi bằng phương tiện gì?\n1) A: Đến công ty bằng gì vậy?\nB: Đi bằng tàu điện.\n2) A: Đến nhà ga bằng gì?\nB: Đi xe đạp.\n3) A: Đến Tokyo bằng gì?\nB: Đi bằng tàu siêu tốc Shinkansen.\n4) A: Đến siêu thị bằng gì?\nB: Tôi đi bộ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đến công ty", "options": ["c. 電車 (Tàu điện)", "a. バス (Xe buýt)"], "correctAnswer": "c. 電車 (Tàu điện)", "explanation": "Đi bằng tàu điện."},
            {"itemIndex": "2", "prompt": "Câu 2: Đến nhà ga", "options": ["e. 自転車 (Xe đạp)", "b. タクシー (Taxi)"], "correctAnswer": "e. 自転車 (Xe đạp)", "explanation": "Đi bằng xe đạp."},
            {"itemIndex": "3", "prompt": "Câu 3: Đến Tokyo", "options": ["a. 新幹線 (Shinkansen)", "d. 飛行機 (Máy bay)"], "correctAnswer": "a. 新幹線 (Shinkansen)", "explanation": "Đi bằng Shinkansen."},
            {"itemIndex": "4", "prompt": "Câu 4: Đến siêu thị", "options": ["b. 歩いて (Đi bộ)", "c. 車 (Ô tô)"], "correctAnswer": "b. 歩いて (Đi bộ)", "explanation": "Đi bộ (歩いて)."}
        ]
    ),
    make_task(
        "I_CD1", 21, 3, "3. いつ行きますか", "3. Khi nào đi?",
        "3. いつ行きますか\n1) A: いつ旅行に行きますか。\nB: 来週の6日に行きます。金曜日です。\n2) A: いつ国へ帰りますか。\nB: 14日です。火曜日です。\n3) A: 試験はいつですか。\nB: 26日です。木曜日です。",
        "3. Khi nào đi?\n1) A: Khi nào bạn đi du lịch thế?\nB: Mùng 6 tuần sau, vào thứ Sáu.\n2) A: Khi nào bạn về nước?\nB: Ngày 14, thứ Ba.\n3) A: Kì thi diễn ra vào ngày nào?\nB: Ngày 26, thứ Năm.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ngày đi du lịch", "options": ["6日・金曜日 (b)", "5日・木曜日"], "correctAnswer": "6日・金曜日 (b)", "explanation": "Ngày 6 (6日 b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Ngày về nước", "options": ["14日・火曜日 (c)", "15日・水曜日"], "correctAnswer": "14日・火曜日 (c)", "explanation": "Ngày 14 (14日 c)."},
            {"itemIndex": "3", "prompt": "Câu 3: Ngày thi", "options": ["26日・木曜日 (d)", "25日・水曜日"], "correctAnswer": "26日・木曜日 (d)", "explanation": "Ngày 26 (26日 d)."}
        ]
    ),
    make_task(
        "I_CD1", 22, 4, "4. だれと行きますか", "4. Đi cùng với ai?",
        "4. だれと行きますか\n1) A: 富士山へだれと行きましたか。\nB: 会社の同僚と行きました。\n2) A: 京都へだれと行きましたか。\nB: 家族と行きました。\n3) A: 大阪へだれと行きますか。\nB: 一人で行きます。",
        "4. Đi cùng với ai?\n1) A: Bạn đi núi Phú Sĩ cùng ai vậy?\nB: Đi với đồng nghiệp ở công ty.\n2) A: Đi Kyoto với ai?\nB: Đi cùng gia đình.\n3) A: Đi Osaka với ai?\nB: Tôi đi một mình.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đi núi Phú Sĩ với ai?", "options": ["b. 友達・同僚", "a. 家族"], "correctAnswer": "b. 友達・同僚", "explanation": "Đi với bạn bè / đồng nghiệp (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đi Kyoto với ai?", "options": ["c. 家族", "a. 一人で"], "correctAnswer": "c. 家族", "explanation": "Đi với gia đình (c)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đi Osaka với ai?", "options": ["b. 一人で", "a. 彼女と"], "correctAnswer": "b. 一人で", "explanation": "Đi một mình (b)."}
        ]
    ),
    make_task(
        "I_CD1", 23, 5, "5. 誕生日はいつですか", "5. Sinh nhật vào ngày nào?",
        "5. 誕生日はいつですか\n1) A: お誕生日はいつですか。\nB: 12月8日です。\n2) A: ミラーさん、誕生日はいつですか。\nB: 9月1日です。\n3) A: サントスさん、誕生日はいつですか。\nB: 10月9日です。",
        "5. Sinh nhật vào ngày nào?\n1) A: Sinh nhật bạn vào ngày nào thế?\nB: Ngày 8 tháng 12.\n2) A: Anh Miller, sinh nhật anh ngày nào?\nB: Ngày 1 tháng 9.\n3) A: Anh Santos, sinh nhật anh ngày nào?\nB: Ngày 9 tháng 10.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ngày sinh nhật", "options": ["12/8", "8/12"], "correctAnswer": "12/8", "explanation": "Ngày 8 tháng 12 (12月8日)."},
            {"itemIndex": "2", "prompt": "Câu 2: Ngày sinh nhật Miller", "options": ["9/1", "1/9"], "correctAnswer": "9/1", "explanation": "Ngày 1 tháng 9 (9月1日)."},
            {"itemIndex": "3", "prompt": "Câu 3: Ngày sinh nhật Santos", "options": ["10/9", "9/10"], "correctAnswer": "10/9", "explanation": "Ngày 9 tháng 10 (10月9日)."}
        ]
    )
]
LESSONS_01_10.append({
    "lessonNumber": 5, "level": "N5",
    "title": "Bài 5: Đi lại, Phương tiện & Ngày tháng",
    "subtitle": "第5課: 行きます・来ます・乗り物・日付",
    "tasks": l5_tasks
})

# ==================== LESSON 6 ====================
l6_tasks = [
    make_task(
        "I_CD1", 24, 1, "1. 何を〜ますか", "1. Ăn/Uống/Làm cái gì?",
        "1. 何を〜ますか\n1) A: 毎朝何を食べますか。\nB: パンと卵を食べます。コーヒーを飲みます。\n2) A: 昼ごはんは何を食べましたか。\nB: そばとジュースです。\n3) A: 晩ごはんは何を食べますか。\nB: ごはんと魚を食べます。お茶を飲みます。",
        "1. Ăn/Uống/Làm cái gì?\n1) A: Mỗi sáng bạn ăn gì thế?\nB: Tôi ăn bánh mì với trứng, và uống cà phê.\n2) A: Buổi trưa bạn đã ăn gì?\nB: Mì soba và uống nước trái cây.\n3) A: Buổi tối bạn sẽ ăn gì?\nB: Ăn cơm với cá, và uống trà xanh.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Bữa sáng", "options": ["a. パン・d. 卵", "b. ごはん・c. 魚"], "correctAnswer": "a. パン・d. 卵", "explanation": "Bánh mì và trứng (a, d)."},
            {"itemIndex": "2", "prompt": "Câu 2: Bữa trưa", "options": ["b. そば・c. ジュース", "a. カレー・d. お茶"], "correctAnswer": "b. そば・c. ジュース", "explanation": "Mì soba và nước trái cây (b, c)."},
            {"itemIndex": "3", "prompt": "Câu 3: Bữa tối", "options": ["a. ごはん・d. 魚", "b. パン・c. ビール"], "correctAnswer": "a. ごはん・d. 魚", "explanation": "Cơm và cá (a, d)."}
        ]
    ),
    make_task(
        "I_CD1", 25, 2, "2. どこで〜ますか", "2. Làm ở đâu?",
        "2. どこで〜ますか\n1) A: どこで勉強しますか。\nB: 学校で勉強します。\n2) A: どこで本を読みますか。\nB: 図書館で読みます。\n3) A: どこで映画を見ますか。\nB: うちで見ます。",
        "2. Làm ở đâu?\n1) A: Bạn học ở đâu thế?\nB: Tôi học ở trường.\n2) A: Bạn đọc sách ở đâu?\nB: Tôi đọc ở thư viện.\n3) A: Bạn xem phim ở đâu?\nB: Tôi xem ở nhà.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nơi học bài", "options": ["学校 (Trường học)", "うち (Ở nhà)"], "correctAnswer": "学校 (Trường học)", "explanation": "Học ở trường (学校 b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nơi đọc sách", "options": ["図書館 (Thư viện)", "喫茶店 (Quán cafe)"], "correctAnswer": "図書館 (Thư viện)", "explanation": "Đọc ở thư viện (図書館 a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Nơi xem phim", "options": ["うち (Ở nhà)", "映画館 (Rạp chiếu phim)"], "correctAnswer": "うち (Ở nhà)", "explanation": "Xem ở nhà (うち b)."}
        ]
    ),
    make_task(
        "I_CD1", 26, 3, "3. どこで何をしましたか", "3. Đã làm gì ở đâu?",
        "3. どこで何をしましたか\n1) A: イギリスで何をしましたか。\nB: お城を見ました。\n2) A: アメリカで何をしましたか。\nB: コンサートに行きました。\n3) A: メキシコで何をしましたか。\nB: タコスを食べました。",
        "3. Đã làm gì ở đâu?\n1) A: Ở Anh bạn đã làm gì?\nB: Tôi đã đi tham quan lâu đài cổ.\n2) A: Ở Mỹ bạn đã làm gì?\nB: Tôi đã đi xem buổi hòa nhạc.\n3) A: Ở Mexico bạn đã làm gì?\nB: Tôi đã ăn món bánh Tacos.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ở Anh", "options": ["イギリス a (Tham quan lâu đài)", "イギリス b"], "correctAnswer": "イギリス a (Tham quan lâu đài)", "explanation": "Xem lâu đài (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Ở Mỹ", "options": ["アメリカ e (Xem hòa nhạc)", "アメリカ c"], "correctAnswer": "アメリカ e (Xem hòa nhạc)", "explanation": "Đi xem hòa nhạc (e)."},
            {"itemIndex": "3", "prompt": "Câu 3: Ở Mexico", "options": ["メキシコ c (Ăn tacos)", "メキシコ a"], "correctAnswer": "メキシコ c (Ăn tacos)", "explanation": "Ăn bánh tacos (c)."}
        ]
    ),
    make_task(
        "I_CD1", 27, 4, "4. 〜ませんか/〜ましょう", "4. Lời rủ rê & Đề nghị cùng làm",
        "4. 〜ませんか/〜ましょう\n1) A: 一緒に京都へ行きませんか。\nB: ええ、行きましょう。\n2) A: お茶を飲みませんか。\nB: いいですね、飲みましょう。\n3) A: テニスをしませんか。\nB: ええ、ぜひ。",
        "4. Lời rủ rê & Đề nghị cùng làm\n1) A: Cùng đi Kyoto với tôi nhé?\nB: Vâng, cùng đi nào.\n2) A: Uống tách trà nhé?\nB: Hay quá, uống thôi.\n3) A: Chơi tennis không bạn?\nB: Vâng, nhất định rồi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Rủ làm gì?", "options": ["f. Đi Kyoto", "a. Đi Tokyo"], "correctAnswer": "f. Đi Kyoto", "explanation": "Rủ cùng đi Kyoto (f)."},
            {"itemIndex": "2", "prompt": "Câu 2: Rủ làm gì?", "options": ["d. Uống trà", "b. Ăn cơm"], "correctAnswer": "d. Uống trà", "explanation": "Rủ uống trà (d)."},
            {"itemIndex": "3", "prompt": "Câu 3: Rủ làm gì?", "options": ["e. Chơi tennis", "c. Đá bóng"], "correctAnswer": "e. Chơi tennis", "explanation": "Rủ chơi tennis (e)."}
        ]
    )
]
LESSONS_01_10.append({
    "lessonNumber": 6, "level": "N5",
    "title": "Bài 6: Hành động hằng ngày & Rủ rê",
    "subtitle": "第6課: 食事・買い物・誘い (~ませんか)",
    "tasks": l6_tasks
})

# ==================== LESSON 7 ====================
l7_tasks = [
    make_task(
        "I_CD1", 28, 1, "1. 〜で〜ました", "1. Đã làm bằng công cụ/phương tiện gì?",
        "1. 〜で〜ました\n1) A: はさみで紙を切りました。\n2) A: 日本語で手紙を書きました。\n3) A: お箸でご飯を食べました。",
        "1. Đã làm bằng công cụ/phương tiện gì?\n1) A: Tôi đã dùng kéo cắt giấy.\n2) A: Tôi đã viết thư bằng tiếng Nhật.\n3) A: Tôi đã ăn cơm bằng đũa.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Cắt giấy bằng gì?", "options": ["b. はさみ (Kéo)", "a. ナイフ (Dao)"], "correctAnswer": "b. はさみ (Kéo)", "explanation": "Cắt bằng kéo (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Viết thư bằng gì?", "options": ["a. 日本語 (Tiếng Nhật)", "b. 英語 (Tiếng Anh)"], "correctAnswer": "a. 日本語 (Tiếng Nhật)", "explanation": "Viết bằng tiếng Nhật (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Ăn cơm bằng gì?", "options": ["a. はし (Đũa)", "b. スプーン (Thìa)"], "correctAnswer": "a. はし (Đũa)", "explanation": "Ăn bằng đũa (a)."}
        ]
    ),
    make_task(
        "I_CD1", 29, 2, "2. 日本語で何ですか", "2. Tiếng Nhật gọi là gì?",
        "2. 日本語で何ですか\n1) A: 「Thank you」は日本語で何ですか。\nB: 「ありがとう」です。\n2) A: 「Goodbye」は日本語で何ですか。\nB: 「さようなら」です。\n3) A: 「Excuse me」は日本語で何ですか。\nB: 「すみません」です。",
        "2. Tiếng Nhật gọi là gì?\n1) A: Từ \"Thank you\" tiếng Nhật là gì ạ?\nB: Là \"Arigatou\".\n2) A: Từ \"Goodbye\" tiếng Nhật là gì?\nB: Là \"Sayounara\".\n3) A: Từ \"Excuse me\" tiếng Nhật là gì?\nB: Là \"Sumimasen\".",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Cảm ơn tiếng Nhật", "options": ["c. ありがとう", "a. こんにちは"], "correctAnswer": "c. ありがとう", "explanation": "Arigatou (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Tạm biệt tiếng Nhật", "options": ["e. さようなら", "b. おやすみ"], "correctAnswer": "e. さようなら", "explanation": "Sayounara (e)."},
            {"itemIndex": "3", "prompt": "Câu 3: Xin lỗi tiếng Nhật", "options": ["d. すみません", "a. ごめんなさい"], "correctAnswer": "d. すみません", "explanation": "Sumimasen (d)."}
        ]
    ),
    make_task(
        "I_CD1", 30, 3, "3. あげます/もらいます・教えます/習います", "3. Cho - Nhận & Dạy - Học",
        "3. あげます/もらいます・教えます/習います\n1) A: 田中さんに花をあげました。\n2) A: 先生に辞書をもらいました。\n3) A: ワンさんに中国語を習いました。",
        "3. Cho - Nhận & Dạy - Học\n1) A: Tôi đã tặng hoa cho anh Tanaka.\n2) A: Tôi được thầy giáo tặng cuốn từ điển.\n3) A: Tôi đã học tiếng Trung từ anh Wang.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Tặng hoa cho ai?", "options": ["b. 田中さんにあげました", "a. もらいました"], "correctAnswer": "b. 田中さんにあげました", "explanation": "Tặng hoa cho Tanaka (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nhận từ điển từ ai?", "options": ["b. 先生にもらいました", "a. あげました"], "correctAnswer": "b. 先生にもらいました", "explanation": "Nhận từ thầy giáo (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Học tiếng Trung từ ai?", "options": ["b. ワンさんに習いました", "a. 教えます"], "correctAnswer": "b. ワンさんに習いました", "explanation": "Học từ anh Wang (b)."}
        ]
    ),
    make_task(
        "I_CD1", 31, 4, "4. もう〜ました/まだです", "4. Đã làm rồi hay chưa làm?",
        "4. もう〜ました/まだです\n1) A: もう荷物を送りましたか。\nB: いいえ、まだです。これから送ります。\n2) A: もう昼ごはんを食べましたか。\nB: はい、もう食べました。\n3) A: もう宿題をしましたか。\nB: いいえ、まだです。",
        "4. Đã làm rồi hay chưa làm?\n1) A: Bạn đã gửi kiện hành lý đi chưa?\nB: Chưa, tôi chưa gửi. Bây giờ tôi sẽ đi gửi đây.\n2) A: Bạn đã ăn trưa chưa?\nB: Vâng, tôi ăn rồi.\n3) A: Bạn đã làm bài tập về nhà chưa?\nB: Dạ chưa ạ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Đã gửi hành lý chưa?", "options": ["b. まだです (Chưa)", "a. もう送りました (Rồi)"], "correctAnswer": "b. まだです (Chưa)", "explanation": "Chưa gửi (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đã ăn trưa chưa?", "options": ["a. もう食べました (Rồi)", "b. まだです (Chưa)"], "correctAnswer": "a. もう食べました (Rồi)", "explanation": "Đã ăn rồi (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đã làm bài tập chưa?", "options": ["b. まだです (Chưa)", "a. もうしました (Rồi)"], "correctAnswer": "b. まだです (Chưa)", "explanation": "Chưa làm (b)."}
        ]
    )
]
LESSONS_01_10.append({
    "lessonNumber": 7, "level": "N5",
    "title": "Bài 7: Công cụ, Cho - Nhận & Đã làm chưa",
    "subtitle": "第7課: 道具・あげます・もらいます・もう〜ました",
    "tasks": l7_tasks
})

# ==================== LESSON 8 ====================
l8_tasks = [
    make_task(
        "I_CD1", 32, 1, "1. 〜はどうですか", "1. Như thế nào?",
        "1. 〜はどうですか\n1) A: 日本の生活はどうですか。\nB: 楽しいです。でも、忙しいです。\n2) A: 寮の部屋はどうですか。\nB: 狭いですが、きれいです。",
        "1. Như thế nào?\n1) A: Cuộc sống ở Nhật Bản thế nào?\nB: Vui lắm, nhưng mà cũng bận rộn.\n2) A: Phòng ở ký túc xá thế nào?\nB: Hơi chật nhưng rất sạch đẹp.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Cuộc sống ở Nhật", "options": ["b. 楽しい・忙しい", "a. 暇・つまらない"], "correctAnswer": "b. 楽しい・忙しい", "explanation": "Vui nhưng bận (b a b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Phòng ký túc xá", "options": ["b. 狭い・きれい", "a. 広い・汚い"], "correctAnswer": "b. 狭い・きれい", "explanation": "Chật nhưng sạch (b b)."}
        ]
    ),
    make_task(
        "I_CD1", 33, 2, "2. 〜はどんな〜ですか", "2. Là cái/nơi như thế nào?",
        "2. 〜はどんな〜ですか\n1) A: 富士山はどんな山ですか。\nB: とても高い山です。\n2) A: 京都はどんな町ですか。\nB: 静かで古い町です。\n3) A: サントスさんはどんな人ですか。\nB: 親切で面白い人です。\n4) A: IMCはどんな会社ですか。\nB: 新しくて大きい会社です。",
        "2. Là cái/nơi như thế nào?\n1) A: Núi Phú Sĩ là ngọn núi như thế nào?\nB: Là ngọn núi rất cao.\n2) A: Kyoto là thành phố thế nào?\nB: Là thành phố cổ kính và yên tĩnh.\n3) A: Anh Santos là người như thế nào?\nB: Là người tốt bụng và thú vị.\n4) A: IMC là công ty như thế nào?\nB: Là công ty mới và lớn.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Núi Phú Sĩ", "options": ["b. 高い山", "a. 低い山"], "correctAnswer": "b. 高い山", "explanation": "Núi cao (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Kyoto", "options": ["b. 静かで古い町", "a. にぎやかな町"], "correctAnswer": "b. 静かで古い町", "explanation": "Cổ kính yên tĩnh (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Anh Santos", "options": ["b. 親切で面白い人", "a. 静かな人"], "correctAnswer": "b. 親切で面白い人", "explanation": "Tốt bụng vui vẻ (b)."},
            {"itemIndex": "4", "prompt": "Câu 4: Công ty IMC", "options": ["a. 新しくて大きい会社", "b. 小さい会社"], "correctAnswer": "a. 新しくて大きい会社", "explanation": "Mới và lớn (a)."}
        ]
    ),
    make_task(
        "I_CD1", 34, 3, "3. どれですか", "3. Là cái nào?",
        "3. どれですか\n1) A: ミラーさんの傘はどれですか。\nB: あの青い大きいの傘です。\n2) A: 木村さんの車はどれですか。\nB: あの白い車です。\n3) A: 山田さんの鞄はどれですか。\nB: あの黒い鞄です。",
        "3. Là cái nào?\n1) A: Chiếc ô của anh Miller là cái nào vậy?\nB: Là cái màu xanh da trời to đằng kia kìa.\n2) A: Xe ô tô của chị Kimura là cái nào?\nB: Là chiếc xe màu trắng đằng kia.\n3) A: Cặp của anh Yamada là cái nào?\nB: Là chiếc cặp màu đen đằng kia.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ô của Miller", "options": ["b. 青くて大きい傘", "a. 赤い傘"], "correctAnswer": "b. 青くて大きい傘", "explanation": "Ô to màu xanh (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Xe của Kimura", "options": ["c. 白い車", "a. 赤い車"], "correctAnswer": "c. 白い車", "explanation": "Xe màu trắng (c)."},
            {"itemIndex": "3", "prompt": "Câu 3: Cặp của Yamada", "options": ["b. 黒い鞄", "a. 茶色い鞄"], "correctAnswer": "b. 黒い鞄", "explanation": "Cặp màu đen (b)."}
        ]
    )
]
LESSONS_01_10.append({
    "lessonNumber": 8, "level": "N5",
    "title": "Bài 8: Tính từ, Miêu tả đặc điểm & Ấn tượng",
    "subtitle": "第8課: 形容詞・特徴・どんな町・どれ",
    "tasks": l8_tasks
})

# ==================== LESSON 9 ====================
l9_tasks = [
    make_task(
        "I_CD1", 35, 1, "1. 〜が好きです", "1. Thích cái gì?",
        "1. 〜が好きです\n1) A: スポーツが好きですか。\nB: はい、野球が好きです。\n2) A: どんな音楽が好きですか。\nB: ジャズが好きです。",
        "1. Thích cái gì?\n1) A: Bạn có thích thể thao không?\nB: Có, tôi thích bóng chày.\n2) A: Bạn thích loại âm nhạc nào?\nB: Tôi thích nhạc Jazz.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Thể thao yêu thích", "options": ["b. 野球 (Bóng chày)", "a. サッカー (Bóng đá)"], "correctAnswer": "b. 野球 (Bóng chày)", "explanation": "Thích bóng chày (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Thể loại nhạc yêu thích", "options": ["b. ジャズ (Jazz)", "a. クラシック (Cổ điển)"], "correctAnswer": "b. ジャズ (Jazz)", "explanation": "Thích nhạc jazz (b)."}
        ]
    ),
    make_task(
        "I_CD1", 36, 2, "2. 〜が上手です/わかります", "2. Giỏi / Hiểu rõ cái gì?",
        "2. 〜が上手です/わかります\n1) A: 料理が上手ですか。\nB: はい、とても上手です。\n2) A: カラオケはどうですか。\nB: あまり上手じゃありません。\n3) A: 日本語がわかりますか。\nB: だいたいわかります。\n4) A: 英語がわかりますか。\nB: あまりわかりません。",
        "2. Giỏi / Hiểu rõ cái gì?\n1) A: Bạn nấu ăn có giỏi không?\nB: Vâng, tôi nấu rất giỏi.\n2) A: Hát karaoke thì sao?\nB: Tôi hát không giỏi lắm.\n3) A: Bạn có hiểu tiếng Nhật không?\nB: Tôi hiểu được đại khái.\n4) A: Bạn có hiểu tiếng Anh không?\nB: Tôi không hiểu lắm.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nấu ăn", "options": ["d. とても上手です", "a. 下手です"], "correctAnswer": "d. とても上手です", "explanation": "Rất giỏi (d)."},
            {"itemIndex": "2", "prompt": "Câu 2: Karaoke", "options": ["a. あまり上手じゃありません", "b. 上手です"], "correctAnswer": "a. あまり上手じゃありません", "explanation": "Không giỏi lắm (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Tiếng Nhật", "options": ["c. だいたいわかります", "a. 全然わかりません"], "correctAnswer": "c. だいたいわかります", "explanation": "Hiểu đại khái (c)."},
            {"itemIndex": "4", "prompt": "Câu 4: Tiếng Anh", "options": ["a. あまりわかりません", "b. よくわかります"], "correctAnswer": "a. あまりわかりません", "explanation": "Không hiểu lắm (a)."}
        ]
    ),
    make_task(
        "I_CD1", 37, 3, "3. 〜から(理由)", "3. Vì... (Lý do)",
        "3. 〜から(理由)\n1) A: ビールを飲みませんか。\nB: すみません、車ですから、飲みません。\n2) A: このパソコンを買いませんか。\nB: 高いですから、買いません。\n3) A: 明日コンサートに行きますか。\nB: 時間がありますから、行きます。",
        "3. Vì... (Lý do)\n1) A: Uống bia không bạn?\nB: Xin lỗi, vì tôi lái xe nên tôi không uống.\n2) A: Bạn mua chiếc máy tính này nhé?\nB: Vì đắt quá nên tôi không mua đâu.\n3) A: Ngày mai bạn có đi hòa nhạc không?\nB: Vì có thời gian nên tôi sẽ đi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Uống bia không?", "options": ["e. 飲みません (Vì lái xe)", "a. 飲みます"], "correctAnswer": "e. 飲みません (Vì lái xe)", "explanation": "Không uống vì lái xe (e)."},
            {"itemIndex": "2", "prompt": "Câu 2: Mua máy tính không?", "options": ["b. 買いません (Vì đắt)", "a. 買います"], "correctAnswer": "b. 買いません (Vì đắt)", "explanation": "Không mua vì đắt (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đi hòa nhạc không?", "options": ["d. 行きます (Vì rảnh)", "b. 行きません"], "correctAnswer": "d. 行きます (Vì rảnh)", "explanation": "Đi vì có thời gian (d)."}
        ]
    ),
    make_task(
        "I_CD1", 38, 4, "4. どうして/〜から", "4. Tại sao & Vì...",
        "4. どうして/〜から\n1) A: どうしてきのう来ませんでしたか。\nB: 約束がありましたから。\n2) A: どうして日本語を勉強しますか。\nB: 日本の会社で働きますから。\n3) A: どうして朝ごはんを食べませんでしたか。\nB: 時間がありませんでしたから。",
        "4. Tại sao & Vì...\n1) A: Tại sao hôm qua bạn không đến?\nB: Vì tôi có hẹn trước.\n2) A: Tại sao bạn học tiếng Nhật?\nB: Vì tôi sẽ làm việc ở công ty Nhật.\n3) A: Tại sao bạn không ăn sáng?\nB: Vì tôi không kịp giờ.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Lý do hôm qua không đến", "options": ["d. 約束がありましたから", "a. 病気でしたから"], "correctAnswer": "d. 約束がありましたから", "explanation": "Vì có hẹn (d)."},
            {"itemIndex": "2", "prompt": "Câu 2: Lý do học tiếng Nhật", "options": ["b. 日本の会社で働きますから", "a. アニメが好きだから"], "correctAnswer": "b. 日本の会社で働きますから", "explanation": "Vì làm công ty Nhật (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Lý do không ăn sáng", "options": ["a. 時間がありませんでしたから", "c. お腹がいっぱいでしたから"], "correctAnswer": "a. 時間がありませんでしたから", "explanation": "Vì không có thời gian (a)."}
        ]
    )
]
LESSONS_01_10.append({
    "lessonNumber": 9, "level": "N5",
    "title": "Bài 9: Sở thích, Năng lực & Lý do (~から)",
    "subtitle": "第9課: 好き・嫌い・上手・下手・理由から",
    "tasks": l9_tasks
})

# ==================== LESSON 10 ====================
l10_tasks = [
    make_task(
        "I_CD1", 39, 1, "1. 〜に〜がありますか", "1. Ở đâu có cái gì?",
        "1. 〜に〜がありますか\n1) A: 地下に銀行がありますか。\nB: いいえ、ありません。スーパーがあります。\n2) A: 2階にレストランがありますか。\nB: はい、あります。",
        "1. Ở đâu có cái gì?\n1) A: Ở dưới tầng hầm có ngân hàng không ạ?\nB: Không, không có. Có siêu thị thôi.\n2) A: Trên tầng 2 có nhà hàng không?\nB: Vâng, có nhà hàng.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Dưới tầng hầm có ngân hàng không?", "options": ["× ○ 地下 (Không có ngân hàng, có siêu thị)", "○ ○"], "correctAnswer": "× ○ 地下 (Không có ngân hàng, có siêu thị)", "explanation": "Không có ngân hàng, có siêu thị (× ○ 地下)."},
            {"itemIndex": "2", "prompt": "Câu 2: Trên tầng 2 có nhà hàng không?", "options": ["○ ○ 2階 (Có nhà hàng)", "× ×"], "correctAnswer": "○ ○ 2階 (Có nhà hàng)", "explanation": "Có nhà hàng trên tầng 2 (○ ○ 2階)."}
        ]
    ),
    make_task(
        "I_CD1", 40, 2, "2. 何がありますか/いますか", "2. Có cái gì / Có ai?",
        "2. 何がありますか/いますか\n1) A: 部屋に何がありますか。\nB: 机とベッドがあります。\n2) A: 庭にだれがいますか。\nB: 犬がいます。\n3) A: 箱の中に何がありますか。\nB: 手紙と写真があります。",
        "2. Có cái gì / Có ai?\n1) A: Trong phòng có những gì?\nB: Có bàn làm việc và giường ngủ.\n2) A: Ngoài sân có ai thế?\nB: Có chú chó đang ở đó.\n3) A: Trong chiếc hộp có những gì?\nB: Có những lá thư và vài tấm ảnh.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Trong phòng có gì?", "options": ["c. 机とベッド", "a. テレビ"], "correctAnswer": "c. 机とベッド", "explanation": "Bàn và giường (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Ngoài sân có con gì?", "options": ["e. 犬 (Chó)", "b. 猫 (Mèo)"], "correctAnswer": "e. 犬 (Chó)", "explanation": "Có con chó (e)."},
            {"itemIndex": "3", "prompt": "Câu 3: Trong hộp có gì?", "options": ["g. 手紙と写真", "d. 本"], "correctAnswer": "g. 手紙と写真", "explanation": "Thư và ảnh (g)."}
        ]
    ),
    make_task(
        "I_CD1", 41, 3, "3. 〜はどこにいますか", "3. Ai / Con vật ở đâu?",
        "3. 〜はどこにいますか\n1) A: 猫はどこにいますか。\nB: ベッドの下にいます。\n2) A: 子供はどこにいますか。\nB: 公園にいます。\n3) A: 田中さんはどこですか。\nB: 事務所にいます。",
        "3. Ai / Con vật ở đâu?\n1) A: Con mèo ở đâu vậy?\nB: Nó đang ở dưới gầm giường.\n2) A: Lũ trẻ đang ở đâu thế?\nB: Tụi nhỏ đang ở công viên.\n3) A: Anh Tanaka đang ở đâu?\nB: Anh ấy đang ở văn phòng.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Con mèo ở đâu?", "options": ["a. ベッドの下", "b. 机の上"], "correctAnswer": "a. ベッドの下", "explanation": "Dưới gầm giường (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Bọn trẻ ở đâu?", "options": ["e. 公園", "c. うち"], "correctAnswer": "e. 公園", "explanation": "Ở công viên (e)."},
            {"itemIndex": "3", "prompt": "Câu 3: Anh Tanaka ở đâu?", "options": ["c. 事務所", "b. 会議室"], "correctAnswer": "c. 事務所", "explanation": "Ở văn phòng (c)."}
        ]
    ),
    make_task(
        "I_CD1", 42, 4, "4. 〜はどこにありますか", "4. Đồ vật ở đâu?",
        "4. 〜はどこにありますか\n1) A: 鍵はどこにありますか。\nB: テーブルの上にあります。\n2) A: ポストはどこにありますか。\nB: 郵便局の前にあります。\n3) A: 薬屋はどこにありますか。\nB: 駅の隣にあります。",
        "4. Đồ vật ở đâu?\n1) A: Chìa khóa ở đâu vậy?\nB: Ở trên bàn đó.\n2) A: Hòm thư ở đâu vậy?\nB: Ở ngay trước bưu điện.\n3) A: Tiệm thuốc tây ở đâu?\nB: Ở bên cạnh nhà ga.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Chìa khóa ở đâu?", "options": ["a. テーブルの上", "b. かばんの中"], "correctAnswer": "a. テーブルの上", "explanation": "Trên bàn (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Hòm thư ở đâu?", "options": ["f. 郵便局の前", "c. 銀行の横"], "correctAnswer": "f. 郵便局の前", "explanation": "Trước bưu điện (f)."},
            {"itemIndex": "3", "prompt": "Câu 3: Hiệu thuốc ở đâu?", "options": ["i. 駅の隣", "e. 公園の前"], "correctAnswer": "i. 駅の隣", "explanation": "Cạnh nhà ga (i)."}
        ]
    )
]
LESSONS_01_10.append({
    "lessonNumber": 10, "level": "N5",
    "title": "Bài 10: Tồn tại & Vị trí người, đồ vật",
    "subtitle": "第10課: あります・います・物の位置・部屋",
    "tasks": l10_tasks
})

print("Lessons 1 to 10 successfully defined in LESSONS_01_10.")
