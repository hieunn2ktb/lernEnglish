# -*- coding: utf-8 -*-
import json
import os

def create_database():
    lessons = []
    
    def add_lesson(lesson_num, level, title, subtitle, cd_code, tasks_data):
        tasks = []
        for t in tasks_data:
            track_num = t["track"]
            if cd_code == "I_CD1":
                audio_file = f"{track_num:02d}.Track{track_num:02d}.mp3"
                cd_folder = "JML.Minna.no.Nihongo.I-Choukai.CD1/Minna no Nihongo Choukai I CD1"
                track_label = f"CD 1 - Track {track_num:02d}"
                cd_name = "CD1"
            elif cd_code == "I_CD2":
                audio_file = f"{track_num:02d}.Track{track_num:02d}.mp3"
                cd_folder = "JML.Minna.no.Nihongo.I-Choukai.CD2/Minna no Nihongo Choukai I CD2"
                track_label = f"CD 2 - Track {track_num:02d}"
                cd_name = "CD2"
            elif cd_code == "II_CD1":
                audio_file = f"{track_num:02d}.MNN II Choukai CD1 - Track{track_num:02d}.mp3"
                cd_folder = "JML.Minna.no.Nihongo.II-Choukai.CD1/Minna no Nihongo Choukai II CD1"
                track_label = f"CD 1 - Track {track_num:02d}"
                cd_name = "CD1"
            elif cd_code == "II_CD2":
                audio_file = f"{track_num:02d}.MNN II Choukai CD2 - Track{track_num:02d}.mp3"
                cd_folder = "JML.Minna.no.Nihongo.II-Choukai.CD2/Minna no Nihongo Choukai II CD2"
                track_label = f"CD 2 - Track {track_num:02d}"
                cd_name = "CD2"
            elif cd_code == "II_CD3":
                audio_file = f"{track_num:02d}.MNN II Choukai CD3 - Track{track_num:02d}.mp3"
                cd_folder = "JML.Minna.no.Nihongo.II-Choukai.CD3/Minna no Nihongo Choukai II CD3"
                track_label = f"CD 3 - Track {track_num:02d}"
                cd_name = "CD3"
            
            task_obj = {
                "taskNumber": t["taskNum"],
                "title": t["title"],
                "titleVi": t["titleVi"],
                "cdName": cd_name,
                "cdFolder": cd_folder,
                "audioFile": audio_file,
                "trackLabel": track_label,
                "script": t.get("script", ""),
                "scriptVi": t.get("scriptVi", ""),
                "questions": t.get("questions", [])
            }
            tasks.append(task_obj)
            
        lessons.append({
            "lessonNumber": lesson_num,
            "level": level,
            "title": title,
            "subtitle": subtitle,
            "tasks": tasks
        })

    return lessons, add_lesson

print("Base setup ready")
