package self.subin.sdp.monkey.state;

import self.subin.sdp.monkey.Constants;
import self.subin.sdp.monkey.entity.Monkey;

/**
 * State to denote that the monkey is currently idle
 * 
 * @author Subin
 *
 */
public class MonkeyMovingState extends MonkeyState {

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.snake.state.MonkeyState#onKeyUp()
	 */
	@Override
	public MonkeyState onKeyUp(Monkey monkey) {
		if (!(monkey.getPlayerDetails().getyCoor() == 0)) {
			monkey.getPlayerDetails()
					.setyCoor(monkey.getPlayerDetails().getyCoor() - (Constants.HEIGHT / Constants.NO_OF_BLOCKS));
		}
		return monkey.getMonkeyState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.snake.state.MonkeyState#onKeyDown()
	 */
	@Override
	public MonkeyState onKeyDown(Monkey monkey) {
		if (!(monkey.getPlayerDetails().getyCoor() == Constants.HEIGHT - (Constants.HEIGHT / Constants.NO_OF_BLOCKS))) {
			monkey.getPlayerDetails()
					.setyCoor(monkey.getPlayerDetails().getyCoor() + (Constants.HEIGHT / Constants.NO_OF_BLOCKS));
		}
		return monkey.getMonkeyState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.snake.state.MonkeyState#onKeyLeft()
	 */
	@Override
	public MonkeyState onKeyLeft(Monkey monkey) {
		if (!(monkey.getPlayerDetails().getxCoor() == 0)) {
			monkey.getPlayerDetails()
					.setxCoor(monkey.getPlayerDetails().getxCoor() - (Constants.WIDTH / Constants.NO_OF_BLOCKS));
		}
		return monkey.getMonkeyState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.snake.state.MonkeyState#onKeyRight()
	 */
	@Override
	public MonkeyState onKeyRight(Monkey monkey) {
		if (!(monkey.getPlayerDetails().getxCoor() == Constants.WIDTH - (Constants.WIDTH / Constants.NO_OF_BLOCKS))) {
			monkey.getPlayerDetails()
					.setxCoor(monkey.getPlayerDetails().getxCoor() + (Constants.WIDTH / Constants.NO_OF_BLOCKS));
		}
		return monkey.getMonkeyState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * self.subin.sdp.monkey.state.MonkeyState#onKeyReleased(self.subin.sdp.
	 * monkey.entity.Monkey)
	 */
	@Override
	public MonkeyState onKeyReleased(Monkey monkey) {
		monkey.setMonkeyState(monkey.getMonkeyIdleState());
		return monkey.getMonkeyState();
	}

}
