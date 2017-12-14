package com.ggh.music;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.util.ArrayList;
import java.util.List;

import ggh.music.com.player.Music;
import ggh.music.com.player.PlayList;
import ggh.music.com.player.Player;

/**
 * @author ZQZN
 */
public class MainActivity extends AppCompatActivity {
    Player mPlayer;
    SeekBar pro;
    SeekBar voice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initMusic();
        progreeSet();

    }

    private void progreeSet() {
        voice.setMax(Player.MAXVOLUME);

    }

    private void initView() {
        pro = (SeekBar)findViewById(R.id.seek_pro);
        voice = (SeekBar)findViewById(R.id.seek_voice);
        voice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("ggh","voice  " + seekBar.getProgress());
                Log.d("ggh","voicecrrent  " + mPlayer.getVolume());
                mPlayer.setVolume(seekBar.getProgress());

            }
        });
        pro.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                Log.d("ggh","onProgressChanged");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                Log.d("ggh","onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                Log.d("ggh","onStopTrackingTouch");
                mPlayer.setProgress((int)seekBar.getProgress());

            }
        });
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.start();
            }
        });
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.reset();
            }
        });
        findViewById(R.id.pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.pause();
            }
        });
        findViewById(R.id.pre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.preSong();

            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.nextSong();

            }
        });
        findViewById(R.id.random).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.randomModel();

            }
        });
        findViewById(R.id.sequence).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.sequenceModel();

            }
        });
        findViewById(R.id.singelmodel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.loopModel();

            }
        });
        findViewById(R.id.single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Music music = new Music();
                music.setId("asdasd");
                music.setPath("http://m10.music.126.net/20171214195654/ad472d2db8ad7867e88a7fe6da93b7d8/ymusic/559c/7b24/b98a/c753aac0ddc35b9e419e97d2403e6372.mp3");
                music.setTitle("歌曲一");
                mPlayer.musicPlayer(music);

            }
        });
    }

    private void initMusic() {
        Music music = new Music();
        music.setId("asdasd");
        music.setPath("http://m10.music.126.net/20171214195654/ad472d2db8ad7867e88a7fe6da93b7d8/ymusic/559c/7b24/b98a/c753aac0ddc35b9e419e97d2403e6372.mp3");
        music.setTitle("歌曲一");

        Music music1 = new Music();
        music1.setId("fhdfg");
        music1.setPath("http://m10.music.126.net/20171214195824/4751d49b2822f7774de939a10ddd4bb2/ymusic/8244/a988/215f/6a51cda0bf0f925c342ba7678c09c557.mp3");
        music1.setTitle("歌曲二");
        Music music2 = new Music();
        music2.setId("asvbnvbndasd");
        music2.setPath("http://m10.music.126.net/20171214224557/bf4b1492ac4a04d2db7b6bb97a597166/ymusic/9c52/b25b/d654/79dc67d73ad69bcf79bf82cbda1254c7.mp3");
        music2.setTitle("歌曲三");

        List<Music> mList = new ArrayList<>();
        mList.add(music);
        mList.add(music1);
        mList.add(music2);
        PlayList mPlayList = new PlayList(mList);
        mPlayer = new Player.Buidler()
                .setAutoPlay(true)
                .setContext(MainActivity.this)
                .setPlayList(mPlayList)
                .setEventListener(new Player.PlayerEventListener() {
                    @Override
                    public void onEvent(MediaPlayer.Event event) {
                        switch (event.type) {
                            case MediaPlayer.Event.EndReached:
                                Log.d("music", "播放完毕");
                                break;
                            case MediaPlayer.Event.Playing:
                                Log.d("music", "正在播放");
                                break;
                            case MediaPlayer.Event.Paused:
                                Log.d("music", "播放暂停");
                                break;
                            case MediaPlayer.Event.Stopped:
                                Log.d("music", "播放停止");
                                break;
                            case MediaPlayer.Event.EncounteredError:
                                Log.d("music", "播放异常");
                                break;
                            case MediaPlayer.Event.PositionChanged:
                                pro.setMax((int) mPlayer.getMaxPostion());
                                pro.setProgress((int) mPlayer.getCurrentPostion());
                                break;
                            default:
                                break;
                        }
                    }
                })
                .create();
    }
}
