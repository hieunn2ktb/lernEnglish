# Worksheets for Lessons 21 to 30 based on Minna no Nihongo Kaite Oboeru

def q(question, meaning, options, correct_index, explanation):
    return {
        "question": question,
        "meaning": meaning,
        "options": options,
        "correctIndex": correct_index,
        "correctAnswer": options[correct_index],
        "explanation": explanation
    }

LESSONS_21_30 = {}

# ==================== LESSON 21 ====================
LESSONS_21_30[21] = [
    q("【21課 1 〜と思います】日本は 物価が（　）と 思います。", "Tôi nghĩ rằng vật giá ở Nhật Bản đắt đỏ.", ["高い", "高く", "高くて", "高かった"], 0, "Đứng trước と思います là câu ở thể thông thường (普通形): 高い."),
    q("【21課 1 〜と思います】明日は 雨が（　）と 思います。", "Tôi nghĩ rằng ngày mai trời sẽ mưa.", ["降る", "降ります", "降って", "降れば"], 0, "Phỏng đoán trong tương lai: động từ chia thể từ điển 降る + と思います."),
    q("【21課 2 〜と言いました】田中さんは「明日 休みます」（　）言いました。", "Anh Tanaka đã nói rằng: 'Ngày mai tôi nghỉ'.", ["と", "を", "に", "で"], 0, "Trích dẫn trực tiếp hoặc gián tiếp lời nói đi với trợ từ と."),
    q("【21課 2 〜と言いました】山田さんは 来週 東京へ（　）と 言いました。", "Anh Yamada nói rằng tuần sau anh ấy sẽ đi Tokyo.", ["行く", "行きます", "行って", "行った"], 0, "Trích dẫn gián tiếp dùng thể thông thường: 行く と言いました."),
    q("【21課 3 〜でしょう】明日は 暑い（　）。", "Ngày mai chắc là trời sẽ nóng nhỉ.", ["でしょう", "ですか", "でした", "だろうか"], 0, "Xác nhận hoặc phỏng đoán thân mật với người nghe: ～でしょう.")
]

# ==================== LESSON 22 ====================
LESSONS_21_30[22] = [
    q("【22課 1 連体修飾】これは ミラーさんが（　）ケーキです。", "Đây là chiếc bánh kem mà anh Miller đã làm.", ["作った", "作ります", "作って", "作るの"], 0, "Mệnh đề bổ ngữ cho danh từ (ケーキ) dùng thể thông thường: 作った ケーキ."),
    q("【22課 1 連体修飾】あそこで 本を（　）人は だれですか。", "Người đang đọc sách đằng kia là ai thế?", ["読んでいる", "読みます", "読んで", "読んだの"], 0, "Hành động đang diễn ra bổ nghĩa cho danh từ 人: 読んでいる人."),
    q("【22課 2 着用動詞】あそこに 眼鏡を（　）人が います。", "Đằng kia có người đang đeo kính.", ["かけている", "着ている", "はいている", "かぶっている"], 0, "Đeo kính dùng động từ かけます → かけている."),
    q("【22課 2 着用動詞】シャツを（　）人は 山田さんです。", "Người đang mặc áo sơ mi là anh Yamada.", ["着ている", "はいている", "かぶっている", "かけている"], 0, "Mặc áo từ thắt lưng trở lên (áo, vest) dùng 着ます (きます) → 着ている."),
    q("【22課 2 着用動詞】靴を（　）人は だれですか。", "Người đang đi giày là ai thế?", ["はいている", "着ている", "かぶっている", "つけている"], 0, "Đi giày, mặc quần (từ thắt lưng trở xuống) dùng 履きます (はきます) → はいている.")
]

# ==================== LESSON 23 ====================
LESSONS_21_30[23] = [
    q("【23課 1 〜とき】図書館で 本を（　）とき、カードが 要ります。", "Khi mượn sách ở thư viện thì cần có thẻ.", ["借りる", "借りた", "借りて", "借ります"], 0, "Hành động làm thẻ xảy ra trước khi mượn: dùng thể từ điển 借りるとき."),
    q("【23課 1 〜とき】うちへ（　）とき、「ただいま」と 言います。", "Khi về đến nhà, ta nói câu 'Tadaima'.", ["帰った", "帰る", "帰って", "帰ります"], 0, "Về đến nơi rồi mới nói 'Tadaima' nên dùng thể quá khứ: 帰ったとき."),
    q("【23課 2 〜と】この ボタンを（　）と、おつりが 出ます。", "Hễ ấn nút này thì tiền thừa sẽ ra.", ["押す", "押した", "押して", "押します"], 0, "Điều kiện tự nhiên tất yếu: [Động từ thể từ điển] + と (押すと)."),
    q("【23課 2 道案内】あの 交差点を 右へ（　）と、銀行が あります。", "Hễ rẽ phải ở ngã tư đằng kia thì sẽ thấy ngân hàng.", ["曲がる", "曲がった", "曲がって", "曲がります"], 0, "Chỉ đường bằng mẫu câu [Động từ thể từ điển] + と: 曲がると.")
]

