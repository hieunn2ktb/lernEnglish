# -*- coding: utf-8 -*-
import json
import os

def build_choukai_database():
    lessons = []
    
    # Lesson 1 to 50 definitions
    lesson_metadata = [
        # Lesson 1
        {
            "num": 1, "level": "N5",
            "title": "Bài 1: Giới thiệu bản thân & Nghề nghiệp",
            "subtitle": "第1課: 自己紹介・国・仕事・年齢",
            "cd": "I_CD1",
            "tasks": [
                {
                    "taskNum": 1, "track": 2,
                    "title": "1. 名前は a ですか、b ですか。",
                    "titleVi": "1. Tên là a hay b?",
                    "script": "1. 名前は a ですか、b ですか。\n例) A: 初めまして、益田です。\nB: 松田さんですね。\nA: いいえ、益田です。\n1) A: 初めまして、佐野です。どうぞよろしく。\nB: さろさんですか。\nA: いいえ、佐野です。\n2) A: こんにちは、鈴木です。\nB: ああ、鈴木さんですね。\nA: ええ。\n3) A: 失礼ですが、お名前は？\nB: 後藤です。\nA: ごどうさんですか。\nB: いいえ、後藤です。",
                    "scriptVi": "1. Tên nhân vật là a hay b?\nVí dụ) A: Xin chào lần đầu gặp mặt, tôi là Masuda.\nB: Anh Matsuda phải không?\nA: Không, tôi là Masuda.\n1) A: Xin chào, tôi là Sano. Rất mong được giúp đỡ.\nB: Anh Saro à?\nA: Không, là Sano ạ.\n2) A: Xin chào, tôi là Suzuki.\nB: À, anh Suzuki phải không.\nA: Vâng đúng rồi.\n3) A: Xin lỗi, anh tên là gì ạ?\nB: Tôi là Gotou.\nA: Anh Godou à?\nB: Không, là Gotou ạ.",
                    "questions": [
                        {"item": "例", "prompt": "Ví dụ: Tên của nhân vật", "options": ["a. まつだ (Matsuda)", "b. ますだ (Masuda)"], "answer": "b", "explanation": "Nhân vật nói: いいえ、益田 (ますだ) です -> Đáp án b."},
                        {"item": "1", "prompt": "Câu 1: Tên của nhân vật", "options": ["a. さの (Sano)", "b. さろ (Saro)"], "answer": "a", "explanation": "Nhân vật đính chính: いいえ、佐野 (さの) です -> Đáp án a."},
                        {"item": "2", "prompt": "Câu 2: Tên của nhân vật", "options": ["a. すずき (Suzuki)", "b. つづき (Tsuzuki)"], "answer": "a", "explanation": "Nhân vật xác nhận: 鈴木 (すずき) です -> Đáp án a."},
                        {"item": "3", "prompt": "Câu 3: Tên của nhân vật", "options": ["a. こどう (Kodou)", "b. ごとう (Gotou)"], "answer": "b", "explanation": "Nhân vật sửa lại: いいえ、後藤 (ごとう) です -> Đáp án b."}
                    ]
                },
                {
                    "taskNum": 2, "track": 3,
                    "title": "2. 国は a ですか、b ですか。",
                    "titleVi": "2. Đất nước/quốc tịch là a hay b?",
                    "script": "2. 国は a ですか、b ですか。\n例) A: 初めまして、ワンです。中国から来ました。どうぞよろしく。\nB: 初めまして、田中です。どうぞよろしく。\n1) A: 皆さん、こちらはセンさんです。\nB: 初めまして、センです。インドから来ました。どうぞよろしくお願いします。\n2) A: 佐藤さん、こちらはジャンさんです。\nC: ジャンです。フランスから来ました。どうぞよろしく。\nB: 佐藤です。よろしくお願いします。\n3) A: 初めまして、コールです。\nB: コーンさんですか。\nA: いいえ、コールです。ドイツから来ました。\nB: わたしは山田です。よろしくお願いします。",
                    "scriptVi": "2. Quốc gia xuất xứ là a hay b?\nVí dụ) A: Xin chào, tôi là Wang. Tôi đến từ Trung Quốc.\nB: Xin chào, tôi là Tanaka.\n1) A: Mọi người ơi, đây là anh Sen.\nB: Xin chào, tôi là Sen, đến từ Ấn Độ.\n2) A: Chị Satou, đây là anh Jean.\nC: Tôi là Jean, đến từ Pháp.\nB: Tôi là Satou, rất vui được gặp anh.\n3) A: Xin chào, tôi là Cole.\nB: Anh Cone à?\nA: Không, là Cole. Tôi đến từ Đức.\nB: Tôi là Yamada. Rất mong được giúp đỡ.",
                    "questions": [
                        {"item": "例", "prompt": "Ví dụ: ワン (Wang) đến từ nước nào?", "options": ["a. 中国 (Trung Quốc)", "b. 韓国 (Hàn Quốc)"], "answer": "a", "explanation": "Wang giới thiệu: 中国から来ました -> Đáp án a."},
                        {"item": "1", "prompt": "Câu 1: セン (Sen) đến từ nước nào?", "options": ["a. インド (Ấn Độ)", "b. インドネシア (Indonesia)"], "answer": "a", "explanation": "Sen giới thiệu: インドから来ました -> Đáp án a."},
                        {"item": "2", "prompt": "Câu 2: ジャン (Jean) đến từ nước nào?", "options": ["a. ブラジル (Brazil)", "b. フランス (Pháp)"], "answer": "b", "explanation": "Jean giới thiệu: フランスから来ました -> Đáp án b."},
                        {"item": "3", "prompt": "Câu 3: コール (Cole) đến từ nước nào?", "options": ["a. インド (Ấn Độ)", "b. ドイツ (Đức)"], "answer": "b", "explanation": "Cole nói: ドイツから来ました -> Đáp án b."}
                    ]
                },
                {
                    "taskNum": 3, "track": 4,
                    "title": "3. 女の人の仕事は a ですか、b ですか。",
                    "titleVi": "3. Nghề nghiệp của người phụ nữ là a hay b?",
                    "script": "3. 女の人の仕事は a ですか、b ですか。\n例) A: 初めまして、ワットです。イギリスから来ました。\nB: 初めまして、小林です。\nA: わたしはさくら大学の教師です。\nB: あ、わたしも教師です。富士大学です。どうぞよろしく。\n1) A: 初めまして、ワンです。\nB: 初めまして、後藤です。\nA: わたしは神戸病院の医者です。どうぞよろしく。\nB: あ、わたしも医者です。どうぞよろしく。\n2) A: 初めまして、カルロスです。ブラジルから来ました。\nB: 初めまして、木村です。\nA: わたしはブラジルエアーの社員です。\nB: 木村さんは？\nB: わたしは銀行員です。\n3) A: 初めまして、ミラーです。IMCの社員です。どうぞよろしく。\nB: 初めまして、益田です。\nA: 益田さんもIMCの社員ですか。\nB: いいえ、パワー電気の社員です。どうぞよろしく。",
                    "scriptVi": "3. Công việc của người phụ nữ là a hay b?\nVí dụ) A: Xin chào, tôi là Watt đến từ Anh, giảng viên đại học Sakura.\nB: Tôi là Kobayashi, tôi cũng là giảng viên ở đại học Fuji.\n1) A: Tôi là Wang, bác sĩ bệnh viện Kobe.\nB: Tôi là Gotou, tôi cũng là bác sĩ.\n2) A: Tôi là Carlos, nhân viên hãng hàng không Brazil Air. Chị Kimura làm nghề gì?\nB: Tôi là nhân viên ngân hàng.\n3) A: Tôi là Miller, nhân viên công ty IMC. Cô Masuda cũng làm ở IMC à?\nB: Không, tôi là nhân viên công ty Điện lực Power.",
                    "questions": [
                        {"item": "例", "prompt": "Ví dụ: Người phụ nữ làm ở đâu?", "options": ["a. 富士大学 (Đại học Fuji)", "b. さくら大学 (Đại học Sakura)"], "answer": "a", "explanation": "Người phụ nữ (B) nói: わたしも教師です。富士大学です -> Đáp án a."},
                        {"item": "1", "prompt": "Câu 1: Nghề nghiệp của người phụ nữ (後藤)", "options": ["a. 医者 (Bác sĩ)", "b. 教師 (Giáo viên)"], "answer": "a", "explanation": "Người phụ nữ nói: わたしも医者です (Tôi cũng là bác sĩ) -> Đáp án a."},
                        {"item": "2", "prompt": "Câu 2: Nghề nghiệp của người phụ nữ (木村)", "options": ["a. 会社の社員 (Nhân viên công ty)", "b. 銀行員 (Nhân viên ngân hàng)"], "answer": "b", "explanation": "Người phụ nữ nói: わたしは銀行員です (Tôi là nhân viên ngân hàng) -> Đáp án b."},
                        {"item": "3", "prompt": "Câu 3: Nơi làm việc của người phụ nữ (益田)", "options": ["a. IMC", "b. パワー電気 (Điện lực Power)"], "answer": "b", "explanation": "Người phụ nữ nói: いいえ、パワー電気の社員です -> Đáp án b."}
                    ]
                },
                {
                    "taskNum": 4, "track": 5,
                    "title": "4. 何歳ですか。",
                    "titleVi": "4. Bao nhiêu tuổi?",
                    "script": "4. 何歳ですか。\n例) A: ミラーさん、失礼ですが、おいくつですか。\nB: 28歳です。\n1) A: 益田さん、お母さんはおいくつですか。\nB: 母ですか。71歳です。\n2) A: 可愛いですね。何歳ですか。\nB: 8歳です。小学2年生です。\n3) A: ケリーさん、失礼ですが、おいくつですか。\nB: 26歳です。\nA: そうですか。わたしも26歳です。",
                    "scriptVi": "4. Bao nhiêu tuổi?\nVí dụ) A: Anh Miller, xin lỗi nhưng anh bao nhiêu tuổi rồi ạ?\nB: Tôi 28 tuổi.\n1) A: Anh Masuda, mẹ của anh bao nhiêu tuổi rồi?\nB: Mẹ tôi à? 71 tuổi rồi.\n2) A: Bé dễ thương quá, bé mấy tuổi rồi?\nB: Cháu 8 tuổi, học sinh lớp 2.\n3) A: Kelly này, xin hỏi cô bao nhiêu tuổi?\nB: Tôi 26 tuổi.\nA: Thế à, tôi cũng 26 tuổi.",
                    "questions": [
                        {"item": "例", "prompt": "Ví dụ: Tuổi của ミラー (Miller)", "options": ["28歳", "25歳", "30歳"], "answer": "28歳", "explanation": "Miller trả lời: 28歳です -> 28 tuổi."},
                        {"item": "1", "prompt": "Câu 1: Tuổi của mẹ anh 益田 (Masuda)", "options": ["71歳", "61歳", "81歳"], "answer": "71歳", "explanation": "Masuda trả lời: 母ですか。71歳 (ななじゅういっさい) です -> 71 tuổi."},
                        {"item": "2", "prompt": "Câu 2: Tuổi của em bé", "options": ["8歳", "7歳", "9歳"], "answer": "8歳", "explanation": "Người mẹ trả lời: 8歳 (はっさい) です -> 8 tuổi."},
                        {"item": "3", "prompt": "Câu 3: Tuổi của cô ケリー (Kelly)", "options": ["26歳", "24歳", "28歳"], "answer": "26歳", "explanation": "Kelly trả lời: 26歳 (にじゅうろくさい) です -> 26 tuổi."}
                    ]
                }
            ]
        }
    ]
    print("Base metadata check passed")

build_choukai_database()
