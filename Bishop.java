package finalProject.pieces;

import java.util.ArrayList;

import finalProject.gameParts.Location;
import finalProject.gameParts.Square;

public class Bishop extends ChessPiece {

	/* Nicholas Ospina
	 * Constructor Name: Bishop
	 * Description: creates a new Bishop
	 * Parameters: n/a
	 * Returns: n/a
	 * 
	 */
	public Bishop() {
		
		
	}// end constructor for bishop 
	
	
	/* Nicholas Ospina
	 * Method Name: getMoves
	 * Description: checks the possible moves of the Bishop
	 * Parameters: int row, int col, Square[][] allSquares, Square initialSquare, boolean squareCheck
	 * Returns: ArrayList<Location> locations
	 * 
	 */
	public ArrayList<Location> getMoves(int row, int col, Square[][] allSquares, Square initialSquare, boolean squareCheck) {
		
		ArrayList<Location> locations = new ArrayList<Location>(); // Create an ArrayList object
		
		
		//inserts the moves going down and to the right
		for (int i = 1; i < 8; i++) {

			//if the move is out of bounds discard it
			if (row + i > 7 || col + i > 7) {
				break;
				
			}// end if

			
			//if the square is empty it is valid
			if (allSquares[row + i][col + i].getPiece() == null) {

				locations.add(new Location(row + i, col + i));
				
			}// end if
			
			// if doing which square are in check include squares that ally pieces are on
			else if (squareCheck
					&& allSquares[row + i][col + i].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {
				Location newLocation = new Location(row + i, col + i);
				locations.add(newLocation);
				break;

			}// end if
			
			// if the square has an enemeny piece allow the move to that square but no further
			else if (allSquares[row + i][col + i].getPiece().isWhite() != initialSquare.getPiece().isWhite()) {

				locations.add(new Location(row + i, col + i));
				break;
				
			}// end if 
			
			//if the square has an ally stop the rook's moves in this direction
			else if (allSquares[row + i][col + i].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {

				break;
				
			}// end if

		} // end for

		
		//for the moves down and to the left
		for (int i = 1; i < 8; i++) {

			//if the move is out of bounds discard it
			if (row + i > 7 || col - i < 0) {
				break;
				
			}// end if

			
			//if the square is empty it is valid
			if (allSquares[row + i][col - i].getPiece() == null) {

				locations.add(new Location(row + i, col - i));
				
			}// end if
			
			
			// if doing which square are in check include squares that ally pieces are on
			else if (squareCheck
					&& allSquares[row + i][col - i].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {
				Location newLocation = new Location(row + i, col - i);
				locations.add(newLocation);
				break;
				

			}// end if
			
			// if the square has an enemeny piece allow the move to that square but no further
			else if (allSquares[row + i][col - i].getPiece().isWhite() != initialSquare.getPiece().isWhite()) {

				locations.add(new Location(row + i, col - i));
				break;
				
			}// end if
			
			//if the square has an ally stop the rook's moves in this direction
			else if (allSquares[row + i][col - i].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {

				break;
				
			}// end if
			
		}// end for

		
		//for the moves up and to the left
		for (int i = 1; i < 8; i++) {

			//if the move is out of bounds discard it
			if (row - i < 0 || col - i < 0) {
				
				break;
				
			}// end if

			
			//if the square is empty it is valid
			if (allSquares[row - i][col - i].getPiece() == null) {

				locations.add(new Location(row - i, col - i));
				
			}// end if
			
			// if doing which square are in check include squares that ally pieces are on
			else if (squareCheck
					&& allSquares[row - i][col - i].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {
				Location newLocation = new Location(row - i, col - i);
				locations.add(newLocation);
				break;

			}// end if
			
			// if the square has an enemeny piece allow the move to that square but no further
			else if (allSquares[row - i][col - i].getPiece().isWhite() != initialSquare.getPiece().isWhite()) {

				locations.add(new Location(row - i, col - i));
				break;
				
			}// end if
			
			//if the square has an ally stop the rook's moves in this direction
			else if (allSquares[row - i][col - i].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {

				break;
				
			}// end if
			
		}// end for

		
		//for the moves up and to the right
		for (int i = 1; i < 8; i++) {

			//if the move is out of bounds discard it
			if (row - i < 0 || col + i > 7) {
				break;
				
			}// end if

			
			//if the square is empty it is valid
			if (allSquares[row - i][col + i].getPiece() == null) {

				locations.add(new Location(row - i, col + i));
				
			}// end if
			
			
			// if doing which square are in check include squares that ally pieces are on
			else if (squareCheck
					&& allSquares[row - i][col + i].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {
				Location newLocation = new Location(row - i, col + i);
				locations.add(newLocation);
				break;

			}// end if
			
			// if the square has an enemeny piece allow the move to that square but no further
			else if (allSquares[row - i][col + i].getPiece().isWhite() != initialSquare.getPiece().isWhite()) {

				locations.add(new Location(row - i, col + i));
				break;
				
			}// end if
			
			//if the square has an ally stop the rook's moves in this direction
			else if (allSquares[row - i][col + i].getPiece().isWhite() == initialSquare.getPiece().isWhite()) {

				break;
			}// end if
			
		}// end for
		
		return locations;
		
	}// end getMoves method
}// end Bishop class
