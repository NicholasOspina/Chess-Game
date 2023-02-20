package practiceObjects;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class eventHandling extends Application {

	Button button;
	Scene title, howToPlay;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Test");
		button = new Button();
		button.setText("Play");
		button.setOnAction(e -> stage.setScene(howToPlay));
		
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		
		Scene title =  new Scene (layout, 300, 250);
		Scene howToPlay =  new Scene (layout, 300, 250);
		
		
		
		stage.setScene(title);
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

	

}
