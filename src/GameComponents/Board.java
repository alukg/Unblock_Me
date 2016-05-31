package GameComponents;

import com.sun.java.swing.plaf.windows.WindowsBorders;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.StrokeBorder;

public class Board extends JPanel implements MouseListener , KeyListener
{
	private Game game;
	private Block selected;
	private Block[] allBlocks;
	private int[][] arrBoard;
	private EmptySpace[][] labels;
	private final int size = 6;
	private final int finishi = 3;
	private final int finishj = 6;
	private Stack<Object[]> lastMove;

	private ImageIcon freeSpaceIcon = new ImageIcon("design//emptySpaceTrans.png");
	private ImageIcon horizontal2shipIcon = new ImageIcon("design/ships/horizontal2ship.png");
	private ImageIcon horizontal3shipIcon = new ImageIcon("design/ships/horizontal3ship.png");
	private ImageIcon vertical2shipIcon = new ImageIcon("design/ships/vertical2ship.png");
	private ImageIcon vertical3shipIcon = new ImageIcon("design/ships/vertical3ship.png");
	private ImageIcon targetShipIcon = new ImageIcon("design/ships/targetShip.png");

	private Image boardBackground;

	public Board(Game game, Block[] allBlocks) {
		this.lastMove = new Stack<Object[]>();
		//this.size = size;
		this.game = game;
		this.setLayout(new GridBagLayout());
		this.setSize(350, 350);
		this.setBackground(new Color(24, 99, 131));
		this.allBlocks = new Block[allBlocks.length];
		for (int i = 0; i < allBlocks.length; i++) {
			this.allBlocks[i] = new Block(allBlocks[i]);
		}
		this.selected = this.allBlocks[0];
		CreateFrame(this.size, this.allBlocks);
		AddBlocks(this.allBlocks);
		this.selected.setFocusable(true);
		this.selected.requestFocus();

		//Add background to the panel
		File file = new File("design\\boardBackground.png");
		try {
			boardBackground = ImageIO.read(file);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(boardBackground, 0, 0, null); // Draw the background image.
	}
	
	public Board(Board b)
	{
		new Board(b.getGame() , b.getAllBlocks());
	}
	public Game getGame() {
		return game;
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
		this.labels = new EmptySpace[this.size][this.size];
		for(int i= 0; i < this.size; i++)
		{
			for (int j = 0; j < this.size; j++)
			{
				labels[i][j] = new EmptySpace();
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
					//if( j == this.finishj && i == this.finishi)
					//{
						//labels[i-1][j-1].setText("--->");
					//}
					//else

					gbc.gridx = j;
					gbc.gridy = i;
					this.add(labels[i-1][j-1], gbc);
				}
				
			}
		}
		for (int i = 0; i < this.allBlocks.length; i++) 
		{

			gbc = new GridBagConstraints();
			JLabel toAdd = new Block(this.allBlocks[i]);
			gbc.gridx = ((Block)(toAdd)).getMy_x();
			gbc.gridy = ((Block)(toAdd)).getMy_y();
			if(((Block)(toAdd)).getMy_dir().equals("Horizontal"))
			{
				gbc.gridwidth = ((Block)(toAdd)).getMy_length();
				gbc.fill = GridBagConstraints.HORIZONTAL;
				if(((Block)(toAdd)).getMy_target()) {
					toAdd.setPreferredSize(new Dimension(116,58));
					toAdd.setIcon(targetShipIcon);
					//toAdd.setBorder(BorderFactory.createLineBorder(Color.green));
				}
				else
				{
					if(gbc.gridwidth == 3) {
						toAdd.setPreferredSize(new Dimension(174, 58));
						toAdd.setIcon(horizontal3shipIcon);
					}
					else {
						toAdd.setPreferredSize(new Dimension(116, 58));
						toAdd.setIcon(horizontal2shipIcon);
					}
				}
			}
			else
			{
				gbc.gridheight = ((Block)(toAdd)).getMy_length();
				gbc.fill = GridBagConstraints.VERTICAL;
				if(gbc.gridheight == 3) {
					toAdd.setPreferredSize(new Dimension(58, 174));
					toAdd.setIcon(vertical3shipIcon);
				}
				else {
					toAdd.setPreferredSize(new Dimension(58, 116));
					toAdd.setIcon(vertical2shipIcon);
				}
			}
			toAdd.addMouseListener(this);
			toAdd.addKeyListener(this);
			this.add(toAdd, gbc);
			//this.selected.setBorder(BorderFactory.createLineBorder(Color.green));
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
		

		public void undoFunction()
		{
			if(!(this.lastMove.isEmpty()))
			{
			Object[] tmp = this.lastMove.pop();
			setSelected((Block)tmp[0]);
			String dirToGo = (String)tmp[1];
			if(dirToGo.equals("up"))
				dirToGo = "down";
			else if(dirToGo.equals("down"))
				dirToGo = "up";
			else if(dirToGo.equals("left"))
				dirToGo = "right";
			else if(dirToGo.equals("right"))
				dirToGo = "left";
			MoveBlock(dirToGo);
			}
			else
			{
				System.out.println("There are no last moves");
			}
		}

	public boolean MoveBlock(String dir)
	{
		boolean moveDone = false;
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
				this.add(this.labels[i-1][j-1],gbc);
				gbc.gridx = j+1;
				gbc.gridy = i;
				gbc.gridwidth = length;
				this.add(this.selected, gbc);
				moveDone = true;
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
		this.add(this.labels[i-1][j+length-1-1],gbc);
		gbc.gridx = j-1;
		gbc.gridy = i;
		gbc.gridwidth = length;
		this.add(this.selected, gbc);
		moveDone = true;

		}
		if(dir.equals("up") && dirHorOrVer.equals("Vertical") && this.arrBoard[i-1][j] == -1)
		{
		this.arrBoard[i+length-1][j] = -1;
		this.arrBoard[i-1][j] = blockNum;
		this.remove(this.labels[i-1-1][j-1]);
		this.remove(selected);
		this.selected.moveUp();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = j;
		gbc.gridy = i+length -1;
		this.add(this.labels[i+length-1-1][j-1],gbc);
		gbc.gridx = j;
		gbc.gridy = i-1;
		gbc.gridheight = length;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(this.selected, gbc);
		moveDone = true;


		}
		if(dir.equals("down") && dirHorOrVer.equals("Vertical") && this.arrBoard[i+length][j] == -1)
		{
		this.arrBoard[i][j] = -1;
		this.arrBoard[i+length][j] = blockNum;
		this.remove(this.labels[i+length-1][j-1]);
		this.remove(selected);
		this.selected.moveDown();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = j;
		gbc.gridy = i;
		this.add(this.labels[i-1][j-1],gbc);
		gbc.gridx = j;
		gbc.gridy = i+1;
		gbc.gridheight = length;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(this.selected, gbc);
		moveDone = true;

		}
		if(this.arrBoard[finishi][finishj] > -1 && this.allBlocks[this.arrBoard[finishi][finishj]].getMy_target())
		{
			System.out.println("Finish game");
			this.game.Finish();
		}
		revalidate();
		repaint();
		return moveDone;
		}
		
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getSource() instanceof Block)
		{
			this.selected.setBorder(BorderFactory.createEmptyBorder());
			this.selected  = (Block)e.getSource();
			this.selected.setBorder(BorderFactory.createLineBorder(Color.green));
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
		    if(MoveBlock(dirToMove))
				{
				Object[] toPush = new Object[2];
				toPush[0] = this.selected;
				toPush[1] = dirToMove;
				this.lastMove.push(toPush);
				}
			e.getComponent().setFocusable(true);
			e.getComponent().requestFocus();
	}
	public void keyReleased(KeyEvent e) {
		
	}
	public void keyTyped(KeyEvent e) {
		
	}

	private class EmptySpace extends JLabel {

		public EmptySpace(){
			super();
			setPreferredSize(new Dimension(58,58));
			//setBorder(BorderFactory.createLineBorder(Color.white));
			setIcon(freeSpaceIcon);
		}

	}
	
}