// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package motauto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.collections.ObservableList;

import java.sql.Time;
/************************************************************/
/**
 * 
 */
public class Comprovaciones {
	/**
	 * 
	 * @param dni 
	 * @param existe 
	 */
	public static boolean  comprovarDni(String dni, Database db) {
		boolean existe=false;
		try 
		{
            ResultSet rs = db.ExecuteQuery("SELECT * FROM clientes WHERE dni = '"+dni+"';");
            
            if(rs.next()) 
            {
            	existe = true;
            }
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return existe;
	}

	/**
	 * 
	 * @param codigoArticulo 
	 * @return 
	 */
	public static boolean comprovarCodigoArticulo(String codigoArticulo, Database db) 
	{
		boolean trobat = false;
		
		try 
		{
            ResultSet rs = db.ExecuteQuery("SELECT * FROM articulos WHERE codigo = '"+codigoArticulo+"';");
            
            while (rs.next()) 
            {
            	trobat = true;
            }
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return trobat;

	}

	/**
	 * 
	 * @param matricula 
	 * @param Existeix 
	 */
	public static boolean comprovarMatricula(String matricula, Database db) 
	{
		boolean trobat = false;
		
		try 
		{
            ResultSet rs = db.ExecuteQuery("SELECT * FROM vehiculo WHERE matricula = '"+matricula+"';");
            
            if (rs.next()) 
            {
            	trobat = true;
            }
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return trobat;
	}
	
	/**
	 * 
	 * @param codigo 
	 */
	public static boolean comprobarNumFactura(int codigo, Database db) 
	{
		boolean trobat = false;
		
		try 
		{
            ResultSet rs = db.ExecuteQuery("SELECT * FROM facturas_header WHERE num_factura = '"+codigo+"';");
            
            if (rs.next()) 
            {
            	trobat = true;
            }
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return trobat;

	}
	
	/**
	 * 
	 * @param num fila 
	 */
	public static boolean comprobarNumFila(int num, Database db) 
	{
		boolean trobat = false;
		
		try 
		{
            ResultSet rs = db.ExecuteQuery("SELECT * FROM facturas_filas WHERE num_fila = '"+num+"';");
            
            if (rs.next()) 
            {
            	trobat = true;
            }
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return trobat;

	}

	/**
	 * 
	 * @param DNI 
	 *  
	 */
	public static Cliente consultaClient(String dni, Database db) {
		Cliente cliente=null;
		try 
		{
            ResultSet rs = db.ExecuteQuery("SELECT * FROM clientes WHERE dni = '"+dni+"';");
            
            if(rs.next()) 
            {
            	cliente = new Cliente(rs.getString(1), rs.getNString(2), rs.getNString(3), rs.getNString(4), rs.getInt(5), rs.getString(6));
            }
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		return cliente;
	}


	public static Articulos consultaArticulo(String codigoArticulo, Database db) {
		String codigo = "";
		String nombre = "";
		float precio = 0;
		float iva = 0;
		try 
		{
            ResultSet rs = db.ExecuteQuery("SELECT * FROM articulos WHERE codigo = '"+codigoArticulo+"';");
            while (rs.next()) 
            {
            	codigo = rs.getString(1);
            	nombre = rs.getString(2);
            	precio = rs.getFloat(3);
            	iva = rs.getFloat(4);
            }
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		Articulos articulo = new Articulos(codigo,nombre,precio,iva);
		
		return articulo;
	}
	
	
	public static Vehiculo consultaVehiculo(String matricula, Database db) {
		Vehiculo vehiculo=null;
		Cliente cliente=null;
		try 
		{
            ResultSet rs = db.ExecuteQuery("SELECT * FROM vehiculo WHERE matricula = '"+matricula+"';");
            
            if(rs.next()) 
            {
            	cliente = consultaClient(rs.getString(1), db);
            	vehiculo = new Vehiculo(rs.getString(2), rs.getString(3), rs.getString(4), cliente);
            }
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return vehiculo;
	}
	

	//RETORNA NUM FACTURA
	public static int returnNumFactura(Database db, String dniCliente, LocalDate data, LocalTime hora)
	{
		
		int num = 0;
		
		try{
			
			ResultSet rs = db.ExecuteQuery("SELECT num_factura FROM facturas_header WHERE dni_cliente = '"+dniCliente+"' AND fecha = '"+java.sql.Date.valueOf(data)+"' AND hora = '"+java.sql.Time.valueOf(hora)+"';");
			
			if(rs.next())
			{
				num = rs.getInt(1);
			}
			//IMPORTANTE
			rs.close();

		}
		catch(Exception e)
		{
			System.out.println(e.getCause());
		}
		
		return num;
	}
	

	//RETORNA FACTURA HEADER
	public static FacturaHeader consultaFacturaHeader(int codigoFactura, Database db) 
	{
		String cifEmpresa = "";
		int numPressupost = 0;
		int estado = 0;
		float total = 0;
		Cliente cliente=null;
		Vehiculo vehiculo=null;
		ArrayList<FacturaFiles> arrayFacturaFiles = new ArrayList<FacturaFiles>();
		String forma_pago = "";
		float descuento = 0;
		float totalIva = 0;
		float totalFactura = 0;
		String observaciones = "";
		Date dataFactura = null;
		Time horaFactura = null;
		String dniCliente = "";
		
		

		try 
		{
            ResultSet rs = db.ExecuteQuery("SELECT * FROM facturas_header WHERE num_factura = '"+codigoFactura+"';");
            while (rs.next()) 
            {
            	cifEmpresa = rs.getString(1);
            	numPressupost = rs.getInt(2);
            	cliente = consultaClient(rs.getString(3), db);
            	dataFactura = rs.getDate(4);
            	horaFactura = rs.getTime(5);
            	forma_pago = rs.getString(6);
            	vehiculo = consultaVehiculo(rs.getString(7), db);
            	estado = rs.getInt(8);
            	total = rs.getFloat(9);
            	descuento = rs.getFloat(10);
            	totalIva = rs.getFloat(11);
            	totalFactura = rs.getFloat(12);
            	observaciones = rs.getString(13);
            }
		}
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		//CONVERTIR DATE Y TIME A LOCALDATE LOCALTIME
		LocalDate dataBona = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(dataFactura));
		LocalTime horaBona = toLocalTime(java.sql.Time.valueOf(horaFactura.toString()));


		
		FacturaHeader facturaHeader = new FacturaHeader();
		
		ArrayList<FacturaFiles> filas = consultaFacturaFiles(facturaHeader,codigoFactura,db);
	
		facturaHeader = new FacturaHeader(numPressupost,cifEmpresa,estado,total,cliente,vehiculo,filas,descuento,totalIva,totalFactura,observaciones,dataBona,horaBona,forma_pago);

		for(FacturaFiles f : filas) 
		{
			f.setFacturasHeader(facturaHeader);
		}
		
		return facturaHeader;
	}
	
	//CONVERTEIX EL TIME A LOCALTIME
	public static LocalTime toLocalTime(java.sql.Time time) 
	{
	    return time.toLocalTime();
	}
	
	//RETORNA ARRAYLIST FILES
	public static ArrayList<FacturaFiles> consultaFacturaFiles(FacturaHeader header, int codigoFactura, Database db) 
	{
			
		ArrayList<FacturaFiles> filas = new ArrayList<FacturaFiles>();
		
		try 
		{
            ResultSet rs = db.ExecuteQuery("SELECT * FROM facturas_filas WHERE num_factura = '"+codigoFactura+"';");
            
            while (rs.next()) 
            {
            	Articulos articulo = Comprovaciones.consultaArticulo(rs.getString(2), db);
            	FacturaFiles ff = new FacturaFiles(rs.getInt(5),rs.getInt(4),articulo,header,rs.getFloat(6),rs.getFloat(7));         
            	filas.add(ff);
            }           
           
		}
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		
		return filas;
	}
	
	
	
	public static void mostrarClientes(Database db) {
		try {
			System.out.println("Lista de los clientes: ");
			 ResultSet rs = db.ExecuteQuery("SELECT * FROM clientes;");
	            while (rs.next()) 
	            {
	            	System.out.print(rs.getString(2) + " " + rs.getString(3) + " amb DNI: "+ rs.getString(1) + " Correo: " +rs.getString(4) + " Telefono: " + rs.getInt(5) + " Direccion " + rs.getString(6));
	            	System.out.println();
	            }
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
	}
	
	
	public static void mostrarVehiculos(Database db, ObservableList<Vehiculo> vehiculo) {
		try 
			{
	            ResultSet rs = db.ExecuteQuery("SELECT * FROM vehiculo;");
	            while (rs.next()) 
	            {
	            	vehiculo.add(
	            			new Vehiculo (rs.getString(2),rs.getString(3),rs.getString(4),Comprovaciones.consultaClient(rs.getString(1), db)));
	            }
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}


		}
	
	
	public static void mostrarArticulos(Database db, ObservableList<Articulos> articulo) {
		try 
			{
	            ResultSet rs = db.ExecuteQuery("SELECT * FROM articulos;");
	            while (rs.next()) 
	            {
	            	articulo.add(
	            			new Articulos(rs.getString(1),rs.getString(2),rs.getFloat(3),rs.getFloat(4)));
	            }
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}


		}	
	
	public static void llenarInformacionFacturaFiles(Database db, ObservableList<FacturaFiles> ff, int numFactura) 
	{
		try 
		{
			ResultSet rs = db.ExecuteQuery("SELECT * FROM facturas_filas where num_factura = '"+numFactura+"';");
			
			while(rs.next()) 
			{
				ff.add(
				new FacturaFiles(rs.getInt(4),Comprovaciones.consultaArticulo(rs.getString(2), db),Comprovaciones.consultaArticulo(rs.getString(2), db).getNombre(),Comprovaciones.consultaArticulo(rs.getString(2), db).getIva(),Comprovaciones.consultaArticulo(rs.getString(2), db).getPrecio(),Comprovaciones.consultaFacturaHeader(rs.getInt(1), db),rs.getFloat(6),rs.getFloat(7)));
			}
			
			rs.close();

		}
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	
	
	public static void mostrarFacturas(Database db) {
		try 
		{
			ResultSet rs = db.ExecuteQuery("SELECT * FROM facturas_header;");
			
			while(rs.next()) 
			{
				System.out.println("CIF: " + rs.getString(1) + " | NUM FACTURA: "+rs.getString(2) + " | DNI CLIENTE: " + rs.getString(3) + " | FECHA: " + rs.getDate(4) + " | HORA: " + rs.getTime(5) + " | FORMA PAGO: " + rs.getString(6) + " | MATRICULA: " + rs.getString(7) + " | ESTADO: " + rs.getString(8) + " | TOTAL BASE IMPONIBLE: " + rs.getFloat(9) + " | DESCUENTO: " + rs.getFloat(10) + " | TOTAL IVA: " + rs.getFloat(11) + " | TOTAL FACTURA: " + rs.getFloat(12) + " | OBSERVACIONES: " + rs.getString(13));
			}
			
			rs.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*public static void mostrarFacturasPerNum(FacturaHeader fh, ArrayList<FacturaFiles> ff, Database db) 
	{
		System.out.println("\n-------------------------------------------------------");

		//INFO PRESUPUESTO
		System.out.println("N� Pressupost: "+fh.getNumPressupost());
		System.out.println("CIF Empresa: "+fh.getCifEmpresa());
		System.out.println("Fecha Factura: "+fh.getDataFactura());
		System.out.println("Estado: "+fh.getEstado());
		System.out.println("Forma De Pago: "+fh.getForma_pago());

		//INFO CLIENTE
		System.out.println("Dni Cliente: "+fh.getCliente().getDni());
		System.out.println("Nombre Cliente: "+fh.getCliente().getNombre());
		System.out.println("Apellidos Cliente: "+fh.getCliente().getApellidos());
		
		//INFO VEHICULO
		System.out.println("Matricula Vehiculo: "+fh.getVehiculo().getMatricula());
		System.out.println("Tipo Vehiculo: "+fh.getVehiculo().getTipo_vehiculo());

		//FILAS ARTICULOS USADOS
		for(FacturaFiles f : ff) 
		{
			System.out.println(					
					"\tArticulo: "+f.getArticulos().getNombre() + 
					" Cantidad: "+f.getCantidad() + 
					" Descuento: " + f.getDescuento() + 
					" Total: " + f.getPrecio_total());
		}
		
		//INFO TOTALES
		System.out.println("Descuento: "+fh.getDescuentoFactura());
		System.out.println("Total Base Imponible"+fh.getTotal());
		System.out.println("Total Iva: "+fh.getTotalIva());
		System.out.println("Total + IVA: "+fh.getTotalFactura());
		
		//OBSERVACIONES
		System.out.println("Observaciones: "+fh.getObservaciones());

		System.out.println("-------------------------------------------------------");

	}*/
	
	/*public static void mostrarFiles(FacturaHeader fh, ArrayList<FacturaFiles> ff, Database db) 
	{
		System.out.println("\n-------------------------------------------------------");
		//FILAS ARTICULOS USADOS
		for(FacturaFiles f : ff) 
		{
			System.out.println(
					"Num Fila: " + f.getNumFila() +
					" Articulo: "+f.getArticulos().getNombre() + 
					" Cantidad: "+f.getCantidad() + 
					" Descuento: " + f.getDescuento() + 
					" Total: " + f.getPrecio_total());
		}
		System.out.println("-------------------------------------------------------");

	}*/
	
	/*public static void imprimirFacturaPorCodigo(Database db) 
	{
		Comprovaciones.mostrarFacturas(db);
		System.out.println("Inserta num factura a imprimir: ");
		int num = Utiles.demanarNum();
		
		if(!comprobarNumFactura(num, db))
		{
			System.out.println("Num factura no existe, saliendo... ");
			MotAuto.MenuPrincipal(db);
		}
		FacturaHeader fh = Comprovaciones.consultaFacturaHeader(num, db);
		ArrayList<FacturaFiles> ff = Comprovaciones.consultaFacturaFiles(fh, num, db);
		
		Comprovaciones.mostrarFacturasPerNum(fh, ff, db);
	}*/
	

	/*public static void ImprimirArticulos(Database db) {
		try {
			ResultSet rs = db.ExecuteQuery("SELECT * FROM articulos");
			System.out.println("CODIGO:\tNombre: ");
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 * Obtener el ultimo numero de factura +1 que no sea repetido
	 * 
	 */
	public static int getNumFactura(Database db) 
	{
		int num = 0;
		
		try 
		{
			
            ResultSet rs = db.ExecuteQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME = 'facturas_header';");
            rs.next();
            num = rs.getInt(1);


		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return num;

	}
	

	public static Vehiculo consultaVehiculoPorDni(String dni, Database db) {
		Vehiculo vehiculo=null;
		Cliente cliente=null;
		try 
		{
            ResultSet rs = db.ExecuteQuery("SELECT * FROM vehiculo WHERE dni = '"+dni+"';");
            
            if(rs.next()) 
            {
            	cliente = consultaClient(rs.getString(1), db);
            	vehiculo = new Vehiculo(rs.getString(2), rs.getString(3), rs.getString(4), cliente);
            }
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		return vehiculo;
	}
	
	
	/*
	 * Llenar observableList
	 */
	public static void mostrarClientes(Database db, ObservableList<Cliente> clientes) {
		try {
			 ResultSet rs = db.ExecuteQuery("SELECT * FROM clientes;");
	            while (rs.next()) 
	            {
	            	clientes.add(
	            			new Cliente (rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6)));
	            }
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
	}
	
	
	public static void llenarInformacionHeader(Database db, ObservableList<FacturaHeader> header) 
	{
		try 
		{
			ResultSet rs = db.ExecuteQuery("SELECT * FROM facturas_header");
			ArrayList<FacturaFiles> ff = new ArrayList<FacturaFiles>();
			while(rs.next()) 
			{
				header.add(
				new FacturaHeader(rs.getInt(2),rs.getString(1),rs.getInt(8),rs.getFloat(9), Comprovaciones.consultaClient(rs.getString(3), db), Comprovaciones.consultaVehiculo(rs.getString(7), db),ff,rs.getFloat(10),rs.getFloat(11),rs.getFloat(12),rs.getString(13),rs.getDate(4).toLocalDate(),rs.getTime(5).toLocalTime(),rs.getString(6)));
			}
			
			rs.close();

		}
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void llenarInformacionCliente(Database db, ObservableList<Cliente> clientes) 
	{
		try 
		{
			ResultSet rs = db.ExecuteQuery("SELECT * FROM clientes");
			
			while(rs.next()) 
			{
				clientes.add(
				new Cliente(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getInt(5), rs.getString(6)));
			}
			
			rs.close();

		}
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void llenarInformacionArticulos(Database db, ObservableList<Articulos> articulos) 
	{
		try 
		{
			ResultSet rs = db.ExecuteQuery("SELECT * FROM articulos");
			
			while(rs.next()) 
			{
				articulos.add(
				new Articulos(rs.getString(1),rs.getString(2),rs.getFloat(3),rs.getFloat(4)));
			}
			
			rs.close();

		}
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}

	public static void llenarInformacionVehiculo(Database db, ObservableList<Vehiculo> vehiculo, String dni) 
	{
		try 
		{
			ResultSet rs = db.ExecuteQuery("SELECT * FROM vehiculo where dni = '" + dni + "';");
			
			while(rs.next()) 
			{
				vehiculo.add(
				new Vehiculo(rs.getString(2),rs.getString(3),rs.getString(4),Comprovaciones.consultaClient(rs.getString(1), db)));
			}
			
			rs.close();

		}
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}


}
