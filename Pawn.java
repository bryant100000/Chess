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
