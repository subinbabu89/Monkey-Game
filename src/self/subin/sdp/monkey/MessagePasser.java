package self.subin.sdp.monkey;

/**
 * Interface to implement passing variables between classes
 * 
 * @author Subin
 *
 */
public interface MessagePasser {

	/**
	 * Method used to indicate that the banana was eaten
	 * 
	 */
	void bananaEaten();

	/**
	 * Method used to indicate that the banana was missed
	 */
	void bananaMissed();

	/**
	 * Method used to pass the current time for the banana from the game panel
	 * to the application window
	 * 
	 * @param currentTime
	 *            time remaining for the current banana
	 */
	void sendCurrentTimer(int currentTime);

	/**
	 * Method used to pass the total time for the game to the application window
	 * 
	 * @param totalTime
	 *            time remaining for the game
	 */
	void sendTotalTimer(int totalTime);

}
