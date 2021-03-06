package GameMenu.ModelClasses;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * The base structure for all the windows. All the windows extends it.
 */
public class PanelModel extends JSplitPane {

    private final int dividerLocation = 350; //location of the divider.
    protected JPanel mainPanel;
    protected JPanel menuPanel;

    /**
     * Constructor
     */
    public PanelModel(){
        super(JSplitPane.HORIZONTAL_SPLIT);
        setDividerLocation(dividerLocation);
        this.setPreferredSize(new Dimension(520, 350));
        this.setDividerSize(4);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        menuPanel = new JPanelWithBackground("design\\backPirate.jpg");
        menuPanel.setLayout(new GridLayout(8, 1));
        menuPanel.setBackground(Color.BLACK);
        setRightComponent(menuPanel);
        setLeftComponent(mainPanel);
    }

}
