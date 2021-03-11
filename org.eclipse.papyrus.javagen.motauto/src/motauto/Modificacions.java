// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package motauto;

/**
 * 
 */
public class Modificacions {
	
	public static void altaCliente() {
		String dni, nombre, apellidos, correo, direccion;
		int telefono;
		System.out.println("Dni cliente: ");
		dni = Utiles.demanarString();
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
	}

	/**
	 * 
	 * @param ObjectArticulo 
	 */
	public static void altaArticulo() {
		String codigo, nombre;
		float precio, iva;
		Database db = new Database();
		
		System.out.println("Codigo Articulo: ");
		codigo = Utiles.demanarString();
		
		boolean existe = Comprovaciones.comprovarCodigoArticulo(codigo);
		if(existe) 
		{
			System.out.println("CODIGO ARTICULO YA EXISTE");
		}
		else if(!existe) 
		{
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
	public static void altaVehiculo() {
		String matricula, dni, color, tipo;
		System.out.println("Matricula Vehiculo: ");
		matricula = Utiles.demanarString(); 
		System.out.println("Dni client: ");
		dni = Utiles.demanarString();
		System.out.println("Color Vehiculo: ");
		color = Utiles.demanarString();
		System.out.println("Tipo Vehiculo: ");
		tipo = Utiles.demanarString();
		Vehiculo vehiculo = new Vehiculo();
	}

	/**
	 * 
	 * @param dni 
	 */
	public static void borrarCliente(String dni) {
		
	}

	/**
	 * 
	 * @param codigoArticulo 
	 */
	public static void borrarArticulo(String codigoArticulo) {
	}

	/**
	 * 
	 * @param matricula 
	 */
	public static void borrarVehiculo(String matricula) {
	}

	/**
	 * 
	 * @param cantidadArticulo 
	 * @param precioArticulo 
	 * @param iva 
	 * @param Descuento 
	 */
	public static void calcularTotal(int cantidadArticulo, float precioArticulo,float iva, int Descuento) {
	
	}

	/**
	 * 
	 * @param FacturaHeader 
	 * @param FacturaFiles 
	 */
	public static void altaFactura(FacturaHeader FacturaHeader, FacturaFiles FacturaFiles) {
	}

	/**
	 * 
	 * @param esborrat 
	 * @param numFactura 
	 */
	public static void borrarFactura(boolean esborrat, String numFactura) {
	}

	/**
	 * 
	 * @param ObjecteVehicle 
	 * @return 
	 */
	public static Object ModificarVehiculo() 
	{
		Vehiculo objecteVehicle;
		return objecteVehicle;
	}

	/**
	 * 
	 * @param FacturaHeader 
	 * @param FacturaFiles 
	 */
	public static void ModificarFactura(FacturaHeader FacturaHeader, FacturaFiles FacturaFiles) {
	}

	/**
	 * 
	 * @param ObjecteClient 
	 */
	public static Object ModificarCliente() 
	{
		Cliente objecteClient;
		return objecteClient;
	}

	/**
	 * 
	 * @param ObjectArticulo 
	 */
	public static Object ModificarAriculo() 
	{
		Articulos objectArticulo;
		return objectArticulo;
	}
	
;