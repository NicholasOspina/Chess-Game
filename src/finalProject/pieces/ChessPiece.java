package finalProject.pieces;

public class ChessPiece {
	
	//value for which colour the piece is
	// true is for white, false is for black
	protected boolean whitePiece;
	
	/* Nicholas Ospina
	 * Constructor Name: ChessPiece 
	 * Description; creates a new piece of a certain colour
	 * Parameters: boolean isWhite
	 * Returns: n/a
	 */
	public ChessPiece( boolean isWhite) {
		
		this.whitePiece = isWhite;
		
		
	}//end constructor ChessPiece with parameters
	
	
	/* Nicholas Ospina
	 * Constructor Name: ChessPiece 
	 * Description; creates a new piece without a certain colour
	 * Parameters: n/a
	 * Returns: n/a
	 */
	public ChessPiece() {
		
	}//end constructor ChessPiece without parameters
	
	
	/* Nicholas Ospina
	 * Method Name: isWhite 
	 * Description; gets the coluor of a piece
	 * Parameters: n/a
	 * Returns: whitePiece
	 */
	public boolean isWhite() {
		
		return whitePiece;
	}// end getter for colour
	
	
	/* Nicholas Ospina
	 * Method Name: setWhitePiece 
	 * Description; sets the colour of a piece
	 * Parameters: n/a
	 * Returns: whitePiece
	 */
	public void setWhitePiece(boolean whitePiece) {
		
		this.whitePiece = whitePiece;
		
	}// end setter for colour


	
}// end ChessPiece class
