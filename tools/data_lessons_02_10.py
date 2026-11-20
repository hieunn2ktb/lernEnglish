# Worksheets for Lessons 2 to 10 based on Minna no Nihongo Kaite Oboeru

def q(question, meaning, options, correct_index, explanation):
    return {
        "question": question,
        "meaning": meaning,
        "options": options,
        "correctIndex": correct_index,
        "correctAnswer": options[correct_index],
        "explanation": explanation
    }

LESSONS_02_10 = {}

# ==================== LESSON 2 ====================
LESSONS_02_10[2] = [
    q("【2課 1 物の名前】言葉の意味を 調べるときに 使う 本は 何ですか。", "Cuốn sách dùng khi tra cứu ý nghĩa của từ ngữ là gì?", ["じしょ", "ほん", "ざっし", "ノート"], 0, "じしょ (từ điển) là sách dùng để tra cứu từ vựng."),
    q("【2課 1 物の名前】毎日 配達される、ニュースが 書いてある 紙は 何ですか。", "Tờ giấy có viết tin tức được phát hàng ngày là gì?", ["しんぶん", "ざっし", "てちょう", "めいし"], 0, "しんぶん (báo) là báo chí cập nhật tin tức hàng ngày."),
    q("【2課 1 物の名前】写真や 記事が たくさん ある 本は 何ですか。", "Cuốn sách có nhiều bài viết và hình ảnh là gì?", ["ざっし", "じしょ", "ノート", "ほん"], 0, "ざっし: tạp chí."),
    q("【2課 1 物の名前】予定や 電話番号などを 書く 小さい 手帳は 何ですか。", "Sổ tay nhỏ dùng để ghi lịch trình hoặc số điện thoại là gì?", ["てちょう", "しんぶん", "めいし", "カード"], 0, "てちょう: sổ tay cá nhân."),
    q("【2課 1 物の名前】名前や 会社の 連絡先が 書いてある 小さい 紙は 何ですか。", "Mảnh giấy nhỏ có ghi tên và thông tin liên lạc của công ty là gì?", ["めいし", "てちょう", "カード", "しんぶん"], 0, "めいし: danh thiếp."),
    q("【2課 2 疑問文】（話し手の近く）A:（　）は 何ですか。 B: これは 辞書です。", "A: Cái này là cái gì? - B: Đây là từ điển.", ["これ", "それ", "あれ"], 0, "Vật ở gần người nói thì dùng これ."),
    q("【2課 2 疑問文】（聞き手の近く）A:（　）は 日本語の 本ですか。 B: はい、そうです。", "A: Đó là sách tiếng Nhật à? - B: Vâng, đúng vậy.", ["それ", "これ", "あれ"], 0, "Vật ở gần người nghe thì người nói dùng それ."),
    q("【2課 2 疑問文】（双方から遠い）A:（　）は だれの かばんですか。 B: 田中さんのです。", "A: Đằng kia là cặp sách của ai? - B: Của anh Tanaka.", ["あれ", "それ", "これ"], 0, "Vật ở xa cả người nói và người nghe thì dùng あれ."),
    q("【2課 2 疑問文】A: これは シャープペンシルですか。（　）ボールペンですか。 B: ボールペンです。", "A: Đây là bút chì kim hay là bút bi? - B: Là bút bi.", ["それとも", "また", "そして", "しかし"], 0, "Dùng câu hỏi lựa chọn S1ですか、S2ですか (hoặc それとも)."),
    q("【2課 2 疑問文】A: それは なんの 雑誌ですか。 B:（　）の 雑誌です。", "A: Đó là tạp chí gì vậy? - B: Là tạp chí về ô tô.", ["じどうしゃ", "わたし", "たなかさん", "IMC"], 0, "Hỏi về nội dung cuốn tạp chí (なんの雑誌) trả lời về chủ đề (じどうしゃ - ô tô)."),
    q("【2課 2 疑問文】（　）傘は わたしのです。", "Cây dù này là của tôi.", ["この", "これ", "ここ", "こちら"], 0, "Trước danh từ (傘) phải dùng từ chỉ thị dạng この/その/あの.")
]

