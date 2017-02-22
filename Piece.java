
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
	

	//Represent's a piece's reinforced status
	private boolean reinforced;
	
	public boolean isReinforced() {
		return reinforced;
	}
	
	public void setReinforced(boolean reinforceStatus) {
		reinforced = reinforceStatus;
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
	
	//Iterate through each piece of one color (white or black) and (expect pawn) look at whether or not each array
	//index of the board (0 - 7) can be moved to by a piece of that color; note white == 0, black == 1
	public ArrayList<Integer> generateAllMoves(int side, ArrayList<Piece> pieces) {
		ArrayList<Integer> allMoves = new ArrayList<Integer>();
		boolean foundMatch = false;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				foundMatch = false;
				for (int k = 0; k < pieces.size(); k++) {
					//If the piece in question is the side in question and can move to said position (and isn't pawn),
					//OR if the piece in question is a pawn and can take at the position in question
					if ((pieces.get(k).getSide() == side && pieces.get(k).canMoveTo(pieces.get(k).getX(), 
							pieces.get(k).getY(), i, j, pieces) && !(pieces.get(k) instanceof Pawn)) || 
							(pieces.get(k).getSide() == side && pieces.get(k) instanceof Pawn && 
							Pawn.canTake(pieces.get(k).getX(), pieces.get(k).getY(), i, j, pieces, pieces.get(k)))) {
						foundMatch = true;
						break;
					}
				}
				//If a piece of the side requested can move (and take) at the following square, then add it
				if (foundMatch) {
					allMoves.add(i);
					allMoves.add(j);
				}
			}
		}
	return allMoves;
	}
}
