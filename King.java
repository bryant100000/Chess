import java.util.ArrayList;

public class King extends Piece{
	public King(int side, int number) {
		setSide(side);
		//Note only 1 king per side; number is just to maintain consistency
		setNumber(number); 		
		//Set starting position (2 kings in a game)
		if (isWhite()) {
			setImage("./ChessPieces/0king.png");
			setStart(4, 0);
		}
		else {
			setImage("./ChessPieces/1king.png");
			setStart(4, 7);
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
