
import java.util.ArrayList;

public class Queen extends Piece{
	public Queen(int side, int number){
		setSide(side);
		setNumber(number); 		
		//Set starting position (2 queens in a game)
		if (isWhite()) {
			setImage("./ChessPieces/0queen.png");
			setStart(3, 0);
		}
		else {
			setImage("./ChessPieces/1queen.png");
			setStart(3, 7);
		}
		resetPosition();
	}

	@Override
	ArrayList<Integer> generateMoves(int x, int y, ArrayList<Piece> pieces) {
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		
		//Check 4 cardinal directions, list out moves, then check for collisions
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
		
		//Then check the diagonals
		int i; //For X
		int j; //For Y
		i = x + 1; j = y + 1; //Upper-right side
		while (i < 8 && j < 8) {
			if (Chessboard.pieceAt(i, j, pieces) != null 
					&& Chessboard.pieceAt(i, j, pieces).getSide() == this.getSide()) {
				//If a collision is found/while going in order, i.e. a piece is found on a square 
				//in the path of travel that is the same color/side as this piece
				break; //You can't move there
			}
			else if (Chessboard.pieceAt(i, j, pieces) != null 
					&& Chessboard.pieceAt(i, j, pieces).getSide() != this.getSide()) {
				possibleMoves.add(i);
				possibleMoves.add(j);
				break; //If the piece is available to take; then the move is valid
				//However this piece cannot move past; it can only take
			}
			//Otherwise add the indices to the lists while no collision
			possibleMoves.add(i);
			possibleMoves.add(j);
			//And increment both i and j (move along diagonal)
			i++;
			j++;
		}
		
		i = x - 1; j = y + 1; //Upper-left side
		while (i >= 0 && j < 8) {
			if (Chessboard.pieceAt(i, j, pieces) != null 
					&& Chessboard.pieceAt(i, j, pieces).getSide() == this.getSide()) {
				//If a collision is found/while going in order, i.e. a piece is found on a square 
				//in the path of travel that is the same color/side as this piece
				break; //You can't move there
			}
			else if (Chessboard.pieceAt(i, j, pieces) != null 
					&& Chessboard.pieceAt(i, j, pieces).getSide() != this.getSide()) {
				possibleMoves.add(i);
				possibleMoves.add(j);
				break; //If the piece is available to take; then the move is valid
				//However this piece cannot move past; it can only take
			}
			//Otherwise add the indices to the lists while no collision
			possibleMoves.add(i);
			possibleMoves.add(j);
			//And decrement i while incrementing j (move along diagonal)
			i--;
			j++;
		}
		
		i = x + 1; j = y - 1; //Lower-right side
		while (i < 8 && j >= 0) {
			if (Chessboard.pieceAt(i, j, pieces) != null 
					&& Chessboard.pieceAt(i, j, pieces).getSide() == this.getSide()) {
				//If a collision is found/while going in order, i.e. a piece is found on a square 
				//in the path of travel that is the same color/side as this piece
				break; //You can't move there
			}
			else if (Chessboard.pieceAt(i, j, pieces) != null 
					&& Chessboard.pieceAt(i, j, pieces).getSide() != this.getSide()) {
				possibleMoves.add(i);
				possibleMoves.add(j);
				break; //If the piece is available to take; then the move is valid
				//However this piece cannot move past; it can only take
			}
			//Otherwise add the indices to the lists while no collision
			possibleMoves.add(i);
			possibleMoves.add(j);
			//And increment i while decrementing j (move along diagonal)
			i++;
			j--;
		}
		
		i = x - 1; j = y - 1; //Lower-left side
		while (i >= 0 && j >= 0) {
			if (Chessboard.pieceAt(i, j, pieces) != null 
					&& Chessboard.pieceAt(i, j, pieces).getSide() == this.getSide()) {
				//If a collision is found/while going in order, i.e. a piece is found on a square 
				//in the path of travel that is the same color/side as this piece
				break; //You can't move there
			}
			else if (Chessboard.pieceAt(i, j, pieces) != null 
					&& Chessboard.pieceAt(i, j, pieces).getSide() != this.getSide()) {
				possibleMoves.add(i);
				possibleMoves.add(j);
				break; //If the piece is available to take; then the move is valid
				//However this piece cannot move past; it can only take
			}
			//Otherwise add the indices to the lists while no collision
			possibleMoves.add(i);
			possibleMoves.add(j);
			//And increment both i and j (move along diagonal)
			i--;
			j--;
		}
		return possibleMoves;	
	}
}
