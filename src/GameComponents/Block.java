package GameComponents;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.peer.KeyboardFocusManagerPeer;

import javax.swing.JLabel;

/**
 * The class that represents a block in the game.
 *
 */
public class Block extends JLabel 
{
	//Variables
	private int my_x;
	private int my_y;
	private int my_length;
	private String my_dir;
	private Boolean my_target;//Saves whether the block is a target block or not.
	private Boolean my_Selected;
	
	
	
	//Constructors
	public Block(int x, int y, int length, String dir, Boolean target)
	{
		super();
		this.my_target = target;
		this.my_dir = dir;
		this.my_x = x;
		this.my_y = y;
		this.my_length = length;
		this.my_Selected = false;
			}
	public Block(Block b)
	{
		 super();
		 this.my_x = b.getMy_x();
		 this.my_y = b.getMy_y();
		 this.my_length = b.getMy_length();
		 this.my_dir = b.getMy_dir();
		 this.my_target = b.getMy_target();
		 this.my_Selected = false;
	}
	
	
	
	//Getters and Setters
	public int getMy_x() {
		return my_x;
	}
	public void setMy_x(int my_x) {
		this.my_x = my_x;
	}
	public int getMy_y() {
		return my_y;
	}
	public void setMy_y(int my_y) {
		this.my_y = my_y;
	}
	public int getMy_length() {
		return my_length;
	}
	public void setMy_length(int my_length) {
		this.my_length = my_length;
	}
	public String getMy_dir() {
		return my_dir;
	}
	public void setMy_dir(String my_dir) {
		this.my_dir = my_dir;
	}
	public Boolean getMy_target() {
		return my_target;
	}
	public void setMy_target(Boolean my_target) {
		this.my_target = my_target;
	}
	public Boolean getMy_Selected() {
		return my_Selected;
	}
	public void setMy_Selected(Boolean my_Selected) {
		this.my_Selected = my_Selected;
	}

	/**
	 * The function updates the indexes of the block after a movement to the right.
	 **/
	public void moveRight()
	{
		this.my_x = this.my_x + 1;
	}
	/**
	 * The function updates the indexes of the block after a movement to the left.
	 ***/
	public void moveLeft()
	{
		this.my_x = this.my_x - 1;
	}
	/**
	 * The function updates the indexes of the block after up movement.
	 ***/
	public void moveUp()
	{
		this.my_y = this.my_y - 1;
	}
	/**
	 * The function updates the indexes of the block after down movement.
	 ***/
	public void moveDown()
	{
		this.my_y = this.my_y + 1;
	}
	
	
	
	

}