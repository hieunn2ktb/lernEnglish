import json
import os

# Helper to build a quiz item
def q(question, meaning, options, correct_index, explanation):
    return {
        "question": question,
        "meaning": meaning,
        "options": options,
        "correctIndex": correct_index,
        "correctAnswer": options[correct_index],
        "explanation": explanation
    }

# Load current exercises (which has Lesson 1 fully expanded and 2-50 checksheets)
DATA_PATH = "app/src/main/assets/kaite_oboeru_exercises.json"
with open(DATA_PATH, "r", encoding="utf-8") as f:
    lessons = json.load(f)

# Map existing checksheets
checksheets = {}
for l in lessons:
    num = l["lessonNumber"]
    # For lesson 1, it already has 29 quizzes (worksheets + checksheet)
    if num == 1:
        continue
    # For others, extract the existing checksheet questions
    existing_qs = l.get("quizzes", [])
    # Ensure they have tag 【X課 文法チェック】
    for item in existing_qs:
        if not item["question"].startswith("【"):
            item["question"] = f"【{num}課 文法チェック】" + item["question"]
    checksheets[num] = existing_qs

print(f"Loaded checksheets for {len(checksheets)} lessons.")
