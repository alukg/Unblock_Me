package GameMenu;

import GameComponents.Level;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class LevelsWindow extends PanelModel {

    Controller controller;
    JPanel levelsChoose;
    JScrollPane scrollPane;

    public LevelsWindow(Controller controller) {
        this.controller = controller;
        JButton home = new JButton("Home");
        home.setActionCommand("Home");
        home.addActionListener(controller.new menuPress());
        menuPanel.add(home);

        addLevelChooseScroller();
    }

    private void addLevelChooseScroller() {
        if (scrollPane!=null && scrollPane.getParent()!=null)
            mainPanel.remove(scrollPane);

        levelsChoose = new JPanel(new GridLayout(0, 3, 4, 4));
        levelsChoose.setBackground(new Color(19, 115, 132));
        scrollPane = new JScrollPane(levelsChoose);
        scrollPane.setPreferredSize(new Dimension(340,338));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setBackground(new Color(19, 115, 132));

        mainPanel.add(scrollPane);

        Vector<Level> vector = controller.getLevelsDB();

        int i = 0;
        while(i<vector.size()){
            JButton levelButton = new JButton(""+i);
            levelButton.setText("<html><center>Level "+(i+1)+"<br/>"+vector.elementAt(i).bestTime+"</center></html>");
            levelButton.setActionCommand(""+i);
            //add action listener
            levelsChoose.add(levelButton);
            i++;
        }
    }
}
