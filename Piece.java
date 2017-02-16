
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
	
	//Holds the moveset for the piece; updated to minimize calculations
	private ArrayList<Integer> moveSet = new ArrayList<Integer>();
	
	//The hasMoved boolean should always be initialized to false, since a
	//new piece hasn't moved
	private boolean hasMoved = false;
	
	public boolean moved() {
		return hasMoved;
	}
	
	//Returns if a piece is white (and conversely, is black)
	public boolean isWhite() {
		return side == 0;
	}
	
	//Required functionality for each piece to generate a set of possible moves
	//based on its subclass and current position, and store them in an ArrayList
	//as an indexed set of integers, in order of alternating x and y
	abstract ArrayList<Integer> generateMoves(int x, int y, ArrayList<Piece> pieces);

	//Sets the moveSet to a new referenced ArrayList
	public void setMoveSet(ArrayList<Integer> moves) {
		this.moveSet = moves;
	}
	
	public ArrayList<Integer> getMoveSet() {
		return this.moveSet;
	}
	
	//Required functionality to detect if a piece can move to a position
	public boolean canMoveTo(int currX, int currY, int x, int y, ArrayList<Piece> pieces) {
		boolean canMove = false;
		//If the position the user tries to move to is within the returned
		//alternating ArrayList, tell them they can move to that space
		for (int i = 0; i < moveSet.size(); i += 2) {
			if (moveSet.get(i) == x && moveSet.get(i + 1) == y) {
				canMove = true;
			} 
		}
		return canMove;
	}
	
	//Method to move a piece using the helper method canMoveTo to check
	public void move(int currX, int currY, int x, int y, ArrayList<Piece> pieces) {
		//If a piece is at the moved-to space, remove that piece from the game;
		//the move method should only be called to move onto another piece if it 
		//is an attack move and the other piece is an enemy
		if (Chessboard.pieceAt(x, y, pieces) != null
				&& this.canMoveTo(currX, currY, x, y, pieces)) {
			pieces.remove(Chessboard.pieceAt(x, y, pieces));
			xPos = x;
			yPos = y;
		}
		//Or move this piece to that space normally
		else if (this.canMoveTo(currX, currY, x, y, pieces)) {
			xPos = x;
			yPos = y;
		}
		for (int i = 0; i < pieces.size(); i++) {
			//Re-reference all movesets to their new, updated movesets as the result
			//of the moved piece's change to the board
			pieces.get(i).setMoveSet(pieces.get(i).generateMoves(pieces.get(i).getX(),
				pieces.get(i).getY(), pieces));
		}
		if (!hasMoved) { //If the piece hasn't moved, record that it has now
			hasMoved = true;
		}
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
