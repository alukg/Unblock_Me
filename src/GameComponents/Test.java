package GameComponents;

import GameComponents.Block;
import GameComponents.Board;
import GameComponents.Game;


import javax.swing.*;


public class Test {

	public static void main(String[] args)
	{
		/*JFrame frame = new JFrame("BlaBla");
		JPanel pane = new JPanel();
		JButton button;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		button = new JButton("Button 1");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Button 2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Button 3");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Long-Named Button 4");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 1;
		c.gridy = 6;
		pane.add(button, c);

		button = new JButton("5");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 2;       //third row
		pane.add(button, c);
		pane.add(button, c);
		frame.add(pane);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		/*JPanel panel = new JPanel(new GridLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		JLabel b = new JLabel("bla");
		JLabel b1 = new JLabel("bla1");
		gbc.gridx = 10;
		gbc.gridy = 22;
		panel.add(b,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(b1,gbc);
		
		frame.add(panel);
		frame.setSize(600, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		
		
		
		Game g = new Game();
		Block b1 = new Block(1,1,2, "Horizontal", false);
	    Block b2 = new Block(3,3,3,"Horizontal", true);
		Block b3 = new Block(5,5, 2,"Vertical", false);
		Block[] arrb = new Block[3];
		arrb[0] = new Block(b1);
		arrb[1] = new Block(b2);
		arrb[2] = new Block(b3);
		Board b = new Board(arrb, arrb[0], 6 , 5,5, g);
		g.setBestTime("11:23");
		g.setBoard(b);
		JFrame frame = new JFrame("Unblock Me");
		frame.getContentPane().add(g);
		frame.setVisible(true);
		frame.setSize(400, 400);
		//frame.pack();
	}

}

