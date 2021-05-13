package JUnit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import motauto.AlterarEstructuraBBDD;
import motauto.Cliente;
import motauto.Comprovaciones;
import motauto.Database;
import motauto.Vehiculo;

public class VehiculoTest {
	
	Database db = null;

	@Test
	public void insertVehiculo() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = Comprovaciones.consultaClient("41529675r", db);
		Vehiculo vehiculo = new Vehiculo("9173PEP","Blanco","Moto",cliente);
		boolean correcto = vehiculo.insertVehiculo(db);
		assertEquals(true,correcto);
	}
	
	@Test
	public void insertVehiculo2() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = Comprovaciones.consultaClient("41529675r", db);
		Vehiculo vehiculo = new Vehiculo("9173PEP","Blanco","Moto",cliente);
		boolean correcto = false;

		try {
		correcto = vehiculo.insertVehiculo(null);
		}
		catch(Exception e) {
		}
		assertEquals(false,correcto);
	}

	@Test
	public void modificarVehiculo() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = Comprovaciones.consultaClient("41529675r", db);
		Vehiculo vehiculo = new Vehiculo("9173PEP","Blanquito","Motito",cliente);
		boolean correcto = false;
		correcto = vehiculo.modificarVehiculo(db);

		assertEquals(true,correcto);
	}
	
	@Test
	public void modificarVehiculo2() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = Comprovaciones.consultaClient("41529675r", db);
		Vehiculo vehiculo = new Vehiculo("9173PEP","Blanquito","Motito",cliente);
		boolean correcto = false;

		try {
		correcto = vehiculo.modificarVehiculo(null);
		}
		catch(Exception e) {
		}
		assertEquals(false,correcto);
	}

	@Test
	public void comprovarMatricula() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = Comprovaciones.consultaClient("41529675r", db);
		Vehiculo vehiculo = new Vehiculo("9173PEP","Blanquito","Motito",cliente);

		boolean correcto = Comprovaciones.comprovarMatricula(vehiculo.getMatricula(), db);

		assertEquals(true,correcto);
	}
	
	@Test
	public void comprovarMatricula2() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = Comprovaciones.consultaClient("41529675r", db);
		Vehiculo vehiculo = new Vehiculo("1","Blanquito","Motito",cliente);

		boolean correcto = Comprovaciones.comprovarMatricula(vehiculo.getMatricula(), db);

		assertEquals(false,correcto);
	}
	
	@Test
	public void comprovarMatricula3() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = Comprovaciones.consultaClient("41529675r", db);
		Vehiculo vehiculo = new Vehiculo("9173PEP","Blanquito","Motito",cliente);

		boolean correcto = false;
		try {
		correcto = Comprovaciones.comprovarMatricula(vehiculo.getMatricula(), null);
		}
		catch(Exception e) {
		}
		assertEquals(false,correcto);
	}
	
	@Test
	public void comprovarMatricula4() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = Comprovaciones.consultaClient("41529675r", db);
		Vehiculo vehiculo = new Vehiculo("1","Blanquito","Motito",cliente);

		boolean correcto = false;
		try {
		correcto = Comprovaciones.comprovarMatricula(vehiculo.getMatricula(), null);
		}
		catch(Exception e) {
		}
		assertEquals(false,correcto);
	}
	
	@Test
	public void consultaVehiculo() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		Cliente cliente = Comprovaciones.consultaClient("41529675r", db);
		Vehiculo vehiculoBueno = new Vehiculo("9173PEP","Blanco","Moto",cliente);
		
		Vehiculo vehiculoComprovar = Comprovaciones.consultaVehiculo("9173PEP", db);
		

		assertEquals(vehiculoBueno.getMatricula(),vehiculoComprovar.getMatricula());
	}
	
	
	@Test
	public void consultaVehiculo2() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		Vehiculo vehiculoComprovar = null;
		try {
			vehiculoComprovar = Comprovaciones.consultaVehiculo("9173PEP", null);		
		}
		catch(Exception e) {
			
		}
		
		assertEquals(null,vehiculoComprovar);
	}
	
	@Test
	public void consultaVehiculo3() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		Vehiculo vehiculoComprovar = null;
		try {
			vehiculoComprovar = Comprovaciones.consultaVehiculo("91A", null);		
		}
		catch(Exception e) {
			
		}
		
		assertEquals(null,vehiculoComprovar);
	}
	
	@Test
	public void borrarVehiculo() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = Comprovaciones.consultaClient("41529675r", db);
		Vehiculo vehiculo = new Vehiculo("9173PEP","Blanquito","Motito",cliente);
		boolean correcto = false;
		correcto = vehiculo.borrarVehiculo(db);

		assertEquals(true,correcto);
	}
	
	@Test
	public void borrarVehiculo2() {
		try 
		{
			db = AlterarEstructuraBBDD.establecerPrimeraConexion();
			db.connectDatabase();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		Cliente cliente = Comprovaciones.consultaClient("41529675r", db);
		Vehiculo vehiculo = new Vehiculo("9173PEP","Blanquito","Motito",cliente);
		boolean correcto = false;

		try {
		correcto = vehiculo.borrarVehiculo(null);
		}
		catch(Exception e) {
		}
		assertEquals(false,correcto);
	}
	

}
