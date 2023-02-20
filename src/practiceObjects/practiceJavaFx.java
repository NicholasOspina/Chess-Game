package practiceObjects;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class practiceJavaFx extends Application implements EventHandler<ActionEvent> {

	Button button;

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Test");
		button = new Button();
		button.setText("Play");
		button.setOnAction(this);
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		
		Scene scene =  new Scene (layout, 300, 250);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args)  {
		
		launch(args);
	}
	
//	EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
//		   @Override 
//		   public void handle(MouseEvent e) { 
//		      System.out.println("Hello World"); 
//		      circle.setFill(Color.DARKSLATEBLUE);  
//		   } 
//		};

	@Override
	public void handle(ActionEvent event) {
		
		
		if(event.getSource() == button) {
			System.out.println("button pressed");
		}
		
	}   

	
}
