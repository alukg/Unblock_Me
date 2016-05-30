package GameMenu;

import GameMenu.ModelClasses.JButtonWithBackground;
import GameMenu.ModelClasses.PanelModel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class HomeWindow extends PanelModel {

    private Controller controller;
    private Clip clip;
    private JButton music;

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
        music = new JButtonWithBackground("design\\musicOffPirate.jpg");
        music.addActionListener(new MusicListener());
        menuPanel.add(selectLevel);
        menuPanel.add(editor);
        menuPanel.add(exit);
        menuPanel.add(music);
        mainPanel.setBackground(Color.BLACK);
        JLabel headLine = new JLabel();
        headLine.setIcon(new ImageIcon("design\\headlabel.png"));
        mainPanel.add(headLine);

        //Add music
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("PiratesOfTheCaribbean.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public class MusicListener implements ActionListener{

        private boolean musicOn = false;

        @Override
        public void actionPerformed(ActionEvent e) {
            if(musicOn == true){
                musicOn = false;
                music.setIcon(new ImageIcon("design\\musicOffPirate.jpg"));
                clip.stop();
            }
            else{
                musicOn = true;
                music.setIcon(new ImageIcon("design\\musicOnPirate.jpg"));
                clip.start();
            }
        }
    }
}