# ==================== LESSON 24 ====================
LESSONS_21_30[24] = [
    q("【24課 1 〜てあげます】わたしは 木村さんに 傘を（　）あげました。", "Tôi đã cho chị Kimura mượn ô.", ["貸して", "借りて", "もらって", "くれて"], 0, "Làm giúp ai việc gì: [Động từ thể て] + あげます."),
    q("【24課 2 〜てもらいます】わたしは 鈴木さんに 写真を（　）もらいました。", "Tôi đã được anh Suzuki chụp ảnh cho.", ["撮って", "撮られて", "あげて", "くれて"], 0, "Nhận được sự giúp đỡ từ người khác: 鈴木さんに [Động từ thể て] + もらいました."),
    q("【24課 3 〜てくれます】母は わたしに セーターを（　）くれました。", "Mẹ đã đan áo len cho tôi.", ["編んで", "編まれて", "編み", "編む"], 0, "Người khác làm cho mình (chủ ngữ là người làm): [Động từ thể て] + くれます.")
]

# ==================== LESSON 25 ====================
LESSONS_21_30[25] = [
    q("【25課 1 〜たら】雨が（　）ら、出かけません。", "Nếu trời mưa thì tôi sẽ không ra ngoài.", ["降った", "降る", "降って", "降ります"], 0, "Điều kiện giả định: [Động từ thể た] + ら (降ったら)."),
    q("【25課 1 〜たら】時間が（　）ら、映画を 見に 行きましょう。", "Nếu có thời gian thì cùng đi xem phim nhé.", ["あった", "ある", "あって", "あります"], 0, "あったら: nếu có (thời gian)."),
    q("【25課 2 〜ても】雨が（　）も、サッカーを します。", "Dù trời mưa thì tôi vẫn chơi bóng đá.", ["降って", "降った", "降る", "降り"], 0, "Điều kiện nhượng bộ (cho dù...): [Động từ thể て] + も (降っても)."),
    q("【25課 2 〜ても】いくら（　）も、わかりません。", "Dù suy nghĩ bao nhiêu đi nữa tôi vẫn không hiểu.", ["考えて", "考えた", "考える", "考え"], 0, "Phó từ いくら đi với thể ても mang nghĩa 'cho dù... bao nhiêu đi nữa'.")
]

# ==================== LESSON 26 ====================
LESSONS_21_30[26] = [
    q("【26課 1 〜んです】A: どうして 遅れたんですか。 B: バスが（　）んです。", "A: Tại sao bạn lại đến muộn thế? - B: Vì xe buýt không đến ạ.", ["来なかった", "来ません", "来ない", "来ないでした"], 0, "Giải thích lý do dùng thể thông thường + んです: 来なかったんです."),
    q("【26課 2 〜んですが】日本語で レポートを 書いた（　）が、見て いただけませんか。", "Tôi đã viết bài báo cáo bằng tiếng Nhật, bạn có thể xem giúp tôi được không?", ["んです", "のです", "ので", "から"], 0, "Mở đầu đề nghị nhờ vả nhẹ nhàng: [Thể thông thường] + んですが."),
    q("【26課 3 〜ていただけませんか】生け花を 習いたいんですが、いい 先生を 紹介して（　）か。", "Tôi muốn học cắm hoa, bạn có thể giới thiệu cho tôi giáo viên giỏi được không?", ["いただけません", "くださいません", "もらいません", "あげません"], 0, "Nhờ người khác làm giúp một cách lịch sự, kính cẩn: ～ていただけませんか."),
    q("【26課 4 疑問詞＋たらいいですか】カメラを 買いたいんですが、どこで（　）いいですか。", "Tôi muốn mua máy ảnh, nên mua ở đâu thì tốt nhỉ?", ["買ったら", "買うと", "買えば", "買って"], 0, "Xin lời khuyên nên làm gì: [Từ để hỏi] + [Động từ thể た] + らいいですか (どこで買ったらいいですか).")
]

