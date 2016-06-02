package GameMenu;

import GameComponents.Block;
import GameComponents.Level;
import GameMenu.ModelClasses.JButtonWithIcon;
import GameMenu.ModelClasses.JPanelWithBackground;
import GameMenu.ModelClasses.PanelModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Vector;

public class GameEditor extends PanelModel {
    private Controller controller;
    private Vector<Block> ships; //contain all the new ships for the new level.
    private GridBagConstraints gbc;
    private Vector<freeSpaceButton> freeSpaces; //contain the marked free spaces for creating new ship.
    private JLabel targetShip;
    private ImageIcon freeSpaceIcon = new ImageIcon("design//emptySpaceTrans.png");

    /**
     * Constructor
     * @param controller controller instance for getting the controller listeners.
     */
    public GameEditor(Controller controller) {
        this.controller = controller;
        ships = new Vector<>();
        freeSpaces = new Vector<>();

        JButton returnButton = new JButtonWithIcon("design\\returnPirate.jpg");
        returnButton.setActionCommand("Editor");
        returnButton.addActionListener(controller.new menuPress());
        JButton saveLevel = new JButtonWithIcon("design\\savelevelPirate.jpg");
        saveLevel.setActionCommand("Save Level");
        saveLevel.addActionListener(new SaveLevelListener());
        JButton createShip = new JButtonWithIcon("design\\addshipPirate.jpg");
        createShip.addActionListener(new CreateShipListener());

        menuPanel.add(returnButton);
        menuPanel.add(saveLevel);
        menuPanel.add(createShip);

        //explanations for the editor.
        JLabel explenation = new JLabel("<html><center>Mark the wanted<br/>place on the board</center></html>");
        explenation.setForeground(Color.WHITE);
        explenation.setFont(new Font("Century Gothic",Font.BOLD,12));
        explenation.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel explenation2 = new JLabel("<html><center>and than press<br/>\"Add ship\".</center></html>");
        explenation2.setForeground(Color.WHITE);
        explenation2.setFont(new Font("Century Gothic",Font.BOLD,12));
        explenation2.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel explenation3 = new JLabel("<html><center>For delete, press<br/>on any ship</center></html>");
        explenation3.setForeground(Color.WHITE);
        explenation3.setFont(new Font("Century Gothic",Font.BOLD,12));
        explenation3.setHorizontalAlignment(SwingConstants.CENTER);
        menuPanel.add(explenation);
        menuPanel.add(explenation2);
        menuPanel.add(explenation3);

        mainPanel.setBackground(new Color(24,99,131));
        mainPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        arrangeBoard();
    }

