package GameMenu;

import GameMenu.ModelClasses.JButtonWithIcon;

import javax.swing.*;
import java.awt.*;

/**
 * Editor choose window.
 */
public class EditorWindow extends LevelsWindow {

    /**
     * Constructor.
     * @param controller get the controller for adding the menu listener.
     */
    public EditorWindow(Controller controller) {
        super(controller);
        JButton addLevel = new JButtonWithIcon("design\\addnewlevelPirate.jpg"); //add level button.
        addLevel.setActionCommand("Add new level");
        addLevel.addActionListener(controller.new menuPress());
        JLabel explanation = new JLabel("<html><center>Press on level<br/>for delete</center></html>"); //explanation label.
        explanation.setForeground(Color.WHITE);
        explanation.setFont(new Font("Century Gothic",Font.BOLD,14));
        explanation.setHorizontalAlignment(SwingConstants.CENTER);
        menuPanel.add(addLevel);
        menuPanel.add(new JLabel());
        menuPanel.add(explanation);
    }

}
