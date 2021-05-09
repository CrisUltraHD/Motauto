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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import motauto.AlterarEstructuraBBDD;
import motauto.Cliente;
import motauto.Comprovaciones;
import motauto.Database;
import motauto.Vehiculo;

public class Modificar_Vehiculo implements Initializable {

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
    private TextField colorVehiculo;
    @FXML
    private TextField matriculaVehiculo;

    @FXML
    private TextField tipoVehiculo;

    @FXML
    private ComboBox<Cliente> clienteCombo;

    @FXML
    private Label info;

    @FXML
    private Button btnModificar;
    @FXML
    private Button fill;

    private ObservableList <Vehiculo> headers;
    
    private ObservableList <Cliente> clientes;

    
    @FXML
	private FilteredList<Vehiculo> llistaFiltrada;
	private FilteredList<Cliente> llistaFiltradaCliente;

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
		
		clientes = FXCollections.observableArrayList();
    	Comprovaciones.llenarInformacionCliente(db, clientes); 	
		llistaFiltradaCliente = new FilteredList<>(clientes, p -> true);
		clienteCombo.setItems(llistaFiltradaCliente);

		
		// Crear Table VIEW
		matriculaCol.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("matricula"));
		colorCol.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("color"));
		tipoCol.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("tipo_vehiculo"));
    	
    	Comprovaciones.mostrarVehiculos(db, headers); 	
    	fill.setOnAction(new EventHandler<ActionEvent>()
        {    	
            public void handle(ActionEvent e)
            {
            	Vehiculo vehiculo = tabla.getSelectionModel().getSelectedItem();
            	matriculaVehiculo.setText(vehiculo.getMatricula());
            	colorVehiculo.setText(vehiculo.getColor());
            	tipoVehiculo.setText(vehiculo.getTipo_vehiculo());
            	clienteCombo.setPromptText(vehiculo.getCliente().getNombre());

            }
        });
    	btnModificar.setOnAction(new EventHandler<ActionEvent>()
        {    	
            public void handle(ActionEvent e)
            {
            	try {
	            	Vehiculo vehiculo = new Vehiculo(matriculaVehiculo.getText(), colorVehiculo.getText(), tipoVehiculo.getText(), Comprovaciones.consultaClient(clienteCombo.getSelectionModel().getSelectedItem().getDni(), db));
					if (Comprovaciones.comprovarMatricula(vehiculo.getMatricula(), db)) {
						vehiculo.modificarVehiculo(db);
						matriculaVehiculo.clear();
						colorVehiculo.clear();
						tipoVehiculo.clear();
						info.setText("Vehiculo modificado");
						headers.clear();

						clienteCombo.getSelectionModel().clearSelection();
						Comprovaciones.mostrarVehiculos(db, headers);

					}
					else {
						info.setText("El vehiculo no existe en la base de datos");
					}
            	}
            	
            	catch(Exception ex) {
            		info.setText(ex.getLocalizedMessage());
            	}
            	
            }
        });
	}

}
