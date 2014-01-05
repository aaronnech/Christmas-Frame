import java.awt.Font;
import java.awt.Graphics2D;

/**
 * @author Aaron Nech
 *  
 * An element of text on the screen
 */
public class TextElement extends ScreenElement {
	private final static int CHAR_WIDTH = 24;
	private final static int CHAR_HEIGHT = 40;
	private String data;

	public TextElement(String text, int screenW, int screenH) {
		super(text.length() * CHAR_WIDTH, CHAR_HEIGHT, screenW, screenH);
		data = text;
	}

	/**{@inheritDoc}*/
	@Override
	public void render(Graphics2D g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 24)); 
		g.drawString(data, getX(), getY());
	}
}
