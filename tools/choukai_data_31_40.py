# -*- coding: utf-8 -*-
from choukai_helper import make_task

LESSONS_31_40 = []

# ==================== LESSON 31 ====================
l31_tasks = [
    make_task(
        "II_CD1", 24, 1, "1. 〜(よ)う[意向形]", "1. Thể ý chí (Cùng làm / Sẽ làm)",
        "1. 〜(よ)う[意向形]\n1) A: ちょっと休もう。\nB: うん、休もう。\n2) A: 明日映画を見に行こう。\nB: いいね、行こう。\n3) A: そろそろ帰ろうか。\nB: うん、帰ろう。",
        "1. Thể ý chí (Cùng làm / Sẽ làm)\n1) A: Nghỉ một lát đi bạn.\nB: Ừ, nghỉ giải lao tí nào.\n2) A: Ngày mai đi xem phim đi.\nB: Hay đấy, đi thôi.\n3) A: Chuẩn bị về thôi nhỉ.\nB: Ừ, về thôi nào.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nghỉ ngơi", "options": ["a b (Cùng nghỉ ngơi)", "c"], "correctAnswer": "a b (Cùng nghỉ ngơi)", "explanation": "Cùng nghỉ ngơi (a b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đi xem phim", "options": ["b a (Cùng đi xem phim)", "c"], "correctAnswer": "b a (Cùng đi xem phim)", "explanation": "Cùng đi xem phim (b a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Về nhà", "options": ["a (Cùng về)", "b"], "correctAnswer": "a (Cùng về)", "explanation": "Cùng về (a)."}
        ]
    ),
    make_task(
        "II_CD1", 25, 2, "2. 〜(よ)うと思っています", "2. Đang có dự định, ấp ủ...",
        "2. 〜(よ)うと思っています\n1) A: 将来自分の店を持とうと思っています。\n2) A: 夏休みに国へ帰ろうと思っています。\n3) A: 日本語の試験を受けようと思っています。",
        "2. Đang có dự định, ấp ủ...\n1) A: Tương lai tôi dự định sẽ mở một cửa hàng của riêng mình.\n2) A: Kì nghỉ hè này tôi tính sẽ về thăm quê hương.\n3) A: Tôi đang dự định sẽ thi lấy chứng chỉ tiếng Nhật JLPT.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Tương lai", "options": ["b (Mở cửa hàng riêng)", "a"], "correctAnswer": "b (Mở cửa hàng riêng)", "explanation": "Mở cửa hàng (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Kì nghỉ hè", "options": ["a (Về nước)", "b"], "correctAnswer": "a (Về nước)", "explanation": "Về nước (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Kế hoạch học tập", "options": ["b (Thi chứng chỉ JLPT)", "a"], "correctAnswer": "b (Thi chứng chỉ JLPT)", "explanation": "Dự định đi thi (b)."}
        ]
    ),
    make_task(
        "II_CD1", 26, 3, "3. 〜つもりです", "3. Dự định chắc chắn sẽ làm...",
        "3. 〜つもりです\n1) A: 料理の勉強をして、レストランを開くつもりです。自分の店です。\n2) A: 動物の病院で働くつもりです。言葉を覚えます。",
        "3. Dự định chắc chắn sẽ làm...\n1) A: Tôi học nghề nấu ăn rồi sẽ mở một nhà hàng ăn uống của riêng mình.\n2) A: Tôi dự định sẽ vào làm việc tại một bệnh viện thú y.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Kế hoạch mở quán", "options": ["①料理 ②レストラン ③(自分の)店 ④(おいしい)料理", "Không mở quán"], "correctAnswer": "①料理 ②レストラン ③(自分の)店 ④(おいしい)料理", "explanation": "Học nấu ăn, mở nhà hàng của mình."},
            {"itemIndex": "2", "prompt": "Câu 2: Kế hoạch nghề nghiệp", "options": ["①はい ②動物 ③(動物の)病院 ④ことば", "Làm công ty"], "correctAnswer": "①はい ②動物 ③(動物の)病院 ④ことば", "explanation": "Làm ở bệnh viện thú y."}
        ]
    ),
    make_task(
        "II_CD1", 27, 4, "4. 〜予定です", "4. Lịch trình, kế hoạch đã lên sẵn",
        "4. 〜予定です\n1) A: 出張はいつですか。\nB: 19日の土曜日の予定です。",
        "4. Lịch trình, kế hoạch đã lên sẵn\n1) A: Lịch chuyến đi công tác là khi nào thế?\nB: Dự kiến vào ngày thứ Bảy, ngày 19.",
        [
            {"itemIndex": "1", "prompt": "Ngày đi công tác", "options": ["19 土 (Thứ Bảy ngày 19)", "20 Nhật"], "correctAnswer": "19 土 (Thứ Bảy ngày 19)", "explanation": "Thứ Bảy ngày 19 (19 土)."}
        ]
    )
]
LESSONS_31_40.append({
    "lessonNumber": 31, "level": "N4",
    "title": "Bài 31: Thể ý chí & Kế hoạch, Dự định",
    "subtitle": "第31課: 意向形・〜と思っています・つもり・予定",
    "tasks": l31_tasks
})

