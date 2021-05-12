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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import motauto.AlterarEstructuraBBDD;
import motauto.Articulos;
import motauto.Comprovaciones;
import motauto.Database;

public class Modificar_Articulo implements Initializable {	

    @FXML
    private TableColumn<Articulos, String> codigo;

    @FXML
    private TableColumn<Articulos, Float> precio;

    @FXML
    private TableColumn<Articulos, Float> iva;

    @FXML
    private TableView<Articulos> tabla;

    @FXML
    private TableColumn<Articulos, String> nombre;

    @FXML
    private Button btnModificar;

    @FXML
    private TextField codigoArticulo;

    @FXML
    private TextField precioArticulo;

    @FXML
    private TextField nombreArticulo;

    @FXML
    private TextField ivaArticulo;

    @FXML
    private Label info;
    
    @FXML
    private Button fill;

    @FXML
    private ObservableList <Articulos> headers;
    
	private FilteredList<Articulos> llistaFiltrada;
    
	static Database db=null;
	
	
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
    	Comprovaciones.mostrarArticulos(db, headers);
		llistaFiltrada = new FilteredList<>(headers, p -> true);
		tabla.setItems(llistaFiltrada);
		
		// Crear Table VIEW
		codigo.setCellValueFactory(new PropertyValueFactory<Articulos,String>("codigo"));
		precio.setCellValueFactory(new PropertyValueFactory<Articulos,Float>("precio"));
    	iva.setCellValueFactory(new PropertyValueFactory<Articulos,Float>("iva"));
    	nombre.setCellValueFactory(new PropertyValueFactory<Articulos,String>("nombre"));
    	
    	
    	//BOTON AÑADIR INFORMACION
    	fill.setOnAction(new EventHandler<ActionEvent>()
        {    	
            public void handle(ActionEvent e)
            {
            	Articulos articulo = new Articulos(tabla.getSelectionModel().getSelectedItem().getCodigo(),tabla.getSelectionModel().getSelectedItem().getNombre(),tabla.getSelectionModel().getSelectedItem().getPrecio(),tabla.getSelectionModel().getSelectedItem().getIva());
            	codigoArticulo.setText(articulo.getCodigo());
				nombreArticulo.setText(articulo.getNombre());
				ivaArticulo.setText(articulo.getIva()+"");
				precioArticulo.setText(articulo.getPrecio()+"");
            }
        });
    	
    	
    	btnModificar.setOnAction(new EventHandler<ActionEvent>()
        {    	
            public void handle(ActionEvent e)
            {
            	try {
	            	Articulos articulo = new Articulos(codigoArticulo.getText(),nombreArticulo.getText(),Float.parseFloat(precioArticulo.getText()),Float.parseFloat(ivaArticulo.getText()));
            		articulo.modificarArticulo(db);
					codigoArticulo.clear();
					nombreArticulo.clear();
					precioArticulo.clear();
					ivaArticulo.clear();

            		info.setText("Articulo modificado");
					headers.clear();
			    	Comprovaciones.mostrarArticulos(db, headers);
            	}
            	
            	catch(Exception ex) {
            		info.setText(ex.getLocalizedMessage());
            	}
            }
        });
	}
}