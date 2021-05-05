package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import motauto.AlterarEstructuraBBDD;
import motauto.Cliente;
import motauto.Comprovaciones;
import motauto.Database;

public class Crear_Cliente implements Initializable {

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
	private Button btnCrear;
	
	private Database db=null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.conectToBDD();
		}
		catch(Exception e) {
			System.out.print(e);
		}
		
		//BOTON
		btnCrear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					Cliente cliente = new Cliente(dni.getText(), nombre.getText(), apellidos.getText(), correo.getText(), Integer.parseInt(telefono.getText()), direccion.getText());
					if (!Comprovaciones.comprovarDni(dni.getText(), db)) {
						cliente.insertCliente(db);
						dni.clear();
						nombre.clear();
						apellidos.clear();
						correo.clear();
						telefono.clear();
						direccion.clear();
						info.setText("Alta Cliente correcta");
					} else {
						info.setText("El Cliente ya existe en la BDD");
					}
				} 		
				catch (NumberFormatException e) {
					info.setText(e.toString());
				}
			}
		});
	}
}
