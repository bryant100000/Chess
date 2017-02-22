
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
	ArrayList<Integer> generateMoves(int x, int y, ArrayList<Piece> pieces) {
		//King's movement is all adjacent squares; restricted by other pieces
		//and by line-of-attacks/checks of enemy pieces
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

		//First add all possible movements
		possibleMoves.add(x);
		possibleMoves.add(y + 1);
		possibleMoves.add(x + 1);
		possibleMoves.add(y + 1);
		possibleMoves.add(x + 1);
		possibleMoves.add(y);
		possibleMoves.add(x + 1);
		possibleMoves.add(y - 1);
		possibleMoves.add(x);
		possibleMoves.add(y - 1);
		possibleMoves.add(x - 1);
		possibleMoves.add(y - 1);
		possibleMoves.add(x - 1);
		possibleMoves.add(y);
		possibleMoves.add(x - 1);
		possibleMoves.add(y + 1);

		//Weed out movements that bring the king out of bounds
		for (int i = 0; i < possibleMoves.size(); i += 2) {
			if (possibleMoves.get(i) > 7 || possibleMoves.get(i) < 0 || 
					possibleMoves.get(i + 1) > 7 || possibleMoves.get(i + 1) < 0) {
				possibleMoves.remove(i); //Remove the x-index of the move
				possibleMoves.remove(i); //Remove the y-index of the move
				i -= 2; //Decrement i backwards, so not to skip the next moveset
				continue;
			}
			//As well as movements onto pieces of the king's own side
			else if (Chessboard.pieceAt(possibleMoves.get(i), possibleMoves.get(i + 1),
					pieces) != null && Chessboard.pieceAt(possibleMoves.get(i), 
							possibleMoves.get(i + 1), pieces).getSide() == this.getSide()) {
				Chessboard.pieceAt(possibleMoves.get(i), possibleMoves.get(i + 1), pieces).setReinforced(true);
				possibleMoves.remove(i); //Remove the x-index of the move
				possibleMoves.remove(i); //Remove the y-index of the move
				i -= 2;
			}
			//And movements that take reinforced pieces
			else if (Chessboard.pieceAt(possibleMoves.get(i), possibleMoves.get(i + 1),
					pieces) != null && Chessboard.pieceAt(possibleMoves.get(i), 
						possibleMoves.get(i + 1), pieces).getSide() != this.getSide()
							&& Chessboard.pieceAt(possibleMoves.get(i), 
								possibleMoves.get(i + 1), pieces).isReinforced() == true) {
				possibleMoves.remove(i); //Remove the x-index of the move
				possibleMoves.remove(i); //Remove the y-index of the move
				i -= 2;
			}
		}
		
		boolean shouldRemove;
		for (int i = 0; i < possibleMoves.size(); i += 2) {
			//Also weed out moves that put the king into check
			for (int j = 0; j < pieces.size(); j++) {
				shouldRemove = false;
				//Only enemy pieces can threaten the king
				if (pieces.get(j).getSide() != this.getSide()) {
					//Pawns have distinct move and take methods
					if (pieces.get(j) instanceof Pawn) {
						if (pieces.get(j).getSide() == 0) { //VS White pawn
							if (pieces.get(j).getX() + 1 < 8 && pieces.get(j).getY() + 1 < 8
									&& pieces.get(j).getX() + 1 == possibleMoves.get(i)
									&& pieces.get(j).getY() + 1 == possibleMoves.get(i+1)) {
								possibleMoves.remove(i); //Remove the x-index of the move
								possibleMoves.remove(i+1); //Remove the y-index of the move
								i -= 2;
								break;
							}	
							if (pieces.get(j).getX() - 1 >= 0 && pieces.get(j).getY() + 1 < 8
									&& pieces.get(j).getX() - 1 == possibleMoves.get(i)
									&& pieces.get(j).getY() + 1 == possibleMoves.get(i+1)) {
								possibleMoves.remove(i); //Remove the x-index of the move
								possibleMoves.remove(i); //Remove the y-index of the move
								i -= 2;
								break;
							}
						}
						else {
							if (pieces.get(j).getX() + 1 < 8 && pieces.get(j).getY() - 1 >= 0
									&& pieces.get(j).getX() + 1 == possibleMoves.get(i)
									&& pieces.get(j).getY() - 1 == possibleMoves.get(i+1)) {
								possibleMoves.remove(i); //Remove the x-index of the move
								possibleMoves.remove(i); //Remove the y-index of the move
								i -= 2;
								break;
							}	
							if (pieces.get(j).getX() - 1 >= 0 && pieces.get(j).getY() - 1 >= 0
									&& pieces.get(j).getX() - 1 == possibleMoves.get(i)
									&& pieces.get(j).getY() - 1 == possibleMoves.get(i+1)) {
								possibleMoves.remove(i); //Remove the x-index of the move
								possibleMoves.remove(i); //Remove the y-index of the move
								i -= 2;
								break;
							}
						}
					}
					else { //Other pieces move normally; go through their moveSets
						for (int k = 0; k < pieces.get(j).getMoveSet().size(); k += 2) {
							//If a set of coordinates in 
							if (pieces.get(j).getMoveSet().get(k) == possibleMoves.get(i)
									&& pieces.get(j).getMoveSet().get(k + 1) == possibleMoves.get(i+1)) {
								System.out.println("Removing " + possibleMoves.get(i) + " and " + possibleMoves.get(i + 1) +
										" because of " + pieces.get(j).getImageName());
								possibleMoves.remove(i);
								possibleMoves.remove(i);
								i -= 2;
								shouldRemove = true;
								break; //Break if you find a match
							}
						}
						if (shouldRemove) {
							break;
						}
					}
				}
			}
		}
		return possibleMoves;
	}
}
