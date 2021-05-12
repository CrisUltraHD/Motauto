package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import motauto.AlterarEstructuraBBDD;
import motauto.Cliente;
import motauto.Comprovaciones;
import motauto.Database;

public class Crear_Cliente implements Initializable {

	public static Stage stageProjecte;
	
	//HEADER
	@FXML
    private MenuItem verClientes;

    @FXML
    private MenuItem modificarCliente;

    @FXML
    private MenuItem crearArticulo;

    @FXML
    private MenuItem verVehiculos;

    @FXML
    private MenuItem borrarFactura;

    @FXML
    private MenuItem verFacturas;

    @FXML
    private MenuItem borrarArticulo;

    @FXML
    private MenuItem crearFactura;

    @FXML
    private MenuItem modificarArticulo;

    @FXML
    private MenuItem crearCliente;

    @FXML
    private MenuItem borrarCliente;

    @FXML
    private Button home;

    @FXML
    private MenuItem verArticulos;

    @FXML
    private MenuItem modificarVehiculo;

    @FXML
    private MenuItem crearVehiculo;

    @FXML
    private MenuItem modificarFactura;

    @FXML
    private MenuItem borrarVehiculos;

	//ACABA HEADER

    @FXML
    public static Pane pane;
    
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
		
		
		
		/*crearFactura.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					System.out.println("asdadadsdasdasdsadad");
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistes/Alta_Factura.fxml"));
					AnchorPane root = (AnchorPane) fxmlLoader.load();
					stageProjecte = new Stage();
					stageProjecte.setScene(new Scene(root));
					stageProjecte.show();
					//loginController.stagePrincipal.close();
				} catch (Exception e) {
					e.printStackTrace();
				} 		
			}
		});*/

		
		
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
