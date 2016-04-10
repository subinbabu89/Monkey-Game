package self.subin.sdp.monkey;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import self.subin.sdp.monkey.graphics.WonderLand;

/**
 * The main application window for the game
 * 
 * @author Subin
 *
 */
public class MonkeyGame extends JFrame implements MessagePasser, ItemListener {

	/**
	 * Serializable id for the current class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * label used to display the score status
	 */
	private JLabel score;

	/**
	 * menu bar object for the application
	 */
	private JMenuBar menuBar;

	/**
	 * menu for the application
	 */
	private JMenu menu;

	/**
	 * checkbox to enable-disable grid
	 */
	private JCheckBoxMenuItem cbMenuItemGrid;
	/**
	 * checkbox to enable-disable background
	 */
	private JCheckBoxMenuItem cbMenuItemBG;
	/**
	 * checkbox to enable-disable audio
	 */
	private JCheckBoxMenuItem cbMenuItemAudio;

	/**
	 * menu item to start new game
	 */
	private JMenuItem newGame;
	/**
	 * menu item to exit the game
	 */
	private JMenuItem exitGame;

	/**
	 * menu item to initialize the setting submenu
	 */
	private JMenu settingSubmenu;

	/**
	 * current timer object for the banana
	 */
	private int current_timer;
	/**
	 * total timer for the game
	 */
	private int total_timer;
	/**
	 * current number of bananas to be eaten
	 */
	private int bananas = Constants.TOTAL_BANANAS;

	/**
	 * Screen object with the game to be played
	 */
	private WonderLand screen;

	/**
	 * Screen object for the game over screen
	 */
	private JPanel gameOverPanel;

	/**
	 * variable to count the number of bananas eaten
	 */
	private int bananas_eaten;

	/**
	 * Constructor to initialize the Frame with
	 * 
	 * @throws HeadlessException
	 */
	public MonkeyGame() throws HeadlessException {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(Boolean.FALSE);

		initUIElements();
		init();
		keyEvents();
	}

	/**
	 * Method to initialize the application window
	 */
	private void init() {
		// Create the menu bar.

		setLayout(new BorderLayout(0, 0));

		screen = new WonderLand(this);
		add(screen, BorderLayout.CENTER);

		score = new JLabel("New label");
		add(score, BorderLayout.SOUTH);

		pack();

		setLocationRelativeTo(null);
		setVisible(Boolean.TRUE);
	}

	/**
	 * Method used to initialize the view elements in the application window
	 */
	private void initUIElements() {
		menuBar = new JMenuBar();

		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);

		newGame = new JMenuItem("New Game", KeyEvent.VK_N);
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		newGame.getAccessibleContext().setAccessibleDescription("Starts a new Game");

		menu.add(newGame);

		menu.addSeparator();

		settingSubmenu = new JMenu("Setting");
		settingSubmenu.setMnemonic(KeyEvent.VK_S);

		cbMenuItemGrid = new JCheckBoxMenuItem("Enable Grid");
		cbMenuItemGrid.setMnemonic(KeyEvent.VK_G);
		settingSubmenu.add(cbMenuItemGrid);

		cbMenuItemBG = new JCheckBoxMenuItem("Enable Background");
		cbMenuItemBG.setMnemonic(KeyEvent.VK_B);
		settingSubmenu.add(cbMenuItemBG);

		cbMenuItemAudio = new JCheckBoxMenuItem("Enable Audio");
		cbMenuItemAudio.setMnemonic(KeyEvent.VK_A);
		settingSubmenu.add(cbMenuItemAudio);

		menu.add(settingSubmenu);

		menu.addSeparator();

		exitGame = new JMenuItem("Exit Game", KeyEvent.VK_X);
		exitGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
		exitGame.getAccessibleContext().setAccessibleDescription("Exits the Game");
		menu.add(exitGame);