# ==================== LESSON 27 ====================
LESSONS_21_30[27] = [
    q("【27課 1 可能動詞】「行きます」の 可能動詞は 何ですか。", "Thể khả năng của いききます (đi) là gì?", ["行ける", "行かれる", "行かせる", "行きる"], 0, "Nhóm 1 đổi vần i sang vần e: 行きます → 行ける."),
    q("【27課 1 可能動詞】「食べます」の 可能動詞は 何ですか。", "Thể khả năng của たべます (ăn) là gì?", ["食べられる", "食べれる", "食べさせる", "食べられる"], 0, "Nhóm 2 bỏ ます thêm られる: 食べます → 食べられる."),
    q("【27課 2 助詞が】わたしは 日本語（　）話せます。", "Tôi có thể nói được tiếng Nhật.", ["が", "を", "に", "で"], 0, "Tân ngữ trực tiếp của động từ thể khả năng thường đi với trợ từ が."),
    q("【27課 3 見える・聞こえる】窓から 富士山が（　）。", "Từ cửa sổ có thể nhìn thấy núi Phú Sĩ.", ["見えます", "見られます", "見ます", "見ています"], 0, "Sự vật tự lọt vào tầm mắt (khả năng tự nhiên) dùng 見えます."),
    q("【27課 4 しか〜ない】ひらがな（　）書けません。（chỉ duy nhất hiragana）", "Tôi chỉ có thể viết được chữ Hiragana.", ["しか", "だけ", "ばかり", "ほど"], 0, "しか đi với thể phủ định biểu thị ý nghĩa 'chỉ duy nhất' (しか書けません).")
]

# ==================== LESSON 28 ====================
LESSONS_21_30[28] = [
    q("【28課 1 〜ながら】音楽を（　）ながら、勉強します。", "Tôi vừa nghe nhạc vừa học bài.", ["聞き", "聞く", "聞いて", "聞いた"], 0, "Hai hành động đồng thời: [Động từ 1 bỏ ます] + ながら、[Động từ 2]. Hành động 2 là hành động chính."),
    q("【28課 2 習慣の〜ています】休みの 日は いつも 絵を（　）います。", "Vào ngày nghỉ tôi luôn vẽ tranh.", ["描いて", "描きます", "描く", "描いた"], 0, "Thói quen diễn ra thường xuyên dùng: ～ています (描いています)."),
    q("【28課 3 〜し、〜し】この 店は 料理も おいしい（　）、値段も 安いです。", "Quán ăn này món ăn vừa ngon, giá cả lại rẻ.", ["し", "から", "ので", "けど"], 0, "Liệt kê nhiều lý do, ưu điểm tương đồng dùng thể thông thường + し.")
]

# ==================== LESSON 29 ====================
LESSONS_21_30[29] = [
    q("【29課 1 自他動詞】風で ドアが（　）。（Tự nó mở ra）", "Do gió nên cánh cửa tự mở ra.", ["開きました", "開けました", "閉めました", "消えました"], 0, "Tự động từ: 開きます (ドアが開く); Tha động từ: 開けます (ドアを開ける)."),
    q("【29課 2 状態の〜ています】窓が（　）います。", "Cửa sổ đang mở.", ["開いて", "開けて", "閉めて", "消して"], 0, "Tự động từ + ています biểu thị trạng thái đang diễn ra: 窓が開いています."),
    q("【29課 3 〜てしまいました】電車に かばんを（　）しまいました。", "Tôi đã lỡ để quên cặp trên tàu điện.", ["忘れて", "忘れた", "忘れる", "忘れ"], 0, "Lỡ làm việc gì ngoài ý muốn, mang tâm trạng tiếc nuối: [Động từ thể て] + しまいました.")
]

# ==================== LESSON 30 ====================
LESSONS_21_30[30] = [
    q("【30課 1 〜てあります】カレンダーに 今月の 予定が（　）あります。", "Trên tờ lịch có ghi lịch trình của tháng này.", ["書いて", "書かれて", "書く", "書いた"], 0, "Tha động từ + てあります biểu thị trạng thái có chủ ý chuẩn bị của con người: 書いてあります."),
    q("【30課 2 〜ておきます】旅行の まえに、切符を（　）おきます。", "Trước chuyến du lịch, tôi mua sẵn vé.", ["買って", "買います", "買う", "買った"], 0, "Làm sẵn việc gì để chuẩn bị cho mục đích sau: [Động từ thể て] + おきます."),
    q("【30課 2 そのまま】ハサミを 使ったら、元の 所に（　）おいて ください。", "Dùng kéo xong hãy để lại chỗ cũ nhé.", ["戻して", "戻す", "戻した", "戻し"], 0, "Giữ nguyên trạng thái cần thiết: 戻しておいてください."),
    q("【30課 3 まだ〜ています】まだ 雨が（　）います。", "Trời vẫn đang mưa.", ["降って", "降ります", "降る", "降った"], 0, "Hành động vẫn đang tiếp diễn dùng まだ + [Động từ thể て] + います.")
]

print("Lesson 21 to 30 worksheet templates loaded.")
