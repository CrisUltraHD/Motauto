// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package motauto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */
public class Modificacions {

	public static Scanner lector = new Scanner(System.in);

	public static void altaCliente(Database db) {
		String dni, nombre, apellidos, correo, direccion;
		int telefono;
		System.out.println("Dni cliente: ");
		dni = Utiles.demanarString();
		if (!Comprovaciones.comprovarDni(dni, db)) {
			System.out.println("Nombre cliente: ");
			nombre = Utiles.demanarString();
			System.out.println("Apellidos cliente: ");
			apellidos = Utiles.demanarString();
			System.out.println("Correo cliente: ");
			correo = Utiles.demanarString();
			System.out.println("Telefono cliente: ");
			telefono = Utiles.demanarNum();
			System.out.println("Direccion cliente: ");
			direccion = Utiles.demanarString();
			Cliente cliente = new Cliente(dni, nombre, apellidos, correo, telefono, direccion);
			cliente.insertCliente(db);
		} else {
			System.out.println("Ya existe un cliente con este dni.");
		}

	}

	/**
	 * 
	 * @param ObjectArticulo
	 */
	public static void altaArticulo(Database db) {
		String codigo, nombre;
		float precio, iva;

		System.out.println("Codigo Articulo: ");
		codigo = Utiles.demanarString();

		boolean existe = Comprovaciones.comprovarCodigoArticulo(codigo, db);
		if (existe) {
			System.out.println("CODIGO ARTICULO YA EXISTE");
		} else if (!existe) {
			System.out.println("Nombre Articulo: ");
			nombre = Utiles.demanarString();
			System.out.println("Precio Articulo: ");
			precio = Utiles.demanarNumFloat();
			System.out.println("Iva Articulo: ");
			iva = Utiles.demanarNumFloat();

			Articulos articulo = new Articulos(codigo, nombre, precio, iva);
			articulo.insertArticulo(db);
		}
	}

	/**
	 * 
	 * @param ObjecteVehicle
	 */

	public static void altaVehiculo(Database db) {

		String matricula, dni, color, tipo;
		System.out.println("Matricula Vehiculo: ");
		matricula = Utiles.demanarString();
		if (!Comprovaciones.comprovarMatricula(matricula, db)) {
			System.out.println("Dni client: ");
			dni = Utiles.demanarString();
			if (Comprovaciones.comprovarDni(dni, db)) {
				Cliente cliente = Comprovaciones.consultaClient(dni, db);
				System.out.println("Color Vehiculo: ");
				color = Utiles.demanarString();
				System.out.println("Tipo Vehiculo: ");
				tipo = Utiles.demanarString();
				Vehiculo vehiculo = new Vehiculo(matricula, color, tipo, cliente);
				vehiculo.insertVehiculo(db);
			} else

			{
				System.out.println("No existe un cliente con este DNI");
			}

		} else {
			System.out.println("Ya existe un Vehiculo con esta matricula");
		}
	}

	/**
	 * 
	 * @param dni
	 */
	public static void borrarCliente(Database db) {

		if (Utiles.siNo("Quieres mostrar los Clientes? ")) {
			Comprovaciones.mostrarClientes(db);
		}
		System.out.println("Introduce el Dni del cliente para borrarlo: ");
		String dni = Utiles.demanarString();
		if (Comprovaciones.comprovarDni(dni, db)) {

			Cliente cliente = Comprovaciones.consultaClient(dni, db);
			System.out.println("El dni " + dni + " equivale " + cliente.getNombre() + " " + cliente.getApellidos());
			if (Utiles.siNo("Seguro que quieres borrarlo?")) {
				cliente.borrarCliente(db);
			} else {
				System.out.println("Cancelado.");
			}

		} else {
			System.out.println("Este dni no exsite.");
			borrarCliente(db);
		}

	}

	/**
	 * 
	 * @param codigoArticulo
	 */
	public static void borrarArticulo(Database db) {
		if (Utiles.siNo("Quieres mostrar los Articulos? ")) {
			Comprovaciones.mostrarArticulos(db);
		}
		System.out.println("Introduce el codigo del Articulo para borrar-lo: ");
		String codigoArticulo = Utiles.demanarString();
		if (Comprovaciones.comprovarCodigoArticulo(codigoArticulo, db)) {

			Articulos articulo = Comprovaciones.consultaArticulo(codigoArticulo, db);
			System.out.println("El codigo articulo " + codigoArticulo + " equivale a " + articulo.getNombre());
			if (Utiles.siNo("Seguro que quieres borrarlo?")) {
				articulo.borrarArticulo(db);
			} else {
				System.out.println("Cancelado.");
			}

		} else {
			System.out.println("Este codigo del articulo no exsite.");

		}
	}

