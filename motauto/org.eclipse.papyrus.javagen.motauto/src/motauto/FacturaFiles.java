// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package motauto;

import java.sql.ResultSet;

import javafx.collections.ObservableList;

/************************************************************/
/**
 * 
 */
public class FacturaFiles {

	private int numFila;
	private int cantidad;
	private Articulos articulos;
	private FacturaHeader facturasHeader;
	private float descuento;
	private float precio_total;
	private String nombre;
	private float precio;
	private float iva;
	
	public FacturaFiles(int numFila, int cantidad, Articulos articulo,FacturaHeader facturasHeader, float descuento, float precio_total)
	{
		this.numFila = numFila;
		this.cantidad = cantidad;
		this.articulos = articulo;
		this.facturasHeader = facturasHeader;
		this.descuento = descuento;
		this.precio_total = precio_total;
	}
	
	public FacturaFiles(int cantidad, Articulos articulo,FacturaHeader facturasHeader, float descuento, float precio_total)
	{
		this.cantidad = cantidad;
		this.articulos = articulo;
		this.facturasHeader = facturasHeader;
		this.descuento = descuento;
		this.precio_total = precio_total;
	}
	
	public FacturaFiles(int cantidad, Articulos articulo, String nombre, float iva, float precio, FacturaHeader facturasHeader, float descuento, float precio_total)
	{
		this.cantidad = cantidad;
		this.articulos = articulo;
		this.setNombre(nombre);
		this.setIva(iva);
		this.setPrecio(precio);
		this.facturasHeader = facturasHeader;
		this.descuento = descuento;
		this.precio_total = precio_total;
	}

	public FacturaFiles() {
		// TODO Auto-generated constructor stub
	}

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Articulos getArticulos() {
		return articulos;
	}
	public void setArticulos(Articulos articulos) {
		this.articulos = articulos;
	}
	public FacturaHeader getFacturasHeader() {
		return facturasHeader;
	}
	public void setFacturasHeader(FacturaHeader facturasHeader) {
		this.facturasHeader = facturasHeader;
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento2) {
		this.descuento = descuento2;
	}

	public float getPrecio_total() {
		return precio_total;
	}

	public void setPrecio_total(float precio_total) {
		this.precio_total = precio_total;
	}
	
	public int getNumFila() {
		return numFila;
	}

	public void setNumFila(int numFila) {
		this.numFila = numFila;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public boolean insertFacturaFila(Database db) {
		boolean creacionCorrecta = false;

		try 
		{
		creacionCorrecta = db.ExecuteUpdate("INSERT INTO facturas_filas (num_factura,codigo_articulo,nombre,cantidad,descuento,precio_total) VALUES ('" + this.facturasHeader.getNumPressupost() + "','" + this.articulos.getCodigo() + "','" + this.articulos.getNombre() + "','"+ this.cantidad + "','"+ this.descuento + "','" + this.precio_total  + "');");			
		System.out.println(creacionCorrecta ? "Insert Correcte" : "Insert Incorrecte");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return creacionCorrecta;
	}

	/**
	 * 
	 *  Modifica La Fila de Factura
	 */
	public boolean modificarFilaFactura(Database db) 
	{
		boolean updateCorrecto = false;

		try 
		{
			updateCorrecto = db.ExecuteUpdate("UPDATE facturas_filas SET codigo_articulo = '"+this.articulos.getCodigo()+"', cantidad = '"+this.cantidad+"', descuento = '"+this.descuento+"', precio_total = '"+this.precio_total+"' WHERE num_fila = '"+this.numFila+"';");			
			System.out.println(updateCorrecto ? "Insert Correcte" : "Insert Incorrecte");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return updateCorrecto;
	}
	
	/**
	 * Borra las filas de la factura
	 */
	public boolean borrarFila(Database db) 
	{
		boolean updateCorrecto = false;

		try 
		{
			updateCorrecto = db.ExecuteUpdate("DELETE FROM facturas_filas WHERE num_factura = '"+this.facturasHeader.getNumPressupost()+"';");			
			System.out.println(updateCorrecto ? "Borrado Correcto" : "Borrado Incorrecto");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return updateCorrecto;
	}
}
