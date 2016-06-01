package GameMenu;

import GameMenu.ModelClasses.JButtonWithIcon;
import GameMenu.ModelClasses.PanelModel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Welcome screen class.
 */
public class HomeWindow extends PanelModel {

    private Controller controller;
    private Clip clip; //the music controller.
    private JButton music;

    /**
     * Constructor.
     * @param controller get the controller for adding the menu listener.
     */
    public HomeWindow(Controller controller) {
        this.controller = controller;
        JButton selectLevel = new JButtonWithIcon("design\\selectlevelPirate.jpg");
        selectLevel.setActionCommand("Select level");
        selectLevel.addActionListener(controller.new menuPress());
        JButton editor = new JButtonWithIcon("design\\editorPirate.jpg");
        editor.setActionCommand("Editor");
        editor.addActionListener(controller.new menuPress());
        JButton exit = new JButtonWithIcon("design\\exitPirate.jpg");
        exit.addActionListener(e -> {controller.saveDB(); System.exit(0);});
        music = new JButtonWithIcon("design\\musicOffPirate.jpg");
        music.addActionListener(new MusicListener());
        menuPanel.add(selectLevel);
        menuPanel.add(editor);
        menuPanel.add(exit);
        menuPanel.add(music);
        mainPanel.setBackground(Color.BLACK);
        JLabel headLine = new JLabel();
        headLine.setIcon(new ImageIcon("design\\headlabel.png")); //add background to the main panel.
        mainPanel.add(headLine);

        //Add music
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("PiratesOfTheCaribbean.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream); //open the sound file.
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


    /**
     * Listener for the music player.
     */
    public class MusicListener implements ActionListener{

        private boolean musicOn = false; //decide if the music is on or off.

        @Override
        public void actionPerformed(ActionEvent e) {
            if(musicOn == true){
                musicOn = false;
                music.setIcon(new ImageIcon("design\\musicOffPirate.jpg")); //change the music icon to on.
                clip.stop();
            }
            else{
                musicOn = true;
                music.setIcon(new ImageIcon("design\\musicOnPirate.jpg")); //change the music icon to off.
                clip.start();
            }
        }
    }
}
