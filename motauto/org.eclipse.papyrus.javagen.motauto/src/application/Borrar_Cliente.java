package application;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
