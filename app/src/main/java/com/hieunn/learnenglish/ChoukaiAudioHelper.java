package com.hieunn.learnenglish;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * Quản lý phát âm thanh cho bài luyện nghe Choukai Tasuku:
 * - Hỗ trợ phát từ assets (Bài 1 mẫu sẵn)
 * - Tự động quét từ thư mục máy (/sdcard/Music/choukai/, Download/choukai/, custom path)
 * - Fallback Text-to-Speech phát giọng tiếng Nhật tự nhiên khi chưa có file MP3
 * - Điều khiển tua -5s/+5s, tốc độ 0.8x/1.0x/1.2x, seekbar đồng bộ thời gian thực
 */
public class ChoukaiAudioHelper {
    private static final String TAG = "ChoukaiAudioHelper";
    public static final String PREF_AUDIO_DIR = "pref_choukai_audio_dir";

    public interface AudioListener {
        void onAudioPrepared(int durationMs, String sourceDescription, boolean isTts);
        void onAudioProgress(int currentMs, int durationMs);
        void onAudioStateChanged(boolean isPlaying);
        void onAudioCompleted();
        void onAudioError(String errorMessage);
    }

    private final Context context;
    private AudioListener listener;
    private MediaPlayer mediaPlayer;
    private TextToSpeech tts;
    private boolean isTtsReady = false;
    private boolean isUsingTts = false;
    private boolean isPlayingTts = false;

    private ChoukaiTask currentTask;
    private float currentSpeed = 1.0f;
    private final Handler progressHandler = new Handler(Looper.getMainLooper());
    private final Runnable progressRunnable;

