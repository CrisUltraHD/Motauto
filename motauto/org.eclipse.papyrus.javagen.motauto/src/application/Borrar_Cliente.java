package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import motauto.AlterarEstructuraBBDD;
import motauto.Cliente;
import motauto.Comprovaciones;
import motauto.Database;
import motauto.FacturaFiles;
import motauto.FacturaHeader;

public class Borrar_Cliente implements Initializable {

	@FXML
    private TableColumn<Cliente, String> dni;

    @FXML
    private TableColumn<Cliente, String> nombre;

    @FXML
    private TableColumn<Cliente, String> apellidos;

    @FXML
    private TableColumn<Cliente, String> correo;

    @FXML
    private TableColumn<Cliente, Integer> telefono;

    @FXML
    private TableColumn<Cliente, String> direccion;

    @FXML
    private ComboBox<Cliente> dniCombo;	

    @FXML
    private Label info;

    @FXML
    private Button btnBorrar;
    
    @FXML
    private TableView<Cliente> tabla;

    @FXML
    private ObservableList <Cliente> headers;
    
    @FXML
	private FilteredList<Cliente> llistaFiltrada;
	static Database db=null;

	 
	public void start(Stage primaryStage) throws IOException {
         primaryStage.setTitle("Title");
         primaryStage.setScene(createScene(loadMainPane("path_of_your_fxml")));
         primaryStage.show();

	}

	 private AnchorPane loadMainPane(String path) throws IOException {
	     FXMLLoader loader = new FXMLLoader();
	
	     AnchorPane mainPane = (AnchorPane) loader.load(getClass().getResourceAsStream(path));
	
	     return mainPane;
	 }

	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		headers = FXCollections.observableArrayList();
    	Comprovaciones.mostrarClientes(db, headers);
		llistaFiltrada = new FilteredList<>(headers, p -> true);
		tabla.setItems(llistaFiltrada);
		
		// Crear Table VIEW
		dni.setCellValueFactory(new PropertyValueFactory<Cliente,String>("dni"));
    	nombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
    	apellidos.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellidos"));
    	correo.setCellValueFactory(new PropertyValueFactory<Cliente,String>("correo"));
    	telefono.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("telefono"));
    	direccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
    	
    	dniCombo.setItems(llistaFiltrada);
    	
    	btnBorrar.setOnAction(new EventHandler<ActionEvent>()
        {    	
            public void handle(ActionEvent e)
            {
            	try {
        		Cliente cliente = Comprovaciones.consultaClient(dniCombo.getSelectionModel().getSelectedItem().getDni(), db);
				if (Comprovaciones.comprovarDni(cliente.getDni(), db)) {
					
					if(Comprovaciones.consultaVehiculoPorDni(cliente.getDni(), db) != null) 
					{
						//Mostrem un missatge de que la inserció s'ha fet correctament
						Alert missatge=new Alert(AlertType.CONFIRMATION);
						missatge.setTitle("ATENCION!");
						missatge.setContentText("Existe un vehiculo con este DNI, quieres borrarlo?");
						missatge.setHeaderText("Resultat:");
						missatge.show();


						ButtonType si = new ButtonType("Si",ButtonBar.ButtonData.YES);
						ButtonType no = new ButtonType("No",ButtonBar.ButtonData.NO);
						missatge.getButtonTypes().setAll(si,no);
						
						
						missatge.showAndWait().ifPresent(type -> {
							if(type == ButtonType.OK) {
								Comprovaciones.consultaVehiculoPorDni(cliente.getDni(), db).borrarVehiculo(db);
								cliente.borrarCliente(db);
								info.setText("Cliente Borrado");
								headers.clear();
						    	Comprovaciones.mostrarClientes(db, headers);
							}
							else if(type == ButtonType.NO) {
								info.setText("Operacion Cancelada");
							}
							else {
								info.setText("Operacion Cancelada");
							}
						});
					}
				}
				else {
					info.setText("El Cliente no existe en al BDD");
				}

        		info.setText(cliente.borrarCliente(db) ? "Se borro el cliente "+cliente.getNombre() : "No se borro el cliente " + cliente.getNombre());
            	}
            	catch(Exception ex) 
            	{
            		ex.printStackTrace();
            	}
    			headers.clear();
    			Comprovaciones.mostrarClientes(db, headers);
            }
        });	
	}
}
