import PyPDF2
import os

def extract_text_from_pdf(pdf_path, txt_path):
    with open(pdf_path, 'rb') as file:
        reader = PyPDF2.PdfReader(file)
        text = ''
        for page_num in range(len(reader.pages)):
            page = reader.pages[page_num]
            text += page.extract_text()
            
    with open(txt_path, 'w', encoding='utf-8') as f:
        f.write(text)

files_to_extract = [
    (r"C:\Users\Admin\AndroidStudioProjects\LearnEnglish\ouput\day16\Thì tương lai đơn.pdf", "day16_vocab.txt"),
    (r"C:\Users\Admin\AndroidStudioProjects\LearnEnglish\ouput\day16\Bài thi online - Thì tương lai đơn.pdf", "day16_quiz.txt"),
    (r"C:\Users\Admin\AndroidStudioProjects\LearnEnglish\ouput\day17\Thì tương lai hoàn thành.pdf", "day17_vocab.txt"),
    (r"C:\Users\Admin\AndroidStudioProjects\LearnEnglish\ouput\day17\Bài thi online - Thì tương lai hoàn thành.pdf", "day17_quiz.txt")
]

for pdf_path, txt_path in files_to_extract:
    try:
        extract_text_from_pdf(pdf_path, txt_path)
        print(f"Extracted {txt_path}")
    except Exception as e:
        print(f"Error extracting {txt_path}: {e}")
