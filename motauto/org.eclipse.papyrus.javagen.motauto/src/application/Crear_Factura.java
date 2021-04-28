package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import motauto.AlterarEstructuraBBDD;
import motauto.Articulos;
import motauto.Cliente;
import motauto.Database;
import motauto.FacturaFiles;

public class Crear_Factura implements Initializable {


    @FXML
    private TableColumn<FacturaFiles, String> colCodArticulo;

    @FXML
    private Label hora;

    @FXML
    private Button btncrear;

    @FXML
    private TableColumn<FacturaFiles, Float> colIva;

    @FXML
    private Button addfila;

    @FXML
    private Label dir;

    @FXML
    private Label nombre;

    @FXML
    private ComboBox<Articulos> codart;

    @FXML
    private TextField precio;

    @FXML
    private TextField total;

    @FXML
    private TableColumn<FacturaFiles, String> colDescripcion;

    @FXML
    private TextField iva;

    @FXML
    private TableColumn<FacturaFiles, Float> colPrecio;

    @FXML
    private Label apellido;

    @FXML
    private Label correo;

    @FXML
    private TableView<FacturaFiles> tabla;

    @FXML
    private TableColumn<FacturaFiles, Integer> id;

    @FXML
    private ComboBox<Cliente> dni;

    @FXML
    private TableColumn<FacturaFiles, Integer> colCantidad;

    @FXML
    private TableColumn<FacturaFiles, Float> colTotal;

    @FXML
    private Label nfactura;

    @FXML
    private Label tlf;

    @FXML
    private DatePicker fechaFactura;
    
    @FXML
    private TextField cantidad;

    @FXML
    private TextField desc;
    
    //CLIENTES
    private ObservableList <Cliente> clientes;

    //ARTICULOS
    private ObservableList <Articulos> articulos;


    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	
		//Connexio BBDD
		Database db=null;
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		//CLIENTES
    	clientes = FXCollections.observableArrayList();
    	Cliente.llenarInformacionCliente(db, clientes);
    	
		FilteredList<Cliente> clientsFiltrats;
		clientsFiltrats = new FilteredList<>(clientes, p -> true);
		dni.setItems(clientsFiltrats);

		dni.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cliente>() {
			@Override
			public void changed(ObservableValue<? extends Cliente> seleccionat, Cliente anterior, Cliente nou) {
	    		nombre.setText(nou.getNombre());
	    		apellido.setText(nou.getApellidos());
	    		correo.setText(nou.getCorreo());
	    		tlf.setText(nou.getTelefono()+"");
	    		dir.setText(nou.getDireccion());
			}
		});
		
		
    	
    	//ARTICULOS
    	articulos = FXCollections.observableArrayList();
    	Articulos.llenarInformacionArticulos(db, articulos);
    	
		FilteredList<Articulos> articlesFiltrats;
		articlesFiltrats = new FilteredList<>(articulos, p -> true);
    	codart.setItems(articlesFiltrats);
    	
    	codart.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Articulos>() {
			@Override
			public void changed(ObservableValue<? extends Articulos> seleccionat, Articulos anterior, Articulos nou) {
				System.out.println("HAS CANVIAT EL COMBO");
				//PREUS DE LA FILA
				float ivat;
				float preciot;
				int cantidadt = 1;
				ivat = codart.getSelectionModel().getSelectedItem().getIva();
				preciot = codart.getSelectionModel().getSelectedItem().getPrecio();
													
				cantidad.setText("1");
				iva.setText(""+ivat);
				precio.setText("" +preciot);
				total.setText(""+((preciot * ivat)+preciot * cantidadt));
			}});

    	
    	
    	//CANTIDAD
    	cantidad.textProperty().addListener(new ChangeListener<String>() {
    		@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try 
    			{
    				float ivat = Float.parseFloat(iva.getText());
    				float preciot = Float.parseFloat(precio.getText());
    				int cantidadt = Integer.parseInt(newValue);
    				
    				total.setText(""+((preciot * ivat)+preciot * cantidadt));
    			}
    			catch(Exception e) {e.printStackTrace();}    			
    		}
    	});
    	
    	
    	
    	//IVA
    	iva.textProperty().addListener(new ChangeListener<String>() {
    		@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try 
    			{
    				float ivat = Float.parseFloat(newValue);
    				float preciot = Float.parseFloat(precio.getText());
    				int cantidadt = Integer.parseInt(cantidad.getText());
    				
    				total.setText(""+((preciot * ivat)+preciot * cantidadt));
    			}
    			catch(Exception e) {e.printStackTrace();}    			
    		}
    	});
    	
    	
    	
    	//PRECIO ARTICULO
    	precio.textProperty().addListener(new ChangeListener<String>() {
    		@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try 
    			{
    				float ivat = Float.parseFloat(iva.getText());
    				float preciot = Float.parseFloat(newValue);
    				int cantidadt = Integer.parseInt(cantidad.getText());
    				
    				total.setText(""+((preciot * ivat)+preciot * cantidadt));
    			}
    			catch(Exception e) {e.printStackTrace();}    			
    		}
    	});   	
	}
}
