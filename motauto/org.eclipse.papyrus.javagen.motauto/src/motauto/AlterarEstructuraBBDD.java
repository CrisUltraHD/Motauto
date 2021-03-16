package motauto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.ibatis.common.jdbc.ScriptRunner;

public class AlterarEstructuraBBDD {

	public static boolean createBDD() {
		boolean correct = false;
		try {
			if (Utiles.siNo("Seguro que quieres crear una nueva BDD?")) {
				Database db = AlterarEstructuraBBDD.configuracionBDD();
				Connection conex = db.conectToBDD();
				System.out.println(db.ExecuteCreate("CREATE DATABASE "+db.GetDB()) ? "Correcte" : "Incorrecte");
				conex = db.conectToBDD(db.GetDB());
				ScriptRunner runner = new ScriptRunner(conex, false, false);
				InputStreamReader reader;

				reader = new InputStreamReader(new FileInputStream("Scripts\\CreacionBaseDatos.sql"), "UTF8");

				runner.runScript(reader);
				reader.close();
				correct = true;
			}
			
		} catch (Exception e) {
			correct = false;
		}
		return correct;
	}

	public static boolean updateBDD(Database db) {
		boolean correct;
		try {
			String ruta = "Scripts\\";
			File carpeta = new File(ruta);
			String[] nomDelsFichers = carpeta.list();
			Connection conex = db.GetConnection();
			ScriptRunner runner = new ScriptRunner(conex, false, false);
			InputStreamReader reader;
			System.out.println("Posa el nom del Script a Carregar: ");
			for (int i =0; i < nomDelsFichers.length;i++) {
				System.out.println("- " +nomDelsFichers[i]);
			}
			reader = new InputStreamReader(new FileInputStream("Scripts\\"+Utiles.demanarString()+".sql"), "UTF8");
			runner.runScript(reader);
			reader.close();
			correct = true;
		} catch (Exception e) {
			correct = false;
		}
		return correct;
	}

	public static Database configuracionBDD() {
		String ruta = "Scripts\\datosConex.txt";
		File archivo = new File(ruta);
		int opcio;
		BufferedWriter bw;
		String driver = "org.mariadb.jdbc.Driver";
		System.out.println("Diges la base de dades on treballaras: ");
		String db = Utiles.demanarString();
		System.out.println("Diges l'usuari de la base de dades: ");
		String user = Utiles.demanarString();
		System.out.println("Diges la contrasenya de la base de dades: ");
		String passwd = Utiles.demanarString();
		System.out.println("Diges la IP de la base de dades: (nomes la Ip)");
		String ip = Utiles.demanarString();
		String url = "jdbc:mysql://" + ip + "/";
		System.out.println("Quin es la base de dades utilitzada? ");
		System.out.println("0- MariaDB ");
		System.out.println("1- PostGress ");
		System.out.println("2- MySQL ");

		opcio = Utiles.menu(3);
		switch (opcio) {
		case 0:
			driver = "org.mariadb.jdbc.Driver";
			break;
		case 1:
			driver = "org.postgresql.Driver";
			break;
		case 2:
			driver = "com.mysql.jdbc.Driver";
			break;

		}
		try {
			bw = new BufferedWriter(new FileWriter(archivo));
			bw.write(db+"#"+user+"#"+passwd+"#"+url+"#"+driver);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Database conexion = new Database(db, user, passwd, url, driver);
		System.out.println(conexion.connectDatabase() ? "Conexio Correcte" : "Conexio Incorrecte");
		return conexion;

	}

	public static void showTables(Database db) {
		ArrayList<String> tables = new ArrayList<String>();
		
		try {
			ResultSet rs = db.ExecuteQuery("SHOW TABLES");
			while (rs.next()) {
				tables.add(rs.getString(1));
			}
			for (int i = 0 ; i<tables.size();i++) {
			rs = db.ExecuteQuery("SHOW COLUMNS FROM "+tables.get(i));
			System.out.println("- "+tables.get(i));
			while (rs.next()) {
				System.out.print(rs.getString(1) +" / ");
			}
			System.out.println("\n");
			}

		} catch (Exception e) {

		}
	}
	public static void sqlInyector(Database db) {
		ArrayList<String> commands = new ArrayList<String>();
		String text="";
		System.out.println("Escribe las comandas para inyectar en la BDD seguidas de ';' i enter.");
		while (!text.equalsIgnoreCase("Execute")) {
			text=Utiles.demanarString();
			if (!text.equalsIgnoreCase("Execute"))
			commands.add(text);
		}
		for (int i = 0 ; i<commands.size();i++) {
			 try {
				System.out.println(db.ExecuteUpdate(commands.get(i)) ? "Correcte" : "Correcte");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static Database establecerPrimeraConexion() throws FileNotFoundException {
		String ruta = "Scripts\\datosConex.txt";
		File archivo = new File(ruta);
		Database db=null;	
		if(archivo.exists()) {	
			Scanner scanner;
				scanner = new Scanner(archivo);
					String linea = scanner.nextLine();
					String[] datos = linea.split("#");
				scanner.close();
				db = new Database(datos[0], datos[1], datos[2], datos[3], datos[4]);
				System.out.println(db.connectDatabase() ? "Prueba de Conexion positiva." : "Error en la Conexion");
		} else {
		    System.out.println("No emos detectado un archivo de conexion existente, vamos a crearlo.");
		    db = AlterarEstructuraBBDD.configuracionBDD();
		    System.out.println(db.connectDatabase() ? "Prueba de Conexion positiva." : "Error en la Conexion");
		}
		return db;
	}
	

}
