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
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import motauto.AlterarEstructuraBBDD;
import motauto.Cliente;
import motauto.Comprovaciones;
import motauto.Database;

public class Header implements Initializable {

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
    

    @FXML
    private Button boton;


    public void initialize(URL arg0, ResourceBundle arg1) {
    	Stage stage = null;
    	
    	crearArticulo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Alta_Articulo.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    
    	crearCliente.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Alta_Cliente.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	crearFactura.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Alta_Factura.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	crearVehiculo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Alta_Vehiculo.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	borrarArticulo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Borrar_Articulo.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	borrarCliente.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Borrar_Cliente.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	borrarFactura.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Borrar_Factura.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	borrarVehiculos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Borrar_Vehiculo.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	modificarArticulo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Modificar_Articulo.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	modificarCliente.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Modificar_Cliente.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	modificarFactura.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Modificar_Factura.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	modificarVehiculo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Modificar_Vehiculo.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	verArticulos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Mostrar_Articulos.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	
    	verClientes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Mostrar_Clientes.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	verVehiculos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Mostrar_Vehiculos.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Menu.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	
    	

    
    
    }		
}
