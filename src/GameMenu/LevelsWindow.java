package GameMenu;

import GameComponents.Level;
import GameMenu.ModelClasses.JButtonWithBackground;
import GameMenu.ModelClasses.PanelModel;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class LevelsWindow extends PanelModel {

    private Controller controller;
    private JPanel levelsChoose = null;
    private JScrollPane scrollPane;

    public LevelsWindow(Controller controller) {
        this.controller = controller;
        JButton home = new JButtonWithBackground("design\\home.jpg");
        home.setActionCommand("Home");
        home.addActionListener(controller.new menuPress());
        menuPanel.add(home);

        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(340,338));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setBackground(new Color(24, 99, 131));

        addLevelChoosePanel();

        mainPanel.add(scrollPane);
    }

    public void addLevelChoosePanel() {
        if (levelsChoose!=null && levelsChoose.getParent()!=null)
            scrollPane.remove(levelsChoose);
        levelsChoose = new JPanel();
        levelsChoose.setBackground(new Color(24, 99, 131));

        scrollPane.setViewportView(levelsChoose);

        Vector<Level> vector = controller.getLevelsDB();

        if(vector==null || vector.size()==0){
            JLabel noLevels = new JLabel("There is no levels",SwingConstants.CENTER);
            noLevels.setForeground(Color.WHITE);
            noLevels.setFont(new Font(noLevels.getFont().getFontName(),Font.BOLD,20));
            levelsChoose.add(noLevels);
        }
        else{
            levelsChoose.setLayout(new GridBagLayout());
            int i = 0;
            while(i<vector.size()){
                JButton levelButton = new JButton(""+i);
                levelButton.setPreferredSize(new Dimension(100,100));
                levelButton.setText("<html><center>Level "+(i+1)+"<br/>"+vector.elementAt(i).bestTime+"</center></html>");
                if(this.getClass().getSimpleName().equals("LevelsWindow")){
                    levelButton.setActionCommand(""+(i));
                    levelButton.addActionListener(controller.new gamePress());
                }
                else{
                    levelButton.setActionCommand(""+i);
                    levelButton.addActionListener(controller.new removeLevelPress());
                }
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.gridx = i%3;
                gbc.gridy = i/3;
                levelsChoose.add(levelButton);
                i++;
            }
        }
    }
}
