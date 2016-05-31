package GameMenu.ModelClasses;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Class that creates JPanel with background.
 */
public class JPanelWithBackground extends JPanel {

    private Image backgroundImage;

    /**
     * Constructor
     * @param fileName background image address.
     */
    public JPanelWithBackground(String fileName) {
        super();
        File file = new File(fileName);
        try {
            backgroundImage = ImageIO.read(file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Adds the background to the image.
     * @param g painter.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null); // Draw the background image.
    }
}