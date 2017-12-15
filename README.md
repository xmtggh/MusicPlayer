# MusicPlayer
一款基于vlc（2.0+）封装的音乐播放器，方便于以后搬砖使用，尽量简化于播放器的逻辑处理，让开发者专注于接口调用和业务实现
博客地址<http://blog.csdn.net/qfgggh/article/details/78817653>

## 使用方法
### 1.根目录下的build.gradle添加
```html
    maven { url "https://jitpack.io"}
```
```html
    allprojects {
        repositories {
            google()
            jcenter()
            maven { url "https://jitpack.io"}
    
        }
    }
```

### 2. 使用的model中添加

```html
    compile 'com.github.xmtggh:musicPlayer:1.0.1'
```

### 初始化示例
```java
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
```

### 初始化api

#### setAutoPlayer（true：自动播放，false：手动播放）

是否自动播放，即无需调用play方法，初始化完成后直接播放

#### setContext

获取当前上下问对象

#### setPlayList

初始化播放列表

#### setEventListener
播放器时间监听方法，示例代码已包含其意义！

### 操作api介绍
包括一些基本的操作，上一首，下一首，暂停，开始，重置，播放单曲！

#### mPlayer.start();

开始播放

#### mPlayer.reset();

重置当前播放歌曲

#### mPlayer.pause();

暂停播放
#### mPlayer.nextSong();

播放下一首，当前只能在有播放列表的情况下使用
#### mPlayer.preSong();

播放上一首
#### mPlayer.getMaxPostion()

获取当前歌曲最大进度long类型

#### mPlayer.getCurrentPostion()

获取当前播放进度

#### mPlayer.setProgress(long progress)

设置播放进度

#### mPlayer.setVolume(int progress)

设置播放音量大小，目前最大值为300 可根据需求动态调整

#### mPlayer.randomModel()

设置为随机播放

#### mPlayer.sequenceModel()

设置为顺序播放

#### mPlayer.loopModel()

设置为单曲循环

### 最后
记得添加网络权限哦
```html
    <uses-permission android:name="android.permission.INTERNET" />

```

### 版本信息

1.0.2（尚未规划（解决bug为主））
1.0.1（已完成2017.12.14）
1.0.0（已完成2017.11.16）

### 版本内容
#### 1.0.0
初次提交，播放器可设置在service或者activity中运行，具有播放，暂停，上一首，下一首，开始，重置，获取播放进度等功能

#### 1.0.1
添加可拖动进度api 设置音量大小，添加播放模式（随机，顺序，单曲播放等）

#### 1.0.2
尚未完成，目前规划修复一些未知的bug
### 闭幕
欢迎大家来fork和点赞，若是有bug请随意提起，欢迎各种商用或者demo，免费纯净版