		setJMenuBar(menuBar);
	}

	/**
	 * Method used to handle the click events for the various views in the
	 * window
	 */
	private void keyEvents() {
		cbMenuItemGrid.addItemListener(this);
		cbMenuItemBG.addItemListener(this);
		cbMenuItemAudio.addItemListener(this);

		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MonkeyGame.this.setVisible(false);
				MonkeyGame.this.dispose();
				new MonkeyGame();
			}
		});

		exitGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * Main method to start the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MonkeyGame();
	}

	/**
	 * Method used to display the score on the application window
	 */
	private void showScore() {
		StringBuilder scoreStringBuilder = new StringBuilder();
		scoreStringBuilder.append("Current Timer : ");
		scoreStringBuilder.append(current_timer);
		scoreStringBuilder.append(" Total Timer : ");
		scoreStringBuilder.append(total_timer);
		scoreStringBuilder.append(" Remaining Bananas : ");
		scoreStringBuilder.append(bananas);
		score.setText(scoreStringBuilder.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.monkey.MessagePasser#sendScore(int)
	 */
	@Override
	public void bananaEaten() {
		this.bananas--;
		bananas_eaten++;
		showScore();
		if (bananas == 0) {
			gameOver();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.monkey.MessagePasser#bananaMissed()
	 */
	@Override
	public void bananaMissed() {
		this.bananas--;
		showScore();
		if (bananas == 0) {
			gameOver();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.monkey.MessagePasser#sendCurrentTimer(int)
	 */
	@Override
	public void sendCurrentTimer(int currentTime) {
		this.current_timer = currentTime;
		showScore();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.monkey.MessagePasser#sendTotalTimer(int)
	 */
	@Override
	public void sendTotalTimer(int totalTime) {
		this.total_timer = totalTime;
		showScore();
		if (this.total_timer == 0) {
			gameOver();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getItemSelectable().equals(cbMenuItemGrid)) {
			if (event.getStateChange() == ItemEvent.SELECTED)
				screen.showGrid(Boolean.TRUE);
			if (event.getStateChange() == ItemEvent.DESELECTED)
				screen.showGrid(Boolean.FALSE);
		}

		else if (event.getItemSelectable().equals(cbMenuItemBG)) {
			if (event.getStateChange() == ItemEvent.SELECTED)
				screen.showBG(Boolean.TRUE);
			if (event.getStateChange() == ItemEvent.DESELECTED)
				screen.showBG(Boolean.FALSE);
		}

		else if (event.getItemSelectable().equals(cbMenuItemAudio)) {
			if (event.getStateChange() == ItemEvent.SELECTED)
				screen.playAudio(Boolean.TRUE);
			if (event.getStateChange() == ItemEvent.DESELECTED)
				screen.playAudio(Boolean.FALSE);
		}
	}

	/**
	 * Method event to call when the game finishes
	 */
	private void gameOver() {
		gameOverPanel = new JPanel();

		JLabel gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
		gameOverLabel.setFont(new Font("Verdana", 1, 35));

		JLabel newGameInfoLabel = new JLabel("To start a new game, click on the New Game option in the menu",
				SwingConstants.CENTER);
		newGameInfoLabel.setFont(new Font("Verdana", 1, 15));

		JLabel scoreDetailLabel = new JLabel(
				"You ate " + bananas_eaten + " bananas in " + (Constants.TOTAL_TIMER - total_timer) + " secs",
				SwingConstants.CENTER);
		scoreDetailLabel.setFont(new Font("Verdana", 1, 15));

		remove(screen);
		gameOverLabel.setVerticalAlignment(SwingConstants.CENTER);
		newGameInfoLabel.setVerticalAlignment(SwingConstants.CENTER);
		scoreDetailLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		gameOverPanel.add(scoreDetailLabel, SwingConstants.CENTER);
		gameOverPanel.add(newGameInfoLabel, SwingConstants.CENTER);
		gameOverPanel.add(gameOverLabel, SwingConstants.CENTER);
		add(gameOverPanel, BorderLayout.CENTER);
		screen.stopGame();
	}
}
