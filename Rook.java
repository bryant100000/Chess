
import java.util.ArrayList;

public class Rook extends Piece{
	public Rook(int side, int number) {
		setSide(side);
		setNumber(number); 		
		//Set starting position (4 rooks in a game)
		if (isWhite()) {
			setImage("./ChessPieces/0rook.png");
			if (getNumber() == 1) {
				setStart(0, 0);
			}
			else {
				setStart(7, 0);
			}
		}
		else {
			setImage("./ChessPieces/1rook.png");
			if (getNumber() == 1) {
				setStart(0, 7);
			}
			else {
				setStart(7, 7);
			}
		}
		resetPosition();
	}

	//Method returns an ArrayList of acceptable moves about 
	//current position when called (x1, y1, x2, y2,....)
	@Override
	ArrayList<Integer> generateMoves(int x, int y, ArrayList<Piece> pieces) {
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		
		//Check all 4 directions, list out moves, then check for collisions
		for (int i = x + 1; i < 8; i++) { //Right side
			if (Chessboard.pieceAt(i, y, pieces) != null 
					&& Chessboard.pieceAt(i, y, pieces).getSide() == this.getSide()) {
				break;
			}
			else if (Chessboard.pieceAt(i, y, pieces) != null 
					&& Chessboard.pieceAt(i, y, pieces).getSide() != this.getSide()) {
				possibleMoves.add(i);
				possibleMoves.add(y);
				break;
			}
			//Otherwise add the indices to the lists while no collision
			possibleMoves.add(i);
			possibleMoves.add(y);
		}
		
		for (int i = x - 1; i >= 0; i--) { //Left side
			if (Chessboard.pieceAt(i, y, pieces) != null 
					&& Chessboard.pieceAt(i, y, pieces).getSide() == this.getSide()) {
				break;
			}
			else if (Chessboard.pieceAt(i, y, pieces) != null 
					&& Chessboard.pieceAt(i, y, pieces).getSide() != this.getSide()) {
				possibleMoves.add(i);
				possibleMoves.add(y);
				break;
			}
			possibleMoves.add(i);
			possibleMoves.add(y);
		}
		
		for (int i = y + 1; i < 8; i++) { //Upwards
			if (Chessboard.pieceAt(x, i, pieces) != null 
					&& Chessboard.pieceAt(x, i, pieces).getSide() == this.getSide()) {
				break;
			}
			else if (Chessboard.pieceAt(x, i, pieces) != null 
					&& Chessboard.pieceAt(x, i, pieces).getSide() != this.getSide()) {
				possibleMoves.add(x);
				possibleMoves.add(i);
				break;
			}
			possibleMoves.add(x);
			possibleMoves.add(i);
		}	
		
		for (int i = y - 1; i >= 0; i--) { //Downwards
			if (Chessboard.pieceAt(x, i, pieces) != null 
					&& Chessboard.pieceAt(x, i, pieces).getSide() == this.getSide()) {
				break;
			}
			else if (Chessboard.pieceAt(x, i, pieces) != null 
					&& Chessboard.pieceAt(x, i, pieces).getSide() != this.getSide()) {
				possibleMoves.add(x);
				possibleMoves.add(i);
				break;
			}
			possibleMoves.add(x);
			possibleMoves.add(i);
		}
		return possibleMoves;	
	}
}
