package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
import motauto.Comprovaciones;
import motauto.Database;
import motauto.FacturaFiles;
import motauto.FacturaHeader;

public class Borrar_Factura implements Initializable {

    @FXML
    private TableColumn<FacturaHeader, Integer> estado;

    @FXML
    private Button btnBorrar;

    @FXML
    private TableColumn<FacturaHeader, Float> descuento;

    @FXML
    private TableColumn<FacturaHeader, Float> totaliva;

    @FXML
    private TableColumn<FacturaHeader, Integer> numFactura;

    @FXML
    private TableColumn<FacturaHeader, String> vehiculo;

    @FXML
    private TableColumn<FacturaHeader, String> formapago;

    @FXML
    private TableColumn<FacturaHeader, LocalDate> fecha;
    
    @FXML
    private TableColumn<FacturaHeader, LocalTime> hora;

    @FXML
    private TableColumn<FacturaHeader, String> cliente;

    @FXML
    private TableColumn<FacturaHeader, Float> total;

    @FXML
    private ComboBox<FacturaHeader> numfacturaCombo;

    @FXML
    private TableColumn<FacturaHeader, Float> totalFactura;

    @FXML
    private TableColumn<FacturaHeader, String> observaciones;

    @FXML
    private Label info;
    
    @FXML
    private TableView<FacturaHeader> tabla;
    
    @FXML
    private ObservableList <FacturaHeader>headers;
    
    @FXML
	private FilteredList<FacturaHeader> llistaFiltrada;

    
	public void initialize(URL location, ResourceBundle resources) {

		//Connexio BBDD
		Database db=null;
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
    	Database bd = db;
    	

    	headers = FXCollections.observableArrayList();
    	llistaFiltrada = new FilteredList<>(headers, p -> true);
    	tabla.setItems(llistaFiltrada);

    	//TABLEVIEW    	
    	numFactura.setCellValueFactory(new PropertyValueFactory<FacturaHeader,Integer>("numPressupost"));
    	estado.setCellValueFactory(new PropertyValueFactory<FacturaHeader,Integer>("estado"));
    	fecha.setCellValueFactory(new PropertyValueFactory<FacturaHeader,LocalDate>("dataFactura"));
    	hora.setCellValueFactory(new PropertyValueFactory<FacturaHeader,LocalTime>("horaFactura"));
    	cliente.setCellValueFactory(new PropertyValueFactory<FacturaHeader,String>("cliente"));
    	vehiculo.setCellValueFactory(new PropertyValueFactory<FacturaHeader,String>("vehiculo"));
    	observaciones.setCellValueFactory(new PropertyValueFactory<FacturaHeader,String>("observaciones"));
    	formapago.setCellValueFactory(new PropertyValueFactory<FacturaHeader,String>("forma_pago"));
    	descuento.setCellValueFactory(new PropertyValueFactory<FacturaHeader,Float>("descuentoFactura"));
    	total.setCellValueFactory(new PropertyValueFactory<FacturaHeader,Float>("total"));
    	totaliva.setCellValueFactory(new PropertyValueFactory<FacturaHeader,Float>("totalIva"));
    	totalFactura.setCellValueFactory(new PropertyValueFactory<FacturaHeader,Float>("totalFactura"));


    	Comprovaciones.llenarInformacionHeader(bd, headers);
    	

    	numfacturaCombo.setItems(llistaFiltrada);
    	
    	btnBorrar.setOnAction(new EventHandler<ActionEvent>()
        {    	
            public void handle(ActionEvent e)
            {
        		FacturaHeader fh = Comprovaciones.consultaFacturaHeader(numfacturaCombo.getSelectionModel().getSelectedItem().getNumPressupost(), bd);
        		ArrayList<FacturaFiles> ff = Comprovaciones.consultaFacturaFiles(fh, numfacturaCombo.getSelectionModel().getSelectedItem().getNumPressupost(), bd);
            
    			for(FacturaFiles f : ff) 
    			{
    				boolean ffBorrat = f.borrarFila(bd);
    				
    				if(ffBorrat) 
    				{
    					System.out.println("La Fila Num: "+f.getNumFila()+" se ha borrado satisfactoriamente!");
    				}
    				else if(!ffBorrat) 
    				{
    					System.out.println("La Fila Num: "+f.getNumFila()+" NO SE ha borrado");
    				}
    			}		

    			
    			boolean fhBorrat = fh.borrarHeader(bd);
    			
    			if(fhBorrat) 
    			{
    				System.out.println("El Header de la factura: "+fh.getNumPressupost()+" se ha borrado satisfactoriamente!");
    			}
    			else if(!fhBorrat) 
    			{
    				System.out.println("La Fila Num: "+fh.getNumPressupost()+" NO SE ha borrado");
    			}		
    			
    			
    			info.setText("Se ha borrado satisfactoriamente");

    			headers.clear();
    			Comprovaciones.llenarInformacionHeader(bd, headers);

            }
        });
	}
}
