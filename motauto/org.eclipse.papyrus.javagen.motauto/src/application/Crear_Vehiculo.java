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
import javafx.scene.control.TextField;
import motauto.AlterarEstructuraBBDD;
import motauto.Cliente;
import motauto.Comprovaciones;
import motauto.Database;
import motauto.Vehiculo;

public class Crear_Vehiculo implements Initializable {

	@FXML
	private TextField matricula;

	@FXML
	private TextField color;

	@FXML
	private TextField tipo;

	@FXML
	private ComboBox<Cliente> cliente;
	private ObservableList<Cliente> headers;
	private FilteredList<Cliente> llistaFiltrada;
	@FXML
	private Label info;

	@FXML
	private Button btnCrear;
	private Database db = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.conectToBDD();
		} catch (Exception e) {
			System.out.print(e);
		}
		headers = FXCollections.observableArrayList();
		Comprovaciones.mostrarClientes(db, headers);
		llistaFiltrada = new FilteredList<>(headers, p -> true);

		cliente.setItems(llistaFiltrada);
		// BOTON
		btnCrear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {

					Vehiculo vehiculo = new Vehiculo(matricula.getText(), color.getText(), tipo.getText(),Comprovaciones.consultaClient(cliente.getSelectionModel().getSelectedItem().getDni(), db));
					if (Comprovaciones.consultaVehiculo(matricula.getText(), db) == null) {
						vehiculo.insertVehiculo(db);
						matricula.clear();
						color.clear();
						tipo.clear();
						info.setText("Alta Vehiculo correcta");
					} else {
						info.setText("El Vehiculo ya existe en la BDD");
					}
				} catch (NumberFormatException e) {
					info.setText(e.toString());
				}
			}
		});
	}

}
