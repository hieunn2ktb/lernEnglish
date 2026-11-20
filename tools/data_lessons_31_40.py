# Worksheets for Lessons 31 to 40 based on Minna no Nihongo Kaite Oboeru

def q(question, meaning, options, correct_index, explanation):
    return {
        "question": question,
        "meaning": meaning,
        "options": options,
        "correctIndex": correct_index,
        "correctAnswer": options[correct_index],
        "explanation": explanation
    }

LESSONS_31_40 = {}

# ==================== LESSON 31 ====================
LESSONS_31_40[31] = [
    q("【31課 1 意向形】「行きます」の 意向形は 何ですか。", "Thể ý chí của いききます (đi) là gì?", ["行こう", "行け", "行かそう", "行きて"], 0, "Nhóm 1 đổi vần i sang vần o rồi thêm う: 行きます → 行こう."),
    q("【31課 1 意向形】「食べます」の 意向形は 何ですか。", "Thể ý chí của たべます (ăn) là gì?", ["食べよう", "食べろう", "食べろ", "食べた"], 0, "Nhóm 2 bỏ ます thêm よう: 食べます → 食べよう."),
    q("【31課 2 〜ようと思う】週末は 映画を（　）と 思っています。", "Cuối tuần tôi định đi xem phim.", ["見よう", "見る", "見たい", "見て"], 0, "Mẫu câu dự định có từ trước: [Động từ thể ý chí] + と思っています (見ようと思っています)."),
    q("【31課 3 〜つもりです】国へ 帰っても、日本語の 勉強を（　）つもりです。", "Dù về nước, tôi vẫn dự định tiếp tục học tiếng Nhật.", ["続ける", "続けよう", "続けて", "続けた"], 0, "Dự định chắc chắn: [Động từ thể từ điển / thể ない] + つもりです (続けるつもりです)."),
    q("【31課 4 〜予定です】来週 出張する（　）です。", "Tuần sau tôi có lịch đi công tác.", ["予定", "つもり", "こと", "もの"], 0, "Lịch trình, kế hoạch đã định sẵn: [Động từ thể từ điển / Danh từ の] + 予定です.")
]

# ==================== LESSON 32 ====================
LESSONS_31_40[32] = [
    q("【32課 1 〜たほうがいい】熱が あるから、病院へ（　）ほうが いいですよ。", "Vì bị sốt nên bạn nên đến bệnh viện thì hơn.", ["行った", "行く", "行かない", "行けば"], 0, "Khuyên nên làm việc gì có lợi: [Động từ thể た] + ほうがいいです (行ったほうがいい)."),
    q("【32課 1 〜ないほうがいい】風邪を 引いたから、お風呂に（　）ほうが いいです。", "Bị cảm cúm thì không nên tắm đâu.", ["入らない", "入った", "入る", "入らなくて"], 0, "Khuyên không nên làm gì: [Động từ thể ない] + ほうがいいです (入らないほうがいい)."),
    q("【32課 2 〜でしょう】明日は 天気が（　）でしょう。", "Ngày mai chắc là thời tiết sẽ đẹp.", ["いい", "よくて", "よかった", "いいだ"], 0, "Phỏng đoán có cơ sở thời tiết: [Thể thông thường] + でしょう (いいでしょう)."),
    q("【32課 3 〜かもしれません】午後は 雨が（　）かも しれません。", "Buổi chiều có thể trời sẽ mưa.", ["降る", "降ります", "降って", "降れば"], 0, "Phỏng đoán khả năng 50/50: [Thể thông thường] + かもしれません (降るかもしれません).")
]

# ==================== LESSON 33 ====================
LESSONS_31_40[33] = [
    q("【33課 1 命令形】「急ぎます」の 命令形は 何ですか。", "Thể mệnh lệnh của いそぎます (khẩn trương/nhanh lên) là gì?", ["急げ", "急ごう", "急ぎろ", "急ぐな"], 0, "Nhóm 1 đổi vần i sang vần e: 急ぎます → 急げ!"),
    q("【33課 2 禁止形】「入ります」の 禁止形は 何ですか。", "Thể cấm chỉ của はいります (vào) là gì?", ["入るな", "入るまい", "入るなよ", "入らない"], 0, "Thể cấm chỉ: [Động từ thể từ điển] + な (入るな: cấm vào!)."),
    q("【33課 3 〜という意味です】「立入禁止」は ここに（　）と いう 意味です。", "'立入禁止' có nghĩa là cấm vào đây.", ["入るな", "入れ", "入って", "入る"], 0, "Giải thích ý nghĩa biển báo: [Động từ thể cấm chỉ/mệnh lệnh] + という意味です."),
    q("【33課 4 〜と言っていました】田中さんは「明日 9時に（　）」と 言っていました。", "Anh Tanaka nhắn là: 'Ngày mai đến lúc 9 giờ nhé'.", ["来て", "来る", "来い", "来ます"], 0, "Truyền đạt lại lời nhắn: ～と言っていました.")
]