# ==================== LESSON 3 ====================
LESSONS_02_10[3] = [
    q("【3課 1 場所】学生が 授業を 受ける 部屋は どこですか。", "Căn phòng nơi học sinh học bài là phòng nào?", ["きょうしつ", "じむしょ", "かいぎしつ", "しょくどう"], 0, "きょうしつ: lớp học, phòng học."),
    q("【3課 1 場所】会社の 人が 仕事を したり 電話を かけたり する 所は 何ですか。", "Nơi người trong công ty làm việc, gọi điện thoại là gì?", ["じむしょ", "きょうしつ", "しょくどう", "へや"], 0, "じむしょ: văn phòng làm việc."),
    q("【3課 1 場所】ごはんを 食べる 所は どこですか。", "Nơi ăn cơm là ở đâu?", ["しょくどう", "じむしょ", "うけつけ", "ロビー"], 0, "しょくどう: nhà ăn, căng tin."),
    q("【3課 1 場所】（話し手のいる場所）A:（　）は 教室です。", "Chỗ này là phòng học.", ["ここ", "そこ", "あそこ"], 0, "Chỉ vị trí gần người nói: ここ."),
    q("【3課 1 場所】（聞き手のいる場所）A:（　）は 受付です。", "Chỗ đó là quầy tiếp tân.", ["そこ", "ここ", "あそこ"], 0, "Chỉ vị trí ở phía người nghe: そこ."),
    q("【3課 1 場所】（遠い場所・丁寧）A: お手洗いは（　）で ございます。", "Nhà vệ sinh ở đằng kia ạ.", ["あちら", "そちら", "こちら", "どちら"], 0, "Dạng lịch sự chỉ phương hướng/vị trí xa: あちら."),
    q("【3課 2 数字・買い物】この ネクタイは（　）ですか。 B: 5,000円です。", "Chiếc cà vạt này giá bao nhiêu? - B: 5.000 yên.", ["いくら", "なん", "どこ", "どちら"], 0, "Hỏi giá tiền dùng いくら."),
    q("【3課 3 どこの】A: これは（　）の カメラですか。 B: 日本の カメラです。", "A: Đây là máy ảnh của nước nào/hãng nào? - B: Máy ảnh của Nhật Bản.", ["どこ", "だれ", "なん", "どちら"], 0, "Hỏi xuất xứ nguồn gốc của đồ vật dùng [どこ] の [danh từ]."),
    q("【3課 4 ことばを並べる】会社は（　）ですか。 B: IMCです。", "A: Công ty của bạn là công ty nào? - B: Là công ty IMC.", ["どちら", "だれ", "なんさい", "いくら"], 0, "Hỏi tên công ty, trường học một cách lịch sự dùng どちら (hoặc どこ).")
]

