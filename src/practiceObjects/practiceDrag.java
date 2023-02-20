package practiceObjects;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class practiceDrag extends Application {

	@Override
	public void start(Stage window) throws Exception {
		
		Label l = new Label("Rawr");
		Rectangle r1 = new Rectangle(50,50);
		
		
		l.setFont(Font.font(50));
		
		l.setOnDragDetected(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent event) {
				
				Dragboard db = l.startDragAndDrop(TransferMode.MOVE);
				
				ClipboardContent content = new ClipboardContent(); 
				content.putString(l.getText());
				db.setContent(content);
				
				event.consume();
			}
			
			
			
		});
		
		
		Label  target = new Label("Here");
		
		target.setOnDragOver(new EventHandler<DragEvent>() {
		    
			public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target &&
		                event.getDragboard().hasString()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.MOVE);
		            
		        }
		        
		        event.consume();
		    }
		});
		
		target.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasString()) {
		           target.setText(db.getString());
		           success = true;
		           System.out.println("Yes");
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		Group root = new Group(l, target);
		Scene scene =  new Scene(root,  400,400);
		
		window.setScene(scene);
		window.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