# ==================== LESSON 32 ====================
l32_tasks = [
    make_task(
        "II_CD1", 28, 1, "1. 〜た/〜ないほうがいいです", "1. Nên / Không nên làm gì (Lời khuyên)",
        "1. 〜た/〜ないほうがいいです\n1) A: 地震の準備をしておいたほうがいいですよ。\nB: ええ、水や食料を用意します。\n2) A: もし地震があったら、慌てて外へ出ないほうがいいです。",
        "1. Nên / Không nên làm gì (Lời khuyên)\n1) A: Bạn nên chuẩn bị sẵn sàng đối phó với động đất trước nhé.\nB: Vâng, tôi sẽ chuẩn bị nước uống và lương thực dự trữ.\n2) A: Giả sử nếu có động đất, đừng hoảng loạn chạy tháo ra ngoài.",
        [
            {"itemIndex": "1", "prompt": "Lời khuyên chuẩn bị & ứng phó động đất", "options": ["準備: a b / もし地震があったら: a c", "Không cần chuẩn bị"], "correctAnswer": "準備: a b / もし地震があったら: a c", "explanation": "Chuẩn bị lương thực và bình tĩnh xử lý (a b / a c)."}
        ]
    ),
    make_task(
        "II_CD1", 29, 2, "2. 〜た/〜ないほうがいいです (Sức khỏe)", "2. Lời khuyên chăm sóc sức khỏe",
        "2. 〜た/〜ないほうがいいです\n1) A: 風邪を引いたときは、温かいものを飲んで早く寝たほうがいいですよ。\n2) A: お風呂には入らないほうがいいです。\n3) A: 辛いものは食べないほうがいいですよ。",
        "2. Lời khuyên chăm sóc sức khỏe\n1) A: Khi bị cảm lạnh bạn nên uống đồ ấm và đi ngủ sớm nhé.\n2) A: Đang sốt thì không nên tắm đâu.\n3) A: Không nên ăn đồ cay nóng trong lúc ốm nhé.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Bị cảm lạnh", "options": ["a (Nên ngủ sớm)", "b"], "correctAnswer": "a (Nên ngủ sớm)", "explanation": "Nên ngủ sớm (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Tắm rửa lúc sốt", "options": ["a (Không nên tắm)", "b"], "correctAnswer": "a (Không nên tắm)", "explanation": "Không nên tắm (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đồ ăn cay", "options": ["b (Không nên ăn cay)", "a"], "correctAnswer": "b (Không nên ăn cay)", "explanation": "Không nên ăn đồ cay (b)."}
        ]
    ),
    make_task(
        "II_CD1", 30, 3, "3. 〜でしょう", "3. Phỏng đoán chắc là... (Dự báo)",
        "3. 〜でしょう\n1) A: 明日は雨が降るでしょう。\n2) A: 午後は風が強くなるでしょう。",
        "3. Phỏng đoán chắc là... (Dự báo)\n1) A: Ngày mai trời chắc là sẽ có mưa đấy.\n2) A: Vào buổi chiều gió chắc sẽ mạnh dần lên.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Dự báo mưa", "options": ["a ② (Trời mưa)", "b"], "correctAnswer": "a ② (Trời mưa)", "explanation": "Dự báo có mưa (a ②)."},
            {"itemIndex": "2", "prompt": "Câu 2: Dự báo gió", "options": ["b ② (Gió mạnh)", "a"], "correctAnswer": "b ② (Gió mạnh)", "explanation": "Gió mạnh dần (b ②)."}
        ]
    ),
    make_task(
        "II_CD1", 31, 4, "4. 〜かもしれません", "4. Có thể / Có lẽ... (Xác suất xảy ra)",
        "4. 〜かもしれません\n1) A: 午後から雨が降るかもしれませんから、傘を持っていったほうがいいですよ。\n2) A: 何か悪いことがあるかもしれません。",
        "4. Có thể / Có lẽ... (Xác suất xảy ra)\n1) A: Từ chiều có thể trời sẽ mưa đấy, bạn nên mang theo ô đi.\n2) A: Có thể sắp có chuyện gì đó chẳng lành xảy ra.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Trời có thể mưa", "options": ["雨が降る b (Có thể mưa)", "Trời nắng"], "correctAnswer": "雨が降る b (Có thể mưa)", "explanation": "Có thể đổ mưa (雨が降る b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Chuyện xấu có thể đến", "options": ["(何か)悪いことがある c", "Tốt lành"], "correctAnswer": "(何か)悪いことがある c", "explanation": "Có thể có chuyện chẳng lành."}
        ]
    )
]
LESSONS_31_40.append({
    "lessonNumber": 32, "level": "N4",
    "title": "Bài 32: Lời khuyên (ほうがいい), Phỏng đoán & Dự đoán",
    "subtitle": "第32課: 〜たほうがいい・〜でしょう・〜かもしれません",
    "tasks": l32_tasks
})

# ==================== LESSON 33 ====================
l33_tasks = [
    make_task(
        "II_CD1", 32, 1, "1. 命令形", "1. Thể mệnh lệnh (Cổ vũ & Chỉ thị)",
        "1. 命令形\n1) A: 頑張れ！走れ！\n2) A: 止まれ！危ないぞ！\n3) A: 急げ！遅れるぞ！",
        "1. Thể mệnh lệnh (Cổ vũ & Chỉ thị)\n1) A: Cố lên! Chạy nhanh lên nào!\n2) A: Dừng lại mau! Nguy hiểm đấy!\n3) A: Nhanh chân lên! Muộn giờ mất rồi!",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Cổ vũ", "options": ["a (Cố lên, chạy lên)", "b"], "correctAnswer": "a (Cố lên, chạy lên)", "explanation": "Chạy nhanh lên (a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Dừng lại", "options": ["b (Dừng lại mau)", "a"], "correctAnswer": "b (Dừng lại mau)", "explanation": "Dừng lại nguy hiểm (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Nhanh lên", "options": ["a (Nhanh lên kẻo muộn)", "b"], "correctAnswer": "a (Nhanh lên kẻo muộn)", "explanation": "Nhanh lên (a)."}
        ]
    ),
    make_task(
        "II_CD1", 33, 2, "2. 〜と読みます/〜と書いてあります/〜という意味です", "2. Đọc là / Viết là / Có nghĩa là...",
        "2. 〜と読みます/〜と書いてあります/〜という意味です\n1) A: あの漢字は何と読みますか。\nB: 「きんえん」と読みます。タバコを吸ってはいけないという意味です。\n2) A: あそこに何と書いてありますか。\nB: 「立入禁止」と書いてあります。入ってはいけないという意味です。\n3) A: 「使用中」はどういう意味ですか。\nB: 今使っているという意味です。",
        "2. Đọc là / Viết là / Có nghĩa là...\n1) A: Chữ Hán kia đọc là gì thế?\nB: Đọc là \"Kinen\", có nghĩa là cấm hút thuốc lá.\n2) A: Ở đằng kia viết chữ gì vậy?\nB: Viết là \"Tachiiri Kinshi\", có nghĩa là cấm vào trong.\n3) A: Chữ \"Shiyouchuu\" nghĩa là gì thế?\nB: Có nghĩa là hiện tại đang có người sử dụng.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Biển cấm hút thuốc", "options": ["⑤ a (Kinen - Cấm hút thuốc)", "① b"], "correctAnswer": "⑤ a (Kinen - Cấm hút thuốc)", "explanation": "Kinen: Cấm hút thuốc (⑤ a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Biển cấm vào", "options": ["④ b (Tachiiri Kinshi - Cấm vào)", "② a"], "correctAnswer": "④ b (Tachiiri Kinshi - Cấm vào)", "explanation": "Cấm đi vào (④ b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đang sử dụng", "options": ["② b (Đang sử dụng)", "① a"], "correctAnswer": "② b (Đang sử dụng)", "explanation": "Đang dùng (② b)."}
        ]
    ),
    make_task(
        "II_CD1", 34, 3, "3. 〜という意味です", "3. Giải thích ký hiệu & Ý nghĩa",
        "3. 〜という意味です\n1) A: このマークはどういう意味ですか。\nB: 洗濯機で洗えるという意味です。\n2) A: このマークはどういう意味ですか。\nB: アイロンをかけてはいけないという意味です。\n3) A: この記号はどういう意味ですか。\nB: 非常口という意味です。",
        "3. Giải thích ký hiệu & Ý nghĩa\n1) A: Biểu tượng này có nghĩa là gì vậy?\nB: Có nghĩa là giặt được bằng máy giặt.\n2) A: Biểu tượng này nghĩa là gì?\nB: Có nghĩa là không được dùng bàn ủi.\n3) A: Ký hiệu này có ý nghĩa gì?\nB: Có nghĩa là lối thoát hiểm khẩn cấp.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ký hiệu giặt máy", "options": ["e (Giặt máy được)", "a"], "correctAnswer": "e (Giặt máy được)", "explanation": "Giặt được bằng máy (e)."},
            {"itemIndex": "2", "prompt": "Câu 2: Ký hiệu bàn ủi", "options": ["a (Không ủi nhiệt)", "b"], "correctAnswer": "a (Không ủi nhiệt)", "explanation": "Không ủi bàn là (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Biển thoát hiểm", "options": ["c (Lối thoát hiểm)", "d"], "correctAnswer": "c (Lối thoát hiểm)", "explanation": "Cửa thoát hiểm (c)."}
        ]
    ),
    make_task(
        "II_CD1", 35, 4, "4. 〜と伝えていただけませんか", "4. Nhắn lại giúp tôi rằng...",
        "4. 〜と伝えていただけませんか\n1) A: 田中さんに「明日の会議は10時からだ」と伝えていただけませんか。\nB: はい、わかりました。\n2) A: 佐藤さんに「後で電話をください」と伝えていただけませんか。\nB: はい、伝えます。\n3) A: 部長に「少し遅れる」と伝えていただけませんか。\nB: ええ、わかりました。",
        "4. Nhắn lại giúp tôi rằng...\n1) A: Nhờ anh nhắn với anh Tanaka là cuộc họp mai bắt đầu lúc 10h giúp tôi được không?\nB: Vâng, tôi sẽ chuyển lời.\n2) A: Nhờ bạn nhắn với chị Satou là tí nữa gọi lại cho tôi nhé?\nB: Vâng, tôi sẽ nhắn.\n3) A: Nhờ anh báo với trưởng phòng là em đến muộn một chút với ạ?\nB: Ừ, tôi biết rồi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nhắn anh Tanaka", "options": ["b (Họp lúc 10h)", "a"], "correctAnswer": "b (Họp lúc 10h)", "explanation": "Họp lúc 10h (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nhắn chị Satou", "options": ["c (Gọi lại sau)", "a"], "correctAnswer": "c (Gọi lại sau)", "explanation": "Gọi lại sau (c)."},
            {"itemIndex": "3", "prompt": "Câu 3: Nhắn trưởng phòng", "options": ["a (Đến muộn chút)", "b"], "correctAnswer": "a (Đến muộn chút)", "explanation": "Đến muộn một chút (a)."}
        ]
    )
]
LESSONS_31_40.append({
    "lessonNumber": 33, "level": "N4",
    "title": "Bài 33: Thể mệnh lệnh, Cấm chỉ & Ý nghĩa ký hiệu biển báo",
    "subtitle": "第33課: 命令・禁止・〜という意味です・伝言",
    "tasks": l33_tasks
})