    /**
     * arranges the board for the first time.
     */
    public void arrangeBoard(){
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                if(i==2 && j==0){ //set the target ship.
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
                else{ //put free spaces in the other places.
                    gbc.gridx = j;
                    gbc.gridy = i;
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                    freeSpaceButton freeSpace = new freeSpaceButton(new Point(j,i));
                    mainPanel.add(freeSpace,gbc);
                }
            }
        }
    }

    /**
     * Listener that examines the marked places, and put the new ship.
     */
    public class CreateShipListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isChoseFine()) { //checks that the chosen place is by the rules.
                for (int i = 0; i < freeSpaces.size(); i++) { //remove all the free spaces from the place of the new ship.
                    mainPanel.remove(freeSpaces.elementAt(i));
                }
                //get the chosen place coordinates.
                int aX = freeSpaces.elementAt(0).place.x;
                int aY = freeSpaces.elementAt(0).place.y;
                int bX = freeSpaces.elementAt(1).place.x;
                int bY = freeSpaces.elementAt(1).place.y;
                if (freeSpaces.size() == 2) { //if the wanted ship is length of 2.
                    //get the left-top place of the new ship.
                    gbc.gridx = Math.min(aX,bX);
                    gbc.gridy = Math.min(aY,bY);
                    if(aX==bX){ //if the two free spaces have the same X that means they are one on top of the other, that means the ship is vertical.
                        gbc.gridwidth = 1;
                        gbc.gridheight = 2;
                        shipButton newShip = new shipButton(gbc.gridx,gbc.gridy,2,"Vertical"); //set new ship button, and put the left-top coordinates of it.
                        newShip.setPreferredSize(new Dimension(58,116));
                        newShip.setIcon(new ImageIcon("design\\ships\\vertical2ship.png"));
                        mainPanel.add(newShip,gbc); //pur the new ship in it's place.
                        ships.addElement(newShip);
                    }
                    else{ //if the ship isn't vertical, so it is horizontal.
                        gbc.gridwidth = 2;
                        gbc.gridheight = 1;
                        shipButton newShip = new shipButton(gbc.gridx,gbc.gridy,2,"Horizontal");
                        newShip.setPreferredSize(new Dimension(116,58));
                        newShip.setIcon(new ImageIcon("design\\ships\\horizontal2ship.png"));
                        mainPanel.add(newShip,gbc);
                        ships.addElement(newShip);
                    }
                }
                else { //if the wanted ship isn't 2 length, but it is by the rules, so it means the ship is 3 length.
                    //get the 3rd place coordinates.
                    int cX = freeSpaces.elementAt(2).place.x;
                    int cY = freeSpaces.elementAt(2).place.y;
                    //get the left-top place of the new ship.
                    gbc.gridx = Math.min(cX, Math.min(aX,bX));
                    gbc.gridy = Math.min(cY, Math.min(aY,bY));
                    if(aX==bX){ //if there is two of the places that are in the same column, that means the ship is vertical.
                        gbc.gridwidth = 1;
                        gbc.gridheight = 3;
                        shipButton newShip = new shipButton(gbc.gridx,gbc.gridy,3,"Vertical");
                        newShip.setPreferredSize(new Dimension(58,174));
                        newShip.setIcon(new ImageIcon("design\\ships\\vertical3ship.png"));
                        mainPanel.add(newShip,gbc);
                        ships.addElement(newShip);
                    }
                    else{ //else, the ship is horizontal.
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
            else { //if the choose is not fine, cancel the choose.
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
            freeSpaces.removeAllElements(); //clear the list of the chosen free spaces.
            mainPanel.revalidate();
            mainPanel.repaint(); //repaint the board.
        }
    }

    /**
     * checks if the chosen free spaces is by the rules.
     * @return if the choose is fine.
     */
    private boolean isChoseFine(){
        if(freeSpaces.size()!=2 & freeSpaces.size()!=3){ //if the number of chosen spaces is not 3 or 2, that means it can't be good.
            return false;
        }
        else if(freeSpaces.size()==2){ //if there are 2.
            if(freeSpaces.elementAt(0).place.distance(freeSpaces.elementAt(1).place)==1)
                return true;
            else //if the distance between the spaces isn't 1, that means it can't be two suite spaces.
                return false;
        }
        else{ //if there are 3.
            //get the all 3 elements.
            int aX = freeSpaces.elementAt(0).place.x;
            int aY = freeSpaces.elementAt(0).place.y;
            int bX = freeSpaces.elementAt(1).place.x;
            int bY = freeSpaces.elementAt(1).place.y;
            int cX = freeSpaces.elementAt(2).place.x;
            int cY = freeSpaces.elementAt(2).place.y;
            if(aX==bX && bX==cX){ //if they are in the same column.
                int min = Math.min(aY, Math.min(bY, cY));
                int max = Math.max(aY, Math.max(bY, cY));
                return (max - min == 2); //if the distance between the max and the min is 2, that means they 3 suited.
            }
            else if(aY==bY && bY==cY){ //if they are in the same row.
                int min = Math.min(aX, Math.min(bX, cX));
                int max = Math.max(aX, Math.max(bX, cX));
                return (max - min == 2); //if the distance between the max and the min is 2, that means they 3 suited.
            }
            else //if they are in different rows and columns, so it isn't good.
                return false;
        }
    }

    /**
     * free space button, with the listener  inside.
     */
    private class freeSpaceButton extends JButton implements MouseListener {
        private Point place;
        private boolean pressed = false;

        /**
         * Constructor.
         * @param place the point of the button.
         */
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
                if(pressed){ //if the button is pressed, cancel it.
                    setBackground(new Color(24, 99, 131));
                    pressed = false;
                    freeSpaces.removeElement(this); //remove this free space from the marked ones.
                }
                else{ //if not pressed, mark it.
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
                this.setBorder(new LineBorder(Color.WHITE)); //mark the free space in white when i'm on it.

        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource()==this)
                this.setBorder(null); //cancel the border when i'm out.

        }
    }

    /**
     * ships used Block class for the editor needs.
     */
    private class shipButton extends Block implements MouseListener {
        /**
         * Constructor.
         * @param x the x argument of the button left-top point.
         * @param y the y argument of the button left-top point.
         * @param length the length of the ship.
         * @param dir the direction of the ship.
         */
        public shipButton(int x, int y, int length, String dir) {
            super(x,y,length,dir,false); //super the Block class, in this case the target is always false.
            setBackground(new Color(24,99,131));
            setBorder(null);
            addMouseListener(this);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==this){
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this ship?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE); //confirm dialog for deleting the ship.
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
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }

        /**
         * function that remove the ship and place free spaces in it place.
         */
        private void removeShip(){
            mainPanel.remove(this);
            gbc.gridheight=1;
            gbc.gridwidth=1;
            int placeX = getMy_x();
            int placeY = getMy_y();
            for(int i=0;i<getMy_length();i++){ //put free spaces by the length of the ship.
                if(getMy_dir().equals("Horizontal")){
                    placeX = getMy_x()+i;
                }
                if(getMy_dir().equals("Vertical")){
                    placeY = getMy_y()+i;
                }
                gbc.gridx = placeX;
                gbc.gridy = placeY;
                freeSpaceButton freeSpace = new freeSpaceButton(new Point(placeX,placeY));
                freeSpace.setPreferredSize(new Dimension(58,58));
                mainPanel.add(freeSpace,gbc); //add free space to the board.
            }
            mainPanel.revalidate();
            mainPanel.repaint();
        }

    }

    /**
     * Listener for the save level button. convert the Blocks array to multi Object array for saving it with gson.
     */
    private class SaveLevelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] blocks = new Object[ships.size()+1][5]; //the columns will be the ship and the rows will be the arguments.
            //index the target ship.
            blocks[0][0] = new Double(0);
            blocks[0][1] = new Double(2);
            blocks[0][2] = new Double(2);
            blocks[0][3] = "Horizontal";
            blocks[0][4] = true;
            //index the other ships.
            for(int i=0;i<ships.size();i++){
                blocks[i+1][0] = new Double(ships.elementAt(i).getMy_x());
                blocks[i+1][1] = new Double(ships.elementAt(i).getMy_y());
                blocks[i+1][2] = new Double(ships.elementAt(i).getMy_length());
                blocks[i+1][3] = ships.elementAt(i).getMy_dir();
                blocks[i+1][4] = ships.elementAt(i).getMy_target();
            }
            Level newLevel = new Level(blocks,"--:--"); //create the level object.
            controller.saveAndReturnToEditor(newLevel); //send to serialize with gson and return to the editor main window.
        }
    }

}
