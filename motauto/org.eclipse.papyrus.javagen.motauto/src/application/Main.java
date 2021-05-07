package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import motauto.AlterarEstructuraBBDD;
import motauto.Database;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		try 
		{
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Vistes/Alta_Factura.fxml"));
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Vistes/Borrar_Factura.fxml"));
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Vistes/Modificar_Factura.fxml"));
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Vistes/Alta_Articulo.fxml"));
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Vistes/Borrar_Articulo.fxml"));
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Vistes/Alta_Cliente.fxml"));
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Vistes/Borrar_Cliente.fxml"));
			AnchorPane root2 = (AnchorPane)FXMLLoader.load(getClass().getResource("/Vistes/Header.fxml"));

			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Vistes/Modificar_Cliente.fxml"));

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			root.getChildren().add(root2);

			primaryStage.setScene(scene);
			primaryStage.show();



		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) 
	{		
		launch(args);
	}
}
