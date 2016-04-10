package self.subin.sdp.monkey.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import self.subin.sdp.monkey.Constants;
import self.subin.sdp.monkey.GameListener;
import self.subin.sdp.monkey.MessagePasser;
import self.subin.sdp.monkey.SettingListener;
import self.subin.sdp.monkey.entity.Banana;
import self.subin.sdp.monkey.entity.Monkey;
import self.subin.sdp.monkey.entity.PlayerDetails;

/**
 * Class used to initial the game window for the game
 * 
 * @author Subin
 *
 */
public class WonderLand extends JPanel implements Runnable, KeyListener, SettingListener, GameListener {

	/**
	 * Serializable id for the current class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the thread for running the app with the counter
	 */
	private Thread runThread;

	/**
	 * variable to check whether to run the thread
	 */
	private boolean isRunning;

	/**
	 * randomizer variable
	 */
	private Random random = new Random();

	/**
	 * the object to initialize the player details for the monkey in the game
	 */
	private PlayerDetails player;

	/**
	 * the banana object for the game
	 */
	private Banana banana;

	/**
	 * the monkey object for the game
	 */
	private Monkey monkey;

	/**
	 * the image object for the background of the application
	 */
	private Image bgImage;

	/**
	 * boolean to enable/disable the grid for the game
	 */
	private boolean showGrid = Constants.SHOWGRID;
	/**
	 * boolean to enable/disable the background for the game
	 */
	private boolean showBG = Constants.SHOWBG;
	/**
	 * boolean to enable/disable the audio for the game
	 */
	private boolean playAudio = Constants.PLAYAUDIO;

	/**
	 * interface object to pass message from the game window to the application
	 * window
	 */
	private MessagePasser messagePasser;

	/**
	 * local object for the total bananas in the game
	 */
	private int bananas = Constants.TOTAL_BANANAS;
	/**
	 * local object for the current time for the banana in the game
	 */
	private int current_timer = Constants.CURRENT_TIMER;
	/**
	 * local object for the total timer in the game
	 */
	private int total_timer = Constants.TOTAL_TIMER;

