package application;

import java.net.URL;
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
import javafx.scene.control.cell.PropertyValueFactory;
import motauto.AlterarEstructuraBBDD;
import motauto.Comprovaciones;
import motauto.Database;
import motauto.Vehiculo;

public class Borrar_Vehiculo implements Initializable{

    @FXML
    private TableView<Vehiculo> tabla;

    @FXML
    private TableColumn<Vehiculo, String> matriculaCol;

    @FXML
    private TableColumn<Vehiculo, String> colorCol;

    @FXML
    private TableColumn<Vehiculo, String> tipoCol;

    @FXML
    private ComboBox<Vehiculo> matricula;

    @FXML
    private Label info;

    @FXML
    private Button btnBorrarVehiculo;
  
    private ObservableList <Vehiculo> headers;
    
    @FXML
	private FilteredList<Vehiculo> llistaFiltrada;
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
    	
		llistaFiltrada = new FilteredList<>(headers, p -> true);
		tabla.setItems(llistaFiltrada);
	
		matriculaCol.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("matricula"));
		colorCol.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("color"));
		tipoCol.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("tipo_vehiculo"));
		Comprovaciones.mostrarVehiculos(db, headers);
		matricula.setItems(llistaFiltrada);

		
		btnBorrarVehiculo.setOnAction(new EventHandler<ActionEvent>()
        {    	
            public void handle(ActionEvent e)
            {
            	try 
				{
				Vehiculo vehiculo = Comprovaciones.consultaVehiculo(matricula.getSelectionModel().getSelectedItem().getMatricula(),db);
				vehiculo.borrarVehiculo(db);
				info.setText("Se ha borrado satisfactoriamente");
				}
				catch(Exception ex) {info.setText(ex.getMessage());}
				headers.clear();
				Comprovaciones.mostrarVehiculos(db, headers);
			
            }
        });	
	}
		
	
    

}