# ==================== LESSON 34 ====================
LESSONS_31_40[34] = [
    q("【34課 1 〜とおりに】わたしが（　）とおりに、書いて ください。", "Hãy viết theo đúng như tôi nói.", ["言う", "言った", "言って", "言います"], 0, "Làm theo như: [Động từ thể từ điển / thể た] + とおりに (言うとおりに)."),
    q("【34課 2 〜あとで】仕事が（　）あとで、飲みに行きませんか。", "Sau khi công việc kết thúc, cùng đi uống nước không?", ["終わった", "終わる", "終わって", "終わり"], 0, "Sau khi làm xong A: [Động từ thể た] + あとで (終わったあとで)."),
    q("【34課 3 〜ないで】砂糖を（　）ないで、コーヒーを 飲みます。", "Tôi uống cà phê mà không cho đường.", ["入れ", "入れる", "入れた", "入って"], 0, "Làm việc gì mà không làm việc kia: [Động từ thể ない bỏ い] + で (入れないで).")
]

# ==================== LESSON 35 ====================
LESSONS_31_40[35] = [
    q("【35課 1 条件形〜ば】「行きます」の 条件形は 何ですか。", "Thể điều kiện ば của いきます là gì?", ["行けば", "行かば", "行ったら", "行くと"], 0, "Nhóm 1 đổi vần i sang vần e rồi thêm ば: 行きます → 行けば."),
    q("【35課 1 条件形〜ば】「安いです」の 条件形は 何ですか。", "Thể điều kiện của やすい (rẻ) là gì?", ["安ければ", "安かったら", "安くて", "安いなら"], 0, "Tính từ đuôi い bỏ い thêm ければ: 安い → 安ければ."),
    q("【35課 2 疑問詞＋ばいいですか】どこで 切符を（　）いいですか。", "Tôi nên mua vé ở đâu thì tốt nhỉ?", ["買えば", "買ったら", "買うと", "買って"], 0, "Xin hướng dẫn, ý kiến: [Từ để hỏi] + [Động từ thể 条件形] + いいですか (買えばいいですか)."),
    q("【35課 3 〜なら】カメラを 買う（　）、秋葉原が いいですよ。", "Nếu mà mua máy ảnh thì Akihabara là tốt nhất đấy.", ["なら", "たら", "ば", "と"], 0, "Đưa ra lời khuyên dựa trên chủ đề đối phương vừa nêu: [Danh từ / Động từ từ điển] + なら.")
]

# ==================== LESSON 36 ====================
LESSONS_31_40[36] = [
    q("【36課 1 〜ように】忘れない（　）、メモを して おきます。", "Để không bị quên, tôi ghi chú lại trước.", ["ように", "ために", "ので", "から"], 0, "Chỉ mục đích với động từ không có ý chí hoặc thể phủ định: [V thể ない / V khả năng] + ように."),
    q("【36課 2 〜ようになる】毎日 練習して、漢字が（　）ように なりました。", "Luyện tập mỗi ngày nên tôi đã có thể viết được chữ Hán.", ["書ける", "書く", "書いて", "書いた"], 0, "Biến đổi năng lực từ chưa thể sang có thể: [Động từ khả năng] + ようになる (書けるようになる)."),
    q("【36課 3 〜ようにしている】体に いいですから、野菜を たくさん（　）ように して います。", "Vì tốt cho sức khỏe nên tôi luôn cố gắng ăn nhiều rau.", ["食べる", "食べて", "食べた", "食べよう"], 0, "Cố gắng duy trì một thói quen tốt: [Động từ thể từ điển / thể ない] + ようにしている.")
]

