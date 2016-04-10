package self.subin.sdp.monkey.state;

import self.subin.sdp.monkey.entity.Monkey;

/**
 * Class to handle the different events for specific states
 * 
 * @author Subin
 *
 */
public class MonkeyState {

	/**
	 * Method to handle the up arrow key
	 */
	public MonkeyState onKeyUp(Monkey monkey){
		return this;
	}

	/**
	 * Method to handle the down arrow key
	 */
	public MonkeyState onKeyDown(Monkey monkey){
		return this;
	}
	/**
	 * Method to handle the left arrow key
	 */
	public MonkeyState onKeyLeft(Monkey monkey){
		return this;
	}
	/**
	 * Method to handle the right arrow key
	 */
	public MonkeyState onKeyRight(Monkey monkey){
		return this;
	}
	
	/**
	 * Method to handle the right arrow key
	 */
	public MonkeyState onKeyReleased(Monkey monkey){
		return this;
	}
}
