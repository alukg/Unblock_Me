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
	public Stack<Object[]> getLastMove() {
		return lastMove;
	}
	public void setLastMove(Stack<Object[]> lastMove) {
		this.lastMove = lastMove;
	}
	private Stack<Object[]> lastMove;
	private Timer time;
	private String BestTime;
	private static int min=0,sec=0;
	private JLabel labelTimer;
	
	public Game()
	{
		this.lastMove = new Stack<Object[]>();
		this.setLayout(new GridBagLayout());
		this.lastMove = new Stack<Object[]>();
		JButton buttonUndo = new JButton("Undo");
		labelTimer = new JLabel("00:00");
		this.time = new Timer(1000, new starChrono());
		this.time.start();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(labelTimer, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		this.add(buttonUndo, gbc);
		
		this.setVisible(true);	
	}
	public Game(Board b, String BestTime)
	{
		this.lastMove = new Stack<Object[]>();
		this.BestTime= BestTime;
		this.board = new Board(b);
		this.setLayout(new GridBagLayout());
		JButton buttonUndo = new JButton("Undo");
		JLabel labelbestTime = new JLabel("Best time is : "+this.BestTime);
		labelTimer = new JLabel("00:00");
		this.time = new Timer(1000, new starChrono());
		this.time.start();
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
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(this.board, gbc);

		//this.board.setFocusable(true);
		this.setVisible(true);		
	}
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) 
	{
		this.board = board;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		//this.board.setVisible(true);
		//this.board.requestFocusInWindow();
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
		this.add(labelbestTime, gbc);	
		}

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
