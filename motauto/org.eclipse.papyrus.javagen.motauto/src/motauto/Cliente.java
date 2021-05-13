package motauto;

public class Cliente {

	private String dni;
	private String nombre;
	private String apellidos;
	private String correo;
	private int telefono;
	private String direccion;

	// creem el constructor i li passem els paràmetres que fan falta

	public Cliente(String dni, String nombre, String apellidos, String correo, int telefono, String direccion) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	// getters i setters 
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

	
	/*public String columnes() {
		String columnes = "('dni','nombre','apellidos','correo','telefono','direccion')";
		return columnes;
	}*/

	//fem insert apartir de les variables del constructor a la db. Ens retorna true or false passat a text per saber si sha creat correctamet 
	public boolean insertCliente(Database db) {
		boolean correcto = false;

		try {
			correcto = db.ExecuteUpdate("INSERT INTO clientes VALUES ('" + this.dni + "','" + this.nombre + "','"
					+ this.apellidos + "','" + this.correo + "','" + this.telefono + "','" + this.direccion + "');");
			System.out.println(correcto ? "Insert Correcte" : "Insert Incorrecte");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return correcto;
	}
	//ajunteem codi i nombre en un sol String
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
	 * @return 
	 * Modifiquem un client apartir del seu dni 
	 */
	public boolean modificarCliente(Database db) {
		boolean correcto = false;
		try {
			correcto = db.ExecuteUpdate("UPDATE clientes SET dni='" + this.dni + "'," + "nombre='" + this.nombre + "',"
					+ "apellidos='" + this.apellidos + "',correo='" + this.correo + "',telefono='" + this.telefono
					+ "',direccion='" + this.direccion + "' WHERE dni = '" + this.dni + "';");
			System.out.println(correcto ? "Update Correcte" : "Update Incorrecte");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return correcto;
	}

	//borrem client apartir del DNI

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