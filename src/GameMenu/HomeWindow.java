package GameMenu;

import javax.swing.*;

public class HomeWindow extends PanelModel {

    Controller controller;

    public HomeWindow(Controller controller) {
        this.controller = controller;
        JButton selectLevel = new JButton("Select level");
        selectLevel.setActionCommand("Select level");
        selectLevel.addActionListener(controller.new menuPress());
        JButton editor = new JButton("Editor");
        editor.setActionCommand("Editor");
        editor.addActionListener(controller.new menuPress());
        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> {controller.saveDB(); System.exit(0);});
        menuPanel.add(selectLevel);
        menuPanel.add(editor);
        menuPanel.add(exit);
    }
}
