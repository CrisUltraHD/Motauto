// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package motauto;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


/************************************************************/
/**
 * 
 */
public interface MotAuto {
	
	static Scanner lector = new Scanner(System.in);
	
	public static void main(String[] args){
		Database db=null;
		try {
		db = AlterarEstructuraBBDD.establecerPrimeraConexion();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		while(true) 
		{
			MenuPrincipal(db);
		}
		
	}
	public static void MenuPrincipal(Database db) 
	{
		System.out.println("\n--------MOTAUTO--------");
		System.out.println("1 - Menu Facturas");
		System.out.println("2 - Menu Clientes");
		System.out.println("3 - Menu Vehiculos");
		System.out.println("4 - Menu Articulos");
		System.out.println("5 - Configuracion Base de Datos");
		System.out.println("6 - Salir del programa");
		System.out.println("_______________________");
		System.out.print("Opcion: ");
		
		int opcion = Utiles.menu(6);
		
		switch(opcion) 
		{
		
		case 1:
			MenuFactures(db);
			break;
		case 2:
			MenuClient(db);
			break;
		case 3:
			MenuVehicles(db);
			break;
		case 4:
			MenuAticulos(db);
			break;
		case 5:
			db = MenuConfiguracion(db);
			break;
		case 6:
			System.exit(0);
			default:
				MenuPrincipal(db);
				break;
	
		}

	}
	public static void MenuFactures(Database db) 
	{
		System.out.println("\n--------MOTAUTO--------");
		System.out.println("1 - Crear Factura");
		System.out.println("2 - Modificar Factura");
		System.out.println("3 - Borrar Factura");
		System.out.println("4 - Imprimir Factura");
		System.out.println("5 - Imprimir Facturas");
		System.out.println("6 - Volver");
		System.out.println("7 - Salir del programa");
		System.out.println("_______________________");
		System.out.print("Opcion: ");
		
		int opcion = Utiles.menu(7);
		
		switch(opcion) 
		{
		case 1:
			Modificacions.CrearFactura(db);
			break;
		case 2:
			Modificacions.ModificarFactura(db);
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		case 5:
			
			break;
		case 6:
			MenuPrincipal(db);
			break;
		case 7:
			System.exit(0);
			break;
			
		}
	}

	public static void MenuClient(Database db) 
	{
		System.out.println("\n--------Cliente--------");
		System.out.println("1 - Introducir Cliente");
		System.out.println("2 - Modificar Cliente");
		System.out.println("3 - Borrar Cliente");
		System.out.println("4 - Imprimir Clientes");
		System.out.println("5 - Volver");
		System.out.println("6 - Salir del programa");
		System.out.println("_______________________");
		System.out.print("Opcion: ");

		int opcion = Utiles.demanarNum();
		
		switch(opcion) 
		{
		case 1:
			Modificacions.altaCliente(db);
			break;
		case 2:
			Modificacions.ModificarCliente(db);
			break;
		case 3:
			Modificacions.borrarCliente(db);
			break;
		case 4:
			Comprovaciones.mostrarClientes(db);
			break;
		case 5:
			MenuPrincipal(db);
			break;
		case 6:
			System.exit(0);
			break;
			
		}
	}
	
	
	public static void MenuVehicles(Database db)
	{
		System.out.println("\n--------MOTAUTO--------");
		System.out.println("1 - Crear Vehiculo");
		System.out.println("2 - Modificar Vehicles");
		System.out.println("3 - Borrar Vehicles");
		System.out.println("4 - Imprimir Vehicles");
		System.out.println("5 - Volver");
		System.out.println("6 - Salir del programa");
		System.out.println("_______________________");
		System.out.print("Opcion: ");
		
		int opcion = Utiles.demanarNum();
		
		switch(opcion) 
		{
		case 1:
			Modificacions.altaVehiculo(db);
			break;
		case 2:
		Modificacions.ModificarVehiculo(db);
			break;
		case 3:
		Modificacions.borrarVehiculo(db);
			break;
		case 4:
		Comprovaciones.mostrarVehiculos(db);
			break;
		case 5:
			MenuPrincipal(db);
			break;
		case 6:
			System.exit(0);
			break;
			
		}
	}
public static void MenuAticulos(Database db) {
		
		System.out.println("1 - Crear Articulo");
		System.out.println("2 - Modificar Articulo");
		System.out.println("3 - Borrar Articulo");
		System.out.println("4 - Imprimir Articulos");
		System.out.println("5 - Volver");
		System.out.println("6 - Salir del programa");
		System.out.println("_______________________");
		
		int opcion = Utiles.menu(6);
		
		switch(opcion) 
		{
		case 1:
			Modificacions.altaArticulo(db);
			break;
		case 2:
			Modificacions.ModificarAriculo(db);
			break;
		case 3:
			Modificacions.borrarArticulo(db);
			break;
		case 4:
			Comprovaciones.mostrarArticulos(db);
			break;
		case 5:
			MenuPrincipal(db);
			break;
		case 6:
			System.exit(0);
			break;
			
		}
		
	}
public static Database MenuConfiguracion(Database db) {
	System.out.print("Introduce la contraseņa de administrador: ");
	String passw;
	   passw = Utiles.demanarString();
	   if (!passw.equalsIgnoreCase("admin")) {return null;}
	
	Database conexion = db;
	System.out.println("1 - Cambiar conexion Base de datos");
	System.out.println("2 - Crear BDD ");
	System.out.println("3 - Cargar Scripts SQL");
	System.out.println("4 - Mostrar tablas Base de datos");
	System.out.println("5 - Inyectar Codigo SQL");
	System.out.println("6 - Mostrar Datos de Conexion");
	System.out.println("7 - Volver");
	System.out.println("8 - Salir del programa");
	System.out.println("_______________________");
	
	int opcion = Utiles.menu(6);
	
	switch(opcion) 
	{
	case 1:
		conexion = AlterarEstructuraBBDD.configuracionBDD();
		break;
	case 2:
		AlterarEstructuraBBDD.createBDD();
		break;
	case 3:
		AlterarEstructuraBBDD.updateBDD(db);
		break;
	case 4:
		AlterarEstructuraBBDD.showTables(db);
		break;
	case 5:
		AlterarEstructuraBBDD.sqlInyector(db);
		break;
	case 6:
		conexion.showAll();
		break;
	case 7:
		MenuPrincipal(db);
		break;
	case 8:
		System.exit(0);
		break;
		
	}
	return conexion;
	   
}
}