	/**
	 * 
	 * @param matricula
	 */
	public static void borrarVehiculo(Database db) {

	if (Utiles.siNo("Vols mostrar les matricules? ")) {
			Comprovaciones.mostrarVehiculos(db);
		}
		System.out.println("Introdueix la matricula per borrar-lo: ");
		String matricula = Utiles.demanarString();
		if (Comprovaciones.comprovarMatricula(matricula, db)) {

			
			Vehiculo vehiculo= Comprovaciones.consultaVehiculo(matricula, db);
			System.out.println("La matricula: " + matricula + " equivale a " +  vehiculo.getMatricula());
			if (Utiles.siNo("Seguro que quieres borrarlo?")) {
				vehiculo.borrarVehiculo(db);
			} else {
				System.out.println("Cancelado.");
			}

		} else {
			System.out.println("Esta matricula no exsite.");

		}

	}

	/*
	 * 
	 * Crea una factura
	 * 
	 */
	public static void CrearFactura(Database db) {

		boolean fiArticles = false;
		float totalFiles = 0;
		ArrayList<FacturaFiles> filas = new ArrayList<FacturaFiles>();

		Comprovaciones.mostrarClientes(db);

		System.out.println("DNI Cliente: ");
		String dni = lector.nextLine();

		if (!Comprovaciones.comprovarDni(dni, db)) {
			boolean resposta = Utiles.siNo("Quieres a�adir el cliente?");
			if (!resposta) {
				MotAuto.MenuPrincipal(db);
			}

			System.out.println("--Crear Cliente--");
			Modificacions.altaCliente(db);
			Comprovaciones.mostrarClientes(db);

			System.out.println("DNI Cliente: ");
			dni = lector.nextLine();

		}

		Cliente cliente = Comprovaciones.consultaClient(dni, db);
		// *******************************************************

		// SECCION VEHICULO
		Comprovaciones.mostrarVehiculos(db);
		System.out.println("Matricula Cliente: ");
		String matricula = lector.nextLine();

		if (!Comprovaciones.comprovarMatricula(matricula, db))

		{
			boolean resposta = Utiles.siNo("Quieres a�adir el vehiculo?");
			if (!resposta) {
				MotAuto.MenuPrincipal(db);
			}

			Modificacions.altaVehiculo(db);

			Comprovaciones.mostrarVehiculos(db);

			System.out.println("Matricula Cliente: ");
			matricula = lector.nextLine();
		}

		Vehiculo vehiculo = Comprovaciones.consultaVehiculo(matricula, db);
		// *******************************************************

		FacturaHeader fh = new FacturaHeader();

		while (!fiArticles) {
			System.out.println("ARTICULOS A A�ADIR: ");
			Comprovaciones.ImprimirArticulos(db);
			System.out.println("CODIGO ARTICULO: \n Inserta 999 para salir");
			String codigo = lector.nextLine();

			if (codigo.equalsIgnoreCase("999")) {
				fiArticles = true;
			}

			// SI EL CODIGO ARTICULO NO EXISTE
			else if (!Comprovaciones.comprovarCodigoArticulo(codigo, db)) 
			{
				// GUARDA SI O NO
				boolean creacio = Utiles.siNo("El articulo no existe quieres crearlo? Si No");

				if (creacio) 
				{
					Modificacions.altaArticulo(db);
				}
				
			}
			
			else if (Comprovaciones.comprovarCodigoArticulo(codigo, db)) 
			{
				// DEVUELVE UN OBJETO ARTICULO
				Articulos articulo = Comprovaciones.consultaArticulo(codigo, db);

				System.out.println("Cantidad: ");
				int cantidad = Utiles.demanarNum();

				System.out.println("Descuento: ");
				float descuento = Utiles.demanarNum();

				float total = articulo.getPrecio();
				total = total * cantidad;
				total = total + (total * articulo.getIva());
				total = total - (total * descuento);
				

				System.out.println("Precio total calculado de la fila: " + total);
				totalFiles += total;

				// GENEREM UNA FILA I LA GUARDEM AL ARRAYLIST
				FacturaFiles ff = new FacturaFiles(cantidad, articulo, fh, descuento, total);
				filas.add(ff);
			}
		}

		// ACABAT DE ESCOLLIR ARTICLES
		System.out.println("Estado Factura \n0 - Por Pagar\n1 - Pagado: ");
		int estado = Utiles.menu(2);

		System.out.println("Inserta IVA Factura");
		float ivaFactura = Utiles.demanarNumFloat();

		System.out.println("Descuento Factura: ");
		float descuentoFactura = Utiles.demanarNumFloat();

		System.out.println("Observaciones: ");
		String observaciones = lector.nextLine();

		System.out.println("Forma De Pago: ");
		String formaPago = lector.nextLine();

		float totalBaseImponible = totalFiles;
		float precioIva = totalBaseImponible * ivaFactura;
		float totalFacturaConIva = totalFiles + (totalFiles * ivaFactura);
		totalFacturaConIva = totalFacturaConIva - (totalFacturaConIva * descuentoFactura);

		LocalDate data = LocalDate.now();
		LocalTime hora = LocalTime.now();


		fh = new FacturaHeader(0,"", estado, totalBaseImponible, cliente, vehiculo, filas, descuentoFactura, precioIva, totalFacturaConIva, observaciones, data, hora, formaPago);

		// HACE LOS INSERT DEL HEADER Y LAS FILAS
		fh.insertFacturaHeader(db);

		//AL HACER EL INSERT DEL HEADER TIENE QUE OBTENER EL NUM FACTURA
		int numFactura = Comprovaciones.returnNumFactura(db, dni, data, hora);
		fh.setNumPressupost(numFactura);
	
		
		// MODIFICA EL OBJETO FILA A�ADIENDOLE EL HEADER COMPLETO CON TODOS LOS VALORES
		for (FacturaFiles f : filas) {
			f.setFacturasHeader(fh);
		}

		//HACE EL INSERT A LA BBDD
		for (FacturaFiles f : filas) {
			f.insertFacturaFila(db);
		}
	}

