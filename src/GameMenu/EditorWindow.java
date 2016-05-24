package GameMenu;

import javax.swing.*;

public class EditorWindow extends PanelModel {

    Controller controller;

    public EditorWindow(Controller controller) {
        this.controller = controller;
        JButton home = new JButton("Home");
        home.setActionCommand("Home");
        home.addActionListener(controller.new menuPress());
        JButton addLevel = new JButton("Add new level");
        addLevel.setActionCommand("Add Level");
        menuPanel.add(home);
        menuPanel.add(addLevel);
    }
}
