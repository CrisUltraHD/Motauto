package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import motauto.AlterarEstructuraBBDD;
import motauto.Articulos;
import motauto.Cliente;
import motauto.Comprovaciones;
import motauto.Database;
import motauto.FacturaFiles;
import motauto.FacturaHeader;
import motauto.Vehiculo;

public class Crear_Factura implements Initializable {
    
    @FXML
    private TableColumn<FacturaFiles, String> colCodArticulo;

    @FXML
    private Label hora;

    @FXML
    private Button btncrear;

    @FXML
    private TableColumn<FacturaFiles, Float> colIva;

    @FXML
    private Button addfila;

    @FXML
    private Label dir;

    @FXML
    private Label nombre;

    @FXML
    private ComboBox<Articulos> codart;

    @FXML
    private TextField precio;

    @FXML
    private TextField total;

    @FXML
    private TextField iva;

    @FXML
    private TableColumn<FacturaFiles, Float> colPrecio;

    @FXML
    private Label apellido;

    @FXML
    private Label correo;

    @FXML
    private TableView<FacturaFiles> tabla;

    @FXML
    private TableColumn<FacturaFiles, Integer> id;

    @FXML
    private ComboBox<Cliente> dni;

    @FXML
    private TableColumn<FacturaFiles, Integer> colCantidad;

    @FXML
    private TableColumn<FacturaFiles, Float> colTotal;

    @FXML
    private Label nfactura;

    @FXML
    private Label tlf;

    @FXML
    private DatePicker fechaFactura;
    
    @FXML
    private TextField cantidad;

    @FXML
    private TableColumn<FacturaFiles, Float> colDescuento;  
    
    @FXML
    private TextField descuento;
    
    @FXML
    private ComboBox<Vehiculo> vehiculo;
    
    @FXML
    private TextField formapago;
    

    @FXML
    private ToggleGroup pago;
    @FXML
    private RadioButton pagado;
    @FXML
    private RadioButton porpagar;

    @FXML
    private TextArea observaciones;

    
    @FXML
    private TextField ivaFactura;
    @FXML
    private Label baseImponible;
    @FXML
    private TextField descuentoTotalFactura;
    @FXML
    private Label totalIvaFactura;
    @FXML
    private Label totalFactura;
    @FXML
    private Label info;

    

    //CREAMOS LOS OBSERVABLELIST
    //CLIENTES
    private ObservableList <Cliente> clientes;
    //VEHICULOS
    private ObservableList <Vehiculo> vehiculos;
    //ARTICULOS
    private ObservableList <Articulos> articulos;


    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
    		
		//DEFAULT VALUES
		porpagar.setSelected(true);
		fechaFactura.setValue(LocalDate.now());
		formapago.setText("N/A");
		

		
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


		//CLIENTES
    	clientes = FXCollections.observableArrayList();
    	Comprovaciones.llenarInformacionCliente(db, clientes);
    	//LISTA FILTRADA
		FilteredList<Cliente> clientsFiltrats;
		clientsFiltrats = new FilteredList<>(clientes, p -> true);
		dni.setItems(clientsFiltrats);

