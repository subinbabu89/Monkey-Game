package self.subin.sdp.monkey;

/**
 * Inteface to pass settings status to the game panel
 * 
 * @author Subin
 *
 */
public interface SettingListener {

	/**
	 * Method to enable/disable showing of background
	 * 
	 * @param status
	 */
	void showBG(boolean status);

	/**
	 * Method to enable/disable showing of grid for the game
	 * 
	 * @param status
	 */
	void showGrid(boolean status);

	/**
	 * Method to enable/disable playing of audio
	 * 
	 * @param status
	 */
	void playAudio(boolean status);
}
