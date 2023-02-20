/* Nicholas Ospina	
 * Mrs. McCaffery
 * ICS4UI
 * ChessGame Class
 * Class that runs a chess game
 * 
 */

package finalProject.game;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import finalProject.gameParts.CheckeredBoard;
import finalProject.gameParts.Location;
import finalProject.gameParts.Square;
import finalProject.pieces.Bishop;
import finalProject.pieces.ChessPiece;
import finalProject.pieces.King;
import finalProject.pieces.Knight;
import finalProject.pieces.Pawn;
import finalProject.pieces.Queen;
import finalProject.pieces.Rook;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChessGame extends Application {

	private Scene title, howTo, boardGUI;

	private Stage stage;

	private static GridPane gridBoard;//gridpane that holds the display of the noard and pieces
	private static Square movingFromSquare;// square that is being moved to
	private static boolean WCastleL, WCastleR, BCastleL, BCastleR;//to see if it is possible to castle
	private static boolean iswhiteTurn = true;//used to determine whos turn it is
	private static boolean wasChecked;//to check if the king was checked in order to return the colour of the square back to normal
	private static boolean isKingChecked;// is the king in check
	private static Label playerTurn;//used to show the players whos turn it is and who won
	
	
	// holds the colors of the squares whose colour were changed
	private static Color[] squareColour;
	private static Color kingSquareColour;

	// holds what squares are in check,the moves if the king is in check, and valid moves of a regualr piece
	private static ArrayList<Location> validLocations, kingCheck, blackChecked, whiteChecked;
	
	// holds the information of all the squares
	private static Square[][] allSquares;

	// objects for the images of the different pieces
	private static Image imageBBishop, imageBRook, imageBPawn, imageBQueen, imageBKing, imageBKnight, imageWRook,
			imageWBishop, imageWQueen, imageWKing, imageWKnight, imageWPawn;

	@Override
	// the main method the runs the majority of the program
	public void start(Stage window) throws Exception {

		
		// initializes the moves if the king is in check
		kingCheck = new ArrayList<Location>();
		

		// grabs the images of the pieces from the computer and puts them into objects
		// of images
		createImages();

		// creates a new checkered board
		CheckeredBoard gameBoard = new CheckeredBoard(8, 8);

		
		// transfers the grid pane and the array of squares the CheckeredBoard class created
		allSquares = inputPieces(gameBoard.getAllSquares());
		gridBoard =gameBoard.getGridBoard();

		
		// setting all the pieces to be able to be draggable if they have a piece, and
		// able to be dragged over
		for (int i = 0; i < allSquares.length; i++) {

			for (int j = 0; j < allSquares.length; j++) {

				allSquares[i][j] = setDrag(allSquares[i][j]);
				allSquares[i][j] = setDragOver(allSquares[i][j]);

			} // end for
		} // end for

//**********************************************************************************************************************

		// GUI for title page
		StackPane titleStack = new StackPane();
		Rectangle backgroundTitle = new Rectangle(1260, 1100);
		backgroundTitle.setFill(Color.CORNSILK);
		Rectangle borderTitle = new Rectangle(550, 550);
		borderTitle.setFill(Color.BURLYWOOD);
		Rectangle innerBackgroundTitle = new Rectangle(500, 500);
		innerBackgroundTitle.setFill(Color.BISQUE);

		
		// functions of the Vbox
		VBox titleVbx = new VBox(50);
		titleVbx.setAlignment(Pos.CENTER);

		Label headerTitle = new Label("Welcome to Chess");
		headerTitle.setFont(Font.font(30));

		// creating buttons and giving them sizes
		Button playBtn = new Button("Play");
		playBtn.setFont(Font.font(20));
		Button howToBtn = new Button("How to Play");
		howToBtn.setFont(Font.font(20));
		Button exitBtn = new Button("Exit");
		exitBtn.setFont(Font.font(20));

		
		// adding buttons
		titleVbx.getChildren().addAll(headerTitle, playBtn, howToBtn, exitBtn);

		
		// setting the buttons to switch scenes
		playBtn.setOnAction(e -> window.setScene(boardGUI));
		howToBtn.setOnAction(e -> window.setScene(howTo));
		exitBtn.setOnAction(e -> window.close());

		
		// inputs the parts of the title page into a stack pane
		titleStack.getChildren().addAll(backgroundTitle, borderTitle, innerBackgroundTitle, titleVbx);

		// creates a scene for the title screen
		title = new Scene(titleStack, 1260, 1000);

//***************************************************************************************

		// GUI for board

		// initilaizes the board
		BorderPane boardDisplay = new BorderPane();

		// creates the borders
		Rectangle border = new Rectangle(150, 960);
		border.setFill(Color.BISQUE);
		Rectangle bottom = new Rectangle(1260, 20);
		bottom.setFill(Color.BISQUE);
		Rectangle top = new Rectangle(1260, 20);
		top.setFill(Color.BISQUE);

		// creates vboxes for both sides of the board display
		VBox leftBorderVbox = new VBox(60);
		VBox rightBorder = new VBox();
		
		// creates the buttons to be used
		Button menuBoard = new Button("Return to\n   Menu");
		menuBoard.setFont(Font.font(20));
		menuBoard.setOnAction(e -> window.setScene(title));

		
		Button restart = new Button("Restart Game");
		restart.setFont(Font.font(20));
		restart.setOnAction(e -> restart());

		
		//adds the parts to the left border 
		leftBorderVbox.getChildren().addAll(restart, menuBoard);
		leftBorderVbox.setAlignment(Pos.CENTER);

		// creates vboxes to be used to display info on the right side
		
		//creates vboxs to display the player and a forfeit button
		VBox player1 = new VBox(20);
		VBox player2 = new VBox(20);

		// creates the pieces for the vboxes
		Rectangle player2Border = new Rectangle(150, 320);
		player2Border.setFill(Color.BISQUE);
		Rectangle player1Border = new Rectangle(150, 320);
		player1Border.setFill(Color.BISQUE);
		Rectangle playerTurnBorder = new Rectangle(150, 320);
		playerTurnBorder.setFill(Color.BISQUE);

		
		// makes labels and forfiet button for both players
		Label player1Label = new Label("Player 1");
		Label player2Label = new Label("Player 2");

		player1Label.setFont(Font.font(30));
		player2Label.setFont(Font.font(30));

		Button ffplayer1 = new Button("Forfeit");
		Button ffplayer2 = new Button("Forfeit");

		
		// setting buttons perform an action
		ffplayer1.setFont(Font.font(20));
		ffplayer1.setOnAction(e ->playerTurn.setText("Black Wins"));

		ffplayer2.setFont(Font.font(20));
		ffplayer2.setOnAction(e -> playerTurn.setText(" White Wins"));

		
		// adding the parts to the vboxes and setting its position
		player1.getChildren().addAll(player1Label, ffplayer1);
		player2.getChildren().addAll(player2Label, ffplayer2);

		player1.setAlignment(Pos.CENTER);
		player2.setAlignment(Pos.CENTER);

		
		// creating and setting the label that shows whos turn it is
		playerTurn = new Label("White To Move");
		playerTurn.setFont(Font.font(20));

		
		// creating and adding the parts to the stack panes
		StackPane player1Stack = new StackPane();
		StackPane player2Stack = new StackPane();
		StackPane playerTurnStack = new StackPane();

		player1Stack.getChildren().addAll(player1Border, player1);
		player2Stack.getChildren().addAll(player2Border, player2);
		playerTurnStack.getChildren().addAll(playerTurnBorder, playerTurn);

		// setting up the right border into an hbox
		rightBorder.getChildren().addAll(player2Stack, playerTurnStack, player1Stack);

		// inputs the buttons into the borders where neccesary
		StackPane leftBorder = new StackPane();
		leftBorder.getChildren().addAll(border, leftBorderVbox);

		// inputs all the pieces of the board onto a borderPane
		boardDisplay.setCenter(gridBoard);
		boardDisplay.setRight(rightBorder);
		boardDisplay.setLeft(leftBorder);
		boardDisplay.setTop(top);
		boardDisplay.setBottom(bottom);

		// creates a scene for the board display
		boardGUI = new Scene(boardDisplay, 1260, 1000);

//***************************************************************************************

		// GUI for how to play
		StackPane howToStack = new StackPane();

		Rectangle howToBackground = new Rectangle(1260, 1000);
		howToBackground.setFill(Color.CORNSILK);
		Rectangle howToBorder = new Rectangle(550, 550);
		howToBorder.setFill(Color.BURLYWOOD);
		Rectangle howToInnerBackground = new Rectangle(500, 500);
		howToInnerBackground.setFill(Color.BISQUE);

		
		VBox howToVbx = new VBox(30);
		howToVbx.setAlignment(Pos.CENTER);

		
		
		
		Label howToLabel = new Label("How To Play");
		howToLabel.setFont(Font.font(30));
		Button moveBtn = new Button("How to move the pieces");
		moveBtn.setFont(Font.font(20));
		
		Button specialMovesBtn = new Button("SpecialMoves");
		specialMovesBtn.setFont(Font.font(20));
		
		Button checksBtn = new Button("Checks and checkmate");
		checksBtn.setFont(Font.font(20));
		
		Button menu = new Button("Go Back");
		menu.setFont(Font.font(15));
		howToVbx.getChildren().addAll(howToLabel, moveBtn, specialMovesBtn, checksBtn, menu);

		
		
		//creating new stackPanes for each how to play screen 
		
		StackPane movesStack = new StackPane();
		StackPane specialMovesStack = new StackPane();
		StackPane checksStack = new StackPane();
		
		//creating vboces for the how to play screens
		VBox movesVbox = new VBox(40);
		VBox specialMovesVbox = new VBox(40);
		VBox checksVbox = new VBox(40);
		
		//making the parts for the different how to play screens
		Rectangle newBackground = new Rectangle(1260, 1000);
		newBackground.setFill(Color.CORNSILK);
		Rectangle newBorder = new Rectangle(950, 550);
		newBorder.setFill(Color.BURLYWOOD);
		Rectangle newInnerBackground = new Rectangle(900, 500);
		newInnerBackground.setFill(Color.BISQUE);
		
		Rectangle newBackground2 = new Rectangle(1260, 1000);
		newBackground2.setFill(Color.CORNSILK);
		Rectangle newBorder2 = new Rectangle(950, 550);
		newBorder2.setFill(Color.BURLYWOOD);
		Rectangle newInnerBackground2 = new Rectangle(900, 500);
		newInnerBackground2.setFill(Color.BISQUE);
		
		Rectangle newBackground3 = new Rectangle(1260, 1000);
		newBackground3.setFill(Color.CORNSILK);
		Rectangle newBorder3 = new Rectangle(950, 550);
		newBorder3.setFill(Color.BURLYWOOD);
		Rectangle newInnerBackground3 = new Rectangle(900, 500);
		newInnerBackground3.setFill(Color.BISQUE);
		
		//creating button to return to ht emain how to pay screen
		Button movesReturn = new Button("Go Back");
		movesReturn.setFont(Font.font(15));
		Button specialMovesReturn = new Button("Go Back");
		specialMovesReturn.setFont(Font.font(15));
		Button checksReturn = new Button("Go Back");
		checksReturn.setFont(Font.font(15));
		
		
		
		//creates labels for the text on how to play the game
		Label moves = new Label();
		Label specialMoves = new Label();
		Label checks =  new Label();
		Label movesHeader = new Label();
		Label specialMovesHeader = new Label();
		Label checksHeader =  new Label();
		
		//creates the text for the rules
		moves.setText("Pawn: Pawn can only move forward and only if there is not another piece in front of it,\n          the can attack if there is a piece forward and to the right"
				+ " or left. If the pawn is at the\n starting position it can move 2 squares forward \n\n Knight: The knight moves in an L shape going 2 squares in one direction"
				+ " and then one to the left or right The knight \n\n Bishop: The bishop moves diagonally along the colour it was assigned  \n\n Rook: The rook can only move "
				+ "vertically and horizontally across the board \n\n Queen: The queen moves like a bishop and a rook combined going diagonally, vertically and horizontally  "
				+ " \n\n King: The king can move one square in any direction but only if that square is not\n          being attacked by an enemy piece. Explained more in the Checks "
				+ "and Checkmate section  ");
		
		specialMoves.setText("The special moves consist of castling and promotion: \n\n Promotion: This happens when the pawn reaches the end of the board, when it does it automatically becomes a queen \n"
				+ " \nCastling: This move invloces the king and rooks. If both the king and a rook have not moved from the intial spot,\n              there are no pieces between them, and the squares between them"
				+ " arent in check theis can happen.\n              The king will move two squares towards one of the rooks and the rook will hop over the king landing\n              adjacent to it.");
		
		checks.setText("Checks and Checkmate is the system in which a player loses or wins \n\n Checks: This is when the king is in a square that is being attacked by another "
				+ "piece which means\n              that the enemy piece would be able to take the king in the next turn. This must be addressed\n              immediately and you can not play anymoves "
				+ "that will still\n              leave you you king in check. \n\n CheckMate: This happens when a king is in check and no move from anypiece\n                     will prevent the king from being "
				+ "in check in the next turn.\n                     When this happens the player in check loses the game.");
		
		//creating headers for the text
		movesHeader.setText("How to Move");
		specialMovesHeader.setText("Special Moves");		
		checksHeader.setText("Checks and \n Checkmate");
						
		//sets the fonts of the text and the alignment of the vboxes
		moves.setFont(Font.font(17));
		specialMoves.setFont(Font.font(17));
		checks.setFont(Font.font(17));
		
		movesHeader.setFont(Font.font(25));
		specialMovesHeader.setFont(Font.font(25));
		checksHeader.setFont(Font.font(25));
		
		movesVbox.setAlignment(Pos.CENTER);
		specialMovesVbox.setAlignment(Pos.CENTER);
		checksVbox.setAlignment(Pos.CENTER);
		
		//adding all the parts to the different vboxes
		movesVbox.getChildren().addAll(movesHeader, moves, movesReturn );
		specialMovesVbox.getChildren().addAll(specialMovesHeader, specialMoves, specialMovesReturn );
		checksVbox.getChildren().addAll(checksHeader, checks, checksReturn );
		
		//adds the different parts to a stack pane
		movesStack.getChildren().addAll(newBackground, newBorder, newInnerBackground, movesVbox);
		specialMovesStack.getChildren().addAll(newBackground2, newBorder2, newInnerBackground2, specialMovesVbox);
		checksStack.getChildren().addAll(newBackground3, newBorder3, newInnerBackground3, checksVbox);
		
		
		//creating scenes for each of the subsections
		Scene movesScene = new Scene(movesStack, 1260, 1000);
		Scene specialMovesScene = new Scene(specialMovesStack, 1260, 1000);
		Scene checksScene = new Scene(checksStack, 1260, 1000);
		
		// setting button to switch scenes
		menu.setOnAction(e -> window.setScene(title));
		moveBtn.setOnAction(e -> window.setScene(movesScene));
		specialMovesBtn.setOnAction(e -> window.setScene(specialMovesScene));
		checksBtn.setOnAction(e -> window.setScene(checksScene));
		
		movesReturn.setOnAction(e -> window.setScene(howTo));
		specialMovesReturn.setOnAction(e -> window.setScene(howTo));
		checksReturn.setOnAction(e -> window.setScene(howTo));
		
		// adds all of the parts of the GUI into a stackpane
		howToStack.getChildren().addAll(howToBackground, howToBorder, howToInnerBackground, howToVbx);

		// creates a scene for the how to play screen
		howTo = new Scene(howToStack, 1260, 1000);

//***************************************************************************************

		//sets the stage equal to a private variable to be used in a later stage
		this.stage = window;

		// initiates the title screen and sets the title of the screen
		window.setScene(title);
		window.setTitle("Chess Game");
		window.show();

	}// end start method

	// launches the program
	public static void main(String[] args) {

		launch(args);
	}// end main method

	
	/* Nicholas Ospina
	 * Method Name: restart 
	 * Description; resets the board to have the pieces on their original squares
	 * Parameters: N/a
	 * Returns: N/a
	 */
	public static void restart() {
		
		//runs through all the squares and removes their info
		for (int i = 0; i < allSquares.length; i++) {
			for (int j = 0; j < allSquares.length; j++) {
				
				allSquares[i][j].removeInfo();
							
			}//end for
		}//end for
		
		//resets values back to original
		allSquares = inputPieces(allSquares);
		iswhiteTurn = true;
		playerTurn.setText("White To Move");
		
		if(isKingChecked) {
			
			//searchs through the squares to find the king
			for (int i = 0; i < allSquares.length; i++) {
				
				for (int j = 0; j < allSquares.length; j++) {
					
					//if the king that is in check is found
					if(allSquares[i][j].getPiece() instanceof King && allSquares[i][j].getPiece().isWhite() == iswhiteTurn) {
						
						//change the square back to normal
						allSquares[i][j].setRectColour(kingSquareColour);
						
					}//end if
					
				}// end for
			}// end for
			
		}//end if
		
	}// end method restart
	
	
	
	/* Nicholas Ospina
	 * Method Name: setDrag 
	 * Description; sets a certain square to be draggable
	 * Parameters: Square square
	 * Returns: returns the square with a draggable function
	 */
	public Square setDrag(Square square) throws Exception {

		// allow the square to be able to be picked up
		square.getDisplay().setOnDragDetected(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {

				
				// initializes the valid locations of pieces when it is grabbed
				validLocations = new ArrayList<Location>();

				// if the king is in check
				if (isKingChecked) {

					// if user tries ot move king when in check
					if (square.getPiece() instanceof King) {

						
						//intializes list to use 
						ArrayList<Location> checkToUse = new ArrayList<Location>();

						//checks to see which squares are in check for the corresponding colour
						if (!iswhiteTurn) {

							checkToUse = whiteChecked;
						} else {

							checkToUse = blackChecked;
						}//end if
						
						// calculates the possible moves if the king is in check
						calcCheckMoves(!iswhiteTurn, checkToUse);
						validLocations = kingCheck;
						

					} // end if
					else {// if user tries ot move piece other than king when in check

						ArrayList<Location> tempHolder = getValidMoves(square, false);

						// search through some possible moves and see if they can be played to remove
						// the king from check
						for (Location location : tempHolder) {

							int row = location.getRank();
							int col = location.getCol();

							// if the location has a row or column out of bounds dicsrd the move
							if (row >= 8 || row < 0 || col >= 8 || col < 0) {

								continue;
							}// end if

							// simulates if the piece was in a potential square it can move to
							ChessPiece pieceHolder = allSquares[row][col].getPiece();
							allSquares[row][col].setPiece(square.getPiece());

							// checks which squares are in check if the move was made, and if the king is in
							// check as a result
							checkedSquare(!iswhiteTurn);
							isKingCheck(!iswhiteTurn);

							// if after the check has been done the king is not in check list it as a valid
							// move
							if (!isKingChecked) {
								validLocations.add(location);

							}// end if

							// resets the simulation back to normal
							allSquares[row][col].setPiece(pieceHolder);

						} // end for

					} // end else

					//make this value true to change the colour of the king red in a later step
					wasChecked = true;

				} // end if king is in check

				
				// if the square has a piece allow it to be draggable
				else if (square.getImage() != null) {

					// finds what moves the piece can make
					ArrayList<Location> tempHolder = getValidMoves(square, false);

					// goes through all of the moves of the piece
					for (Location location : tempHolder) {

						int row = location.getRank();
						int col = location.getCol();

						// if the location has a row or column out of bounds dicsrd the move
						if (row >= 8 || row < 0 || col >= 8 || col < 0) {

							continue;
						}// end if

						// if the piece is in one of its potential squares is the king still in check
						ChessPiece pieceHolder = allSquares[row][col].getPiece();
						ChessPiece pieceHolder2 = square.getPiece();
						allSquares[row][col].setPiece(square.getPiece());
						square.setPiece(null);

						// checks which squares are in check if the move was made, and if the king is in
						// check as a result
						checkedSquare(!iswhiteTurn);
						isKingCheck(!iswhiteTurn);

						// if after the check has been done the king is not in check list it as a valid
						// move
						if (!isKingChecked) {
							validLocations.add(location);

						}// end if

						
						// resets the simulation back to normal
						allSquares[row][col].setPiece(pieceHolder);
						square.setPiece(pieceHolder2);
					}// end for

				}// end else if

				
				// creates an instance of this current squares location and piece to be used
				// later
				Location newLocation = new Location(square.getLocation().getRank(), square.getLocation().getCol());
				movingFromSquare = new Square(newLocation, square.getPiece());

				
				// for aesthetic on the drag makes image to drag around
				Dragboard db = square.getDisplay().startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				content.putImage(square.getImage());
				db.setContent(content);

				// for the colors of the squares when being dragged
				squareColour = new Color[validLocations.size()];

				
				

				// at the valid locations make the square colour green to signify it being able
				// to move there
				int counter = 0;
				for (Location location : validLocations) {

					// if the piece grabbed is of the right colour show the squares it move to with colours
					if (square.getPiece().isWhite() == iswhiteTurn) {

						squareColour[counter] = allSquares[location.getRank()][location.getCol()].getRectColour();
						allSquares[location.getRank()][location.getCol()].setRectColour(Color.LIGHTGREEN);

						counter++;
					}// end if
				}// end for

				event.consume();

			}// end method handle

		});// ends the action

		
		// allows the square to delete its previous information once a piece has been
		// dragged to another square
		square.getDisplay().setOnDragDone(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {

				// rests the colours of the squares
				int counter = 0;
				for (Location location : validLocations) {

					//initializing variables to compare with locations 
					int row = location.getRank();
					int col = location.getCol();

					// squareColour[counter] = allSquares[row][col].getRectColour();
					if (square.getPiece().isWhite() == iswhiteTurn) {

						allSquares[row][col].setRectColour(squareColour[counter]);

						counter++;
					}// end if
				}// end for

				// once the previous piece has been transffered reset the information of this
				// square to be an empty square
				if (event.getTransferMode() == TransferMode.MOVE) {

					
					// resets the square to be empty
					square.removeInfo();

					event.consume();

					
					// if one of the castle values is true check which castle it is and perform the special operation
					if(movingFromSquare.getPiece() instanceof King && (WCastleL || WCastleR || BCastleL || BCastleR)){
						
						// if caslting with left white rook
						if(WCastleL) {
							
							castleMove(7, 0, 3);
							
							WCastleL = false;
						}// end if
						
						else if(WCastleR) {// if caslting with right white rook
							
							castleMove(7, 7, 5);
							
							WCastleR = false;
						}// end if
						
						else if(BCastleL) {// if caslting with left black rook
							
							castleMove(0, 0, 3);
							
							BCastleL = false;
						}// end if
						
						else  {// if caslting with right black rook
							
							castleMove(0, 7, 5);
							
							BCastleR = false;
						}// end if
						
						
					}//end if
					
					
					// sees which squares are in check
					checkedSquare(true);
					checkedSquare(false);


					
					// switches the turns
					if (iswhiteTurn) {

						iswhiteTurn = false;
					} else if (!iswhiteTurn) {
						iswhiteTurn = true;
					} // end ifs

					// checks if the king is in check based on which squares are in check
					isKingCheck(!iswhiteTurn);
					
					
					// if the king is in check make the square colour red to signify it
					if (isKingChecked) {

						for (int i = 0; i < allSquares.length; i++) {
							for (int j = 0; j < allSquares.length; j++) {

								// if the right king is found make the colour of its square red
								if (allSquares[i][j].getPiece() instanceof King
										&& allSquares[i][j].getPiece().isWhite() == iswhiteTurn) {

									// make the square colour of the king red
									kingSquareColour = allSquares[i][j].getRectColour();
									allSquares[i][j].setRectColour(Color.RED);
									
								} // end if

							} // end for
						} // end for

					} // end if

					// if the king was in check but is not anymore return the square colour to
					// normal
					else if (wasChecked) {

						
						// if king moved change the square where the king was back to normal
						if (movingFromSquare.getPiece() instanceof King) {

							allSquares[movingFromSquare.getLocation().getRank()][movingFromSquare.getLocation()
									.getCol()].setRectColour(kingSquareColour);

							
						} else {// if another piece moved to prevent check change the square where the king is back to normal

							// finds where the king of the corresponding colour is
							for (int i = 0; i < allSquares.length; i++) {

								for (int j = 0; j < allSquares.length; j++) {

									// if the piece is a king and of the right colour reset the square where the king is back to normal
									if (allSquares[i][j].getPiece() instanceof King
											&& allSquares[i][j].getPiece().isWhite() != iswhiteTurn) {

										allSquares[i][j].setRectColour(kingSquareColour);
										
									}// end if

								}// end for

							}// end for

						}// end if another piece was moved

						// reset the value
						wasChecked = false;
						
					} // end else if king was previously checked
				} // end if

				// if the king is checked
				if (isKingChecked) {

					// check if there are possible moves, and check if it is checkmate
					if (isCheckmate()) {
					

						// set a label to say whos turn it is based on last move
						if (!iswhiteTurn) {
							playerTurn.setText("CheckMate\n White Wins");
						} else {

							playerTurn.setText("CheckMate\n Black Wins");
						}// end if

						
					}// end if
					
				} else {// if the king is not in check

					// set a label to say whos turn it is based on last move
					if (iswhiteTurn) {
						playerTurn.setText("White To Move");
					} else {

						playerTurn.setText("Black To Move");
					}// end if
					
				}// end if transfer occured

			}// end method handle

		});// ends the action

		return square;

	}// end method setDrag

	
	
	/* Nicholas Ospina
	 * Method Name: setDragOver 
	 * Description; sets a certain square to be able to have an object dragged on to it
	 * Parameters: Square square
	 * Returns: returns the square with a function to be dragged on
	 */
	public Square setDragOver(Square square) {

		// checks if the square that is being hoevered over is valid
		square.getDisplay().setOnDragOver(new EventHandler<DragEvent>() {

			public void handle(DragEvent event) {

				// variables to use and check the valid moves
				boolean moveable = false;
				// ArrayList<Location> locations = getValidMoves(movingFromSquare,
				// square);//grabs the valid moves a piece based on the piece they have

				// cycles through all the possible moves of a piece and references it with this
				// square
				for (Location location : validLocations) {

					// if this square is one of the valid moves aloow the piece to move here
					if (location.getCol() == square.getLocation().getCol()
							&& location.getRank() == square.getLocation().getRank()) {

						moveable = true;
					} // end if

				} // end for

				// makes the current piece unable to move to a square occupied by a same
				// coloured piece
				if (square.getPiece() != null && movingFromSquare.getPiece().isWhite() == square.getPiece().isWhite()) {

					moveable = false;
				} // end if

				// if the piece is not of the right coloiur to play
				if (movingFromSquare.getPiece().isWhite() != iswhiteTurn) {

					moveable = false;

				} // end if

				// if moveable has proven to be true allow the piece to move here
				if (event.getGestureSource() != square.getDisplay() && moveable) {
					/* allow for both copying and moving, whatever user chooses */
					event.acceptTransferModes(TransferMode.MOVE);

				}// end if

				event.consume();
			}// end method handle

		});// ends the action

		
		// sets new information to this square once a piece is dragged on to it
		square.getDisplay().setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {


				// if there is an enemy piece here remove it to allow for the new piece
				if (square.getImageView() != null) {

					square.removeInfo();
					
				}// end if there was an enemy piece

				
				// if a promotion occurs
				if(movingFromSquare.getPiece() instanceof Pawn && (square.getLocation().getRank() == 7 || square.getLocation().getRank() == 0)) {
					
					// create a new queen imageview
					ImageView newImageView = new ImageView();
					Queen newQueen = new Queen();
					
					// makes the queen white or black depending on which user promoted
					if(iswhiteTurn) {
						newImageView.setImage(imageWQueen);
						square.setImage(imageWQueen);
						newQueen.setWhitePiece(true);
					}
					else {
						newImageView.setImage(imageBQueen);
						square.setImage(imageBQueen);
						newQueen.setWhitePiece(false);
					}// end else
					
					
					setImageViewValues(newImageView, 100);
					square.setImageView(newImageView);
					square.setPiece(newQueen);
					
				}// end if promotion occurs
				
				// if there is no promotion occuring, move the piece normally
				else {
					
					// used to get some information about the piece that was grabbed initailly
					Dragboard db = event.getDragboard();
					
					// gives this square the information that the piece's square that moved here
					// previously had
					ImageView newImageView = new ImageView();
					newImageView.setImage(db.getImage());
					setImageViewValues(newImageView, 100);
					square.setImageView(newImageView);
					square.setImage(db.getImage());
					square.setPiece(movingFromSquare.getPiece());
					
					
				}// end if no promotion
				
				// let the source know whether the image was successfully transferred and used
				event.setDropCompleted(true);

				event.consume();

			}// end method handle
		});// ends the action

		return square;
	}// end method setDragOver

	/* Nicholas Ospina
	 * Method Name: castleMove
	 * Description: performs the move of the rook if a user castles
	 * Parameters: int row, int colFrom, int colTo
	 * Returns: N/a
	 */
	public static void castleMove(int row, int colFrom, int colTo ) {
		
		// sets the square the rook is moving to, to have the right information
		allSquares[row][colTo].setPiece(allSquares[row][colFrom].getPiece());
		allSquares[row][colTo].setImage(allSquares[row][colFrom].getImage());
		allSquares[row][colTo].setImageView(allSquares[row][colFrom].getImageView());
		
		// resets the square to be empty
		allSquares[row][colFrom].removeInfo();
		
	}//end method castleMove
	
	
	/* Nicholas Ospina
	 * Method Name: isCheckMate
	 * Description: checks which squares are being put in check by a side
	 * Parameters: boolean colour
	 * Returns: N/a
	 */
	public static boolean isCheckmate() {

		// creates new instance of the moves that are valid
		validLocations = new ArrayList<Location>();

		for (int i = 0; i < allSquares.length; i++) {
			for (int j = 0; j < allSquares.length; j++) {

				// get only the current colours pieces
				if (allSquares[i][j].getPiece() != null && allSquares[i][j].getPiece().isWhite() == iswhiteTurn) {

					//creates a list that holds the moves of a piece
					ArrayList<Location> tempHolder = getValidMoves(allSquares[i][j], true);

					// search through some possible moves and see if they can be played to remove
					// the king from check
					for (Location location : tempHolder) {

						//creates instance of the location to make easier to call
						int row = location.getRank();
						int col = location.getCol();

						// if the location has a row or column out of bounds dicsrd the move
						if (row >= 8 || row < 0 || col >= 8 || col < 0) {

							continue;
						}// end if

						// if potential square is empty or has no ally piece on it run a simulation 
						else if(allSquares[row][col].getPiece() == null || allSquares[row][col].getPiece().isWhite() != iswhiteTurn) {
							
							
							// if the piece is in one of its potential squares is the king still in check
							ChessPiece pieceHolder = allSquares[row][col].getPiece();
							ChessPiece pieceHolder2 = allSquares[i][j].getPiece();
							allSquares[row][col].setPiece(allSquares[i][j].getPiece());
							allSquares[i][j].setPiece(null);
							
							// checks which squares are in check if the move was made, and if the king is in
							// check as a result
							checkedSquare(!iswhiteTurn);
							isKingCheck(!iswhiteTurn);
							
							
							// if after the check has been done the king is not in check list it as a valid
							// move
							if (!isKingChecked && (allSquares[row][col].getPiece() == null
									|| allSquares[row][col].getPiece().isWhite() == iswhiteTurn)) {
								validLocations.add(location);
								
							}//end if king is still in check 
							
							// resets the simulation back to normal
							allSquares[row][col].setPiece(pieceHolder);
							allSquares[i][j].setPiece(pieceHolder2);
							
						}// end if
						
					}// end for

				} // end for

			} // end for
			
		} // end for

		// if there are no moves available it is checkmate
		if (validLocations.isEmpty()) {

			return true;
		}

		//if not checkmate continue the game
		return false;

	}// end method isCheckmate

	
	
	/* Nicholas Ospina
	 * Method Name: checkedSquare
	 * Description: checks which squares are being put in check by a side
	 * Parameters: boolean colour
	 * Returns: N/a
	 */
	public static void checkedSquare(boolean colour) {

		// creates a list to hold the squares that are in check for this method
		ArrayList<Location> checkToUse = new ArrayList<Location>();

		// goes through all of the pieces of this colour and puts all their possible
		// moves on a list
		for (int i = 0; i < allSquares.length; i++) {

			for (int j = 0; j < allSquares.length; j++) {

				//goes through all the pieces of a certain colour and adds them to a list
				if (allSquares[i][j].getPiece() != null && allSquares[i][j].getPiece().isWhite() == colour) {
				
					//this is check to see which squares are in check so it will be different from normal move checking
					checkToUse.addAll(getValidMoves(allSquares[i][j], true));

				} // end if

			} // end for
		} // end for

		// put the found information into an object to be referenced later
		if (colour) {// if  info for white

			whiteChecked = checkToUse;
		} else {// if  info for black

			blackChecked = checkToUse;
		} // end else

	}// end method checkedSquare

	
	
	/* Nicholas Ospina
	 * Method Name: isKingCheck
	 * Description: checks if the king is in check
	 * Parameters: boolean colour
	 * Returns: n/a
	 * makes a value true to signify the king is in check
	 */
	public static void isKingCheck(boolean colour) {

		isKingChecked = false;

		ArrayList<Location> checkToUse = new ArrayList<Location>();

		//checks to see which squares are in check for the corresponding colour
		if (colour) {

			checkToUse = whiteChecked;
		} else {

			checkToUse = blackChecked;
		}//end if

		
		//goes through all the squares that are checkes
		for (Location location : checkToUse) {

			// check if king is in check
			for (int i = 0; i < allSquares.length; i++) {

				for (int j = 0; j < allSquares.length; j++) {

					// if the king is on a square that is in check, the king is in check
					if (location.getCol() == allSquares[i][j].getLocation().getCol()
							&& location.getRank() == allSquares[i][j].getLocation().getRank()
							&& allSquares[i][j].getPiece() instanceof King
							&& allSquares[i][j].getPiece().isWhite() != colour) {

						isKingChecked = true;

					} // end if

				} // end for

			} // end for
		} // end for

	}// end method isKingCheck

	
	
	/* Nicholas Ospina
	 * Method Name: calcCheck Moves
	 * Description: checks the possible moves of the king when the king is in check
	 * Parameters: boolean colour, ArrayList<Location> checkToUse
	 * Returns: N/a
	 * puts the possible moves into an object to be able to be reference later
	 */
	public static void calcCheckMoves(boolean colour, ArrayList<Location> checkToUse) {

		//list to hold the possible moves of the king if it is in check
		kingCheck = new ArrayList<Location>();

		
		
		// find the valid moves of the king
		for (int i = 0; i < allSquares.length; i++) {

			for (int j = 0; j < allSquares.length; j++) {

				if (allSquares[i][j].getPiece() instanceof King && allSquares[i][j].getPiece().isWhite() != colour) {

					// finds the valid moves of the king
					ArrayList<Location> temp = getValidMoves(allSquares[i][j], false);

					
					// goes through all the moves and checks if they collide witht he squares in
					// check
					for (Location location : temp) {

						boolean moveable = true;

						//goes through all the squares that are in check
						for (Location check : checkToUse) {

							// if the two values collide the the square can not be moved to
							if (location.getCol() == check.getCol() && location.getRank() == check.getRank()) {

								moveable = false;

							} // end if

						} // end for

						// if the piece can move to this square add it to the possible moves when the
						// king is in check
						if (moveable) {

							kingCheck.add(location);

						} // end if

					} // end for

				} // end if piece is king and of the right colour

			} // end for

		} // end for

	}// end method calcCheckMoves

	
	
	/* Nicholas Ospina
	 * Method Name: getValidMoves
	 * Description: checks the possible moves of a given piece
	 * Parameters: Square initialSquare, boolean squareCheck
	 * Returns: N/a
	 * 
	 */
	public static ArrayList<Location> getValidMoves(Square initialSquare, boolean squareCheck) {

		// initializes current row and column of the square to calculate the pieces moves
		int row = initialSquare.getLocation().getRank();
		int col = initialSquare.getLocation().getCol();

		ArrayList<Location> locations = new ArrayList<Location>();// Create an ArrayList object

		
		
		// if the piece is a knight give it its corresponding possibile moves
		if (initialSquare.getPiece() instanceof Knight) {

			// get the moves of the knight
			Knight knight = new Knight();
			locations.addAll(knight.getMoves(row, col, allSquares, initialSquare, squareCheck));
			
		}// end if the piece is a knight

		
		// if the piece is a Rook give it its corresponding possible moves
		else if (initialSquare.getPiece() instanceof Rook) {

			// get the moves of the rook
			Rook rook = new Rook();
			locations.addAll(rook.getMoves(row, col, allSquares, initialSquare, squareCheck));

		}//end if the piece is a rook
		
		
		// if the piece is a Bishop give it its corresponding possibile moves
		else if (initialSquare.getPiece() instanceof Bishop) {

			// get the moves of the bishop
			Bishop bishop = new Bishop();
			locations.addAll(bishop.getMoves(row, col, allSquares, initialSquare, squareCheck));

			
		}// end if the piece is a bishop

		
		// if the piece is a Pawn give it its corresponding possibile moves
		else if (initialSquare.getPiece() instanceof Pawn) {

			// get the moves of the pawn
			Pawn pawn = new Pawn();
			locations.addAll(pawn.getMoves(row, col, allSquares, initialSquare, squareCheck));

		}// end if the piece is a pawn
		
		

		// if the piece is a Queen give it its corresponding possibile moves
		else if (initialSquare.getPiece() instanceof Queen) {

			// get the moves of the queen
			Queen queen = new Queen();			
			locations.addAll(queen.getMoves(row, col, allSquares, initialSquare, squareCheck)) ;

		}// end if queen moves
		
		
		// if the piece is a King give it its corresponding possible moves
		else if (initialSquare.getPiece() instanceof King) {

			// initilaizes a list to be used as a holder
			ArrayList<Location> temp = new ArrayList<Location>();

			// get the moves of the king
			King king  = new King();
			temp.addAll(king.getMoves(row, col, allSquares, initialSquare, squareCheck));
			
			
			
			// if checking whcich squares are in check
			if (squareCheck) {

				// add all the kings possible moves
				for (Location location : temp) {
					locations.add(location);

				}

			} else if (!squareCheck) {// if checking noraml moves of piece

				
				ArrayList<Location> checkToUse = new ArrayList<Location>();
				
				// checks whose turn it is in order to use the right arraylist to check if the king is check
				if (!initialSquare.getPiece().isWhite()) {

					checkToUse = whiteChecked;

				} else {

					checkToUse = blackChecked;
				}// end if
				

				// go through all the locations of the king 
				for (Location location : temp) {

					boolean moveable = true;

					// go through all of the squares that are in check
					for (Location check : checkToUse) {

						// if the sqauares in question have the same location make the king unable to move there
						if (location.getCol() == check.getCol() && location.getRank() == check.getRank()) {

							moveable = false;

						}//end if

					} // end for

					// if the square is not in check king can move there
					if (moveable) {

						// checks if the move is within the paramters and if there is an ally or enemny there
						// can move if the square is empty or if there is an unprotected enemy
						if (location.getRank() < 8 && location.getRank() >= 0 && location.getCol() < 8 && location.getCol() >= 0
								&& (allSquares[location.getRank()][location.getCol()].getPiece() == null
										|| allSquares[location.getRank()][location.getCol()].getPiece()
												.isWhite() != initialSquare.getPiece().isWhite())) {

							locations.add(location);
							
						}// end if
					}// end if
					
				} // end for
				
				
				
				// check if the white king can castle
				if(initialSquare.getPiece().isWhite()) {
					
					// test to see if king is in right location to castle
					Location testKing =  new Location(7, 4);
		
					// if king is in right place
					if(row == testKing.getRank() && col == testKing.getCol()) {
						
						// if left rook is in the right place and there are no piece in between the king and rook
						if(allSquares[7][0].getPiece() instanceof Rook && allSquares[7][1].getPiece() == null && allSquares[7][2].getPiece() == null && allSquares[7][3].getPiece() == null) {
							
							locations.add(new Location(7, 2));
							WCastleL = true;
						}// end if for left rook
						
						// if left rook is in the right place and there are no piece in between the king and rook
						else if(allSquares[7][7].getPiece() instanceof Rook && allSquares[7][6].getPiece() == null && allSquares[7][5].getPiece() == null) {
							
							locations.add(new Location(7, 6));
							WCastleR = true;
							
						}// end if of right rook
						
					}// end if king in right place
					
				}// end if white king castle
				else {//check if the black king can castle
					
					// test to see if king is in right location to castle
					Location testKing =  new Location(0, 4);
					
					// if king is in right place
					if(row == testKing.getRank() && col == testKing.getCol()) {
						
						// if left rook is in the right place and there are no piece in between the king and rook
						if(allSquares[0][0].getPiece() instanceof Rook && allSquares[0][1].getPiece() == null && allSquares[0][2].getPiece() == null && allSquares[0][3].getPiece() == null) {
							
							locations.add(new Location(0, 2));
							BCastleL = true;

						}// end if for left rook
						
						// if left rook is in the right place and there are no piece in between the king and rook
						else if(allSquares[0][7].getPiece() instanceof Rook && allSquares[0][6].getPiece() == null && allSquares[0][5].getPiece() == null) {
							
							locations.add(new Location(0, 6));
							BCastleR = true;

						}// end if of right rook
						
					}// end if king in right place
					
					
				}// end if black king castle
				
				
			}// end if it is not a square check
		
		}//end if the piece is a king	
		
		//return the valid locations of this piece
		return locations;

	}// end method getValidMoves


	/* Nicholas Ospina
	 * Method Name: setImageViewValues
	 * Description: sets the imageview to be a certain size and preserves the ratio of the image
	 * Parameters: ImageView piece, int setWidth
	 * Returns: returns the image with the new size
	 */
	public static ImageView setImageViewValues(ImageView piece, int setWidth) {

		//sets the values of the image
		piece.setFitHeight(setWidth);
		piece.setFitWidth(setWidth);
		piece.setPreserveRatio(true);

		return piece;

	}// end method setImageViewValues

	
