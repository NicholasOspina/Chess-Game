package finalProject.pieces;

import java.util.ArrayList;

import finalProject.gameParts.Location;
import finalProject.gameParts.Square;

public class Rook extends ChessPiece {

	/* Nicholas Ospina
	 * Constructor Name: Rook
	 * Description: creates a new Rook
	 * Parameters: n/a
	 * Returns:n/a
	 * 
	 */
	public Rook() {

	}//end constructor Rook

	
	/* Nicholas Ospina
	 * Method Name: getMoves
	 * Description: checks the possible moves of the rook
	 * Parameters: int row, int col, Square[][] allSquares, Square initialSquare, boolean squareCheck
	 * Returns: ArrayList<Location> locations
	 * 
	 */
	public ArrayList<Location> getMoves(int row, int col, Square[][] allSquares, Square initialSquare,
			boolean squareCheck) {

		
		ArrayList<Location> locations = new ArrayList<Location>(); // Create an ArrayList object

		// for the moves going down the board
		for (int i = row + 1; i < 8; i++) {

			// if the square is empty it is valid
			if (allSquares[i][col].getPiece() == null) {

				Location newLocation2 = new Location(i, col);
				locations.add(newLocation2);

			} // end if

			// if doing which square are in check include squares that ally pieces are on
			// but dont allow it to go further
			else if (squareCheck && allSquares[i][col].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {

				Location newLocation = new Location(i, col);
				locations.add(newLocation);
				break;

			} // end if

			// if the square has an enemeny piece allow the move to that square but no
			// further
			else if (allSquares[i][col].getPiece().isWhite() != initialSquare.getPiece().isWhite()) {

				locations.add(new Location(i, col));
				break;

			} // end if

			// if the square has an ally stop the rook's moves in this direction
			else if (allSquares[i][col].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {

				break;

			} // end if

		} // end for

		// for the moves going up the board
		for (int i = row - 1; i >= 0; i--) {

			// if the square is empty it is valid
			if (allSquares[i][col].getPiece() == null) {

				locations.add(new Location(i, col));

			} // end if

			// if doing which square are in check include squares that ally pieces are on
			// but dont allow it to go further
			else if (squareCheck && allSquares[i][col].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {

				Location newLocation = new Location(i, col);
				locations.add(newLocation);
				break;

			} // end if

			// if the square has an enemeny piece allow the move to that square but no
			// further
			else if (allSquares[i][col].getPiece().isWhite() != initialSquare.getPiece().isWhite()) {

				locations.add(new Location(i, col));
				break;

			} // end if

			// if the square has an ally stop the rook's moves in this direction
			else if (allSquares[i][col].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {

				break;

			} // end if
		} // end for

		// for the moves going right of the rook
		for (int i = col + 1; i < 8; i++) {

			// if the square is empty it is valid
			if (allSquares[row][i].getPiece() == null) {

				locations.add(new Location(row, i));

			} // end if

			// if doing which square are in check include squares that ally pieces are on
			// but dont allow it to go further
			else if (squareCheck && allSquares[row][i].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {

				Location newLocation = new Location(row, i);
				locations.add(newLocation);
				break;

			} // end if

			// if the square has an enemeny piece allow the move to that square but no
			// further
			else if (allSquares[row][i].getPiece().isWhite() != initialSquare.getPiece().isWhite()) {

				locations.add(new Location(row, i));
				break;

			} // end if

			// if the square has an ally stop the rook's moves in this direction
			else if (allSquares[row][i].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {

				break;

			} // end else if

		} // end for

		// for the moves going left of the rook
		for (int i = col - 1; i >= 0; i--) {

			// if the square is empty it is valid
			if (allSquares[row][i].getPiece() == null) {

				locations.add(new Location(row, i));

			} // end if

			// if doing which square are in check include squares that ally pieces are on
			// but dont allow it to go further
			else if (squareCheck && allSquares[row][i].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {

				Location newLocation = new Location(row, i);
				locations.add(newLocation);
				break;

			} // end else if

			// if the square has an enemeny piece allow the move to that square but no
			// further
			else if (allSquares[row][i].getPiece().isWhite() != initialSquare.getPiece().isWhite()) {

				locations.add(new Location(row, i));
				break;

			} // end else if

			// if the square has an ally stop the rook's moves in this direction
			else if (allSquares[row][i].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {

				break;

			} // end if

		} // end for

		return locations;

	}//end getMoves method

}//end Rook class
