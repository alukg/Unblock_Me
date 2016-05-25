package GameComponents;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener , KeyListener
{
	private Block selected;
	private Block[] allBlocks;
	private int[][] arrBoard;
	private JLabel[][] labels;
	private int size;
	private final int finishi = 3;
	private final int finishj = 6;
	private Stack<Object[]> lastMove;

	
	

	public Board(Block[] allBlocks, Block selected, int size)
	{
		this.size = size;
		this.selected = selected;
		this.setLayout(new GridBagLayout());
		this.setSize(300, 300);
		this.setBackground(Color.YELLOW);
		this.allBlocks = new Block[allBlocks.length];
		for (int i = 0; i < allBlocks.length; i++) 
		{
			this.allBlocks[i] = new Block(allBlocks[i]);
		}
		CreateFrame(this.size, this.allBlocks);
		AddBlocks(this.allBlocks);
		this.setBorder(BorderFactory.createTitledBorder(
		       BorderFactory.createEtchedBorder(), "Board"));
		}
	
	public Board(Board b)
	{
		new Board(b.getAllBlocks(), b.getSelected(), b.getBoardSize());
	}
	public int getFinishj() 
	{
		return finishj;
	}
	public int getfinishi() {
		return finishi;
	}

	
	public Stack<Object[]> getLastMove() 
	{
		return lastMove;
	}
	public void setLastMove(Stack<Object[]> lastMove) 
	{
		this.lastMove = lastMove;
	}
	
	
	
	//-2 ���� �����
	//-1 ����
	//�� ���� ��� ���� ���� �"� ���� ������ �� ���� ����� �����
	private void CreateFrame(int size , Block[] allBlocks)
	{
		this.arrBoard = new int[size+2][size+2];
		for(int i = 0; i < size+2; i++)
		{
			for (int j = 0; j < size+2; j++)
			{
				if(i == 0 || j== 0 || i == size+1 || j == size+1)
					this.arrBoard[i][j] = -2;
				else
					this.arrBoard[i][j] = -1;
			}
		}
		for(int i = 0; i < this.allBlocks.length; i++)
		{
			Block tmp = this.allBlocks[i];
			int x = tmp.getMy_x();
			int y = tmp.getMy_y();
			for(int j = 0; j < tmp.getMy_length(); j++)
			{
				if(tmp.getMy_dir().equals("Horizontal"))
				{
					this.arrBoard[y][x + j] = i;
				}
				else
				{
					this.arrBoard[y + j][x] = i;
				}
			}
		}
	}
	private void PrintArray(int[][] arr)
	{
		for(int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr[0].length; j++)
			{
				System.out.print(arr[i][j]+ " ");
			}
			System.out.println();
		}
	}
	private void AddBlocks(Block[] allBlocks)
	{
		this.labels = new JLabel[this.size][this.size];
		for(int i= 0; i < this.size; i++)
		{
			for (int j = 0; j < this.size; j++) 
			{
				labels[i][j] = new JLabel();
			}
		}
		GridBagConstraints gbc;
		for (int i = 1; i < this.size+1; i++) 
		{
			for (int j = 1; j < this.size+1; j++) 
			{
				if(this.arrBoard[i][j] == -1)
				{
					gbc = new GridBagConstraints();
					if( j == this.finishj && i == this.finishi)
					{
						labels[i-1][j-1].setText("--->");
					}
					else
						labels[i-1][j-1].setIcon(new ImageIcon("Images/FreeSpace.jpg"));
					gbc.gridx = j;
					gbc.gridy = i;
					this.add(labels[i-1][j-1], gbc);
				}
				/*else
				{
					gbc = new GridBagConstraints();
					JLabel toAdd = new Block(this.allBlocks[this.arrBoard[i][j]]);
					if(((Block)(toAdd)).getMy_target())
						toAdd.setIcon(new ImageIcon("Images/BlockTarget.png"));
					else
						toAdd.setIcon(new ImageIcon("Images/Block.png"));
					gbc.gridx = i;
					gbc.gridy = j;
					//gbc.gridwidth = ((Block)(toAdd)).getMy_length();
					toAdd.addMouseListener(this);
					toAdd.addKeyListener(this);
					this.add(toAdd, gbc);
				}*/
			}
		}
		for (int i = 0; i < this.allBlocks.length; i++) 
		{
			gbc = new GridBagConstraints();
			JLabel toAdd = new Block(this.allBlocks[i]);
			if(((Block)(toAdd)).getMy_target())
				toAdd.setIcon(new ImageIcon("Images/BlockTarget.png"));
			else
				toAdd.setIcon(new ImageIcon("Images/Block.png"));
			gbc.gridx = ((Block)(toAdd)).getMy_x();
			gbc.gridy = ((Block)(toAdd)).getMy_y();
			if(((Block)(toAdd)).getMy_dir().equals("Horizontal"))
			{
				gbc.gridwidth = ((Block)(toAdd)).getMy_length();
				gbc.fill = GridBagConstraints.HORIZONTAL;
			}
			else
			{
				gbc.gridheight = ((Block)(toAdd)).getMy_length();
				gbc.fill = GridBagConstraints.HORIZONTAL;
			}
			toAdd.addMouseListener(this);
			toAdd.addKeyListener(this);
			this.add(toAdd, gbc);
		}
	}
	public void ChangeColor()
	{
		if(this.selected.getMy_target())
		{
			this.selected.setBackground(Color.red);
		}
		else
		{
			this.selected.setBackground(Color.gray);
		}
	}
		public int getBoardSize() {
			return size;
		}
		public Block getSelected() {
			return selected;
		}

		public void setSelected(Block selected) {
			this.selected = selected;
		}

		public Block[] getAllBlocks() 
		{
			Block[] ans = new Block[this.allBlocks.length];
			for (int i = 0; i < ans.length; i++) 
			{
				ans[i] = new Block(this.allBlocks[i]);
			}
			return ans;
		}

		public void setAllBlocks(Block[] allBlocks) {
			this.allBlocks = allBlocks;
		}

		public int[][] getArrBoard() {
			return arrBoard;
		}

		public void setArrBoard(int[][] arrBoard)
		{
			this.arrBoard = arrBoard;
		}
		

	public void MoveBlock(String dir)
	{
		int i = this.selected.getMy_y();
		int j = this.selected.getMy_x();
		int blockNum = this.arrBoard[i][j];
		int length = this.selected.getMy_length();
		String dirHorOrVer = this.selected.getMy_dir();
		if(dir.equals("right") && dirHorOrVer.equals("Horizontal") && this.arrBoard[i][j+length] == -1)
		{
				this.arrBoard[i][j] = -1;
				this.arrBoard[i][j+length] = blockNum;
				this.remove(selected);
				this.selected.moveRight();
				this.remove(this.labels[i-1][j+length-1]);
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridx = j;
				gbc.gridy = i;
				this.labels[i-1][j-1].setIcon(new ImageIcon("Images/FreeSpace.jpg"));
				this.add(this.labels[i-1][j-1],gbc);
				gbc.gridx = j+1;
				gbc.gridy = i;
				gbc.gridwidth = length;
				this.add(this.selected, gbc);
		}
		if(dir.equals("left") &&  dirHorOrVer.equals("Horizontal") && this.arrBoard[i][j-1] == -1)
		{
		this.arrBoard[i][j+length-1] = -1;
		this.arrBoard[i][j-1] = blockNum;
		this.remove(selected);
		this.selected.moveLeft();
		this.remove(this.labels[i-1][j-1-1]);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = j+length-1;
		gbc.gridy = i;
		this.labels[i-1][j+length-1-1].setIcon(new ImageIcon("Images/FreeSpace.jpg"));
		this.add(this.labels[i-1][j+length-1-1],gbc);
		gbc.gridx = j-1;
		gbc.gridy = i;
		gbc.gridwidth = length;
		this.add(this.selected, gbc);
		}
		if(dir.equals("up") && dirHorOrVer.equals("Vertical") && this.arrBoard[i-1][j] == -1)
		{
		this.arrBoard[i+length-1][j] = -1;
		this.arrBoard[i-1][j] = blockNum;
		this.remove(selected);
		this.selected.moveUp();
		this.remove(this.labels[i-1-1][j-1]);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = j;
		gbc.gridy = i+length -1;
		this.labels[i+length-1-1][j-1].setIcon(new ImageIcon("Images/FreeSpace.jpg"));
		this.add(this.labels[i+length-1-1][j-1],gbc);
		gbc.gridx = j;
		gbc.gridy = i-1;
		gbc.gridheight = length;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(this.selected, gbc);

		}
		if(dir.equals("down"))
			if(dirHorOrVer.equals("Vertical"))
				if(this.arrBoard[i+length][j] == -1)
		{
		this.arrBoard[i][j] = -1;
		this.arrBoard[i+length][j] = blockNum;
		this.remove(selected);
		this.selected.moveDown();
		this.remove(this.labels[i+length-1][j-1]);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = j;
		gbc.gridy = i;
		this.labels[i-1][j-1].setIcon(new ImageIcon("Images/FreeSpace.jpg"));
		this.add(this.labels[i-1][j-1],gbc);
		gbc.gridx = j;
		gbc.gridy = i+1;
		gbc.gridheight = length;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(this.selected, gbc);
		}
		if(this.arrBoard[finishi][finishj] > -1 && this.allBlocks[this.arrBoard[finishi][finishj]].getMy_target())
		{
			System.out.println("Finish game");
		}
		repaint();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		if(blockMoved)
		{
			
		/*int dirNum =-1;
			if(dir.equals("up"))
				dirNum=8;
			else if(dir.equals("down"))
				dirNum=2;
			else if(dir.equals("left"))
				dirNum=4;
			else if(dir.equals("right"))
				dirNum = 6;
			this.lastMove.push(new Object[x][y][dirNum]);
			
		}*/
		
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getSource() instanceof Block)
		{
			this.selected  = (Block)e.getSource();
			e.getComponent().setFocusable(true);
			e.getComponent().requestFocus();
		}
		
		}
	public void mouseEntered(MouseEvent e) {
		}
	@Override
	public void mouseExited(MouseEvent e) {


		
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{


	}
	@Override
	public void mouseReleased(MouseEvent e)
	{

	}
	
	public void keyPressed(KeyEvent e) 
	{
		 int keyCode = e.getKeyCode();
		 String dirToMove = "";
		    switch( keyCode ) 
		    { 
		        case KeyEvent.VK_UP:
		        	dirToMove = "up";
				    MoveBlock(dirToMove);   

		            break;
		        case KeyEvent.VK_DOWN:
		        	dirToMove="down";
				    MoveBlock(dirToMove);   

		        	break;
		        case KeyEvent.VK_LEFT:
		        	dirToMove="left";
				    MoveBlock(dirToMove);   

		            break;
		        case KeyEvent.VK_RIGHT :
		        	dirToMove="right";
				    MoveBlock(dirToMove);   

		            break;
		    }
			e.getComponent().setFocusable(true);
			e.getComponent().requestFocus();
	}
	public void keyReleased(KeyEvent e) {
		
	}
	public void keyTyped(KeyEvent e) {
		
	}

	
	
	
}