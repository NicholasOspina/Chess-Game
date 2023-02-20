package finalProject.pieces;

import java.util.ArrayList;

import finalProject.gameParts.Location;
import finalProject.gameParts.Square;

public class Knight extends ChessPiece {
	
	
	/* Nicholas Ospina
	 * Constructor Name: Knight
	 * Description: creates a new Knight
	 * Parameters: n/a
	 * Returns:n/a
	 * 
	 */
	public Knight () {
		
		
	}//end constructor Knight
	
	
	/* Nicholas Ospina
	 * Method Name: getMoves
	 * Description: checks the possible moves of the knight
	 * Parameters: int row, int col, Square[][] allSquares, Square initialSquare, boolean squareCheck
	 * Returns: ArrayList<Location> locations
	 * 
	 */
	public ArrayList<Location> getMoves(int row, int col, Square[][] allSquares, Square initialSquare,
			boolean squareCheck) {

		
		ArrayList<Location> locations = new ArrayList<Location>(); // Create an ArrayList object
		
		//for loopes in order to cycle through the moves of knight
		for (int i = -2; i < 3; i += 4) {

			for (int j = -1; j < 2; j += 2) {

				//if the move is within the parameters of the board, the piece is of the right colour and if square has an enemy or is empty
				//this is a valid move
				if (row + i < 8 && row + i >= 0 && col + j < 8 && col + j >= 0
						&& (allSquares[row + i][col + j].getPiece() == null || allSquares[row + i][col + j]
								.getPiece().isWhite() != initialSquare.getPiece().isWhite())) {

					Location newLocation = new Location(row + i, col + j);
					locations.add(newLocation);
					
				}// end if
				// if seeing squares are in check, include squares that ally pieces are on
				else if (row + i < 8 && row + i >= 0 && col + j < 8 && col + j >= 0 && squareCheck
						&& allSquares[row + i][col + j].getPiece().isWhite() == initialSquare.getPiece()
								.isWhite()) {
					
					
					Location newLocation = new Location(row + i, col + j);
					locations.add(newLocation);

				}// else if
			}// end for
		}// end for

		
		
		//for loopes in order to cycle through the moves of knight
		for (int i = -1; i < 2; i += 2) {

			for (int j = -2; j < 3; j += 4) {
				
				//if the move is within the parameters of the board, the piece is of the right colour and if square has an enemy or is empty
				//this is a valid move
				if (row + i < 8 && row + i >= 0 && col + j < 8 && col + j >= 0
						&& (allSquares[row + i][col + j].getPiece() == null || allSquares[row + i][col + j]
								.getPiece().isWhite() != initialSquare.getPiece().isWhite())) {

					
					Location newLocation = new Location(row + i, col + j);
					locations.add(newLocation);
					
				}// end if
				// if seeing squares are in check, include squares that ally pieces are on
				else if (row + i < 8 && row + i >= 0 && col + j < 8 && col + j >= 0 && squareCheck
						&& allSquares[row + i][col + j].getPiece().isWhite() == initialSquare.getPiece()
								.isWhite()) {
					
					
					Location newLocation = new Location(row + i, col + j);
					locations.add(newLocation);
					

				}// end else if
				
			}// end for
			
		}//end for

		
		return locations;	
	}//end getMoves method
	
}//end Knight class
