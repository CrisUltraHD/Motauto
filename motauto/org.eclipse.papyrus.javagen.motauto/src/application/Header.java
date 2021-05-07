package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import motauto.AlterarEstructuraBBDD;
import motauto.Cliente;
import motauto.Comprovaciones;
import motauto.Database;

public class Header extends Application {

    @FXML
    private MenuItem verClientes;

    @FXML
    private MenuItem modificarCliente;

    @FXML
    private MenuItem crearArticulo;

    @FXML
    private MenuItem verVehiculos;

    @FXML
    private MenuItem borrarFactura;

    @FXML
    private MenuItem verFacturas;

    @FXML
    private MenuItem borrarArticulo;

    @FXML
    private MenuItem crearFactura;

    @FXML
    private MenuItem modificarArticulo;

    @FXML
    private MenuItem crearCliente;

    @FXML
    private MenuItem borrarCliente;

    @FXML
    private Button home;

    @FXML
    private MenuItem verArticulos;

    @FXML
    private MenuItem modificarVehiculo;

    @FXML
    private MenuItem crearVehiculo;

    @FXML
    private MenuItem modificarFactura;

    @FXML
    private MenuItem borrarVehiculos;

	@Override
		
	public void start(Stage stage) throws Exception {

		crearCliente.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("adsadsaadsaada");
				AnchorPane root;
				try {
					root = FXMLLoader.load(getClass().getResource("/Vistes/Alta_Cliente.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	@FXML AnchorPane secPane;

	public void loadFxml (ActionEvent event)  {
		AnchorPane newLoadedPane;
		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("/Vistes/Alta_Cliente.fxml"));
			secPane.getChildren().add(newLoadedPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
