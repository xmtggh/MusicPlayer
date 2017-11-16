package ggh.music.com.player;
/**
 * @author ggh
 */
public class Constant {
	/**
	 * 顺序循环模式
	 */
	public static final int MODE_NEXT = 0;
	/**
	 * 默认只播一首
	 */
	public static final int MODE_ONCE = 1;
	/**
	 * 随机模式
	 */
	public static final int MODE_RANDOM = 2;
	/** 
	* @Fields MODE_LOOP : TODO 单曲循环
	*/ 
	public static final int MODE_LOOP = 3;
	/**
	 * 不可用状态
	 */
	public static final int STATE_DISABLE = -1;
	/**
	 * 空闲状态
	 */
	public static final int STATE_IDLE = 0;
	/**
	 * 播放状态
	 */
	public static final int STATE_PLAYING = 1;
	/**
	 * 暂停状态
	 */
	public static final int STATE_PAUSED = 2;
	/**
	 * 错误状态
	 */
	public static final int STATE_ERROR = 3;
	/** 
	* 播放器准备中
	*/ 
	public static final int STATE_Loading = 4;
	/** 
	* 更新播放进度
	*/ 
	public static final int STATE_UPDATE_PROGRESS = 5;
	/**
	 * 播放器销毁
	 */
	public static final int STATE_DESTORY = 6;
	


}