	/**
	 * 
	 * Modifica la Factura
	 * 
	 */
	public static void ModificarFactura(Database db) {
		if (Utiles.siNo("Quieres Mostrar Todos los numeros de Facturas? ")) 
		{
			Comprovaciones.mostrarFacturas(db);
		}
		System.out.println("Inserta el codigo factura\n999 Per sortir: ");
		int codigo = Utiles.demanarNum();

		boolean existeix = Comprovaciones.comprobarNumFactura(codigo, db);

		// SI NO EXISTE VUELVE AL MAIN
		if (!existeix) {
			System.out.println("Num Factura No Existe: ");
			MotAuto.MenuPrincipal(db);
		}
		
		//CREA LOS OBJETOS HEADER Y FILA PARA TRABAJAR SOBRE ELLOS
		FacturaHeader fh = Comprovaciones.consultaFacturaHeader(codigo, db);
		ArrayList<FacturaFiles> ff = Comprovaciones.consultaFacturaFiles(fh, codigo, db);


		// OPCIONES
		System.out.println("1 - Modificar Header");
		//System.out.println("2 - Modificar Filas");
		int opcion = Utiles.menu(1);
		
		//IMPRIME LA FACTURA SELECCIONADA
		Comprovaciones.mostrarFacturasPerNum(fh, ff, db);


		switch (opcion) {
		case 1:
			System.out.println("1 - Modificar Cif Empresa");
			System.out.println("2 - Modificar Dni Cliente");
			System.out.println("3 - Modificar Fecha");
			System.out.println("4 - Modificar Hora");
			System.out.println("5 - Modificar Forma de Pago");
			System.out.println("6 - Modificar Matricula");
			System.out.println("7 - Modificar Estado");
			System.out.println("8 - Modificar Observaciones");
			System.out.println("9 - Volver");

			int opcion2 = Utiles.menu(9);
			
			
			switch (opcion2) {
			case 1:
				System.out.println("Inserta Nuevo CIF: ");
				String cif = Utiles.demanarString();
				
				fh.setCifEmpresa(cif);
				fh.modificarFactura(db);
				break;
				
			case 2:
				Comprovaciones.mostrarClientes(db);
				
				System.out.println("Inserta Nuevo DNI Cliente: ");
				String dni = Utiles.demanarString();
				
				if(!Comprovaciones.comprovarDni(dni, db)) 
				{
					System.out.println("DNI No existente Saliendo...");
					MotAuto.MenuPrincipal(db);
				}
				
				fh.setCliente(Comprovaciones.consultaClient(dni, db));
				fh.modificarFactura(db);
				break;
				
			case 3:
				LocalDate data = Utiles.demanarData();
				fh.setDataFactura(data);
				fh.modificarFactura(db);
				break;
				
			case 4:
				LocalTime hora = Utiles.demanarHora();
				fh.setHoraFactura(hora);
				fh.modificarFactura(db);
				break;
				
			case 5:
				System.out.println("Introduce nueva forma de pago: ");
				String pago = Utiles.demanarString();
				fh.setForma_pago(pago);
				fh.modificarFactura(db);
				break;
				
			case 6:
				System.out.println("Inserta nueva matricula: ");
				String matricula = Utiles.demanarString();
				
				if(!Comprovaciones.comprovarMatricula(matricula, db)) 
				{
					System.out.println("Matricula No existente Saliendo...");
					MotAuto.MenuPrincipal(db);
				}
				
				fh.setVehiculo(Comprovaciones.consultaVehiculo(matricula, db));
				fh.modificarFactura(db);
				break;
				
			case 7:
				System.out.println("Inserta nuevo estado \n0 - Por Pagar\n1 - Pagado: ");
				int estado = Utiles.menu(2);
				
				fh.setEstado(estado);
				fh.modificarFactura(db);
				break;
				
			case 8:
				System.out.println("Modificar Observaciones: ");
				String observaciones = Utiles.demanarString();
				
				fh.setObservaciones(observaciones);
				fh.modificarFactura(db);
				break;
				
			case 9:
				MotAuto.MenuPrincipal(db);
				break;
			}
		}
	}

