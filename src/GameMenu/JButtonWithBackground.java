package GameMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JButtonWithBackground extends JButton{

    private Image backgroundImage;

    public JButtonWithBackground(String fileName) {
        super();
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(null);
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
