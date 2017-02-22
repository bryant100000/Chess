//FIXME 
// - Add castling capability
// - Add enpassent capability
// - Add promotion capability
// - Add checking as a game mechanism
// - Add checkmate as a game mechanism
// - Add 3-fold repetition as a game mechanism

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
			//Note at the start we assume nothing is reinforced
			gamePieces.get(i).setReinforced(false);
			//and Initialize movesets
			gamePieces.get(i).setMoveSet(gamePieces.get(i).generateMoves
					(gamePieces.get(i).getX(), gamePieces.get(i).getY(), gamePieces));
		}
		
		canHold = true; //A piece can be held if no other piece is being held
	}
	
	public void mouseDragged() {
		boolean firstClick = true;
		//If the mouse is clicked
		if (mouseButton == LEFT && firstClick) {
			firstClick = false; //Don't call this repeatedly while dragging
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
		}
	}
	
	public void mouseReleased() {
		if (mouseButton == LEFT) {
			for (int i = 0; i < gamePieces.size(); i++) {
				if (gamePieces.get(i).isHeld()) {
					int x = (mouseX - leftCornerX) / scale;
					int y = (leftCornerY + scale - mouseY) / scale;
					
					//Set held value to false when piece is released
					gamePieces.get(i).setHeld(false);
					canHold = true;
					
					//If the piece is dropped over a square it can move to, then
					//move the piece to that square
					if (gamePieces.get(i).canMoveTo(gamePieces.get(i).getX(), 
							gamePieces.get(i).getY(), x, y, gamePieces)) {
						gamePieces.get(i).move(gamePieces.get(i).getX(), 
								gamePieces.get(i).getY(), x, y, gamePieces);
						
						//And after the board changes, reset movesets
						for (int k = 0; k < gamePieces.size(); k++) {
							//Reset movesets
							gamePieces.get(k).setMoveSet(gamePieces.get(k).generateMoves
									(gamePieces.get(k).getX(), gamePieces.get(k).getY(), gamePieces));
						}
					}
				}
			}
		}
	}
	
	public void draw() {
		background(175);
		
		Piece drawLast = null;
		//Draw the empty chessboard
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size(); j++) {
				//Set color to be dark or white
				if (board.isWhite(i, j)) {
					fill(250, 250, 230);
				}
				else {
					fill(140, 120, 110);
				}
				//Draw a grid space at each position, going from bottom
				//left across, then up a row, repeating
				stroke(125);
				rect(leftCornerX + i * scale, 
					leftCornerY - j * scale, scale, scale);
				
				//If the player is currently holding a piece that can move there
				for (int k = 0; k < gamePieces.size(); k++) {
					if (gamePieces.get(k).isHeld()) {
						if (gamePieces.get(k).canMoveTo(gamePieces.get(k).getX(),
								gamePieces.get(k).getY(), i, j, gamePieces)) {	
							//Highlight/contrast that square to the player
							if (board.isWhite(i, j)) {
								fill(235, 225, 240);
							}
							else {
								fill(160, 140, 130);
							}
							stroke(75);
							rect(leftCornerX + i * scale, 
									leftCornerY - j * scale, scale, scale);
						}
						//Then reference it for later
						drawLast = gamePieces.get(k);
					}
				}
			}
		}

		//Then draw the pieces at their current locations, if not held
		for (int i = 0; i < gamePieces.size(); i++) {
			if (!gamePieces.get(i).isHeld()) {
				gamePieces.get(i).draw(this);
			}
		}
		
		//If a piece was held, draw it last so it is drawn over other pieces
		if (drawLast != null) {
			drawLast.draw(this);
		}
		
		//FIXME debugging text
		text("" + (mouseX - leftCornerX) / scale + ", " 
				+ ( ( (leftCornerY + scale - mouseY) / scale ) ), 
				25, 25);
		text(leftCornerY + scale - mouseY, 55, 25);
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