	/**
	 * 
	 * @param esborrat
	 * @param numFactura
	 */
	public static void borrarFactura(Database db) {
		
		Comprovaciones.mostrarFacturas(db);
		System.out.println("Inserta num factura a borrar: ");
		int num = Utiles.demanarNum();
		
		if(!Comprovaciones.comprobarNumFactura(num, db)) 
		{
			System.out.println("Num Factura No Existe, Saliendo...");
			MotAuto.MenuPrincipal(db);
		}
		
		FacturaHeader fh = Comprovaciones.consultaFacturaHeader(num, db);
		ArrayList<FacturaFiles> ff = Comprovaciones.consultaFacturaFiles(fh, num, db);
		
		if(Utiles.siNo("El codigo de factura: "+num+" Es la factura del cliente con el dni: "+fh.getCliente().getDni()+" Del dia: "+fh.getDataFactura()+"\nSeguro que quieres borrarla?")) 
		{
			for(FacturaFiles f : ff) 
			{
				boolean ffBorrat = f.borrarFila(db);
				
				if(ffBorrat) 
				{
					System.out.println("La Fila Num: "+f.getNumFila()+" se ha borrado satisfactoriamente!");
				}
				else if(!ffBorrat) 
				{
					System.out.println("La Fila Num: "+f.getNumFila()+" NO SE ha borrado");
				}
			}		

			
			boolean fhBorrat = fh.borrarHeader(db);
			
			if(fhBorrat) 
			{
				System.out.println("El Header de la factura: "+fh.getNumPressupost()+" se ha borrado satisfactoriamente!");
			}
			else if(!fhBorrat) 
			{
				System.out.println("La Fila Num: "+fh.getNumPressupost()+" NO SE ha borrado");
			}			
		}
	}

