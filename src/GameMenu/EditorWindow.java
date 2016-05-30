package GameMenu;

import GameMenu.ModelClasses.JButtonWithBackground;

import javax.swing.*;
import java.awt.*;

public class EditorWindow extends LevelsWindow {

    public EditorWindow(Controller controller) {
        super(controller);
        JButton addLevel = new JButtonWithBackground("C:\\Users\\guyal\\workspace\\UnblockMe\\design\\addnewlevelPirate.jpg");
        addLevel.setActionCommand("Add new level");
        addLevel.addActionListener(controller.new menuPress());
        JLabel explenation = new JLabel("<html><center>Press on level<br/>for delete</center></html>");
        explenation.setForeground(Color.WHITE);
        explenation.setFont(new Font("Century Gothic",Font.BOLD,14));
        explenation.setHorizontalAlignment(SwingConstants.CENTER);
        menuPanel.add(addLevel);
        menuPanel.add(new JLabel());
        menuPanel.add(explenation);
    }

}