# ==================== LESSON 34 ====================
l34_tasks = [
    make_task(
        "II_CD1", 36, 1, "1. 〜とおりに", "1. Làm theo đúng như hướng dẫn",
        "1. 〜とおりに\n1) A: わたしが言うとおりに書いてください。\n2) A: 説明書のとおりに組み立ててください。\n3) A: 母が教えてくれたとおりに作りました。",
        "1. Làm theo đúng như hướng dẫn\n1) A: Hãy viết theo đúng như những gì tôi đọc nhé.\n2) A: Hãy lắp ráp theo đúng như sách hướng dẫn.\n3) A: Tôi đã nấu theo đúng công thức mẹ đã dạy.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Viết theo lời đọc", "options": ["b (Viết theo lời)", "a"], "correctAnswer": "b (Viết theo lời)", "explanation": "Viết theo lời đọc (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Lắp ráp", "options": ["a (Lắp theo sách hướng dẫn)", "b"], "correctAnswer": "a (Lắp theo sách hướng dẫn)", "explanation": "Theo sách hướng dẫn (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Nấu ăn", "options": ["c (Nấu theo mẹ dạy)", "a"], "correctAnswer": "c (Nấu theo mẹ dạy)", "explanation": "Theo mẹ dạy (c)."}
        ]
    ),
    make_task(
        "II_CD1", 37, 2, "2. 〜あとで、〜", "2. Sau khi...",
        "2. 〜あとで、〜\n1) A: 仕事が終わったあとで、飲みに行きませんか。\n2) A: ご飯を食べたあとで、薬を飲みます。\n3) A: 説明を聞いたあとで、質問してください。\n4) A: 映画を見たあとで、喫茶店でお茶をしました。",
        "2. Sau khi...\n1) A: Sau khi xong việc cùng đi uống một ly nhé?\n2) A: Sau khi ăn cơm xong thì uống thuốc.\n3) A: Sau khi nghe giải thích xong thì hãy đặt câu hỏi.\n4) A: Sau khi xem phim xong thì vào quán cà phê ngồi nói chuyện.",
        [
            {"itemIndex": "1", "prompt": "Câu 1", "options": ["3. c", "1. a"], "correctAnswer": "3. c", "explanation": "Sau khi xong việc (3. c)."},
            {"itemIndex": "2", "prompt": "Câu 2", "options": ["4. f", "2. b"], "correctAnswer": "4. f", "explanation": "Uống thuốc sau ăn (4. f)."},
            {"itemIndex": "3", "prompt": "Câu 3", "options": ["5. e", "3. a"], "correctAnswer": "5. e", "explanation": "Đặt câu hỏi sau nghe (5. e)."},
            {"itemIndex": "4", "prompt": "Câu 4", "options": ["6. g", "4. b"], "correctAnswer": "6. g", "explanation": "Vào quán cà phê (6. g)."}
        ]
    ),
    make_task(
        "II_CD1", 38, 3, "3. 〜て/〜ないで〜", "3. Có làm hay không làm gì kèm theo",
        "3. 〜て/〜ないで〜\n1) A: 砂糖を入れて飲みますか。\nB: いいえ、砂糖を入れないでブラックで飲みます。\n2) A: 傘を持って出かけましたか。\nB: はい、傘を持って出かけました。",
        "3. Có làm hay không làm gì kèm theo\n1) A: Bạn uống cà phê có cho đường không?\nB: Không, tôi không cho đường, uống đen nguyên chất.\n2) A: Bạn có mang ô theo khi ra ngoài không?\nB: Vâng, tôi mang theo ô.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Cà phê", "options": ["d (Uống đen không đường)", "a"], "correctAnswer": "d (Uống đen không đường)", "explanation": "Uống không đường (d)."},
            {"itemIndex": "2", "prompt": "Câu 2: Mang ô", "options": ["h (Có mang ô theo)", "b"], "correctAnswer": "h (Có mang ô theo)", "explanation": "Mang ô theo (h)."}
        ]
    ),
    make_task(
        "II_CD1", 39, 4, "4. 〜ないで、〜", "4. Không làm việc A mà làm việc B",
        "4. 〜ないで、〜\n1) A: 昨日はどこも出かけないで、一日中うちで寝ていました。\n2) A: エレベーターに乗らないで、階段を歩きました。",
        "4. Không làm việc A mà làm việc B\n1) A: Hôm qua tôi không đi ra ngoài đâu cả mà ở nhà ngủ cả ngày.\n2) A: Không đi thang máy mà leo cầu thang bộ cho khỏe người.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ở nhà ngủ", "options": ["(1) × (2) ○", "(1) ○ (2) ×"], "correctAnswer": "(1) × (2) ○", "explanation": "Ở nhà ngủ cả ngày ((1) × (2) ○)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đi thang bộ", "options": ["(3) × (4) ×", "(3) ○ (4) ○"], "correctAnswer": "(3) × (4) ×", "explanation": "Đi thang bộ ((3) × (4) ×)."}
        ]
    )
]
LESSONS_31_40.append({
    "lessonNumber": 34, "level": "N4",
    "title": "Bài 34: Theo đúng như (とおりに) & Sau khi (あとで)",
    "subtitle": "第34課: とおりに・あとで・てないで",
    "tasks": l34_tasks
})