# ==================== LESSON 37 ====================
LESSONS_31_40[37] = [
    q("【37課 1 受身形の作り方】「ほめます」の 受身形は 何ですか。", "Thể bị động của ほめます (khen) là gì?", ["ほめられる", "ほめさせる", "ほまれる", "ほめた"], 0, "Nhóm 2 bỏ ます thêm られる: ほめます → ほめられる."),
    q("【37課 1 受身形の作り方】「叱ります（しかります）」の 受身形は 何ですか。", "Thể bị động của しかります (mắng) là gì?", ["叱られる", "叱させる", "叱れる", "叱られた"], 0, "Nhóm 1 đổi vần i sang vần a thêm れる: 叱ります → 叱られる."),
    q("【37課 2 直接受身】わたしは 先生（　）ほめられました。", "Tôi đã được thầy giáo khen.", ["に", "で", "を", "から"], 0, "Đối tượng thực hiện hành động tác động trong câu bị động đi với trợ từ に."),
    q("【37課 3 迷惑の受身】弟に パソコンを（　）しまいました。", "Máy tính của tôi đã bị em trai làm hỏng.", ["壊されて", "壊して", "壊れて", "壊させて"], 0, "Bị động gián tiếp / phiền toái: 弟に パソコンを 壊されました."),
    q("【37課 4 〜によって】電話は ベル（　）発明されました。", "Điện thoại được phát minh bởi Bell.", ["によって", "について", "にとって", "に対して"], 0, "Sáng chế, phát minh, công trình do ai tạo ra dùng: [Tên người] + によって [V bị động].")
]

# ==================== LESSON 38 ====================
LESSONS_31_40[38] = [
    q("【38課 1 〜のは〜です】サッカーを（　）のは おもしろいです。", "Việc chơi bóng đá rất thú vị.", ["する", "して", "した", "します"], 0, "Danh từ hóa động từ làm chủ ngữ: [Động từ thể từ điển] + のは [Tính từ] です."),
    q("【38課 2 〜のを忘れました】鍵を（　）のを 忘れました。", "Tôi đã quên khóa cửa.", ["かける", "かけた", "かけて", "かけます"], 0, "Quên làm một việc gì đó: [Động từ thể từ điển] + のを 忘れました (鍵をかけるのを忘れました)."),
    q("【38課 3 〜のを知っていますか】木村さんが 結婚した（　）を 知って いますか。", "Bạn có biết việc chị Kimura đã kết hôn chưa?", ["の", "こと", "もの", "ところ"], 0, "Biết một sự thật, tin tức: [Thể thông thường] + のを知っていますか.")
]

# ==================== LESSON 39 ====================
LESSONS_31_40[39] = [
    q("【39課 1 て形で理由】ニュースを（　）、びっくりしました。", "Nghe tin tức xong tôi đã rất giật mình.", ["聞いて", "聞く", "聞いたら", "聞いた"], 0, "Vế trước thể て biểu thị nguyên nhân tự nhiên dẫn đến cảm xúc, tâm trạng ở vế sau."),
    q("【39課 2 Danh từ で理由】地震（　）ビルが 倒れました。", "Do động đất nên tòa nhà bị sập.", ["で", "に", "を", "から"], 0, "Nguyên nhân do thiên tai, sự cố bất khả kháng đi với trợ từ で: 地震で."),
    q("【39課 3 〜ので】用事が（　）ので、お先に 失礼します。", "Vì có việc bận nên tôi xin phép về trước.", ["ある", "あります", "あって", "あった"], 0, "Chỉ lý do một cách khách quan, lịch sự: [Thể thông thường] + ので (あるので).")
]

# ==================== LESSON 40 ====================
LESSONS_31_40[40] = [
    q("【40課 1 疑問詞＋か】会議は 何時に（　）か、教えて ください。", "Hãy cho tôi biết cuộc họp bắt đầu lúc mấy giờ.", ["始まる", "始まります", "始まって", "始まった"], 0, "Câu hỏi lồng có từ để hỏi: [Thể thông thường] + か (何時に始まるか)."),
    q("【40課 2 〜かどうか】間違いが（　）かどうか、調べて ください。", "Hãy kiểm tra xem có sai sót hay không.", ["ない", "ありません", "なくて", "なかった"], 0, "Câu hỏi lồng không có từ để hỏi: [Thể thông thường] + かどうか (ないかどうか)."),
    q("【40課 3 〜てみる】この ズボンを（　）みても いいですか。", "Tôi mặc thử chiếc quần này có được không?", ["はいて", "はく", "はいた", "はき"], 0, "Thử làm việc gì xem thế nào: [Động từ thể て] + みます (はいてみます).")
]

print("Lesson 31 to 40 worksheet templates loaded.")
