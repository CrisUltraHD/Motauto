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
		// ArrayList donde guardaremos toda la inforamcion de los articulos para poderla imprimir en la tabla
		headers = FXCollections.observableArrayList();
		llistaFiltrada = new FilteredList<>(headers, p -> true);
		tabla.setItems(llistaFiltrada);

		// Inicializamos las variables que controlan el contenido de las columnas de la tabla 
		codigo.setCellValueFactory(new PropertyValueFactory<Articulos, String>("codigo"));
		precio.setCellValueFactory(new PropertyValueFactory<Articulos, Float>("precio"));
		iva.setCellValueFactory(new PropertyValueFactory<Articulos, Float>("iva"));
		nombre.setCellValueFactory(new PropertyValueFactory<Articulos, String>("nombre"));
		// Llamamos la funcion llenarInformaticon, esta ara un select a la base de datos i nos recojera toda la informacion de los articuos
		// Le pasamos como parametro la conexion a la base de datos, i la arraylist donde guardaremos dicha informacion 
		Comprovaciones.llenarInformacionArticulos(bd, headers);
		// Rellenamos el comboBox con la lista de codigos i nombre de los articulos para poder escojerlos
		codigoArticulo.setItems(llistaFiltrada);
		// Cuando clicamos sobre el boton de borrar articulos, recojeremos toda la inforamcion te los textViews,
		// Con ella creamos un objeto Articulo que sera el encargado de llamar su funcion interna para borrarse de la base de datos
		btnBorrar.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try 
				{
					//Llamamos la funcion consultaArticulo asi nos cercioramos de que el articulo existe en la base de datos
				Articulos articulo = Comprovaciones.consultaArticulo(codigoArticulo.getSelectionModel().getSelectedItem().getCodigo(), bd);
				articulo.borrarArticulo(bd); // Una vez recojido lo borramos
				info.setText("Se ha borrado satisfactoriamente");
				}
				catch(Exception ex) {info.setText(ex.getMessage());}
				// Limpiamos el arraylist 
				headers.clear();
				// Lo volvemos a llenar con la inforamcion nueva de la base de datos
				Comprovaciones.llenarInformacionArticulos(bd, headers);
			}
		});
	}
}
