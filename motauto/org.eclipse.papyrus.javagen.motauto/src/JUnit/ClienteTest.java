package JUnit;

import static org.junit.Assert.*;
import org.junit.Test;
import motauto.AlterarEstructuraBBDD;
import motauto.Cliente;
import motauto.Comprovaciones;
import motauto.Database;

public class ClienteTest {

	Database db=null;
	@Test
	public void insertCliente() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = new Cliente("10203040A","nombre" , "apellido", "correo", 0, "direccion");
		boolean correcto = cliente.insertCliente(db);
		assertEquals(true,correcto);
	}
	
	@Test
	public void insertClienteDbNull() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = new Cliente("10203040A","nombre" , "apellido", "correo", 0, "direccion");
		boolean correcto = false;

		try {
		correcto = cliente.insertCliente(null);
		}
		catch(Exception e) {
		}
		assertEquals(false,correcto);
	}

	@Test
	public void modificarCliente() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = new Cliente("10203040A","nombrito" , "apellidito", "correo", 0, "direccion");
		boolean correcto  = cliente.modificarCliente(db);

		assertEquals(true,correcto);
	}
	
	@Test
	public void modificarClienteDbNull() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = new Cliente("10203040A","nombrito" , "apellidito", "correo", 0, "direccion");
		boolean correcto  = cliente.modificarCliente(null);

		assertEquals(false,correcto);
	}
	
	@Test
	public void comprovarDni() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = new Cliente("10203040A","nombrito" , "apellidito", "correo", 0, "direccion");

		boolean correcto = Comprovaciones.comprovarDni(cliente.getDni(), db);

		assertEquals(true,correcto);
	}
	
	@Test
	public void comprovarDni2() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = new Cliente("304060b","nombrito" , "apellidito", "correo", 0, "direccion");

		boolean correcto = Comprovaciones.comprovarDni(cliente.getDni(), db);

		assertEquals(false,correcto);
	}
	
	@Test
	public void comprovarDniDbNull() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = new Cliente("10203040A","nombrito" , "apellidito", "correo", 0, "direccion");

		boolean correcto = false;
		
		try {
			correcto = Comprovaciones.comprovarDni(cliente.getDni(), null);
		}
		catch(Exception e) {}
		
		assertEquals(false,correcto);
	}
	
	@Test
	public void comprovarDni2DbNull() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = new Cliente("304060b","nombrito" , "apellidito", "correo", 0, "direccion");
		boolean correcto = false;
		
		try {
		correcto = Comprovaciones.comprovarDni(cliente.getDni(), null);
		}
		catch(Exception e) {}

		assertEquals(false,correcto);
	}
	
	@Test
	public void consultaCliente() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		Cliente cliente = new Cliente("10203040A","nombre" , "apellido", "correo", 0, "direccion");
		Cliente clienteComprovar = Comprovaciones.consultaClient("10203040A", db);
		
		assertEquals(cliente.getDni(),clienteComprovar.getDni());
	}
	
	
	@Test
	public void consultaCliente2() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente clienteComprovar = null;
		try {
		clienteComprovar = Comprovaciones.consultaClient("10203040A", null);
		}
		catch(Exception e) {
			
		}
		assertEquals(null,clienteComprovar);
	}
	
	@Test
	public void consultaCliente3() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente clienteComprovar = null;
		try {
		clienteComprovar = Comprovaciones.consultaClient("304060B", null);
		}
		catch(Exception e) {
			
		}

		assertEquals(null,clienteComprovar);
	}
	
	
	@Test
	public void consultaCliente4() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente clienteComprovar = null;
		try {
		clienteComprovar = Comprovaciones.consultaClient(null, null);
		}
		catch(Exception e) {
			
		}

		assertEquals(null,clienteComprovar);
	}
	
	@Test
	public void consultaCliente5() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente clienteComprovar = null;
		try {
		clienteComprovar = Comprovaciones.consultaClient(null, db);
		}
		catch(Exception e) {
			
		}

		assertEquals(null,clienteComprovar);
	}
	

	@Test
	public void borrarCliente() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = new Cliente("10203040A","nombrito" , "apellidito", "correo", 0, "direccion");
		boolean correcto = false;
		correcto = cliente.borrarCliente(db);

		assertEquals(true,correcto);
	}
	
	@Test
	public void borrarClienteDbNull() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = new Cliente("10203040A","nombrito" , "apellidito", "correo", 0, "direccion");
		boolean correcto = false;
		correcto = cliente.borrarCliente(null);

		assertEquals(false,correcto);
	}

}
