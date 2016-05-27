package GameMenu;

import GameComponents.Game;
import GameComponents.Level;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Controller extends JFrame {

    private Controller controller = this;
    private CardLayout cards;
    private HomeWindow homeWindow;
    private LevelsWindow levelsWindow;
    private EditorWindow editorWindow;
    private GameEditor gameEditor;
    private Game gameWindow;
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

        levelsDB = DataBase.Serializer.deserialize();

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

    public Vector<Level> getLevelsDB(){
        return levelsDB;
    }

    public void saveDB(){
        DataBase.Serializer.serialize(levelsDB);
    }

    public class gameFinished implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e) {
            levelsWindow.addLevelChoosePanel();
            cards.show(getContentPane(),"Select level");
            gameWindow = null;
        }
    }

    public class menuPress implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Select level")){
                levelsWindow.addLevelChoosePanel();
                cards.show(getContentPane(), "Select level");
            }
            else if(e.getActionCommand().equals("Editor")){
                cards.show(getContentPane(), "Editor");
            }
            else if(e.getActionCommand().equals("Add new level")){
                if(gameEditor!=null){
                    getContentPane().remove(gameEditor);
                }
                gameEditor = new GameEditor(controller);
                getContentPane().add(gameEditor, "Game Editor");
                cards.show(getContentPane(), "Game Editor");
            }
            else{
                cards.show(getContentPane(), "Home");
            }
        }
    }

    public void saveAndReturnToEditor(Level newLevel){
        if(levelsDB == null){
            levelsDB = new Vector<>();
        }
        levelsDB.add(newLevel);
        editorWindow.addLevelChoosePanel();
        cards.show(getContentPane(), "Editor");
        gameEditor = null;
    }

    public class gamePress implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(gameWindow!=null){
                getContentPane().remove(gameWindow);
            }
            gameWindow = new Game(e.getActionCommand());
            getContentPane().add(gameWindow, "Game");
            cards.show(getContentPane(), "Game");
        }
    }

    public class removeLevelPress implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this level?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(dialogResult == JOptionPane.YES_OPTION){
                int dialogResult2 = JOptionPane.showConfirmDialog(null, "Really sure? that you won't cry after this","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(dialogResult2 == JOptionPane.YES_OPTION){
                    levelsDB.removeElementAt(Integer.parseInt(e.getActionCommand()));
                    editorWindow.addLevelChoosePanel();
                }
            }
        }
    }

}
