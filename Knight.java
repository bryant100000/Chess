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
