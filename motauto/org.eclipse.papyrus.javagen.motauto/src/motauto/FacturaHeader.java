// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package motauto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/************************************************************/
/**
 * 
 */
 class FacturaHeader {
	private int numPressupost;
	private String cifEmpresa;
	private int estado;
	private float total;
	private Cliente cliente;
	private Vehiculo vehiculo;
	private ArrayList<FacturaFiles> arrayFacturaFiles;
	private String forma_pago;
	private float descuentoFactura;
	private float totalIva;
	private float totalFactura;
	private String observaciones;
	private LocalDate dataFactura;
	private LocalTime horaFactura;
				
	FacturaHeader(int numPressupost, String cifEmpresa, int estado, float total, Cliente cliente, Vehiculo vehiculo, ArrayList<FacturaFiles> arrayFacturaFiles, float descuentoFactura, float totalIva, float totalFactura, String observaciones, LocalDate dataFactura, LocalTime horaFactura, String forma_pago)
	{
		this.numPressupost = numPressupost;
		this.cifEmpresa = "C565845";
		this.estado = estado;
		this.total = total;
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.arrayFacturaFiles = arrayFacturaFiles;
		this.descuentoFactura = descuentoFactura;
		this.totalIva = totalIva;
		this.totalFactura = totalFactura;
		this.observaciones = observaciones;
		this.dataFactura = dataFactura;
		this.horaFactura = horaFactura;
		this.forma_pago = forma_pago;
	}
	
	FacturaHeader(){}
		
	
	public int getNumPressupost() {
		return numPressupost;
	}



	public void setNumPressupost(int numPressupost) {
		this.numPressupost = numPressupost;
	}



	public String getCifEmpresa() {
		return cifEmpresa;
	}



	public void setCifEmpresa(String cifEmpresa) {
		this.cifEmpresa = cifEmpresa;
	}



	public int getEstado() {
		return estado;
	}



	public void setEstado(int estado) {
		this.estado = estado;
	}



	public float getTotal() {
		return total;
	}



	public void setTotal(float total) {
		this.total = total;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public Vehiculo getVehiculo() {
		return vehiculo;
	}



	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}



	public ArrayList<FacturaFiles> getArrayFacturaFiles() {
		return arrayFacturaFiles;
	}



	public void setArrayFacturaFiles(ArrayList<FacturaFiles> arrayFacturaFiles) {
		this.arrayFacturaFiles = arrayFacturaFiles;
	}



	public float getDescuentoFactura() {
		return descuentoFactura;
	}



	public void setDescuentoFactura(float descuentoFactura) {
		this.descuentoFactura = descuentoFactura;
	}



	public float getTotalIva() {
		return totalIva;
	}



	public void setTotalIva(float totalIva) {
		this.totalIva = totalIva;
	}



	public float getTotalFactura() {
		return totalFactura;
	}



	public void setTotalFactura(float totalFactura) {
		this.totalFactura = totalFactura;
	}



	public String getObservaciones() {
		return observaciones;
	}



	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}



	public LocalDate getDataFactura() {
		return dataFactura;
	}



	public void setDataFactura(LocalDate dataFactura) {
		this.dataFactura = dataFactura;
	}



	public String getForma_pago() {
		return forma_pago;
	}


	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
	public LocalTime getHoraFactura() {
		return horaFactura;
	}

	public void setHoraFactura(LocalTime horaFactura) {
		this.horaFactura = horaFactura;
	}


	/**
	 * 
	 *  
	 */
	public boolean insertFacturaHeader(Database db) 
	{
		boolean creacionCorrecta = false;

		try 
		{
			creacionCorrecta = db.ExecuteUpdate("INSERT INTO facturas_header (cif_empresa,dni_cliente,fecha,hora,forma_pago,matricula,estado,total_base_imponible,descuento,total_iva,total_factura,observaciones) VALUES ('"+ this.cifEmpresa +"','"+ this.cliente.getDni() + "','"+ this.dataFactura +"','"+ this.horaFactura +"','"+ this.forma_pago + "','" + this.vehiculo.getMatricula() + "','" + this.estado + "','" + this.total + "','" + this.descuentoFactura + "','" + this.totalIva + "','" + this.totalFactura + "','" + this.observaciones + "');");			
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
	 *  Modifica La Factura
	 */
	public boolean modificarFactura(Database db) 
	{
		boolean updateCorrecto = false;

		try 
		{
			updateCorrecto = db.ExecuteUpdate("UPDATE facturas_header SET cif_empresa = '"+this.cifEmpresa+"', dni_cliente = '"+this.cliente.getDni()+"', fecha = '"+this.dataFactura+"', hora = '"+this.horaFactura+"', forma_pago = '"+this.forma_pago+"', matricula = '"+this.vehiculo.getMatricula()+"', estado = '"+this.estado+"', total_base_imponible = '"+this.total+"', descuento = '"+this.descuentoFactura+"', total_iva = '"+this.totalIva+"', total_factura = '"+this.totalFactura+"', observaciones = '"+this.observaciones+"' WHERE num_factura = '"+this.numPressupost+"';");			
			System.out.println(updateCorrecto ? "Insert Correcte" : "Insert Incorrecte");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return updateCorrecto;

	}
	/**
	 * Borra la factura
	 */
	public boolean borrarHeader(Database db) 
	{
		boolean updateCorrecto = false;

		try 
		{
			updateCorrecto = db.ExecuteUpdate("DELETE FROM facturas_header WHERE num_factura = '"+this.numPressupost+"';");			
			System.out.println(updateCorrecto ? "Borrado Correcto" : "Borrado Incorrecto");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return updateCorrecto;
	}
}