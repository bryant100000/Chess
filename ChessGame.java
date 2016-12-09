import java.util.ArrayList;

import processing.core.PApplet;

public class ChessGame extends PApplet{
	private static Chessboard board;
	private static ArrayList<Piece> gamePieces;
	
	protected static int scale;
	private static int dimensions;
	
	protected static int leftCornerX;
	protected static int leftCornerY;
	
	private boolean canHold;
	
	public void settings() {
		//Made so that changing the dimensions field initialization
		//will change the scale of the entire game ** note above
		//around 400-450 will make the pieces resolution too low
		dimensions = 300;
		size(dimensions, dimensions);
	}
	
	public void setup() {
		//scale corresponds to pixel scale of each grid space, and offset
		//is the pixel distance between the edge of the window and the board
		scale = dimensions / 10;
		
		//Convenient variables for the coordinates of the left corner
		//of the bottom left corner gridspace, or "a1" of the board
		leftCornerX = scale;
		leftCornerY = dimensions - 2 * scale;
		
		for (int i = 0; i < gamePieces.size(); i++) {
			gamePieces.get(i).
				setImg(loadImage(gamePieces.get(i).getImageName()));
		}
		
		canHold = true; //A piece can be held if no other piece is being held
	}
	
	public void mouseDragged() {
		//If the mouse is clicked
		if (mouseButton == LEFT) {
			for (int i = 0; i < gamePieces.size(); i++) {
				if (mouseX > leftCornerX + scale * gamePieces.get(i).getX()
						&& mouseX < leftCornerX + scale * (gamePieces.get(i).getX() + 1)
						&& mouseY > leftCornerY - scale * gamePieces.get(i).getY()
						&& mouseY < leftCornerY - scale * (gamePieces.get(i).getY() - 1)
						&& canHold) {
					//And it is over a game-tile, pick up that piece
					gamePieces.get(i).setHeld(true);
					canHold = false;
				}
			}
		} //FIXME: Non-white-backline pieces aren't being dragged
	}
	
	public void mouseReleased() {
		if (mouseButton == LEFT) {
			for (int i = 0; i < gamePieces.size(); i++) {
				if (gamePieces.get(i).isHeld()) {
					//Set held value to false when piece is releases
					gamePieces.get(i).setHeld(false);
					canHold = true;
				}
			}
		}
	}
	
	public void draw() {
		background(175);
		
		int color = 0;
		//Draw the empty chessboard
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size(); j++) {
				//Set color to be black or white
				if (board.isWhite(i, j)) {
					color = 255;
				}
				else {
					color = 100;
				}
				
				//Draw a grid space at each position, going from bottom
				//left across, then up a row, repeating
				fill(color);
				stroke(0);
				rect(leftCornerX + i * scale, 
					leftCornerY - j * scale, scale, scale);
			}
		}
		
		
		//Then draw the pieces at their current locations
		for (int i = 0; i < gamePieces.size(); i++) {
			gamePieces.get(i).draw(this);
		}
		
		//For debugging FIXME
		text(mouseX + ", " + mouseY, 25, 25);
		text(gamePieces.get(9).getX() + ", " + gamePieces.get(9).getY(), 100, 25);
	} 
	
	public static void main(String[] args) {
		//Initialize a new board to play the game on
		board = new Chessboard();
		
		//Hold all piece objects of the game in the ArrayList
		//for easy drawing/iterating
		gamePieces = new ArrayList<Piece>();
		
		//1st outer loop will make all of white's pieces, 
		//2nd will make all of black's pieces
		for (int i = 0; i < 2; i++) {
			//Inner loops make pieces i.e. below makes 8 pawns for both sides
			for (int j = 1; j <= 8; j++) {
				gamePieces.add( new Pawn(i, j) );
			}
			for (int j = 1; j <= 2; j++) {
				gamePieces.add( new Rook(i, j) );
			}
			for (int j = 1; j <= 2; j++) {
				gamePieces.add( new Knight(i, j) );
			}
			for (int j = 1; j <= 2; j++) {
				gamePieces.add( new Bishop(i, j) );
			}
			for (int j = 1; j <= 1; j++) {
				gamePieces.add( new Queen(i, j) );
			}
			for (int j = 1; j <= 1; j++) {
				gamePieces.add( new King(i, j) );
			}
		}
		
		PApplet.main("ChessGame");
	}
}
