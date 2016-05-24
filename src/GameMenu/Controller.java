package GameMenu;

import GameComponents.Game;
import GameComponents.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Controller extends JFrame {

    private CardLayout cards;
    private HomeWindow homeWindow;
    private LevelsWindow levelsWindow;
    private EditorWindow editorWindow;
    private Vector<Level> levelsDB;

    public Controller(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        CardLayout cardL = new CardLayout();
        getContentPane().setLayout(cardL);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveDB();
                System.exit(0);
            }
        });

        //just for check, replace the deserialize
        levelsDB = new Vector<>();
        for(int i=0;i<50;i++){
            levelsDB.add(new Level(null,"--:--"));
        }

        //levelsDB = DataBase.Serializer.deserialize();

        homeWindow = new HomeWindow(this);
        levelsWindow = new LevelsWindow(this);
        editorWindow = new EditorWindow(this);

        getContentPane().add(homeWindow, "Home");
        getContentPane().add(levelsWindow, "Select level");
        getContentPane().add(editorWindow, "Editor");

        //game Window just for check
        Game game = new Game();
        getContentPane().add(game, "Game");
        /////////////////////////////////////////////////

        cards = (CardLayout) getContentPane().getLayout();
        //cards.show(getContentPane(), "Home");
        cards.show(getContentPane(), "Game");

        pack();
        this.setVisible(true);
    }

    public Vector<Level> getLevelsDB(){
        return levelsDB;
    }

    public void saveDB(){
        DataBase.Serializer.serialize(levelsDB);
    }

    public class menuPress implements ActionListener {
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