# ==================== LESSON 35 ====================
l35_tasks = [
    make_task(
        "II_CD2", 1, 1, "1. 〜ば、〜", "1. Thể điều kiện (ば)",
        "1. 〜ば、〜\n1) A: ボタンを押せば、窓が開きます。\n2) A: 安ければ、買います。\n3) A: 暇なら、遊びに行きます。",
        "1. Thể điều kiện (ば)\n1) A: Bấm chiếc nút này thì cửa sổ sẽ tự động mở.\n2) A: Nếu giá cả phải chăng hợp lý thì tôi sẽ mua.\n3) A: Nếu rảnh rỗi thì cùng đi chơi nhé.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Bấm nút", "options": ["c (Cửa mở)", "a"], "correctAnswer": "c (Cửa mở)", "explanation": "Cửa mở (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nếu rẻ", "options": ["a (Sẽ mua)", "b"], "correctAnswer": "a (Sẽ mua)", "explanation": "Sẽ mua (a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Nếu rảnh", "options": ["e (Đi chơi)", "d"], "correctAnswer": "e (Đi chơi)", "explanation": "Đi chơi (e)."}
        ]
    ),
    make_task(
        "II_CD2", 2, 2, "2. 〜ければ/〜なら、〜", "2. Điều kiện với tính từ và danh từ",
        "2. 〜ければ/〜なら、〜\n1) A: 10万円あれば、新しいパソコンが買えますよ。\nB: そうですね、買いたいですね。",
        "2. Điều kiện với tính từ và danh từ\n1) A: Nếu có 10 vạn yên (100.000 yên) là có thể mua được chiếc máy tính mới cứng rồi.\nB: Đúng rồi, muốn mua ghê.",
        [
            {"itemIndex": "1", "prompt": "Số tiền cần thiết", "options": ["b 10万 (10 vạn yên)", "5万"], "correctAnswer": "b 10万 (10 vạn yên)", "explanation": "10 vạn yên (b 10万)."}
        ]
    ),
    make_task(
        "II_CD2", 3, 3, "3. 〜なければ、〜", "3. Nếu không... thì...",
        "3. 〜なければ、〜\n1) A: 薬を飲まなければ、治りませんよ。\n2) A: パスポートがなければ、飛行機に乗れません。\n3) A: 急がなければ、間に合いませんよ。",
        "3. Nếu không... thì...\n1) A: Nếu không chịu uống thuốc thì không khỏi được đâu.\n2) A: Nếu không có hộ chiếu thì không thể lên máy bay được.\n3) A: Nếu không khẩn trương lên thì không kịp giờ đâu.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Uống thuốc", "options": ["b a", "c d"], "correctAnswer": "b a", "explanation": "Phải uống thuốc (b a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Hộ chiếu", "options": ["a b", "c d"], "correctAnswer": "a b", "explanation": "Phải có hộ chiếu (a b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Khẩn trương", "options": ["a a", "b b"], "correctAnswer": "a a", "explanation": "Nhanh lên kẻo muộn (a a)."}
        ]
    ),
    make_task(
        "II_CD2", 4, 4, "4. 〜なら、〜(話題)", "4. Nếu là chủ đề đó thì...",
        "4. 〜なら、〜(話題)\n1) A: 日本の町なら、東京の町がにぎやかでいいですよ。\n2) A: 秋の旅行なら、紅葉が綺麗ですから京都がいいですね。\n3) A: 春なら、桜が綺麗な上野公園が一番です。",
        "4. Nếu là chủ đề đó thì...\n1) A: Nếu là thành phố ở Nhật Bản thì Tokyo rất náo nhiệt và sầm uất.\n2) A: Nếu là du lịch mùa thu thì Kyoto ngắm lá đỏ tuyệt đẹp.\n3) A: Nếu là mùa xuân thì công viên Ueno ngắm hoa anh đào nở là số một.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Du lịch thành phố", "options": ["東京の町 ②", "Osaka"], "correctAnswer": "東京の町 ②", "explanation": "Thành phố Tokyo."},
            {"itemIndex": "2", "prompt": "Câu 2: Mùa thu", "options": ["紅葉 ⑤ (Ngắm lá đỏ)", "Tuyết"], "correctAnswer": "紅葉 ⑤ (Ngắm lá đỏ)", "explanation": "Ngắm lá đỏ mùa thu."},
            {"itemIndex": "3", "prompt": "Câu 3: Mùa xuân", "options": ["桜 ① (Ngắm hoa anh đào)", "Lá vàng"], "correctAnswer": "桜 ① (Ngắm hoa anh đào)", "explanation": "Hoa anh đào."}
        ]
    ),
    make_task(
        "II_CD2", 5, 5, "5. 〜ば〜ほど", "5. Càng... thì càng...",
        "5. 〜ば〜ほど\n1) A: 日本語は勉強すればするほど、おもしろくなります。\n2) A: パソコンは新しければ新しいほど、性能がいいです。\n3) A: 会場は広ければ広いほど、人がたくさん入れます。",
        "5. Càng... thì càng...\n1) A: Tiếng Nhật càng học sâu thì càng thấy thú vị.\n2) A: Máy tính càng đời mới thì hiệu năng càng mạnh mẽ.\n3) A: Hội trường càng rộng rãi thì càng chứa được nhiều người.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Càng học càng thích", "options": ["b", "a"], "correctAnswer": "b", "explanation": "Càng học càng thú vị (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Càng mới càng tốt", "options": ["c", "a"], "correctAnswer": "c", "explanation": "Càng mới càng tốt (c)."},
            {"itemIndex": "3", "prompt": "Câu 3: Càng rộng càng chứa nhiều", "options": ["a", "b"], "correctAnswer": "a", "explanation": "Càng rộng càng tốt (a)."}
        ]
    )
]
LESSONS_31_40.append({
    "lessonNumber": 35, "level": "N4",
    "title": "Bài 35: Thể điều kiện (ば/なら) & Càng... càng...",
    "subtitle": "第35課: 〜ば・〜なら・ば〜ほど",
    "tasks": l35_tasks
})

