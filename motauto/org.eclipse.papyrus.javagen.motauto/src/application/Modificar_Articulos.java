package application;

import java.awt.Label;
import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import motauto.AlterarEstructuraBBDD;
import motauto.Articulos;
import motauto.Comprovaciones;
import motauto.Database;

public class Modificar_Articulos {

	

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
    private ComboBox<Articulos> codigoArticulo;

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
    
    @FXML
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
		llistaFiltrada = new FilteredList<>(headers, p -> true);
		tabla.setItems(llistaFiltrada);
		
		// Crear Table VIEW
		codigo.setCellValueFactory(new PropertyValueFactory<Articulos,String>("codigo"));
		precio.setCellValueFactory(new PropertyValueFactory<Articulos,Float>("precio"));
    	iva.setCellValueFactory(new PropertyValueFactory<Articulos,Float>("iva"));
    	nombre.setCellValueFactory(new PropertyValueFactory<Articulos,String>("nombre"));
    
    	Comprovaciones.mostrarArticulos(db);   	
    	
    	//BOTON AÑADIR INFORMACION
    	fill.setOnAction(new EventHandler<ActionEvent>()
        {    	
            public void handle(ActionEvent e)
            {
            	Articulos articulo = tabla.getSelectionModel().getSelectedItem();
            	codigo.setText(articulo.getCodigo());
				nombre.setText(articulo.getNombre());
				iva.setText(articulo.getIva()+"");
				precio.setText(articulo.getPrecio()+"");
				

            }
        });
    	
    	
    	btnModificar.setOnAction(new EventHandler<ActionEvent>()
        {    	
            public void handle(ActionEvent e)
            {
            	try {
            		Articulos articulo = new Articulos(precioArticulo.getText(), nombreArticulo.getText(),Integer.parseInt(precioArticulo.getText()), Integer.parseInt(ivaArticulo.getText()));
					if (Comprovaciones.consultaArticulo(articulo.getCodigo(), db)!=null) {
						articulo.modificarArticulo(db);
						
						
						
						info.setText("Cliente modificado");
						headers.clear();
				    	Comprovaciones.mostrarArticulos(db);;

					}
					else {
						info.setText("El Article no existe en al BDD");
					}
            	}
            	
            	catch(Exception ex) {
            		info.setText(ex.getLocalizedMessage());
            	}
            	
            }
        });
		
		
	}
}
