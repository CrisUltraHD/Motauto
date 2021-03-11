// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package motauto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/************************************************************/
/**
 * 
 */
public interface MotAuto {
	
	static Scanner lector = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean introducirParametros = Utiles.siNo("Quieres introducir los datos par la conexion? ");
		if (introducirParametros){
		Database db = Utiles.dadesConexion();
		}
		
		while(true) 
		{
			MenuPrincipal();
		}
		
	}
	/**
	 * 
	 */
	
	public static void MenuPrincipal() 
	{
		System.out.println("\n--------MOTAUTO--------");
		System.out.println("1 - Menu Facturas");
		System.out.println("2 - Menu Clientes");
		System.out.println("3 - Menu Vehiculos");
		System.out.println("4 - Salir del programa");
		System.out.println("_______________________");
		System.out.print("Opcion: ");
		
		int opcion = Utiles.demanarNum();
		
		switch(opcion) 
		{
		
		case 1:
			MenuFactures();
			break;
		case 2:
			MenuClient();
			break;
		case 3:
			MenuVehicles();
			break;
		case 4:
			System.exit(0);
			break;
	
		}

	}

	
	
	
	public static void MenuFactures() 
	{
		System.out.println("\n--------MOTAUTO--------");
		System.out.println("1 - Crear Factura");
		System.out.println("2 - Modificar Factura");
		System.out.println("3 - Borrar Factura");
		System.out.println("4 - Imprimir Factura");
		System.out.println("5 - Volver");
		System.out.println("6 - Salir del programa");
		System.out.println("_______________________");
		
		int opcion = Utiles.demanarNum();
		
		switch(opcion) 
		{
		case 1:
			System.out.println("Introdueix DNI Client: ");
			String dniClient = lector.nextLine();
			
			LocalDate data = Utiles.demanarData();
			
			System.out.println("Insereix Forma De Pagament: ");
			String formaPagament = lector.nextLine();
			
			System.out.println("Insereix Matricula Client: ");
			String matriculaClient = lector.nextLine();
			
			System.out.println("Estado Factura: ");
			String estado = lector.nextLine();
			
			//ArrayList<String> articulos = Utiles.ArticlesComprats();


			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			MenuPrincipal();
			break;
		case 6:
			System.exit(0);
			break;
			
		}
	}

	public static void MenuClient() 
	{
		System.out.println("\n--------MOTAUTO--------");
		System.out.println("1 - Crear Vehiculo");
		System.out.println("2 - Modificar Cliente");
		System.out.println("3 - Borrar Cliente");
		System.out.println("4 - Imprimir Cliente");
		System.out.println("5 - Volver");
		System.out.println("6 - Salir del programa");
		System.out.println("_______________________");

		int opcion = Utiles.demanarNum();
		
		switch(opcion) 
		{
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			MenuPrincipal();
			break;
		case 6:
			System.exit(0);
			break;
			
		}
	}
	
	
	public static void MenuVehicles()
	{
		System.out.println("\n--------MOTAUTO--------");
		System.out.println("1 - Crear Vehiculo");
		System.out.println("2 - Modificar Cliente");
		System.out.println("3 - Borrar Cliente");
		System.out.println("4 - Imprimir Cliente");
		System.out.println("5 - Volver");
		System.out.println("6 - Salir del programa");
		System.out.println("_______________________");
		
		int opcion = Utiles.demanarNum();
		
		switch(opcion) 
		{
		case 1:
			
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			MenuPrincipal();
			break;
		case 6:
			System.exit(0);
			break;
			
		}
	}
}
