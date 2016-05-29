package GameMenu;

import GameMenu.ModelClasses.JButtonWithBackground;
import GameMenu.ModelClasses.PanelModel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HomeWindow extends PanelModel {

    Controller controller;

    public HomeWindow(Controller controller) {
        this.controller = controller;
        JButton selectLevel = new JButtonWithBackground("design\\selectlevelPirate.jpg");
        selectLevel.setActionCommand("Select level");
        selectLevel.addActionListener(controller.new menuPress());
        JButton editor = new JButtonWithBackground("design\\editorPirate.jpg");
        editor.setActionCommand("Editor");
        editor.addActionListener(controller.new menuPress());
        JButton exit = new JButtonWithBackground("design\\exitPirate.jpg");
        exit.addActionListener(e -> {controller.saveDB(); System.exit(0);});
        menuPanel.add(selectLevel);
        menuPanel.add(editor);
        menuPanel.add(exit);
        mainPanel.setBackground(Color.BLACK);
        JLabel headLine = new JLabel();
        headLine.setIcon(new ImageIcon("design\\headlabel.png"));
        mainPanel.add(headLine);
    }
}
