package finalProject.pieces;

import java.util.ArrayList;

import finalProject.gameParts.Location;
import finalProject.gameParts.Square;

public class King extends ChessPiece {

	/* Nicholas Ospina
	 * Constructor Name: King
	 * Description: creates a new King
	 * Parameters: n/a
	 * Returns: n/a
	 * 
	 */
	public King () {
		
		
		
	}//end constructor for king
	
	/* Nicholas Ospina
	 * Method Name: getMoves
	 * Description: checks the possible moves of the king
	 * Parameters: int row, int col, Square[][] allSquares, Square initialSquare, boolean squareCheck
	 * Returns: ArrayList<Location> locations
	 * 
	 */
	public ArrayList<Location> getMoves(int row, int col, Square[][] allSquares, Square initialSquare,
			boolean squareCheck) {

		
		ArrayList<Location> locations = new ArrayList<Location>(); // Create an ArrayList object
		
		//inserts all of the surronding squartes of the current location of the king and adds them to a temporary list
		Location newLocation1 = new Location(row + 1, col + 1);
		locations.add(newLocation1);

		Location newLocation2 = new Location(row + 1, col);
		locations.add(newLocation2);

		Location newLocation3 = new Location(row + 1, col - 1);
		locations.add(newLocation3);

		Location newLocation4 = new Location(row, col + 1);
		locations.add(newLocation4);

		Location newLocation5 = new Location(row, col - 1);
		locations.add(newLocation5);

		Location newLocation6 = new Location(row - 1, col + 1);
		locations.add(newLocation6);

		Location newLocation7 = new Location(row - 1, col);
		locations.add(newLocation7);

		Location newLocation8 = new Location(row - 1, col - 1);
		locations.add(newLocation8);

		
		
		return locations;
	}//end method getMoves
	
}//end class King
