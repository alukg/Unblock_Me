package GameComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GameMenu.PanelModel;

import java.util.Stack;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;

public class Game extends PanelModel
{
	private Board board;
	private Stack<Board> lastMove;
	private Timer time;
	private String BestTime;
	private static int min=0,sec=0;
	private JLabel labelTimer;
	private JButton undo;
	
	public Game()
	{
		this.lastMove = new Stack<Board>();
		undo = new JButton("Undo");
		labelTimer = new JLabel("00:00",SwingConstants.CENTER);
		labelTimer.setForeground(Color.WHITE);
		labelTimer.setFont(new Font(labelTimer.getFont().getFontName(),Font.BOLD,20));
		this.time = new Timer(1000, new starChrono());
		this.time.start();

		menuPanel.add(labelTimer);
		menuPanel.add(undo);
		
		//this.board.setFocusable(true);
		//gbc.gridx = 1;
		//gbc.gridy = 1;
		//this.add(this.board, gbc);
		//this.lastMove.push(new Board(this.board));
	}

	public Game(Board b, String BestTime)
	{
		this.lastMove = new Stack<Board>();
		this.BestTime= BestTime;
		//this.time = new Timer(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.board = new Board(b);
		//b.getSelected().setIcon(new ImageIcon("Images/BlockSelected"));
		undo = new JButton("Undo");
		//buttonUndo.add(new)
		JLabel labelBestTime = new JLabel("Best time is : "+this.BestTime);
		labelTimer = new JLabel("00:00",SwingConstants.CENTER);
		labelTimer.setForeground(Color.WHITE);
		labelTimer.setFont(new Font(labelTimer.getFont().getFontName(),Font.BOLD,20));
		this.time = new Timer(1000, new starChrono());
		this.time.start();
		//Add more Buttons

		menuPanel.add(labelTimer);
		menuPanel.add(labelBestTime);
		menuPanel.add(undo);
		
		//this.board.setFocusable(true);
		mainPanel.add(this.board);
		//this.lastMove.push(new Board(this.board));
	}
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
		mainPanel.add(this.board);
	}

	public String getBestTime() {
		return BestTime;
	}

	public void setBestTime(String bestTime) {
		BestTime = bestTime;
		JLabel labelbestTime = new JLabel("Best time is : "+this.BestTime);
		menuPanel.add(labelbestTime);
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
