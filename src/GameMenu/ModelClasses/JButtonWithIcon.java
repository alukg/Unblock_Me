package GameMenu.ModelClasses;

import javax.swing.*;
import java.awt.*;

/**
 * class for creating Button with Icon.
 */
public class JButtonWithIcon extends JButton{

    /**
     * Constructor
     * @param fileName address of the icon file
     */
    public JButtonWithIcon(String fileName) {
        super();
        setCursor(new Cursor(Cursor.HAND_CURSOR)); //set Hand cursor
        setBorder(null);
        setIcon(new ImageIcon(fileName)); //set icon for the image
    }
}