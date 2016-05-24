package GameComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GameComponents.Board;

import java.util.Stack;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;

public class Game extends JPanel
{
	private Board board;
	private Stack<Board> lastMove;
	private Timer time;
	private String BestTime;
	private static int min=0,sec=0;
	private JLabel labelTimer;
	
	public Game()
	{
		super();
		this.setLayout(new GridBagLayout());
		this.lastMove = new Stack<Board>();
		JButton buttonUndo = new JButton("Undo");
		//buttonUndo.add(new)
		labelTimer = new JLabel("00:00");
		this.time = new Timer(1000, new starChrono());
		this.time.start();
		//Add more Buttons		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(labelTimer, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		this.add(buttonUndo, gbc);
		
		//this.board.setFocusable(true);
		//gbc.gridx = 1;
		//gbc.gridy = 1;
		//this.add(this.board, gbc);
		//this.lastMove.push(new Board(this.board));

		this.setVisible(true);	
	}
	public Game(Board b, String BestTime)
	{
		//super("Unblock Me");
		super();
		this.lastMove = new Stack<Board>();
		this.BestTime= BestTime;
		//this.time = new Timer(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.board = new Board(b);
		//b.getSelected().setIcon(new ImageIcon("Images/BlockSelected"));
		JButton buttonUndo = new JButton("Undo");
		//buttonUndo.add(new)
		JLabel labelbestTime = new JLabel("Best time is : "+this.BestTime);
		labelTimer = new JLabel("00:00");
		this.time = new Timer(1000, new starChrono());
		this.time.start();
		//Add more Buttons		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(labelTimer, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(labelbestTime, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		this.add(buttonUndo, gbc);
		
		//this.board.setFocusable(true);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(this.board, gbc);
		//this.lastMove.push(new Board(this.board));

		this.setVisible(true);		
	}
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		//this.board.req
		this.add(this.board, gbc);
	}
	public String getBestTime() {
		return BestTime;
	}
	public void setBestTime(String bestTime) {
		BestTime = bestTime;
		JLabel labelbestTime = new JLabel("Best time is : "+this.BestTime);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(labelbestTime, gbc);	}

	private class starChrono implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(sec<59)
			{
				sec++;
				String sec2 = (sec<10?"0":"")+sec;
				labelTimer.setText(min+":"+sec2);
			}
			else
			{
				min++;
				sec = 0;
				String min2 = (min<10?"0":"")+min;
				labelTimer.setText(min2+":00");
			}
		}
	}
	public void GameFinished()
	{
		this.time.stop();
		String finishTime = this.labelTimer.getText();
		
	}

	

	

	

	
	
}
