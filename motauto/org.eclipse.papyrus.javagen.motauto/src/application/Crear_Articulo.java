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
import motauto.Articulos;
import motauto.Comprovaciones;
import motauto.Database;

public class Crear_Articulo implements Initializable {

	@FXML
	private Button btnCrear;

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
	
		// Cuando clicamos el boton crar, coge los valores que hay puestos en los textfield y los utiliza para hacer el insert en db
		// tambien comprueva si existe en la db ya el articulo o si se ha creado correctmente
		btnCrear.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				try {
					String codigoA = (codigoArticulo.getText());
					String nombreA = (nombreArticulo.getText());
					float ivaA = Float.parseFloat(ivaArticulo.getText());
					float precioA = Float.parseFloat(precioArticulo.getText());

					Articulos articulo = new Articulos(codigoA, nombreA, precioA, ivaA);
					
					if(!Comprovaciones.comprovarCodigoArticulo(codigoArticulo.getText(), bd)) 
					{
						articulo.insertArticulo(bd);
						info.setText("Articulo Creado Correctamente");
						codigoArticulo.clear();
						precioArticulo.clear();
						nombreArticulo.clear();
						ivaArticulo.clear();
					}
					else {
						info.setText("EL ARTICULO YA EXISTE");
					}
				} catch (Exception e1) {
					info.setText("Error: "+e1.getLocalizedMessage());
				}

			}
		});

	}

}