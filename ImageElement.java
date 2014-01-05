import java.awt.Graphics2D;
import java.awt.Image;

/**
 * 
 * @author Aaron Nech
 * 
 * This is a screen element that represents an image
 *
 */
public class ImageElement extends ScreenElement {
	/**
	 * The image that is shown by this element
	 */
	private Image data;

	public ImageElement(Image img, int screenW, int screenH) {
		super(img.getWidth(null), img.getHeight(null), screenW, screenH);
		data = img;
	}

	/**{@inheritDoc}**/
	@Override
	public void render(Graphics2D g) {
		g.drawImage(data, getX(), getY(), null);
	}

}