//*****************************************************************************************************************************

	/* Nicholas Ospina
	 * Method Name: inputPieces
	 * Description: inputs images, pieces and colours of them into certain squares to display on the board
	 * Parameters: Square[][] allSquares
	 * Returns: returns the Md array of squares with the new information
	 */
	public static Square[][] inputPieces(Square[][] allSquares) {

		// size of the images
		int size = 100;

		// creates the imageview of black rooks, attaches them to a square, gives them a
		// colour and a piece

		ImageView viewBRook = new ImageView();
		viewBRook.setImage(imageBRook);
		setImageViewValues(viewBRook, size);
		allSquares[0][0].setImageView(viewBRook);
		allSquares[0][0].setImage(imageBRook);

		ImageView viewBRook2 = new ImageView();
		viewBRook2.setImage(imageBRook);
		setImageViewValues(viewBRook2, size);
		allSquares[0][7].setImageView(viewBRook2);
		allSquares[0][7].setImage(imageBRook);

		Rook pieceBRook = new Rook();
		allSquares[0][0].setPiece(pieceBRook);

		Rook pieceBRook2 = new Rook();
		allSquares[0][7].setPiece(pieceBRook2);

		// creates the imageview of black bishops, attaches them to a square, gives them
		// a colour and a piece

		ImageView viewBBishop = new ImageView();
		viewBBishop.setImage(imageBBishop);
		setImageViewValues(viewBBishop, size);
		allSquares[0][2].setImageView(viewBBishop);
		allSquares[0][2].setImage(imageBBishop);

		ImageView viewBBishop2 = new ImageView();
		viewBBishop2.setImage(imageBBishop);
		setImageViewValues(viewBBishop2, size);
		allSquares[0][5].setImageView(viewBBishop2);
		allSquares[0][5].setImage(imageBBishop);

		Bishop pieceBBishop = new Bishop();
		allSquares[0][2].setPiece(pieceBBishop);

		Bishop pieceBBishop2 = new Bishop();
		allSquares[0][5].setPiece(pieceBBishop2);

		// creates the imageview of black knights, attaches them to a square, gives them
		// a colour and a piece

		ImageView viewBKnight = new ImageView();
		viewBKnight.setImage(imageBKnight);
		setImageViewValues(viewBKnight, size);
		allSquares[0][1].setImageView(viewBKnight);
		allSquares[0][1].setImage(imageBKnight);

		ImageView viewBKnight2 = new ImageView();
		viewBKnight2.setImage(imageBKnight);
		setImageViewValues(viewBKnight2, size);
		allSquares[0][6].setImageView(viewBKnight2);
		allSquares[0][6].setImage(imageBKnight);

		Knight pieceBKnight = new Knight();
		allSquares[0][1].setPiece(pieceBKnight);
		Knight pieceBKnight2 = new Knight();
		allSquares[0][6].setPiece(pieceBKnight2);

		// creates the imageview of black queen, attaches them to a square, gives them a
		// colour and a piece

		ImageView viewBQueen = new ImageView();
		viewBQueen.setImage(imageBQueen);
		setImageViewValues(viewBQueen, size);
		allSquares[0][3].setImageView(viewBQueen);
		allSquares[0][3].setImage(imageBQueen);

		Queen pieceBQueen = new Queen();
		allSquares[0][3].setPiece(pieceBQueen);

		// creates the imageview of black king, attaches them to a square, gives them a
		// colour and a piece

		ImageView viewBKing = new ImageView();
		viewBKing.setImage(imageBKing);
		setImageViewValues(viewBKing, size);
		allSquares[0][4].setImageView(viewBKing);
		allSquares[0][4].setImage(imageBKing);

		King pieceBKing = new King();
		allSquares[0][4].setPiece(pieceBKing);

		// creates the imageview of black Pawns, attaches them to a square, gives them a
		// colour and a piece

		for (int i = 0; i < 8; i++) {// cycles through all this coloured pawns

			ImageView viewBPawn = new ImageView();
			viewBPawn.setImage(imageBPawn);
			setImageViewValues(viewBPawn, size);
			allSquares[1][i].setImageView(viewBPawn);
			allSquares[1][i].setImage(imageBPawn);

			ChessPiece pieceBPawn = new Pawn();
			allSquares[1][i].setPiece(pieceBPawn);

		}// end for

		// ******************************************************

		// creates the white pieces

		// creates the imageview of white bishops, attaches them to a square, gives them
		// a colour and a piece

		ImageView viewWBishop = new ImageView();
		viewWBishop.setImage(imageWBishop);
		setImageViewValues(viewWBishop, size);
		allSquares[7][2].setImageView(viewWBishop);
		allSquares[7][2].setImage(imageWBishop);

		ImageView viewWBishop2 = new ImageView();
		viewWBishop2.setImage(imageWBishop);
		setImageViewValues(viewWBishop2, size);
		allSquares[7][5].setImageView(viewWBishop2);
		allSquares[7][5].setImage(imageWBishop);

		Bishop pieceWBishop = new Bishop();
		allSquares[7][2].setPiece(pieceWBishop);

		Bishop pieceWBishop2 = new Bishop();
		allSquares[7][5].setPiece(pieceWBishop2);

		// creates the imageview of white knights, attaches them to a square, gives them
		// a colour and a piece

		ImageView viewWKnight = new ImageView();
		viewWKnight.setImage(imageWKnight);
		setImageViewValues(viewWKnight, size);
		allSquares[7][1].setImageView(viewWKnight);
		allSquares[7][1].setImage(imageWKnight);

		ImageView viewWKnight2 = new ImageView();
		viewWKnight2.setImage(imageWKnight);
		setImageViewValues(viewWKnight2, size);
		allSquares[7][6].setImageView(viewWKnight2);
		allSquares[7][6].setImage(imageWKnight);

		Knight pieceWKnight = new Knight();
		allSquares[7][1].setPiece(pieceWKnight);

		Knight pieceWKnight2 = new Knight();
		allSquares[7][6].setPiece(pieceWKnight2);

		// creates the imageview of white rooks, attaches them to a square, gives them a
		// colour and a piece

		ImageView viewWRook = new ImageView();
		viewWRook.setImage(imageWRook);
		setImageViewValues(viewWRook, size);
		allSquares[7][0].setImageView(viewWRook);
		allSquares[7][0].setImage(imageWRook);

		ImageView viewWRook2 = new ImageView();
		viewWRook2.setImage(imageWRook);
		setImageViewValues(viewWRook2, size);
		allSquares[7][7].setImageView(viewWRook2);
		allSquares[7][7].setImage(imageWRook);

		Rook pieceWRook = new Rook();
		allSquares[7][0].setPiece(pieceWRook);

		Rook pieceWRook2 = new Rook();
		allSquares[7][7].setPiece(pieceWRook2);

		// creates the imageview of white queen, attaches them to a square, gives them a
		// colour and a piece

		ImageView viewWQueen = new ImageView();
		viewWQueen.setImage(imageWQueen);
		setImageViewValues(viewWQueen, size);
		allSquares[7][3].setImageView(viewWQueen);
		allSquares[7][3].setImage(imageWQueen);

		Queen pieceWQueen = new Queen();
		allSquares[7][3].setPiece(pieceWQueen);

		// creates the imageview of white king, attaches them to a square, gives them a
		// colour and a piece

		ImageView viewWKing = new ImageView();
		viewWKing.setImage(imageWKing);
		setImageViewValues(viewWKing, size);
		allSquares[7][4].setImageView(viewWKing);
		allSquares[7][4].setImage(imageWKing);

		King pieceWKing = new King();
		allSquares[7][4].setPiece(pieceWKing);

		// creates the imageview of white Pawns, attaches them to a square, gives them a
		// colour and a piece

		for (int i = 0; i < 8; i++) {// cycles through all this coloured pawns

			ImageView viewWPawn = new ImageView();
			viewWPawn.setImage(imageWPawn);
			setImageViewValues(viewWPawn, size);
			allSquares[6][i].setImageView(viewWPawn);
			allSquares[6][i].setImage(imageWPawn);

			Pawn pieceWPawn = new Pawn();
			allSquares[6][i].setPiece(pieceWPawn);

			// sets all the white pieces to be of white colour in the logic
			allSquares[6][i].setColour(true);
			allSquares[7][i].setColour(true);

		}// end for

		return allSquares;

	}// end method input pieces

	/* Nicholas Ospina
	 * Method Name: createImages
	 * Description: grabs the images from the computer and inputs them into objects that will be used later
	 * Parameters:N/A
	 * Returns: N/A
	 */
	public void createImages() throws FileNotFoundException {

		// creating the images of the black pieces

		// black bishop
		InputStream BBishopstream = new FileInputStream("src\\finalProject\\imagesforchess\\BBishop.png");
		imageBBishop = new Image(BBishopstream);

		// black knights
		InputStream BKnightstream = new FileInputStream("src\\finalProject\\imagesforchess\\BKnight.png");
		imageBKnight = new Image(BKnightstream);

		// black rooks
		InputStream BRookstream = new FileInputStream("src\\finalProject\\imagesforchess\\BRook.png");
		imageBRook = new Image(BRookstream);

		// black queen
		InputStream BQueenstream = new FileInputStream("src\\finalProject\\imagesforchess\\BQueen.png");
		imageBQueen = new Image(BQueenstream);

		// black king
		InputStream BKingstream = new FileInputStream("src\\finalProject\\imagesforchess\\BKing.png");
		imageBKing = new Image(BKingstream);

		// black Pawns
		InputStream BPawnstream = new FileInputStream("src\\finalProject\\imagesforchess\\BPawnReal.png");
		imageBPawn = new Image(BPawnstream);

		// **********************************************************************************

		// creating the images of the white pieces

		// White bishops
		InputStream WBishopstream = new FileInputStream("src\\finalProject\\imagesforchess\\WBishop.png");
		imageWBishop = new Image(WBishopstream);

		// White knights
		InputStream WKnightstream = new FileInputStream("src\\finalProject\\imagesforchess\\WKnight.png");
		imageWKnight = new Image(WKnightstream);

		// black rooks
		InputStream WRookstream = new FileInputStream("src\\finalProject\\imagesforchess\\WRook.png");
		imageWRook = new Image(WRookstream);

		// black queen
		InputStream WQueenstream = new FileInputStream("src\\finalProject\\imagesforchess\\WQueen.png");
		imageWQueen = new Image(WQueenstream);

		// black king
		InputStream WKingstream = new FileInputStream("src\\finalProject\\imagesforchess\\WKing.png");
		imageWKing = new Image(WKingstream);

		// black Pawns
		InputStream WPawnstream = new FileInputStream("src\\finalProject\\imagesforchess\\WPawn.png");
		imageWPawn = new Image(WPawnstream);

	}// end method create images

}//end class ChessGame