# ==================== LESSON 36 ====================
l36_tasks = [
    make_task(
        "II_CD2", 6, 1, "1. 〜ように、〜", "1. Để mà... (Mục đích)",
        "1. 〜ように、〜\n1) A: 忘れないように、メモしておきます。\n2) A: よく聞こえるように、前の方に座ります。\n3) A: 風邪を引かないように、温かい服を着ます。",
        "1. Để mà... (Mục đích)\n1) A: Để khỏi quên, tôi ghi chú lại vào sổ tay.\n2) A: Để nghe được rõ ràng, tôi tiến lên ngồi hàng ghế đầu.\n3) A: Để không bị cảm lạnh, tôi mặc thêm áo ấm.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Ghi chú", "options": ["b ③ (Ghi chú để khỏi quên)", "a"], "correctAnswer": "b ③ (Ghi chú để khỏi quên)", "explanation": "Ghi chú để khỏi quên (b ③)."},
            {"itemIndex": "2", "prompt": "Câu 2: Ngồi phía trước", "options": ["d ② (Ngồi trước để nghe rõ)", "c"], "correctAnswer": "d ② (Ngồi trước để nghe rõ)", "explanation": "Ngồi trước để nghe rõ (d ②)."},
            {"itemIndex": "3", "prompt": "Câu 3: Mặc áo ấm", "options": ["e ① (Mặc ấm để không ốm)", "b"], "correctAnswer": "e ① (Mặc ấm để không ốm)", "explanation": "Mặc ấm để tránh cảm (e ①)."}
        ]
    ),
    make_task(
        "II_CD2", 7, 2, "2. 〜ようになりました", "2. Đã trở nên biết làm / có thể làm được",
        "2. 〜ようになりました\n1) A: 日本語のニュースがわかるようになりました。\n2) A: 刺身が食べられるようになりました。\n3) A: 一人で新幹線に乗れるようになりました。",
        "2. Đã trở nên biết làm / có thể làm được\n1) A: Tôi đã bắt đầu hiểu được các bản tin thời sự tiếng Nhật.\n2) A: Tôi đã biết ăn món gỏi cá sống Sashimi rồi.\n3) A: Tôi đã có thể tự một mình đi tàu siêu tốc Shinkansen.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Hiểu tin tức", "options": ["b (Đã hiểu được thời sự)", "a"], "correctAnswer": "b (Đã hiểu được thời sự)", "explanation": "Đã hiểu được thời sự (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Ăn Sashimi", "options": ["b (Biết ăn gỏi cá)", "a"], "correctAnswer": "b (Biết ăn gỏi cá)", "explanation": "Biết ăn gỏi cá (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đi Shinkansen", "options": ["a (Tự đi Shinkansen)", "b"], "correctAnswer": "a (Tự đi Shinkansen)", "explanation": "Tự đi được tàu (a)."}
        ]
    ),
    make_task(
        "II_CD2", 8, 3, "3. 〜なくなりました", "3. Đã không còn làm / không thể làm được nữa",
        "3. 〜なくなりました\n1) A: 忙しくて、全然眠られなくなりました。\n2) A: 最近全然遊べなくなりました。",
        "3. Đã không còn làm / không thể làm được nữa\n1) A: Dạo này bận quá, chẳng còn được chợp mắt ngủ ngon nữa.\n2) A: Gần đây công việc ngập đầu, chẳng còn thời gian rảnh đi chơi đâu cả.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Thiếu ngủ", "options": ["寝られません (Không ngủ được)", "Ngủ nhiều"], "correctAnswer": "寝られません (Không ngủ được)", "explanation": "Không ngủ được vì bận."},
            {"itemIndex": "2", "prompt": "Câu 2: Không được đi chơi", "options": ["遊べません (Không được đi chơi)", "Chơi nhiều"], "correctAnswer": "遊べません (Không được đi chơi)", "explanation": "Không còn được đi chơi."}
        ]
    ),
    make_task(
        "II_CD2", 9, 4, "4. 〜ようにしています", "4. Luôn cố gắng tạo thói quen tốt",
        "4. 〜ようにしています\n1) A: 目を大切にするようにしています。\n2) A: 手を清潔にするようにしています。\n3) A: 大きな声で挨拶するようにしています。",
        "4. Luôn cố gắng tạo thói quen tốt\n1) A: Tôi luôn cố gắng giữ gìn chăm sóc đôi mắt của mình.\n2) A: Tôi luôn chú ý rửa tay thật sạch sẽ.\n3) A: Tôi luôn cố gắng chào hỏi mọi người với giọng nói to rõ ràng.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Chăm sóc mắt", "options": ["目 d h", "Tay"], "correctAnswer": "目 d h", "explanation": "Giữ gìn mắt (目 d h)."},
            {"itemIndex": "2", "prompt": "Câu 2: Vệ sinh tay", "options": ["手 b f", "Chân"], "correctAnswer": "手 b f", "explanation": "Rửa tay sạch sẽ (手 b f)."},
            {"itemIndex": "3", "prompt": "Câu 3: Giọng nói chào hỏi", "options": ["声 e c", "Nụ cười"], "correctAnswer": "声 e c", "explanation": "Chào to rõ ràng (声 e c)."}
        ]
    ),
    make_task(
        "II_CD2", 10, 5, "5. 〜ようにしてください", "5. Xin hãy chú ý luôn luôn làm việc này",
        "5. 〜ようにしてください\n1) A: 毎日運動するようにしてください。\n2) A: 甘いものを食べすぎないようにしてください。\n3) A: 約束の時間を守るようにしてください。",
        "5. Xin hãy chú ý luôn luôn làm việc này\n1) A: Bạn hãy cố gắng tập thể dục đều đặn mỗi ngày nhé.\n2) A: Hãy chú ý đừng ăn quá nhiều đồ ngọt nhé.\n3) A: Bạn hãy nhớ luôn luôn giữ đúng giờ hẹn nhé.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Tập thể dục", "options": ["b (Tập đều mỗi ngày)", "a"], "correctAnswer": "b (Tập đều mỗi ngày)", "explanation": "Tập thể dục đều (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đồ ngọt", "options": ["b (Hạn chế đồ ngọt)", "a"], "correctAnswer": "b (Hạn chế đồ ngọt)", "explanation": "Đừng ăn quá ngọt (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Giữ đúng giờ", "options": ["a (Đúng giờ hẹn)", "b"], "correctAnswer": "a (Đúng giờ hẹn)", "explanation": "Đúng hẹn (a)."}
        ]
    )
]
LESSONS_31_40.append({
    "lessonNumber": 36, "level": "N4",
    "title": "Bài 36: Mục đích (ように) & Biến đổi thói quen (ようにしています)",
    "subtitle": "第36課: 〜ように・〜ようになりました・ようにしている",
    "tasks": l36_tasks
})

