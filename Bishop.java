import java.util.ArrayList;

public class Bishop extends Piece{
	public Bishop(int side, int number) {
		setSide(side);
		setNumber(number);
		//Set starting position (4 bishops in a game)
		if (isWhite()) {
			setImage("./ChessPieces/0bishop.png");
			if (getNumber() == 1) {
				setStart(2, 0);
			}
			else {
				setStart(5, 0);
			}
		}
		else {
			setImage("./ChessPieces/1bishop.png");
			if (getNumber() == 1) {
				setStart(2, 7);
			}
			else {
				setStart(5, 7);
			}
		}
		resetPosition();
	}

	//*Do NOT check the square the piece is on; will prevent move generation
	@Override
	ArrayList<Integer> generateMoves(int x, int y, ArrayList<Piece> pieces) {
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

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
