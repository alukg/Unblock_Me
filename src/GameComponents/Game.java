package GameComponents;
import GameMenu.Controller;
import GameMenu.ModelClasses.JButtonWithIcon;
import GameMenu.ModelClasses.PanelModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;

public class Game extends PanelModel implements ActionListener
{
	private Board board;
	Controller controller;
	private Timer time;
	private String BestTime;
	private static int min=0,sec=0;
	private JLabel labelTimer;
	private JButton undo;
	JButton selectLevel;
	
	public Game()
	{
		/*undo = new JButtonWithIcon("design\\UndoPirate.jpg");
		selectLevel = new JButtonWithIcon("design\\returnPirate.jpg");
		selectLevel.setActionCommand("Select level");
		selectLevel.addActionListener(this);
		selectLevel.addActionListener(controller.new menuPress());
		labelTimer = new JLabel("00:00",SwingConstants.CENTER);
		labelTimer.setForeground(Color.WHITE);
		labelTimer.setFont(new Font(labelTimer.getFont().getFontName(),Font.BOLD,20));
		this.time = new Timer(1000,this);
		undo.addActionListener(this);
		menuPanel.add(labelTimer);
		menuPanel.add(undo);
		menuPanel.add(selectLevel);

		mainPanel.setBackground(new Color(24,99,131));*/
	}

	public Game(Controller controller, Block[] b, String BestTime)
	{
		this.BestTime= BestTime;
		this.controller = controller;
		undo = new JButtonWithIcon("design\\UndoPirate.jpg");
		selectLevel = new JButtonWithIcon("design\\returnPirate.jpg");
		selectLevel.setActionCommand("Select level");
		selectLevel.addActionListener(this);
		selectLevel.addActionListener(controller.new menuPress());
		JLabel labelBestTime = new JLabel("Best time is : "+this.BestTime,SwingConstants.CENTER);
		labelBestTime.setForeground(Color.WHITE);
		labelBestTime.setFont(new Font(labelBestTime.getFont().getFontName(),Font.BOLD,16));
		labelTimer = new JLabel("00:00",SwingConstants.CENTER);
		labelTimer.setForeground(Color.WHITE);
		labelTimer.setFont(new Font(labelTimer.getFont().getFontName(),Font.BOLD,20));
		this.time = new Timer(1000, this);
		undo.addActionListener(this);
		menuPanel.add(labelTimer);
		menuPanel.add(labelBestTime);
		menuPanel.add(undo);
		menuPanel.add(selectLevel);
		this.board = new Board(this, b);
		mainPanel.setLayout(null);
		mainPanel.add(this.board);
		this.time.start();


	}
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) 
	{
		this.board = board;
		mainPanel.add(this.board);
	}

	public String getBestTime() {
		return BestTime;
	}

	public void setBestTime(String bestTime)
	{
		BestTime = bestTime;
		JLabel labelbestTime = new JLabel("Best time is : "+this.BestTime);
		menuPanel.add(labelbestTime);
	}

	/*private class starChrono implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == time)
			{
				if (sec < 59)
				{
					sec++;
					String sec2 = (sec < 10 ? "0" : "") + sec;
					labelTimer.setText(min + ":" + sec2);
				} else
				{
					min++;
					sec = 0;
					String min2 = (min < 10 ? "0" : "") + min;
					labelTimer.setText(min2 + ":00");
				}
			}
		}
	}*/

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == time)
		{
			if (sec < 59) {
				sec++;
				String sec2 = (sec < 10 ? "0" : "") + sec;
				labelTimer.setText(min + ":" + sec2);
			} else {
				min++;
				sec = 0;
				String min2 = (min < 10 ? "0" : "") + min;
				labelTimer.setText(min2 + ":00");
			}
		}
		else
		{
			if(e.getSource() == undo)
			{
				this.board.undoFunction();
			}
			else
			{
				if(e.getSource() == selectLevel)
				{
					this.time.stop();
					min = 0;
					sec = 0;
				}
			}
		}
	}

	public void Finish()
	{
		System.out.println("stop timer");
		this.time.stop();
		String currentTime = this.labelTimer.getText();
		min = 0;
		sec = 0;
		this.controller.gameFinished(currentTime,BestTime);
	}
	
}
