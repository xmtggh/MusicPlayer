package ggh.music.com.player;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ggh
 * @ClassName: Music
 * @Description: TODO 音乐实体类
 */
public class Music implements Parcelable {
    /**
     * 音乐的id
     */
    public String id;
    /**
     * 音乐的播放路径
     */
    public String path;
    /**
     * 音乐的标题
     */
    public String title;
    /**
     * 专辑名称
     */
    public String album;
    /**
     * 音乐艺术家
     */
    public String artist;
    /**
     * TODO
     * 任务时间长度
     */
    public long taskDuration;
    /**
     * 音乐的持续时间
     */
    public long duration;


    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            Music music = new Music();
            music.id = in.readString();
            music.path = in.readString();
            music.title = in.readString();
            music.album = in.readString();
            music.artist = in.readString();
            music.taskDuration = in.readLong();
            music.duration = in.readLong();
            return music;
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(long taskDuration) {
        this.taskDuration = taskDuration;
    }


    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(path);
        dest.writeString(title);
        dest.writeString(album);
        dest.writeString(artist);
        dest.writeLong(taskDuration);
        dest.writeLong(duration);
    }
}
