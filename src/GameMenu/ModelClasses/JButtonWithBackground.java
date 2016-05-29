package GameMenu.ModelClasses;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JButtonWithBackground extends JButton{

    public JButtonWithBackground(String fileName) {
        super();
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(null);
        setIcon(new ImageIcon(fileName));
    }
}