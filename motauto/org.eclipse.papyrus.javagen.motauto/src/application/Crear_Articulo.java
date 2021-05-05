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

    	//ARTICULOS
		ArrayList<Articulos> articulos;
		articulos = Articulos.getArticulos(bd);
		
		codigoArticulo.textProperty().addListener(new ChangeListener<String>() {
    		@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try 
    			{  	
    				for(Articulos a : articulos) 
    				{
        				if(codigoArticulo.getText().equalsIgnoreCase(a.getCodigo())) 
        				{
        					btnCrear.setDisable(true);
        				}
        				else 
        				{
        					btnCrear.setDisable(false);
        				}
    				}

    				
    			}
    			catch(Exception e) {}    			
    		}
    	}); 
		

		btnCrear.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				try {
					String codigoA = (codigoArticulo.getText());
					String nombreA = (nombreArticulo.getText());
					float ivaA = Float.parseFloat(ivaArticulo.getText());
					float precioA = Float.parseFloat(precioArticulo.getText());

					Articulos articulo = new Articulos(codigoA, nombreA, precioA, ivaA);
					articulo.insertArticulo(bd);
					info.setText("Articulo Creado Correctamente");
				} catch (Exception e1) {
					info.setText("Error: "+e1.getLocalizedMessage());
				}

			}
		});

	}

}