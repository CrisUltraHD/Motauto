// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package motauto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javafx.collections.ObservableList;

/************************************************************/
/**
 * 
 */
public class Vehiculo {
	static Scanner lector = new Scanner(System.in);

	private String matricula;

	private String color;

	private String tipo_vehiculo;

	private Cliente cliente;

	public Vehiculo (String matricula, String color, String tipo_vehiculo, Cliente cliente) {
		this.matricula = matricula;
		this.color = color;
		this.tipo_vehiculo = tipo_vehiculo;
		this.cliente = cliente;
	}
	
	Vehiculo(){}

	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTipo_vehiculo() {
		return tipo_vehiculo;
	}

	public void setTipo_vehiculo(String tipo_vehiculo) {
		this.tipo_vehiculo = tipo_vehiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public void insertVehiculo(Database db) {
		boolean insertCorrecte = false;

		try {
			insertCorrecte = db.ExecuteUpdate("INSERT INTO vehiculo (matricula,dni,color,tipo) values ('" + this.matricula + "', '" + this.cliente.getDni() + "', '" + this.color + "','"
					+ tipo_vehiculo + "');");
			System.out.println(insertCorrecte ? "Insert Correcte" : "Insert Incorrecte");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	public void modificarVehiculo(Database db) {
				
	boolean correcto;
		try 
		{
		correcto = db.ExecuteUpdate("UPDATE vehiculo SET color = '"+this.color+"', tipo = '"+this.tipo_vehiculo+"', dni = '"+ cliente.getDni() +"' WHERE matricula = '"+this.matricula+"';");			
		System.out.println(correcto ? "Update Correcte" : "Update Incorrecte");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


	}
	public void borrarVehiculo(Database db) {
		boolean correcto;
		try 
		{
		correcto = db.ExecuteUpdate("DELETE FROM vehiculo WHERE matricula='"+this.matricula+"';");			
		System.out.println(correcto ? "Delete Correcte" : "Delete Incorrecte");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public void imprimirVehicle() {
		System.out.println(this.matricula);
		System.out.println(this.color);
		System.out.println(this.tipo_vehiculo);
		cliente.ImprimirClient();
	}
	
	@Override
	public String toString() 
	{
		return this.matricula + " " + this.tipo_vehiculo;
	}
}
