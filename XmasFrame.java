import java.awt.Cursor;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * 
 * 
 * @author Aaron Nech
 * 
 * This is the starting point for the christmas frame Raspberry Pi
 * Project I wrote for Ternessa.
 *
 */
public class XmasFrame {
	
	/**
	 * The reference to the window
	 */
	private static JFrame window;
	
	/**
	 * The frames per second to run the application at
	 */
	private static final int FPS = 60;
	
	/**
	 * The crude loop timer
	 */
	private static Timer loop;
	
	/**
	 * The width of the graphics context
	 */
	private static int gWidth;
	
	/**
	 * the height of the graphics context
	 */
	private static int gHeight;
	
	public static void main(String[] args) {
		window = new JFrame("Xmas Frame Project 2013");
		window.setAlwaysOnTop(true);
		gWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		gHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		window.setContentPane(new XmasDisplay(gWidth, gHeight));
		window.setExtendedState(Frame.MAXIMIZED_BOTH);
		window.setUndecorated(true);
		GraphicsDevice d = GraphicsEnvironment
			    .getLocalGraphicsEnvironment().getDefaultScreenDevice();
		d.setFullScreenWindow(window);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB),
				new Point(0, 0), "blank");
		window.setCursor(blankCursor);
		window.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode() == 27) {
					window.setVisible(false);
					window.dispose();
					loop.cancel();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent k) {
				//nothing needed here
			}

			@Override
			public void keyTyped(KeyEvent k) {
				//nothing needed here
			}
			
		});
        loop = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                window.getContentPane().repaint();
            }
        };
        loop.scheduleAtFixedRate(task, 0, 1000/FPS);
        try {
			addElements();
		} catch (IOException e) {
			e.printStackTrace();
		}
		window.setVisible(true);
	}
	
	/**
	 * 
	 * Add elements to the repository of screen elements by
	 * loading them from disk. The files must be located
	 * at /mnt/usb
	 * 
	 * @throws IOException if the files fail to load
	 */
	public static void addElements() throws IOException {
		XmasDisplay display = (XmasDisplay) window.getContentPane();
		Scanner s = new Scanner(new File("/mnt/usb/media/messages.txt"));
		while(s.hasNextLine()) {
			display.addElement(new TextElement(s.nextLine(), gWidth, gHeight));
		}
	    File directory = new File("/mnt/usb/media/pic");
	    File[] list = directory.listFiles();
	    for(File f : list) {
	    	if(f.getName().endsWith("jpg") || 
	    			f.getName().endsWith("jpeg")) {
	    		BufferedImage img = ImageIO.read(f);
	    		float ratio = (float) (gHeight - 10) / Math.max(gHeight - 10, img.getHeight());
	    		Image image = img.getScaledInstance((int) (img.getWidth() * ratio),
	    				(int) (img.getHeight() * ratio), Image.SCALE_FAST);
	    		display.addElement(new ImageElement(image, gWidth, gHeight));
	    	}
	    }
	    s.close();
	}
}