# ==================== LESSON 4 ====================
LESSONS_02_10[4] = [
    q("【4課 1 時刻】A: 今（　）ですか。 B: 4時半です。", "A: Bây giờ là mấy giờ? - B: 4 rưỡi.", ["なんじ", "なんぷん", "なんようび", "なんにち"], 0, "Hỏi giờ dùng なんじ (mấy giờ)."),
    q("【4課 1 時刻】朝 6時（　）起きます。", "Tôi thức dậy vào lúc 6 giờ sáng.", ["に", "で", "を", "へ"], 0, "Thời điểm có con số cụ thể đi với trợ từ に."),
    q("【4課 2 曜日】A: 今日は 何曜日ですか。 B:（　）です。", "A: Hôm nay là thứ mấy? - B: Là thứ Năm.", ["もくようび", "4じ", "4がつ", "4にち"], 0, "Trả lời cho câu hỏi thứ trong tuần (何曜日): もくようび (thứ Năm)."),
    q("【4課 2 から・まで】銀行は 9時から 3時（　）です。", "Ngân hàng mở cửa từ 9 giờ đến 3 giờ.", ["まで", "から", "に", "で"], 0, "Điểm kết thúc của thời gian/không gian đi với まで (đến)."),
    q("【4課 3 動詞の時制】毎朝 7時に（　）。", "Mỗi sáng tôi thức dậy lúc 7 giờ.", ["おきます", "おきました", "おきません", "おきませんでした"], 0, "Thói quen hàng ngày ở hiện tại dùng thể khẳng định: おきます."),
    q("【4課 3 動詞の時制】昨日の 晩 勉強（　）。", "Tối hôm qua tôi đã không học bài.", ["しませんでした", "しません", "します", "しました"], 0, "Hành động phủ định trong quá khứ (昨晩) dùng: ～ませんでした."),
    q("【4課 4 時刻に】昼休みは 12時（　）1時までです。", "Nghỉ trưa là từ 12 giờ đến 1 giờ.", ["から", "まで", "に", "で"], 0, "Thời điểm bắt đầu đi với から (từ)."),
    q("【4課 4 助詞と】土曜日（　）日曜日は 休みです。", "Thứ Bảy và Chủ Nhật là ngày nghỉ.", ["と", "に", "で", "や"], 0, "Trợ từ と dùng để liệt kê đầy đủ các danh từ (thứ Bảy và Chủ Nhật).")
]

# ==================== LESSON 5 ====================
LESSONS_02_10[5] = [
    q("【5課 1 日付】4月（　）は 私の 誕生日です。", "Ngày mùng 1 tháng 4 là sinh nhật tôi.", ["ついたち", "いちにち", "ひとつ", "つぎ"], 0, "Ngày mùng 1 hàng tháng đọc là ついたち."),
    q("【5課 1 日付】A:（　）日本へ 来ましたか。 B: 先月 来ました。", "A: Bạn đã đến Nhật khi nào? - B: Tôi đến hồi tháng trước.", ["いつ", "どこ", "なんじ", "なんで"], 0, "Từ để hỏi về thời gian (khi nào) là いつ."),
    q("【5課 2 移動の動詞】明日 京都（　）行きます。", "Ngày mai tôi sẽ đi Kyoto.", ["へ", "で", "を", "から"], 0, "Phương hướng di chuyển của 行きます/来ます/帰ります đi với trợ từ へ (hoặc に)."),
    q("【5課 2 手段で】電車（　）会社へ 行きます。", "Tôi đi đến công ty bằng tàu điện.", ["で", "へ", "に", "を"], 0, "Phương tiện giao thông dùng để di chuyển đi với trợ từ で."),
    q("【5課 2 徒歩で】駅まで（　）で行きます。", "Tôi đi bộ đến ga.", ["あるいて", "あるく", "あるきで", "あるきます"], 0, "Đi bộ là あるいて, không dùng trợ từ で sau あるいて (歩いて行きます)."),
    q("【5課 2 同伴者と】友達（　）日本へ 来ました。", "Tôi đã đến Nhật Bản cùng với bạn bè.", ["と", "に", "へ", "で"], 0, "Người cùng thực hiện hành động đi với trợ từ と (cùng với)."),
    q("【5課 2 単独で】一人（　）スーパーへ 行きました。", "Tôi đã đi siêu thị một mình.", ["で", "に", "と", "へ"], 0, "Làm việc gì một mình dùng 一人で (không thêm trợ từ với người khác).")
]

