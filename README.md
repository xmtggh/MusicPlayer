# MusicPlayer
一款基于vlc（2.0+）封装的音乐播放器，方便于以后搬砖使用，尽量简化于播放器的逻辑处理，让开发者专注于接口调用和业务实现
##使用预知
项目中依赖player（暂时无提供maven url）


##使用方法
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

### 最后
记得添加网络权限哦
```html
    <uses-permission android:name="android.permission.INTERNET" />

```

### 版本信息

1.0.1（预计12月）
1.0.0（已完成2017.11.16）

### 版本内容
#### 1.0.0
初次提交，播放器可设置在service或者activity中运行，具有播放，暂停，上一首，下一首，开始，重置，获取播放进度等功能

#### 1.0.1(尚未完成)
添加可拖动api 设置音量，添加播放模式（随机，顺序，单曲播放等）

### 闭幕
欢迎大家来fork和点赞，若是有bug请随意提起，欢迎各种商用或者demo，免费纯净版