# ==================== LESSON 37 ====================
l37_tasks = [
    make_task(
        "II_CD2", 11, 1, "1. (人)に〜(ら)れます[受身動詞]", "1. Thể bị động (Bị ai đó tác động)",
        "1. (人)に〜(ら)れます[受身動詞]\n1) A: 弟にパソコンを壊されました。\n2) A: 電車の中で足を踏まれました。\n3) A: 雨に降られて服が濡れました。\n4) A: 先生に褒められました。",
        "1. Thể bị động (Bị ai đó tác động)\n1) A: Tôi bị thằng em trai làm vỡ hỏng mất chiếc máy tính.\n2) A: Trên tàu điện đông đúc tôi bị người ta giẫm vào chân.\n3) A: Tôi bị dính cơn mưa rào ướt sũng cả quần áo.\n4) A: Tôi được thầy giáo khen ngợi trước cả lớp.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Bị hỏng máy tính", "options": ["b a", "c d"], "correctAnswer": "b a", "explanation": "Em làm hỏng máy tính (b a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Bị giẫm chân", "options": ["b a", "c d"], "correctAnswer": "b a", "explanation": "Bị giẫm vào chân (b a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Bị dính mưa", "options": ["a b", "c d"], "correctAnswer": "a b", "explanation": "Dính mưa ướt áo (a b)."},
            {"itemIndex": "4", "prompt": "Câu 4: Được khen", "options": ["b a", "c d"], "correctAnswer": "b a", "explanation": "Được thầy khen (b a)."}
        ]
    ),
    make_task(
        "II_CD2", 12, 2, "2. 〜を(人)に〜(ら)れます", "2. Bị ai đó lấy mất / làm phiền",
        "2. 〜を(人)に〜(ら)れます\n1) A: 泥棒にお金を盗まれました。\n2) A: 妹にケーキを食べられました。",
        "2. Bị ai đó lấy mất / làm phiền\n1) A: Tôi bị kẻ trộm lẻn vào trộm mất tiền.\n2) A: Tôi bị đứa em gái ăn mất miếng bánh kem ngon lành.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Kẻ trộm", "options": ["④ (Bị trộm tiền)", "①"], "correctAnswer": "④ (Bị trộm tiền)", "explanation": "Bị trộm mất tiền (④)."},
            {"itemIndex": "2", "prompt": "Câu 2: Em gái ăn bánh", "options": ["⑤ (Bị ăn mất bánh)", "②"], "correctAnswer": "⑤ (Bị ăn mất bánh)", "explanation": "Bị em gái ăn mất bánh (⑤)."}
        ]
    ),
    make_task(
        "II_CD2", 13, 3, "3. 〜は〜(ら)れます/〜(ら)れています", "3. Sự vật được sáng chế / được tổ chức",
        "3. 〜は〜(ら)れます/〜(ら)れています\n1) A: この寺は300年前に建てられました。\n2) A: オリンピックは4年ごとに開かれます。\n3) A: 日本の車は世界中で使われています。",
        "3. Sự vật được sáng chế / được tổ chức\n1) A: Ngôi chùa cổ kính này được xây dựng từ cách đây 300 năm.\n2) A: Đại hội Olympic được tổ chức định kỳ 4 năm một lần.\n3) A: Xe ô tô của Nhật Bản được ưa chuộng sử dụng trên toàn thế giới.",
        [
            {"itemIndex": "1", "prompt": "Lịch sử & Tổ chức", "options": ["①300 ②遊ぶ ③手伝う ④覚える/話す ⑤歌う ⑥(小さい)子ども", "Không có thông tin"], "correctAnswer": "①300 ②遊ぶ ③手伝う ④覚える/話す ⑤歌う ⑥(小さい)子ども", "explanation": "Chùa 300 năm, hoạt động giao lưu trẻ em."}
        ]
    ),
    make_task(
        "II_CD2", 14, 4, "4. (人)によって〜(ら)れました", "4. Được phát minh / sáng tác bởi ai",
        "4. (人)によって〜(ら)れました\n1) A: この絵はだれによって描かれましたか。\nB: ピカソによって描かれました。\n2) A: 電話はベルによって発明されました。\n3) A: この小説は有名な作家によって書かれました。",
        "4. Được phát minh / sáng tác bởi ai\n1) A: Bức tranh kiệt tác này được vẽ bởi ai thế?\nB: Được vẽ bởi danh họa Picasso.\n2) A: Chiếc điện thoại đầu tiên được phát minh bởi Graham Bell.\n3) A: Cuốn tiểu thuyết xuất sắc này được viết bởi một đại văn hào nổi tiếng.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Tranh Picasso vẽ", "options": ["b かいた (Picasso vẽ)", "a"], "correctAnswer": "b かいた (Picasso vẽ)", "explanation": "Vẽ bởi Picasso (b かいた)."},
            {"itemIndex": "2", "prompt": "Câu 2: Điện thoại Bell phát minh", "options": ["a 作った (Chế tạo)", "b"], "correctAnswer": "a 作った (Chế tạo)", "explanation": "Phát minh bởi Bell (a 作った)."},
            {"itemIndex": "3", "prompt": "Câu 3: Phát minh / Viết", "options": ["c 発明した", "d"], "correctAnswer": "c 発明した", "explanation": "Phát minh (c 発明した)."},
            {"itemIndex": "4", "prompt": "Câu 4: Phát hiện", "options": ["f 発見した", "b"], "correctAnswer": "f 発見した", "explanation": "Phát hiện ra (f 発見した)."}
        ]
    )
]
LESSONS_31_40.append({
    "lessonNumber": 37, "level": "N4",
    "title": "Bài 37: Thể bị động (受身形)",
    "subtitle": "第37課: 受身・迷惑・〜によって作られました",
    "tasks": l37_tasks
})

# ==================== LESSON 38 ====================
l38_tasks = [
    make_task(
        "II_CD2", 15, 1, "1. 〜のは[形容詞]です", "1. Danh từ hóa động từ (〜のは〜です)",
        "1. 〜のは[形容詞]です\n1) A: 車を持つのはお金の無駄だと思います。\n2) A: ずっと一人で生活するのは寂しいです。\n3) A: 子どもを育てるのは大きな夢があります。",
        "1. Danh từ hóa động từ (〜のは〜です)\n1) A: Việc sở hữu xe hơi ở Tokyo theo tôi là rất lãng phí tiền bạc.\n2) A: Việc sống cô độc một mình suốt bao năm qua thật là quạnh hiu.\n3) A: Việc nuôi nấng dạy dỗ con cái là cả một ước mơ lớn lao.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Sở hữu xe", "options": ["b 車を持つ お金のむだだ (Lãng phí tiền)", "Rất tiện"], "correctAnswer": "b 車を持つ お金のむだだ (Lãng phí tiền)", "explanation": "Sở hữu xe là tốn tiền."},
            {"itemIndex": "2", "prompt": "Câu 2: Sống một mình", "options": ["a (ずっと)一人で生活する 寂しい (Cô đơn)", "Vui"], "correctAnswer": "a (ずっと)一人で生活する 寂しい (Cô đơn)", "explanation": "Sống một mình thì cô đơn."},
            {"itemIndex": "3", "prompt": "Câu 3: Nuôi con", "options": ["a 子どもを育てる 夢がある (Có ước mơ)", "Vất vả"], "correctAnswer": "a 子どもを育てる 夢がある (Có ước mơ)", "explanation": "Nuôi dạy con là ước mơ."}
        ]
    ),
    make_task(
        "II_CD2", 16, 2, "2. 〜のが好き/上手です", "2. Thích việc làm gì / Giỏi việc làm gì",
        "2. 〜のが好き/上手です\n1) A: わたしは絵を描くのが好きです。\n2) A: 彼は料理を作るのがとても上手です。\n3) A: 彼女は走るのが速いです。",
        "2. Thích việc làm gì / Giỏi việc làm gì\n1) A: Tôi rất thích việc vẽ tranh phong cảnh.\n2) A: Anh ấy nấu các món ăn cực kỳ ngon và điêu luyện.\n3) A: Cô ấy chạy cự ly ngắn rất nhanh nhẹn.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Thích vẽ", "options": ["b ② a", "c"], "correctAnswer": "b ② a", "explanation": "Thích vẽ tranh (b ② a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Giỏi nấu ăn", "options": ["f ④ a", "c"], "correctAnswer": "f ④ a", "explanation": "Nấu ăn rất giỏi (f ④ a)."},
            {"itemIndex": "3", "prompt": "Câu 3: Chạy nhanh", "options": ["g ① b", "d"], "correctAnswer": "g ① b", "explanation": "Chạy nhanh (g ① b)."}
        ]
    ),
    make_task(
        "II_CD2", 17, 3, "3. 〜のを忘れました", "3. Quên mất việc phải làm gì",
        "3. 〜のを忘れました\n1) A: 鍵をかけるのを忘れました。\n2) A: 手紙を出すのを忘れました。",
        "3. Quên mất việc phải làm gì\n1) A: Tôi quên béng mất việc khóa cửa phòng rồi.\n2) A: Tôi quên gửi lá thư đi mất rồi.",
        [
            {"itemIndex": "1", "prompt": "Quên làm việc gì", "options": ["① ②", "③ ④"], "correctAnswer": "① ②", "explanation": "Quên khóa cửa và gửi thư (① ②)."}
        ]
    ),
    make_task(
        "II_CD2", 18, 4, "4. 〜のを知っていますか", "4. Bạn có biết việc... không?",
        "4. 〜のを知っていますか\n1) A: 木村さんが結婚したのを知っていますか。\nB: ええ、知っています。\n2) A: 来週試験があるのを知っていますか。\nB: いいえ、知りませんでした。",
        "4. Bạn có biết việc... không?\n1) A: Bạn có biết chuyện chị Kimura đã kết hôn rồi không?\nB: Có, tôi biết rồi.\n2) A: Bạn có biết việc tuần sau có kì thi không?\nB: Ôi thế à, tôi hoàn toàn không hay biết.",
        [
            {"itemIndex": "1", "prompt": "Thông tin", "options": ["中国 小さい / 沖縄 日本", "Mỹ to"], "correctAnswer": "中国 小さい / 沖縄 日本", "explanation": "Thông tin địa lý và kinh tế."},
            {"itemIndex": "2", "prompt": "Kinh tế", "options": ["日本 中国 経済", "Châu Âu"], "correctAnswer": "日本 中国 経済", "explanation": "Kinh tế Nhật Trung."}
        ]
    ),
    make_task(
        "II_CD2", 19, 5, "5. 〜のは[名詞]です", "5. Nơi/Thứ mà tôi... chính là...",
        "5. 〜のは[名詞]です\n1) A: わたしが生まれたのは北海道の小さな町です。\n2) A: 初めて会ったのは京都の喫茶店でした。\n3) A: 一番欲しいのは時間です。",
        "5. Nơi/Thứ mà tôi... chính là...\n1) A: Nơi mà tôi cất tiếng khóc chào đời chính là một thị trấn nhỏ ở Hokkaido.\n2) A: Lần đầu tiên chúng tôi gặp nhau chính là ở một quán cà phê cổ tại Kyoto.\n3) A: Thứ mà hiện tại tôi khao khát nhất chính là thời gian.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nơi sinh", "options": ["b (Hokkaido)", "a"], "correctAnswer": "b (Hokkaido)", "explanation": "Sinh ra ở Hokkaido (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nơi gặp đầu tiên", "options": ["b (Quán cà phê Kyoto)", "a"], "correctAnswer": "b (Quán cà phê Kyoto)", "explanation": "Gặp ở quán cafe (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Thứ cần nhất", "options": ["c (Thời gian)", "a"], "correctAnswer": "c (Thời gian)", "explanation": "Cần thời gian (c)."}
        ]
    )
]
LESSONS_31_40.append({
    "lessonNumber": 38, "level": "N4",
    "title": "Bài 38: Danh từ hóa động từ (〜のは/のが/のを)",
    "subtitle": "第38課: のは形容詞・のが好き・のを忘れた・のを知っている",
    "tasks": l38_tasks
})

