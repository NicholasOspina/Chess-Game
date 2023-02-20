package practiceObjects;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;	

/** Hello World program with GUI interface
 *  using JavaFX
 * @author Catherine McCaffery
 */
public class HelloWorld extends Application 
{
	/** start method sets up all graphical components
	 *  in the program 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		Text text = new Text(10,50,"Hello World");
		text.setFont(Font.font(50));

		Group root = new Group(text);
		Scene scene = new Scene(root);

		primaryStage.setTitle("Welcome to JavaFX");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/** Main method only launches args 
	 * (method found in the application class)
	 * @param args
	 */
	public static void main(String[] args) 
	{
		launch(args);
	}
}

