package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import motauto.AlterarEstructuraBBDD;
import motauto.Database;

public class Header extends Application {

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
  

}