	/**
	 * 
	 * @param ObjecteVehicle
	 * @return
	 */
	public static void ModificarVehiculo(Database db) {
		Vehiculo vehiculo = null;

		System.out.println("Menu:");
		boolean sortir2 = false;

		while (!sortir2) {
			// les tres opcions

			System.out.print("\n1: Modificar color ");
			System.out.print("\n2: Modificar Tipo vehiculo ");
			System.out.print("\n3: Sortir ");
			System.out.println("\nTria opcio: ");
			int menuGeneral1 = lector.nextInt();
			lector.nextLine();

			switch (menuGeneral1) {

			case 1:

				System.out.println("\nMatricula: ");
				String auxMatricula = lector.nextLine();
				if(!Comprovaciones.comprovarMatricula(auxMatricula, db)) {
					MotAuto.MenuPrincipal(db);
				}
				// comproave
				System.out.println("\nColor nou: ");
				String auxColor = lector.nextLine();
				vehiculo = Comprovaciones.consultaVehiculo(auxMatricula, db);
				vehiculo.setColor(auxColor);
				vehiculo.modificarVehiculo(db);
				break;
			case 2:
				System.out.println("\nMatricula: ");
				auxMatricula = lector.nextLine();
				if(!Comprovaciones.comprovarMatricula(auxMatricula, db)) {
					MotAuto.MenuPrincipal(db);
				}
				// comproave
				System.out.println("\n Tipo vehiculo nou: ");
				String auxtipus = lector.nextLine();
				vehiculo = Comprovaciones.consultaVehiculo(auxMatricula, db);
				vehiculo.setTipo_vehiculo(auxtipus);
				vehiculo.modificarVehiculo(db);

				break;

			case 3:
				// while en sortir true i sortim
				System.out.print("\nHas sortit de modificacions ");
				sortir2 = true;
				break;

			}
		}

	}

	/**
	 * 
	 * @param ObjecteClient
	 */
	public static void ModificarCliente(Database db) {
		Cliente cliente = null;
		if (Utiles.siNo("Quieres mostrar la lista de los clientes?")) {
			Comprovaciones.mostrarClientes(db);
		}
		System.out.println("Introduce el dni del cliente que quieres modificar: ");
		String dni = Utiles.demanarString();
		if (Comprovaciones.comprovarDni(dni, db)) {
			cliente = Comprovaciones.consultaClient(dni, db);
			System.out.println("El dni " + dni + " equivale a " + cliente.getNombre() + " " + cliente.getApellidos());
			System.out.println("Que quieres modificar?");
			System.out.println("1. Nombre / Apellidos");
			System.out.println("2. Correo / Telefono");
			System.out.println("3. Direccion");
			int opcion = Utiles.menu(3);
			switch (opcion) {
			case 1:
				System.out.println("Nuevo nombre: ");
				cliente.setNombre(Utiles.demanarString());
				System.out.println("Nuevo apellido: ");
				cliente.setApellidos(Utiles.demanarString());
				break;
			case 2:
				System.out.println("Nuevo correo: ");
				cliente.setCorreo(Utiles.demanarString());
				System.out.println("Nuevo telefono: ");
				cliente.setTelefono(Utiles.demanarNum());
				break;
			case 3:
				System.out.println("Nuevo direccion: ");
				cliente.setDireccion(Utiles.demanarString());
				break;
			}
			cliente.modificarCliente(db);

		} else {
			System.out.println("No existe un cliente con el dni " + dni);
		}
	}

	public static void ModificarAriculo(Database db) {

		Articulos articulo = null;
		if (Utiles.siNo("Quieres mostrar la lista de los articulos?")) {
			Comprovaciones.mostrarArticulos(db);
		}
		System.out.println("Introduce el codigo del aritculo que quieres modificar: ");
		String codigo = Utiles.demanarString();
		if (Comprovaciones.comprovarCodigoArticulo(codigo, db)) {
			articulo = Comprovaciones.consultaArticulo(codigo, db);
			System.out.println("El codigo articulo " + codigo + " equivale a " + articulo.getNombre());
			System.out.println("Que quieres modificar?");
			System.out.println("1. Nombre ");
			System.out.println("2. Precio");
			System.out.println("3. Iva");
			int opcion = Utiles.menu(3);
			switch (opcion) {
			case 1:
				System.out.println("Nuevo nombre: ");
				articulo.setNombre(Utiles.demanarString());
				break;
			case 2:
				System.out.println("Nuevo precio: ");
				articulo.setPrecio(Utiles.demanarNumFloat());
				break;
			case 3:
				System.out.println("Nuevo iva: ");
				articulo.setIva(Utiles.demanarNumFloat());
				break;
			}
			articulo.modificarArticulo(db);
		} else {
			System.out.println("No existe este codigo de articulo");
			ModificarAriculo(db);
		}

	}

}