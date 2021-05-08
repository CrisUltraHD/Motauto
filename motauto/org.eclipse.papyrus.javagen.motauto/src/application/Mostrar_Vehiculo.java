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
import motauto.Vehiculo;

public class Mostrar_Vehiculo implements Initializable {

    @FXML
    private TableView<Vehiculo> table;

    @FXML
    private TableColumn<Vehiculo, String> matricula;

    @FXML
    private TableColumn<Vehiculo, String> color;

    @FXML
    private TableColumn<Vehiculo, String> tipo;

    @FXML
    private TableColumn<Vehiculo, String> cliente;
    
    @FXML
    private Database db=null;

    @FXML
    private ObservableList <Vehiculo> headers;
    
    @FXML
	private FilteredList<Vehiculo> llistaFiltrada;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) {
			System.out.print(e);
		}
		
		
		try 
		{
			headers = FXCollections.observableArrayList();
	    	Comprovaciones.mostrarVehiculos(db, headers);
			llistaFiltrada = new FilteredList<>(headers, p -> true);
			table.setItems(llistaFiltrada);
			
			matricula.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("matricula"));
			color.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("color"));
			tipo.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("tipo_vehiculo"));
			cliente.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("cliente"));
	    	
		} 		
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
}