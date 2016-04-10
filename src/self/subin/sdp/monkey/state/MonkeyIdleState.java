package self.subin.sdp.monkey.state;

import self.subin.sdp.monkey.entity.Monkey;

/**
 * State to denote that the monkey is currently idle
 * 
 * @author Subin
 *
 */
public class MonkeyIdleState extends MonkeyState {

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.snake.state.MonkeyState#onKeyUp()
	 */
	@Override
	public MonkeyState onKeyUp(Monkey monkey) {
		monkey.setMonkeyState(monkey.getMonkeyMovingState());
		monkey.onKeyUp();
		return monkey.getMonkeyState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.snake.state.MonkeyState#onKeyDown()
	 */
	@Override
	public MonkeyState onKeyDown(Monkey monkey) {
		monkey.setMonkeyState(monkey.getMonkeyMovingState());
		monkey.onKeyDown();
		return monkey.getMonkeyState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.snake.state.MonkeyState#onKeyLeft()
	 */
	@Override
	public MonkeyState onKeyLeft(Monkey monkey) {
		monkey.setMonkeyState(monkey.getMonkeyMovingState());
		monkey.onKeyLeft();
		return monkey.getMonkeyState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.snake.state.MonkeyState#onKeyRight()
	 */
	@Override
	public MonkeyState onKeyRight(Monkey monkey) {
		monkey.setMonkeyState(monkey.getMonkeyMovingState());
		monkey.onKeyRight();
		return monkey.getMonkeyState();
	}

}
