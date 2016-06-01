package GameMenu;

import GameComponents.Level;
import GameMenu.ModelClasses.JButtonWithIcon;
import GameMenu.ModelClasses.PanelModel;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * The window of level choose to play.
 */
public class LevelsWindow extends PanelModel {

    private Controller controller;
    private JPanel levelsChoose = null;
    private JScrollPane scrollPane;

    /**
     * Constructor.
     * @param controller the controller instance for getting the controller functions.
     */
    public LevelsWindow(Controller controller) {
        this.controller = controller;
        JButton home = new JButtonWithIcon("design\\homePirate.jpg");
        home.setActionCommand("Home");
        home.addActionListener(controller.new menuPress());
        menuPanel.add(home);

        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(340,338));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //set the panel not to scroll horizontal.
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);

        addLevelChoosePanel(); //add the levels from the DB to the panel.

        mainPanel.add(scrollPane);
    }

    /**
     * add the levels from the DB function.
     */
    public void addLevelChoosePanel() {
        if (levelsChoose!=null && levelsChoose.getParent()!=null) //if there is panel of levels, create a new one.
            scrollPane.remove(levelsChoose);
        levelsChoose = new JPanel();
        levelsChoose.setBackground(Color.BLACK);

        scrollPane.setViewportView(levelsChoose); //set the levels panel in the scrollPane.

        Vector<Level> vector = controller.getLevelsDB();

        if(vector==null || vector.size()==0){ //if there is no levels.
            JLabel noLevels = new JLabel("There is no levels",SwingConstants.CENTER);
            noLevels.setForeground(Color.WHITE);
            noLevels.setFont(new Font(noLevels.getFont().getFontName(),Font.BOLD,20));
            levelsChoose.add(noLevels); //set message on the panel that there is no levels to the user.
        }
        else{
            levelsChoose.setLayout(new GridBagLayout());
            int i = 0;
            while(i<vector.size()){ //add the levels from the DB to the panel.
                //new level button.
                JButton levelButton = new JButtonWithIcon("design/levelButtonPirate.jpg");
                levelButton.setPreferredSize(new Dimension(100,100));
                levelButton.setHorizontalTextPosition(JButton.CENTER);
                levelButton.setVerticalTextPosition(JButton.CENTER);
                levelButton.setActionCommand(""+i);
                levelButton.setText("<html><center>Level "+(i+1)+"<br/>"+vector.elementAt(i).bestTime+"</center></html>");
                if(this.getClass().getSimpleName().equals("LevelsWindow")){ //if the levels panel is for game playing.
                    levelButton.addActionListener(controller.new gamePress());
                }
                else{ //if the levels panel is for editor.
                    levelButton.addActionListener(controller.new removeLevelPress());
                }
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                //just 3 buttons in a row.
                gbc.gridx = i%3;
                gbc.gridy = i/3;
                levelsChoose.add(levelButton,gbc);
                i++;
            }
        }
    }
}
