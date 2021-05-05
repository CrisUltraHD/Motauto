package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import motauto.AlterarEstructuraBBDD;
import motauto.Cliente;
import motauto.Comprovaciones;
import motauto.Database;

public class Mostrar_Cliente implements Initializable{

    @FXML
    private TableView<Cliente> table;

    @FXML
    private TableColumn<Cliente, String> dni;

    @FXML
    private TableColumn<Cliente, String> nombre;

    @FXML
    private TableColumn<Cliente, String> apellido;

    @FXML
    private TableColumn<Cliente, String> correo;

    @FXML
    private TableColumn<Cliente, Integer> telefono;

    @FXML
    private TableColumn<Cliente, String> direccion;

    private Database db=null;
    private ObservableList <Cliente>headers;
    
    @FXML
	private FilteredList<Cliente> llistaFiltrada;

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				db = AlterarEstructuraBBDD.establecerPrimeraConexion();
				db.conectToBDD();
			}
			catch(Exception e) {
				System.out.print(e);
			}
			
			try {
				headers = FXCollections.observableArrayList();
				llistaFiltrada = new FilteredList<>(headers, p -> true);
				table.setItems(llistaFiltrada);
				
				// Crear Table VIEW
				dni.setCellValueFactory(new PropertyValueFactory<Cliente,String>("dni"));
		    	nombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
		    	apellido.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellidos"));
		    	correo.setCellValueFactory(new PropertyValueFactory<Cliente,String>("correo"));
		    	telefono.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("telefono"));
		    	direccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
		    	
		    	Comprovaciones.mostrarClientes(db, headers);
			} 		
			catch (Exception e) {
				
			}
		}

	
}
