
import java.util.ArrayList;

public class Knight extends Piece{
	public Knight(int side, int number) {
		setSide(side);
		setNumber(number);
		//Set starting position (4 knights in a game)
		if (isWhite()) {
			setImage("./ChessPieces/0knight.png");
			if (getNumber() == 1) {
				setStart(1, 0);
			}
			else {
				setStart(6, 0);
			}
		}
		else {
			setImage("./ChessPieces/1knight.png");
			if (getNumber() == 1) {
				setStart(1, 7);
			}
			else {
				setStart(6, 7);
			}
		}
		resetPosition();
	}
	@Override
	ArrayList<Integer> generateMoves(int x, int y, ArrayList<Piece> pieces) {
		//Knight moves differently; no collision with other pieces, and jumps
		//Must check if the jumping coordinates are within bounds 1st
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		
		//Add all sets of possible moves for a knight
		possibleMoves.add(x + 2); possibleMoves.add(y + 1);
		possibleMoves.add(x + 1); possibleMoves.add(y + 2);
		possibleMoves.add(x + 2); possibleMoves.add(y - 1);
		possibleMoves.add(x + 1); possibleMoves.add(y - 2);
		possibleMoves.add(x - 2); possibleMoves.add(y - 1);
		possibleMoves.add(x - 1); possibleMoves.add(y - 2);
		possibleMoves.add(x - 2); possibleMoves.add(y + 1);
		possibleMoves.add(x - 1); possibleMoves.add(y + 2);
		
		//Weed out moves that are out-of-bounds (2 ways to be invalid)
		for (int i = 0; i < possibleMoves.size(); i += 2) {
			if (possibleMoves.get(i) > 7 || possibleMoves.get(i) < 0 || 
					possibleMoves.get(i + 1) > 7 || possibleMoves.get(i + 1) < 0) {
				possibleMoves.remove(i); //Remove the x-index of the move
				possibleMoves.remove(i); //Remove the y-index of the move
				i -= 2; //Decrement i backwards, so not to skip the next moveset
				continue;
			}
			//And if the kn ight would move on top of a piece of its own color, don't
			if (Chessboard.pieceAt(possibleMoves.get(i), 
				possibleMoves.get(i + 1), pieces) != null 
					&& Chessboard.pieceAt(possibleMoves.get(i), 
					possibleMoves.get(i + 1), pieces).getSide() == this.getSide()) {
				possibleMoves.remove(i); //Remove the x-index of the move
				possibleMoves.remove(i); //Remove the y-index of the move
				i -= 2;
			}
		}		
		return possibleMoves;
	}
}
