#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import json
import os

def get_track_filename(cd_code, track_num):
    if cd_code == "I_CD1":
        return f"{track_num:02d}.Track{track_num:02d}.mp3", "JML.Minna.no.Nihongo.I-Choukai.CD1/Minna no Nihongo Choukai I CD1", f"CD 1 - Track {track_num:02d}"
    elif cd_code == "I_CD2":
        return f"{track_num:02d}.Track{track_num:02d}.mp3", "JML.Minna.no.Nihongo.I-Choukai.CD2/Minna no Nihongo Choukai I CD2", f"CD 2 - Track {track_num:02d}"
    elif cd_code == "II_CD1":
        return f"{track_num:02d}.MNN II Choukai CD1 - Track{track_num:02d}.mp3", "JML.Minna.no.Nihongo.II-Choukai.CD1/Minna no Nihongo Choukai II CD1", f"CD 1 - Track {track_num:02d}"
    elif cd_code == "II_CD2":
        return f"{track_num:02d}.MNN II Choukai CD2 - Track{track_num:02d}.mp3", "JML.Minna.no.Nihongo.II-Choukai.CD2/Minna no Nihongo Choukai II CD2", f"CD 2 - Track {track_num:02d}"
    elif cd_code == "II_CD3":
        return f"{track_num:02d}.MNN II Choukai CD3 - Track{track_num:02d}.mp3", "JML.Minna.no.Nihongo.II-Choukai.CD3/Minna no Nihongo Choukai II CD3", f"CD 3 - Track {track_num:02d}"

print("Track helper ready")
