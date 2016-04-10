package self.subin.sdp.monkey.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Object class to hold the instance of the player object along with the method
 * used to draw the player(monkey)
 * 
 * @author Subin
 *
 */
public class PlayerDetails {

	/**
	 * co-ordinates for the player along with the dimension
	 */
	private int xCoor, yCoor, width, height;

	/**
	 * Constructor to initialize the Monkey object for the game
	 * 
	 * @param xCoor
	 *            the starting x co-ordinate to start drawing the object
	 * @param yCoor
	 *            the starting y co-ordinate to start drawing the object
	 * @param width
	 *            the width of the monkey object
	 * @param height
	 *            the height of the monkey object
	 */
	public PlayerDetails(int xCoor, int yCoor, int width, int height) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.width = width;
		this.height = height;
	}

	/**
	 * Getter for the x co-ordinate of the monkey
	 * 
	 * @return the xCoor
	 */
	public int getxCoor() {
		return xCoor;
	}

	/**
	 * Setter for the x co-ordinate of the monkey
	 * 
	 * @param xCoor
	 *            the xCoor to set
	 */
	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	/**
	 * Getter for the y co-ordinate of the monkey
	 * 
	 * @return the yCoor
	 */
	public int getyCoor() {
		return yCoor;
	}

	/**
	 * Setter for the y co-ordinate of the monkey
	 * 
	 * @param yCoor
	 *            the yCoor to set
	 */
	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

	/**
	 * Getter for the width of the banana
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Setter for the width of the banana
	 * 
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Getter for the height of the banana
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Setter for the height of the banana
	 * 
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Method used to draw the monkey object on the panel
	 * 
	 * @param graphics
	 *            Graphics object to use to draw the monkey object
	 */
	public void draw(Graphics graphics) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource("monkey.png"));
		} catch (IOException e) {
		}
		graphics.drawImage(img, xCoor, yCoor, width, height, null);
	}
}