# ==================== LESSON 6 ====================
LESSONS_02_10[6] = [
    q("【6課 1 を〜ます】毎朝 ごはん（　）食べます。", "Mỗi sáng tôi ăn cơm.", ["を", "に", "で", "が"], 0, "Tân ngữ trực tiếp chịu tác động của ngoại động từ đi với trợ từ を."),
    q("【6課 2 場所で】図書館（　）本を 読みます。", "Tôi đọc sách ở thư viện.", ["で", "に", "へ", "を"], 0, "Nơi diễn ra hành động đi với trợ từ で."),
    q("【6課 3 何をしますか】日曜日 何を（　）か。 B: テニスを しました。", "Chủ Nhật bạn đã làm gì? - B: Tôi đã chơi quần vợt.", ["しました", "します", "して", "する"], 0, "Hỏi về hành động đã thực hiện trong quá khứ dùng 何をしましたか."),
    q("【6課 4 〜ませんか】いっしょに 京都へ（　）か。 B: ええ、行きましょう。", "Cùng đi Kyoto với tôi không? - B: Vâng, cùng đi nào.", ["行きません", "行きます", "行かない", "行った"], 0, "Mẫu câu rủ rê, mời mọc lịch sự: ～ませんか."),
    q("【6課 4 〜ましょう】ちょっと（　）ましょう。 B: ええ、そうしましょう。", "Nghỉ một chút nào! - B: Vâng, làm vậy đi.", ["休み", "休む", "休んで", "休んだ"], 0, "Mẫu câu đề nghị, cùng nhau làm gì: [Động từ thể ます bỏ ます] + ましょう.")
]

# ==================== LESSON 7 ====================
LESSONS_02_10[7] = [
    q("【7課 1 道具で】はし（　）ごはんを 食べます。", "Tôi ăn cơm bằng đũa.", ["で", "を", "に", "と"], 0, "Dụng cụ, công cụ dùng để thực hiện hành động đi với trợ từ で."),
    q("【7課 2 言語で】「ありがとう」は 英語（　）何ですか。", "'Arigatou' trong tiếng Anh là gì?", ["で", "に", "を", "は"], 0, "Ngôn ngữ dùng để biểu đạt đi với trợ từ で."),
    q("【7課 3 あげます】母（　）花を あげました。", "Tôi đã tặng hoa cho mẹ.", ["に", "で", "を", "から"], 0, "Người nhận hành động tặng/cho đi với trợ từ に."),
    q("【7課 4 もらいます】先生（　）本を もらいました。", "Tôi đã nhận cuốn sách từ thầy giáo.", ["に", "で", "を", "へ"], 0, "Người cho mình nhận đi với trợ từ に (hoặc から)."),
    q("【7課 5 もう・まだ】A: もう 昼ごはんを 食べましたか。 B: いいえ、（　）です。", "A: Bạn đã ăn trưa chưa? - B: Chưa, tôi chưa ăn.", ["まだ", "もう", "いま", "それから"], 0, "Trả lời chưa làm gì cho câu hỏi もう～ましたか là いいえ、まだです.")
]

# ==================== LESSON 8 ====================
LESSONS_02_10[8] = [
    q("【8課 1 形容詞の分類】富士山は（　）山です。", "Núi Phú Sĩ là ngọn núi cao.", ["たかい", "たかな", "たかくて", "たかだ"], 0, "Tính từ đuôi い bổ nghĩa cho danh từ giữ nguyên い (たかい山)."),
    q("【8課 1 形容詞の分類】桜は（　）花です。", "Hoa anh đào là loài hoa đẹp.", ["きれいな", "きれいい", "きれいく", "きれい"], 0, "Tính từ đuôi な bổ nghĩa cho danh từ phải thêm な (きれいな花)."),
    q("【8課 2 形容詞の否定】この 本は（　）。（おもしろい）", "Cuốn sách này không thú vị.", ["おもしろくないです", "おもしろいじゃありません", "おもしろくないでした", "おもしろいです"], 0, "Phủ định của tính từ đuôi い đổi い thành くないです."),
    q("【8課 2 形容詞の否定】この 町は（　）。（にぎやか）", "Thị trấn này không nhộn nhịp.", ["にぎやかじゃ ありません", "にぎやかtheorem", "にぎやかではないです", "にぎやかじゃないです"], 0, "Phủ định của tính từ đuôi な là [bỏ な] + じゃありません."),
    q("【8課 3 そして・が】日本の 食べ物は おいしいですが、（　）。", "Đồ ăn Nhật Bản ngon nhưng đắt.", ["たかいです", "やすいです", "きれいです", "しずかです"], 0, "Liên từ が nối 2 vế tương phản (ngon nhưng đắt: たかいです)."),
    q("【8課 4 どんな】日本の 生活は（　）ですか。 B: とても 楽しいです。", "Cuộc sống ở Nhật thế nào? - B: Rất vui.", ["どう", "どんな", "どれ", "どちら"], 0, "Hỏi về cảm tưởng, tính chất của sự vật dùng どうですか.")
]

