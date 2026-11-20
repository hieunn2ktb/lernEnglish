# -*- coding: utf-8 -*-
import json
import os

print("Generating Choukai 50 lessons dataset...")

lessons = []

def make_task(cd_code, track_num, task_num, title, title_vi, script, script_vi, questions):
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
    
    return {
        "taskNumber": task_num,
        "title": title,
        "titleVi": title_vi,
        "cdName": cd_name,
        "cdFolder": cd_folder,
        "audioFile": audio_file,
        "trackLabel": track_label,
        "script": script,
        "scriptVi": script_vi,
        "questions": questions
    }

print("make_task defined successfully.")
