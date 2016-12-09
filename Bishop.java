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
