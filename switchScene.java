package practiceObjects;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class switchScene extends Application {

	Stage window;
	Scene title, howToPlay;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		
		
		Label label = new Label("Welcome");
		Button button = new Button("clik here");
		button.setOnAction(e -> {
			window.setScene(howToPlay);
			System.out.println("scene2");
		});
		
		//layout1
		VBox layout = new VBox(20);
		layout.getChildren().addAll(label, button);
		title = new Scene(layout, 200, 200);
		
		//button 2
		Button button2 = new Button("Go back");
		button2.setOnAction(e -> {
			window.setScene(title);
			System.out.println("scene1");
		});
		
		//layout how to play
		StackPane stack = new StackPane();
		stack.getChildren().add(button2);
		howToPlay = new Scene(stack, 400, 300);
		
		window.setScene(title);
		window.setTitle("Chess");
		window.show();
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