	/**
	 * Constructor to be initialized with the messagePasser object
	 */
	public WonderLand(MessagePasser messagePasser) {
		super();

		this.messagePasser = messagePasser;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int current_dimenstion = (int) (Math.min(screenSize.getWidth(), screenSize.getHeight()) - 220);
		Constants.WIDTH = Constants.HEIGHT = current_dimenstion;
		setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		addKeyListener(this);
		try {
			bgImage = ImageIO.read(getClass().getResource("background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setFocusable(Boolean.TRUE);
		start();
	}

	/**
	 * Method to start the game
	 */
	private void start() {
		isRunning = Boolean.TRUE;
		if (runThread == null) {
			runThread = new Thread(this);
			runThread.start();
		}

		generatePlayer();
		generateBanana();
	}

	/**
	 * Method to stop the game
	 */
	private void stop() {
		try {
			isRunning = Boolean.FALSE;
			runThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to check the collision of monkey and the banana
	 */
	private void checkCollision() {
		if (banana.getxCoor() == player.getxCoor() && banana.getyCoor() == player.getyCoor()) {
			if (playAudio) {
				playSound("banana.wav");
			}
			generateBanana();
			current_timer = Constants.CURRENT_TIMER;
			bananas--;
			messagePasser.bananaEaten();

			checkGameWon();
		}
	}

	/**
	 * Method to check if the game is won
	 */
	private void checkGameWon() {
		if (bananas == 0) {
			isRunning = Boolean.FALSE;
			stop();
		}
	}

	/**
	 * Method used to draw the grid on the game window
	 * 
	 * @param graphics
	 *            Graphics object to draw the grid with
	 */
	private void drawGrid(Graphics graphics) {
		graphics.setColor(Color.BLACK);

		/**
		 * to draw the horizontal lines
		 */
		for (int i = 1; i <= Constants.NO_OF_BLOCKS; i++) {
			graphics.drawLine(0, Constants.HEIGHT / Constants.NO_OF_BLOCKS * i, Constants.WIDTH,
					Constants.HEIGHT / Constants.NO_OF_BLOCKS * i);
		}

		/**
		 * to draw the vertical lines
		 */
		for (int i = 1; i <= Constants.NO_OF_BLOCKS; i++) {
			graphics.drawLine(Constants.WIDTH / Constants.NO_OF_BLOCKS * i, 0,
					Constants.WIDTH / Constants.NO_OF_BLOCKS * i, Constants.HEIGHT);
		}
	}

	/**
	 * Method used to draw the player on the game window
	 * 
	 * @param graphics
	 *            Graphics object to draw the player with
	 */
	private void drawPlayer(Graphics graphics) {
		player.draw(graphics);
	}

	/**
	 * Method to generate the player and randomly place him on the game grid
	 */
	private void generatePlayer() {
		int playerXcoor = random.nextInt(Constants.NO_OF_BLOCKS) * (Constants.WIDTH / Constants.NO_OF_BLOCKS);
		int playerYcoor = random.nextInt(Constants.NO_OF_BLOCKS) * (Constants.HEIGHT / Constants.NO_OF_BLOCKS);

		player = new PlayerDetails(playerXcoor, playerYcoor, Constants.WIDTH / Constants.NO_OF_BLOCKS,
				Constants.HEIGHT / Constants.NO_OF_BLOCKS);
		this.monkey = new Monkey(player);
	}

	/**
	 * Method used to draw the banana on the game window
	 * 
	 * @param graphics
	 *            Graphics object to draw the banana with
	 */
	private void drawBanana(Graphics graphics) {
		banana.draw(graphics);
	}

	/**
	 * Method to generate the banana and randomly place him on the game grid
	 */
	private void generateBanana() {
		int bananaXcoor = random.nextInt(Constants.NO_OF_BLOCKS) * (Constants.WIDTH / Constants.NO_OF_BLOCKS);
		int bananaYcoor = random.nextInt(Constants.NO_OF_BLOCKS) * (Constants.HEIGHT / Constants.NO_OF_BLOCKS);

		if(bananaXcoor == monkey.getPlayerDetails().getxCoor() && bananaYcoor == monkey.getPlayerDetails().getyCoor()){
			bananaXcoor = random.nextInt(Constants.NO_OF_BLOCKS) * (Constants.WIDTH / Constants.NO_OF_BLOCKS);
		}
		
		banana = new Banana(bananaXcoor, bananaYcoor, Constants.WIDTH / Constants.NO_OF_BLOCKS,
				Constants.HEIGHT / Constants.NO_OF_BLOCKS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (isRunning) {
			try {
				Thread.currentThread();
				Thread.sleep(1000);
				messagePasser.sendCurrentTimer(--current_timer);
				messagePasser.sendTotalTimer(--total_timer);
				if (current_timer == 0) {
					messagePasser.bananaMissed();
					generateBanana();
					repaint();
					current_timer = Constants.CURRENT_TIMER;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (showGrid)
			drawGrid(g);
		drawBanana(g);
		drawPlayer(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (showBG)
			g.drawImage(bgImage, 0, 0, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			monkey.onKeyUp();
			break;
		case KeyEvent.VK_DOWN:
			monkey.onKeyDown();
			break;
		case KeyEvent.VK_LEFT:
			monkey.onKeyLeft();
			break;
		case KeyEvent.VK_RIGHT:
			monkey.onKeyRight();
			break;
		default:
			break;
		}
		repaint();
		checkCollision();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		monkey.onKeyReleased();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.monkey.SettingListener#showBG(boolean)
	 */
	@Override
	public void showBG(boolean status) {
		showBG = status;
		repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.monkey.SettingListener#showGrid(boolean)
	 */
	@Override
	public void showGrid(boolean status) {
		showGrid = status;
		repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.monkey.SettingListener#playAudio(boolean)
	 */
	@Override
	public void playAudio(boolean status) {
		playAudio = status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	@Override
	public void finalize() throws Throwable {
		super.finalize();
		isRunning = Boolean.FALSE;
		runThread.join();
		bananas = Constants.TOTAL_BANANAS;
	}

	/**
	 * Method used to play sound
	 * 
	 * @param url
	 *            URL to the file to be played
	 */
	public static synchronized void playSound(final String url) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem
							.getAudioInputStream(WonderLand.class.getResourceAsStream(url));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see self.subin.sdp.monkey.GameListener#restartGame()
	 */
	@Override
	public void stopGame() {
		isRunning = Boolean.FALSE;
	}
}
