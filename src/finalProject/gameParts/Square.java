package finalProject.gameParts;



import finalProject.pieces.ChessPiece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square  {
	
	
	private Location location;
	private ImageView imageView;
	private Image image;
	private Rectangle rect;
	private StackPane display;
	private ChessPiece piece;
	

	/* Nicholas Ospina
	 * Constructor Name: Square 
	 * Description; construtor for the class, will create a stack pane display
	 * Parameters:  Location location,  Image image, ImageView imageView, ChessPiece piece
	 * Returns: n/a
	 */
	public Square( Location location,  Image image, ImageView imageView, ChessPiece piece) {
		
		this.setLocation(location);
		this.setImage(image);
		this.display = new StackPane();
		this.setPiece(piece);
		
	}//end constructor Square

	

	/* Nicholas Ospina
	 * Constructor Name: Square 
	 * Description; constructor for just location and a piece
	 * Parameters: Location location, ChessPiece piece
	 * Returns: n/a
	 */
	public Square(Location location, ChessPiece piece) {
		
		this.setLocation(location);
		this.setPiece(piece);
		
	}//end constructor Square
	
	/* Nicholas Ospina
	 * Constructor Name: Square 
	 * Description; constructor for empty square
	 * Parameters: n/a
	 * Returns: n/a
	 */
	public Square() {
		
		
	}//end constructor Square
	
	
	/* Nicholas Ospina
	 * Method Name: getLocation 
	 * Description; gets the location of the square
	 * Parameters: n/a
	 * Returns: location
	 */
	public Location getLocation() {
		
		
		return location;
	}//end method getLocation
	
	
	/* Nicholas Ospina
	 * Method Name: setLocation 
	 * Description; sets the location of the square
	 * Parameters: Location location
	 * Returns: n/a
	 */
	public void setLocation(Location location) {
		this.location = location;
	}//end method setLocation

	
	
	
	/* Nicholas Ospina
	 * Method Name: getImage 
	 * Description; gets the image of the square
	 * Parameters: n/a
	 * Returns: image
	 */
	public Image getImage() {
		return image;
		
	}//end method getImage


	/* Nicholas Ospina
	 * Method Name: setImage 
	 * Description; sets the image of the square
	 * Parameters: Image i
	 * Returns: n/a
	 */
	public void setImage(Image i) {
		this.image = i;
				
	}//end method setImage
	
	
	/* Nicholas Ospina
	 * Method Name: getPiece 
	 * Description; gets the ChessPiece of the square
	 * Parameters: n/a
	 * Returns: piece
	 */
	public ChessPiece getPiece() {
		return piece;
		
	}//end method getPiece

	/* Nicholas Ospina
	 * Method Name: setPiece 
	 * Description; sets the ChessPiece of the square
	 * Parameters: ChessPiece piece
	 * Returns:n/a
	 */
	public void setPiece(ChessPiece piece) {
		this.piece = piece;
		
	}//end method setPiece
	
	
	
	/* Nicholas Ospina
	 * Method Name: getRect 
	 * Description; gets the Rectangle of the square
	 * Parameters: n/a
	 * Returns:rect
	 */
	public Rectangle getRect() {
		return rect;
		
	}//end method getRect


	/* Nicholas Ospina
	 * Method Name: setRect 
	 * Description; sets the Rectangle of the square, and adds it to the stack pane
	 * Parameters: Rectangle r1
	 * Returns:n/a
	 */
	public void setRect(Rectangle r1) {
		this.rect = r1;
		
		this.display.getChildren().add(rect);
	}//end method setRect

	
	/* Nicholas Ospina
	 * Method Name: getRectColour 
	 * Description; gets the colour of the rectangle
	 * Parameters: n/a
	 * Returns: rect colour
	 */
	public Color getRectColour() {
		
		return (Color) this.rect.getFill();
		
	}//end method getRectColour
	
	/* Nicholas Ospina
	 * Method Name: setRectColour 
	 * Description; sets the colour of the rectangle
	 * Parameters: Color colour
	 * Returns: n/a
	 */
	public void setRectColour(Color colour) {
		
		this.rect.setFill(colour);
		
	}//end method setRectColour

	
	
	/* Nicholas Ospina
	 * Method Name: getDisplay 
	 * Description; gets the stackpane of the square
	 * Parameters: n/a
	 * Returns: display
	 */
	public StackPane getDisplay() {
		return display;
	}//end method getDisplay


	/* Nicholas Ospina
	 * Method Name: setDisplay 
	 * Description; sets the stackpane of the square
	 * Parameters: StackPane display
	 * Returns: n/a
	 */
	public void setDisplay(StackPane display) {
		
		this.display = display;
		
	}//end method setDisplay
	
	
	/* Nicholas Ospina
	 * Method Name: getImageView 
	 * Description; gets the imageview of the square
	 * Parameters: n/a
	 * Returns: imageView
	 */
	public ImageView getImageView() {
		return imageView;
	}//end method getImageView

	
	/* Nicholas Ospina
	 * Method Name: setImageView 
	 * Description; sets the imageview of the square
	 * Parameters: ImageView iV
	 * Returns: n/a
	 */
	public void setImageView(ImageView iV) {
		this.imageView = iV;
		
		// if the image view is not null remove fomr the stack pane
		if(iV != null) {
		
			this.display.getChildren().add(iV);
		}//end if

	}//end method setImageView
	
	
	/* Nicholas Ospina
	 * Method Name: removeInfo 
	 * Description; removes the information of a square except the rectangle
	 * Parameters: n/a
	 * Returns: n/a
	 */
	public void removeInfo() {
		
		//if the stack pane is greater than one, remove the piece from the stack pane
		if(this.display.getChildren().size() > 1) {
			
			this.display.getChildren().remove(1);
			
		}//end if
		
		//resets the info
		this.image = null;
		this.imageView = null;
		this.piece = null;
		
	}//end method removeInfo

	
	/* Nicholas Ospina
	 * Method Name: setColour 
	 * Description; sets the coluor of a piece
	 * Parameters: boolean isWhite
	 * Returns: n/a
	 */
	public void setColour(boolean isWhite) {
		
		this.piece.setWhitePiece(isWhite);
		
	}//end method setColour
	
		
}//end class Square


