package GameMenu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener, KeyListener
{
	private Block selected;
	private Block[] allBlocks;
	private Game game;
	private int[][] arrBoard;//free 0 , occupied 1
	private int size;
	private final int finishx = 3;
	private final int finishy = 3;
	
	
	
	public int getFinishx() {
		return finishx;
	}
	public int getFinishy() {
		return finishy;
	}

	public Board(Board b)
	{
		super();
		this.game = b.getGame();
		this.size = b.getBoardSize();
		this.selected = b.getSelected();
		this.setLayout(new GridBagLayout());
		this.setSize(100, 100);
		this.setBackground(Color.yellow);
		this.allBlocks = new Block[b.getAllBlocks().length];
		for (int i = 0; i < allBlocks.length; i++) 
		{
			this.allBlocks[i] = new Block(b.getAllBlocks()[i]);
		}
		CreateFrame(this.size, this.allBlocks);
		AddBlocks(this.allBlocks);
		//this.setBorder(BorderFactory.createTitledBorder(
		//        BorderFactory.createEtchedBorder(), "Board"));
		this.setVisible(true);
		this.addKeyListener(this);
	}
	
	public Game getGame() {
		return game;
	}
	public Board(Block[] allBlocks, Block selected, int size, int finishx, int finishy, Game game)
	{
		super();
		this.game = game;
		this.size = size;
		this.selected = selected;
		this.setLayout(new GridBagLayout());
		this.setSize(300, 300);
		this.setBackground(Color.GREEN);
		this.allBlocks = new Block[allBlocks.length];
		for (int i = 0; i < allBlocks.length; i++) 
		{
			this.allBlocks[i] = new Block(allBlocks[i]);
		}
		CreateFrame(this.size, this.allBlocks);
		AddBlocks(this.allBlocks);
		//this.setBorder(BorderFactory.createTitledBorder(
		//       BorderFactory.createEtchedBorder(), "Board"));
		this.setVisible(true);
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
		PrintArray(this.arrBoard);
		System.out.println("After");
		for(int i = 0; i < this.allBlocks.length; i++)
		{
			Block tmp = this.allBlocks[i];
			int x = tmp.getMy_x();
			int y = tmp.getMy_y();
			//this.arrBoard[x][y] = i;
			for(int j = 0; j < tmp.getMy_length(); j++)
			{
				if(tmp.getMy_dir().equals("Horizontal"))
				{
					this.arrBoard[x + j][y] = i;
				}
				else
				{
					this.arrBoard[x][y + j] = i;
				}
			}
		}
		PrintArray(this.arrBoard);
		
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
		GridBagConstraints gbc;
		for (int i = 1; i < this.size+1; i++) 
		{
			for (int j = 1; j < this.size+1; j++) 
			{
				if(this.arrBoard[i][j] == -1)
				{
					gbc = new GridBagConstraints();
					JLabel free = new JLabel();
					free.setIcon(new ImageIcon("Images/FreeSpace.jpg"));
					gbc.gridx = i;
					gbc.gridy = j;
					this.add(free, gbc);
				}
				
				//
				else
				{
					gbc = new GridBagConstraints();
					JLabel toAdd = new Block(this.allBlocks[this.arrBoard[i][j]]);
					toAdd.setIcon(new ImageIcon("Images/Block.png"));
					gbc.gridx = i;
					gbc.gridy = j;
					toAdd.addMouseListener(this);
					toAdd.addKeyListener(this);
					this.add(toAdd, gbc);
				}
				
			}
		}
			/*for (int i = 0; i < this.allBlocks.length; i++)
			{
					Block tmp = allBlocks[i];
					System.out.println("i:"+i+" x:"+ allBlocks[i].getMy_x()+" y:"+ allBlocks[i].getMy_y() );
					gbc = new GridBagConstraints();
					JLabel toAdd = new Block(tmp);
					toAdd.setIcon(new ImageIcon("Images/Block.png"));*/

					//if(tmp.getDir().equals("Horizontal"))
					//{
					//	gbc.fill = GridBagConstraints.HORIZONTAL;
					//	gbc.gridwidth = tmp.getLength();

					//}
					//else
					//{
					//	gbc.fill = GridBagConstraints.VERTICAL;
					//	gbc.gridheight = tmp.getLength();
					//	
					//}
					//if(tmp.getTarget())
					//{
					//	toAdd.setIcon(new ImageIcon("Images//BlockTarget.png"));
						//toAdd.setBackground(Color.RED);
					//}
					//else
					//{
					//	toAdd.setIcon(new ImageIcon("Images//Block.png"));
						//toAdd.setBackground(Color.gray);
					//}
					//if(tmp.equals(this.selected))
					//{
					//	toAdd.setIcon(new ImageIcon("Images//BlockSelected.png"));
					    //toAdd.setBackground(Color.black);
					//}
					//toAdd.setBorder(BorderFactory.createLineBorder(Color.black));
					//toAdd.addMouseListener(this);
					//toAdd.addKeyListener(this);
					//System.out.println("i:"+i+" x:"+ tmp.getMy_x()+" y:"+tmp.getMy_y() );

					//gbc.gridx = tmp.getMy_x();				
					//gbc.gridy = tmp.getMy_y();

					//System.out.println("i:"+i+" x:"+ gbc.gridx+" y:"+gbc.gridy );
					
					//this.add(toAdd, gbc);
				//}	
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
		int x= this.selected.getMy_x();
		int y = this.selected.getMy_y();
		int blockNum = this.arrBoard[x][y];
		int length = this.selected.getMy_length();
		String dirHorOrVer = this.selected.getMy_dir();
		switch(dir)
		{
		case "right" :
			if(dirHorOrVer.equals("Vertical")) 
			{
				//the block is not horizontal
			}
			if(arrBoard[x + length][y] == -1 )//free place to go
			{
				this.selected.moveRight();
				arrBoard[x + length][y] = blockNum;
				arrBoard[x][y] = -1;
				
			}
		case "left" :
			if(dirHorOrVer.equals("Vertical")) 
			{
				//the block is not horizontal
			}
			if(arrBoard[x-1][y] == -1 )//free place to go
			{
				this.selected.moveLeft();
				arrBoard[x-1][y] = blockNum;
				arrBoard[x+ length-1][y] = -1;
			}
		case "up" :
			if(dirHorOrVer.equals("Horizontal")) 
			{
				//the block is not vertical
			}
			if(arrBoard[x][y + 1 ] == -1 )//free place to go
			{
				this.selected.moveUp();
				arrBoard[x][y +1 ] = blockNum;
				arrBoard[x][y - length  + 1] = -1; 
			}
		case "down" :
			if(dirHorOrVer.equals("Horizontal")) 
			{
				//the block is not vertical
			}
			if(arrBoard[x][y - length] == -1 )//free place to go
			{
				this.selected.moveDown();
				arrBoard[x][y - length] = blockNum;
				arrBoard[x][y] = -1;
			}
		}
		if(this.arrBoard[finishx][finishy] > -1)
		{
			this.game.GameFinished();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getSource() instanceof Block)
		{
			((Block)e.getSource()).setMy_Selected(true);
			this.selected = (Block)e.getSource();
			System.out.println("Mouse clicked on block x:"+ this.selected.getMy_x()+"  y:"+this.selected.getMy_y());
		}
		//this.setFocusable(true);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		System.out.println("Moving selected block from x:"+ this.selected.getMy_x()+"  y:"+this.selected.getMy_y());

		 int keyCode = e.getKeyCode();
		 String dirToMove = "";
		    switch( keyCode ) 
		    { 
		        case KeyEvent.VK_UP:
		        	dirToMove = "up";
		            break;
		        case KeyEvent.VK_DOWN:
		        	dirToMove="down";
		        	break;
		        case KeyEvent.VK_LEFT:
		        	dirToMove="left";
		            break;
		        case KeyEvent.VK_RIGHT :
		        	dirToMove="right";
		            break;
		    }
		    MoveBlock(dirToMove);
		    repaint();
		    
		    
		    //Check if the game is finished
		    if(this.selected.getMy_target())
		    {
		    	//Target Block is always horizontal and finishy and y are the same.
		    	if(this.selected.getMy_x() + this.selected.getMy_length() >= this.finishx)
		    	{
		    		//game finished
		    	}
		    }
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}