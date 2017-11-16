package ggh.music.com.player;

import android.app.Service;
import android.content.Context;
import android.content.EntityIterator;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;


import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import ggh.music.com.player.base.VLCInstance;

/**
 * @author ggh
 * @date 2017/11/8
 */

public class Player {
    private MediaPlayer mMediaPlayer;
    private LibVLC libvlc;
    private Context mCtx;
    private boolean isAutoPlay = false;

    /**
     * 音乐播放列表
     */
    private PlayList playList;
    /**
     * 播放器状态不可用
     */
    public static int mState = Constant.STATE_DISABLE;

    public interface PlayerEventListener extends MediaPlayer.EventListener {
    }


    public Player(Buidler buidler) {
        this.mCtx = buidler.context;
        this.isAutoPlay = buidler.isAutoPlay;
        this.playList = buidler.playList;
        init();
        setEventListener(buidler.eventListener);
        if (isAutoPlay) {
            playMusic();
        }
    }

    public synchronized void setEventListener(PlayerEventListener eventListener) {
        mMediaPlayer.setEventListener(eventListener);
    }

    /**
     * 播放音乐
     */
    public void playMusic() {
        //获取第一首歌曲并播放
        if (playList != null) {
            musicPlayer(playList.getMusic(0));
        } else {
            Log.d("Player", "播放失败，无法获取列表");
        }
    }

    /**
     * 初始化音乐播放器
     */
    private void init() {
        Log.d("Player", "初始化播放器");
        if (!VLCInstance.testCompatibleCPU(mCtx)) {
            Log.d("Player", "cpu不支持");
            return;
        }
        try {
            libvlc = VLCInstance.get(mCtx);
            mMediaPlayer = new MediaPlayer(libvlc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 播放音乐的核心方法
     *
     * @param currentMusic 需要播放的歌曲
     */
    public void musicPlayer(Music currentMusic) {
        Log.d("Player", "播放歌曲  " + currentMusic.getTitle());

        Media m = new Media(libvlc, Uri.parse(currentMusic.getPath()));
        mMediaPlayer.setMedia(m);
        mMediaPlayer.play();
        updatePlayerState(Constant.STATE_PLAYING);

    }

    /**
     * 销毁核心播放器
     */
    private void releasePlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

    }

    public void onDestroy() {
        Log.d("Player", "播放器销毁");
        releasePlayer();
    }

    /**
     * TODO 调整当前播放器的状态
     */
    public void updatePlayerState(int newState) {
        mState = newState;
        if (playList == null) {
            return;
        }
        broadcasetStateChange(mState, playList.getCurrentMusic());

    }

    /**
     * TODO 将状态变化 更新到不同的界面
     */
    public void broadcasetStateChange(int state, Music song) {
        if (state != -1) {

        }
    }

    /**
     * TODO 上一首
     */
    public void preSong() {
        Music music = playList.getPreSong();
        musicPlayer(music);
    }

    /**
     * TODO 下一首
     */
    public void nextSong() {
        Music music = playList.getNextSong();
        musicPlayer(music);
    }

    /**
     * TODO 开始播放
     */
    public void start() {
        if (mMediaPlayer != null) {
            mMediaPlayer.play();
        }
    }

    /**
     * TODO 暂停正在播放歌曲
     */
    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }

    /**
     * TODO 重置当前播放
     */
    public void reset() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
    }

    /**
     * 获取当前播放时间
     *
     * @return
     */
    public long getCurrentPostion() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getTime();
        } else {
            return -1L;
        }
    }

    /**
     * 获取最大的播放进度
     *
     * @return
     */
    public long getMaxPostion() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getLength();
        } else {
            return -1L;
        }
    }

    public static class Buidler {
        private Context context;
        /**
         * 是否自动播放
         */
        private boolean isAutoPlay = false;
        /**
         * 音乐播放列表
         */
        private PlayList playList;
        private PlayerEventListener eventListener;

        public Buidler setEventListener(PlayerEventListener eventListener) {
            this.eventListener = eventListener;
            return this;
        }

        public Buidler setContext(Context mCtx) {
            this.context = mCtx;
            return this;
        }

        public Buidler setAutoPlay(boolean autoPlay) {
            isAutoPlay = autoPlay;
            return this;
        }

        public Buidler setPlayList(PlayList playList) {
            this.playList = playList;
            return this;
        }

        public Player create() {
            if (context == null) {
                throw new IllegalStateException("mCtx == null");
            }
            return new Player(this);
        }
    }
}
