package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	//AL EJECUTARSE EL MAIN ABRE LA VENTANA MENU.FXML
	@Override
	public void start(Stage primaryStage) {
		
		try 
		{
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Vistes/Menu.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//EJECUTA EL START
	public static void main(String[] args) 
	{		
		launch(args);
	}
}
