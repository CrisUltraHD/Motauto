package motauto;

import java.sql.ResultSet;

import javafx.collections.ObservableList;

public class Cliente {

	private String dni;
	private String nombre;
	private String apellidos;
	private String correo;
	private int telefono;
	private String direccion;

	public Cliente(String dni, String nombre, String apellidos, String correo, int telefono, String direccion) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String columnes() {
		String columnes = "('dni','nombre','apellidos','correo','telefono','direccion')";
		return columnes;
	}

	public void insertCliente(Database db) {
		boolean correcto = false;

		try {
			correcto = db.ExecuteUpdate("INSERT INTO clientes VALUES ('" + this.dni + "','" + this.nombre + "','"
					+ this.apellidos + "','" + this.correo + "','" + this.telefono + "','" + this.direccion + "');");
			System.out.println(correcto ? "Insert Correcte" : "Insert Incorrecte");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public String toString() 
	{
		return this.dni + " " + this.nombre;
	}

	public void ImprimirClient() {
		System.out.println("------------------------------");
		System.out.println(nombre + " " + apellidos);
		System.out.println("Con dni: " + dni);
		System.out.println("Correo: " + correo);
		System.out.println("Tlf: " + telefono);
		System.out.println("Direccion: " + direccion);
	}

	/**
	 * 
	 */
	public void modificarCliente(Database db) {
		boolean correcto;
		try {
			correcto = db.ExecuteUpdate("UPDATE clientes SET dni='" + this.dni + "'," + "nombre='" + this.nombre + "',"
					+ "apellidos='" + this.apellidos + "',correo='" + this.correo + "',telefono='" + this.telefono
					+ "',direccion='" + this.direccion + "' WHERE dni = '" + this.dni + "';");
			System.out.println(correcto ? "Update Correcte" : "Update Incorrecte");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean borrarCliente(Database db) {
		boolean correcto = false;
		try {
			correcto = db.ExecuteUpdate("DELETE FROM clientes WHERE dni='" + this.dni + "';");
			System.out.println(correcto ? "Delete Correcte" : "Delete Incorrecte");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return correcto;
	}
	
};