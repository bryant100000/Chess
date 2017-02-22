
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
		}
		//Now find the possible moves that involve taking with pawns
		ArrayList<Integer> possibleTakes = Pawn.generateTakes(x, y, pieces, this);
		for (int i = 0; i < possibleTakes.size(); i += 2) {
			possibleMoves.add(possibleTakes.get(i));
			possibleMoves.add(possibleTakes.get(i + 1));
		}
		return possibleMoves;
	}
	
	//Pawn-exclusive taking-move generator, since pawn moves and takes differently
	public static ArrayList<Integer> generateTakes(int x, int y, ArrayList<Piece> pieces, Piece pawn) {
		ArrayList<Integer> possibleTakes = new ArrayList<Integer>();
	
		if (pawn.getSide() == 0) {
			//Pawn moving and taking are distinct: can only go diagonal if taking
			if (Chessboard.pieceAt(x + 1, y + 1, pieces) != null
					&& Chessboard.pieceAt(x + 1, y + 1, pieces).getSide() != pawn.getSide()
					&& (y + 1) < 8 && (x + 1) < 8) { //Right diagonal
				//If nothing is blocking, you can move forward one space
				possibleTakes.add(x + 1);
				possibleTakes.add(y + 1);
			}
			else if (Chessboard.pieceAt(x + 1, y + 1, pieces) != null
					&& Chessboard.pieceAt(x + 1, y + 1, pieces).getSide() == pawn.getSide()
					&& (y + 1) < 8 && (x + 1) < 8) {
				Chessboard.pieceAt(x + 1, y + 1, pieces).setReinforced(true);
			}
			if (Chessboard.pieceAt(x - 1, y + 1, pieces) != null
					&& Chessboard.pieceAt(x - 1, y + 1, pieces).getSide() != pawn.getSide()
					&& (y + 1) < 8 && (x - 1) >= 0) { //Left diagonal
				//If nothing is blocking, you can move forward one space
				possibleTakes.add(x - 1);
				possibleTakes.add(y + 1);
			}
			else if (Chessboard.pieceAt(x - 1, y + 1, pieces) != null
					&& Chessboard.pieceAt(x - 1, y + 1, pieces).getSide() == pawn.getSide()
					&& (y + 1) < 8 && (x - 1) >= 0) {
				Chessboard.pieceAt(x - 1, y + 1, pieces).setReinforced(true);
			}
		}
		else {
			//Pawn moving and taking are distinct: can only go diagonal if taking
			if (Chessboard.pieceAt(x + 1, y - 1, pieces) != null
					&& Chessboard.pieceAt(x + 1, y - 1, pieces).getSide() != pawn.getSide()
					&& (y - 1) >= 0 && (x + 1) < 8) { //Right diagonal
				//If nothing is blocking, you can move forward one space
				possibleTakes.add(x + 1);
				possibleTakes.add(y - 1);
			}
			else if (Chessboard.pieceAt(x + 1, y - 1, pieces) != null
					&& Chessboard.pieceAt(x + 1, y - 1, pieces).getSide() == pawn.getSide()
					&& (y - 1) >= 0 && (x + 1) < 8) {
				Chessboard.pieceAt(x + 1, y - 1, pieces).setReinforced(true);
			}
			if (Chessboard.pieceAt(x - 1, y - 1, pieces) != null
					&& Chessboard.pieceAt(x - 1, y - 1, pieces).getSide() != pawn.getSide()
					&& (y - 1) >= 0 && (x - 1) >= 0) { //Left diagonal
				//If nothing is blocking, you can move forward one space
				possibleTakes.add(x - 1);
				possibleTakes.add(y - 1);
			}
			else if (Chessboard.pieceAt(x - 1, y - 1, pieces) != null
					&& Chessboard.pieceAt(x - 1, y - 1, pieces).getSide() == pawn.getSide()
					&& (y - 1) >= 0 && (x - 1) >= 0) {
				Chessboard.pieceAt(x - 1, y - 1, pieces).setReinforced(true);
			}
		}
		return possibleTakes;
	}	
	
	//Pawn exclusive canTake method that decides whether a pawn can take at a certain square
	public static boolean canTake(int currX, int currY, int x, int y, ArrayList<Piece> pieces, Piece pawn) {
		boolean canTake = false;
		ArrayList<Integer> takeMoves = Pawn.generateTakes(x, y, pieces, pawn);
		//If the position the user tries to move to is within the returned
		//alternating ArrayList, tell them they can move to that space
		for (int i = 0; i < takeMoves.size(); i += 2) {
			if (takeMoves.get(i) == x && takeMoves.get(i + 1) == y) {
				canTake = true;
			} 
		}
		return canTake;
	}
}