    public ChoukaiAudioHelper(Context context, AudioListener listener) {
        this.context = context.getApplicationContext();
        this.listener = listener;

        progressRunnable = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && !isUsingTts) {
                    try {
                        if (mediaPlayer.isPlaying()) {
                            int cur = mediaPlayer.getCurrentPosition();
                            int dur = mediaPlayer.getDuration();
                            if (listener != null) {
                                listener.onAudioProgress(cur, dur);
                            }
                            progressHandler.postDelayed(this, 250);
                        }
                    } catch (Exception ignored) {}
                }
            }
        };

        initTts();
    }

    private void initTts() {
        tts = new TextToSpeech(context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int res = tts.setLanguage(Locale.JAPAN);
                if (res != TextToSpeech.LANG_MISSING_DATA && res != TextToSpeech.LANG_NOT_SUPPORTED) {
                    isTtsReady = true;
                } else {
                    res = tts.setLanguage(Locale.JAPANESE);
                    isTtsReady = (res != TextToSpeech.LANG_MISSING_DATA && res != TextToSpeech.LANG_NOT_SUPPORTED);
                }
            }
        });

        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
                isPlayingTts = true;
                if (listener != null) {
                    new Handler(Looper.getMainLooper()).post(() -> listener.onAudioStateChanged(true));
                }
            }

            @Override
            public void onDone(String utteranceId) {
                isPlayingTts = false;
                if (listener != null) {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        listener.onAudioStateChanged(false);
                        listener.onAudioCompleted();
                    });
                }
            }

            @Override
            public void onError(String utteranceId) {
                isPlayingTts = false;
                if (listener != null) {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        listener.onAudioStateChanged(false);
                        listener.onAudioError("Lỗi giọng đọc TTS");
                    });
                }
            }
        });
    }

    public void loadTask(ChoukaiTask task) {
        this.currentTask = task;
        stop();

        if (task == null) return;

        // 1. Check in assets/choukai/
        String assetPath = "choukai/" + task.getAudioFile();
        try {
            AssetFileDescriptor afd = context.getAssets().openFd(assetPath);
            setupMediaPlayerWithAfd(afd, "Âm thanh gốc trong ứng dụng (Assets)");
            return;
        } catch (Exception ignored) {
            // Not in assets
        }

        // 2. Check local storage paths
        File audioFile = findAudioFileOnDevice(task);
        if (audioFile != null && audioFile.exists() && audioFile.length() > 0) {
            setupMediaPlayerWithFile(audioFile, "File âm thanh từ bộ nhớ: " + audioFile.getName());
            return;
        }

        // 3. Fallback: TTS
        isUsingTts = true;
        if (listener != null) {
            listener.onAudioPrepared(30000, "Giọng đọc mô phỏng (TTS) - Chưa tải MP3", true);
            listener.onAudioStateChanged(false);
        }
    }

    private File findAudioFileOnDevice(ChoukaiTask task) {
        SharedPreferences prefs = context.getSharedPreferences("choukai_prefs", Context.MODE_PRIVATE);
        String customDir = prefs.getString(PREF_AUDIO_DIR, "");

        String[] searchFolders = new String[] {
                customDir,
                "/sdcard/Music/choukai",
                "/sdcard/Music/choukai/" + task.getCdName(),
                "/sdcard/Download/choukai",
                "/sdcard/Download/choukai/" + task.getCdName(),
                "/storage/emulated/0/Music/choukai",
                "/storage/emulated/0/Download/choukai",
                context.getExternalFilesDir("choukai") != null ? context.getExternalFilesDir("choukai").getAbsolutePath() : "",
                "/home/nguyen-nhu-hieu/Downloads/nhat/1/nghe/drive-download-20261120T083142Z-1-001"
        };

        for (String folder : searchFolders) {
            if (folder == null || folder.trim().isEmpty()) continue;

            // Direct check inside folder
            File f1 = new File(folder, task.getAudioFile());
            if (f1.exists() && f1.length() > 0) return f1;

            // Check with cdFolder
            if (task.getCdFolder() != null && !task.getCdFolder().isEmpty()) {
                File f2 = new File(folder, task.getCdFolder() + File.separator + task.getAudioFile());
                if (f2.exists() && f2.length() > 0) return f2;
            }

            // Check inside CD subdirectories
            File dir = new File(folder);
            if (dir.exists() && dir.isDirectory()) {
                File found = searchRecursive(dir, task.getAudioFile(), 2);
                if (found != null) return found;
            }
        }
        return null;
    }

    private File searchRecursive(File dir, String filename, int maxDepth) {
        if (maxDepth < 0 || dir == null || !dir.isDirectory()) return null;
        File[] files = dir.listFiles();
        if (files == null) return null;

        for (File f : files) {
            if (f.isFile() && f.getName().equalsIgnoreCase(filename)) {
                return f;
            } else if (f.isDirectory()) {
                File child = searchRecursive(f, filename, maxDepth - 1);
                if (child != null) return child;
            }
        }
        return null;
    }

    private void setupMediaPlayerWithAfd(AssetFileDescriptor afd, String description) {
        releaseMediaPlayer();
        isUsingTts = false;
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build());
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            prepareMediaPlayer(description);
        } catch (Exception e) {
            Log.e(TAG, "Error playing from asset", e);
            fallbackToTts();
        }
    }

    private void setupMediaPlayerWithFile(File file, String description) {
        releaseMediaPlayer();
        isUsingTts = false;
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build());
            mediaPlayer.setDataSource(file.getAbsolutePath());
            prepareMediaPlayer(description);
        } catch (Exception e) {
            Log.e(TAG, "Error playing from file: " + file.getAbsolutePath(), e);
            fallbackToTts();
        }
    }

    private void prepareMediaPlayer(String description) throws IOException {
        mediaPlayer.setOnPreparedListener(mp -> {
            applySpeed();
            if (listener != null) {
                listener.onAudioPrepared(mp.getDuration(), description, false);
                listener.onAudioStateChanged(false);
            }
        });

        mediaPlayer.setOnCompletionListener(mp -> {
            progressHandler.removeCallbacks(progressRunnable);
            if (listener != null) {
                listener.onAudioProgress(mp.getDuration(), mp.getDuration());
                listener.onAudioStateChanged(false);
                listener.onAudioCompleted();
            }
        });

        mediaPlayer.setOnErrorListener((mp, what, extra) -> {
            progressHandler.removeCallbacks(progressRunnable);
            if (listener != null) {
                listener.onAudioError("Lỗi phát audio (mã: " + what + ")");
            }
            return true;
        });

        mediaPlayer.prepareAsync();
    }

    private void fallbackToTts() {
        isUsingTts = true;
        if (listener != null) {
            listener.onAudioPrepared(30000, "Giọng đọc mô phỏng (TTS)", true);
            listener.onAudioStateChanged(false);
        }
    }

    public void play() {
        if (isUsingTts) {
            playTts();
            return;
        }

        if (mediaPlayer != null) {
            try {
                mediaPlayer.start();
                progressHandler.post(progressRunnable);
                if (listener != null) {
                    listener.onAudioStateChanged(true);
                }
            } catch (Exception e) {
                Log.e(TAG, "Error playing media", e);
            }
        }
    }

    public void pause() {
        if (isUsingTts) {
            pauseTts();
            return;
        }

        if (mediaPlayer != null) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                progressHandler.removeCallbacks(progressRunnable);
                if (listener != null) {
                    listener.onAudioStateChanged(false);
                }
            } catch (Exception e) {
                Log.e(TAG, "Error pausing media", e);
            }
        }
    }

    public void togglePlayPause() {
        if (isPlaying()) {
            pause();
        } else {
            play();
        }
    }

    public void seekTo(int positionMs) {
        if (isUsingTts) return;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.seekTo(positionMs);
                if (listener != null) {
                    listener.onAudioProgress(positionMs, mediaPlayer.getDuration());
                }
            } catch (Exception ignored) {}
        }
    }

    public void seekRelative(int deltaMs) {
        if (isUsingTts) return;
        if (mediaPlayer != null) {
            try {
                int cur = mediaPlayer.getCurrentPosition();
                int dur = mediaPlayer.getDuration();
                int next = Math.max(0, Math.min(dur, cur + deltaMs));
                seekTo(next);
            } catch (Exception ignored) {}
        }
    }

    public void setPlaybackSpeed(float speed) {
        this.currentSpeed = speed;
        applySpeed();
    }

    private void applySpeed() {
        if (isUsingTts && tts != null) {
            tts.setSpeechRate(currentSpeed);
            return;
        }

        if (mediaPlayer != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                PlaybackParams params = mediaPlayer.getPlaybackParams();
                params.setSpeed(currentSpeed);
                mediaPlayer.setPlaybackParams(params);
            } catch (Exception ignored) {}
        }
    }

    public float getPlaybackSpeed() {
        return currentSpeed;
    }

    private void playTts() {
        if (tts != null && currentTask != null) {
            String script = currentTask.getScript();
            if (script == null || script.trim().isEmpty()) {
                script = currentTask.getTitle();
            }
            tts.setSpeechRate(currentSpeed);
            tts.speak(cleanScriptForTts(script), TextToSpeech.QUEUE_FLUSH, null, "choukai_utterance");
            isPlayingTts = true;
            if (listener != null) {
                listener.onAudioStateChanged(true);
            }
        }
    }

    private String cleanScriptForTts(String script) {
        // Strip line markers like "1. ", "例) ", "A: ", "B: ", "1) " for natural TTS flow
        return script.replaceAll("[A-Z]:", "")
                .replaceAll("例\\)", "")
                .replaceAll("\\d+\\)", "")
                .replaceAll("――+", "")
                .replaceAll("[★▲■●]", "");
    }

    private void pauseTts() {
        if (tts != null && isPlayingTts) {
            tts.stop();
            isPlayingTts = false;
            if (listener != null) {
                listener.onAudioStateChanged(false);
            }
        }
    }

    public boolean isPlaying() {
        if (isUsingTts) {
            return isPlayingTts;
        }
        if (mediaPlayer != null) {
            try {
                return mediaPlayer.isPlaying();
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean isUsingTts() {
        return isUsingTts;
    }

    public void stop() {
        progressHandler.removeCallbacks(progressRunnable);
        if (mediaPlayer != null) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
            } catch (Exception ignored) {}
        }
        if (tts != null && isPlayingTts) {
            tts.stop();
            isPlayingTts = false;
        }
        if (listener != null) {
            listener.onAudioStateChanged(false);
        }
    }

    public void release() {
        stop();
        releaseMediaPlayer();
        if (tts != null) {
            tts.shutdown();
            tts = null;
        }
        listener = null;
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.reset();
                mediaPlayer.release();
            } catch (Exception ignored) {}
            mediaPlayer = null;
        }
    }
}
