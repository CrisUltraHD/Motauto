package application;

import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import motauto.AlterarEstructuraBBDD;
import motauto.Articulos;
import motauto.Cliente;
import motauto.Comprovaciones;
import motauto.Database;
import motauto.FacturaFiles;

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

		Database db = null;
		try {
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Database bd = db;
	

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