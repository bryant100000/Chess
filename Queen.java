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
