package finalProject.pieces;

import java.util.ArrayList;

import finalProject.gameParts.Location;
import finalProject.gameParts.Square;

public class Pawn extends ChessPiece {
	
	/* Nicholas Ospina
	 * Constructor Name: Pawn
	 * Description: creates a new Pawn
	 * Parameters: n/a
	 * Returns:n/a
	 * 
	 */
	public Pawn () {
		
		
	}//end contructor Pawn
	
	/* Nicholas Ospina
	 * Method Name: getMoves
	 * Description: checks the possible moves of the pawn
	 * Parameters: int row, int col, Square[][] allSquares, Square initialSquare, boolean squareCheck
	 * Returns: ArrayList<Location> locations
	 * 
	 */
	public ArrayList<Location> getMoves(int row, int col, Square[][] allSquares, Square initialSquare,
			boolean squareCheck) {
		
		
		ArrayList<Location> locations = new ArrayList<Location>(); // Create an ArrayList object
		
		// to check the square forward it weirdly uses the col and no the row

		// if the pawn is white, make it move up the board only
		if (initialSquare.getPiece().isWhite()) {

			// locations to test if pawn is at the start
			Location test1 = new Location(6, 0);
			
			//locations to test when pawn is attacking
			Location test2 = new Location(row - 1, col + 1);
			Location test3 = new Location(row - 1, col - 1);

			// if checking what squares are in check
			if (squareCheck) {

				locations.add(test2);
				locations.add(test3);
			} else {// if calculating the possible moves of the pawns

				if (allSquares[row - 1][col].getPiece() == null) {

					Location newLocation = new Location(row - 1, col);
					locations.add(newLocation);
				}// end if

				// checks if it can go forward 2 squares
				if (initialSquare.getLocation().getRank() == test1.getRank()
						&& allSquares[row - 2][col].getPiece() == null
						&& allSquares[row - 1][col].getPiece() == null) {

					Location newLocation2 = new Location(row - 2, col);
					locations.add(newLocation2);
				}// end if

				// if pawn is on the border do not check a space out of bounds
				if (initialSquare.getLocation().getCol() != 0) {

					//if the square is empty or has an enemy piece make it a moveable square
					if (allSquares[row - 1][col - 1].getPiece() != null && allSquares[row - 1][col - 1].getPiece()
							.isWhite() != initialSquare.getPiece().isWhite()) {

						locations.add(test3);

					}//end if

				}//end if

				//if the pawn is in the starting position do a series of checks
				if (initialSquare.getLocation().getCol() != 7) {

					// checks if there are enemy pieces up and to the left or right, if there are
					// list it as a possible move
					if (allSquares[row - 1][col + 1].getPiece() != null && allSquares[row - 1][col + 1].getPiece()
							.isWhite() != initialSquare.getPiece().isWhite()) {

						locations.add(test2);
					}// end if

				}// end if
				
			}// end if

		} // end if pawn if white

		// if the pawn is black, make it move down the board only
		else if (!initialSquare.getPiece().isWhite()) {

			// locations to test if pawn is at the start
			Location test2 = new Location(1, 0);
			
			//check for where ther pawn attackes
			Location test3 = new Location(row + 1, col + 1);
			Location test4 = new Location(row + 1, col - 1);

			//if doing a check of where the squares are checked only include the squares the pawn attacks
			if (squareCheck) {

				locations.add(test3);
				locations.add(test4);
			} else {

				if (allSquares[row + 1][col].getPiece() == null) {

					Location newLocation = new Location(row + 1, col);
					locations.add(newLocation);
				}// end if

				if (initialSquare.getLocation().getRank() == test2.getRank()
						&& allSquares[row + 2][col].getPiece() == null
						&& allSquares[row + 1][col].getPiece() == null) {

					Location newLocation2 = new Location(row + 2, col);
					locations.add(newLocation2);
				}// end if 

				// if pawn is on the border do not check a space out of bounds
				if (initialSquare.getLocation().getCol() != 0) {

					//if the square is empty or has an enemy piece make it a moveable square
					if (allSquares[row + 1][col - 1].getPiece() != null && allSquares[row + 1][col - 1].getPiece()
							.isWhite() != initialSquare.getPiece().isWhite()) {

						locations.add(test4);

					}// end if

				}// end if

				if (initialSquare.getLocation().getCol() != 7) {

					// checks if there are enemy pieces up and to the left or right, if there are
					// list it as a possible move
					if (allSquares[row + 1][col + 1].getPiece() != null && allSquares[row + 1][col + 1].getPiece()
							.isWhite() != initialSquare.getPiece().isWhite()) {

						locations.add(test3);
					}// end if

				}// end if
				
			}// end if

		} // end if pawn if black
		
		return locations;
		
	}// end getMoves method

		
		
	
	
}// end pawn class
