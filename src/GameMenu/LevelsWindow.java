package GameMenu;

import DataBase.Serializer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ListIterator;

public class LevelsWindow extends PanelModel {

    Controller controller;
    JPanel levelsChoose;


    public LevelsWindow(Controller controller) {
        this.controller = controller;
        JButton home = new JButton("Home");
        home.setActionCommand("Home");
        home.addActionListener(controller.new buttonPress());
        menuPanel.add(home);

        levelsChoose = new JPanel(new GridLayout(0,4,4,4));
        levelsChoose.setBackground(new Color(19, 115, 132));
        JScrollPane scrollPane = new JScrollPane(levelsChoose);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);

        mainPanel.add(scrollPane);

        addLevelsButtons();

        JButton one = new JButton("<html><center>Level 1<br/>"+"13:14"+"</center></html>");
    }

    private void addLevelsButtons(){
        List<Level> list = Serializer.deserialize();
        ListIterator<Level> iterator = list.listIterator();
        int i = 1;
        while(iterator.hasNext()){
            JButton levelButton = new JButton(""+i);
            levelButton.setText("<html><center>"+i+"<br/>"+iterator.next().bestTime+"</center></html>");
            levelsChoose.add(levelButton);
            i++;
        }
    }

    public class Level
    {
        //public Board board;
        public String bestTime;
    }
}
