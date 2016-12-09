import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Piece extends PApplet{	
	//Starting positions for a given piece type
	private int startX;
	private int startY;
	
	//Current positions for a piece
	private int xPos;
	private int yPos;
	
	//Actual/pixel values for x and y positions
	protected int xValue;
	protected int yValue;
	
	//Boolean for whether a piece is being held
	protected boolean held;
	
	//Which side the piece is on (0 for white, 1 for black)
	private int side;
	
	//For duplicate pieces ex. 2x minor, 8x pawns <starts at 1>
	private int number;
	
	//Whether or not a current piece is still in play
	private boolean isAlive;
	
	//Holds the name of the image of the piece (color + piece type)
	private String imgName;
	private PImage img;
	
	//Returns if a piece is white (and conversely, is black)
	public boolean isWhite() {
		return side == 0;
	}
	
	//Required functionality for each piece to generate a set of possible moves
	//based on its subclass and current position, and store them in an ArrayList
	//as a 2-character String, e.g. "d5", "e3", "f2"
	abstract ArrayList<String> generateMoves(int x, int y);
	
	//Required functionality to detect if a piece can move to a position
	abstract boolean canMoveTo(int x, int y);
	
	//Method to move a piece using the helper method canMoveTo to check
	public void move(int x, int y) {
		if (this.canMoveTo(x, y)) {
			xPos = x;
			yPos = y;
		}
	}
	
	//Destroy a piece and remove from game, either via take or promotion
	public void destroy() {
		isAlive = false;
	}
	
	public void setStart(int x, int y) {
		startX = x;
		startY = y;
	}
	
	//Method to reset position to start, and reset it's alive field
	public void resetPosition() {
		xPos = startX;
		yPos = startY;
		isAlive = true;
	}
	
	//The following methods are for use in the derived classes:
	public int getX() { 
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public void setX(int x) {
		xPos = x;
	}
	
	public void setY(int y) {
		yPos = y;
	}
	
	//Note numbers run from left to right, from white's perspective
	public void setNumber(int num) {
		number = num;
	}
	
	public int getNumber() {
		return number;
	}

	public void setSide(int side) {
		this.side = side;
	}
	
	public int getSide() {
		return side;
	}
	
	//Following methods give each Piece child object its own individual
	//responsibility for drawing itself, which allows all Pieces
	//to be drawn with iterations via polymorphism
	public void setImage(String name) {
		imgName = name;
	}
	
	public String getImageName() {
		return imgName;
	}
	
	//Draws the Piece child with selected image at it's position on the board
	public void draw(ChessGame game) {
		//If a piece is not held, it's position is static
		if (!held) {
			xValue = ChessGame.leftCornerX + xPos * ChessGame.scale;
			yValue = ChessGame.leftCornerY - yPos * ChessGame.scale;
		}
		else { //If held, draw at mouse location
			xValue = game.mouseX - (ChessGame.scale / 2);
			yValue = game.mouseY - (ChessGame.scale / 2);
		}
		//Draw only if the piece is still in the game
		if (isAlive) {
			game.image(getImg(), xValue, yValue,
					ChessGame.scale, ChessGame.scale);
		}
	}

	public void setXValue(int x) {
		xValue = x;
	}

	public void setYValue(int y) {
		yValue = y;
	}
	
	public void setHeld(boolean heldStatus) {
		held = heldStatus;
	}
	
	public boolean isHeld() {
		return held;
	}
	
	public PImage getImg() {
		return img;
	}

	public void setImg(PImage img) {
		this.img = img;
	}
}