# ==================== LESSON 39 ====================
l39_tasks = [
    make_task(
        "II_CD2", 20, 1, "1. 〜て/〜なくて、〜", "1. Nguyên nhân cảm xúc (Vì... nên thấy...)",
        "1. 〜て/〜なくて、〜\n1) A: ニュースを聞いて、びっくりしました。\n2) A: 家族に会えなくて、寂しいです。\n3) A: 試験に合格して、とても嬉しいです。",
        "1. Nguyên nhân cảm xúc (Vì... nên thấy...)\n1) A: Nghe bản tin xong mà tôi giật cả mình sửng sốt.\n2) A: Không được gặp gia đình nên tôi thấy nhớ nhà da diết.\n3) A: Đỗ kì thi chứng chỉ nên tôi vui mừng khôn xiết.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Bất ngờ", "options": ["c (Nghe tin giật mình)", "a"], "correctAnswer": "c (Nghe tin giật mình)", "explanation": "Sửng sốt khi nghe tin (c)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nhớ nhà", "options": ["c (Nhớ nhà vì không gặp)", "a"], "correctAnswer": "c (Nhớ nhà vì không gặp)", "explanation": "Buồn vì xa nhà (c)."},
            {"itemIndex": "3", "prompt": "Câu 3: Đỗ kỳ thi", "options": ["b (Vui mừng vì đỗ)", "a"], "correctAnswer": "b (Vui mừng vì đỗ)", "explanation": "Vui vì thi đỗ (b)."}
        ]
    ),
    make_task(
        "II_CD2", 21, 2, "2. 〜て/〜くて/〜で、〜", "2. Vì điều kiện... nên kết quả...",
        "2. 〜て/〜くて/〜で、〜\n1) A: 部屋が狭くて、荷物が入りません。\n2) A: 話が複雑で、よくわかりませんでした。",
        "2. Vì điều kiện... nên kết quả...\n1) A: Căn phòng chật chội quá nên không nhét hết đồ đạc vào được.\n2) A: Câu chuyện rắc rối phức tạp quá nên tôi không hiểu rõ ngọn ngành.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Phòng chật", "options": ["b ② (Phòng chật không chứa hết)", "a"], "correctAnswer": "b ② (Phòng chật không chứa hết)", "explanation": "Phòng chật chội (b ②)."},
            {"itemIndex": "2", "prompt": "Câu 2: Câu chuyện", "options": ["a ② (Phức tạp không hiểu)", "b"], "correctAnswer": "a ② (Phức tạp không hiểu)", "explanation": "Phức tạp không rõ (a ②)."}
        ]
    ),
    make_task(
        "II_CD2", 22, 3, "3. [名詞]で、〜", "3. Vì thiên tai, sự cố, lý do khách quan",
        "3. [名詞]で、〜\n1) A: 病気で会社を休みました。\n2) A: 大雪で電車が止まりました。\n3) A: 地震でビルが倒れました。",
        "3. Vì thiên tai, sự cố, lý do khách quan\n1) A: Vì ốm đau nên tôi phải xin nghỉ làm.\n2) A: Vì tuyết rơi dày đặc nên các chuyến tàu bị tê liệt tạm dừng.\n3) A: Vì trận động đất mạnh làm cho tòa nhà bị đổ sập.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Nghỉ ốm", "options": ["病気 b (Vì ốm)", "Du lịch"], "correctAnswer": "病気 b (Vì ốm)", "explanation": "Nghỉ do ốm đau."},
            {"itemIndex": "2", "prompt": "Câu 2: Tàu dừng vì tuyết", "options": ["雪 a (Vì bão tuyết)", "Mưa"], "correctAnswer": "雪 a (Vì bão tuyết)", "explanation": "Tàu dừng do bão tuyết."},
            {"itemIndex": "3", "prompt": "Câu 3: Đổ sập vì động đất", "options": ["倒れて a (Đổ sập do động đất)", "Cháy"], "correctAnswer": "倒れて a (Đổ sập do động đất)", "explanation": "Tòa nhà đổ do động đất."}
        ]
    ),
    make_task(
        "II_CD2", 23, 4, "4. 〜ので、〜", "4. Vì... (Nguyên nhân khách quan lịch sự)",
        "4. 〜ので、〜\n1) A: 用事があるので、お先に失礼します。\n2) A: 気分が悪いので、少し休んでもいいですか。\n3) A: バスが来ないので、タクシーで行きましょう。",
        "4. Vì... (Nguyên nhân khách quan lịch sự)\n1) A: Vì tôi có chút việc bận nên xin phép cho tôi được về trước ạ.\n2) A: Vì thấy trong người hơi khó chịu nên em xin phép nghỉ một lát được không ạ?\n3) A: Vì mãi không thấy xe buýt tới nên chúng ta bắt taxi đi thôi.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Về trước", "options": ["④ a (Có việc xin về trước)", "② b"], "correctAnswer": "④ a (Có việc xin về trước)", "explanation": "Xin phép về trước (④ a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Nghỉ ngơi", "options": ["⑤ b (Mệt xin nghỉ)", "① a"], "correctAnswer": "⑤ b (Mệt xin nghỉ)", "explanation": "Mệt xin nghỉ một lát (⑤ b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Bắt taxi", "options": ["② b (Bắt taxi vì lỡ xe)", "③ a"], "correctAnswer": "② b (Bắt taxi vì lỡ xe)", "explanation": "Bắt taxi vì xe buýt chưa tới (② b)."}
        ]
    )
]
LESSONS_31_40.append({
    "lessonNumber": 39, "level": "N4",
    "title": "Bài 39: Nguyên nhân, Lý do (〜て/〜ので/〜で)",
    "subtitle": "第39課: 原因理由・感情て・ので・事故で",
    "tasks": l39_tasks
})

