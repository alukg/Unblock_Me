package GameMenu;

import javax.swing.*;

public class HomeWindow extends PanelModel {

    Controller controller;

    public HomeWindow(Controller controller) {
        this.controller = controller;
        JButton selectLevel = new JButtonWithBackground("design\\selectlevel.jpg");
        selectLevel.setActionCommand("Select level");
        selectLevel.addActionListener(controller.new menuPress());
        JButton editor = new JButtonWithBackground("design\\editor.jpg");
        editor.setActionCommand("Editor");
        editor.addActionListener(controller.new menuPress());
        JButton exit = new JButtonWithBackground("design\\exit.jpg");
        exit.addActionListener(e -> {controller.saveDB(); System.exit(0);});
        menuPanel.add(selectLevel);
        menuPanel.add(editor);
        menuPanel.add(exit);
    }
}
