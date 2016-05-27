package GameMenu;

import GameComponents.Block;
import GameComponents.Game;
import GameComponents.Level;

import javax.swing.*;
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
    private Integer openLevel;

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

    public void gameFinished(String thisTime, String bestTime){
        String[] thisTimeSplit = thisTime.split(" \\: ");
        String[] bestTimeSplit = bestTime.split(" \\: ");
        String newBestTime;
        if(Integer.parseInt(thisTimeSplit[0])>Integer.parseInt(bestTimeSplit[0]))
           newBestTime=thisTime;
        else if(Integer.parseInt(thisTimeSplit[0])<Integer.parseInt(bestTimeSplit[0]))
            newBestTime=bestTime;
        else{
            if(Integer.parseInt(thisTimeSplit[1])>Integer.parseInt(bestTimeSplit[1]))
                newBestTime=thisTime;
            else if(Integer.parseInt(thisTimeSplit[1])<Integer.parseInt(bestTimeSplit[1]))
                newBestTime=bestTime;
            else
                newBestTime=thisTime;
        }
        levelsDB.elementAt(openLevel).bestTime=newBestTime;
        boolean stop = false;
        while(!stop){
            String[] options = { "Back to Main menu", "Select another level", "Next level" };
            JPanel panel = new JPanel();
            String dialogString;
            if(newBestTime.equals(thisTime))
                dialogString = "Congradulations! New record!\nBest time: "+newBestTime+"\nYour time: "+thisTime;
            else
                dialogString = "Congradulations! Successfully complete a level\nBest time: "+newBestTime+"\nYour time: "+thisTime;
            panel.add(new JLabel(dialogString), BorderLayout.CENTER);
            int selected = JOptionPane.showOptionDialog(controller,panel,"", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if(selected == 0){ //back to main menu
                stop = true;
                openLevel=null;
                cards.show(getContentPane(),"Home");
                gameWindow = null;
            }
            else if(selected == 1){ //select another level
                stop=true;
                openLevel=null;
                levelsWindow.addLevelChoosePanel();
                cards.show(getContentPane(),"Select level");
                gameWindow = null;
            }
            else{ //next level
                if(openLevel==levelsDB.size()-1){
                    JOptionPane.showOptionDialog(controller,"There is no next level","", JOptionPane.OK_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    continue;
                }
                else{
                    stop = true;
                    newGame(openLevel+1);
                }
            }
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
            newGame(Integer.parseInt(e.getActionCommand()));
        }
    }

    public void newGame(int levelSlot){
        if(gameWindow!=null){
            getContentPane().remove(gameWindow);
        }
        Level level = levelsDB.elementAt(levelSlot);
        Block[] blocks = new Block[level.blocks.length];
        for(int i=0;i<blocks.length;i++){
            Double dx = (Double)level.blocks[i][0];
            int x = dx.intValue();
            Double dy = (Double)level.blocks[i][1];
            int y = dy.intValue();
            Double dlength = (Double)level.blocks[i][2];
            int length = dlength.intValue();
            String dir = (String)level.blocks[i][3];
            Boolean target = (Boolean)level.blocks[i][4];
            blocks[i] = new Block(x,y,length,dir,target);
        }
        gameWindow = new Game(controller,blocks,level.bestTime);
        openLevel = levelSlot;
        getContentPane().add(gameWindow, "Game");
        cards.show(getContentPane(), "Game");
    }

    public class removeLevelPress implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] options = { "Confirm", "Cancel" };
            JPanel panel = new JPanel();
            panel.add(new JLabel("Are you sure you want to delete this level?"), BorderLayout.CENTER);
            int selected = JOptionPane.showOptionDialog(controller,panel,"Confirmation", JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if(selected == JOptionPane.YES_OPTION){
                levelsDB.removeElementAt(Integer.parseInt(e.getActionCommand()));
                editorWindow.addLevelChoosePanel();
            }
        }
    }

}
