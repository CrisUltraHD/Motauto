// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package motauto;

import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.ObservableList;

/************************************************************/
/**
 * 
 */
public class Articulos {

	private String codigo;
	private String nombre;
	private float precio;
	private float iva;

	public Articulos (String codigo, String nombre, float precio, float iva){
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.iva = iva;
	}


	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public void insertArticulo(Database db) {
		boolean creacionCorrecta = false;

		try 
		{
		creacionCorrecta = db.ExecuteUpdate("INSERT INTO articulos VALUES ('"+ this.codigo +"','"+ this.nombre + "','"+ this.precio +"','"+ this.iva + "');");			
		System.out.println(creacionCorrecta ? "Insert Correcte" : "Insert Incorrecte");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void modificarArticulo(Database db) {
		boolean creacionCorrecta = false;

		try 
		{
		creacionCorrecta = db.ExecuteUpdate("UPDATE articulos SET nombre='"+this.nombre+"',"+"precio='"+this.precio+"',"+"iva='"+this.iva+"' WHERE codigo = '"+this.codigo+"';");			
		System.out.println(creacionCorrecta ? "Update Correcte" : "Update Incorrecte");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() 
	{
		return this.codigo + " " + this.nombre;
	}
	
	public  void ImprimirArticulo() {
		
		System.out.println("------------------------------");
		System.out.println(nombre);
		System.out.println("Con codigo: " + codigo);
		System.out.println("Precio: " + precio + "�");
		System.out.println("Iva: " + iva + "%");
	}
	
	public void borrarArticulo(Database db) {
		boolean correcto;
		try 
		{
		correcto = db.ExecuteUpdate("DELETE FROM articulos WHERE codigo='"+this.codigo+"';");			
		System.out.println(correcto ? "Delete Correcte" : "Delete Incorrecte");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Articulos> getArticulos(Database db) {
		
		ArrayList<Articulos> art = new ArrayList<Articulos>();
		
		try 
		{
			ResultSet rs = db.ExecuteQuery("SELECT * FROM articulos");
			
			while(rs.next()) 
			{
				art.add(
				new Articulos(rs.getString(1),rs.getString(2),rs.getFloat(3),rs.getFloat(4)));
			}
			
			rs.close();

		}
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return art;
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

};
