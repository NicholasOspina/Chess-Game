package finalProject.gameParts;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CheckeredBoard {

	//objects for the squares and the GridPane
	private Square[][] allSquares;
	private GridPane  gridBoard;
	
	/* Nicholas Ospina
	 * Method Name: getAllSquares 
	 * Description; getter for the variable allSquares
	 * Parameters: N/a
	 * Returns: allSquares
	 */
	public Square[][] getAllSquares() {
		return allSquares;
		
	}//end method getAllSquares

	
	/* Nicholas Ospina
	 * Method Name: setAllSquares 
	 * Description; setter for the variable allSquares
	 * Parameters: Square[][] allSquares
	 * Returns: n/a
	 */
	public void setAllSquares(Square[][] allSquares) {
		this.allSquares = allSquares;
		
	}//end method setAllSquares

	
	/* Nicholas Ospina
	 * Method Name: getGridBoard 
	 * Description; getter for the variable gridBoard
	 * Parameters: N/a
	 * Returns: gridBoard
	 */
	public GridPane getGridBoard() {
		return gridBoard;
		
	}//end method getGridBoard

	/* Nicholas Ospina
	 * Method Name: setGridBoard 
	 * Description; setter for the variable gridBoard
	 * Parameters: GridPane gridBoard
	 * Returns: n/a
	 */
	public void setGridBoard(GridPane gridBoard) {
		this.gridBoard = gridBoard;
		
	}//end method setGridBoard

	
	/* Nicholas Ospina
	 * Constructor Name: CheckeredBoard 
	 * Description; vreates the board display with the squares and gridPane
	 * Parameters: int rows, int col
	 * Returns: n/a
	 */
	public CheckeredBoard(int rows, int cols) {
		
		this.gridBoard = new GridPane();
		this.allSquares = new Square[rows][cols];
		
		// create the board with tiles
		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8; j++) {

				// creates the light coloured squares
				if ((i + j) % 2 == 0) {

					// create the square, give it a coluor and size and add it to the gridPane
					Square newSquare = new Square(new Location(i, j), null, null, null);

					Rectangle rect = new Rectangle(120, 120);
					rect.setFill(Color.CORNSILK);
					newSquare.setRect(rect);

					gridBoard.add(newSquare.getDisplay(), j, i);

					allSquares[i][j] = newSquare;

				} else {// creates the dark coloured squares

					// create the square, give it a coluor and size and add it to the gridPane
					Square newSquare = new Square(new Location(i, j), null, null, null);

					Rectangle rect = new Rectangle(120, 120);
					rect.setFill(Color.BURLYWOOD);
					newSquare.setRect(rect);

					gridBoard.add(newSquare.getDisplay(), j, i);

					allSquares[i][j] = newSquare;
				} // end else

			} // end for loop

		} // end for loop

		
		
	}//end constructor CheckeredBoard
	
}//end class checkeredBoard
