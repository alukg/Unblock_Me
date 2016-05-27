package GameMenu;

import javax.swing.*;
import java.awt.*;

public class PanelModel extends JSplitPane {

    private final int location = 350;
    protected JPanel mainPanel;
    protected JPanel menuPanel;

    public PanelModel(){
        super(JSplitPane.HORIZONTAL_SPLIT);
        setDividerLocation(location);
        this.setPreferredSize(new Dimension(520, 350));
        this.setDividerSize(4);
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(24, 99, 131));
        menuPanel = new JPanelWithBackground("design\\back.jpg");
        menuPanel.setLayout(new GridLayout(8, 1));
        menuPanel.setBackground(new Color(19, 115, 132));
        setRightComponent(menuPanel);
        setLeftComponent(mainPanel);
    }

    @Override
    public int getDividerLocation() {
        return location;
    }

    @Override
    public int getLastDividerLocation() {
        return location;
    }

}
