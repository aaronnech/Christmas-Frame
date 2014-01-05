import java.awt.Graphics2D;
import java.util.Random;

/**
 * 
 * @author Aaron Nech
 * 
 * A screen element is a piece of visual information
 * that will drift across the xmas frame
 *
 */
abstract public class ScreenElement {
	/**
	 * static random object to produce random values
	 */
	private static final Random r = new Random();
	
	/**
	 * dimensional/physical information
	 */
	private static final int SPEED = 1;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean dispose;
	
	/**
	 * Environment information
	 */
	private int screenWidth;
	private int screenHeight;
	
	public ScreenElement(int w, int h, int screenW, int screenH) {
		screenWidth = screenW;
		screenHeight = screenH;
		width = w;
		height = h;
		reset();
	}
	
	/**
	 * Resets the screen element to rerun
	 * 
	 * @modifies this
	 * @effects this screen element will be replaced at the left to
	 * 			be re-animated
	 */
	public void reset() {
		dispose = false;
		x = screenWidth;
		y = r.nextInt(screenHeight - height);
	} 
	
	/**
	 * Updates the elements position on the screen
	 * 
	 * @modifies this
	 * @effects moves the element forward in its' animation
	 * 			if the element flies off the screen, it will be disposed.
	 */
	public void update() {
		x -= SPEED;
		if(x < -1 * width) {
			dispose = true;
		}
	}
	
	/**
	 * Gets the disposed status of this element
	 * 
	 * @return the disposed status of this element
	 */
	public boolean disposed() {
		return dispose;
	}
	
	/**
	 * Gets the x coordinate of the element
	 * 
	 * @return the x coordinate of this element
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y coordinate of the element
	 * 
	 * @return the y coordinate of this element
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Renders this screen element with the given graphics object
	 * 
	 * @param g the graphics object to render with
	 */
	abstract public void render(Graphics2D g);
}
