import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

/**
 * 
 * @author Aaron Nech
 * 
 * The main display port panel for the window view
 *
 */
public class XmasDisplay extends JPanel {
	/**
	 * Static randomized selection object
	 */
	private static final Random r = new Random();
	
	/**
	 * Element collection to choose from
	 */
	private List<ScreenElement> elements;
	
	/**
	 * The current displayed element
	 */
	private ScreenElement current;
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public XmasDisplay(int gWidth, int gHeight) {
		super();
		elements = new ArrayList<ScreenElement>();
		setPreferredSize(new Dimension(gWidth, gHeight));
	}
	
	/**
	 * Adds an element to this display
	 * 
	 * @param elm the element to add to the display
	 * @modifies this
	 * @effects this display now contains the given element as a
	 * 			display choice
	 */
	public void addElement(ScreenElement elm) {
		elements.add(elm);
		if(current == null)
			current = elm;
	}
	
	/**{@inheritDoc}*/
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		logic();
		render((Graphics2D) g);
	}

	/**
	 * Renders the graphics of this xmas display
	 * 
	 * @param graphics the graphics object to render on
	 */
	private void render(Graphics2D graphics) {
		graphics.setBackground(Color.BLACK);
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setColor(Color.WHITE);
		if(current != null)
			current.render(graphics);
	}

	/**
	 * Does a single logical step in the display application
	 */
	private void logic() {
		if(current != null) {
			current.update();
			if(current.disposed()) {
				current = elements.get(r.nextInt(elements.size()));
				current.reset();
			}
		}
	}
}
