package GameMenu;

import GameComponents.Block;
import GameComponents.Level;
import GameMenu.ModelClasses.JButtonWithBackground;
import GameMenu.ModelClasses.PanelModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class GameEditor extends PanelModel {
    private Controller controller;
    private Vector<Block> ships;
    private GridBagConstraints gbc;
    private Vector<freeSpaceButton> freeSpaces;
    private JLabel targetShip;

    public GameEditor(Controller controller) {
        this.controller = controller;
        ships = new Vector<>();
        freeSpaces = new Vector<>();

        JButton returnButton = new JButtonWithBackground("design\\returnPirate.jpg");
        returnButton.setActionCommand("Editor");
        returnButton.addActionListener(controller.new menuPress());
        JButton saveLevel = new JButtonWithBackground("design\\savelevelPirate.jpg");
        saveLevel.setActionCommand("Save Level");
        saveLevel.addActionListener(new SaveLevelListener());
        JButton createShip = new JButtonWithBackground("design\\addshipPirate.jpg");
        createShip.addActionListener(new CreateShipListener());

        menuPanel.add(returnButton);
        menuPanel.add(saveLevel);
        menuPanel.add(createShip);

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(24,99,131));
        gbc = new GridBagConstraints();

        arrangeBoard();
    }

    public void arrangeBoard(){
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                if(i==2 && j==0){
                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    gbc.gridwidth = 2;
                    targetShip = new JLabel();
                    targetShip.setIcon(new ImageIcon("design\\ships\\targetShip.png"));
                    mainPanel.add(targetShip,gbc);
                }
                else if(i==2 && j==1){
                    continue;
                }
                else{
                    gbc.gridx = j;
                    gbc.gridy = i;
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                    freeSpaceButton freeSpace = new freeSpaceButton(new Point(j,i));
                    //freeSpace.setText(""+(j+1)+","+(i+1));
                    mainPanel.add(freeSpace,gbc);
                }
            }
        }
    }

    public class CreateShipListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isChoseFine()) {
                for (int i = 0; i < freeSpaces.size(); i++) {
                    mainPanel.remove(freeSpaces.elementAt(i));
                }
                int aX = freeSpaces.elementAt(0).place.x;
                int aY = freeSpaces.elementAt(0).place.y;
                int bX = freeSpaces.elementAt(1).place.x;
                int bY = freeSpaces.elementAt(1).place.y;
                if (freeSpaces.size() == 2) {
                    gbc.gridx = Math.min(aX,bX);
                    gbc.gridy = Math.min(aY,bY);
                    if(aX==bX){
                        gbc.gridwidth = 1;
                        gbc.gridheight = 2;
                        shipButton newShip = new shipButton(gbc.gridx,gbc.gridy,2,"Vertical");
                        newShip.setPreferredSize(new Dimension(58,116));
                        newShip.setIcon(new ImageIcon("design\\ships\\vertical2ship.png"));
                        mainPanel.add(newShip,gbc);
                        ships.addElement(newShip);
                    }
                    else{
                        gbc.gridwidth = 2;
                        gbc.gridheight = 1;
                        shipButton newShip = new shipButton(gbc.gridx,gbc.gridy,2,"Horizontal");
                        newShip.setPreferredSize(new Dimension(116,58));
                        newShip.setIcon(new ImageIcon("design\\ships\\horizontal2ship.png"));
                        mainPanel.add(newShip,gbc);
                        ships.addElement(newShip);
                    }
                }
                else {
                    int cX = freeSpaces.elementAt(2).place.x;
                    int cY = freeSpaces.elementAt(2).place.y;
                    gbc.gridx = Math.min(cX, Math.min(aX,bX));
                    gbc.gridy = Math.min(cY, Math.min(aY,bY));
                    if(aX==bX){
                        gbc.gridwidth = 1;
                        gbc.gridheight = 3;
                        shipButton newShip = new shipButton(gbc.gridx,gbc.gridy,3,"Vertical");
                        newShip.setPreferredSize(new Dimension(58,174));
                        newShip.setIcon(new ImageIcon("design\\ships\\vertical3ship.png"));
                        mainPanel.add(newShip,gbc);
                        ships.addElement(newShip);
                    }
                    else{
                        gbc.gridwidth = 3;
                        gbc.gridheight = 1;
                        shipButton newShip = new shipButton(gbc.gridx,gbc.gridy,3,"Horizontal");
                        newShip.setPreferredSize(new Dimension(174,58));
                        newShip.setIcon(new ImageIcon("design\\ships\\horizontal3ship.png"));
                        mainPanel.add(newShip,gbc);
                        ships.addElement(newShip);
                    }
                }
            }
            else {
                for (int i = 0; i < freeSpaces.size(); i++) {
                    freeSpaceButton element = freeSpaces.elementAt(i);
                    mainPanel.remove(element);
                    element.pressed = false;
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                    gbc.gridx = element.place.x;
                    gbc.gridy = element.place.y;
                    element.setBackground(new Color(24,99,131));
                    mainPanel.add(element,gbc);
                }
            }
            freeSpaces.removeAllElements();
            mainPanel.repaint();
        }
    }

    private boolean isChoseFine(){
        if(freeSpaces.size()!=2 & freeSpaces.size()!=3){
            return false;
        }
        else if(freeSpaces.size()==2){
            if(freeSpaces.elementAt(0).place.distance(freeSpaces.elementAt(1).place)==1)
                return true;
            else
                return false;
        }
        else{
            int aX = freeSpaces.elementAt(0).place.x;
            int aY = freeSpaces.elementAt(0).place.y;
            int bX = freeSpaces.elementAt(1).place.x;
            int bY = freeSpaces.elementAt(1).place.y;
            int cX = freeSpaces.elementAt(2).place.x;
            int cY = freeSpaces.elementAt(2).place.y;
            if(aX==bX && bX==cX){
                int min = Math.min(aY, Math.min(bY, cY));
                int max = Math.max(aY, Math.max(bY, cY));
                return (max - min == 2);
            }
            else if(aY==bY && bY==cY){
                int min = Math.min(aX, Math.min(bX, cX));
                int max = Math.max(aX, Math.max(bX, cX));
                return (max - min == 2);
            }
            else
                return false;
        }
    }

    private class freeSpaceButton extends JButton implements MouseListener {
        private Point place;
        private boolean pressed = false;

        public freeSpaceButton(Point place) {
            super();
            this.place = place;
            setBackground(new Color(24,99,131));
            setPreferredSize(new Dimension(58,58));
            setBorder(null);
            addMouseListener(this);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==this){
                if(pressed){
                    setBackground(new Color(24, 99, 131));
                    pressed = false;
                    freeSpaces.removeElement(this);
                }
                else{
                    setBackground(new Color(67, 134, 130));
                    pressed = true;
                    freeSpaces.add(this);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) { }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource()==this)
                this.setBorder(new LineBorder(Color.WHITE));

        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource()==this)
                this.setBorder(null);

        }
    }

    private class shipButton extends Block implements MouseListener {
        public shipButton(int x, int y, int length, String dir) {
            super(x,y,length,dir,false);
            setBackground(new Color(24,99,131));
            setBorder(null);
            addMouseListener(this);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==this){
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this ship?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(dialogResult == JOptionPane.YES_OPTION){
                    ships.removeElement(this);
                    removeShip();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) { }

        @Override
        public void mouseEntered(MouseEvent e) {
            /*
            if(e.getSource()==this)
                this.setBorder(new LineBorder(Color.WHITE));
            */
        }

        @Override
        public void mouseExited(MouseEvent e) {
            /*
            if(e.getSource()==this)
                this.setBorder(null);
            */
        }

        private void removeShip(){
            mainPanel.remove(this);
            gbc.gridheight=1;
            gbc.gridwidth=1;
            int placeX = getMy_x();
            int placeY = getMy_y();
            for(int i=0;i<getMy_length();i++){
                if(getMy_dir().equals("Horizontal")){
                    placeX = getMy_x()+i;
                }
                if(getMy_dir().equals("Vertical")){
                    placeY = getMy_y()+i;
                }
                gbc.gridx = placeX;
                gbc.gridy = placeY;
                freeSpaceButton freeSpace = new freeSpaceButton(new Point(placeX,placeY));
                //freeSpace.setText(""+(placeX+1)+","+(placeY+1));
                freeSpace.setPreferredSize(new Dimension(58,58));
                mainPanel.add(freeSpace,gbc);
            }
            mainPanel.repaint();
        }

    }

    private class SaveLevelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] blocks = new Object[ships.size()+1][5];
            blocks[0][0] = new Double(0);
            blocks[0][1] = new Double(2);
            blocks[0][2] = new Double(2);
            blocks[0][3] = "Horizontal";
            blocks[0][4] = true;
            for(int i=0;i<ships.size();i++){
                blocks[i+1][0] = new Double(ships.elementAt(i).getMy_x());
                blocks[i+1][1] = new Double(ships.elementAt(i).getMy_y());
                blocks[i+1][2] = new Double(ships.elementAt(i).getMy_length());
                blocks[i+1][3] = ships.elementAt(i).getMy_dir();
                blocks[i+1][4] = ships.elementAt(i).getMy_target();
            }
            Level newLevel = new Level(blocks,"--:--");
            controller.saveAndReturnToEditor(newLevel);
        }
    }

}
