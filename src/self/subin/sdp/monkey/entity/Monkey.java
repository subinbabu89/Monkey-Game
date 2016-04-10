package self.subin.sdp.monkey.entity;

import self.subin.sdp.monkey.state.MonkeyIdleState;
import self.subin.sdp.monkey.state.MonkeyMovingState;
import self.subin.sdp.monkey.state.MonkeyState;

/**
 * Class to hold the monkey object to react to its current state
 * 
 * @author Subin
 *
 */
public class Monkey {

	/**
	 * object to hold the position and size of the monkey
	 */
	private PlayerDetails playerDetails;

	/**
	 * the current state of the monkey
	 */
	private MonkeyState monkeyState;
	/**
	 * the state that the monkey is in when it is moving
	 */
	private MonkeyMovingState monkeyMovingState;
	/**
	 * the state that the monkey is in when it is idle
	 */
	private MonkeyIdleState monkeyIdleState;

	/**
	 * Constructor to initialize the object with the player details
	 * 
	 * @param playerDetails
	 *            PlayerDetails to initialize the Monkey with
	 */
	public Monkey(PlayerDetails playerDetails) {
		this.playerDetails = playerDetails;
		this.monkeyMovingState = new MonkeyMovingState();
		this.monkeyIdleState = new MonkeyIdleState();

		this.monkeyState = monkeyIdleState;
	}

	/**
	 * Getter for the current monkeyState
	 * 
	 * @return the monkeyState
	 */
	public MonkeyState getMonkeyState() {
		return monkeyState;
	}

	/**
	 * Setter for the current monkeyState
	 * 
	 * @param monkeyState
	 */
	public void setMonkeyState(MonkeyState monkeyState) {
		this.monkeyState = monkeyState;
	}

	/**
	 * Method to handle the up arrow key
	 */
	public void onKeyUp() {
		monkeyState.onKeyUp(this);
	}

	/**
	 * Method to handle the down arrow key
	 */
	public void onKeyDown() {
		monkeyState.onKeyDown(this);
	}

	/**
	 * Method to handle the left arrow key
	 */
	public void onKeyLeft() {
		monkeyState.onKeyLeft(this);
	}

	/**
	 * Method to handle the right arrow key
	 */
	public void onKeyRight() {
		monkeyState.onKeyRight(this);
	}
	
	/**
	 * Method to handle the key release
	 */
	public void onKeyReleased(){
		monkeyState.onKeyReleased(this);
	}

	/**
	 * Getter for the moving monkeyState
	 * 
	 * @return the monkeyMovingState
	 */
	public MonkeyMovingState getMonkeyMovingState() {
		return monkeyMovingState;
	}

	/**
	 * Getter for the idle monkeyState
	 * 
	 * @return the monkeyIdleState
	 */
	public MonkeyIdleState getMonkeyIdleState() {
		return monkeyIdleState;
	}

	/**
	 * Getter for the playerDetails for the monkey
	 * 
	 * @return the player
	 */
	public PlayerDetails getPlayerDetails() {
		return playerDetails;
	}
}
