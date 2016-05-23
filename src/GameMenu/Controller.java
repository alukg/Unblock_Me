package GameMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JFrame {

    private CardLayout cards;
    private HomeWindow homeWindow;
    private LevelsWindow levelsWindow;
    private EditorWindow editorWindow;

    public Controller(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        CardLayout cardL = new CardLayout();
        getContentPane().setLayout(cardL);

        homeWindow = new HomeWindow(this);
        levelsWindow = new LevelsWindow(this);
        editorWindow = new EditorWindow(this);

        getContentPane().add(homeWindow, "Home");
        getContentPane().add(levelsWindow, "Select level");
        getContentPane().add(editorWindow, "Editor");

        cards = (CardLayout) getContentPane().getLayout();
        cards.show(getContentPane(), "Home");

        pack();
        this.setVisible(true);
    }

    public class buttonPress implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Select level")){
                cards.show(getContentPane(), "Select level");
            }
            else if(e.getActionCommand().equals("Editor")){
                cards.show(getContentPane(), "Editor");
            }
            else{
                cards.show(getContentPane(), "Home");
            }
        }
    }

}
