package GameComponents;

import javax.swing.JLabel;


public class Block extends JLabel 
{
	private int x;
	private int y;
	private int length;
	private String dir;
	private Boolean target;//Saves whether the block is a target block or not.
	private Boolean Selected;
	
	
	
	//Constructors
	public Block(int x, int y, int length, String dir, Boolean target)
	{
		super();
		this.target = target;
		this.dir = dir;
		this.x = x;
		this.y = y;
		this.length = length;
		this.Selected = false;
	}
	public Block(Block b)
	{
		 super();
		 this.x = b.getX();
		 this.y = b.getY();
		 this.length = b.getLength();
		 this.dir = b.getDir();
		 this.target = b.getTarget();
		 this.Selected = false;
	}
	
	
	//Getters and Setters
	public Boolean getSelected() {
		return Selected;
	}
	public void setSelected(Boolean selected) {
		Selected = selected;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getLength() {
		return length;
	}
	public String getDir() {
		return dir;
	}
	public Boolean getTarget() {
		return target;
	}
	
	public void moveRight()
	{
		this.x = this.x + 1;
	}
	public void moveLeft()
	{
		this.x = this.x - 1;
	}
	public void moveUp()
	{
		this.y = this.y + 1;
	}
	public void moveDown()
	{
		this.y = this.y - 1;
	}
	
	

}