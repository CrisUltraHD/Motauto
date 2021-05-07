package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import motauto.AlterarEstructuraBBDD;
import motauto.Articulos;
import motauto.Cliente;
import motauto.Comprovaciones;
import motauto.Database;

public class Modificar_Cliente implements Initializable {

    @FXML
    private TableView<Cliente> tabla;

    @FXML
    private TableColumn<Cliente, String> dniCol;

    @FXML
    private TableColumn<Cliente, String> nombreCol;

    @FXML
    private TableColumn<Cliente, String> apellidosCol;

    @FXML
    private TableColumn<Cliente, String> correoCol;

    @FXML
    private TableColumn<Cliente, Integer> telefonoCol;
    
    @FXML
    private TableColumn<Cliente, String> direccionCol;
    @FXML
    private TextField dni;

    @FXML
    private TextField nombre;

    @FXML
    private TextField apellidos;

    @FXML
    private TextField correo;

    @FXML
    private TextField telefono;

    @FXML
    private TextField direccion;

    @FXML
    private Label info;
    
    @FXML
    private Button fill;

    @FXML
    private Button btnModificar;
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
		llistaFiltrada = new FilteredList<>(headers, p -> true);
		tabla.setItems(llistaFiltrada);
		
		// Crear Table VIEW
		dniCol.setCellValueFactory(new PropertyValueFactory<Cliente,String>("dni"));
    	nombreCol.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
    	apellidosCol.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellidos"));
    	correoCol.setCellValueFactory(new PropertyValueFactory<Cliente,String>("correo"));
    	telefonoCol.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("telefono"));
    	direccionCol.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
    	
    	Comprovaciones.mostrarClientes(db, headers);    	
    	
    	//BOTON AÑADIR INFORMACION
    	fill.setOnAction(new EventHandler<ActionEvent>()
        {    	
            public void handle(ActionEvent e)
            {
            	Cliente cliente = tabla.getSelectionModel().getSelectedItem();
				dni.setText(cliente.getDni());
				nombre.setText(cliente.getNombre());
				apellidos.setText(cliente.getApellidos());
				correo.setText(cliente.getCorreo());
				telefono.setText(cliente.getTelefono()+"");
				direccion.setText(cliente.getDireccion());

            }
        });
    	
    	
    	btnModificar.setOnAction(new EventHandler<ActionEvent>()
        {    	
            public void handle(ActionEvent e)
            {
            	try {
	            	Cliente cliente = new Cliente(dni.getText(), nombre.getText(), apellidos.getText(), correo.getText(), Integer.parseInt(telefono.getText()), direccion.getText());
					if (Comprovaciones.comprovarDni(cliente.getDni(), db)) {
						cliente.modificarCliente(db);
						dni.clear();
						nombre.clear();
						apellidos.clear();
						correo.clear();
						telefono.clear();
						direccion.clear();
						info.setText("Cliente modificado");
						headers.clear();
				    	Comprovaciones.mostrarClientes(db, headers);

					}
					else {
						info.setText("El Cliente no existe en al BDD");
					}
            	}
            	
            	catch(Exception ex) {
            		info.setText(ex.getLocalizedMessage());
            	}
            	
            }
        });
		
		
	}

}
