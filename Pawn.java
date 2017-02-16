
import java.util.ArrayList;

public class Pawn extends Piece{	
	public Pawn(int side, int number) { //ex. (1, 2) means black's 2nd pawn from right
		setSide(side);
		setNumber(number);
		//Set starting position (16 pawns in a game; 8 per side)
		if (isWhite()) {
			setImage("./ChessPieces/0pawn.png");
			switch (getNumber()) {
			case 1:
				setStart(0, 1);
				break;
			case 2:
				setStart(1, 1);
				break;
			case 3:
				setStart(2, 1);
				break;
			case 4:
				setStart(3, 1);
				break;
			case 5:
				setStart(4, 1);
				break;
			case 6:
				setStart(5, 1);
				break;
			case 7:
				setStart(6, 1);
				break;
			case 8:
				setStart(7, 1);
				break;
			}
		}
		else {
			setImage("./ChessPieces/1pawn.png");
			switch (getNumber()) {
			case 1:
				setStart(0, 6);
				break;
			case 2:
				setStart(1, 6);
				break;
			case 3:
				setStart(2, 6);
				break;
			case 4:
				setStart(3, 6);
				break;
			case 5:
				setStart(4, 6);
				break;
			case 6:
				setStart(5, 6);
				break;
			case 7:
				setStart(6, 6);
				break;
			case 8:
				setStart(7, 6);
				break;
			}
		}
		resetPosition(); //Set initial position and isAlive
	}
	
	@Override
	ArrayList<Integer> generateMoves(int x, int y, ArrayList<Piece> pieces) {
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		
		if (this.getSide() == 0) { //White pawns move "up"
			if (Chessboard.pieceAt(x, y + 1, pieces) == null
					&& (y + 1) < 8) {
				//If nothing is blocking, you can move forward one space
				possibleMoves.add(x);
				possibleMoves.add(y + 1);
			} 
			if (Chessboard.pieceAt(x, y + 2, pieces) == null && !moved()) {
				//Additionally, if the pawn hasn't moved, it may move 2 spaces
				possibleMoves.add(x);
				possibleMoves.add(y + 2);
			}

			//Pawn moving and taking are distinct: can only go diagonal if taking
			if (Chessboard.pieceAt(x + 1, y + 1, pieces) != null
					&& Chessboard.pieceAt(x + 1, y + 1, pieces).getSide() != this.getSide()
					&& (y + 1) < 8 && (x + 1) < 8) { //Right diagonal
				//If nothing is blocking, you can move forward one space
				possibleMoves.add(x + 1);
				possibleMoves.add(y + 1);
			}
			if (Chessboard.pieceAt(x - 1, y + 1, pieces) != null
					&& Chessboard.pieceAt(x - 1, y + 1, pieces).getSide() != this.getSide()
					&& (y + 1) < 8 && (x - 1) >= 0) { //Left diagonal
				//If nothing is blocking, you can move forward one space
				possibleMoves.add(x - 1);
				possibleMoves.add(y + 1);
			}
		}
		else { //Black pawns move "down"
			if (Chessboard.pieceAt(x, y - 1, pieces) == null
					&& (y - 1) >= 0) {
				//If nothing is blocking, you can move forward one space
				possibleMoves.add(x);
				possibleMoves.add(y - 1);
			} 
			if (Chessboard.pieceAt(x, y - 2, pieces) == null && !moved()) {
				//Additionally, if the pawn hasn't moved, it may move 2 spaces
				possibleMoves.add(x);
				possibleMoves.add(y - 2);
			}

			//Pawn moving and taking are distinct: can only go diagonal if taking
			if (Chessboard.pieceAt(x + 1, y - 1, pieces) != null
					&& Chessboard.pieceAt(x + 1, y - 1, pieces).getSide() != this.getSide()
					&& (y - 1) >= 0 && (x + 1) < 8) { //Right diagonal
				//If nothing is blocking, you can move forward one space
				possibleMoves.add(x + 1);
				possibleMoves.add(y - 1);
			}
			if (Chessboard.pieceAt(x - 1, y - 1, pieces) != null
					&& Chessboard.pieceAt(x - 1, y - 1, pieces).getSide() != this.getSide()
					&& (y - 1) >= 0 && (x - 1) >= 0) { //Left diagonal
				//If nothing is blocking, you can move forward one space
				possibleMoves.add(x - 1);
				possibleMoves.add(y - 1);
			}
		}
		return possibleMoves;
	}
}