# ==================== LESSON 40 ====================
l40_tasks = [
    make_task(
        "II_CD2", 24, 1, "1. [疑問詞]〜か、〜", "1. Câu hỏi lồng có từ để hỏi (か)",
        "1. [疑問詞]〜か、〜\n1) A: 会議は何時に始まるか、知っていますか。\n2) A: どこへ行くか、決まりましたか。\n3) A: だれが来るか、教えてください。",
        "1. Câu hỏi lồng có từ để hỏi (か)\n1) A: Bạn có biết cuộc họp sẽ bắt đầu lúc mấy giờ không?\n2) A: Đã chốt được sẽ đi đâu chơi chưa?\n3) A: Cho tôi biết danh sách những ai sẽ đến nhé.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Giờ họp", "options": ["d (Mấy giờ họp)", "a"], "correctAnswer": "d (Mấy giờ họp)", "explanation": "Mấy giờ bắt đầu cuộc họp (d)."},
            {"itemIndex": "2", "prompt": "Câu 2: Đi đâu", "options": ["e (Đi đâu)", "b"], "correctAnswer": "e (Đi đâu)", "explanation": "Quyết định đi đâu (e)."},
            {"itemIndex": "3", "prompt": "Câu 3: Ai đến", "options": ["b (Ai sẽ tới)", "c"], "correctAnswer": "b (Ai sẽ tới)", "explanation": "Ai sẽ tới tham dự (b)."}
        ]
    ),
    make_task(
        "II_CD2", 25, 2, "2. 〜かどうか、〜", "2. Có hay là không... (かどうか)",
        "2. 〜かどうか、〜\n1) A: 田中さんが一人で住んでいるか両親と一緒に住んでいるか、知っていますか。\n2) A: アルバイトが好きかどうかわかりません。\n3) A: 結婚して子どもがいるかどうかわかりません。",
        "2. Có hay là không... (かどうか)\n1) A: Bạn có biết anh Tanaka đang sống một mình hay sống cùng bố mẹ không?\n2) A: Tôi không rõ cậu ấy có thích đi làm thêm hay không.\n3) A: Tôi không rõ là chị ấy đã kết hôn và có con hay chưa.",
        [
            {"itemIndex": "1", "prompt": "Cuộc sống riêng", "options": ["①住んで ②両親 (Sống cùng bố mẹ hay một mình)", "Ở trọ"], "correctAnswer": "①住んで ②両親 (Sống cùng bố mẹ hay một mình)", "explanation": "Sống với bố mẹ hay một mình."},
            {"itemIndex": "2", "prompt": "Làm thêm", "options": ["③アルバイト ④アルバイト ⑤勉強します ⑥好きです", "Không đi làm"], "correctAnswer": "③アルバイト ④アルバイト ⑤勉強します ⑥好きです", "explanation": "Có thích làm thêm và học hay không."},
            {"itemIndex": "3", "prompt": "Gia đình", "options": ["⑦結婚し ⑧子ども", "Độc thân"], "correctAnswer": "⑦結婚し ⑧子ども", "explanation": "Kết hôn và có con."}
        ]
    ),
    make_task(
        "II_CD2", 26, 3, "3. 〜てみます", "3. Thử làm việc gì xem sao",
        "3. 〜てみます\n1) A: この靴を履いてみてもいいですか。\nB: はい、どうぞ履いてみてください。\n2) A: 日本の料理を作ってみました。\n3) A: 新しいソフトを使ってみます。",
        "3. Thử làm việc gì xem sao\n1) A: Tôi xỏ thử đôi giày này xem có vừa chân không được không ạ?\nB: Vâng, xin mời bạn cứ đi thử thoải mái.\n2) A: Tôi đã thử tự tay nấu món ăn truyền thống Nhật Bản.\n3) A: Tôi sẽ thử cài đặt và sử dụng phần mềm mới xem thế nào.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Thử giày", "options": ["③ a (Đi thử giày)", "① b"], "correctAnswer": "③ a (Đi thử giày)", "explanation": "Xỏ thử giày (③ a)."},
            {"itemIndex": "2", "prompt": "Câu 2: Thử nấu ăn", "options": ["⑥ f (Thử nấu món Nhật)", "④ e"], "correctAnswer": "⑥ f (Thử nấu món Nhật)", "explanation": "Thử nấu món ăn (⑥ f)."},
            {"itemIndex": "3", "prompt": "Câu 3: Thử phần mềm", "options": ["② e (Thử dùng phần mềm)", "① d"], "correctAnswer": "② e (Thử dùng phần mềm)", "explanation": "Dùng thử phần mềm (② e)."},
            {"itemIndex": "4", "prompt": "Câu 4: Trải nghiệm", "options": ["① d", "② c"], "correctAnswer": "① d", "explanation": "Trải nghiệm thử (① d)."}
        ]
    ),
    make_task(
        "II_CD2", 27, 4, "4. 〜かどうか、〜てみます", "4. Thử xem có được hay không",
        "4. 〜かどうか、〜てみます\n1) A: サイズが合うかどうか、着てみます。\n2) A: 間に合うかどうか、走ってみます。\n3) A: 美味しいかどうか、食べてみます。",
        "4. Thử xem có được hay không\n1) A: Tôi mặc thử xem cỡ áo có vừa người hay không.\n2) A: Tôi chạy thử hết tốc lực xem có kịp giờ tàu chạy hay không.\n3) A: Tôi nếm thử xem món ăn có hợp khẩu vị hay không.",
        [
            {"itemIndex": "1", "prompt": "Câu 1: Thử cỡ áo", "options": ["b (Mặc thử xem vừa không)", "a"], "correctAnswer": "b (Mặc thử xem vừa không)", "explanation": "Mặc thử cỡ áo (b)."},
            {"itemIndex": "2", "prompt": "Câu 2: Chạy thử", "options": ["b (Chạy thử cho kịp)", "a"], "correctAnswer": "b (Chạy thử cho kịp)", "explanation": "Chạy thử xem kịp giờ (b)."},
            {"itemIndex": "3", "prompt": "Câu 3: Nếm thử", "options": ["a (Nếm thử đồ ăn)", "b"], "correctAnswer": "a (Nếm thử đồ ăn)", "explanation": "Nếm thử đồ ăn (a)."}
        ]
    )
]
LESSONS_31_40.append({
    "lessonNumber": 40, "level": "N4",
    "title": "Bài 40: Câu hỏi gián tiếp (〜か/〜かどうか) & Thử làm (〜てみます)",
    "subtitle": "第40課: か・かどうか・〜てみる",
    "tasks": l40_tasks
})

print("Lessons 31 to 40 defined in LESSONS_31_40.")
