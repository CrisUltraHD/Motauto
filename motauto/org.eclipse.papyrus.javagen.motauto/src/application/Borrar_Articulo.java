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
import motauto.Articulos;
import motauto.Comprovaciones;
import motauto.Database;

public class Borrar_Articulo implements Initializable {

	@FXML
	private TableColumn<Articulos, String> codigo;

	@FXML
	private TableColumn<Articulos, Float> precio;

	@FXML
	private Button btnBorrar;

	@FXML
	private TableColumn<Articulos, Float> iva;

	@FXML
	private TableColumn<Articulos, String> nombre;

	@FXML
	private ComboBox<Articulos> codigoArticulo;

	@FXML
	private Label info;

	@FXML
	private ObservableList<Articulos> headers;

	@FXML
	private TableView<Articulos> tabla;

	@FXML
	private FilteredList<Articulos> llistaFiltrada;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub

		// Connexio BBDD
		Database db = null;
		try {
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Database bd = db;

		headers = FXCollections.observableArrayList();
		llistaFiltrada = new FilteredList<>(headers, p -> true);
		tabla.setItems(llistaFiltrada);

		// TABLEVIEW
		codigo.setCellValueFactory(new PropertyValueFactory<Articulos, String>("codigo"));
		precio.setCellValueFactory(new PropertyValueFactory<Articulos, Float>("precio"));
		iva.setCellValueFactory(new PropertyValueFactory<Articulos, Float>("iva"));
		nombre.setCellValueFactory(new PropertyValueFactory<Articulos, String>("nombre"));

		Comprovaciones.llenarInformacionArticulos(bd, headers);

		codigoArticulo.setItems(llistaFiltrada);

		btnBorrar.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try 
				{
				Articulos articulo = Comprovaciones.consultaArticulo(codigoArticulo.getSelectionModel().getSelectedItem().getCodigo(), bd);
				articulo.borrarArticulo(bd);
				info.setText("Se ha borrado satisfactoriamente");
				}
				catch(Exception ex) {info.setText(ex.getMessage());}

				headers.clear();
				Comprovaciones.llenarInformacionArticulos(bd, headers);
			}
		});
	}
}
