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
import motauto.Cliente;
import motauto.AlterarEstructuraBBDD;
import motauto.Articulos;
import motauto.Comprovaciones;
import motauto.Database;


public class Mostrar_Articulo implements Initializable {

    @FXML
    private TableColumn<Articulos, String> codigo;

    @FXML
    private TableColumn<Articulos, Float> precio;

    @FXML
    private TableColumn<Articulos, Float> iva;

    @FXML
    private TableView<Articulos> panel;

    @FXML
    private TableColumn<Articulos, String> nombre;
    
    @FXML
    private ObservableList <Articulos>headers;
	private FilteredList<Articulos> llistaFiltrada;
    private Database db=null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			// Conectaremos con la Base de datos
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) {
			System.out.print(e);
		}
		
		try {
			// Creamos una Array
			headers = FXCollections.observableArrayList();
			
	    	Comprovaciones.mostrarArticulos(db, headers);
			llistaFiltrada = new FilteredList<>(headers, p -> true);
			panel.setItems(llistaFiltrada);

			
			// Crear Table VIEW
			codigo.setCellValueFactory(new PropertyValueFactory<Articulos,String>("codigo"));
	    	nombre.setCellValueFactory(new PropertyValueFactory<Articulos,String>("nombre"));
	    	precio.setCellValueFactory(new PropertyValueFactory<Articulos,Float>("precio"));
	    	iva.setCellValueFactory(new PropertyValueFactory<Articulos,Float>("iva"));
		} 		
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
