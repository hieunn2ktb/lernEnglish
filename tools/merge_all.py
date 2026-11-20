import json
import os
import sys

from data_lessons_02_10 import LESSONS_02_10
from data_lessons_11_20 import LESSONS_11_20
from data_lessons_21_30 import LESSONS_21_30
from data_lessons_31_40 import LESSONS_31_40
from data_lessons_41_50 import LESSONS_41_50

TARGET_FILE = "app/src/main/assets/kaite_oboeru_exercises.json"

with open(TARGET_FILE, "r", encoding="utf-8") as f:
    existing_lessons = json.load(f)

# Combine worksheet dictionaries
all_worksheets = {}
all_worksheets.update(LESSONS_02_10)
all_worksheets.update(LESSONS_11_20)
all_worksheets.update(LESSONS_21_30)
all_worksheets.update(LESSONS_31_40)
all_worksheets.update(LESSONS_41_50)

updated_lessons = []

for l in existing_lessons:
    num = l["lessonNumber"]
    book = 1 if num <= 25 else 2
    title = l.get("title", f"Bài {num}")

    if num == 1:
        # Lesson 1 already has full 29 quizzes
        updated_lessons.append(l)
        continue

    ws_quizzes = all_worksheets.get(num, [])
    
    # Get existing checksheet quizzes
    cs_quizzes = l.get("quizzes", [])
    standardized_cs = []
    for q in cs_quizzes:
        q_copy = dict(q)
        q_text = q_copy.get("question", "")
        if not q_text.startswith("【"):
            q_copy["question"] = f"【{num}課 文法チェック】" + q_text
        standardized_cs.append(q_copy)

    # Combined quizzes: Worksheets + Checksheet
    combined_quizzes = ws_quizzes + standardized_cs

    updated_lessons.append({
        "lessonNumber": num,
        "book": book,
        "title": title,
        "quizzes": combined_quizzes
    })

updated_lessons.sort(key=lambda x: x["lessonNumber"])

with open(TARGET_FILE, "w", encoding="utf-8") as f:
    json.dump(updated_lessons, f, ensure_ascii=False, indent=2)

print(f"Successfully updated {len(updated_lessons)} lessons in {TARGET_FILE}")
total_q = sum(len(x["quizzes"]) for x in updated_lessons)
print(f"Total quizzes across all 50 lessons: {total_q}")
for l in updated_lessons[:5]:
    print(f"  Lesson {l['lessonNumber']}: {len(l['quizzes'])} quizzes")
print("  ...")
for l in updated_lessons[-3:]:
    print(f"  Lesson {l['lessonNumber']}: {len(l['quizzes'])} quizzes")
