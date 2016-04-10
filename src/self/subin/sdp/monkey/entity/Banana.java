package self.subin.sdp.monkey.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Object class to hold the instance of the banana object along with the method
 * used to draw the banana
 * 
 * @author Subin
 *
 */
public class Banana {

	/**
	 * co-ordinates for the banana along with the dimension
	 */
	private int xCoor, yCoor, width, height;

	/**
	 * Constructor to initialize the Banana object for the game
	 * 
	 * @param xCoor
	 *            the starting x co-ordinate to start drawing the object
	 * @param yCoor
	 *            the starting y co-ordinate to start drawing the object
	 * @param width
	 *            the width of the banana object
	 * @param height
	 *            the height of the banana object
	 */
	public Banana(int xCoor, int yCoor, int width, int height) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.width = width;
		this.height = height;
	}

	/**
	 * Getter for the x co-ordinate of the banana
	 * 
	 * @return the xCoor
	 */
	public int getxCoor() {
		return xCoor;
	}

	/**
	 * Setter for the x co-ordinate of the banana
	 * 
	 * @param xCoor
	 *            the xCoor to set for the banana
	 */
	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	/**
	 * Getter for the y co-ordinate of the banana
	 * 
	 * @return the xCoor for the banana
	 */
	public int getyCoor() {
		return yCoor;
	}

	/**
	 * Setter for the y co-ordinate of the banana
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
	 * Method used to draw the banana object on the panel
	 * 
	 * @param graphics
	 *            Graphics object to use to draw the banana object
	 */
	public void draw(Graphics graphics) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource("banana.png"));
		} catch (IOException e) {
		}
		graphics.drawImage(img, xCoor, yCoor, width, height, null);
	}
}