		//A�ADIMOS LISTENER AL DNI
		dni.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cliente>() {
			//AL SELECCIONAR EL CLIENTE DE EL COMBOBOX CANVIA LOS TEXTOS
			@Override
			public void changed(ObservableValue<? extends Cliente> seleccionat, Cliente anterior, Cliente nou) {
	    		nombre.setText(nou.getNombre());
	    		apellido.setText(nou.getApellidos());
	    		correo.setText(nou.getCorreo());
	    		tlf.setText(nou.getTelefono()+"");
	    		dir.setText(nou.getDireccion());
	    		
	    		//LLENAMOS EL COMBOBOX DE VEHICULOS QUE TIENE EL CLIENTE
	        	vehiculos = FXCollections.observableArrayList();
	        	Comprovaciones.llenarInformacionVehiculo(bd, vehiculos, nou.getDni());
	        	
	    		FilteredList<Vehiculo> vehiculoFiltrado;
	    		vehiculoFiltrado = new FilteredList<>(vehiculos, p -> true);
	    		vehiculo.setItems(vehiculoFiltrado);
			}
		});
		
		
    	
    	//ARTICULOS
    	articulos = FXCollections.observableArrayList();
    	Comprovaciones.llenarInformacionArticulos(db, articulos);
    	
		FilteredList<Articulos> articlesFiltrats;
		articlesFiltrats = new FilteredList<>(articulos, p -> true);
    	codart.setItems(articlesFiltrats);
    	
    	//A�ADIMOS UN LISTENER
    	codart.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Articulos>() {
			
    		//CALCULA LOS PRECIOS, IVA Y LLENA LOS CAMPOS PARA A�ADIR UN ARTICULO
    		@Override
			public void changed(ObservableValue<? extends Articulos> seleccionat, Articulos anterior, Articulos nou) {
				//PREUS DE LA FILA
				float ivat;
				float preciot;
				int cantidadt = 1;
				ivat = codart.getSelectionModel().getSelectedItem().getIva();
				preciot = codart.getSelectionModel().getSelectedItem().getPrecio();
													
				cantidad.setText("1");
				descuento.setText("0.0");
				iva.setText(""+ivat);
				precio.setText("" +preciot);
				total.setText(""+round1(((preciot * ivat)+preciot * cantidadt),2));
			}
		});

    	
    	
    	//CANTIDAD
    	cantidad.textProperty().addListener(new ChangeListener<String>() {
    		@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try 
    			{
    				float ivat = Float.parseFloat(iva.getText());
    				float preciot = Float.parseFloat(precio.getText());
    				int cantidadt = Integer.parseInt(newValue);
    				float descuentot = Float.parseFloat(descuento.getText());
    				
    				float totalt = (preciot * ivat)+preciot * cantidadt;
    				float totalDescuento = (float) round1(((totalt*descuentot)+totalt),2);
    				total.setText(totalDescuento+"");
    			}
    			catch(Exception e) {}    			
    		}
    	});
    	
    	//DESCUENTO TOTAL FACTURA
    	descuentoTotalFactura.setText("0");
    	
    	
    	
    	//IVA
    	iva.textProperty().addListener(new ChangeListener<String>() {
    		@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try 
    			{
    				codart.getSelectionModel().getSelectedItem().setIva(Float.parseFloat(newValue));
    				
    				float ivat = Float.parseFloat(newValue);
    				float preciot = Float.parseFloat(precio.getText());
    				int cantidadt = Integer.parseInt(cantidad.getText());
    				float descuentot = Float.parseFloat(descuento.getText());
    				
    				float totalt = (preciot * ivat)+preciot * cantidadt;
    				float totalDescuento = (float) round1(((totalt*descuentot)+totalt),2);
    				total.setText(totalDescuento+"");
    			}
    			catch(Exception e) {}    			
    		}
    	});
    	
    	
    	
    	//PRECIO ARTICULO
    	precio.textProperty().addListener(new ChangeListener<String>() {
    		@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try 
    			{
    				codart.getSelectionModel().getSelectedItem().setPrecio(Float.parseFloat(newValue));
    				
    				float ivat = Float.parseFloat(iva.getText());
    				float preciot = Float.parseFloat(newValue);
    				int cantidadt = Integer.parseInt(cantidad.getText());
    				float descuentot = Float.parseFloat(descuento.getText());
    				
    				float totalt = (preciot * ivat)+preciot * cantidadt;
    				float totalDescuento = (float) round1(((totalt*descuentot)+totalt),2);
    				total.setText(totalDescuento+"");
    			}
    			catch(Exception e) {e.printStackTrace();}    			
    		}
    	}); 
    	
    	//DESCUENTO FILA
    	descuento.textProperty().addListener(new ChangeListener<String>() {
    		@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try 
    			{  					
    				float ivat = Float.parseFloat(iva.getText());
    				float preciot = Float.parseFloat(precio.getText());
    				int cantidadt = Integer.parseInt(cantidad.getText());
    				float descuentot = Float.parseFloat(newValue);
    				
    				float totalt = (preciot * ivat)+preciot * cantidadt;
    				float totalDescuento = (float) round1(totalt - (totalt*descuentot),2);
    				total.setText(totalDescuento+"");
    			}
    			catch(Exception e) {}    			
    		}
    	}); 
    	
    	
    	//IVA FINAL FACTURA
    	ivaFactura.textProperty().addListener(new ChangeListener<String>() {
    		@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try 
    			{
    				float baseImponibleC = Float.parseFloat(baseImponible.getText());
    				float precioIvaC = (float) round1(baseImponibleC * Float.parseFloat(newValue),2);
    				float totalFacturaConIvaC = (float) round1(baseImponibleC + (baseImponibleC * Float.parseFloat(newValue)),2);
    				totalFacturaConIvaC = (float) round1(totalFacturaConIvaC - (totalFacturaConIvaC * Float.parseFloat(descuentoTotalFactura.getText())),2);
    				
    				//Base Imponible
    				baseImponible.setText(baseImponibleC+"");
    				//IVA
    				totalIvaFactura.setText(precioIvaC+"");
    				//Total
    				totalFactura.setText(totalFacturaConIvaC+"");
    				
    			}
    			catch(Exception e) {
    				//e.printStackTrace();    			
    			}
    		}
    	}); 
    	
    	//DESCUENTO FINAL FACTURA
    	descuentoTotalFactura.textProperty().addListener(new ChangeListener<String>() {
    		@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try 
    			{
    				float baseImponibleC = Float.parseFloat(baseImponible.getText());
    				float precioIvaC = (float) round1(baseImponibleC * Float.parseFloat(ivaFactura.getText()),2);
    				float totalFacturaConIvaC = (float) round1(baseImponibleC + (baseImponibleC * Float.parseFloat(ivaFactura.getText())),2);
    				totalFacturaConIvaC = (float) round1(totalFacturaConIvaC - (totalFacturaConIvaC * Float.parseFloat(newValue)),2);
    				
    				//Base Imponible
    				baseImponible.setText(baseImponibleC+"");
    				//IVA
    				totalIvaFactura.setText(precioIvaC+"");
    				//Total
    				totalFactura.setText(totalFacturaConIvaC+"");

    			}
    			catch(Exception e) {}    			
    		}
    	}); 
    	
    	//TOTAL FINAL FACTURA
    	totalFactura.textProperty().addListener(new ChangeListener<String>() {
    		@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try 
    			{
    				btncrear.setDisable(false);
    			}
    			catch(Exception e) {}    			
    		}
    	}); 

    	
    	//HORA
    	hora.setText(LocalTime.now()+"");
    	
    	//NUM FACTURA
    	int numFactura = Comprovaciones.getNumFactura(db);
    	nfactura.setText("Num Factura: "+numFactura+"");
    		
    	//TABLEVIEW    	
    	colCodArticulo.setCellValueFactory(new PropertyValueFactory<FacturaFiles,String>("nombre"));
    	colCantidad.setCellValueFactory(new PropertyValueFactory<FacturaFiles,Integer>("cantidad"));
    	colDescuento.setCellValueFactory(new PropertyValueFactory<FacturaFiles,Float>("descuento"));
    	colIva.setCellValueFactory(new PropertyValueFactory<FacturaFiles,Float>("iva"));
    	colPrecio.setCellValueFactory(new PropertyValueFactory<FacturaFiles,Float>("precio"));
    	colTotal.setCellValueFactory(new PropertyValueFactory<FacturaFiles,Float>("precio_total"));  
    	
    	ArrayList<FacturaFiles> files = new ArrayList<FacturaFiles>();

    	//BOTON + A�ADIR FILA
    	addfila.setOnAction(new EventHandler<ActionEvent>()
        {    	
            public void handle(ActionEvent e)
            {
            	files.add(anadirFila());
            	
            	//BASE IMPONIBLE
            	float bi = 0;
            	for(FacturaFiles f : files) 
            	{
            		bi += f.getPrecio_total();
            	}
            	baseImponible.setText(bi+"");
            }
        });     
    	
    	//BOTON CREAR FACTURA
    	btncrear.setOnAction(new EventHandler<ActionEvent>()
        {    	
            public void handle(ActionEvent e)
            {
            	btnCrearFactura(files,bd);
        	}
        });  
    	
    	//SI HAY ALGUN CAMPO OBLIGATORIO Y NO ESTA RELLENADO DESACTIVA EL BOTON DE CREAR FACTURA AL PASAR POR ENCIMA EL BOTON
    	btncrear.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {                
                if(dni.getSelectionModel().isEmpty() || vehiculo.getSelectionModel().isEmpty() || formapago.getText().equalsIgnoreCase("") || tabla.getItems().isEmpty() || ivaFactura.getText().equalsIgnoreCase("")) {
                	btncrear.setDisable(true);
                }
            }
        });

	}
	
	//BOTON A�ADIR FILA
	FacturaFiles anadirFila() 
	{
		FacturaFiles ff = new FacturaFiles();
		try 
		{
			//COGE LOS DATOS DE LA FILA
			int cant = Integer.parseInt(cantidad.getText());
			Articulos art = codart.getSelectionModel().getSelectedItem();
			float desc = Float.parseFloat(descuento.getText());
			float precioTotal = Float.parseFloat(total.getText());
			FacturaHeader fh = new FacturaHeader();

			ff = new FacturaFiles(cant,art,art.getNombre(),art.getIva(), art.getPrecio(),fh,desc,precioTotal);
			//LO A�ADE AL LISTVIEW
			tabla.getItems().add(ff);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ff;
	}
	
	//BOTON CREAR FACTURA
	void btnCrearFactura(ArrayList<FacturaFiles> files, Database bd) {
    	//PAGADO, POR PAGAR
    	int estado = 0;
    	if(pagado.isSelected()) {estado = 1;}
    	else if(porpagar.isSelected()) {estado = 0;}
    	else {estado = 0;}
    	
    	try 
    	{
    		LocalDate value = fechaFactura.getValue();
    		FacturaHeader fh = new FacturaHeader(0,"",estado,Float.parseFloat(baseImponible.getText()),dni.getSelectionModel().getSelectedItem(),vehiculo.getSelectionModel().getSelectedItem(),files,Float.parseFloat(descuentoTotalFactura.getText()),Float.parseFloat(totalIvaFactura.getText()),Float.parseFloat(totalFactura.getText()),observaciones.getText(),value,LocalTime.parse(hora.getText()),formapago.getText());
    		fh.insertFacturaHeader(bd);
    		//AL HACER EL INSERT DEL HEADER TIENE QUE OBTENER EL NUM FACTURA
    		int numFactura = Comprovaciones.returnNumFactura(bd, dni.getSelectionModel().getSelectedItem().getDni(), value, LocalTime.parse(hora.getText()));
    		fh.setNumPressupost(numFactura);
        	for(FacturaFiles f : files) 
        	{
        		f.setFacturasHeader(fh);
        	}
    		// HACE LOS INSERT DEL HEADER Y LAS FILAS
    		for (FacturaFiles f : files) {
    			f.insertFacturaFila(bd);
    		}
    		info.setText("FACTURA CREADA CORRECTAMENTE");
    		System.out.println("INSERT CORRECTO");
    	}
    	catch(Exception ex) {info.setText(ex.getLocalizedMessage());}
	}
	
	//FORMATAR FLOAT A 2 DECIMALES
	public static double round1(double value, int scale) {
	    return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
	}
}
