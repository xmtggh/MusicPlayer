package com.ggh.music;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        findViewById(R.id.single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Music music = new Music();
                music.setId("asdasd");
                music.setPath("http://192.168.8.16:8280/terminalsystem/readFile?filepath=audio/CD32397A7DE548C9A4C2BAA4B1FC76D6.mp3");
                music.setTitle("夜空中最亮的星");
                mPlayer.musicPlayer(music);

            }
        });
        Music music = new Music();
        music.setId("asdasd");
        music.setPath("http://192.168.8.16:8280/terminalsystem/readFile?filepath=audio/CD32397A7DE548C9A4C2BAA4B1FC76D6.mp3");
        music.setTitle("夜空中最亮的星");
        List<Music> mList = new ArrayList<>();
        mList.add(music);
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
                                Log.d("music", "最大播放进度" + mPlayer.getMaxPostion());
                                Log.d("music", "当前播放进度" + mPlayer.getCurrentPostion());
                                break;
                            default:
                                break;
                        }
                    }
                })
                .create();

    }
}