# ==================== LESSON 9 ====================
LESSONS_02_10[9] = [
    q("【9課 1 が 好きです】わたしは イタリア料理（　）好きです。", "Tôi thích món ăn Ý.", ["が", "を", "に", "で"], 0, "Tân ngữ của các từ chỉ sở thích, năng lực (好き, 嫌い, 上手, 下手) đi với trợ từ が."),
    q("【9課 1 が わかります】日本語（　）少し わかります。", "Tôi hiểu một chút tiếng Nhật.", ["が", "を", "で", "に"], 0, "Động từ わかります (hiểu) và あります (có) đi với trợ từ が."),
    q("【9課 2 副詞】英語が（　）わかりません。", "Tôi hoàn toàn không hiểu tiếng Anh.", ["ぜんぜん", "よく", "だいたい", "たくさん"], 0, "ぜんぜん đi với câu phủ định mang nghĩa 'hoàn toàn không'."),
    q("【9課 2 副詞】時間（　）ありませんから、タクシーで 行きます。", "Vì không có thời gian nên tôi đi bằng taxi.", ["が", "を", "に", "で"], 0, "Chủ ngữ của ありません đi với が: 時間がありません."),
    q("【9課 3 から】時間が ありません（　）、テレビを 見ません。", "Vì không có thời gian nên tôi không xem TV.", ["から", "ので", "けど", "でも"], 0, "Vế chỉ nguyên nhân, lý do đứng trước から (vì... nên...)."),
    q("【9課 4 どうして】A:（　）昨日 休みましたか。 B: 熱が ありましたから。", "A: Tại sao hôm qua bạn nghỉ học? - B: Vì tôi bị sốt.", ["どうして", "なん", "いつ", "どう"], 0, "Từ để hỏi lý do 'tại sao' là どうして (trả lời kết thúc bằng から).")
]

# ==================== LESSON 10 ====================
LESSONS_02_10[10] = [
    q("【10課 1 あります・います】あそこに 犬が（　）。", "Ở đằng kia có một con chó.", ["います", "あります", "します", "おきます"], 0, "Động vật và con người (sinh vật chuyển động) dùng います."),
    q("【10課 1 あります・います】机の 上に 本が（　）。", "Trên bàn có cuốn sách.", ["あります", "います", "おきます", "します"], 0, "Đồ vật vô tri vô giác, thực vật dùng あります."),
    q("【10課 2 位置詞】猫は 机の（　）に います。", "Con mèo ở dưới gầm bàn.", ["した", "うえ", "なか", "まえ"], 0, "した: phía dưới."),
    q("【10課 3 場所に〜が】部屋（　）だれが いますか。", "Trong phòng có ai thế?", ["に", "で", "を", "へ"], 0, "Vị trí tồn tại của sự vật/con người đi với trợ từ に."),
    q("【10課 4 〜や〜など】かばんの 中に 本（　）ペンなどが あります。", "Trong cặp có những thứ như sách và bút.", ["や", "と", "も", "に"], 0, "Trợ từ や dùng để liệt kê không đầy đủ (tiêu biểu) các đồ vật, thường đi kèm など.")
]

print("Lesson 2 to 10 worksheet templates loaded.")
