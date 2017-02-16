import java.util.ArrayList;

/* TODO
 * 
 * - Decide on dimensions for board, grid spaces
 * - Use 2D Array to hold board spaces
 * - 
 * - Draw board, remember to alternate colors <ensure white/black correct sides>
 * - Use processing to draw what pieces will look like, to scale
 * 
 * - Make a piece class w/methods and fields + accessors, mutators
 * - Associate each piece with its image
 * - Implement draw method to set the board to original state <white perspective>
 * 
 * - Go through each piece and extend piece, <HARD> create movement methods
 * (remember white/black pawns move different AND 1st pawn move 1 or 2 spaces) 
 * - Take into account moving out of bounds and don't allow it
 * 
 * - Implement checking, checkmate, win/loss for each player
 * - Implement special movement methods (en passent, castle K/Q side)
 * 
 * - Create a menu w/2 buttons in Processing, 1 perspective, 2 perspective
 * - Add implementation for 1 perspective for now
 */

public class Chessboard {
	//gameBoard array that holds integers corresponding to square color
	private int[][] gameBoard;
	
	//All chessboards are 8x8.
	private final int dimension = 8;
	
	//Chessboards start with a black square at "a1", bottom left, and alternate;
	//For a zero-based 2-D array chessboard, (0, 0) will represent "a1"
	public Chessboard() {
		gameBoard = new int[dimension][dimension];
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				//Like pieces, a value of zero will represent white squares
				//while a value of one will represent black squares
				if ( (i + j) % 2 == 0 ) {
					gameBoard[i][j] = 1;
				}
				else {
					gameBoard[i][j] = 0;
				}
			}
		}
	}
	
	//Returns whether or not a gridspace is white
	public boolean isWhite(int x, int y) {
		return gameBoard[x][y] == 0;
	}
	
	//Returns the board's size on either axis
	public int size() {
		return dimension;
	}
	
	//Returns a piece at a given index set 
	//(use hasPiece first to make sure returns properly)
	public static Piece pieceAt(int x, int y, ArrayList<Piece> pieces) {
		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).getX() == x && pieces.get(i).getY() == y) {
				return pieces.get(i);
			}
		}
		//Otherwise return null
		return null;
	}
}
