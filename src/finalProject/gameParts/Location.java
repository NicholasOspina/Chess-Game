package finalProject.gameParts;

import java.util.ArrayList;

import finalProject.pieces.ChessPiece;

public class Location {

	
	private int rank;//the row
	private int col;// the column
	

	
	/* Nicholas Ospina
	 * Constructor Name: Location 
	 * Description; creats a new location
	 * Parameters: boolean isWhite
	 * Returns: n/a
	 */
	public Location(int rank, int col) {
		
		this.setRank(rank);
		this.setCol(col);
		
		
	}//end construtor of Location
	

	/* Nicholas Ospina
	 * Method Name: getRank 
	 * Description; gets the row of a location
	 * Parameters:n/a
	 * Returns: rank
	 */
	public int getRank() {
		
		return rank;
	}// end getter for row

	/* Nicholas Ospina
	 * Method Name: setRank 
	 * Description; sets the row of a location
	 * Parameters:n/a
	 * Returns: rank
	 */
	public void setRank(int rank) {
		
		this.rank = rank;
		
	}// end setter for rank

	
	/* Nicholas Ospina
	 * Method Name: getCol 
	 * Description; gets the column of a location
	 * Parameters: n/a
	 * Returns: col
	 */
	public int getCol() {
		
		return col;
	}// end getter for column
	
	
	/* Nicholas Ospina
	 * Method Name: setCol 
	 * Description; sets the column of a location
	 * Parameters:int col
	 * Returns: n/a
	 */
	public void setCol(int col) {
		
		this.col = col;
	
	}// end setter for column



}//end class Location
