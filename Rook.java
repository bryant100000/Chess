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

	@Override
	ArrayList<String> generateMoves(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	boolean canMoveTo(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
