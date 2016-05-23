package GameMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JPanelWithBackground extends JPanel {

    private Image backgroundImage;

    public JPanelWithBackground(String fileName) {
        super();
        File file = new File(fileName);
        try {
            backgroundImage = ImageIO.read(file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
