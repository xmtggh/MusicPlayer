package ggh.music.com.player;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.List;
import java.util.Random;

/**
* @ClassName: PlanList
* @Description: 播放器歌单
* @author Selpro xzwang008@gmail.com
* @date 2015年6月24日 下午5:13:31 
*/
public class PlayList implements Parcelable {
	private Music currentMusic;
	private List<Music> list = null;
	private Integer currentMode = Constant.MODE_LOOP;

	public PlayList() {
	}

	/**
	 * @param list
	 * 初始化歌单
	 */
	public PlayList(List<Music> list){
		this.setList(list);
	}

	protected PlayList(Parcel in) {
		currentMusic = in.readParcelable(Music.class.getClassLoader());
		list = in.createTypedArrayList(Music.CREATOR);
		if (in.readByte() == 0) {
			currentMode = null;
		} else {
			currentMode = in.readInt();
		}
	}

	public static final Creator<PlayList> CREATOR = new Creator<PlayList>() {
		@Override
		public PlayList createFromParcel(Parcel in) {
			return new PlayList(in);
		}

		@Override
		public PlayList[] newArray(int size) {
			return new PlayList[size];
		}
	};

	public void clear(){
		if(getList() != null){
			getList().clear();
		}
	}
	
	public int addMusic(Music music){
		if(getList() != null){
			int index = getList().indexOf(music);
			if(index == -1){
				getList().add(music);
				return getList().indexOf(music);
			}else{
				return index;
			}
		}
		return -1;
	}
	
	public void setCurrentMusic(Music music){
		if(getList().contains(music)){
			currentMusic = music;
		}
	}
	
	public void addMusic(List<Music> data){
		if(data != null){
			if(getList() != null){
				getList().addAll(data);
			}else{
				setList(data);
			}
		}
	}
	
	public Music getMusic(int position){
		Music music = null;
		if(getList().size() > position){
			music = getList().get(position);
		}
		return music;
	}
	
	
	public Music getPreSong() {
		Music music = null;
		if(getList() == null || getList().size() == 0){
			return null;
		}
		synchronized (currentMode) {
			//区分模式
			switch (currentMode) {
			case Constant.MODE_RANDOM:
				int size = getList().size();
				if(size == 1){
					music = currentMusic;
				}else{
					music = getRandomMusic(currentMusic, size);
				}
				break;
			default:
				int index = getList().indexOf(currentMusic);

				if(index < 0){
					music = getList().get(0);
				}else if(index == 0){
					music = getList().get(getList().size() -1);
				}else{
					index--;
				Log.e("ggh",index+"     currentMusic");
					music = getList().get(index);
				}
				break;
			}
			return music;
		}
	}
	
	
	public Music getNextSong(){
		Music music = null;
		if(getList() == null || getList().size() == 0){
			return null;
		}
		
		synchronized (currentMode) {
			//区分模式
			switch (currentMode) {
			case Constant.MODE_RANDOM:
				int size = getList().size();
				if(size == 1){
					music = currentMusic;
				}else{
					music = getRandomMusic(currentMusic, size);
				}
				break;
			default:
				int index = getList().indexOf(currentMusic);
				if(index == getList().size() -1){
					music = getList().get(0);
				}else if(index == -1){
					music = getList().get(0);
				}else{
					index++;
					music = getList().get(index);
				}
				break;
			}
			return music;
		}
	}
	
	/**
	 * 播放一首完结后调用,返回下一首音乐
	 * @return
	 */
	public Music onComplete(){
		Music music = null;
		
		if(getList() == null || getList().size() == 0){
			return null;
		}
		
		synchronized (currentMode) {
			//区分模式
			switch (currentMode) {
			case Constant.MODE_NEXT:
				int index = getList().indexOf(currentMusic);
				if(index == getList().size() -1){
					music = getList().get(0);
				}else{
					index++;
					music = getList().get(index);
				}
				break;
			case Constant.MODE_RANDOM:
				int size = getList().size();
				if(size == 1){
					music = currentMusic;
				}else{
					music = getRandomMusic(currentMusic, size);
				}
				break;
			case Constant.MODE_ONCE:
				//不返回
				break;
			case Constant.MODE_LOOP:
				music = currentMusic;
				break;
			default:
				break;
			}
			return music;
		}
	}

	private Music getRandomMusic(Music curent, int size) {
		Music music = null;
		Random random = new Random();
		if(curent == null){
			music = getList().get(random.nextInt(size));
		}
		return music;
	}

	public Music getCurrentMusic() {
		if(currentMusic == null && getList().size() > 0){
			currentMusic = getList().get(0);
		}
		return currentMusic;
	}

	public int getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(int currentMode) {
		synchronized (this.currentMode){
			this.currentMode = currentMode;
		}
	}

	public List<Music> getList() {
		return list;
	}

	public void setList(List<Music> list) {
		this.list = list;
	}

	public boolean isLoop() {
		return currentMode == Constant.MODE_LOOP;
	}

	public int size() {
		return list.size();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(currentMusic, flags);
		dest.writeTypedList(list);
		if (currentMode == null) {
			dest.writeByte((byte) 0);
		} else {
			dest.writeByte((byte) 1);
			dest.writeInt(currentMode);
		}
	}
}
